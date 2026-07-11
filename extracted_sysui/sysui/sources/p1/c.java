package p1;

import l1.F;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final F f6394a = new F("NO_OWNER");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final F f6395b = new F("ALREADY_LOCKED_BY_OWNER");

    public static final a a(boolean z2) {
        return new b(z2);
    }

    public static /* synthetic */ a b(boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        return a(z2);
    }
}
