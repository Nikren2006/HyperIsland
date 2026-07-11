package com.android.systemui.miui.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.miui.miplay.audio.data.DeviceInfo;
import com.xiaomi.onetrack.util.aa;
import miui.systemui.util.AnimatedVectorDrawableUtils;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.ThemeUtils;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.ControlsEventTracker;
import systemui.plugin.eventtracking.trackers.VolumeEventTracker;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiRingerModeLayout extends LinearLayout {
    private static final String TAG = "RingerModeLayout";
    private boolean isBroadcastRegistered;
    private final BroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    private RingerButtonHelper mDndHelper;
    private TimerItem mDndTimer;
    private int mLockTaskModeState;
    private boolean mNeedShowDialog;
    private RingerButtonHelper mRingerHelper;
    private boolean mRingerMode;
    private TimerItem mSilentTimer;
    private Handler mWorkerHandler;
    private HandlerThread mWorkerThread;
    private boolean mZenMode;

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.MiuiRingerModeLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0(int i2) {
            MiuiRingerModeLayout miuiRingerModeLayout = MiuiRingerModeLayout.this;
            miuiRingerModeLayout.setRingerModeInternal(i2 != 2, miuiRingerModeLayout.mZenMode);
            MiuiRingerModeLayout.this.updateRemainTimeH();
            MiuiRingerModeLayout.this.updateExpandedStateH();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$1(Context context) {
            final int ringerModeInternal = ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).getRingerModeInternal();
            Log.d(MiuiRingerModeLayout.TAG, "mBroadcastReceiver onReceive mode = " + ringerModeInternal);
            MiuiRingerModeLayout.this.post(new Runnable() { // from class: com.android.systemui.miui.volume.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1492a.lambda$onReceive$0(ringerModeInternal);
                }
            });
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            MiuiRingerModeLayout.this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1490a.lambda$onReceive$1(context);
                }
            });
        }
    }

    public class RingerButtonHelper {
        private com.miui.blur.sdk.backdrop.a mBlurView;
        private boolean mExpanded;
        private ImageView mIcon;
        private boolean mIsZen;
        private boolean mLastExpanded;
        private boolean mLastState;
        private View mStandardView;
        private boolean mState;
        private View.OnAttachStateChangeListener mBlurViewAttachListener = new View.OnAttachStateChangeListener() { // from class: com.android.systemui.miui.volume.MiuiRingerModeLayout.RingerButtonHelper.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(@NonNull View view) {
                RingerButtonHelper.this.updateState();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(@NonNull View view) {
            }
        };
        View.AccessibilityDelegate standardViewAccessibilityDelegate = new View.AccessibilityDelegate() { // from class: com.android.systemui.miui.volume.MiuiRingerModeLayout.RingerButtonHelper.2
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityEvent(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 4) {
                    accessibilityEvent.setContentDescription("");
                }
            }

            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setContentDescription(view.getContext().getResources().getString(RingerButtonHelper.this.mIsZen ? RingerButtonHelper.this.mState ? R.string.miui_dnd_mode_on : R.string.miui_dnd_mode_off : RingerButtonHelper.this.mState ? R.string.miui_silent_mode_on : R.string.miui_silent_mode_off));
                accessibilityNodeInfo.setClassName(Button.class.getName());
            }
        };

        public RingerButtonHelper(View view, final boolean z2, boolean z3) {
            View viewFindViewById = view.findViewById(R.id.miui_standard_btn);
            this.mStandardView = viewFindViewById;
            viewFindViewById.setAccessibilityDelegate(this.standardViewAccessibilityDelegate);
            this.mIcon = (ImageView) this.mStandardView.findViewById(R.id.icon);
            this.mLastState = z3;
            this.mState = z3;
            this.mIsZen = z2;
            AnimatedVectorDrawableUtils.setDrawableToEnd(this.mIcon, ContextCompat.getDrawable(MiuiRingerModeLayout.this.mContext, RingerButtonRes.getIconResId(z2, z3)));
            com.miui.blur.sdk.backdrop.a aVar = (com.miui.blur.sdk.backdrop.a) view.findViewById(R.id.bg_blur);
            this.mBlurView = aVar;
            aVar.addOnAttachStateChangeListener(this.mBlurViewAttachListener);
            touchAnimation(this.mBlurView);
            this.mBlurView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.miui.volume.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f1494a.lambda$new$0(z2, view2);
                }
            });
            this.mExpanded = true;
            onExpanded(false, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(boolean z2, View view) {
            if (MiuiRingerModeLayout.this.mLockTaskModeState == 2) {
                Util.showPinningEscapeToast();
                return;
            }
            trackClickEvent(z2);
            ControlsEventTracker.trackControlCenterSecondaryVolumeQSClickEvent(EventTracker.getScreenType(MiuiRingerModeLayout.this.mContext), MiuiRingerModeLayout.this.mContext.getResources().getConfiguration().orientation, z2, this.mState);
            if (z2) {
                MiuiRingerModeLayout.this.setZenModeByUser(!this.mState);
            } else {
                MiuiRingerModeLayout.this.setRingerModeByUser(!this.mState);
            }
            Log.d(MiuiRingerModeLayout.TAG, "ringer btn click is zen = " + z2 + ", state =" + this.mState);
            MiuiRingerModeLayout.this.performHapticFeedback();
        }

        private void setBlurViewBg(Boolean bool) {
            if (bool.booleanValue()) {
                Util.setViewBlurForS(this.mBlurView, RingerButtonRes.getButtonRadius(MiuiRingerModeLayout.this.mContext, this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog));
            } else {
                this.mBlurView.setBackgroundResource(RingerButtonRes.getBlurViewBg(MiuiRingerModeLayout.this.mNeedShowDialog, this.mExpanded));
                this.mBlurView.setBlurEnabled(!this.mExpanded);
            }
        }

        private void setIcon() {
            AnimatedVectorDrawableUtils.setDrawable(this.mIcon, MiuiRingerModeLayout.this.mContext.getDrawable(RingerButtonRes.getIconResId(this.mIsZen, this.mState)));
        }

        private void startIconAnimation(ImageView imageView) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable).start();
            }
        }

        private void touchAnimation(View view) {
            Folme.useAt(view).touch().setTint(0.08f, 0.0f, 0.0f, 0.0f).handleTouchOf(view, new AnimConfig[0]);
        }

        private void trackClickEvent(boolean z2) {
            if (this.mExpanded) {
                VolumeEventTracker.trackClickExpandRingerBtn(z2);
            } else {
                VolumeEventTracker.trackClickRingerBtn(z2, this.mState);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateState() {
            Log.d(MiuiRingerModeLayout.TAG, "updateState is zen= " + this.mIsZen + ", state=" + this.mState);
            this.mStandardView.setActivated(this.mExpanded ^ true);
            this.mStandardView.setSelected(this.mState);
            if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(MiuiRingerModeLayout.this.mContext)) {
                boolean z2 = !BlurUtils.isLowEndDevice() && (Util.isSupportBlurS() || Util.isBackdropBlurSupported()) && ThemeUtils.INSTANCE.getDefaultPluginTheme();
                if (z2 && MiuiRingerModeLayout.this.mNeedShowDialog) {
                    setBlurViewBg(Boolean.TRUE);
                }
                this.mStandardView.setBackgroundResource(RingerButtonRes.getButtonBgId(this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog, z2));
            } else if (this.mLastExpanded != this.mExpanded || this.mLastState != this.mState) {
                Util.setRoundRect(this.mStandardView, RingerButtonRes.getButtonRadius(MiuiRingerModeLayout.this.mContext, this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog));
                Util.setMiViewBlurAndBlendColor(this.mStandardView, 1, RingerButtonRes.getButtonBgBlendColor(this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog, this.mState));
            }
            this.mStandardView.getContext();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mIcon.getLayoutParams();
            int iconSize = RingerButtonRes.getIconSize(MiuiRingerModeLayout.this.mContext, this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog);
            layoutParams.height = iconSize;
            layoutParams.width = iconSize;
            this.mIcon.setLayoutParams(layoutParams);
            if (this.mLastState != this.mState) {
                setIcon();
                startIconAnimation(this.mIcon);
            }
            this.mLastState = this.mState;
        }

        public void onExpanded(boolean z2, boolean z3) {
            boolean z4 = this.mExpanded;
            if (z4 != z2 || z3) {
                this.mLastExpanded = z4;
                this.mExpanded = z2;
                int width = RingerButtonRes.getWidth(MiuiRingerModeLayout.this.getContext(), this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog);
                int height = RingerButtonRes.getHeight(MiuiRingerModeLayout.this.getContext(), this.mExpanded, MiuiRingerModeLayout.this.mNeedShowDialog);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mStandardView.getLayoutParams();
                layoutParams.height = height;
                layoutParams.width = width;
                this.mStandardView.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = this.mBlurView.getLayoutParams();
                layoutParams2.width = width;
                layoutParams2.height = height;
                this.mBlurView.setLayoutParams(layoutParams2);
                setBlurViewBg(Boolean.FALSE);
            }
        }

        public void setRingerMode(boolean z2) {
            this.mState = z2;
        }
    }

    public MiuiRingerModeLayout(Context context) {
        this(context, null);
    }

    private void initTimer(View view, View view2) {
        this.mSilentTimer = new TimerItem(this.mContext, view, false, this);
        this.mDndTimer = new TimerItem(this.mContext, view2, true, this);
        updateRemainTimeH();
        this.mDndTimer.setupCountDownProgress();
        this.mSilentTimer.setupCountDownProgress();
    }

    private void initialize() {
        View viewFindViewById = findViewById(R.id.ringer_layout);
        View viewFindViewById2 = findViewById(R.id.dnd_layout);
        boolean zIsSilentMode = VolumeUtil.isSilentMode(this.mContext);
        boolean zIsZenMode = VolumeUtil.isZenMode(this.mContext);
        this.mRingerHelper = new RingerButtonHelper(viewFindViewById, false, zIsSilentMode);
        this.mDndHelper = new RingerButtonHelper(viewFindViewById2, true, zIsZenMode);
        setMotionEventSplittingEnabled(false);
        setRingerModeInternal(zIsSilentMode, zIsZenMode);
        initTimer(viewFindViewById, viewFindViewById2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performHapticFeedback$0() {
        VolumeUtil.performHapticFeedback(this, 268435458);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setRingerModeByUser$1(boolean z2) {
        VolumeUtil.setSilenceMode(this.mContext, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setSilenceMode$3() {
        updateExpandedStateH();
        updateRemainTimeH();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setZenModeByUser$2(boolean z2) {
        VolumeUtil.setZenMode(this.mContext, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performHapticFeedback() {
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f1489a.lambda$performHapticFeedback$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRingerModeByUser(final boolean z2) {
        setRingerModeInternal(z2, this.mZenMode);
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f1486a.lambda$setRingerModeByUser$1(z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRingerModeInternal(boolean z2, boolean z3) {
        this.mRingerMode = z2;
        this.mZenMode = z3;
        this.mRingerHelper.setRingerMode(z2);
        this.mDndHelper.setRingerMode(z3);
    }

    private void setTimerShowingState(boolean z2) {
        this.mDndTimer.updateShowingState(z2);
        this.mSilentTimer.updateShowingState(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setZenModeByUser(final boolean z2) {
        setRingerModeInternal(this.mRingerMode, z2);
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f1484a.lambda$setZenModeByUser$2(z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateExpandedStateH() {
        this.mRingerHelper.updateState();
        this.mDndHelper.updateState();
        this.mSilentTimer.updateCountProgressH(this.mRingerMode);
        this.mDndTimer.updateCountProgressH(this.mZenMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRemainTimeH() {
        this.mSilentTimer.lambda$new$0();
        this.mDndTimer.lambda$new$0();
    }

    public void cleanUp() {
        if (this.isBroadcastRegistered) {
            try {
                getContext().unregisterReceiver(this.mBroadcastReceiver);
                this.isBroadcastRegistered = false;
            } catch (Exception e2) {
                Log.e(TAG, "unregisterReceiver failed:" + e2);
            }
        }
        HandlerThread handlerThread = this.mWorkerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    public void init() {
        HandlerThread handlerThread = new HandlerThread("RingerModeLayout-thread");
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
        getContext().registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"));
        this.isBroadcastRegistered = true;
        setRingerModeInternal(this.mRingerMode, VolumeUtil.isZenMode(this.mContext));
        updateRemainTimeH();
        updateExpandedStateH();
    }

    public boolean isOffMode() {
        return !this.mRingerMode;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTimerShowingState(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setTimerShowingState(false);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        initialize();
    }

    public void scheduleTimerUpdateH(boolean z2) {
        this.mSilentTimer.scheduleTimerUpdateH(z2);
        this.mDndTimer.scheduleTimerUpdateH(z2);
    }

    public void setLockTaskModeState(int i2) {
        this.mLockTaskModeState = i2;
    }

    public void setNeedShowDialog(boolean z2) {
        this.mNeedShowDialog = z2;
        this.mSilentTimer.setNeedShowDialog(z2);
        this.mDndTimer.setNeedShowDialog(this.mNeedShowDialog);
    }

    public void setSilenceMode(boolean z2, boolean z3, boolean z4) {
        Log.i(TAG, "Zenmode changed " + this.mRingerMode + aa.f3429b + this.mZenMode + " -> " + z2 + aa.f3429b + z3 + " doAnimation:" + z4);
        setRingerModeInternal(z2, z3);
        if (z4) {
            post(new Runnable() { // from class: com.android.systemui.miui.volume.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1488a.lambda$setSilenceMode$3();
                }
            });
        }
    }

    public void setSlienceMode(boolean z2) {
        this.mRingerMode = z2;
    }

    public void updateExpandedH(boolean z2) {
        TransitionManager.endTransitions(this);
        this.mDndTimer.updateExpanded(z2);
        this.mSilentTimer.updateExpanded(z2);
        this.mRingerHelper.onExpanded(z2, false);
        this.mDndHelper.onExpanded(z2, false);
        updateExpandedStateH();
    }

    public void updateResources() {
        this.mSilentTimer.updateTimerSeekbar();
        this.mDndTimer.updateTimerSeekbar();
        RingerButtonHelper ringerButtonHelper = this.mRingerHelper;
        ringerButtonHelper.onExpanded(ringerButtonHelper.mExpanded, true);
        RingerButtonHelper ringerButtonHelper2 = this.mDndHelper;
        ringerButtonHelper2.onExpanded(ringerButtonHelper2.mExpanded, true);
    }

    public MiuiRingerModeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiuiRingerModeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRingerMode = false;
        this.mZenMode = false;
        this.mNeedShowDialog = true;
        this.isBroadcastRegistered = false;
        this.mBroadcastReceiver = new AnonymousClass1();
        this.mContext = context;
    }
}
