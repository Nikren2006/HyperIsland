package I0;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public final class D implements C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f318b;

    public D(Map map, Function1 function1) {
        kotlin.jvm.internal.n.g(map, "map");
        kotlin.jvm.internal.n.g(function1, "default");
        this.f317a = map;
        this.f318b = function1;
    }

    @Override // I0.C
    public Map a() {
        return this.f317a;
    }

    @Override // I0.C
    public Object b(Object obj) {
        Map mapA = a();
        Object obj2 = mapA.get(obj);
        return (obj2 != null || mapA.containsKey(obj)) ? obj2 : this.f318b.invoke(obj);
    }

    public Set c() {
        return a().entrySet();
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return a().containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return a().containsValue(obj);
    }

    public Set d() {
        return a().keySet();
    }

    public int e() {
        return a().size();
    }

    @Override // java.util.Map
    public final /* bridge */ Set entrySet() {
        return c();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return a().equals(obj);
    }

    public Collection f() {
        return a().values();
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return a().get(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return a().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return a().isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set keySet() {
        return d();
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return e();
    }

    public String toString() {
        return a().toString();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection values() {
        return f();
    }
}
