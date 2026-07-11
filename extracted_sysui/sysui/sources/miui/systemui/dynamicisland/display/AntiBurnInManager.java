package miui.systemui.dynamicisland.display;

import H0.d;
import I0.K;
import O0.a;
import O0.b;
import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import d1.InterfaceC0324c;
import g1.AbstractC0369g;
import g1.E;
import g1.InterfaceC0380l0;
import g1.Q;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.w;
import kotlin.jvm.internal.z;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewExtKt;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ConvenienceExtensionsKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class AntiBurnInManager {
    private static final int ENTER_EXTEND = 3;
    private static final int ENTER_TYPE_EXPOSE = 1;
    private static final int ENTER_TYPE_RESTORE_FINISHED = 2;
    private static final String TAG = "DynamicIslandBurnIn";
    private final d bigIslandHandler$delegate;
    private final Context context;
    private final Set<InterfaceC0324c> handledEventTypes;
    private DynamicIslandEvent handlingEvent;
    private final E scope;
    private final d smallIslandHandler$delegate;
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG_ANTI_BURN_IN = SystemProperties.getBoolean("debug.sysui.notif.island.antiburnin", false);
    private static final long MAX_EXPOSE_TIME_OUT = SystemProperties.getLong("debug.sysui.notif.island.antiburnin.timeout", 3600000);
    private static final long EXTEND_EXPOSE_TIME = SystemProperties.getLong("debug.sysui.notif.island.antiburnin.extend", 180000);
    private static final long EXTEND_THRESHOLD = SystemProperties.getLong("debug.sysui.notif.island.antiburnin.extend.threshold", 30000);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class BurnInStates {
        private static final /* synthetic */ a $ENTRIES;
        private static final /* synthetic */ BurnInStates[] $VALUES;
        public static final BurnInStates Normal = new BurnInStates("Normal", 0);
        public static final BurnInStates BurnIn = new BurnInStates("BurnIn", 1);
        public static final BurnInStates TempRestore = new BurnInStates("TempRestore", 2);
        public static final BurnInStates PauseExpose = new BurnInStates("PauseExpose", 3);
        public static final BurnInStates Stop = new BurnInStates("Stop", 4);

        private static final /* synthetic */ BurnInStates[] $values() {
            return new BurnInStates[]{Normal, BurnIn, TempRestore, PauseExpose, Stop};
        }

        static {
            BurnInStates[] burnInStatesArr$values = $values();
            $VALUES = burnInStatesArr$values;
            $ENTRIES = b.a(burnInStatesArr$values);
        }

        private BurnInStates(String str, int i2) {
        }

        public static a getEntries() {
            return $ENTRIES;
        }

        public static BurnInStates valueOf(String str) {
            return (BurnInStates) Enum.valueOf(BurnInStates.class, str);
        }

        public static BurnInStates[] values() {
            return (BurnInStates[]) $VALUES.clone();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long getEXTEND_EXPOSE_TIME() {
            return AntiBurnInManager.EXTEND_EXPOSE_TIME;
        }

        public final long getEXTEND_THRESHOLD() {
            return AntiBurnInManager.EXTEND_THRESHOLD;
        }

        public final long getMAX_EXPOSE_TIME_OUT() {
            return AntiBurnInManager.MAX_EXPOSE_TIME_OUT;
        }

        private Companion() {
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BurnInStates.values().length];
            try {
                iArr[BurnInStates.BurnIn.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BurnInStates.Normal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BurnInStates.TempRestore.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AntiBurnInManager(Context context, E0.a bigIslandStateHandler, E0.a smallIslandStateHandler, @Plugin E scope) {
        n.g(context, "context");
        n.g(bigIslandStateHandler, "bigIslandStateHandler");
        n.g(smallIslandStateHandler, "smallIslandStateHandler");
        n.g(scope, "scope");
        this.context = context;
        this.scope = scope;
        log("AntiBurnInController init, " + MAX_EXPOSE_TIME_OUT + ", " + EXTEND_EXPOSE_TIME + ", " + EXTEND_THRESHOLD + " ");
        this.smallIslandHandler$delegate = ConvenienceExtensionsKt.getKotlinLazy(smallIslandStateHandler);
        this.bigIslandHandler$delegate = ConvenienceExtensionsKt.getKotlinLazy(bigIslandStateHandler);
        this.handledEventTypes = K.d(z.b(DynamicIslandEvent.ClickDynamicIsland.class), z.b(DynamicIslandEvent.SwipeRight.class), z.b(DynamicIslandEvent.SwipeLeft.class), z.b(DynamicIslandEvent.IslandLongPressed.class), z.b(DynamicIslandEvent.AutoExpandIsland.class), z.b(DynamicIslandEvent.IslandTempHiddenChanged.class), z.b(DynamicIslandEvent.Collapse.class), z.b(DynamicIslandEvent.AddDynamicIsland.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long calculateRemainingTime() {
        long remainingUnitLong;
        if (getBigIslandHandler().getCurrent() == null) {
            Log.e(TAG, "wrong state calculateRemaining");
            remainingUnitLong = MAX_EXPOSE_TIME_OUT;
        } else if (isDualIsland()) {
            DynamicIslandContentView current = getSmallIslandHandler().getCurrent();
            n.d(current);
            long remainingUnitLong2 = DynamicIslandContentViewExtKt.getRemainingUnitLong(current);
            DynamicIslandContentView current2 = getBigIslandHandler().getCurrent();
            n.d(current2);
            remainingUnitLong = Math.max(remainingUnitLong2, DynamicIslandContentViewExtKt.getRemainingUnitLong(current2));
        } else {
            DynamicIslandContentView current3 = getBigIslandHandler().getCurrent();
            n.d(current3);
            remainingUnitLong = DynamicIslandContentViewExtKt.getRemainingUnitLong(current3);
        }
        boolean zIsDualIsland = isDualIsland();
        DynamicIslandContentView current4 = getBigIslandHandler().getCurrent();
        Boolean boolValueOf = current4 != null ? Boolean.valueOf(current4.getHasEverBurnedIn()) : null;
        DynamicIslandContentView current5 = getSmallIslandHandler().getCurrent();
        Log.d(TAG, "isDual=" + zIsDualIsland + ", bigEntered=" + boolValueOf + ", smallEntered=" + (current5 != null ? Boolean.valueOf(current5.getHasEverBurnedIn()) : null) + ", remaining: " + remainingUnitLong);
        return remainingUnitLong;
    }

    private final BurnInStates currentBurnInState() {
        if (getBigIslandHandler().getCurrent() != null) {
            DynamicIslandContentView current = getBigIslandHandler().getCurrent();
            if (current != null) {
                return current.getBurnInState();
            }
            return null;
        }
        if (getSmallIslandHandler().getCurrent() == null) {
            return BurnInStates.Normal;
        }
        DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
        if (current2 != null) {
            return current2.getBurnInState();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enter(int i2) {
        Log.i(TAG, "enter " + i2);
        getBigIslandHandler().handleEnterBurnIn();
        getSmallIslandHandler().handleEnterBurnIn();
    }

    private final boolean enterOrExitApp(DynamicIslandContentView dynamicIslandContentView) {
        String pkgName = dynamicIslandContentView.getPkgName();
        DynamicIslandContentView current = getBigIslandHandler().getCurrent();
        if (n.c(current != null ? current.getPkgName() : null, pkgName)) {
            return true;
        }
        DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
        return n.c(current2 != null ? current2.getPkgName() : null, pkgName);
    }

    private final void exit() {
        exitBurnIn();
    }

    private final void exitBurnIn() {
        Log.i(TAG, "exit current state:" + getCurrentState());
        getBigIslandHandler().handleExitBurnIn();
        getSmallIslandHandler().handleExitBurnIn();
    }

    private final void extendExpose() {
        if (supportBurnIn() && getBigIslandHandler().getCurrent() != null) {
            boolean zLastMinEvent = lastMinEvent();
            log("extend expose=" + zLastMinEvent + ", cur big:" + (getBigIslandHandler().getCurrent() != null) + ", cur small:" + (getSmallIslandHandler().getCurrent() != null));
            exit();
            if (zLastMinEvent) {
                updateCurrentExpo(getBigIslandHandler().getCurrent());
                updateCurrentExpo(getSmallIslandHandler().getCurrent());
                purgeJobs();
                InterfaceC0380l0 interfaceC0380l0B = AbstractC0369g.b(this.scope, Q.c().z(), null, new AntiBurnInManager$extendExpose$job$1(this, null), 2, null);
                DynamicIslandContentView current = getBigIslandHandler().getCurrent();
                if (current != null) {
                    current.setExtendJob(interfaceC0380l0B);
                }
                DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
                if (current2 == null) {
                    return;
                }
                current2.setExtendJob(interfaceC0380l0B);
            }
        }
    }

    private final void foo() {
        if (supportBurnIn()) {
            exit();
            DynamicIslandContentView current = getBigIslandHandler().getCurrent();
            BurnInStates burnInState = current != null ? current.getBurnInState() : null;
            DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
            Log.e(TAG, "error. current big: " + burnInState + ", small: " + (current2 != null ? current2.getBurnInState() : null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigIslandStateHandler getBigIslandHandler() {
        return (BigIslandStateHandler) this.bigIslandHandler$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getCurrentState() {
        return (getBigIslandHandler().getCurrent() == null || getSmallIslandHandler().getCurrent() == null) ? getBigIslandHandler().getCurrent() != null ? "single" : "wrong" : "dual";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SmallIslandStateHandler getSmallIslandHandler() {
        return (SmallIslandStateHandler) this.smallIslandHandler$delegate.getValue();
    }

    private final boolean isDualIsland() {
        return (getBigIslandHandler().getCurrent() == null || getSmallIslandHandler().getCurrent() == null) ? false : true;
    }

    private final boolean lastMinEvent() {
        w wVar = new w();
        lastMinEvent$checkIsland(this, wVar, getBigIslandHandler().getCurrent(), "big_" + getBigIslandHandler().getCurrentKey());
        lastMinEvent$checkIsland(this, wVar, getSmallIslandHandler().getCurrent(), "small_" + getSmallIslandHandler().getCurrentKey());
        return wVar.f5057a;
    }

    private static final void lastMinEvent$checkIsland(AntiBurnInManager antiBurnInManager, w wVar, DynamicIslandContentView dynamicIslandContentView, String str) {
        if (dynamicIslandContentView != null) {
            if (DynamicIslandContentViewExtKt.getRemainingUnitExact(dynamicIslandContentView) >= EXTEND_THRESHOLD) {
                dynamicIslandContentView = null;
            }
            if (dynamicIslandContentView != null) {
                antiBurnInManager.log(str + " Island is in last minute, remainingTime: " + DynamicIslandContentViewExtKt.getRemainingUnitLong(dynamicIslandContentView));
                wVar.f5057a = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void log(String str) {
        if (DEBUG_ANTI_BURN_IN) {
            Log.d(TAG, str);
        }
    }

    private final void preHandle(DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView) {
        if (dynamicIslandEvent instanceof DynamicIslandEvent.EnterApp) {
            if ((dynamicIslandContentView != null ? dynamicIslandContentView.getBurnInState() : null) == BurnInStates.BurnIn) {
                dynamicIslandContentView.animExitBurnIn(dynamicIslandContentView);
            }
        }
    }

    private final void purgeForPause(DynamicIslandContentView dynamicIslandContentView) {
        InterfaceC0380l0 enterJob = dynamicIslandContentView.getEnterJob();
        if (enterJob != null) {
            InterfaceC0380l0.a.a(enterJob, null, 1, null);
        }
        InterfaceC0380l0 restoreJob = dynamicIslandContentView.getRestoreJob();
        if (restoreJob != null) {
            InterfaceC0380l0.a.a(restoreJob, null, 1, null);
        }
        InterfaceC0380l0 extendJob = dynamicIslandContentView.getExtendJob();
        if (extendJob != null) {
            InterfaceC0380l0.a.a(extendJob, null, 1, null);
        }
    }

    private final void purgeJobs() {
        InterfaceC0380l0 restoreJob;
        InterfaceC0380l0 extendJob;
        InterfaceC0380l0 enterJob;
        InterfaceC0380l0 restoreJob2;
        InterfaceC0380l0 extendJob2;
        InterfaceC0380l0 enterJob2;
        DynamicIslandContentView current = getBigIslandHandler().getCurrent();
        if (current != null && (enterJob2 = current.getEnterJob()) != null) {
            InterfaceC0380l0.a.a(enterJob2, null, 1, null);
        }
        DynamicIslandContentView current2 = getBigIslandHandler().getCurrent();
        if (current2 != null && (extendJob2 = current2.getExtendJob()) != null) {
            InterfaceC0380l0.a.a(extendJob2, null, 1, null);
        }
        DynamicIslandContentView current3 = getBigIslandHandler().getCurrent();
        if (current3 != null && (restoreJob2 = current3.getRestoreJob()) != null) {
            InterfaceC0380l0.a.a(restoreJob2, null, 1, null);
        }
        DynamicIslandContentView current4 = getSmallIslandHandler().getCurrent();
        if (current4 != null && (enterJob = current4.getEnterJob()) != null) {
            InterfaceC0380l0.a.a(enterJob, null, 1, null);
        }
        DynamicIslandContentView current5 = getSmallIslandHandler().getCurrent();
        if (current5 != null && (extendJob = current5.getExtendJob()) != null) {
            InterfaceC0380l0.a.a(extendJob, null, 1, null);
        }
        DynamicIslandContentView current6 = getSmallIslandHandler().getCurrent();
        if (current6 == null || (restoreJob = current6.getRestoreJob()) == null) {
            return;
        }
        InterfaceC0380l0.a.a(restoreJob, null, 1, null);
    }

    private final void scheduleRestore() {
        if (supportBurnIn()) {
            Log.i(TAG, "schedule restore");
            exit();
            purgeJobs();
            DynamicIslandContentView current = getBigIslandHandler().getCurrent();
            if (current != null) {
                current.setBurnInState(BurnInStates.TempRestore);
            }
            DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
            if (current2 != null) {
                current2.setBurnInState(BurnInStates.TempRestore);
            }
            InterfaceC0380l0 interfaceC0380l0B = AbstractC0369g.b(this.scope, Q.c().z(), null, new AntiBurnInManager$scheduleRestore$job$1(this, null), 2, null);
            DynamicIslandContentView current3 = getBigIslandHandler().getCurrent();
            if (current3 != null) {
                current3.setRestoreJob(interfaceC0380l0B);
            }
            DynamicIslandContentView current4 = getSmallIslandHandler().getCurrent();
            if (current4 == null) {
                return;
            }
            current4.setRestoreJob(interfaceC0380l0B);
        }
    }

    private final boolean shouldHandle(DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView) {
        return this.handledEventTypes.contains(z.b(dynamicIslandEvent.getClass()));
    }

    public static /* synthetic */ boolean shouldHandle$default(AntiBurnInManager antiBurnInManager, DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            dynamicIslandContentView = null;
        }
        return antiBurnInManager.shouldHandle(dynamicIslandEvent, dynamicIslandContentView);
    }

    private final boolean shouldHandleApp(DynamicIslandEvent dynamicIslandEvent, DynamicIslandContentView dynamicIslandContentView) {
        if (dynamicIslandContentView == null) {
            return false;
        }
        return ((dynamicIslandEvent instanceof DynamicIslandEvent.ExitApp) || (dynamicIslandEvent instanceof DynamicIslandEvent.EnterApp) || (dynamicIslandEvent instanceof DynamicIslandEvent.ExitMiniWindow) || (dynamicIslandEvent instanceof DynamicIslandEvent.EnterMiniWindow)) && enterOrExitApp(dynamicIslandContentView);
    }

    private final void updateCurrentExpo(DynamicIslandContentView dynamicIslandContentView) {
        if (dynamicIslandContentView == null) {
            return;
        }
        if (dynamicIslandContentView.getHasEverBurnedIn()) {
            dynamicIslandContentView.setBurnInState(BurnInStates.TempRestore);
        } else {
            dynamicIslandContentView.setBurnInState(BurnInStates.Normal);
        }
    }

    public final void alignBurnInStates() {
        if (supportBurnIn()) {
            DynamicIslandContentView current = getBigIslandHandler().getCurrent();
            BurnInStates burnInState = current != null ? current.getBurnInState() : null;
            String currentKey = getBigIslandHandler().getCurrentKey();
            DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
            BurnInStates burnInState2 = current2 != null ? current2.getBurnInState() : null;
            Log.w(TAG, "align burnin " + burnInState + "-" + currentKey + ", " + burnInState2 + "-" + getSmallIslandHandler().getCurrentKey());
            if (getBigIslandHandler().getCurrent() == null || getSmallIslandHandler().getCurrent() == null) {
                return;
            }
            DynamicIslandContentView current3 = getBigIslandHandler().getCurrent();
            n.d(current3);
            BurnInStates burnInState3 = current3.getBurnInState();
            BurnInStates burnInStates = BurnInStates.BurnIn;
            boolean z2 = burnInState3 == burnInStates;
            DynamicIslandContentView current4 = getSmallIslandHandler().getCurrent();
            n.d(current4);
            boolean z3 = current4.getBurnInState() == burnInStates;
            DynamicIslandContentView current5 = getBigIslandHandler().getCurrent();
            n.d(current5);
            boolean zInBurnIn = current5.inBurnIn();
            DynamicIslandContentView current6 = getSmallIslandHandler().getCurrent();
            n.d(current6);
            boolean zInBurnIn2 = current6.inBurnIn();
            if (z2 == z3 && zInBurnIn == zInBurnIn2) {
                return;
            }
            Log.w(TAG, "burnin state aligned");
            exit();
        }
    }

    public final void handleEvent(DynamicIslandEvent event, DynamicIslandContentView dynamicIslandContentView) {
        n.g(event, "event");
        if (supportBurnIn()) {
            BurnInStates burnInStatesCurrentBurnInState = currentBurnInState();
            boolean zShouldHandle = shouldHandle(event, dynamicIslandContentView);
            boolean zShouldHandleApp = shouldHandleApp(event, dynamicIslandContentView);
            if (zShouldHandle || zShouldHandleApp) {
                String simpleName = event.getClass().getSimpleName();
                String islandKey = dynamicIslandContentView != null ? dynamicIslandContentView.getIslandKey() : null;
                DynamicIslandContentView current = getBigIslandHandler().getCurrent();
                BurnInStates burnInState = current != null ? current.getBurnInState() : null;
                DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
                log("Anti-burn-in handleEvent: " + simpleName + ", " + islandKey + ", \n big in " + burnInState + ", small in " + (current2 != null ? current2.getBurnInState() : null));
            }
            this.handlingEvent = event;
            preHandle(event, dynamicIslandContentView);
            if (!zShouldHandle && !zShouldHandleApp) {
                Log.w(TAG, "event: " + event.getClass().getSimpleName() + " not anti-burn-in related");
                return;
            }
            int i2 = burnInStatesCurrentBurnInState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[burnInStatesCurrentBurnInState.ordinal()];
            if (i2 == 1) {
                scheduleRestore();
            } else if (i2 == 2 || i2 == 3) {
                extendExpose();
            } else {
                foo();
            }
        }
    }

    public final void pauseExpose(DynamicIslandContentView view, long j2, String islandKey) {
        n.g(view, "view");
        n.g(islandKey, "islandKey");
        if (supportBurnIn()) {
            purgeForPause(view);
            int exposedUnit = AntiBurnInManagerKt.toExposedUnit(j2);
            String currentState = getCurrentState();
            DynamicIslandState state = view.getState();
            String simpleName = state != null ? state.getClass().getSimpleName() : null;
            DynamicIslandState lastState = view.getLastState();
            String simpleName2 = lastState != null ? lastState.getClass().getSimpleName() : null;
            Log.i(TAG, "pause for " + islandKey + ", " + currentState + "\n cur:[" + simpleName + "], last: [" + simpleName2 + "], exposed:" + j2 + ", inSec:" + exposedUnit + " hasBurnIn:" + view.getHasEverBurnedIn());
            if (!(view.getState() instanceof DynamicIslandState.BigIsland) && !(view.getState() instanceof DynamicIslandState.SmallIsland)) {
                view.setBurnInState(BurnInStates.PauseExpose);
            }
            if (view.getHasEverBurnedIn()) {
                view.setExposedBurnInUnit(view.getExposedBurnInUnit() + j2);
                view.setRemainingBurnInUnit(view.getRemainingBurnInUnit() - j2);
            } else {
                view.setExposedUnit(view.getExposedUnit() + j2);
                view.setRemainingUnit(view.getRemainingUnit() - j2);
            }
            Log.d(TAG, "update exposed " + view.getHasEverBurnedIn() + ", burnin(" + view.getExposedBurnInUnit() + ", " + view.getRemainingBurnInUnit() + "), normal(" + view.getExposedUnit() + ", " + view.getRemainingUnit() + ")");
        }
    }

    public final void scheduleEnter(String key, DynamicIslandContentView view) {
        n.g(key, "key");
        n.g(view, "view");
        if (supportBurnIn()) {
            log("schedule enter " + key);
            updateCurrentExpo(getBigIslandHandler().getCurrent());
            updateCurrentExpo(getSmallIslandHandler().getCurrent());
            alignBurnInStates();
            purgeJobs();
            InterfaceC0380l0 interfaceC0380l0B = AbstractC0369g.b(this.scope, Q.c().z(), null, new AntiBurnInManager$scheduleEnter$job$1(this, key, null), 2, null);
            DynamicIslandContentView current = getBigIslandHandler().getCurrent();
            if (current != null) {
                current.setEnterJob(interfaceC0380l0B);
            }
            DynamicIslandContentView current2 = getSmallIslandHandler().getCurrent();
            if (current2 == null) {
                return;
            }
            current2.setEnterJob(interfaceC0380l0B);
        }
    }

    public final boolean supportBurnIn() {
        return !CommonUtils.INSTANCE.getIS_TABLET();
    }
}
