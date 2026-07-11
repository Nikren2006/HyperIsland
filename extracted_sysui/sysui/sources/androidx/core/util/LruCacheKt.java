package androidx.core.util;

import H0.s;
import V0.n;
import android.util.LruCache;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class LruCacheKt {

    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Integer invoke(Object obj, Object obj2) {
            return 1;
        }
    }

    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements n {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(4);
        }

        public final void invoke(boolean z2, Object obj, Object obj2, Object obj3) {
        }

        @Override // V0.n
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke(((Boolean) obj).booleanValue(), obj2, obj3, obj4);
            return s.f314a;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: androidx.core.util.LruCacheKt$lruCache$4, reason: invalid class name */
    public static final class AnonymousClass4<K, V> extends LruCache<K, V> {
        final /* synthetic */ Function1 $create;
        final /* synthetic */ n $onEntryRemoved;
        final /* synthetic */ Function2 $sizeOf;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(int i2, Function2 function2, Function1 function1, n nVar) {
            super(i2);
            this.$sizeOf = function2;
            this.$create = function1;
            this.$onEntryRemoved = nVar;
        }

        @Override // android.util.LruCache
        public V create(K k2) {
            return (V) this.$create.invoke(k2);
        }

        @Override // android.util.LruCache
        public void entryRemoved(boolean z2, K k2, V v2, V v3) {
            this.$onEntryRemoved.invoke(Boolean.valueOf(z2), k2, v2, v3);
        }

        @Override // android.util.LruCache
        public int sizeOf(K k2, V v2) {
            return ((Number) this.$sizeOf.invoke(k2, v2)).intValue();
        }
    }

    public static final <K, V> LruCache<K, V> lruCache(int i2, Function2 function2, Function1 function1, n nVar) {
        return new AnonymousClass4(i2, function2, function1, nVar);
    }

    public static /* synthetic */ LruCache lruCache$default(int i2, Function2 function2, Function1 function1, n nVar, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        if ((i3 & 4) != 0) {
            function1 = AnonymousClass2.INSTANCE;
        }
        if ((i3 & 8) != 0) {
            nVar = AnonymousClass3.INSTANCE;
        }
        return new AnonymousClass4(i2, function2, function1, nVar);
    }
}
