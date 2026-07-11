package u1;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1.c f6868c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(q1.b kSerializer, q1.b vSerializer) {
        super(kSerializer, vSerializer, null);
        kotlin.jvm.internal.n.g(kSerializer, "kSerializer");
        kotlin.jvm.internal.n.g(vSerializer, "vSerializer");
        this.f6868c = new h(kSerializer.getDescriptor(), vSerializer.getDescriptor());
    }

    @Override // u1.l, q1.b, q1.a
    public s1.c getDescriptor() {
        return this.f6868c;
    }

    @Override // u1.a
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public LinkedHashMap a() {
        return new LinkedHashMap();
    }

    @Override // u1.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public int b(LinkedHashMap linkedHashMap) {
        kotlin.jvm.internal.n.g(linkedHashMap, "<this>");
        return linkedHashMap.size() * 2;
    }

    @Override // u1.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public void c(LinkedHashMap linkedHashMap, int i2) {
        kotlin.jvm.internal.n.g(linkedHashMap, "<this>");
    }

    @Override // u1.a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public LinkedHashMap i(Map map) {
        kotlin.jvm.internal.n.g(map, "<this>");
        LinkedHashMap linkedHashMap = map instanceof LinkedHashMap ? (LinkedHashMap) map : null;
        return linkedHashMap == null ? new LinkedHashMap(map) : linkedHashMap;
    }

    @Override // u1.a
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public Map j(LinkedHashMap linkedHashMap) {
        kotlin.jvm.internal.n.g(linkedHashMap, "<this>");
        return linkedHashMap;
    }
}
