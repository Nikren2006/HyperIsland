package u1;

import I0.G;
import c1.C0230b;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import t1.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class l extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q1.b f6875a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q1.b f6876b;

    public /* synthetic */ l(q1.b bVar, q1.b bVar2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bVar, bVar2);
    }

    @Override // q1.b, q1.a
    public abstract s1.c getDescriptor();

    @Override // u1.a
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void e(t1.b decoder, Map builder, int i2, int i3) {
        kotlin.jvm.internal.n.g(decoder, "decoder");
        kotlin.jvm.internal.n.g(builder, "builder");
        if (i3 < 0) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL");
        }
        C0230b c0230bK = c1.f.k(c1.f.l(0, i3 * 2), 2);
        int iC = c0230bK.c();
        int iD = c0230bK.d();
        int iE = c0230bK.e();
        if ((iE <= 0 || iC > iD) && (iE >= 0 || iD > iC)) {
            return;
        }
        while (true) {
            f(decoder, i2 + iC, builder, false);
            if (iC == iD) {
                return;
            } else {
                iC += iE;
            }
        }
    }

    @Override // u1.a
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final void f(t1.b decoder, int i2, Map builder, boolean z2) {
        int iB;
        kotlin.jvm.internal.n.g(decoder, "decoder");
        kotlin.jvm.internal.n.g(builder, "builder");
        Object objC = b.a.c(decoder, getDescriptor(), i2, this.f6875a, null, 8, null);
        if (z2) {
            iB = decoder.b(getDescriptor());
            if (iB != i2 + 1) {
                throw new IllegalArgumentException(("Value must follow key in a map, index for key: " + i2 + ", returned index for value: " + iB).toString());
            }
        } else {
            iB = i2 + 1;
        }
        int i3 = iB;
        builder.put(objC, (!builder.containsKey(objC) || (this.f6876b.getDescriptor().c() instanceof s1.b)) ? b.a.c(decoder, getDescriptor(), i3, this.f6876b, null, 8, null) : decoder.i(getDescriptor(), i3, this.f6876b, G.g(builder, objC)));
    }

    public l(q1.b bVar, q1.b bVar2) {
        super(null);
        this.f6875a = bVar;
        this.f6876b = bVar2;
    }
}
