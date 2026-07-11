package miui.systemui.flashlight;

import miui.systemui.util.TalkBackUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController_MembersInjector implements E0.b {
    private final G0.a talkBackUtilsProvider;

    public MiFlashlightController_MembersInjector(G0.a aVar) {
        this.talkBackUtilsProvider = aVar;
    }

    public static E0.b create(G0.a aVar) {
        return new MiFlashlightController_MembersInjector(aVar);
    }

    public static void injectTalkBackUtils(MiFlashlightController miFlashlightController, TalkBackUtils talkBackUtils) {
        miFlashlightController.talkBackUtils = talkBackUtils;
    }

    public void injectMembers(MiFlashlightController miFlashlightController) {
        injectTalkBackUtils(miFlashlightController, (TalkBackUtils) this.talkBackUtilsProvider.get());
    }
}
