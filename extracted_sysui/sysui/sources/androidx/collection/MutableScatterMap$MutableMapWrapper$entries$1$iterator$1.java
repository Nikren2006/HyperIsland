package androidx.collection;

import H0.s;
import L0.d;
import N0.f;
import N0.k;
import W0.a;
import e1.AbstractC0342g;
import e1.AbstractC0343h;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> implements Iterator<Map.Entry<K, V>>, a {
    private int current = -1;
    private Iterator<? extends Map.Entry<K, V>> iterator;
    final /* synthetic */ MutableScatterMap<K, V> this$0;

    /* JADX INFO: renamed from: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1, reason: invalid class name */
    @f(c = "androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1", f = "ScatterMap.kt", l = {1328}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends k implements Function2 {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutableScatterMap<K, V> this$0;
        final /* synthetic */ MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableScatterMap mutableScatterMap, MutableScatterMap$MutableMapWrapper$entries$1$iterator$1 mutableScatterMap$MutableMapWrapper$entries$1$iterator$1, d dVar) {
            super(2, dVar);
            this.this$0 = mutableScatterMap;
            this.this$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$1;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.this$1, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AbstractC0342g abstractC0342g, d dVar) {
            return ((AnonymousClass1) create(abstractC0342g, dVar)).invokeSuspend(s.f314a);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00b2  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00c2  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x005a -> B:14:0x0070). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00a7 -> B:20:0x00aa). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00ad -> B:22:0x00ae). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00bf -> B:27:0x00c0). Please report as a decompilation issue!!! */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 201
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public MutableScatterMap$MutableMapWrapper$entries$1$iterator$1(MutableScatterMap<K, V> mutableScatterMap) {
        this.this$0 = mutableScatterMap;
        this.iterator = AbstractC0343h.a(new AnonymousClass1(mutableScatterMap, this, null));
    }

    public final int getCurrent() {
        return this.current;
    }

    public final Iterator<Map.Entry<K, V>> getIterator() {
        return this.iterator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i2 = this.current;
        if (i2 != -1) {
            this.this$0.removeValueAt(i2);
            this.current = -1;
        }
    }

    public final void setCurrent(int i2) {
        this.current = i2;
    }

    public final void setIterator(Iterator<? extends Map.Entry<K, V>> it) {
        n.g(it, "<set-?>");
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public Map.Entry<K, V> next() {
        return this.iterator.next();
    }
}
