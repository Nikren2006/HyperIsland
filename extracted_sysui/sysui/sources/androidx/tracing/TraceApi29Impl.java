package androidx.tracing;

import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(29)
final class TraceApi29Impl {
    private TraceApi29Impl() {
    }

    public static void beginAsyncSection(@NonNull String str, int i2) {
        android.os.Trace.beginAsyncSection(str, i2);
    }

    public static void endAsyncSection(@NonNull String str, int i2) {
        android.os.Trace.endAsyncSection(str, i2);
    }

    @DoNotInline
    public static boolean isEnabled() {
        return android.os.Trace.isEnabled();
    }

    public static void setCounter(@NonNull String str, int i2) {
        android.os.Trace.setCounter(str, i2);
    }
}
