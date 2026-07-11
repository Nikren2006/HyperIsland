package androidx.core.app;

import android.app.Dialog;
import android.view.View;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public class DialogCompat {

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static <T> T requireViewById(Dialog dialog, int i2) {
            return (T) dialog.requireViewById(i2);
        }
    }

    private DialogCompat() {
    }

    public static View requireViewById(Dialog dialog, int i2) {
        return (View) Api28Impl.requireViewById(dialog, i2);
    }
}
