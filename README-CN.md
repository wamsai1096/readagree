# ReadAgree

[![][jitpackSvg]][jitpack] [![][androidciSvg]][androidci] [![api][apiSvg]][api] [![build][buildSvg]][build] [![License][licenseSvg]][license]

## About

**[ReadAgree][readme]** 可以显示《隐私政策》和《用户协议》的用户确认Dialog，包括About的界面的入口。

**Feature:**

- [x] 支持应用初次安装和第二次打开的入口配置
- [x] 支持国际化
- [x] 支持配置Assets(html)和Web url(html)
- [ ] 支持本地存储卡(html)
- [ ] 通用控件

## Download

Step 1. 将 JitPack 存储库添加到您的构建文件

将它添加到存储库末尾的根目录 `settings.gradle` 中：
```
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. 添加依赖
```
implementation 'com.github.wamsai1096:readagree:v0.0.4'
```

## Use

Application init:
```
// I18N
//HashMap<Locale, String> anotherLocale = new HashMap<>();
//anotherLocale.put(Locale.ENGLISH, "https://github.com/");
//anotherLocale.put(Locale.SIMPLIFIED_CHINESE, "http://www.baidu.com"); // 网址的隐私协议

IReadAgree readAgree = ReadAgree.config(this)
        .addReadAgreeItem(new PrivacyPolicy(
                R.string.privacy_policy,
                "privacy_policy.html"))  // assets 文件夹下的 privacy_policy.html
        .addReadAgreeItem(new TermsConditions(
                R.string.user_agreement,
                "terms_and_conditions.html")) // assets 文件夹下的 terms_and_conditions.html 也支持网页的写法如 "https://www.baidu.com"
        /* // 国际化使用
        .addReadAgreeItem(new BaseReadAgreeItem(
                Consts.KEY_ANOTHER_AGREEMENT,
                R.string.another_agreement,
                anotherLocale.get(Locale.ENGLISH), // DEFAULT ENGLISH
                anotherLocale))*/
        .install();
```

LaunchActivity:
```
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...

        boolean isNeed = ReadAgree.showReadAgreeDialogIfNeed(this, isAgree -> {
            if (isAgree) {
                finish();
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // ReadAgree will exit app
            }

            return false; // not handle
        });

        if (!isNeed) {
            new Handler().postDelayed(() -> {
                finish();
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
            }, 3000);
        }
    }
}
```

AboutActivity:
```
public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        // Make TextView text clickable, onClick goto privacy_policy.html...
        ReadAgreeUtils.setClickableContent(ReadAgree.getInstance(), mTvClickContent);

        // Button onClick goto privacy_policy.html...
        mBtnPrivacyPolicy.setOnClickListener(v -> {
            ReadAgreeItem item = ReadAgree.findReadAgreeItem(Consts.KEY_PRIVACY_POLICY);
            if (item != null) {
                item.onToUrl(MainActivity.this);
            }
        });

        // Button onClick goto terms_and_conditions.html...
        mBtnUserAgreement.setOnClickListener(v -> {
            ReadAgreeItem item = ReadAgree.findReadAgreeItem(Consts.KEY_TERMS_AND_CONDITIONS);
            if (item != null) {
                item.onToUrl(MainActivity.this);
            }
        });

        // Button onClick goto another_agreement.html...
        mBtnAnotherAgreement.setOnClickListener(v -> {
            ReadAgreeItem item = ReadAgree.findReadAgreeItem(Consts.KEY_ANOTHER_AGREEMENT);
            if (item != null) {
                item.onToUrl(MainActivity.this);
            }
        });
    }
}
```

## Sample

[Donwload](https://github.com/WAMsAI/readagree.git) and Run.

![](sample.gif)

## Contact

<wamsai1096@qq.com>

[![][contactGithubSvg]][contactGithub]

## [Change Log][changeLog.md]

[aucSvg]: https://img.shields.io/badge/ReadAgree-v0.0.3-brightgreen

[auc]: https://github.com/WAMsAI/readagree

[apiSvg]: https://img.shields.io/badge/API-19+-brightgreen.svg

[api]: https://android-arsenal.com/api?level=19

[buildSvg]: https://img.shields.io/badge/build-null-red
[build]: https://travis-ci.org/WAMsAI/readagree

[licenseSvg]: https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg
[license]: https://github.com/WAMsAI/readagree/blob/master/LICENSE

[readme]: https://github.com/WAMsAI/readagree
[readme-cn]: https://github.com/WAMsAI/readagree/blob/master/README-CN.md

[changeLog.md]: https://github.com/WAMsAI/readagree/blob/master/CHANGELOG.md

[blogSvg]:
[blog]:

[jianshuSvg]:
[jianshu]:

[weiboSvg]:
[weibo]:

[qqgroupSvg]:
[qqgroup]:

[contactGithubSvg]: https://img.shields.io/badge/GitHub-wamsai1096-blue.svg?logo=github

[contactGithub]: https://github.com/wamsai1096

[androidciSvg]:https://github.com/wamsai/readagree/workflows/Android%20CI/badge.svg?branch=master

[androidci]: https://github.com/wamsai1096/readagree

[jitpackSvg]: https://jitpack.io/v/wamsai1096/readagree.svg

[jitpack]: https://jitpack.io/#wamsai1096/readagree

