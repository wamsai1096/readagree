package io.wamsai.readagree;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class ReadAgreeImpl implements IReadAgree {

    private Context mContext;

    private LinkedHashMap<String, ReadAgreeItem> mReadAgreeItemMap = new LinkedHashMap<>();

    private IReadAgreeDialogFactory mReadAgreeDialog;

    private final String mSpKeyReadAgree = "SP_KEY_READ_AGREE";

    protected ReadAgreeImpl(Context context) {
        mContext = context.getApplicationContext();
        mReadAgreeDialog = new ReadAgreeDialogFactory(this);
    }

    @Override
    public boolean isReadAndAgree() {
        return Utils.getSp(mContext).getBoolean(mSpKeyReadAgree, false);
    }

    @Override
    public void setReadAndAgree(boolean isReadAndAgree) {
        Utils.getSp(mContext).edit().putBoolean(mSpKeyReadAgree, isReadAndAgree).apply();
    }

    @Override
    public SpannableString createToUrlWithClickableContent(final Context context, String content) {
        SpannableString spannableString = new SpannableString(content);


        Collection<ReadAgreeItem> values = this.getReadAgreeItemMap().values();

        for (final ReadAgreeItem value : values) {
            int count = 0;
            int startIndex = 0;
            String child = value.name();
            while ((startIndex = content.indexOf(value.name(), startIndex)) != -1) {
                spannableString.setSpan(new ClickableSpan() {

                    @Override
                    public void onClick(@NonNull View widget) {
                        value.onToUrl(context);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        ds.setColor(ds.linkColor);
                        ds.setUnderlineText(false);
                    }

                }, startIndex, startIndex + child.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                startIndex += child.length();
                count++;
            }
        }

//        if (values.size() == 1) {
//            final ReadAgreeItem item = values.iterator().next();
//            list.add(createSpannableStringClick(item, new ClickableSpan() {
//                @Override
//                public void onClick(@NonNull View widget) {
//                    item.onToUrl(context);
//                }
//            }));
//        }
//        else if (values.size() > 1){
//            for (final ReadAgreeItem item : values) {
//                list.add(createSpannableStringClick(item, new ClickableSpan() {
//                    @Override
//                    public void onClick(@NonNull View widget) {
//                        item.onToUrl(context);
//                    }
//                }));
//            }
//        }

        return spannableString;
    }

    @Override
    public IReadAgreeDialogFactory getReadAgreeDialogFactory() {
        return mReadAgreeDialog;
    }

    @Override
    public LinkedHashMap<String, ReadAgreeItem> getReadAgreeItemMap() {
        return mReadAgreeItemMap;
    }

}
