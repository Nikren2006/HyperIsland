package u1;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements q1.b {
    public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ void g(a aVar, t1.b bVar, int i2, Object obj, boolean z2, int i3, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readElement");
        }
        if ((i3 & 8) != 0) {
            z2 = true;
        }
        aVar.f(bVar, i2, obj, z2);
    }

    public abstract Object a();

    public abstract int b(Object obj);

    public abstract void c(Object obj, int i2);

    public final Object d(t1.d decoder, Object obj) {
        Object objA;
        kotlin.jvm.internal.n.g(decoder, "decoder");
        if (obj == null || (objA = i(obj)) == null) {
            objA = a();
        }
        int iB = b(objA);
        t1.b bVarA = decoder.a(getDescriptor());
        if (!bVarA.d()) {
            while (true) {
                int iB2 = bVarA.b(getDescriptor());
                if (iB2 == -1) {
                    break;
                }
                g(this, bVarA, iB + iB2, objA, false, 8, null);
            }
        } else {
            e(bVarA, objA, iB, h(bVarA, objA));
        }
        bVarA.h(getDescriptor());
        return j(objA);
    }

    @Override // q1.a
    public Object deserialize(t1.d decoder) {
        kotlin.jvm.internal.n.g(decoder, "decoder");
        return d(decoder, null);
    }

    public abstract void e(t1.b bVar, Object obj, int i2, int i3);

    public abstract void f(t1.b bVar, int i2, Object obj, boolean z2);

    public final int h(t1.b bVar, Object obj) {
        int iE = bVar.e(getDescriptor());
        c(obj, iE);
        return iE;
    }

    public abstract Object i(Object obj);

    public abstract Object j(Object obj);

    public a() {
    }
}
