package miui.systemui.controlcenter.panel.main.anim;

import android.util.ArraySet;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.collection.ArrayMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class SpreadRowsAnimator extends ControlCenterViewController<ControlCenterWindowViewImpl> implements LifecycleOwner, ShadeSwitchController.OnShadeSwitchChangedListener {
    public static final int MAX_ROWS = 10;
    private static final int SLIDE_ROW_TRANSLATE = 0;
    private static final int SLIDE_ROW_TRANSLATE_STEP = 200;
    private static final float SPREAD_RATIO = 0.06f;
    private static final String TAG = "SpreadRowsAnimator";
    private final Row[] animators;
    private final BrightnessSliderController brightnessSliderController;
    private final E0.a expandController;
    private final float[] expandValues;
    private final E0.a frameCallback;
    private final MainPanelHeaderController headerController;
    private final LifecycleRegistry lifecycleRegistry;
    private final ArrayMap<Integer, ArraySet<Listener>> listeners;
    private final LinearLayout mainPanelContainer;
    private final Lifecycle mirrorLifecycle;
    private final LifecycleEventObserver mirrorObserver;
    private boolean mirrorShowing;
    private float scaleFactor;
    private final ShadeSwitchController shadeSwitchController;
    private final Row shiftingAnimator;
    private float shiftingValue;
    private boolean showing;
    private final float[] slideTransXValues;
    private final E0.a windowViewController;
    public static final Companion Companion = new Companion(null);
    private static final SpreadRowsAnimator$Companion$EXPAND$1 EXPAND = new FloatProperty<Row>() { // from class: miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator$Companion$EXPAND$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(SpreadRowsAnimator.Row row) {
            n.g(row, "row");
            return row.getExpand();
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(SpreadRowsAnimator.Row row, float f2) {
            n.g(row, "row");
            row.setExpand(f2);
        }
    };
    private static final SpreadRowsAnimator$Companion$SLIDE_TRANS_X$1 SLIDE_TRANS_X = new FloatProperty<Row>() { // from class: miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator$Companion$SLIDE_TRANS_X$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(SpreadRowsAnimator.Row row) {
            n.g(row, "row");
            return row.getSlideTransX();
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(SpreadRowsAnimator.Row row, float f2) {
            n.g(row, "row");
            row.setSlideTransX(f2);
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float friction(float f2, float f3, float f4) {
            float fMax = Math.max(0.0f, f2 / f3);
            float f5 = f2 - f3;
            return fMax > 1.0f ? Folme.afterFrictionValue(f5, f4) * 0.4f : smix(fMax, f5, 0.0f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float multipleForRow(float f2, int i2) {
            return f2 < 0.0f ? f2 : f2 * (1 + (i2 * SpreadRowsAnimator.SPREAD_RATIO));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float smix(float f2, float f3, float f4) {
            return f3 + ((f4 - f3) * f2);
        }

        private Companion() {
        }
    }

    public interface Listener {
        void onExpandChange(float f2);

        default void onScaleChange(float f2, float f3) {
        }

        void onSlideTransXChange(float f2);
    }

    public final class Row {
        private final IStateStyle anim;
        private float expand;
        private boolean expandAnimating;
        private final AnimConfig expandConfig;
        private final SpreadRowsAnimator$Row$listener$1 listener;
        private final EaseManager.EaseStyle releaseEase;
        private final int row;
        private float slideTransX;
        private boolean slideTransXAnimating;
        private final AnimConfig slideTransXConfig;
        private final EaseManager.EaseStyle slideTransXInEase;
        private final EaseManager.EaseStyle spreadEase;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator$Row$listener$1] */
        public Row(int i2) {
            this.row = i2;
            ?? r02 = new TransitionListener() { // from class: miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator$Row$listener$1
                @Override // miuix.animation.listener.TransitionListener
                public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                    if (collection != null) {
                        SpreadRowsAnimator.Row row = this.this$0;
                        SpreadRowsAnimator spreadRowsAnimator = spreadRowsAnimator;
                        for (UpdateInfo updateInfo : collection) {
                            FloatProperty floatProperty = updateInfo.property;
                            if (n.c(floatProperty, SpreadRowsAnimator.EXPAND)) {
                                if (updateInfo.isCompleted) {
                                    row.setExpandAnimating(false);
                                    spreadRowsAnimator.adjustLifecycleState();
                                }
                            } else if (n.c(floatProperty, SpreadRowsAnimator.SLIDE_TRANS_X) && updateInfo.isCompleted) {
                                row.setSlideTransXAnimating(false);
                                spreadRowsAnimator.adjustLifecycleState();
                            }
                        }
                    }
                }
            };
            this.listener = r02;
            this.expandConfig = new AnimConfig().addListeners(r02);
            this.slideTransXConfig = new AnimConfig().addListeners(r02);
            EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.9f, 0.3f);
            n.d(style);
            this.spreadEase = style;
            EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 0.7f, 0.4f);
            n.d(style2);
            this.releaseEase = style2;
            Companion companion = SpreadRowsAnimator.Companion;
            float f2 = 10;
            EaseManager.EaseStyle style3 = EaseManager.getStyle(-2, companion.smix((i2 * 1.0f) / f2, 0.9f, 1.2f), companion.smix((i2 * 1.0f) / f2, 0.3f, 0.35f));
            n.d(style3);
            this.slideTransXInEase = style3;
            IStateStyle iStateStyleUseValue = Folme.useValue(this);
            n.f(iStateStyleUseValue, "useValue(...)");
            this.anim = iStateStyleUseValue;
        }

        public final IStateStyle getAnim() {
            return this.anim;
        }

        public final float getExpand() {
            return this.expand;
        }

        public final boolean getExpandAnimating() {
            return this.expandAnimating;
        }

        public final AnimConfig getExpandConfig() {
            return this.expandConfig;
        }

        public final EaseManager.EaseStyle getReleaseEase() {
            return this.releaseEase;
        }

        public final int getRow() {
            return this.row;
        }

        public final float getSlideTransX() {
            return this.slideTransX;
        }

        public final boolean getSlideTransXAnimating() {
            return this.slideTransXAnimating;
        }

        public final AnimConfig getSlideTransXConfig() {
            return this.slideTransXConfig;
        }

        public final EaseManager.EaseStyle getSlideTransXInEase() {
            return this.slideTransXInEase;
        }

        public final EaseManager.EaseStyle getSpreadEase() {
            return this.spreadEase;
        }

        public final void recycle() {
            Folme.clean(this);
        }

        public final void setExpand(float f2) {
            if (this.expand == f2) {
                return;
            }
            this.expand = f2;
            ((MainPanelFrameCallback) SpreadRowsAnimator.this.frameCallback.get()).scheduleUpdate();
        }

        public final void setExpandAnimating(boolean z2) {
            this.expandAnimating = z2;
        }

        public final void setSlideTransX(float f2) {
            if (this.slideTransX == f2) {
                return;
            }
            this.slideTransX = f2;
            ((MainPanelFrameCallback) SpreadRowsAnimator.this.frameCallback.get()).scheduleUpdate();
        }

        public final void setSlideTransXAnimating(boolean z2) {
            this.slideTransXAnimating = z2;
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpreadRowsAnimator(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, E0.a windowViewController, E0.a expandController, @Qualifiers.Mirror Lifecycle mirrorLifecycle, E0.a frameCallback, ShadeSwitchController shadeSwitchController, MainPanelHeaderController headerController, BrightnessSliderController brightnessSliderController, @Qualifiers.MainPanelContainer LinearLayout mainPanelContainer) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(windowViewController, "windowViewController");
        n.g(expandController, "expandController");
        n.g(mirrorLifecycle, "mirrorLifecycle");
        n.g(frameCallback, "frameCallback");
        n.g(shadeSwitchController, "shadeSwitchController");
        n.g(headerController, "headerController");
        n.g(brightnessSliderController, "brightnessSliderController");
        n.g(mainPanelContainer, "mainPanelContainer");
        this.windowViewController = windowViewController;
        this.expandController = expandController;
        this.mirrorLifecycle = mirrorLifecycle;
        this.frameCallback = frameCallback;
        this.shadeSwitchController = shadeSwitchController;
        this.headerController = headerController;
        this.brightnessSliderController = brightnessSliderController;
        this.mainPanelContainer = mainPanelContainer;
        this.lifecycleRegistry = new LifecycleRegistry(this);
        Row[] rowArr = new Row[10];
        for (int i2 = 0; i2 < 10; i2++) {
            rowArr[i2] = new Row(i2);
        }
        this.animators = rowArr;
        this.shiftingAnimator = new Row(0);
        this.shiftingValue = Float.MIN_VALUE;
        this.expandValues = new float[10];
        this.slideTransXValues = new float[10];
        ArrayMap<Integer, ArraySet<Listener>> arrayMap = new ArrayMap<>(10);
        for (int i3 = 0; i3 < 10; i3++) {
            arrayMap.put(Integer.valueOf(i3), new ArraySet<>());
        }
        this.listeners = arrayMap;
        this.mirrorObserver = new LifecycleEventObserver() { // from class: miui.systemui.controlcenter.panel.main.anim.b
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                SpreadRowsAnimator.mirrorObserver$lambda$1(this.f5354a, lifecycleOwner, event);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustLifecycleState() {
        for (Row row : this.animators) {
        }
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    public static /* synthetic */ void changeExpand$default(SpreadRowsAnimator spreadRowsAnimator, float f2, float f3, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f3 = f2;
        }
        spreadRowsAnimator.changeExpand(f2, f3, z2, z3);
    }

    private final void changeExpandWithCompleteEffect(float f2, float f3, boolean z2, boolean z3) {
        Companion companion = Companion;
        float fFriction = companion.friction(f2, getThresh(), getExpandFriction());
        int i2 = 0;
        if (z3) {
            this.shiftingAnimator.setExpandAnimating(true);
            for (Row row : this.animators) {
                row.setExpandAnimating(true);
            }
            Row row2 = this.shiftingAnimator;
            row2.getAnim().to(EXPAND, Float.valueOf(Companion.friction(f3, getThresh(), getExpandFriction())), row2.getExpandConfig().setEase(z2 ? row2.getReleaseEase() : row2.getSpreadEase()));
            Row[] rowArr = this.animators;
            int length = rowArr.length;
            int i3 = 0;
            while (i2 < length) {
                Row row3 = rowArr[i2];
                int i4 = i3 + 1;
                row3.getAnim().to(EXPAND, Float.valueOf(Companion.multipleForRow(fFriction, i3)), row3.getExpandConfig().setEase(z2 ? row3.getReleaseEase() : row3.getSpreadEase()));
                i2++;
                i3 = i4;
            }
            ((ControlCenterExpandController) this.expandController.get()).changePanelExpand(true, z2, f2, getThresh(), getExpandFriction());
        } else {
            Row row4 = this.shiftingAnimator;
            row4.setExpandAnimating(false);
            row4.getAnim().cancel();
            row4.getAnim().setTo(EXPAND, Float.valueOf(companion.friction(f3, getThresh(), getExpandFriction())), row4.getExpandConfig());
            Row[] rowArr2 = this.animators;
            int length2 = rowArr2.length;
            int i5 = 0;
            int i6 = 0;
            while (i5 < length2) {
                Row row5 = rowArr2[i5];
                row5.setExpandAnimating(false);
                row5.getAnim().cancel();
                row5.getAnim().setTo(EXPAND, Float.valueOf(Companion.multipleForRow(fFriction, i6)), row5.getExpandConfig());
                i5++;
                i6++;
            }
            ((ControlCenterExpandController) this.expandController.get()).changePanelExpand(false, z2, f2, getThresh(), getExpandFriction());
            onFrameCallback(true);
        }
        adjustLifecycleState();
    }

    public static /* synthetic */ void changeExpandWithCompleteEffect$default(SpreadRowsAnimator spreadRowsAnimator, float f2, float f3, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f3 = f2;
        }
        spreadRowsAnimator.changeExpandWithCompleteEffect(f2, f3, z2, z3);
    }

    public static /* synthetic */ void changeVisible$default(SpreadRowsAnimator spreadRowsAnimator, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z4 = false;
        }
        spreadRowsAnimator.changeVisible(z2, z3, z4);
    }

    private final float getExpandFriction() {
        return ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenHeight();
    }

    private final float getThresh() {
        return ((ControlCenterExpandController) this.expandController.get()).getExpandThresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void mirrorObserver$lambda$1(SpreadRowsAnimator this$0, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        n.g(this$0, "this$0");
        n.g(lifecycleOwner, "<anonymous parameter 0>");
        n.g(event, "event");
        int i2 = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i2 == 1) {
            this$0.changeVisible(false, true, true);
            this$0.headerController.onBrightnessMirrorChanged(true, true);
            this$0.brightnessSliderController.updateShadow(true);
        } else if (i2 == 2 && ((ControlCenterWindowViewImpl) this$0.getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            this$0.changeVisible(true, true, true);
            this$0.headerController.onBrightnessMirrorChanged(false, true);
            this$0.brightnessSliderController.updateShadow(false);
        }
    }

    public static /* synthetic */ void onFrameCallback$default(SpreadRowsAnimator spreadRowsAnimator, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        spreadRowsAnimator.onFrameCallback(z2);
    }

    private final void updateScaleFactor() {
        this.scaleFactor = getResources().getDimension(R.dimen.scale_factor);
    }

    public final void changeExpand(float f2, float f3, boolean z2, boolean z3) {
        if (MainPanelController.Companion.getLowEndAnim()) {
            ((ControlCenterExpandController) this.expandController.get()).changePanelExpand(z3, z2, f2, getThresh(), getExpandFriction());
        } else {
            changeExpandWithCompleteEffect(f2, f3, z2, z3);
        }
    }

    public final void changeVisible(boolean z2, boolean z3, boolean z4) {
        Log.d(TAG, "changeVisible " + z2 + " " + z3 + " " + z4);
        this.showing = z2;
        this.mirrorShowing = z4;
        ((ControlCenterExpandController) this.expandController.get()).changeItemVisible(z2, z3, z4);
        adjustLifecycleState();
    }

    public final float[] getExpandValues() {
        return this.expandValues;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final boolean getMirrorShowing() {
        return this.mirrorShowing;
    }

    public final float getScaleFactor() {
        return this.scaleFactor;
    }

    public final float getScreenHeight() {
        return ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenHeight();
    }

    public final float getScreenWidth() {
        return ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenWidth();
    }

    public final float[] getSlideTransXValues() {
        return this.slideTransXValues;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.dimensionsChanged(i2)) {
            updateScaleFactor();
        }
    }

    public void onControlCenterAppearChanged(boolean z2, boolean z3) {
        slideControlCenter(z2, 1, z3);
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        updateScaleFactor();
        this.mirrorLifecycle.addObserver(this.mirrorObserver);
        this.shadeSwitchController.addOnShadeSwitchChangedListener(this);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        Log.d(TAG, "on destroy " + this);
        this.mirrorLifecycle.removeObserver(this.mirrorObserver);
        for (Row row : this.animators) {
            row.recycle();
        }
        this.shadeSwitchController.removeOnShadeSwitchChangedListener(this);
    }

    public final void onFrameCallback(boolean z2) {
        if (z2 || this.shiftingValue != this.shiftingAnimator.getExpand()) {
            this.shiftingValue = this.shiftingAnimator.getExpand();
            ArraySet<Listener> arraySet = this.listeners.get(0);
            if (arraySet != null) {
                Iterator<T> it = arraySet.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).onExpandChange(this.shiftingValue);
                }
            }
        }
        for (int i2 = 0; i2 < 10; i2++) {
            ArraySet<Listener> arraySet2 = this.listeners.get(Integer.valueOf(i2));
            if (this.expandValues[i2] != this.animators[i2].getExpand()) {
                this.expandValues[i2] = this.animators[i2].getExpand();
                if (arraySet2 != null) {
                    Iterator<T> it2 = arraySet2.iterator();
                    while (it2.hasNext()) {
                        ((Listener) it2.next()).onExpandChange(this.expandValues[i2]);
                    }
                }
            }
            if (this.slideTransXValues[i2] != this.animators[i2].getSlideTransX()) {
                this.slideTransXValues[i2] = this.animators[i2].getSlideTransX();
                if (arraySet2 != null) {
                    Iterator<T> it3 = arraySet2.iterator();
                    while (it3.hasNext()) {
                        ((Listener) it3.next()).onSlideTransXChange(this.slideTransXValues[i2]);
                    }
                }
            }
        }
        ((ControlCenterWindowViewController) this.windowViewController.get()).setExpansion(this.expandValues[0]);
        if (MainPanelController.Companion.getLowEndAnim()) {
            return;
        }
        ((ControlCenterExpandController) this.expandController.get()).notifyStretchHeightChanged(this.expandValues[0]);
    }

    public void onNotificationAppearChanged(boolean z2, boolean z3) {
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        super.onStart();
        if (this.mainPanelContainer.getVisibility() == 0) {
            return;
        }
        this.mainPanelContainer.setVisibility(0);
        Log.i(TAG, "mainPanelContainer force visible");
    }

    public final void slideControlCenter(boolean z2, int i2, boolean z3) {
        Float fValueOf = Float.valueOf(0.0f);
        if (z3) {
            for (Row row : this.animators) {
                row.setSlideTransXAnimating(true);
            }
            for (Row row2 : this.animators) {
                int row3 = row2.getRow() * 200 * i2 * (CommonUtils.isLayoutRtl(getContext()) ? -1 : 1);
                if (z2) {
                    IStateStyle anim = row2.getAnim();
                    SpreadRowsAnimator$Companion$SLIDE_TRANS_X$1 spreadRowsAnimator$Companion$SLIDE_TRANS_X$1 = SLIDE_TRANS_X;
                    anim.setTo(spreadRowsAnimator$Companion$SLIDE_TRANS_X$1, Integer.valueOf(row3)).to(spreadRowsAnimator$Companion$SLIDE_TRANS_X$1, fValueOf, row2.getSlideTransXConfig().setEase(row2.getSlideTransXInEase()));
                } else {
                    row2.getAnim().setTo(SLIDE_TRANS_X, fValueOf);
                }
            }
        } else {
            for (Row row4 : this.animators) {
                row4.setSlideTransXAnimating(false);
                row4.getAnim().cancel();
                int row5 = row4.getRow() * 200 * i2;
                if (z2) {
                    row4.getAnim().setTo(SLIDE_TRANS_X, fValueOf);
                } else {
                    row4.getAnim().setTo(SLIDE_TRANS_X, Integer.valueOf(row5));
                }
            }
            onFrameCallback(true);
        }
        adjustLifecycleState();
    }
}
