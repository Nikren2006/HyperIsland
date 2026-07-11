package kotlin.jvm.internal;

import d1.EnumC0332k;
import d1.InterfaceC0323b;
import d1.InterfaceC0325d;
import d1.InterfaceC0331j;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: kotlin.jvm.internal.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0430d implements InterfaceC0323b, Serializable {
    public static final Object NO_RECEIVER = a.f5044a;
    private final boolean isTopLevel;
    private final String name;
    private final Class owner;
    protected final Object receiver;
    private transient InterfaceC0323b reflected;
    private final String signature;

    /* JADX INFO: renamed from: kotlin.jvm.internal.d$a */
    public static class a implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5044a = new a();
    }

    public AbstractC0430d(Object obj, Class cls, String str, String str2, boolean z2) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z2;
    }

    @Override // d1.InterfaceC0323b
    public Object call(Object... objArr) {
        return getReflected().call(objArr);
    }

    @Override // d1.InterfaceC0323b
    public Object callBy(Map map) {
        return getReflected().callBy(map);
    }

    public InterfaceC0323b compute() {
        InterfaceC0323b interfaceC0323b = this.reflected;
        if (interfaceC0323b != null) {
            return interfaceC0323b;
        }
        InterfaceC0323b interfaceC0323bComputeReflected = computeReflected();
        this.reflected = interfaceC0323bComputeReflected;
        return interfaceC0323bComputeReflected;
    }

    public abstract InterfaceC0323b computeReflected();

    @Override // d1.InterfaceC0322a
    public List<Annotation> getAnnotations() {
        return getReflected().getAnnotations();
    }

    public Object getBoundReceiver() {
        return this.receiver;
    }

    @Override // d1.InterfaceC0323b
    public String getName() {
        return this.name;
    }

    public InterfaceC0325d getOwner() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        return this.isTopLevel ? z.c(cls) : z.b(cls);
    }

    @Override // d1.InterfaceC0323b
    public List<Object> getParameters() {
        return getReflected().getParameters();
    }

    public InterfaceC0323b getReflected() {
        InterfaceC0323b interfaceC0323bCompute = compute();
        if (interfaceC0323bCompute != this) {
            return interfaceC0323bCompute;
        }
        throw new U0.b();
    }

    @Override // d1.InterfaceC0323b
    public InterfaceC0331j getReturnType() {
        getReflected().getReturnType();
        return null;
    }

    public String getSignature() {
        return this.signature;
    }

    @Override // d1.InterfaceC0323b
    public List<Object> getTypeParameters() {
        return getReflected().getTypeParameters();
    }

    @Override // d1.InterfaceC0323b
    public EnumC0332k getVisibility() {
        return getReflected().getVisibility();
    }

    @Override // d1.InterfaceC0323b
    public boolean isAbstract() {
        return getReflected().isAbstract();
    }

    @Override // d1.InterfaceC0323b
    public boolean isFinal() {
        return getReflected().isFinal();
    }

    @Override // d1.InterfaceC0323b
    public boolean isOpen() {
        return getReflected().isOpen();
    }
}
