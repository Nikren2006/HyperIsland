package androidx.core.os;

import android.os.Trace;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class TraceCompat {
    private static final String TAG = "TraceCompat";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void beginAsyncSection(String str, int i2) {
            Trace.beginAsyncSection(str, i2);
        }

        public static void endAsyncSection(String str, int i2) {
            Trace.endAsyncSection(str, i2);
        }

        public static boolean isEnabled() {
            return Trace.isEnabled();
        }

        public static void setCounter(String str, long j2) {
            Trace.setCounter(str, j2);
        }
    }

    private TraceCompat() {
    }

    public static void beginAsyncSection(String str, int i2) {
        Api29Impl.beginAsyncSection(str, i2);
    }

    public static void beginSection(String str) {
        Trace.beginSection(str);
    }

    public static void endAsyncSection(String str, int i2) {
        Api29Impl.endAsyncSection(str, i2);
    }

    public static void endSection() {
        Trace.endSection();
    }

    public static boolean isEnabled() {
        return Api29Impl.isEnabled();
    }

    public static void setCounter(String str, int i2) {
        Api29Impl.setCounter(str, i2);
    }
}
