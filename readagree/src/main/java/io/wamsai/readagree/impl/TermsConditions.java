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
public class TermsConditions extends BaseReadAgreeItem {

    public TermsConditions(int nameId, String url) {
        this(nameId, url, null);
    }

    public TermsConditions(int nameId, String url, Map<Locale, String> urlMap) {
        super(IReadAgree.KEY_TERMS_AND_CONDITIONS, nameId, url, urlMap);
    }

}
