package io.wamsai.readagree;

import android.content.Context;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public interface ReadAgreeItem extends Serializable {

    /**
     * @return such as {@link IReadAgree#KEY_PRIVACY_POLICY},
     * {@link IReadAgree#KEY_TERMS_AND_CONDITIONS} or you can custom key.
     */
    String key();

    String name();

    /**
     * Such as: webView.loadUrl("file:///android_asset/about.html");
     *
     * @return if "xx.html", this is in Asset file like "file:///android_asset/about.html";<br>
     * if "/storage/emulated/0/Download/about.html", this is in LocalStorage;<br>
     * if "https://www.pgyer.com/about/privacy", this is web url;<br>
     * if not http, please set android:networkSecurityConfig  base-config cleartextTrafficPermitted="true"
     */
    String url();

    @Nullable
    String url(Locale locale, boolean onlyLang);

    void onToUrl(Context context);

}
