package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Message;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class MessageCompat {
    private static boolean sTryIsAsynchronous = true;
    private static boolean sTrySetAsynchronous = true;

    @RequiresApi(22)
    public static class Api22Impl {
        private Api22Impl() {
        }

        public static boolean isAsynchronous(Message message) {
            return message.isAsynchronous();
        }

        public static void setAsynchronous(Message message, boolean z2) {
            message.setAsynchronous(z2);
        }
    }

    private MessageCompat() {
    }

    @SuppressLint({"NewApi"})
    public static boolean isAsynchronous(Message message) {
        return Api22Impl.isAsynchronous(message);
    }

    @SuppressLint({"NewApi"})
    public static void setAsynchronous(Message message, boolean z2) {
        Api22Impl.setAsynchronous(message, z2);
    }
}
