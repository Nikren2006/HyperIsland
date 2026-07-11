package androidx.window.core;

import android.util.Log;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidLogger implements Logger {
    public static final AndroidLogger INSTANCE = new AndroidLogger();

    private AndroidLogger() {
    }

    @Override // androidx.window.core.Logger
    public void debug(String tag, String message) {
        n.g(tag, "tag");
        n.g(message, "message");
        Log.d(tag, message);
    }
}
