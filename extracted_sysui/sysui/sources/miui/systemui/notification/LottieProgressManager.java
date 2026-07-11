package miui.systemui.notification;

import H0.k;
import H0.s;
import I0.G;
import I0.m;
import N0.l;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.notification.LottieProgressManager;

/* JADX INFO: loaded from: classes4.dex */
public final class LottieProgressManager implements AutoDensityController.OnDensityChangeListener {
    private E0.a autoDensityController;
    private boolean isNightMode;
    private final Map<String, LottieProgressSyncHelper> lottieProgressMap;
    private final E pluginScope;

    /* JADX INFO: renamed from: miui.systemui.notification.LottieProgressManager$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.LottieProgressManager$1", f = "LottieProgressManager.kt", l = {33}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.LottieProgressManager$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            LottieProgressManager.this.reset();
        }
    }

    public static final class LottieProgressSyncHelper {
        public static final Companion Companion = new Companion(null);
        public static final String FOCUS_NOTIFICATION = "lottie_focus_notification";
        public static final String FOCUS_NOTIFICATION_DARK = "lottie_focus_notification_dark";
        public static final String FOCUS_NOTIFICATION_MODAL = "lottie_focus_notification_modal";
        public static final String FOCUS_NOTIFICATION_MODAL_DARK = "lottie_focus_notification_modal_dark";
        public static final String ISLAND_BIG_ISLAND = "lottie_island_big_island";
        public static final String ISLAND_BIG_ISLAND_FAKE = "lottie_island_big_island_fake";
        public static final String ISLAND_EXPANDED_NOTIFICATION = "lottie_island_expanded_notification";
        public static final String ISLAND_EXPANDED_NOTIFICATION_FAKE = "lottie_island_expanded_notification_fake";
        public static final String ISLAND_SMALL_ISLAND = "lottie_island_small_island";
        public static final String ISLAND_SMALL_ISLAND_FAKE = "lottie_island_small_island_fake";
        public static final String TAG = "LottieProgressSyncHelper";
        private boolean dynamicIslandManaged;
        private boolean focusNotificationManaged;
        private final Map<String, LottieAnimationView> lottieSceneMap = new LinkedHashMap();

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        private final void print() {
            for (Map.Entry<String, LottieAnimationView> entry : this.lottieSceneMap.entrySet()) {
                Log.d(TAG, "key: " + entry.getKey() + ", value: " + entry.getValue().getProgress());
            }
        }

        private final void updateProgress(float f2) {
            Map<String, LottieAnimationView> map = this.lottieSceneMap;
            final LottieProgressManager$LottieProgressSyncHelper$updateProgress$1 lottieProgressManager$LottieProgressSyncHelper$updateProgress$1 = new LottieProgressManager$LottieProgressSyncHelper$updateProgress$1(f2);
            map.forEach(new BiConsumer() { // from class: miui.systemui.notification.c
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    LottieProgressManager.LottieProgressSyncHelper.updateProgress$lambda$9(lottieProgressManager$LottieProgressSyncHelper$updateProgress$1, obj, obj2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void updateProgress$lambda$9(Function2 tmp0, Object obj, Object obj2) {
            n.g(tmp0, "$tmp0");
            tmp0.invoke(obj, obj2);
        }

        public final void addIslandLottie(View view, View view2, View view3, View view4, int i2) {
            LottieAnimationView lottieAnimationView;
            for (Map.Entry entry : G.h(H0.o.a(ISLAND_BIG_ISLAND, view), H0.o.a(ISLAND_BIG_ISLAND_FAKE, view2), H0.o.a(ISLAND_SMALL_ISLAND, view3), H0.o.a(ISLAND_SMALL_ISLAND_FAKE, view4)).entrySet()) {
                String str = (String) entry.getKey();
                View view5 = (View) entry.getValue();
                if (view5 != null && (lottieAnimationView = (LottieAnimationView) view5.findViewById(i2)) != null) {
                    n.d(lottieAnimationView);
                    this.lottieSceneMap.put(str, lottieAnimationView);
                }
            }
        }

        public final void addLottieAnimationView(FocusNotificationContent fnc, int i2) {
            LottieAnimationView lottieAnimationView;
            n.g(fnc, "fnc");
            for (Map.Entry entry : G.h(H0.o.a(FOCUS_NOTIFICATION, fnc.getFocusNotification()), H0.o.a(FOCUS_NOTIFICATION_DARK, fnc.getFocusNotificationDark()), H0.o.a(FOCUS_NOTIFICATION_MODAL, fnc.getFocusNotificationModal()), H0.o.a(FOCUS_NOTIFICATION_MODAL_DARK, fnc.getFocusNotificationDarkModal()), H0.o.a(ISLAND_EXPANDED_NOTIFICATION, fnc.getIslandExpandedView()), H0.o.a(ISLAND_EXPANDED_NOTIFICATION_FAKE, fnc.getIslandExpandedViewFake())).entrySet()) {
                String str = (String) entry.getKey();
                View view = (View) entry.getValue();
                if (view != null && (lottieAnimationView = (LottieAnimationView) view.findViewById(i2)) != null) {
                    n.d(lottieAnimationView);
                    this.lottieSceneMap.put(str, lottieAnimationView);
                }
            }
        }

        public final void bigIslandShift(boolean z2) {
            LottieAnimationView lottieAnimationView = this.lottieSceneMap.get(z2 ? ISLAND_BIG_ISLAND_FAKE : ISLAND_BIG_ISLAND);
            if (lottieAnimationView != null) {
                updateProgress(lottieAnimationView.getProgress());
            }
        }

        public final void dropIslandLottie() {
            Iterator it = m.j(ISLAND_BIG_ISLAND, ISLAND_BIG_ISLAND_FAKE, ISLAND_SMALL_ISLAND, ISLAND_SMALL_ISLAND_FAKE).iterator();
            while (it.hasNext()) {
                this.lottieSceneMap.remove((String) it.next());
            }
            this.dynamicIslandManaged = false;
        }

        public final void focusModalShift(boolean z2, boolean z3) {
            if (z3) {
                LottieAnimationView lottieAnimationView = this.lottieSceneMap.get(z2 ? FOCUS_NOTIFICATION_MODAL_DARK : FOCUS_NOTIFICATION_DARK);
                if (lottieAnimationView != null) {
                    updateProgress(lottieAnimationView.getProgress());
                    return;
                }
                return;
            }
            LottieAnimationView lottieAnimationView2 = this.lottieSceneMap.get(z2 ? FOCUS_NOTIFICATION_MODAL : FOCUS_NOTIFICATION);
            if (lottieAnimationView2 != null) {
                updateProgress(lottieAnimationView2.getProgress());
            }
        }

        public final boolean getDynamicIslandManaged() {
            return this.dynamicIslandManaged;
        }

        public final boolean getFocusNotificationManaged() {
            return this.focusNotificationManaged;
        }

        public final boolean hasLottieAnimationView() {
            return !this.lottieSceneMap.isEmpty();
        }

        public final void islandExpandShift(boolean z2) {
            LottieAnimationView lottieAnimationView = this.lottieSceneMap.get(z2 ? ISLAND_EXPANDED_NOTIFICATION_FAKE : ISLAND_EXPANDED_NOTIFICATION);
            if (lottieAnimationView != null) {
                updateProgress(lottieAnimationView.getProgress());
            }
        }

        public final void release() {
            this.lottieSceneMap.clear();
        }

        public final void setDynamicIslandManaged(boolean z2) {
            this.dynamicIslandManaged = z2;
        }

        public final void setFocusNotificationManaged(boolean z2) {
            this.focusNotificationManaged = z2;
        }

        public final void smallIslandShift(boolean z2) {
            LottieAnimationView lottieAnimationView = this.lottieSceneMap.get(z2 ? ISLAND_SMALL_ISLAND_FAKE : ISLAND_SMALL_ISLAND);
            if (lottieAnimationView != null) {
                updateProgress(lottieAnimationView.getProgress());
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.LottieProgressManager$reset$1, reason: invalid class name and case insensitive filesystem */
    public static final class C06711 extends o implements Function2 {
        public static final C06711 INSTANCE = new C06711();

        public C06711() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, (LottieProgressSyncHelper) obj2);
            return s.f314a;
        }

        public final void invoke(String key, LottieProgressSyncHelper helper) {
            n.g(key, "key");
            n.g(helper, "helper");
            helper.release();
        }
    }

    public LottieProgressManager(@Plugin E pluginScope, E0.a autoDensityController) {
        n.g(pluginScope, "pluginScope");
        n.g(autoDensityController, "autoDensityController");
        this.pluginScope = pluginScope;
        this.autoDensityController = autoDensityController;
        this.lottieProgressMap = new LinkedHashMap();
        ((AutoDensityController) this.autoDensityController.get()).addOnDensityChangeListener(this);
        AbstractC0369g.b(pluginScope, null, null, new AnonymousClass1(null), 3, null).l(new AnonymousClass2());
    }

    public static /* synthetic */ void islandShift$default(LottieProgressManager lottieProgressManager, String str, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        lottieProgressManager.islandShift(str, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        Map<String, LottieProgressSyncHelper> map = this.lottieProgressMap;
        final C06711 c06711 = C06711.INSTANCE;
        map.forEach(new BiConsumer() { // from class: miui.systemui.notification.b
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                LottieProgressManager.reset$lambda$3(c06711, obj, obj2);
            }
        });
        this.lottieProgressMap.clear();
        ((AutoDensityController) this.autoDensityController.get()).removeOnDensityChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reset$lambda$3(Function2 tmp0, Object obj, Object obj2) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj, obj2);
    }

    public final void addIslandLottie(String str, int i2, View view, View view2, View view3, View view4) {
        if (str != null) {
            LottieProgressSyncHelper lottieProgressSyncHelperRemove = this.lottieProgressMap.remove(str);
            if (lottieProgressSyncHelperRemove == null) {
                lottieProgressSyncHelperRemove = new LottieProgressSyncHelper();
            }
            if (!lottieProgressSyncHelperRemove.getDynamicIslandManaged()) {
                lottieProgressSyncHelperRemove.addIslandLottie(view, view3, view2, view4, i2);
            }
            if (!lottieProgressSyncHelperRemove.hasLottieAnimationView()) {
                lottieProgressSyncHelperRemove.release();
            } else {
                lottieProgressSyncHelperRemove.setDynamicIslandManaged(true);
                this.lottieProgressMap.put(str, lottieProgressSyncHelperRemove);
            }
        }
    }

    public final void addLottie(String str, FocusNotificationContent fnc, int i2) {
        n.g(fnc, "fnc");
        if (str != null) {
            LottieProgressSyncHelper lottieProgressSyncHelperRemove = this.lottieProgressMap.remove(str);
            if (lottieProgressSyncHelperRemove == null) {
                lottieProgressSyncHelperRemove = new LottieProgressSyncHelper();
            }
            if (!lottieProgressSyncHelperRemove.getFocusNotificationManaged()) {
                lottieProgressSyncHelperRemove.addLottieAnimationView(fnc, i2);
            }
            if (!lottieProgressSyncHelperRemove.hasLottieAnimationView()) {
                lottieProgressSyncHelperRemove.release();
            } else {
                lottieProgressSyncHelperRemove.setFocusNotificationManaged(true);
                this.lottieProgressMap.put(str, lottieProgressSyncHelperRemove);
            }
        }
    }

    public final void focusModalShift(String str, boolean z2) {
        LottieProgressSyncHelper lottieProgressSyncHelper = this.lottieProgressMap.get(str);
        if (lottieProgressSyncHelper != null) {
            lottieProgressSyncHelper.focusModalShift(z2, this.isNightMode);
        }
    }

    public final boolean isNightMode() {
        return this.isNightMode;
    }

    public final void islandExpandShift(String str, boolean z2) {
        LottieProgressSyncHelper lottieProgressSyncHelper = this.lottieProgressMap.get(str);
        if (lottieProgressSyncHelper != null) {
            lottieProgressSyncHelper.islandExpandShift(z2);
        }
    }

    public final void islandShift(String str, boolean z2, boolean z3) {
        LottieProgressSyncHelper lottieProgressSyncHelper = this.lottieProgressMap.get(str);
        if (lottieProgressSyncHelper != null) {
            if (z2) {
                lottieProgressSyncHelper.bigIslandShift(z3);
            } else {
                lottieProgressSyncHelper.smallIslandShift(z3);
            }
        }
    }

    @Override // miui.systemui.autodensity.AutoDensityController.OnDensityChangeListener
    public void onConfigChanged(Configuration config) {
        n.g(config, "config");
        this.isNightMode = config.isNightModeActive();
    }

    public final void removeIslandLottie(String str) {
        LottieProgressSyncHelper lottieProgressSyncHelperRemove;
        if (str == null || (lottieProgressSyncHelperRemove = this.lottieProgressMap.remove(str)) == null) {
            return;
        }
        if (lottieProgressSyncHelperRemove.getDynamicIslandManaged()) {
            lottieProgressSyncHelperRemove.dropIslandLottie();
        }
        if (lottieProgressSyncHelperRemove.hasLottieAnimationView()) {
            this.lottieProgressMap.put(str, lottieProgressSyncHelperRemove);
        } else {
            lottieProgressSyncHelperRemove.release();
        }
    }

    public final void removeLottie(String str) {
        LottieProgressSyncHelper lottieProgressSyncHelper = (LottieProgressSyncHelper) D.d(this.lottieProgressMap).remove(str);
        if (lottieProgressSyncHelper != null) {
            lottieProgressSyncHelper.release();
        }
    }

    public final void setNightMode(boolean z2) {
        this.isNightMode = z2;
    }
}
