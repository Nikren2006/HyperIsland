package miui.systemui.dagger;

import miui.systemui.controlcenter.dagger.ControlCenterViewInstanceCreator;

/* JADX INFO: loaded from: classes.dex */
public interface ViewCreator {
    ControlCenterViewInstanceCreator createControlCenterViewInstanceCreator(ViewAttributeProvider viewAttributeProvider);

    PluginViewInstanceCreator createPluginViewInstanceCreator(ViewAttributeProvider viewAttributeProvider);
}
