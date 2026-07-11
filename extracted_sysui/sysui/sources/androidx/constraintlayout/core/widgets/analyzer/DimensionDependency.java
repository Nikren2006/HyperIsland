package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;

/* JADX INFO: loaded from: classes.dex */
class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof HorizontalWidgetRun) {
            this.mType = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.mType = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.DependencyNode
    public void resolve(int i2) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        this.value = i2;
        for (Dependency dependency : this.mDependencies) {
            dependency.update(dependency);
        }
    }
}
