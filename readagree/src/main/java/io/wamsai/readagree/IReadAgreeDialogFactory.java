package io.wamsai.readagree;

import android.content.Context;

import androidx.annotation.Nullable;

/**
 * Created on 2019/11/22.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public interface IReadAgreeDialogFactory {

    void showReadAgreeDialog(Context context, @Nullable ReadAgreeDialogListener listener);

    interface ReadAgreeDialogListener {

        /**
         * @return true handle agree process, the default process not run.
         */
        boolean onUserAgreeHandle(boolean isAgree);

    }
}
