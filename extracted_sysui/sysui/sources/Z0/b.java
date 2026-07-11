package Z0;

import d1.InterfaceC0330i;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b implements c {
    private Object value;

    public b(Object obj) {
        this.value = obj;
    }

    public abstract void afterChange(InterfaceC0330i interfaceC0330i, Object obj, Object obj2);

    public boolean beforeChange(InterfaceC0330i property, Object obj, Object obj2) {
        n.g(property, "property");
        return true;
    }

    @Override // Z0.c
    public Object getValue(Object obj, InterfaceC0330i property) {
        n.g(property, "property");
        return this.value;
    }

    @Override // Z0.c
    public void setValue(Object obj, InterfaceC0330i property, Object obj2) {
        n.g(property, "property");
        Object obj3 = this.value;
        if (beforeChange(property, obj3, obj2)) {
            this.value = obj2;
            afterChange(property, obj3, obj2);
        }
    }

    public String toString() {
        return "ObservableProperty(value=" + this.value + ')';
    }
}
