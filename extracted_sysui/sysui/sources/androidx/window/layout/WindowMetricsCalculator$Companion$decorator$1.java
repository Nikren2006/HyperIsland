package androidx.window.layout;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class WindowMetricsCalculator$Companion$decorator$1 extends o implements Function1 {
    public static final WindowMetricsCalculator$Companion$decorator$1 INSTANCE = new WindowMetricsCalculator$Companion$decorator$1();

    public WindowMetricsCalculator$Companion$decorator$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final WindowMetricsCalculator invoke(WindowMetricsCalculator it) {
        n.g(it, "it");
        return it;
    }
}
