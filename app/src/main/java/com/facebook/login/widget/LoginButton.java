package com.facebook.login.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.p004v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.C0750R;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.LoginAuthorizationType;
import com.facebook.internal.Utility;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ToolTipPopup;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoginButton extends FacebookButtonBase {
    private static final String TAG = LoginButton.class.getName();
    private AccessTokenTracker accessTokenTracker;
    /* access modifiers changed from: private */
    public boolean confirmLogout;
    /* access modifiers changed from: private */
    public String loginLogoutEventName = AnalyticsEvents.EVENT_LOGIN_VIEW_USAGE;
    private LoginManager loginManager;
    private String loginText;
    private String logoutText;
    /* access modifiers changed from: private */
    public LoginButtonProperties properties = new LoginButtonProperties();
    private boolean toolTipChecked;
    private long toolTipDisplayTime = ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME;
    private ToolTipMode toolTipMode;
    private ToolTipPopup toolTipPopup;
    private ToolTipPopup.Style toolTipStyle = ToolTipPopup.Style.BLUE;

    static class LoginButtonProperties {
        /* access modifiers changed from: private */
        public LoginAuthorizationType authorizationType = null;
        private DefaultAudience defaultAudience = DefaultAudience.FRIENDS;
        private LoginBehavior loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        /* access modifiers changed from: private */
        public List<String> permissions = Collections.emptyList();

        LoginButtonProperties() {
        }

        public void setDefaultAudience(DefaultAudience defaultAudience2) {
            this.defaultAudience = defaultAudience2;
        }

        public DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        public void setReadPermissions(List<String> list) {
            if (LoginAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            this.permissions = list;
            this.authorizationType = LoginAuthorizationType.READ;
        }

        public void setPublishPermissions(List<String> list) {
            if (LoginAuthorizationType.READ.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            } else if (Utility.isNullOrEmpty(list)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            } else {
                this.permissions = list;
                this.authorizationType = LoginAuthorizationType.PUBLISH;
            }
        }

        /* access modifiers changed from: package-private */
        public List<String> getPermissions() {
            return this.permissions;
        }

        public void clearPermissions() {
            this.permissions = null;
            this.authorizationType = null;
        }

        public void setLoginBehavior(LoginBehavior loginBehavior2) {
            this.loginBehavior = loginBehavior2;
        }

        public LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
    }

    protected class LoginClickListener implements View.OnClickListener {
        protected LoginClickListener() {
        }

        public void onClick(View view) {
            LoginButton.this.callExternalOnClickListener(view);
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentAccessToken != null) {
                performLogout(LoginButton.this.getContext());
            } else {
                performLogin();
            }
            AppEventsLogger newLogger = AppEventsLogger.newLogger(LoginButton.this.getContext());
            Bundle bundle = new Bundle();
            bundle.putInt("logging_in", currentAccessToken != null ? 0 : 1);
            newLogger.logSdkEvent(LoginButton.this.loginLogoutEventName, null, bundle);
        }

        /* access modifiers changed from: protected */
        public void performLogin() {
            LoginManager loginManager = getLoginManager();
            if (LoginAuthorizationType.PUBLISH.equals(LoginButton.this.properties.authorizationType)) {
                if (LoginButton.this.getFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getFragment(), (Collection<String>) LoginButton.this.properties.permissions);
                } else if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getNativeFragment(), (Collection<String>) LoginButton.this.properties.permissions);
                } else {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getActivity(), (Collection<String>) LoginButton.this.properties.permissions);
                }
            } else if (LoginButton.this.getFragment() != null) {
                loginManager.logInWithReadPermissions(LoginButton.this.getFragment(), (Collection<String>) LoginButton.this.properties.permissions);
            } else if (LoginButton.this.getNativeFragment() != null) {
                loginManager.logInWithReadPermissions(LoginButton.this.getNativeFragment(), (Collection<String>) LoginButton.this.properties.permissions);
            } else {
                loginManager.logInWithReadPermissions(LoginButton.this.getActivity(), (Collection<String>) LoginButton.this.properties.permissions);
            }
        }

        /* access modifiers changed from: protected */
        public void performLogout(Context context) {
            String string;
            final LoginManager loginManager = getLoginManager();
            if (LoginButton.this.confirmLogout) {
                String string2 = LoginButton.this.getResources().getString(C0750R.string.com_facebook_loginview_log_out_action);
                String string3 = LoginButton.this.getResources().getString(C0750R.string.com_facebook_loginview_cancel_action);
                Profile currentProfile = Profile.getCurrentProfile();
                if (currentProfile == null || currentProfile.getName() == null) {
                    string = LoginButton.this.getResources().getString(C0750R.string.com_facebook_loginview_logged_in_using_facebook);
                } else {
                    string = String.format(LoginButton.this.getResources().getString(C0750R.string.com_facebook_loginview_logged_in_as), new Object[]{currentProfile.getName()});
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(string).setCancelable(true).setPositiveButton(string2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loginManager.logOut();
                    }
                }).setNegativeButton(string3, null);
                builder.create().show();
                return;
            }
            loginManager.logOut();
        }

        /* access modifiers changed from: protected */
        public LoginManager getLoginManager() {
            LoginManager instance = LoginManager.getInstance();
            instance.setDefaultAudience(LoginButton.this.getDefaultAudience());
            instance.setLoginBehavior(LoginButton.this.getLoginBehavior());
            return instance;
        }
    }

    public enum ToolTipMode {
        AUTOMATIC(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC, 0),
        DISPLAY_ALWAYS("display_always", 1),
        NEVER_DISPLAY("never_display", 2);
        
        public static ToolTipMode DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            DEFAULT = AUTOMATIC;
        }

        public static ToolTipMode fromInt(int i) {
            for (ToolTipMode toolTipMode : values()) {
                if (toolTipMode.getValue() == i) {
                    return toolTipMode;
                }
            }
            return null;
        }

        private ToolTipMode(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        public String toString() {
            return this.stringValue;
        }

        public int getValue() {
            return this.intValue;
        }
    }

    public LoginButton(Context context) {
        super(context, null, 0, 0, AnalyticsEvents.EVENT_LOGIN_BUTTON_CREATE, AnalyticsEvents.EVENT_LOGIN_BUTTON_DID_TAP);
    }

    public LoginButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0, AnalyticsEvents.EVENT_LOGIN_BUTTON_CREATE, AnalyticsEvents.EVENT_LOGIN_BUTTON_DID_TAP);
    }

    public LoginButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0, AnalyticsEvents.EVENT_LOGIN_BUTTON_CREATE, AnalyticsEvents.EVENT_LOGIN_BUTTON_DID_TAP);
    }

    public void setDefaultAudience(DefaultAudience defaultAudience) {
        this.properties.setDefaultAudience(defaultAudience);
    }

    public DefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }

    public void setReadPermissions(List<String> list) {
        this.properties.setReadPermissions(list);
    }

    public void setReadPermissions(String... strArr) {
        this.properties.setReadPermissions(Arrays.asList(strArr));
    }

    public void setPublishPermissions(List<String> list) {
        this.properties.setPublishPermissions(list);
    }

    public void setPublishPermissions(String... strArr) {
        this.properties.setPublishPermissions(Arrays.asList(strArr));
    }

    public void clearPermissions() {
        this.properties.clearPermissions();
    }

    public void setLoginBehavior(LoginBehavior loginBehavior) {
        this.properties.setLoginBehavior(loginBehavior);
    }

    public LoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }

    public void setToolTipStyle(ToolTipPopup.Style style) {
        this.toolTipStyle = style;
    }

    public void setToolTipMode(ToolTipMode toolTipMode2) {
        this.toolTipMode = toolTipMode2;
    }

    public ToolTipMode getToolTipMode() {
        return this.toolTipMode;
    }

    public void setToolTipDisplayTime(long j) {
        this.toolTipDisplayTime = j;
    }

    public long getToolTipDisplayTime() {
        return this.toolTipDisplayTime;
    }

    public void dismissToolTip() {
        if (this.toolTipPopup != null) {
            this.toolTipPopup.dismiss();
            this.toolTipPopup = null;
        }
    }

    public void registerCallback(CallbackManager callbackManager, FacebookCallback<LoginResult> facebookCallback) {
        getLoginManager().registerCallback(callbackManager, facebookCallback);
    }

    public void unregisterCallback(CallbackManager callbackManager) {
        getLoginManager().unregisterCallback(callbackManager);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.accessTokenTracker != null && !this.accessTokenTracker.isTracking()) {
            this.accessTokenTracker.startTracking();
            setButtonText();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.toolTipChecked && !isInEditMode()) {
            this.toolTipChecked = true;
            checkToolTipSettings();
        }
    }

    /* access modifiers changed from: private */
    public void showToolTipPerSettings(FetchedAppSettings fetchedAppSettings) {
        if (fetchedAppSettings != null && fetchedAppSettings.getNuxEnabled() && getVisibility() == 0) {
            displayToolTip(fetchedAppSettings.getNuxContent());
        }
    }

    private void displayToolTip(String str) {
        this.toolTipPopup = new ToolTipPopup(str, this);
        this.toolTipPopup.setStyle(this.toolTipStyle);
        this.toolTipPopup.setNuxDisplayTime(this.toolTipDisplayTime);
        this.toolTipPopup.show();
    }

    private void checkToolTipSettings() {
        switch (this.toolTipMode) {
            case AUTOMATIC:
                final String metadataApplicationId = Utility.getMetadataApplicationId(getContext());
                FacebookSdk.getExecutor().execute(new Runnable() {
                    public void run() {
                        final FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(metadataApplicationId, false);
                        LoginButton.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                LoginButton.this.showToolTipPerSettings(queryAppSettings);
                            }
                        });
                    }
                });
                return;
            case DISPLAY_ALWAYS:
                displayToolTip(getResources().getString(C0750R.string.com_facebook_tooltip_default));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setButtonText();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.accessTokenTracker != null) {
            this.accessTokenTracker.stopTracking();
        }
        dismissToolTip();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            dismissToolTip();
        }
    }

    /* access modifiers changed from: package-private */
    public List<String> getPermissions() {
        return this.properties.getPermissions();
    }

    /* access modifiers changed from: package-private */
    public void setProperties(LoginButtonProperties loginButtonProperties) {
        this.properties = loginButtonProperties;
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        setInternalOnClickListener(getNewLoginClickListener());
        parseLoginButtonAttributes(context, attributeSet, i, i2);
        if (isInEditMode()) {
            setBackgroundColor(getResources().getColor(C0750R.color.com_facebook_blue));
            this.loginText = "Continue with Facebook";
        } else {
            this.accessTokenTracker = new AccessTokenTracker() {
                /* access modifiers changed from: protected */
                public void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                    LoginButton.this.setButtonText();
                }
            };
        }
        setButtonText();
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(getContext(), C0750R.C0751drawable.com_facebook_button_login_logo), null, null, null);
    }

    /* access modifiers changed from: protected */
    public LoginClickListener getNewLoginClickListener() {
        return new LoginClickListener();
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return C0750R.C0753style.com_facebook_loginview_default_style;
    }

    private void parseLoginButtonAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        this.toolTipMode = ToolTipMode.DEFAULT;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C0750R.styleable.com_facebook_login_view, i, i2);
        try {
            this.confirmLogout = obtainStyledAttributes.getBoolean(C0750R.styleable.com_facebook_login_view_com_facebook_confirm_logout, true);
            this.loginText = obtainStyledAttributes.getString(C0750R.styleable.com_facebook_login_view_com_facebook_login_text);
            this.logoutText = obtainStyledAttributes.getString(C0750R.styleable.com_facebook_login_view_com_facebook_logout_text);
            this.toolTipMode = ToolTipMode.fromInt(obtainStyledAttributes.getInt(C0750R.styleable.com_facebook_login_view_com_facebook_tooltip_mode, ToolTipMode.DEFAULT.getValue()));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        int compoundPaddingBottom = getCompoundPaddingBottom() + ((int) Math.ceil((double) (Math.abs(fontMetrics.bottom) + Math.abs(fontMetrics.top)))) + getCompoundPaddingTop();
        Resources resources = getResources();
        String str = this.loginText;
        if (str == null) {
            str = resources.getString(C0750R.string.com_facebook_loginview_log_in_button_continue);
            int measureButtonWidth = measureButtonWidth(str);
            if (resolveSize(measureButtonWidth, i) < measureButtonWidth) {
                str = resources.getString(C0750R.string.com_facebook_loginview_log_in_button);
            }
        }
        int measureButtonWidth2 = measureButtonWidth(str);
        String str2 = this.logoutText;
        if (str2 == null) {
            str2 = resources.getString(C0750R.string.com_facebook_loginview_log_out_button);
        }
        setMeasuredDimension(resolveSize(Math.max(measureButtonWidth2, measureButtonWidth(str2)), i), compoundPaddingBottom);
    }

    private int measureButtonWidth(String str) {
        return measureTextWidth(str) + getCompoundPaddingLeft() + getCompoundDrawablePadding() + getCompoundPaddingRight();
    }

    /* access modifiers changed from: private */
    public void setButtonText() {
        String string;
        Resources resources = getResources();
        if (!isInEditMode() && AccessToken.getCurrentAccessToken() != null) {
            if (this.logoutText != null) {
                string = this.logoutText;
            } else {
                string = resources.getString(C0750R.string.com_facebook_loginview_log_out_button);
            }
            setText(string);
        } else if (this.loginText != null) {
            setText(this.loginText);
        } else {
            String string2 = resources.getString(C0750R.string.com_facebook_loginview_log_in_button_continue);
            int width = getWidth();
            if (width != 0 && measureButtonWidth(string2) > width) {
                string2 = resources.getString(C0750R.string.com_facebook_loginview_log_in_button);
            }
            setText(string2);
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
    }

    /* access modifiers changed from: package-private */
    public LoginManager getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = LoginManager.getInstance();
        }
        return this.loginManager;
    }

    /* access modifiers changed from: package-private */
    public void setLoginManager(LoginManager loginManager2) {
        this.loginManager = loginManager2;
    }
}
