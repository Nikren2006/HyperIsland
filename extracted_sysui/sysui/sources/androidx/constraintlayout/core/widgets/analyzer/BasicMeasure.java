package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    private static final boolean DO_NOT_USE = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer mConstraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mConstraintWidgetContainer = constraintWidgetContainer;
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, int i2) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = dimensionBehaviour == dimensionBehaviour2;
        boolean z3 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        boolean z5 = z3 && constraintWidget.mDimensionRatio > 0.0f;
        if (z4 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z5 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure measure2 = this.mMeasure;
        measure2.measureStrategy = Measure.SELF_DIMENSIONS;
        return measure2.measuredNeedsSolverPass;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0098 A[PHI: r10
      0x0098: PHI (r10v2 boolean) = (r10v1 boolean), (r10v1 boolean), (r10v1 boolean), (r10v4 boolean), (r10v4 boolean) binds: [B:32:0x0062, B:34:0x0068, B:36:0x006c, B:54:0x0095, B:52:0x008e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ac A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void measureChildren(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r13) {
        /*
            r12 = this;
            java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r0 = r13.mChildren
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.optimizeFor(r1)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r13.getMeasurer()
            r3 = 0
            r4 = r3
        L12:
            if (r4 >= r0) goto Lb0
            java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r5 = r13.mChildren
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r6 == 0) goto L22
            goto Lac
        L22:
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r6 == 0) goto L28
            goto Lac
        L28:
            boolean r6 = r5.isInVirtualLayout()
            if (r6 == 0) goto L30
            goto Lac
        L30:
            if (r1 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r6 = r5.mHorizontalRun
            if (r6 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r7 = r5.mVerticalRun
            if (r7 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r6.mDimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r7.mDimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L48
            goto Lac
        L48:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.getDimensionBehaviour(r3)
            r7 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getDimensionBehaviour(r7)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L61
            int r10 = r5.mMatchConstraintDefaultWidth
            if (r10 == r7) goto L61
            if (r8 != r9) goto L61
            int r10 = r5.mMatchConstraintDefaultHeight
            if (r10 == r7) goto L61
            r10 = r7
            goto L62
        L61:
            r10 = r3
        L62:
            if (r10 != 0) goto L98
            boolean r11 = r13.optimizeFor(r7)
            if (r11 == 0) goto L98
            boolean r11 = r5 instanceof androidx.constraintlayout.core.widgets.VirtualLayout
            if (r11 != 0) goto L98
            if (r6 != r9) goto L7d
            int r11 = r5.mMatchConstraintDefaultWidth
            if (r11 != 0) goto L7d
            if (r8 == r9) goto L7d
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L7d
            r10 = r7
        L7d:
            if (r8 != r9) goto L8c
            int r11 = r5.mMatchConstraintDefaultHeight
            if (r11 != 0) goto L8c
            if (r6 == r9) goto L8c
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L8c
            r10 = r7
        L8c:
            if (r6 == r9) goto L90
            if (r8 != r9) goto L98
        L90:
            float r6 = r5.mDimensionRatio
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L98
            goto L99
        L98:
            r7 = r10
        L99:
            if (r7 == 0) goto L9c
            goto Lac
        L9c:
            int r6 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r12.measure(r2, r5, r6)
            androidx.constraintlayout.core.Metrics r5 = r13.mMetrics
            if (r5 == 0) goto Lac
            long r6 = r5.measuredWidgets
            r8 = 1
            long r6 = r6 + r8
            r5.measuredWidgets = r6
        Lac:
            int r4 = r4 + 1
            goto L12
        Lb0:
            r2.didMeasures()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.measureChildren(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer):void");
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer, String str, int i2, int i3, int i4) {
        long jNanoTime = constraintWidgetContainer.mMetrics != null ? System.nanoTime() : 0L;
        int minWidth = constraintWidgetContainer.getMinWidth();
        int minHeight = constraintWidgetContainer.getMinHeight();
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setWidth(i3);
        constraintWidgetContainer.setHeight(i4);
        constraintWidgetContainer.setMinWidth(minWidth);
        constraintWidgetContainer.setMinHeight(minHeight);
        this.mConstraintWidgetContainer.setPass(i2);
        this.mConstraintWidgetContainer.layout();
        if (constraintWidgetContainer.mMetrics != null) {
            long jNanoTime2 = System.nanoTime();
            Metrics metrics = constraintWidgetContainer.mMetrics;
            metrics.mSolverPasses++;
            metrics.measuresLayoutDuration += jNanoTime2 - jNanoTime;
        }
    }

    public long solverMeasure(ConstraintWidgetContainer constraintWidgetContainer, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        boolean zDirectMeasureWithOrientation;
        int i11;
        long j2;
        int i12;
        boolean z2;
        boolean z3;
        int i13;
        int i14;
        int i15;
        boolean z4;
        Metrics metrics;
        BasicMeasure basicMeasure = this;
        Measurer measurer = constraintWidgetContainer.getMeasurer();
        int size = constraintWidgetContainer.mChildren.size();
        int width = constraintWidgetContainer.getWidth();
        int height = constraintWidgetContainer.getHeight();
        boolean zEnabled = Optimizer.enabled(i2, 128);
        boolean z5 = zEnabled || Optimizer.enabled(i2, 64);
        if (z5) {
            for (int i16 = 0; i16 < size; i16++) {
                ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i16);
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z6 = (horizontalDimensionBehaviour == dimensionBehaviour) && (constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((constraintWidget.isInHorizontalChain() && z6) || ((constraintWidget.isInVerticalChain() && z6) || (constraintWidget instanceof VirtualLayout) || constraintWidget.isInHorizontalChain() || constraintWidget.isInVerticalChain())) {
                    z5 = false;
                    break;
                }
            }
        }
        if (z5 && (metrics = LinearSystem.sMetrics) != null) {
            metrics.measures++;
        }
        boolean z7 = z5 & ((i5 == 1073741824 && i7 == 1073741824) || zEnabled);
        int i17 = 2;
        if (z7) {
            int iMin = Math.min(constraintWidgetContainer.getMaxWidth(), i6);
            int iMin2 = Math.min(constraintWidgetContainer.getMaxHeight(), i8);
            if (i5 == 1073741824 && constraintWidgetContainer.getWidth() != iMin) {
                constraintWidgetContainer.setWidth(iMin);
                constraintWidgetContainer.invalidateGraph();
            }
            if (i7 == 1073741824 && constraintWidgetContainer.getHeight() != iMin2) {
                constraintWidgetContainer.setHeight(iMin2);
                constraintWidgetContainer.invalidateGraph();
            }
            if (i5 == 1073741824 && i7 == 1073741824) {
                zDirectMeasureWithOrientation = constraintWidgetContainer.directMeasure(zEnabled);
                i11 = 2;
            } else {
                boolean zDirectMeasureSetup = constraintWidgetContainer.directMeasureSetup(zEnabled);
                if (i5 == 1073741824) {
                    zDirectMeasureSetup &= constraintWidgetContainer.directMeasureWithOrientation(zEnabled, 0);
                    i11 = 1;
                } else {
                    i11 = 0;
                }
                if (i7 == 1073741824) {
                    zDirectMeasureWithOrientation = constraintWidgetContainer.directMeasureWithOrientation(zEnabled, 1) & zDirectMeasureSetup;
                    i11++;
                } else {
                    zDirectMeasureWithOrientation = zDirectMeasureSetup;
                }
            }
            if (zDirectMeasureWithOrientation) {
                constraintWidgetContainer.updateFromRuns(i5 == 1073741824, i7 == 1073741824);
            }
        } else {
            zDirectMeasureWithOrientation = false;
            i11 = 0;
        }
        if (!zDirectMeasureWithOrientation || i11 != 2) {
            int optimizationLevel = constraintWidgetContainer.getOptimizationLevel();
            if (size > 0) {
                measureChildren(constraintWidgetContainer);
            }
            jNanoTime = constraintWidgetContainer.mMetrics != null ? System.nanoTime() : 0L;
            updateHierarchy(constraintWidgetContainer);
            int size2 = basicMeasure.mVariableDimensionsWidgets.size();
            if (size > 0) {
                solveLinearSystem(constraintWidgetContainer, "First pass", 0, width, height);
            }
            if (size2 > 0) {
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z8 = horizontalDimensionBehaviour2 == dimensionBehaviour2;
                boolean z9 = constraintWidgetContainer.getVerticalDimensionBehaviour() == dimensionBehaviour2;
                int iMax = Math.max(constraintWidgetContainer.getWidth(), basicMeasure.mConstraintWidgetContainer.getMinWidth());
                int iMax2 = Math.max(constraintWidgetContainer.getHeight(), basicMeasure.mConstraintWidgetContainer.getMinHeight());
                int i18 = 0;
                boolean zNeedSolverPass = false;
                while (i18 < size2) {
                    ConstraintWidget constraintWidget2 = basicMeasure.mVariableDimensionsWidgets.get(i18);
                    long j3 = jNanoTime;
                    if (constraintWidget2 instanceof VirtualLayout) {
                        int width2 = constraintWidget2.getWidth();
                        int height2 = constraintWidget2.getHeight();
                        i13 = optimizationLevel;
                        boolean zMeasure = zNeedSolverPass | basicMeasure.measure(measurer, constraintWidget2, Measure.TRY_GIVEN_DIMENSIONS);
                        Metrics metrics2 = constraintWidgetContainer.mMetrics;
                        i14 = width;
                        i15 = height;
                        if (metrics2 != null) {
                            metrics2.measuredMatchWidgets++;
                        }
                        int width3 = constraintWidget2.getWidth();
                        int height3 = constraintWidget2.getHeight();
                        if (width3 != width2) {
                            constraintWidget2.setWidth(width3);
                            if (z8 && constraintWidget2.getRight() > iMax) {
                                iMax = Math.max(iMax, constraintWidget2.getRight() + constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                            }
                            z4 = true;
                        } else {
                            z4 = zMeasure;
                        }
                        if (height3 != height2) {
                            constraintWidget2.setHeight(height3);
                            if (z9 && constraintWidget2.getBottom() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget2.getBottom() + constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                            }
                            z4 = true;
                        }
                        zNeedSolverPass = z4 | ((VirtualLayout) constraintWidget2).needSolverPass();
                    } else {
                        i14 = width;
                        i15 = height;
                        i13 = optimizationLevel;
                    }
                    i18++;
                    optimizationLevel = i13;
                    jNanoTime = j3;
                    width = i14;
                    height = i15;
                    i17 = 2;
                }
                j2 = jNanoTime;
                int i19 = width;
                int i20 = height;
                int i21 = optimizationLevel;
                int i22 = i17;
                int i23 = 0;
                while (i23 < i22) {
                    int i24 = 0;
                    while (i24 < size2) {
                        ConstraintWidget constraintWidget3 = basicMeasure.mVariableDimensionsWidgets.get(i24);
                        if (((constraintWidget3 instanceof Helper) && !(constraintWidget3 instanceof VirtualLayout)) || (constraintWidget3 instanceof Guideline) || constraintWidget3.getVisibility() == 8 || ((z7 && constraintWidget3.mHorizontalRun.mDimension.resolved && constraintWidget3.mVerticalRun.mDimension.resolved) || (constraintWidget3 instanceof VirtualLayout))) {
                            z2 = z7;
                        } else {
                            int width4 = constraintWidget3.getWidth();
                            int height4 = constraintWidget3.getHeight();
                            int baselineDistance = constraintWidget3.getBaselineDistance();
                            int i25 = Measure.TRY_GIVEN_DIMENSIONS;
                            if (i23 == 1) {
                                i25 = Measure.USE_GIVEN_DIMENSIONS;
                            }
                            boolean zMeasure2 = zNeedSolverPass | basicMeasure.measure(measurer, constraintWidget3, i25);
                            Metrics metrics3 = constraintWidgetContainer.mMetrics;
                            z2 = z7;
                            if (metrics3 != null) {
                                metrics3.measuredMatchWidgets++;
                            }
                            int width5 = constraintWidget3.getWidth();
                            int height5 = constraintWidget3.getHeight();
                            if (width5 != width4) {
                                constraintWidget3.setWidth(width5);
                                if (z8 && constraintWidget3.getRight() > iMax) {
                                    iMax = Math.max(iMax, constraintWidget3.getRight() + constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                z3 = true;
                            } else {
                                z3 = zMeasure2;
                            }
                            if (height5 != height4) {
                                constraintWidget3.setHeight(height5);
                                if (z9 && constraintWidget3.getBottom() > iMax2) {
                                    iMax2 = Math.max(iMax2, constraintWidget3.getBottom() + constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                }
                                z3 = true;
                            }
                            zNeedSolverPass = (!constraintWidget3.hasBaseline() || baselineDistance == constraintWidget3.getBaselineDistance()) ? z3 : true;
                        }
                        i24++;
                        basicMeasure = this;
                        z7 = z2;
                    }
                    boolean z10 = z7;
                    if (!zNeedSolverPass) {
                        break;
                    }
                    i23++;
                    solveLinearSystem(constraintWidgetContainer, "intermediate pass", i23, i19, i20);
                    i22 = 2;
                    zNeedSolverPass = false;
                    basicMeasure = this;
                    z7 = z10;
                }
                i12 = i21;
            } else {
                j2 = jNanoTime;
                i12 = optimizationLevel;
            }
            constraintWidgetContainer.setOptimizationLevel(i12);
            jNanoTime = j2;
        }
        return constraintWidgetContainer.mMetrics != null ? System.nanoTime() - jNanoTime : jNanoTime;
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i2);
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (horizontalDimensionBehaviour == dimensionBehaviour || constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer.invalidateGraph();
    }
}
