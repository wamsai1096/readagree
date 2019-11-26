package io.wamsai.readagree;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.Collection;
import java.util.Iterator;

import static io.wamsai.readagree.Utils.getAppName;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class ReadAgreeDialogFactory implements IReadAgreeDialogFactory {

    protected IReadAgree mReadAgree;

    public ReadAgreeDialogFactory(IReadAgree readAgree) {
        mReadAgree = readAgree;
    }

    @Override
    public void showReadAgreeDialog(final Context context,
                                    @Nullable final ReadAgreeDialogListener listener) {
        final IReadAgree readAgree = mReadAgree;

        String appName = getAppName(context, context.getApplicationContext().getPackageName());
        String agreeContent = getAgreeContent(context, readAgree);
        final String content = context.getResources()
                .getString(R.string.readagree_dialog_content,
                        appName,
                        appName,
                        agreeContent);

        //TextView textView = new TextView(context);
        //textView.setText(content);
        //textView.append();

        OnClickListener agree = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // agree
                boolean h = false;
                if (listener != null) {
                    h = listener.onUserAgreeHandle(true);
                }
                if (!h) {
                    readAgree.setReadAndAgree(true);
                }
            }
        };

        OnClickListener disagree = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // disagree
                boolean h = false;
                if (listener != null) {
                    h = listener.onUserAgreeHandle(false);
                }
                if (!h) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ReadAgreeUtils.exitApp(context.getApplicationContext());
                        }
                    }, 800);
                }
            }
        };

        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(R.string.readagree_dialog_title)
                .setMessage(content)
                //.setView(textView)
                .setPositiveButton(R.string.readagree_dialog_agree, agree)
                .setNegativeButton(R.string.readagree_dialog_disagree, disagree)
                .setCancelable(false)
                .create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                button.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
                button.setTextSize(11);

                TextView msg = alertDialog.findViewById(android.R.id.message);
                msg.setMovementMethod(LinkMovementMethod.getInstance()); // must be set to use onclick
                msg.setText(readAgree.createToUrlWithClickableContent(context, content));
            }
        });
        alertDialog.show();
    }

    public static String getAgreeContent(final Context context, IReadAgree readAgree) {
        Collection<ReadAgreeItem> values = readAgree.getReadAgreeItemMap().values();
        String comma = context.getResources().getString(R.string.readagree_dialog_comma);

        if (values.size() == 0) {
            return "null";
        } else if (values.size() == 1) {
            final ReadAgreeItem item = values.iterator().next();
            return item.name();
        } else if (values.size() == 2) {
            String and = context.getResources().getString(R.string.readagree_dialog_and);
            Iterator<ReadAgreeItem> iterator = values.iterator();
            return iterator.next().name() + and + iterator.next().name();
        } else {
            StringBuilder builder = new StringBuilder();
            int index = 0;
            for (final ReadAgreeItem item : values) {
                builder.append(item.name());
                if (index < values.size() - 1) {
                    builder.append(comma);
                }
                index++;
            }
            //builder.replace(builder.length() - 1, builder.length(), "");
            return builder.toString();
        }
    }

}
