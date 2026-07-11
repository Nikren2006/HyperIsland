package com.android.systemui.miui.volume;

import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes2.dex */
public class SlideContainerAnim {
    private static final String TAG_PROP_BTN_1_Y = "tag_prop_btn_1_y";
    private static final String TAG_PROP_BTN_2_Y = "tag_prop_btn_2_y";
    private static final String TAG_PROP_SCALE = "tag_prop_scale";
    private static final String TAG_PROP_Y = "tag_prop_y";
    private final IStateStyle anim;
    private final AnimConfig animConfig;
    private final TransitionListener listener;
    private final AnimListener mAnimListener;
    private int mAnimateState = 0;
    private float mCurScale = 1.0f;
    private float mCurBarY = 0.0f;
    private float mCurSuperVolumeY = 0.0f;
    private float mCurBtn1Y = 0.0f;
    private float mCurBtn2Y = 0.0f;
    private final EaseManager.EaseStyle dragMoveSpreadEase = EaseManager.getStyle(-2, 0.8f, 0.2f);
    private final EaseManager.EaseStyle dragUpSpreadEase = EaseManager.getStyle(-2, 1.0f, 0.35f);
    private final EaseManager.EaseStyle keyDownSpreadEase = EaseManager.getStyle(-2, 1.0f, 0.5f);
    private final EaseManager.EaseStyle keyUpSpreadEase = EaseManager.getStyle(-2, 1.0f, 0.35f);
    private final EaseManager.EaseStyle pressDownEase = FolmeEase.spring(1.0f, 1.0f);
    private final EaseManager.EaseStyle pressUpEase = FolmeEase.spring(0.95f, 0.35f);

    public interface AnimListener {
        void resetView();

        void setDndY(float f2, float f3);

        void setRingerY(float f2, float f3);

        void setScale(float f2, float f3);

        void setSuperVolumeY(float f2, float f3);

        void setVolY(float f2, float f3);
    }

    public SlideContainerAnim(AnimListener animListener) {
        TransitionListener transitionListener = new TransitionListener() { // from class: com.android.systemui.miui.volume.SlideContainerAnim.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj, Collection<UpdateInfo> collection) {
                super.onBegin(obj, collection);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                if (SlideContainerAnim.this.mAnimateState == 4) {
                    SlideContainerAnim.this.mAnimateState = 0;
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, SlideContainerAnim.TAG_PROP_SCALE);
                UpdateInfo updateInfoFindByName2 = UpdateInfo.findByName(collection, SlideContainerAnim.TAG_PROP_Y);
                UpdateInfo updateInfoFindByName3 = UpdateInfo.findByName(collection, SlideContainerAnim.TAG_PROP_BTN_1_Y);
                UpdateInfo updateInfoFindByName4 = UpdateInfo.findByName(collection, SlideContainerAnim.TAG_PROP_BTN_2_Y);
                if (updateInfoFindByName != null) {
                    float f2 = SlideContainerAnim.this.mCurScale;
                    SlideContainerAnim.this.mCurScale = updateInfoFindByName.getFloatValue();
                    SlideContainerAnim.this.mAnimListener.setScale(f2, SlideContainerAnim.this.mCurScale);
                }
                if (updateInfoFindByName2 != null) {
                    float f3 = SlideContainerAnim.this.mCurBarY;
                    SlideContainerAnim.this.mCurBarY = updateInfoFindByName2.getFloatValue();
                    SlideContainerAnim.this.mAnimListener.setVolY(f3, SlideContainerAnim.this.mCurBarY);
                }
                if (updateInfoFindByName3 != null) {
                    float f4 = SlideContainerAnim.this.mCurSuperVolumeY;
                    SlideContainerAnim.this.mCurSuperVolumeY = updateInfoFindByName3.getFloatValue() * 2.7f;
                    SlideContainerAnim.this.mAnimListener.setSuperVolumeY(f4, SlideContainerAnim.this.mCurSuperVolumeY);
                }
                if (updateInfoFindByName3 != null) {
                    float f5 = SlideContainerAnim.this.mCurBtn1Y;
                    SlideContainerAnim.this.mCurBtn1Y = updateInfoFindByName3.getFloatValue();
                    SlideContainerAnim.this.mAnimListener.setRingerY(f5, SlideContainerAnim.this.mCurBtn1Y);
                }
                if (updateInfoFindByName4 != null) {
                    float f6 = SlideContainerAnim.this.mCurBtn2Y;
                    SlideContainerAnim.this.mCurBtn2Y = updateInfoFindByName4.getFloatValue();
                    SlideContainerAnim.this.mAnimListener.setDndY(f6, SlideContainerAnim.this.mCurBtn2Y);
                }
            }
        };
        this.listener = transitionListener;
        this.anim = Folme.useValue(this);
        this.animConfig = new AnimConfig().addListeners(transitionListener);
        this.mAnimListener = animListener;
    }

    private void animSetTo(float f2, float f3, float f4, float f5) {
        this.anim.cancel();
        this.anim.setTo(TAG_PROP_SCALE, Float.valueOf(f2), TAG_PROP_Y, Float.valueOf(f3), TAG_PROP_BTN_1_Y, Float.valueOf(f4), TAG_PROP_BTN_2_Y, Float.valueOf(f5));
    }

    private void animTo(EaseManager.EaseStyle easeStyle, float f2, float f3, float f4, float f5) {
        this.animConfig.setEase(easeStyle);
        this.anim.to(TAG_PROP_SCALE, Float.valueOf(f2), TAG_PROP_Y, Float.valueOf(f3), TAG_PROP_BTN_1_Y, Float.valueOf(f4), TAG_PROP_BTN_2_Y, Float.valueOf(f5), this.animConfig);
    }

    public void animDownSetTo(float f2, float f3) {
        this.anim.cancel();
        animSetTo(f3, f2, f2, f2);
    }

    public void animDragMove(float f2, float f3, float f4, float f5) {
        this.mAnimateState = 3;
        animTo(this.dragMoveSpreadEase, f2, f3, f4, f5);
    }

    public void animDragUp() {
        if (this.mAnimateState == 4) {
            return;
        }
        this.mAnimateState = 4;
        animTo(this.dragUpSpreadEase, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void animKeyDown(float f2, float f3, float f4, float f5) {
        this.mAnimateState = 2;
        animTo(this.dragMoveSpreadEase, f2, f3, f4, f5);
    }

    public void animKeyUp() {
        this.mAnimateState = 4;
        animTo(this.dragUpSpreadEase, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void animPressDownTo(float f2) {
        this.mAnimateState = 3;
        animTo(this.pressDownEase, f2, 0.0f, 0.0f, 0.0f);
    }

    public void animPressUpTo() {
        if (this.mAnimateState == 4) {
            return;
        }
        this.mAnimateState = 4;
        animTo(this.pressUpEase, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void clean() {
        Folme.clean(this);
    }

    public int getAnimateState() {
        return this.mAnimateState;
    }

    public void initView() {
        this.mAnimateState = 0;
        this.mCurScale = 1.0f;
        this.mCurBarY = 0.0f;
        this.mCurSuperVolumeY = 0.0f;
        this.mCurBtn1Y = 0.0f;
        this.mCurBtn2Y = 0.0f;
        animSetTo(1.0f, 0.0f, 0.0f, 0.0f);
        this.mAnimListener.resetView();
    }
}
