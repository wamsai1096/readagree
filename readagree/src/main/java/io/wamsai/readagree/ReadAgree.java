package io.wamsai.readagree;

import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/11/21.
 *
 * @author WAMsAI (WAMsAI1096@gmail.com)
 */
public class ReadAgree {

    private static IReadAgree INSTANCE;

    private static Context sContext;


    public static ReadAgreeConfig config(Context context) {
        return config(context, null);
    }

    public static ReadAgreeConfig config(Context context, IReadAgree readAgree) {
        sContext = context.getApplicationContext();
        INSTANCE = readAgree;
        if (INSTANCE == null) {
            // use default
            INSTANCE = new ReadAgreeImpl(sContext);
        }
        return new ReadAgreeConfig(INSTANCE);
    }

    /**
     * @return if true need to show dialog.
     */
    public static boolean showReadAgreeDialogIfNeed(
            final Context context,
            IReadAgreeDialogFactory.ReadAgreeDialogListener listener) {
        if (!INSTANCE.isReadAndAgree()) {
            INSTANCE.getReadAgreeDialogFactory().showReadAgreeDialog(context, listener);
            return true;
        }
        return false;
    }

    /**
     * @param key {@link ReadAgreeItem#key()}.
     */
    @Nullable
    public static ReadAgreeItem findReadAgreeItem(String key) {
        return INSTANCE.getReadAgreeItemMap().get(key);
    }

    public static IReadAgree getInstance() {
        return INSTANCE;
    }

    static Context getContext() {
        return sContext;
    }


    public static class ReadAgreeConfig {

        private IReadAgree mReadAgree;

        private List<ReadAgreeItem> mReadAgreeItems = new ArrayList<>();

        ReadAgreeConfig(IReadAgree agree) {
            mReadAgree = agree;
        }

        public ReadAgreeConfig addReadAgreeItem(ReadAgreeItem item) {
            if (item == null) {
                return this;
            }
            mReadAgreeItems.add(item);
            return this;
        }

        public IReadAgree install() {
            mReadAgree.getReadAgreeItemMap().clear();
            for (ReadAgreeItem readAgreeItem : mReadAgreeItems) {
                mReadAgree.getReadAgreeItemMap().put(readAgreeItem.key(), readAgreeItem);
            }
            return mReadAgree;
        }

    }

}
