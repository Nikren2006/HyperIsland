package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CircularFlow extends VirtualLayout {
    private static final String TAG = "CircularFlow";
    private static float sDefaultAngle;
    private static int sDefaultRadius;
    private float[] mAngles;
    ConstraintLayout mContainer;
    private int mCountAngle;
    private int mCountRadius;
    private int[] mRadius;
    private String mReferenceAngles;
    private Float mReferenceDefaultAngle;
    private Integer mReferenceDefaultRadius;
    private String mReferenceRadius;
    int mViewCenter;

    public CircularFlow(Context context) {
        super(context);
    }

    private void addAngle(String str) {
        float[] fArr;
        if (str == null || str.length() == 0 || this.myContext == null || (fArr = this.mAngles) == null) {
            return;
        }
        if (this.mCountAngle + 1 > fArr.length) {
            this.mAngles = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.mAngles[this.mCountAngle] = Integer.parseInt(str);
        this.mCountAngle++;
    }

    private void addRadius(String str) {
        int[] iArr;
        if (str == null || str.length() == 0 || this.myContext == null || (iArr = this.mRadius) == null) {
            return;
        }
        if (this.mCountRadius + 1 > iArr.length) {
            this.mRadius = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.mRadius[this.mCountRadius] = (int) (Integer.parseInt(str) * this.myContext.getResources().getDisplayMetrics().density);
        this.mCountRadius++;
    }

    private void anchorReferences() {
        this.mContainer = (ConstraintLayout) getParent();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            View viewById = this.mContainer.getViewById(this.mIds[i2]);
            if (viewById != null) {
                int i3 = sDefaultRadius;
                float f2 = sDefaultAngle;
                int[] iArr = this.mRadius;
                if (iArr == null || i2 >= iArr.length) {
                    Integer num = this.mReferenceDefaultRadius;
                    if (num == null || num.intValue() == -1) {
                        Log.e(TAG, "Added radius to view with id: " + this.mMap.get(Integer.valueOf(viewById.getId())));
                    } else {
                        this.mCountRadius++;
                        if (this.mRadius == null) {
                            this.mRadius = new int[1];
                        }
                        int[] radius = getRadius();
                        this.mRadius = radius;
                        radius[this.mCountRadius - 1] = i3;
                    }
                } else {
                    i3 = iArr[i2];
                }
                float[] fArr = this.mAngles;
                if (fArr == null || i2 >= fArr.length) {
                    Float f3 = this.mReferenceDefaultAngle;
                    if (f3 == null || f3.floatValue() == -1.0f) {
                        Log.e(TAG, "Added angle to view with id: " + this.mMap.get(Integer.valueOf(viewById.getId())));
                    } else {
                        this.mCountAngle++;
                        if (this.mAngles == null) {
                            this.mAngles = new float[1];
                        }
                        float[] angles = getAngles();
                        this.mAngles = angles;
                        angles[this.mCountAngle - 1] = f2;
                    }
                } else {
                    f2 = fArr[i2];
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewById.getLayoutParams();
                layoutParams.circleAngle = f2;
                layoutParams.circleConstraint = this.mViewCenter;
                layoutParams.circleRadius = i3;
                viewById.setLayoutParams(layoutParams);
            }
        }
        applyLayoutFeatures();
    }

    private float[] removeAngle(float[] fArr, int i2) {
        return (fArr == null || i2 < 0 || i2 >= this.mCountAngle) ? fArr : removeElementFromArray(fArr, i2);
    }

    private static int[] removeElementFromArray(int[] iArr, int i2) {
        int[] iArr2 = new int[iArr.length - 1];
        int i3 = 0;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (i4 != i2) {
                iArr2[i3] = iArr[i4];
                i3++;
            }
        }
        return iArr2;
    }

    private int[] removeRadius(int[] iArr, int i2) {
        return (iArr == null || i2 < 0 || i2 >= this.mCountRadius) ? iArr : removeElementFromArray(iArr, i2);
    }

    private void setAngles(String str) {
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.mCountAngle = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i2);
            if (iIndexOf == -1) {
                addAngle(str.substring(i2).trim());
                return;
            } else {
                addAngle(str.substring(i2, iIndexOf).trim());
                i2 = iIndexOf + 1;
            }
        }
    }

    private void setRadius(String str) {
        if (str == null) {
            return;
        }
        int i2 = 0;
        this.mCountRadius = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i2);
            if (iIndexOf == -1) {
                addRadius(str.substring(i2).trim());
                return;
            } else {
                addRadius(str.substring(i2, iIndexOf).trim());
                i2 = iIndexOf + 1;
            }
        }
    }

    public void addViewToCircularFlow(View view, int i2, float f2) {
        if (containsId(view.getId())) {
            return;
        }
        addView(view);
        this.mCountAngle++;
        float[] angles = getAngles();
        this.mAngles = angles;
        angles[this.mCountAngle - 1] = f2;
        this.mCountRadius++;
        int[] radius = getRadius();
        this.mRadius = radius;
        radius[this.mCountRadius - 1] = (int) (i2 * this.myContext.getResources().getDisplayMetrics().density);
        anchorReferences();
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.mAngles, this.mCountAngle);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.mRadius, this.mCountRadius);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.mViewCenter = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_angles) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.mReferenceAngles = string;
                    setAngles(string);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.mReferenceRadius = string2;
                    setRadius(string2);
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    Float fValueOf = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, sDefaultAngle));
                    this.mReferenceDefaultAngle = fValueOf;
                    setDefaultAngle(fValueOf.floatValue());
                } else if (index == R.styleable.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    Integer numValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, sDefaultRadius));
                    this.mReferenceDefaultRadius = numValueOf;
                    setDefaultRadius(numValueOf.intValue());
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public boolean isUpdatable(View view) {
        return containsId(view.getId()) && indexFromId(view.getId()) != -1;
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceAngles;
        if (str != null) {
            this.mAngles = new float[1];
            setAngles(str);
        }
        String str2 = this.mReferenceRadius;
        if (str2 != null) {
            this.mRadius = new int[1];
            setRadius(str2);
        }
        Float f2 = this.mReferenceDefaultAngle;
        if (f2 != null) {
            setDefaultAngle(f2.floatValue());
        }
        Integer num = this.mReferenceDefaultRadius;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        anchorReferences();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public int removeView(View view) {
        int iRemoveView = super.removeView(view);
        if (iRemoveView == -1) {
            return iRemoveView;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mContainer);
        constraintSet.clear(view.getId(), 8);
        constraintSet.applyTo(this.mContainer);
        float[] fArr = this.mAngles;
        if (iRemoveView < fArr.length) {
            this.mAngles = removeAngle(fArr, iRemoveView);
            this.mCountAngle--;
        }
        int[] iArr = this.mRadius;
        if (iRemoveView < iArr.length) {
            this.mRadius = removeRadius(iArr, iRemoveView);
            this.mCountRadius--;
        }
        anchorReferences();
        return iRemoveView;
    }

    public void setDefaultAngle(float f2) {
        sDefaultAngle = f2;
    }

    public void setDefaultRadius(int i2) {
        sDefaultRadius = i2;
    }

    public void updateAngle(View view, float f2) {
        if (!isUpdatable(view)) {
            Log.e(TAG, "It was not possible to update angle to view with id: " + view.getId());
            return;
        }
        int iIndexFromId = indexFromId(view.getId());
        if (iIndexFromId > this.mAngles.length) {
            return;
        }
        float[] angles = getAngles();
        this.mAngles = angles;
        angles[iIndexFromId] = f2;
        anchorReferences();
    }

    public void updateRadius(View view, int i2) {
        if (!isUpdatable(view)) {
            Log.e(TAG, "It was not possible to update radius to view with id: " + view.getId());
            return;
        }
        int iIndexFromId = indexFromId(view.getId());
        if (iIndexFromId > this.mRadius.length) {
            return;
        }
        int[] radius = getRadius();
        this.mRadius = radius;
        radius[iIndexFromId] = (int) (i2 * this.myContext.getResources().getDisplayMetrics().density);
        anchorReferences();
    }

    public void updateReference(View view, int i2, float f2) {
        if (!isUpdatable(view)) {
            Log.e(TAG, "It was not possible to update radius and angle to view with id: " + view.getId());
            return;
        }
        int iIndexFromId = indexFromId(view.getId());
        if (getAngles().length > iIndexFromId) {
            float[] angles = getAngles();
            this.mAngles = angles;
            angles[iIndexFromId] = f2;
        }
        if (getRadius().length > iIndexFromId) {
            int[] radius = getRadius();
            this.mRadius = radius;
            radius[iIndexFromId] = (int) (i2 * this.myContext.getResources().getDisplayMetrics().density);
        }
        anchorReferences();
    }

    public CircularFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularFlow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    private static float[] removeElementFromArray(float[] fArr, int i2) {
        float[] fArr2 = new float[fArr.length - 1];
        int i3 = 0;
        for (int i4 = 0; i4 < fArr.length; i4++) {
            if (i4 != i2) {
                fArr2[i3] = fArr[i4];
                i3++;
            }
        }
        return fArr2;
    }
}
