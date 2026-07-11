package miui.systemui.controlcenter.events;

import A0.a;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.os.SystemClock;
import com.miui.misight.MiEvent;
import com.miui.misight.MiSight;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterScenarioTracker {
    public static final long COLLAPSE_CONTROL_CENTER_RESPONSE_TIME1 = 325;
    public static final long COLLAPSE_CONTROL_CENTER_RESPONSE_TIME2 = 326;
    public static final long CONTROL_CENTER_ENTER_SECONDARY_ANIMATION = 411;
    public static final long CONTROL_CENTER_ENTER_SECONDARY_RESPONSE = 410;
    public static final int CONTROL_CENTER_EXPANDING_FAILED = 923020001;
    public static final int ERROR_TYPE = 1;
    public static final long EXPAND_CONTROL_CENTER_MIPLAY_PANEL_ANIMATION = 356;
    public static final long EXPAND_CONTROL_CENTER_RESPONSE_TIME1 = 323;
    public static final long EXPAND_CONTROL_CENTER_RESPONSE_TIME2 = 324;
    public static final long EXPAND_CONTROL_CENTER_VOLUME_PANEL_ANIMATION = 355;
    public static final ControlCenterScenarioTracker INSTANCE = new ControlCenterScenarioTracker();
    public static final String PANEL_NAME = "panelName";
    public static final int SECONDARY_PANEL_ENTRY_FAILED = 923021001;
    public static final String TYPE = "type";

    /* JADX INFO: renamed from: miui.systemui.controlcenter.events.ControlCenterScenarioTracker$setControlCenterScenarioState$1, reason: invalid class name */
    @f(c = "miui.systemui.controlcenter.events.ControlCenterScenarioTracker$setControlCenterScenarioState$1", f = "ControlCenterScenarioTracker.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ boolean $isStart;
        final /* synthetic */ long $time;
        final /* synthetic */ long $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(long j2, long j3, boolean z2, d dVar) {
            super(2, dVar);
            this.$type = j2;
            this.$time = j3;
            this.$isStart = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new AnonymousClass1(this.$type, this.$time, this.$isStart, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            a.a().b(this.$type, this.$time, this.$isStart);
            return s.f314a;
        }
    }

    private ControlCenterScenarioTracker() {
    }

    public static /* synthetic */ void reportCollapseAnimEnd$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.reportCollapseAnimEnd(z2);
    }

    public static /* synthetic */ void reportCollapseEvents$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.reportCollapseEvents(z2);
    }

    public static /* synthetic */ void reportExpandAnimEnd$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.reportExpandAnimEnd(z2);
    }

    public static /* synthetic */ void reportExpandEvents$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.reportExpandEvents(z2);
    }

    public static /* synthetic */ void setControlCenterScenarioState$default(ControlCenterScenarioTracker controlCenterScenarioTracker, long j2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.setControlCenterScenarioState(j2, z2);
    }

    public static /* synthetic */ void setControlCenterScenarioStateArray$default(ControlCenterScenarioTracker controlCenterScenarioTracker, Long[] lArr, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.setControlCenterScenarioStateArray(lArr, z2);
    }

    public static /* synthetic */ void setControlCenterSecondaryOnShow$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.setControlCenterSecondaryOnShow(z2);
    }

    public static /* synthetic */ void setControlCenterSecondaryStartShow$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.setControlCenterSecondaryStartShow(z2);
    }

    public static /* synthetic */ void setControlCenterSecondaryState$default(ControlCenterScenarioTracker controlCenterScenarioTracker, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        controlCenterScenarioTracker.setControlCenterSecondaryState(z2);
    }

    public final void reportCollapseAnimEnd(boolean z2) {
        setControlCenterScenarioStateArray(new Long[]{280L, 326L}, z2);
    }

    public final void reportCollapseAnimStart() {
        setControlCenterScenarioState(325L, false);
        setControlCenterScenarioState(280L, true);
    }

    public final void reportCollapseEvents(boolean z2) {
        setControlCenterScenarioStateArray(new Long[]{325L, 326L}, z2);
    }

    public final void reportExpandAnimEnd(boolean z2) {
        setControlCenterScenarioStateArray(new Long[]{278L, 324L}, z2);
    }

    public final void reportExpandAnimStart() {
        setControlCenterScenarioState(323L, false);
        setControlCenterScenarioState(278L, true);
    }

    public final void reportExpandEvents(boolean z2) {
        setControlCenterScenarioStateArray(new Long[]{323L, 324L}, z2);
    }

    public final void sendControlCenterExpandingFailedEvent(boolean z2, boolean z3) {
        if (!z2 || z3) {
            return;
        }
        sendMiEvent(CONTROL_CENTER_EXPANDING_FAILED, "type", 1);
    }

    public final void sendEventSafety(MiEvent event) {
        n.g(event, "event");
        try {
            MiSight.sendEvent(event);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void sendMiEvent(int i2, String key, int i3) {
        n.g(key, "key");
        MiEvent miEvent = new MiEvent(i2);
        miEvent.addInt(key, i3);
        sendEventSafety(miEvent);
    }

    public final void sendMiEventWithPanelName(int i2, String intKey, int i3, String strKey, String strValue) {
        n.g(intKey, "intKey");
        n.g(strKey, "strKey");
        n.g(strValue, "strValue");
        MiEvent miEvent = new MiEvent(i2);
        miEvent.addInt(intKey, i3);
        miEvent.addStr(strKey, strValue);
        sendEventSafety(miEvent);
    }

    public final void sendSecondaryPanelEntryFailedEvent(String currentName) {
        n.g(currentName, "currentName");
        sendMiEventWithPanelName(SECONDARY_PANEL_ENTRY_FAILED, "type", 1, PANEL_NAME, currentName);
    }

    public final void setControlCenterScenarioState(long j2, boolean z2) {
        if (j2 > 0) {
            AbstractC0369g.b(F.a(Dispatchers.INSTANCE.getDefault()), null, null, new AnonymousClass1(j2, SystemClock.elapsedRealtime(), z2, null), 3, null);
        }
    }

    public final void setControlCenterScenarioStateArray(Long[] types, boolean z2) {
        n.g(types, "types");
        for (Long l2 : types) {
            setControlCenterScenarioState(l2.longValue(), z2);
        }
    }

    public final void setControlCenterSecondaryOnShow(boolean z2) {
        setControlCenterScenarioState(411L, z2);
    }

    public final void setControlCenterSecondaryStartShow(boolean z2) {
        setControlCenterScenarioState(410L, z2);
    }

    public final void setControlCenterSecondaryState(boolean z2) {
        setControlCenterScenarioStateArray(new Long[]{410L, 411L}, z2);
    }
}
