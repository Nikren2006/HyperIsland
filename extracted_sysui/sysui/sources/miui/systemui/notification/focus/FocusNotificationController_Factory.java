package miui.systemui.notification.focus;

import F0.d;
import F0.e;
import android.content.Context;
import android.os.Handler;
import g1.E;
import miui.systemui.notification.NotificationSettingsManager;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotificationController_Factory implements e {
    private final G0.a contextProvider;
    private final G0.a focusNotifPreHandlerProvider;
    private final G0.a focusNotifUtilsProvider;
    private final G0.a hideDeletedFocusControllerProvider;
    private final G0.a lottieProgressManagerProvider;
    private final G0.a mainHandlerProvider;
    private final G0.a notificationChronometerManagerProvider;
    private final G0.a notificationSettingsManagerProvider;
    private final G0.a scopeProvider;
    private final G0.a signatureCheckerProvider;
    private final G0.a windowViewCreatorProvider;

    public FocusNotificationController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.mainHandlerProvider = aVar3;
        this.windowViewCreatorProvider = aVar4;
        this.hideDeletedFocusControllerProvider = aVar5;
        this.notificationChronometerManagerProvider = aVar6;
        this.notificationSettingsManagerProvider = aVar7;
        this.focusNotifPreHandlerProvider = aVar8;
        this.lottieProgressManagerProvider = aVar9;
        this.focusNotifUtilsProvider = aVar10;
        this.signatureCheckerProvider = aVar11;
    }

    public static FocusNotificationController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11) {
        return new FocusNotificationController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11);
    }

    public static FocusNotificationController newInstance(E e2, Context context, Handler handler, E0.a aVar, HideDeletedFocusController hideDeletedFocusController, E0.a aVar2, NotificationSettingsManager notificationSettingsManager, FocusNotifPreHandler focusNotifPreHandler, E0.a aVar3, FocusNotifUtils focusNotifUtils, SignatureChecker signatureChecker) {
        return new FocusNotificationController(e2, context, handler, aVar, hideDeletedFocusController, aVar2, notificationSettingsManager, focusNotifPreHandler, aVar3, focusNotifUtils, signatureChecker);
    }

    @Override // G0.a
    public FocusNotificationController get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (Handler) this.mainHandlerProvider.get(), d.a(this.windowViewCreatorProvider), (HideDeletedFocusController) this.hideDeletedFocusControllerProvider.get(), d.a(this.notificationChronometerManagerProvider), (NotificationSettingsManager) this.notificationSettingsManagerProvider.get(), (FocusNotifPreHandler) this.focusNotifPreHandlerProvider.get(), d.a(this.lottieProgressManagerProvider), (FocusNotifUtils) this.focusNotifUtilsProvider.get(), (SignatureChecker) this.signatureCheckerProvider.get());
    }
}
