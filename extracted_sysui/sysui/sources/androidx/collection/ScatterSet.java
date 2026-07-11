package androidx.collection;

import W0.a;
import androidx.annotation.IntRange;
import androidx.collection.internal.ContainerHelpersKt;
import e1.AbstractC0343h;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public abstract class ScatterSet<E> {
    public int _capacity;
    public int _size;
    public Object[] elements;
    public long[] metadata;

    public class SetWrapper implements Set<E>, a {
        public SetWrapper() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(E e2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return ScatterSet.this.contains(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            n.g(elements, "elements");
            ScatterSet<E> scatterSet = ScatterSet.this;
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (!scatterSet.contains((E) it.next())) {
                    return false;
                }
            }
            return true;
        }

        public int getSize() {
            return ScatterSet.this._size;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return ScatterSet.this.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return AbstractC0343h.a(new ScatterSet$SetWrapper$iterator$1(ScatterSet.this, null));
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<? extends Object> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<? extends Object> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
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
        public <T> T[] toArray(T[] array) {
            n.g(array, "array");
            return (T[]) g.b(this, array);
        }
    }

    /* JADX INFO: renamed from: androidx.collection.ScatterSet$toString$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ ScatterSet<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ScatterSet<E> scatterSet) {
            super(1);
            this.this$0 = scatterSet;
        }

        @Override // kotlin.jvm.functions.Function1
        public final CharSequence invoke(E e2) {
            return e2 == this.this$0 ? "(this)" : String.valueOf(e2);
        }
    }

    public /* synthetic */ ScatterSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ void getElements$annotations() {
    }

    public static /* synthetic */ void getMetadata$annotations() {
    }

    public static /* synthetic */ String joinToString$default(ScatterSet scatterSet, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i3 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return scatterSet.joinToString(charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public final boolean all(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128 && !((Boolean) predicate.invoke(objArr[(i2 << 3) + i4])).booleanValue()) {
                        return false;
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return true;
                }
            }
            if (i2 == length) {
                return true;
            }
            i2++;
        }
    }

    public final boolean any() {
        return this._size != 0;
    }

    public final Set<E> asSet() {
        return new SetWrapper();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean contains(E r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.2930312E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0._capacity
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.metadata
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.elements
            r15 = r15[r11]
            boolean r15 = kotlin.jvm.internal.n.c(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L74
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            return r2
        L74:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterSet.contains(java.lang.Object):boolean");
    }

    @IntRange(from = 0)
    public final int count() {
        return getSize();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r15) {
        /*
            r14 = this;
            r0 = 1
            if (r15 != r14) goto L4
            return r0
        L4:
            boolean r1 = r15 instanceof androidx.collection.ScatterSet
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            androidx.collection.ScatterSet r15 = (androidx.collection.ScatterSet) r15
            int r1 = r15.getSize()
            int r3 = r14.getSize()
            if (r1 == r3) goto L17
            return r2
        L17:
            java.lang.Object[] r1 = r14.elements
            long[] r14 = r14.metadata
            int r3 = r14.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L5d
            r4 = r2
        L21:
            r5 = r14[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L58
            int r7 = r4 - r3
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r2
        L3b:
            if (r9 >= r7) goto L56
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.32E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L52
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]
            boolean r10 = r15.contains(r10)
            if (r10 != 0) goto L52
            return r2
        L52:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L3b
        L56:
            if (r7 != r8) goto L5d
        L58:
            if (r4 == r3) goto L5d
            int r4 = r4 + 1
            goto L21
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterSet.equals(java.lang.Object):boolean");
    }

    public final int findElementIndex$collection(E e2) {
        int i2 = 0;
        int iHashCode = (e2 != null ? e2.hashCode() : 0) * ScatterMapKt.MurmurHashC1;
        int i3 = iHashCode ^ (iHashCode << 16);
        int i4 = i3 & 127;
        int i5 = this._capacity;
        int i6 = i3 >>> 7;
        while (true) {
            int i7 = i6 & i5;
            long[] jArr = this.metadata;
            int i8 = i7 >> 3;
            int i9 = (i7 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = (((long) i4) * ScatterMapKt.BitmaskLsb) ^ j2;
            for (long j4 = (~j3) & (j3 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i7) & i5;
                if (n.c(this.elements[iNumberOfTrailingZeros], e2)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i2 += 8;
            i6 = i7 + i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final E first() {
        /*
            r13 = this;
            java.lang.Object[] r0 = r13.elements
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L41
            r2 = 0
            r3 = r2
        Lb:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L3c
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L25:
            if (r8 >= r6) goto L3a
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L36
            int r13 = r3 << 3
            int r13 = r13 + r8
            r13 = r0[r13]
            return r13
        L36:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L25
        L3a:
            if (r6 != r7) goto L41
        L3c:
            if (r3 == r1) goto L41
            int r3 = r3 + 1
            goto Lb
        L41:
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r0 = "The ScatterSet is empty"
            r13.<init>(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterSet.first():java.lang.Object");
    }

    public final E firstOrNull(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return null;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        E e2 = (E) objArr[(i2 << 3) + i4];
                        if (((Boolean) predicate.invoke(e2)).booleanValue()) {
                            return e2;
                        }
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return null;
                }
            }
            if (i2 == length) {
                return null;
            }
            i2++;
        }
    }

    public final void forEach(Function1 block) {
        n.g(block, "block");
        Object[] objArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        block.invoke(objArr[(i2 << 3) + i4]);
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return;
                }
            }
            if (i2 == length) {
                return;
            } else {
                i2++;
            }
        }
    }

    public final void forEachIndex(Function1 block) {
        n.g(block, "block");
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            long j2 = jArr[i2];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i2 - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j2) < 128) {
                        block.invoke(Integer.valueOf((i2 << 3) + i4));
                    }
                    j2 >>= 8;
                }
                if (i3 != 8) {
                    return;
                }
            }
            if (i2 == length) {
                return;
            } else {
                i2++;
            }
        }
    }

    @IntRange(from = 0)
    public final int getCapacity() {
        return this._capacity;
    }

    @IntRange(from = 0)
    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        Object[] objArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int iHashCode = 0;
            while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i5 = 0; i5 < i4; i5++) {
                        if ((255 & j2) < 128) {
                            Object obj = objArr[(i3 << 3) + i5];
                            iHashCode += obj != null ? obj.hashCode() : 0;
                        }
                        j2 >>= 8;
                    }
                    if (i4 != 8) {
                        return iHashCode;
                    }
                }
                if (i3 == length) {
                    i2 = iHashCode;
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return joinToString$default(this, null, null, null, 0, null, null, 63, null);
    }

    public final boolean none() {
        return this._size == 0;
    }

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, new AnonymousClass1(this), 25, null);
    }

    private ScatterSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = ContainerHelpersKt.EMPTY_OBJECTS;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean any(kotlin.jvm.functions.Function1 r14) {
        /*
            r13 = this;
            java.lang.String r0 = "predicate"
            kotlin.jvm.internal.n.g(r14, r0)
            java.lang.Object[] r0 = r13.elements
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            r2 = 0
            if (r1 < 0) goto L53
            r3 = r2
        L10:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L4e
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L2a:
            if (r8 >= r6) goto L4c
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L48
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            java.lang.Object r9 = r14.invoke(r9)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L48
            r13 = 1
            return r13
        L48:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L2a
        L4c:
            if (r6 != r7) goto L53
        L4e:
            if (r3 == r1) goto L53
            int r3 = r3 + 1
            goto L10
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterSet.any(kotlin.jvm.functions.Function1):boolean");
    }

    @IntRange(from = 0)
    public final int count(Function1 predicate) {
        n.g(predicate, "predicate");
        Object[] objArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128 && ((Boolean) predicate.invoke(objArr[(i3 << 3) + i6])).booleanValue()) {
                            i4++;
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        return i4;
                    }
                }
                if (i3 == length) {
                    i2 = i4;
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    public final String joinToString(CharSequence separator) {
        n.g(separator, "separator");
        return joinToString$default(this, separator, null, null, 0, null, null, 62, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        return joinToString$default(this, separator, prefix, null, 0, null, null, 60, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, null, 56, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i2, null, null, 48, null);
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        return joinToString$default(this, separator, prefix, postfix, i2, truncated, null, 32, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final E first(kotlin.jvm.functions.Function1 r14) {
        /*
            r13 = this;
            java.lang.String r0 = "predicate"
            kotlin.jvm.internal.n.g(r14, r0)
            java.lang.Object[] r0 = r13.elements
            long[] r13 = r13.metadata
            int r1 = r13.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L52
            r2 = 0
            r3 = r2
        L10:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L4d
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L2a:
            if (r8 >= r6) goto L4b
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.32E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L47
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            java.lang.Object r10 = r14.invoke(r9)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L47
            return r9
        L47:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L2a
        L4b:
            if (r6 != r7) goto L52
        L4d:
            if (r3 == r1) goto L52
            int r3 = r3 + 1
            goto L10
        L52:
            java.util.NoSuchElementException r13 = new java.util.NoSuchElementException
            java.lang.String r14 = "Could not find a match"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ScatterSet.first(kotlin.jvm.functions.Function1):java.lang.Object");
    }

    public final String joinToString(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        n.g(separator, "separator");
        n.g(prefix, "prefix");
        n.g(postfix, "postfix");
        n.g(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Object[] objArr = this.elements;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            loop0: while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8;
                    int i6 = 8 - ((~(i3 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((j2 & 255) < 128) {
                            Object obj = objArr[(i3 << 3) + i7];
                            if (i4 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i4 != 0) {
                                sb.append(separator);
                            }
                            if (function1 == null) {
                                sb.append(obj);
                            } else {
                                sb.append((CharSequence) function1.invoke(obj));
                            }
                            i4++;
                        }
                        j2 >>= 8;
                        i7++;
                        i5 = 8;
                    }
                    if (i6 != i5) {
                        break;
                    }
                }
                if (i3 == length) {
                    break;
                }
                i3++;
            }
            sb.append(postfix);
        } else {
            sb.append(postfix);
        }
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
