package miui.systemui.controlcenter.panel.main.volume;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import androidx.lifecycle.Lifecycle;
import com.xiaomi.onetrack.util.aa;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.recyclerview.ToggleSliderViewHolder;
import miui.systemui.controlcenter.widget.VerticalSeekBar;
import miui.systemui.controlcenter.widget.VerticalSeekBarDragAnim;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
public final class VolumeSliderDragAnimator {
    private static final long LONG_PRESS_RELEASE_DELAY = 0;
    public static final String TAG = "VolumeSliderDragAnimator";
    private final int STATE_ANIMATING;
    private final int STATE_IDLE;
    private boolean abortEvent;
    private IStateStyle anim;
    private int animateState;
    private float direction;
    private float dragOffset;
    private final Lifecycle lifecycle;
    private int pressCount;
    private boolean touching;
    private float translationFactor;
    private final Handler uiHandler;
    private final VolumeSliderController volumeSliderController;
    public static final Companion Companion = new Companion(null);
    private static final long SHORT_PRESS_RELEASE_DELAY = 100;
    private static final VolumeSliderDragAnimator$Companion$KEY_DRAG_PROGRESS$1 KEY_DRAG_PROGRESS = new FloatProperty<VolumeSliderDragAnimator>() { // from class: miui.systemui.controlcenter.panel.main.volume.VolumeSliderDragAnimator$Companion$KEY_DRAG_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(VolumeSliderDragAnimator volumeSliderDragAnimator) {
            if (volumeSliderDragAnimator != null) {
                return volumeSliderDragAnimator.dragOffset;
            }
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(VolumeSliderDragAnimator volumeSliderDragAnimator, float f2) {
            VerticalSeekBar slider;
            if (f2 == -1.0f) {
                if (volumeSliderDragAnimator != null) {
                    volumeSliderDragAnimator.dragOffset = 0.0f;
                }
                Log.i(VolumeSliderDragAnimator.TAG, "stop immediately");
            } else {
                if (volumeSliderDragAnimator != null) {
                    volumeSliderDragAnimator.dragOffset = Math.abs(f2);
                }
                if (volumeSliderDragAnimator == null || (slider = volumeSliderDragAnimator.getSlider()) == null) {
                    return;
                }
                VerticalSeekBarDragAnim.performDrag$default(slider.getDragAnim(), volumeSliderDragAnimator.dragOffset * volumeSliderDragAnimator.direction, false, 2, null);
            }
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public VolumeSliderDragAnimator(VolumeSliderController volumeSliderController, Handler uiHandler, Lifecycle lifecycle) {
        n.g(volumeSliderController, "volumeSliderController");
        n.g(uiHandler, "uiHandler");
        n.g(lifecycle, "lifecycle");
        this.volumeSliderController = volumeSliderController;
        this.uiHandler = uiHandler;
        this.lifecycle = lifecycle;
        this.direction = -1.0f;
        this.STATE_ANIMATING = 1;
        this.animateState = this.STATE_IDLE;
        this.translationFactor = 1.5f;
    }

    private final boolean allowPerformDragAnim() {
        return (this.abortEvent || this.touching || !this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) ? false : true;
    }

    private final float getMaxMoveDistance() {
        VerticalSeekBarDragAnim dragAnim;
        VerticalSeekBar slider = getSlider();
        if (slider == null || (dragAnim = slider.getDragAnim()) == null) {
            return 800.0f;
        }
        return dragAnim.getMaxMoveDistance();
    }

    private final long getRelease_delay() {
        return this.pressCount > 2 ? LONG_PRESS_RELEASE_DELAY : SHORT_PRESS_RELEASE_DELAY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VerticalSeekBar getSlider() {
        ToggleSliderViewHolder sliderHolder = this.volumeSliderController.getSliderHolder();
        if (sliderHolder != null) {
            return sliderHolder.getSlider();
        }
        return null;
    }

    private final void startDragAnim() {
        float fE = c1.f.e(Math.abs(this.dragOffset) + getMaxMoveDistance(), Math.abs(getMaxMoveDistance()));
        float fAbs = Math.abs(fE - Math.abs(this.dragOffset)) * 0.5f;
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.setEase(FolmeEase.linear((long) fAbs), KEY_DRAG_PROGRESS);
        }
        IStateStyle iStateStyle2 = this.anim;
        if (iStateStyle2 != null) {
            iStateStyle2.to(KEY_DRAG_PROGRESS, Float.valueOf(fE));
        }
    }

    public static /* synthetic */ void stopImmediately$default(VolumeSliderDragAnimator volumeSliderDragAnimator, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        volumeSliderDragAnimator.stopImmediately(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopImmediately$lambda$1(VolumeSliderDragAnimator this$0, long j2) {
        VerticalSeekBarDragAnim dragAnim;
        n.g(this$0, "this$0");
        Log.i(TAG, "execute RestoreAnim " + this$0.animateState + " " + j2);
        if (this$0.animateState != this$0.STATE_ANIMATING) {
            return;
        }
        IStateStyle iStateStyle = this$0.anim;
        if (iStateStyle != null) {
            iStateStyle.setTo(KEY_DRAG_PROGRESS, -1);
        }
        VerticalSeekBar slider = this$0.getSlider();
        if (slider != null && (dragAnim = slider.getDragAnim()) != null) {
            VerticalSeekBarDragAnim.animUpTo$default(dragAnim, 0.0f, 0.0f, 3, null);
        }
        this$0.animateState = this$0.STATE_IDLE;
    }

    public final int getSTATE_ANIMATING() {
        return this.STATE_ANIMATING;
    }

    public final int getSTATE_IDLE() {
        return this.STATE_IDLE;
    }

    public final float getTranslationFactor() {
        return this.translationFactor;
    }

    public final void handleTouchEvent(MotionEvent event) {
        n.g(event, "event");
        this.touching = (event.getActionMasked() == 1 || event.getActionMasked() == 3) ? false : true;
        if (this.animateState != this.STATE_ANIMATING) {
            return;
        }
        stopImmediately$default(this, 0L, 1, null);
        this.abortEvent = this.touching;
    }

    public final void onCreate() {
        this.anim = Folme.useValue(this);
    }

    public final void onDestroy() {
        Folme.clean(this);
    }

    public final void resetAbortEvent() {
        this.abortEvent = false;
    }

    public final void resetTouchStatus() {
        this.touching = false;
    }

    public final void setTranslationFactor(float f2) {
        this.translationFactor = f2;
    }

    public final void stopImmediately(final long j2) {
        this.uiHandler.postDelayed(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.volume.h
            @Override // java.lang.Runnable
            public final void run() {
                VolumeSliderDragAnimator.stopImmediately$lambda$1(this.f5453a, j2);
            }
        }, j2);
        this.pressCount = 0;
    }

    public final void volumeKeyDownEvent(int i2, boolean z2) {
        int i3 = i2 + (z2 ? 1 : -1);
        Log.i(TAG, "volumeKeyDownEvent," + this.abortEvent + aa.f3429b + this.touching + aa.f3429b + this.lifecycle.getCurrentState());
        VerticalSeekBar slider = getSlider();
        if (i3 <= (slider != null ? slider.getMax() : Integer.MAX_VALUE)) {
            VerticalSeekBar slider2 = getSlider();
            if (i3 >= (slider2 != null ? slider2.getMin() : Integer.MIN_VALUE)) {
                return;
            }
        }
        if (allowPerformDragAnim()) {
            this.direction = z2 ? -1.0f : 1.0f;
            int i4 = this.pressCount + 1;
            this.pressCount = i4;
            if (i4 == 1) {
                VerticalSeekBar slider3 = getSlider();
                if (slider3 != null) {
                    slider3.getDragAnim().setTranslationFactor(this.translationFactor);
                    slider3.getDragAnim().updateProgress(slider3);
                    slider3.getDragAnim().calAnimationBound();
                }
                startDragAnim();
            }
            this.animateState = this.STATE_ANIMATING;
        }
    }

    public final void volumeKeyUpEvent() {
        Log.i(TAG, "volumeKeyUpEvent " + this.abortEvent + " " + this.touching + " " + this.lifecycle.getCurrentState());
        if (allowPerformDragAnim()) {
            stopImmediately(getRelease_delay());
        } else {
            this.abortEvent = false;
        }
    }
}
