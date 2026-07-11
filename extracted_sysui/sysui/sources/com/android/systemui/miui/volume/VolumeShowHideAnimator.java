package com.android.systemui.miui.volume;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeShowHideAnimator {
    private IStateStyle anim;
    private final List<AnimViewConfig> animViewConfigList;
    private final List<String> animViewConfigTag;
    private ViewArgs centerArgs;
    private TransitionListener listener;
    private TransitionListener mCallback;
    private final Context mContext;
    private boolean mExpanded;
    private boolean mIsAnimating;
    private final MiuiVolumeDialogMotion mMotion;
    private View[] mRingerBtnLayouts;
    private View mRingerDivider;
    private View mRingerLayout;
    private View mShadowView;
    private View mSuperVolume;
    private View mVolumeContainer;
    private View mVolumeView;
    private Float[] ringerBtnLayoutsX;
    private long startTime;
    private float volumeX;

    public VolumeShowHideAnimator(Context mContext, MiuiVolumeDialogMotion mMotion) {
        kotlin.jvm.internal.n.g(mContext, "mContext");
        kotlin.jvm.internal.n.g(mMotion, "mMotion");
        this.mContext = mContext;
        this.mMotion = mMotion;
        IStateStyle iStateStyleUseValue = Folme.useValue(this);
        kotlin.jvm.internal.n.f(iStateStyleUseValue, "useValue(...)");
        this.anim = iStateStyleUseValue;
        this.animViewConfigList = new ArrayList();
        this.animViewConfigTag = new ArrayList();
        Float fValueOf = Float.valueOf(0.0f);
        this.ringerBtnLayoutsX = new Float[]{fValueOf, fValueOf};
        this.listener = new TransitionListener() { // from class: com.android.systemui.miui.volume.VolumeShowHideAnimator$listener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj, Collection<UpdateInfo> collection) {
                if (collection != null) {
                    Iterator<T> it = collection.iterator();
                    while (it.hasNext()) {
                        Log.i(VolumeShowHideAnimatorKt.TAG, "listener_onBegin: " + ((UpdateInfo) it.next()).property.getName());
                    }
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object toTag) {
                kotlin.jvm.internal.n.g(toTag, "toTag");
                Log.e(VolumeShowHideAnimatorKt.TAG, "listener_onCancel: " + toTag);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                this.this$0.onAnimComplete();
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                Iterator it = this.this$0.animViewConfigList.iterator();
                while (it.hasNext()) {
                    ((AnimViewConfig) it.next()).updateInfo(collection);
                }
                if (collection != null) {
                    VolumeShowHideAnimator volumeShowHideAnimator = this.this$0;
                    for (UpdateInfo updateInfo : collection) {
                        String name = updateInfo.property.getName();
                        if (updateInfo.isCompleted && volumeShowHideAnimator.animViewConfigTag.contains(name)) {
                            long jCurrentTimeMillis = System.currentTimeMillis() - volumeShowHideAnimator.getStartTime();
                            Log.i(VolumeShowHideAnimatorKt.TAG, "listener_remove: " + volumeShowHideAnimator.animViewConfigTag.size() + ", " + jCurrentTimeMillis + ", " + name + ": " + updateInfo.getFloatValue());
                            volumeShowHideAnimator.animViewConfigTag.remove(name);
                        }
                    }
                }
            }
        };
    }

    private final void addConfigTag(String str, Float f2) {
        this.animViewConfigTag.add(str);
        if (f2 != null) {
            Folme.getValueTarget(this).setMinVisibleChange(f2.floatValue(), str);
        }
    }

    public static /* synthetic */ void addConfigTag$default(VolumeShowHideAnimator volumeShowHideAnimator, String str, Float f2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f2 = null;
        }
        volumeShowHideAnimator.addConfigTag(str, f2);
    }

    private final void expanded(final boolean z2, boolean z3) {
        String str;
        View[] viewArr;
        int i2;
        ViewArgs viewArgs = this.centerArgs;
        String str2 = "centerArgs";
        if (viewArgs == null) {
            kotlin.jvm.internal.n.w("centerArgs");
            viewArgs = null;
        }
        float fx = viewArgs.getFX();
        ViewArgs viewArgs2 = this.centerArgs;
        if (viewArgs2 == null) {
            kotlin.jvm.internal.n.w("centerArgs");
            viewArgs2 = null;
        }
        Log.e(VolumeShowHideAnimatorKt.TAG, "expand: " + z2 + ", withAnim:" + z3 + ", " + fx + " -> " + viewArgs2.getTX());
        BoostHelper boostHelper = BoostHelper.getInstance();
        View view = this.mVolumeView;
        if (view == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view = null;
        }
        boostHelper.boostWithCpuFreq(2000L, view);
        this.mExpanded = z2;
        this.startTime = System.currentTimeMillis();
        onAnimBegin();
        if (!z2 && !z3) {
            this.mMotion.setVolumeDialogVisible(false, "dismiss: rotation Change");
        }
        ArrayList arrayList = new ArrayList();
        final int width$default = VolumeColumnRes.getWidth$default(this.mContext, false, false, false, 14, null);
        final int tempColumnContainerMarginLeft = MiuiVolumeDialogRes.getTempColumnContainerMarginLeft(this.mContext);
        final int shadowWidth$default = (this.mMotion.isDualVolume() ? (MiuiVolumeDialogRes.getShadowWidth$default(this.mContext, false, true, 0, 8, null) - (width$default * 2)) - tempColumnContainerMarginLeft : MiuiVolumeDialogRes.getShadowWidth$default(this.mContext, false, false, 0, 8, null) - width$default) >> 1;
        ViewArgs viewArgs3 = this.centerArgs;
        if (viewArgs3 == null) {
            kotlin.jvm.internal.n.w("centerArgs");
            viewArgs3 = null;
        }
        viewArgs3.setFScale(z2 ? 0.8f : 1.0f);
        ViewArgs viewArgs4 = this.centerArgs;
        if (viewArgs4 == null) {
            kotlin.jvm.internal.n.w("centerArgs");
            viewArgs4 = null;
        }
        viewArgs4.setTScale(z2 ? 1.0f : 0.8f);
        View view2 = this.mVolumeView;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view2 = null;
        }
        final AnimViewConfig animViewConfig = new AnimViewConfig(view2, this.listener);
        ViewArgs viewArgs5 = new ViewArgs(this.mExpanded ? new float[]{0.95f, 0.25f} : new float[]{0.95f, 0.3f}, 0L, 2, null);
        viewArgs5.setFX(animViewConfig.getTarget().getX());
        ViewArgs viewArgs6 = this.centerArgs;
        if (viewArgs6 == null) {
            kotlin.jvm.internal.n.w("centerArgs");
            viewArgs6 = null;
        }
        viewArgs5.setTX(viewArgs6.getTX());
        View view3 = this.mVolumeContainer;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("mVolumeContainer");
            view3 = null;
        }
        viewArgs5.setFScale(view3.getScaleX());
        ViewArgs viewArgs7 = this.centerArgs;
        if (viewArgs7 == null) {
            kotlin.jvm.internal.n.w("centerArgs");
            viewArgs7 = null;
        }
        viewArgs5.setTScale(viewArgs7.getTScale());
        viewArgs5.setEaseScale(this.mExpanded ? new float[]{0.95f, 0.15f} : new float[]{1.0f, 0.25f});
        animViewConfig.setViewArgs(viewArgs5);
        if (z2) {
            this.volumeX = animViewConfig.getViewArgs().getFX();
        }
        animViewConfig.addUpdateCallback(animViewConfig.propertyName(animViewConfig.getX()), new UpdateCallback() { // from class: com.android.systemui.miui.volume.VolumeShowHideAnimator$expanded$configVolumeView$1$2
            @Override // com.android.systemui.miui.volume.UpdateCallback
            public UpdateResult callback(float f2) {
                View view4 = this.this$0.mSuperVolume;
                View view5 = null;
                if (view4 == null) {
                    kotlin.jvm.internal.n.w("mSuperVolume");
                    view4 = null;
                }
                int i3 = width$default;
                VolumeShowHideAnimator volumeShowHideAnimator = this.this$0;
                int i4 = tempColumnContainerMarginLeft;
                CommonUtils.INSTANCE.setAlphaEx(view4, 1.0f);
                if (view4.getVisibility() == 0) {
                    float width = ((i3 - view4.getWidth()) / 2) + f2;
                    if (volumeShowHideAnimator.getMMotion().getSuperVolumeShowAboveTemp()) {
                        width = width + i3 + i4;
                    }
                    view4.setX(width);
                } else {
                    view4.setTranslationX(0.0f);
                }
                View view6 = this.this$0.mShadowView;
                if (view6 == null) {
                    kotlin.jvm.internal.n.w("mShadowView");
                } else {
                    view5 = view6;
                }
                int i5 = shadowWidth$default;
                AnimViewConfig animViewConfig2 = animViewConfig;
                boolean z4 = z2;
                view5.setX(f2 - i5);
                float fx2 = (f2 - animViewConfig2.getViewArgs().getFX()) / (animViewConfig2.getViewArgs().getTX() - animViewConfig2.getViewArgs().getFX());
                if (!z4) {
                    fx2 = 1.0f - fx2;
                }
                view5.setAlpha(fx2);
                this.this$0.volumeX = Y0.b.b(f2);
                this.this$0.setViewX();
                return new UpdateResult(false, f2);
            }
        });
        View view4 = this.mRingerLayout;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("mRingerLayout");
            view4 = null;
        }
        ViewGroup.LayoutParams layoutParams = view4.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        final int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        View view5 = this.mRingerDivider;
        if (view5 == null) {
            kotlin.jvm.internal.n.w("mRingerDivider");
            view5 = null;
        }
        final int measuredHeight = view5.getMeasuredHeight();
        View view6 = this.mVolumeView;
        if (view6 == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view6 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = view6.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        final int i4 = ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin;
        View view7 = this.mSuperVolume;
        if (view7 == null) {
            kotlin.jvm.internal.n.w("mSuperVolume");
            view7 = null;
        }
        ViewGroup.LayoutParams layoutParams3 = view7.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams3, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        final int i5 = ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin;
        animViewConfig.addUpdateCallback(animViewConfig.propertyName(animViewConfig.getSCALE()), new UpdateCallback() { // from class: com.android.systemui.miui.volume.VolumeShowHideAnimator$expanded$configVolumeView$1$3
            @Override // com.android.systemui.miui.volume.UpdateCallback
            public UpdateResult callback(float f2) {
                int height;
                int height2;
                View view8 = this.this$0.mVolumeContainer;
                if (view8 == null) {
                    kotlin.jvm.internal.n.w("mVolumeContainer");
                    view8 = null;
                }
                view8.setScaleX(f2);
                View view9 = this.this$0.mVolumeContainer;
                if (view9 == null) {
                    kotlin.jvm.internal.n.w("mVolumeContainer");
                    view9 = null;
                }
                view9.setScaleY(f2);
                View view10 = this.this$0.mSuperVolume;
                if (view10 == null) {
                    kotlin.jvm.internal.n.w("mSuperVolume");
                    view10 = null;
                }
                int i6 = i4;
                int i7 = i5;
                VolumeShowHideAnimator volumeShowHideAnimator = this.this$0;
                view10.setScaleX(f2);
                view10.setScaleY(f2);
                int height3 = (i6 - i7) - view10.getHeight();
                View view11 = volumeShowHideAnimator.mVolumeContainer;
                if (view11 == null) {
                    kotlin.jvm.internal.n.w("mVolumeContainer");
                    view11 = null;
                }
                float f3 = 1;
                view10.setTranslationY(((view11.getHeight() / 2) + height3 + (view10.getHeight() / 2)) * (f3 - f2));
                View[] viewArr2 = this.this$0.mRingerBtnLayouts;
                if (viewArr2 == null) {
                    kotlin.jvm.internal.n.w("mRingerBtnLayouts");
                    viewArr2 = null;
                }
                VolumeShowHideAnimator volumeShowHideAnimator2 = this.this$0;
                int i8 = i3;
                int i9 = measuredHeight;
                int length = viewArr2.length;
                int i10 = 0;
                int i11 = 0;
                while (i10 < length) {
                    View view12 = viewArr2[i10];
                    int i12 = i11 + 1;
                    if (i11 == 0) {
                        View view13 = volumeShowHideAnimator2.mVolumeContainer;
                        if (view13 == null) {
                            kotlin.jvm.internal.n.w("mVolumeContainer");
                            view13 = null;
                        }
                        height = (view13.getHeight() / 2) + i8;
                        View[] viewArr3 = volumeShowHideAnimator2.mRingerBtnLayouts;
                        if (viewArr3 == null) {
                            kotlin.jvm.internal.n.w("mRingerBtnLayouts");
                            viewArr3 = null;
                        }
                        height2 = viewArr3[0].getHeight() / 2;
                    } else {
                        View view14 = volumeShowHideAnimator2.mVolumeContainer;
                        if (view14 == null) {
                            kotlin.jvm.internal.n.w("mVolumeContainer");
                            view14 = null;
                        }
                        int height4 = (view14.getHeight() / 2) + i8;
                        View[] viewArr4 = volumeShowHideAnimator2.mRingerBtnLayouts;
                        if (viewArr4 == null) {
                            kotlin.jvm.internal.n.w("mRingerBtnLayouts");
                            viewArr4 = null;
                        }
                        height = height4 + viewArr4[0].getHeight() + i9;
                        View[] viewArr5 = volumeShowHideAnimator2.mRingerBtnLayouts;
                        if (viewArr5 == null) {
                            kotlin.jvm.internal.n.w("mRingerBtnLayouts");
                            viewArr5 = null;
                        }
                        height2 = viewArr5[1].getHeight() / 2;
                    }
                    view12.setTranslationY((height + height2) * (f2 - f3));
                    i10++;
                    i11 = i12;
                }
                return new UpdateResult(false, f2);
            }
        });
        arrayList.add(animViewConfig);
        View[] viewArr2 = this.mRingerBtnLayouts;
        if (viewArr2 == null) {
            kotlin.jvm.internal.n.w("mRingerBtnLayouts");
            viewArr2 = null;
        }
        int length = viewArr2.length;
        int i6 = 0;
        final int i7 = 0;
        while (i6 < length) {
            int i8 = i7 + 1;
            AnimViewConfig animViewConfig2 = new AnimViewConfig(viewArr2[i6], this.listener);
            if (z2) {
                ViewArgs viewArgs8 = new ViewArgs(new float[]{0.95f, 0.25f}, 0L, 2, null);
                str = str2;
                viewArgs8.setDelayX((long) ((i7 == 0 ? 0.03d : 0.05d) * ((double) 1000)));
                animViewConfig2.setViewArgs(viewArgs8);
                viewArr = viewArr2;
                i2 = length;
            } else {
                str = str2;
                viewArr = viewArr2;
                i2 = length;
                animViewConfig2.setViewArgs(new ViewArgs(new float[]{1.1f, (float) (((double) 0.2f) - (((double) i7) * 0.01d))}, 0L, 2, null));
            }
            ViewArgs viewArgs9 = animViewConfig2.getViewArgs();
            View view8 = this.mVolumeView;
            if (view8 == null) {
                kotlin.jvm.internal.n.w("mVolumeView");
                view8 = null;
            }
            viewArgs9.setFX(view8.getX() + animViewConfig2.getTarget().getX());
            ViewArgs viewArgs10 = this.centerArgs;
            if (viewArgs10 == null) {
                kotlin.jvm.internal.n.w(str);
                viewArgs10 = null;
            }
            viewArgs9.setTX(viewArgs10.getTX());
            viewArgs9.setFScale(animViewConfig2.getTarget().getScaleX());
            ViewArgs viewArgs11 = this.centerArgs;
            if (viewArgs11 == null) {
                kotlin.jvm.internal.n.w(str);
                viewArgs11 = null;
            }
            viewArgs9.setTScale(viewArgs11.getTScale());
            if (z2) {
                this.ringerBtnLayoutsX[i7] = Float.valueOf(animViewConfig2.getViewArgs().getFX());
            }
            animViewConfig2.addUpdateCallback(animViewConfig2.propertyName(animViewConfig2.getX()), new UpdateCallback() { // from class: com.android.systemui.miui.volume.VolumeShowHideAnimator$expanded$1$configBtnIndex$1$3
                @Override // com.android.systemui.miui.volume.UpdateCallback
                public UpdateResult callback(float f2) {
                    this.this$0.ringerBtnLayoutsX[i7] = Float.valueOf(f2);
                    this.this$0.setViewX();
                    return new UpdateResult(false, f2);
                }
            });
            arrayList.add(animViewConfig2);
            i6++;
            length = i2;
            i7 = i8;
            viewArr2 = viewArr;
            str2 = str;
        }
        AnimViewConfig[] animViewConfigArr = (AnimViewConfig[]) arrayList.toArray(new AnimViewConfig[0]);
        startAnim((AnimViewConfig[]) Arrays.copyOf(animViewConfigArr, animViewConfigArr.length));
    }

    private final void onAnimBegin() {
        if (this.mIsAnimating) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.startTime;
        Log.e(VolumeShowHideAnimatorKt.TAG, "listener_onAnimBegin:" + this.mExpanded + ", beginTime: " + jCurrentTimeMillis);
        setMIsAnimating(true);
        TransitionListener transitionListener = this.mCallback;
        if (transitionListener != null) {
            transitionListener.onBegin("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAnimComplete() {
        if (this.mIsAnimating) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.startTime;
            Log.e(VolumeShowHideAnimatorKt.TAG, "listener_onAnimComplete:" + this.mExpanded + ", " + jCurrentTimeMillis);
            setMIsAnimating(false);
            TransitionListener transitionListener = this.mCallback;
            if (transitionListener != null) {
                transitionListener.onComplete("");
            }
        }
    }

    private final void setMIsAnimating(boolean z2) {
        Log.i(VolumeShowHideAnimatorKt.TAG, "mIsAnimating_set:" + this.mIsAnimating + ", " + z2);
        this.mIsAnimating = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewX() {
        View view = this.mVolumeView;
        View[] viewArr = null;
        if (view == null) {
            kotlin.jvm.internal.n.w("mVolumeView");
            view = null;
        }
        view.setX(this.volumeX);
        View[] viewArr2 = this.mRingerBtnLayouts;
        if (viewArr2 == null) {
            kotlin.jvm.internal.n.w("mRingerBtnLayouts");
        } else {
            viewArr = viewArr2;
        }
        int length = viewArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            viewArr[i2].setX(this.ringerBtnLayoutsX[i3].floatValue() - this.volumeX);
            i2++;
            i3++;
        }
    }

    private final void startAnim(AnimViewConfig... animViewConfigArr) {
        this.animViewConfigList.clear();
        I0.r.u(this.animViewConfigList, animViewConfigArr);
        this.animViewConfigTag.clear();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        AnimConfig animConfigAddListeners = new AnimConfig().addListeners(this.listener);
        for (AnimViewConfig animViewConfig : animViewConfigArr) {
            arrayList.add(animViewConfig.propertyName(animViewConfig.getSCALE()));
            arrayList.add(Float.valueOf(animViewConfig.getViewArgs().getFScale()));
            arrayList.add(animViewConfig.propertyName(animViewConfig.getX()));
            arrayList.add(Float.valueOf(animViewConfig.getViewArgs().getFX()));
            if (animViewConfig.getViewArgs().getFScale() != animViewConfig.getViewArgs().getTScale()) {
                String strPropertyName = animViewConfig.propertyName(animViewConfig.getSCALE());
                addConfigTag$default(this, strPropertyName, null, 2, null);
                String scale = animViewConfig.getSCALE();
                float[] easeScale = animViewConfig.getViewArgs().getEaseScale();
                animConfigAddListeners.setSpecial(strPropertyName, animViewConfig.folmeConfig(scale, Arrays.copyOf(easeScale, easeScale.length)));
                arrayList2.add(strPropertyName);
                arrayList2.add(Float.valueOf(animViewConfig.getViewArgs().getTScale()));
            }
            if (animViewConfig.getViewArgs().getFX() != animViewConfig.getViewArgs().getTX()) {
                String strPropertyName2 = animViewConfig.propertyName(animViewConfig.getX());
                addConfigTag(strPropertyName2, Float.valueOf(1.0f));
                String x2 = animViewConfig.getX();
                float[] easeX = animViewConfig.getViewArgs().getEaseX();
                animConfigAddListeners.setSpecial(strPropertyName2, animViewConfig.folmeConfig(x2, Arrays.copyOf(easeX, easeX.length)));
                arrayList2.add(strPropertyName2);
                arrayList2.add(Float.valueOf(animViewConfig.getViewArgs().getTX()));
            }
        }
        Log.d(VolumeShowHideAnimatorKt.TAG, "startAnim: setToList: " + arrayList.size() + ", " + arrayList + ", toList: " + arrayList2.size() + ", " + arrayList2);
        IStateStyle iStateStyle = this.anim;
        Object[] array = arrayList.toArray();
        iStateStyle.setTo(Arrays.copyOf(array, array.length));
        if (arrayList2.isEmpty()) {
            onAnimComplete();
            return;
        }
        kotlin.jvm.internal.B b2 = new kotlin.jvm.internal.B(2);
        b2.b(arrayList2.toArray());
        b2.a(animConfigAddListeners);
        iStateStyle.to(b2.d(new Object[b2.c()]));
    }

    public final void cancel() {
        Log.i(VolumeShowHideAnimatorKt.TAG, "cancel:");
        setMIsAnimating(false);
        this.anim.cancel();
    }

    public final void clean() {
        Folme.clean(this);
    }

    public final TransitionListener getListener() {
        return this.listener;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final MiuiVolumeDialogMotion getMMotion() {
        return this.mMotion;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final VolumeShowHideAnimator hide(boolean z2, ViewArgs viewArgs, TransitionListener listener) {
        kotlin.jvm.internal.n.g(viewArgs, "viewArgs");
        kotlin.jvm.internal.n.g(listener, "listener");
        this.centerArgs = viewArgs;
        this.mCallback = listener;
        expanded(false, z2);
        return this;
    }

    public final boolean hideIsRunning() {
        boolean z2 = this.mIsAnimating;
        boolean z3 = z2 && !this.mExpanded;
        Log.i(VolumeShowHideAnimatorKt.TAG, "hideIsRunning: " + z3 + ", mIsAnimating: " + z2 + ", hide: " + (!this.mExpanded));
        return z3;
    }

    public final VolumeShowHideAnimator initView(View volumeView, View shadowView, View superVolume) {
        kotlin.jvm.internal.n.g(volumeView, "volumeView");
        kotlin.jvm.internal.n.g(shadowView, "shadowView");
        kotlin.jvm.internal.n.g(superVolume, "superVolume");
        View viewFindViewById = volumeView.findViewById(R.id.volume_dialog_container);
        kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
        this.mVolumeContainer = viewFindViewById;
        View viewFindViewById2 = volumeView.findViewById(R.id.miui_volume_ringer_layout);
        kotlin.jvm.internal.n.f(viewFindViewById2, "findViewById(...)");
        this.mRingerLayout = viewFindViewById2;
        View viewFindViewById3 = volumeView.findViewById(R.id.miui_volume_ringer_divider);
        kotlin.jvm.internal.n.f(viewFindViewById3, "findViewById(...)");
        this.mRingerDivider = viewFindViewById3;
        View viewFindViewById4 = volumeView.findViewById(R.id.ringer_layout);
        kotlin.jvm.internal.n.f(viewFindViewById4, "findViewById(...)");
        View viewFindViewById5 = volumeView.findViewById(R.id.dnd_layout);
        kotlin.jvm.internal.n.f(viewFindViewById5, "findViewById(...)");
        this.mRingerBtnLayouts = new View[]{viewFindViewById4, viewFindViewById5};
        this.mVolumeView = volumeView;
        this.mShadowView = shadowView;
        this.mSuperVolume = superVolume;
        return this;
    }

    public final boolean isRunning() {
        boolean z2 = this.mIsAnimating;
        Log.i(VolumeShowHideAnimatorKt.TAG, "isRunning: " + z2 + ", mExpanded: " + this.mExpanded);
        return z2;
    }

    public final void setListener(TransitionListener transitionListener) {
        kotlin.jvm.internal.n.g(transitionListener, "<set-?>");
        this.listener = transitionListener;
    }

    public final void setStartTime(long j2) {
        this.startTime = j2;
    }

    public final VolumeShowHideAnimator show(ViewArgs viewArgs, TransitionListener listener) {
        kotlin.jvm.internal.n.g(viewArgs, "viewArgs");
        kotlin.jvm.internal.n.g(listener, "listener");
        this.centerArgs = viewArgs;
        this.mCallback = listener;
        expanded(true, true);
        return this;
    }

    public final boolean showIsRunning() {
        boolean z2 = this.mIsAnimating;
        boolean z3 = z2 && this.mExpanded;
        Log.i(VolumeShowHideAnimatorKt.TAG, "showIsRunning: " + z3 + ", mIsAnimating: " + z2 + ", show: " + this.mExpanded);
        return z3;
    }
}
