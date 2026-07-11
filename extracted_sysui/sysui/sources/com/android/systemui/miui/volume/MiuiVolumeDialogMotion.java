package com.android.systemui.miui.volume;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.SpringInterpolator;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.systemui.miui.ViewStateGroup;
import com.android.systemui.miui.volume.MiuiVolumeSeekBar;
import com.android.systemui.volume.VolumeDialogMotion;
import java.util.List;
import java.util.function.Supplier;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.ThemeUtils;
import miuix.animation.listener.TransitionListener;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiVolumeDialogMotion {
    private static final String TAG = "MiuiVolumeDialogMotion";
    private Callback mCallback;
    private FrameLayout mColumnsLayout;
    private Context mContext;
    private View.OnAttachStateChangeListener mDialogContentListener;
    private View mDialogContentView;
    private View.OnAttachStateChangeListener mDialogViewListener;
    private Display mDisplay;
    private View mExpandBgView;
    private View mExpandButton;
    private boolean mExpanded;
    private boolean mIsCutoutLeft;
    private View mRingerModeLayout;
    private View mShadowView;
    private AnimatorSet mSuperAnimSet;
    private View mSuperVolume;
    private boolean mSuperVolumeShowAboveTemp;
    private View.OnAttachStateChangeListener mTempColumnAttachListener;
    private FrameLayout mTempColumnContainer;
    private VolumeExpandCollapsedAnimator mVolumeExpandCollapsedAnimator;
    private MiuiVolumeSeekBar mVolumeSeekBar;
    private VolumeShowHideAnimator mVolumeShowHideAnimator;
    private View mVolumeView;
    private boolean mIsExpandButton = false;
    private int mActivePos = 0;
    private int mVisibleNumber = 1;
    private boolean mNeedShowDialog = true;
    private boolean mNeedUpdateDialogPosition = false;
    private boolean isControlCenterPanel = false;
    private boolean mRotationChanged = false;
    private int mLastRotation = -1;
    private final String TAG_SHOW = "show";
    private final String TAG_EXPAND = "expand";

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.MiuiVolumeDialogMotion$3, reason: invalid class name */
    public class AnonymousClass3 extends TransitionListener {
        final /* synthetic */ Runnable val$onComplete;

        public AnonymousClass3(Runnable runnable) {
            this.val$onComplete = runnable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onComplete$0(int i2, ValueAnimator valueAnimator) {
            if (i2 == MiuiVolumeDialogMotion.this.getShowX()) {
                MiuiVolumeDialogMotion.this.mVolumeView.setX(((Integer) valueAnimator.getAnimatedValue()).intValue());
            } else {
                MiuiVolumeDialogMotion.this.mVolumeView.setX(MiuiVolumeDialogMotion.this.getShowX());
                valueAnimator.cancel();
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onBegin(Object obj) {
            if (MiuiVolumeDialogMotion.this.mNeedShowDialog && MiuiVolumeDialogMotion.this.mCallback != null) {
                MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(true);
            }
            MiuiVolumeDialogMotion.this.mNeedUpdateDialogPosition = false;
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            if (MiuiVolumeDialogMotion.this.mNeedShowDialog) {
                if (MiuiVolumeDialogMotion.this.mCallback != null) {
                    MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(false);
                }
                this.val$onComplete.run();
            }
            MiuiVolumeDialogMotion.this.mVolumeExpandCollapsedAnimator.calculateFromViewValues(true);
            if (!MiuiVolumeDialogMotion.this.mNeedUpdateDialogPosition || MiuiVolumeDialogMotion.this.mExpanded) {
                return;
            }
            final int showX = MiuiVolumeDialogMotion.this.getShowX();
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt((int) MiuiVolumeDialogMotion.this.mVolumeView.getX(), showX);
            valueAnimatorOfInt.setDuration(200L);
            valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.volume.l
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f1499a.lambda$onComplete$0(showX, valueAnimator);
                }
            });
            valueAnimatorOfInt.start();
            MiuiVolumeDialogMotion.this.mNeedUpdateDialogPosition = false;
        }
    }

    public interface Callback extends VolumeDialogMotion.Callback {
        void onDismiss();

        void onExpandClicked();

        void onShow();

        void onStartBlurAnimation(float f2, float f3, int i2);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public MiuiVolumeDialogMotion(View view, ViewGroup viewGroup, FrameLayout frameLayout, View view2, View view3) {
        this.mContext = view.getContext();
        this.mVolumeView = view;
        this.mDialogContentView = viewGroup;
        this.mColumnsLayout = (FrameLayout) viewGroup.findViewById(R.id.volume_dialog_column_collapsed);
        this.mTempColumnContainer = frameLayout;
        this.mExpandButton = view2;
        this.mRingerModeLayout = view3;
        view2.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.miui.volume.i
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view4, MotionEvent motionEvent) {
                return this.f1496a.lambda$new$0(view4, motionEvent);
            }
        });
        if ((!BlurUtils.isLowEndDevice() && Util.isSupportBlurS()) || MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            initBlurView();
        }
        this.mVolumeShowHideAnimator = new VolumeShowHideAnimator(this.mContext, this);
        this.mVolumeExpandCollapsedAnimator = new VolumeExpandCollapsedAnimator(this.mContext, this);
    }

    private View.OnAttachStateChangeListener addBlurViewListener(View view, boolean z2, Supplier<Integer> supplier) {
        View.OnAttachStateChangeListener onAttachStateChangeListenerCreateAttachListener = createAttachListener(z2, supplier);
        view.addOnAttachStateChangeListener(onAttachStateChangeListenerCreateAttachListener);
        return onAttachStateChangeListenerCreateAttachListener;
    }

    private void clearBlurView() {
        this.mTempColumnContainer.removeOnAttachStateChangeListener(this.mTempColumnAttachListener);
        this.mDialogContentView.removeOnAttachStateChangeListener(this.mDialogContentListener);
        this.mVolumeView.removeOnAttachStateChangeListener(this.mDialogViewListener);
    }

    private void collapsedVolumePanelAnimation(boolean z2, final Runnable runnable) {
        this.mVolumeExpandCollapsedAnimator.collapse(z2 && !displayRotationChanged("collapsed"), new TransitionListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogMotion.6
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (!MiuiVolumeDialogMotion.this.mNeedShowDialog || MiuiVolumeDialogMotion.this.mCallback == null) {
                    return;
                }
                MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(true);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (MiuiVolumeDialogMotion.this.mNeedShowDialog) {
                    if (MiuiVolumeDialogMotion.this.mCallback != null) {
                        MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(false);
                        MiuiVolumeDialogMotion.this.mCallback.onDismiss();
                    }
                    runnable.run();
                }
            }
        });
    }

    private ObjectAnimator createAnimator(String str, float f2, float f3) {
        return ObjectAnimator.ofFloat(this.mSuperVolume, str, f2, f3);
    }

    private View.OnAttachStateChangeListener createAttachListener(final boolean z2, final Supplier<Integer> supplier) {
        return new View.OnAttachStateChangeListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogMotion.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                if (Util.DEBUG) {
                    Log.d(MiuiVolumeDialogMotion.TAG, "onViewAttachedToWindow");
                }
                if (!z2) {
                    Supplier supplier2 = supplier;
                    Util.setViewBlurForS(view, supplier2 == null ? 0 : ((Integer) supplier2.get()).intValue());
                    return;
                }
                View rootView = view.getRootView();
                if (!MiuiVolumeDialogMotion.this.mNeedShowDialog || rootView == null) {
                    return;
                }
                Util.setMiBgBlur(rootView, MiuiVolumeDialogRes.getBlandBlurRadius(MiuiVolumeDialogMotion.this.mContext));
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                if (Util.DEBUG) {
                    Log.d(MiuiVolumeDialogMotion.TAG, "onViewDetachedFromWindow");
                }
                view.setBackground(null);
            }
        };
    }

    private void dismissVolumePanelAnimation(boolean z2, final Runnable runnable) {
        boolean zDisplayRotationChanged = displayRotationChanged("dismiss");
        ViewArgs viewArgs = new ViewArgs();
        viewArgs.setFX(this.mVolumeView.getX());
        viewArgs.setTX(getDismissX());
        this.mVolumeShowHideAnimator.hide(z2 && !zDisplayRotationChanged, viewArgs, new TransitionListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogMotion.4
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (!MiuiVolumeDialogMotion.this.mNeedShowDialog || MiuiVolumeDialogMotion.this.mCallback == null) {
                    return;
                }
                MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(true);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (MiuiVolumeDialogMotion.this.mNeedShowDialog) {
                    if (MiuiVolumeDialogMotion.this.mCallback != null) {
                        MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(false);
                        MiuiVolumeDialogMotion.this.mCallback.onDismiss();
                    }
                    runnable.run();
                }
            }
        });
    }

    private ViewStateGroup getCollapsedStates() {
        int insetRight = getInsetRight();
        int marginRight = MiuiVolumeDialogRes.getMarginRight(this.mContext, this.mNeedShowDialog, this.mExpanded);
        boolean z2 = this.mNeedShowDialog;
        if (z2) {
            marginRight += insetRight;
        }
        ViewStateGroup.Builder builderAddState = new ViewStateGroup.Builder(this.mContext).addStateWithIntRes(this.mVolumeView.getId(), 12, R.integer.miui_volume_dialog_gravity).addStateWithIntRes(this.mVolumeView.getId(), 1, R.integer.miui_volume_dialog_gravity_collapsed).addState(this.mVolumeView.getId(), 11, 1).addState(this.mVolumeView.getId(), 6, MiuiVolumeDialogRes.getMarginTop(this.mContext, this.mNeedShowDialog, this.mExpanded, provideDisplayMetrics().heightPixels, MiuiVolumeDialogRes.getHeight(this.mContext, z2, this.mExpanded))).addState(this.mVolumeView.getId(), 7, marginRight).addState(this.mDialogContentView.getId(), 2, -2).addState(this.mDialogContentView.getId(), 3, -2).addState(this.mDialogContentView.getId(), 6, 0).addState(R.id.miui_super_volume_expanded, 10, 8).addState(this.mTempColumnContainer.getId(), 5, MiuiVolumeDialogRes.getTempColumnContainerMarginLeft(this.mContext)).addState(this.mTempColumnContainer.getId(), 6, 0).addState(this.mRingerModeLayout.getId(), 2, -2).addState(this.mRingerModeLayout.getId(), 3, -2).addState(this.mRingerModeLayout.getId(), 11, 1).addState(this.mRingerModeLayout.getId(), 9, 0);
        int id = this.mRingerModeLayout.getId();
        int i2 = R.dimen.miui_volume_footer_margin_top;
        ViewStateGroup.Builder builderAddStateWithIntDimen = builderAddState.addStateWithIntDimen(id, 6, i2);
        int i3 = R.id.miui_ringer_state_layout;
        return builderAddStateWithIntDimen.addState(i3, 2, -2).addState(i3, 3, -2).addStateWithIntDimen(R.id.miui_volume_ringer_divider, 3, i2).addStateWithIntRes(R.id.miui_ringer_btn_layout, 11, R.integer.miui_volume_ringer_layout_orientation).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDismissX() {
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.miui_volume_show_animation_offset);
        return volumeShowLeft() ? -(this.mVolumeView.getWidth() + dimension) : provideDisplayMetrics().widthPixels + dimension;
    }

    private ViewStateGroup getExpandedStates() {
        int insetRight = getInsetRight();
        int marginRight = MiuiVolumeDialogRes.getMarginRight(this.mContext, this.mNeedShowDialog, this.mExpanded);
        boolean z2 = this.mNeedShowDialog;
        if (z2) {
            marginRight += insetRight;
        }
        int bgWithContentPadding = MiuiVolumeDialogRes.getBgWithContentPadding(this.mContext, z2);
        int width = MiuiVolumeDialogRes.getWidth(this.mContext, this.mNeedShowDialog, Util.sIsNotificationSingle);
        int height = MiuiVolumeDialogRes.getHeight(this.mContext, this.mNeedShowDialog, this.mExpanded);
        int volumeViewWidth = marginRight + bgWithContentPadding;
        if (shouldHorizontalCenter()) {
            volumeViewWidth = ((provideDisplayMetrics().widthPixels - width) / 2) + bgWithContentPadding;
        }
        int marginTop = MiuiVolumeDialogRes.getMarginTop(this.mContext, this.mNeedShowDialog, this.mExpanded, provideDisplayMetrics().heightPixels, height);
        if (!this.mNeedShowDialog && CommonUtils.isLayoutRtl(this.mContext)) {
            volumeViewWidth = (provideDisplayMetrics().widthPixels - MiuiVolumeDialogRes.getVolumeViewWidth(this.mContext, this.mNeedShowDialog, Util.sIsNotificationSingle)) - volumeViewWidth;
        }
        int ringerModeLayoutWidth = MiuiVolumeDialogRes.getRingerModeLayoutWidth(this.mContext, this.mNeedShowDialog, Util.sIsNotificationSingle);
        int bgWithContentPadding2 = MiuiVolumeDialogRes.getBgWithContentPadding(this.mContext, this.mNeedShowDialog);
        int ringerModeLayoutMarginTop = MiuiVolumeDialogRes.getRingerModeLayoutMarginTop(this.mContext, this.mNeedShowDialog);
        ViewStateGroup.Builder builderAddState = new ViewStateGroup.Builder(this.mContext).addState(this.mVolumeView.getId(), 1, GravityCompat.END).addState(this.mVolumeView.getId(), 12, GravityCompat.END);
        int id = this.mVolumeView.getId();
        int i2 = R.integer.miui_volume_layout_orientation_expanded;
        ViewStateGroup.Builder builderAddState2 = builderAddState.addStateWithIntRes(id, 11, i2).addState(this.mVolumeView.getId(), 6, marginTop).addState(this.mVolumeView.getId(), 7, volumeViewWidth).addState(this.mDialogContentView.getId(), 2, -2).addState(this.mDialogContentView.getId(), 6, bgWithContentPadding2).addState(this.mExpandButton.getId(), 10, 8).addState(this.mTempColumnContainer.getId(), 10, 8).addState(R.id.miui_super_volume_collapsed, 10, 8).addState(this.mRingerModeLayout.getId(), 2, ringerModeLayoutWidth);
        int id2 = this.mRingerModeLayout.getId();
        int i3 = R.dimen.miui_volume_ringer_layout_height_expanded;
        ViewStateGroup.Builder builderAddState3 = builderAddState2.addStateWithIntDimen(id2, 3, i3).addStateWithIntRes(this.mRingerModeLayout.getId(), 11, i2).addState(this.mRingerModeLayout.getId(), 6, ringerModeLayoutMarginTop);
        int i4 = R.id.miui_ringer_state_layout;
        ViewStateGroup.Builder builderAddState4 = builderAddState3.addState(i4, 2, ringerModeLayoutWidth).addStateWithIntDimen(i4, 3, i3).addState(this.mRingerModeLayout.getId(), 9, 0);
        int i5 = R.id.miui_volume_ringer_divider;
        ViewStateGroup.Builder builderAddStateWithIntRes = builderAddState4.addStateWithIntDimen(i5, 2, R.dimen.miui_volume_ringer_divider_width).addState(i5, 3, MiuiVolumeDialogRes.getRingerModeLayoutDividerHeight(this.mContext, this.mNeedShowDialog)).addStateWithIntRes(R.id.miui_ringer_btn_layout, 11, R.integer.miui_volume_ringer_layout_orientation_expand).addStateWithIntRes(R.id.miui_standard_btn, 11, R.integer.miui_volume_ringer_layout_orientation);
        int i6 = R.id.ringer_layout;
        int i7 = R.integer.expand_silent_dnd_orientation;
        return builderAddStateWithIntRes.addStateWithIntRes(i6, 11, i7).addStateWithIntRes(R.id.dnd_layout, 11, i7).build();
    }

    private int getInsetRight() {
        Display display = this.mDisplay;
        if (display == null || display.getRotation() == 0 || this.mVolumeView.getRootWindowInsets() == null) {
            return 0;
        }
        return (this.mDisplay.getRotation() != 3 || this.mVolumeView.getRootWindowInsets().getDisplayCutout() == null) ? this.mVolumeView.getRootWindowInsets().getStableInsetRight() : this.mVolumeView.getRootWindowInsets().getDisplayCutout().getSafeInsetRight();
    }

    private int getSeekbarMarginRight() {
        return (FlipUtils.isFlipTiny() && this.mVisibleNumber == 2 && !isLandscape()) ? this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_offset_end_with_temp) : MiuiVolumeDialogRes.getMarginRight(this.mContext, this.mNeedShowDialog, this.mExpanded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getShowX() {
        int seekbarMarginRight;
        if (volumeShowLeft()) {
            seekbarMarginRight = getSeekbarMarginRight();
        } else {
            int width = VolumeColumnRes.getWidth(this.mContext);
            int i2 = provideDisplayMetrics().widthPixels;
            int i3 = this.mVisibleNumber;
            int tempColumnContainerMarginLeft = i2 - (width * i3);
            if (i3 == 2) {
                tempColumnContainerMarginLeft -= MiuiVolumeDialogRes.getTempColumnContainerMarginLeft(this.mContext);
            }
            seekbarMarginRight = tempColumnContainerMarginLeft - getSeekbarMarginRight();
        }
        return isLandscape() ? seekbarMarginRight - getInsetRight() : seekbarMarginRight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getVolumeRadius() {
        return VolumeColumnRes.getRadius(this.mContext, this.mNeedShowDialog, Util.sIsNotificationSingle);
    }

    private void initBlurView() {
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            this.mDialogViewListener = addBlurViewListener(this.mVolumeView, true, null);
        } else {
            this.mTempColumnAttachListener = addBlurViewListener(this.mTempColumnContainer, false, new Supplier() { // from class: com.android.systemui.miui.volume.j
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(this.f1497a.getVolumeRadius());
                }
            });
            this.mDialogContentListener = addBlurViewListener(this.mDialogContentView, false, new Supplier() { // from class: com.android.systemui.miui.volume.j
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(this.f1497a.getVolumeRadius());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.mIsExpandButton = true;
        MiuiVolumeSeekBar miuiVolumeSeekBar = this.mVolumeSeekBar;
        if (miuiVolumeSeekBar == null) {
            return false;
        }
        miuiVolumeSeekBar.doClick();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processExpandTouch$1() {
        if (this.mIsExpandButton) {
            this.mVolumeExpandCollapsedAnimator.calculateFromViewValues(true);
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onExpandClicked();
            }
            this.mIsExpandButton = false;
        }
    }

    private void processExpandTouch() {
        if (this.mExpanded) {
            return;
        }
        View childAt = this.mColumnsLayout.getChildAt(this.mActivePos);
        if (childAt == null) {
            Log.w(TAG, "processExpandTouch get active column failed");
            return;
        }
        MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) childAt.findViewById(R.id.volume_column_slider);
        this.mVolumeSeekBar = miuiVolumeSeekBar;
        miuiVolumeSeekBar.setSeekBarOnclickListener(new MiuiVolumeSeekBar.SeekBarOnclickListener() { // from class: com.android.systemui.miui.volume.k
            @Override // com.android.systemui.miui.volume.MiuiVolumeSeekBar.SeekBarOnclickListener
            public final void onClick() {
                this.f1498a.lambda$processExpandTouch$1();
            }
        });
    }

    private boolean shouldHorizontalCenter() {
        return (Util.isLargeDisplay(this.mContext) || this.mNeedShowDialog || isLandscape()) ? false : true;
    }

    private boolean shouldVerticalCenter() {
        return !this.mNeedShowDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVolumePanelAnimation(Runnable runnable) {
        displayRotationChanged("show");
        ViewArgs viewArgs = new ViewArgs();
        viewArgs.setFX(getDismissX());
        viewArgs.setTX(getShowX());
        this.mVolumeShowHideAnimator.show(viewArgs, new AnonymousClass3(runnable));
    }

    private void updateExpandBgSize() {
        View view = this.mExpandBgView;
        if (view != null) {
            Util.setRoundRect(view, MiuiVolumeDialogRes.getBgRadius(this.mContext));
            int marginRight = MiuiVolumeDialogRes.getMarginRight(this.mContext, this.mNeedShowDialog, this.mExpanded);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mExpandBgView.getLayoutParams();
            marginLayoutParams.width = MiuiVolumeDialogRes.getWidth(this.mContext, this.mNeedShowDialog, Util.sIsNotificationSingle);
            marginLayoutParams.height = MiuiVolumeDialogRes.getHeight(this.mContext, this.mNeedShowDialog, this.mExpanded);
            int insetRight = marginRight + (this.mNeedShowDialog ? getInsetRight() : 0);
            if (shouldHorizontalCenter()) {
                insetRight = (provideDisplayMetrics().widthPixels - marginLayoutParams.width) / 2;
            }
            int marginTop = MiuiVolumeDialogRes.getMarginTop(this.mContext, this.mNeedShowDialog, this.mExpanded, provideDisplayMetrics().heightPixels, marginLayoutParams.height);
            if (!this.mNeedShowDialog && CommonUtils.isLayoutRtl(this.mContext)) {
                insetRight = (provideDisplayMetrics().widthPixels - marginLayoutParams.width) - insetRight;
            }
            marginLayoutParams.topMargin = marginTop;
            marginLayoutParams.setMarginEnd(insetRight);
            this.mExpandBgView.setLayoutParams(marginLayoutParams);
        }
    }

    private void updateExpandBgState() {
        if (this.mExpandBgView == null || !this.mExpanded) {
            return;
        }
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            Util.setMiViewBlurAndBlendColor(this.mExpandBgView, 1, MiuiVolumeDialogRes.getBgBlandColor(this.mNeedShowDialog));
        } else if (this.mNeedShowDialog && !BlurUtils.isLowEndDevice() && ThemeUtils.INSTANCE.getDefaultPluginTheme()) {
            Util.setViewBlurForS(this.mExpandBgView, MiuiVolumeDialogRes.getBgRadius(this.mContext));
            Drawable background = this.mExpandBgView.getBackground();
            if (background instanceof BackgroundBlurDrawable) {
                background.setAlpha(0);
            }
        } else {
            this.mExpandBgView.setBackgroundResource(MiuiVolumeDialogRes.getBgRes(this.mNeedShowDialog));
        }
        updateExpandBgSize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSuperVolumeState() {
        View view = this.mSuperVolume;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.topMargin = MiuiVolumeDialogRes.getSuperVolumeMarginTop(this.mContext, this.mNeedShowDialog, this.mExpanded, provideDisplayMetrics().heightPixels);
            marginLayoutParams.setMarginEnd(MiuiVolumeDialogRes.getSuperVolumeMarginEnd(this.mContext, getInsetRight()));
            this.mSuperVolume.setLayoutParams(marginLayoutParams);
        }
    }

    public void cancelShowAnimation() {
        this.mVolumeShowHideAnimator.cancel();
    }

    public void clean() {
        VolumeShowHideAnimator volumeShowHideAnimator = this.mVolumeShowHideAnimator;
        if (volumeShowHideAnimator != null) {
            volumeShowHideAnimator.clean();
        }
        VolumeExpandCollapsedAnimator volumeExpandCollapsedAnimator = this.mVolumeExpandCollapsedAnimator;
        if (volumeExpandCollapsedAnimator != null) {
            volumeExpandCollapsedAnimator.clean();
        }
        AnimatorSet animatorSet = this.mSuperAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    public void destroy() {
        if (Util.isSupportBlurS()) {
            clearBlurView();
        }
    }

    public void dismissVolumePanel(boolean z2, Runnable runnable) {
        Log.i(TAG, "dismissVolumePanel: mExpanded: " + this.mExpanded + ", isAnim: " + z2 + ", isShown: " + this.mVolumeView.isShown() + ", mNeedShowDialog: " + this.mNeedShowDialog);
        if (this.mVolumeView.isShown()) {
            if (this.mExpanded) {
                collapsedVolumePanelAnimation(z2, runnable);
            } else {
                dismissVolumePanelAnimation(z2, runnable);
            }
        }
    }

    public boolean displayRotationChanged(String str) {
        if (str.equals("show") || str.equals("expand")) {
            setVolumeDialogVisible(true, str);
        }
        Display display = this.mDisplay;
        if (display == null) {
            return false;
        }
        int rotation = display.getRotation();
        boolean z2 = rotation != this.mLastRotation;
        Log.d(TAG, "displayRotationChanged_" + str + ": " + this.mLastRotation + " ---> " + rotation + " --> " + z2 + ", " + this.mRotationChanged);
        this.mLastRotation = rotation;
        return z2;
    }

    public void expandVolumePanelAnimation() {
        updateShadowState();
        this.mVolumeView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogMotion.5
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                MiuiVolumeDialogMotion.this.mVolumeView.getViewTreeObserver().removeOnPreDrawListener(this);
                if (MiuiVolumeDialogMotion.this.mVolumeExpandCollapsedAnimator.isRunning()) {
                    return true;
                }
                MiuiVolumeDialogMotion.this.mVolumeExpandCollapsedAnimator.calculateToViewValues();
                MiuiVolumeDialogMotion.this.mVolumeExpandCollapsedAnimator.expand(!MiuiVolumeDialogMotion.this.displayRotationChanged("expand"), new TransitionListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogMotion.5.1
                    @Override // miuix.animation.listener.TransitionListener
                    public void onBegin(Object obj) {
                        if (!MiuiVolumeDialogMotion.this.mNeedShowDialog || MiuiVolumeDialogMotion.this.mCallback == null) {
                            return;
                        }
                        MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(true);
                    }

                    @Override // miuix.animation.listener.TransitionListener
                    public void onComplete(Object obj) {
                        if (!MiuiVolumeDialogMotion.this.mNeedShowDialog || MiuiVolumeDialogMotion.this.mCallback == null) {
                            return;
                        }
                        MiuiVolumeDialogMotion.this.mCallback.onAnimatingChanged(false);
                    }
                });
                return true;
            }
        });
    }

    public int getDisplayWidthPixels() {
        return provideDisplayMetrics().widthPixels;
    }

    public boolean getSuperVolumeShowAboveTemp() {
        return this.mSuperVolumeShowAboveTemp;
    }

    public boolean isAnimating() {
        return this.mVolumeShowHideAnimator.isRunning() || this.mVolumeExpandCollapsedAnimator.isRunning();
    }

    public boolean isDualVolume() {
        return this.mVisibleNumber == 2;
    }

    public boolean isExpandCollapsedAnimating() {
        return this.mVolumeExpandCollapsedAnimator.isRunning();
    }

    public boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public boolean isShowAnimating() {
        return this.mVolumeShowHideAnimator.showIsRunning();
    }

    public boolean isSuperAnimating() {
        AnimatorSet animatorSet = this.mSuperAnimSet;
        return animatorSet != null && animatorSet.isRunning();
    }

    public DisplayMetrics provideDisplayMetrics() {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    public void setActiveStreamPosition(int i2, int i3) {
        Log.d(TAG, "setActiveStreamPosition: pos=" + i2 + ", num=" + i3);
        this.mActivePos = i2;
        if (this.mVisibleNumber != i3 && this.mVolumeShowHideAnimator.showIsRunning()) {
            this.mNeedUpdateDialogPosition = true;
        }
        this.mVisibleNumber = i3;
        processExpandTouch();
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setContentView(View view, View view2) {
        View viewFindViewById = this.mVolumeView.getRootView().findViewById(R.id.shadow);
        this.mShadowView = viewFindViewById;
        this.mExpandBgView = view;
        this.mSuperVolume = view2;
        this.mVolumeShowHideAnimator.initView(this.mVolumeView, viewFindViewById, view2);
        this.mVolumeExpandCollapsedAnimator.initView(this.mVolumeView, this.mExpandBgView, this.mShadowView, this.mSuperVolume, this.mRingerModeLayout);
    }

    public void setControlCenterPanel(boolean z2) {
        this.isControlCenterPanel = z2;
    }

    public void setDisplay(Display display) {
        this.mDisplay = display;
        displayRotationChanged("init");
        if (display != null) {
            updateRotation();
            updateStates(false);
        }
    }

    public void setNeedShowDialog(boolean z2) {
        if (this.mNeedShowDialog != z2) {
            this.mNeedShowDialog = z2;
        }
    }

    public void setVolumeColumns(List<VolumeColumn> list) {
        this.mVolumeExpandCollapsedAnimator.setVolumeColumns(list);
    }

    public void setVolumeDialogVisible(boolean z2, String str) {
        View view = this.mVolumeView;
        if (view == null || view.getParent() == null) {
            return;
        }
        ((View) this.mVolumeView.getParent()).setVisibility(z2 ? 0 : 4);
        Log.i(TAG, "setVolumeDialogVisible: " + str + ", " + z2);
    }

    public void showVolumePanel(final Runnable runnable) {
        processExpandTouch();
        if (this.mVolumeShowHideAnimator.showIsRunning()) {
            return;
        }
        if (this.mVolumeShowHideAnimator.hideIsRunning()) {
            this.mVolumeShowHideAnimator.cancel();
            showVolumePanelAnimation(runnable);
        } else {
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.onShow();
            }
            this.mVolumeView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogMotion.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    MiuiVolumeDialogMotion.this.mVolumeView.getViewTreeObserver().removeOnPreDrawListener(this);
                    MiuiVolumeDialogMotion.this.updateShadowState();
                    MiuiVolumeDialogMotion.this.updateSuperVolumeState();
                    MiuiVolumeDialogMotion.this.mRingerModeLayout.setPadding(0, 0, 0, 0);
                    View viewFindViewById = MiuiVolumeDialogMotion.this.mRingerModeLayout.findViewById(R.id.miui_ringer_state_layout);
                    viewFindViewById.setAlpha(1.0f);
                    viewFindViewById.setScaleX(1.0f);
                    viewFindViewById.setScaleY(1.0f);
                    float dismissX = MiuiVolumeDialogMotion.this.getDismissX();
                    MiuiVolumeDialogMotion.this.mShadowView.setX(dismissX);
                    MiuiVolumeDialogMotion.this.mShadowView.setAlpha(0.0f);
                    MiuiVolumeDialogMotion.this.mVolumeView.setX(dismissX);
                    MiuiVolumeDialogMotion.this.mSuperVolume.setAlpha(0.0f);
                    MiuiVolumeDialogMotion.this.mVolumeExpandCollapsedAnimator.calculateHideViewValues();
                    MiuiVolumeDialogMotion.this.showVolumePanelAnimation(runnable);
                    return true;
                }
            });
        }
    }

    public void startSuperVolumeAnimation(boolean z2) {
        TimeInterpolator springInterpolator = new SpringInterpolator(0.7f, 0.4f);
        TimeInterpolator springInterpolator2 = new SpringInterpolator(0.9f, 0.2f);
        ObjectAnimator objectAnimatorCreateAnimator = createAnimator("alpha", z2 ? 0.0f : 1.0f, z2 ? 1.0f : 0.0f);
        ObjectAnimator objectAnimatorCreateAnimator2 = createAnimator("scaleX", z2 ? 0.6f : 1.0f, z2 ? 1.0f : 0.6f);
        ObjectAnimator objectAnimatorCreateAnimator3 = createAnimator("scaleY", z2 ? 0.6f : 1.0f, z2 ? 1.0f : 0.6f);
        objectAnimatorCreateAnimator.setInterpolator(springInterpolator2);
        objectAnimatorCreateAnimator2.setInterpolator(z2 ? springInterpolator : springInterpolator2);
        if (!z2) {
            springInterpolator = springInterpolator2;
        }
        objectAnimatorCreateAnimator3.setInterpolator(springInterpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mSuperAnimSet = animatorSet;
        animatorSet.playTogether(objectAnimatorCreateAnimator, objectAnimatorCreateAnimator2, objectAnimatorCreateAnimator3);
        this.mSuperAnimSet.start();
    }

    public void updateRotation() {
        if (FlipUtils.isFlipTiny()) {
            this.mIsCutoutLeft = FlipUtils.isCutoutLeft(this.mDisplay);
        }
    }

    public void updateShadowState() {
        if (!this.mNeedShowDialog || this.mShadowView == null) {
            return;
        }
        boolean z2 = this.mRingerModeLayout.getVisibility() == 0;
        this.mShadowView.setBackgroundResource(MiuiVolumeDialogRes.getShadowResource(this.mExpanded, isDualVolume(), z2));
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mExpandBgView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mShadowView.getLayoutParams();
        marginLayoutParams2.width = MiuiVolumeDialogRes.getShadowWidth(this.mContext, this.mExpanded, isDualVolume(), marginLayoutParams.width);
        marginLayoutParams2.height = MiuiVolumeDialogRes.getShadowHeight(this.mContext, z2, this.mExpanded, marginLayoutParams.height, isDualVolume());
        marginLayoutParams2.topMargin = MiuiVolumeDialogRes.getShadowMarginTop(this.mContext, this.mVolumeView.getTop(), z2, this.mExpanded, marginLayoutParams.topMargin, isDualVolume());
        marginLayoutParams2.setMarginEnd(MiuiVolumeDialogRes.getShadowMarginEnd(this.mContext, this.mExpanded, marginLayoutParams.getMarginEnd(), marginLayoutParams.width, this.mVolumeView.getWidth(), ((ViewGroup.MarginLayoutParams) this.mVolumeView.getLayoutParams()).getMarginEnd(), isDualVolume()));
        this.mShadowView.setTranslationX(0.0f);
        this.mShadowView.setLayoutParams(marginLayoutParams2);
    }

    public void updateStateToExpand(boolean z2) {
        Log.d(TAG, "updateStateToExpand: expand: " + z2 + ", mNeedShowDialog: " + this.mNeedShowDialog);
        this.mExpanded = z2;
        if (!z2) {
            getCollapsedStates().apply((ViewGroup) this.mVolumeView);
            return;
        }
        getExpandedStates().apply((ViewGroup) this.mVolumeView);
        updateExpandBgState();
        if (this.mNeedShowDialog) {
            expandVolumePanelAnimation();
        }
    }

    public void updateStates(boolean z2) {
        Log.d(TAG, "updateStates: " + z2 + ", expand: " + this.mExpanded);
        this.mRotationChanged = z2;
        if (this.mExpanded) {
            getExpandedStates().apply((ViewGroup) this.mVolumeView);
        } else {
            getCollapsedStates().apply((ViewGroup) this.mVolumeView);
        }
    }

    public void updateSuperVolumeShowX(boolean z2) {
        this.mSuperVolumeShowAboveTemp = z2;
    }

    public void updateSuperVolumeVisibility(boolean z2, boolean z3) {
        boolean z4 = false;
        boolean z5 = this.mSuperVolume.getVisibility() == 0;
        if (!z3 && z2) {
            z4 = true;
        }
        if (z4 && !z5 && !isShowAnimating()) {
            startSuperVolumeAnimation(true);
        }
        Util.setVisOrGone(this.mSuperVolume, z4);
        if (z5 != z4) {
            updateShadowState();
        }
    }

    public void updateVolumePanelSize() {
        updateExpandBgSize();
        updateStates(false);
    }

    public boolean volumeShowLeft() {
        return FlipUtils.isFlipTiny() && this.mIsCutoutLeft;
    }
}
