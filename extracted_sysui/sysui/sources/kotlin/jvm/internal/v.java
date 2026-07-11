package kotlin.jvm.internal;

import d1.InterfaceC0323b;
import d1.InterfaceC0330i;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v extends AbstractC0430d implements InterfaceC0330i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f5056a;

    public v(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        this.f5056a = (i2 & 2) == 2;
    }

    @Override // kotlin.jvm.internal.AbstractC0430d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public InterfaceC0330i getReflected() {
        if (this.f5056a) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties");
        }
        return (InterfaceC0330i) super.getReflected();
    }

    @Override // kotlin.jvm.internal.AbstractC0430d
    public InterfaceC0323b compute() {
        return this.f5056a ? this : super.compute();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof v) {
            v vVar = (v) obj;
            return getOwner().equals(vVar.getOwner()) && getName().equals(vVar.getName()) && getSignature().equals(vVar.getSignature()) && n.c(getBoundReceiver(), vVar.getBoundReceiver());
        }
        if (obj instanceof InterfaceC0330i) {
            return obj.equals(compute());
        }
        return false;
    }

    public int hashCode() {
        return (((getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        InterfaceC0323b interfaceC0323bCompute = compute();
        if (interfaceC0323bCompute != this) {
            return interfaceC0323bCompute.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }
}
