package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.xiaomi.onetrack.util.aa;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewSpline extends SplineSet {
    private static final String TAG = "ViewSpline";

    public static class AlphaSet extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setAlpha(get(f2));
        }
    }

    public static class CustomSet extends ViewSpline {
        String mAttributeName;
        SparseArray<ConstraintAttribute> mConstraintAttributeList;
        float[] mTempValues;

        public CustomSet(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.mAttributeName = str.split(aa.f3429b)[1];
            this.mConstraintAttributeList = sparseArray;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int i2, float f2) {
            throw new RuntimeException("call of custom attribute setPoint");
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            this.mCurveFit.getPos(f2, this.mTempValues);
            CustomSupport.setInterpolatedValue(this.mConstraintAttributeList.valueAt(0), view, this.mTempValues);
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int i2) {
            int size = this.mConstraintAttributeList.size();
            int iNumberOfInterpolatedValues = this.mConstraintAttributeList.valueAt(0).numberOfInterpolatedValues();
            double[] dArr = new double[size];
            this.mTempValues = new float[iNumberOfInterpolatedValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, iNumberOfInterpolatedValues);
            for (int i3 = 0; i3 < size; i3++) {
                int iKeyAt = this.mConstraintAttributeList.keyAt(i3);
                ConstraintAttribute constraintAttributeValueAt = this.mConstraintAttributeList.valueAt(i3);
                dArr[i3] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.getValuesToInterpolate(this.mTempValues);
                int i4 = 0;
                while (true) {
                    if (i4 < this.mTempValues.length) {
                        dArr2[i3][i4] = r6[i4];
                        i4++;
                    }
                }
            }
            this.mCurveFit = CurveFit.get(i2, dArr, dArr2);
        }

        public void setPoint(int i2, ConstraintAttribute constraintAttribute) {
            this.mConstraintAttributeList.append(i2, constraintAttribute);
        }
    }

    public static class ElevationSet extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setElevation(get(f2));
        }
    }

    public static class PathRotate extends ViewSpline {
        public void setPathRotate(View view, float f2, double d2, double d3) {
            view.setRotation(get(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
        }
    }

    public static class PivotXset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setPivotX(get(f2));
        }
    }

    public static class PivotYset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setPivotY(get(f2));
        }
    }

    public static class ProgressSet extends ViewSpline {
        boolean mNoMethod = false;

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f2));
                return;
            }
            if (this.mNoMethod) {
                return;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.mNoMethod = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(get(f2)));
                } catch (IllegalAccessException e2) {
                    Log.e(ViewSpline.TAG, "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e(ViewSpline.TAG, "unable to setProgress", e3);
                }
            }
        }
    }

    public static class RotationSet extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setRotation(get(f2));
        }
    }

    public static class RotationXset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setRotationX(get(f2));
        }
    }

    public static class RotationYset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setRotationY(get(f2));
        }
    }

    public static class ScaleXset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setScaleX(get(f2));
        }
    }

    public static class ScaleYset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setScaleY(get(f2));
        }
    }

    public static class TranslationXset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setTranslationX(get(f2));
        }
    }

    public static class TranslationYset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setTranslationY(get(f2));
        }
    }

    public static class TranslationZset extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view, float f2) {
            view.setTranslationZ(get(f2));
        }
    }

    public static ViewSpline makeCustomSpline(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new CustomSet(str, sparseArray);
    }

    public static ViewSpline makeSpline(String str) {
        str.hashCode();
        switch (str) {
        }
        return new AlphaSet();
    }

    public abstract void setProperty(View view, float f2);
}
