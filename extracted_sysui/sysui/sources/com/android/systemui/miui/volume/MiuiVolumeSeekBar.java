package com.android.systemui.miui.volume;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.systemui.miui.volume.SlideContainerAnim;
import com.android.systemui.miui.volume.widget.VolumeVerticalSeekBar;
import miui.systemui.util.VolumeUtils;
import miui.systemui.widget.RelativeSeekBarInjector;
import miuix.animation.Folme;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiVolumeSeekBar extends VolumeVerticalSeekBar {
    private static final float CLICK_MOVE_Y = 20.0f;
    private static final int CLICK_TIME = 200;
    private static final float NORMAL_SCALE = 1.0f;
    private static final float PRESS_DOWN_SCALE = 0.94f;
    private static final String TAG = "MiuiVolumeSeekBar";
    private int dndBlurHeight;
    private MotionEvent downEvent;
    private boolean isNeedDoClick;
    private boolean isSuperVolume;
    private float mBaseScale;
    private long mCurrentMS;
    private float mDownY;
    private RelativeSeekBarInjector mInjector;
    private float mMaxMoveDistance;
    private float mMorePer;
    private long mMoveTime;
    private float mMoveY;
    private int mRadius;
    private SeekBarAnimListener mSeekBarAnimListener;
    private SeekBarOnclickListener mSeekBarOnclickListener;
    private SlideContainerAnim mSlideAnim;
    private SliderKeyAnim mSliderKeyAnim;
    private int ringerBlurHeight;
    private int ringerDivider;
    private int ringerTop;
    private float startY;
    private String superVolumePercent;
    private int volumeHeight;
    private float volumeStartProgress;

    public interface SeekBarAnimListener extends SlideContainerAnim.AnimListener {
        int[] getHeightArray();
    }

    public interface SeekBarOnclickListener {
        void onClick();
    }

    public MiuiVolumeSeekBar(Context context) {
        this(context, null);
    }

    private float calDndY(float f2) {
        float f3 = 1.0f - f2;
        return ((this.volumeHeight / 2.0f) * f3) + ((this.ringerTop + this.ringerBlurHeight + this.ringerDivider + (this.dndBlurHeight / 2.0f)) * f3);
    }

    private float calMorePer(float f2) {
        float f3 = this.volumeStartProgress - (f2 / this.volumeHeight);
        return (Folme.afterFrictionValue(Math.max(f3 - 1.0f, 0.0f), 5.0f) * (-1.0f)) + Folme.afterFrictionValue(Math.min(f3, 0.0f) * (-1.0f), 5.0f);
    }

    private float calTargetBarY(float f2) {
        return f2 * this.volumeHeight * 0.05f;
    }

    private float calTargetRingerY(float f2) {
        float f3 = 1.0f - f2;
        return ((this.volumeHeight / 2.0f) * f3) + ((this.ringerTop + (this.ringerBlurHeight / 2.0f)) * f3);
    }

    private void drawRadius(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, getWidth(), getHeight());
        int i2 = this.mRadius;
        path.addRoundRect(rectF, i2, i2, Path.Direction.CW);
        canvas.clipPath(path);
    }

    public void cancelKeyAnim() {
        SliderKeyAnim sliderKeyAnim = this.mSliderKeyAnim;
        if (sliderKeyAnim != null) {
            sliderKeyAnim.restore();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            com.android.systemui.miui.volume.SlideContainerAnim r0 = r5.mSlideAnim
            if (r0 != 0) goto L9
            boolean r5 = super.dispatchTouchEvent(r6)
            return r5
        L9:
            int r0 = r6.getAction()
            r1 = 1064346583(0x3f70a3d7, float:0.94)
            r2 = 0
            if (r0 == 0) goto L74
            r3 = 1
            if (r0 == r3) goto L5c
            r3 = 2
            if (r0 == r3) goto L1e
            r3 = 3
            if (r0 == r3) goto L5c
            goto L9e
        L1e:
            float r0 = r6.getY()
            float r1 = r5.startY
            float r0 = r0 - r1
            float r1 = r5.mMaxMoveDistance
            float r3 = -r1
            float r0 = com.android.systemui.miui.volume.Util.constrain(r0, r3, r1)
            float r0 = r5.calMorePer(r0)
            r5.mMorePer = r0
            float r1 = r5.mBaseScale
            float r0 = java.lang.Math.abs(r0)
            r3 = 1065353216(0x3f800000, float:1.0)
            float r0 = com.android.systemui.miui.volume.Util.constrain(r0, r2, r3)
            float r2 = r5.mBaseScale
            float r3 = r3 - r2
            float r0 = r0 * r3
            float r1 = r1 + r0
            float r0 = r5.mMorePer
            r2 = 1028443341(0x3d4ccccd, float:0.05)
            float r2 = r2 * r0
            float r1 = r1 - r2
            float r0 = r5.calTargetBarY(r0)
            float r2 = r5.calTargetRingerY(r1)
            float r3 = r5.calDndY(r1)
            com.android.systemui.miui.volume.SlideContainerAnim r4 = r5.mSlideAnim
            r4.animDragMove(r1, r0, r2, r3)
            goto L9e
        L5c:
            float r0 = r5.mBaseScale
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L6e
            float r0 = r5.mMorePer
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L6e
            com.android.systemui.miui.volume.SlideContainerAnim r0 = r5.mSlideAnim
            r0.animPressUpTo()
            goto L9e
        L6e:
            com.android.systemui.miui.volume.SlideContainerAnim r0 = r5.mSlideAnim
            r0.animDragUp()
            goto L9e
        L74:
            r5.mMorePer = r2
            r5.updateViewHeight()
            r5.updateMaxMoveDistance()
            float r0 = r6.getY()
            r5.startY = r0
            r5.updateProgress()
            float r0 = r5.mBaseScale
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L91
            com.android.systemui.miui.volume.SlideContainerAnim r1 = r5.mSlideAnim
            r1.animPressDownTo(r0)
            goto L9e
        L91:
            int r0 = r5.getAnimateState()
            if (r0 != 0) goto L9e
            com.android.systemui.miui.volume.SlideContainerAnim r0 = r5.mSlideAnim
            float r1 = r5.mBaseScale
            r0.animDownSetTo(r2, r1)
        L9e:
            boolean r5 = super.dispatchTouchEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.miui.volume.MiuiVolumeSeekBar.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void doClick() {
        this.isNeedDoClick = true;
    }

    public void enableTouchDownAnim() {
        this.mBaseScale = 0.94f;
    }

    public int getAnimateState() {
        SlideContainerAnim slideContainerAnim = this.mSlideAnim;
        if (slideContainerAnim == null) {
            return 0;
        }
        return slideContainerAnim.getAnimateState();
    }

    public int getRadius() {
        return this.mRadius;
    }

    public float getmMaxMoveDistance() {
        return this.mMaxMoveDistance;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSlideAnim = new SlideContainerAnim(this.mSeekBarAnimListener);
        SliderKeyAnim sliderKeyAnim = new SliderKeyAnim(this, getHandler());
        this.mSliderKeyAnim = sliderKeyAnim;
        sliderKeyAnim.onCreate();
        post(new Runnable() { // from class: com.android.systemui.miui.volume.MiuiVolumeSeekBar.1
            @Override // java.lang.Runnable
            public void run() {
                if (MiuiVolumeSeekBar.this.mSlideAnim != null) {
                    MiuiVolumeSeekBar.this.mSlideAnim.animDownSetTo(0.0f, 1.0f);
                }
            }
        });
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mSlideAnim != null) {
            resetView();
            this.mSlideAnim.clean();
            this.mSlideAnim = null;
        }
        SliderKeyAnim sliderKeyAnim = this.mSliderKeyAnim;
        if (sliderKeyAnim != null) {
            sliderKeyAnim.onDestory();
            this.mSliderKeyAnim = null;
        }
    }

    @Override // miui.systemui.widget.VerticalSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        drawRadius(canvas);
        super.onDraw(canvas);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (this.isSuperVolume) {
            accessibilityNodeInfo.setStateDescription(this.superVolumePercent);
        }
    }

    @Override // miui.systemui.widget.VerticalSeekBar, android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mInjector.transformTouchEvent(motionEvent);
        if (this.mSeekBarOnclickListener != null && this.isNeedDoClick && doClick(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void resetView() {
        SlideContainerAnim slideContainerAnim = this.mSlideAnim;
        if (slideContainerAnim != null) {
            slideContainerAnim.initView();
        }
    }

    public void setRadius(int i2) {
        this.mRadius = i2;
    }

    public void setSeekBarAnimListener(SeekBarAnimListener seekBarAnimListener) {
        this.mSeekBarAnimListener = seekBarAnimListener;
    }

    public void setSeekBarOnclickListener(SeekBarOnclickListener seekBarOnclickListener) {
        this.mSeekBarOnclickListener = seekBarOnclickListener;
    }

    public void startKeyDownAnim(float f2) {
        float fCalMorePer = calMorePer(f2);
        float f3 = 1.0f - (0.05f * fCalMorePer);
        this.mSlideAnim.animKeyDown(f3, calTargetBarY(fCalMorePer), calTargetRingerY(f3), calDndY(f3));
    }

    public void startRestoreAnim() {
        this.mSlideAnim.animKeyUp();
    }

    public void updateMaxMoveDistance() {
        this.mMaxMoveDistance = getHeight() * 1.4f;
    }

    public void updateProgress() {
        this.volumeStartProgress = getProgress() / getMax();
    }

    public void updateSuperVolume(boolean z2, int i2) {
        this.isSuperVolume = z2;
        this.superVolumePercent = VolumeUtils.getSuperVolumePercent(getContext(), i2);
    }

    public void updateViewHeight() {
        this.volumeHeight = getHeight();
        int[] heightArray = this.mSeekBarAnimListener.getHeightArray();
        this.ringerBlurHeight = heightArray[0];
        this.dndBlurHeight = heightArray[1];
        this.ringerDivider = heightArray[2];
        this.ringerTop = heightArray[3];
    }

    public void volumeKeyDown(boolean z2, boolean z3) {
        SliderKeyAnim sliderKeyAnim = this.mSliderKeyAnim;
        if (sliderKeyAnim != null) {
            sliderKeyAnim.volumeKeyDownEvent(getProgress(), z2, z3);
        }
    }

    public void volumeKeyUp() {
        SliderKeyAnim sliderKeyAnim = this.mSliderKeyAnim;
        if (sliderKeyAnim != null) {
            sliderKeyAnim.volumeKeyUpEvent();
        }
    }

    public MiuiVolumeSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean doClick(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downEvent = motionEvent;
            this.mDownY = motionEvent.getY();
            this.mMoveY = 0.0f;
            this.mCurrentMS = System.currentTimeMillis();
            this.mMoveTime = 0L;
        } else if (action == 1) {
            this.isNeedDoClick = false;
            if (this.mMoveTime < 200 && this.mMoveY < CLICK_MOVE_Y) {
                this.mSeekBarOnclickListener.onClick();
            }
        } else if (action == 2) {
            this.mMoveTime = System.currentTimeMillis() - this.mCurrentMS;
            this.mMoveY += Math.abs(motionEvent.getY() - this.mDownY);
            this.mDownY = motionEvent.getY();
        } else if (action == 3) {
            this.isNeedDoClick = false;
        }
        if (this.mMoveTime < 200 && this.mMoveY < CLICK_MOVE_Y) {
            return true;
        }
        this.isNeedDoClick = false;
        MotionEvent motionEvent2 = this.downEvent;
        if (motionEvent2 != null) {
            super.onTouchEvent(motionEvent2);
            this.downEvent = null;
        }
        return false;
    }

    public MiuiVolumeSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mInjector = new RelativeSeekBarInjector(this, true);
        this.mDownY = 0.0f;
        this.mMoveY = 0.0f;
        this.mCurrentMS = 0L;
        this.mMoveTime = 0L;
        this.mRadius = 0;
        this.mMaxMoveDistance = -1.0f;
        this.superVolumePercent = VolumeUtils.getSuperVolumePercent(getContext());
        this.isSuperVolume = false;
        this.mBaseScale = 1.0f;
        this.isNeedDoClick = false;
        this.downEvent = null;
        this.mMorePer = 0.0f;
    }
}
