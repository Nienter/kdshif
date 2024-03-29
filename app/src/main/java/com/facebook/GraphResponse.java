package com.facebook;

import android.support.p004v7.widget.helper.ItemTouchHelper;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GraphResponse {
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final String rawResponse;
    private final GraphRequest request;

    public enum PagingDirection {
        NEXT,
        PREVIOUS
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, null, null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, null, jSONArray, null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        this.request = graphRequest;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.graphObject = jSONObject;
        this.graphObjectArray = jSONArray;
        this.error = facebookRequestError;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    public final JSONObject getJSONObject() {
        return this.graphObject;
    }

    public final JSONArray getJSONArray() {
        return this.graphObjectArray;
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    public GraphRequest getRequest() {
        return this.request;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    public GraphRequest getRequestForPagedResults(PagingDirection pagingDirection) {
        String str;
        if (this.graphObject != null) {
            JSONObject optJSONObject = this.graphObject.optJSONObject("paging");
            if (optJSONObject != null) {
                if (pagingDirection == PagingDirection.NEXT) {
                    str = optJSONObject.optString("next");
                } else {
                    str = optJSONObject.optString("previous");
                }
                if (!Utility.isNullOrEmpty(str)) {
                    return null;
                }
                if (str != null && str.equals(this.request.getUrlForSingleRequest())) {
                    return null;
                }
                try {
                    return new GraphRequest(this.request.getAccessToken(), new URL(str));
                } catch (MalformedURLException e) {
                    return null;
                }
            }
        }
        str = null;
        if (!Utility.isNullOrEmpty(str)) {
        }
    }

    public String toString() {
        String str;
        try {
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
            str = String.format(locale, "%d", objArr);
        } catch (IOException e) {
            str = "unknown";
        }
        return "{Response: " + " responseCode: " + str + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
    }

    static List<GraphResponse> fromHttpConnection(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        List<GraphResponse> constructErrorResponses;
        InputStream inputStream;
        boolean z = false;
        try {
            if (httpURLConnection.getResponseCode() >= 400) {
                inputStream = httpURLConnection.getErrorStream();
            } else {
                inputStream = httpURLConnection.getInputStream();
            }
            constructErrorResponses = createResponsesFromStream(z, httpURLConnection, graphRequestBatch);
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e);
            constructErrorResponses = constructErrorResponses(graphRequestBatch, httpURLConnection, e);
        } catch (Exception e2) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", e2);
            constructErrorResponses = constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException((Throwable) e2));
        } finally {
            Utility.closeQuietly(z);
        }
        return constructErrorResponses;
    }

    static List<GraphResponse> createResponsesFromStream(InputStream inputStream, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        String readStreamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, RESPONSE_LOG_TAG, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(readStreamToString.length()), readStreamToString);
        return createResponsesFromString(readStreamToString, httpURLConnection, graphRequestBatch);
    }

    static List<GraphResponse> createResponsesFromString(String str, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        List<GraphResponse> createResponsesFromObject = createResponsesFromObject(httpURLConnection, graphRequestBatch, new JSONTokener(str).nextValue());
        Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.getId(), Integer.valueOf(str.length()), createResponsesFromObject);
        return createResponsesFromObject;
    }

    private static List<GraphResponse> createResponsesFromObject(HttpURLConnection httpURLConnection, List<GraphRequest> list, Object obj) {
        JSONArray jSONArray;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        if (size == 1) {
            GraphRequest graphRequest = list.get(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(BODY_KEY, obj);
                jSONObject.put(CODE_KEY, httpURLConnection != null ? httpURLConnection.getResponseCode() : ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(jSONObject);
                jSONArray = jSONArray2;
            } catch (JSONException e) {
                arrayList.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) e)));
                jSONArray = obj;
            } catch (IOException e2) {
                arrayList.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) e2)));
            }
            if ((jSONArray instanceof JSONArray) || ((JSONArray) jSONArray).length() != size) {
                throw new FacebookException("Unexpected number of results");
            }
            JSONArray jSONArray3 = (JSONArray) jSONArray;
            for (int i = 0; i < jSONArray3.length(); i++) {
                GraphRequest graphRequest2 = list.get(i);
                try {
                    arrayList.add(createResponseFromObject(graphRequest2, httpURLConnection, jSONArray3.get(i), obj));
                } catch (JSONException e3) {
                    arrayList.add(new GraphResponse(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) e3)));
                } catch (FacebookException e4) {
                    arrayList.add(new GraphResponse(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) e4)));
                }
            }
            return arrayList;
        }
        jSONArray = obj;
        if (jSONArray instanceof JSONArray) {
        }
        throw new FacebookException("Unexpected number of results");
    }

    private static GraphResponse createResponseFromObject(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError checkResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jSONObject, obj2, httpURLConnection);
            if (checkResponseAndCreateError != null) {
                if (checkResponseAndCreateError.getErrorCode() == 190 && Utility.isCurrentAccessToken(graphRequest.getAccessToken())) {
                    AccessToken.setCurrentAccessToken(null);
                }
                return new GraphResponse(graphRequest, httpURLConnection, checkResponseAndCreateError);
            }
            Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, BODY_KEY, NON_JSON_RESPONSE_PROPERTY);
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONObject) stringPropertyAsJSON);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONArray) stringPropertyAsJSON);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new GraphResponse(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    static List<GraphResponse> constructErrorResponses(List<GraphRequest> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new GraphResponse(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) facebookException)));
        }
        return arrayList;
    }
}
