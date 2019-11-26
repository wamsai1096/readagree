package io.wamsai.readagree;

import androidx.annotation.Nullable;

import java.util.regex.Pattern;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class UrlParser {

    /**
     * @param url {@link ReadAgreeItem#url()}.
     * @return if null, parse failure!
     */
    @Nullable
    public static UrlType parse(String url) {
        if (url == null) return null;
        if (url.isEmpty()) return null;

        if (UrlType.WEB_URL.matches(url)) {
            return UrlType.WEB_URL;
        } else if (UrlType.LOCAL_STORAGE.matches(url)) {
            return UrlType.LOCAL_STORAGE;
        } else if (UrlType.ASSET_FILE.matches(url)) {
            return UrlType.ASSET_FILE;
        }

        return null;
    }

    public enum UrlType {
        WEB_URL("http.*|www.*"),
        LOCAL_STORAGE("/.*\\.html"),
        ASSET_FILE(".*\\.html");

        String mPattern;

        UrlType(String pattern) {
            mPattern = pattern;
        }

        public String getPattern() {
            return mPattern;
        }

        public boolean matches(String url) {
            return Pattern.matches(mPattern, url);
        }

    }

}
