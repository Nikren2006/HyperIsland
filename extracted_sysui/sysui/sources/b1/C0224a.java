package b1;

import a1.AbstractC0197a;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: b1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0224a extends AbstractC0197a {
    @Override // a1.AbstractC0197a
    public Random d() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        n.f(threadLocalRandomCurrent, "current(...)");
        return threadLocalRandomCurrent;
    }
}
