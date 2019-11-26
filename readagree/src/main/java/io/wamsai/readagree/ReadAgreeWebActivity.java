package io.wamsai.readagree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Locale;

public class ReadAgreeWebActivity extends AppCompatActivity {

    public static final String TAG = "ReadAgreeWebActivity";

    public static final String KEY_DATA = "KEY_READ_AGREE_ITEM";

    private WebView mWebView;

    private ReadAgreeItem mReadAgreeItem;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) { // back key
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readagree_activity_web);

        mWebView = findViewById(R.id.wb_content);

        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(KEY_DATA);
            mReadAgreeItem = (ReadAgreeItem) serializableExtra;

        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            if (mReadAgreeItem != null) {
                actionBar.setTitle(mReadAgreeItem.name());

                // I18N
                Locale systemLocale = Utils.getSystemLocale();

                // only compare lang
                String url = mReadAgreeItem.url(systemLocale, true);
                if (url == null) {
                    // DEFAULT
                    url = mReadAgreeItem.url();
                }

                initData(UrlParser.parse(url), url);
            }
        }

        Log.i(TAG, "onCreate: " + mReadAgreeItem);
    }

    private void initData(UrlParser.UrlType type, String url) {
        if (type == null) {
            return;
        }

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);


        switch (type) {
            case WEB_URL:
                mWebView.loadUrl(url);
                break;
            case ASSET_FILE:
                mWebView.loadUrl("file:///android_asset/" + url);
                break;
            case LOCAL_STORAGE:
                throw new UnsupportedOperationException(
                        "Not impl UrlParser.UrlType is LOCAL_STORAGE!");
        }

        Log.i(TAG, "initData: " + type);

    }
}
