package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p001v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.share.internal.ShareConstants;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

final class AccessTokenManager {
    static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    /* access modifiers changed from: private */
    public AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    private static class RefreshResult {
        public String accessToken;
        public int expiresAt;

        private RefreshResult() {
        }
    }

    AccessTokenManager(LocalBroadcastManager localBroadcastManager2, AccessTokenCache accessTokenCache2) {
        Validate.notNull(localBroadcastManager2, "localBroadcastManager");
        Validate.notNull(accessTokenCache2, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.accessTokenCache = accessTokenCache2;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    /* access modifiers changed from: package-private */
    public boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcast(accessToken2, accessToken);
        }
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, accessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken(null);
        }
    }

    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (!this.currentAccessToken.getSource().canExtendToken() || valueOf.longValue() - this.lastAttemptedTokenExtendDate.getTime() <= 3600000 || valueOf.longValue() - this.currentAccessToken.getLastRefresh().getTime() <= 86400000) {
            return false;
        }
        return true;
    }

    private static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, GraphRequest.Callback callback) {
        return new GraphRequest(accessToken, ME_PERMISSIONS_GRAPH_PATH, new Bundle(), HttpMethod.GET, callback);
    }

    private static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, GraphRequest.Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, TOKEN_EXTEND_GRAPH_PATH, bundle, HttpMethod.GET, callback);
    }

    /* access modifiers changed from: package-private */
    public void refreshCurrentAccessToken(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void refreshCurrentAccessTokenImpl(AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        final AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (accessTokenRefreshCallback != null) {
                accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
            this.lastAttemptedTokenExtendDate = new Date();
            final HashSet hashSet = new HashSet();
            final HashSet hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, new GraphRequest.Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        JSONArray optJSONArray = jSONObject.optJSONArray(ShareConstants.WEB_DIALOG_PARAM_DATA);
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                                        String lowerCase = optString2.toLowerCase(Locale.US);
                                        if (lowerCase.equals("granted")) {
                                            hashSet.add(optString);
                                        } else if (lowerCase.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            Log.w(AccessTokenManager.TAG, "Unexpected status: " + lowerCase);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), createExtendAccessTokenRequest(accessToken, new GraphRequest.Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        refreshResult.accessToken = jSONObject.optString("access_token");
                        refreshResult.expiresAt = jSONObject.optInt("expires_at");
                    }
                }
            }));
            final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback2 = accessTokenRefreshCallback;
            graphRequestBatch.addCallback(new GraphRequestBatch.Callback() {
                public void onBatchCompleted(GraphRequestBatch graphRequestBatch) {
                    AccessToken accessToken;
                    String token;
                    Set<String> permissions;
                    Set<String> declinedPermissions;
                    Date expires;
                    try {
                        if (AccessTokenManager.getInstance().getCurrentAccessToken() == null || AccessTokenManager.getInstance().getCurrentAccessToken().getUserId() != accessToken.getUserId()) {
                            if (accessTokenRefreshCallback2 != null) {
                                accessTokenRefreshCallback2.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
                            }
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            if (accessTokenRefreshCallback2 != null && 0 != 0) {
                                accessTokenRefreshCallback2.OnTokenRefreshed(null);
                            }
                        } else if (!atomicBoolean.get() && refreshResult.accessToken == null && refreshResult.expiresAt == 0) {
                            if (accessTokenRefreshCallback2 != null) {
                                accessTokenRefreshCallback2.OnTokenRefreshFailed(new FacebookException("Failed to refresh access token"));
                            }
                            AccessTokenManager.this.tokenRefreshInProgress.set(false);
                            if (accessTokenRefreshCallback2 != null && 0 != 0) {
                                accessTokenRefreshCallback2.OnTokenRefreshed(null);
                            }
                        } else {
                            if (refreshResult.accessToken != null) {
                                token = refreshResult.accessToken;
                            } else {
                                token = accessToken.getToken();
                            }
                            String applicationId = accessToken.getApplicationId();
                            String userId = accessToken.getUserId();
                            if (atomicBoolean.get()) {
                                permissions = hashSet;
                            } else {
                                permissions = accessToken.getPermissions();
                            }
                            if (atomicBoolean.get()) {
                                declinedPermissions = hashSet2;
                            } else {
                                declinedPermissions = accessToken.getDeclinedPermissions();
                            }
                            AccessTokenSource source = accessToken.getSource();
                            if (refreshResult.expiresAt != 0) {
                                expires = new Date(((long) refreshResult.expiresAt) * 1000);
                            } else {
                                expires = accessToken.getExpires();
                            }
                            AccessToken accessToken2 = new AccessToken(token, applicationId, userId, permissions, declinedPermissions, source, expires, new Date());
                            try {
                                AccessTokenManager.getInstance().setCurrentAccessToken(accessToken2);
                                AccessTokenManager.this.tokenRefreshInProgress.set(false);
                                if (accessTokenRefreshCallback2 != null && accessToken2 != null) {
                                    accessTokenRefreshCallback2.OnTokenRefreshed(accessToken2);
                                }
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                accessToken = accessToken2;
                                th = th2;
                                AccessTokenManager.this.tokenRefreshInProgress.set(false);
                                accessTokenRefreshCallback2.OnTokenRefreshed(accessToken);
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        accessToken = null;
                        AccessTokenManager.this.tokenRefreshInProgress.set(false);
                        if (!(accessTokenRefreshCallback2 == null || accessToken == null)) {
                            accessTokenRefreshCallback2.OnTokenRefreshed(accessToken);
                        }
                        throw th;
                    }
                }
            });
            graphRequestBatch.executeAsync();
        } else if (accessTokenRefreshCallback != null) {
            accessTokenRefreshCallback.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
        }
    }
}
