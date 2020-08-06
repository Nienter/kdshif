package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.facebook.C0750R;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.internal.ShareInternalUtility;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebDialog extends Dialog {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int BACKGROUND_GRAY = -872415232;
    static final String CANCEL_URI = "fbconnect://cancel";
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    private static final int MAX_PADDING_SCREEN_WIDTH = 800;
    private static final double MIN_SCALE_FACTOR = 0.5d;
    private static final int NO_PADDING_SCREEN_HEIGHT = 800;
    private static final int NO_PADDING_SCREEN_WIDTH = 480;
    static final String REDIRECT_URI = "fbconnect://success";
    /* access modifiers changed from: private */
    public FrameLayout contentFrameLayout;
    /* access modifiers changed from: private */
    public ImageView crossImageView;
    /* access modifiers changed from: private */
    public String expectedRedirectUrl;
    /* access modifiers changed from: private */
    public boolean isDetached;
    /* access modifiers changed from: private */
    public boolean isPageFinished;
    private boolean listenerCalled;
    private OnCompleteListener onCompleteListener;
    /* access modifiers changed from: private */
    public ProgressDialog spinner;
    private UploadStagingResourcesTask uploadTask;
    /* access modifiers changed from: private */
    public String url;
    /* access modifiers changed from: private */
    public WebView webView;

    public static class Builder {
        private AccessToken accessToken;
        private String action;
        private String applicationId;
        private Context context;
        private OnCompleteListener listener;
        private Bundle parameters;
        private int theme;

        public Builder(Context context2, String str, Bundle bundle) {
            this.accessToken = AccessToken.getCurrentAccessToken();
            if (this.accessToken == null) {
                String metadataApplicationId = Utility.getMetadataApplicationId(context2);
                if (metadataApplicationId != null) {
                    this.applicationId = metadataApplicationId;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            finishInit(context2, str, bundle);
        }

        public Builder(Context context2, String str, String str2, Bundle bundle) {
            str = str == null ? Utility.getMetadataApplicationId(context2) : str;
            Validate.notNullOrEmpty(str, "applicationId");
            this.applicationId = str;
            finishInit(context2, str2, bundle);
        }

        public Builder setTheme(int i) {
            this.theme = i;
            return this;
        }

        public Builder setOnCompleteListener(OnCompleteListener onCompleteListener) {
            this.listener = onCompleteListener;
            return this;
        }

        public WebDialog build() {
            if (this.accessToken != null) {
                this.parameters.putString("app_id", this.accessToken.getApplicationId());
                this.parameters.putString("access_token", this.accessToken.getToken());
            } else {
                this.parameters.putString("app_id", this.applicationId);
            }
            return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
        }

        public String getApplicationId() {
            return this.applicationId;
        }

        public Context getContext() {
            return this.context;
        }

        public int getTheme() {
            return this.theme;
        }

        public Bundle getParameters() {
            return this.parameters;
        }

        public OnCompleteListener getListener() {
            return this.listener;
        }

        private void finishInit(Context context2, String str, Bundle bundle) {
            this.context = context2;
            this.action = str;
            if (bundle != null) {
                this.parameters = bundle;
            } else {
                this.parameters = new Bundle();
            }
        }
    }

    private class DialogWebViewClient extends WebViewClient {
        private DialogWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            int i;
            Utility.logd(WebDialog.LOG_TAG, "Redirect URL: " + str);
            if (str.startsWith(WebDialog.this.expectedRedirectUrl)) {
                Bundle parseResponseUri = WebDialog.this.parseResponseUri(str);
                String string = parseResponseUri.getString("error");
                if (string == null) {
                    string = parseResponseUri.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
                }
                String string2 = parseResponseUri.getString("error_msg");
                if (string2 == null) {
                    string2 = parseResponseUri.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
                }
                if (string2 == null) {
                    string2 = parseResponseUri.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
                }
                String string3 = parseResponseUri.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                if (!Utility.isNullOrEmpty(string3)) {
                    try {
                        i = Integer.parseInt(string3);
                    } catch (NumberFormatException e) {
                        i = -1;
                    }
                } else {
                    i = -1;
                }
                if (Utility.isNullOrEmpty(string) && Utility.isNullOrEmpty(string2) && i == -1) {
                    WebDialog.this.sendSuccessToListener(parseResponseUri);
                } else if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                    WebDialog.this.cancel();
                } else if (i == WebDialog.API_EC_DIALOG_CANCEL) {
                    WebDialog.this.cancel();
                } else {
                    WebDialog.this.sendErrorToListener(new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
                }
                return true;
            } else if (str.startsWith("fbconnect://cancel")) {
                WebDialog.this.cancel();
                return true;
            } else if (str.contains("touch")) {
                return false;
            } else {
                try {
                    WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (ActivityNotFoundException e2) {
                    return false;
                }
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            WebDialog.this.sendErrorToListener(new FacebookDialogException(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Utility.logd(WebDialog.LOG_TAG, "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.show();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
            boolean unused = WebDialog.this.isPageFinished = true;
        }
    }

    public interface OnCompleteListener {
        void onComplete(Bundle bundle, FacebookException facebookException);
    }

    private class UploadStagingResourcesTask extends AsyncTask<Void, Void, String[]> {
        private String action;
        /* access modifiers changed from: private */
        public Exception[] exceptions;
        private Bundle parameters;

        UploadStagingResourcesTask(String str, Bundle bundle) {
            this.action = str;
            this.parameters = bundle;
        }

        /* access modifiers changed from: protected */
        public String[] doInBackground(Void... voidArr) {
            String[] stringArray = this.parameters.getStringArray(ShareConstants.WEB_DIALOG_PARAM_MEDIA);
            final String[] strArr = new String[stringArray.length];
            this.exceptions = new Exception[stringArray.length];
            final CountDownLatch countDownLatch = new CountDownLatch(stringArray.length);
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            final int i = 0;
            while (i < stringArray.length) {
                try {
                    if (isCancelled()) {
                        Iterator it = concurrentLinkedQueue.iterator();
                        while (it.hasNext()) {
                            ((AsyncTask) it.next()).cancel(true);
                        }
                        return null;
                    }
                    Uri parse = Uri.parse(stringArray[i]);
                    if (Utility.isWebUri(parse)) {
                        strArr[i] = parse.toString();
                        countDownLatch.countDown();
                    } else {
                        concurrentLinkedQueue.add(ShareInternalUtility.newUploadStagingResourceWithImageRequest(currentAccessToken, parse, (GraphRequest.Callback) new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse graphResponse) {
                                try {
                                    FacebookRequestError error = graphResponse.getError();
                                    if (error != null) {
                                        String errorMessage = error.getErrorMessage();
                                        if (errorMessage == null) {
                                            errorMessage = "Error staging photo.";
                                        }
                                        throw new FacebookGraphResponseException(graphResponse, errorMessage);
                                    }
                                    JSONObject jSONObject = graphResponse.getJSONObject();
                                    if (jSONObject == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    String optString = jSONObject.optString(ShareConstants.MEDIA_URI);
                                    if (optString == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    strArr[i] = optString;
                                    countDownLatch.countDown();
                                } catch (Exception e) {
                                    UploadStagingResourcesTask.this.exceptions[i] = e;
                                }
                            }
                        }).executeAsync());
                    }
                    i++;
                } catch (Exception e) {
                    Iterator it2 = concurrentLinkedQueue.iterator();
                    while (it2.hasNext()) {
                        ((AsyncTask) it2.next()).cancel(true);
                    }
                    return null;
                }
            }
            countDownLatch.await();
            return strArr;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String[] strArr) {
            WebDialog.this.spinner.dismiss();
            for (Exception exc : this.exceptions) {
                if (exc != null) {
                    WebDialog.this.sendErrorToListener(exc);
                    return;
                }
            }
            if (strArr == null) {
                WebDialog.this.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            List asList = Arrays.asList(strArr);
            if (asList.contains(null)) {
                WebDialog.this.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            Utility.putJSONValueInBundle(this.parameters, ShareConstants.WEB_DIALOG_PARAM_MEDIA, new JSONArray(asList));
            String unused = WebDialog.this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + this.action, this.parameters).toString();
            WebDialog.this.setUpWebView((WebDialog.this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
    }

    public WebDialog(Context context, String str) {
        this(context, str, FacebookSdk.getWebDialogTheme());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebDialog(Context context, String str, int i) {
        super(context, i == 0 ? FacebookSdk.getWebDialogTheme() : i);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        this.url = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebDialog(Context context, String str, Bundle bundle, int i, OnCompleteListener onCompleteListener2) {
        super(context, i == 0 ? FacebookSdk.getWebDialogTheme() : i);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        bundle = bundle == null ? new Bundle() : bundle;
        bundle.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
        bundle.putString(ServerProtocol.DIALOG_PARAM_DISPLAY, "touch");
        bundle.putString(ServerProtocol.DIALOG_PARAM_SDK_VERSION, String.format(Locale.ROOT, "android-%s", new Object[]{FacebookSdk.getSdkVersion()}));
        this.onCompleteListener = onCompleteListener2;
        if (!str.equals("share") || !bundle.containsKey(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) {
            this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + str, bundle).toString();
        } else {
            this.uploadTask = new UploadStagingResourcesTask(str, bundle);
        }
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
        this.onCompleteListener = onCompleteListener2;
    }

    public OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        if (this.webView != null) {
            this.webView.stopLoading();
        }
        if (!this.isDetached && this.spinner != null && this.spinner.isShowing()) {
            this.spinner.dismiss();
        }
        super.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.uploadTask == null || this.uploadTask.getStatus() != AsyncTask.Status.PENDING) {
            resize();
            return;
        }
        this.uploadTask.execute(new Void[0]);
        this.spinner.show();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.uploadTask != null) {
            this.uploadTask.cancel(true);
            this.spinner.dismiss();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.spinner = new ProgressDialog(getContext());
        this.spinner.requestWindowFeature(1);
        this.spinner.setMessage(getContext().getString(C0750R.string.com_facebook_loading));
        this.spinner.setCanceledOnTouchOutside(false);
        this.spinner.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                WebDialog.this.cancel();
            }
        });
        requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(getContext());
        resize();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        createCrossImage();
        if (this.url != null) {
            setUpWebView((this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
        this.contentFrameLayout.addView(this.crossImageView, new ViewGroup.LayoutParams(-2, -2));
        setContentView(this.contentFrameLayout);
    }

    /* access modifiers changed from: protected */
    public void setExpectedRedirectUrl(String str) {
        this.expectedRedirectUrl = str;
    }

    /* access modifiers changed from: protected */
    public Bundle parseResponseUri(String str) {
        Uri parse = Uri.parse(str);
        Bundle parseUrlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        parseUrlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
        return parseUrlQueryString;
    }

    /* access modifiers changed from: protected */
    public boolean isListenerCalled() {
        return this.listenerCalled;
    }

    /* access modifiers changed from: protected */
    public boolean isPageFinished() {
        return this.isPageFinished;
    }

    /* access modifiers changed from: protected */
    public WebView getWebView() {
        return this.webView;
    }

    public void resize() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        getWindow().setLayout(Math.min(getScaledSize(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels, displayMetrics.density, NO_PADDING_SCREEN_WIDTH, 800), displayMetrics.widthPixels), Math.min(getScaledSize(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels, displayMetrics.density, 800, MAX_PADDING_SCREEN_HEIGHT), displayMetrics.heightPixels));
    }

    private int getScaledSize(int i, float f, int i2, int i3) {
        double d = MIN_SCALE_FACTOR;
        int i4 = (int) (((float) i) / f);
        if (i4 <= i2) {
            d = 1.0d;
        } else if (i4 < i3) {
            d = MIN_SCALE_FACTOR + ((((double) (i3 - i4)) / ((double) (i3 - i2))) * MIN_SCALE_FACTOR);
        }
        return (int) (d * ((double) i));
    }

    /* access modifiers changed from: protected */
    public void sendSuccessToListener(Bundle bundle) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(bundle, null);
            dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void sendErrorToListener(Throwable th) {
        FacebookException facebookException;
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            if (th instanceof FacebookException) {
                facebookException = (FacebookException) th;
            } else {
                facebookException = new FacebookException(th);
            }
            this.onCompleteListener.onComplete(null, facebookException);
            dismiss();
        }
    }

    public void cancel() {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            sendErrorToListener(new FacebookOperationCanceledException());
        }
    }

    private void createCrossImage() {
        this.crossImageView = new ImageView(getContext());
        this.crossImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebDialog.this.cancel();
            }
        });
        this.crossImageView.setImageDrawable(getContext().getResources().getDrawable(C0750R.C0751drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }

    /* access modifiers changed from: private */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void setUpWebView(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.webView = new WebView(getContext().getApplicationContext()) {
            public void onWindowFocusChanged(boolean z) {
                try {
                    super.onWindowFocusChanged(z);
                } catch (NullPointerException e) {
                }
            }
        };
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient(new DialogWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        this.webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!view.hasFocus()) {
                    view.requestFocus();
                }
                return false;
            }
        });
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.webView);
        linearLayout.setBackgroundColor(BACKGROUND_GRAY);
        this.contentFrameLayout.addView(linearLayout);
    }
}
