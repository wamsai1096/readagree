package io.wamsai.readagree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.util.Collection;

/**
 * Created on 2019/11/23.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class ReadAgreeUtils {

    public static void setClickableContent(IReadAgree readAgree, TextView dest) {
        StringBuilder sb = new StringBuilder();

        Resources resources = dest.getResources();
        Collection<ReadAgreeItem> values = readAgree.getReadAgreeItemMap().values();
        for (ReadAgreeItem item : values) {
            sb.append(item.name()).append(resources.getString(R.string.readagree_dialog_comma));
        }

        setClickableContent(readAgree, dest, sb.toString());
    }

    public static void setClickableContent(IReadAgree readAgree, TextView dest, String content) {
        SpannableString spannableString = readAgree.createToUrlWithClickableContent(
                dest.getContext(), content);

        dest.setMovementMethod(LinkMovementMethod.getInstance());
        dest.setText(spannableString);
    }


    /**
     * Exit the application.
     */
    public static void exitApp(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (!(context instanceof Activity)) {
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(homeIntent);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 500);
    }

}
