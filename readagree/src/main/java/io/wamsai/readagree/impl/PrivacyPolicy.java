package io.wamsai.readagree.impl;

import java.util.Locale;
import java.util.Map;

import io.wamsai.readagree.BaseReadAgreeItem;
import io.wamsai.readagree.IReadAgree;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class PrivacyPolicy extends BaseReadAgreeItem {

    public PrivacyPolicy(int nameId, String url) {
        this(nameId, url, null);
    }

    public PrivacyPolicy(int nameId, String url, Map<Locale, String> urlMap) {
        super(IReadAgree.KEY_PRIVACY_POLICY, nameId, url, urlMap);
    }

}
