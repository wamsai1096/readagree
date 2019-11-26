package io.wamsai.readagree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class BaseReadAgreeItem implements ReadAgreeItem {

    public static final String TAG = "BaseReadAgreeItem";

    final
    private String mKey;

    @StringRes
    final
    private int mIdName;

    final
    private String mUrl;

    final Map<Locale, String> mUrlMap = new HashMap<>();

    /**
     * @param key    {@link #key()}
     * @param nameId {@link #name()}
     * @param url    {@link #url()}
     */
    public BaseReadAgreeItem(String key, @StringRes int nameId, String url) {
        this(key, nameId, url, null);
    }

    public BaseReadAgreeItem(String key,
                             @StringRes int nameId,
                             String url,
                             Map<Locale, String> urlMap) {
        mKey = key;

        mIdName = nameId;
        mUrl = url;

        if (urlMap != null) {
            mUrlMap.clear();
            mUrlMap.putAll(urlMap);
        }
    }

    protected String getResString(@StringRes int id) {
        return ReadAgree.getContext().getResources().getString(id);
    }

    @Override
    public String key() {
        return mKey;
    }

    @Override
    public String name() {
        return getResString(mIdName);
    }

    @Override
    public String url() {
        return mUrl;
    }

    @Override
    public String url(Locale locale, boolean onlyLang) {
        if (onlyLang) {
            Set<Locale> localeKeyset = mUrlMap.keySet();

            for (Locale item : localeKeyset) {
                if (locale.getLanguage().equals(item.getLanguage())) {
                    return mUrlMap.get(item);
                }
            }
            return null;
        } else {
            return mUrlMap.get(locale);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onToUrl(Context context) {
        Log.i(TAG, "onToUrl: " + toString());

        Intent intent = new Intent(context, ReadAgreeWebActivity.class);
        intent.putExtra(ReadAgreeWebActivity.KEY_DATA, this);

        if (context instanceof Activity) {
            context.startActivity(intent);
        } else if (context == context.getApplicationContext()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "key='" + key() + '\'' +
                ", name=" + name() +
                ", url='" + url() + '\'' +
                ", urlMap=" + mUrlMap +
                '}';
    }
}
