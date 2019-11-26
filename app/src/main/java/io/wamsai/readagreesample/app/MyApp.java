package io.wamsai.readagreesample.app;

import android.app.Application;

import java.util.HashMap;
import java.util.Locale;

import io.wamsai.readagree.BaseReadAgreeItem;
import io.wamsai.readagree.IReadAgree;
import io.wamsai.readagree.ReadAgree;
import io.wamsai.readagree.impl.PrivacyPolicy;
import io.wamsai.readagree.impl.TermsConditions;
import io.wamsai.readagreesample.R;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class MyApp extends Application {

    /**
     * https://policies.google.com/privacy?hl=en-US
     * https://policies.google.com/terms?hl=en-US
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: change language programmatically
        // ...

        HashMap<Locale, String> anotherLocale = new HashMap<>();
        anotherLocale.put(Locale.ENGLISH, "https://github.com/");
        anotherLocale.put(Locale.SIMPLIFIED_CHINESE, "http://www.baidu.com");

        IReadAgree readAgree = ReadAgree.config(this)
                .addReadAgreeItem(new PrivacyPolicy(
                        R.string.privacy_policy,
                        "privacy_policy.html"))
                .addReadAgreeItem(new TermsConditions(
                        R.string.user_agreement,
                        "terms_and_conditions.html"))
                .addReadAgreeItem(new BaseReadAgreeItem(
                        Consts.KEY_ANOTHER_AGREEMENT,
                        R.string.another_agreement,
                        anotherLocale.get(Locale.ENGLISH), // DEFAULT
                        anotherLocale))
                .install();
    }

}
