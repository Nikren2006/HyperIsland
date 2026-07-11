package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.xiaomi.onetrack.util.aa;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class DependencyGraph {
    private static final boolean DEBUG = false;
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer mContainer;
    private ConstraintWidgetContainer mWidgetcontainer;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> mRunGroups = new ArrayList<>();
    private BasicMeasure.Measurer mMeasurer = null;
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    ArrayList<RunGroup> mGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mWidgetcontainer = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    private void applyGroup(DependencyNode dependencyNode, int i2, int i3, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun.mRunGroup == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.mWidgetcontainer;
            if (widgetRun == constraintWidgetContainer.mHorizontalRun || widgetRun == constraintWidgetContainer.mVerticalRun) {
                return;
            }
            if (runGroup == null) {
                runGroup = new RunGroup(widgetRun, i3);
                arrayList.add(runGroup);
            }
            widgetRun.mRunGroup = runGroup;
            runGroup.add(widgetRun);
            for (Dependency dependency : widgetRun.start.mDependencies) {
                if (dependency instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency, i2, 0, dependencyNode2, arrayList, runGroup);
                }
            }
            for (Dependency dependency2 : widgetRun.end.mDependencies) {
                if (dependency2 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency2, i2, 1, dependencyNode2, arrayList, runGroup);
                }
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.mDependencies) {
                    if (dependency3 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency3, i2, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.start.mTargets) {
                if (dependencyNode3 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(dependencyNode3, i2, 0, dependencyNode2, arrayList, runGroup);
            }
            for (DependencyNode dependencyNode4 : widgetRun.end.mTargets) {
                if (dependencyNode4 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(dependencyNode4, i2, 1, dependencyNode2, arrayList, runGroup);
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                Iterator<DependencyNode> it = ((VerticalWidgetRun) widgetRun).baseline.mTargets.iterator();
                while (it.hasNext()) {
                    applyGroup(it.next(), i2, 2, dependencyNode2, arrayList, runGroup);
                }
            }
        }
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int width;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int height;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        for (ConstraintWidget constraintWidget : constraintWidgetContainer.mChildren) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (constraintWidget.getVisibility() == 8) {
                constraintWidget.measured = true;
            } else {
                if (constraintWidget.mMatchConstraintPercentWidth < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.mMatchConstraintDefaultWidth = 2;
                }
                if (constraintWidget.mMatchConstraintPercentHeight < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget.mMatchConstraintDefaultHeight = 2;
                }
                if (constraintWidget.getDimensionRatio() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.mMatchConstraintDefaultWidth = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        constraintWidget.mMatchConstraintDefaultHeight = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            constraintWidget.mMatchConstraintDefaultWidth = 3;
                        }
                        if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                            constraintWidget.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && constraintWidget.mMatchConstraintDefaultWidth == 1 && (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && constraintWidget.mMatchConstraintDefaultHeight == 1 && (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.mHorizontalRun;
                horizontalWidgetRun.mDimensionBehavior = dimensionBehaviour9;
                int i2 = constraintWidget.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i2;
                VerticalWidgetRun verticalWidgetRun = constraintWidget.mVerticalRun;
                verticalWidgetRun.mDimensionBehavior = dimensionBehaviour10;
                int i3 = constraintWidget.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i3;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int width2 = constraintWidget.getWidth();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        width = (constraintWidgetContainer.getWidth() - constraintWidget.mLeft.mMargin) - constraintWidget.mRight.mMargin;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        width = width2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int height2 = constraintWidget.getHeight();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        height = (constraintWidgetContainer.getHeight() - constraintWidget.mTop.mMargin) - constraintWidget.mBottom.mMargin;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        height = height2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    measure(constraintWidget, dimensionBehaviour, width, dimensionBehaviour2, height);
                    constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                    constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                    constraintWidget.measured = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i2 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                measure(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int height3 = constraintWidget.getHeight();
                            int i4 = (int) ((height3 * constraintWidget.mDimensionRatio) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            measure(constraintWidget, dimensionBehaviour12, i4, dimensionBehaviour12, height3);
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (i2 == 1) {
                            measure(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                        } else if (i2 == 2) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = constraintWidgetContainer.mListDimensionBehaviors[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviour13 == dimensionBehaviour11) {
                                measure(constraintWidget, dimensionBehaviour14, (int) ((constraintWidget.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour10, constraintWidget.getHeight());
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
                            if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                                measure(constraintWidget, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i3 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                measure(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int width3 = constraintWidget.getWidth();
                            float f2 = constraintWidget.mDimensionRatio;
                            if (constraintWidget.getDimensionRatioSide() == -1) {
                                f2 = 1.0f / f2;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            measure(constraintWidget, dimensionBehaviour15, width3, dimensionBehaviour15, (int) ((width3 * f2) + 0.5f));
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (i3 == 1) {
                            measure(constraintWidget, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                        } else if (i3 == 2) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = constraintWidgetContainer.mListDimensionBehaviors[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviour16 == dimensionBehaviour11) {
                                measure(constraintWidget, dimensionBehaviour9, constraintWidget.getWidth(), dimensionBehaviour17, (int) ((constraintWidget.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.mListAnchors;
                            if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
                                measure(constraintWidget, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i2 == 1 || i3 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            measure(constraintWidget, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                            constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                        } else if (i3 == 2 && i2 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 && dimensionBehaviourArr2[1] == dimensionBehaviour20) {
                                measure(constraintWidget, dimensionBehaviour20, (int) ((constraintWidget.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour20, (int) ((constraintWidget.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        int size = this.mGroups.size();
        long jMax = 0;
        for (int i3 = 0; i3 < size; i3++) {
            jMax = Math.max(jMax, this.mGroups.get(i3).computeWrapSize(constraintWidgetContainer, i2));
        }
        return (int) jMax;
    }

    private void displayGraph() {
        Iterator<WidgetRun> it = this.mRuns.iterator();
        String strGenerateDisplayGraph = "digraph {\n";
        while (it.hasNext()) {
            strGenerateDisplayGraph = generateDisplayGraph(it.next(), strGenerateDisplayGraph);
        }
        PrintStream printStream = System.out;
        printStream.println("content:<<\n" + (strGenerateDisplayGraph + "\n}\n") + "\n>>");
    }

    private void findGroup(WidgetRun widgetRun, int i2, ArrayList<RunGroup> arrayList) {
        for (Dependency dependency : widgetRun.start.mDependencies) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i2, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i2, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.mDependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i2, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i2, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i2 == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.mDependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i2, 2, null, arrayList, null);
                }
            }
        }
    }

    private String generateChainDisplayGraph(ChainRun chainRun, String str) {
        int i2 = chainRun.orientation;
        StringBuilder sb = new StringBuilder("subgraph ");
        sb.append("cluster_");
        sb.append(chainRun.mWidget.getDebugName());
        if (i2 == 0) {
            sb.append("_h");
        } else {
            sb.append("_v");
        }
        sb.append(" {\n");
        String strGenerateDisplayGraph = "";
        for (WidgetRun widgetRun : chainRun.mWidgets) {
            sb.append(widgetRun.mWidget.getDebugName());
            if (i2 == 0) {
                sb.append("_HORIZONTAL");
            } else {
                sb.append("_VERTICAL");
            }
            sb.append(";\n");
            strGenerateDisplayGraph = generateDisplayGraph(widgetRun, strGenerateDisplayGraph);
        }
        sb.append("}\n");
        return str + strGenerateDisplayGraph + ((Object) sb);
    }

    private String generateDisplayGraph(WidgetRun widgetRun, String str) {
        boolean z2;
        DependencyNode dependencyNode = widgetRun.start;
        DependencyNode dependencyNode2 = widgetRun.end;
        StringBuilder sb = new StringBuilder(str);
        if (!(widgetRun instanceof HelperReferences) && dependencyNode.mDependencies.isEmpty() && dependencyNode2.mDependencies.isEmpty() && dependencyNode.mTargets.isEmpty() && dependencyNode2.mTargets.isEmpty()) {
            return str;
        }
        sb.append(nodeDefinition(widgetRun));
        boolean zIsCenteredConnection = isCenteredConnection(dependencyNode, dependencyNode2);
        String strGenerateDisplayNode = generateDisplayNode(dependencyNode2, zIsCenteredConnection, generateDisplayNode(dependencyNode, zIsCenteredConnection, str));
        boolean z3 = widgetRun instanceof VerticalWidgetRun;
        if (z3) {
            strGenerateDisplayNode = generateDisplayNode(((VerticalWidgetRun) widgetRun).baseline, zIsCenteredConnection, strGenerateDisplayNode);
        }
        if ((widgetRun instanceof HorizontalWidgetRun) || (((z2 = widgetRun instanceof ChainRun)) && ((ChainRun) widgetRun).orientation == 0)) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = widgetRun.mWidget.getHorizontalDimensionBehaviour();
            if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!dependencyNode.mTargets.isEmpty() && dependencyNode2.mTargets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode2.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode.name());
                    sb.append("\n");
                } else if (dependencyNode.mTargets.isEmpty() && !dependencyNode2.mTargets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode2.name());
                    sb.append("\n");
                }
            } else if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.mWidget.getDimensionRatio() > 0.0f) {
                sb.append("\n");
                sb.append(widgetRun.mWidget.getDebugName());
                sb.append("_HORIZONTAL -> ");
                sb.append(widgetRun.mWidget.getDebugName());
                sb.append("_VERTICAL;\n");
            }
        } else if (z3 || (z2 && ((ChainRun) widgetRun).orientation == 1)) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = widgetRun.mWidget.getVerticalDimensionBehaviour();
            if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!dependencyNode.mTargets.isEmpty() && dependencyNode2.mTargets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode2.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode.name());
                    sb.append("\n");
                } else if (dependencyNode.mTargets.isEmpty() && !dependencyNode2.mTargets.isEmpty()) {
                    sb.append("\n");
                    sb.append(dependencyNode.name());
                    sb.append(" -> ");
                    sb.append(dependencyNode2.name());
                    sb.append("\n");
                }
            } else if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.mWidget.getDimensionRatio() > 0.0f) {
                sb.append("\n");
                sb.append(widgetRun.mWidget.getDebugName());
                sb.append("_VERTICAL -> ");
                sb.append(widgetRun.mWidget.getDebugName());
                sb.append("_HORIZONTAL;\n");
            }
        }
        return widgetRun instanceof ChainRun ? generateChainDisplayGraph((ChainRun) widgetRun, strGenerateDisplayNode) : sb.toString();
    }

    private String generateDisplayNode(DependencyNode dependencyNode, boolean z2, String str) {
        StringBuilder sb = new StringBuilder(str);
        for (DependencyNode dependencyNode2 : dependencyNode.mTargets) {
            String str2 = ("\n" + dependencyNode.name()) + " -> " + dependencyNode2.name();
            if (dependencyNode.mMargin > 0 || z2 || (dependencyNode.mRun instanceof HelperReferences)) {
                String str3 = str2 + "[";
                if (dependencyNode.mMargin > 0) {
                    str3 = str3 + "label=\"" + dependencyNode.mMargin + "\"";
                    if (z2) {
                        str3 = str3 + aa.f3429b;
                    }
                }
                if (z2) {
                    str3 = str3 + " style=dashed ";
                }
                if (dependencyNode.mRun instanceof HelperReferences) {
                    str3 = str3 + " style=bold,color=gray ";
                }
                str2 = str3 + "]";
            }
            sb.append(str2 + "\n");
        }
        return sb.toString();
    }

    private boolean isCenteredConnection(DependencyNode dependencyNode, DependencyNode dependencyNode2) {
        Iterator<DependencyNode> it = dependencyNode.mTargets.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next() != dependencyNode2) {
                i2++;
            }
        }
        Iterator<DependencyNode> it2 = dependencyNode2.mTargets.iterator();
        int i3 = 0;
        while (it2.hasNext()) {
            if (it2.next() != dependencyNode) {
                i3++;
            }
        }
        return i2 > 0 && i3 > 0;
    }

    private void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i3;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    private String nodeDefinition(WidgetRun widgetRun) {
        boolean z2 = widgetRun instanceof VerticalWidgetRun;
        String debugName = widgetRun.mWidget.getDebugName();
        StringBuilder sb = new StringBuilder(debugName);
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = !z2 ? widgetRun.mWidget.getHorizontalDimensionBehaviour() : widgetRun.mWidget.getVerticalDimensionBehaviour();
        RunGroup runGroup = widgetRun.mRunGroup;
        if (z2) {
            sb.append("_VERTICAL");
        } else {
            sb.append("_HORIZONTAL");
        }
        sb.append(" [shape=none, label=<");
        sb.append("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">");
        sb.append("  <TR>");
        if (z2) {
            sb.append("    <TD ");
            if (widgetRun.start.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"TOP\" BORDER=\"1\">T</TD>");
        } else {
            sb.append("    <TD ");
            if (widgetRun.start.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"LEFT\" BORDER=\"1\">L</TD>");
        }
        sb.append("    <TD BORDER=\"1\" ");
        boolean z3 = widgetRun.mDimension.resolved;
        if (z3 && !widgetRun.mWidget.measured) {
            sb.append(" BGCOLOR=\"green\" ");
        } else if (z3) {
            sb.append(" BGCOLOR=\"lightgray\" ");
        } else if (widgetRun.mWidget.measured) {
            sb.append(" BGCOLOR=\"yellow\" ");
        }
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            sb.append("style=\"dashed\"");
        }
        sb.append(">");
        sb.append(debugName);
        if (runGroup != null) {
            sb.append(" [");
            sb.append(runGroup.mGroupIndex + 1);
            sb.append("/");
            sb.append(RunGroup.index);
            sb.append("]");
        }
        sb.append(" </TD>");
        if (z2) {
            sb.append("    <TD ");
            if (((VerticalWidgetRun) widgetRun).baseline.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"BASELINE\" BORDER=\"1\">b</TD>");
            sb.append("    <TD ");
            if (widgetRun.end.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"BOTTOM\" BORDER=\"1\">B</TD>");
        } else {
            sb.append("    <TD ");
            if (widgetRun.end.resolved) {
                sb.append(" BGCOLOR=\"green\"");
            }
            sb.append(" PORT=\"RIGHT\" BORDER=\"1\">R</TD>");
        }
        sb.append("  </TR></TABLE>");
        sb.append(">];\n");
        return sb.toString();
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.mWidgetcontainer.mHorizontalRun, 0, this.mGroups);
        findGroup(this.mWidgetcontainer.mVerticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2) {
        if (this.mNeedBuildGraph) {
            buildGraph();
            boolean z2 = false;
            for (ConstraintWidget constraintWidget : this.mWidgetcontainer.mChildren) {
                boolean[] zArr = constraintWidget.isTerminalWidget;
                zArr[0] = true;
                zArr[1] = true;
                if (constraintWidget instanceof Barrier) {
                    z2 = true;
                }
            }
            if (z2) {
                return;
            }
            for (RunGroup runGroup : this.mGroups) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                runGroup.defineTerminalWidgets(dimensionBehaviour == dimensionBehaviour3, dimensionBehaviour2 == dimensionBehaviour3);
            }
        }
    }

    public boolean directMeasure(boolean z2) {
        boolean z3;
        boolean z4 = false;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            for (ConstraintWidget constraintWidget : this.mWidgetcontainer.mChildren) {
                constraintWidget.ensureWidgetRuns();
                constraintWidget.measured = false;
                constraintWidget.mHorizontalRun.reset();
                constraintWidget.mVerticalRun.reset();
            }
            this.mWidgetcontainer.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.mWidgetcontainer;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.mHorizontalRun.reset();
            this.mWidgetcontainer.mVerticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.mWidgetcontainer.setX(0);
        this.mWidgetcontainer.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.mWidgetcontainer.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.mWidgetcontainer.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x2 = this.mWidgetcontainer.getX();
        int y2 = this.mWidgetcontainer.getY();
        this.mWidgetcontainer.mHorizontalRun.start.resolve(x2);
        this.mWidgetcontainer.mVerticalRun.start.resolve(y2);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z2) {
                Iterator<WidgetRun> it = this.mRuns.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().supportsWrapComputation()) {
                        z2 = false;
                        break;
                    }
                }
            }
            if (z2 && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.mWidgetcontainer.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.mWidgetcontainer;
                constraintWidgetContainer2.setWidth(computeWrap(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.mWidgetcontainer;
                constraintWidgetContainer3.mHorizontalRun.mDimension.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z2 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.mWidgetcontainer.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.mWidgetcontainer;
                constraintWidgetContainer4.setHeight(computeWrap(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.mWidgetcontainer;
                constraintWidgetContainer5.mVerticalRun.mDimension.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.mWidgetcontainer;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = constraintWidgetContainer6.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x2;
            this.mWidgetcontainer.mHorizontalRun.end.resolve(width);
            this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(width - x2);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.mWidgetcontainer;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = constraintWidgetContainer7.mListDimensionBehaviors[1];
            if (dimensionBehaviour6 == dimensionBehaviour5 || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y2;
                this.mWidgetcontainer.mVerticalRun.end.resolve(height);
                this.mWidgetcontainer.mVerticalRun.mDimension.resolve(height - y2);
            }
            measureWidgets();
            z3 = true;
        } else {
            z3 = false;
        }
        for (WidgetRun widgetRun : this.mRuns) {
            if (widgetRun.mWidget != this.mWidgetcontainer || widgetRun.mResolved) {
                widgetRun.applyToWidget();
            }
        }
        Iterator<WidgetRun> it2 = this.mRuns.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z4 = true;
                break;
            }
            WidgetRun next = it2.next();
            if (z3 || next.mWidget != this.mWidgetcontainer) {
                if (!next.start.resolved || ((!next.end.resolved && !(next instanceof GuidelineReference)) || (!next.mDimension.resolved && !(next instanceof ChainRun) && !(next instanceof GuidelineReference)))) {
                    break;
                }
            }
        }
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.mWidgetcontainer.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z4;
    }

    public boolean directMeasureSetup(boolean z2) {
        if (this.mNeedBuildGraph) {
            for (ConstraintWidget constraintWidget : this.mWidgetcontainer.mChildren) {
                constraintWidget.ensureWidgetRuns();
                constraintWidget.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.mHorizontalRun;
                horizontalWidgetRun.mDimension.resolved = false;
                horizontalWidgetRun.mResolved = false;
                horizontalWidgetRun.reset();
                VerticalWidgetRun verticalWidgetRun = constraintWidget.mVerticalRun;
                verticalWidgetRun.mDimension.resolved = false;
                verticalWidgetRun.mResolved = false;
                verticalWidgetRun.reset();
            }
            this.mWidgetcontainer.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.mWidgetcontainer;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.mHorizontalRun;
            horizontalWidgetRun2.mDimension.resolved = false;
            horizontalWidgetRun2.mResolved = false;
            horizontalWidgetRun2.reset();
            VerticalWidgetRun verticalWidgetRun2 = this.mWidgetcontainer.mVerticalRun;
            verticalWidgetRun2.mDimension.resolved = false;
            verticalWidgetRun2.mResolved = false;
            verticalWidgetRun2.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.mWidgetcontainer.setX(0);
        this.mWidgetcontainer.setY(0);
        this.mWidgetcontainer.mHorizontalRun.start.resolve(0);
        this.mWidgetcontainer.mVerticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z2, int i2) {
        boolean z3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z4 = false;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.mWidgetcontainer.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = this.mWidgetcontainer.getDimensionBehaviour(1);
        int x2 = this.mWidgetcontainer.getX();
        int y2 = this.mWidgetcontainer.getY();
        if (z2 && (dimensionBehaviour2 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour3 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.mRuns.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.orientation == i2 && !next.supportsWrapComputation()) {
                    z2 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z2 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.mWidgetcontainer.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.mWidgetcontainer;
                    constraintWidgetContainer.setWidth(computeWrap(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.mWidgetcontainer;
                    constraintWidgetContainer2.mHorizontalRun.mDimension.resolve(constraintWidgetContainer2.getWidth());
                }
            } else if (z2 && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.mWidgetcontainer.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.mWidgetcontainer;
                constraintWidgetContainer3.setHeight(computeWrap(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = this.mWidgetcontainer;
                constraintWidgetContainer4.mVerticalRun.mDimension.resolve(constraintWidgetContainer4.getHeight());
            }
        }
        if (i2 == 0) {
            ConstraintWidgetContainer constraintWidgetContainer5 = this.mWidgetcontainer;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = constraintWidgetContainer5.mListDimensionBehaviors[0];
            if (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int width = constraintWidgetContainer5.getWidth() + x2;
                this.mWidgetcontainer.mHorizontalRun.end.resolve(width);
                this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(width - x2);
                z3 = true;
            }
            z3 = false;
        } else {
            ConstraintWidgetContainer constraintWidgetContainer6 = this.mWidgetcontainer;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = constraintWidgetContainer6.mListDimensionBehaviors[1];
            if (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer6.getHeight() + y2;
                this.mWidgetcontainer.mVerticalRun.end.resolve(height);
                this.mWidgetcontainer.mVerticalRun.mDimension.resolve(height - y2);
                z3 = true;
            }
            z3 = false;
        }
        measureWidgets();
        for (WidgetRun widgetRun : this.mRuns) {
            if (widgetRun.orientation == i2 && (widgetRun.mWidget != this.mWidgetcontainer || widgetRun.mResolved)) {
                widgetRun.applyToWidget();
            }
        }
        Iterator<WidgetRun> it2 = this.mRuns.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z4 = true;
                break;
            }
            WidgetRun next2 = it2.next();
            if (next2.orientation == i2 && (z3 || next2.mWidget != this.mWidgetcontainer)) {
                if (!next2.start.resolved || !next2.end.resolved || (!(next2 instanceof ChainRun) && !next2.mDimension.resolved)) {
                    break;
                }
            }
        }
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        this.mWidgetcontainer.setVerticalDimensionBehaviour(dimensionBehaviour3);
        return z4;
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void measureWidgets() {
        DimensionDependency dimensionDependency;
        for (ConstraintWidget constraintWidget : this.mWidgetcontainer.mChildren) {
            if (!constraintWidget.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
                boolean z2 = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i2 = constraintWidget.mMatchConstraintDefaultWidth;
                int i3 = constraintWidget.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z3 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i3 == 1)) {
                    z2 = true;
                }
                DimensionDependency dimensionDependency2 = constraintWidget.mHorizontalRun.mDimension;
                boolean z4 = dimensionDependency2.resolved;
                DimensionDependency dimensionDependency3 = constraintWidget.mVerticalRun.mDimension;
                boolean z5 = dimensionDependency3.resolved;
                if (z4 && z5) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    measure(constraintWidget, dimensionBehaviour4, dimensionDependency2.value, dimensionBehaviour4, dimensionDependency3.value);
                    constraintWidget.measured = true;
                } else if (z4 && z2) {
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency2.value, dimensionBehaviour3, dimensionDependency3.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                    } else {
                        constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                        constraintWidget.measured = true;
                    }
                } else if (z5 && z3) {
                    measure(constraintWidget, dimensionBehaviour3, dimensionDependency2.value, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency3.value);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                    } else {
                        constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                        constraintWidget.measured = true;
                    }
                }
                if (constraintWidget.measured && (dimensionDependency = constraintWidget.mVerticalRun.mBaselineDimension) != null) {
                    dimensionDependency.resolve(constraintWidget.getBaselineDistance());
                }
            }
        }
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.mContainer.mHorizontalRun.clear();
        this.mContainer.mVerticalRun.clear();
        arrayList.add(this.mContainer.mHorizontalRun);
        arrayList.add(this.mContainer.mVerticalRun);
        HashSet hashSet = null;
        for (ConstraintWidget constraintWidget : this.mContainer.mChildren) {
            if (constraintWidget instanceof Guideline) {
                arrayList.add(new GuidelineReference(constraintWidget));
            } else {
                if (constraintWidget.isInHorizontalChain()) {
                    if (constraintWidget.horizontalChainRun == null) {
                        constraintWidget.horizontalChainRun = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.horizontalChainRun);
                } else {
                    arrayList.add(constraintWidget.mHorizontalRun);
                }
                if (constraintWidget.isInVerticalChain()) {
                    if (constraintWidget.verticalChainRun == null) {
                        constraintWidget.verticalChainRun = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.verticalChainRun);
                } else {
                    arrayList.add(constraintWidget.mVerticalRun);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        for (WidgetRun widgetRun : arrayList) {
            if (widgetRun.mWidget != this.mContainer) {
                widgetRun.apply();
            }
        }
    }
}
