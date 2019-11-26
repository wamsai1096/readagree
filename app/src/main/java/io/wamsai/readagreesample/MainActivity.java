package io.wamsai.readagreesample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.wamsai.readagree.ReadAgree;
import io.wamsai.readagree.ReadAgreeItem;
import io.wamsai.readagree.ReadAgreeUtils;
import io.wamsai.readagreesample.app.Consts;

public class MainActivity extends AppCompatActivity {

    private TextView mTvClickContent;

    private Button mBtnPrivacyPolicy;

    private Button mBtnUserAgreement;

    private Button mBtnAnotherAgreement;

    private Button mBtnDisagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvClickContent = findViewById(R.id.tv_click_content);
        mBtnPrivacyPolicy = findViewById(R.id.btn_privacy_policy);
        mBtnUserAgreement = findViewById(R.id.btn_user_agreement);
        mBtnAnotherAgreement = findViewById(R.id.btn_another_agreement);
        mBtnDisagree = findViewById(R.id.btn_disagree);

        // Make TextView text clickable
        ReadAgreeUtils.setClickableContent(ReadAgree.getInstance(), mTvClickContent);

        mBtnPrivacyPolicy.setOnClickListener(v -> {
            ReadAgreeItem item = ReadAgree.findReadAgreeItem(Consts.KEY_PRIVACY_POLICY);
            if (item != null) {
                item.onToUrl(MainActivity.this);
            }
        });
        mBtnUserAgreement.setOnClickListener(v -> {
            ReadAgreeItem item = ReadAgree.findReadAgreeItem(Consts.KEY_TERMS_AND_CONDITIONS);
            if (item != null) {
                item.onToUrl(MainActivity.this);
            }
        });
        mBtnAnotherAgreement.setOnClickListener(v -> {
            ReadAgreeItem item = ReadAgree.findReadAgreeItem(Consts.KEY_ANOTHER_AGREEMENT);
            if (item != null) {
                item.onToUrl(MainActivity.this);
            }
        });
        mBtnDisagree.setOnClickListener(v -> {
            ReadAgree.getInstance().setReadAndAgree(false);
            ReadAgreeUtils.exitApp(MainActivity.this);
        });

    }

}
