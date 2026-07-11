package miui.systemui.dynamicisland.anim;

import H0.s;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import c1.f;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.miui.maml.folme.AnimatedProperty;
import j1.u;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandScenarioUtils;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.DynamicIslandAnimUtils;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.ThreadUtils;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.FolmeObject;
import miuix.animation.IFolme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationDelegate implements FolmeObject {
    private final /* synthetic */ FolmeObject $$delegate_0;
    private final EaseManager.EaseStyle ALPHA_EASE;
    private final EaseManager.EaseStyle APPEAR_EASE;
    private final EaseManager.EaseStyle CHANGE_EASE;
    private final EaseManager.EaseStyle FAKE_ALPHA_EASE;
    private final EaseManager.EaseStyle HIDDEN_EASE;
    private final EaseManager.EaseStyle SCALE_EASE;
    private final EaseManager.EaseStyle SHOW_EASE;
    private final String TAG;
    private boolean appClose;
    private float bigIslandAlpha;
    private float bigIslandAreaLeft;
    private float bigIslandAreaRight;
    private float bigIslandBlur;
    private float bigIslandScale;
    private final BigIslandStateHandler bigIslandStateHandler;
    private float bigIslandTransY;
    private float bigIslandX;
    private Choreographer choreographer;
    private float containerAlpha;
    private float containerClipBottomProgress;
    private float containerClipEndProgress;
    private float containerClipStartProgress;
    private float containerClipTopProgress;
    private float containerTransY;
    private float containerX;
    private final float debugIslandAnimScale;
    private final DynamicIslandAnimationController dynamicIslandAnimController;
    private float expandedAlpha;
    private float expandedBlur;
    private float expandedScaleX;
    private float expandedScaleY;
    private final ExpandedStateHandler expandedStateHandler;
    private boolean expandedToTempHiddenAnimating;
    private float expandedTransY;
    private float expandedViewAnimatingProgress;
    private boolean expandedViewHadScaled;
    private float fakeBigAlpha;
    private float fakeBigBlur;
    private float fakeExpandedAlpha;
    private float fakeExpandedBlur;
    private float fakeSmallAlpha;
    private float fakeSmallBlur;
    private final DynamicIslandContentFakeView fakeView;
    private boolean fakeViewAnimating;
    private final Choreographer.FrameCallback frameCallback;
    private DynamicIslandState hiddenStateFrom;
    private boolean isAnimateExpanding;
    private boolean isAnimating;
    private boolean isAppClosing;
    private boolean islandAppAnimRunning;
    private boolean islandFreeformAnimRunning;
    private boolean needResetContainerAlpha;
    private float smallIslandAlpha;
    private float smallIslandBlur;
    private float smallIslandScale;
    private final SmallIslandStateHandler smallIslandStateHandler;
    private float smallIslandTransX;
    private float smallIslandTransY;
    private final boolean supportBlur;
    private boolean updateScheduled;
    private final DynamicIslandBaseContentView view;
    private final DynamicIslandWindowView windowView;
    public static final Companion Companion = new Companion(null);
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_X = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerX = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_TRANS_Y = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_TRANS_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerTransY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerTransY = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerAlpha = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_CLIP_START_PROGRESS = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_CLIP_START_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerClipStartProgress;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerClipStartProgress = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_CLIP_END_PROGRESS = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_CLIP_END_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerClipEndProgress;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerClipEndProgress = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_CLIP_TOP_PROGRESS = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_CLIP_TOP_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerClipTopProgress;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerClipTopProgress = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> CONTAINER_CLIP_BOTTOM_PROGRESS = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$CONTAINER_CLIP_BOTTOM_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.containerClipBottomProgress;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.containerClipBottomProgress = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> EXPANDED_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$EXPANDED_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.expandedAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.expandedAlpha = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> EXPANDED_SCALE_X = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$EXPANDED_SCALE_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.expandedScaleX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.expandedScaleX = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> EXPANDED_SCALE_Y = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$EXPANDED_SCALE_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.expandedScaleY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.expandedScaleY = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> EXPANDED_TRANS_Y = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$EXPANDED_TRANS_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.expandedTransY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.expandedTransY = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandAlpha = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_SCALE = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_SCALE$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandScale;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandScale = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_X = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandX = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_TRANS_Y = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_TRANS_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandTransY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandTransY = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_AREA_LEFT_TRANS_X = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_AREA_LEFT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandAreaLeft;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandAreaLeft = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_AREA_RIGHT_TRANS_X = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_AREA_RIGHT_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandAreaRight;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandAreaRight = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> SMALL_ISLAND_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$SMALL_ISLAND_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.smallIslandAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.smallIslandAlpha = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> SMALL_ISLAND_SCALE = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$SMALL_ISLAND_SCALE$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.smallIslandScale;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.smallIslandScale = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> SMALL_ISLAND_TRANS_X = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$SMALL_ISLAND_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.smallIslandTransX;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.smallIslandTransX = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> SMALL_ISLAND_TRANS_Y = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$SMALL_ISLAND_TRANS_Y$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.smallIslandTransY;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.smallIslandTransY = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> SMALL_ISLAND_BLUR = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$SMALL_ISLAND_BLUR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.smallIslandBlur;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.smallIslandBlur = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> BIG_ISLAND_BLUR = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$BIG_ISLAND_BLUR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.bigIslandBlur;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.bigIslandBlur = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> EXPANDED_BLUR = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$EXPANDED_BLUR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.expandedBlur;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.expandedBlur = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> FAKE_EXPANDED_BLUR = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$FAKE_EXPANDED_BLUR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.fakeExpandedBlur;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.fakeExpandedBlur = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> FAKE_EXPANDED_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$FAKE_EXPANDED_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.fakeExpandedAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.fakeExpandedAlpha = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> FAKE_BIG_BLUR = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$FAKE_BIG_BLUR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.fakeBigBlur;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.fakeBigBlur = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> FAKE_BIG_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$FAKE_BIG_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.fakeBigAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.fakeBigAlpha = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> FAKE_SMALL_BLUR = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$FAKE_SMALL_BLUR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.fakeSmallBlur;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.fakeSmallBlur = f2;
        }
    };
    private static final FloatProperty<DynamicIslandAnimationDelegate> FAKE_SMALL_ALPHA = new FloatProperty<DynamicIslandAnimationDelegate>() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$Companion$FAKE_SMALL_ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandAnimationDelegate holder) {
            n.g(holder, "holder");
            return holder.fakeSmallAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandAnimationDelegate holder, float f2) {
            n.g(holder, "holder");
            if (Float.isNaN(f2)) {
                return;
            }
            holder.fakeSmallAlpha = f2;
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_ALPHA() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_AREA_LEFT_TRANS_X() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_AREA_LEFT_TRANS_X;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_AREA_RIGHT_TRANS_X() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_AREA_RIGHT_TRANS_X;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_BLUR() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_BLUR;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_SCALE() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_SCALE;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_TRANS_Y() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_TRANS_Y;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getBIG_ISLAND_X() {
            return DynamicIslandAnimationDelegate.BIG_ISLAND_X;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_ALPHA() {
            return DynamicIslandAnimationDelegate.CONTAINER_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_CLIP_BOTTOM_PROGRESS() {
            return DynamicIslandAnimationDelegate.CONTAINER_CLIP_BOTTOM_PROGRESS;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_CLIP_END_PROGRESS() {
            return DynamicIslandAnimationDelegate.CONTAINER_CLIP_END_PROGRESS;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_CLIP_START_PROGRESS() {
            return DynamicIslandAnimationDelegate.CONTAINER_CLIP_START_PROGRESS;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_CLIP_TOP_PROGRESS() {
            return DynamicIslandAnimationDelegate.CONTAINER_CLIP_TOP_PROGRESS;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_TRANS_Y() {
            return DynamicIslandAnimationDelegate.CONTAINER_TRANS_Y;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getCONTAINER_X() {
            return DynamicIslandAnimationDelegate.CONTAINER_X;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getEXPANDED_ALPHA() {
            return DynamicIslandAnimationDelegate.EXPANDED_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getEXPANDED_BLUR() {
            return DynamicIslandAnimationDelegate.EXPANDED_BLUR;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getEXPANDED_SCALE_X() {
            return DynamicIslandAnimationDelegate.EXPANDED_SCALE_X;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getEXPANDED_SCALE_Y() {
            return DynamicIslandAnimationDelegate.EXPANDED_SCALE_Y;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getEXPANDED_TRANS_Y() {
            return DynamicIslandAnimationDelegate.EXPANDED_TRANS_Y;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getFAKE_BIG_ALPHA() {
            return DynamicIslandAnimationDelegate.FAKE_BIG_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getFAKE_BIG_BLUR() {
            return DynamicIslandAnimationDelegate.FAKE_BIG_BLUR;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getFAKE_EXPANDED_ALPHA() {
            return DynamicIslandAnimationDelegate.FAKE_EXPANDED_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getFAKE_EXPANDED_BLUR() {
            return DynamicIslandAnimationDelegate.FAKE_EXPANDED_BLUR;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getFAKE_SMALL_ALPHA() {
            return DynamicIslandAnimationDelegate.FAKE_SMALL_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getFAKE_SMALL_BLUR() {
            return DynamicIslandAnimationDelegate.FAKE_SMALL_BLUR;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getSMALL_ISLAND_ALPHA() {
            return DynamicIslandAnimationDelegate.SMALL_ISLAND_ALPHA;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getSMALL_ISLAND_BLUR() {
            return DynamicIslandAnimationDelegate.SMALL_ISLAND_BLUR;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getSMALL_ISLAND_SCALE() {
            return DynamicIslandAnimationDelegate.SMALL_ISLAND_SCALE;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getSMALL_ISLAND_TRANS_X() {
            return DynamicIslandAnimationDelegate.SMALL_ISLAND_TRANS_X;
        }

        public final FloatProperty<DynamicIslandAnimationDelegate> getSMALL_ISLAND_TRANS_Y() {
            return DynamicIslandAnimationDelegate.SMALL_ISLAND_TRANS_Y;
        }

        private Companion() {
        }
    }

    public interface Factory {
        DynamicIslandAnimationDelegate create(DynamicIslandBaseContentView dynamicIslandBaseContentView);
    }

    public interface GlobalAnimationCallback {
        void onAnimationCancel(String str);

        void onAnimationFinished(String str);

        void onAnimationStart(String str);
    }

    public DynamicIslandAnimationDelegate(DynamicIslandBaseContentView view, DynamicIslandAnimationController dynamicIslandAnimController, SmallIslandStateHandler smallIslandStateHandler, BigIslandStateHandler bigIslandStateHandler, ExpandedStateHandler expandedStateHandler, DynamicIslandWindowView windowView) {
        n.g(view, "view");
        n.g(dynamicIslandAnimController, "dynamicIslandAnimController");
        n.g(smallIslandStateHandler, "smallIslandStateHandler");
        n.g(bigIslandStateHandler, "bigIslandStateHandler");
        n.g(expandedStateHandler, "expandedStateHandler");
        n.g(windowView, "windowView");
        this.view = view;
        this.dynamicIslandAnimController = dynamicIslandAnimController;
        this.smallIslandStateHandler = smallIslandStateHandler;
        this.bigIslandStateHandler = bigIslandStateHandler;
        this.expandedStateHandler = expandedStateHandler;
        this.windowView = windowView;
        this.$$delegate_0 = FolmeKt.FolmeObject();
        this.TAG = "DynamicIslandAnimationDelegate";
        this.supportBlur = (DeviceUtils.isLowEndDevice() || DeviceUtils.isLowLevel() || DeviceUtils.isMidLowLevel() || DeviceUtils.isSubMidLevel() || DeviceUtils.isNormalLevel()) ? false : true;
        this.fakeView = view.getFakeView();
        this.containerAlpha = 1.0f;
        this.bigIslandAlpha = 1.0f;
        this.bigIslandScale = 1.0f;
        this.smallIslandAlpha = 1.0f;
        this.smallIslandScale = 1.0f;
        this.expandedAlpha = 1.0f;
        this.expandedScaleX = 1.0f;
        this.expandedScaleY = 1.0f;
        this.fakeExpandedAlpha = 1.0f;
        this.fakeBigAlpha = 1.0f;
        this.fakeSmallAlpha = 1.0f;
        this.choreographer = Choreographer.getInstance();
        FolmeKt.cleanWhenViewDetached(this, view);
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.dynamicisland.anim.b
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                DynamicIslandAnimationDelegate.frameCallback$lambda$0(this.f5715a, j2);
            }
        };
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        float debugIslandAnimScale = dynamicIslandUtils.getDebugIslandAnimScale(context);
        this.debugIslandAnimScale = debugIslandAnimScale;
        this.CHANGE_EASE = FolmeEase.spring(0.82f, 0.4f * debugIslandAnimScale);
        this.SHOW_EASE = FolmeEase.spring(0.95f, 0.35f * debugIslandAnimScale);
        this.HIDDEN_EASE = FolmeEase.spring(1.0f, 0.2f * debugIslandAnimScale);
        this.APPEAR_EASE = FolmeEase.spring(0.7f, 0.5f * debugIslandAnimScale);
        this.SCALE_EASE = FolmeEase.spring(1.0f, debugIslandAnimScale * 1.0f);
        this.ALPHA_EASE = FolmeEase.spring(0.95f, 0.15f * debugIslandAnimScale);
        this.FAKE_ALPHA_EASE = FolmeEase.spring(1.0f, debugIslandAnimScale * 0.1f);
    }

    private final void animToFakeBigIslandView(final DynamicIslandContentView dynamicIslandContentView) {
        AnimState animStateAdd;
        AnimState animStateAdd2;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "animToFakeBigIslandView");
        DynamicIslandState lastState = dynamicIslandContentView.getLastState();
        if (lastState instanceof DynamicIslandState.SmallIsland) {
            AnimState animState = new AnimState();
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty = FAKE_SMALL_BLUR;
            AnimState animStateAdd3 = animState.add((FloatProperty) floatProperty, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty2 = FAKE_SMALL_ALPHA;
            AnimState animStateAdd4 = animStateAdd3.add((FloatProperty) floatProperty2, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty3 = FAKE_BIG_BLUR;
            AnimState animStateAdd5 = animStateAdd4.add((FloatProperty) floatProperty3, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty4 = FAKE_BIG_ALPHA;
            AnimState animStateAdd6 = animStateAdd5.add((FloatProperty) floatProperty4, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty5 = FAKE_EXPANDED_BLUR;
            AnimState animStateAdd7 = animStateAdd6.add((FloatProperty) floatProperty5, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty6 = FAKE_EXPANDED_ALPHA;
            animStateAdd = animStateAdd7.add((FloatProperty) floatProperty6, 0.0f, new long[0]);
            animStateAdd2 = new AnimState().add((FloatProperty) floatProperty, 1.0f, new long[0]).add((FloatProperty) floatProperty2, 0.0f, new long[0]).add((FloatProperty) floatProperty3, 0.0f, new long[0]).add((FloatProperty) floatProperty4, 1.0f, new long[0]).add((FloatProperty) floatProperty5, 0.0f, new long[0]).add((FloatProperty) floatProperty6, 0.0f, new long[0]);
        } else if (lastState instanceof DynamicIslandState.Expanded) {
            AnimState animState2 = new AnimState();
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty7 = FAKE_EXPANDED_BLUR;
            AnimState animStateAdd8 = animState2.add((FloatProperty) floatProperty7, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty8 = FAKE_EXPANDED_ALPHA;
            AnimState animStateAdd9 = animStateAdd8.add((FloatProperty) floatProperty8, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty9 = FAKE_BIG_BLUR;
            AnimState animStateAdd10 = animStateAdd9.add((FloatProperty) floatProperty9, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty10 = FAKE_BIG_ALPHA;
            AnimState animStateAdd11 = animStateAdd10.add((FloatProperty) floatProperty10, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty11 = FAKE_SMALL_BLUR;
            AnimState animStateAdd12 = animStateAdd11.add((FloatProperty) floatProperty11, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty12 = FAKE_SMALL_ALPHA;
            animStateAdd = animStateAdd12.add((FloatProperty) floatProperty12, 0.0f, new long[0]);
            animStateAdd2 = new AnimState().add((FloatProperty) floatProperty7, 1.0f, new long[0]).add((FloatProperty) floatProperty8, 0.0f, new long[0]).add((FloatProperty) floatProperty9, 0.0f, new long[0]).add((FloatProperty) floatProperty10, 1.0f, new long[0]).add((FloatProperty) floatProperty11, 0.0f, new long[0]).add((FloatProperty) floatProperty12, 0.0f, new long[0]);
        } else {
            animStateAdd = null;
            animStateAdd2 = null;
        }
        if (animStateAdd == null || animStateAdd2 == null) {
            return;
        }
        this.fakeViewAnimating = true;
        FolmeKt.getFolme(this).setTo(animStateAdd).to(animStateAdd2, new AnimConfig().setSpecial(FAKE_SMALL_ALPHA, this.FAKE_ALPHA_EASE, new float[0]).setSpecial(FAKE_EXPANDED_ALPHA, this.FAKE_ALPHA_EASE, new float[0]).setSpecial(FAKE_BIG_ALPHA, (EaseManager.EaseStyle) null, 50L, new float[0]).addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.animToFakeBigIslandView.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                FrameLayout fakeBigIsland = fakeView != null ? fakeView.getFakeBigIsland() : null;
                if (fakeBigIsland != null) {
                    fakeBigIsland.setAlpha(0.0f);
                }
                DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeBigIsland2 = fakeView2 != null ? fakeView2.getFakeBigIsland() : null;
                if (fakeBigIsland2 == null) {
                    return;
                }
                fakeBigIsland2.setVisibility(0);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                this.fakeViewAnimating = false;
                DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                FrameLayout fakeSmallIsland = fakeView != null ? fakeView.getFakeSmallIsland() : null;
                if (fakeSmallIsland != null) {
                    fakeSmallIsland.setVisibility(4);
                }
                DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeExpandedView = fakeView2 != null ? fakeView2.getFakeExpandedView() : null;
                if (fakeExpandedView == null) {
                    return;
                }
                fakeExpandedView.setVisibility(4);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                this.scheduleUpdate();
            }
        }));
    }

    private final void animToFakeExpandedView(final DynamicIslandContentView dynamicIslandContentView) {
        AnimState animStateAdd;
        AnimState animStateAdd2;
        DynamicIslandContentFakeView fakeView;
        View miniBar;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "animToFakeExpandedView");
        if (this.islandAppAnimRunning && (fakeView = dynamicIslandContentView.getFakeView()) != null && (miniBar = fakeView.getMiniBar()) != null) {
            int expandedViewWidth = dynamicIslandContentView.getExpandedViewWidth();
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            Context context = dynamicIslandContentView.getContext();
            n.f(context, "getContext(...)");
            float screenWidthOld = dynamicIslandUtils.getScreenWidthOld(context);
            float screenWidthOld2 = expandedViewWidth;
            if (screenWidthOld2 == 0.0f) {
                Context context2 = dynamicIslandContentView.getContext();
                n.f(context2, "getContext(...)");
                screenWidthOld2 = dynamicIslandUtils.getScreenWidthOld(context2);
            }
            miniBar.setTranslationY((miniBar.getTranslationY() * (screenWidthOld / screenWidthOld2)) - dynamicIslandContentView.getIslandViewMarginTop());
        }
        DynamicIslandState lastState = dynamicIslandContentView.getLastState();
        if (lastState instanceof DynamicIslandState.SmallIsland) {
            AnimState animState = new AnimState();
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty = FAKE_SMALL_BLUR;
            AnimState animStateAdd3 = animState.add((FloatProperty) floatProperty, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty2 = FAKE_SMALL_ALPHA;
            AnimState animStateAdd4 = animStateAdd3.add((FloatProperty) floatProperty2, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty3 = FAKE_EXPANDED_BLUR;
            AnimState animStateAdd5 = animStateAdd4.add((FloatProperty) floatProperty3, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty4 = FAKE_EXPANDED_ALPHA;
            AnimState animStateAdd6 = animStateAdd5.add((FloatProperty) floatProperty4, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty5 = FAKE_BIG_BLUR;
            AnimState animStateAdd7 = animStateAdd6.add((FloatProperty) floatProperty5, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty6 = FAKE_BIG_ALPHA;
            animStateAdd = animStateAdd7.add((FloatProperty) floatProperty6, 0.0f, new long[0]);
            animStateAdd2 = new AnimState().add((FloatProperty) floatProperty, 1.0f, new long[0]).add((FloatProperty) floatProperty2, 0.0f, new long[0]).add((FloatProperty) floatProperty3, 0.0f, new long[0]).add((FloatProperty) floatProperty4, 1.0f, new long[0]).add((FloatProperty) floatProperty5, 0.0f, new long[0]).add((FloatProperty) floatProperty6, 0.0f, new long[0]);
        } else if (lastState instanceof DynamicIslandState.BigIsland) {
            AnimState animState2 = new AnimState();
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty7 = FAKE_EXPANDED_BLUR;
            AnimState animStateAdd8 = animState2.add((FloatProperty) floatProperty7, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty8 = FAKE_EXPANDED_ALPHA;
            AnimState animStateAdd9 = animStateAdd8.add((FloatProperty) floatProperty8, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty9 = FAKE_BIG_BLUR;
            AnimState animStateAdd10 = animStateAdd9.add((FloatProperty) floatProperty9, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty10 = FAKE_BIG_ALPHA;
            AnimState animStateAdd11 = animStateAdd10.add((FloatProperty) floatProperty10, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty11 = FAKE_SMALL_BLUR;
            AnimState animStateAdd12 = animStateAdd11.add((FloatProperty) floatProperty11, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty12 = FAKE_SMALL_ALPHA;
            animStateAdd = animStateAdd12.add((FloatProperty) floatProperty12, 0.0f, new long[0]);
            animStateAdd2 = new AnimState().add((FloatProperty) floatProperty7, 0.0f, new long[0]).add((FloatProperty) floatProperty8, 1.0f, new long[0]).add((FloatProperty) floatProperty9, 1.0f, new long[0]).add((FloatProperty) floatProperty10, 0.0f, new long[0]).add((FloatProperty) floatProperty11, 0.0f, new long[0]).add((FloatProperty) floatProperty12, 0.0f, new long[0]);
        } else {
            animStateAdd = null;
            animStateAdd2 = null;
        }
        if (animStateAdd == null || animStateAdd2 == null) {
            return;
        }
        this.fakeViewAnimating = true;
        FolmeKt.getFolme(this).setTo(animStateAdd).to(animStateAdd2, new AnimConfig().setSpecial(FAKE_SMALL_ALPHA, this.FAKE_ALPHA_EASE, new float[0]).setSpecial(FAKE_BIG_ALPHA, this.FAKE_ALPHA_EASE, new float[0]).setSpecial(FAKE_EXPANDED_ALPHA, (EaseManager.EaseStyle) null, 50L, new float[0]).addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.animToFakeExpandedView.2
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeExpandedView = fakeView2 != null ? fakeView2.getFakeExpandedView() : null;
                if (fakeExpandedView != null) {
                    fakeExpandedView.setAlpha(0.0f);
                }
                DynamicIslandContentFakeView fakeView3 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeExpandedView2 = fakeView3 != null ? fakeView3.getFakeExpandedView() : null;
                if (fakeExpandedView2 == null) {
                    return;
                }
                fakeExpandedView2.setVisibility(0);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                this.fakeViewAnimating = false;
                DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeBigIsland = fakeView2 != null ? fakeView2.getFakeBigIsland() : null;
                if (fakeBigIsland != null) {
                    fakeBigIsland.setVisibility(4);
                }
                DynamicIslandContentFakeView fakeView3 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeSmallIsland = fakeView3 != null ? fakeView3.getFakeSmallIsland() : null;
                if (fakeSmallIsland == null) {
                    return;
                }
                fakeSmallIsland.setVisibility(4);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                this.scheduleUpdate();
            }
        }));
    }

    private final void animToFakeSmallIslandView(final DynamicIslandContentView dynamicIslandContentView) {
        AnimState animStateAdd;
        AnimState animStateAdd2;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "animToFakeSmallIslandView");
        DynamicIslandState lastState = dynamicIslandContentView.getLastState();
        if (lastState instanceof DynamicIslandState.BigIsland) {
            AnimState animState = new AnimState();
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty = FAKE_BIG_BLUR;
            AnimState animStateAdd3 = animState.add((FloatProperty) floatProperty, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty2 = FAKE_BIG_ALPHA;
            AnimState animStateAdd4 = animStateAdd3.add((FloatProperty) floatProperty2, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty3 = FAKE_SMALL_BLUR;
            AnimState animStateAdd5 = animStateAdd4.add((FloatProperty) floatProperty3, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty4 = FAKE_SMALL_ALPHA;
            AnimState animStateAdd6 = animStateAdd5.add((FloatProperty) floatProperty4, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty5 = FAKE_EXPANDED_BLUR;
            AnimState animStateAdd7 = animStateAdd6.add((FloatProperty) floatProperty5, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty6 = FAKE_EXPANDED_ALPHA;
            animStateAdd = animStateAdd7.add((FloatProperty) floatProperty6, 0.0f, new long[0]);
            animStateAdd2 = new AnimState().add((FloatProperty) floatProperty, 1.0f, new long[0]).add((FloatProperty) floatProperty2, 0.0f, new long[0]).add((FloatProperty) floatProperty3, 0.0f, new long[0]).add((FloatProperty) floatProperty4, 1.0f, new long[0]).add((FloatProperty) floatProperty5, 0.0f, new long[0]).add((FloatProperty) floatProperty6, 0.0f, new long[0]);
        } else if (lastState instanceof DynamicIslandState.Expanded) {
            AnimState animState2 = new AnimState();
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty7 = FAKE_SMALL_BLUR;
            AnimState animStateAdd8 = animState2.add((FloatProperty) floatProperty7, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty8 = FAKE_SMALL_ALPHA;
            AnimState animStateAdd9 = animStateAdd8.add((FloatProperty) floatProperty8, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty9 = FAKE_EXPANDED_BLUR;
            AnimState animStateAdd10 = animStateAdd9.add((FloatProperty) floatProperty9, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty10 = FAKE_EXPANDED_ALPHA;
            AnimState animStateAdd11 = animStateAdd10.add((FloatProperty) floatProperty10, 1.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty11 = FAKE_BIG_BLUR;
            AnimState animStateAdd12 = animStateAdd11.add((FloatProperty) floatProperty11, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty12 = FAKE_BIG_ALPHA;
            animStateAdd = animStateAdd12.add((FloatProperty) floatProperty12, 0.0f, new long[0]);
            animStateAdd2 = new AnimState().add((FloatProperty) floatProperty7, 0.0f, new long[0]).add((FloatProperty) floatProperty8, 1.0f, new long[0]).add((FloatProperty) floatProperty9, 1.0f, new long[0]).add((FloatProperty) floatProperty10, 0.0f, new long[0]).add((FloatProperty) floatProperty11, 0.0f, new long[0]).add((FloatProperty) floatProperty12, 0.0f, new long[0]);
        } else {
            animStateAdd = null;
            animStateAdd2 = null;
        }
        if (animStateAdd == null || animStateAdd2 == null) {
            return;
        }
        this.fakeViewAnimating = true;
        FolmeKt.getFolme(this).setTo(animStateAdd).to(animStateAdd2, new AnimConfig().setSpecial(FAKE_EXPANDED_ALPHA, this.FAKE_ALPHA_EASE, new float[0]).setSpecial(FAKE_BIG_ALPHA, this.FAKE_ALPHA_EASE, new float[0]).setSpecial(FAKE_SMALL_ALPHA, (EaseManager.EaseStyle) null, 50L, new float[0]).addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.animToFakeSmallIslandView.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                FrameLayout fakeSmallIsland = fakeView != null ? fakeView.getFakeSmallIsland() : null;
                if (fakeSmallIsland != null) {
                    fakeSmallIsland.setAlpha(0.0f);
                }
                DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeSmallIsland2 = fakeView2 != null ? fakeView2.getFakeSmallIsland() : null;
                if (fakeSmallIsland2 == null) {
                    return;
                }
                fakeSmallIsland2.setVisibility(0);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                this.fakeViewAnimating = false;
                DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
                FrameLayout fakeBigIsland = fakeView != null ? fakeView.getFakeBigIsland() : null;
                if (fakeBigIsland != null) {
                    fakeBigIsland.setVisibility(4);
                }
                DynamicIslandContentFakeView fakeView2 = dynamicIslandContentView.getFakeView();
                FrameLayout fakeExpandedView = fakeView2 != null ? fakeView2.getFakeExpandedView() : null;
                if (fakeExpandedView == null) {
                    return;
                }
                fakeExpandedView.setVisibility(4);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                this.scheduleUpdate();
            }
        }));
    }

    private final float calculateSwipeAlpha(float f2) {
        float f3 = ((f2 * (-0.8f)) / 0.14f) + 1;
        if (f3 < 0.2f) {
            return 0.2f;
        }
        return f3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$0(DynamicIslandAnimationDelegate this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        this$0.containerScheduleUpdate();
        this$0.bigIslandScheduleUpdate();
        this$0.smallIslandScheduleUpdate();
        this$0.expandedScheduleUpdate();
        this$0.fakeViewScheduleUpdate();
    }

    private final int getAppAnimSmallX() {
        int appAnimSmallX = this.windowView.getWindowViewController().getAppAnimSmallX();
        if (this.bigIslandStateHandler.getCurrent() != null || appAnimSmallX == 0) {
            return 0;
        }
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        return (appAnimSmallX - (dynamicIslandUtils.getScreenWidthOld(context) / 2)) + (this.view.getSmallIslandViewWidth() / 2);
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    private final AnimState getBigIslandAnimState() {
        DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
        if (backgroundView != null) {
            backgroundView.setZ(3.0f);
        }
        AnimState animStateAdd = new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getBigIslandLeft(), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getBigIslandWidth(), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getBigIslandTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getBigIslandHeight(), new long[0]).add((FloatProperty) SMALL_ISLAND_ALPHA, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_BLUR, 0.0f, new long[0]).add(SMALL_ISLAND_TRANS_X, getSmallTransXToBig(), new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_ALPHA, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_BLUR, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_AREA_LEFT_TRANS_X, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_AREA_RIGHT_TRANS_X, 0.0f, new long[0]).add((FloatProperty) EXPANDED_ALPHA, 0.0f, new long[0]).add(EXPANDED_SCALE_X, this.view.getBigIslandViewWidth() / this.view.getExpandedViewWidth(), new long[0]).add(EXPANDED_SCALE_Y, this.view.getBigIslandViewWidth() / this.view.getExpandedViewWidth(), new long[0]).add((FloatProperty) EXPANDED_BLUR, 1.0f, new long[0]).add(EXPANDED_TRANS_Y, -getExpandedTransYToBig(), new long[0]);
        n.f(animStateAdd, "add(...)");
        return animStateAdd;
    }

    private final AnimState getCutoutAnimState() {
        DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
        if (backgroundView != null) {
            backgroundView.setZ(0.0f);
        }
        AnimState animStateAdd = new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) CONTAINER_ALPHA, 0.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getCutoutLeft(), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getCutoutWidth(), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getCutoutTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getCutoutHeight(), new long[0]).add((FloatProperty) SMALL_ISLAND_ALPHA, 0.0f, new long[0]).add(SMALL_ISLAND_SCALE, this.view.getCutoutWidth() / this.view.getIslandViewHeight(), new long[0]).add((FloatProperty) SMALL_ISLAND_BLUR, 1.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_X, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_ALPHA, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_BLUR, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_TRANS_Y, 0.0f, new long[0]).add(BIG_ISLAND_AREA_LEFT_TRANS_X, getAreaLeftCollapseTransX(), new long[0]).add(BIG_ISLAND_AREA_RIGHT_TRANS_X, getAreaRightCollapseTransX(), new long[0]).add((FloatProperty) EXPANDED_ALPHA, 0.0f, new long[0]).add(EXPANDED_SCALE_X, this.view.getCutoutWidth() / this.view.getExpandedViewWidth(), new long[0]).add(EXPANDED_SCALE_Y, this.view.getCutoutWidth() / this.view.getExpandedViewWidth(), new long[0]).add((FloatProperty) EXPANDED_BLUR, 1.0f, new long[0]).add(EXPANDED_TRANS_Y, -getExpandedTransYToCutout(), new long[0]);
        n.f(animStateAdd, "add(...)");
        return animStateAdd;
    }

    private final AnimState getExpandedAnimState() {
        DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
        if (backgroundView != null) {
            backgroundView.setZ(2.0f);
        }
        AnimState animStateAdd = new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, this.view.getExpandedViewY() - this.view.getIslandViewMarginTop(), new long[0]).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getExpandedLeft(), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getExpandedWidth(), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getExpandedTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getExpandedHeight(), new long[0]).add((FloatProperty) SMALL_ISLAND_ALPHA, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_BLUR, 0.0f, new long[0]).add(SMALL_ISLAND_TRANS_X, getSmallTransXToExpanded(), new long[0]).add(SMALL_ISLAND_TRANS_Y, getSmallTransYToExpanded(), new long[0]).add((FloatProperty) BIG_ISLAND_ALPHA, 0.0f, new long[0]).add(BIG_ISLAND_SCALE, this.view.getExpandedViewWidth() / this.view.getBigIslandViewWidth(), new long[0]).add((FloatProperty) BIG_ISLAND_BLUR, 1.0f, new long[0]).add(BIG_ISLAND_TRANS_Y, getBigTransYToExpanded(), new long[0]).add((FloatProperty) BIG_ISLAND_AREA_LEFT_TRANS_X, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_AREA_RIGHT_TRANS_X, 0.0f, new long[0]).add((FloatProperty) EXPANDED_ALPHA, 1.0f, new long[0]).add((FloatProperty) EXPANDED_SCALE_X, 1.0f, new long[0]).add((FloatProperty) EXPANDED_SCALE_Y, 1.0f, new long[0]).add((FloatProperty) EXPANDED_BLUR, 0.0f, new long[0]).add((FloatProperty) EXPANDED_TRANS_Y, 0.0f, new long[0]);
        n.f(animStateAdd, "add(...)");
        return animStateAdd;
    }

    private final AnimState getHiddenAnimState() {
        DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
        if (backgroundView != null) {
            backgroundView.setZ(0.0f);
        }
        AnimState animStateAdd = new AnimState().add((FloatProperty) CONTAINER_X, getSmallIslandX(), new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) CONTAINER_ALPHA, 0.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getSmallIslandLeft() + (this.view.getSmallIslandViewWidth() * 0.2f), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getSmallIslandWidth() - (this.view.getSmallIslandViewWidth() * 0.2f), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getSmallIslandTop() + (this.view.getIslandViewHeight() * 0.2f), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getSmallIslandHeight() - (this.view.getIslandViewHeight() * 0.2f), new long[0]).add((FloatProperty) SMALL_ISLAND_ALPHA, 1.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_SCALE, 0.6f, new long[0]).add((FloatProperty) SMALL_ISLAND_BLUR, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_X, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_ALPHA, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_BLUR, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_TRANS_Y, 0.0f, new long[0]).add(BIG_ISLAND_AREA_LEFT_TRANS_X, getAreaLeftCollapseTransX(), new long[0]).add(BIG_ISLAND_AREA_RIGHT_TRANS_X, getAreaRightCollapseTransX(), new long[0]).add((FloatProperty) EXPANDED_ALPHA, 0.0f, new long[0]).add(EXPANDED_SCALE_X, this.view.getSmallIslandViewWidth() / this.view.getExpandedViewWidth(), new long[0]).add(EXPANDED_SCALE_Y, this.view.getSmallIslandViewWidth() / this.view.getExpandedViewWidth(), new long[0]).add((FloatProperty) EXPANDED_BLUR, 1.0f, new long[0]).add(EXPANDED_TRANS_Y, -getExpandedTransYToSmall(), new long[0]);
        n.f(animStateAdd, "add(...)");
        return animStateAdd;
    }

    private final AnimState getSmallIslandAnimState() {
        DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
        if (backgroundView != null) {
            backgroundView.setZ(1.0f);
        }
        AnimState animStateAdd = new AnimState().add((FloatProperty) CONTAINER_X, getSmallIslandX(), new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getSmallIslandLeft(), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getSmallIslandWidth(), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getSmallIslandTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getSmallIslandHeight(), new long[0]).add((FloatProperty) SMALL_ISLAND_ALPHA, 1.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_BLUR, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_X, 0.0f, new long[0]).add((FloatProperty) SMALL_ISLAND_TRANS_Y, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_ALPHA, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_SCALE, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_BLUR, 0.0f, new long[0]).add((FloatProperty) BIG_ISLAND_TRANS_Y, 0.0f, new long[0]).add(BIG_ISLAND_AREA_LEFT_TRANS_X, getAreaLeftCollapseTransX(), new long[0]).add(BIG_ISLAND_AREA_RIGHT_TRANS_X, getAreaRightCollapseTransX(), new long[0]).add((FloatProperty) EXPANDED_ALPHA, 0.0f, new long[0]).add(EXPANDED_SCALE_X, this.view.getSmallIslandViewWidth() / this.view.getExpandedViewWidth(), new long[0]).add(EXPANDED_SCALE_Y, this.view.getSmallIslandViewWidth() / this.view.getExpandedViewWidth(), new long[0]).add((FloatProperty) EXPANDED_BLUR, 1.0f, new long[0]).add(EXPANDED_TRANS_Y, -getExpandedTransYToSmall(), new long[0]);
        n.f(animStateAdd, "add(...)");
        return animStateAdd;
    }

    private final int getSmallIslandX() {
        int smallIslandX = this.dynamicIslandAnimController.getSmallIslandX();
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        return (smallIslandX - (dynamicIslandUtils.getScreenWidthOld(context) / 2)) + (this.view.getSmallIslandViewWidth() / 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final float getSupposedContainerAlpha() {
        /*
            r4 = this;
            miui.systemui.util.FoldUtils r0 = miui.systemui.util.FoldUtils.INSTANCE
            miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView r1 = r4.view
            boolean r0 = r0.isFoldScreenLayoutLarge(r1)
            boolean r1 = miui.systemui.util.CommonUtils.isFlipDevice()
            java.lang.String r2 = "getContext(...)"
            if (r1 == 0) goto L21
            miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView r1 = r4.view
            android.content.Context r1 = r1.getContext()
            kotlin.jvm.internal.n.f(r1, r2)
            boolean r1 = miui.systemui.util.CommonUtils.isTinyScreen(r1)
            if (r1 == 0) goto L21
            r1 = 1
            goto L22
        L21:
            r1 = 0
        L22:
            miui.systemui.util.CommonUtils r3 = miui.systemui.util.CommonUtils.INSTANCE
            miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView r4 = r4.view
            android.content.Context r4 = r4.getContext()
            kotlin.jvm.internal.n.f(r4, r2)
            boolean r4 = r3.getInVerticalMode(r4)
            if (r4 == 0) goto L42
            if (r0 != 0) goto L42
            if (r1 != 0) goto L42
            miui.systemui.util.OneHandModeUtils r4 = miui.systemui.util.OneHandModeUtils.INSTANCE
            boolean r4 = r4.isOneHandMode()
            if (r4 != 0) goto L42
            r4 = 1065353216(0x3f800000, float:1.0)
            goto L43
        L42:
            r4 = 0
        L43:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.getSupposedContainerAlpha():float");
    }

    private final float getSwipeLeftAreaTransX(float f2) {
        return (float) (((double) ((isRtlLayout() ? -this.view.getBigIslandViewWidth() : this.view.getBigIslandViewWidth()) * f2)) * 0.5d);
    }

    private final float getSwipeRightAreaTransX(float f2) {
        return (float) (((double) ((isRtlLayout() ? this.view.getBigIslandViewWidth() : -this.view.getBigIslandViewWidth()) * f2)) * 0.5d);
    }

    private final float getSwipeSmallToBigX(float f2) {
        int smallIslandX = getSmallIslandX();
        Integer currentBigIslandViewWidth = this.view.getCurrentBigIslandViewWidth();
        int iIntValue = currentBigIslandViewWidth != null ? currentBigIslandViewWidth.intValue() : 0;
        return (float) (isRtlLayout() ? ((double) smallIslandX) + (((double) (iIntValue * f2)) * 0.25d) : ((double) smallIslandX) - (((double) (iIntValue * f2)) * 0.25d));
    }

    private final boolean hasSmallIsland() {
        return this.view.hasSmallIsland();
    }

    private final void initToHiddenNoAnim(DynamicIslandContentView dynamicIslandContentView) {
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.initToHiddenNoAnim.1
            {
                super(this);
            }
        }));
    }

    private final boolean isRtlLayout() {
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        return CommonUtils.isLayoutRtl(context);
    }

    private final void onSwipeBigToSmallAnimation(DynamicIslandContentView dynamicIslandContentView, float f2) {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = dynamicIslandContentView.getContext();
        n.f(context, "getContext(...)");
        float fAbs = Math.abs(ratio(dynamicIslandUtils.getScreenWidthOld(context) / 2, (int) f2));
        FolmeKt.getFolme(this).to(new AnimState().add(CONTAINER_X, ((isRtlLayout() ? getSmallIslandX() : this.dynamicIslandAnimController.getSmallIslandX()) - DynamicIslandBaseContentView.getCurrentBigIslandX$default(dynamicIslandContentView, null, 1, null)) * fAbs, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, ((double) getBigIslandLeft()) + (((double) (dynamicIslandContentView.getBigIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_END_PROGRESS, ((double) getBigIslandWidth()) - (((double) (dynamicIslandContentView.getBigIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_TOP_PROGRESS, getBigIslandTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getBigIslandHeight(), new long[0]).add(BIG_ISLAND_AREA_LEFT_TRANS_X, getSwipeLeftAreaTransX(fAbs), new long[0]).add(BIG_ISLAND_AREA_RIGHT_TRANS_X, getSwipeRightAreaTransX(fAbs), new long[0]).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add(BIG_ISLAND_ALPHA, calculateSwipeAlpha(fAbs), new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onSwipeBigToSmallAnimation.1
            {
                super(this);
            }
        }));
    }

    private final void onSwipeExpandedToBig(float f2, float f3) {
        if (Math.abs(f2) > Math.abs(f3)) {
            swipeLeftExpandedAnimation(f2);
        } else if (Math.abs(f2) >= Math.abs(f3) || f3 >= 0.0f) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_SWIPE, "error on swipe expandedState");
        } else {
            swipeUpExpandedAnimation(f3);
        }
    }

    private final void onSwipeSmallIslandToHiddenAnimation(DynamicIslandContentView dynamicIslandContentView, float f2) {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = dynamicIslandContentView.getContext();
        n.f(context, "getContext(...)");
        float fAbs = Math.abs(ratio(dynamicIslandUtils.getScreenWidthOld(context) / 2, (int) f2));
        FolmeKt.getFolme(this).to(new AnimState().add((FloatProperty) CONTAINER_ALPHA, 0.8f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, ((double) getSmallIslandLeft()) + (((double) (dynamicIslandContentView.getSmallIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_END_PROGRESS, ((double) getSmallIslandWidth()) - (((double) (dynamicIslandContentView.getSmallIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_TOP_PROGRESS, ((double) getSmallIslandTop()) + (((double) (dynamicIslandContentView.getIslandViewHeight() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_BOTTOM_PROGRESS, ((double) getSmallIslandHeight()) - (((double) (dynamicIslandContentView.getIslandViewHeight() * fAbs)) * 0.5d)).add(SMALL_ISLAND_ALPHA, calculateSwipeAlpha(fAbs), new long[0]).add((FloatProperty) SMALL_ISLAND_SCALE, 0.8f, new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onSwipeSmallIslandToHiddenAnimation.1
            {
                super(this);
            }
        }));
    }

    private final void onSwipeSmallToTempHidden(float f2) {
        double smallIslandX;
        if (this.view.getCurrentBigIslandViewWidth() == null) {
            return;
        }
        DynamicIslandContentView current = this.bigIslandStateHandler.getCurrent();
        if ((current != null ? Integer.valueOf(current.getBigIslandViewWidth()) : null) == null) {
            return;
        }
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        float fAbs = Math.abs(ratio(dynamicIslandUtils.getScreenWidthOld(context) / 2, (int) f2));
        if (isRtlLayout()) {
            smallIslandX = getSmallIslandX() + (((double) ((this.view.getCurrentBigIslandViewWidth() != null ? r1.intValue() : 0) * fAbs)) * 0.5d);
        } else {
            smallIslandX = getSmallIslandX() - (((double) ((this.view.getCurrentBigIslandViewWidth() != null ? r1.intValue() : 0) * fAbs)) * 0.5d);
        }
        FolmeKt.getFolme(this).to(new AnimState().add(CONTAINER_X, smallIslandX).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add(SMALL_ISLAND_ALPHA, calculateSwipeAlpha(fAbs), new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onSwipeSmallToTempHidden.1
            {
                super(this);
            }
        }));
    }

    private final float ratio(int i2, int i3) {
        return Math.abs(i3 / i2) * 0.14f;
    }

    private final void resetToBig(DynamicIslandContentView dynamicIslandContentView) {
        FolmeKt.getFolme(this).to(getBigIslandAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetToBig.1
            {
                super(this);
            }
        }));
    }

    private final void resetToExpanded(DynamicIslandContentView dynamicIslandContentView) {
        FolmeKt.getFolme(this).to(getExpandedAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetToExpanded.1
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
            }
        }));
    }

    private final void resetToHidden(DynamicIslandContentView dynamicIslandContentView) {
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetToHidden.1
            {
                super(this);
            }
        }));
    }

    private final void resetToSmall(DynamicIslandContentView dynamicIslandContentView) {
        FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetToSmall.1
            {
                super(this);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnimateExpanding(boolean z2) {
        if (this.isAnimateExpanding == z2) {
            return;
        }
        this.isAnimateExpanding = z2;
        DynamicIslandExpandedView expandedView = this.view.getExpandedView();
        if (expandedView != null) {
            expandedView.setTag(R.id.dynamic_island_animate_expanding_tag, Boolean.valueOf(z2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewVisible(DynamicIslandBaseContentView dynamicIslandBaseContentView, int i2) {
        if (dynamicIslandBaseContentView != null) {
            dynamicIslandBaseContentView.setVisibility(i2);
        }
        DynamicIslandBackgroundView backgroundView = dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getBackgroundView() : null;
        if (backgroundView == null) {
            return;
        }
        backgroundView.setVisibility(i2);
    }

    private final void smallIslandToTempHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToTempHiddenNoAnimation.1
            {
                super(this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
            }
        }));
        DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    private final void swipeLeftExpandedAnimation(float f2) {
        float fAbs = Math.abs(ratio(this.view.getExpandedViewWidth(), (int) f2));
        float f3 = 1;
        FolmeKt.getFolme(this).to(new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, this.view.getExpandedViewY() - this.view.getIslandViewMarginTop(), new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getExpandedLeft() + (this.view.getExpandedViewWidth() * fAbs * 0.5f), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getExpandedWidth() - ((this.view.getExpandedViewWidth() * fAbs) * 0.5f), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getExpandedTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getExpandedHeight(), new long[0]).add(EXPANDED_SCALE_X, f3 - Math.abs(fAbs), new long[0]).add((FloatProperty) EXPANDED_SCALE_Y, 1.0f, new long[0]).add(EXPANDED_ALPHA, calculateSwipeAlpha(fAbs), new long[0]).add(EXPANDED_BLUR, f3 - calculateSwipeAlpha(fAbs), new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.swipeLeftExpandedAnimation.1
            {
                super(this);
            }
        }));
    }

    private final void swipeUpExpandedAnimation(float f2) {
        float fAbs = Math.abs(ratio(this.view.getExpandedViewHeight(), (int) f2));
        float f3 = 1;
        FolmeKt.getFolme(this).to(new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, this.view.getExpandedViewY() - this.view.getIslandViewMarginTop(), new long[0]).add(CONTAINER_CLIP_START_PROGRESS, getExpandedLeft(), new long[0]).add(CONTAINER_CLIP_END_PROGRESS, getExpandedWidth(), new long[0]).add(CONTAINER_CLIP_TOP_PROGRESS, getExpandedTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getExpandedHeight() - (this.view.getExpandedViewHeight() * fAbs), new long[0]).add(EXPANDED_ALPHA, calculateSwipeAlpha(fAbs), new long[0]).add((FloatProperty) EXPANDED_SCALE_X, 1.0f, new long[0]).add(EXPANDED_SCALE_Y, f3 - fAbs, new long[0]).add(EXPANDED_TRANS_Y, ((double) ((-this.view.getExpandedViewHeight()) * fAbs)) * 0.5d).add(EXPANDED_BLUR, f3 - calculateSwipeAlpha(fAbs), new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.swipeUpExpandedAnimation.1
            {
                super(this);
            }
        }));
    }

    public static /* synthetic */ void tempHiddenToBigIslandAnimation$default(DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate, DynamicIslandContentView dynamicIslandContentView, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        dynamicIslandAnimationDelegate.tempHiddenToBigIslandAnimation(dynamicIslandContentView, z2);
    }

    private final void tempHiddenToBigIslandNoAnimation(final DynamicIslandContentView dynamicIslandContentView) {
        setViewVisible(dynamicIslandContentView, 0);
        dynamicIslandContentView.updateBigIslandLayout();
        FolmeKt.getFolme(this).setTo(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.APPEAR_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.tempHiddenToBigIslandNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandBigIslandView bigIslandView = dynamicIslandContentView.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                Context context = dynamicIslandContentView.getContext();
                n.f(context, "getContext(...)");
                if (commonUtils.getInVerticalMode(context)) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, dynamicIslandContentView.getIslandViewMarginTop(), dynamicIslandContentView.getIslandViewHeight());
            }
        }));
    }

    public static /* synthetic */ void tempHiddenToSmallIslandAnimation$default(DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate, DynamicIslandContentView dynamicIslandContentView, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        dynamicIslandAnimationDelegate.tempHiddenToSmallIslandAnimation(dynamicIslandContentView, z2);
    }

    private final void tempHiddenToSmallIslandNoAnimation(final DynamicIslandContentView dynamicIslandContentView) {
        setViewVisible(dynamicIslandContentView, 0);
        FolmeKt.getFolme(this).setTo(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_X, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.tempHiddenToSmallIslandNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                FrameLayout smallIslandView = dynamicIslandContentView.getSmallIslandView();
                if (smallIslandView == null) {
                    return;
                }
                smallIslandView.setVisibility(0);
            }
        }));
    }

    public static /* synthetic */ void updateFakeViewStateForAppAnim$default(DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate, int i2, int i3, int i4, int i5, DynamicIslandContentView dynamicIslandContentView, boolean z2, boolean z3, int i6, Object obj) {
        dynamicIslandAnimationDelegate.updateFakeViewStateForAppAnim(i2, i3, i4, i5, dynamicIslandContentView, z2, (i6 & 64) != 0 ? false : z3);
    }

    public final void appExpandedToAppExpandedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public final void appExpandedToBigIslandAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        appExpandedToBigIslandAnimation(view, true);
    }

    public final void appExpandedToHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        expandedToHiddenAnimation(view, true);
    }

    public final void appExpandedToMiniWindowExpandedAnimation(DynamicIslandContentView view) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(view, "view");
        setViewVisible(view, 4);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null || (dynamicIslandEventCoordinator = fakeView.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        dynamicIslandEventCoordinator.updateFreeformFakeView(fakeView2, view, currentIslandData != null ? currentIslandData.getExtras() : null);
    }

    public final void appExpandedToSmallIslandAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        appExpandedToSmallIslandAnimation(view, true);
    }

    public final void beforeBigIslandToTempHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        setViewVisible(view, 8);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void beforeSmallIslandToTempHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        setViewVisible(view, 8);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void bigIslandChangedAnimation(final DynamicIslandContentView view) {
        DynamicIslandContentFakeView dynamicIslandContentFakeView;
        DynamicIslandContentFakeView dynamicIslandContentFakeView2;
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).to(getBigIslandAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandChangedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                view.updateBigIslandLayout();
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
            }
        }));
        if (!getIslandWindowAnimRunning() || (((dynamicIslandContentFakeView = this.fakeView) != null && dynamicIslandContentFakeView.getBigIslandViewNeedAnim()) || ((dynamicIslandContentFakeView2 = this.fakeView) != null && dynamicIslandContentFakeView2.getForceUpdateBigIslandView()))) {
            fakeViewToBigIsland(view, false);
        }
    }

    public final void bigIslandChangedNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).setTo(getBigIslandAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandChangedNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                view.updateBigIslandLayout();
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
            }
        }));
        if (getIslandWindowAnimRunning()) {
            return;
        }
        fakeViewToBigIsland(view, false);
    }

    public final void bigIslandScheduleUpdate() {
        DynamicIslandBigIslandView bigIslandView;
        DynamicIslandBigIslandView bigIslandView2 = this.view.getBigIslandView();
        if (bigIslandView2 != null) {
            bigIslandView2.setScaleX(this.bigIslandScale);
        }
        DynamicIslandBigIslandView bigIslandView3 = this.view.getBigIslandView();
        if (bigIslandView3 != null) {
            bigIslandView3.setScaleY(this.bigIslandScale);
        }
        DynamicIslandBigIslandView bigIslandView4 = this.view.getBigIslandView();
        if (bigIslandView4 != null) {
            bigIslandView4.setAlpha(this.bigIslandAlpha);
        }
        DynamicIslandBigIslandView bigIslandView5 = this.view.getBigIslandView();
        if (bigIslandView5 != null) {
            bigIslandView5.setTranslationY(this.bigIslandTransY);
        }
        FrameLayout bigIslandAreaLeft = this.view.getBigIslandAreaLeft();
        if (bigIslandAreaLeft != null) {
            bigIslandAreaLeft.setTranslationX(this.bigIslandAreaLeft);
        }
        FrameLayout bigIslandAreaRight = this.view.getBigIslandAreaRight();
        if (bigIslandAreaRight != null) {
            bigIslandAreaRight.setTranslationX(this.bigIslandAreaRight);
        }
        DynamicIslandBigIslandView bigIslandView6 = this.view.getBigIslandView();
        if (bigIslandView6 != null) {
            float f2 = this.containerClipStartProgress;
            float f3 = this.containerX;
            float f4 = f2 + f3;
            float f5 = this.containerClipTopProgress;
            float f6 = this.containerTransY;
            bigIslandView6.updateGlowEffectAnim$miui_dynamicisland_release(f4, f5 + f6, this.containerClipEndProgress + f3, this.containerClipBottomProgress + f6, containerClipRadius());
            DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
            bigIslandView6.setZOrderOfGlowEffect$miui_dynamicisland_release(backgroundView != null ? backgroundView.getZ() : 0.0f);
            bigIslandView6.setAlphaOfGlowEffect$miui_dynamicisland_release(this.bigIslandAlpha);
        }
        if (!this.supportBlur || (bigIslandView = this.view.getBigIslandView()) == null) {
            return;
        }
        updateContentBlur(bigIslandView, this.bigIslandBlur);
    }

    public final void bigIslandToAppExpandedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        bigIslandToHiddenAnimation(view, false);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateView(view);
        }
    }

    public final void bigIslandToDeletedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(BIG_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.ALPHA_EASE, new float[0]).setSpecial(BIG_ISLAND_BLUR, this.ALPHA_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToDeletedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x0058  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00ac  */
            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onBegin(java.lang.Object r7) {
                /*
                    Method dump skipped, instruction units count: 275
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.C05261.onBegin(java.lang.Object):void");
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
            }
        }));
        fakeViewToDeleted(view);
    }

    public final void bigIslandToDeletedNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToDeletedNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x0058  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00ac  */
            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onBegin(java.lang.Object r7) {
                /*
                    Method dump skipped, instruction units count: 282
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.C05271.onBegin(java.lang.Object):void");
            }
        }));
        fakeViewToDeleted(view);
    }

    public final void bigIslandToExpandedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getExpandedAnimState(), new AnimConfig().setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_X, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_ALPHA, this.SHOW_EASE, 50L, new float[0]).setSpecial(EXPANDED_BLUR, this.SHOW_EASE, 50L, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(BIG_ISLAND_BLUR, this.HIDDEN_EASE, new float[0]).setSpecial(EXPANDED_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_TRANS_Y, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToExpandedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(0);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.performAccessibilityAction(128, null);
                }
                DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(true, view.getExpandedViewY(), (int) (view.getIslandViewHeight() * (view.getExpandedViewHeight() / view.getIslandViewHeight())));
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                Log.e(DynamicIslandAnimationDelegate.this.TAG, "bigIslandToExpandedAnimation1 toTag " + obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(true);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                Log.e(DynamicIslandAnimationDelegate.this.TAG, "bigIslandToExpandedAnimation2 toTag " + obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.sendAccessibilityEvent(128);
                }
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }
        }));
        fakeViewToExpanded(view, false);
    }

    public final void bigIslandToHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        bigIslandToHiddenAnimation(view, true);
    }

    public final void bigIslandToMiniWindowExpandedAnimation(DynamicIslandContentView view) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(view, "view");
        bigIslandToHiddenAnimation(view, false);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null || (dynamicIslandEventCoordinator = fakeView.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        dynamicIslandEventCoordinator.updateFreeformFakeView(fakeView2, view, currentIslandData != null ? currentIslandData.getExtras() : null);
    }

    public final void bigIslandToSmallIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_TRANS_X, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToSmallIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                boolean zC;
                super.onBegin(obj);
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                String pkgName = view.getPkgName();
                if (pkgName != null) {
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
                    zC = n.c(dynamicIslandEventCoordinator != null ? Boolean.valueOf(dynamicIslandEventCoordinator.hasSamePackageIsland(pkgName)) : null, Boolean.FALSE);
                } else {
                    zC = false;
                }
                if (zC) {
                    DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                    DynamicIslandContentFakeView fakeView = view.getFakeView();
                    if (fakeView != null) {
                        fakeView.setVisibility(4);
                    }
                }
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }
        }));
        fakeViewToSmallIsland(view, false);
    }

    public final void bigIslandToSmallIslandNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_TRANS_X, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToSmallIslandNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView == null) {
                    return;
                }
                expandedView.setVisibility(4);
            }
        }));
        fakeViewToSmallIsland(view, false);
    }

    public final void bigIslandToTempHiddenAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(BIG_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.ALPHA_EASE, new float[0]).setSpecial(BIG_ISLAND_BLUR, this.ALPHA_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToTempHiddenAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                DynamicIslandWindowState windowState;
                u screenPinning;
                super.onBegin(obj);
                DynamicIslandWindowState windowState2 = view.getWindowState();
                if (windowState2 != null && !windowState2.getKeyguardShowing() && (windowState = view.getWindowState()) != null && (screenPinning = windowState.getScreenPinning()) != null && !((Boolean) screenPinning.getValue()).booleanValue()) {
                    DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                }
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                Context context = view.getContext();
                n.f(context, "getContext(...)");
                if (!commonUtils.getInVerticalMode(context) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null && DynamicIslandAnimationDelegate.this.bigIslandStateHandler.getCurrentTempShow() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, 0, 0);
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                expandedView.setVisibility(4);
            }
        }));
        if (getIslandWindowAnimRunning()) {
            fakeViewToHidden(view, true);
            return;
        }
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void bigIslandToTempHiddenNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToTempHiddenNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                Context context = view.getContext();
                n.f(context, "getContext(...)");
                if (!commonUtils.getInVerticalMode(context) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null && DynamicIslandAnimationDelegate.this.bigIslandStateHandler.getCurrentTempShow() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, 0, 0);
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                expandedView.setVisibility(4);
            }
        }));
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final float containerClipRadius() {
        return f.e((this.containerClipBottomProgress - this.containerClipTopProgress) / 2.0f, this.view.getContext().getResources().getDimension(R.dimen.island_radius));
    }

    public final void containerScheduleUpdate() {
        this.view.setTranslationX(this.containerX);
        this.view.setTranslationY(this.containerTransY);
        this.view.setAlpha(this.containerAlpha);
        DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
        if (backgroundView != null) {
            backgroundView.setAlpha(this.containerAlpha);
        }
        this.expandedViewAnimatingProgress = this.containerClipBottomProgress / getExpandedHeight();
        this.view.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.containerScheduleUpdate.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                n.g(view, "view");
                n.g(outline, "outline");
                if (DynamicIslandAnimationDelegate.this.view.getBackgroundView() != null) {
                    DynamicIslandBackgroundView backgroundView2 = DynamicIslandAnimationDelegate.this.view.getBackgroundView();
                    if (backgroundView2 != null) {
                        backgroundView2.setActualWidth((int) (DynamicIslandAnimationDelegate.this.containerX + DynamicIslandAnimationDelegate.this.containerClipEndProgress));
                    }
                    if (backgroundView2 != null) {
                        backgroundView2.setActualHeight((int) (DynamicIslandAnimationDelegate.this.containerTransY + DynamicIslandAnimationDelegate.this.containerClipBottomProgress));
                    }
                    if (backgroundView2 != null) {
                        backgroundView2.setActualLeft((int) (DynamicIslandAnimationDelegate.this.containerX + DynamicIslandAnimationDelegate.this.containerClipStartProgress));
                    }
                    if (backgroundView2 != null) {
                        backgroundView2.setActualTop((int) (DynamicIslandAnimationDelegate.this.containerTransY + DynamicIslandAnimationDelegate.this.containerClipTopProgress));
                    }
                    if (ThreadUtils.isMainThread()) {
                        ViewParent parent = view.getParent();
                        n.e(parent, "null cannot be cast to non-null type miui.systemui.dynamicisland.DynamicIslandBackgroundView");
                        ((DynamicIslandBackgroundView) parent).invalidate();
                    } else {
                        ViewParent parent2 = view.getParent();
                        n.e(parent2, "null cannot be cast to non-null type miui.systemui.dynamicisland.DynamicIslandBackgroundView");
                        ((DynamicIslandBackgroundView) parent2).postInvalidate();
                    }
                }
                outline.setRoundRect((int) DynamicIslandAnimationDelegate.this.containerClipStartProgress, (int) DynamicIslandAnimationDelegate.this.containerClipTopProgress, (int) DynamicIslandAnimationDelegate.this.containerClipEndProgress, (int) DynamicIslandAnimationDelegate.this.containerClipBottomProgress, DynamicIslandAnimationDelegate.this.containerClipRadius());
                RectF roundedRect = DynamicIslandAnimationDelegate.this.view.getRoundedRect();
                if (roundedRect != null) {
                    roundedRect.set(DynamicIslandAnimationDelegate.this.containerClipStartProgress, DynamicIslandAnimationDelegate.this.containerClipTopProgress, DynamicIslandAnimationDelegate.this.containerClipEndProgress, DynamicIslandAnimationDelegate.this.containerClipBottomProgress);
                }
            }
        });
        this.view.setClipToOutline(true);
    }

    public final void expandedChangedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getExpandedAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedChangedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(0);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(4);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(4);
                }
                View mask = view.getMask();
                if (mask != null) {
                    mask.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(true);
                DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(true, view.getExpandedViewY(), (int) (view.getIslandViewHeight() * (view.getExpandedViewHeight() / view.getIslandViewHeight())));
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
            }
        }));
        if (getIslandWindowAnimRunning()) {
            return;
        }
        fakeViewToExpanded(view, false);
    }

    public final void expandedScheduleUpdate() {
        DynamicIslandExpandedView expandedView;
        DynamicIslandExpandedView expandedView2 = this.view.getExpandedView();
        if (expandedView2 != null) {
            expandedView2.setScaleX(this.expandedScaleX);
        }
        DynamicIslandExpandedView expandedView3 = this.view.getExpandedView();
        if (expandedView3 != null) {
            expandedView3.setScaleY(this.expandedScaleY);
        }
        DynamicIslandExpandedView expandedView4 = this.view.getExpandedView();
        if (expandedView4 != null) {
            expandedView4.setAlpha(this.expandedAlpha);
        }
        DynamicIslandExpandedView expandedView5 = this.view.getExpandedView();
        if (expandedView5 != null) {
            expandedView5.setTranslationY(this.expandedTransY);
        }
        DynamicIslandExpandedView expandedView6 = this.view.getExpandedView();
        if (expandedView6 != null) {
            float f2 = this.containerClipStartProgress;
            float f3 = this.containerX;
            float f4 = f2 + f3;
            float f5 = this.containerClipTopProgress;
            float f6 = this.containerTransY;
            expandedView6.updateGlowEffectAnim$miui_dynamicisland_release(f4, f5 + f6, this.containerClipEndProgress + f3, this.containerClipBottomProgress + f6, containerClipRadius());
            DynamicIslandBackgroundView backgroundView = this.view.getBackgroundView();
            expandedView6.setZOrderOfGlowEffect$miui_dynamicisland_release(backgroundView != null ? backgroundView.getZ() : 0.0f);
            expandedView6.setAlphaOfGlowEffect$miui_dynamicisland_release(this.containerAlpha);
        }
        if (this.supportBlur && (expandedView = this.view.getExpandedView()) != null) {
            updateContentBlur(expandedView, this.expandedBlur);
        }
        this.view.updateExpandViewBlur((int) (this.containerClipBottomProgress + this.containerTransY));
    }

    public final void expandedToAppExpandedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        if (getIslandWindowAnimRunning()) {
            FolmeKt.getFolme(this).cancel();
            DynamicIslandExpandedView expandedView = view.getExpandedView();
            if (expandedView != null) {
                expandedView.stopGlowEffect$miui_dynamicisland_release(true);
            }
        } else {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator != null) {
                dynamicIslandEventCoordinator.updateView(view);
            }
            expandedToTempHiddenAnimation(view);
        }
        if (n.c(this.expandedStateHandler.getLastExpandedView(), view) && this.expandedStateHandler.getCurrent() == null) {
            updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
        }
        fakeViewToExpanded(view, true);
    }

    public final void expandedToBigIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).to(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_X, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(EXPANDED_BLUR, this.HIDDEN_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.SHOW_EASE, 50L, new float[0]).setSpecial(BIG_ISLAND_BLUR, this.SHOW_EASE, 50L, new float[0]).setSpecial(EXPANDED_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_TRANS_Y, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToBigIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandContentFakeView dynamicIslandContentFakeView = DynamicIslandAnimationDelegate.this.fakeView;
                if (dynamicIslandContentFakeView != null) {
                    dynamicIslandContentFakeView.setVisibility(4);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }
        }));
        fakeViewToBigIsland(view, false);
    }

    public final void expandedToBigIslandNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).setTo(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_X, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(EXPANDED_BLUR, this.HIDDEN_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.SHOW_EASE, 50L, new float[0]).setSpecial(BIG_ISLAND_BLUR, this.SHOW_EASE, 50L, new float[0]).setSpecial(EXPANDED_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_TRANS_Y, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToBigIslandNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandContentFakeView dynamicIslandContentFakeView = DynamicIslandAnimationDelegate.this.fakeView;
                if (dynamicIslandContentFakeView != null) {
                    dynamicIslandContentFakeView.setVisibility(4);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        fakeViewToBigIsland(view, false);
    }

    public final void expandedToDeletedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_TRANS_Y, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(EXPANDED_TRANS_Y, easeStyle, new float[0]).setSpecial(EXPANDED_SCALE_X, easeStyle, new float[0]).setSpecial(EXPANDED_SCALE_Y, easeStyle, new float[0]).setSpecial(EXPANDED_ALPHA, easeStyle, new float[0]).setSpecial(EXPANDED_BLUR, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToDeletedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_DELETED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_DELETED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_DELETED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        fakeViewToDeleted(view);
    }

    public final void expandedToDeletedNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? this.SHOW_EASE : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToDeletedNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_DELETED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_DELETED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_DELETED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        fakeViewToDeleted(view);
    }

    public final void expandedToHiddenAnimation(final DynamicIslandContentView view, final boolean z2) {
        n.g(view, "view");
        this.hiddenStateFrom = new DynamicIslandState.Expanded();
        FolmeKt.getFolme(this).to(getHiddenAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_ALPHA, this.HIDDEN_EASE, 150L, new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToHiddenAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                if (!z2) {
                    DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                    DynamicIslandContentFakeView fakeView = view.getFakeView();
                    if (fakeView != null) {
                        fakeView.setVisibility(4);
                    }
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                FrameLayout fakeSmallIsland = fakeView != null ? fakeView.getFakeSmallIsland() : null;
                if (fakeSmallIsland != null) {
                    fakeSmallIsland.setVisibility(4);
                }
                DynamicIslandContentFakeView fakeView2 = view.getFakeView();
                if (fakeView2 != null) {
                    fakeView2.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        fakeViewToHidden(view, z2);
    }

    public final void expandedToHiddenNoAnim(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToHiddenNoAnim.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
    }

    public final void expandedToMiniWindowAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToMiniWindowAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        scheduleUpdate();
        setViewVisible(view, 4);
    }

    public final void expandedToSmallIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_X, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(SMALL_ISLAND_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.SHOW_EASE, 50L, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToSmallIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                if (fakeView != null) {
                    fakeView.setVisibility(4);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_SMALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_SMALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_SMALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }
        }));
        fakeViewToSmallIsland(view, false);
    }

    public final void expandedToSmallIslandNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToSmallIslandNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_SMALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_SMALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_SMALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        fakeViewToSmallIsland(view, false);
    }

    public final void expandedToTempHiddenAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        if (DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            this.expandedToTempHiddenAnimating = true;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_TRANS_Y, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(EXPANDED_TRANS_Y, easeStyle, new float[0]).setSpecial(EXPANDED_SCALE_X, easeStyle, new float[0]).setSpecial(EXPANDED_SCALE_Y, easeStyle, new float[0]).setSpecial(EXPANDED_ALPHA, easeStyle, new float[0]).setSpecial(EXPANDED_BLUR, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToTempHiddenAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                BigIslandStateHandler bigIslandStateHandler;
                DynamicIslandWindowState windowState;
                DynamicIslandWindowState windowState2;
                u screenPinning;
                super.onBegin(obj);
                DynamicIslandWindowState windowState3 = view.getWindowState();
                if (windowState3 != null && !windowState3.getKeyguardShowing() && (windowState = view.getWindowState()) != null && !windowState.getNotificationVisible() && (windowState2 = view.getWindowState()) != null && (screenPinning = windowState2.getScreenPinning()) != null && !((Boolean) screenPinning.getValue()).booleanValue()) {
                    DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    CommonUtils commonUtils = CommonUtils.INSTANCE;
                    Context context = view.getContext();
                    n.f(context, "getContext(...)");
                    if (!commonUtils.getInVerticalMode(context)) {
                        DynamicIslandEventCoordinator eventCoordinator = DynamicIslandAnimationDelegate.this.windowView.getEventCoordinator();
                        if (((eventCoordinator == null || (bigIslandStateHandler = eventCoordinator.getBigIslandStateHandler()) == null) ? null : bigIslandStateHandler.getCurrentTempShow()) == null) {
                            DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, 0, 0);
                            return;
                        }
                    }
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                expandedView.setVisibility(4);
                DynamicIslandAnimationDelegate.this.setExpandedToTempHiddenAnimating(false);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void expandedToTempHiddenNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.expandedToTempHiddenNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                if (n.c(DynamicIslandAnimationDelegate.this.expandedStateHandler.getLastExpandedView(), view) && DynamicIslandAnimationDelegate.this.expandedStateHandler.getCurrent() == null) {
                    DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                    DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN;
                    DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                    n.e(obj, "null cannot be cast to non-null type kotlin.String");
                    animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                    CommonUtils commonUtils = CommonUtils.INSTANCE;
                    Context context = view.getContext();
                    n.f(context, "getContext(...)");
                    if (commonUtils.getInVerticalMode(context)) {
                        DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                    } else {
                        DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, 0, 0);
                    }
                }
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onCancel(obj);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                expandedView.setVisibility(4);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.EXPANDED_TO_TEMP_HIDDEN;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                super.onComplete(obj);
            }
        }));
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void fakeViewScheduleUpdate() {
        if (this.fakeViewAnimating) {
            updateFakeViewAnimState();
        }
    }

    public final void fakeViewToBigIsland(DynamicIslandContentView view, boolean z2) {
        DynamicIslandContentFakeView dynamicIslandContentFakeView;
        DynamicIslandContentFakeView dynamicIslandContentFakeView2;
        FrameLayout fakeExpandedView;
        n.g(view, "view");
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        boolean closingToExpanded = fakeView != null ? fakeView.getClosingToExpanded() : false;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "fakeViewToBigIsland: windowAnimRunning: " + getIslandWindowAnimRunning() + ", isAppClose: " + z2 + ", isClosingToExpanded: " + closingToExpanded);
        int currentBigIslandX$default = DynamicIslandBaseContentView.getCurrentBigIslandX$default(view, null, 1, null);
        int currentBigIslandWidth$default = DynamicIslandBaseContentView.getCurrentBigIslandWidth$default(view, null, 1, null);
        int islandViewMarginTop = view.getIslandViewMarginTop();
        int islandViewHeight = view.getIslandViewHeight();
        if ((view.getLastState() instanceof DynamicIslandState.SmallIsland) && getIslandWindowAnimRunning()) {
            if (z2) {
                updateFakeViewStateForAppAnim$default(this, currentBigIslandX$default, islandViewMarginTop, currentBigIslandWidth$default, islandViewHeight, view, false, false, 64, null);
            } else if (!this.islandAppAnimRunning) {
                if (view.getLastState() instanceof DynamicIslandState.SmallIsland) {
                    DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
                    FrameLayout fakeBigIsland = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeBigIsland() : null;
                    if (fakeBigIsland != null) {
                        float f2 = currentBigIslandX$default;
                        DynamicIslandContentFakeView dynamicIslandContentFakeView4 = this.fakeView;
                        fakeBigIsland.setTranslationX(-(f2 + Math.abs(dynamicIslandContentFakeView4 != null ? dynamicIslandContentFakeView4.getBigIslandTx() : 0.0f)));
                    }
                    DynamicIslandContentFakeView dynamicIslandContentFakeView5 = this.fakeView;
                    fakeExpandedView = dynamicIslandContentFakeView5 != null ? dynamicIslandContentFakeView5.getFakeBigIsland() : null;
                    if (fakeExpandedView != null) {
                        fakeExpandedView.setTranslationY(-islandViewMarginTop);
                    }
                }
                updateFakeViewState(0, 0, currentBigIslandX$default, currentBigIslandWidth$default, islandViewMarginTop, islandViewHeight, view);
            }
            animToFakeBigIslandView(view);
            return;
        }
        if (!(z2 && getIslandWindowAnimRunning()) && (!this.islandAppAnimRunning || (((dynamicIslandContentFakeView = this.fakeView) == null || !dynamicIslandContentFakeView.getBigIslandViewNeedAnim()) && ((dynamicIslandContentFakeView2 = this.fakeView) == null || !dynamicIslandContentFakeView2.getForceUpdateBigIslandView())))) {
            updateFakeViewState(0, 0, currentBigIslandX$default, currentBigIslandWidth$default, islandViewMarginTop, islandViewHeight, view);
        } else {
            updateFakeViewStateForAppAnim$default(this, currentBigIslandX$default, islandViewMarginTop, currentBigIslandWidth$default, islandViewHeight, view, true, false, 64, null);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView6 = this.fakeView;
        FrameLayout fakeBigIsland2 = dynamicIslandContentFakeView6 != null ? dynamicIslandContentFakeView6.getFakeBigIsland() : null;
        if (fakeBigIsland2 != null) {
            fakeBigIsland2.setVisibility(0);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView7 = this.fakeView;
        FrameLayout fakeSmallIsland = dynamicIslandContentFakeView7 != null ? dynamicIslandContentFakeView7.getFakeSmallIsland() : null;
        if (fakeSmallIsland != null) {
            fakeSmallIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView8 = this.fakeView;
        fakeExpandedView = dynamicIslandContentFakeView8 != null ? dynamicIslandContentFakeView8.getFakeExpandedView() : null;
        if (fakeExpandedView == null) {
            return;
        }
        fakeExpandedView.setVisibility(4);
    }

    public final void fakeViewToDeleted(DynamicIslandContentView view) {
        n.g(view, "view");
        Context context = view.getContext();
        updateFakeViewState(context != null ? DynamicIslandUtils.INSTANCE.getScreenWidthOld(context) / 2 : 0, 0, 0, 0, view.getIslandViewMarginTop(), 1, view);
        DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
        if (dynamicIslandContentFakeView != null) {
            dynamicIslandContentFakeView.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.fakeView;
        FrameLayout fakeBigIsland = dynamicIslandContentFakeView2 != null ? dynamicIslandContentFakeView2.getFakeBigIsland() : null;
        if (fakeBigIsland != null) {
            fakeBigIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
        FrameLayout fakeSmallIsland = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeSmallIsland() : null;
        if (fakeSmallIsland != null) {
            fakeSmallIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView4 = this.fakeView;
        FrameLayout fakeExpandedView = dynamicIslandContentFakeView4 != null ? dynamicIslandContentFakeView4.getFakeExpandedView() : null;
        if (fakeExpandedView == null) {
            return;
        }
        fakeExpandedView.setVisibility(4);
    }

    public final void fakeViewToExpanded(DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "fakeViewToExpanded: windowAnimRunning: " + getIslandWindowAnimRunning() + ", isAppOpen: " + z2);
        int expandedViewY = view.getExpandedViewY() - view.getIslandViewMarginTop();
        int expandedViewMarginHorizontal = view.getExpandedViewMarginHorizontal();
        int expandedViewWidth = view.getExpandedViewWidth();
        int islandViewMarginTop = view.getIslandViewMarginTop();
        int expandedViewHeight = view.getExpandedViewHeight();
        if (getIslandWindowAnimRunning() && !z2) {
            updateFakeViewStateForAppAnim(expandedViewMarginHorizontal, islandViewMarginTop, expandedViewWidth, expandedViewHeight, view, false, z2);
            animToFakeExpandedView(view);
            return;
        }
        if (this.islandAppAnimRunning) {
            updateFakeViewStateForAppAnim(expandedViewMarginHorizontal, islandViewMarginTop, expandedViewWidth, expandedViewHeight, view, true, z2);
        } else {
            updateFakeViewState(0, expandedViewY, expandedViewMarginHorizontal, expandedViewWidth, islandViewMarginTop, expandedViewHeight, view);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
        FrameLayout fakeBigIsland = dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeBigIsland() : null;
        if (fakeBigIsland != null) {
            fakeBigIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.fakeView;
        FrameLayout fakeSmallIsland = dynamicIslandContentFakeView2 != null ? dynamicIslandContentFakeView2.getFakeSmallIsland() : null;
        if (fakeSmallIsland != null) {
            fakeSmallIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
        FrameLayout fakeExpandedView = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeExpandedView() : null;
        if (fakeExpandedView == null) {
            return;
        }
        fakeExpandedView.setVisibility(0);
    }

    public final void fakeViewToHidden(DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        float screenWidthOld = ((view.getContext() != null ? DynamicIslandUtils.INSTANCE.getScreenWidthOld(r0) / 2 : 0) - (view.getSmallIslandViewWidth() / 2)) + (view.getSmallIslandViewWidth() * 0.2f);
        int smallIslandViewWidth = view.getSmallIslandViewWidth();
        float islandViewMarginTop = view.getIslandViewMarginTop() + (view.getIslandViewHeight() * 0.2f);
        float islandViewHeight = view.getIslandViewHeight() * 0.6f;
        if (z2 && getIslandWindowAnimRunning()) {
            updateFakeViewStateForAppAnim$default(this, (int) screenWidthOld, (int) islandViewMarginTop, smallIslandViewWidth, (int) islandViewHeight, view, true, false, 64, null);
        } else {
            updateFakeViewState(0, 0, (int) screenWidthOld, smallIslandViewWidth, (int) islandViewMarginTop, (int) islandViewHeight, view);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
        FrameLayout fakeSmallIsland = dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeSmallIsland() : null;
        if (fakeSmallIsland != null) {
            fakeSmallIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.fakeView;
        FrameLayout fakeBigIsland = dynamicIslandContentFakeView2 != null ? dynamicIslandContentFakeView2.getFakeBigIsland() : null;
        if (fakeBigIsland != null) {
            fakeBigIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
        FrameLayout fakeExpandedView = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeExpandedView() : null;
        if (fakeExpandedView == null) {
            return;
        }
        fakeExpandedView.setVisibility(4);
    }

    public final void fakeViewToNormalExpanded(DynamicIslandContentView view) {
        n.g(view, "view");
        updateFakeViewState(0, view.getExpandedViewY() - view.getIslandViewMarginTop(), view.getExpandedViewMarginHorizontal(), view.getExpandedViewWidth(), view.getIslandViewMarginTop(), view.getExpandedViewHeight(), view);
        this.expandedViewHadScaled = false;
        DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
        FrameLayout fakeBigIsland = dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeBigIsland() : null;
        if (fakeBigIsland != null) {
            fakeBigIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.fakeView;
        FrameLayout fakeSmallIsland = dynamicIslandContentFakeView2 != null ? dynamicIslandContentFakeView2.getFakeSmallIsland() : null;
        if (fakeSmallIsland != null) {
            fakeSmallIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
        FrameLayout fakeExpandedView = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeExpandedView() : null;
        if (fakeExpandedView == null) {
            return;
        }
        fakeExpandedView.setVisibility(0);
    }

    public final void fakeViewToSmallIsland(DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        boolean closingToExpanded = fakeView != null ? fakeView.getClosingToExpanded() : false;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "fakeViewToSmallIsland: windowAnimRunning: " + getIslandWindowAnimRunning() + ", isAppClose: " + z2 + ", isClosingToExpanded: " + closingToExpanded + ", fakeViewAnimating: " + this.fakeViewAnimating);
        Context context = view.getContext();
        int screenWidthOld = (context != null ? DynamicIslandUtils.INSTANCE.getScreenWidthOld(context) / 2 : 0) - (view.getSmallIslandViewWidth() / 2);
        int smallIslandViewWidth = view.getSmallIslandViewWidth();
        int islandViewMarginTop = view.getIslandViewMarginTop();
        int islandViewHeight = view.getIslandViewHeight();
        if (this.islandAppAnimRunning && (view.getLastState() instanceof DynamicIslandState.BigIsland)) {
            updateFakeViewStateForAppAnim$default(this, screenWidthOld, islandViewMarginTop, smallIslandViewWidth, islandViewHeight, view, false, false, 64, null);
            animToFakeSmallIslandView(view);
            return;
        }
        if (z2 && getIslandWindowAnimRunning()) {
            DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
            FrameLayout fakeSmallIsland = dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeSmallIsland() : null;
            if (fakeSmallIsland != null) {
                fakeSmallIsland.setTranslationX(0.0f);
            }
            updateFakeViewStateForAppAnim$default(this, screenWidthOld, islandViewMarginTop, smallIslandViewWidth, islandViewHeight, view, true, false, 64, null);
        } else {
            if (getIslandWindowAnimRunning()) {
                int smallIslandX = getSmallIslandX();
                int appAnimSmallX = getAppAnimSmallX();
                if (appAnimSmallX != 0) {
                    smallIslandX = appAnimSmallX;
                }
                DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.fakeView;
                FrameLayout fakeSmallIsland2 = dynamicIslandContentFakeView2 != null ? dynamicIslandContentFakeView2.getFakeSmallIsland() : null;
                if (fakeSmallIsland2 != null) {
                    fakeSmallIsland2.setTranslationX(smallIslandX);
                }
            }
            updateFakeViewState(0, 0, screenWidthOld, smallIslandViewWidth, islandViewMarginTop, islandViewHeight, view);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
        FrameLayout fakeBigIsland = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeBigIsland() : null;
        if (fakeBigIsland != null) {
            fakeBigIsland.setVisibility(4);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView4 = this.fakeView;
        FrameLayout fakeSmallIsland3 = dynamicIslandContentFakeView4 != null ? dynamicIslandContentFakeView4.getFakeSmallIsland() : null;
        if (fakeSmallIsland3 != null) {
            fakeSmallIsland3.setVisibility(0);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView5 = this.fakeView;
        FrameLayout fakeExpandedView = dynamicIslandContentFakeView5 != null ? dynamicIslandContentFakeView5.getFakeExpandedView() : null;
        if (fakeExpandedView == null) {
            return;
        }
        fakeExpandedView.setVisibility(4);
    }

    public final void fakeViewToTempHiddenAnimation(final DynamicIslandContentView view) {
        FrameLayout fakeContainer;
        IFolme folme;
        IStateStyle iStateStyleState;
        n.g(view, "view");
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null || (fakeContainer = fakeView.getFakeContainer()) == null || (folme = FolmeKt.getFolme(fakeContainer)) == null || (iStateStyleState = folme.state()) == null) {
            return;
        }
        AnimState animState = new AnimState();
        ViewProperty viewProperty = ViewProperty.ALPHA;
        iStateStyleState.to(animState.add(viewProperty, 0.0f, new long[0]), new AnimConfig().setSpecial(viewProperty, getSupposedContainerAlpha() == 0.0f ? this.SHOW_EASE : FolmeEase.linear(100L), getSupposedContainerAlpha() != 0.0f ? 400L : 100L, new float[0]).addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.fakeViewToTempHiddenAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandContentFakeView fakeView2 = view.getFakeView();
                if (fakeView2 != null) {
                    fakeView2.setVisibility(4);
                }
                DynamicIslandContentFakeView fakeView3 = view.getFakeView();
                FrameLayout fakeContainer2 = fakeView3 != null ? fakeView3.getFakeContainer() : null;
                if (fakeContainer2 == null) {
                    return;
                }
                fakeContainer2.setAlpha(1.0f);
            }
        }));
    }

    @Override // miuix.animation.FolmeObject
    public Folme.ObjectFolmeImpl folme() {
        return this.$$delegate_0.folme();
    }

    public final EaseManager.EaseStyle getALPHA_EASE() {
        return this.ALPHA_EASE;
    }

    public final EaseManager.EaseStyle getAPPEAR_EASE() {
        return this.APPEAR_EASE;
    }

    public final boolean getAppClose() {
        return this.appClose;
    }

    public final float getAreaLeftCollapseTransX() {
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        if (CommonUtils.isLayoutRtl(context)) {
            return -(this.view.getBigIslandAreaLeft() != null ? r2.getWidth() : 0.0f);
        }
        if (this.view.getBigIslandAreaLeft() != null) {
            return r2.getWidth();
        }
        return 0.0f;
    }

    public final float getAreaRightCollapseTransX() {
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        if (!CommonUtils.isLayoutRtl(context)) {
            return -(this.view.getBigIslandAreaRight() != null ? r2.getWidth() : 0.0f);
        }
        if (this.view.getBigIslandAreaRight() != null) {
            return r2.getWidth();
        }
        return 0.0f;
    }

    public final float getBigIslandHeight() {
        return this.view.getIslandViewHeight() + getBigIslandTop();
    }

    public final float getBigIslandLeft() {
        return DynamicIslandBaseContentView.getCurrentBigIslandX$default(this.view, null, 1, null);
    }

    public final float getBigIslandTop() {
        return this.view.getIslandViewMarginTop();
    }

    public final float getBigIslandWidth() {
        return ((DynamicIslandBaseContentView.getCurrentBigIslandWidth$default(this.view, null, 1, null) / (((float) this.view.getBigIslandViewWidth()) == 0.0f ? 1.0f : this.view.getBigIslandViewWidth())) * this.view.getBigIslandViewWidth()) + getBigIslandLeft();
    }

    public final float getBigTransYToExpanded() {
        return (this.view.getExpandedViewHeight() - this.view.getIslandViewHeight()) / 2.0f;
    }

    public final EaseManager.EaseStyle getCHANGE_EASE() {
        return this.CHANGE_EASE;
    }

    public final Choreographer getChoreographer() {
        return this.choreographer;
    }

    public final float getCutoutHeight() {
        return ((this.view.getCutoutHeight() / this.view.getIslandViewHeight()) * this.view.getIslandViewHeight()) + getCutoutTop();
    }

    public final float getCutoutLeft() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        n.f(this.view.getContext(), "getContext(...)");
        return (dynamicIslandUtils.getScreenWidthOld(r1) / 2.0f) - (this.view.getCutoutWidth() / 2);
    }

    public final float getCutoutTop() {
        return this.view.getCutoutY() - (this.view.getCutoutHeight() / 2);
    }

    public final float getCutoutWidth() {
        return ((this.view.getCutoutWidth() / (((float) this.view.getBigIslandViewWidth()) == 0.0f ? 1.0f : this.view.getBigIslandViewWidth())) * this.view.getBigIslandViewWidth()) + getCutoutLeft();
    }

    public final float getDebugIslandAnimScale() {
        return this.debugIslandAnimScale;
    }

    public final float getExpandedHeight() {
        return ((this.view.getExpandedViewHeight() / this.view.getIslandViewHeight()) * this.view.getIslandViewHeight()) + getExpandedTop();
    }

    public final float getExpandedLeft() {
        return this.view.getExpandedViewMarginHorizontal();
    }

    public final boolean getExpandedToTempHiddenAnimating() {
        return this.expandedToTempHiddenAnimating;
    }

    public final float getExpandedTop() {
        return this.view.getIslandViewMarginTop();
    }

    public final float getExpandedTransYToBig() {
        float bigIslandViewWidth = this.view.getBigIslandViewWidth() / this.view.getExpandedViewWidth();
        return ((this.view.getExpandedViewHeight() - (this.view.getExpandedViewHeight() * bigIslandViewWidth)) / 2) + (bigIslandViewWidth * this.view.getIslandViewMarginTop());
    }

    public final float getExpandedTransYToCutout() {
        return (((this.view.getExpandedViewHeight() - ((this.view.getCutoutWidth() / this.view.getExpandedViewWidth()) * this.view.getExpandedViewHeight())) / 2.0f) - getCutoutTop()) - (this.view.getIslandViewMarginTop() / 2.0f);
    }

    public final float getExpandedTransYToSmall() {
        return ((this.view.getExpandedViewHeight() - ((this.view.getSmallIslandViewWidth() / this.view.getExpandedViewWidth()) * this.view.getExpandedViewHeight())) / 2.0f) - (this.view.getIslandViewMarginTop() * 2.0f);
    }

    public final float getExpandedViewAnimatingProgress() {
        return this.expandedViewAnimatingProgress;
    }

    public final float getExpandedWidth() {
        return ((this.view.getExpandedViewWidth() / (((float) this.view.getBigIslandViewWidth()) == 0.0f ? 1.0f : this.view.getBigIslandViewWidth())) * this.view.getBigIslandViewWidth()) + getExpandedLeft();
    }

    public final EaseManager.EaseStyle getFAKE_ALPHA_EASE() {
        return this.FAKE_ALPHA_EASE;
    }

    public final EaseManager.EaseStyle getHIDDEN_EASE() {
        return this.HIDDEN_EASE;
    }

    public final DynamicIslandState getHiddenStateFrom() {
        return this.hiddenStateFrom;
    }

    public final boolean getIslandAppAnimRunning() {
        return this.islandAppAnimRunning;
    }

    public final boolean getIslandFreeformAnimRunning() {
        return this.islandFreeformAnimRunning;
    }

    public final boolean getIslandWindowAnimRunning() {
        return this.islandFreeformAnimRunning || this.islandAppAnimRunning;
    }

    public final EaseManager.EaseStyle getSCALE_EASE() {
        return this.SCALE_EASE;
    }

    public final EaseManager.EaseStyle getSHOW_EASE() {
        return this.SHOW_EASE;
    }

    public final float getSmallIslandHeight() {
        return this.view.getIslandViewHeight() + getSmallIslandTop();
    }

    public final float getSmallIslandLeft() {
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        n.f(this.view.getContext(), "getContext(...)");
        return (dynamicIslandUtils.getScreenWidthOld(r1) / 2.0f) - (this.view.getSmallIslandViewWidth() / 2);
    }

    public final float getSmallIslandTop() {
        return this.view.getIslandViewMarginTop();
    }

    public final float getSmallIslandWidth() {
        return ((this.view.getSmallIslandViewWidth() / (((float) this.view.getBigIslandViewWidth()) == 0.0f ? 1.0f : this.view.getBigIslandViewWidth())) * this.view.getBigIslandViewWidth()) + getSmallIslandLeft();
    }

    public final float getSmallTransXToBig() {
        float width = this.view.getBigIslandAreaLeft() != null ? r0.getWidth() : 0.0f;
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        if (CommonUtils.isLayoutRtl(context)) {
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            n.f(this.view.getContext(), "getContext(...)");
            return width - dynamicIslandUtils.dpToPx(8, r4);
        }
        DynamicIslandUtils dynamicIslandUtils2 = DynamicIslandUtils.INSTANCE;
        n.f(this.view.getContext(), "getContext(...)");
        return (-width) + dynamicIslandUtils2.dpToPx(8, r4);
    }

    public final float getSmallTransXToExpanded() {
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        return (CommonUtils.isLayoutRtl(context) ? this.view.getExpandedViewWidth() - this.view.getSmallIslandViewWidth() : -(this.view.getExpandedViewWidth() - this.view.getSmallIslandViewWidth())) / 2.0f;
    }

    public final float getSmallTransYToExpanded() {
        return (this.view.getExpandedViewHeight() - this.view.getIslandViewHeight()) / 2.0f;
    }

    public final boolean getUpdateScheduled() {
        return this.updateScheduled;
    }

    public final void hiddenChangedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        AnimConfig animConfigAddListeners = new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$hiddenChangedAnimation$animConfig$1
            {
                super(this);
            }
        });
        DynamicIslandState dynamicIslandState = this.hiddenStateFrom;
        if ((dynamicIslandState instanceof DynamicIslandState.Expanded) || (dynamicIslandState instanceof DynamicIslandState.SmallIsland)) {
            FolmeKt.getFolme(this).to(getHiddenAnimState(), animConfigAddListeners);
        } else {
            FolmeKt.getFolme(this).to(getCutoutAnimState(), animConfigAddListeners);
        }
    }

    public final void hiddenChangedNoAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        AnimConfig animConfigAddListeners = new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$hiddenChangedNoAnimation$animConfig$1
            {
                super(this);
            }
        });
        DynamicIslandState dynamicIslandState = this.hiddenStateFrom;
        if ((dynamicIslandState instanceof DynamicIslandState.Expanded) || (dynamicIslandState instanceof DynamicIslandState.SmallIsland)) {
            FolmeKt.getFolme(this).setTo(getHiddenAnimState(), animConfigAddListeners);
        } else {
            FolmeKt.getFolme(this).setTo(getCutoutAnimState(), animConfigAddListeners);
        }
    }

    public final void hiddenToAppExpandedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView != null) {
            fakeView.updateBigIslandLayout();
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateView(view);
        }
    }

    public final void hiddenToBigIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).setTo(getCutoutAnimState()).to(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(BIG_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.APPEAR_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.hiddenToBigIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
            }
        }));
        fakeViewToBigIsland(view, false);
    }

    public final void hiddenToDeletedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        removeViewFromWindow(view);
    }

    public final void hiddenToMiniWindowExpandedAnimation(DynamicIslandContentView view) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(view, "view");
        setViewVisible(view, 4);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView != null) {
            fakeView.updateBigIslandLayout();
        }
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        if (fakeView2 == null || (dynamicIslandEventCoordinator = fakeView2.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        DynamicIslandContentFakeView fakeView3 = view.getFakeView();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        dynamicIslandEventCoordinator.updateFreeformFakeView(fakeView3, view, currentIslandData != null ? currentIslandData.getExtras() : null);
    }

    public final void hiddenToSmallIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_X, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.APPEAR_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.hiddenToSmallIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandContentFakeView dynamicIslandContentFakeView = DynamicIslandAnimationDelegate.this.fakeView;
                if (dynamicIslandContentFakeView == null) {
                    return;
                }
                dynamicIslandContentFakeView.setVisibility(4);
            }
        }));
        fakeViewToSmallIsland(view, false);
    }

    public final void hiddenToTempHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.hiddenToTempHiddenAnimation.1
            {
                super(this);
            }
        }));
    }

    public final void hiddenToTempHiddenNoAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.hiddenToTempHiddenNoAnimation.1
            {
                super(this);
            }
        }));
    }

    public final void initToAppExpandedAnimation(DynamicIslandContentView view) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(view, "view");
        initToHiddenNoAnim(view);
        setViewVisible(view, 4);
        if (!(view.getState() instanceof DynamicIslandState.AppExpanded) || (dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        dynamicIslandEventCoordinator.updateView(view);
    }

    public final void initToBigIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).setTo(getCutoutAnimState()).to(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.APPEAR_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.initToBigIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                DynamicIslandData currentIslandData;
                Integer properties;
                ExpandedStateHandler expandedStateHandler;
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setAlpha(0.0f);
                }
                DynamicIslandBigIslandView bigIslandView2 = view.getBigIslandView();
                if (bigIslandView2 != null) {
                    bigIslandView2.setVisibility(0);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(4);
                }
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.INIT_TO_BIG;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                Context context = view.getContext();
                n.f(context, "getContext(...)");
                if (commonUtils.getInVerticalMode(context) || (currentIslandData = view.getCurrentIslandData()) == null || (properties = currentIslandData.getProperties()) == null || properties.intValue() != 0) {
                    return;
                }
                DynamicIslandEventCoordinator eventCoordinator = DynamicIslandAnimationDelegate.this.windowView.getEventCoordinator();
                if (((eventCoordinator == null || (expandedStateHandler = eventCoordinator.getExpandedStateHandler()) == null) ? null : expandedStateHandler.getCurrent()) == null) {
                    DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
                }
            }
        }));
        fakeViewToBigIsland(view, false);
    }

    public final void initToExpandedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState()).to(getExpandedAnimState(), new AnimConfig().setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.initToExpandedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(0);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(4);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(4);
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                View mask = view.getMask();
                if (mask != null) {
                    mask.setVisibility(4);
                }
                DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(true, view.getExpandedViewY(), (int) (view.getIslandViewHeight() * (view.getExpandedViewHeight() / view.getIslandViewHeight())));
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
            }
        }));
        fakeViewToExpanded(view, false);
    }

    public final void initToMiniWindowExpandedAnimation(DynamicIslandContentView view) {
        DynamicIslandContentFakeView fakeView;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(view, "view");
        initToHiddenNoAnim(view);
        setViewVisible(view, 4);
        if (!(view.getState() instanceof DynamicIslandState.MiniWindowExpanded) || (fakeView = view.getFakeView()) == null || (dynamicIslandEventCoordinator = fakeView.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        dynamicIslandEventCoordinator.updateFreeformFakeView(fakeView2, view, currentIslandData != null ? currentIslandData.getExtras() : null);
    }

    public final void initToSmallIslandAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState()).to(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(CONTAINER_X, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.initToSmallIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(4);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                View mask = view.getMask();
                if (mask == null) {
                    return;
                }
                mask.setVisibility(8);
            }
        }));
        fakeViewToSmallIsland(view, false);
    }

    public final void initToTempHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        view.setAlpha(0.0f);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView != null) {
            fakeView.setAlpha(0.0f);
        }
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.initToTempHiddenAnimation.1
            {
                super(this);
            }
        }));
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        if (fakeView2 == null) {
            return;
        }
        fakeView2.setVisibility(8);
    }

    public final boolean isAnimating() {
        return this.isAnimating;
    }

    public final boolean isAppClosing() {
        return this.isAppClosing;
    }

    public final void isLandDragShake(List<DynamicIslandContentView> list) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        ObjectAnimator objectAnimatorOfFloat3;
        DynamicIslandBackgroundView backgroundView;
        Log.i(this.TAG, "isLandDragShake: from.size " + (list != null ? Integer.valueOf(list.size()) : null));
        float dimension = this.view.getContext().getResources().getDimension(R.dimen.isLand_drag_shake_to_left);
        float dimension2 = this.view.getContext().getResources().getDimension(R.dimen.isLand_drag_shake_to_right);
        float dimension3 = this.view.getContext().getResources().getDimension(R.dimen.isLand_drag_shake_to_original_position);
        if (list != null) {
            for (DynamicIslandContentView dynamicIslandContentView : list) {
                Float fValueOf = (dynamicIslandContentView == null || (backgroundView = dynamicIslandContentView.getBackgroundView()) == null) ? null : Float.valueOf(backgroundView.getX());
                Float fValueOf2 = fValueOf != null ? Float.valueOf(fValueOf.floatValue() + dimension) : null;
                Float fValueOf3 = fValueOf != null ? Float.valueOf(fValueOf.floatValue() + dimension2) : null;
                Float fValueOf4 = fValueOf != null ? Float.valueOf(fValueOf.floatValue() + dimension3) : null;
                if (fValueOf2 != null) {
                    objectAnimatorOfFloat = ObjectAnimator.ofFloat(dynamicIslandContentView.getBackgroundView(), AnimatedProperty.PROPERTY_NAME_X, fValueOf2.floatValue());
                    objectAnimatorOfFloat.setDuration(100L);
                    objectAnimatorOfFloat.setInterpolator(new PathInterpolator(0.1f, 0.1f, 0.9f, 0.9f));
                } else {
                    objectAnimatorOfFloat = null;
                }
                if (fValueOf3 != null) {
                    objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(dynamicIslandContentView.getBackgroundView(), AnimatedProperty.PROPERTY_NAME_X, fValueOf3.floatValue());
                    objectAnimatorOfFloat2.setDuration(100L);
                    objectAnimatorOfFloat2.setInterpolator(new PathInterpolator(0.1f, 0.1f, 0.9f, 0.9f));
                } else {
                    objectAnimatorOfFloat2 = null;
                }
                if (fValueOf4 != null) {
                    objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(dynamicIslandContentView.getBackgroundView(), AnimatedProperty.PROPERTY_NAME_X, fValueOf4.floatValue());
                    objectAnimatorOfFloat3.setDuration(200L);
                    objectAnimatorOfFloat3.setInterpolator(new PathInterpolator(0.2f, 0.2f, 0.8f, 0.8f));
                } else {
                    objectAnimatorOfFloat3 = null;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(objectAnimatorOfFloat, objectAnimatorOfFloat2, objectAnimatorOfFloat3);
                animatorSet.start();
            }
        }
    }

    public final void miniWindowToAppExpanded(DynamicIslandContentView view) {
        n.g(view, "view");
        setViewVisible(view, 0);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView != null) {
            fakeView.setAlpha(1.0f);
        }
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateView(view);
        }
    }

    public final void miniWindowToBigIslandAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        appExpandedToBigIslandAnimation(view, false);
    }

    public final void miniWindowToSmallIslandAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        appExpandedToSmallIslandAnimation(view, false);
    }

    public final void miniWindowToTempHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.miniWindowToTempHiddenAnimation.1
            {
                super(this);
            }
        }));
        view.setAlpha(0.0f);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        FrameLayout fakeExpandedView = fakeView != null ? fakeView.getFakeExpandedView() : null;
        if (fakeExpandedView != null) {
            fakeExpandedView.setVisibility(4);
        }
        fakeViewToTempHiddenAnimation(view);
    }

    public final void miniWindowToTempHiddenNoAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        setViewVisible(view, 8);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void onBegin(Object obj) {
        DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
        DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
        DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
    }

    public final void onCancel(Object obj) {
        DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
        DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
        DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
    }

    public final void onComplete(Object obj) {
        DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
        DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
        DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
    }

    public final void onPress(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandAnimController.getDynamicIslandWindowState();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        if (dynamicIslandWindowState.isTempHidden(currentIslandData != null ? currentIslandData.getProperties() : null)) {
            return;
        }
        DynamicIslandData currentIslandData2 = view.getCurrentIslandData();
        Log.d(DynamicIslandConstants.TAG_DEBUG_SWIPE, "press: " + (currentIslandData2 != null ? currentIslandData2.getKey() : null));
        if (n.c(this.smallIslandStateHandler.getCurrent(), view)) {
            IFolme folme = FolmeKt.getFolme(this);
            AnimState animStateAdd = new AnimState().add((FloatProperty) CONTAINER_X, getSmallIslandX(), new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty = CONTAINER_CLIP_START_PROGRESS;
            AnimState animStateAdd2 = animStateAdd.add(floatProperty, getSmallIslandLeft() + (view.getSmallIslandViewWidth() * 0.05f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty2 = CONTAINER_CLIP_END_PROGRESS;
            AnimState animStateAdd3 = animStateAdd2.add(floatProperty2, getSmallIslandWidth() - (view.getSmallIslandViewWidth() * 0.05f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty3 = CONTAINER_CLIP_TOP_PROGRESS;
            AnimState animStateAdd4 = animStateAdd3.add(floatProperty3, getSmallIslandTop() + (view.getIslandViewHeight() * 0.05f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty4 = CONTAINER_CLIP_BOTTOM_PROGRESS;
            folme.to(animStateAdd4.add(floatProperty4, getSmallIslandHeight() - (view.getIslandViewHeight() * 0.05f), new long[0]).add((FloatProperty) SMALL_ISLAND_SCALE, 0.9f, new long[0]), new AnimConfig().setSpecial(floatProperty, this.SCALE_EASE, new float[0]).setSpecial(floatProperty2, this.SCALE_EASE, new float[0]).setSpecial(floatProperty3, this.SCALE_EASE, new float[0]).setSpecial(floatProperty4, this.SCALE_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.SCALE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onPress.1
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                }
            }));
            return;
        }
        if (n.c(this.bigIslandStateHandler.getCurrent(), view)) {
            IFolme folme2 = FolmeKt.getFolme(this);
            AnimState animStateAdd5 = new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty5 = CONTAINER_CLIP_START_PROGRESS;
            AnimState animStateAdd6 = animStateAdd5.add(floatProperty5, getBigIslandLeft() + (view.getBigIslandViewWidth() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty6 = CONTAINER_CLIP_END_PROGRESS;
            AnimState animStateAdd7 = animStateAdd6.add(floatProperty6, getBigIslandWidth() - (view.getBigIslandViewWidth() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty7 = CONTAINER_CLIP_TOP_PROGRESS;
            AnimState animStateAdd8 = animStateAdd7.add(floatProperty7, getBigIslandTop() + (view.getIslandViewHeight() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty8 = CONTAINER_CLIP_BOTTOM_PROGRESS;
            AnimState animStateAdd9 = animStateAdd8.add(floatProperty8, getBigIslandHeight() - (view.getIslandViewHeight() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty9 = BIG_ISLAND_SCALE;
            folme2.to(animStateAdd9.add((FloatProperty) floatProperty9, 0.95f, new long[0]), new AnimConfig().setSpecial(floatProperty5, this.SCALE_EASE, new float[0]).setSpecial(floatProperty6, this.SCALE_EASE, new float[0]).setSpecial(floatProperty7, this.SCALE_EASE, new float[0]).setSpecial(floatProperty8, this.SCALE_EASE, new float[0]).setSpecial(floatProperty9, this.SCALE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onPress.2
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                }
            }));
            return;
        }
        if (n.c(this.expandedStateHandler.getCurrent(), view)) {
            IFolme folme3 = FolmeKt.getFolme(this);
            AnimState animStateAdd10 = new AnimState().add((FloatProperty) CONTAINER_X, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, view.getExpandedViewY() - view.getIslandViewMarginTop(), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty10 = CONTAINER_CLIP_START_PROGRESS;
            AnimState animStateAdd11 = animStateAdd10.add(floatProperty10, getExpandedLeft() + (view.getExpandedViewWidth() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty11 = CONTAINER_CLIP_END_PROGRESS;
            AnimState animStateAdd12 = animStateAdd11.add(floatProperty11, getExpandedWidth() - (view.getExpandedViewWidth() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty12 = CONTAINER_CLIP_TOP_PROGRESS;
            AnimState animStateAdd13 = animStateAdd12.add(floatProperty12, getExpandedTop() + (view.getExpandedViewHeight() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty13 = CONTAINER_CLIP_BOTTOM_PROGRESS;
            AnimState animStateAdd14 = animStateAdd13.add(floatProperty13, getExpandedHeight() - (view.getExpandedViewHeight() * 0.025f), new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty14 = EXPANDED_SCALE_X;
            AnimState animStateAdd15 = animStateAdd14.add((FloatProperty) floatProperty14, 0.95f, new long[0]);
            FloatProperty<DynamicIslandAnimationDelegate> floatProperty15 = EXPANDED_SCALE_Y;
            folme3.to(animStateAdd15.add((FloatProperty) floatProperty15, 0.95f, new long[0]), new AnimConfig().setSpecial(floatProperty10, this.SCALE_EASE, new float[0]).setSpecial(floatProperty11, this.SCALE_EASE, new float[0]).setSpecial(floatProperty12, this.SCALE_EASE, new float[0]).setSpecial(floatProperty13, this.SCALE_EASE, new float[0]).setSpecial(floatProperty14, this.SCALE_EASE, new float[0]).setSpecial(floatProperty15, this.SCALE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onPress.3
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                }
            }));
        }
    }

    public final void onSwipe(DynamicIslandContentView view, DynamicIslandState dynamicIslandState, DynamicIslandState dynamicIslandState2, float f2, float f3) {
        n.g(view, "view");
        DynamicIslandState lastState = view.getLastState();
        String simpleName = lastState != null ? lastState.getClass().getSimpleName() : null;
        DynamicIslandState state = view.getState();
        Log.d(DynamicIslandConstants.TAG_DEBUG_SWIPE, "onSwipe:  " + simpleName + " -> " + (state != null ? state.getClass().getSimpleName() : null));
        DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandAnimController.getDynamicIslandWindowState();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        if (dynamicIslandWindowState.isTempHidden(currentIslandData != null ? currentIslandData.getProperties() : null)) {
            return;
        }
        if ((dynamicIslandState instanceof DynamicIslandState.Expanded) && (dynamicIslandState2 instanceof DynamicIslandState.BigIsland)) {
            onSwipeExpandedToBig(f2, f3);
        }
        if ((dynamicIslandState instanceof DynamicIslandState.Hidden) && (dynamicIslandState2 instanceof DynamicIslandState.BigIsland)) {
            onSwipeHiddenToBig(view, f2);
        }
        boolean z2 = dynamicIslandState instanceof DynamicIslandState.BigIsland;
        if (z2 && (dynamicIslandState2 instanceof DynamicIslandState.SmallIsland)) {
            onSwipeBigToSmallAnimation(view, f2);
        }
        if (z2 && (dynamicIslandState2 instanceof DynamicIslandState.Hidden)) {
            onSwipeBigToHiddenAnimation(view, f2);
        }
        boolean z3 = dynamicIslandState instanceof DynamicIslandState.SmallIsland;
        if (z3 && (dynamicIslandState2 instanceof DynamicIslandState.BigIsland)) {
            onSwipeSmallIslandToBigIslandAnimation(view, f2);
        }
        if (z3 && (dynamicIslandState2 instanceof DynamicIslandState.Hidden)) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
            if (dynamicIslandEventCoordinator == null || !dynamicIslandEventCoordinator.lastHiddenListItemIsNull()) {
                onSwipeSmallIslandToHiddenAnimation(view, f2);
            } else {
                onSwipeSmallToTempHidden(f2);
            }
        }
    }

    public final void onSwipeBigToHiddenAnimation(DynamicIslandContentView view, float f2) {
        n.g(view, "view");
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        float fAbs = Math.abs(ratio(dynamicIslandUtils.getScreenWidthOld(context) / 2, (int) f2));
        FolmeKt.getFolme(this).to(new AnimState().add((FloatProperty) CONTAINER_X, 0, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, ((double) getBigIslandLeft()) + (((double) (view.getBigIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_END_PROGRESS, ((double) getBigIslandWidth()) - (((double) (view.getBigIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_TOP_PROGRESS, getBigIslandTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getBigIslandHeight(), new long[0]).add(BIG_ISLAND_AREA_LEFT_TRANS_X, getSwipeLeftAreaTransX(fAbs), new long[0]).add(BIG_ISLAND_AREA_RIGHT_TRANS_X, getSwipeRightAreaTransX(fAbs), new long[0]).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add(BIG_ISLAND_ALPHA, calculateSwipeAlpha(fAbs), new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onSwipeBigToHiddenAnimation.1
            {
                super(this);
            }
        }));
    }

    public final void onSwipeHiddenToBig(DynamicIslandContentView view, float f2) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        float fAbs = Math.abs(ratio(dynamicIslandUtils.getScreenWidthOld(context) / 2, (int) f2));
        IFolme folme = FolmeKt.getFolme(this);
        AnimState animState = new AnimState();
        FloatProperty<DynamicIslandAnimationDelegate> floatProperty = CONTAINER_X;
        folme.setTo(animState.add((FloatProperty) floatProperty, 0.0f, new long[0])).to(new AnimState().add((FloatProperty) floatProperty, 0.0f, new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, ((double) getCutoutLeft()) - (((double) (view.getBigIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_END_PROGRESS, ((double) getCutoutWidth()) + (((double) (view.getBigIslandViewWidth() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_TOP_PROGRESS, ((double) getCutoutTop()) - (((double) (view.getIslandViewHeight() * fAbs)) * 0.5d)).add(CONTAINER_CLIP_BOTTOM_PROGRESS, ((double) getCutoutHeight()) + (((double) (view.getIslandViewHeight() * fAbs)) * 0.5d)).add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]).add((FloatProperty) BIG_ISLAND_ALPHA, 0.0f, new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onSwipeHiddenToBig.1
            {
                super(this);
            }
        }));
    }

    public final void onSwipeSmallIslandToBigIslandAnimation(DynamicIslandContentView view, float f2) {
        n.g(view, "view");
        if (view.getCurrentBigIslandViewWidth() == null) {
            return;
        }
        getSmallIslandX();
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        float fAbs = Math.abs(ratio(dynamicIslandUtils.getScreenWidthOld(context) / 2, (int) f2));
        double dIntValue = ((double) ((view.getCurrentBigIslandViewWidth() != null ? r9.intValue() : 0) * fAbs)) * 0.25d;
        FolmeKt.getFolme(this).to(new AnimState().add(CONTAINER_X, getSwipeSmallToBigX(fAbs), new long[0]).add((FloatProperty) CONTAINER_TRANS_Y, 0.0f, new long[0]).add(CONTAINER_CLIP_START_PROGRESS, ((double) getSmallIslandLeft()) - dIntValue).add(CONTAINER_CLIP_END_PROGRESS, ((double) getSmallIslandWidth()) + dIntValue).add(CONTAINER_CLIP_TOP_PROGRESS, getBigIslandTop(), new long[0]).add(CONTAINER_CLIP_BOTTOM_PROGRESS, getBigIslandHeight(), new long[0]).add((FloatProperty) SMALL_ISLAND_ALPHA, 0.8f, new long[0]), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.onSwipeSmallIslandToBigIslandAnimation.1
            {
                super(this);
            }
        }));
    }

    public final void removeViewFromWindow(DynamicIslandContentView view) {
        n.g(view, "view");
        try {
            int i2 = R.id.dynamic_island_removing;
            Object tag = view.getTag(i2);
            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
            Boolean bool2 = Boolean.TRUE;
            if (n.c(bool, bool2)) {
                return;
            }
            view.setTag(i2, bool2);
            Object parent = view.getParent();
            if (parent != null) {
                this.windowView.removeView((View) parent);
            }
            this.windowView.preRemoveDynamicIsland(view, view.getState());
            view.setTag(i2, null);
        } catch (Exception e2) {
            String str = this.TAG;
            e2.printStackTrace();
            Log.e(str, "removeViewFromWindow error + " + s.f314a);
        }
    }

    public final void resetContainerAlpha() {
        if (this.needResetContainerAlpha) {
            DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandAnimController.getDynamicIslandWindowState();
            DynamicIslandData currentIslandData = this.view.getCurrentIslandData();
            if (dynamicIslandWindowState.isTempHidden(currentIslandData != null ? currentIslandData.getProperties() : null)) {
                return;
            }
            FolmeKt.getFolme(this).setTo(new AnimState().add((FloatProperty) CONTAINER_ALPHA, 1.0f, new long[0]));
            this.view.setAlpha(1.0f);
            this.needResetContainerAlpha = false;
        }
    }

    public final void resetFakeViewAnimState() {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "resetFakeViewAnimState");
        if (this.fakeViewAnimating) {
            FolmeKt.getFolme(this).end(FAKE_SMALL_BLUR, FAKE_SMALL_ALPHA, FAKE_BIG_BLUR, FAKE_BIG_ALPHA, FAKE_EXPANDED_BLUR, FAKE_EXPANDED_ALPHA);
            this.fakeViewAnimating = false;
        }
        this.fakeSmallAlpha = 1.0f;
        this.fakeBigAlpha = 1.0f;
        this.fakeExpandedAlpha = 1.0f;
        this.fakeSmallBlur = 0.0f;
        this.fakeBigBlur = 0.0f;
        this.fakeExpandedBlur = 0.0f;
        updateFakeViewAnimState();
    }

    public final void resetPress(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandAnimController.getDynamicIslandWindowState();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        if (dynamicIslandWindowState.isTempHidden(currentIslandData != null ? currentIslandData.getProperties() : null)) {
            return;
        }
        DynamicIslandData currentIslandData2 = view.getCurrentIslandData();
        Log.d(DynamicIslandConstants.TAG_DEBUG_SWIPE, "resetPress: " + (currentIslandData2 != null ? currentIslandData2.getKey() : null));
        if (n.c(this.smallIslandStateHandler.getCurrent(), view)) {
            FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.SHOW_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetPress.1
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                }
            }));
        } else if (n.c(this.bigIslandStateHandler.getCurrent(), view)) {
            FolmeKt.getFolme(this).to(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.SHOW_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetPress.2
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                }
            }));
        } else if (n.c(this.expandedStateHandler.getCurrent(), view)) {
            FolmeKt.getFolme(this).to(getExpandedAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(EXPANDED_SCALE_X, this.SHOW_EASE, new float[0]).setSpecial(EXPANDED_SCALE_Y, this.SHOW_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.resetPress.3
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                }
            }));
        }
    }

    public final void resetSwipe(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandAnimController.getDynamicIslandWindowState();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        if (dynamicIslandWindowState.isTempHidden(currentIslandData != null ? currentIslandData.getProperties() : null)) {
            return;
        }
        if (view.getState() instanceof DynamicIslandState.BigIsland) {
            resetToBig(view);
        }
        if (view.getState() instanceof DynamicIslandState.SmallIsland) {
            resetToSmall(view);
        }
        if (view.getState() instanceof DynamicIslandState.Hidden) {
            resetToHidden(view);
        }
        if (view.getState() instanceof DynamicIslandState.Expanded) {
            resetToExpanded(view);
        }
    }

    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        Choreographer choreographer = this.choreographer;
        if (choreographer != null) {
            choreographer.postFrameCallback(this.frameCallback);
        }
    }

    public final void setAnimating(boolean z2) {
        if (this.isAnimating == z2) {
            return;
        }
        this.isAnimating = z2;
        DynamicIslandExpandedView expandedView = this.view.getExpandedView();
        if (expandedView != null) {
            expandedView.setTag(R.id.dynamic_island_animating_tag, Boolean.valueOf(z2));
        }
    }

    public final void setAppClose(boolean z2) {
        this.appClose = z2;
    }

    public final void setAppClosing(boolean z2) {
        this.isAppClosing = z2;
    }

    public final void setChoreographer(Choreographer choreographer) {
        this.choreographer = choreographer;
    }

    public final void setExpandedToTempHiddenAnimating(boolean z2) {
        this.expandedToTempHiddenAnimating = z2;
    }

    public final void setExpandedViewAnimatingProgress(float f2) {
        this.expandedViewAnimatingProgress = f2;
    }

    @Override // miuix.animation.FolmeObject
    public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
        this.$$delegate_0.setFolmeImpl(objectFolmeImpl);
    }

    public final void setHiddenStateFrom(DynamicIslandState dynamicIslandState) {
        this.hiddenStateFrom = dynamicIslandState;
    }

    public final void setIslandAppAnimRunning(boolean z2) {
        this.islandAppAnimRunning = z2;
    }

    public final void setIslandFreeformAnimRunning(boolean z2) {
        this.islandFreeformAnimRunning = z2;
    }

    public final void setUpdateScheduled(boolean z2) {
        this.updateScheduled = z2;
    }

    public final void smallIslandChangedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandChangedAnimation.1
            {
                super(this);
            }
        }));
        if (getIslandWindowAnimRunning()) {
            return;
        }
        fakeViewToSmallIsland(view, false);
    }

    public final void smallIslandChangedNoAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getSmallIslandAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandChangedNoAnimation.1
            {
                super(this);
            }
        }));
        if (getIslandWindowAnimRunning()) {
            return;
        }
        fakeViewToSmallIsland(view, false);
    }

    public final void smallIslandScaleAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        IStateStyle to = FolmeKt.getFolme(this).setTo(getSmallIslandAnimState());
        AnimState animState = new AnimState();
        FloatProperty<DynamicIslandAnimationDelegate> floatProperty = SMALL_ISLAND_SCALE;
        AnimState animStateAdd = animState.add((FloatProperty) floatProperty, 1.1f, new long[0]);
        FloatProperty<DynamicIslandAnimationDelegate> floatProperty2 = CONTAINER_CLIP_START_PROGRESS;
        AnimState animStateAdd2 = animStateAdd.add(floatProperty2, getSmallIslandLeft() - (view.getSmallIslandViewWidth() * 0.05f), new long[0]);
        FloatProperty<DynamicIslandAnimationDelegate> floatProperty3 = CONTAINER_CLIP_END_PROGRESS;
        AnimState animStateAdd3 = animStateAdd2.add(floatProperty3, getSmallIslandWidth() + (view.getSmallIslandViewWidth() * 0.05f), new long[0]);
        FloatProperty<DynamicIslandAnimationDelegate> floatProperty4 = CONTAINER_CLIP_TOP_PROGRESS;
        AnimState animStateAdd4 = animStateAdd3.add(floatProperty4, getSmallIslandTop() - (view.getIslandViewHeight() * 0.05f), new long[0]);
        FloatProperty<DynamicIslandAnimationDelegate> floatProperty5 = CONTAINER_CLIP_BOTTOM_PROGRESS;
        to.to(animStateAdd4.add(floatProperty5, getSmallIslandHeight() + (view.getIslandViewHeight() * 0.05f), new long[0]), new AnimConfig().setSpecial(floatProperty, FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(floatProperty2, FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(floatProperty3, FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(floatProperty4, FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(floatProperty5, FolmeEase.sinInOut(200L), 100L, new float[0]).addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandScaleAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_CANCEL;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                SmallIslandStateHandler smallIslandStateHandler;
                super.onComplete(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
                if (n.c((dynamicIslandEventCoordinator == null || (smallIslandStateHandler = dynamicIslandEventCoordinator.getSmallIslandStateHandler()) == null) ? null : smallIslandStateHandler.getCurrent(), view)) {
                    IFolme folme = FolmeKt.getFolme(DynamicIslandAnimationDelegate.this);
                    AnimState animState2 = new AnimState();
                    Companion companion = DynamicIslandAnimationDelegate.Companion;
                    AnimState animStateAdd5 = animState2.add((FloatProperty) companion.getSMALL_ISLAND_SCALE(), 1.0f, new long[0]).add(companion.getCONTAINER_CLIP_START_PROGRESS(), DynamicIslandAnimationDelegate.this.getSmallIslandLeft(), new long[0]).add(companion.getCONTAINER_CLIP_END_PROGRESS(), DynamicIslandAnimationDelegate.this.getSmallIslandWidth(), new long[0]).add(companion.getCONTAINER_CLIP_TOP_PROGRESS(), DynamicIslandAnimationDelegate.this.getSmallIslandTop(), new long[0]).add(companion.getCONTAINER_CLIP_BOTTOM_PROGRESS(), DynamicIslandAnimationDelegate.this.getSmallIslandHeight(), new long[0]);
                    AnimConfig special = new AnimConfig().setSpecial(companion.getSMALL_ISLAND_SCALE(), FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(companion.getCONTAINER_CLIP_START_PROGRESS(), FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(companion.getCONTAINER_CLIP_END_PROGRESS(), FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(companion.getCONTAINER_CLIP_TOP_PROGRESS(), FolmeEase.sinInOut(200L), 100L, new float[0]).setSpecial(companion.getCONTAINER_CLIP_BOTTOM_PROGRESS(), FolmeEase.sinInOut(200L), 100L, new float[0]);
                    final DynamicIslandAnimationDelegate dynamicIslandAnimationDelegate = DynamicIslandAnimationDelegate.this;
                    folme.to(animStateAdd5, special.addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate$smallIslandScaleAnimation$1$onComplete$1
                        @Override // miuix.animation.listener.TransitionListener
                        public void onComplete(Object obj2) {
                            super.onComplete(obj2);
                        }

                        @Override // miuix.animation.listener.TransitionListener
                        public void onUpdate(Object obj2, Collection<UpdateInfo> collection) {
                            super.onUpdate(obj2, collection);
                            dynamicIslandAnimationDelegate.scheduleUpdate();
                        }
                    }));
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                DynamicIslandAnimationDelegate.this.scheduleUpdate();
            }
        }));
    }

    public final void smallIslandScheduleUpdate() {
        FrameLayout smallIslandView;
        FrameLayout smallIslandView2 = this.view.getSmallIslandView();
        if (smallIslandView2 != null) {
            smallIslandView2.setScaleX(this.smallIslandScale);
        }
        FrameLayout smallIslandView3 = this.view.getSmallIslandView();
        if (smallIslandView3 != null) {
            smallIslandView3.setScaleY(this.smallIslandScale);
        }
        FrameLayout smallIslandView4 = this.view.getSmallIslandView();
        if (smallIslandView4 != null) {
            smallIslandView4.setAlpha(this.smallIslandAlpha);
        }
        FrameLayout smallIslandView5 = this.view.getSmallIslandView();
        if (smallIslandView5 != null) {
            smallIslandView5.setTranslationX(this.smallIslandTransX);
        }
        FrameLayout smallIslandView6 = this.view.getSmallIslandView();
        if (smallIslandView6 != null) {
            smallIslandView6.setTranslationY(this.smallIslandTransY);
        }
        if (!this.supportBlur || (smallIslandView = this.view.getSmallIslandView()) == null) {
            return;
        }
        updateContentBlur(smallIslandView, this.smallIslandBlur);
    }

    public final void smallIslandToAppExpandedAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        smallIslandToTempHiddenAnimation(view);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = view.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator != null) {
            dynamicIslandEventCoordinator.updateView(view);
        }
    }

    public final void smallIslandToBigIslandAnimation(final DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).to(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_TRANS_X, this.CHANGE_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToBigIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }
        }));
        fakeViewToBigIsland(view, z2);
    }

    public final void smallIslandToDeletedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_X, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(SMALL_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(SMALL_ISLAND_BLUR, this.HIDDEN_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToDeletedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
            }
        }));
        fakeViewToDeleted(view);
    }

    public final void smallIslandToDeletedNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToDeletedNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.removeViewFromWindow(view);
            }
        }));
        fakeViewToDeleted(view);
    }

    public final void smallIslandToExpandedAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).to(getExpandedAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_END_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_X, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.CHANGE_EASE, new float[0]).setSpecial(CONTAINER_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_X, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_SCALE_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(EXPANDED_ALPHA, this.SHOW_EASE, 50L, new float[0]).setSpecial(SMALL_ISLAND_TRANS_X, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_TRANS_Y, this.CHANGE_EASE, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.HIDDEN_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToExpandedAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(0);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(4);
                }
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.performAccessibilityAction(128, null);
                }
                DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(true, view.getExpandedViewY(), (int) (view.getIslandViewHeight() * (view.getExpandedViewHeight() / view.getIslandViewHeight())));
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_START;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(true);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, true);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                DynamicIslandAnimationCallback animationCallback$miui_dynamicisland_release = DynamicIslandAnimationDelegate.this.dynamicIslandAnimController.getAnimationCallback$miui_dynamicisland_release();
                DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.TO_EXPANDED;
                DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType = DynamicIslandAnimationCallbackType.ANIM_FINISH;
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                animationCallback$miui_dynamicisland_release.executeCallback(dynamicIslandAnimationType, dynamicIslandAnimationCallbackType, (String) obj);
                DynamicIslandAnimationDelegate.this.setAnimateExpanding(false);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.sendAccessibilityEvent(128);
                }
                DynamicIslandScenarioUtils.INSTANCE.setDynamicIslandScenarioState(357L, false);
            }
        }));
        fakeViewToExpanded(view, false);
    }

    public final void smallIslandToHiddenAnimation(DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        smallIslandToHiddenAnimation(view, z2, true);
    }

    public final void smallIslandToHiddenNoAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        smallIslandToHiddenNoAnimation(view, true);
    }

    public final void smallIslandToMiniWindowExpandedAnimation(DynamicIslandContentView view) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        n.g(view, "view");
        smallIslandToTempHiddenAnimation(view);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null || (dynamicIslandEventCoordinator = fakeView.getDynamicIslandEventCoordinator()) == null) {
            return;
        }
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        DynamicIslandData currentIslandData = view.getCurrentIslandData();
        dynamicIslandEventCoordinator.updateFreeformFakeView(fakeView2, view, currentIslandData != null ? currentIslandData.getExtras() : null);
    }

    public final void smallIslandToTempHiddenAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_X, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(SMALL_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(SMALL_ISLAND_BLUR, this.HIDDEN_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToTempHiddenAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                DynamicIslandWindowState windowState;
                u screenPinning;
                super.onBegin(obj);
                DynamicIslandWindowState windowState2 = view.getWindowState();
                if (windowState2 == null || windowState2.getKeyguardShowing() || (windowState = view.getWindowState()) == null || (screenPinning = windowState.getScreenPinning()) == null || ((Boolean) screenPinning.getValue()).booleanValue()) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
            }
        }));
        if (getIslandWindowAnimRunning()) {
            fakeViewToHidden(view, true);
            return;
        }
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(4);
    }

    public final void tempHiddenToBigIslandAnimation(final DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        if (z2) {
            tempHiddenToBigIslandNoAnimation(view);
            return;
        }
        setViewVisible(view, 0);
        view.updateBigIslandLayout();
        FolmeKt.getFolme(this).to(getBigIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(BIG_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.APPEAR_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.tempHiddenToBigIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                Context context = view.getContext();
                n.f(context, "getContext(...)");
                if (commonUtils.getInVerticalMode(context)) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.updateHeadsUpLocation(false, view.getIslandViewMarginTop(), view.getIslandViewHeight());
            }
        }));
    }

    public final void tempHiddenToSmallIslandAnimation(final DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        if (z2) {
            tempHiddenToSmallIslandNoAnimation(view);
        } else {
            setViewVisible(view, 0);
            FolmeKt.getFolme(this).to(getSmallIslandAnimState(), new AnimConfig().setSpecial(CONTAINER_X, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(SMALL_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.tempHiddenToSmallIslandAnimation.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this);
                }

                @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
                public void onBegin(Object obj) {
                    super.onBegin(obj);
                    FrameLayout smallIslandView = view.getSmallIslandView();
                    if (smallIslandView == null) {
                        return;
                    }
                    smallIslandView.setVisibility(0);
                }
            }));
        }
    }

    public final void updateContentBlur(View view, float f2) {
        n.g(view, "view");
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        if (MiBlurCompat.getBackgroundBlurOpened(context)) {
            DynamicIslandAnimationController.Companion.setMiSelfBlurCompat(view, (int) (40 * f2));
        } else {
            DynamicIslandAnimationController.Companion.setMiSelfBlurCompat(view, 0);
        }
    }

    public final void updateFakeViewAnimState() {
        FrameLayout fakeExpandedView;
        FrameLayout fakeBigIsland;
        FrameLayout fakeSmallIsland;
        DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
        FrameLayout fakeSmallIsland2 = dynamicIslandContentFakeView != null ? dynamicIslandContentFakeView.getFakeSmallIsland() : null;
        if (fakeSmallIsland2 != null) {
            fakeSmallIsland2.setAlpha(this.fakeSmallAlpha);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView2 = this.fakeView;
        FrameLayout fakeBigIsland2 = dynamicIslandContentFakeView2 != null ? dynamicIslandContentFakeView2.getFakeBigIsland() : null;
        if (fakeBigIsland2 != null) {
            fakeBigIsland2.setAlpha(this.fakeBigAlpha);
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView3 = this.fakeView;
        FrameLayout fakeExpandedView2 = dynamicIslandContentFakeView3 != null ? dynamicIslandContentFakeView3.getFakeExpandedView() : null;
        if (fakeExpandedView2 != null) {
            fakeExpandedView2.setAlpha(this.fakeExpandedAlpha);
        }
        if (this.supportBlur) {
            DynamicIslandContentFakeView dynamicIslandContentFakeView4 = this.fakeView;
            if (dynamicIslandContentFakeView4 != null && (fakeSmallIsland = dynamicIslandContentFakeView4.getFakeSmallIsland()) != null) {
                updateContentBlur(fakeSmallIsland, this.fakeSmallBlur);
            }
            DynamicIslandContentFakeView dynamicIslandContentFakeView5 = this.fakeView;
            if (dynamicIslandContentFakeView5 != null && (fakeBigIsland = dynamicIslandContentFakeView5.getFakeBigIsland()) != null) {
                updateContentBlur(fakeBigIsland, this.fakeBigBlur);
            }
            DynamicIslandContentFakeView dynamicIslandContentFakeView6 = this.fakeView;
            if (dynamicIslandContentFakeView6 != null && (fakeExpandedView = dynamicIslandContentFakeView6.getFakeExpandedView()) != null) {
                updateContentBlur(fakeExpandedView, this.fakeExpandedBlur);
            }
        }
        if (this.islandFreeformAnimRunning) {
            View miniBar = this.view.getMiniBar();
            if (miniBar != null) {
                float translationY = miniBar.getTranslationY();
                DynamicIslandContentFakeView dynamicIslandContentFakeView7 = this.fakeView;
                View miniBar2 = dynamicIslandContentFakeView7 != null ? dynamicIslandContentFakeView7.getMiniBar() : null;
                if (miniBar2 != null) {
                    DynamicIslandContentFakeView dynamicIslandContentFakeView8 = this.fakeView;
                    if (dynamicIslandContentFakeView8 == null || dynamicIslandContentFakeView8.getTop() != this.view.getTop()) {
                        translationY -= this.view.getIslandViewMarginTop();
                    }
                    miniBar2.setTranslationY(translationY);
                }
            }
            View miniBar3 = this.view.getMiniBar();
            if (miniBar3 != null) {
                float translationX = miniBar3.getTranslationX();
                DynamicIslandContentFakeView dynamicIslandContentFakeView9 = this.fakeView;
                View miniBar4 = dynamicIslandContentFakeView9 != null ? dynamicIslandContentFakeView9.getMiniBar() : null;
                if (miniBar4 == null) {
                    return;
                }
                DynamicIslandContentFakeView dynamicIslandContentFakeView10 = this.fakeView;
                if (dynamicIslandContentFakeView10 == null || dynamicIslandContentFakeView10.getLeft() != this.view.getLeft()) {
                    translationX -= this.view.getExpandedViewMarginHorizontal();
                }
                miniBar4.setTranslationX(translationX);
            }
        }
    }

    public final void updateFakeViewState(int i2, int i3, final int i4, final int i5, final int i6, final int i7, DynamicIslandContentView view) {
        n.g(view, "view");
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateFakeViewState: left:" + i4 + ", top:" + i6 + ", width:" + i5 + ", height:" + i7);
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView != null) {
            fakeView.setTranslationX(i2);
        }
        DynamicIslandContentFakeView fakeView2 = view.getFakeView();
        if (fakeView2 != null) {
            fakeView2.setTranslationY(i3);
        }
        DynamicIslandContentFakeView fakeView3 = view.getFakeView();
        if (fakeView3 != null) {
            fakeView3.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.updateFakeViewState.1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    RectF roundedRect;
                    n.g(view2, "view");
                    n.g(outline, "outline");
                    float dimension = view2.getContext().getResources().getDimension(R.dimen.island_radius);
                    int i8 = i4;
                    int i9 = i6;
                    outline.setRoundRect(i8, i9, i5 + i8, i7 + i9, dimension);
                    DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
                    if (dynamicIslandContentFakeView == null || (roundedRect = dynamicIslandContentFakeView.getRoundedRect()) == null) {
                        return;
                    }
                    roundedRect.set(i4, i6, i5 + r9, i7 + r1);
                }
            });
        }
        DynamicIslandContentFakeView fakeView4 = view.getFakeView();
        if (fakeView4 == null) {
            return;
        }
        fakeView4.setClipToOutline(true);
    }

    public final void updateFakeViewStateForAppAnim(int i2, int i3, int i4, int i5, DynamicIslandContentView view, boolean z2, boolean z3) {
        float screenWidthOld;
        FrameLayout fakeExpandedView;
        FrameLayout fakeBigIsland;
        IFolme folme;
        IStateStyle iStateStyleState;
        IFolme folme2;
        IStateStyle iStateStyleState2;
        n.g(view, "view");
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        Boolean boolValueOf = fakeView != null ? Boolean.valueOf(fakeView.getClosingToExpanded()) : null;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateFakeViewStateForAppAnim: left:" + i2 + ", top:" + i3 + ", width:" + i4 + ", height:" + i5 + ", isClosingToExpanded: " + boolValueOf + ", isAppOpen: " + z3 + ", needReset: " + z2 + ", expandedViewHadScaled: " + this.expandedViewHadScaled);
        if (z2) {
            resetFakeViewAnimState();
        }
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = view.getContext();
        n.f(context, "getContext(...)");
        float screenWidthOld2 = dynamicIslandUtils.getScreenWidthOld(context);
        float f2 = i4;
        if (f2 == 0.0f) {
            Context context2 = view.getContext();
            n.f(context2, "getContext(...)");
            screenWidthOld = dynamicIslandUtils.getScreenWidthOld(context2);
        } else {
            screenWidthOld = f2;
        }
        float f3 = screenWidthOld2 / screenWidthOld;
        float f4 = i5;
        float f5 = ((f4 / 2.0f) + i3) - ((f4 * f3) / 2.0f);
        float f6 = ((f2 / 2.0f) + i2) - ((f2 * f3) / 2.0f);
        if (view.getState() instanceof DynamicIslandState.BigIsland) {
            DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
            if (dynamicIslandContentFakeView == null || !dynamicIslandContentFakeView.getBigIslandViewNeedAnim()) {
                DynamicIslandContentFakeView fakeView2 = view.getFakeView();
                if (fakeView2 != null && (fakeBigIsland = fakeView2.getFakeBigIsland()) != null && (folme = FolmeKt.getFolme(fakeBigIsland)) != null && (iStateStyleState = folme.state()) != null) {
                    iStateStyleState.cancel();
                }
                DynamicIslandContentFakeView fakeView3 = view.getFakeView();
                FrameLayout fakeBigIsland2 = fakeView3 != null ? fakeView3.getFakeBigIsland() : null;
                if (fakeBigIsland2 != null) {
                    fakeBigIsland2.setScaleX(f3);
                }
                DynamicIslandContentFakeView fakeView4 = view.getFakeView();
                FrameLayout fakeBigIsland3 = fakeView4 != null ? fakeView4.getFakeBigIsland() : null;
                if (fakeBigIsland3 != null) {
                    fakeBigIsland3.setScaleY(f3);
                }
                DynamicIslandContentFakeView fakeView5 = view.getFakeView();
                FrameLayout fakeBigIsland4 = fakeView5 != null ? fakeView5.getFakeBigIsland() : null;
                if (fakeBigIsland4 != null) {
                    fakeBigIsland4.setTranslationY(-f5);
                }
                DynamicIslandContentFakeView fakeView6 = view.getFakeView();
                FrameLayout fakeBigIsland5 = fakeView6 != null ? fakeView6.getFakeBigIsland() : null;
                if (fakeBigIsland5 != null) {
                    DynamicIslandContentFakeView fakeView7 = view.getFakeView();
                    fakeBigIsland5.setTranslationX(fakeView7 != null ? fakeView7.getBigIslandTx() - f6 : 0.0f);
                }
            } else {
                FrameLayout fakeBigIsland6 = this.fakeView.getFakeBigIsland();
                if (fakeBigIsland6 != null && (folme2 = FolmeKt.getFolme(fakeBigIsland6)) != null && (iStateStyleState2 = folme2.state()) != null) {
                    iStateStyleState2.to(new AnimState().add(ViewProperty.SCALE_X, f3, new long[0]).add(ViewProperty.SCALE_Y, f3, new long[0]).add(ViewProperty.TRANSLATION_Y, -f5, new long[0]), new AnimConfig());
                }
            }
        } else if (view.getState() instanceof DynamicIslandState.SmallIsland) {
            DynamicIslandContentFakeView fakeView8 = view.getFakeView();
            FrameLayout fakeSmallIsland = fakeView8 != null ? fakeView8.getFakeSmallIsland() : null;
            if (fakeSmallIsland != null) {
                fakeSmallIsland.setScaleX(f3);
            }
            DynamicIslandContentFakeView fakeView9 = view.getFakeView();
            FrameLayout fakeSmallIsland2 = fakeView9 != null ? fakeView9.getFakeSmallIsland() : null;
            if (fakeSmallIsland2 != null) {
                fakeSmallIsland2.setScaleY(f3);
            }
            DynamicIslandContentFakeView fakeView10 = view.getFakeView();
            FrameLayout fakeSmallIsland3 = fakeView10 != null ? fakeView10.getFakeSmallIsland() : null;
            if (fakeSmallIsland3 != null) {
                fakeSmallIsland3.setTranslationY(Math.abs(f5));
            }
        } else if (!this.expandedViewHadScaled && ((view.getState() instanceof DynamicIslandState.Expanded) || (z3 && (view.getLastState() instanceof DynamicIslandState.Expanded)))) {
            DynamicIslandContentFakeView fakeView11 = view.getFakeView();
            FrameLayout fakeExpandedView2 = fakeView11 != null ? fakeView11.getFakeExpandedView() : null;
            if (fakeExpandedView2 != null) {
                fakeExpandedView2.setScaleX(f3);
            }
            DynamicIslandContentFakeView fakeView12 = view.getFakeView();
            FrameLayout fakeExpandedView3 = fakeView12 != null ? fakeView12.getFakeExpandedView() : null;
            if (fakeExpandedView3 != null) {
                fakeExpandedView3.setScaleY(f3);
            }
            if (z3 && (view.getLastState() instanceof DynamicIslandState.Expanded)) {
                this.expandedViewHadScaled = true;
                DynamicIslandContentFakeView fakeView13 = view.getFakeView();
                FrameLayout fakeExpandedView4 = fakeView13 != null ? fakeView13.getFakeExpandedView() : null;
                if (fakeExpandedView4 != null) {
                    fakeExpandedView4.setTranslationY(-f5);
                }
            } else {
                this.expandedViewHadScaled = true;
                int[] iArr = {0, 0};
                DynamicIslandContentFakeView fakeView14 = view.getFakeView();
                if (fakeView14 != null && (fakeExpandedView = fakeView14.getFakeExpandedView()) != null) {
                    fakeExpandedView.getLocationInSurface(iArr);
                }
                DynamicIslandContentFakeView fakeView15 = view.getFakeView();
                FrameLayout fakeExpandedView5 = fakeView15 != null ? fakeView15.getFakeExpandedView() : null;
                if (fakeExpandedView5 != null) {
                    fakeExpandedView5.setTranslationY(-iArr[1]);
                }
            }
            DynamicIslandContentFakeView fakeView16 = view.getFakeView();
            View miniBar = fakeView16 != null ? fakeView16.getMiniBar() : null;
            if (miniBar != null) {
                miniBar.setScaleX(f3);
            }
            DynamicIslandContentFakeView fakeView17 = view.getFakeView();
            View miniBar2 = fakeView17 != null ? fakeView17.getMiniBar() : null;
            if (miniBar2 != null) {
                miniBar2.setScaleY(f3);
            }
        }
        DynamicIslandContentFakeView fakeView18 = view.getFakeView();
        if (fakeView18 != null) {
            fakeView18.setTranslationX(0.0f);
        }
        DynamicIslandContentFakeView fakeView19 = view.getFakeView();
        if (fakeView19 != null) {
            fakeView19.setTranslationY(0.0f);
        }
        DynamicIslandContentFakeView fakeView20 = view.getFakeView();
        if (fakeView20 != null) {
            fakeView20.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.updateFakeViewStateForAppAnim.1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    n.g(view2, "view");
                    n.g(outline, "outline");
                    float dimension = view2.getContext().getResources().getDimension(R.dimen.island_radius);
                    DynamicIslandUtils dynamicIslandUtils2 = DynamicIslandUtils.INSTANCE;
                    Context context3 = view2.getContext();
                    n.f(context3, "getContext(...)");
                    int screenWidthOld3 = dynamicIslandUtils2.getScreenWidthOld(context3);
                    Context context4 = view2.getContext();
                    n.f(context4, "getContext(...)");
                    outline.setRoundRect(0, 0, screenWidthOld3, dynamicIslandUtils2.getScreenHeightOld(context4), dimension);
                }
            });
        }
        DynamicIslandContentFakeView fakeView21 = view.getFakeView();
        if (fakeView21 == null) {
            return;
        }
        fakeView21.setClipToOutline(true);
    }

    public final void updateHeadsUpLocation(boolean z2, int i2, int i3) {
        DynamicIslandContent.DynamicIslandViewChangedListener dynamicIslandViewChangedListener;
        int i4 = i2 + i3;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        Context context = this.view.getContext();
        n.f(context, "getContext(...)");
        int iDpToPx = i4 + dynamicIslandUtils.dpToPx(8, context);
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.ACTION_KEY, DynamicIslandConstants.ACTION_LOCATION_CHANGED_FOR_HEADS_UP);
        bundle.putBoolean(DynamicIslandConstants.EXTRA_IS_EXPAND, z2);
        bundle.putInt(DynamicIslandConstants.EXTRA_ISLAND_BOTTOM, iDpToPx);
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.view.getDynamicIslandEventCoordinator();
        if (dynamicIslandEventCoordinator == null || (dynamicIslandViewChangedListener = dynamicIslandEventCoordinator.getDynamicIslandViewChangedListener()) == null) {
            return;
        }
        dynamicIslandViewChangedListener.onIslandViewChanged(bundle);
    }

    public final void updateOrientationBigIslandToTempHiddenNoAnimation(final DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.SHOW_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.SHOW_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.updateOrientationBigIslandToTempHiddenNoAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandWindowState windowState = view.getWindowState();
                if (windowState == null || windowState.getKeyguardShowing()) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
            }
        }));
        DynamicIslandContentFakeView fakeView = view.getFakeView();
        if (fakeView == null) {
            return;
        }
        fakeView.setVisibility(8);
    }

    public final void updateOrientationSmallIslandToTempHiddenAnimation(DynamicIslandContentView view) {
        n.g(view, "view");
        FolmeKt.getFolme(this).setTo(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.updateOrientationSmallIslandToTempHiddenAnimation.1
            {
                super(this);
            }
        }));
    }

    private final void smallIslandToHiddenAnimation(DynamicIslandContentView dynamicIslandContentView, boolean z2, boolean z3) {
        if (this.bigIslandStateHandler.getCurrent() == null || this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation()) {
            smallIslandToTempHiddenAnimation(dynamicIslandContentView);
        } else {
            this.hiddenStateFrom = new DynamicIslandState.SmallIsland();
            FolmeKt.getFolme(this).to(getHiddenAnimState(), new AnimConfig().setSpecial(CONTAINER_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.HIDDEN_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToHiddenAnimation.1
                {
                    super(this);
                }
            }));
        }
        if (z3) {
            fakeViewToHidden(dynamicIslandContentView, z2);
        }
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public final void appExpandedToBigIslandAnimation(final DynamicIslandContentView view, final boolean z2) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        DynamicIslandContentFakeView dynamicIslandContentFakeView = this.fakeView;
        if (dynamicIslandContentFakeView != null) {
            dynamicIslandContentFakeView.suppressBigIslandChange(true);
        }
        AnimState bigIslandAnimState = getBigIslandAnimState();
        if (this.expandedToTempHiddenAnimating) {
            bigIslandAnimState.add((FloatProperty) CONTAINER_ALPHA, 0.0f, new long[0]);
            this.needResetContainerAlpha = true;
        }
        FolmeKt.getFolme(this).to(bigIslandAnimState, new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.APPEAR_EASE, new float[0]).setSpecial(CONTAINER_ALPHA, FolmeEase.linear(1L), new float[0]).setSpecial(BIG_ISLAND_SCALE, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.HIDDEN_EASE, 100L, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, this.APPEAR_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, this.APPEAR_EASE, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.appExpandedToBigIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                DynamicIslandAnimationDelegate.this.setAppClosing(true);
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                if (!DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning()) {
                    DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                    DynamicIslandContentFakeView fakeView = view.getFakeView();
                    if (fakeView == null) {
                        return;
                    }
                    fakeView.setVisibility(4);
                    return;
                }
                if (!DynamicIslandAnimationDelegate.this.getExpandedToTempHiddenAnimating()) {
                    DynamicIslandAnimationDelegate.this.setViewVisible(view, 4);
                }
                DynamicIslandContentFakeView dynamicIslandContentFakeView2 = DynamicIslandAnimationDelegate.this.fakeView;
                if (dynamicIslandContentFakeView2 == null) {
                    return;
                }
                dynamicIslandContentFakeView2.setVisibility(0);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationDelegate.this.setAppClosing(false);
                super.onCancel(obj);
                if (DynamicIslandAnimationDelegate.this.getExpandedToTempHiddenAnimating()) {
                    if (DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning()) {
                        DynamicIslandAnimationDelegate.this.setViewVisible(view, 4);
                    }
                    DynamicIslandAnimationDelegate.this.setExpandedToTempHiddenAnimating(false);
                }
                if (!DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning() || z2) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                if (fakeView == null) {
                    return;
                }
                fakeView.setVisibility(4);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandAnimationDelegate.this.setAppClosing(false);
                super.onComplete(obj);
                if (DynamicIslandAnimationDelegate.this.getExpandedToTempHiddenAnimating()) {
                    if (DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning()) {
                        DynamicIslandAnimationDelegate.this.setViewVisible(view, 4);
                    }
                    DynamicIslandAnimationDelegate.this.resetContainerAlpha();
                    DynamicIslandAnimationDelegate.this.setExpandedToTempHiddenAnimating(false);
                }
                if (!DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning() || z2) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
                if (bigIslandView != null) {
                    bigIslandView.setVisibility(0);
                }
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                if (fakeView == null) {
                    return;
                }
                fakeView.setVisibility(4);
            }
        }));
        fakeViewToBigIsland(view, z2);
    }

    public final void appExpandedToSmallIslandAnimation(final DynamicIslandContentView view, final boolean z2) {
        n.g(view, "view");
        view.updateBigIslandLayout();
        AnimState smallIslandAnimState = getSmallIslandAnimState();
        int appAnimSmallX = getAppAnimSmallX();
        if (appAnimSmallX != 0) {
            smallIslandAnimState.add((FloatProperty) CONTAINER_X, appAnimSmallX, new long[0]);
        }
        if (this.expandedToTempHiddenAnimating) {
            smallIslandAnimState.add((FloatProperty) CONTAINER_ALPHA, 0.0f, new long[0]);
            this.needResetContainerAlpha = true;
        }
        FolmeKt.getFolme(this).to(smallIslandAnimState, new AnimConfig().addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.appExpandedToSmallIslandAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                DynamicIslandAnimationDelegate.this.setAppClosing(true);
                super.onBegin(obj);
                DynamicIslandExpandedView expandedView = view.getExpandedView();
                if (expandedView != null) {
                    expandedView.setVisibility(4);
                }
                if (DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning()) {
                    if (!DynamicIslandAnimationDelegate.this.getExpandedToTempHiddenAnimating()) {
                        DynamicIslandAnimationDelegate.this.setViewVisible(view, 4);
                    }
                    DynamicIslandContentFakeView dynamicIslandContentFakeView = DynamicIslandAnimationDelegate.this.fakeView;
                    if (dynamicIslandContentFakeView == null) {
                        return;
                    }
                    dynamicIslandContentFakeView.setVisibility(0);
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                if (fakeView == null) {
                    return;
                }
                fakeView.setVisibility(4);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                DynamicIslandAnimationDelegate.this.setAppClosing(false);
                super.onCancel(obj);
                if (DynamicIslandAnimationDelegate.this.getExpandedToTempHiddenAnimating()) {
                    if (DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning()) {
                        DynamicIslandAnimationDelegate.this.setViewVisible(view, 4);
                    }
                    DynamicIslandAnimationDelegate.this.setExpandedToTempHiddenAnimating(false);
                }
                if (!DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning() || z2) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                if (fakeView == null) {
                    return;
                }
                fakeView.setVisibility(4);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                DynamicIslandAnimationDelegate.this.setAppClosing(false);
                super.onComplete(obj);
                if (DynamicIslandAnimationDelegate.this.getExpandedToTempHiddenAnimating()) {
                    if (DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning()) {
                        DynamicIslandAnimationDelegate.this.setViewVisible(view, 4);
                    }
                    DynamicIslandAnimationDelegate.this.resetContainerAlpha();
                    DynamicIslandAnimationDelegate.this.setExpandedToTempHiddenAnimating(false);
                }
                if (!DynamicIslandAnimationDelegate.this.getIslandWindowAnimRunning() || z2) {
                    return;
                }
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                FrameLayout smallIslandView = view.getSmallIslandView();
                if (smallIslandView != null) {
                    smallIslandView.setVisibility(0);
                }
                DynamicIslandContentFakeView fakeView = view.getFakeView();
                if (fakeView == null) {
                    return;
                }
                fakeView.setVisibility(4);
            }
        }));
        fakeViewToSmallIsland(view, z2);
    }

    public final void bigIslandToHiddenAnimation(final DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        EaseManager.EaseStyle easeStyle = this.SHOW_EASE;
        if (this.dynamicIslandAnimController.getDynamicIslandWindowState().isToScreenLockNoAnimation() || !DynamicIslandAnimUtils.INSTANCE.supportShowElementAndFreeformAnim()) {
            easeStyle = this.HIDDEN_EASE;
        }
        FolmeKt.getFolme(this).to(getCutoutAnimState(), new AnimConfig().setSpecial(CONTAINER_CLIP_START_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, easeStyle, new float[0]).setSpecial(CONTAINER_ALPHA, getSupposedContainerAlpha() == 0.0f ? easeStyle : FolmeEase.linear(100L), getSupposedContainerAlpha() == 0.0f ? 100L : 400L, new float[0]).setSpecial(BIG_ISLAND_SCALE, easeStyle, new float[0]).setSpecial(BIG_ISLAND_ALPHA, this.ALPHA_EASE, new float[0]).setSpecial(BIG_ISLAND_BLUR, this.ALPHA_EASE, new float[0]).setSpecial(BIG_ISLAND_AREA_LEFT_TRANS_X, easeStyle, new float[0]).setSpecial(BIG_ISLAND_AREA_RIGHT_TRANS_X, easeStyle, new float[0]).addListeners(new DynamicIslandAnimListener() { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.bigIslandToHiddenAnimation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(DynamicIslandAnimationDelegate.this);
            }

            @Override // miui.systemui.dynamicisland.anim.DynamicIslandAnimListener, miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                DynamicIslandAnimationDelegate.this.setViewVisible(view, 0);
                DynamicIslandContentFakeView dynamicIslandContentFakeView = DynamicIslandAnimationDelegate.this.fakeView;
                if (dynamicIslandContentFakeView == null) {
                    return;
                }
                dynamicIslandContentFakeView.setVisibility(4);
            }
        }));
        if (z2) {
            fakeViewToHidden(view, false);
        }
    }

    public final void smallIslandToHiddenNoAnimation(DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        this.hiddenStateFrom = new DynamicIslandState.SmallIsland();
        FolmeKt.getFolme(this).setTo(getHiddenAnimState(), new AnimConfig().setSpecial(CONTAINER_ALPHA, this.HIDDEN_EASE, new float[0]).setSpecial(SMALL_ISLAND_SCALE, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_START_PROGRESS, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_END_PROGRESS, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_TOP_PROGRESS, this.HIDDEN_EASE, new float[0]).setSpecial(CONTAINER_CLIP_BOTTOM_PROGRESS, this.HIDDEN_EASE, new float[0]).addListeners(new DynamicIslandAnimListener(this) { // from class: miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate.smallIslandToHiddenNoAnimation.1
            {
                super(this);
            }
        }));
        if (z2) {
            fakeViewToHidden(view, false);
        }
    }

    public final void resetFakeViewAnimState(DynamicIslandContentView view, boolean z2) {
        n.g(view, "view");
        resetFakeViewAnimState();
        if (z2) {
            fakeViewToNormalExpanded(view);
        }
    }
}
