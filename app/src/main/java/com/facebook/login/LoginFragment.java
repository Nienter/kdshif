package com.facebook.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.C0750R;
import com.facebook.login.LoginClient;

public class LoginFragment extends Fragment {
    static final String EXTRA_REQUEST = "request";
    private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    static final String REQUEST_KEY = "com.facebook.LoginFragment:Request";
    static final String RESULT_KEY = "com.facebook.LoginFragment:Result";
    private static final String SAVED_LOGIN_CLIENT = "loginClient";
    private static final String TAG = "LoginFragment";
    private String callingPackage;
    private LoginClient loginClient;
    private LoginClient.Request request;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.loginClient = (LoginClient) bundle.getParcelable(SAVED_LOGIN_CLIENT);
            this.loginClient.setFragment(this);
        } else {
            this.loginClient = createLoginClient();
        }
        this.loginClient.setOnCompletedListener(new LoginClient.OnCompletedListener() {
            public void onCompleted(LoginClient.Result result) {
                LoginFragment.this.onLoginClientCompleted(result);
            }
        });
        FragmentActivity activity = getActivity();
        if (activity != null) {
            initializeCallingPackage(activity);
            if (activity.getIntent() != null) {
                this.request = (LoginClient.Request) activity.getIntent().getBundleExtra(REQUEST_KEY).getParcelable("request");
            }
        }
    }

    /* access modifiers changed from: protected */
    public LoginClient createLoginClient() {
        return new LoginClient((Fragment) this);
    }

    public void onDestroy() {
        this.loginClient.cancelCurrentHandler();
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        final View inflate = layoutInflater.inflate(C0750R.layout.com_facebook_login_fragment, viewGroup, false);
        this.loginClient.setBackgroundProcessingListener(new LoginClient.BackgroundProcessingListener() {
            public void onBackgroundProcessingStarted() {
                inflate.findViewById(C0750R.C0752id.com_facebook_login_activity_progress_bar).setVisibility(0);
            }

            public void onBackgroundProcessingStopped() {
                inflate.findViewById(C0750R.C0752id.com_facebook_login_activity_progress_bar).setVisibility(8);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void onLoginClientCompleted(LoginClient.Result result) {
        this.request = null;
        int i = result.code == LoginClient.Result.Code.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_KEY, result);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (isAdded()) {
            getActivity().setResult(i, intent);
            getActivity().finish();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.callingPackage == null) {
            Log.e(TAG, NULL_CALLING_PKG_ERROR_MSG);
            getActivity().finish();
            return;
        }
        this.loginClient.startOrContinueAuth(this.request);
    }

    public void onPause() {
        super.onPause();
        getActivity().findViewById(C0750R.C0752id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.loginClient.onActivityResult(i, i2, intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(SAVED_LOGIN_CLIENT, this.loginClient);
    }

    private void initializeCallingPackage(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            this.callingPackage = callingActivity.getPackageName();
        }
    }

    /* access modifiers changed from: package-private */
    public LoginClient getLoginClient() {
        return this.loginClient;
    }
}
