package miuix.smooth;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SmoothPathProvider2 {
    private static final float DEFAULT_KSI = 0.46f;
    private static final float DEFAULT_SMOOTH = 0.8f;
    private float mSmooth = DEFAULT_SMOOTH;
    private float mKsi = DEFAULT_KSI;

    public static class CornerData {
        public static final int BOTTOM_LEFT = 3;
        public static final int BOTTOM_RIGHT = 2;
        public static final int TOP_LEFT = 0;
        public static final int TOP_RIGHT = 1;
        public PointF[] bezierAnchorHorizontal = new PointF[4];
        public PointF[] bezierAnchorVertical = new PointF[4];
        public float radius;
        public RectF rect;
        public double smoothForHorizontal;
        public double smoothForVertical;
        public float swapAngle;
        public double thetaForHorizontal;
        public double thetaForVertical;

        public void build(float f2, RectF rectF, float f3, float f4, double d2, float f5, int i2) {
            this.radius = f2;
            float fWidth = rectF.width();
            float fHeight = rectF.height();
            float f6 = rectF.left;
            float f7 = rectF.top;
            float f8 = rectF.right;
            float f9 = rectF.bottom;
            this.smoothForHorizontal = SmoothPathProvider2.smoothForWidth(fWidth, this.radius, d2, f5);
            this.smoothForVertical = SmoothPathProvider2.smoothForHeight(fHeight, this.radius, d2, f5);
            this.thetaForHorizontal = SmoothPathProvider2.thetaForWidth(this.smoothForHorizontal);
            double dThetaForHeight = SmoothPathProvider2.thetaForHeight(this.smoothForVertical);
            this.thetaForVertical = dThetaForHeight;
            this.swapAngle = (float) SmoothPathProvider2.radToAngle((1.5707963267948966d - dThetaForHeight) - this.thetaForHorizontal);
            double d3 = f5;
            double dKForWidth = SmoothPathProvider2.kForWidth(this.smoothForHorizontal * d3, this.thetaForHorizontal);
            double dMForWidth = SmoothPathProvider2.mForWidth(this.radius, this.thetaForHorizontal);
            double dNForWidth = SmoothPathProvider2.nForWidth(this.radius, this.thetaForHorizontal);
            double dPForWidth = SmoothPathProvider2.pForWidth(this.radius, this.thetaForHorizontal);
            double dXForWidth = SmoothPathProvider2.xForWidth(this.radius, this.thetaForHorizontal);
            double dYForWidth = SmoothPathProvider2.yForWidth(dKForWidth, dXForWidth);
            double dKForHeight = SmoothPathProvider2.kForHeight(this.smoothForVertical * d3, this.thetaForVertical);
            double dMForHeight = SmoothPathProvider2.mForHeight(this.radius, this.thetaForVertical);
            double dNForHeight = SmoothPathProvider2.nForHeight(this.radius, this.thetaForVertical);
            double dPForHeight = SmoothPathProvider2.pForHeight(this.radius, this.thetaForVertical);
            double dXForHeight = SmoothPathProvider2.xForHeight(this.radius, this.thetaForVertical);
            double dYForHeight = SmoothPathProvider2.yForHeight(dKForHeight, dXForHeight);
            if (i2 == 0) {
                float f10 = f6 + f3;
                float f11 = f7 + f4;
                float f12 = this.radius;
                this.rect = new RectF(f10, f11, (f12 * 2.0f) + f10, (f12 * 2.0f) + f11);
                double d4 = f10;
                double d5 = f11;
                this.bezierAnchorHorizontal[0] = new PointF((float) (dMForWidth + d4), (float) (dNForWidth + d5));
                this.bezierAnchorHorizontal[1] = new PointF((float) (dPForWidth + d4), f11);
                double d6 = dPForWidth + dXForWidth;
                this.bezierAnchorHorizontal[2] = new PointF((float) (d6 + d4), f11);
                this.bezierAnchorHorizontal[3] = new PointF((float) (d6 + dYForWidth + d4), f11);
                double d7 = dXForHeight + dPForHeight;
                this.bezierAnchorVertical[0] = new PointF(f10, (float) (d7 + dYForHeight + d5));
                this.bezierAnchorVertical[1] = new PointF(f10, (float) (d7 + d5));
                this.bezierAnchorVertical[2] = new PointF(f10, (float) (dPForHeight + d5));
                this.bezierAnchorVertical[3] = new PointF((float) (dMForHeight + d4), (float) (dNForHeight + d5));
                return;
            }
            if (i2 == 1) {
                float f13 = f7 + f4;
                float f14 = this.radius;
                float f15 = f8 - f3;
                this.rect = new RectF((f8 - (f14 * 2.0f)) - f3, f13, f15, (f14 * 2.0f) + f13);
                double d8 = f8;
                double d9 = d8 - dPForWidth;
                double d10 = d9 - dXForWidth;
                double d11 = f3;
                this.bezierAnchorHorizontal[0] = new PointF((float) ((d10 - dYForWidth) - d11), f13);
                this.bezierAnchorHorizontal[1] = new PointF((float) (d10 - d11), f13);
                this.bezierAnchorHorizontal[2] = new PointF((float) (d9 - d11), f13);
                double d12 = f13;
                this.bezierAnchorHorizontal[3] = new PointF((float) ((d8 - dMForWidth) - d11), (float) (dNForWidth + d12));
                this.bezierAnchorVertical[0] = new PointF((float) ((d8 - dMForHeight) - d11), (float) (dNForHeight + d12));
                this.bezierAnchorVertical[1] = new PointF(f15, (float) (dPForHeight + d12));
                double d13 = dPForHeight + dXForHeight;
                this.bezierAnchorVertical[2] = new PointF(f15, (float) (d13 + d12));
                this.bezierAnchorVertical[3] = new PointF(f15, (float) (d13 + dYForHeight + d12));
                return;
            }
            if (i2 == 2) {
                float f16 = this.radius;
                float f17 = f8 - f3;
                float f18 = f9 - f4;
                this.rect = new RectF((f8 - (f16 * 2.0f)) - f3, (f9 - (f16 * 2.0f)) - f4, f17, f18);
                double d14 = f8;
                double d15 = f3;
                double d16 = f9;
                double d17 = f4;
                this.bezierAnchorHorizontal[0] = new PointF((float) ((d14 - dMForWidth) - d15), (float) ((d16 - dNForWidth) - d17));
                double d18 = d14 - dPForWidth;
                this.bezierAnchorHorizontal[1] = new PointF((float) (d18 - d15), f18);
                double d19 = d18 - dXForWidth;
                this.bezierAnchorHorizontal[2] = new PointF((float) (d19 - d15), f18);
                this.bezierAnchorHorizontal[3] = new PointF((float) ((d19 - dYForWidth) - d15), f18);
                double d20 = d16 - dPForHeight;
                double d21 = d20 - dXForHeight;
                this.bezierAnchorVertical[0] = new PointF(f17, (float) ((d21 - dYForHeight) - d17));
                this.bezierAnchorVertical[1] = new PointF(f17, (float) (d21 - d17));
                this.bezierAnchorVertical[2] = new PointF(f17, (float) (d20 - d17));
                this.bezierAnchorVertical[3] = new PointF((float) ((d14 - dMForHeight) - d15), (float) ((d16 - dNForHeight) - d17));
                return;
            }
            if (i2 == 3) {
                float f19 = f6 + f3;
                float f20 = this.radius;
                float f21 = f9 - f4;
                this.rect = new RectF(f19, (f9 - (f20 * 2.0f)) - f4, (f20 * 2.0f) + f19, f21);
                double d22 = dPForWidth + dXForWidth;
                double d23 = f19;
                this.bezierAnchorHorizontal[0] = new PointF((float) (d22 + dYForWidth + d23), f21);
                this.bezierAnchorHorizontal[1] = new PointF((float) (d22 + d23), f21);
                this.bezierAnchorHorizontal[2] = new PointF((float) (dPForWidth + d23), f21);
                float f22 = (float) (dMForWidth + d23);
                double d24 = f9;
                double d25 = f4;
                this.bezierAnchorHorizontal[3] = new PointF(f22, (float) ((d24 - dNForWidth) - d25));
                this.bezierAnchorVertical[0] = new PointF((float) (dMForHeight + d23), (float) ((d24 - dNForHeight) - d25));
                double d26 = d24 - dPForHeight;
                this.bezierAnchorVertical[1] = new PointF(f19, (float) (d26 - d25));
                double d27 = d26 - dXForHeight;
                this.bezierAnchorVertical[2] = new PointF(f19, (float) (d27 - d25));
                this.bezierAnchorVertical[3] = new PointF(f19, (float) ((d27 - dYForHeight) - d25));
            }
        }
    }

    public static class SmoothData {
        public float height;
        public float ksi;
        public double smooth;
        public float width;
        public CornerData topLeft = null;
        public CornerData topRight = null;
        public CornerData bottomRight = null;
        public CornerData bottomLeft = null;

        public SmoothData(float f2, float f3, double d2, float f4) {
            this.width = f2;
            this.height = f3;
            this.smooth = d2;
            this.ksi = f4;
        }
    }

    private void ensureFourCornerData(@NonNull SmoothData smoothData) {
        if (smoothData.topLeft == null) {
            smoothData.topLeft = new CornerData();
        }
        if (smoothData.topRight == null) {
            smoothData.topRight = new CornerData();
        }
        if (smoothData.bottomRight == null) {
            smoothData.bottomRight = new CornerData();
        }
        if (smoothData.bottomLeft == null) {
            smoothData.bottomLeft = new CornerData();
        }
    }

    private boolean isFourCornerDataValid(@NonNull SmoothData smoothData) {
        return smoothData.topLeft == null || smoothData.topRight == null || smoothData.bottomRight == null || smoothData.bottomLeft == null;
    }

    private static boolean isHeightCollapsed(float f2, float f3, float f4, double d2, float f5) {
        return ((double) f2) <= ((double) (f3 + f4)) * ((d2 * ((double) f5)) + 1.0d);
    }

    private static boolean isWidthCollapsed(float f2, float f3, float f4, double d2, float f5) {
        return ((double) f2) <= ((double) (f3 + f4)) * ((d2 * ((double) f5)) + 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double kForHeight(double d2, double d3) {
        if (d3 == 0.0d) {
            return 0.0d;
        }
        double d4 = d3 / 2.0d;
        return (((((d2 * 0.46000000834465027d) + Math.tan(d4)) * 2.0d) * (Math.cos(d3) + 1.0d)) / (Math.tan(d4) * 3.0d)) - 1.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double kForWidth(double d2, double d3) {
        if (d3 == 0.0d) {
            return 0.0d;
        }
        double d4 = d3 / 2.0d;
        return (((((d2 * 0.46000000834465027d) + Math.tan(d4)) * 2.0d) * (Math.cos(d3) + 1.0d)) / (Math.tan(d4) * 3.0d)) - 1.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double mForHeight(float f2, double d2) {
        return ((double) f2) * (1.0d - Math.cos(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double mForWidth(float f2, double d2) {
        return ((double) f2) * (1.0d - Math.sin(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double nForHeight(float f2, double d2) {
        return ((double) f2) * (1.0d - Math.sin(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double nForWidth(float f2, double d2) {
        return ((double) f2) * (1.0d - Math.cos(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double pForHeight(float f2, double d2) {
        return ((double) f2) * (1.0d - Math.tan(d2 / 2.0d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double pForWidth(float f2, double d2) {
        return ((double) f2) * (1.0d - Math.tan(d2 / 2.0d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double radToAngle(double d2) {
        return (d2 * 180.0d) / 3.141592653589793d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double smoothForHeight(float f2, float f3, double d2, float f4) {
        return isHeightCollapsed(f2, f3, f3, d2, f4) ? Math.max(Math.min(((f2 / (f3 * 2.0f)) - 1.0f) / f4, 1.0f), 0.0f) : d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double smoothForWidth(float f2, float f3, double d2, float f4) {
        return isWidthCollapsed(f2, f3, f3, d2, f4) ? Math.max(Math.min(((f2 / (f3 * 2.0f)) - 1.0f) / f4, 1.0f), 0.0f) : d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double thetaForHeight(double d2) {
        return (d2 * 3.141592653589793d) / 4.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double thetaForWidth(double d2) {
        return (d2 * 3.141592653589793d) / 4.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double xForHeight(float f2, double d2) {
        return ((((double) f2) * 1.5d) * Math.tan(d2 / 2.0d)) / (Math.cos(d2) + 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double xForWidth(float f2, double d2) {
        return ((((double) f2) * 1.5d) * Math.tan(d2 / 2.0d)) / (Math.cos(d2) + 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double yForHeight(double d2, double d3) {
        return d2 * d3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double yForWidth(double d2, double d3) {
        return d2 * d3;
    }

    @Nullable
    public SmoothData buildSmoothData(RectF rectF, float f2) {
        return buildSmoothData(rectF, f2, 0.0f, 0.0f);
    }

    public void drawPath(Canvas canvas, Paint paint, @Nullable SmoothData smoothData, int i2, int i3, int i4) {
        if (smoothData == null) {
            return;
        }
        if (isFourCornerDataValid(smoothData)) {
            paint.setColor(i2);
            canvas.drawRect(new RectF(0.0f, 0.0f, smoothData.width, smoothData.height), paint);
            return;
        }
        PointF pointF = new PointF();
        paint.setColor(i3);
        CornerData cornerData = smoothData.topLeft;
        canvas.drawArc(cornerData.rect, (float) radToAngle(cornerData.thetaForVertical + 3.141592653589793d), smoothData.topLeft.swapAngle, false, paint);
        CornerData cornerData2 = smoothData.topLeft;
        PointF pointF2 = cornerData2.bezierAnchorHorizontal[0];
        pointF.x = pointF2.x;
        pointF.y = pointF2.y;
        if (cornerData2.smoothForHorizontal != 0.0d) {
            Path path = new Path();
            path.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr = smoothData.topLeft.bezierAnchorHorizontal;
            PointF pointF3 = pointFArr[1];
            float f2 = pointF3.x;
            float f3 = pointF3.y;
            PointF pointF4 = pointFArr[2];
            float f4 = pointF4.x;
            float f5 = pointF4.y;
            PointF pointF5 = pointFArr[3];
            path.cubicTo(f2, f3, f4, f5, pointF5.x, pointF5.y);
            paint.setColor(i4);
            canvas.drawPath(path, paint);
            PointF pointF6 = smoothData.topLeft.bezierAnchorHorizontal[3];
            pointF.x = pointF6.x;
            pointF.y = pointF6.y;
        }
        if (!isWidthCollapsed(smoothData.width, smoothData.topLeft.radius, smoothData.topRight.radius, smoothData.smooth, smoothData.ksi)) {
            paint.setColor(i2);
            float f6 = pointF.x;
            float f7 = pointF.y;
            PointF pointF7 = smoothData.topRight.bezierAnchorHorizontal[0];
            canvas.drawLine(f6, f7, pointF7.x, pointF7.y, paint);
            PointF pointF8 = smoothData.topRight.bezierAnchorHorizontal[0];
            pointF.x = pointF8.x;
            pointF.y = pointF8.y;
        }
        if (smoothData.topRight.smoothForHorizontal != 0.0d) {
            Path path2 = new Path();
            path2.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr2 = smoothData.topRight.bezierAnchorHorizontal;
            PointF pointF9 = pointFArr2[1];
            float f8 = pointF9.x;
            float f9 = pointF9.y;
            PointF pointF10 = pointFArr2[2];
            float f10 = pointF10.x;
            float f11 = pointF10.y;
            PointF pointF11 = pointFArr2[3];
            path2.cubicTo(f8, f9, f10, f11, pointF11.x, pointF11.y);
            paint.setColor(i4);
            canvas.drawPath(path2, paint);
            PointF pointF12 = smoothData.topRight.bezierAnchorHorizontal[3];
            pointF.x = pointF12.x;
            pointF.y = pointF12.y;
        }
        paint.setColor(i3);
        CornerData cornerData3 = smoothData.topRight;
        canvas.drawArc(cornerData3.rect, (float) radToAngle(cornerData3.thetaForHorizontal + 4.71238898038469d), smoothData.topRight.swapAngle, false, paint);
        CornerData cornerData4 = smoothData.topRight;
        PointF pointF13 = cornerData4.bezierAnchorVertical[0];
        pointF.x = pointF13.x;
        pointF.y = pointF13.y;
        if (cornerData4.smoothForVertical != 0.0d) {
            Path path3 = new Path();
            path3.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr3 = smoothData.topRight.bezierAnchorVertical;
            PointF pointF14 = pointFArr3[1];
            float f12 = pointF14.x;
            float f13 = pointF14.y;
            PointF pointF15 = pointFArr3[2];
            float f14 = pointF15.x;
            float f15 = pointF15.y;
            PointF pointF16 = pointFArr3[3];
            path3.cubicTo(f12, f13, f14, f15, pointF16.x, pointF16.y);
            paint.setColor(i4);
            canvas.drawPath(path3, paint);
            PointF pointF17 = smoothData.topRight.bezierAnchorVertical[3];
            pointF.x = pointF17.x;
            pointF.y = pointF17.y;
        }
        if (!isHeightCollapsed(smoothData.height, smoothData.topRight.radius, smoothData.bottomRight.radius, smoothData.smooth, smoothData.ksi)) {
            paint.setColor(i2);
            float f16 = pointF.x;
            float f17 = pointF.y;
            PointF pointF18 = smoothData.bottomRight.bezierAnchorVertical[0];
            canvas.drawLine(f16, f17, pointF18.x, pointF18.y, paint);
            PointF pointF19 = smoothData.bottomRight.bezierAnchorVertical[0];
            pointF.x = pointF19.x;
            pointF.y = pointF19.y;
        }
        if (smoothData.bottomRight.smoothForVertical != 0.0d) {
            Path path4 = new Path();
            path4.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr4 = smoothData.bottomRight.bezierAnchorVertical;
            PointF pointF20 = pointFArr4[1];
            float f18 = pointF20.x;
            float f19 = pointF20.y;
            PointF pointF21 = pointFArr4[2];
            float f20 = pointF21.x;
            float f21 = pointF21.y;
            PointF pointF22 = pointFArr4[3];
            path4.cubicTo(f18, f19, f20, f21, pointF22.x, pointF22.y);
            paint.setColor(i4);
            canvas.drawPath(path4, paint);
            PointF pointF23 = smoothData.bottomRight.bezierAnchorVertical[3];
            pointF.x = pointF23.x;
            pointF.y = pointF23.y;
        }
        paint.setColor(i3);
        CornerData cornerData5 = smoothData.bottomRight;
        canvas.drawArc(cornerData5.rect, (float) radToAngle(cornerData5.thetaForVertical), smoothData.bottomRight.swapAngle, false, paint);
        CornerData cornerData6 = smoothData.bottomRight;
        PointF pointF24 = cornerData6.bezierAnchorHorizontal[0];
        pointF.x = pointF24.x;
        pointF.y = pointF24.y;
        if (cornerData6.smoothForHorizontal != 0.0d) {
            Path path5 = new Path();
            path5.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr5 = smoothData.bottomRight.bezierAnchorHorizontal;
            PointF pointF25 = pointFArr5[1];
            float f22 = pointF25.x;
            float f23 = pointF25.y;
            PointF pointF26 = pointFArr5[2];
            float f24 = pointF26.x;
            float f25 = pointF26.y;
            PointF pointF27 = pointFArr5[3];
            path5.cubicTo(f22, f23, f24, f25, pointF27.x, pointF27.y);
            paint.setColor(i4);
            canvas.drawPath(path5, paint);
            PointF pointF28 = smoothData.bottomRight.bezierAnchorHorizontal[3];
            pointF.x = pointF28.x;
            pointF.y = pointF28.y;
        }
        if (!isWidthCollapsed(smoothData.width, smoothData.bottomRight.radius, smoothData.bottomLeft.radius, smoothData.smooth, smoothData.ksi)) {
            paint.setColor(i2);
            float f26 = pointF.x;
            float f27 = pointF.y;
            PointF pointF29 = smoothData.bottomLeft.bezierAnchorHorizontal[0];
            canvas.drawLine(f26, f27, pointF29.x, pointF29.y, paint);
            PointF pointF30 = smoothData.bottomLeft.bezierAnchorHorizontal[0];
            pointF.x = pointF30.x;
            pointF.y = pointF30.y;
        }
        if (smoothData.bottomLeft.smoothForHorizontal != 0.0d) {
            Path path6 = new Path();
            path6.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr6 = smoothData.bottomLeft.bezierAnchorHorizontal;
            PointF pointF31 = pointFArr6[1];
            float f28 = pointF31.x;
            float f29 = pointF31.y;
            PointF pointF32 = pointFArr6[2];
            float f30 = pointF32.x;
            float f31 = pointF32.y;
            PointF pointF33 = pointFArr6[3];
            path6.cubicTo(f28, f29, f30, f31, pointF33.x, pointF33.y);
            paint.setColor(i4);
            canvas.drawPath(path6, paint);
            PointF pointF34 = smoothData.bottomLeft.bezierAnchorHorizontal[3];
            pointF.x = pointF34.x;
            pointF.y = pointF34.y;
        }
        paint.setColor(i3);
        CornerData cornerData7 = smoothData.bottomLeft;
        canvas.drawArc(cornerData7.rect, (float) radToAngle(cornerData7.thetaForHorizontal + 1.5707963267948966d), smoothData.bottomLeft.swapAngle, false, paint);
        CornerData cornerData8 = smoothData.bottomLeft;
        PointF pointF35 = cornerData8.bezierAnchorVertical[0];
        pointF.x = pointF35.x;
        pointF.y = pointF35.y;
        if (cornerData8.smoothForVertical != 0.0d) {
            Path path7 = new Path();
            path7.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr7 = smoothData.bottomLeft.bezierAnchorVertical;
            PointF pointF36 = pointFArr7[1];
            float f32 = pointF36.x;
            float f33 = pointF36.y;
            PointF pointF37 = pointFArr7[2];
            float f34 = pointF37.x;
            float f35 = pointF37.y;
            PointF pointF38 = pointFArr7[3];
            path7.cubicTo(f32, f33, f34, f35, pointF38.x, pointF38.y);
            paint.setColor(i4);
            canvas.drawPath(path7, paint);
            PointF pointF39 = smoothData.bottomLeft.bezierAnchorVertical[3];
            pointF.x = pointF39.x;
            pointF.y = pointF39.y;
        }
        if (!isHeightCollapsed(smoothData.height, smoothData.bottomLeft.radius, smoothData.topLeft.radius, smoothData.smooth, smoothData.ksi)) {
            paint.setColor(i2);
            float f36 = pointF.x;
            float f37 = pointF.y;
            PointF pointF40 = smoothData.topLeft.bezierAnchorVertical[0];
            canvas.drawLine(f36, f37, pointF40.x, pointF40.y, paint);
            PointF pointF41 = smoothData.topLeft.bezierAnchorVertical[0];
            pointF.x = pointF41.x;
            pointF.y = pointF41.y;
        }
        if (smoothData.topLeft.smoothForVertical != 0.0d) {
            Path path8 = new Path();
            path8.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr8 = smoothData.topLeft.bezierAnchorVertical;
            PointF pointF42 = pointFArr8[1];
            float f38 = pointF42.x;
            float f39 = pointF42.y;
            PointF pointF43 = pointFArr8[2];
            float f40 = pointF43.x;
            float f41 = pointF43.y;
            PointF pointF44 = pointFArr8[3];
            path8.cubicTo(f38, f39, f40, f41, pointF44.x, pointF44.y);
            paint.setColor(i4);
            canvas.drawPath(path8, paint);
            PointF pointF45 = smoothData.topLeft.bezierAnchorVertical[3];
            pointF.x = pointF45.x;
            pointF.y = pointF45.y;
        }
    }

    public float getKsi() {
        return this.mKsi;
    }

    public float getSmooth() {
        return this.mSmooth;
    }

    public Path getSmoothPath(Path path, @Nullable SmoothData smoothData) {
        Path path2 = path == null ? new Path() : path;
        path2.reset();
        if (smoothData == null) {
            return path2;
        }
        if (isFourCornerDataValid(smoothData)) {
            path2.addRect(new RectF(0.0f, 0.0f, smoothData.width, smoothData.height), Path.Direction.CCW);
            return path2;
        }
        CornerData cornerData = smoothData.topLeft;
        if (cornerData.swapAngle != 0.0f) {
            path2.arcTo(cornerData.rect, (float) radToAngle(cornerData.thetaForVertical + 3.141592653589793d), smoothData.topLeft.swapAngle);
        } else {
            PointF pointF = cornerData.bezierAnchorHorizontal[0];
            path2.moveTo(pointF.x, pointF.y);
        }
        CornerData cornerData2 = smoothData.topLeft;
        if (cornerData2.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr = cornerData2.bezierAnchorHorizontal;
            PointF pointF2 = pointFArr[1];
            float f2 = pointF2.x;
            float f3 = pointF2.y;
            PointF pointF3 = pointFArr[2];
            float f4 = pointF3.x;
            float f5 = pointF3.y;
            PointF pointF4 = pointFArr[3];
            path2.cubicTo(f2, f3, f4, f5, pointF4.x, pointF4.y);
        }
        if (!isWidthCollapsed(smoothData.width, smoothData.topLeft.radius, smoothData.topRight.radius, smoothData.smooth, smoothData.ksi)) {
            PointF pointF5 = smoothData.topRight.bezierAnchorHorizontal[0];
            path2.lineTo(pointF5.x, pointF5.y);
        }
        CornerData cornerData3 = smoothData.topRight;
        if (cornerData3.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr2 = cornerData3.bezierAnchorHorizontal;
            PointF pointF6 = pointFArr2[1];
            float f6 = pointF6.x;
            float f7 = pointF6.y;
            PointF pointF7 = pointFArr2[2];
            float f8 = pointF7.x;
            float f9 = pointF7.y;
            PointF pointF8 = pointFArr2[3];
            path2.cubicTo(f6, f7, f8, f9, pointF8.x, pointF8.y);
        }
        CornerData cornerData4 = smoothData.topRight;
        if (cornerData4.swapAngle != 0.0f) {
            path2.arcTo(cornerData4.rect, (float) radToAngle(cornerData4.thetaForHorizontal + 4.71238898038469d), smoothData.topRight.swapAngle);
        }
        CornerData cornerData5 = smoothData.topRight;
        if (cornerData5.smoothForVertical != 0.0d) {
            PointF[] pointFArr3 = cornerData5.bezierAnchorVertical;
            PointF pointF9 = pointFArr3[1];
            float f10 = pointF9.x;
            float f11 = pointF9.y;
            PointF pointF10 = pointFArr3[2];
            float f12 = pointF10.x;
            float f13 = pointF10.y;
            PointF pointF11 = pointFArr3[3];
            path2.cubicTo(f10, f11, f12, f13, pointF11.x, pointF11.y);
        }
        if (!isHeightCollapsed(smoothData.height, smoothData.topRight.radius, smoothData.bottomRight.radius, smoothData.smooth, smoothData.ksi)) {
            PointF pointF12 = smoothData.bottomRight.bezierAnchorVertical[0];
            path2.lineTo(pointF12.x, pointF12.y);
        }
        CornerData cornerData6 = smoothData.bottomRight;
        if (cornerData6.smoothForVertical != 0.0d) {
            PointF[] pointFArr4 = cornerData6.bezierAnchorVertical;
            PointF pointF13 = pointFArr4[1];
            float f14 = pointF13.x;
            float f15 = pointF13.y;
            PointF pointF14 = pointFArr4[2];
            float f16 = pointF14.x;
            float f17 = pointF14.y;
            PointF pointF15 = pointFArr4[3];
            path2.cubicTo(f14, f15, f16, f17, pointF15.x, pointF15.y);
        }
        CornerData cornerData7 = smoothData.bottomRight;
        if (cornerData7.swapAngle != 0.0f) {
            path2.arcTo(cornerData7.rect, (float) radToAngle(cornerData7.thetaForVertical), smoothData.bottomRight.swapAngle);
        }
        CornerData cornerData8 = smoothData.bottomRight;
        if (cornerData8.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr5 = cornerData8.bezierAnchorHorizontal;
            PointF pointF16 = pointFArr5[1];
            float f18 = pointF16.x;
            float f19 = pointF16.y;
            PointF pointF17 = pointFArr5[2];
            float f20 = pointF17.x;
            float f21 = pointF17.y;
            PointF pointF18 = pointFArr5[3];
            path2.cubicTo(f18, f19, f20, f21, pointF18.x, pointF18.y);
        }
        if (!isWidthCollapsed(smoothData.width, smoothData.bottomRight.radius, smoothData.bottomLeft.radius, smoothData.smooth, smoothData.ksi)) {
            PointF pointF19 = smoothData.bottomLeft.bezierAnchorHorizontal[0];
            path2.lineTo(pointF19.x, pointF19.y);
        }
        CornerData cornerData9 = smoothData.bottomLeft;
        if (cornerData9.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr6 = cornerData9.bezierAnchorHorizontal;
            PointF pointF20 = pointFArr6[1];
            float f22 = pointF20.x;
            float f23 = pointF20.y;
            PointF pointF21 = pointFArr6[2];
            float f24 = pointF21.x;
            float f25 = pointF21.y;
            PointF pointF22 = pointFArr6[3];
            path2.cubicTo(f22, f23, f24, f25, pointF22.x, pointF22.y);
        }
        CornerData cornerData10 = smoothData.bottomLeft;
        if (cornerData10.swapAngle != 0.0f) {
            path2.arcTo(cornerData10.rect, (float) radToAngle(cornerData10.thetaForHorizontal + 1.5707963267948966d), smoothData.bottomLeft.swapAngle);
        }
        CornerData cornerData11 = smoothData.bottomLeft;
        if (cornerData11.smoothForVertical != 0.0d) {
            PointF[] pointFArr7 = cornerData11.bezierAnchorVertical;
            PointF pointF23 = pointFArr7[1];
            float f26 = pointF23.x;
            float f27 = pointF23.y;
            PointF pointF24 = pointFArr7[2];
            float f28 = pointF24.x;
            float f29 = pointF24.y;
            PointF pointF25 = pointFArr7[3];
            path2.cubicTo(f26, f27, f28, f29, pointF25.x, pointF25.y);
        }
        if (!isHeightCollapsed(smoothData.height, smoothData.bottomLeft.radius, smoothData.topLeft.radius, smoothData.smooth, smoothData.ksi)) {
            PointF pointF26 = smoothData.topLeft.bezierAnchorVertical[0];
            path2.lineTo(pointF26.x, pointF26.y);
        }
        CornerData cornerData12 = smoothData.topLeft;
        if (cornerData12.smoothForVertical != 0.0d) {
            PointF[] pointFArr8 = cornerData12.bezierAnchorVertical;
            PointF pointF27 = pointFArr8[1];
            float f30 = pointF27.x;
            float f31 = pointF27.y;
            PointF pointF28 = pointFArr8[2];
            float f32 = pointF28.x;
            float f33 = pointF28.y;
            PointF pointF29 = pointFArr8[3];
            path2.cubicTo(f30, f31, f32, f33, pointF29.x, pointF29.y);
        }
        path2.close();
        return path2;
    }

    public void setKsi(float f2) {
        this.mKsi = f2;
    }

    public void setSmooth(float f2) {
        this.mSmooth = f2;
    }

    @Nullable
    public SmoothData buildSmoothData(RectF rectF, float f2, float f3, float f4) {
        return buildSmoothData(rectF, new float[]{f2, f2, f2, f2, f2, f2, f2, f2}, f3, f4);
    }

    @Nullable
    public SmoothData buildSmoothData(RectF rectF, float[] fArr) {
        return buildSmoothData(rectF, fArr, 0.0f, 0.0f);
    }

    @Nullable
    public SmoothData buildSmoothData(RectF rectF, float[] fArr, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        float f7;
        SmoothPathProvider2 smoothPathProvider2;
        if (fArr == null) {
            return null;
        }
        float ksi = getKsi();
        float smooth = getSmooth();
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        double d2 = smooth;
        SmoothData smoothData = new SmoothData(fWidth, fHeight, d2, ksi);
        float[] fArr2 = new float[8];
        fArr2[0] = 0.0f;
        fArr2[1] = 0.0f;
        fArr2[2] = 0.0f;
        fArr2[3] = 0.0f;
        fArr2[4] = 0.0f;
        fArr2[5] = 0.0f;
        fArr2[6] = 0.0f;
        fArr2[7] = 0.0f;
        for (int i2 = 0; i2 < Math.min(8, fArr.length); i2++) {
            if (!Float.isNaN(fArr[i2])) {
                fArr2[i2] = fArr[i2];
            }
        }
        float f8 = fArr2[0];
        float f9 = fArr2[1];
        float f10 = fArr2[2];
        float f11 = fArr2[3];
        float f12 = fArr2[4];
        float f13 = fArr2[5];
        float f14 = fArr2[6];
        float f15 = fArr2[7];
        if (f8 + f10 > fWidth) {
            float f16 = (fWidth * f8) / (f8 + f10);
            f10 = (fWidth * f10) / (f8 + f10);
            f8 = f16;
        }
        float f17 = f10;
        if (f11 + f13 > fHeight) {
            float f18 = (fHeight * f11) / (f11 + f13);
            f13 = (fHeight * f13) / (f11 + f13);
            f4 = f18;
        } else {
            f4 = f11;
        }
        if (f12 + f14 > fWidth) {
            float f19 = (fWidth * f12) / (f12 + f14);
            f5 = (fWidth * f14) / (f12 + f14);
            f6 = f19;
        } else {
            f5 = f14;
            f6 = f12;
        }
        if (f15 + f9 > fHeight) {
            float f20 = (fHeight * f15) / (f15 + f9);
            f9 = (fHeight * f9) / (f15 + f9);
            smoothPathProvider2 = this;
            f7 = f20;
        } else {
            f7 = f15;
            smoothPathProvider2 = this;
        }
        smoothPathProvider2.ensureFourCornerData(smoothData);
        smoothData.topLeft.build(Math.min(f8, f9), rectF, f2, f3, d2, ksi, 0);
        smoothData.topRight.build(Math.min(f17, f4), rectF, f2, f3, d2, ksi, 1);
        smoothData.bottomRight.build(Math.min(f6, f13), rectF, f2, f3, d2, ksi, 2);
        smoothData.bottomLeft.build(Math.min(f5, f7), rectF, f2, f3, d2, ksi, 3);
        return smoothData;
    }
}
