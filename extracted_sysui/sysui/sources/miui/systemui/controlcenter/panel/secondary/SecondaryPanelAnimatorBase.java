package miui.systemui.controlcenter.panel.secondary;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.secondary.SecondaryParams;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.DeviceUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class SecondaryPanelAnimatorBase<P extends SecondaryParams> {
    public static final float ALPHA_CLIP_THRESHOLD_COLLAPSING = 0.9f;
    public static final float ALPHA_CLIP_THRESHOLD_EXPANDING = 0.3f;
    public static final int ANIM_COLLAPSED = 0;
    public static final int ANIM_COLLAPSING = 3;
    public static final int ANIM_EXPANDED = 2;
    public static final int ANIM_EXPANDING = 1;
    public static final Companion Companion = new Companion(null);
    private int animState;
    private final String animStateString;
    private float bgAlphaValue;
    private final Choreographer choreographer;
    private final Context context;
    private final Object deviceStateManager;
    private final Choreographer.FrameCallback frameCallback;
    private boolean isFoldStateChanged;
    private boolean isOrientationChanged;
    private int lastDeviceState;
    private final E0.a mainPanelController;
    private final String tag;
    private boolean updateScheduled;

    public static final class Companion {

        @Retention(RetentionPolicy.SOURCE)
        public @interface SecondaryAnimState {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class ViewLocValue {
        private final int height;
        private final int left;
        private final int locLeft;
        private final int locTop;
        private final int top;
        private final int width;

        public ViewLocValue(int i2, int i3, int i4, int i5, int i6, int i7) {
            this.locLeft = i2;
            this.locTop = i3;
            this.left = i4;
            this.top = i5;
            this.width = i6;
            this.height = i7;
        }

        public static /* synthetic */ ViewLocValue copy$default(ViewLocValue viewLocValue, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
            if ((i8 & 1) != 0) {
                i2 = viewLocValue.locLeft;
            }
            if ((i8 & 2) != 0) {
                i3 = viewLocValue.locTop;
            }
            int i9 = i3;
            if ((i8 & 4) != 0) {
                i4 = viewLocValue.left;
            }
            int i10 = i4;
            if ((i8 & 8) != 0) {
                i5 = viewLocValue.top;
            }
            int i11 = i5;
            if ((i8 & 16) != 0) {
                i6 = viewLocValue.width;
            }
            int i12 = i6;
            if ((i8 & 32) != 0) {
                i7 = viewLocValue.height;
            }
            return viewLocValue.copy(i2, i9, i10, i11, i12, i7);
        }

        public final int component1() {
            return this.locLeft;
        }

        public final int component2() {
            return this.locTop;
        }

        public final int component3() {
            return this.left;
        }

        public final int component4() {
            return this.top;
        }

        public final int component5() {
            return this.width;
        }

        public final int component6() {
            return this.height;
        }

        public final ViewLocValue copy(int i2, int i3, int i4, int i5, int i6, int i7) {
            return new ViewLocValue(i2, i3, i4, i5, i6, i7);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!n.c(ViewLocValue.class, obj != null ? obj.getClass() : null)) {
                return false;
            }
            n.e(obj, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase.ViewLocValue");
            ViewLocValue viewLocValue = (ViewLocValue) obj;
            return this.locLeft == viewLocValue.locLeft && this.locTop == viewLocValue.locTop && this.left == viewLocValue.left && this.top == viewLocValue.top && this.width == viewLocValue.width && this.height == viewLocValue.height;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getLocLeft() {
            return this.locLeft;
        }

        public final int getLocTop() {
            return this.locTop;
        }

        public final int getTop() {
            return this.top;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((((((((this.locLeft * 31) + this.locTop) * 31) + this.left) * 31) + this.top) * 31) + this.width) * 31) + this.height;
        }

        public String toString() {
            return "ViewLocValue(locLeft=" + this.locLeft + ", locTop=" + this.locTop + ", left=" + this.left + ", top=" + this.top + ", width=" + this.width + ", height=" + this.height + ")";
        }
    }

    public static final class ViewValue {
        public static final Companion Companion = new Companion(null);
        private final int height;
        private final int left;
        private final int top;
        private final int width;

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final ViewValue create(View v2) {
                n.g(v2, "v");
                return new ViewValue(v2.getLeft(), v2.getTop(), v2.getWidth(), v2.getHeight());
            }

            private Companion() {
            }
        }

        public ViewValue(int i2, int i3, int i4, int i5) {
            this.left = i2;
            this.top = i3;
            this.width = i4;
            this.height = i5;
        }

        public static /* synthetic */ ViewValue copy$default(ViewValue viewValue, int i2, int i3, int i4, int i5, int i6, Object obj) {
            if ((i6 & 1) != 0) {
                i2 = viewValue.left;
            }
            if ((i6 & 2) != 0) {
                i3 = viewValue.top;
            }
            if ((i6 & 4) != 0) {
                i4 = viewValue.width;
            }
            if ((i6 & 8) != 0) {
                i5 = viewValue.height;
            }
            return viewValue.copy(i2, i3, i4, i5);
        }

        public final int component1() {
            return this.left;
        }

        public final int component2() {
            return this.top;
        }

        public final int component3() {
            return this.width;
        }

        public final int component4() {
            return this.height;
        }

        public final ViewValue copy(int i2, int i3, int i4, int i5) {
            return new ViewValue(i2, i3, i4, i5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!n.c(ViewValue.class, obj != null ? obj.getClass() : null)) {
                return false;
            }
            n.e(obj, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase.ViewValue");
            ViewValue viewValue = (ViewValue) obj;
            return this.left == viewValue.left && this.top == viewValue.top && this.width == viewValue.width && this.height == viewValue.height;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getTop() {
            return this.top;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((((this.left * 31) + this.top) * 31) + this.width) * 31) + this.height;
        }

        public String toString() {
            return "ViewValue(left=" + this.left + ", top=" + this.top + ", width=" + this.width + ", height=" + this.height + ")";
        }
    }

    public SecondaryPanelAnimatorBase(Context context, E0.a mainPanelController) {
        n.g(context, "context");
        n.g(mainPanelController, "mainPanelController");
        this.context = context;
        this.mainPanelController = mainPanelController;
        String simpleName = getClass().getSimpleName();
        n.f(simpleName, "getSimpleName(...)");
        this.tag = simpleName;
        this.choreographer = Choreographer.getInstance();
        this.bgAlphaValue = 1.0f;
        this.deviceStateManager = DeviceStateManagerCompat.INSTANCE.getDeviceStateManagerInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.controlcenter.panel.secondary.e
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                SecondaryPanelAnimatorBase.frameCallback$lambda$0(this.f5472a, j2);
            }
        };
        this.animStateString = "";
    }

    public static /* synthetic */ void collapse$default(SecondaryPanelAnimatorBase secondaryPanelAnimatorBase, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: collapse");
        }
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        secondaryPanelAnimatorBase.collapse(z2);
    }

    public static /* synthetic */ void doFrame$default(SecondaryPanelAnimatorBase secondaryPanelAnimatorBase, long j2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: doFrame");
        }
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        secondaryPanelAnimatorBase.doFrame(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$0(SecondaryPanelAnimatorBase this$0, long j2) {
        n.g(this$0, "this$0");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this$0.frameCallback();
        Log.i(this$0.tag, "frameCallback " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + "ms " + this$0.getAnimStateString());
    }

    private static /* synthetic */ void getAnimState$annotations() {
    }

    private final ControlCenterExpandController getExpandController() {
        return ((MainPanelController) this.mainPanelController.get()).getExpandController();
    }

    private final SecondaryPanelRouter getSecondaryPanelRouter() {
        return (SecondaryPanelRouter) ((MainPanelController) this.mainPanelController.get()).getSecondaryPanelRouter().get();
    }

    public void collapse(boolean z2) {
        this.isOrientationChanged = this.isOrientationChanged != (this.context.getResources().getConfiguration().orientation == 1);
        this.isFoldStateChanged = DeviceStateManagerCompat.INSTANCE.getCurrentStateCompat(this.deviceStateManager) != this.lastDeviceState;
        boolean zIsExpanding = isExpanding();
        this.animState = 3;
        if (zIsExpanding) {
            getWindowController().updateClip(getSecondaryPanelRouter().getRoutingToMainPanel());
        }
        Log.i(this.tag, "collapse " + this.animState + " " + isMainPanelCollapsing() + " " + this.isOrientationChanged + " " + this.isFoldStateChanged + " " + zIsExpanding);
    }

    public final void doFrame(long j2) {
        this.frameCallback.doFrame(j2);
    }

    public final void doUpdateClipHeaderCheck(float f2) {
        if ((isExpanding() && f2 > 0.3f && this.bgAlphaValue < 0.3f) || (isCollapsing() && f2 < 0.9f && this.bgAlphaValue > 0.9f)) {
            Log.i(this.tag, "force update clip header: " + isCollapsing() + " " + isExpanding() + " " + f2);
            forceUpdateClipHeader();
        }
        this.bgAlphaValue = f2;
    }

    public void expand() {
        this.isOrientationChanged = this.context.getResources().getConfiguration().orientation == 1;
        int currentStateCompat = DeviceStateManagerCompat.INSTANCE.getCurrentStateCompat(this.deviceStateManager);
        this.lastDeviceState = currentStateCompat;
        this.animState = 1;
        Log.i(this.tag, "expand 1 " + this.isOrientationChanged + " " + currentStateCompat);
    }

    public void forceToHide() {
        collapse(false);
    }

    public void forceUpdateClipHeader() {
    }

    public void frameCallback() {
        this.updateScheduled = false;
    }

    public String getAnimStateString() {
        return this.animStateString;
    }

    public final boolean getCollapseWithNoAnim() {
        return getSecondaryPanelRouter().getInCollapsingState() && this.isOrientationChanged;
    }

    public final String getTag() {
        return this.tag;
    }

    public final ControlCenterWindowViewController getWindowController() {
        return (ControlCenterWindowViewController) ((MainPanelController) this.mainPanelController.get()).getWindowViewController().get();
    }

    public final boolean isAppearance() {
        return ((MainPanelController) this.mainPanelController.get()).getExpandController().getAppearance();
    }

    public final boolean isCollapsed() {
        return this.animState == 0;
    }

    public final boolean isCollapsing() {
        return this.animState == 3;
    }

    public final boolean isExpanded() {
        return this.animState == 2;
    }

    public final boolean isExpanding() {
        return this.animState == 1;
    }

    public final boolean isFoldStateChanged() {
        return this.isFoldStateChanged;
    }

    public final boolean isLowEndDevice() {
        return DeviceUtils.isLowEndDevice();
    }

    public final boolean isMainPanelCollapsing() {
        return getExpandController().isMainPanelCollapsing();
    }

    public final boolean isOrientationChanged() {
        return this.isOrientationChanged;
    }

    public void onAnimComplete() {
        int i2 = this.animState;
        if (i2 == 1) {
            this.animState = 2;
        } else if (i2 == 3) {
            this.animState = 0;
        }
        Log.i(this.tag, "onAnimComplete " + this.animState + " " + i2 + " " + getAnimStateString());
    }

    public void prepareCollapse() {
        this.bgAlphaValue = 1.0f;
    }

    public void prepareExpand(P p2) {
        this.bgAlphaValue = 0.0f;
    }

    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        this.choreographer.postFrameCallback(this.frameCallback);
    }

    public final void setFoldStateChanged(boolean z2) {
        this.isFoldStateChanged = z2;
    }

    public final void setOrientationChanged(boolean z2) {
        this.isOrientationChanged = z2;
    }
}
