package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer;
import com.facebook.share.internal.OpenGraphJSONUtility;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.LikeView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShareInternalUtility {
    public static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String STAGING_PARAM = "file";

    public static void invokeCallbackWithException(FacebookCallback<Sharer.Result> facebookCallback, Exception exc) {
        if (exc instanceof FacebookException) {
            invokeOnErrorCallback(facebookCallback, (FacebookException) exc);
        } else {
            invokeCallbackWithError(facebookCallback, "Error preparing share content: " + exc.getLocalizedMessage());
        }
    }

    public static void invokeCallbackWithError(FacebookCallback<Sharer.Result> facebookCallback, String str) {
        invokeOnErrorCallback(facebookCallback, str);
    }

    public static void invokeCallbackWithResults(FacebookCallback<Sharer.Result> facebookCallback, String str, GraphResponse graphResponse) {
        FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            String errorMessage = error.getErrorMessage();
            if (Utility.isNullOrEmpty(errorMessage)) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(facebookCallback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(facebookCallback, str);
    }

    public static String getNativeDialogCompletionGesture(Bundle bundle) {
        if (bundle.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY)) {
            return bundle.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
        }
        return bundle.getString(NativeProtocol.EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getShareDialogPostId(Bundle bundle) {
        if (bundle.containsKey(ShareConstants.RESULT_POST_ID)) {
            return bundle.getString(ShareConstants.RESULT_POST_ID);
        }
        if (bundle.containsKey(ShareConstants.EXTRA_RESULT_POST_ID)) {
            return bundle.getString(ShareConstants.EXTRA_RESULT_POST_ID);
        }
        return bundle.getString(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
    }

    public static boolean handleActivityResult(int i, int i2, Intent intent, ResultProcessor resultProcessor) {
        AppCall appCallFromActivityResult = getAppCallFromActivityResult(i, i2, intent);
        if (appCallFromActivityResult == null) {
            return false;
        }
        NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCallFromActivityResult.getCallId());
        if (resultProcessor == null) {
            return true;
        }
        FacebookException exceptionFromErrorData = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(intent));
        if (exceptionFromErrorData == null) {
            resultProcessor.onSuccess(appCallFromActivityResult, NativeProtocol.getSuccessResultsFromIntent(intent));
            return true;
        } else if (exceptionFromErrorData instanceof FacebookOperationCanceledException) {
            resultProcessor.onCancel(appCallFromActivityResult);
            return true;
        } else {
            resultProcessor.onError(appCallFromActivityResult, exceptionFromErrorData);
            return true;
        }
    }

    public static ResultProcessor getShareResultProcessor(final FacebookCallback<Sharer.Result> facebookCallback) {
        return new ResultProcessor(facebookCallback) {
            public void onSuccess(AppCall appCall, Bundle bundle) {
                if (bundle != null) {
                    String nativeDialogCompletionGesture = ShareInternalUtility.getNativeDialogCompletionGesture(bundle);
                    if (nativeDialogCompletionGesture == null || "post".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                        ShareInternalUtility.invokeOnSuccessCallback(facebookCallback, ShareInternalUtility.getShareDialogPostId(bundle));
                    } else if ("cancel".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                        ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
                    } else {
                        ShareInternalUtility.invokeOnErrorCallback((FacebookCallback<Sharer.Result>) facebookCallback, new FacebookException(NativeProtocol.ERROR_UNKNOWN_ERROR));
                    }
                }
            }

            public void onCancel(AppCall appCall) {
                ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
            }

            public void onError(AppCall appCall, FacebookException facebookException) {
                ShareInternalUtility.invokeOnErrorCallback((FacebookCallback<Sharer.Result>) facebookCallback, facebookException);
            }
        };
    }

    private static AppCall getAppCallFromActivityResult(int i, int i2, Intent intent) {
        UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            return null;
        }
        return AppCall.finishPendingCall(callIdFromIntent, i);
    }

    public static void registerStaticShareCallback(final int i) {
        CallbackManagerImpl.registerStaticCallback(i, new CallbackManagerImpl.Callback() {
            public boolean onActivityResult(int i, Intent intent) {
                return ShareInternalUtility.handleActivityResult(i, i, intent, ShareInternalUtility.getShareResultProcessor(null));
            }
        });
    }

    public static void registerSharerCallback(final int i, CallbackManager callbackManager, final FacebookCallback<Sharer.Result> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl) callbackManager).registerCallback(i, new CallbackManagerImpl.Callback() {
            public boolean onActivityResult(int i, Intent intent) {
                return ShareInternalUtility.handleActivityResult(i, i, intent, ShareInternalUtility.getShareResultProcessor(facebookCallback));
            }
        });
    }

    public static List<String> getPhotoUrls(SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            List<SharePhoto> photos = sharePhotoContent.getPhotos();
            if (photos != null) {
                List<K> map = Utility.map(photos, new Utility.Mapper<SharePhoto, NativeAppCallAttachmentStore.Attachment>() {
                    public NativeAppCallAttachmentStore.Attachment apply(SharePhoto sharePhoto) {
                        return ShareInternalUtility.getAttachment(uuid, sharePhoto);
                    }
                });
                List<String> map2 = Utility.map(map, new Utility.Mapper<NativeAppCallAttachmentStore.Attachment, String>() {
                    public String apply(NativeAppCallAttachmentStore.Attachment attachment) {
                        return attachment.getAttachmentUrl();
                    }
                });
                NativeAppCallAttachmentStore.addAttachments(map);
                return map2;
            }
        }
        return null;
    }

    public static String getVideoUrl(ShareVideoContent shareVideoContent, UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.getVideo() == null) {
            return null;
        }
        NativeAppCallAttachmentStore.Attachment createAttachment = NativeAppCallAttachmentStore.createAttachment(uuid, shareVideoContent.getVideo().getLocalUrl());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(createAttachment);
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        return createAttachment.getAttachmentUrl();
    }

    public static List<Bundle> getMediaInfos(ShareMediaContent shareMediaContent, final UUID uuid) {
        if (shareMediaContent != null) {
            List<ShareMedia> media = shareMediaContent.getMedia();
            if (media != null) {
                final ArrayList arrayList = new ArrayList();
                List<Bundle> map = Utility.map(media, new Utility.Mapper<ShareMedia, Bundle>() {
                    public Bundle apply(ShareMedia shareMedia) {
                        NativeAppCallAttachmentStore.Attachment access$000 = ShareInternalUtility.getAttachment(uuid, shareMedia);
                        arrayList.add(access$000);
                        Bundle bundle = new Bundle();
                        bundle.putString(ShareConstants.MEDIA_TYPE, shareMedia.getMediaType().name());
                        bundle.putString(ShareConstants.MEDIA_URI, access$000.getAttachmentUrl());
                        return bundle;
                    }
                });
                NativeAppCallAttachmentStore.addAttachments(arrayList);
                return map;
            }
        }
        return null;
    }

    public static JSONObject toJSONObjectForCall(final UUID uuid, ShareOpenGraphContent shareOpenGraphContent) {
        HashSet jsonArrayToSet;
        ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = OpenGraphJSONUtility.toJSONObject(action, (OpenGraphJSONUtility.PhotoJSONProcessor) new OpenGraphJSONUtility.PhotoJSONProcessor() {
            public JSONObject toJSONObject(SharePhoto sharePhoto) {
                NativeAppCallAttachmentStore.Attachment access$000 = ShareInternalUtility.getAttachment(uuid, sharePhoto);
                if (access$000 == null) {
                    return null;
                }
                arrayList.add(access$000);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", access$000.getAttachmentUrl());
                    if (!sharePhoto.getUserGenerated()) {
                        return jSONObject;
                    }
                    jSONObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    return jSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", (Throwable) e);
                }
            }
        });
        NativeAppCallAttachmentStore.addAttachments(arrayList);
        if (shareOpenGraphContent.getPlaceId() != null && Utility.isNullOrEmpty(jSONObject.optString("place"))) {
            jSONObject.put("place", shareOpenGraphContent.getPlaceId());
        }
        if (shareOpenGraphContent.getPeopleIds() != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("tags");
            if (optJSONArray == null) {
                jsonArrayToSet = new HashSet();
            } else {
                jsonArrayToSet = Utility.jsonArrayToSet(optJSONArray);
            }
            for (String add : shareOpenGraphContent.getPeopleIds()) {
                jsonArrayToSet.add(add);
            }
            jSONObject.put("tags", new JSONArray(jsonArrayToSet));
        }
        return jSONObject;
    }

    public static JSONObject toJSONObjectForWeb(ShareOpenGraphContent shareOpenGraphContent) {
        return OpenGraphJSONUtility.toJSONObject(shareOpenGraphContent.getAction(), (OpenGraphJSONUtility.PhotoJSONProcessor) new OpenGraphJSONUtility.PhotoJSONProcessor() {
            public JSONObject toJSONObject(SharePhoto sharePhoto) {
                Uri imageUrl = sharePhoto.getImageUrl();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", imageUrl.toString());
                    return jSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", (Throwable) e);
                }
            }
        });
    }

    public static JSONArray removeNamespacesFromOGJsonArray(JSONArray jSONArray, boolean z) {
        JSONArray jSONArray2 = new JSONArray();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jSONArray.length()) {
                return jSONArray2;
            }
            Object obj = jSONArray.get(i2);
            if (obj instanceof JSONArray) {
                obj = removeNamespacesFromOGJsonArray((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = removeNamespacesFromOGJsonObject((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
            i = i2 + 1;
        }
    }

    public static JSONObject removeNamespacesFromOGJsonObject(JSONObject jSONObject, boolean z) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    jSONObject2 = removeNamespacesFromOGJsonObject((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    jSONObject2 = removeNamespacesFromOGJsonArray((JSONArray) obj, true);
                } else {
                    jSONObject2 = obj;
                }
                Pair<String, String> fieldNameAndNamespaceFromFullName = getFieldNameAndNamespaceFromFullName(string);
                String str = (String) fieldNameAndNamespaceFromFullName.first;
                String str2 = (String) fieldNameAndNamespaceFromFullName.second;
                if (z) {
                    if (str != null && str.equals("fbsdk")) {
                        jSONObject3.put(string, jSONObject2);
                    } else if (str == null || str.equals("og")) {
                        jSONObject3.put(str2, jSONObject2);
                    } else {
                        jSONObject4.put(str2, jSONObject2);
                    }
                } else if (str == null || !str.equals("fb")) {
                    jSONObject3.put(str2, jSONObject2);
                } else {
                    jSONObject3.put(string, jSONObject2);
                }
            }
            if (jSONObject4.length() > 0) {
                jSONObject3.put(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONObject4);
            }
            return jSONObject3;
        } catch (JSONException e) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> getFieldNameAndNamespaceFromFullName(String str) {
        String str2 = null;
        int indexOf = str.indexOf(58);
        if (indexOf != -1 && str.length() > indexOf + 1) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        }
        return new Pair<>(str2, str);
    }

    /* access modifiers changed from: private */
    public static NativeAppCallAttachmentStore.Attachment getAttachment(UUID uuid, ShareMedia shareMedia) {
        Uri uri;
        Bitmap bitmap;
        if (shareMedia instanceof SharePhoto) {
            SharePhoto sharePhoto = (SharePhoto) shareMedia;
            bitmap = sharePhoto.getBitmap();
            uri = sharePhoto.getImageUrl();
        } else if (shareMedia instanceof ShareVideo) {
            uri = ((ShareVideo) shareMedia).getLocalUrl();
            bitmap = null;
        } else {
            uri = null;
            bitmap = null;
        }
        if (bitmap != null) {
            return NativeAppCallAttachmentStore.createAttachment(uuid, bitmap);
        }
        if (uri != null) {
            return NativeAppCallAttachmentStore.createAttachment(uuid, uri);
        }
        return null;
    }

    static void invokeOnCancelCallback(FacebookCallback<Sharer.Result> facebookCallback) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, null);
        if (facebookCallback != null) {
            facebookCallback.onCancel();
        }
    }

    static void invokeOnSuccessCallback(FacebookCallback<Sharer.Result> facebookCallback, String str) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED, null);
        if (facebookCallback != null) {
            facebookCallback.onSuccess(new Sharer.Result(str));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> facebookCallback, GraphResponse graphResponse, String str) {
        logShareResult("error", str);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookGraphResponseException(graphResponse, str));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> facebookCallback, String str) {
        logShareResult("error", str);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookException(str));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> facebookCallback, FacebookException facebookException) {
        logShareResult("error", facebookException.getMessage());
        if (facebookCallback != null) {
            facebookCallback.onError(facebookException);
        }
    }

    private static void logShareResult(String str, String str2) {
        AppEventsLogger newLogger = AppEventsLogger.newLogger(FacebookSdk.getApplicationContext());
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_SHARE_OUTCOME, str);
        if (str2 != null) {
            bundle.putString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, str2);
        }
        newLogger.logSdkEvent(AnalyticsEvents.EVENT_SHARE_RESULT, null, bundle);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Bitmap bitmap, GraphRequest.Callback callback) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(STAGING_PARAM, bitmap);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) {
        GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType(ParcelFileDescriptor.open(file, 268435456), "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(STAGING_PARAM, parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Uri uri, GraphRequest.Callback callback) {
        if (Utility.isFileUri(uri)) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(uri.getPath()), callback);
        }
        if (!Utility.isContentUri(uri)) {
            throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
        }
        GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType(uri, "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(STAGING_PARAM, parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, bundle, HttpMethod.POST, callback);
    }

    @Nullable
    public static LikeView.ObjectType getMostSpecificObjectType(LikeView.ObjectType objectType, LikeView.ObjectType objectType2) {
        if (objectType == objectType2) {
            return objectType;
        }
        if (objectType == LikeView.ObjectType.UNKNOWN) {
            return objectType2;
        }
        if (objectType2 != LikeView.ObjectType.UNKNOWN) {
            return null;
        }
        return objectType;
    }
}
