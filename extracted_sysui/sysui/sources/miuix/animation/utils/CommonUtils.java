package miuix.animation.utils;

import android.animation.ArgbEvaluator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import miuix.animation.IAnimTarget;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewProperty;

/* JADX INFO: loaded from: classes4.dex */
public class CommonUtils {
    public static final String D_TAG = "folme_design";
    public static final String TAG = "miuix_anim";
    public static final int UNIT_MILLIS_SECOND = 1000000000;
    public static final int UNIT_SECOND = 1000;
    private static float sTouchSlop;
    public static final ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
    private static final Class<?>[] BUILT_IN = {String.class, Integer.TYPE, Integer.class, Long.TYPE, Long.class, Short.TYPE, Short.class, Float.TYPE, Float.class, Double.TYPE, Double.class};
    private static ArrayMap<String, Long> sTimeStatArray = new ArrayMap<>();

    public static class OnPreDrawTask implements ViewTreeObserver.OnPreDrawListener {
        Runnable mTask;
        WeakReference<View> mView;

        public OnPreDrawTask(Runnable runnable) {
            this.mTask = runnable;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            View view = this.mView.get();
            if (view != null) {
                Runnable runnable = this.mTask;
                if (runnable != null) {
                    runnable.run();
                }
                view.getViewTreeObserver().removeOnPreDrawListener(this);
            }
            this.mTask = null;
            return true;
        }

        public void start(View view) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.mView = new WeakReference<>(view);
            viewTreeObserver.addOnPreDrawListener(this);
        }
    }

    private CommonUtils() {
    }

    public static <T> void addTo(Collection<T> collection, Collection<T> collection2) {
        for (T t2 : collection) {
            if (!collection2.contains(t2)) {
                collection2.add(t2);
            }
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                Log.w(TAG, "close " + closeable + " failed", e2);
            }
        }
    }

    public static Bitmap compressImage(@NonNull Bitmap bitmap, @IntRange(from = 0, to = 100) int i2, @IntRange(from = 1, to = 50) int i3) {
        StringBuilder sb;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmapDecodeStream = null;
        try {
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = i3;
                bitmapDecodeStream = BitmapFactory.decodeStream(parseToInputStream(byteArrayOutputStream), null, options);
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    Log.i(TAG, "IO close fail, " + e2);
                }
                throw th;
            }
        } catch (Exception e3) {
            Log.w(TAG, "TintDrawable.compressImage failed, " + e3);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                e = e4;
                sb = new StringBuilder();
                sb.append("IO close fail, ");
                sb.append(e);
                Log.i(TAG, sb.toString());
            }
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e5) {
            e = e5;
            sb = new StringBuilder();
            sb.append("IO close fail, ");
            sb.append(e);
            Log.i(TAG, sb.toString());
        }
        return bitmapDecodeStream;
    }

    public static double getDistance(float f2, float f3, float f4, float f5) {
        return Math.sqrt(Math.pow(f4 - f2, 2.0d) + Math.pow(f5 - f3, 2.0d));
    }

    public static <T> T getLocal(ObjectPool objectPool, ThreadLocal<T> threadLocal, Class cls) {
        T t2 = threadLocal.get();
        if (t2 != null || cls == null) {
            return t2;
        }
        T t3 = (T) ObjectPool.acquire(objectPool, cls, new Object[0]);
        threadLocal.set(t3);
        return t3;
    }

    public static void getRect(IAnimTarget iAnimTarget, RectF rectF) {
        rectF.left = iAnimTarget.getValue(ViewProperty.f6001X);
        rectF.top = iAnimTarget.getValue(ViewProperty.f6002Y);
        rectF.right = rectF.left + iAnimTarget.getValue(ViewProperty.WIDTH);
        rectF.bottom = rectF.top + iAnimTarget.getValue(ViewProperty.HEIGHT);
    }

    public static float getSize(IAnimTarget iAnimTarget, FloatProperty floatProperty) {
        if (floatProperty == ViewProperty.f6001X) {
            floatProperty = ViewProperty.WIDTH;
        } else if (floatProperty == ViewProperty.f6002Y) {
            floatProperty = ViewProperty.HEIGHT;
        } else if (floatProperty != ViewProperty.WIDTH && floatProperty != ViewProperty.HEIGHT) {
            floatProperty = null;
        }
        if (floatProperty == null) {
            return 0.0f;
        }
        return iAnimTarget.getValue(floatProperty);
    }

    public static float getTouchSlop(View view) {
        if (sTouchSlop == 0.0f && view != null) {
            sTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        }
        return sTouchSlop;
    }

    public static boolean hasFlags(long j2, long j3) {
        return (j2 & j3) != 0;
    }

    public static <T> boolean inArray(T[] tArr, T t2) {
        if (t2 != null && tArr != null && tArr.length > 0) {
            for (T t3 : tArr) {
                if (t3.equals(t2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> boolean isArrayEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static boolean isBuiltInClass(Class<?> cls) {
        return inArray(BUILT_IN, cls);
    }

    public static <K, V> StringBuilder mapToString(Map<K, V> map, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        if (map != null && map.size() > 0) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                sb.append('\n');
                sb.append(str);
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            sb.append('\n');
        }
        sb.append('\t');
        sb.append('}');
        return sb;
    }

    public static String mapsToString(Map[] mapArr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i2 = 0; i2 < mapArr.length; i2++) {
            sb.append('\n');
            sb.append(i2);
            sb.append('.');
            sb.append((CharSequence) mapToString(mapArr[i2], "    "));
        }
        sb.append('\n');
        sb.append(']');
        return sb.toString();
    }

    @SafeVarargs
    public static <T> T[] mergeArray(T[] tArr, T... tArr2) {
        if (tArr == null) {
            return tArr2;
        }
        if (tArr2 == null) {
            return tArr;
        }
        Object objNewInstance = Array.newInstance(tArr.getClass().getComponentType(), tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, objNewInstance, 0, tArr.length);
        System.arraycopy(tArr2, 0, objNewInstance, tArr.length, tArr2.length);
        return (T[]) ((Object[]) objNewInstance);
    }

    public static ByteArrayInputStream parseToInputStream(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.Closeable] */
    public static String readProp(String str) {
        InputStreamReader inputStreamReader;
        Throwable th;
        IOException e2;
        BufferedReader bufferedReader;
        try {
            try {
                inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("getprop " + ((String) str)).getInputStream());
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            inputStreamReader = null;
            e2 = e3;
            bufferedReader = null;
        } catch (Throwable th3) {
            inputStreamReader = null;
            th = th3;
            str = 0;
        }
        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                closeQuietly(bufferedReader);
                closeQuietly(inputStreamReader);
                return line;
            } catch (IOException e4) {
                e2 = e4;
                Log.i(TAG, "readProp failed", e2);
                closeQuietly(bufferedReader);
                closeQuietly(inputStreamReader);
                return "";
            }
        } catch (IOException e5) {
            e2 = e5;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            str = 0;
            closeQuietly(str);
            closeQuietly(inputStreamReader);
            throw th;
        }
    }

    public static void runOnPreDraw(View view, Runnable runnable) {
        if (view == null) {
            return;
        }
        new OnPreDrawTask(runnable).start(view);
    }

    public static StringBuilder setToString(Set set) {
        StringBuilder sb = new StringBuilder();
        if (set == null) {
            sb.append("null");
            return sb;
        }
        sb.append('{');
        Object[] array = set.toArray();
        for (int i2 = 0; i2 < set.size(); i2++) {
            sb.append('\n');
            sb.append(i2);
            sb.append('.');
            sb.append(array[i2]);
        }
        sb.append('\n');
        sb.append('}');
        return sb;
    }

    public static void timeStatBegin(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                sTimeStatArray.put(str, Long.valueOf(jCurrentTimeMillis));
            }
        }
    }

    public static void timeStatClear() {
        sTimeStatArray.clear();
    }

    public static long timeStatEnd(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = jCurrentTimeMillis - (sTimeStatArray.get(str) != null ? jCurrentTimeMillis : 0L);
        if (LogUtils.isLogMainEnabled()) {
            LogUtils.debug("打印当前用时： TAG=" + str + " 用时为 " + j2, new Object[0]);
        }
        return j2;
    }

    public static float toFloatValue(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).floatValue();
        }
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        throw new IllegalArgumentException("toFloat failed, value is " + obj);
    }

    public static int[] toIntArray(float[] fArr) {
        int[] iArr = new int[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            iArr[i2] = (int) fArr[i2];
        }
        return iArr;
    }

    public static int toIntValue(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Float) {
            return ((Float) obj).intValue();
        }
        throw new IllegalArgumentException("toFloat failed, value is " + obj);
    }
}
