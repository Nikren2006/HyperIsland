package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.settings.GlobalSettings;

/* JADX INFO: loaded from: classes.dex */
public final class BaseMiPlayController_MembersInjector implements E0.b {
    private final G0.a _MIPLAY_AUDIO_MANAGERProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a contextProvider;
    private final G0.a globalSettingsProvider;
    private final G0.a mainHandlerProvider;
    private final G0.a systemUIContextProvider;

    public BaseMiPlayController_MembersInjector(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.contextProvider = aVar;
        this.systemUIContextProvider = aVar2;
        this.mainHandlerProvider = aVar3;
        this._MIPLAY_AUDIO_MANAGERProvider = aVar4;
        this.broadcastDispatcherProvider = aVar5;
        this.globalSettingsProvider = aVar6;
    }

    public static E0.b create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new BaseMiPlayController_MembersInjector(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static void injectBroadcastDispatcher(BaseMiPlayController baseMiPlayController, BroadcastDispatcher broadcastDispatcher) {
        baseMiPlayController.broadcastDispatcher = broadcastDispatcher;
    }

    public static void injectContext(BaseMiPlayController baseMiPlayController, Context context) {
        baseMiPlayController.context = context;
    }

    public static void injectGlobalSettings(BaseMiPlayController baseMiPlayController, GlobalSettings globalSettings) {
        baseMiPlayController.globalSettings = globalSettings;
    }

    @Main
    public static void injectMainHandler(BaseMiPlayController baseMiPlayController, Handler handler) {
        baseMiPlayController.mainHandler = handler;
    }

    @SystemUI
    public static void injectSystemUIContext(BaseMiPlayController baseMiPlayController, Context context) {
        baseMiPlayController.systemUIContext = context;
    }

    public static void inject_MIPLAY_AUDIO_MANAGER(BaseMiPlayController baseMiPlayController, E0.a aVar) {
        baseMiPlayController._MIPLAY_AUDIO_MANAGER = aVar;
    }

    public void injectMembers(BaseMiPlayController baseMiPlayController) {
        injectContext(baseMiPlayController, (Context) this.contextProvider.get());
        injectSystemUIContext(baseMiPlayController, (Context) this.systemUIContextProvider.get());
        injectMainHandler(baseMiPlayController, (Handler) this.mainHandlerProvider.get());
        inject_MIPLAY_AUDIO_MANAGER(baseMiPlayController, F0.d.a(this._MIPLAY_AUDIO_MANAGERProvider));
        injectBroadcastDispatcher(baseMiPlayController, (BroadcastDispatcher) this.broadcastDispatcherProvider.get());
        injectGlobalSettings(baseMiPlayController, (GlobalSettings) this.globalSettingsProvider.get());
    }
}
