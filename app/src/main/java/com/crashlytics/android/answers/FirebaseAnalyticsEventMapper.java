package com.crashlytics.android.answers;

import android.os.Bundle;
import com.crashlytics.android.answers.SessionEvent;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsFilesManager;

public class FirebaseAnalyticsEventMapper {
    private static final Set<String> EVENT_NAMES = new HashSet(Arrays.asList(new String[]{"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "screen_view", "firebase_extra_parameter"}));
    private static final String FIREBASE_LEVEL_NAME = "level_name";
    private static final String FIREBASE_METHOD = "method";
    private static final String FIREBASE_RATING = "rating";
    private static final String FIREBASE_SUCCESS = "success";

    public FirebaseAnalyticsEvent mapEvent(SessionEvent sessionEvent) {
        boolean z;
        Bundle bundle;
        String mapCustomEventName;
        boolean z2 = true;
        boolean z3 = SessionEvent.Type.CUSTOM.equals(sessionEvent.type) && sessionEvent.customType != null;
        if (!SessionEvent.Type.PREDEFINED.equals(sessionEvent.type) || sessionEvent.predefinedType == null) {
            z = false;
        } else {
            z = true;
        }
        if (!z3 && !z) {
            return null;
        }
        if (z) {
            bundle = mapPredefinedEvent(sessionEvent);
        } else {
            Bundle bundle2 = new Bundle();
            if (sessionEvent.customAttributes != null) {
                mapCustomEventAttributes(bundle2, sessionEvent.customAttributes);
            }
            bundle = bundle2;
        }
        if (z) {
            String str = (String) sessionEvent.predefinedAttributes.get("success");
            if (str == null || Boolean.parseBoolean(str)) {
                z2 = false;
            }
            mapCustomEventName = mapPredefinedEventName(sessionEvent.predefinedType, z2);
        } else {
            mapCustomEventName = mapCustomEventName(sessionEvent.customType);
        }
        Fabric.m456h().mo8506a(Answers.TAG, "Logging event into firebase...");
        return new FirebaseAnalyticsEvent(mapCustomEventName, bundle);
    }

    private String mapCustomEventName(String str) {
        if (str == null || str.length() == 0) {
            return "fabric_unnamed_event";
        }
        if (EVENT_NAMES.contains(str)) {
            return "fabric_" + str;
        }
        String replaceAll = str.replaceAll("[^\\p{Alnum}_]+", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        if (replaceAll.startsWith("ga_") || replaceAll.startsWith("google_") || replaceAll.startsWith("firebase_") || !Character.isLetter(replaceAll.charAt(0))) {
            replaceAll = "fabric_" + replaceAll;
        }
        if (replaceAll.length() > 40) {
            return replaceAll.substring(0, 40);
        }
        return replaceAll;
    }

    private String mapAttribute(String str) {
        if (str == null || str.length() == 0) {
            return "fabric_unnamed_parameter";
        }
        String replaceAll = str.replaceAll("[^\\p{Alnum}_]+", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        if (replaceAll.startsWith("ga_") || replaceAll.startsWith("google_") || replaceAll.startsWith("firebase_") || !Character.isLetter(replaceAll.charAt(0))) {
            replaceAll = "fabric_" + replaceAll;
        }
        if (replaceAll.length() > 40) {
            return replaceAll.substring(0, 40);
        }
        return replaceAll;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        if (r6.equals("purchase") != false) goto L_0x0019;
     */
    private String mapPredefinedEventName(String str, boolean z) {
        boolean z2;
        char c = 0;
        if (z) {
            switch (str.hashCode()) {
                case -902468296:
                    if (str.equals("signUp")) {
                        z2 = true;
                        break;
                    }
                case 103149417:
                    if (str.equals(FirebaseAnalytics.Event.LOGIN)) {
                        z2 = true;
                        break;
                    }
                case 1743324417:
                    if (str.equals("purchase")) {
                        z2 = false;
                        break;
                    }
                default:
                    z2 = true;
                    break;
            }
            switch (z2) {
                case false:
                    return "failed_ecommerce_purchase";
                case true:
                    return "failed_sign_up";
                case true:
                    return "failed_login";
            }
        }
        switch (str.hashCode()) {
            case -2131650889:
                if (str.equals("levelEnd")) {
                    c = 11;
                    break;
                }
            case -1183699191:
                if (str.equals("invite")) {
                    c = 9;
                    break;
                }
            case -938102371:
                if (str.equals(FIREBASE_RATING)) {
                    c = 6;
                    break;
                }
            case -906336856:
                if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
                    c = 4;
                    break;
                }
            case -902468296:
                if (str.equals("signUp")) {
                    c = 7;
                    break;
                }
            case -389087554:
                if (str.equals("contentView")) {
                    c = 3;
                    break;
                }
            case 23457852:
                if (str.equals("addToCart")) {
                    c = 1;
                    break;
                }
            case 103149417:
                if (str.equals(FirebaseAnalytics.Event.LOGIN)) {
                    c = 8;
                    break;
                }
            case 109400031:
                if (str.equals("share")) {
                    c = 5;
                    break;
                }
            case 196004670:
                if (str.equals("levelStart")) {
                    c = 10;
                    break;
                }
            case 1664021448:
                if (str.equals("startCheckout")) {
                    c = 2;
                    break;
                }
            case 1743324417:
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return FirebaseAnalytics.Event.ECOMMERCE_PURCHASE;
            case 1:
                return FirebaseAnalytics.Event.ADD_TO_CART;
            case 2:
                return FirebaseAnalytics.Event.BEGIN_CHECKOUT;
            case 3:
                return FirebaseAnalytics.Event.SELECT_CONTENT;
            case 4:
                return FirebaseAnalytics.Event.SEARCH;
            case 5:
                return "share";
            case 6:
                return "rate_content";
            case 7:
                return FirebaseAnalytics.Event.SIGN_UP;
            case 8:
                return FirebaseAnalytics.Event.LOGIN;
            case 9:
                return "invite";
            case 10:
                return "level_start";
            case 11:
                return "level_end";
            default:
                return mapCustomEventName(str);
        }
    }

    private Bundle mapPredefinedEvent(SessionEvent sessionEvent) {
        Bundle bundle = new Bundle();
        if ("purchase".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("itemId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("itemName"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_CATEGORY, (String) sessionEvent.predefinedAttributes.get("itemType"));
            putDouble(bundle, FirebaseAnalytics.Param.VALUE, mapPriceValue(sessionEvent.predefinedAttributes.get("itemPrice")));
            putString(bundle, FirebaseAnalytics.Param.CURRENCY, (String) sessionEvent.predefinedAttributes.get(FirebaseAnalytics.Param.CURRENCY));
        } else if ("addToCart".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("itemId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("itemName"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_CATEGORY, (String) sessionEvent.predefinedAttributes.get("itemType"));
            putDouble(bundle, FirebaseAnalytics.Param.PRICE, mapPriceValue(sessionEvent.predefinedAttributes.get("itemPrice")));
            putDouble(bundle, FirebaseAnalytics.Param.VALUE, mapPriceValue(sessionEvent.predefinedAttributes.get("itemPrice")));
            putString(bundle, FirebaseAnalytics.Param.CURRENCY, (String) sessionEvent.predefinedAttributes.get(FirebaseAnalytics.Param.CURRENCY));
            bundle.putLong(FirebaseAnalytics.Param.QUANTITY, 1);
        } else if ("startCheckout".equals(sessionEvent.predefinedType)) {
            putLong(bundle, FirebaseAnalytics.Param.QUANTITY, Long.valueOf((long) ((Integer) sessionEvent.predefinedAttributes.get("itemCount")).intValue()));
            putDouble(bundle, FirebaseAnalytics.Param.VALUE, mapPriceValue(sessionEvent.predefinedAttributes.get("totalPrice")));
            putString(bundle, FirebaseAnalytics.Param.CURRENCY, (String) sessionEvent.predefinedAttributes.get(FirebaseAnalytics.Param.CURRENCY));
        } else if ("contentView".equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.CONTENT_TYPE, (String) sessionEvent.predefinedAttributes.get("contentType"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("contentId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("contentName"));
        } else if (FirebaseAnalytics.Event.SEARCH.equals(sessionEvent.predefinedType)) {
            putString(bundle, FirebaseAnalytics.Param.SEARCH_TERM, (String) sessionEvent.predefinedAttributes.get(SearchIntents.EXTRA_QUERY));
        } else if ("share".equals(sessionEvent.predefinedType)) {
            putString(bundle, FIREBASE_METHOD, (String) sessionEvent.predefinedAttributes.get(FIREBASE_METHOD));
            putString(bundle, FirebaseAnalytics.Param.CONTENT_TYPE, (String) sessionEvent.predefinedAttributes.get("contentType"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("contentId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("contentName"));
        } else if (FIREBASE_RATING.equals(sessionEvent.predefinedType)) {
            putString(bundle, FIREBASE_RATING, String.valueOf(sessionEvent.predefinedAttributes.get(FIREBASE_RATING)));
            putString(bundle, FirebaseAnalytics.Param.CONTENT_TYPE, (String) sessionEvent.predefinedAttributes.get("contentType"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_ID, (String) sessionEvent.predefinedAttributes.get("contentId"));
            putString(bundle, FirebaseAnalytics.Param.ITEM_NAME, (String) sessionEvent.predefinedAttributes.get("contentName"));
        } else if ("signUp".equals(sessionEvent.predefinedType)) {
            putString(bundle, FIREBASE_METHOD, (String) sessionEvent.predefinedAttributes.get(FIREBASE_METHOD));
        } else if (FirebaseAnalytics.Event.LOGIN.equals(sessionEvent.predefinedType)) {
            putString(bundle, FIREBASE_METHOD, (String) sessionEvent.predefinedAttributes.get(FIREBASE_METHOD));
        } else if ("invite".equals(sessionEvent.predefinedType)) {
            putString(bundle, FIREBASE_METHOD, (String) sessionEvent.predefinedAttributes.get(FIREBASE_METHOD));
        } else if ("levelStart".equals(sessionEvent.predefinedType)) {
            putString(bundle, FIREBASE_LEVEL_NAME, (String) sessionEvent.predefinedAttributes.get("levelName"));
        } else if ("levelEnd".equals(sessionEvent.predefinedType)) {
            putDouble(bundle, FirebaseAnalytics.Param.SCORE, mapDouble(sessionEvent.predefinedAttributes.get(FirebaseAnalytics.Param.SCORE)));
            putString(bundle, FIREBASE_LEVEL_NAME, (String) sessionEvent.predefinedAttributes.get("levelName"));
            putInt(bundle, "success", mapBooleanValue((String) sessionEvent.predefinedAttributes.get("success")));
        }
        mapCustomEventAttributes(bundle, sessionEvent.customAttributes);
        return bundle;
    }

    private void putLong(Bundle bundle, String str, Long l) {
        if (l != null) {
            bundle.putLong(str, l.longValue());
        }
    }

    private void putInt(Bundle bundle, String str, Integer num) {
        if (num != null) {
            bundle.putInt(str, num.intValue());
        }
    }

    private void putString(Bundle bundle, String str, String str2) {
        if (str2 != null) {
            bundle.putString(str, str2);
        }
    }

    private void putDouble(Bundle bundle, String str, Double d) {
        Double mapDouble = mapDouble(d);
        if (mapDouble != null) {
            bundle.putDouble(str, mapDouble.doubleValue());
        }
    }

    private Double mapDouble(Object obj) {
        String valueOf = String.valueOf(obj);
        if (valueOf == null) {
            return null;
        }
        return Double.valueOf(valueOf);
    }

    private Integer mapBooleanValue(String str) {
        if (str == null) {
            return null;
        }
        return Integer.valueOf(str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) ? 1 : 0);
    }

    private Double mapPriceValue(Object obj) {
        if (((Long) obj) == null) {
            return null;
        }
        return Double.valueOf(new BigDecimal(((Long) obj).longValue()).divide(AddToCartEvent.MICRO_CONSTANT).doubleValue());
    }

    private void mapCustomEventAttributes(Bundle bundle, Map<String, Object> map) {
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            String mapAttribute = mapAttribute((String) next.getKey());
            if (value instanceof String) {
                bundle.putString(mapAttribute, next.getValue().toString());
            } else if (value instanceof Double) {
                bundle.putDouble(mapAttribute, ((Double) next.getValue()).doubleValue());
            } else if (value instanceof Long) {
                bundle.putLong(mapAttribute, ((Long) next.getValue()).longValue());
            } else if (value instanceof Integer) {
                bundle.putInt(mapAttribute, ((Integer) next.getValue()).intValue());
            }
        }
    }
}
