package miuix.popupwidget.internal.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import miuix.popupwidget.R;

/* JADX INFO: loaded from: classes5.dex */
public class ArrowPopupContentWrapper extends LinearLayout {
    public static final byte ARROW_BOTTOM_LEFT_MODE = 18;
    public static final byte ARROW_BOTTOM_MODE = 16;
    public static final byte ARROW_BOTTOM_RIGHT_MODE = 17;
    public static final byte ARROW_LEFT_MODE = 32;
    public static final byte ARROW_NONE_MODE = 0;
    public static final byte ARROW_RIGHT_MODE = 64;
    public static final byte ARROW_TOP_LEFT_MODE = 9;
    public static final byte ARROW_TOP_MODE = 8;
    public static final byte ARROW_TOP_RIGHT_MODE = 10;
    public static final int LAYOUT_MODE_LTR = 0;
    public static final int LAYOUT_MODE_RTL = 1;
    public static final int LAYOUT_MODE_UNSPECIFIED = 2;
    float density;
    private float mArrowHorizonOffset;
    private int mArrowMode;
    private Paint mBackgroundPaint;
    private boolean mIsRtl;
    private Bitmap mMask1;
    private Bitmap mMask2;
    private Bitmap mMask3;
    private Bitmap mMask4;
    private Paint mPaint;
    private Path mPath;
    private int mRtlMode;
    private PointF middle;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private PointF f6138p0;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    private PointF f6139p1;
    private PointF p2;
    private PointF p3;
    private PointF p4;
    private PointF p5;
    private PointF p6;
    private PointF p7;
    private PointF pA;
    private PointF pB;
    private PointF pC;
    private PointF pD;
    private PointF pE;
    private PointF pF;
    private PointF pG;
    private PointF pH;
    private PointF pI;
    private PointF pJ;
    private PointF pK;
    private PointF pL;
    private PointF pM;
    float paddingBottom;
    float paddingEnd;
    float paddingStart;
    float paddingTop;
    float radius;

    public ArrowPopupContentWrapper(Context context) {
        this(context, null);
    }

    private void drawBottomArrow(float f2, float f3, float f4, float f5, float f6, float f7, float f8, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7) {
        this.middle.set(f2 / 2.0f, f8);
        PointF pointF8 = this.pA;
        PointF pointF9 = this.middle;
        pointF8.set(pointF9.x + (14.0f * f4) + this.mArrowHorizonOffset, pointF9.y);
        this.pB.set(this.pA.x - (2.2988f * f4), f8);
        float f9 = (0.8772f * f4) + f8;
        this.pC.set(this.pA.x - (4.5169f * f4), f9);
        float f10 = (2.4636f * f4) + f8;
        this.pD.set(this.pA.x - (6.2295f * f4), f10);
        float f11 = f8 + (8.5073f * f4);
        this.pE.set(this.pA.x - (12.7541f * f4), f11);
        float f12 = f8 + (9.1642f * f4);
        this.pF.set(this.pA.x - (13.4633f * f4), f12);
        this.pG.set(this.pA.x - (14.5367f * f4), f12);
        this.pH.set(this.pA.x - (15.2459f * f4), f11);
        this.pI.set(this.pA.x - (21.7705f * f4), f10);
        this.pJ.set(this.pA.x - (23.4831f * f4), f9);
        this.pK.set(this.pA.x - (25.7012f * f4), f8);
        this.pL.set(this.pA.x - (28.0f * f4), f8);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            this.mPath.lineTo(f6 - f3, f7);
            this.mPath.quadTo(f6, f7, pointF2.x, pointF2.y);
            this.mPath.lineTo(pointF3.x, pointF3.y);
            this.mPath.quadTo(f6, f8, pointF4.x, pointF4.y);
            Path path2 = this.mPath;
            PointF pointF10 = this.pA;
            path2.lineTo(pointF10.x, pointF10.y);
            Path path3 = this.mPath;
            PointF pointF11 = this.pB;
            float f13 = pointF11.x;
            float f14 = pointF11.y;
            PointF pointF12 = this.pC;
            float f15 = pointF12.x;
            float f16 = pointF12.y;
            PointF pointF13 = this.pD;
            path3.cubicTo(f13, f14, f15, f16, pointF13.x, pointF13.y);
            Path path4 = this.mPath;
            PointF pointF14 = this.pE;
            path4.lineTo(pointF14.x, pointF14.y);
            Path path5 = this.mPath;
            PointF pointF15 = this.pF;
            float f17 = pointF15.x;
            float f18 = pointF15.y;
            PointF pointF16 = this.pG;
            float f19 = pointF16.x;
            float f20 = pointF16.y;
            PointF pointF17 = this.pH;
            path5.cubicTo(f17, f18, f19, f20, pointF17.x, pointF17.y);
            Path path6 = this.mPath;
            PointF pointF18 = this.pI;
            path6.lineTo(pointF18.x, pointF18.y);
            Path path7 = this.mPath;
            PointF pointF19 = this.pJ;
            float f21 = pointF19.x;
            float f22 = pointF19.y;
            PointF pointF20 = this.pK;
            float f23 = pointF20.x;
            float f24 = pointF20.y;
            PointF pointF21 = this.pL;
            path7.cubicTo(f21, f22, f23, f24, pointF21.x, pointF21.y);
            this.mPath.lineTo(pointF5.x, pointF5.y);
            this.mPath.quadTo(f5, f8, pointF6.x, pointF6.y);
            this.mPath.lineTo(pointF7.x, pointF7.y);
            this.mPath.quadTo(f5, f7, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void drawBottomLeftArrow(float f2, float f3, float f4, float f5, float f6, float f7, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
        float f8 = 28.0f * f3;
        this.pA.set(f4 + f2 + f8, f7);
        this.pB.set(this.pA.x - (2.2988f * f3), f7);
        float f9 = (0.8772f * f3) + f7;
        this.pC.set(this.pA.x - (4.5169f * f3), f9);
        float f10 = (2.4636f * f3) + f7;
        this.pD.set(this.pA.x - (6.2295f * f3), f10);
        float f11 = (8.5073f * f3) + f7;
        this.pE.set(this.pA.x - (12.7541f * f3), f11);
        float f12 = f7 + (9.1642f * f3);
        this.pF.set(this.pA.x - (13.4633f * f3), f12);
        this.pG.set(this.pA.x - (14.5367f * f3), f12);
        this.pH.set(this.pA.x - (15.2459f * f3), f11);
        this.pI.set(this.pA.x - (21.7705f * f3), f10);
        this.pJ.set(this.pA.x - (23.4831f * f3), f9);
        this.pK.set(this.pA.x - (25.7012f * f3), f7);
        this.pL.set(this.pA.x - f8, f7);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            this.mPath.lineTo(f5 - f2, f6);
            this.mPath.quadTo(f5, f6, pointF2.x, pointF2.y);
            this.mPath.lineTo(pointF3.x, pointF3.y);
            this.mPath.quadTo(f5, f7, pointF4.x, pointF4.y);
            Path path2 = this.mPath;
            PointF pointF6 = this.pA;
            path2.lineTo(pointF6.x, pointF6.y);
            Path path3 = this.mPath;
            PointF pointF7 = this.pB;
            float f13 = pointF7.x;
            float f14 = pointF7.y;
            PointF pointF8 = this.pC;
            float f15 = pointF8.x;
            float f16 = pointF8.y;
            PointF pointF9 = this.pD;
            path3.cubicTo(f13, f14, f15, f16, pointF9.x, pointF9.y);
            Path path4 = this.mPath;
            PointF pointF10 = this.pE;
            path4.lineTo(pointF10.x, pointF10.y);
            Path path5 = this.mPath;
            PointF pointF11 = this.pF;
            float f17 = pointF11.x;
            float f18 = pointF11.y;
            PointF pointF12 = this.pG;
            float f19 = pointF12.x;
            float f20 = pointF12.y;
            PointF pointF13 = this.pH;
            path5.cubicTo(f17, f18, f19, f20, pointF13.x, pointF13.y);
            Path path6 = this.mPath;
            PointF pointF14 = this.pI;
            path6.lineTo(pointF14.x, pointF14.y);
            Path path7 = this.mPath;
            PointF pointF15 = this.pJ;
            float f21 = pointF15.x;
            float f22 = pointF15.y;
            PointF pointF16 = this.pK;
            float f23 = pointF16.x;
            float f24 = pointF16.y;
            PointF pointF17 = this.pL;
            path7.cubicTo(f21, f22, f23, f24, pointF17.x, pointF17.y);
            this.mPath.quadTo(f4, f7, f4, f7 - f2);
            this.mPath.lineTo(pointF5.x, pointF5.y);
            this.mPath.quadTo(f4, f6, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void drawBottomRightArrow(float f2, float f3, float f4, float f5, float f6, float f7, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6) {
        float f8 = f5 - f2;
        this.pA.set(f8, f7);
        this.pB.set(this.pA.x - (2.2988f * f3), f7);
        float f9 = (0.8772f * f3) + f7;
        this.pC.set(this.pA.x - (4.5169f * f3), f9);
        float f10 = (2.4636f * f3) + f7;
        this.pD.set(this.pA.x - (6.2295f * f3), f10);
        float f11 = (8.5073f * f3) + f7;
        this.pE.set(this.pA.x - (12.7541f * f3), f11);
        float f12 = f7 + (9.1642f * f3);
        this.pF.set(this.pA.x - (13.4633f * f3), f12);
        this.pG.set(this.pA.x - (14.5367f * f3), f12);
        this.pH.set(this.pA.x - (15.2459f * f3), f11);
        this.pI.set(this.pA.x - (21.7705f * f3), f10);
        this.pJ.set(this.pA.x - (23.4831f * f3), f9);
        this.pK.set(this.pA.x - (25.7012f * f3), f7);
        this.pL.set(this.pA.x - (28.0f * f3), f7);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            this.mPath.lineTo(f8, f6);
            this.mPath.quadTo(f5, f6, pointF2.x, pointF2.y);
            this.mPath.lineTo(pointF3.x, f7 - f2);
            Path path2 = this.mPath;
            PointF pointF7 = this.pA;
            path2.quadTo(f5, f7, pointF7.x, pointF7.y);
            Path path3 = this.mPath;
            PointF pointF8 = this.pB;
            float f13 = pointF8.x;
            float f14 = pointF8.y;
            PointF pointF9 = this.pC;
            float f15 = pointF9.x;
            float f16 = pointF9.y;
            PointF pointF10 = this.pD;
            path3.cubicTo(f13, f14, f15, f16, pointF10.x, pointF10.y);
            Path path4 = this.mPath;
            PointF pointF11 = this.pE;
            path4.lineTo(pointF11.x, pointF11.y);
            Path path5 = this.mPath;
            PointF pointF12 = this.pF;
            float f17 = pointF12.x;
            float f18 = pointF12.y;
            PointF pointF13 = this.pG;
            float f19 = pointF13.x;
            float f20 = pointF13.y;
            PointF pointF14 = this.pH;
            path5.cubicTo(f17, f18, f19, f20, pointF14.x, pointF14.y);
            Path path6 = this.mPath;
            PointF pointF15 = this.pI;
            path6.lineTo(pointF15.x, pointF15.y);
            Path path7 = this.mPath;
            PointF pointF16 = this.pJ;
            float f21 = pointF16.x;
            float f22 = pointF16.y;
            PointF pointF17 = this.pK;
            float f23 = pointF17.x;
            float f24 = pointF17.y;
            PointF pointF18 = this.pL;
            path7.cubicTo(f21, f22, f23, f24, pointF18.x, pointF18.y);
            this.mPath.lineTo(pointF4.x, pointF4.y);
            this.mPath.quadTo(f4, f7, pointF5.x, pointF5.y);
            this.mPath.lineTo(pointF6.x, pointF6.y);
            this.mPath.quadTo(f4, f6, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void drawLeftArrow(float f2, float f3, float f4, float f5, float f6, float f7, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7) {
        this.pA.set(f4, ((f7 - f6) / 2.0f) + f6);
        PointF pointF8 = this.pB;
        PointF pointF9 = this.pA;
        float f8 = 8.0f * f3;
        pointF8.set(pointF9.x, pointF9.y + f8);
        PointF pointF10 = this.pC;
        PointF pointF11 = this.pA;
        float f9 = 1.7716f * f3;
        pointF10.set(pointF11.x - (7.1326f * f3), pointF11.y + f9);
        PointF pointF12 = this.pD;
        PointF pointF13 = this.pA;
        float f10 = 8.2892f * f3;
        float f11 = 0.7613f * f3;
        pointF12.set(pointF13.x - f10, pointF13.y + f11);
        PointF pointF14 = this.pE;
        PointF pointF15 = this.pA;
        pointF14.set(pointF15.x - f10, pointF15.y - f11);
        PointF pointF16 = this.pF;
        PointF pointF17 = this.pA;
        pointF16.set(pointF17.x - (7.1323f * f3), pointF17.y - f9);
        PointF pointF18 = this.pG;
        PointF pointF19 = this.pA;
        pointF18.set(pointF19.x, pointF19.y - f8);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            this.mPath.lineTo(f5 - f2, f6);
            this.mPath.quadTo(f5, f6, pointF2.x, pointF2.y);
            this.mPath.lineTo(pointF3.x, pointF3.y);
            this.mPath.quadTo(f5, f7, pointF4.x, pointF4.y);
            this.mPath.lineTo(pointF5.x, pointF5.y);
            this.mPath.quadTo(f4, f7, pointF6.x, pointF6.y);
            Path path2 = this.mPath;
            PointF pointF20 = this.pB;
            path2.lineTo(pointF20.x, pointF20.y);
            Path path3 = this.mPath;
            PointF pointF21 = this.pC;
            path3.lineTo(pointF21.x, pointF21.y);
            Path path4 = this.mPath;
            PointF pointF22 = this.pD;
            float f12 = pointF22.x;
            float f13 = pointF22.y;
            PointF pointF23 = this.pE;
            float f14 = pointF23.x;
            float f15 = pointF23.y;
            PointF pointF24 = this.pF;
            path4.cubicTo(f12, f13, f14, f15, pointF24.x, pointF24.y);
            Path path5 = this.mPath;
            PointF pointF25 = this.pG;
            path5.lineTo(pointF25.x, pointF25.y);
            this.mPath.lineTo(pointF7.x, pointF7.y);
            this.mPath.quadTo(f4, f6, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void drawRightArrow(float f2, float f3, float f4, float f5, float f6, float f7, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7) {
        this.pA.set(f5, ((f7 - f6) / 2.0f) + f6);
        PointF pointF8 = this.pB;
        PointF pointF9 = this.pA;
        float f8 = 8.0f * f3;
        pointF8.set(pointF9.x, pointF9.y - f8);
        PointF pointF10 = this.pC;
        PointF pointF11 = this.pA;
        float f9 = 1.7716f * f3;
        pointF10.set(pointF11.x + (7.1323f * f3), pointF11.y - f9);
        PointF pointF12 = this.pD;
        PointF pointF13 = this.pA;
        float f10 = 8.2892f * f3;
        float f11 = 0.7613f * f3;
        pointF12.set(pointF13.x + f10, pointF13.y - f11);
        PointF pointF14 = this.pE;
        PointF pointF15 = this.pA;
        pointF14.set(pointF15.x + f10, pointF15.y + f11);
        PointF pointF16 = this.pF;
        PointF pointF17 = this.pA;
        pointF16.set(pointF17.x + (7.1326f * f3), pointF17.y + f9);
        PointF pointF18 = this.pG;
        PointF pointF19 = this.pA;
        pointF18.set(pointF19.x, pointF19.y + f8);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            this.mPath.lineTo(f5 - f2, f6);
            this.mPath.quadTo(f5, f6, pointF2.x, pointF2.y);
            Path path2 = this.mPath;
            PointF pointF20 = this.pB;
            path2.lineTo(pointF20.x, pointF20.y);
            Path path3 = this.mPath;
            PointF pointF21 = this.pC;
            path3.lineTo(pointF21.x, pointF21.y);
            Path path4 = this.mPath;
            PointF pointF22 = this.pD;
            float f12 = pointF22.x;
            float f13 = pointF22.y;
            PointF pointF23 = this.pE;
            float f14 = pointF23.x;
            float f15 = pointF23.y;
            PointF pointF24 = this.pF;
            path4.cubicTo(f12, f13, f14, f15, pointF24.x, pointF24.y);
            Path path5 = this.mPath;
            PointF pointF25 = this.pG;
            path5.lineTo(pointF25.x, pointF25.y);
            this.mPath.lineTo(pointF3.x, pointF3.y);
            this.mPath.quadTo(f5, f7, pointF4.x, pointF4.y);
            this.mPath.lineTo(pointF5.x, pointF5.y);
            this.mPath.quadTo(f4, f7, pointF6.x, pointF6.y);
            this.mPath.lineTo(pointF7.x, pointF7.y);
            this.mPath.quadTo(f4, f6, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void drawTopArrow(float f2, float f3, float f4, float f5, float f6, float f7, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7, PointF pointF8) {
        this.middle.set(f2 / 2.0f, f6);
        PointF pointF9 = this.pA;
        PointF pointF10 = this.middle;
        pointF9.set((pointF10.x - (14.0f * f3)) + this.mArrowHorizonOffset, pointF10.y);
        this.pB.set(this.pA.x + (2.2988f * f3), f6);
        float f8 = f6 - (0.8772f * f3);
        this.pC.set(this.pA.x + (4.5169f * f3), f8);
        float f9 = f6 - (2.4636f * f3);
        this.pD.set(this.pA.x + (6.2295f * f3), f9);
        float f10 = f6 - (8.5073f * f3);
        this.pF.set(this.pA.x + (12.7541f * f3), f10);
        float f11 = f6 - (9.1642f * f3);
        this.pG.set(this.pA.x + (13.4633f * f3), f11);
        this.pH.set(this.pA.x + (14.5367f * f3), f11);
        this.pI.set(this.pA.x + (15.2459f * f3), f10);
        this.pJ.set(this.pA.x + (21.7705f * f3), f9);
        this.pK.set(this.pA.x + (23.4831f * f3), f8);
        this.pL.set(this.pA.x + (25.7012f * f3), f6);
        this.pM.set(this.pA.x + (28.0f * f3), f6);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            Path path2 = this.mPath;
            PointF pointF11 = this.pA;
            path2.lineTo(pointF11.x, pointF11.y);
            Path path3 = this.mPath;
            PointF pointF12 = this.pB;
            float f12 = pointF12.x;
            float f13 = pointF12.y;
            PointF pointF13 = this.pC;
            float f14 = pointF13.x;
            float f15 = pointF13.y;
            PointF pointF14 = this.pD;
            path3.cubicTo(f12, f13, f14, f15, pointF14.x, pointF14.y);
            Path path4 = this.mPath;
            PointF pointF15 = this.pF;
            path4.lineTo(pointF15.x, pointF15.y);
            Path path5 = this.mPath;
            PointF pointF16 = this.pG;
            float f16 = pointF16.x;
            float f17 = pointF16.y;
            PointF pointF17 = this.pH;
            float f18 = pointF17.x;
            float f19 = pointF17.y;
            PointF pointF18 = this.pI;
            path5.cubicTo(f16, f17, f18, f19, pointF18.x, pointF18.y);
            Path path6 = this.mPath;
            PointF pointF19 = this.pJ;
            path6.lineTo(pointF19.x, pointF19.y);
            Path path7 = this.mPath;
            PointF pointF20 = this.pK;
            float f20 = pointF20.x;
            float f21 = pointF20.y;
            PointF pointF21 = this.pL;
            float f22 = pointF21.x;
            float f23 = pointF21.y;
            PointF pointF22 = this.pM;
            path7.cubicTo(f20, f21, f22, f23, pointF22.x, pointF22.y);
            this.mPath.lineTo(pointF8.x, pointF8.y);
            this.mPath.quadTo(f5, f6, pointF2.x, pointF2.y);
            this.mPath.lineTo(pointF3.x, pointF3.y);
            this.mPath.quadTo(f5, f7, pointF4.x, pointF4.y);
            this.mPath.lineTo(pointF5.x, pointF5.y);
            this.mPath.quadTo(f4, f7, pointF6.x, pointF6.y);
            this.mPath.lineTo(pointF7.x, pointF7.y);
            this.mPath.quadTo(f4, f6, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void drawTopLeftArrow(float f2, float f3, float f4, float f5, float f6, float f7, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6, PointF pointF7) {
        this.pA.set(f4 + f2, f6);
        this.pB.set(this.pA.x + (2.2988f * f3), f6);
        float f8 = f6 - (0.8772f * f3);
        this.pC.set(this.pA.x + (4.5169f * f3), f8);
        float f9 = f6 - (2.4636f * f3);
        this.pD.set(this.pA.x + (6.2295f * f3), f9);
        float f10 = f6 - (8.5073f * f3);
        this.pF.set(this.pA.x + (12.7541f * f3), f10);
        float f11 = f6 - (9.1642f * f3);
        this.pG.set(this.pA.x + (13.4633f * f3), f11);
        this.pH.set(this.pA.x + (14.5367f * f3), f11);
        this.pI.set(this.pA.x + (15.2459f * f3), f10);
        this.pJ.set(this.pA.x + (21.7705f * f3), f9);
        this.pK.set(this.pA.x + (23.4831f * f3), f8);
        this.pL.set(this.pA.x + (25.7012f * f3), f6);
        this.pM.set(this.pA.x + (28.0f * f3), f6);
        pointF6.set(f4, f6 + f2);
        Path path = this.mPath;
        if (path != null) {
            PointF pointF8 = this.pA;
            path.moveTo(pointF8.x, pointF8.y);
            Path path2 = this.mPath;
            PointF pointF9 = this.pB;
            float f12 = pointF9.x;
            float f13 = pointF9.y;
            PointF pointF10 = this.pC;
            float f14 = pointF10.x;
            float f15 = pointF10.y;
            PointF pointF11 = this.pD;
            path2.cubicTo(f12, f13, f14, f15, pointF11.x, pointF11.y);
            Path path3 = this.mPath;
            PointF pointF12 = this.pF;
            path3.lineTo(pointF12.x, pointF12.y);
            Path path4 = this.mPath;
            PointF pointF13 = this.pG;
            float f16 = pointF13.x;
            float f17 = pointF13.y;
            PointF pointF14 = this.pH;
            float f18 = pointF14.x;
            float f19 = pointF14.y;
            PointF pointF15 = this.pI;
            path4.cubicTo(f16, f17, f18, f19, pointF15.x, pointF15.y);
            Path path5 = this.mPath;
            PointF pointF16 = this.pJ;
            path5.lineTo(pointF16.x, pointF16.y);
            Path path6 = this.mPath;
            PointF pointF17 = this.pK;
            float f20 = pointF17.x;
            float f21 = pointF17.y;
            PointF pointF18 = this.pL;
            float f22 = pointF18.x;
            float f23 = pointF18.y;
            PointF pointF19 = this.pM;
            path6.cubicTo(f20, f21, f22, f23, pointF19.x, pointF19.y);
            this.mPath.lineTo(pointF7.x, pointF7.y);
            this.mPath.quadTo(f5, f6, pointF.x, pointF.y);
            this.mPath.lineTo(pointF2.x, pointF2.y);
            this.mPath.quadTo(f5, f7, pointF3.x, pointF3.y);
            this.mPath.lineTo(pointF4.x, pointF4.y);
            this.mPath.quadTo(f4, f7, pointF5.x, pointF5.y);
            this.mPath.lineTo(pointF6.x, pointF6.y);
            Path path7 = this.mPath;
            PointF pointF20 = this.pA;
            path7.quadTo(f4, f6, pointF20.x, pointF20.y);
            this.mPath.close();
        }
    }

    private void drawTopRightArrow(float f2, float f3, float f4, float f5, float f6, PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5, PointF pointF6) {
        float f7 = 28.0f * f2;
        this.pA.set((f4 - this.radius) - f7, f5);
        this.pB.set(this.pA.x + (2.2988f * f2), f5);
        float f8 = f5 - (0.8772f * f2);
        this.pC.set(this.pA.x + (4.5169f * f2), f8);
        float f9 = f5 - (2.4636f * f2);
        this.pD.set(this.pA.x + (6.2295f * f2), f9);
        float f10 = f5 - (8.5073f * f2);
        this.pF.set(this.pA.x + (12.7541f * f2), f10);
        float f11 = f5 - (9.1642f * f2);
        this.pG.set(this.pA.x + (13.4633f * f2), f11);
        this.pH.set(this.pA.x + (14.5367f * f2), f11);
        this.pI.set(this.pA.x + (15.2459f * f2), f10);
        this.pJ.set(this.pA.x + (21.7705f * f2), f9);
        this.pK.set(this.pA.x + (23.4831f * f2), f8);
        this.pL.set(this.pA.x + (25.7012f * f2), f5);
        this.pM.set(this.pA.x + f7, f5);
        Path path = this.mPath;
        if (path != null) {
            path.moveTo(pointF.x, pointF.y);
            Path path2 = this.mPath;
            PointF pointF7 = this.pA;
            path2.lineTo(pointF7.x, pointF7.y);
            Path path3 = this.mPath;
            PointF pointF8 = this.pB;
            float f12 = pointF8.x;
            float f13 = pointF8.y;
            PointF pointF9 = this.pC;
            float f14 = pointF9.x;
            float f15 = pointF9.y;
            PointF pointF10 = this.pD;
            path3.cubicTo(f12, f13, f14, f15, pointF10.x, pointF10.y);
            Path path4 = this.mPath;
            PointF pointF11 = this.pF;
            path4.lineTo(pointF11.x, pointF11.y);
            Path path5 = this.mPath;
            PointF pointF12 = this.pG;
            float f16 = pointF12.x;
            float f17 = pointF12.y;
            PointF pointF13 = this.pH;
            float f18 = pointF13.x;
            float f19 = pointF13.y;
            PointF pointF14 = this.pI;
            path5.cubicTo(f16, f17, f18, f19, pointF14.x, pointF14.y);
            Path path6 = this.mPath;
            PointF pointF15 = this.pJ;
            path6.lineTo(pointF15.x, pointF15.y);
            Path path7 = this.mPath;
            PointF pointF16 = this.pK;
            float f20 = pointF16.x;
            float f21 = pointF16.y;
            PointF pointF17 = this.pL;
            float f22 = pointF17.x;
            float f23 = pointF17.y;
            PointF pointF18 = this.pM;
            path7.cubicTo(f20, f21, f22, f23, pointF18.x, pointF18.y);
            this.mPath.quadTo(f4, f5, f4, this.radius + f5);
            this.mPath.lineTo(pointF2.x, pointF2.y);
            this.mPath.quadTo(f4, f6, pointF3.x, pointF3.y);
            this.mPath.lineTo(pointF4.x, pointF4.y);
            this.mPath.quadTo(f3, f6, pointF5.x, pointF5.y);
            this.mPath.lineTo(pointF6.x, pointF6.y);
            this.mPath.quadTo(f3, f5, pointF.x, pointF.y);
            this.mPath.close();
        }
    }

    private void init() {
        Paint paint = new Paint();
        this.mBackgroundPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
        setWillNotDraw(false);
        this.mBackgroundPaint.setStyle(Paint.Style.FILL);
        this.mBackgroundPaint.setAntiAlias(true);
        this.mPath = new Path();
        this.f6138p0 = new PointF();
        this.f6139p1 = new PointF();
        this.p2 = new PointF();
        this.p3 = new PointF();
        this.p4 = new PointF();
        this.p5 = new PointF();
        this.p6 = new PointF();
        this.p7 = new PointF();
        this.pA = new PointF();
        this.pB = new PointF();
        this.pC = new PointF();
        this.pD = new PointF();
        this.pE = new PointF();
        this.pF = new PointF();
        this.pG = new PointF();
        this.pH = new PointF();
        this.pI = new PointF();
        this.pJ = new PointF();
        this.pK = new PointF();
        this.pL = new PointF();
        this.pM = new PointF();
        this.middle = new PointF();
        this.paddingStart = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_arrow_popup_view_paddingStart);
        this.paddingEnd = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_arrow_popup_view_paddingEnd);
        this.paddingTop = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_arrow_popup_view_paddingTop);
        this.paddingBottom = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_arrow_popup_view_paddingBottom);
        this.radius = getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_arrow_popup_view_round_corners);
        this.density = getResources().getDisplayMetrics().density;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        super.dispatchDraw(canvas);
        canvas.drawBitmap(this.mMask1, getPaddingLeft(), getPaddingTop(), this.mPaint);
        canvas.drawBitmap(this.mMask2, (getWidth() - this.mMask2.getWidth()) - getPaddingRight(), getPaddingTop(), this.mPaint);
        canvas.drawBitmap(this.mMask3, getPaddingLeft(), (getHeight() - this.mMask3.getHeight()) - getPaddingBottom(), this.mPaint);
        canvas.drawBitmap(this.mMask4, (getWidth() - this.mMask4.getWidth()) - getPaddingRight(), (getHeight() - this.mMask4.getHeight()) - getPaddingBottom(), this.mPaint);
        canvas.restore();
    }

    @Override // android.widget.LinearLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        Path path;
        super.onDraw(canvas);
        this.mIsRtl = ViewCompat.getLayoutDirection(this) == 1;
        Path path2 = this.mPath;
        if (path2 != null) {
            path2.reset();
        }
        float width = getWidth();
        float height = getHeight();
        float f2 = this.paddingTop;
        float f3 = height - this.paddingBottom;
        float f4 = this.paddingStart;
        float f5 = width - this.paddingEnd;
        int i2 = this.mArrowMode;
        if (i2 == 8 || i2 == 16) {
            float f6 = this.mArrowHorizonOffset;
            float f7 = this.radius;
            float f8 = this.density;
            float f9 = width / 2.0f;
            if (f6 < ((f4 + f7) + (f8 * 14.0f)) - f9) {
                this.mArrowHorizonOffset = ((f7 + f4) + (f8 * 14.0f)) - f9;
            } else if (f6 > ((f5 - f7) - (f8 * 14.0f)) - f9) {
                this.mArrowHorizonOffset = ((f5 - f7) - (f8 * 14.0f)) - f9;
            }
        }
        this.f6138p0.set(this.radius + f4, f2);
        this.f6139p1.set(f5, this.radius + f2);
        this.p2.set(f5, f3 - this.radius);
        this.p3.set(f5 - this.radius, f3);
        this.p4.set(this.radius + f4, f3);
        this.p5.set(f4, f3 - this.radius);
        this.p6.set(f4, this.radius + f2);
        this.p7.set(f5 - this.radius, f2);
        boolean z2 = this.mIsRtl;
        if ((z2 || this.mArrowMode != 10) && !(z2 && this.mArrowMode == 9)) {
            int i3 = this.mArrowMode;
            if (i3 == 8) {
                drawTopArrow(width, this.density, f4, f5, f2, f3, this.f6138p0, this.f6139p1, this.p2, this.p3, this.p4, this.p5, this.p6, this.p7);
            } else if ((!z2 && i3 == 9) || (z2 && i3 == 10)) {
                drawTopLeftArrow(this.radius, this.density, f4, f5, f2, f3, this.f6139p1, this.p2, this.p3, this.p4, this.p5, this.p6, this.p7);
            } else if ((!z2 && i3 == 32 && this.mRtlMode != 1) || ((z2 && i3 == 64 && this.mRtlMode != 0) || ((z2 && i3 == 32 && this.mRtlMode == 0) || (!z2 && i3 == 64 && this.mRtlMode == 1)))) {
                drawLeftArrow(this.radius, this.density, f4, f5, f2, f3, this.f6138p0, this.f6139p1, this.p2, this.p3, this.p4, this.p5, this.p6);
            } else if ((!z2 && i3 == 64) || ((z2 && i3 == 32 && this.mRtlMode != 1) || ((z2 && i3 == 64) || (!z2 && i3 == 32)))) {
                drawRightArrow(this.radius, this.density, f4, f5, f2, f3, this.f6138p0, this.f6139p1, this.p2, this.p3, this.p4, this.p5, this.p6);
            } else if ((!z2 && i3 == 17) || (z2 && i3 == 18)) {
                drawBottomRightArrow(this.radius, this.density, f4, f5, f2, f3, this.f6138p0, this.f6139p1, this.p2, this.p4, this.p5, this.p6);
            } else if (i3 == 16) {
                drawBottomArrow(width, this.radius, this.density, f4, f5, f2, f3, this.f6138p0, this.f6139p1, this.p2, this.p3, this.p4, this.p5, this.p6);
            } else if ((!z2 && i3 == 18) || (z2 && i3 == 17)) {
                drawBottomLeftArrow(this.radius, this.density, f4, f5, f2, f3, this.f6138p0, this.f6139p1, this.p2, this.p3, this.p6);
            }
        } else {
            drawTopRightArrow(this.density, f4, f5, f2, f3, this.f6138p0, this.p2, this.p3, this.p4, this.p5, this.p6);
        }
        Paint paint = this.mBackgroundPaint;
        if (paint == null || (path = this.mPath) == null) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    public void setArrowBackgroundPaintColor(int i2) {
        Paint paint = this.mBackgroundPaint;
        if (paint != null) {
            paint.setColor(i2);
        }
    }

    public void setArrowHorizonOffset(float f2) {
        this.mArrowHorizonOffset = f2;
    }

    public void setArrowMode(int i2) {
        this.mArrowMode = i2;
    }

    public void setRtlMode(int i2) {
        this.mRtlMode = i2;
    }

    public ArrowPopupContentWrapper(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArrowPopupContentWrapper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mArrowMode = 0;
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mIsRtl = false;
        this.mRtlMode = 2;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.mPaint.setAntiAlias(true);
        Resources resources = getResources();
        this.mMask1 = BitmapFactory.decodeResource(resources, R.drawable.miuix_appcompat_popup_mask_1);
        this.mMask2 = BitmapFactory.decodeResource(resources, R.drawable.miuix_appcompat_popup_mask_2);
        this.mMask3 = BitmapFactory.decodeResource(resources, R.drawable.miuix_appcompat_popup_mask_3);
        this.mMask4 = BitmapFactory.decodeResource(resources, R.drawable.miuix_appcompat_popup_mask_4);
        init();
    }
}
