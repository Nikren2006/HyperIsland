package com.android.systemui.miui.volume;

import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import com.android.systemui.miui.volume.MiuiVolumeDialogMotion;
import com.android.systemui.miui.volume.VolumePanelDialog;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.MiBlurCompat;
import miuix.view.animation.QuadraticEaseOutInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public class VolumePanelDialogController {
    private static final String EXTRA_IS_END = "IsEnd";
    private static final Interpolator QUART_EASE_OUT = new QuadraticEaseOutInterpolator();
    private static final String TAG = "VolumePanelDialogController";
    private BlurUtils mBlurUtils;
    private Callback mCallback;
    private Context mContext;
    private boolean mExpanded;
    private boolean mPendingRecheckAll;
    protected VolumePanelDialog mVolumePanelDialog;
    private Window mWindow;
    private int mWindowType;
    private float mMiBlurMaxRadiusExpanded = 100.0f;
    protected MiuiVolumeDialogMotion.Callback mMotionCallback = new MiuiVolumeDialogMotion.Callback() { // from class: com.android.systemui.miui.volume.VolumePanelDialogController.1
        @Override // com.android.systemui.volume.VolumeDialogMotion.Callback
        public void onAnimatingChanged(boolean z2) {
            if (z2) {
                return;
            }
            if (VolumePanelDialogController.this.mCallback != null && VolumePanelDialogController.this.mCallback.getPendingState()) {
                VolumePanelDialogController.this.mCallback.stateChange();
                VolumePanelDialogController.this.mCallback.setPendingState(false);
            }
            if (VolumePanelDialogController.this.mPendingRecheckAll) {
                if (VolumePanelDialogController.this.mCallback != null) {
                    VolumePanelDialogController.this.mCallback.recheckAll();
                }
                VolumePanelDialogController.this.mPendingRecheckAll = false;
            }
        }

        @Override // com.android.systemui.miui.volume.MiuiVolumeDialogMotion.Callback
        public void onDismiss() {
            VolumePanelDialog volumePanelDialog = VolumePanelDialogController.this.mVolumePanelDialog;
            if (volumePanelDialog == null || !volumePanelDialog.isShowing()) {
                return;
            }
            Log.d(VolumePanelDialogController.TAG, "onDismiss isShowing");
            VolumePanelDialogController.this.mVolumePanelDialog.dismiss();
        }

        @Override // com.android.systemui.miui.volume.MiuiVolumeDialogMotion.Callback
        public void onExpandClicked() {
            if (VolumePanelDialogController.this.mCallback != null) {
                VolumePanelDialogController.this.mCallback.expandClicked();
            }
        }

        @Override // com.android.systemui.miui.volume.MiuiVolumeDialogMotion.Callback
        public void onShow() {
            Log.i(VolumePanelDialogController.TAG, "onShow isShowing:" + VolumePanelDialogController.this.mVolumePanelDialog.isShowing());
            if (VolumePanelDialogController.this.mVolumePanelDialog.isShowing()) {
                return;
            }
            VolumePanelDialogController.this.setOutsideTouchFlag(true);
            VolumePanelDialogController.this.mVolumePanelDialog.show();
        }

        @Override // com.android.systemui.miui.volume.MiuiVolumeDialogMotion.Callback
        public void onStartBlurAnimation(float f2, float f3, int i2) {
            VolumePanelDialogController.this.startBlurAnim(f2, f3, i2);
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.miui.volume.VolumePanelDialogController.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (VolumeUtil.LONG_SCREENSHOT.equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra(VolumePanelDialogController.EXTRA_IS_END, false);
                WindowManager.LayoutParams attributes = VolumePanelDialogController.this.mWindow.getAttributes();
                if (booleanExtra) {
                    attributes.extraFlags &= -8388609;
                } else {
                    attributes.extraFlags = 8388608;
                }
            }
        }
    };
    public VolumePanelDialog.DialogEventListener mDialogEventListener = new VolumePanelDialog.DialogEventListener() { // from class: com.android.systemui.miui.volume.VolumePanelDialogController.3
        @Override // com.android.systemui.miui.volume.VolumePanelDialog.DialogEventListener
        public void dismiss(int i2) {
            if (VolumePanelDialogController.this.mCallback != null) {
                VolumePanelDialogController.this.setOutsideTouchFlag(false);
                VolumePanelDialogController.this.mCallback.dismiss(i2);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialog.DialogEventListener
        public void onStop() {
            if (VolumePanelDialogController.this.mCallback != null) {
                boolean zIsAnimating = VolumePanelDialogController.this.mCallback.isAnimating();
                if (Util.DEBUG) {
                    Log.d(VolumePanelDialogController.TAG, "onStop animating=" + zIsAnimating);
                }
                if (zIsAnimating) {
                    VolumePanelDialogController.this.mPendingRecheckAll = true;
                } else {
                    VolumePanelDialogController.this.mCallback.recheckAll();
                }
            }
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialog.DialogEventListener
        public void rescheduleTimeout() {
            if (VolumePanelDialogController.this.mCallback != null) {
                VolumePanelDialogController.this.mCallback.rescheduleTimeout();
            }
        }
    };

    public interface Callback {
        void dismiss(int i2);

        void expandClicked();

        boolean getPendingState();

        View getVolumeRootView();

        boolean isAnimating();

        void recheckAll();

        void rescheduleTimeout();

        void setPendingState(boolean z2);

        void stateChange();
    }

    public VolumePanelDialogController(Context context) {
        this.mContext = context;
        this.mBlurUtils = new BlurUtils(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startBlurAnim$0(float f2, ValueAnimator valueAnimator) {
        try {
            Float f3 = (Float) valueAnimator.getAnimatedValue();
            float fFloatValue = f3.floatValue();
            View volumeRootView = this.mCallback.getVolumeRootView();
            if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
                setMiBgBlurForExpanded(volumeRootView, fFloatValue, f2);
            } else {
                this.mBlurUtils.setBackgroundBlur(volumeRootView, f3, this.mWindow);
            }
        } catch (Exception e2) {
            Log.e(TAG, "updateBlurRatio error.", e2);
        }
    }

    private void setMiBgBlurForExpanded(View view, float f2, float f3) {
        if (view != null) {
            if (f3 != 0.0f) {
                Util.setMiBgBlur(view, Util.blurRadiusOfRatio(f2, this.mMiBlurMaxRadiusExpanded));
                Util.setMiViewBlurAndBlendColor(view, 1, this.mContext.getResources().getIntArray(R.array.miui_expanded_bg_blend_colors));
                return;
            }
            MiBlurCompat.setMiBackgroundBlurRadiusCompat(view, Util.blurRadiusOfRatio(f2, this.mMiBlurMaxRadiusExpanded));
            if (f2 == 0.0f) {
                MiBlurCompat.setMiBackgroundBlurModeCompat(view, 0);
                MiBlurCompat.setMiViewBlurModeCompat(view, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOutsideTouchFlag(boolean z2) {
        if (z2) {
            this.mWindow.addFlags(262144);
        } else {
            this.mWindow.clearFlags(262144);
        }
    }

    private void setupWindowAttributes() {
        this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mWindow.addFlags(17072160);
        this.mWindow.clearFlags(131072);
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        attributes.type = this.mWindowType;
        attributes.extraFlags = 2 & (-8388609);
        this.mWindow.addPrivateFlags(Util.PRIVATE_FLAG_TRUSTED_OVERLAY);
        attributes.format = -3;
        attributes.setTitle(VolumePanelDialogController.class.getSimpleName());
        attributes.windowAnimations = -1;
        attributes.gravity = 48;
        attributes.width = -1;
        attributes.height = -1;
        attributes.layoutInDisplayCutoutMode = 1;
        updateWindow(false);
        this.mWindow.setAttributes(attributes);
        this.mWindow.setSoftInputMode(48);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBlurAnim(float f2, final float f3, int i2) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f3);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.volume.s
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f1507a.lambda$startBlurAnim$0(f3, valueAnimator);
            }
        });
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.setInterpolator(QUART_EASE_OUT);
        valueAnimatorOfFloat.start();
    }

    public void addCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void clearDialogNotFocusable() {
        Window window = this.mWindow;
        if (window == null || (window.getAttributes().flags & 8) == 0) {
            return;
        }
        Log.i(TAG, "VolumePanelDialog clearDialogNotFocusable");
        this.mWindow.clearFlags(8);
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        VolumePanelDialog volumePanelDialog = this.mVolumePanelDialog;
        if (volumePanelDialog != null) {
            volumePanelDialog.setDialogEventListener(null);
        }
        removeCallback();
    }

    public void init(int i2, VolumePanelView volumePanelView) {
        this.mWindowType = i2;
        initDialog(volumePanelView);
        setDialogNotFocusable();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(VolumeUtil.LONG_SCREENSHOT);
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter, 2);
    }

    public void initDialog(VolumePanelView volumePanelView) {
        Log.d(TAG, "initDialog");
        VolumePanelDialog volumePanelDialog = this.mVolumePanelDialog;
        if (volumePanelDialog != null && volumePanelDialog.isShowing()) {
            this.mVolumePanelDialog.dismiss();
        }
        this.mMiBlurMaxRadiusExpanded = this.mContext.getResources().getDimensionPixelSize(R.dimen.mi_blur_max_radius_expanded);
        VolumePanelDialog volumePanelDialog2 = new VolumePanelDialog(this.mContext);
        this.mVolumePanelDialog = volumePanelDialog2;
        Window window = volumePanelDialog2.getWindow();
        this.mWindow = window;
        window.requestFeature(1);
        this.mVolumePanelDialog.setCanceledOnTouchOutside(true);
        this.mVolumePanelDialog.setContentView(volumePanelView);
        this.mVolumePanelDialog.setDialogEventListener(this.mDialogEventListener);
        setupWindowAttributes();
        ((ViewGroup) this.mVolumePanelDialog.findViewById(android.R.id.content)).setClipChildren(false);
        View decorView = this.mVolumePanelDialog.getWindow().getDecorView();
        if (Util.isLargeDisplay(this.mContext)) {
            decorView.setSystemUiVisibility(5376);
        } else {
            decorView.setSystemUiVisibility(5888);
        }
    }

    public void removeCallback() {
        this.mCallback = null;
    }

    public void setDialogNotFocusable() {
        Window window = this.mWindow;
        if (window == null || (window.getAttributes().flags & 8) != 0) {
            return;
        }
        Log.i(TAG, "VolumePanelDialog setDialogNotFocusable");
        this.mWindow.addFlags(8);
    }

    public void updateExpanded(boolean z2) {
        this.mExpanded = z2;
    }

    public void updateWindow(boolean z2) {
        float fraction = this.mContext.getResources().getFraction(R.fraction.miui_volume_dim_behind_collapsed, 1, 1);
        float fraction2 = this.mContext.getResources().getFraction(R.fraction.miui_volume_dim_behind_expanded, 1, 1);
        if (this.mExpanded) {
            clearDialogNotFocusable();
        } else {
            this.mWindow.clearFlags(4);
            setDialogNotFocusable();
        }
        Window window = this.mWindow;
        if (this.mExpanded && !z2) {
            fraction = fraction2;
        }
        window.setDimAmount(fraction);
    }
}
