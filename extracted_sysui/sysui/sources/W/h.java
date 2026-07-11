package W;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Comparator f799i = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Comparator f800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f803d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f804e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e f805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f806g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c f807h;

    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public class b extends AbstractSet {

        public class a extends d {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry next() {
                return a();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && h.this.e((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e eVarE;
            if (!(obj instanceof Map.Entry) || (eVarE = h.this.e((Map.Entry) obj)) == null) {
                return false;
            }
            h.this.h(eVarE, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f803d;
        }
    }

    public final class c extends AbstractSet {

        public class a extends d {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            public Object next() {
                return a().f821f;
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return h.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return h.this.i(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f803d;
        }
    }

    public abstract class d implements Iterator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f812a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f813b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f814c;

        public d() {
            this.f812a = h.this.f805f.f819d;
            this.f814c = h.this.f804e;
        }

        public final e a() {
            e eVar = this.f812a;
            h hVar = h.this;
            if (eVar == hVar.f805f) {
                throw new NoSuchElementException();
            }
            if (hVar.f804e != this.f814c) {
                throw new ConcurrentModificationException();
            }
            this.f812a = eVar.f819d;
            this.f813b = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f812a != h.this.f805f;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e eVar = this.f813b;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            h.this.h(eVar, true);
            this.f813b = null;
            this.f814c = h.this.f804e;
        }
    }

    public h() {
        this(f799i, true);
    }

    public final boolean c(Object obj, Object obj2) {
        return Objects.equals(obj, obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f802c = null;
        this.f803d = 0;
        this.f804e++;
        e eVar = this.f805f;
        eVar.f820e = eVar;
        eVar.f819d = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return f(obj) != null;
    }

    public e d(Object obj, boolean z2) {
        int iCompareTo;
        e eVar;
        Comparator comparator = this.f800a;
        e eVar2 = this.f802c;
        if (eVar2 != null) {
            Comparable comparable = comparator == f799i ? (Comparable) obj : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f821f) : comparator.compare(obj, eVar2.f821f);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e eVar3 = iCompareTo < 0 ? eVar2.f817b : eVar2.f818c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z2) {
            return null;
        }
        e eVar4 = this.f805f;
        if (eVar2 != null) {
            eVar = new e(this.f801b, eVar2, obj, eVar4, eVar4.f820e);
            if (iCompareTo < 0) {
                eVar2.f817b = eVar;
            } else {
                eVar2.f818c = eVar;
            }
            g(eVar2, true);
        } else {
            if (comparator == f799i && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName() + " is not Comparable");
            }
            eVar = new e(this.f801b, eVar2, obj, eVar4, eVar4.f820e);
            this.f802c = eVar;
        }
        this.f803d++;
        this.f804e++;
        return eVar;
    }

    public e e(Map.Entry entry) {
        e eVarF = f(entry.getKey());
        if (eVarF == null || !c(eVarF.f823h, entry.getValue())) {
            return null;
        }
        return eVarF;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        b bVar = this.f806g;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b();
        this.f806g = bVar2;
        return bVar2;
    }

    public e f(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return d(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public final void g(e eVar, boolean z2) {
        while (eVar != null) {
            e eVar2 = eVar.f817b;
            e eVar3 = eVar.f818c;
            int i2 = eVar2 != null ? eVar2.f824i : 0;
            int i3 = eVar3 != null ? eVar3.f824i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e eVar4 = eVar3.f817b;
                e eVar5 = eVar3.f818c;
                int i5 = (eVar4 != null ? eVar4.f824i : 0) - (eVar5 != null ? eVar5.f824i : 0);
                if (i5 == -1 || (i5 == 0 && !z2)) {
                    k(eVar);
                } else {
                    l(eVar3);
                    k(eVar);
                }
                if (z2) {
                    return;
                }
            } else if (i4 == 2) {
                e eVar6 = eVar2.f817b;
                e eVar7 = eVar2.f818c;
                int i6 = (eVar6 != null ? eVar6.f824i : 0) - (eVar7 != null ? eVar7.f824i : 0);
                if (i6 == 1 || (i6 == 0 && !z2)) {
                    l(eVar);
                } else {
                    k(eVar2);
                    l(eVar);
                }
                if (z2) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.f824i = i2 + 1;
                if (z2) {
                    return;
                }
            } else {
                eVar.f824i = Math.max(i2, i3) + 1;
                if (!z2) {
                    return;
                }
            }
            eVar = eVar.f816a;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        e eVarF = f(obj);
        if (eVarF != null) {
            return eVarF.f823h;
        }
        return null;
    }

    public void h(e eVar, boolean z2) {
        int i2;
        if (z2) {
            e eVar2 = eVar.f820e;
            eVar2.f819d = eVar.f819d;
            eVar.f819d.f820e = eVar2;
        }
        e eVar3 = eVar.f817b;
        e eVar4 = eVar.f818c;
        e eVar5 = eVar.f816a;
        int i3 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                j(eVar, eVar3);
                eVar.f817b = null;
            } else if (eVar4 != null) {
                j(eVar, eVar4);
                eVar.f818c = null;
            } else {
                j(eVar, null);
            }
            g(eVar5, false);
            this.f803d--;
            this.f804e++;
            return;
        }
        e eVarB = eVar3.f824i > eVar4.f824i ? eVar3.b() : eVar4.a();
        h(eVarB, false);
        e eVar6 = eVar.f817b;
        if (eVar6 != null) {
            i2 = eVar6.f824i;
            eVarB.f817b = eVar6;
            eVar6.f816a = eVarB;
            eVar.f817b = null;
        } else {
            i2 = 0;
        }
        e eVar7 = eVar.f818c;
        if (eVar7 != null) {
            i3 = eVar7.f824i;
            eVarB.f818c = eVar7;
            eVar7.f816a = eVarB;
            eVar.f818c = null;
        }
        eVarB.f824i = Math.max(i2, i3) + 1;
        j(eVar, eVarB);
    }

    public e i(Object obj) {
        e eVarF = f(obj);
        if (eVarF != null) {
            h(eVarF, true);
        }
        return eVarF;
    }

    public final void j(e eVar, e eVar2) {
        e eVar3 = eVar.f816a;
        eVar.f816a = null;
        if (eVar2 != null) {
            eVar2.f816a = eVar3;
        }
        if (eVar3 == null) {
            this.f802c = eVar2;
        } else if (eVar3.f817b == eVar) {
            eVar3.f817b = eVar2;
        } else {
            eVar3.f818c = eVar2;
        }
    }

    public final void k(e eVar) {
        e eVar2 = eVar.f817b;
        e eVar3 = eVar.f818c;
        e eVar4 = eVar3.f817b;
        e eVar5 = eVar3.f818c;
        eVar.f818c = eVar4;
        if (eVar4 != null) {
            eVar4.f816a = eVar;
        }
        j(eVar, eVar3);
        eVar3.f817b = eVar;
        eVar.f816a = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.f824i : 0, eVar4 != null ? eVar4.f824i : 0) + 1;
        eVar.f824i = iMax;
        eVar3.f824i = Math.max(iMax, eVar5 != null ? eVar5.f824i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        c cVar = this.f807h;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c();
        this.f807h = cVar2;
        return cVar2;
    }

    public final void l(e eVar) {
        e eVar2 = eVar.f817b;
        e eVar3 = eVar.f818c;
        e eVar4 = eVar2.f817b;
        e eVar5 = eVar2.f818c;
        eVar.f817b = eVar5;
        if (eVar5 != null) {
            eVar5.f816a = eVar;
        }
        j(eVar, eVar2);
        eVar2.f818c = eVar;
        eVar.f816a = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.f824i : 0, eVar5 != null ? eVar5.f824i : 0) + 1;
        eVar.f824i = iMax;
        eVar2.f824i = Math.max(iMax, eVar4 != null ? eVar4.f824i : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        if (obj2 == null && !this.f801b) {
            throw new NullPointerException("value == null");
        }
        e eVarD = d(obj, true);
        Object obj3 = eVarD.f823h;
        eVarD.f823h = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        e eVarI = i(obj);
        if (eVarI != null) {
            return eVarI.f823h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f803d;
    }

    public h(boolean z2) {
        this(f799i, z2);
    }

    public h(Comparator comparator, boolean z2) {
        this.f803d = 0;
        this.f804e = 0;
        this.f800a = comparator == null ? f799i : comparator;
        this.f801b = z2;
        this.f805f = new e(z2);
    }

    public static final class e implements Map.Entry {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f816a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f817b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e f818c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e f819d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e f820e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Object f821f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f822g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Object f823h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f824i;

        public e(boolean z2) {
            this.f821f = null;
            this.f822g = z2;
            this.f820e = this;
            this.f819d = this;
        }

        public e a() {
            e eVar = this.f817b;
            while (true) {
                e eVar2 = eVar;
                e eVar3 = this;
                this = eVar2;
                if (this == null) {
                    return eVar3;
                }
                eVar = this.f817b;
            }
        }

        public e b() {
            e eVar = this.f818c;
            while (true) {
                e eVar2 = eVar;
                e eVar3 = this;
                this = eVar2;
                if (this == null) {
                    return eVar3;
                }
                eVar = this.f818c;
            }
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.f821f;
            if (obj2 == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!obj2.equals(entry.getKey())) {
                return false;
            }
            Object obj3 = this.f823h;
            if (obj3 == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!obj3.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f821f;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f823h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.f821f;
            int iHashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.f823h;
            return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (obj == null && !this.f822g) {
                throw new NullPointerException("value == null");
            }
            Object obj2 = this.f823h;
            this.f823h = obj;
            return obj2;
        }

        public String toString() {
            return this.f821f + "=" + this.f823h;
        }

        public e(boolean z2, e eVar, Object obj, e eVar2, e eVar3) {
            this.f816a = eVar;
            this.f821f = obj;
            this.f822g = z2;
            this.f824i = 1;
            this.f819d = eVar2;
            this.f820e = eVar3;
            eVar3.f819d = this;
            eVar2.f820e = this;
        }
    }
}
