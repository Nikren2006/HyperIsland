package kotlin.jvm.internal;

import d1.InterfaceC0323b;
import d1.InterfaceC0326e;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k extends AbstractC0430d implements j, InterfaceC0326e {
    private final int arity;
    private final int flags;

    public k(int i2, Object obj, Class cls, String str, String str2, int i3) {
        super(obj, cls, str, str2, (i3 & 1) == 1);
        this.arity = i2;
        this.flags = i3 >> 1;
    }

    @Override // kotlin.jvm.internal.AbstractC0430d
    public InterfaceC0323b computeReflected() {
        return z.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof k) {
            k kVar = (k) obj;
            return getName().equals(kVar.getName()) && getSignature().equals(kVar.getSignature()) && this.flags == kVar.flags && this.arity == kVar.arity && n.c(getBoundReceiver(), kVar.getBoundReceiver()) && n.c(getOwner(), kVar.getOwner());
        }
        if (obj instanceof InterfaceC0326e) {
            return obj.equals(compute());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.j
    public int getArity() {
        return this.arity;
    }

    public int hashCode() {
        return (((getOwner() == null ? 0 : getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    @Override // d1.InterfaceC0326e
    public boolean isExternal() {
        return getReflected().isExternal();
    }

    @Override // d1.InterfaceC0326e
    public boolean isInfix() {
        return getReflected().isInfix();
    }

    @Override // d1.InterfaceC0326e
    public boolean isInline() {
        return getReflected().isInline();
    }

    @Override // d1.InterfaceC0326e
    public boolean isOperator() {
        return getReflected().isOperator();
    }

    @Override // d1.InterfaceC0326e
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    public String toString() {
        InterfaceC0323b interfaceC0323bCompute = compute();
        if (interfaceC0323bCompute != this) {
            return interfaceC0323bCompute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }

    @Override // kotlin.jvm.internal.AbstractC0430d
    public InterfaceC0326e getReflected() {
        return (InterfaceC0326e) super.getReflected();
    }
}
