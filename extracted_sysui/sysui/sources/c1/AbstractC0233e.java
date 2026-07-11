package c1;

import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: c1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0233e {
    public static final void a(boolean z2, Number step) {
        n.g(step, "step");
        if (z2) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + step + '.');
    }
}
