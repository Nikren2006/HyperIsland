package com.android.systemui.miui.volume;

import android.os.Handler;
import android.util.Log;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes2.dex */
public final class SliderKeyAnim {
    private static final long LONG_PRESS_RELEASE_DELAY = 0;
    public static final String TAG = "SliderKeyAnim";
    private IStateStyle anim;
    private float direction;
    private float dragOffset;
    private int pressCount;
    private final MiuiVolumeSeekBar slider;
    private final Handler uiHandler;
    public static final Companion Companion = new Companion(null);
    private static final long SHORT_PRESS_RELEASE_DELAY = 100;
    private static final SliderKeyAnim$Companion$KEY_DRAG_PROGRESS$1 KEY_DRAG_PROGRESS = new FloatProperty<SliderKeyAnim>() { // from class: com.android.systemui.miui.volume.SliderKeyAnim$Companion$KEY_DRAG_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(SliderKeyAnim sliderKeyAnim) {
            if (sliderKeyAnim != null) {
                return sliderKeyAnim.dragOffset;
            }
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(SliderKeyAnim sliderKeyAnim, float f2) {
            MiuiVolumeSeekBar miuiVolumeSeekBar;
            if (f2 == -1.0f) {
                if (sliderKeyAnim != null) {
                    sliderKeyAnim.reset();
                }
                Log.e(SliderKeyAnim.TAG, "stop immediately");
            } else {
                if (sliderKeyAnim != null) {
                    sliderKeyAnim.dragOffset = Math.abs(f2);
                }
                if (sliderKeyAnim == null || (miuiVolumeSeekBar = sliderKeyAnim.slider) == null) {
                    return;
                }
                miuiVolumeSeekBar.startKeyDownAnim(sliderKeyAnim.dragOffset * sliderKeyAnim.direction);
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

    public SliderKeyAnim(MiuiVolumeSeekBar slider, Handler uiHandler) {
        kotlin.jvm.internal.n.g(slider, "slider");
        kotlin.jvm.internal.n.g(uiHandler, "uiHandler");
        this.slider = slider;
        this.uiHandler = uiHandler;
        this.direction = -1.0f;
    }

    private final float getMaxMoveDistance() {
        return this.slider.getmMaxMoveDistance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        this.pressCount = 0;
        this.dragOffset = 0.0f;
    }

    private final void startKeyAnim() {
        float fE = c1.f.e(Math.abs(this.dragOffset) + getMaxMoveDistance(), Math.abs(getMaxMoveDistance()));
        float fAbs = Math.abs(fE - Math.abs(this.dragOffset)) * 0.5f;
        AnimConfig animConfig = new AnimConfig();
        SliderKeyAnim$Companion$KEY_DRAG_PROGRESS$1 sliderKeyAnim$Companion$KEY_DRAG_PROGRESS$1 = KEY_DRAG_PROGRESS;
        animConfig.setSpecial(sliderKeyAnim$Companion$KEY_DRAG_PROGRESS$1, FolmeEase.linear((long) fAbs), new float[0]);
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.to(sliderKeyAnim$Companion$KEY_DRAG_PROGRESS$1, Float.valueOf(fE), animConfig);
        }
    }

    private final void startRestoreAnim(long j2) {
        this.uiHandler.postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.o
            @Override // java.lang.Runnable
            public final void run() {
                SliderKeyAnim.startRestoreAnim$lambda$1(this.f1503a);
            }
        }, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startRestoreAnim$lambda$1(SliderKeyAnim this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.slider.getAnimateState() == 0) {
            return;
        }
        IStateStyle iStateStyle = this$0.anim;
        if (iStateStyle != null) {
            iStateStyle.setTo(KEY_DRAG_PROGRESS, -1);
        }
        this$0.slider.startRestoreAnim();
    }

    public final void onCreate() {
        Log.d(TAG, "onCreate");
        this.anim = Folme.useValue(this);
    }

    public final void onDestory() {
        Log.d(TAG, "onDestory");
        Folme.clean(this);
    }

    public final void restore() {
        reset();
        if (this.slider.getAnimateState() == 2) {
            startRestoreAnim(0L);
        }
    }

    public final void volumeKeyDownEvent(int i2, boolean z2, boolean z3) {
        if (this.pressCount == 0) {
            reset();
        }
        Log.i(TAG, "volumeKeyDownEvent: currentProgress = " + i2 + ", pressCount = " + this.pressCount + ", expanded = " + z3);
        this.direction = z2 ? -1.0f : 1.0f;
        int i3 = this.pressCount + 1;
        this.pressCount = i3;
        if (i3 == 1) {
            this.slider.updateMaxMoveDistance();
            this.slider.updateViewHeight();
            this.slider.updateProgress();
            startKeyAnim();
        }
        if (this.pressCount > 100) {
            this.pressCount = 3;
        }
    }

    public final void volumeKeyUpEvent() {
        Log.i(TAG, "volumeKeyUpEvent: pressCount = " + this.pressCount);
        startRestoreAnim(this.pressCount <= 2 ? SHORT_PRESS_RELEASE_DELAY : LONG_PRESS_RELEASE_DELAY);
        reset();
    }
}
