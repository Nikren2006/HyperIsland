package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import com.xiaomi.onetrack.util.aa;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class WidgetGroup {
    private static final boolean DEBUG = false;
    static int sCount;
    int mId;
    int mOrientation;
    ArrayList<ConstraintWidget> mWidgets = new ArrayList<>();
    boolean mAuthoritative = false;
    ArrayList<MeasureResult> mResults = null;
    private int mMoveTo = -1;

    public static class MeasureResult {
        int mBaseline;
        int mBottom;
        int mLeft;
        int mOrientation;
        int mRight;
        int mTop;
        WeakReference<ConstraintWidget> mWidgetRef;

        public MeasureResult(ConstraintWidget constraintWidget, LinearSystem linearSystem, int i2) {
            this.mWidgetRef = new WeakReference<>(constraintWidget);
            this.mLeft = linearSystem.getObjectVariableValue(constraintWidget.mLeft);
            this.mTop = linearSystem.getObjectVariableValue(constraintWidget.mTop);
            this.mRight = linearSystem.getObjectVariableValue(constraintWidget.mRight);
            this.mBottom = linearSystem.getObjectVariableValue(constraintWidget.mBottom);
            this.mBaseline = linearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.mOrientation = i2;
        }

        public void apply() {
            ConstraintWidget constraintWidget = this.mWidgetRef.get();
            if (constraintWidget != null) {
                constraintWidget.setFinalFrame(this.mLeft, this.mTop, this.mRight, this.mBottom, this.mBaseline, this.mOrientation);
            }
        }
    }

    public WidgetGroup(int i2) {
        int i3 = sCount;
        sCount = i3 + 1;
        this.mId = i3;
        this.mOrientation = i2;
    }

    private boolean contains(ConstraintWidget constraintWidget) {
        return this.mWidgets.contains(constraintWidget);
    }

    private String getOrientationString() {
        int i2 = this.mOrientation;
        return i2 == 0 ? "Horizontal" : i2 == 1 ? "Vertical" : i2 == 2 ? "Both" : "Unknown";
    }

    private int measureWrap(int i2, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(i2);
        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            return i2 == 0 ? constraintWidget.getWidth() : constraintWidget.getHeight();
        }
        return -1;
    }

    private int solverMeasure(LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        int objectVariableValue;
        int objectVariableValue2;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) arrayList.get(0).getParent();
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList.get(i3).addToSolver(linearSystem, false);
        }
        if (i2 == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i2 == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e2) {
            System.err.println(e2.toString() + "\n" + Arrays.toString(e2.getStackTrace()).replace("[", "   at ").replace(aa.f3429b, "\n   at").replace("]", ""));
        }
        this.mResults = new ArrayList<>();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            this.mResults.add(new MeasureResult(arrayList.get(i4), linearSystem, i2));
        }
        if (i2 == 0) {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mRight);
            linearSystem.reset();
        } else {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mBottom);
            linearSystem.reset();
        }
        return objectVariableValue2 - objectVariableValue;
    }

    public boolean add(ConstraintWidget constraintWidget) {
        if (this.mWidgets.contains(constraintWidget)) {
            return false;
        }
        this.mWidgets.add(constraintWidget);
        return true;
    }

    public void apply() {
        if (this.mResults != null && this.mAuthoritative) {
            for (int i2 = 0; i2 < this.mResults.size(); i2++) {
                this.mResults.get(i2).apply();
            }
        }
    }

    public void cleanup(ArrayList<WidgetGroup> arrayList) {
        int size = this.mWidgets.size();
        if (this.mMoveTo != -1 && size > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                WidgetGroup widgetGroup = arrayList.get(i2);
                if (this.mMoveTo == widgetGroup.mId) {
                    moveTo(this.mOrientation, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public void clear() {
        this.mWidgets.clear();
    }

    public int getId() {
        return this.mId;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean intersectWith(WidgetGroup widgetGroup) {
        for (int i2 = 0; i2 < this.mWidgets.size(); i2++) {
            if (widgetGroup.contains(this.mWidgets.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthoritative() {
        return this.mAuthoritative;
    }

    public void moveTo(int i2, WidgetGroup widgetGroup) {
        for (ConstraintWidget constraintWidget : this.mWidgets) {
            widgetGroup.add(constraintWidget);
            if (i2 == 0) {
                constraintWidget.horizontalGroup = widgetGroup.getId();
            } else {
                constraintWidget.verticalGroup = widgetGroup.getId();
            }
        }
        this.mMoveTo = widgetGroup.mId;
    }

    public void setAuthoritative(boolean z2) {
        this.mAuthoritative = z2;
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
    }

    public int size() {
        return this.mWidgets.size();
    }

    public String toString() {
        String str = getOrientationString() + " [" + this.mId + "] <";
        Iterator<ConstraintWidget> it = this.mWidgets.iterator();
        while (it.hasNext()) {
            str = str + " " + it.next().getDebugName();
        }
        return str + " >";
    }

    public int measureWrap(LinearSystem linearSystem, int i2) {
        if (this.mWidgets.size() == 0) {
            return 0;
        }
        return solverMeasure(linearSystem, this.mWidgets, i2);
    }
}
