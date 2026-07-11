package g1;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: g1.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0394v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f4463b = AtomicIntegerFieldUpdater.newUpdater(C0394v.class, "_handled");
    private volatile int _handled;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f4464a;

    public C0394v(Throwable th, boolean z2) {
        this.f4464a = th;
        this._handled = z2 ? 1 : 0;
    }

    public final boolean a() {
        return f4463b.get(this) != 0;
    }

    public final boolean b() {
        return f4463b.compareAndSet(this, 0, 1);
    }

    public String toString() {
        return I.a(this) + '[' + this.f4464a + ']';
    }

    public /* synthetic */ C0394v(Throwable th, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i2 & 2) != 0 ? false : z2);
    }
}
