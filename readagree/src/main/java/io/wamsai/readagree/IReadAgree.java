package io.wamsai.readagree;

import android.content.Context;
import android.text.SpannableString;

import java.util.LinkedHashMap;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public interface IReadAgree {

    String KEY_PRIVACY_POLICY = "PRIVACY_POLICY";

    String KEY_TERMS_AND_CONDITIONS = "TERMS_AND_CONDITIONS";


    boolean isReadAndAgree();

    void setReadAndAgree(boolean isReadAndAgree);

    SpannableString createToUrlWithClickableContent(Context context, String content);

    IReadAgreeDialogFactory getReadAgreeDialogFactory();

    LinkedHashMap<String, ReadAgreeItem> getReadAgreeItemMap();

}
