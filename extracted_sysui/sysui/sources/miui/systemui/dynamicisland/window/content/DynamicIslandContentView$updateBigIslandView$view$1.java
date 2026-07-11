package miui.systemui.dynamicisland.window.content;

import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;

/* JADX INFO: loaded from: classes3.dex */
public /* synthetic */ class DynamicIslandContentView$updateBigIslandView$view$1 extends l implements Function2 {
    public DynamicIslandContentView$updateBigIslandView$view$1(Object obj) {
        super(2, obj, DynamicIslandContentView.class, "emitEvent", "emitEvent(Lcom/android/systemui/plugins/miui/dynamicisland/DynamicIslandData;Lmiui/systemui/dynamicisland/event/DynamicIslandEvent;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Boolean invoke(DynamicIslandData dynamicIslandData, DynamicIslandEvent p12) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowView windowView2;
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2;
        DynamicIslandWindowView windowView3;
        Bundle extras;
        DynamicIslandWindowView windowView4;
        n.g(p12, "p1");
        DynamicIslandContentView dynamicIslandContentView = (DynamicIslandContentView) this.receiver;
        Log.d(DynamicIslandContentView.TAG, "emitEvent " + p12);
        boolean z2 = false;
        if (p12 instanceof DynamicIslandEvent.DeletedDynamicIsland) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
            StatusBarNotification statusBarNotification = null;
            if (dynamicIslandEventCoordinator3 != null && (windowView4 = dynamicIslandEventCoordinator3.getWindowView()) != null) {
                DynamicIslandWindowView.removeDynamicIslandData$default(windowView4, dynamicIslandData, false, 2, null);
            }
            if (dynamicIslandData != null && (extras = dynamicIslandData.getExtras()) != null) {
                statusBarNotification = (StatusBarNotification) extras.getParcelable("miui.sbn", StatusBarNotification.class);
            }
            if (statusBarNotification != null && (dynamicIslandEventCoordinator2 = dynamicIslandContentView.getDynamicIslandEventCoordinator()) != null && (windowView3 = dynamicIslandEventCoordinator2.getWindowView()) != null) {
                windowView3.removeNotification(statusBarNotification);
            }
            z2 = true;
        } else if ((p12 instanceof DynamicIslandEvent.UpdateDynamicIsland) && dynamicIslandData != null && (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) != null && (windowView = dynamicIslandEventCoordinator.getWindowView()) != null) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator4 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
            windowView.updateDynamicIslandView(dynamicIslandData, false, (dynamicIslandEventCoordinator4 == null || (windowView2 = dynamicIslandEventCoordinator4.getWindowView()) == null || (windowViewController = windowView2.getWindowViewController()) == null) ? 0.0f : windowViewController.getIslandMaxWidth());
        }
        return Boolean.valueOf(z2);
    }
}
