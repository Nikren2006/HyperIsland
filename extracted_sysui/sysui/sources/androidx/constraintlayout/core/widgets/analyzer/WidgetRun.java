package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* JADX INFO: loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {
    protected ConstraintWidget.DimensionBehaviour mDimensionBehavior;
    RunGroup mRunGroup;
    ConstraintWidget mWidget;
    public int matchConstraintsType;
    DimensionDependency mDimension = new DimensionDependency(this);
    public int orientation = 0;
    boolean mResolved = false;
    public DependencyNode start = new DependencyNode(this);
    public DependencyNode end = new DependencyNode(this);
    protected RunType mRunType = RunType.NONE;

    /* JADX INFO: renamed from: androidx.constraintlayout.core.widgets.analyzer.WidgetRun$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.mWidget = constraintWidget;
    }

    private void resolveDimension(int i2, int i3) {
        int i4 = this.matchConstraintsType;
        if (i4 == 0) {
            this.mDimension.resolve(getLimitedDimension(i3, i2));
            return;
        }
        if (i4 == 1) {
            this.mDimension.resolve(Math.min(getLimitedDimension(this.mDimension.wrapValue, i2), i3));
            return;
        }
        if (i4 == 2) {
            ConstraintWidget parent = this.mWidget.getParent();
            if (parent != null) {
                if ((i2 == 0 ? parent.mHorizontalRun : parent.mVerticalRun).mDimension.resolved) {
                    this.mDimension.resolve(getLimitedDimension((int) ((r9.value * (i2 == 0 ? this.mWidget.mMatchConstraintPercentWidth : this.mWidget.mMatchConstraintPercentHeight)) + 0.5f), i2));
                    return;
                }
                return;
            }
            return;
        }
        if (i4 != 3) {
            return;
        }
        ConstraintWidget constraintWidget = this.mWidget;
        WidgetRun widgetRun = constraintWidget.mHorizontalRun;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.mDimensionBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.matchConstraintsType == 3) {
            VerticalWidgetRun verticalWidgetRun = constraintWidget.mVerticalRun;
            if (verticalWidgetRun.mDimensionBehavior == dimensionBehaviour2 && verticalWidgetRun.matchConstraintsType == 3) {
                return;
            }
        }
        if (i2 == 0) {
            widgetRun = constraintWidget.mVerticalRun;
        }
        if (widgetRun.mDimension.resolved) {
            float dimensionRatio = constraintWidget.getDimensionRatio();
            this.mDimension.resolve(i2 == 1 ? (int) ((widgetRun.mDimension.value / dimensionRatio) + 0.5f) : (int) ((dimensionRatio * widgetRun.mDimension.value) + 0.5f));
        }
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.mTargets.add(dependencyNode2);
        dependencyNode.mMargin = i2;
        dependencyNode2.mDependencies.add(dependencyNode);
    }

    public abstract void apply();

    public abstract void applyToWidget();

    public abstract void clear();

    public final int getLimitedDimension(int i2, int i3) {
        int iMax;
        if (i3 == 0) {
            ConstraintWidget constraintWidget = this.mWidget;
            int i4 = constraintWidget.mMatchConstraintMaxWidth;
            iMax = Math.max(constraintWidget.mMatchConstraintMinWidth, i2);
            if (i4 > 0) {
                iMax = Math.min(i4, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.mWidget;
            int i5 = constraintWidget2.mMatchConstraintMaxHeight;
            iMax = Math.max(constraintWidget2.mMatchConstraintMinHeight, i2);
            if (i5 > 0) {
                iMax = Math.min(i5, i2);
            }
            if (iMax == i2) {
                return i2;
            }
        }
        return iMax;
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[constraintAnchor2.mType.ordinal()];
        if (i2 == 1) {
            return constraintWidget.mHorizontalRun.start;
        }
        if (i2 == 2) {
            return constraintWidget.mHorizontalRun.end;
        }
        if (i2 == 3) {
            return constraintWidget.mVerticalRun.start;
        }
        if (i2 == 4) {
            return constraintWidget.mVerticalRun.baseline;
        }
        if (i2 != 5) {
            return null;
        }
        return constraintWidget.mVerticalRun.end;
    }

    public long getWrapDimension() {
        if (this.mDimension.resolved) {
            return r2.value;
        }
        return 0L;
    }

    public boolean isCenterConnection() {
        int size = this.start.mTargets.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (this.start.mTargets.get(i3).mRun != this) {
                i2++;
            }
        }
        int size2 = this.end.mTargets.size();
        for (int i4 = 0; i4 < size2; i4++) {
            if (this.end.mTargets.get(i4).mRun != this) {
                i2++;
            }
        }
        return i2 >= 2;
    }

    public boolean isDimensionResolved() {
        return this.mDimension.resolved;
    }

    public boolean isResolved() {
        return this.mResolved;
    }

    public abstract void reset();

    public abstract boolean supportsWrapComputation();

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
    }

    public void updateRunCenter(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        DependencyNode target = getTarget(constraintAnchor);
        DependencyNode target2 = getTarget(constraintAnchor2);
        if (target.resolved && target2.resolved) {
            int margin = target.value + constraintAnchor.getMargin();
            int margin2 = target2.value - constraintAnchor2.getMargin();
            int i3 = margin2 - margin;
            if (!this.mDimension.resolved && this.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                resolveDimension(i2, i3);
            }
            DimensionDependency dimensionDependency = this.mDimension;
            if (dimensionDependency.resolved) {
                if (dimensionDependency.value == i3) {
                    this.start.resolve(margin);
                    this.end.resolve(margin2);
                    return;
                }
                float horizontalBiasPercent = i2 == 0 ? this.mWidget.getHorizontalBiasPercent() : this.mWidget.getVerticalBiasPercent();
                if (target == target2) {
                    margin = target.value;
                    margin2 = target2.value;
                    horizontalBiasPercent = 0.5f;
                }
                this.start.resolve((int) (margin + 0.5f + (((margin2 - margin) - this.mDimension.value) * horizontalBiasPercent)));
                this.end.resolve(this.start.value + this.mDimension.value);
            }
        }
    }

    public void updateRunEnd(Dependency dependency) {
    }

    public void updateRunStart(Dependency dependency) {
    }

    public long wrapSize(int i2) {
        long j2;
        DimensionDependency dimensionDependency = this.mDimension;
        if (!dimensionDependency.resolved) {
            return 0L;
        }
        long j3 = dimensionDependency.value;
        if (isCenterConnection()) {
            j2 = this.start.mMargin - this.end.mMargin;
        } else {
            if (i2 != 0) {
                return j3 - ((long) this.end.mMargin);
            }
            j2 = this.start.mMargin;
        }
        return j3 + j2;
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, DimensionDependency dimensionDependency) {
        dependencyNode.mTargets.add(dependencyNode2);
        dependencyNode.mTargets.add(this.mDimension);
        dependencyNode.mMarginFactor = i2;
        dependencyNode.mMarginDependency = dimensionDependency;
        dependencyNode2.mDependencies.add(dependencyNode);
        dimensionDependency.mDependencies.add(dependencyNode);
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.mHorizontalRun : constraintWidget.mVerticalRun;
        int i3 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[constraintAnchor2.mType.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.end;
        }
        return widgetRun.start;
    }
}
