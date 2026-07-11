package miui.systemui.flashlight;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import g1.AbstractC0369g;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.flashlight.utils.TrackUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$seekBarOnTouchListener$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightController$seekBarOnTouchListener$2$1, reason: invalid class name */
    public static final class AnonymousClass1 implements View.OnTouchListener {
        private int preProgress;
        private int seekBarProgress;
        private int targetProgress;
        final /* synthetic */ MiFlashlightController this$0;
        private float touchStart;

        public AnonymousClass1(MiFlashlightController miFlashlightController) {
            this.this$0 = miFlashlightController;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void doTouch(MotionEvent motionEvent) {
            Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                this.touchStart = motionEvent.getRawY();
                int progress = this.this$0.getSeekbarTouchView().getProgress();
                this.seekBarProgress = progress;
                this.preProgress = progress;
                this.targetProgress = progress;
                return;
            }
            if (numValueOf == null || numValueOf.intValue() != 2) {
                if (numValueOf == null || numValueOf.intValue() != 1 || this.targetProgress == this.seekBarProgress) {
                    return;
                }
                TrackUtils.INSTANCE.trackSlide(this.this$0.context, this.seekBarProgress, this.targetProgress);
                return;
            }
            int rawY = this.seekBarProgress + ((int) (((this.touchStart - motionEvent.getRawY()) / this.this$0.getSeekBarTouchLength()) * 100));
            if (rawY > this.this$0.getSeekbarTouchView().getMax()) {
                rawY = this.this$0.getSeekbarTouchView().getMax();
            } else if (rawY < this.this$0.getSeekbarTouchView().getMin()) {
                rawY = this.this$0.getSeekbarTouchView().getMin();
            }
            this.targetProgress = rawY;
            if (this.preProgress != rawY) {
                this.preProgress = rawY;
                float max = rawY / this.this$0.getSeekbarTouchView().getMax();
                this.this$0.setFlashlightStatus(max);
                MiFlashlightController.setUiLogicProgress$default(this.this$0, max, false, 2, null);
                this.this$0.hapticWhenSeekBarToEdge(this.targetProgress);
            }
        }

        public final int getPreProgress() {
            return this.preProgress;
        }

        public final int getSeekBarProgress() {
            return this.seekBarProgress;
        }

        public final int getTargetProgress() {
            return this.targetProgress;
        }

        public final float getTouchStart() {
            return this.touchStart;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            AbstractC0369g.b(this.this$0.mainScope, null, null, new MiFlashlightController$seekBarOnTouchListener$2$1$onTouch$1(this.this$0, this, motionEvent, null), 3, null);
            return true;
        }

        public final void setPreProgress(int i2) {
            this.preProgress = i2;
        }

        public final void setSeekBarProgress(int i2) {
            this.seekBarProgress = i2;
        }

        public final void setTargetProgress(int i2) {
            this.targetProgress = i2;
        }

        public final void setTouchStart(float f2) {
            this.touchStart = f2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekBarOnTouchListener$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        return new AnonymousClass1(this.this$0);
    }
}
