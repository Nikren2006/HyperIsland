package miui.systemui.dynamicisland.window.content;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Collection;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.display.AntiBurnInManagerKt;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miuix.animation.IFolme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandContentViewExtKt {
    public static final void animBgBurnIn(final DynamicIslandContentView dynamicIslandContentView, float f2, final float f3) {
        n.g(dynamicIslandContentView, "<this>");
        Log.d("DynamicIslandBurnIn", "animBgBurnIn, to=" + f3);
        IFolme folme = FolmeKt.getFolme(dynamicIslandContentView);
        AnimState animState = new AnimState();
        DynamicIslandContentView.Companion companion = DynamicIslandContentView.Companion;
        folme.to(animState.add(companion.getCONTAINER_BG_ALPHA(), f3, new long[0]), new AnimConfig().setSpecial(companion.getCONTAINER_BG_ALPHA(), companion.getANTI_BURN_IN_EASE(), new float[0]).addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewExtKt.animBgBurnIn.1
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                if (f3 == 0.6f) {
                    DynamicIslandContentViewExtKt.hideTextShade(dynamicIslandContentView);
                } else {
                    DynamicIslandContentViewExtKt.restoreShade(dynamicIslandContentView);
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                FrameLayout container = dynamicIslandContentView.getContainer();
                Drawable background = container != null ? container.getBackground() : null;
                if (background != null) {
                    background.setAlpha((int) (255 * dynamicIslandContentView.getContainerBgAlpha()));
                }
                dynamicIslandContentView.invalidate();
            }
        }));
    }

    public static final void animEnterBurnIn(DynamicIslandContentView dynamicIslandContentView, DynamicIslandContentView dynamicIslandContentView2, boolean z2) {
        SmallIslandStateHandler smallIslandStateHandler;
        BigIslandStateHandler bigIslandStateHandler;
        n.g(dynamicIslandContentView, "<this>");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator();
        DynamicIslandContentView current = null;
        DynamicIslandContentView current2 = (dynamicIslandEventCoordinator == null || (bigIslandStateHandler = dynamicIslandEventCoordinator.getBigIslandStateHandler()) == null) ? null : bigIslandStateHandler.getCurrent();
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator2 != null && (smallIslandStateHandler = dynamicIslandEventCoordinator2.getSmallIslandStateHandler()) != null) {
            current = smallIslandStateHandler.getCurrent();
        }
        if (current2 != null) {
            animBgBurnIn(current2, 0.0f, 0.6f);
        }
        if (current != null) {
            animBgBurnIn(current, 0.0f, 0.6f);
        }
    }

    public static final void animExitBurnIn(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        SmallIslandStateHandler smallIslandStateHandler;
        BigIslandStateHandler bigIslandStateHandler;
        n.g(dynamicIslandContentView, "<this>");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator();
        DynamicIslandContentView current = null;
        DynamicIslandContentView current2 = (dynamicIslandEventCoordinator == null || (bigIslandStateHandler = dynamicIslandEventCoordinator.getBigIslandStateHandler()) == null) ? null : bigIslandStateHandler.getCurrent();
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator2 != null && (smallIslandStateHandler = dynamicIslandEventCoordinator2.getSmallIslandStateHandler()) != null) {
            current = smallIslandStateHandler.getCurrent();
        }
        if (current2 != null) {
            animBgBurnIn(current2, 0.0f, 1.0f);
        }
        if (current != null) {
            animBgBurnIn(current, 0.0f, 1.0f);
        }
    }

    public static final void dispatchAutoExpand(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(dynamicIslandContentView, "<this>");
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = dynamicIslandContentView.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator2 == null || dynamicIslandEventCoordinator2.getUserExpanded() || (dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator, DynamicIslandEvent.AutoExpandIsland.INSTANCE, null, 2, null);
    }

    public static final long getRemainingUnitExact(DynamicIslandContentView dynamicIslandContentView) {
        n.g(dynamicIslandContentView, "<this>");
        return dynamicIslandContentView.getHasEverBurnedIn() ? dynamicIslandContentView.getRemainingBurnInUnit() : dynamicIslandContentView.getRemainingUnit();
    }

    public static final long getRemainingUnitLong(DynamicIslandContentView dynamicIslandContentView) {
        n.g(dynamicIslandContentView, "<this>");
        Long lValueOf = Long.valueOf(dynamicIslandContentView.getHasEverBurnedIn() ? dynamicIslandContentView.getRemainingBurnInUnit() : dynamicIslandContentView.getRemainingUnit());
        if (lValueOf.longValue() < 0) {
            lValueOf = null;
        }
        return lValueOf != null ? lValueOf.longValue() : AntiBurnInManager.Companion.getEXTEND_EXPOSE_TIME();
    }

    public static final void hideTextShade(DynamicIslandContentView dynamicIslandContentView) {
        n.g(dynamicIslandContentView, "<this>");
        if (dynamicIslandContentView.inBurnIn()) {
            for (View view : AntiBurnInManagerKt.findAllViewsById(dynamicIslandContentView, R.id.island_text_shade)) {
                if (view.getVisibility() == 0) {
                    dynamicIslandContentView.getShadeBackUp().put(view, 0);
                    view.setVisibility(8);
                }
            }
        }
    }

    public static final void restoreShade(DynamicIslandContentView dynamicIslandContentView) {
        n.g(dynamicIslandContentView, "<this>");
        for (View view : AntiBurnInManagerKt.findAllViewsById(dynamicIslandContentView, R.id.island_text_shade)) {
            Integer num = dynamicIslandContentView.getShadeBackUp().get(view);
            if (num != null) {
                view.setVisibility(num.intValue());
            }
        }
    }
}
