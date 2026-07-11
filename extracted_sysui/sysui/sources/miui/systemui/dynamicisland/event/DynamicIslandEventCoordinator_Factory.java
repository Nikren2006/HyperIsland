package miui.systemui.dynamicisland.event;

import F0.d;
import F0.e;
import android.content.Context;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import g1.E;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper;
import miui.systemui.dynamicisland.event.handler.AppStateHandler;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.HiddenStateHandler;
import miui.systemui.dynamicisland.event.handler.MiniWindowStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.window.AppLockController;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandEventCoordinator_Factory implements e {
    private final G0.a addEventCoordinatorProvider;
    private final G0.a animControllerProvider;
    private final G0.a antiBurnInManagerProvider;
    private final G0.a appEventCoordinatorProvider;
    private final G0.a appLockControllerProvider;
    private final G0.a appStateHandlerProvider;
    private final G0.a avoidScreenBurnInEventCoordinatorProvider;
    private final G0.a avoidScreenBurnInHelperProvider;
    private final G0.a bigIslandStateHandlerProvider;
    private final G0.a clickEventCoordinatorProvider;
    private final G0.a collapseEventCoordinatorProvider;
    private final G0.a configChangedEventCoordinatorProvider;
    private final G0.a ctxProvider;
    private final G0.a deletedEventCoordinatorProvider;
    private final G0.a dynamicIslandWindowStateProvider;
    private final G0.a expandedStateHandlerProvider;
    private final G0.a hiddenStateHandlerProvider;
    private final G0.a islandTempHiddenEventCoordinatorProvider;
    private final G0.a miniWindowEventCoordinatorProvider;
    private final G0.a miniWindowStateHandlerProvider;
    private final G0.a notificationSettingsManagerProvider;
    private final G0.a scopeProvider;
    private final G0.a smallIslandStateHandlerProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a swipeEventCoordinatorProvider;
    private final G0.a updateEventCoordinatorProvider;
    private final G0.a windowViewProvider;

    public DynamicIslandEventCoordinator_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18, G0.a aVar19, G0.a aVar20, G0.a aVar21, G0.a aVar22, G0.a aVar23, G0.a aVar24, G0.a aVar25, G0.a aVar26, G0.a aVar27) {
        this.scopeProvider = aVar;
        this.ctxProvider = aVar2;
        this.windowViewProvider = aVar3;
        this.avoidScreenBurnInHelperProvider = aVar4;
        this.expandedStateHandlerProvider = aVar5;
        this.bigIslandStateHandlerProvider = aVar6;
        this.smallIslandStateHandlerProvider = aVar7;
        this.hiddenStateHandlerProvider = aVar8;
        this.appStateHandlerProvider = aVar9;
        this.miniWindowStateHandlerProvider = aVar10;
        this.dynamicIslandWindowStateProvider = aVar11;
        this.superSaveModeControllerProvider = aVar12;
        this.appLockControllerProvider = aVar13;
        this.antiBurnInManagerProvider = aVar14;
        this.addEventCoordinatorProvider = aVar15;
        this.clickEventCoordinatorProvider = aVar16;
        this.deletedEventCoordinatorProvider = aVar17;
        this.collapseEventCoordinatorProvider = aVar18;
        this.swipeEventCoordinatorProvider = aVar19;
        this.updateEventCoordinatorProvider = aVar20;
        this.appEventCoordinatorProvider = aVar21;
        this.miniWindowEventCoordinatorProvider = aVar22;
        this.islandTempHiddenEventCoordinatorProvider = aVar23;
        this.configChangedEventCoordinatorProvider = aVar24;
        this.avoidScreenBurnInEventCoordinatorProvider = aVar25;
        this.animControllerProvider = aVar26;
        this.notificationSettingsManagerProvider = aVar27;
    }

    public static DynamicIslandEventCoordinator_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18, G0.a aVar19, G0.a aVar20, G0.a aVar21, G0.a aVar22, G0.a aVar23, G0.a aVar24, G0.a aVar25, G0.a aVar26, G0.a aVar27) {
        return new DynamicIslandEventCoordinator_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20, aVar21, aVar22, aVar23, aVar24, aVar25, aVar26, aVar27);
    }

    public static DynamicIslandEventCoordinator newInstance(E e2, Context context, DynamicIslandWindowView dynamicIslandWindowView, AvoidScreenBurnInHelper avoidScreenBurnInHelper, ExpandedStateHandler expandedStateHandler, BigIslandStateHandler bigIslandStateHandler, SmallIslandStateHandler smallIslandStateHandler, HiddenStateHandler hiddenStateHandler, AppStateHandler appStateHandler, MiniWindowStateHandler miniWindowStateHandler, DynamicIslandWindowState dynamicIslandWindowState, SuperSaveModeController superSaveModeController, AppLockController appLockController, AntiBurnInManager antiBurnInManager, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4, E0.a aVar5, E0.a aVar6, E0.a aVar7, E0.a aVar8, E0.a aVar9, E0.a aVar10, E0.a aVar11, E0.a aVar12, E0.a aVar13) {
        return new DynamicIslandEventCoordinator(e2, context, dynamicIslandWindowView, avoidScreenBurnInHelper, expandedStateHandler, bigIslandStateHandler, smallIslandStateHandler, hiddenStateHandler, appStateHandler, miniWindowStateHandler, dynamicIslandWindowState, superSaveModeController, appLockController, antiBurnInManager, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13);
    }

    @Override // G0.a
    public DynamicIslandEventCoordinator get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.ctxProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (AvoidScreenBurnInHelper) this.avoidScreenBurnInHelperProvider.get(), (ExpandedStateHandler) this.expandedStateHandlerProvider.get(), (BigIslandStateHandler) this.bigIslandStateHandlerProvider.get(), (SmallIslandStateHandler) this.smallIslandStateHandlerProvider.get(), (HiddenStateHandler) this.hiddenStateHandlerProvider.get(), (AppStateHandler) this.appStateHandlerProvider.get(), (MiniWindowStateHandler) this.miniWindowStateHandlerProvider.get(), (DynamicIslandWindowState) this.dynamicIslandWindowStateProvider.get(), (SuperSaveModeController) this.superSaveModeControllerProvider.get(), (AppLockController) this.appLockControllerProvider.get(), (AntiBurnInManager) this.antiBurnInManagerProvider.get(), d.a(this.addEventCoordinatorProvider), d.a(this.clickEventCoordinatorProvider), d.a(this.deletedEventCoordinatorProvider), d.a(this.collapseEventCoordinatorProvider), d.a(this.swipeEventCoordinatorProvider), d.a(this.updateEventCoordinatorProvider), d.a(this.appEventCoordinatorProvider), d.a(this.miniWindowEventCoordinatorProvider), d.a(this.islandTempHiddenEventCoordinatorProvider), d.a(this.configChangedEventCoordinatorProvider), d.a(this.avoidScreenBurnInEventCoordinatorProvider), d.a(this.animControllerProvider), d.a(this.notificationSettingsManagerProvider));
    }
}
