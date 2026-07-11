package miui.systemui.dynamicisland.window;

import H0.s;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.notification.ActivityManagerWrapper;

/* JADX INFO: loaded from: classes3.dex */
@N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$addDynamicIslandData$1$1", f = "DynamicIslandWindowView.kt", l = {}, m = "invokeSuspend")
public final class DynamicIslandWindowView$addDynamicIslandData$1$1 extends N0.l implements Function2 {
    final /* synthetic */ DynamicIslandContentView $contentView;
    final /* synthetic */ DynamicIslandData $dynamicIslandData;
    int label;
    final /* synthetic */ DynamicIslandWindowView this$0;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowView$addDynamicIslandData$1$1$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowView$addDynamicIslandData$1$1$1", f = "DynamicIslandWindowView.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        final /* synthetic */ DynamicIslandContentView $contentView;
        final /* synthetic */ DynamicIslandData $dynamicIslandData;
        final /* synthetic */ String $topActivity1;
        int label;
        final /* synthetic */ DynamicIslandWindowView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandContentView dynamicIslandContentView, DynamicIslandData dynamicIslandData, String str, L0.d dVar) {
            super(2, dVar);
            this.this$0 = dynamicIslandWindowView;
            this.$contentView = dynamicIslandContentView;
            this.$dynamicIslandData = dynamicIslandData;
            this.$topActivity1 = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(this.this$0, this.$contentView, this.$dynamicIslandData, this.$topActivity1, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            Log.e("DynamicIslandWindowViewImpl", "addDynamicIslandData contentViewList " + this.this$0.getContentViewList().contains(this.$contentView));
            if (!this.this$0.getContentViewList().contains(this.$contentView)) {
                return s.f314a;
            }
            this.this$0.measureExpandedViewHeight(this.$contentView);
            Bundle extras = this.$dynamicIslandData.getExtras();
            String string = extras != null ? extras.getString("miui.pkg.name") : null;
            String topActivityPkg = this.this$0.getWindowViewController().getTopActivityPkg();
            DynamicIslandWindowViewController windowViewController = this.this$0.getWindowViewController();
            Bundle extras2 = this.$dynamicIslandData.getExtras();
            boolean zIsInMiniWindow = windowViewController.isInMiniWindow(string, extras2 != null ? N0.b.c(extras2.getInt("miui.user.id")) : null);
            Log.e("DynamicIslandWindowViewImpl", "appPkg: " + string + ", topActivity: " + topActivityPkg + " " + this.$topActivity1);
            if (kotlin.jvm.internal.n.c(string, this.$topActivity1) && this.this$0.getWindowViewController().canEnterAppState(this.$contentView) && (!((Boolean) this.this$0.getWindowViewController().isFreeformAnimRunning().getValue()).booleanValue() || (((Boolean) this.this$0.getWindowViewController().isFreeformAnimRunning().getValue()).booleanValue() && this.this$0.hasSamePkgEnterFreeFrom(string)))) {
                if (kotlin.jvm.internal.n.c(this.this$0.getWindowViewController().getInSmallWindowMap().get(this.$topActivity1), N0.b.a(true))) {
                    this.this$0.enterMiniWindow(this.$contentView);
                    return s.f314a;
                }
                this.this$0.appEnter(this.$contentView);
                return s.f314a;
            }
            if (zIsInMiniWindow && this.this$0.getWindowViewController().canEnterAppState(this.$contentView) && !((Boolean) this.this$0.getWindowViewController().isFreeformAnimRunning().getValue()).booleanValue()) {
                this.this$0.enterMiniWindow(this.$contentView);
                return s.f314a;
            }
            DynamicIslandEventCoordinator eventCoordinator = this.this$0.getEventCoordinator();
            if (eventCoordinator != null) {
                DynamicIslandEvent.AddDynamicIsland addDynamicIsland = DynamicIslandEvent.AddDynamicIsland.INSTANCE;
                Integer properties = this.$dynamicIslandData.getProperties();
                addDynamicIsland.setTempShow(properties != null && properties.intValue() == 0);
                eventCoordinator.dispatchEvent(addDynamicIsland, this.$contentView);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandWindowView$addDynamicIslandData$1$1(DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandContentView dynamicIslandContentView, DynamicIslandData dynamicIslandData, L0.d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandWindowView;
        this.$contentView = dynamicIslandContentView;
        this.$dynamicIslandData = dynamicIslandData;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new DynamicIslandWindowView$addDynamicIslandData$1$1(this.this$0, this.$contentView, this.$dynamicIslandData, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, L0.d dVar) {
        return ((DynamicIslandWindowView$addDynamicIslandData$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        ComponentName componentName;
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        ActivityTaskManager.RootTaskInfo focusedRootTaskInfo = ActivityManagerWrapper.INSTANCE.getFocusedRootTaskInfo();
        AbstractC0369g.b(this.this$0.getWindowViewController().getUiScope(), null, null, new AnonymousClass1(this.this$0, this.$contentView, this.$dynamicIslandData, (focusedRootTaskInfo == null || (componentName = focusedRootTaskInfo.topActivity) == null) ? null : componentName.getPackageName(), null), 3, null);
        return s.f314a;
    }
}
