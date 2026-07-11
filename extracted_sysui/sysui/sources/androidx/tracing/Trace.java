package androidx.tracing;

import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class Trace {
    static final int MAX_TRACE_LABEL_LENGTH = 127;
    static final String TAG = "Trace";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static boolean sHasAppTracingEnabled;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    private Trace() {
    }

    public static void beginAsyncSection(@NonNull String str, int i2) {
        TraceApi29Impl.beginAsyncSection(truncatedTraceSectionLabel(str), i2);
    }

    private static void beginAsyncSectionFallback(@NonNull String str, int i2) {
        try {
            if (sAsyncTraceBeginMethod == null) {
                sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceBeginMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i2));
        } catch (Exception e2) {
            handleException("asyncTraceBegin", e2);
        }
    }

    public static void beginSection(@NonNull String str) {
        TraceApi18Impl.beginSection(truncatedTraceSectionLabel(str));
    }

    public static void endAsyncSection(@NonNull String str, int i2) {
        TraceApi29Impl.endAsyncSection(truncatedTraceSectionLabel(str), i2);
    }

    private static void endAsyncSectionFallback(@NonNull String str, int i2) {
        try {
            if (sAsyncTraceEndMethod == null) {
                sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceEndMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i2));
        } catch (Exception e2) {
            handleException("asyncTraceEnd", e2);
        }
    }

    public static void endSection() {
        TraceApi18Impl.endSection();
    }

    public static void forceEnableAppTracing() {
    }

    private static void handleException(@NonNull String str, @NonNull Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v(TAG, "Unable to call " + str + " via reflection", exc);
    }

    public static boolean isEnabled() {
        return TraceApi29Impl.isEnabled();
    }

    private static boolean isEnabledFallback() {
        try {
            if (sIsTagEnabledMethod == null) {
                sTraceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception e2) {
            handleException("isTagEnabled", e2);
            return false;
        }
    }

    public static void setCounter(@NonNull String str, int i2) {
        TraceApi29Impl.setCounter(truncatedTraceSectionLabel(str), i2);
    }

    private static void setCounterFallback(@NonNull String str, int i2) {
        try {
            if (sTraceCounterMethod == null) {
                sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            sTraceCounterMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i2));
        } catch (Exception e2) {
            handleException("traceCounter", e2);
        }
    }

    @NonNull
    private static String truncatedTraceSectionLabel(@NonNull String str) {
        return str.length() <= MAX_TRACE_LABEL_LENGTH ? str : str.substring(0, MAX_TRACE_LABEL_LENGTH);
    }
}
