package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;

abstract class NativeAppLoginMethodHandler extends LoginMethodHandler {
    /* access modifiers changed from: package-private */
    public abstract boolean tryAuthorize(LoginClient.Request request);

    NativeAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    NativeAppLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    /* access modifiers changed from: package-private */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        LoginClient.Result handleResultOk;
        LoginClient.Request pendingRequest = this.loginClient.getPendingRequest();
        if (intent == null) {
            handleResultOk = LoginClient.Result.createCancelResult(pendingRequest, "Operation canceled");
        } else if (i2 == 0) {
            handleResultOk = handleResultCancel(pendingRequest, intent);
        } else if (i2 != -1) {
            handleResultOk = LoginClient.Result.createErrorResult(pendingRequest, "Unexpected resultCode from authorization.", null);
        } else {
            handleResultOk = handleResultOk(pendingRequest, intent);
        }
        if (handleResultOk != null) {
            this.loginClient.completeAndValidate(handleResultOk);
        } else {
            this.loginClient.tryNextHandler();
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.facebook.login.LoginClient$Result, java.lang.String] */
    private LoginClient.Result handleResultOk(LoginClient.Request request, Intent intent) {
        ? r0 = 0;
        Bundle extras = intent.getExtras();
        String error = getError(extras);
        String string = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
        String errorMessage = getErrorMessage(extras);
        String string2 = extras.getString("e2e");
        if (!Utility.isNullOrEmpty(string2)) {
            logWebLoginCompleted(string2);
        }
        if (error == null && string == null && errorMessage == null) {
            try {
                return LoginClient.Result.createTokenResult(request, createAccessTokenFromWebBundle(request.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.getApplicationId()));
            } catch (FacebookException e) {
                return LoginClient.Result.createErrorResult(request, r0, e.getMessage());
            }
        } else if (ServerProtocol.errorsProxyAuthDisabled.contains(error)) {
            return r0;
        } else {
            if (ServerProtocol.errorsUserCanceled.contains(error)) {
                return LoginClient.Result.createCancelResult(request, r0);
            }
            return LoginClient.Result.createErrorResult(request, error, errorMessage, string);
        }
    }

    private LoginClient.Result handleResultCancel(LoginClient.Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String error = getError(extras);
        String string = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
        if (ServerProtocol.errorConnectionFailure.equals(string)) {
            return LoginClient.Result.createErrorResult(request, error, getErrorMessage(extras), string);
        }
        return LoginClient.Result.createCancelResult(request, error);
    }

    private String getError(Bundle bundle) {
        String string = bundle.getString("error");
        if (string == null) {
            return bundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
        }
        return string;
    }

    private String getErrorMessage(Bundle bundle) {
        String string = bundle.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        if (string == null) {
            return bundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
        }
        return string;
    }

    /* access modifiers changed from: protected */
    public boolean tryIntent(Intent intent, int i) {
        if (intent == null) {
            return false;
        }
        try {
            this.loginClient.getFragment().startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
}
