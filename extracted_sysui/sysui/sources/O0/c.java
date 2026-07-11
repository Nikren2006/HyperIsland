package O0;

import I0.AbstractC0174b;
import I0.AbstractC0181i;
import java.io.Serializable;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends AbstractC0174b implements a, Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Enum[] f575b;

    public c(Enum[] entries) {
        n.g(entries, "entries");
        this.f575b = entries;
    }

    @Override // I0.AbstractC0173a
    public int c() {
        return this.f575b.length;
    }

    @Override // I0.AbstractC0173a, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return d((Enum) obj);
        }
        return false;
    }

    public boolean d(Enum element) {
        n.g(element, "element");
        return ((Enum) AbstractC0181i.C(this.f575b, element.ordinal())) == element;
    }

    @Override // I0.AbstractC0174b, java.util.List
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Enum get(int i2) {
        AbstractC0174b.f320a.a(i2, this.f575b.length);
        return this.f575b[i2];
    }

    public int f(Enum element) {
        n.g(element, "element");
        int iOrdinal = element.ordinal();
        if (((Enum) AbstractC0181i.C(this.f575b, iOrdinal)) == element) {
            return iOrdinal;
        }
        return -1;
    }

    public int g(Enum element) {
        n.g(element, "element");
        return indexOf(element);
    }

    @Override // I0.AbstractC0174b, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return f((Enum) obj);
        }
        return -1;
    }

    @Override // I0.AbstractC0174b, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return g((Enum) obj);
        }
        return -1;
    }
}
