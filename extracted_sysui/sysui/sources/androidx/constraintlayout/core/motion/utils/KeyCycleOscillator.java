package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class KeyCycleOscillator {
    private static final String TAG = "KeyCycleOscillator";
    private CurveFit mCurveFit;
    private CycleOscillator mCycleOscillator;
    private String mType;
    private int mWaveShape = 0;
    private String mWaveString = null;
    public int mVariesBy = 0;
    ArrayList<WavePoint> mWavePoints = new ArrayList<>();

    public static class CoreSpline extends KeyCycleOscillator {
        String mType;
        int mTypeId;

        public CoreSpline(String str) {
            this.mType = str;
            this.mTypeId = TypedValues.CycleType.getId(str);
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget, float f2) {
            motionWidget.setValue(this.mTypeId, get(f2));
        }
    }

    public static class CycleOscillator {
        private static final String TAG = "CycleOscillator";
        static final int UNSET = -1;
        CurveFit mCurveFit;
        float[] mOffsetArr;
        private final int mOffst;
        Oscillator mOscillator;
        float mPathLength;
        float[] mPeriod;
        private final int mPhase;
        float[] mPhaseArr;
        double[] mPosition;
        float[] mScale;
        double[] mSplineSlopeCache;
        double[] mSplineValueCache;
        private final int mValue;
        float[] mValues;
        private final int mVariesBy;
        int mWaveShape;

        public CycleOscillator(int i2, String str, int i3, int i4) {
            Oscillator oscillator = new Oscillator();
            this.mOscillator = oscillator;
            this.mOffst = 0;
            this.mPhase = 1;
            this.mValue = 2;
            this.mWaveShape = i2;
            this.mVariesBy = i3;
            oscillator.setType(i2, str);
            this.mValues = new float[i4];
            this.mPosition = new double[i4];
            this.mPeriod = new float[i4];
            this.mOffsetArr = new float[i4];
            this.mPhaseArr = new float[i4];
            this.mScale = new float[i4];
        }

        public double getLastPhase() {
            return this.mSplineValueCache[1];
        }

        public double getSlope(float f2) {
            CurveFit curveFit = this.mCurveFit;
            if (curveFit != null) {
                double d2 = f2;
                curveFit.getSlope(d2, this.mSplineSlopeCache);
                this.mCurveFit.getPos(d2, this.mSplineValueCache);
            } else {
                double[] dArr = this.mSplineSlopeCache;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 0.0d;
            }
            double d3 = f2;
            double value = this.mOscillator.getValue(d3, this.mSplineValueCache[1]);
            double slope = this.mOscillator.getSlope(d3, this.mSplineValueCache[1], this.mSplineSlopeCache[1]);
            double[] dArr2 = this.mSplineSlopeCache;
            return dArr2[0] + (value * dArr2[2]) + (slope * this.mSplineValueCache[2]);
        }

        public double getValues(float f2) {
            CurveFit curveFit = this.mCurveFit;
            if (curveFit != null) {
                curveFit.getPos(f2, this.mSplineValueCache);
            } else {
                double[] dArr = this.mSplineValueCache;
                dArr[0] = this.mOffsetArr[0];
                dArr[1] = this.mPhaseArr[0];
                dArr[2] = this.mValues[0];
            }
            double[] dArr2 = this.mSplineValueCache;
            return dArr2[0] + (this.mOscillator.getValue(f2, dArr2[1]) * this.mSplineValueCache[2]);
        }

        public void setPoint(int i2, int i3, float f2, float f3, float f4, float f5) {
            this.mPosition[i2] = ((double) i3) / 100.0d;
            this.mPeriod[i2] = f2;
            this.mOffsetArr[i2] = f3;
            this.mPhaseArr[i2] = f4;
            this.mValues[i2] = f5;
        }

        public void setup(float f2) {
            this.mPathLength = f2;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, this.mPosition.length, 3);
            float[] fArr = this.mValues;
            this.mSplineValueCache = new double[fArr.length + 2];
            this.mSplineSlopeCache = new double[fArr.length + 2];
            if (this.mPosition[0] > 0.0d) {
                this.mOscillator.addPoint(0.0d, this.mPeriod[0]);
            }
            double[] dArr2 = this.mPosition;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.mOscillator.addPoint(1.0d, this.mPeriod[length]);
            }
            for (int i2 = 0; i2 < dArr.length; i2++) {
                double[] dArr3 = dArr[i2];
                dArr3[0] = this.mOffsetArr[i2];
                dArr3[1] = this.mPhaseArr[i2];
                dArr3[2] = this.mValues[i2];
                this.mOscillator.addPoint(this.mPosition[i2], this.mPeriod[i2]);
            }
            this.mOscillator.normalize();
            double[] dArr4 = this.mPosition;
            if (dArr4.length > 1) {
                this.mCurveFit = CurveFit.get(0, dArr4, dArr);
            } else {
                this.mCurveFit = null;
            }
        }
    }

    public static class PathRotateSet extends KeyCycleOscillator {
        String mType;
        int mTypeId;

        public PathRotateSet(String str) {
            this.mType = str;
            this.mTypeId = TypedValues.CycleType.getId(str);
        }

        public void setPathRotate(MotionWidget motionWidget, float f2, double d2, double d3) {
            motionWidget.setRotationZ(get(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget, float f2) {
            motionWidget.setValue(this.mTypeId, get(f2));
        }
    }

    public static class WavePoint {
        float mOffset;
        float mPeriod;
        float mPhase;
        int mPosition;
        float mValue;

        public WavePoint(int i2, float f2, float f3, float f4, float f5) {
            this.mPosition = i2;
            this.mValue = f5;
            this.mOffset = f3;
            this.mPeriod = f2;
            this.mPhase = f4;
        }
    }

    public static KeyCycleOscillator makeWidgetCycle(String str) {
        return str.equals("pathRotate") ? new PathRotateSet(str) : new CoreSpline(str);
    }

    public float get(float f2) {
        return (float) this.mCycleOscillator.getValues(f2);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f2) {
        return (float) this.mCycleOscillator.getSlope(f2);
    }

    public void setCustom(Object obj) {
    }

    public void setPoint(int i2, int i3, String str, int i4, float f2, float f3, float f4, float f5, Object obj) {
        this.mWavePoints.add(new WavePoint(i2, f2, f3, f4, f5));
        if (i4 != -1) {
            this.mVariesBy = i4;
        }
        this.mWaveShape = i3;
        setCustom(obj);
        this.mWaveString = str;
    }

    public void setProperty(MotionWidget motionWidget, float f2) {
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setup(float f2) {
        int size = this.mWavePoints.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.mWavePoints, new Comparator<WavePoint>() { // from class: androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.1
            @Override // java.util.Comparator
            public int compare(WavePoint wavePoint, WavePoint wavePoint2) {
                return Integer.compare(wavePoint.mPosition, wavePoint2.mPosition);
            }
        });
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 3);
        this.mCycleOscillator = new CycleOscillator(this.mWaveShape, this.mWaveString, this.mVariesBy, size);
        int i2 = 0;
        for (WavePoint wavePoint : this.mWavePoints) {
            float f3 = wavePoint.mPeriod;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = wavePoint.mValue;
            dArr3[0] = f4;
            float f5 = wavePoint.mOffset;
            dArr3[1] = f5;
            float f6 = wavePoint.mPhase;
            dArr3[2] = f6;
            this.mCycleOscillator.setPoint(i2, wavePoint.mPosition, f3, f5, f6, f4);
            i2++;
            dArr2 = dArr2;
        }
        this.mCycleOscillator.setup(f2);
        this.mCurveFit = CurveFit.get(0, dArr, dArr2);
    }

    public String toString() {
        String str = this.mType;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<WavePoint> it = this.mWavePoints.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().mPosition + " , " + decimalFormat.format(r2.mValue) + "] ";
        }
        return str;
    }

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }

    public void setPoint(int i2, int i3, String str, int i4, float f2, float f3, float f4, float f5) {
        this.mWavePoints.add(new WavePoint(i2, f2, f3, f4, f5));
        if (i4 != -1) {
            this.mVariesBy = i4;
        }
        this.mWaveShape = i3;
        this.mWaveString = str;
    }
}
