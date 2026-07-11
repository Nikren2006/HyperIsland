package p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.provider.Settings;
import d.AbstractC0302c;
import f.u;
import java.io.Closeable;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f6340a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal f6341b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ThreadLocal f6342c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal f6343d = new d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f6344e = (float) (Math.sqrt(2.0d) / 2.0d);

    public class a extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PathMeasure initialValue() {
            return new PathMeasure();
        }
    }

    public class b extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Path initialValue() {
            return new Path();
        }
    }

    public class c extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Path initialValue() {
            return new Path();
        }
    }

    public class d extends ThreadLocal {
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public float[] initialValue() {
            return new float[4];
        }
    }

    public static void a(Path path, float f2, float f3, float f4) {
        AbstractC0302c.a("applyTrimPathIfNeeded");
        PathMeasure pathMeasure = (PathMeasure) f6340a.get();
        Path path2 = (Path) f6341b.get();
        Path path3 = (Path) f6342c.get();
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (f2 == 1.0f && f3 == 0.0f) {
            AbstractC0302c.b("applyTrimPathIfNeeded");
            return;
        }
        if (length < 1.0f || Math.abs((f3 - f2) - 1.0f) < 0.01d) {
            AbstractC0302c.b("applyTrimPathIfNeeded");
            return;
        }
        float f5 = f2 * length;
        float f6 = f3 * length;
        float f7 = f4 * length;
        float fMin = Math.min(f5, f6) + f7;
        float fMax = Math.max(f5, f6) + f7;
        if (fMin >= length && fMax >= length) {
            fMin = AbstractC0727g.f(fMin, length);
            fMax = AbstractC0727g.f(fMax, length);
        }
        if (fMin < 0.0f) {
            fMin = AbstractC0727g.f(fMin, length);
        }
        if (fMax < 0.0f) {
            fMax = AbstractC0727g.f(fMax, length);
        }
        if (fMin == fMax) {
            path.reset();
            AbstractC0302c.b("applyTrimPathIfNeeded");
            return;
        }
        if (fMin >= fMax) {
            fMin -= length;
        }
        path2.reset();
        pathMeasure.getSegment(fMin, fMax, path2, true);
        if (fMax > length) {
            path3.reset();
            pathMeasure.getSegment(0.0f, fMax % length, path3, true);
            path2.addPath(path3);
        } else if (fMin < 0.0f) {
            path3.reset();
            pathMeasure.getSegment(fMin + length, length, path3, true);
            path2.addPath(path3);
        }
        path.set(path2);
        AbstractC0302c.b("applyTrimPathIfNeeded");
    }

    public static void b(Path path, u uVar) {
        if (uVar == null || uVar.l()) {
            return;
        }
        a(path, ((g.d) uVar.j()).p() / 100.0f, ((g.d) uVar.g()).p() / 100.0f, ((g.d) uVar.i()).p() / 360.0f);
    }

    public static void c(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static Path d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        Path path = new Path();
        path.moveTo(pointF.x, pointF.y);
        if (pointF3 == null || pointF4 == null || (pointF3.length() == 0.0f && pointF4.length() == 0.0f)) {
            path.lineTo(pointF2.x, pointF2.y);
        } else {
            float f2 = pointF3.x + pointF.x;
            float f3 = pointF.y + pointF3.y;
            float f4 = pointF2.x;
            float f5 = f4 + pointF4.x;
            float f6 = pointF2.y;
            path.cubicTo(f2, f3, f5, f6 + pointF4.y, f4, f6);
        }
        return path;
    }

    public static float e() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float f(Context context) {
        return Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
    }

    public static float g(Matrix matrix) {
        float[] fArr = (float[]) f6343d.get();
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        float f2 = f6344e;
        fArr[2] = f2;
        fArr[3] = f2;
        matrix.mapPoints(fArr);
        return (float) Math.hypot(fArr[2] - fArr[0], fArr[3] - fArr[1]);
    }

    public static boolean h(Matrix matrix) {
        float[] fArr = (float[]) f6343d.get();
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = 37394.73f;
        fArr[3] = 39575.234f;
        matrix.mapPoints(fArr);
        return fArr[0] == fArr[2] || fArr[1] == fArr[3];
    }

    public static int i(float f2, float f3, float f4, float f5) {
        int i2 = f2 != 0.0f ? (int) (527 * f2) : 17;
        if (f3 != 0.0f) {
            i2 = (int) (i2 * 31 * f3);
        }
        if (f4 != 0.0f) {
            i2 = (int) (i2 * 31 * f4);
        }
        return f5 != 0.0f ? (int) (i2 * 31 * f5) : i2;
    }

    public static boolean j(int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i2 < i5) {
            return false;
        }
        if (i2 > i5) {
            return true;
        }
        if (i3 < i6) {
            return false;
        }
        return i3 > i6 || i4 >= i7;
    }

    public static boolean k(Throwable th) {
        return (th instanceof SocketException) || (th instanceof ClosedChannelException) || (th instanceof InterruptedIOException) || (th instanceof ProtocolException) || (th instanceof SSLException) || (th instanceof UnknownHostException) || (th instanceof UnknownServiceException);
    }

    public static Bitmap l(Bitmap bitmap, int i2, int i3) {
        if (bitmap.getWidth() == i2 && bitmap.getHeight() == i3) {
            return bitmap;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i3, true);
        bitmap.recycle();
        return bitmapCreateScaledBitmap;
    }

    public static void m(Canvas canvas, RectF rectF, Paint paint) {
        n(canvas, rectF, paint, 31);
    }

    public static void n(Canvas canvas, RectF rectF, Paint paint, int i2) {
        AbstractC0302c.a("Utils#saveLayer");
        canvas.saveLayer(rectF, paint);
        AbstractC0302c.b("Utils#saveLayer");
    }
}
