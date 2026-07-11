package miui.systemui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class SmoothPathProvider {
    public static final float DEFAULT_SMOOTH = 0.7f;
    private SmoothData mAllData = null;

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

        public void build(float f2, int i2, int i3, double d2, int i4) {
            this.radius = f2;
            this.smoothForHorizontal = SmoothPathProvider.smoothForWidth(i2, f2, d2);
            this.smoothForVertical = SmoothPathProvider.smoothForHeight(i3, this.radius, d2);
            this.thetaForHorizontal = SmoothPathProvider.thetaForWidth(this.smoothForHorizontal);
            double dThetaForHeight = SmoothPathProvider.thetaForHeight(this.smoothForVertical);
            this.thetaForVertical = dThetaForHeight;
            this.swapAngle = (float) SmoothPathProvider.radToAngle((1.5707963267948966d - dThetaForHeight) - this.thetaForHorizontal);
            double dKForWidth = SmoothPathProvider.kForWidth(this.smoothForHorizontal, this.thetaForHorizontal);
            double dMForWidth = SmoothPathProvider.mForWidth(this.radius, this.thetaForHorizontal);
            double dNForWidth = SmoothPathProvider.nForWidth(this.radius, this.thetaForHorizontal);
            double dPForWidth = SmoothPathProvider.pForWidth(this.radius, this.thetaForHorizontal);
            double dXForWidth = SmoothPathProvider.xForWidth(this.radius, this.thetaForHorizontal);
            double dYForWidth = SmoothPathProvider.yForWidth(dKForWidth, dXForWidth);
            double dKForHeight = SmoothPathProvider.kForHeight(this.smoothForVertical, this.thetaForVertical);
            double dMForHeight = SmoothPathProvider.mForHeight(this.radius, this.thetaForVertical);
            double dNForHeight = SmoothPathProvider.nForHeight(this.radius, this.thetaForVertical);
            double dPForHeight = SmoothPathProvider.pForHeight(this.radius, this.thetaForVertical);
            double dXForHeight = SmoothPathProvider.xForHeight(this.radius, this.thetaForVertical);
            double dYForHeight = SmoothPathProvider.yForHeight(dKForHeight, dXForHeight);
            if (i4 == 0) {
                float f3 = this.radius;
                this.rect = new RectF(0.0f, 0.0f, f3 * 2.0f, f3 * 2.0f);
                this.bezierAnchorHorizontal[0] = new PointF((float) dMForWidth, (float) dNForWidth);
                this.bezierAnchorHorizontal[1] = new PointF((float) dPForWidth, 0.0f);
                double d3 = dPForWidth + dXForWidth;
                this.bezierAnchorHorizontal[2] = new PointF((float) d3, 0.0f);
                this.bezierAnchorHorizontal[3] = new PointF((float) (d3 + dYForWidth), 0.0f);
                double d4 = dPForHeight + dXForHeight;
                this.bezierAnchorVertical[0] = new PointF(0.0f, (float) (d4 + dYForHeight));
                this.bezierAnchorVertical[1] = new PointF(0.0f, (float) d4);
                this.bezierAnchorVertical[2] = new PointF(0.0f, (float) dPForHeight);
                this.bezierAnchorVertical[3] = new PointF((float) dMForHeight, (float) dNForHeight);
                return;
            }
            if (i4 == 1) {
                float f4 = i2;
                float f5 = this.radius;
                this.rect = new RectF(f4 - (f5 * 2.0f), 0.0f, f4, f5 * 2.0f);
                double d5 = i2;
                double d6 = d5 - dPForWidth;
                double d7 = d6 - dXForWidth;
                this.bezierAnchorHorizontal[0] = new PointF((float) (d7 - dYForWidth), 0.0f);
                this.bezierAnchorHorizontal[1] = new PointF((float) d7, 0.0f);
                this.bezierAnchorHorizontal[2] = new PointF((float) d6, 0.0f);
                this.bezierAnchorHorizontal[3] = new PointF((float) (d5 - dMForWidth), (float) dNForWidth);
                this.bezierAnchorVertical[0] = new PointF((float) (d5 - dMForHeight), (float) dNForHeight);
                this.bezierAnchorVertical[1] = new PointF(f4, (float) dPForHeight);
                double d8 = dPForHeight + dXForHeight;
                this.bezierAnchorVertical[2] = new PointF(f4, (float) d8);
                this.bezierAnchorVertical[3] = new PointF(f4, (float) (d8 + dYForHeight));
                return;
            }
            if (i4 != 2) {
                if (i4 == 3) {
                    float f6 = i3;
                    float f7 = this.radius;
                    this.rect = new RectF(0.0f, f6 - (f7 * 2.0f), f7 * 2.0f, f6);
                    double d9 = dPForWidth + dXForWidth;
                    this.bezierAnchorHorizontal[0] = new PointF((float) (d9 + dYForWidth), f6);
                    this.bezierAnchorHorizontal[1] = new PointF((float) d9, f6);
                    this.bezierAnchorHorizontal[2] = new PointF((float) dPForWidth, f6);
                    double d10 = i3;
                    this.bezierAnchorHorizontal[3] = new PointF((float) dMForWidth, (float) (d10 - dNForWidth));
                    this.bezierAnchorVertical[0] = new PointF((float) dMForHeight, (float) (d10 - dNForHeight));
                    double d11 = d10 - dPForHeight;
                    this.bezierAnchorVertical[1] = new PointF(0.0f, (float) d11);
                    double d12 = d11 - dXForHeight;
                    this.bezierAnchorVertical[2] = new PointF(0.0f, (float) d12);
                    this.bezierAnchorVertical[3] = new PointF(0.0f, (float) (d12 - dYForHeight));
                    return;
                }
                return;
            }
            float f8 = i2;
            float f9 = this.radius;
            float f10 = i3;
            this.rect = new RectF(f8 - (f9 * 2.0f), f10 - (f9 * 2.0f), f8, f10);
            double d13 = i2;
            double d14 = i3;
            this.bezierAnchorHorizontal[0] = new PointF((float) (d13 - dMForWidth), (float) (d14 - dNForWidth));
            double d15 = d13 - dPForWidth;
            this.bezierAnchorHorizontal[1] = new PointF((float) d15, f10);
            double d16 = d15 - dXForWidth;
            this.bezierAnchorHorizontal[2] = new PointF((float) d16, f10);
            this.bezierAnchorHorizontal[3] = new PointF((float) (d16 - dYForWidth), f10);
            double d17 = d14 - dPForHeight;
            double d18 = d17 - dXForHeight;
            this.bezierAnchorVertical[0] = new PointF(f8, (float) (d18 - dYForHeight));
            this.bezierAnchorVertical[1] = new PointF(f8, (float) d18);
            this.bezierAnchorVertical[2] = new PointF(f8, (float) d17);
            this.bezierAnchorVertical[3] = new PointF((float) (d13 - dMForHeight), (float) (d14 - dNForHeight));
        }
    }

    public static class SmoothData {
        public int height;
        public double smooth;
        public int width;
        public CornerData topLeft = null;
        public CornerData topRight = null;
        public CornerData bottomRight = null;
        public CornerData bottomLeft = null;

        public SmoothData(int i2, int i3, double d2) {
            this.width = i2;
            this.height = i3;
            this.smooth = d2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SmoothData smoothData = (SmoothData) obj;
            return this.width == smoothData.width && this.height == smoothData.height && Double.compare(smoothData.smooth, this.smooth) == 0 && Objects.equals(this.topLeft, smoothData.topLeft) && Objects.equals(this.topRight, smoothData.topRight) && Objects.equals(this.bottomRight, smoothData.bottomRight) && Objects.equals(this.bottomLeft, smoothData.bottomLeft);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.width), Integer.valueOf(this.height), Double.valueOf(this.smooth), this.topLeft, this.topRight, this.bottomRight, this.bottomLeft);
        }
    }

    private void ensureFourCornerData() {
        SmoothData smoothData = this.mAllData;
        if (smoothData.topLeft == null) {
            smoothData.topLeft = new CornerData();
        }
        SmoothData smoothData2 = this.mAllData;
        if (smoothData2.topRight == null) {
            smoothData2.topRight = new CornerData();
        }
        SmoothData smoothData3 = this.mAllData;
        if (smoothData3.bottomRight == null) {
            smoothData3.bottomRight = new CornerData();
        }
        SmoothData smoothData4 = this.mAllData;
        if (smoothData4.bottomLeft == null) {
            smoothData4.bottomLeft = new CornerData();
        }
    }

    private static boolean isCircle(SmoothData smoothData) {
        int i2 = smoothData.width;
        int i3 = smoothData.height;
        if (i2 == i3) {
            float f2 = smoothData.topLeft.radius;
            float f3 = smoothData.topRight.radius;
            if (f2 + f3 >= i2) {
                float f4 = smoothData.bottomLeft.radius;
                float f5 = smoothData.bottomRight.radius;
                if (f4 + f5 >= i2 && f2 + f4 >= i3 && f3 + f5 >= i3) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isFourCornerDataValid(SmoothData smoothData) {
        return smoothData.topLeft == null || smoothData.topRight == null || smoothData.bottomRight == null || smoothData.bottomLeft == null;
    }

    private static boolean isHeightCollapsed(int i2, float f2, float f3, double d2) {
        return ((double) i2) <= ((double) (f2 + f3)) * (d2 + 1.0d);
    }

    private static boolean isWidthCollapsed(int i2, float f2, float f3, double d2) {
        return ((double) i2) <= ((double) (f2 + f3)) * (d2 + 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double kForHeight(double d2, double d3) {
        if (d3 == 0.0d) {
            return 0.0d;
        }
        double d4 = d3 / 2.0d;
        return ((((d2 + Math.tan(d4)) * 2.0d) * (Math.cos(d3) + 1.0d)) / (Math.tan(d4) * 3.0d)) - 1.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double kForWidth(double d2, double d3) {
        if (d3 == 0.0d) {
            return 0.0d;
        }
        double d4 = d3 / 2.0d;
        return ((((d2 + Math.tan(d4)) * 2.0d) * (Math.cos(d3) + 1.0d)) / (Math.tan(d4) * 3.0d)) - 1.0d;
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
    public static double smoothForHeight(int i2, float f2, double d2) {
        return isHeightCollapsed(i2, f2, f2, d2) ? Math.max(Math.min((i2 / (f2 * 2.0f)) - 1.0f, 1.0f), 0.0f) : d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double smoothForWidth(int i2, float f2, double d2) {
        return isWidthCollapsed(i2, f2, f2, d2) ? Math.max(Math.min((i2 / (f2 * 2.0f)) - 1.0f, 1.0f), 0.0f) : d2;
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

    public void buildSmoothData(int i2, int i3, float f2, double d2) {
        buildSmoothData(i2, i3, new float[]{f2, f2, f2, f2, f2, f2, f2, f2}, d2);
    }

    public void drawPath(Canvas canvas, Paint paint, int i2, int i3, int i4, int i5, boolean z2) {
        if (isFourCornerDataValid(this.mAllData)) {
            paint.setColor(i2);
            SmoothData smoothData = this.mAllData;
            canvas.drawRect(new RectF(0.0f, 0.0f, smoothData.width, smoothData.height), paint);
            return;
        }
        if (z2) {
            paint.setColor(i5);
            canvas.drawArc(this.mAllData.topLeft.rect, 180.0f, 90.0f, false, paint);
            SmoothData smoothData2 = this.mAllData;
            canvas.drawLine(smoothData2.topLeft.radius, 0.0f, smoothData2.width - smoothData2.topRight.radius, 0.0f, paint);
            canvas.drawArc(this.mAllData.topRight.rect, 270.0f, 90.0f, false, paint);
            SmoothData smoothData3 = this.mAllData;
            int i6 = smoothData3.width;
            canvas.drawLine(i6, smoothData3.topRight.radius, i6, smoothData3.height - smoothData3.bottomRight.radius, paint);
            canvas.drawArc(this.mAllData.bottomRight.rect, 0.0f, 90.0f, false, paint);
            SmoothData smoothData4 = this.mAllData;
            float f2 = smoothData4.width - smoothData4.bottomRight.radius;
            int i7 = smoothData4.height;
            canvas.drawLine(f2, i7, smoothData4.bottomLeft.radius, i7, paint);
            canvas.drawArc(this.mAllData.bottomLeft.rect, 90.0f, 90.0f, false, paint);
            SmoothData smoothData5 = this.mAllData;
            canvas.drawLine(0.0f, smoothData5.height - smoothData5.bottomLeft.radius, 0.0f, smoothData5.topLeft.radius, paint);
        }
        PointF pointF = new PointF();
        paint.setColor(i3);
        CornerData cornerData = this.mAllData.topLeft;
        canvas.drawArc(cornerData.rect, (float) radToAngle(cornerData.thetaForVertical + 3.141592653589793d), this.mAllData.topLeft.swapAngle, false, paint);
        CornerData cornerData2 = this.mAllData.topLeft;
        PointF pointF2 = cornerData2.bezierAnchorHorizontal[0];
        pointF.x = pointF2.x;
        pointF.y = pointF2.y;
        if (cornerData2.smoothForHorizontal != 0.0d) {
            Path path = new Path();
            path.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr = this.mAllData.topLeft.bezierAnchorHorizontal;
            PointF pointF3 = pointFArr[1];
            float f3 = pointF3.x;
            float f4 = pointF3.y;
            PointF pointF4 = pointFArr[2];
            float f5 = pointF4.x;
            float f6 = pointF4.y;
            PointF pointF5 = pointFArr[3];
            path.cubicTo(f3, f4, f5, f6, pointF5.x, pointF5.y);
            paint.setColor(i4);
            canvas.drawPath(path, paint);
            PointF pointF6 = this.mAllData.topLeft.bezierAnchorHorizontal[3];
            pointF.x = pointF6.x;
            pointF.y = pointF6.y;
        }
        SmoothData smoothData6 = this.mAllData;
        if (!isWidthCollapsed(smoothData6.width, smoothData6.topLeft.radius, smoothData6.topRight.radius, smoothData6.smooth)) {
            paint.setColor(i2);
            float f7 = pointF.x;
            float f8 = pointF.y;
            PointF pointF7 = this.mAllData.topRight.bezierAnchorHorizontal[0];
            canvas.drawLine(f7, f8, pointF7.x, pointF7.y, paint);
            PointF pointF8 = this.mAllData.topRight.bezierAnchorHorizontal[0];
            pointF.x = pointF8.x;
            pointF.y = pointF8.y;
        }
        if (this.mAllData.topRight.smoothForHorizontal != 0.0d) {
            Path path2 = new Path();
            path2.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr2 = this.mAllData.topRight.bezierAnchorHorizontal;
            PointF pointF9 = pointFArr2[1];
            float f9 = pointF9.x;
            float f10 = pointF9.y;
            PointF pointF10 = pointFArr2[2];
            float f11 = pointF10.x;
            float f12 = pointF10.y;
            PointF pointF11 = pointFArr2[3];
            path2.cubicTo(f9, f10, f11, f12, pointF11.x, pointF11.y);
            paint.setColor(i4);
            canvas.drawPath(path2, paint);
            PointF pointF12 = this.mAllData.topRight.bezierAnchorHorizontal[3];
            pointF.x = pointF12.x;
            pointF.y = pointF12.y;
        }
        paint.setColor(i3);
        CornerData cornerData3 = this.mAllData.topRight;
        canvas.drawArc(cornerData3.rect, (float) radToAngle(cornerData3.thetaForHorizontal + 4.71238898038469d), this.mAllData.topRight.swapAngle, false, paint);
        CornerData cornerData4 = this.mAllData.topRight;
        PointF pointF13 = cornerData4.bezierAnchorVertical[0];
        pointF.x = pointF13.x;
        pointF.y = pointF13.y;
        if (cornerData4.smoothForVertical != 0.0d) {
            Path path3 = new Path();
            path3.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr3 = this.mAllData.topRight.bezierAnchorVertical;
            PointF pointF14 = pointFArr3[1];
            float f13 = pointF14.x;
            float f14 = pointF14.y;
            PointF pointF15 = pointFArr3[2];
            float f15 = pointF15.x;
            float f16 = pointF15.y;
            PointF pointF16 = pointFArr3[3];
            path3.cubicTo(f13, f14, f15, f16, pointF16.x, pointF16.y);
            paint.setColor(i4);
            canvas.drawPath(path3, paint);
            PointF pointF17 = this.mAllData.topRight.bezierAnchorVertical[3];
            pointF.x = pointF17.x;
            pointF.y = pointF17.y;
        }
        SmoothData smoothData7 = this.mAllData;
        if (!isHeightCollapsed(smoothData7.height, smoothData7.topRight.radius, smoothData7.bottomRight.radius, smoothData7.smooth)) {
            paint.setColor(i2);
            float f17 = pointF.x;
            float f18 = pointF.y;
            PointF pointF18 = this.mAllData.bottomRight.bezierAnchorVertical[0];
            canvas.drawLine(f17, f18, pointF18.x, pointF18.y, paint);
            PointF pointF19 = this.mAllData.bottomRight.bezierAnchorVertical[0];
            pointF.x = pointF19.x;
            pointF.y = pointF19.y;
        }
        if (this.mAllData.bottomRight.smoothForVertical != 0.0d) {
            Path path4 = new Path();
            path4.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr4 = this.mAllData.bottomRight.bezierAnchorVertical;
            PointF pointF20 = pointFArr4[1];
            float f19 = pointF20.x;
            float f20 = pointF20.y;
            PointF pointF21 = pointFArr4[2];
            float f21 = pointF21.x;
            float f22 = pointF21.y;
            PointF pointF22 = pointFArr4[3];
            path4.cubicTo(f19, f20, f21, f22, pointF22.x, pointF22.y);
            paint.setColor(i4);
            canvas.drawPath(path4, paint);
            PointF pointF23 = this.mAllData.bottomRight.bezierAnchorVertical[3];
            pointF.x = pointF23.x;
            pointF.y = pointF23.y;
        }
        paint.setColor(i3);
        CornerData cornerData5 = this.mAllData.bottomRight;
        canvas.drawArc(cornerData5.rect, (float) radToAngle(cornerData5.thetaForVertical), this.mAllData.bottomRight.swapAngle, false, paint);
        CornerData cornerData6 = this.mAllData.bottomRight;
        PointF pointF24 = cornerData6.bezierAnchorHorizontal[0];
        pointF.x = pointF24.x;
        pointF.y = pointF24.y;
        if (cornerData6.smoothForHorizontal != 0.0d) {
            Path path5 = new Path();
            path5.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr5 = this.mAllData.bottomRight.bezierAnchorHorizontal;
            PointF pointF25 = pointFArr5[1];
            float f23 = pointF25.x;
            float f24 = pointF25.y;
            PointF pointF26 = pointFArr5[2];
            float f25 = pointF26.x;
            float f26 = pointF26.y;
            PointF pointF27 = pointFArr5[3];
            path5.cubicTo(f23, f24, f25, f26, pointF27.x, pointF27.y);
            paint.setColor(i4);
            canvas.drawPath(path5, paint);
            PointF pointF28 = this.mAllData.bottomRight.bezierAnchorHorizontal[3];
            pointF.x = pointF28.x;
            pointF.y = pointF28.y;
        }
        SmoothData smoothData8 = this.mAllData;
        if (!isWidthCollapsed(smoothData8.width, smoothData8.bottomRight.radius, smoothData8.bottomLeft.radius, smoothData8.smooth)) {
            paint.setColor(i2);
            float f27 = pointF.x;
            float f28 = pointF.y;
            PointF pointF29 = this.mAllData.bottomLeft.bezierAnchorHorizontal[0];
            canvas.drawLine(f27, f28, pointF29.x, pointF29.y, paint);
            PointF pointF30 = this.mAllData.bottomLeft.bezierAnchorHorizontal[0];
            pointF.x = pointF30.x;
            pointF.y = pointF30.y;
        }
        if (this.mAllData.bottomLeft.smoothForHorizontal != 0.0d) {
            Path path6 = new Path();
            path6.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr6 = this.mAllData.bottomLeft.bezierAnchorHorizontal;
            PointF pointF31 = pointFArr6[1];
            float f29 = pointF31.x;
            float f30 = pointF31.y;
            PointF pointF32 = pointFArr6[2];
            float f31 = pointF32.x;
            float f32 = pointF32.y;
            PointF pointF33 = pointFArr6[3];
            path6.cubicTo(f29, f30, f31, f32, pointF33.x, pointF33.y);
            paint.setColor(i4);
            canvas.drawPath(path6, paint);
            PointF pointF34 = this.mAllData.bottomLeft.bezierAnchorHorizontal[3];
            pointF.x = pointF34.x;
            pointF.y = pointF34.y;
        }
        paint.setColor(i3);
        CornerData cornerData7 = this.mAllData.bottomLeft;
        canvas.drawArc(cornerData7.rect, (float) radToAngle(cornerData7.thetaForHorizontal + 1.5707963267948966d), this.mAllData.bottomLeft.swapAngle, false, paint);
        CornerData cornerData8 = this.mAllData.bottomLeft;
        PointF pointF35 = cornerData8.bezierAnchorVertical[0];
        pointF.x = pointF35.x;
        pointF.y = pointF35.y;
        if (cornerData8.smoothForVertical != 0.0d) {
            Path path7 = new Path();
            path7.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr7 = this.mAllData.bottomLeft.bezierAnchorVertical;
            PointF pointF36 = pointFArr7[1];
            float f33 = pointF36.x;
            float f34 = pointF36.y;
            PointF pointF37 = pointFArr7[2];
            float f35 = pointF37.x;
            float f36 = pointF37.y;
            PointF pointF38 = pointFArr7[3];
            path7.cubicTo(f33, f34, f35, f36, pointF38.x, pointF38.y);
            paint.setColor(i4);
            canvas.drawPath(path7, paint);
            PointF pointF39 = this.mAllData.bottomLeft.bezierAnchorVertical[3];
            pointF.x = pointF39.x;
            pointF.y = pointF39.y;
        }
        SmoothData smoothData9 = this.mAllData;
        if (!isHeightCollapsed(smoothData9.height, smoothData9.bottomLeft.radius, smoothData9.topLeft.radius, smoothData9.smooth)) {
            paint.setColor(i2);
            float f37 = pointF.x;
            float f38 = pointF.y;
            PointF pointF40 = this.mAllData.topLeft.bezierAnchorVertical[0];
            canvas.drawLine(f37, f38, pointF40.x, pointF40.y, paint);
            PointF pointF41 = this.mAllData.topLeft.bezierAnchorVertical[0];
            pointF.x = pointF41.x;
            pointF.y = pointF41.y;
        }
        if (this.mAllData.topLeft.smoothForVertical != 0.0d) {
            Path path8 = new Path();
            path8.moveTo(pointF.x, pointF.y);
            PointF[] pointFArr8 = this.mAllData.topLeft.bezierAnchorVertical;
            PointF pointF42 = pointFArr8[1];
            float f39 = pointF42.x;
            float f40 = pointF42.y;
            PointF pointF43 = pointFArr8[2];
            float f41 = pointF43.x;
            float f42 = pointF43.y;
            PointF pointF44 = pointFArr8[3];
            path8.cubicTo(f39, f40, f41, f42, pointF44.x, pointF44.y);
            paint.setColor(i4);
            canvas.drawPath(path8, paint);
            PointF pointF45 = this.mAllData.topLeft.bezierAnchorVertical[3];
            pointF.x = pointF45.x;
            pointF.y = pointF45.y;
        }
    }

    public Path getSmoothPath() {
        return getSmoothPath(this.mAllData);
    }

    public static Path getSmoothPath(SmoothData smoothData) {
        Path path = new Path();
        if (isFourCornerDataValid(smoothData)) {
            path.addRect(new RectF(0.0f, 0.0f, smoothData.width, smoothData.height), Path.Direction.CCW);
            return path;
        }
        if (isCircle(smoothData)) {
            float f2 = smoothData.width / 2.0f;
            path.addCircle(f2, f2, f2, Path.Direction.CCW);
            return path;
        }
        path.reset();
        CornerData cornerData = smoothData.topLeft;
        path.arcTo(cornerData.rect, (float) radToAngle(cornerData.thetaForVertical + 3.141592653589793d), smoothData.topLeft.swapAngle);
        CornerData cornerData2 = smoothData.topLeft;
        if (cornerData2.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr = cornerData2.bezierAnchorHorizontal;
            PointF pointF = pointFArr[1];
            float f3 = pointF.x;
            float f4 = pointF.y;
            PointF pointF2 = pointFArr[2];
            float f5 = pointF2.x;
            float f6 = pointF2.y;
            PointF pointF3 = pointFArr[3];
            path.cubicTo(f3, f4, f5, f6, pointF3.x, pointF3.y);
        }
        if (!isWidthCollapsed(smoothData.width, smoothData.topLeft.radius, smoothData.topRight.radius, smoothData.smooth)) {
            PointF pointF4 = smoothData.topRight.bezierAnchorHorizontal[0];
            path.lineTo(pointF4.x, pointF4.y);
        }
        CornerData cornerData3 = smoothData.topRight;
        if (cornerData3.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr2 = cornerData3.bezierAnchorHorizontal;
            PointF pointF5 = pointFArr2[1];
            float f7 = pointF5.x;
            float f8 = pointF5.y;
            PointF pointF6 = pointFArr2[2];
            float f9 = pointF6.x;
            float f10 = pointF6.y;
            PointF pointF7 = pointFArr2[3];
            path.cubicTo(f7, f8, f9, f10, pointF7.x, pointF7.y);
        }
        CornerData cornerData4 = smoothData.topRight;
        path.arcTo(cornerData4.rect, (float) radToAngle(cornerData4.thetaForHorizontal + 4.71238898038469d), smoothData.topRight.swapAngle);
        CornerData cornerData5 = smoothData.topRight;
        if (cornerData5.smoothForVertical != 0.0d) {
            PointF[] pointFArr3 = cornerData5.bezierAnchorVertical;
            PointF pointF8 = pointFArr3[1];
            float f11 = pointF8.x;
            float f12 = pointF8.y;
            PointF pointF9 = pointFArr3[2];
            float f13 = pointF9.x;
            float f14 = pointF9.y;
            PointF pointF10 = pointFArr3[3];
            path.cubicTo(f11, f12, f13, f14, pointF10.x, pointF10.y);
        }
        if (!isHeightCollapsed(smoothData.height, smoothData.topRight.radius, smoothData.bottomRight.radius, smoothData.smooth)) {
            PointF pointF11 = smoothData.bottomRight.bezierAnchorVertical[0];
            path.lineTo(pointF11.x, pointF11.y);
        }
        CornerData cornerData6 = smoothData.bottomRight;
        if (cornerData6.smoothForVertical != 0.0d) {
            PointF[] pointFArr4 = cornerData6.bezierAnchorVertical;
            PointF pointF12 = pointFArr4[1];
            float f15 = pointF12.x;
            float f16 = pointF12.y;
            PointF pointF13 = pointFArr4[2];
            float f17 = pointF13.x;
            float f18 = pointF13.y;
            PointF pointF14 = pointFArr4[3];
            path.cubicTo(f15, f16, f17, f18, pointF14.x, pointF14.y);
        }
        CornerData cornerData7 = smoothData.bottomRight;
        path.arcTo(cornerData7.rect, (float) radToAngle(cornerData7.thetaForVertical), smoothData.bottomRight.swapAngle);
        CornerData cornerData8 = smoothData.bottomRight;
        if (cornerData8.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr5 = cornerData8.bezierAnchorHorizontal;
            PointF pointF15 = pointFArr5[1];
            float f19 = pointF15.x;
            float f20 = pointF15.y;
            PointF pointF16 = pointFArr5[2];
            float f21 = pointF16.x;
            float f22 = pointF16.y;
            PointF pointF17 = pointFArr5[3];
            path.cubicTo(f19, f20, f21, f22, pointF17.x, pointF17.y);
        }
        if (!isWidthCollapsed(smoothData.width, smoothData.bottomRight.radius, smoothData.bottomLeft.radius, smoothData.smooth)) {
            PointF pointF18 = smoothData.bottomLeft.bezierAnchorHorizontal[0];
            path.lineTo(pointF18.x, pointF18.y);
        }
        CornerData cornerData9 = smoothData.bottomLeft;
        if (cornerData9.smoothForHorizontal != 0.0d) {
            PointF[] pointFArr6 = cornerData9.bezierAnchorHorizontal;
            PointF pointF19 = pointFArr6[1];
            float f23 = pointF19.x;
            float f24 = pointF19.y;
            PointF pointF20 = pointFArr6[2];
            float f25 = pointF20.x;
            float f26 = pointF20.y;
            PointF pointF21 = pointFArr6[3];
            path.cubicTo(f23, f24, f25, f26, pointF21.x, pointF21.y);
        }
        CornerData cornerData10 = smoothData.bottomLeft;
        path.arcTo(cornerData10.rect, (float) radToAngle(cornerData10.thetaForHorizontal + 1.5707963267948966d), smoothData.bottomLeft.swapAngle);
        CornerData cornerData11 = smoothData.bottomLeft;
        if (cornerData11.smoothForVertical != 0.0d) {
            PointF[] pointFArr7 = cornerData11.bezierAnchorVertical;
            PointF pointF22 = pointFArr7[1];
            float f27 = pointF22.x;
            float f28 = pointF22.y;
            PointF pointF23 = pointFArr7[2];
            float f29 = pointF23.x;
            float f30 = pointF23.y;
            PointF pointF24 = pointFArr7[3];
            path.cubicTo(f27, f28, f29, f30, pointF24.x, pointF24.y);
        }
        if (!isHeightCollapsed(smoothData.height, smoothData.bottomLeft.radius, smoothData.topLeft.radius, smoothData.smooth)) {
            PointF pointF25 = smoothData.topLeft.bezierAnchorVertical[0];
            path.lineTo(pointF25.x, pointF25.y);
        }
        CornerData cornerData12 = smoothData.topLeft;
        if (cornerData12.smoothForVertical != 0.0d) {
            PointF[] pointFArr8 = cornerData12.bezierAnchorVertical;
            PointF pointF26 = pointFArr8[1];
            float f31 = pointF26.x;
            float f32 = pointF26.y;
            PointF pointF27 = pointFArr8[2];
            float f33 = pointF27.x;
            float f34 = pointF27.y;
            PointF pointF28 = pointFArr8[3];
            path.cubicTo(f31, f32, f33, f34, pointF28.x, pointF28.y);
        }
        path.close();
        return path;
    }

    public void buildSmoothData(int i2, int i3, float[] fArr, double d2) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        this.mAllData = new SmoothData(i2, i3, d2);
        if (fArr == null) {
            return;
        }
        float[] fArr2 = new float[8];
        fArr2[0] = 0.0f;
        fArr2[1] = 0.0f;
        fArr2[2] = 0.0f;
        fArr2[3] = 0.0f;
        fArr2[4] = 0.0f;
        fArr2[5] = 0.0f;
        fArr2[6] = 0.0f;
        fArr2[7] = 0.0f;
        for (int i4 = 0; i4 < Math.min(8, fArr.length); i4++) {
            fArr2[i4] = fArr[i4];
        }
        float f7 = fArr2[0];
        float f8 = fArr2[1];
        float f9 = fArr2[2];
        float f10 = fArr2[3];
        float f11 = fArr2[4];
        float f12 = fArr2[5];
        float f13 = fArr2[6];
        float f14 = fArr2[7];
        float f15 = i2;
        if (f7 + f9 > f15) {
            float f16 = (f15 * f7) / (f7 + f9);
            f9 = (f15 * f9) / (f7 + f9);
            f7 = f16;
        }
        float f17 = f9;
        float f18 = i3;
        if (f10 + f12 > f18) {
            float f19 = (f18 * f10) / (f10 + f12);
            f3 = (f18 * f12) / (f10 + f12);
            f2 = f19;
        } else {
            f2 = f10;
            f3 = f12;
        }
        if (f11 + f13 > f15) {
            float f20 = (f15 * f11) / (f11 + f13);
            f4 = (f15 * f13) / (f11 + f13);
            f5 = f20;
        } else {
            f4 = f13;
            f5 = f11;
        }
        if (f14 + f8 > f18) {
            float f21 = (f18 * f14) / (f14 + f8);
            f8 = (f18 * f8) / (f14 + f8);
            f6 = f21;
        } else {
            f6 = f14;
        }
        ensureFourCornerData();
        this.mAllData.topLeft.build(Math.min(f7, f8), i2, i3, d2, 0);
        this.mAllData.topRight.build(Math.min(f17, f2), i2, i3, d2, 1);
        this.mAllData.bottomRight.build(Math.min(f5, f3), i2, i3, d2, 2);
        this.mAllData.bottomLeft.build(Math.min(f4, f6), i2, i3, d2, 3);
    }
}
