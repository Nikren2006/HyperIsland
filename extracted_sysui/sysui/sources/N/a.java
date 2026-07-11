package N;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f429i = new int[3];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final float[] f430j = {0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f431k = new int[4];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final float[] f432l = {0.0f, 0.0f, 0.5f, 1.0f};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f433a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f434b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Paint f435c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f436d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f437e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f438f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Path f439g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f440h;

    public a() {
        this(ViewCompat.MEASURED_STATE_MASK);
    }

    public void a(Canvas canvas, Matrix matrix, RectF rectF, int i2, float f2, float f3) {
        boolean z2 = f3 < 0.0f;
        Path path = this.f439g;
        if (z2) {
            int[] iArr = f431k;
            iArr[0] = 0;
            iArr[1] = this.f438f;
            iArr[2] = this.f437e;
            iArr[3] = this.f436d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f2, f3);
            path.close();
            float f4 = -i2;
            rectF.inset(f4, f4);
            int[] iArr2 = f431k;
            iArr2[0] = 0;
            iArr2[1] = this.f436d;
            iArr2[2] = this.f437e;
            iArr2[3] = this.f438f;
        }
        float fWidth = rectF.width() / 2.0f;
        if (fWidth <= 0.0f) {
            return;
        }
        float f5 = 1.0f - (i2 / fWidth);
        float[] fArr = f432l;
        fArr[1] = f5;
        fArr[2] = ((1.0f - f5) / 2.0f) + f5;
        this.f434b.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, f431k, fArr, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z2) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, this.f440h);
        }
        canvas.drawArc(rectF, f2, f3, true, this.f434b);
        canvas.restore();
    }

    public void b(Canvas canvas, Matrix matrix, RectF rectF, int i2) {
        rectF.bottom += i2;
        rectF.offset(0.0f, -i2);
        int[] iArr = f429i;
        iArr[0] = this.f438f;
        iArr[1] = this.f437e;
        iArr[2] = this.f436d;
        Paint paint = this.f435c;
        float f2 = rectF.left;
        paint.setShader(new LinearGradient(f2, rectF.top, f2, rectF.bottom, iArr, f430j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.f435c);
        canvas.restore();
    }

    public Paint c() {
        return this.f433a;
    }

    public void d(int i2) {
        this.f436d = ColorUtils.setAlphaComponent(i2, 68);
        this.f437e = ColorUtils.setAlphaComponent(i2, 20);
        this.f438f = ColorUtils.setAlphaComponent(i2, 0);
        this.f433a.setColor(this.f436d);
    }

    public a(int i2) {
        this.f439g = new Path();
        Paint paint = new Paint();
        this.f440h = paint;
        this.f433a = new Paint();
        d(i2);
        paint.setColor(0);
        Paint paint2 = new Paint(4);
        this.f434b = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.f435c = new Paint(paint2);
    }
}
