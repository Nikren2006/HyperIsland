package x1;

import d1.InterfaceC0324c;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f7096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f7097b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f7098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map f7099d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f7100e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Map class2ContextualFactory, Map polyBase2Serializers, Map polyBase2DefaultSerializerProvider, Map polyBase2NamedSerializers, Map polyBase2DefaultDeserializerProvider) {
        super(null);
        n.g(class2ContextualFactory, "class2ContextualFactory");
        n.g(polyBase2Serializers, "polyBase2Serializers");
        n.g(polyBase2DefaultSerializerProvider, "polyBase2DefaultSerializerProvider");
        n.g(polyBase2NamedSerializers, "polyBase2NamedSerializers");
        n.g(polyBase2DefaultDeserializerProvider, "polyBase2DefaultDeserializerProvider");
        this.f7096a = class2ContextualFactory;
        this.f7097b = polyBase2Serializers;
        this.f7098c = polyBase2DefaultSerializerProvider;
        this.f7099d = polyBase2NamedSerializers;
        this.f7100e = polyBase2DefaultDeserializerProvider;
    }

    @Override // x1.b
    public q1.b a(InterfaceC0324c kClass, List typeArgumentsSerializers) {
        n.g(kClass, "kClass");
        n.g(typeArgumentsSerializers, "typeArgumentsSerializers");
        android.support.v4.media.a.a(this.f7096a.get(kClass));
        return null;
    }
}
