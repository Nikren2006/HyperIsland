package androidx.collection;

import W0.b;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.n;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$entries$1<K, V> implements Set<Map.Entry<K, V>>, b {
    final /* synthetic */ MutableScatterMap<K, V> this$0;

    public MutableScatterMap$MutableMapWrapper$entries$1(MutableScatterMap<K, V> mutableScatterMap) {
        this.this$0 = mutableScatterMap;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends Map.Entry<K, V>> elements) {
        n.g(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.this$0.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (D.l(obj)) {
            return contains((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        n.g(elements, "elements");
        MutableScatterMap<K, V> mutableScatterMap = this.this$0;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!n.c(mutableScatterMap.get((K) entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return this.this$0._size;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.this$0.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MutableScatterMap$MutableMapWrapper$entries$1$iterator$1(this.this$0);
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean remove(Object obj) {
        if (D.l(obj)) {
            return remove((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        boolean z2;
        n.g(elements, "elements");
        MutableScatterMap<K, V> mutableScatterMap = this.this$0;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            boolean z3 = false;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            Iterator<? extends Object> it = elements.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Map.Entry entry = (Map.Entry) it.next();
                                    if (n.c(entry.getKey(), mutableScatterMap.keys[i5]) && n.c(entry.getValue(), mutableScatterMap.values[i5])) {
                                        mutableScatterMap.removeValueAt(i5);
                                        z3 = true;
                                        break;
                                    }
                                }
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i3 != 8) {
                        return z3;
                    }
                }
                if (i2 == length) {
                    z2 = z3;
                    break;
                }
                i2++;
            }
        } else {
            z2 = false;
        }
        return z2;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        boolean z2;
        n.g(elements, "elements");
        MutableScatterMap<K, V> mutableScatterMap = this.this$0;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            boolean z3 = false;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            Iterator<? extends Object> it = elements.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    mutableScatterMap.removeValueAt(i5);
                                    z3 = true;
                                    break;
                                }
                                Map.Entry entry = (Map.Entry) it.next();
                                if (!n.c(entry.getKey(), mutableScatterMap.keys[i5]) || !n.c(entry.getValue(), mutableScatterMap.values[i5])) {
                                }
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i3 != 8) {
                        return z3;
                    }
                }
                if (i2 == length) {
                    z2 = z3;
                    break;
                }
                i2++;
            }
        } else {
            z2 = false;
        }
        return z2;
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return g.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Map.Entry<K, V> element) {
        n.g(element, "element");
        throw new UnsupportedOperationException();
    }

    public boolean contains(Map.Entry<K, V> element) {
        n.g(element, "element");
        return n.c(this.this$0.get(element.getKey()), element.getValue());
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
    
        if (((r9 & ((~r9) << 6)) & (-9187201950435737472L)) == 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x007a, code lost:
    
        r15 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean remove(java.util.Map.Entry<K, V> r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.String r1 = "element"
            r2 = r19
            kotlin.jvm.internal.n.g(r2, r1)
            androidx.collection.MutableScatterMap<K, V> r1 = r0.this$0
            java.lang.Object r3 = r19.getKey()
            if (r3 == 0) goto L16
            int r5 = r3.hashCode()
            goto L17
        L16:
            r5 = 0
        L17:
            r6 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r5 = r5 * r6
            int r6 = r5 << 16
            r5 = r5 ^ r6
            r6 = r5 & 127(0x7f, float:1.78E-43)
            int r7 = r1._capacity
            int r5 = r5 >>> 7
            r5 = r5 & r7
            r8 = 0
        L26:
            long[] r9 = r1.metadata
            int r10 = r5 >> 3
            r11 = r5 & 7
            int r11 = r11 << 3
            r12 = r9[r10]
            long r12 = r12 >>> r11
            r14 = 1
            int r10 = r10 + r14
            r9 = r9[r10]
            int r15 = 64 - r11
            long r9 = r9 << r15
            long r14 = (long) r11
            long r14 = -r14
            r11 = 63
            long r14 = r14 >> r11
            long r9 = r9 & r14
            long r9 = r9 | r12
            long r11 = (long) r6
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r11 = r11 * r13
            long r11 = r11 ^ r9
            long r13 = r11 - r13
            long r11 = ~r11
            long r11 = r11 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
        L51:
            r16 = 0
            int r15 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            if (r15 == 0) goto L71
            int r15 = java.lang.Long.numberOfTrailingZeros(r11)
            int r15 = r15 >> 3
            int r15 = r15 + r5
            r15 = r15 & r7
            java.lang.Object[] r4 = r1.keys
            r4 = r4[r15]
            boolean r4 = kotlin.jvm.internal.n.c(r4, r3)
            if (r4 == 0) goto L6a
            goto L7b
        L6a:
            r16 = 1
            long r16 = r11 - r16
            long r11 = r11 & r16
            goto L51
        L71:
            long r11 = ~r9
            r4 = 6
            long r11 = r11 << r4
            long r9 = r9 & r11
            long r9 = r9 & r13
            int r4 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r4 == 0) goto L96
            r15 = -1
        L7b:
            if (r15 < 0) goto L94
            androidx.collection.MutableScatterMap<K, V> r1 = r0.this$0
            java.lang.Object[] r1 = r1.values
            r1 = r1[r15]
            java.lang.Object r2 = r19.getValue()
            boolean r1 = kotlin.jvm.internal.n.c(r1, r2)
            if (r1 == 0) goto L94
            androidx.collection.MutableScatterMap<K, V> r0 = r0.this$0
            r0.removeValueAt(r15)
            r0 = 1
            return r0
        L94:
            r4 = 0
            return r4
        L96:
            r4 = 0
            int r8 = r8 + 8
            int r5 = r5 + r8
            r5 = r5 & r7
            goto L26
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1.remove(java.util.Map$Entry):boolean");
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] array) {
        n.g(array, "array");
        return (T[]) g.b(this, array);
    }
}
