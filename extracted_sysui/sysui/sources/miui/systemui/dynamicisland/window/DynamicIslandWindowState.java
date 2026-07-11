package miui.systemui.dynamicisland.window;

import H0.s;
import android.content.Context;
import android.util.Log;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import j1.u;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.FoldUtils;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandWindowState {
    private u bouncerShowing;
    private Boolean configChange;
    private u controlCenterExpanded;
    private u deskTopAnimating;
    private boolean expanded;
    private final DynamicIslandExternalStateRepository externalRepository;
    private boolean isAodOn;
    private boolean isFullAodOn;
    private boolean isTinyScreen;
    private boolean keyguardShowing;
    private boolean lastTinyScreenStatus;
    private u miPlayShow;
    private u notificationAppearance;
    private boolean notificationVisible;
    private final E scope;
    private u screenLocked;
    private boolean screenLockedChange;
    private u screenPinning;
    private u showNotificationIcons;
    private u showOncePropIsland;
    private u statusBarDisappearance;
    private u statusBarViewShowing;
    private final I tempHidden;
    private Boolean tempHiddenChange;
    private TempHiddenType tempHiddenType;
    private String topActivityPkg;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class TempHiddenType {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ TempHiddenType[] $VALUES;
        public static final TempHiddenType SCREEN_LOCKED = new TempHiddenType("SCREEN_LOCKED", 0);
        public static final TempHiddenType NOTIFICATION_APPEARANCE = new TempHiddenType("NOTIFICATION_APPEARANCE", 1);
        public static final TempHiddenType MIPLAY_SHOW = new TempHiddenType("MIPLAY_SHOW", 2);
        public static final TempHiddenType CONTROL_CENTER_EXPANDED = new TempHiddenType("CONTROL_CENTER_EXPANDED", 3);
        public static final TempHiddenType STATUS_BAR_DISAPPEARANCE = new TempHiddenType("STATUS_BAR_DISAPPEARANCE", 4);
        public static final TempHiddenType DESKTOP_ANIMATING = new TempHiddenType("DESKTOP_ANIMATING", 5);
        public static final TempHiddenType SCREEN_PINNING_ACTIVE = new TempHiddenType("SCREEN_PINNING_ACTIVE", 6);
        public static final TempHiddenType SHOW_NOTIFICATION_ICONS = new TempHiddenType("SHOW_NOTIFICATION_ICONS", 7);
        public static final TempHiddenType SHOW_ONCE_PROP_ISLAND = new TempHiddenType("SHOW_ONCE_PROP_ISLAND", 8);
        public static final TempHiddenType BOUNCER_SHOWING = new TempHiddenType("BOUNCER_SHOWING", 9);

        private static final /* synthetic */ TempHiddenType[] $values() {
            return new TempHiddenType[]{SCREEN_LOCKED, NOTIFICATION_APPEARANCE, MIPLAY_SHOW, CONTROL_CENTER_EXPANDED, STATUS_BAR_DISAPPEARANCE, DESKTOP_ANIMATING, SCREEN_PINNING_ACTIVE, SHOW_NOTIFICATION_ICONS, SHOW_ONCE_PROP_ISLAND, BOUNCER_SHOWING};
        }

        static {
            TempHiddenType[] tempHiddenTypeArr$values = $values();
            $VALUES = tempHiddenTypeArr$values;
            $ENTRIES = O0.b.a(tempHiddenTypeArr$values);
        }

        private TempHiddenType(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static TempHiddenType valueOf(String str) {
            return (TempHiddenType) Enum.valueOf(TempHiddenType.class, str);
        }

        public static TempHiddenType[] values() {
            return (TempHiddenType[]) $VALUES.clone();
        }
    }

    public DynamicIslandWindowState(@DynamicIsland E scope, DynamicIslandExternalStateRepository externalRepository) {
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(externalRepository, "externalRepository");
        this.scope = scope;
        this.externalRepository = externalRepository;
        Boolean bool = Boolean.TRUE;
        this.showNotificationIcons = K.a(bool);
        Boolean bool2 = Boolean.FALSE;
        this.controlCenterExpanded = K.a(bool2);
        this.notificationAppearance = K.a(bool2);
        this.miPlayShow = K.a(bool2);
        this.statusBarDisappearance = K.a(bool2);
        this.deskTopAnimating = K.a(bool2);
        this.screenLocked = K.a(bool2);
        this.bouncerShowing = K.a(bool2);
        this.showOncePropIsland = K.a(bool2);
        this.statusBarViewShowing = K.a(bool);
        u uVarA = K.a(bool2);
        this.screenPinning = uVarA;
        this.topActivityPkg = "";
        final InterfaceC0418f[] interfaceC0418fArr = {this.notificationAppearance, this.screenLocked, this.statusBarDisappearance, this.controlCenterExpanded, this.deskTopAnimating, this.bouncerShowing, this.miPlayShow, uVarA};
        this.tempHidden = AbstractC0420h.B(AbstractC0420h.j(new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowState$special$$inlined$combine$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowState$special$$inlined$combine$1$2, reason: invalid class name */
            public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function0 {
                final /* synthetic */ InterfaceC0418f[] $flows;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(InterfaceC0418f[] interfaceC0418fArr) {
                    super(0);
                    this.$flows = interfaceC0418fArr;
                }

                @Override // kotlin.jvm.functions.Function0
                public final Boolean[] invoke() {
                    return new Boolean[this.$flows.length];
                }
            }

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowState$special$$inlined$combine$1$3, reason: invalid class name */
            @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowState$special$$inlined$combine$1$3", f = "DynamicIslandWindowState.kt", l = {238}, m = "invokeSuspend")
            public static final class AnonymousClass3 extends N0.l implements Function3 {
                private /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                public AnonymousClass3(L0.d dVar) {
                    super(3, dVar);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(InterfaceC0419g interfaceC0419g, Boolean[] boolArr, L0.d dVar) {
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(dVar);
                    anonymousClass3.L$0 = interfaceC0419g;
                    anonymousClass3.L$1 = boolArr;
                    return anonymousClass3.invokeSuspend(s.f314a);
                }

                @Override // N0.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Object objC = M0.c.c();
                    int i2 = this.label;
                    if (i2 == 0) {
                        H0.k.b(obj);
                        InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
                        Boolean[] boolArr = (Boolean[]) ((Object[]) this.L$1);
                        boolean zBooleanValue = boolArr[0].booleanValue();
                        boolean zBooleanValue2 = boolArr[1].booleanValue();
                        boolean zBooleanValue3 = boolArr[2].booleanValue();
                        boolean zBooleanValue4 = boolArr[3].booleanValue();
                        boolean zBooleanValue5 = boolArr[4].booleanValue();
                        boolean zBooleanValue6 = boolArr[5].booleanValue();
                        boolean zBooleanValue7 = boolArr[6].booleanValue();
                        boolean zBooleanValue8 = boolArr[7].booleanValue();
                        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "tempHidden1: screenLocked:" + zBooleanValue2 + " controlCenterExpanded:" + zBooleanValue4 + " notificationAppearance:" + zBooleanValue + " statusBarDisappearance:" + zBooleanValue3 + " bouncerShowing:" + zBooleanValue6 + " deskTopAnimating:" + zBooleanValue5 + "miplayShow:" + zBooleanValue7 + "screenPinning:" + zBooleanValue8);
                        Boolean boolA = N0.b.a(!(zBooleanValue2 || !zBooleanValue || zBooleanValue8) || (zBooleanValue3 && !zBooleanValue8) || zBooleanValue7 || (((zBooleanValue2 || zBooleanValue6) && !zBooleanValue4) || zBooleanValue5));
                        this.label = 1;
                        if (interfaceC0419g.emit(boolA, this) == objC) {
                            return objC;
                        }
                    } else {
                        if (i2 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        H0.k.b(obj);
                    }
                    return s.f314a;
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                InterfaceC0418f[] interfaceC0418fArr2 = interfaceC0418fArr;
                Object objA = k1.k.a(interfaceC0419g, interfaceC0418fArr2, new AnonymousClass2(interfaceC0418fArr2), new AnonymousClass3(null), dVar);
                return objA == M0.c.c() ? objA : s.f314a;
            }
        }, this.showNotificationIcons, this.screenLocked, this.showOncePropIsland, new DynamicIslandWindowState$tempHidden$2$1(null)), scope, j1.E.f4648a.c(), bool2);
    }

    public final boolean canSwipe() {
        return !((Boolean) this.statusBarDisappearance.getValue()).booleanValue() || ((Boolean) this.screenPinning.getValue()).booleanValue();
    }

    public final u getBouncerShowing() {
        return this.bouncerShowing;
    }

    public final Boolean getConfigChange() {
        return this.configChange;
    }

    public final u getControlCenterExpanded() {
        return this.controlCenterExpanded;
    }

    public final u getDeskTopAnimating() {
        return this.deskTopAnimating;
    }

    public final boolean getExpanded() {
        return this.expanded;
    }

    public final DynamicIslandExternalStateRepository getExternalRepository() {
        return this.externalRepository;
    }

    public final boolean getKeyguardShowing() {
        return this.keyguardShowing;
    }

    public final boolean getLastTinyScreenStatus() {
        return this.lastTinyScreenStatus;
    }

    public final u getMiPlayShow() {
        return this.miPlayShow;
    }

    public final u getNotificationAppearance() {
        return this.notificationAppearance;
    }

    public final boolean getNotificationVisible() {
        return this.notificationVisible;
    }

    public final u getScreenLocked() {
        return this.screenLocked;
    }

    public final boolean getScreenLockedChange() {
        return this.screenLockedChange;
    }

    public final u getScreenPinning() {
        return this.screenPinning;
    }

    public final u getShowNotificationIcons() {
        return this.showNotificationIcons;
    }

    public final u getShowOncePropIsland() {
        return this.showOncePropIsland;
    }

    public final u getStatusBarDisappearance() {
        return this.statusBarDisappearance;
    }

    public final u getStatusBarViewShowing() {
        return this.statusBarViewShowing;
    }

    public final I getTempHidden() {
        return this.tempHidden;
    }

    public final Boolean getTempHiddenChange() {
        return this.tempHiddenChange;
    }

    public final TempHiddenType getTempHiddenType() {
        return this.tempHiddenType;
    }

    public final String getTopActivityPkg() {
        return this.topActivityPkg;
    }

    public final boolean isAdaptDesktopAnimation(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return (!ConfigUtils.INSTANCE.isLandscape(context) || FoldUtils.INSTANCE.isFoldScreenLayoutLarge(context) || CommonUtils.INSTANCE.getIS_TABLET()) ? false : true;
    }

    public final boolean isAodOn() {
        return this.isAodOn;
    }

    public final boolean isFullAodOn() {
        return this.isFullAodOn;
    }

    public final boolean isTempHidden(Integer num) {
        boolean z2 = (((Boolean) this.tempHidden.getValue()).booleanValue() || !((Boolean) this.statusBarViewShowing.getValue()).booleanValue()) && (num == null || num.intValue() != 0 || !((Boolean) this.externalRepository.isDeviceInteractive().getValue()).booleanValue() || ((Boolean) this.bouncerShowing.getValue()).booleanValue());
        return (this.screenLockedChange && ((Boolean) this.screenPinning.getValue()).booleanValue()) ? z2 : z2 && !((Boolean) this.screenPinning.getValue()).booleanValue();
    }

    public final boolean isTinyScreen() {
        return this.isTinyScreen;
    }

    public final boolean isToScreenLockNoAnimation() {
        return ((Boolean) this.screenLocked.getValue()).booleanValue() && this.screenLockedChange && !(this.isAodOn && this.isFullAodOn);
    }

    public final boolean isToUnLockAnimation() {
        return !((Boolean) this.screenLocked.getValue()).booleanValue() && this.screenLockedChange;
    }

    public final void setAodOn(boolean z2) {
        this.isAodOn = z2;
    }

    public final void setBouncerShowing(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.bouncerShowing = uVar;
    }

    public final void setConfigChange(Boolean bool) {
        this.configChange = bool;
    }

    public final void setControlCenterExpanded(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.controlCenterExpanded = uVar;
    }

    public final void setDeskTopAnimating(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.deskTopAnimating = uVar;
    }

    public final void setExpanded(boolean z2) {
        this.expanded = z2;
    }

    public final void setFullAodOn(boolean z2) {
        this.isFullAodOn = z2;
    }

    public final void setKeyguardShowing(boolean z2) {
        this.keyguardShowing = z2;
    }

    public final void setLastTinyScreenStatus(boolean z2) {
        this.lastTinyScreenStatus = z2;
    }

    public final void setMiPlayShow(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.miPlayShow = uVar;
    }

    public final void setNotificationAppearance(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.notificationAppearance = uVar;
    }

    public final void setNotificationVisible(boolean z2) {
        this.notificationVisible = z2;
    }

    public final void setScreenLocked(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.screenLocked = uVar;
    }

    public final void setScreenLockedChange(boolean z2) {
        this.screenLockedChange = z2;
    }

    public final void setScreenPinning(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.screenPinning = uVar;
    }

    public final void setShowNotificationIcons(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.showNotificationIcons = uVar;
    }

    public final void setShowOncePropIsland(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.showOncePropIsland = uVar;
    }

    public final void setStatusBarDisappearance(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.statusBarDisappearance = uVar;
    }

    public final void setStatusBarViewShowing(u uVar) {
        kotlin.jvm.internal.n.g(uVar, "<set-?>");
        this.statusBarViewShowing = uVar;
    }

    public final void setTempHiddenChange(Boolean bool) {
        this.tempHiddenChange = bool;
    }

    public final void setTempHiddenType(TempHiddenType tempHiddenType) {
        this.tempHiddenType = tempHiddenType;
    }

    public final void setTinyScreen(boolean z2) {
        this.isTinyScreen = z2;
    }

    public final void setTopActivityPkg(String str) {
        this.topActivityPkg = str;
    }
}
