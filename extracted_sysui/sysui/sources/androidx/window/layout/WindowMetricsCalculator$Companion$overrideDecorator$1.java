package androidx.window.layout;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class WindowMetricsCalculator$Companion$overrideDecorator$1 extends l implements Function1 {
    public WindowMetricsCalculator$Companion$overrideDecorator$1(Object obj) {
        super(1, obj, WindowMetricsCalculatorDecorator.class, "decorate", "decorate(Landroidx/window/layout/WindowMetricsCalculator;)Landroidx/window/layout/WindowMetricsCalculator;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final WindowMetricsCalculator invoke(WindowMetricsCalculator p02) {
        n.g(p02, "p0");
        return ((WindowMetricsCalculatorDecorator) this.receiver).decorate(p02);
    }
}
