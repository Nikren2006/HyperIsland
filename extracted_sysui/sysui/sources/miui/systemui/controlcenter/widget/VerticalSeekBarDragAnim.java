package miui.systemui.controlcenter.widget;

import android.view.MotionEvent;
import android.view.View;
import c1.f;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalSeekBarDragAnim {
    public static final Companion Companion = new Companion(null);
    private static final String PROP_SCALE = "tag_prop_scale";
    private static final String PROP_Y = "tag_prop_y";
    private static final String TAG = "VerticalSeekBarScaleAnim";
    private static final EaseManager.EaseStyle moveSpreadEase;
    private static final EaseManager.EaseStyle upSpreadEase;
    private IStateStyle anim;
    private AnimConfig animConfig;
    private float baseScale;
    private float baseTranslationY;
    private View dragView;
    private final TransitionListener listener;
    private float maxScale;
    private float maxTranslationY;
    private float minScale;
    private float minTranslationY;
    private float progressPer;
    private int sliderHeight;
    private float startX;
    private float startY;
    private float transY;
    private float translationFactor;
    private boolean dragEnabled = true;
    private float scaleXY = 1.0f;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.8f, 0.2f);
        n.f(style, "getStyle(...)");
        moveSpreadEase = style;
        EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 1.0f, 0.35f);
        n.f(style2, "getStyle(...)");
        upSpreadEase = style2;
    }

    public VerticalSeekBarDragAnim() {
        View view = this.dragView;
        this.transY = view != null ? view.getTranslationY() : 0.0f;
        this.translationFactor = 1.5f;
        TransitionListener transitionListener = new TransitionListener() { // from class: miui.systemui.controlcenter.widget.VerticalSeekBarDragAnim$listener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object toTag, Collection<? extends UpdateInfo> updateList) {
                n.g(toTag, "toTag");
                n.g(updateList, "updateList");
                super.onUpdate(toTag, updateList);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(updateList, "tag_prop_scale");
                if (updateInfoFindByName != null) {
                    VerticalSeekBarDragAnim verticalSeekBarDragAnim = this.this$0;
                    float f2 = verticalSeekBarDragAnim.scaleXY;
                    View dragView = verticalSeekBarDragAnim.getDragView();
                    float scaleX = dragView != null ? dragView.getScaleX() : 1.0f;
                    verticalSeekBarDragAnim.scaleXY = updateInfoFindByName.getFloatValue();
                    float f3 = scaleX + (verticalSeekBarDragAnim.scaleXY - f2);
                    View dragView2 = verticalSeekBarDragAnim.getDragView();
                    if (dragView2 != null) {
                        dragView2.setScaleX(f3);
                    }
                    View dragView3 = verticalSeekBarDragAnim.getDragView();
                    if (dragView3 != null) {
                        dragView3.setScaleY(f3);
                    }
                }
                UpdateInfo updateInfoFindByName2 = UpdateInfo.findByName(updateList, "tag_prop_y");
                if (updateInfoFindByName2 != null) {
                    VerticalSeekBarDragAnim verticalSeekBarDragAnim2 = this.this$0;
                    float f4 = verticalSeekBarDragAnim2.transY;
                    View dragView4 = verticalSeekBarDragAnim2.getDragView();
                    float translationY = dragView4 != null ? dragView4.getTranslationY() : 0.0f;
                    verticalSeekBarDragAnim2.transY = updateInfoFindByName2.getFloatValue();
                    float f5 = translationY + (verticalSeekBarDragAnim2.transY - f4);
                    View dragView5 = verticalSeekBarDragAnim2.getDragView();
                    if (dragView5 == null) {
                        return;
                    }
                    dragView5.setTranslationY(f5);
                }
            }
        };
        this.listener = transitionListener;
        IStateStyle iStateStyleUseValue = Folme.useValue(this);
        iStateStyleUseValue.setTo(PROP_SCALE, Float.valueOf(1.0f));
        iStateStyleUseValue.setTo(PROP_Y, Float.valueOf(0.0f));
        this.anim = iStateStyleUseValue;
        this.animConfig = new AnimConfig().addListeners(transitionListener);
        this.maxScale = 1.0f;
        this.minScale = 1.0f;
    }

    private final void animDownSetTo(float f2, float f3) {
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
            iStateStyle.setTo(PROP_SCALE, Float.valueOf(f2), PROP_Y, Float.valueOf(f3));
        }
    }

    public static /* synthetic */ void animDownSetTo$default(VerticalSeekBarDragAnim verticalSeekBarDragAnim, float f2, float f3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 1.0f;
        }
        if ((i2 & 2) != 0) {
            f3 = 0.0f;
        }
        verticalSeekBarDragAnim.animDownSetTo(f2, f3);
    }

    private final void animMoveTo(float f2, float f3) {
        AnimConfig animConfig = this.animConfig;
        if (animConfig != null) {
            animConfig.setEase(moveSpreadEase);
        }
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.to(PROP_SCALE, Float.valueOf(f2), PROP_Y, Float.valueOf(f3), this.animConfig);
        }
    }

    public static /* synthetic */ void animUpTo$default(VerticalSeekBarDragAnim verticalSeekBarDragAnim, float f2, float f3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 1.0f;
        }
        if ((i2 & 2) != 0) {
            f3 = 0.0f;
        }
        verticalSeekBarDragAnim.animUpTo(f2, f3);
    }

    private final float calMorePer(float f2) {
        float f3 = this.progressPer - (f2 / this.sliderHeight);
        return (Folme.afterFrictionValue(Math.max(f3 - 1, 0.0f), 5.0f) * (-1.0f)) + Folme.afterFrictionValue(Math.min(f3, 0.0f) * (-1.0f), 5.0f);
    }

    public static /* synthetic */ void performDrag$default(VerticalSeekBarDragAnim verticalSeekBarDragAnim, float f2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        verticalSeekBarDragAnim.performDrag(f2, z2);
    }

    public final void animUpTo(float f2, float f3) {
        AnimConfig animConfig = this.animConfig;
        if (animConfig != null) {
            animConfig.setEase(upSpreadEase);
        }
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.to(PROP_SCALE, Float.valueOf(f2), PROP_Y, Float.valueOf(f3), this.animConfig);
        }
    }

    public final void calAnimationBound() {
        float fCalMorePer = calMorePer(getMaxMoveDistance());
        float fCalMorePer2 = calMorePer(-getMaxMoveDistance());
        int i2 = this.sliderHeight;
        float f2 = i2 * fCalMorePer * 0.05f;
        this.maxTranslationY = f2;
        float f3 = i2 * fCalMorePer2 * 0.05f;
        this.minTranslationY = f3;
        float f4 = 1;
        float f5 = f4 - (fCalMorePer * 0.05f);
        this.minScale = f5;
        float f6 = f4 - (fCalMorePer2 * 0.05f);
        this.maxScale = f6;
        if (f3 > f2 || f5 > f6) {
            this.minTranslationY = 0.0f;
            this.maxTranslationY = 0.0f;
            this.minScale = 0.0f;
            this.maxScale = 0.0f;
            this.baseTranslationY = 0.0f;
            this.baseScale = 0.0f;
        }
    }

    public final void cleanAnim() {
        Folme.clean(this);
    }

    public final void dispatchTouchEvent(VerticalSeekBar seekBar, MotionEvent motionEvent) {
        n.g(seekBar, "seekBar");
        if (motionEvent != null && this.dragEnabled) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        performDrag((motionEvent.getX() - this.startX) * (-1.0f), true);
                        return;
                    } else if (action != 3) {
                        return;
                    }
                }
                animUpTo$default(this, 0.0f, 0.0f, 3, null);
                this.baseTranslationY = 0.0f;
                this.baseScale = 0.0f;
                return;
            }
            updateProgress(seekBar);
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            View view = this.dragView;
            this.baseTranslationY = view != null ? view.getTranslationY() : 0.0f;
            View view2 = this.dragView;
            float scaleY = view2 != null ? view2.getScaleY() : 0.0f;
            this.baseScale = scaleY;
            if (scaleY != 0.0f) {
                this.baseScale = scaleY / 255.0f;
            }
            if (this.baseScale == 0.0f && this.baseTranslationY == 0.0f) {
                return;
            }
            calAnimationBound();
        }
    }

    public final boolean getDragEnabled() {
        return this.dragEnabled;
    }

    public final View getDragView() {
        return this.dragView;
    }

    public final float getMaxMoveDistance() {
        return this.sliderHeight * this.translationFactor;
    }

    public final float getTranslationFactor() {
        return this.translationFactor;
    }

    public final void performDrag(float f2, boolean z2) {
        float fCalMorePer = calMorePer(f2);
        float f3 = this.sliderHeight * fCalMorePer * 0.05f;
        float f4 = 0.05f * fCalMorePer;
        float f5 = 1 - f4;
        if (z2) {
            float f6 = this.baseTranslationY;
            if (f6 != 0.0f || this.baseScale != 0.0f) {
                if (f6 > 0.0f && f2 < 0.0f) {
                    this.baseTranslationY = 0.0f;
                    this.baseScale = 0.0f;
                }
                animMoveTo(f.h((0.94f - f4) + this.baseScale + f.h(Math.abs(fCalMorePer) * 0.060000002f, 0.0f, 1.0f), this.minScale, this.maxScale), f.h(f3 + this.baseTranslationY, this.minTranslationY, this.maxTranslationY));
                return;
            }
        }
        animMoveTo(f5, f3);
    }

    public final void resetAnim() {
        animUpTo$default(this, 0.0f, 0.0f, 3, null);
    }

    public final void setDragEnabled(boolean z2) {
        this.dragEnabled = z2;
    }

    public final void setDragView(View view) {
        this.dragView = view;
    }

    public final void setTranslationFactor(float f2) {
        this.translationFactor = f2;
    }

    public final void updateProgress(VerticalSeekBar seekBar) {
        n.g(seekBar, "seekBar");
        this.sliderHeight = seekBar.getMeasuredWidth();
        this.progressPer = seekBar.getProgress() / seekBar.getMax();
    }
}
