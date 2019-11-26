package io.wamsai.readagree;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
class Utils {

    final
    private static String SP_NAME = "readagree_sp";

    public static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static Locale getSystemLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT < 24) {
            locale = Locale.getDefault();
        } else {
            locale = LocaleList.getDefault().get(0);
        }
        return locale;
    }

    public static boolean isZhCn() {
        Locale locale = getSystemLocale();
        return locale.getLanguage().equals("zh") && locale.getCountry().equals("CN");
    }

    public static boolean isJa() {
        Locale locale = getSystemLocale();
        return locale.getLanguage().equals(new Locale("ja").getLanguage());
    }

    /**
     * Return the application's name.
     *
     * @param packageName The name of the package.
     * @return the application's name
     */
    public static String getAppName(Context context, final String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
    // copy from glide
    @NonNull
    public RequestManager get(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper
                    // Only unwrap a ContextWrapper if the baseContext has a non-null application context.
                    // Context#createPackageContext may return a Context without an Application instance,
                    // in which case a ContextWrapper may be used to attach one.
                    && ((ContextWrapper) context).getBaseContext().getApplicationContext() != null) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }

        return getApplicationManager(context);
    }
    */

}
