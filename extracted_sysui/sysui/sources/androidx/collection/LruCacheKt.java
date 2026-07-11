package androidx.collection;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class LruCacheKt {

    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Integer invoke(Object obj, Object obj2) {
            n.g(obj, "<anonymous parameter 0>");
            n.g(obj2, "<anonymous parameter 1>");
            return 1;
        }
    }

    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            n.g(it, "it");
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements V0.n {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(4);
        }

        public final void invoke(boolean z2, Object obj, Object obj2, Object obj3) {
            n.g(obj, "<anonymous parameter 1>");
            n.g(obj2, "<anonymous parameter 2>");
        }

        @Override // V0.n
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke(((Boolean) obj).booleanValue(), obj2, obj3, obj4);
            return s.f314a;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* JADX INFO: renamed from: androidx.collection.LruCacheKt$lruCache$4, reason: invalid class name */
    public static final class AnonymousClass4<K, V> extends LruCache<K, V> {
        final /* synthetic */ Function1 $create;
        final /* synthetic */ V0.n $onEntryRemoved;
        final /* synthetic */ Function2 $sizeOf;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(int i2, Function2 function2, Function1 function1, V0.n nVar) {
            super(i2);
            this.$sizeOf = function2;
            this.$create = function1;
            this.$onEntryRemoved = nVar;
        }

        @Override // androidx.collection.LruCache
        public V create(K key) {
            n.g(key, "key");
            return (V) this.$create.invoke(key);
        }

        @Override // androidx.collection.LruCache
        public void entryRemoved(boolean z2, K key, V oldValue, V v2) {
            n.g(key, "key");
            n.g(oldValue, "oldValue");
            this.$onEntryRemoved.invoke(Boolean.valueOf(z2), key, oldValue, v2);
        }

        @Override // androidx.collection.LruCache
        public int sizeOf(K key, V value) {
            n.g(key, "key");
            n.g(value, "value");
            return ((Number) this.$sizeOf.invoke(key, value)).intValue();
        }
    }

    public static final <K, V> LruCache<K, V> lruCache(int i2, Function2 sizeOf, Function1 create, V0.n onEntryRemoved) {
        n.g(sizeOf, "sizeOf");
        n.g(create, "create");
        n.g(onEntryRemoved, "onEntryRemoved");
        return new AnonymousClass4(i2, sizeOf, create, onEntryRemoved);
    }

    public static /* synthetic */ LruCache lruCache$default(int i2, Function2 sizeOf, Function1 create, V0.n onEntryRemoved, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            sizeOf = AnonymousClass1.INSTANCE;
        }
        if ((i3 & 4) != 0) {
            create = AnonymousClass2.INSTANCE;
        }
        if ((i3 & 8) != 0) {
            onEntryRemoved = AnonymousClass3.INSTANCE;
        }
        n.g(sizeOf, "sizeOf");
        n.g(create, "create");
        n.g(onEntryRemoved, "onEntryRemoved");
        return new AnonymousClass4(i2, sizeOf, create, onEntryRemoved);
    }
}
