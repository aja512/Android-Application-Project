package com.example.vegito.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Utility functions for dealing with Activity classes.
 */
public final class ActivityUtils {

    private static final String MARKET_URI_PREFIX = "market://details?id=";
    private static final String BROWSER_URI_PREFIX = "https://play.google.com/store/apps/details?id=";

    private ActivityUtils() {
        // hide constructor
    }

    /**
     * Attempts to cast the parents of the supplied Fragment to the supplied type. First tries the
     * target fragment, then the parent fragment, finally the Activity.
     *
     * @param fragment the Fragment
     * @param type     the type to cast the parent to
     * @param <T>      the type of the class
     * @return the parent cast as the required type
     */
    public static <T> T parentAsRequiredType(@NonNull Fragment fragment, Class<T> type) {
        T co = ObjectUtils.asOptionalType(fragment.getTargetFragment(), type);
        if (co != null) {
            return co;
        }

        co = ObjectUtils.asOptionalType(fragment.getParentFragment(), type);
        if (co != null) {
            return co;
        }

        co = ObjectUtils.asOptionalType(fragment.getActivity(), type);
        if (co != null) {
            return co;
        }

        throw new ClassCastException(
                "Object returned by getTargetFragment(), getParentFragment(), "
                        + "or getActivity() must implement " + type.getSimpleName());
    }

    /**
     * Attempts to cast the parents of the supplied Fragment to the supplied type. First tries the
     * target fragment, then the parent fragment, finally the Activity.
     *
     * @param fragment the Fragment
     * @param type     the type to cast the parent to
     * @param <T>      the type of the class
     * @return the parent cast as the optional type
     */
    public static <T> T parentAsOptionalType(@NonNull Fragment fragment, Class<T> type) {
        T co = ObjectUtils.asOptionalType(fragment.getTargetFragment(), type);
        if (co != null) {
            return co;
        }

        co = ObjectUtils.asOptionalType(fragment.getParentFragment(), type);
        if (co != null) {
            return co;
        }

        co = ObjectUtils.asOptionalType(fragment.getActivity(), type);
        if (co != null) {
            return co;
        }

        return null;
    }

    /**
     * Creates intent to Open Android Settings page.
     *
     * @return intent
     */
    public static Intent createSettingsIntent() {
        return new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
    }

    /**
     * This method is used to create an intent which will redirect to app store
     *
     * @param context context of the application
     */
    public static void getAppUpdateIntent(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URI_PREFIX + appPackageName)));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(BROWSER_URI_PREFIX + appPackageName)));
        }
    }
}