package androidx.collection;

import H0.s;
import androidx.annotation.IntRange;
import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import androidx.core.location.LocationRequestCompat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final Lock lock;
    private final LruHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(@IntRange(from = 1, to = LocationRequestCompat.PASSIVE_INTERVAL) int i2) {
        this.maxSize = i2;
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.map = new LruHashMap<>(0, 0.75f);
        this.lock = new Lock();
    }

    private final int safeSizeOf(K k2, V v2) {
        int iSizeOf = sizeOf(k2, v2);
        if (iSizeOf >= 0) {
            return iSizeOf;
        }
        throw new IllegalStateException(("Negative size: " + k2 + '=' + v2).toString());
    }

    public V create(K key) {
        n.g(key, "key");
        return null;
    }

    public final int createCount() {
        int i2;
        synchronized (this.lock) {
            i2 = this.createCount;
        }
        return i2;
    }

    public void entryRemoved(boolean z2, K key, V oldValue, V v2) {
        n.g(key, "key");
        n.g(oldValue, "oldValue");
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final int evictionCount() {
        int i2;
        synchronized (this.lock) {
            i2 = this.evictionCount;
        }
        return i2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final V get(K key) {
        V v2;
        n.g(key, "key");
        synchronized (this.lock) {
            V v3 = this.map.get(key);
            if (v3 != null) {
                this.hitCount++;
                return v3;
            }
            this.missCount++;
            V vCreate = create(key);
            if (vCreate == null) {
                return null;
            }
            synchronized (this.lock) {
                try {
                    this.createCount++;
                    v2 = (V) this.map.put(key, vCreate);
                    if (v2 != null) {
                        this.map.put(key, v2);
                    } else {
                        this.size += safeSizeOf(key, vCreate);
                        s sVar = s.f314a;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (v2 != null) {
                entryRemoved(false, key, vCreate, v2);
                return v2;
            }
            trimToSize(this.maxSize);
            return vCreate;
        }
    }

    public final int hitCount() {
        int i2;
        synchronized (this.lock) {
            i2 = this.hitCount;
        }
        return i2;
    }

    public final int maxSize() {
        int i2;
        synchronized (this.lock) {
            i2 = this.maxSize;
        }
        return i2;
    }

    public final int missCount() {
        int i2;
        synchronized (this.lock) {
            i2 = this.missCount;
        }
        return i2;
    }

    public final V put(K key, V value) {
        V vPut;
        n.g(key, "key");
        n.g(value, "value");
        synchronized (this.lock) {
            try {
                this.putCount++;
                this.size += safeSizeOf(key, value);
                vPut = this.map.put(key, value);
                if (vPut != null) {
                    this.size -= safeSizeOf(key, vPut);
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (vPut != null) {
            entryRemoved(false, key, vPut, value);
        }
        trimToSize(this.maxSize);
        return vPut;
    }

    public final int putCount() {
        int i2;
        synchronized (this.lock) {
            i2 = this.putCount;
        }
        return i2;
    }

    public final V remove(K key) {
        V vRemove;
        n.g(key, "key");
        synchronized (this.lock) {
            try {
                vRemove = this.map.remove(key);
                if (vRemove != null) {
                    this.size -= safeSizeOf(key, vRemove);
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (vRemove != null) {
            entryRemoved(false, key, vRemove, null);
        }
        return vRemove;
    }

    public void resize(@IntRange(from = 1, to = LocationRequestCompat.PASSIVE_INTERVAL) int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this.lock) {
            this.maxSize = i2;
            s sVar = s.f314a;
        }
        trimToSize(i2);
    }

    public final int size() {
        int i2;
        synchronized (this.lock) {
            i2 = this.size;
        }
        return i2;
    }

    public int sizeOf(K key, V value) {
        n.g(key, "key");
        n.g(value, "value");
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<K, V> snapshot() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        synchronized (this.lock) {
            try {
                Iterator<T> it = this.map.getEntries().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return linkedHashMap;
    }

    public String toString() {
        String str;
        synchronized (this.lock) {
            try {
                int i2 = this.hitCount;
                int i3 = this.missCount + i2;
                str = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + (i3 != 0 ? (i2 * 100) / i3 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0056, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L0:
            androidx.collection.internal.Lock r0 = r5.lock
            monitor-enter(r0)
            int r1 = r5.size     // Catch: java.lang.Throwable -> L14
            if (r1 < 0) goto L57
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch: java.lang.Throwable -> L14
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L14
            if (r1 == 0) goto L16
            int r1 = r5.size     // Catch: java.lang.Throwable -> L14
            if (r1 != 0) goto L57
            goto L16
        L14:
            r5 = move-exception
            goto L5f
        L16:
            int r1 = r5.size     // Catch: java.lang.Throwable -> L14
            if (r1 <= r6) goto L55
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch: java.lang.Throwable -> L14
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L14
            if (r1 == 0) goto L23
            goto L55
        L23:
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch: java.lang.Throwable -> L14
            java.util.Set r1 = r1.getEntries()     // Catch: java.lang.Throwable -> L14
            java.lang.Object r1 = I0.u.L(r1)     // Catch: java.lang.Throwable -> L14
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L14
            if (r1 != 0) goto L33
            monitor-exit(r0)
            return
        L33:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L14
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L14
            androidx.collection.internal.LruHashMap<K, V> r3 = r5.map     // Catch: java.lang.Throwable -> L14
            r3.remove(r2)     // Catch: java.lang.Throwable -> L14
            int r3 = r5.size     // Catch: java.lang.Throwable -> L14
            int r4 = r5.safeSizeOf(r2, r1)     // Catch: java.lang.Throwable -> L14
            int r3 = r3 - r4
            r5.size = r3     // Catch: java.lang.Throwable -> L14
            int r3 = r5.evictionCount     // Catch: java.lang.Throwable -> L14
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch: java.lang.Throwable -> L14
            monitor-exit(r0)
            r0 = 0
            r5.entryRemoved(r4, r2, r1, r0)
            goto L0
        L55:
            monitor-exit(r0)
            return
        L57:
            java.lang.String r5 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L14
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L14
            throw r6     // Catch: java.lang.Throwable -> L14
        L5f:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.trimToSize(int):void");
    }
}
