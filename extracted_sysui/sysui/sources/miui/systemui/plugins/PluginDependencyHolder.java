package miui.systemui.plugins;

import d1.InterfaceC0324c;
import j1.I;

/* JADX INFO: loaded from: classes4.dex */
public interface PluginDependencyHolder<T> {

    public interface Factory {
        <T> PluginDependencyHolder<T> create(InterfaceC0324c interfaceC0324c);
    }

    T get();

    I getInstance();

    T require();
}
