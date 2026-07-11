package com.android.systemui.miui.volume;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.AudioServiceInjector;
import android.media.AudioSystem;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.view.InputDeviceCompat;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.systemui.SystemVolumeController;
import com.android.systemui.miui.volume.MiuiVolumeDialogMotion;
import com.android.systemui.miui.volume.MiuiVolumeSeekBar;
import com.android.systemui.miui.volume.VolumeDialogControllerCompat;
import com.android.systemui.miui.volume.VolumePanelDialogController;
import com.android.systemui.miui.volume.VolumePanelView;
import com.android.systemui.miui.volume.widget.DndToast;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.volume.Events;
import com.miui.circulate.device.api.Constant;
import com.miui.miplay.audio.data.DeviceInfo;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.devicecontrols.ui.TouchBehavior;
import miui.systemui.notification.focus.Const;
import miui.systemui.quicksettings.soundeffect.dirac.HeadsetUtil;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.ReflectBuilderUtil;
import miui.systemui.util.StatusBarGuideParams;
import miui.systemui.util.ThreadUtils;
import miui.systemui.util.VolumeUtils;
import miui.util.HapticFeedbackUtil;
import miuix.view.HapticCompat;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.ControlsEventTracker;
import systemui.plugin.eventtracking.trackers.VolumeEventTracker;

/* JADX INFO: loaded from: classes2.dex */
public class VolumePanelViewController {
    private static final int DIALOG_EXPAND_TIMEOUT_MILLIS = 5000;
    private static final int DIALOG_HOVERING_TIMEOUT_MILLIS = 16000;
    private static final int DIALOG_SAFETYWARNING_TIMEOUT_MILLIS = 5000;
    private static final int DIALOG_TIMEOUT_MILLIS = 2000;
    private static final int DYNAMIC_STREAM_START_INDEX = 100;
    public static final int HAPTIC_V2_VOLUME_MAX = 203;
    public static final int HAPTIC_V2_VOLUME_MIN = 202;
    private static final String KEYGUARD_DISMISS_REASON = "dismiss by SafeWarningDialog";
    private static final String KEY_ISLAND_PARAM = "island_param";
    private static final String KEY_NOTIFY_ID = "notifyId";
    private static final String KEY_PACKAGE_NAME = "package_name";
    private static final String KEY_PARAM = "param";
    private static final String KEY_PHONE_IN_CALL = "phone_in_call";
    private static final String KEY_STATUS_BAR_STRONG_TOAST = "status_bar_strong_toast";
    private static final String KEY_STRONG_TOAST_ACTION = "strong_toast_action";
    private static final String KEY_STRONG_TOAST_CATEGORY = "strong_toast_category";
    private static final String KEY_TARGET = "target";
    private static final int LOCK_RECORD_TYPE_SLIDE_ALARM = 4;
    private static final int LOCK_RECORD_TYPE_SLIDE_MUSIC = 1;
    private static final int LOCK_RECORD_TYPE_SLIDE_NOTIFICATION = 8;
    private static final int LOCK_RECORD_TYPE_SLIDE_OTHER = 22;
    private static final int LOCK_RECORD_TYPE_SLIDE_RING = 2;
    private static final int SENSITIVE_CONFIG_CHANGES = -1069545980;
    public static final String SERVICE_STATUS_BAR = "statusbar";
    private static int STATUS_BAR_STATE_KEYGUARD = 1;
    private static int STATUS_BAR_STATE_SHADE_LOCKED = 2;
    private static final int TYPE_HINGE_STATE = 33171087;
    private static final int UPDATE_ANIMATION_DURATION = 200;
    private static final long USER_ATTEMPT_GRACE_PERIOD = 1000;
    private static final String VALUE_STATUS_BAR_STRONG_TOAST = "show_custom_strong_toast";
    private static final String VALUE_STRONG_TOAST_CATEGORY = "text_bitmap";
    private static final int VIBRATE_DELAY = 300;
    private static final int WAIT_STATES_UPDATE_MILLIS = 0;
    private static final int WAIT_TOAST_UPDATE_MILLIS = 50;
    private int afterValue;
    private AccessibilityManager mAccessibilityMgr;
    private ActivityManager mActivityManager;
    private AudioManager mAudioManager;
    private Context mContext;
    private VolumeDialogController mController;
    private int mCurrentUserId;
    private Object mDeviceStateManager;
    private View mExpandBgView;
    private boolean mExpanded;
    private boolean mFlipTinyChanged;
    private Object mFoldStateListener;
    private HapticFeedbackUtil mHapticFeedbackUtil;
    private Integer[] mInitialMaxVolume;
    private Boolean mIsFolded;
    private boolean mIsNotifySingle;
    private KeyguardManager mKeyguard;
    private int mLastDensity;
    private boolean mLastExpanded;
    private boolean mLastFlipTiny;
    private boolean mLastFlipTinyLand;
    public boolean mNeedReInit;
    private int mNotLongPressCount;
    private boolean mPendingStateChanged;
    private ViewGroup mRingerModeContent;
    private MiuiRingerModeLayout mRingerModeLayout;
    private boolean mShouldTempBeVisible;
    private boolean mShowA11yStream;
    private boolean mShowing;
    private VolumeDialogController.State mState;
    private StatusBarStateController mStatusBarStateController;
    private TextView mSuperVolume;
    private View mSuperVolumeBg;
    private boolean mSupportSuperVolume;
    private Context mSystemUIContext;
    private VolumeColumn mTempColumn;
    private FrameLayout mTempColumnContainer;
    private boolean mVoiceSupportSuperVolume;
    private VolumeColumns mVolumeColumns;
    private VolumeDialogControllerCompat mVolumeCompat;
    private ViewGroup mVolumeContainerView;
    private ViewGroup mVolumeContentColumns;
    private ViewGroup mVolumeContentView;
    private VolumePanelDialogController mVolumePanelDialogController;
    public VolumePanelView mVolumePanelView;
    private MiuiVolumeDialogView mVolumeView;
    private H mWorkerHandler;
    private HandlerThread mWorkerThread;
    private int preValue;
    private int streamType;
    private ValueAnimator updateExpandedAnimator;
    private static final String CURRENT_HAPTIC_VERSION = SystemProperties.get("sys.haptic.version", "1.0");
    private static final Object[] mSupportSuperVolumeStream = {3, 2, 5};
    private static final Object[] mSupportUseHeadsetIcon = {3, 0, 11};
    private String TAG = "VolumePanelViewController";
    private H mHandler = new H();
    private boolean mHovering = false;
    private List<VolumeColumn> mColumns = new ArrayList();
    private int mVoiceAssistStreamType = -2;
    private Accessibility mAccessibility = new Accessibility();
    private int mActiveStream = -1;
    private SilenceModeObserver mSilenceModeObserver = new SilenceModeObserver();
    private WeakReference<Toast> mLastToast = new WeakReference<>(null);
    private SparseBooleanArray mDynamic = new SparseBooleanArray();
    private int mAssistMaxLevel = 15;
    private boolean mIsHeadset = false;
    private boolean mIsNeedShowHeadSetIcon = false;
    private boolean mIsSpeakerOn = false;
    private boolean mIsEarpiece = false;
    private boolean mAutomute = true;
    private boolean mSilentMode = true;
    private int mKeyAnimatingStream = -1;
    private boolean mProgressViewNoAnim = false;
    private int mLockTaskModeState = 0;
    private boolean mIsSafetyShowing = false;
    private int mRingerMode = -1;
    private int mLockRecordTypes = 0;
    private Configuration mLastConfiguration = new Configuration();
    private boolean mNeedShowDialog = true;
    private boolean isControlCenterPanel = false;
    private boolean mIsSilenceModeOn = false;
    private Drawable mVolumeContentBlurDrawable = null;
    private VolumeDialogControllerCompat.Callback mControllerCompatCallback = new AnonymousClass1();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String action = intent.getAction();
            if ("android.intent.action.USER_SWITCHED".equals(action)) {
                VolumePanelViewController.this.mCurrentUserId = intent.getIntExtra("android.intent.extra.user_handle", -1);
                VolumePanelViewController volumePanelViewController = VolumePanelViewController.this;
                volumePanelViewController.mIsNotifySingle = Util.isNotificationSingle(volumePanelViewController.mContext, VolumePanelViewController.this.mCurrentUserId);
                VolumePanelViewController.this.updateVolumePanelSize();
            } else if (VolumeUtils.getACTION_CALL_VOLUME_BOOST().equals(action)) {
                Log.e(VolumePanelViewController.this.TAG, "CALL_VOLUME_BOOST_ON");
                if (VolumePanelViewController.this.mNeedShowDialog) {
                    VolumePanelViewController.this.showSVToast(VolumeUtils.getIndexAdd(), false);
                }
            } else if ("android.intent.action.PHONE_STATE".equals(action)) {
                VolumePanelViewController.this.updateHeadsetIconW();
            }
            Log.d(VolumePanelViewController.this.TAG, "onReceive: " + action + ", time= " + (System.currentTimeMillis() - jCurrentTimeMillis));
        }
    };
    private BroadcastReceiver mPackagesBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Uri data;
            long jCurrentTimeMillis = System.currentTimeMillis();
            String action = intent.getAction();
            if (("android.intent.action.PACKAGE_ADDED".equals(action) || SystemVolumeController.ACTION_PACKAGE_REPLACED.equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action)) && (data = intent.getData()) != null && Util.MIUI_VOICE_ASSIST.equals(data.getSchemeSpecificPart())) {
                VolumePanelViewController.this.updateVoiceAssistStreamIcon();
            }
            Log.d(VolumePanelViewController.this.TAG, "onReceive: " + action + ", time= " + (System.currentTimeMillis() - jCurrentTimeMillis));
        }
    };
    private BroadcastReceiver mCountDownOffBroadcastReceiver = new AnonymousClass6();
    private ContentObserver mContentObserver = new ContentObserver(new Handler()) { // from class: com.android.systemui.miui.volume.VolumePanelViewController.7
        @Override // android.database.ContentObserver
        public void onChange(boolean z2) {
            VolumePanelViewController volumePanelViewController = VolumePanelViewController.this;
            volumePanelViewController.mIsNotifySingle = Util.isNotificationSingle(volumePanelViewController.mContext, VolumePanelViewController.this.mCurrentUserId);
            VolumePanelViewController.this.updateVolumePanelSize();
        }
    };
    private boolean disableVolumeDialog = false;
    private boolean isDismissWaitCancelKeyAnim = false;
    private boolean mRecordSuperVolumeScale = false;
    private boolean mRecordSuperVolumeTransition = false;
    private VolumePanelDialogController.Callback mDialogCallback = new VolumePanelDialogController.Callback() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.11
        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public void dismiss(int i2) {
            VolumePanelViewController.this.dismissH(i2);
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public void expandClicked() {
            VolumePanelViewController.this.onExpandClicked();
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public boolean getPendingState() {
            return VolumePanelViewController.this.mPendingStateChanged;
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public View getVolumeRootView() {
            return VolumePanelViewController.this.mVolumeView.getRootView();
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public boolean isAnimating() {
            return VolumePanelViewController.this.mVolumeView.isAnimating();
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public void recheckAll() {
            VolumePanelViewController.this.mHandler.sendEmptyMessage(4);
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public void rescheduleTimeout() {
            VolumePanelViewController.this.rescheduleTimeoutH();
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public void setPendingState(boolean z2) {
            VolumePanelViewController.this.mPendingStateChanged = z2;
        }

        @Override // com.android.systemui.miui.volume.VolumePanelDialogController.Callback
        public void stateChange() {
            VolumePanelViewController.this.mHandler.sendEmptyMessage(7);
        }
    };

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.VolumePanelViewController$1, reason: invalid class name */
    public class AnonymousClass1 implements VolumeDialogControllerCompat.Callback {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onConfigurationChanged$0() {
            int iUpdateFrom = VolumePanelViewController.this.mLastConfiguration.updateFrom(VolumePanelViewController.this.mContext.getResources().getConfiguration());
            if (iUpdateFrom != 0) {
                Log.d(VolumePanelViewController.this.TAG, "onConfigurationChanged delayed: " + VolumePanelViewController.this.mNeedShowDialog + " " + iUpdateFrom);
                VolumePanelViewController.this.onConfigChanged(iUpdateFrom);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onAccessibilityModeChanged(Boolean bool) {
            Log.d(VolumePanelViewController.this.TAG, " onAccessibilityModeChanged showA11yStream =" + bool);
            VolumePanelViewController volumePanelViewController = VolumePanelViewController.this;
            volumePanelViewController.mShowA11yStream = Util.isEnableAccessibility(volumePanelViewController.mContext) && VolumePanelViewController.this.mActiveStream == 10;
            VolumeColumn activeColumn = VolumePanelViewController.this.getActiveColumn();
            if (activeColumn.getStream() == 10 && !VolumePanelViewController.this.mShowA11yStream) {
                activeColumn = (VolumeColumn) VolumePanelViewController.this.mColumns.get(0);
            }
            VolumePanelViewController.this.updateColumnH(activeColumn);
            VolumePanelViewController.this.mVolumeView.notifyAccessibilityChanged(bool.booleanValue());
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onConfigurationChanged() {
            Log.d(VolumePanelViewController.this.TAG, " onConfigurationChanged ");
            int iUpdateFrom = VolumePanelViewController.this.mLastConfiguration.updateFrom(VolumePanelViewController.this.mContext.getResources().getConfiguration());
            if (iUpdateFrom == 0) {
                VolumePanelViewController.this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.L
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1477a.lambda$onConfigurationChanged$0();
                    }
                }, 50L);
            } else {
                VolumePanelViewController.this.onConfigChanged(iUpdateFrom);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onDismissRequested(int i2) {
            Log.d(VolumePanelViewController.this.TAG, "onDismissRequested reason =" + i2);
            VolumePanelViewController.this.dismissH(i2);
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onLayoutDirectionChanged(int i2) {
            Log.d(VolumePanelViewController.this.TAG, "onLayoutDirectionChanged " + i2);
            VolumePanelViewController.this.mVolumeView.setLayoutDirection(i2);
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onPerformHapticFeedback(int i2) {
            Log.d(VolumePanelViewController.this.TAG, " onPerformHapticFeedback flag =" + i2);
            boolean z2 = (i2 & 1) > 0;
            boolean z3 = (i2 & 2) > 0;
            boolean z4 = (i2 & 4) > 0;
            boolean z5 = (i2 & 16) > 0;
            if (VolumePanelViewController.this.isControlCenterPanel) {
                return;
            }
            if (z5) {
                VolumePanelViewController.this.performHapticFeedBack(z2, z3, z4);
            }
            if (z3 || z4 || !z5) {
                VolumePanelViewController.this.performKeyAnimFromVolumeChange(z5, z3, z4);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onScreenOff() {
            Log.d(VolumePanelViewController.this.TAG, " onScreenOff ");
            VolumePanelViewController.this.dismissH(4);
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onShowRequested(int i2) {
            Log.d(VolumePanelViewController.this.TAG, "onShowRequested reason =" + i2);
            if (VolumePanelViewController.this.mNeedShowDialog) {
                VolumePanelViewController.this.showH(i2);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onShowSafetyWarning(int i2) {
            Log.d(VolumePanelViewController.this.TAG, " onShowSafetyWarning flags =" + i2);
            VolumePanelViewController.this.showSafetyWarningH(i2);
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onShowSilentHint() {
            Log.d(VolumePanelViewController.this.TAG, " onShowSilentHint ");
            if (VolumePanelViewController.this.mSilentMode) {
                VolumePanelViewController.this.mController.setRingerMode(2, false);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onShowVibrateHint() {
            Log.d(VolumePanelViewController.this.TAG, " onShowVibrateHint ");
            if (VolumePanelViewController.this.mSilentMode) {
                VolumePanelViewController.this.mController.setRingerMode(0, false);
            }
        }

        @Override // com.android.systemui.miui.volume.VolumeDialogControllerCompat.Callback
        public void onStateChanged(VolumeDialogController.State state) {
            Log.d(VolumePanelViewController.this.TAG, "onStateChanged ");
            VolumePanelViewController.this.onStateChangedH(state);
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.VolumePanelViewController$6, reason: invalid class name */
    public class AnonymousClass6 extends BroadcastReceiver {
        public AnonymousClass6() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0() {
            VolumeUtil.setSilenceMode(VolumePanelViewController.this.mContext, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$1(Context context) {
            if (VolumePanelViewController.this.mAudioManager.getRingerModeInternal() == 2) {
                VolumeUtil.stopCountDownSilence(context);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String action = intent.getAction();
            if (VolumeUtil.COUNTDOWN_TURN_OFF.equals(action)) {
                VolumePanelViewController.this.mVolumeView.setSilenceModeByTimer(false);
                VolumePanelViewController.this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.M
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1478a.lambda$onReceive$0();
                    }
                });
            }
            if ("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION".equals(action) && VolumeUtil.getSilenceRemainTime(VolumePanelViewController.this.mContext) != 0) {
                VolumePanelViewController.this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.N
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1479a.lambda$onReceive$1(context);
                    }
                });
            }
            Log.d(VolumePanelViewController.this.TAG, "onReceive: " + action + ", time= " + (System.currentTimeMillis() - jCurrentTimeMillis));
        }
    }

    public final class Accessibility extends View.AccessibilityDelegate implements AccessibilityManager.AccessibilityStateChangeListener {
        private final View.OnAttachStateChangeListener mAttachListener;
        private boolean mFeedbackEnabled;

        private boolean computeFeedbackEnabled() {
            Iterator<AccessibilityServiceInfo> it = VolumePanelViewController.this.mAccessibilityMgr.getEnabledAccessibilityServiceList(-1).iterator();
            while (it.hasNext()) {
                int i2 = it.next().feedbackType;
                if (i2 != 0 && i2 != 16) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateFeedbackEnabled() {
            this.mFeedbackEnabled = computeFeedbackEnabled();
        }

        public void destroy() {
            VolumePanelViewController.this.mVolumeView.removeOnAttachStateChangeListener(this.mAttachListener);
            VolumePanelViewController.this.mVolumeView.setAccessibilityDelegate(null);
            VolumePanelViewController.this.mAccessibilityMgr.removeAccessibilityStateChangeListener(this);
        }

        public void init() {
            VolumePanelViewController.this.mVolumeView.addOnAttachStateChangeListener(this.mAttachListener);
            VolumePanelViewController.this.mVolumeView.setAccessibilityDelegate(this);
            VolumePanelViewController.this.mAccessibilityMgr.addAccessibilityStateChangeListener(this);
            updateFeedbackEnabled();
            VolumePanelViewController volumePanelViewController = VolumePanelViewController.this;
            volumePanelViewController.mShowA11yStream = Util.isEnableAccessibility(volumePanelViewController.mContext) && VolumePanelViewController.this.mActiveStream == 10;
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z2) {
            updateFeedbackEnabled();
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        private Accessibility() {
            this.mAttachListener = new View.OnAttachStateChangeListener() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.Accessibility.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                    Accessibility.this.updateFeedbackEnabled();
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                }
            };
        }
    }

    public final class SilenceModeObserver {
        private DndPopupWindow mDndPopupWindow;
        private DndToast mDndToast;
        private boolean mIsSilenceMode;
        private boolean mIsZenMode;
        private String SILENT_MODE_OFF = "silent_mode_off";
        private String SILENT_MODE_ON = "silent_mode_on";
        private String DISTURB_MODE_OFF = "disturb_mode_off";
        private String DISTURB_MODE_ON = "disturb_mode_on";

        public SilenceModeObserver() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$updateVolumeInfo$0() {
            VolumeUtil.setBootAudioOn(VolumePanelViewController.this.mContext);
        }

        private void showToastOrStatusBar(boolean z2, boolean z3) {
            String str;
            int i2;
            int i3;
            boolean z4;
            if (z3 == this.mIsZenMode || !VolumePanelViewController.this.isKeyguard()) {
                int i4 = R.color.miui_dnd_or_silent_mode_off_color_os2;
                if (VolumePanelViewController.this.isSuportIsland()) {
                    i4 = R.color.miui_dnd_or_silent_mode_off_color;
                }
                if (z2 && !this.mIsSilenceMode) {
                    i2 = R.string.miui_silent_mode_off;
                    i3 = R.drawable.miui_statuebar_silent_mode_off;
                    str = this.SILENT_MODE_OFF;
                } else if (!z2 && this.mIsSilenceMode) {
                    i2 = R.string.miui_silent_mode_on;
                    i3 = R.drawable.miui_statusbar_silent_mode_on;
                    str = this.SILENT_MODE_ON;
                    i4 = R.color.miui_dnd_or_silent_mode_on_color_os2;
                    if (VolumePanelViewController.this.isSuportIsland()) {
                        i4 = R.color.miui_dnd_or_silent_mode_on_color;
                    }
                } else if (z3 && !this.mIsZenMode) {
                    i2 = R.string.miui_dnd_mode_off;
                    i3 = R.drawable.miui_statusbar_dnd_mode_off;
                    str = this.DISTURB_MODE_OFF;
                } else if (z3 || !this.mIsZenMode) {
                    str = "";
                    i2 = 0;
                    i3 = 0;
                } else {
                    i2 = R.string.miui_dnd_mode_on;
                    i3 = R.drawable.miui_statusbar_dnd_mode_on;
                    str = this.DISTURB_MODE_ON;
                    i4 = R.color.miui_dnd_or_silent_mode_on_color_os2;
                    if (VolumePanelViewController.this.isSuportIsland()) {
                        i4 = R.color.miui_dnd_mode_on_color;
                    }
                }
                if (VolumePanelViewController.this.mNeedShowDialog && z3 != (z4 = this.mIsZenMode) && z4 && Util.isFirstTimeTurnOnDND(VolumePanelViewController.this.mContext) && !Util.isDNDChangedFromSettings(VolumePanelViewController.this.mContext)) {
                    if (this.mDndToast.isShowing()) {
                        this.mDndToast.hide();
                    }
                    this.mDndToast.show(this.mIsZenMode);
                    return;
                }
                DndToast dndToast = this.mDndToast;
                if (dndToast != null && dndToast.isShowing()) {
                    this.mDndToast.hide();
                }
                boolean unused = VolumePanelViewController.this.mShowing;
                boolean unused2 = VolumePanelViewController.this.mNeedShowDialog;
                VolumePanelViewController.this.isKeyguard();
                boolean z5 = VolumePanelViewController.this.mShowing & VolumePanelViewController.this.mExpanded & (!VolumePanelViewController.this.mNeedShowDialog);
                boolean zIsPowerSuperSaveOpen = Util.isPowerSuperSaveOpen(VolumePanelViewController.this.mContext) & VolumePanelViewController.this.mNeedShowDialog;
                if (z5 || zIsPowerSuperSaveOpen) {
                    VolumePanelViewController.this.mWorkerHandler.removeMessages(14);
                    VolumePanelViewController.this.mWorkerHandler.sendMessageDelayed(VolumePanelViewController.this.mWorkerHandler.obtainMessage(14, i2, 0), 200L);
                } else if (VolumePanelViewController.this.mNeedShowDialog) {
                    VolumePanelViewController.this.showToastInStatusBar(i2, i3, i4, str);
                }
            }
        }

        public void dismissPopupWindow() {
            this.mDndPopupWindow.dismiss();
        }

        public boolean getSilenceMode() {
            return this.mIsSilenceMode;
        }

        public boolean getZenMode() {
            return this.mIsZenMode;
        }

        public void init() {
            this.mIsSilenceMode = VolumeUtil.isSilentMode(VolumePanelViewController.this.mContext);
            this.mIsZenMode = VolumeUtil.isZenMode(VolumePanelViewController.this.mContext);
            this.mDndToast = new DndToast(VolumePanelViewController.this.mContext);
            this.mDndPopupWindow = new DndPopupWindow(VolumePanelViewController.this.mContext);
        }

        public boolean isDndPopupShowing() {
            return this.mDndPopupWindow.isShowing();
        }

        public void showDndPopupWindow() {
        }

        public void updateVolumeInfo(boolean z2, boolean z3) {
            boolean z4 = this.mIsSilenceMode;
            boolean z5 = this.mIsZenMode;
            this.mIsSilenceMode = z2;
            this.mIsZenMode = z3;
            if (z4 == z2 && z5 == z3) {
                return;
            }
            Log.i(VolumePanelViewController.this.TAG, "silent: " + z4 + "->" + this.mIsSilenceMode + ", +zen: " + z5 + "->" + this.mIsZenMode);
            VolumePanelViewController.this.mIsSilenceModeOn = true;
            VolumePanelViewController.this.mVolumeView.setSilenceMode(z2, z3, VolumePanelViewController.this.mShowing);
            VolumePanelViewController.this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.O
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1481a.lambda$updateVolumeInfo$0();
                }
            });
            showToastOrStatusBar(z4, z5);
        }
    }

    public static class VolumeColumns {
        private ViewGroup mColumnsCollapsed;
        private ViewGroup mColumnsExpanded;
        private boolean mExpanded;

        public VolumeColumns(ViewGroup viewGroup, ViewGroup viewGroup2) {
            this.mColumnsCollapsed = viewGroup;
            this.mColumnsExpanded = viewGroup2;
        }

        public void addView(View view) {
            getCurrentParent().addView(view);
        }

        public ViewGroup getCurrentParent() {
            return this.mExpanded ? this.mColumnsExpanded : this.mColumnsCollapsed;
        }

        public void removeView(View view) {
            getCurrentParent().removeView(view);
        }

        public void updateExpandedH(boolean z2) {
            this.mExpanded = z2;
            Util.reparentChildren(z2 ? this.mColumnsCollapsed : this.mColumnsExpanded, z2 ? this.mColumnsExpanded : this.mColumnsCollapsed);
        }

        public void addView(View view, int i2) {
            getCurrentParent().addView(view, i2);
        }
    }

    public class VolumeSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private final VolumeColumn mColumn;

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(final SeekBar seekBar, int i2, boolean z2) {
            int i3;
            VolumeDialogController.StreamState ss = this.mColumn.getSs();
            if (ss == null) {
                return;
            }
            if (Util.DEBUG) {
                Log.d(VolumePanelViewController.this.TAG, AudioSystem.streamToString(this.mColumn.getStream()) + " onProgressChanged " + i2 + " fromUser=" + z2);
            }
            VolumePanelViewController.this.updateIconTint(i2 / seekBar.getMax(), this.mColumn);
            this.mColumn.progressView.toProgressWithAnim(z2, seekBar);
            if (z2) {
                VolumePanelViewController.this.recordVolumeChanged(this.mColumn);
                int i4 = ss.levelMin;
                if (i4 > 0 && i2 < (i3 = i4 * 1000)) {
                    seekBar.setProgress(i3);
                    i2 = i3;
                }
                int iProgressToLevel = VolumeUtils.progressToLevel(seekBar.getMax(), i2);
                if (ss.level != iProgressToLevel || (ss.muted && iProgressToLevel > 0)) {
                    this.mColumn.setUserAttempt(SystemClock.uptimeMillis());
                    if (this.mColumn.getRequestedLevel() != iProgressToLevel) {
                        Log.d(VolumePanelViewController.this.TAG, "onProgressChanged =  userLevel = " + iProgressToLevel + " sslevel = " + ss.level + " getMax = " + seekBar.getMax() + " progress = " + i2);
                        VolumePanelViewController.this.mController.setStreamVolume(this.mColumn.getStream(), iProgressToLevel);
                        this.mColumn.setRequestedLevel(iProgressToLevel);
                        VolumePanelViewController.this.showSuperVolumeToast(this.mColumn, iProgressToLevel, true);
                        Events.writeEvent(VolumePanelViewController.this.mContext, 9, Integer.valueOf(this.mColumn.getStream()), Integer.valueOf(iProgressToLevel));
                    }
                }
                if (i2 <= seekBar.getMin() || i2 >= seekBar.getMax()) {
                    VolumePanelViewController.this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.P
                        @Override // java.lang.Runnable
                        public final void run() {
                            VolumeUtil.performHapticFeedback(seekBar, 268435460);
                        }
                    });
                }
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d(VolumePanelViewController.this.TAG, "onStartTrackingTouch " + this.mColumn.getStream());
            BoostHelper.getInstance().boostWithCpuFreq(TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS, VolumePanelViewController.this.mVolumeView);
            int iProgressToLevel = VolumeUtils.progressToLevel(seekBar.getMax(), seekBar.getProgress());
            if (this.mColumn.getSs() != null && this.mColumn.getSs().level != iProgressToLevel) {
                Log.d(VolumePanelViewController.this.TAG, "onStartTrackingTouch mColumn.ss.level=" + this.mColumn.getSs().level + " userLevel=" + iProgressToLevel);
                VolumePanelViewController.this.updateVolumeColumnH(this.mColumn);
            }
            this.mColumn.setTracking(true);
            VolumePanelViewController.this.preValue = seekBar.getProgress();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(VolumePanelViewController.this.TAG, "onStopTrackingTouch " + this.mColumn.getStream());
            this.mColumn.setTracking(false);
            this.mColumn.setUserAttempt(SystemClock.uptimeMillis());
            int iProgressToLevel = VolumeUtils.progressToLevel(seekBar.getMax(), seekBar.getProgress());
            Events.writeEvent(VolumePanelViewController.this.mContext, 16, Integer.valueOf(this.mColumn.getStream()), Integer.valueOf(iProgressToLevel));
            VolumePanelViewController.this.afterValue = seekBar.getProgress();
            VolumePanelViewController.this.streamType = this.mColumn.getStream();
            if (this.mColumn.getSs() != null && this.mColumn.getSs().level != iProgressToLevel) {
                VolumePanelViewController.this.mHandler.sendMessageDelayed(VolumePanelViewController.this.mHandler.obtainMessage(3, this.mColumn), 1000L);
            }
            if (this.mColumn.getStream() != VolumePanelViewController.this.mActiveStream) {
                Log.d(VolumePanelViewController.this.TAG, "onStopTrackingTouch-updateTempVolumeColumn");
                VolumePanelViewController.this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.VolumeSeekBarChangeListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VolumeSeekBarChangeListener volumeSeekBarChangeListener = VolumeSeekBarChangeListener.this;
                        VolumePanelViewController.this.updateVolumeColumnH(volumeSeekBarChangeListener.mColumn);
                        VolumeSeekBarChangeListener volumeSeekBarChangeListener2 = VolumeSeekBarChangeListener.this;
                        VolumePanelViewController.this.updateSuperVolumeView(volumeSeekBarChangeListener2.mColumn);
                    }
                }, 0L);
            }
        }

        private VolumeSeekBarChangeListener(VolumeColumn volumeColumn) {
            this.mColumn = volumeColumn;
        }
    }

    public VolumePanelViewController(Context context, Context context2, VolumeDialogController volumeDialogController, StatusBarStateController statusBarStateController) {
        boolean z2 = true;
        this.mContext = context;
        this.mSystemUIContext = context2;
        this.mController = volumeDialogController;
        this.mStatusBarStateController = statusBarStateController;
        int currentUser = ActivityManager.getCurrentUser();
        this.mCurrentUserId = currentUser;
        this.mIsNotifySingle = Util.isNotificationSingle(this.mContext, currentUser);
        this.mVolumeCompat = new VolumeDialogControllerCompat(volumeDialogController, this.mHandler);
        setResourceClassLoader(context.getResources(), context.getClassLoader());
        VolumePanelView volumePanelView = new VolumePanelView(this.mContext);
        this.mVolumePanelView = volumePanelView;
        volumePanelView.setOnConfigChangeListener(new VolumePanelView.OnConfigChangeListener() { // from class: com.android.systemui.miui.volume.C
            @Override // com.android.systemui.miui.volume.VolumePanelView.OnConfigChangeListener
            public final void onConfigChange() {
                this.f1466a.lambda$new$0();
            }
        });
        this.mVolumePanelDialogController = new VolumePanelDialogController(this.mContext);
        this.mKeyguard = (KeyguardManager) this.mContext.getSystemService("keyguard");
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
        this.mAudioManager = (AudioManager) this.mContext.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        this.mAccessibilityMgr = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        HandlerThread handlerThread = new HandlerThread(VolumePanelViewController.class.getSimpleName());
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorkerHandler = new H(this.mWorkerThread.getLooper());
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(Util.NOTIFICATION_SINGLE_CONTROL_STATE), false, this.mContentObserver);
        this.mSupportSuperVolume = VolumeUtils.getSUPER_VOLUME_SUPPORTED();
        if (!VolumeUtils.getSUPER_VOLUME_VOICE_CALL_SUPPORTED() && !VolumeUtils.voiceSupportSuperVolume()) {
            z2 = false;
        }
        this.mVoiceSupportSuperVolume = z2;
        this.mInitialMaxVolume = VolumeUtils.getInitialMaxVolume(this.mContext);
        VolumeUtil.setSystemUICtx(this.mSystemUIContext);
        this.mHapticFeedbackUtil = new HapticFeedbackUtil(context, false);
        FlipUtils.setFlip(BlurUtils.isFlipDevice(context));
        this.mLastFlipTiny = FlipUtils.updateFlipTiny(BlurUtils.isFlipTinyScreen(this.mContext));
        if (Util.IS_MUILT_DISPLAY || FlipUtils.isFlip()) {
            this.mLastFlipTinyLand = FlipUtils.updateFlipTinyLand(context);
            DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
            this.mDeviceStateManager = deviceStateManagerCompat.getDeviceStateManagerInstance();
            Object foldStateListenerInstance = deviceStateManagerCompat.getFoldStateListenerInstance(this.mContext, new Consumer() { // from class: com.android.systemui.miui.volume.D
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f1467a.lambda$new$1((Boolean) obj);
                }
            });
            this.mFoldStateListener = foldStateListenerInstance;
            if (foldStateListenerInstance != null) {
                deviceStateManagerCompat.registerCallbackCompat(this.mDeviceStateManager, new HandlerExecutor(ThreadUtils.getUiThreadHandler()), this.mFoldStateListener);
            }
        }
    }

    private void addColumn(int i2, boolean z2) {
        addColumn(i2, z2, false);
    }

    private void addExistingColumns() {
        int size = this.mColumns.size();
        for (int i2 = 0; i2 < size; i2++) {
            VolumeColumn volumeColumn = this.mColumns.get(i2);
            initColumn(volumeColumn, volumeColumn.getStream(), volumeColumn.getImportant(), true);
            this.mVolumeColumns.addView(volumeColumn.view);
        }
    }

    private void addTempColumn(int i2, boolean z2) {
        VolumeColumn volumeColumn = this.mTempColumn;
        if (volumeColumn == null) {
            volumeColumn = new VolumeColumn();
            initColumn(volumeColumn, i2, z2);
        } else {
            initColumn(volumeColumn, i2, z2, true);
        }
        if (this.mTempColumnContainer.getChildCount() != 0) {
            this.mTempColumnContainer.removeAllViews();
        }
        this.mTempColumnContainer.addView(volumeColumn.view);
        this.mTempColumn = volumeColumn;
    }

    private int computeTimeoutH() {
        return this.mHovering ? this.mAccessibilityMgr.getRecommendedTimeoutMillis(DIALOG_HOVERING_TIMEOUT_MILLIS, 4) : this.mIsSafetyShowing ? this.mAccessibilityMgr.getRecommendedTimeoutMillis(Constant.MAX_STR_LENGTH, 6) : this.mExpanded ? this.mAccessibilityMgr.getRecommendedTimeoutMillis(Constant.MAX_STR_LENGTH, 4) : this.mAccessibilityMgr.getRecommendedTimeoutMillis(DIALOG_TIMEOUT_MILLIS, 4);
    }

    private void dismissKeyguardThenExecute(final Runnable runnable) {
        IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
        try {
            if (windowManagerService.isKeyguardLocked()) {
                windowManagerService.dismissKeyguard(new IKeyguardDismissCallback.Stub(this) { // from class: com.android.systemui.miui.volume.VolumePanelViewController.10
                    public void onDismissCancelled() {
                    }

                    public void onDismissError() {
                    }

                    public void onDismissSucceeded() {
                        runnable.run();
                    }
                }, KEYGUARD_DISMISS_REASON);
            } else {
                runnable.run();
            }
        } catch (RemoteException e2) {
            Log.e(this.TAG, "dismissKeyguard Exception:" + e2);
        }
    }

    private void dismissVolumePanel(int i2) {
        this.mShowing = false;
        Log.i(this.TAG, "Final dismiss! mActiveStream:" + this.mActiveStream);
        AccessibilityManager accessibilityManager = this.mAccessibilityMgr;
        if (accessibilityManager != null && accessibilityManager.isEnabled()) {
            this.mVolumeView.announceForAccessibility(this.mContext.getString(R.string.volume_dialog_accessibility_dismissed_message));
        }
        Events.writeEvent(this.mContext, 1, Integer.valueOf(i2));
        VolumeEventTracker.trackVolumeDismiss(this.mExpanded, Events.DISMISS_REASONS[i2]);
        this.mController.notifyVisible(false);
        this.mVolumeView.dismissH(i2 != 8, new Runnable() { // from class: com.android.systemui.miui.volume.J
            @Override // java.lang.Runnable
            public final void run() {
                this.f1475a.lambda$dismissVolumePanel$9();
            }
        });
        this.mIsSafetyShowing = false;
        this.mSilenceModeObserver.dismissPopupWindow();
    }

    private int getActiveStreamIndex() {
        for (int i2 = 0; i2 < this.mColumns.size(); i2++) {
            if (this.mColumns.get(i2).getStream() == this.mActiveStream) {
                return i2;
            }
        }
        return 0;
    }

    private String getStreamLabelH(VolumeDialogController.StreamState streamState) {
        return this.mVolumeCompat.getStreamLabelH(this.mSystemUIContext, streamState);
    }

    private int getVoiceAssistStreamType() {
        if (this.mVoiceAssistStreamType == -2) {
            this.mVoiceAssistStreamType = AudioServiceInjector.getVoiceAssistNum();
        }
        return this.mVoiceAssistStreamType;
    }

    private void initColumn(VolumeColumn volumeColumn, int i2, boolean z2) {
        initColumn(volumeColumn, i2, z2, false);
    }

    private void initExpandButtonColor() {
        if (Util.isBackdropBlurSupported() || (!Util.isBackdropBlurSupported() && Util.isDarkTheme(this.mContext))) {
            this.mVolumeView.updateExpandButtonColor(R.color.miui_volume_expand_button_color_blur_light, true);
        } else {
            this.mVolumeView.updateExpandButtonColor(R.color.miui_volume_expand_button_color_blur, true);
        }
    }

    private void initPanelView() {
        this.mExpandBgView = this.mVolumePanelView.findViewById(R.id.blur_frame);
        MiuiVolumeDialogView miuiVolumeDialogView = (MiuiVolumeDialogView) this.mVolumePanelView.findViewById(R.id.volume_dialog);
        this.mVolumeView = miuiVolumeDialogView;
        miuiVolumeDialogView.setNeedShowDialog(this.mNeedShowDialog);
        this.mVolumeView.setVolumeColumns(getColumns());
        this.mSuperVolume = (TextView) this.mVolumePanelView.findViewById(R.id.miui_super_volume_collapsed);
        this.mSuperVolumeBg = this.mVolumePanelView.findViewById(R.id.miui_super_volume_collapsed_bg);
        this.mSuperVolume.setText(VolumeUtils.getSuperVolumePercent(this.mContext));
        this.mVolumeContainerView = (ViewGroup) this.mVolumeView.findViewById(R.id.volume_dialog_container);
        this.mVolumeContentView = (ViewGroup) this.mVolumeView.findViewById(R.id.volume_dialog_content);
        this.mVolumeContentColumns = (ViewGroup) this.mVolumeView.findViewById(R.id.volume_dialog_columns);
        this.mTempColumnContainer = (FrameLayout) this.mVolumeView.findViewById(R.id.volume_dialog_column_temp);
        this.mRingerModeLayout = (MiuiRingerModeLayout) this.mVolumeView.findViewById(R.id.miui_volume_ringer_layout);
        this.mRingerModeContent = (ViewGroup) this.mVolumeView.findViewById(R.id.miui_ringer_state_layout);
        if (this.mNeedShowDialog) {
            this.mVolumePanelView.setAccessibilityPaneTitle(this.mContext.getResources().getString(R.string.volume_adjust_panel_title));
        }
        this.mVolumeView.updateDialogViewLP();
        this.mVolumeView.setContentView(this.mExpandBgView, this.mSuperVolumeBg);
        this.mHovering = false;
        this.mShowing = false;
        this.mExpanded = false;
        this.mLastExpanded = true;
        this.mPendingStateChanged = false;
        this.mVolumeView.setOnHoverListener(new View.OnHoverListener() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.2
            @Override // android.view.View.OnHoverListener
            public boolean onHover(View view, MotionEvent motionEvent) {
                int actionMasked = motionEvent.getActionMasked();
                VolumePanelViewController.this.mHovering = actionMasked == 9 || actionMasked == 7;
                VolumePanelViewController.this.rescheduleTimeoutH();
                return true;
            }
        });
        if (this.mNeedShowDialog) {
            this.mVolumeView.setMotionCallback(this.mVolumePanelDialogController.mMotionCallback);
        }
        this.mVolumeColumns = new VolumeColumns((ViewGroup) this.mVolumeContentView.findViewById(R.id.volume_dialog_column_collapsed), this.mVolumeContentColumns);
        if (this.mColumns.isEmpty()) {
            addColumn(3, true);
            if (!AudioSystem.isSingleVolume(this.mContext)) {
                addColumn(10, true);
                addColumn(2, true);
                addColumn(4, true);
                addColumn(5, true);
                addColumn(0, false);
                addColumn(6, false);
                if (getVoiceAssistStreamType() > 0) {
                    addColumn(getVoiceAssistStreamType(), false);
                    updateVoiceAssistStreamIcon();
                }
            }
        } else {
            addExistingColumns();
        }
        addTempColumn(3, true);
        updateExpandedH(false, false, true);
        initExpandButtonColor();
        initSuperVolumeColor();
    }

    private void initSilentTimer() {
        long silenceRemainTime = VolumeUtil.getSilenceRemainTime(this.mContext);
        if (silenceRemainTime == 0) {
            return;
        }
        final int timeInMillis = (int) (((silenceRemainTime - Calendar.getInstance().getTimeInMillis()) / 60) / 1000);
        if (timeInMillis > 0) {
            this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1510a.lambda$initSilentTimer$4(timeInMillis);
                }
            });
        } else {
            this.mVolumeView.setSilenceModeByTimer(false);
            this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1512a.lambda$initSilentTimer$5();
                }
            });
        }
    }

    private void initSuperVolumeColor() {
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            View view = this.mSuperVolumeBg;
            int i2 = R.drawable.miui_super_volume_view_transparent;
            view.setBackgroundResource(i2);
            this.mSuperVolume.setBackgroundResource(i2);
            View view2 = this.mSuperVolumeBg;
            boolean z2 = this.mExpanded;
            Context context = this.mContext;
            Util.setMiViewBlurAndBlendColor(view2, z2, context, 1, context.getResources().getIntArray(R.array.miui_seekbar_and_ringer_bg_blend_colors_collapsed), false);
        } else if (BlurUtils.isLowEndDevice() || !Util.isSupportBlurS()) {
            this.mSuperVolumeBg.setBackgroundResource(R.drawable.miui_volume_seekbar_backgroud);
        } else {
            this.mSuperVolumeBg.setBackgroundResource(R.drawable.miui_super_volume_view_bg_blur);
        }
        updateSuperVolumeViewColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isKeyguard() {
        return this.mStatusBarStateController.getState() == STATUS_BAR_STATE_KEYGUARD || this.mStatusBarStateController.getState() == STATUS_BAR_STATE_SHADE_LOCKED;
    }

    private boolean isScreenOn() {
        return ((PowerManager) this.mContext.getSystemService("power")).isInteractive();
    }

    private boolean isStreamValid(int i2, int i3) {
        return i2 >= 0 && i2 < i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSuportIsland() {
        return VolumeUtils.getFEATURE_DYNAMIC_ISLAND();
    }

    private boolean isUpdateExpandedAnimating() {
        ValueAnimator valueAnimator = this.updateExpandedAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissVolumePanel$9() {
        updateExpandedH(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initColumn$3(int i2, VolumeColumn volumeColumn) {
        if (isStreamValid(i2, this.mInitialMaxVolume.length)) {
            try {
                volumeColumn.setSuperAddIndex(this.mAudioManager.getStreamVolume(i2) - this.mInitialMaxVolume[i2].intValue());
            } catch (Exception e2) {
                Log.e(this.TAG, "getStreamVolume error: Bad stream type " + i2);
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initController$2() {
        this.mAssistMaxLevel = this.mAudioManager.getStreamMaxVolume(getVoiceAssistStreamType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSilentTimer$4(int i2) {
        VolumeUtil.startCountDownSilenceMode(this.mContext, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSilentTimer$5() {
        VolumeUtil.setSilenceMode(this.mContext, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.mControllerCompatCallback.onConfigurationChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Boolean bool) {
        updateFoldState(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onExpandClicked$12(boolean z2) {
        Events.writeEvent(this.mContext, 3, Boolean.valueOf(z2));
        updateExpandedH(z2, false);
        VolumeEventTracker.trackExpandBtn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performHapticFeedBack$14() {
        VolumeUtil.performHapticFeedback(this.mVolumeView, 268435462);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performHapticFeedBack$15() {
        this.mHapticFeedbackUtil.performExtHapticFeedback(HAPTIC_V2_VOLUME_MAX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performHapticFeedBack$16() {
        this.mHapticFeedbackUtil.performExtHapticFeedback(HAPTIC_V2_VOLUME_MIN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performHapticFeedBack$17() {
        VolumeUtil.performHapticFeedback(this.mVolumeView, 268435460);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showVolumePanelH$8() {
        if (this.mVolumeView.isFooterVisibility() && this.mNeedShowDialog) {
            this.mSilenceModeObserver.showDndPopupWindow();
        }
        AccessibilityManager accessibilityManager = this.mAccessibilityMgr;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        float progress = (getActiveColumn().slider.getProgress() * 1.0f) / getActiveColumn().slider.getMax();
        int stream = getActiveColumn().getStream();
        this.mVolumeView.announceForAccessibility(this.mContext.getString(stream != 0 ? stream != 2 ? stream != 3 ? stream != 4 ? stream != 5 ? stream != 10 ? stream != 11 ? R.string.volume_dialog_shown_unknown_talkback : R.string.volume_dialog_shown_voice_assistant_talkback : R.string.volume_dialog_shown_accessibility_talkback : R.string.volume_dialog_shown_notification_talkback : R.string.volume_dialog_shown_alarm_talkback : R.string.volume_dialog_shown_music_talkback : R.string.volume_dialog_shown_ring_tone_talkback : R.string.volume_dialog_shown_call_talkback, NumberFormat.getPercentInstance().format(progress)));
        if (this.mRingerModeLayout.getVisibility() == 0) {
            this.mVolumeView.announceForAccessibility(this.mContext.getString(this.mSilenceModeObserver.getSilenceMode() ? R.string.miui_silent_mode_on : R.string.miui_silent_mode_off));
            this.mVolumeView.announceForAccessibility(this.mContext.getString(this.mSilenceModeObserver.getZenMode() ? R.string.miui_dnd_mode_on : R.string.miui_dnd_mode_off));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateExpandedAnimator$13(View view, ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        view.setAlpha(fFloatValue);
        Drawable drawable = this.mVolumeContentBlurDrawable;
        if (drawable != null) {
            drawable.setAlpha((int) (fFloatValue * 255.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateExpandedH$6(VolumeColumn volumeColumn) {
        volumeColumn.setExpanded(this.mExpanded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeadsetIconW$10() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_PHONE_IN_CALL, VolumeUtils.isPhoneCall(this.mContext));
        AudioManager audioManager = this.mAudioManager;
        int i2 = this.mActiveStream;
        if (i2 == -1 || i2 == 11) {
            i2 = 3;
        } else if (i2 == 6) {
            i2 = 0;
        }
        bundle.putInt("device", audioManager.getDevicesForStream(i2));
        sendAudioStateW(12, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTempColumnW$7() {
        Bundle bundle = new Bundle();
        boolean zIsMusicActive = this.mAudioManager.isMusicActive();
        boolean z2 = false;
        boolean zIsStreamActive = AudioSystem.isStreamActive(0, 0);
        int i2 = this.mActiveStream;
        boolean z3 = ((i2 == 0 || i2 == 6) && zIsMusicActive) || (i2 == 3 && zIsStreamActive) || (i2 == getVoiceAssistStreamType() && this.mActiveStream > 0);
        if (!this.mExpanded && (z3 || this.mShowA11yStream)) {
            z2 = true;
        }
        this.mShouldTempBeVisible = z2;
        Log.d(this.TAG, "updateTempColumnW: mShouldTempBeVisible:" + this.mShouldTempBeVisible + " mExpanded:" + this.mExpanded + " mShowA11yStream:" + this.mShowA11yStream + " streamVisible:" + z3);
        setActiveStreamPosition();
        bundle.putBoolean(HeadsetUtil.MODE_MUSIC, zIsMusicActive);
        bundle.putBoolean("voice", zIsStreamActive);
        sendAudioStateW(11, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$vibrateH$11() {
        ((Vibrator) this.mContext.getSystemService("vibrator")).vibrate(300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfigChanged(int i2) {
        boolean z2 = this.mLastDensity != this.mContext.getResources().getDisplayMetrics().densityDpi;
        boolean zUpdateFlipTinyLand = FlipUtils.updateFlipTinyLand(this.mContext);
        boolean z3 = this.mLastFlipTinyLand != zUpdateFlipTinyLand;
        this.mLastFlipTinyLand = zUpdateFlipTinyLand;
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zBlurChanged = configUtils.blurChanged(i2);
        boolean z4 = (i2 & SENSITIVE_CONFIG_CHANGES) != 0;
        boolean zLayoutDirectionChanged = configUtils.layoutDirectionChanged(i2);
        if (z4 || zBlurChanged || z2 || this.mFlipTinyChanged || z3 || zLayoutDirectionChanged) {
            Log.d(this.TAG, "onConfigChanged  configCompanion = " + z4 + " configChanges = " + i2 + " SENSITIVE_CONFIG_CHANGES = " + SENSITIVE_CONFIG_CHANGES + " blurChanged = " + zBlurChanged + " densityChanged = " + z2 + " mFlipTinyChanged = " + this.mFlipTinyChanged + " flipTinyLandChanged = " + z3 + " isDirectionChange = " + zLayoutDirectionChanged);
            this.mNeedReInit = true;
            this.mLastDensity = this.mContext.getResources().getDisplayMetrics().densityDpi;
        }
        if (this.mNeedShowDialog) {
            dismissH(8);
        }
        updateVolumePanelSize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExpandClicked() {
        Log.d(this.TAG, "onExpandClicked");
        if (this.mLockTaskModeState == 2) {
            Util.showPinningEscapeToast();
            return;
        }
        if (this.mSilenceModeObserver.isDndPopupShowing()) {
            this.mSilenceModeObserver.dismissPopupWindow();
            return;
        }
        if (this.mVolumeView.isAnimating()) {
            return;
        }
        if (this.mVolumeView.isShowAnimating()) {
            this.mVolumeView.cancelShowAnimation();
        }
        boolean z2 = this.mExpanded;
        final boolean z3 = !z2;
        MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) getActiveColumn().slider;
        if (!z2) {
            miuiVolumeSeekBar.resetView();
            if (this.mKeyAnimatingStream != -1) {
                miuiVolumeSeekBar.cancelKeyAnim();
                this.mKeyAnimatingStream = -1;
            }
        }
        updateExpandedAnim(z3, new Runnable() { // from class: com.android.systemui.miui.volume.B
            @Override // java.lang.Runnable
            public final void run() {
                this.f1464a.lambda$onExpandClicked$12(z3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStateChangedH(VolumeDialogController.State state) {
        boolean zIsAnimating = this.mVolumeView.isAnimating();
        if (this.mShowing || this.mNeedShowDialog) {
            Log.i(this.TAG, String.format("onStateChangedH: activeStream: %s, mActiveStream: %s, animating: %s, mNeedShowDialog: %s, mVolumeView.isShown: %s", Integer.valueOf(state.activeStream), Integer.valueOf(this.mActiveStream), Boolean.valueOf(zIsAnimating), Boolean.valueOf(this.mNeedShowDialog), Boolean.valueOf(this.mVolumeView.isShown())));
        }
        this.mState = state;
        if (zIsAnimating) {
            this.mPendingStateChanged = true;
            return;
        }
        this.mDynamic.clear();
        if (this.mNeedShowDialog) {
            for (int i2 = 0; i2 < state.states.size(); i2++) {
                int iKeyAt = state.states.keyAt(i2);
                if (((VolumeDialogController.StreamState) state.states.valueAt(i2)).dynamic) {
                    this.mDynamic.put(iKeyAt, true);
                    if (findColumn(iKeyAt) == null) {
                        Log.d(this.TAG, "add dynamic column: " + iKeyAt);
                        addColumn(iKeyAt, true, true);
                    }
                }
            }
        }
        int i3 = this.mActiveStream;
        int i4 = state.activeStream;
        if (i3 != i4) {
            this.mActiveStream = i4;
            this.mShowA11yStream = Util.isEnableAccessibility(this.mContext) && this.mActiveStream == 10;
            updateColumnH(getActiveColumn());
            rescheduleTimeoutH();
            if (!this.isControlCenterPanel) {
                MiuiVolumeDialogView miuiVolumeDialogView = this.mVolumeView;
                int i5 = this.mActiveStream;
                miuiVolumeDialogView.updateFooterVisibility((i5 == 0 || i5 == 6 || FlipUtils.isFlipTiny()) ? false : true);
            }
        }
        for (VolumeColumn volumeColumn : this.mColumns) {
            updateVolumeColumnH(volumeColumn);
            updateSuperVolumeView(volumeColumn);
        }
        if (this.mShouldTempBeVisible) {
            updateVolumeColumnH(this.mTempColumn);
        }
        updateRingerH();
        updateHeadsetIconW();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performKeyAnimFromVolumeChange(boolean z2, boolean z3, boolean z4) {
        Log.i(this.TAG, "performKeyAnimFromVolumeChange, isShowUI: " + z2 + ", isMax:" + z3 + ", isMin:" + z4);
        VolumeColumn activeColumn = getActiveColumn();
        boolean z5 = false;
        boolean z6 = activeColumn.slider.getProgress() == activeColumn.slider.getMax();
        boolean z7 = activeColumn.slider.getProgress() == activeColumn.slider.getMin();
        boolean z8 = !z2;
        boolean z9 = z6 && z3;
        if (z7 && z4) {
            z5 = true;
        }
        performKeyAnimInner(z2, z8, z9, z5);
    }

    private void performKeyAnimInner(boolean z2, boolean z3, boolean z4, boolean z5) {
        VolumeDialogController.StreamState ss;
        if (!this.mShowing || isUpdateExpandedAnimating() || this.mVolumeView.isAnimating() || this.mVolumeView.isExpandCollapsedAnimating() || this.isDismissWaitCancelKeyAnim) {
            return;
        }
        VolumeColumn volumeColumnFindColumn = this.isControlCenterPanel ? findColumn(3) : getActiveColumn();
        MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) volumeColumnFindColumn.slider;
        Log.i(this.TAG, "performKeyAnimInner, activeColumnStream: " + volumeColumnFindColumn.getStream() + ", mKeyAnimatingStream: " + this.mKeyAnimatingStream);
        if (volumeColumnFindColumn.getTracking()) {
            if (z3) {
                miuiVolumeSeekBar.cancelKeyAnim();
                return;
            }
            return;
        }
        int i2 = this.mKeyAnimatingStream;
        if (i2 != -1 && i2 != volumeColumnFindColumn.getStream()) {
            ((MiuiVolumeSeekBar) findColumn(this.mKeyAnimatingStream).slider).volumeKeyUp();
        }
        if (z3) {
            miuiVolumeSeekBar.volumeKeyUp();
            this.mKeyAnimatingStream = -1;
            this.mNotLongPressCount = 0;
        } else {
            if (!z2 || (ss = volumeColumnFindColumn.getSs()) == null) {
                return;
            }
            if ((z4 && ss.level == ss.levelMax) || (z5 && ss.level == ss.levelMin)) {
                miuiVolumeSeekBar.volumeKeyDown(z4, this.mExpanded);
                this.mKeyAnimatingStream = volumeColumnFindColumn.getStream();
            }
        }
    }

    private StatusBarGuideParams printStatusBarParamsRaw(int i2, String str, int i3) {
        return new StatusBarGuideParams.Builder().setRightText(new StatusBarGuideParams.TextParams(this.mContext.getResources().getString(i2), Integer.valueOf(this.mContext.getColor(i3)), true)).setLeftIcon(new StatusBarGuideParams.IconParams(str, 0, "lottie", "raw")).create();
    }

    private StatusBarGuideParams printStatusBarParamsSvg(int i2, int i3, int i4) {
        return new StatusBarGuideParams.Builder().setLeftText(new StatusBarGuideParams.TextParams(this.mContext.getResources().getString(i2), Integer.valueOf(this.mContext.getColor(i4)))).setRightIcon(new StatusBarGuideParams.IconParams(this.mContext.getResources().getResourceEntryName(i3), 1, "svg", "drawable")).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reCheckAllH() {
        Log.d(this.TAG, "recheckH ALL");
        trimObsoleteH();
        Iterator<VolumeColumn> it = this.mColumns.iterator();
        while (it.hasNext()) {
            updateVolumeColumnH(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recheckH(VolumeColumn volumeColumn) {
        Log.d(this.TAG, "recheckH " + volumeColumn.getStream());
        updateVolumeColumnH(volumeColumn);
    }

    private void recordCountIfNeed(String str, int i2) {
        int i3 = this.mLockRecordTypes;
        if ((i3 & i2) == 0) {
            this.mLockRecordTypes = i3 | i2;
            Message messageObtain = Message.obtain(this.mHandler, 9);
            messageObtain.arg1 = i2;
            this.mHandler.sendMessageDelayed(messageObtain, 2000L);
            VolumeEventTracker.trackAdjustVolumeStream(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordVolumeChanged(VolumeColumn volumeColumn) {
        if (this.mExpanded && volumeColumn.getTracking()) {
            int stream = volumeColumn.getStream();
            if (stream == 2) {
                recordCountIfNeed(VolumeEventTracker.STREAM_RING, 2);
                return;
            }
            if (stream == 3) {
                recordCountIfNeed(VolumeEventTracker.STREAM_MEDIA, 1);
                return;
            }
            if (stream == 4) {
                recordCountIfNeed(VolumeEventTracker.STREAM_ALARM, 4);
            } else if (stream != 5) {
                recordCountIfNeed(VolumeEventTracker.STREAM_OTHER, 22);
            } else {
                recordCountIfNeed(VolumeEventTracker.STREAM_NOTIFICATION, 8);
            }
        }
    }

    private void releaseVolumeColumn() {
        Iterator<VolumeColumn> it = this.mColumns.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
        this.mTempColumn.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rescheduleTimeoutH() {
        if (this.mNeedShowDialog) {
            this.mHandler.removeMessages(2);
            int iComputeTimeoutH = computeTimeoutH();
            H h2 = this.mHandler;
            h2.sendMessageDelayed(h2.obtainMessage(2, 3, 0), iComputeTimeoutH);
            Log.i(this.TAG, "rescheduleTimeout " + iComputeTimeoutH + " mActiveStream:" + this.mActiveStream);
            this.mController.userActivity();
        }
    }

    private void sendAudioStateW(int i2, Bundle bundle) {
        Message messageObtainMessage = this.mHandler.obtainMessage(i2);
        messageObtainMessage.setData(bundle);
        if (this.mHandler.hasMessages(i2)) {
            this.mHandler.removeMessages(i2);
        }
        this.mHandler.sendMessage(messageObtainMessage);
    }

    private void setActiveStreamPosition() {
        this.mVolumeView.setActiveStreamPosition(getActiveStreamIndex(), shouldShowDoubleVolume() ? 2 : 1);
    }

    private void setResourceClassLoader(Resources resources, ClassLoader classLoader) {
        try {
            Field declaredField = Resources.class.getDeclaredField("mClassLoader");
            declaredField.setAccessible(true);
            declaredField.set(resources, classLoader);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamImportantH(int i2, boolean z2) {
        for (VolumeColumn volumeColumn : this.mColumns) {
            if (volumeColumn.getStream() == i2) {
                volumeColumn.setImportant(z2);
                return;
            }
        }
    }

    private boolean shouldBeVisibleH(VolumeColumn volumeColumn, boolean z2) {
        if (volumeColumn.getStream() == 0 && (this.isControlCenterPanel || this.mExpanded)) {
            return false;
        }
        if (volumeColumn.getStream() == 10) {
            return !this.mExpanded && this.mShowA11yStream;
        }
        if (volumeColumn.getStream() == getVoiceAssistStreamType()) {
            return !this.mExpanded && z2;
        }
        if (volumeColumn.getStream() != 5) {
            return volumeColumn.getStream() >= 100 ? !this.mExpanded && z2 : (this.mExpanded && volumeColumn.view.getVisibility() == 0) || (this.mExpanded && (volumeColumn.getImportant() || (z2 && this.mNeedShowDialog))) || (!this.mExpanded && z2);
        }
        boolean z3 = this.mExpanded;
        return (z3 && this.mIsNotifySingle) || (!z3 && z2);
    }

    private boolean shouldShowDoubleVolume() {
        int voiceAssistStreamType = getVoiceAssistStreamType();
        int i2 = this.mActiveStream;
        return voiceAssistStreamType == i2 || this.mShouldTempBeVisible || i2 == 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSVToast(int i2, boolean z2) {
        int i3;
        Log.d(this.TAG, "showSVToast: indexAdd " + VolumeUtils.getIndexAdd() + ",current " + i2 + ",shouldShowInitial " + z2);
        if (VolumeUtils.getSUPER_VOLUME_VERSION_P() && VolumeUtils.getIndexAdd() != 1 && i2 == 1) {
            i3 = R.string.super_volume_nearly_full_toast;
        } else if (VolumeUtils.getSUPER_VOLUME_VERSION_P() && z2 && VolumeUtils.isInitial()) {
            i3 = R.string.super_volume_initial_remind_toast;
            VolumeUtils.setInitial(false);
        } else {
            i3 = R.string.super_volume_toast;
        }
        this.mWorkerHandler.removeMessages(14);
        H h2 = this.mWorkerHandler;
        h2.sendMessageDelayed(h2.obtainMessage(14, i3, 0), 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSafetyWarningH(int i2) {
        Runnable runnable = new Runnable() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.9
            @Override // java.lang.Runnable
            public void run() {
                VolumePanelViewController.this.mHandler.removeMessages(2);
                int recommendedTimeoutMillis = VolumePanelViewController.this.mAccessibilityMgr.getRecommendedTimeoutMillis(Constant.MAX_STR_LENGTH, 6);
                Intent intent = new Intent("miui.intent.action.ACTIVITY_VOLUME_SAFETY_DIALOG");
                intent.addFlags(268697600);
                intent.putExtra(Const.Param.TIMEOUT_MIN, recommendedTimeoutMillis);
                VolumePanelViewController.this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
                VolumePanelViewController.this.mIsSafetyShowing = true;
                if (VolumePanelViewController.this.mNeedShowDialog) {
                    VolumePanelViewController.this.mHandler.sendMessageDelayed(VolumePanelViewController.this.mHandler.obtainMessage(2, 3, 0), recommendedTimeoutMillis);
                }
                VolumePanelViewController.this.mController.userActivity();
                VolumePanelViewController.this.mHandler.sendEmptyMessage(4);
            }
        };
        if (isScreenOn() || this.mShowing || (i2 & InputDeviceCompat.SOURCE_GAMEPAD) != 0) {
            dismissKeyguardThenExecute(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuperVolumeToast(final VolumeColumn volumeColumn, final int i2, final boolean z2) {
        VolumeDialogController.State state;
        if (this.mSupportSuperVolume || this.mVoiceSupportSuperVolume) {
            if ((this.mNeedShowDialog || this.mShowing) && isStreamValid(volumeColumn.getStream(), this.mInitialMaxVolume.length) && volumeColumn.view.getVisibility() == 0 && (state = this.mState) != null && ((VolumeDialogController.StreamState) state.states.get(volumeColumn.getStream())) != null) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.8
                    @Override // java.lang.Runnable
                    public void run() {
                        int i3 = i2;
                        Log.d(VolumePanelViewController.this.TAG, "showSuperVolumeToast: column.stream=" + volumeColumn.getStream() + ", shouldShow=" + z2 + ", support=" + VolumePanelViewController.this.mSupportSuperVolume + ", voiceSupport=" + VolumePanelViewController.this.mVoiceSupportSuperVolume + ", mIsSpeakerOn=" + VolumePanelViewController.this.mIsSpeakerOn + ", mIsHeadset=" + VolumePanelViewController.this.mIsHeadset + ", mIsEarpiece=" + VolumePanelViewController.this.mIsEarpiece + ", mShowing=" + VolumePanelViewController.this.mShowing + ", mNeedShowDialog=" + VolumePanelViewController.this.mNeedShowDialog + ", supportSuperVolumeStream=" + VolumePanelViewController.this.supportSuperVolumeStream(volumeColumn.getStream()) + ", currentLevel=" + i3 + ", initialMaxLevel=" + VolumePanelViewController.this.mInitialMaxVolume[volumeColumn.getStream()] + ", superAddIndex=" + volumeColumn.getSuperAddIndex() + ", version=" + VolumeUtils.getSUPER_VOLUME_VERSION());
                        boolean z3 = (!VolumePanelViewController.this.mIsSpeakerOn || VolumePanelViewController.this.mIsHeadset || VolumePanelViewController.this.mIsEarpiece) ? false : true;
                        if ((VolumePanelViewController.this.mSupportSuperVolume && z3 && VolumePanelViewController.this.supportSuperVolumeStream(volumeColumn.getStream()) && volumeColumn.getStream() != 0) || (VolumePanelViewController.this.mVoiceSupportSuperVolume && volumeColumn.getStream() == 0 && z3 && !VolumeUtils.getSUPER_VOLUME_VERSION_P())) {
                            int iIntValue = VolumePanelViewController.this.mInitialMaxVolume[volumeColumn.getStream()].intValue();
                            if (!VolumeUtils.MEDIA_VOL_STEPS_IS_15 && volumeColumn.getStream() == 3) {
                                int i4 = VolumeUtils.SUPER_VOLUME_STEP_TRANSFORM;
                                i3 /= i4;
                                iIntValue /= i4;
                            }
                            int i5 = i3 - iIntValue;
                            if (volumeColumn.getSuperAddIndex() != i5) {
                                if (i5 > 0 && z2 && (VolumeUtils.getSUPER_VOLUME_VERSION_P() || volumeColumn.getSuperAddIndex() <= 0)) {
                                    VolumePanelViewController.this.showSVToast(i5, true);
                                }
                                volumeColumn.setSuperAddIndex(i5);
                            }
                        }
                    }
                }, 50L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastInStatusBar(int i2, int i3, int i4, String str) {
        U.d dVar = new U.d();
        String strS = dVar.s(printStatusBarParamsSvg(i2, i3, i4));
        StatusBarGuideParams statusBarGuideParamsPrintStatusBarParamsRaw = printStatusBarParamsRaw(i2, str, i4);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_STRONG_TOAST_CATEGORY, VALUE_STRONG_TOAST_CATEGORY);
        bundle.putParcelable("target", null);
        bundle.putString(KEY_STATUS_BAR_STRONG_TOAST, VALUE_STATUS_BAR_STRONG_TOAST);
        bundle.putString("package_name", this.mContext.getPackageName());
        bundle.putString(KEY_PARAM, strS);
        if (isSuportIsland() && VolumeUtil.showIslandByPkg()) {
            bundle.putString(KEY_ISLAND_PARAM, dVar.s(statusBarGuideParamsPrintStatusBarParamsRaw));
            bundle.putString(KEY_NOTIFY_ID, "vol_island_notify");
            Log.d(this.TAG, "--- showToastInStatusBar show isl  --- : ");
        }
        invokeStatusBar(KEY_STRONG_TOAST_ACTION, bundle);
        this.mVolumePanelView.announceForAccessibility(this.mContext.getResources().getString(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVolumePanelH(int i2) {
        Log.i(this.TAG, "showVolumeDialogH mActiveStream:" + this.mActiveStream + " mLockTaskModeState:" + this.mLockTaskModeState + " disableVolumeDialog:" + this.disableVolumeDialog + " mNeedShowDialog=" + this.mNeedShowDialog);
        if (this.disableVolumeDialog && this.mNeedShowDialog) {
            return;
        }
        this.mShowing = true;
        Events.writeEvent(this.mContext, 0, Integer.valueOf(i2), Boolean.valueOf(this.mKeyguard.isKeyguardLocked()));
        updateLockTaskModeState(this.mActivityManager.getLockTaskModeState());
        this.mController.notifyVisible(true);
        updateMediaIconAnim(this.mColumns.get(0), false, true);
        this.mVolumeView.showH(new Runnable() { // from class: com.android.systemui.miui.volume.A
            @Override // java.lang.Runnable
            public final void run() {
                this.f1462a.lambda$showVolumePanelH$8();
            }
        });
        updateActiveIconTint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean supportSuperVolumeStream(int i2) {
        return (VolumeUtils.getIS_N17() && Arrays.asList(mSupportSuperVolumeStream).contains(Integer.valueOf(i2))) || (isStreamValid(i2, VolumeUtils.getSupportSuperVolumeStreamType().length) ? VolumeUtils.getSupportSuperVolumeStreamType()[i2].booleanValue() : false);
    }

    private void trimObsoleteH() {
        if (Util.DEBUG) {
            Log.d(this.TAG, "trimObsoleteH");
        }
        for (int size = this.mColumns.size() - 1; size >= 0; size--) {
            VolumeColumn volumeColumn = this.mColumns.get(size);
            if (volumeColumn.getSs() != null && volumeColumn.getSs().dynamic && !this.mDynamic.get(volumeColumn.getStream())) {
                if (Util.DEBUG) {
                    String str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("trimObsoleteH remove");
                    sb.append(volumeColumn.getStream());
                    sb.append("reason: ");
                    sb.append(volumeColumn.getSs() == null ? "column.ss is null" : "column.ss.dynamic is " + volumeColumn.getSs().dynamic);
                    Log.d(str, sb.toString());
                }
                this.mColumns.remove(size);
                this.mVolumeColumns.removeView(volumeColumn.view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlockRecordType(int i2) {
        this.mLockRecordTypes = (~i2) & this.mLockRecordTypes;
    }

    private void updateActiveIconTint() {
        SeekBar seekBar;
        VolumeColumn activeColumn = getActiveColumn();
        if (activeColumn == null || (seekBar = activeColumn.slider) == null) {
            return;
        }
        updateIconTint(seekBar.getProgress() / activeColumn.slider.getMax(), activeColumn);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateColumnH(VolumeColumn volumeColumn) {
        Log.d(this.TAG, "updateColumnH");
        if (!this.mShowing) {
            trimObsoleteH();
        }
        if (this.mNeedShowDialog) {
            BoostHelper.getInstance().boostWithCpuFreq(2000L, this.mVolumeView);
        }
        updateTempColumnW();
        Iterator<VolumeColumn> it = this.mColumns.iterator();
        while (it.hasNext()) {
            VolumeColumn next = it.next();
            boolean zShouldBeVisibleH = shouldBeVisibleH(next, volumeColumn == next);
            Util.setVisOrGone(next.view, zShouldBeVisibleH);
            updateSuperVolumeView(next);
            com.miui.blur.sdk.backdrop.a aVar = (com.miui.blur.sdk.backdrop.a) next.view;
            if (aVar.isBackdropBlurSupported()) {
                aVar.setBlurEnabled(true ^ this.mExpanded);
            }
            next.setSliderResource(this.mExpanded);
            if (zShouldBeVisibleH) {
                next.updateIcon();
            }
            if (zShouldBeVisibleH) {
                next.setSliderTintColorList(this.mExpanded);
            }
            next.setSize(this.mExpanded, this.mIsNotifySingle);
        }
    }

    private void updateExpandedAnim(boolean z2, Runnable runnable) {
        this.mVolumeContentBlurDrawable = null;
        if (!z2) {
            runnable.run();
            return;
        }
        if (!this.mShouldTempBeVisible) {
            runnable.run();
            return;
        }
        if (isUpdateExpandedAnimating()) {
            return;
        }
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            Drawable background = this.mVolumeContentView.getBackground();
            if (background instanceof BackgroundBlurDrawable) {
                this.mVolumeContentBlurDrawable = background;
            }
        }
        this.updateExpandedAnimator = updateExpandedAnimator(true, this.mVolumeContentView, runnable);
    }

    private ValueAnimator updateExpandedAnimator(final boolean z2, final View view, final Runnable runnable) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(70L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.volume.z
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f1516a.lambda$updateExpandedAnimator$13(view, valueAnimator);
            }
        });
        valueAnimatorOfFloat.addListener(new Animator.AnimatorListener(this) { // from class: com.android.systemui.miui.volume.VolumePanelViewController.12
            boolean hasCanceled = false;

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(@NonNull Animator animator) {
                this.hasCanceled = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NonNull Animator animator) {
                Runnable runnable2;
                if (!this.hasCanceled && (runnable2 = runnable) != null) {
                    runnable2.run();
                }
                if (z2) {
                    view.setAlpha(1.0f);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(@NonNull Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@NonNull Animator animator) {
            }
        });
        valueAnimatorOfFloat.start();
        return valueAnimatorOfFloat;
    }

    private void updateExpandedH(boolean z2, boolean z3) {
        updateExpandedH(z2, z3, false);
    }

    private void updateFoldState(boolean z2) {
        boolean zUpdateFlipTiny = FlipUtils.updateFlipTiny(z2);
        this.mFlipTinyChanged = this.mLastFlipTiny != zUpdateFlipTiny;
        this.mLastFlipTiny = zUpdateFlipTiny;
        Boolean bool = this.mIsFolded;
        if (bool == null || bool.booleanValue() == z2) {
            this.mIsFolded = Boolean.valueOf(z2);
        } else {
            this.mIsFolded = Boolean.valueOf(z2);
            dismissH(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHeadsetIconW() {
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.K
            @Override // java.lang.Runnable
            public final void run() {
                this.f1476a.lambda$updateHeadsetIconW$10();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIconResWhenDevicesChanged() {
        Iterator<VolumeColumn> it = this.mColumns.iterator();
        while (it.hasNext()) {
            VolumeColumn next = it.next();
            if (shouldBeVisibleH(next, next == getActiveColumn())) {
                if (this.mIsNeedShowHeadSetIcon && ((this.mActiveStream == next.getStream() && Arrays.asList(mSupportUseHeadsetIcon).contains(Integer.valueOf(next.getStream()))) || (this.mExpanded && next.getStream() == 3))) {
                    next.setHeadSetIconRes();
                } else if (next.getStream() == 0 && this.mActiveStream == next.getStream() && this.mIsSpeakerOn) {
                    next.setSpeakerIconRes();
                } else if (next.getStream() == 3) {
                    next.setMediaIconRes(this.mIsNeedShowHeadSetIcon, this.mActiveStream, this.mExpanded);
                } else {
                    next.resetIconRes();
                }
                next.updateIcon();
            }
        }
    }

    private void updateIconResWhenExpandChanged() {
        for (VolumeColumn volumeColumn : this.mColumns) {
            int stream = volumeColumn.getStream();
            if (stream == 2) {
                volumeColumn.setRingIconRes();
            } else if (stream == 3) {
                volumeColumn.setMediaIconRes(this.mIsNeedShowHeadSetIcon, this.mActiveStream, this.mExpanded);
            } else if (stream == 4) {
                volumeColumn.setAlarmIconRes();
            } else if (stream == 5) {
                volumeColumn.setNotificationIconRes();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIconTint(float f2, VolumeColumn volumeColumn) {
        if (!this.mExpanded && volumeColumn.getStream() == this.mActiveStream) {
            this.mVolumeView.updateExpandButtonColor(MiuiVolumeDialogRes.getExpandedIconColorRes(((double) f2) < 0.9d && (Util.isSupportBlurS() || Util.isBackdropBlurSupported() || (!Util.isBackdropBlurSupported() && Util.isDarkTheme(this.mContext)))), false);
        }
        volumeColumn.updateSliderRatio();
    }

    private void updateLockTaskModeState(int i2) {
        this.mLockTaskModeState = i2;
        this.mVolumeView.setLockTaskModeState(i2);
    }

    private void updateMediaIconAnim(VolumeColumn volumeColumn, boolean z2, boolean z3) {
        if (volumeColumn.getStream() == 3) {
            volumeColumn.setMediaIconRes(this.mIsNeedShowHeadSetIcon, this.mActiveStream, this.mExpanded);
            volumeColumn.updateIcon(z2);
        }
    }

    private void updateRingerH() {
        int i2;
        VolumeDialogController.State state = this.mState;
        if (state == null || ((VolumeDialogController.StreamState) state.states.get(2)) == null) {
            return;
        }
        VolumeDialogController.State state2 = this.mState;
        int i3 = state2.zenMode;
        this.mSilenceModeObserver.updateVolumeInfo(state2.ringerModeInternal != 2, i3 == 3 || i3 == 2 || i3 == 1);
        int i4 = this.mRingerMode;
        if (i4 != -1 && i4 != (i2 = this.mState.ringerModeInternal) && i2 == 1 && Settings.System.getIntForUser(this.mContext.getContentResolver(), "vibrate_in_silent", 1, this.mCurrentUserId) == 1) {
            H h2 = this.mHandler;
            h2.sendMessageDelayed(h2.obtainMessage(8), 300L);
        }
        this.mRingerMode = this.mState.ringerModeInternal;
    }

    private void updateSuperVolumeText(TextView textView, int i2) {
        if (VolumeUtils.getIndexAdd() <= 1 || !VolumeUtils.getSUPER_VOLUME_VERSION_P()) {
            return;
        }
        textView.setText(VolumeUtils.getSuperVolumePercent(this.mContext, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSuperVolumeView(VolumeColumn volumeColumn) {
        VolumeDialogController.State state;
        VolumeDialogController.StreamState streamState;
        if (!this.mSupportSuperVolume || (state = this.mState) == null || (streamState = (VolumeDialogController.StreamState) state.states.get(volumeColumn.getStream())) == null) {
            return;
        }
        if (!this.mExpanded && !this.mShouldTempBeVisible && volumeColumn.getStream() == this.mActiveStream && !supportSuperVolumeStream(volumeColumn.getStream())) {
            this.mSuperVolumeBg.setVisibility(8);
        }
        if (this.mIsHeadset || this.mIsEarpiece) {
            this.mSuperVolumeBg.setVisibility(8);
            volumeColumn.superVolume.setVisibility(8);
            ((MiuiVolumeSeekBar) volumeColumn.slider).updateSuperVolume(false, 0);
            return;
        }
        if (this.mIsSpeakerOn && supportSuperVolumeStream(volumeColumn.getStream())) {
            int lastAudibleLevel = volumeColumn.getLastAudibleLevel();
            int iIntValue = this.mInitialMaxVolume[volumeColumn.getStream()].intValue();
            if (!VolumeUtils.MEDIA_VOL_STEPS_IS_15 && volumeColumn.getStream() == 3) {
                int i2 = VolumeUtils.SUPER_VOLUME_STEP_TRANSFORM;
                lastAudibleLevel /= i2;
                iIntValue /= i2;
            }
            boolean z2 = streamState.levelMax == volumeColumn.getLastAudibleLevel() || (lastAudibleLevel > iIntValue && VolumeUtils.getSUPER_VOLUME_VERSION_P());
            int i3 = lastAudibleLevel - iIntValue;
            if ((this.mExpanded || volumeColumn.getStream() == this.mActiveStream) && volumeColumn.view.getVisibility() == 0) {
                if (volumeColumn.getStream() == this.mActiveStream) {
                    if (z2) {
                        updateSuperVolumeText(this.mSuperVolume, i3);
                    }
                    this.mVolumeView.updateSuperVolumeShowX(false);
                    this.mVolumeView.updateSuperVolumeVisibility(z2);
                }
                if (z2 && this.mExpanded) {
                    updateSuperVolumeText(volumeColumn.superVolume, i3);
                }
                Util.setVisOrGone(volumeColumn.superVolume, z2 && this.mExpanded);
                MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) volumeColumn.slider;
                miuiVolumeSeekBar.updateSuperVolume(z2, i3);
                if (miuiVolumeSeekBar.getAnimateState() == 3 && !z2) {
                    this.mRecordSuperVolumeScale = true;
                    this.mRecordSuperVolumeTransition = true;
                }
                Log.d(this.TAG, "updateSuperVolumeViewH: column.stream=" + volumeColumn.getStream() + ", visible=" + z2 + ", add=" + i3);
            }
            if (this.mExpanded || this.mActiveStream == 3 || !this.mShouldTempBeVisible || volumeColumn.getStream() != this.mTempColumn.getStream()) {
                return;
            }
            if (z2) {
                updateSuperVolumeText(this.mSuperVolume, i3);
            }
            this.mVolumeView.updateSuperVolumeShowX(this.mTempColumn.getStream() == 3);
            this.mVolumeView.updateSuperVolumeVisibility(z2);
            Util.setVisOrGone(volumeColumn.superVolume, false);
            ((MiuiVolumeSeekBar) this.mTempColumn.slider).updateSuperVolume(z2, i3);
            Log.d(this.TAG, "updateSuperVolumeViewH: mTempColumn.stream=" + this.mTempColumn.getStream() + ", visible=" + z2);
        }
    }

    private void updateSuperVolumeViewColor() {
        boolean z2 = false;
        boolean z3 = getActiveColumn().getSs() != null && getActiveColumn().getSs().muted;
        if (this.mTempColumn.view.getVisibility() == 0 && this.mShouldTempBeVisible && this.mTempColumn.getStream() == 3) {
            if (this.mTempColumn.getSs() != null && this.mTempColumn.getSs().muted) {
                z2 = true;
            }
            z3 = z2;
        }
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            Util.setMiViewBlurAndBlendColor(this.mSuperVolume, this.mExpanded, this.mContext, 1, z3 ? this.mContext.getResources().getIntArray(R.array.miui_seekbar_fg_blend_colors_disabled_collapsed) : this.mContext.getResources().getIntArray(R.array.miui_seekbar_fg_blend_colors_collapsed), false);
        } else {
            this.mSuperVolume.setBackgroundResource(z3 ? R.drawable.miui_super_volume_view_disabled : R.drawable.miui_super_volume_view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateTempColumnH(boolean r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.miui.volume.VolumePanelViewController.updateTempColumnH(boolean, boolean):void");
    }

    private void updateTempColumnW() {
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.y
            @Override // java.lang.Runnable
            public final void run() {
                this.f1515a.lambda$updateTempColumnW$7();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVoiceAssistStreamIcon() {
        VolumeColumn column = getColumn(getVoiceAssistStreamType());
        if (column == null) {
            return;
        }
        column.setSupportSuperXiaoai(Util.isSupportSuperXiaoai(this.mContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVolumeColumnH(VolumeColumn volumeColumn) {
        VolumeDialogController.StreamState streamState;
        VolumeDialogController.State state = this.mState;
        if (state == null || (streamState = (VolumeDialogController.StreamState) state.states.get(volumeColumn.getStream())) == null) {
            return;
        }
        boolean z2 = true;
        if (volumeColumn.getSs() != null && Math.abs((volumeColumn.slider.getProgress() / volumeColumn.slider.getMax()) - (streamState.level / streamState.levelMax)) <= 0.001f) {
            z2 = false;
        }
        volumeColumn.setSs(streamState);
        int lastAudibleLevel = volumeColumn.getLastAudibleLevel();
        volumeColumn.setLastAudibleLevel(streamState.level);
        if (streamState.level == volumeColumn.getRequestedLevel()) {
            volumeColumn.setRequestedLevel(-1);
        }
        if (volumeColumn.getSs() != null && this.mActiveStream == getVoiceAssistStreamType() && volumeColumn.getStream() == this.mActiveStream) {
            volumeColumn.getSs().levelMax = this.mAssistMaxLevel;
        }
        int i2 = streamState.levelMax * 1000;
        if (i2 != volumeColumn.slider.getMax()) {
            volumeColumn.slider.setMax(i2);
            volumeColumn.progressView.setMaxLevel(streamState.levelMax);
            if (this.mSupportSuperVolume && isStreamValid(volumeColumn.getStream(), this.mInitialMaxVolume.length)) {
                this.mInitialMaxVolume[volumeColumn.getStream()] = Integer.valueOf(VolumeUtils.updateMaxVolume(this.mContext, volumeColumn.getStream()));
            }
        }
        if (lastAudibleLevel != streamState.level && (this.mShowing || this.mNeedShowDialog)) {
            Log.d(this.TAG, String.format("updateVolumeColumnH: stream: %s, level: %s, levelMax: %s, mAutomute: %s, streamMuted: %s, tracking: %s", Integer.valueOf(volumeColumn.getStream()), Integer.valueOf(streamState.level), Integer.valueOf(streamState.levelMax), Boolean.valueOf(this.mAutomute), Boolean.valueOf(streamState.muted), Boolean.valueOf(volumeColumn.getTracking())));
        }
        updateVolumeColumnSliderH(volumeColumn, lastAudibleLevel, z2);
        volumeColumn.setSliderBlendColor(this.mLastExpanded);
        MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) volumeColumn.slider;
        if (this.mNeedShowDialog) {
            volumeColumn.view.setAlpha(1.0f);
            if (!volumeColumn.getTracking() && miuiVolumeSeekBar.getAnimateState() == 0) {
                volumeColumn.view.setScaleX(1.0f);
                volumeColumn.view.setScaleY(1.0f);
                return;
            }
            Log.d(this.TAG, "updateVolumeColumnH: column.tracking: " + volumeColumn.getTracking() + ", slider.getAnimateState(): " + miuiVolumeSeekBar.getAnimateState());
        }
    }

    private void updateVolumeColumnSliderH(VolumeColumn volumeColumn, int i2, boolean z2) {
        volumeColumn.slider.getDrawingRect(new Rect());
        if (volumeColumn.setSliderTintColorList(this.mExpanded)) {
            Log.d(this.TAG, "updateVolumeColumnSliderH CachedSliderTint: column " + volumeColumn.getStream());
            updateSuperVolumeViewColor();
        }
        volumeColumn.slider.setContentDescription(getStreamLabelH(volumeColumn.getSs()));
        if (volumeColumn.getTracking()) {
            return;
        }
        VolumeDialogController.StreamState ss = volumeColumn.getSs();
        Objects.requireNonNull(ss);
        int i3 = ss.level;
        int progress = volumeColumn.slider.getProgress();
        int i4 = i3 * 1000;
        int iProgressToLevel = VolumeUtils.progressToLevel(volumeColumn.slider.getMax(), progress);
        boolean z3 = false;
        boolean z4 = volumeColumn.view.getVisibility() == 0;
        boolean z5 = SystemClock.uptimeMillis() - volumeColumn.getUserAttempt() < 1000;
        if (Util.DEBUG || i2 != i3) {
            VolumeDialogController.StreamState ss2 = volumeColumn.getSs();
            Objects.requireNonNull(ss2);
            Log.d(this.TAG, String.format("updateVolumeColumnSliderH: stream: %s, mActiveStream: %s, streamMute: %s, vlevel: %s, level: %s, progress: %s, newProgress: %s, columnVisible: %s, inGracePeriod: %s, sliderChangeIsNeedAnim: %s", Integer.valueOf(volumeColumn.getStream()), Integer.valueOf(this.mActiveStream), Boolean.valueOf(ss2.muted), Integer.valueOf(i3), Integer.valueOf(iProgressToLevel), Integer.valueOf(progress), Integer.valueOf(i4), Boolean.valueOf(z4), Boolean.valueOf(z5), Boolean.valueOf(z2)));
        }
        this.mHandler.removeMessages(3, volumeColumn);
        boolean z6 = this.mShowing;
        if (z6 && z4 && z5) {
            H h2 = this.mHandler;
            h2.sendMessageAtTime(h2.obtainMessage(3, volumeColumn), volumeColumn.getUserAttempt() + 1000);
            return;
        }
        if (i3 == iProgressToLevel || progress == i4) {
            return;
        }
        if (!z6 || !z4 || !z2) {
            if (volumeColumn.getAnim() != null) {
                volumeColumn.getAnim().cancel();
            }
            volumeColumn.slider.setProgress(i4);
        } else {
            if (volumeColumn.getAnim() != null && volumeColumn.getAnim().isRunning() && volumeColumn.getAnimTargetProgress() == i4) {
                return;
            }
            if (volumeColumn.getAnim() == null) {
                volumeColumn.setAnim(ObjectAnimator.ofInt(volumeColumn.slider, "progress", progress, i4));
                volumeColumn.getAnim().setInterpolator(new DecelerateInterpolator());
            } else {
                volumeColumn.getAnim().cancel();
                volumeColumn.getAnim().setIntValues(progress, i4);
            }
            volumeColumn.setAnimTargetProgress(i4);
            volumeColumn.getAnim().setDuration(200L);
            volumeColumn.getAnim().start();
        }
        if (this.mActiveStream == volumeColumn.getStream() || (!this.mNeedShowDialog && this.mShowing && supportSuperVolumeStream(volumeColumn.getStream()))) {
            z3 = true;
        }
        if (volumeColumn != this.mTempColumn) {
            showSuperVolumeToast(volumeColumn, i3, z3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVolumePanelSize() {
        Log.d(this.TAG, "updateVolumePanelSize");
        for (VolumeColumn volumeColumn : this.mColumns) {
            volumeColumn.setSliderSize(this.mExpanded, this.mIsNotifySingle);
            volumeColumn.setProgressViewBgSize(this.mExpanded, this.mIsNotifySingle);
            volumeColumn.setProgressViewSize(this.mExpanded, this.mIsNotifySingle);
        }
        this.mVolumeView.updateVolumePanelSize();
        reCheckAllH();
        updateMediaIconAnim(this.mColumns.get(0), true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vibrateH() {
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.t
            @Override // java.lang.Runnable
            public final void run() {
                this.f1509a.lambda$vibrateH$11();
            }
        });
    }

    public void destroy() {
        Object obj;
        Log.d(this.TAG, "--- destroy ---");
        MiuiVolumeDialogMotion.Callback callback = this.mVolumePanelDialogController.mMotionCallback;
        if (callback != null) {
            callback.onDismiss();
        }
        this.mVolumePanelView.destroy();
        this.mAccessibility.destroy();
        this.mVolumeView.destroy();
        this.mWorkerThread.quitSafely();
        if (this.mNeedShowDialog) {
            this.mVolumePanelDialogController.destroy();
        }
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mCountDownOffBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mPackagesBroadcastReceiver);
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
        this.mVolumeCompat.removeCallback(this.mControllerCompatCallback);
        if ((Util.IS_MUILT_DISPLAY || FlipUtils.isFlip()) && (obj = this.mFoldStateListener) != null) {
            DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.mDeviceStateManager, obj);
        }
        releaseVolumeColumn();
    }

    public void dismissH(int i2) {
        if (i2 == 9 && this.isControlCenterPanel && this.preValue - this.afterValue != 0) {
            ControlsEventTracker.trackSecondaryVolumeSeekerAdjustEvent(this.mIsSilenceModeOn, findColumn(3).slider.getMax(), EventTracker.getScreenType(this.mContext), this.streamType, this.preValue, this.afterValue);
        }
        boolean zIsAnimating = this.mVolumeView.isAnimating();
        Log.i(this.TAG, "dismissH mShowing:" + this.mShowing + ", reason:" + Events.DISMISS_REASONS[i2] + ", isAnimating: " + zIsAnimating + ", needShowDialog=" + this.mNeedShowDialog + ", KeyAnimatingStream=" + this.mKeyAnimatingStream + ", disableVolumeDialog=" + this.disableVolumeDialog);
        if (isUpdateExpandedAnimating()) {
            this.updateExpandedAnimator.cancel();
        }
        if (!zIsAnimating || i2 == 8 || i2 == 1) {
            if (this.mSilenceModeObserver.isDndPopupShowing() && i2 != 8 && i2 != 4) {
                H h2 = this.mHandler;
                h2.sendMessageDelayed(h2.obtainMessage(2, 3, 0), 2000L);
                return;
            }
            MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) getActiveColumn().slider;
            if (this.mKeyAnimatingStream != -1) {
                miuiVolumeSeekBar.cancelKeyAnim();
                this.mKeyAnimatingStream = -1;
                this.isDismissWaitCancelKeyAnim = true;
                H h3 = this.mHandler;
                h3.sendMessageDelayed(h3.obtainMessage(13, i2, 0), 300L);
                return;
            }
            miuiVolumeSeekBar.resetView();
            this.isDismissWaitCancelKeyAnim = false;
            this.mHandler.removeMessages(13);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(10);
            if (!this.mShowing) {
                boolean z2 = this.mNeedShowDialog;
                if (!z2) {
                    return;
                }
                if (z2 && !this.mVolumePanelDialogController.mVolumePanelDialog.isShowing()) {
                    return;
                }
            }
            dismissVolumePanel(i2);
        }
    }

    public VolumeColumn findColumn(int i2) {
        for (VolumeColumn volumeColumn : this.mColumns) {
            if (volumeColumn.getStream() == i2) {
                return volumeColumn;
            }
        }
        return null;
    }

    public VolumeColumn getActiveColumn() {
        for (VolumeColumn volumeColumn : this.mColumns) {
            if (volumeColumn.getStream() == this.mActiveStream) {
                return volumeColumn;
            }
        }
        return this.mColumns.get(0);
    }

    public VolumeColumn getColumn(int i2) {
        for (VolumeColumn volumeColumn : this.mColumns) {
            if (volumeColumn.getStream() == i2) {
                return volumeColumn;
            }
        }
        return null;
    }

    public List<VolumeColumn> getColumns() {
        return this.mColumns;
    }

    public float getPanelOriginalVolume() {
        float progress;
        VolumeColumn volumeColumnFindColumn = findColumn(3);
        boolean backgroundBlurOpenedInDefaultTheme = MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext);
        if (volumeColumnFindColumn == null || (!backgroundBlurOpenedInDefaultTheme && volumeColumnFindColumn.getSs() == null)) {
            Log.e(this.TAG, "getPanelOriginalVolume column is null");
            return 0.0f;
        }
        if (backgroundBlurOpenedInDefaultTheme) {
            progress = volumeColumnFindColumn.progressView.getVolumeLevel();
        } else {
            progress = ((volumeColumnFindColumn.slider.getProgress() * (volumeColumnFindColumn.getSs() != null ? volumeColumnFindColumn.getSs().levelMax : 0)) * 1.0f) / volumeColumnFindColumn.slider.getMax();
        }
        Log.d(this.TAG, "getPanelOriginalVolume: " + progress);
        return progress;
    }

    public View getVolumeContainerView() {
        return this.mVolumeContainerView;
    }

    public View getVolumeContent() {
        return this.mVolumeView;
    }

    public View getVolumeContentBg() {
        return this.mExpandBgView;
    }

    public View getVolumeContentColumns() {
        return this.mVolumeContentColumns;
    }

    public View getVolumeInnerContent() {
        return this.mVolumeContentView;
    }

    public View getVolumeRingerModeContent() {
        return this.mRingerModeContent;
    }

    public View getVolumeRingerModeLayout() {
        return this.mRingerModeLayout;
    }

    public void init(int i2) {
        this.mVolumePanelDialogController.init(i2, this.mVolumePanelView);
        initController(true);
    }

    public void initAnimListener(final VolumeColumn volumeColumn) {
        final ViewGroup ringerModeLayout = this.mVolumeView.getRingerModeLayout();
        View viewFindViewById = ringerModeLayout.findViewById(R.id.ringer_layout);
        int i2 = R.id.bg_blur;
        final View viewFindViewById2 = viewFindViewById.findViewById(i2);
        final View viewFindViewById3 = ringerModeLayout.findViewById(R.id.miui_volume_ringer_divider);
        final View viewFindViewById4 = ringerModeLayout.findViewById(R.id.dnd_layout).findViewById(i2);
        final View expandButton = this.mVolumeView.getExpandButton();
        final int stream = volumeColumn.getStream();
        ((MiuiVolumeSeekBar) volumeColumn.slider).setSeekBarAnimListener(new MiuiVolumeSeekBar.SeekBarAnimListener() { // from class: com.android.systemui.miui.volume.VolumePanelViewController.3
            private boolean expandShouldAnim() {
                return false;
            }

            private View getVolumeContainer() {
                if (VolumePanelViewController.this.mVolumeView.isExpanded()) {
                    return volumeColumn.view;
                }
                if (!VolumePanelViewController.this.mShouldTempBeVisible) {
                    return VolumePanelViewController.this.mVolumeContentView;
                }
                int i3 = stream;
                int stream2 = VolumePanelViewController.this.mTempColumn.getStream();
                VolumePanelViewController volumePanelViewController = VolumePanelViewController.this;
                return i3 == stream2 ? volumePanelViewController.mTempColumnContainer : volumePanelViewController.mVolumeContentView;
            }

            private boolean ringerShouldAnim() {
                if (VolumePanelViewController.this.mVolumeView.isExpanded() || !VolumePanelViewController.this.mVolumeView.isFooterVisibility()) {
                    return false;
                }
                return !VolumePanelViewController.this.mShouldTempBeVisible || stream == VolumePanelViewController.this.mTempColumn.getStream();
            }

            private boolean superVolumeShouldAnim() {
                if (VolumePanelViewController.this.mVolumeView.isExpanded()) {
                    return false;
                }
                return !VolumePanelViewController.this.mShouldTempBeVisible || stream == VolumePanelViewController.this.mTempColumn.getStream();
            }

            @Override // com.android.systemui.miui.volume.MiuiVolumeSeekBar.SeekBarAnimListener
            public int[] getHeightArray() {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ringerModeLayout.getLayoutParams();
                int i3 = marginLayoutParams.topMargin;
                int measuredHeight = viewFindViewById3.getMeasuredHeight();
                int i4 = marginLayoutParams.topMargin;
                return new int[]{i4, i4, i3, measuredHeight};
            }

            @Override // com.android.systemui.miui.volume.SlideContainerAnim.AnimListener
            public void resetView() {
                getVolumeContainer().setScaleX(1.0f);
                getVolumeContainer().setScaleY(1.0f);
                getVolumeContainer().setTranslationY(0.0f);
                if (VolumePanelViewController.this.mShouldTempBeVisible) {
                    VolumePanelViewController.this.mVolumeContentView.setScaleX(1.0f);
                    VolumePanelViewController.this.mVolumeContentView.setScaleY(1.0f);
                    VolumePanelViewController.this.mVolumeContentView.setTranslationY(0.0f);
                }
                VolumePanelViewController.this.mSuperVolumeBg.setScaleX(1.0f);
                VolumePanelViewController.this.mSuperVolumeBg.setScaleY(1.0f);
                VolumePanelViewController.this.mSuperVolumeBg.setTranslationY(0.0f);
                expandButton.setScaleX(1.0f);
                expandButton.setScaleY(1.0f);
                expandButton.setTranslationY(0.0f);
                viewFindViewById2.setScaleX(1.0f);
                viewFindViewById2.setScaleY(1.0f);
                viewFindViewById2.setTranslationY(0.0f);
                viewFindViewById4.setScaleX(1.0f);
                viewFindViewById4.setScaleY(1.0f);
                viewFindViewById4.setTranslationY(0.0f);
                Log.d(VolumePanelViewController.this.TAG, "resetView: mExpanded: " + VolumePanelViewController.this.mExpanded + ", mShouldTempVisible: " + VolumePanelViewController.this.mShouldTempBeVisible);
            }

            @Override // com.android.systemui.miui.volume.SlideContainerAnim.AnimListener
            public void setDndY(float f2, float f3) {
                if (ringerShouldAnim()) {
                    viewFindViewById4.setTranslationY(viewFindViewById4.getTranslationY() + (f3 - f2));
                }
            }

            @Override // com.android.systemui.miui.volume.SlideContainerAnim.AnimListener
            public void setRingerY(float f2, float f3) {
                if (ringerShouldAnim()) {
                    viewFindViewById2.setTranslationY(viewFindViewById2.getTranslationY() + (f3 - f2));
                }
            }

            @Override // com.android.systemui.miui.volume.SlideContainerAnim.AnimListener
            public void setScale(float f2, float f3) {
                if (superVolumeShouldAnim() && !VolumePanelViewController.this.mVolumeView.isSuperAnimating()) {
                    if (VolumePanelViewController.this.mRecordSuperVolumeScale) {
                        VolumePanelViewController.this.mRecordSuperVolumeScale = false;
                        VolumePanelViewController.this.mSuperVolumeBg.setScaleX(f3);
                        VolumePanelViewController.this.mSuperVolumeBg.setScaleY(f3);
                    } else {
                        float scaleX = VolumePanelViewController.this.mSuperVolumeBg.getScaleX() + (f3 - f2);
                        VolumePanelViewController.this.mSuperVolumeBg.setScaleX(scaleX);
                        VolumePanelViewController.this.mSuperVolumeBg.setScaleY(scaleX);
                    }
                }
                if (expandShouldAnim()) {
                    float scaleX2 = expandButton.getScaleX() + (f3 - f2);
                    expandButton.setScaleX(scaleX2);
                    expandButton.setScaleY(scaleX2);
                }
                if (ringerShouldAnim()) {
                    float f4 = f3 - f2;
                    float scaleX3 = viewFindViewById2.getScaleX() + f4;
                    viewFindViewById2.setScaleX(scaleX3);
                    viewFindViewById2.setScaleY(scaleX3);
                    float scaleX4 = viewFindViewById4.getScaleX() + f4;
                    viewFindViewById4.setScaleX(scaleX4);
                    viewFindViewById4.setScaleY(scaleX4);
                }
                View volumeContainer = getVolumeContainer();
                float scaleX5 = volumeContainer.getScaleX() + (f3 - f2);
                volumeContainer.setScaleX(scaleX5);
                volumeContainer.setScaleY(scaleX5);
            }

            @Override // com.android.systemui.miui.volume.SlideContainerAnim.AnimListener
            public void setSuperVolumeY(float f2, float f3) {
                if (superVolumeShouldAnim()) {
                    if (VolumePanelViewController.this.mRecordSuperVolumeTransition) {
                        VolumePanelViewController.this.mRecordSuperVolumeTransition = false;
                        VolumePanelViewController.this.mSuperVolumeBg.setTranslationY(f3);
                    } else {
                        VolumePanelViewController.this.mSuperVolumeBg.setTranslationY(VolumePanelViewController.this.mSuperVolumeBg.getTranslationY() + (f3 - f2));
                    }
                }
            }

            @Override // com.android.systemui.miui.volume.SlideContainerAnim.AnimListener
            public void setVolY(float f2, float f3) {
                if (expandShouldAnim()) {
                    expandButton.setTranslationY(expandButton.getTranslationY() + (f3 - f2));
                }
                View volumeContainer = getVolumeContainer();
                volumeContainer.setTranslationY(volumeContainer.getTranslationY() + (f3 - f2));
            }
        });
    }

    public void initController(boolean z2) {
        Log.d(this.TAG, "initController: needShowDialog=" + z2);
        this.TAG += "_" + z2;
        this.mNeedShowDialog = z2;
        initPanelView();
        this.mAccessibility.init();
        this.mSilenceModeObserver.init();
        if (getVoiceAssistStreamType() > 0) {
            this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1513a.lambda$initController$2();
                }
            });
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction(VolumeUtils.getACTION_CALL_VOLUME_BOOST());
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter, 2);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter2.addAction(SystemVolumeController.ACTION_PACKAGE_REPLACED);
        intentFilter2.addDataScheme("package");
        this.mContext.registerReceiver(this.mPackagesBroadcastReceiver, intentFilter2, 2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(VolumeUtil.COUNTDOWN_TURN_OFF);
        intentFilter3.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
        this.mContext.registerReceiver(this.mCountDownOffBroadcastReceiver, intentFilter3, 2);
        initSilentTimer();
        updateLockTaskModeState(this.mActivityManager.getLockTaskModeState());
        this.mVolumeCompat.addCallback(this.mControllerCompatCallback);
        this.mController.getState();
        if (z2) {
            this.mVolumePanelDialogController.addCallback(this.mDialogCallback);
        }
    }

    public void invokeStatusBar(String str, Bundle bundle) {
        try {
            ReflectBuilderUtil.callObjectMethod(this.mContext.getSystemService(SERVICE_STATUS_BAR), "setStatus", new Class[]{Integer.TYPE, String.class, Bundle.class}, 1, str, bundle);
            Log.i(this.TAG, bundle.toString());
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e(this.TAG, "invokeStatusBar error: " + e2.getMessage());
        }
    }

    public void performHapticFeedBack(boolean z2) {
        if (this.isControlCenterPanel) {
            VolumeColumn activeColumn = getActiveColumn();
            if (activeColumn.getSs() == null) {
                return;
            }
            int i2 = activeColumn.getSs().level;
            performHapticFeedBack(z2, i2 == activeColumn.getSs().levelMax, i2 == activeColumn.getSs().levelMin);
        }
    }

    public void performKeyAnim(KeyEvent keyEvent) {
        Log.i(this.TAG, "performKeyAnim, KeyEvent: " + MotionEvent.actionToString(keyEvent.getAction()) + ", KeyCode: " + KeyEvent.keyCodeToString(keyEvent.getKeyCode()));
        performKeyAnimInner(keyEvent.getAction() == 0, keyEvent.getAction() == 1, keyEvent.getKeyCode() == 24, keyEvent.getKeyCode() == 25);
    }

    public void prepareShow(float f2) {
        BoostHelper.getInstance().boostWithCpuFreq(2000L, this.mVolumePanelView);
        VolumeColumn volumeColumnFindColumn = findColumn(3);
        boolean backgroundBlurOpenedInDefaultTheme = MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext);
        if (volumeColumnFindColumn == null || volumeColumnFindColumn.getSs() == null) {
            Log.e(this.TAG, "prepareShow column is null");
            return;
        }
        if (backgroundBlurOpenedInDefaultTheme) {
            volumeColumnFindColumn.progressView.setVolumeLevel(f2);
            Log.i(this.TAG, "prepareShow: " + f2);
        }
        SeekBar seekBar = volumeColumnFindColumn.slider;
        MiuiVolumeSeekBar miuiVolumeSeekBar = (MiuiVolumeSeekBar) seekBar;
        int max = seekBar.getMax();
        int i2 = volumeColumnFindColumn.getSs().levelMax;
        int i3 = (int) ((max * f2) / i2);
        miuiVolumeSeekBar.setProgress(i3);
        Log.i(this.TAG, "prepareShow: " + f2 + ", " + i2 + ", " + max + " --> " + i3);
    }

    public void reInit() {
        Log.d(this.TAG, "reInit");
        boolean z2 = false;
        this.mNeedReInit = false;
        this.mProgressViewNoAnim = true;
        this.mFlipTinyChanged = false;
        VolumePanelView volumePanelView = new VolumePanelView(this.mContext);
        this.mVolumePanelView = volumePanelView;
        if (this.mNeedShowDialog) {
            this.mVolumePanelDialogController.initDialog(volumePanelView);
            this.mVolumePanelDialogController.setDialogNotFocusable();
        }
        initPanelView();
        this.mHandler.sendEmptyMessage(7);
        reCheckAllH();
        updateMediaIconAnim(this.mColumns.get(0), true, false);
        if (!this.isControlCenterPanel) {
            MiuiVolumeDialogView miuiVolumeDialogView = this.mVolumeView;
            int i2 = this.mActiveStream;
            if (i2 != 0 && i2 != 6 && !FlipUtils.isFlipTiny()) {
                z2 = true;
            }
            miuiVolumeDialogView.updateFooterVisibility(z2);
        }
        setActiveStreamPosition();
        this.mSilenceModeObserver.init();
    }

    public void setControlCenterPanel(boolean z2) {
        this.isControlCenterPanel = z2;
        MiuiVolumeDialogView miuiVolumeDialogView = this.mVolumeView;
        if (miuiVolumeDialogView != null) {
            miuiVolumeDialogView.setControlCenterPanel(z2);
        }
    }

    public void setDisableVolumeDialog(boolean z2) {
        this.disableVolumeDialog = z2;
        if (z2 && this.mNeedShowDialog) {
            dismissH(1);
        }
    }

    public void showH(int i2) {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("showH reason=");
        String[] strArr = Events.SHOW_REASONS;
        sb.append(strArr[i2]);
        sb.append(" mShowing:");
        sb.append(this.mShowing);
        sb.append(" mNeedReInit:");
        sb.append(this.mNeedReInit);
        sb.append(" needShowDialog=");
        sb.append(this.mNeedShowDialog);
        Log.d(str, sb.toString());
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        rescheduleTimeoutH();
        if (this.mShowing || this.mVolumeView.isAnimating()) {
            return;
        }
        BoostHelper.getInstance().boost(1000L, this.mVolumeView);
        this.mHandler.removeMessages(10);
        if (this.mNeedReInit && this.mNeedShowDialog) {
            reInit();
            this.mHandler.obtainMessage(10, i2, 0).sendToTarget();
        } else {
            showVolumePanelH(i2);
        }
        VolumeEventTracker.trackVolumeShow(strArr[i2]);
    }

    public final class H extends Handler {
        private static final int AUDIO_DEVICE_STATE = 12;
        private static final int AUDIO_MUSIC_STATE = 11;
        private static final int DISMISS = 2;
        private static final int DISMISS_WAIT_CANCEL_KEY_ANIM = 13;
        private static final int RECHECK = 3;
        private static final int RECHECK_ALL = 4;
        private static final int RESCHEDULE_TIMEOUT = 6;
        private static final int SET_STREAM_IMPORTANT = 5;
        private static final int SHOW = 1;
        private static final int SHOW_DIALOG = 10;
        private static final int SHOW_TOAST = 14;
        private static final int STATE_CHANGED = 7;
        private static final int UNLOCK_RECORD_COUNT = 9;
        private static final int VIBRATE = 8;

        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    VolumePanelViewController.this.showH(message.arg1);
                    break;
                case 2:
                case 13:
                    VolumePanelViewController.this.dismissH(message.arg1);
                    break;
                case 3:
                    VolumePanelViewController.this.recheckH((VolumeColumn) message.obj);
                    break;
                case 4:
                    VolumePanelViewController.this.reCheckAllH();
                    break;
                case 5:
                    VolumePanelViewController.this.setStreamImportantH(message.arg1, message.arg2 != 0);
                    break;
                case 6:
                    VolumePanelViewController.this.rescheduleTimeoutH();
                    break;
                case 7:
                    VolumePanelViewController volumePanelViewController = VolumePanelViewController.this;
                    volumePanelViewController.onStateChangedH(volumePanelViewController.mState);
                    break;
                case 8:
                    VolumePanelViewController.this.vibrateH();
                    break;
                case 9:
                    VolumePanelViewController.this.unlockRecordType(message.arg1);
                    break;
                case 10:
                    VolumePanelViewController.this.showVolumePanelH(message.arg1);
                    break;
                case 11:
                    VolumePanelViewController.this.updateTempColumnH(message.getData().getBoolean(HeadsetUtil.MODE_MUSIC), message.getData().getBoolean("voice"));
                    break;
                case 12:
                    int i2 = message.getData().getInt("device");
                    boolean z2 = message.getData().getBoolean(VolumePanelViewController.KEY_PHONE_IN_CALL);
                    Log.d(VolumePanelViewController.this.TAG, "currentDevice=" + i2 + ", isPhoneCall=" + z2);
                    VolumePanelViewController.this.mIsHeadset = VolumeUtils.deviceIsHeadset(i2);
                    VolumePanelViewController volumePanelViewController2 = VolumePanelViewController.this;
                    volumePanelViewController2.mIsNeedShowHeadSetIcon = volumePanelViewController2.mIsHeadset && !z2;
                    VolumePanelViewController.this.mIsEarpiece = VolumeUtils.deviceIsEarpiece(i2);
                    VolumePanelViewController.this.mIsSpeakerOn = VolumeUtils.deviceIsSpeaker(i2);
                    VolumePanelViewController.this.updateIconResWhenDevicesChanged();
                    VolumePanelViewController volumePanelViewController3 = VolumePanelViewController.this;
                    volumePanelViewController3.updateSuperVolumeView(volumePanelViewController3.getActiveColumn());
                    break;
                case 14:
                    if (VolumePanelViewController.this.mLastToast.get() != null) {
                        ((Toast) VolumePanelViewController.this.mLastToast.get()).cancel();
                    }
                    VolumePanelViewController.this.mLastToast = new WeakReference(Util.showSystemOverlayToast(VolumePanelViewController.this.mContext, message.arg1, 0));
                    break;
            }
        }

        public H(Looper looper) {
            super(looper);
        }
    }

    private void addColumn(int i2, boolean z2, boolean z3) {
        int size;
        int childCount;
        VolumeColumn volumeColumn = new VolumeColumn();
        initColumn(volumeColumn, i2, z2);
        if (!this.mShowA11yStream || !z3 || (size = this.mColumns.size()) <= 1 || (childCount = this.mVolumeColumns.getCurrentParent().getChildCount()) <= 1) {
            this.mVolumeColumns.addView(volumeColumn.view);
            this.mColumns.add(volumeColumn);
        } else {
            this.mVolumeColumns.addView(volumeColumn.view, childCount - 2);
            this.mColumns.add(size - 2, volumeColumn);
        }
    }

    private void initColumn(final VolumeColumn volumeColumn, final int i2, boolean z2, boolean z3) {
        volumeColumn.initColumn(this.mContext, this.mVolumeColumns.getCurrentParent(), i2, z2, this.mNeedShowDialog, this.mExpanded);
        if (!this.mNeedShowDialog) {
            ((MiuiVolumeSeekBar) volumeColumn.slider).enableTouchDownAnim();
        }
        volumeColumn.setOnSeekBarChangeListener(new VolumeSeekBarChangeListener(volumeColumn));
        initAnimListener(volumeColumn);
        volumeColumn.setSliderBlendColor(this.mLastExpanded);
        if (z3) {
            volumeColumn.resetCache();
        }
        if (z3 || !this.mSupportSuperVolume) {
            return;
        }
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.E
            @Override // java.lang.Runnable
            public final void run() {
                this.f1468a.lambda$initColumn$3(i2, volumeColumn);
            }
        });
    }

    public void updateExpandedH(boolean z2, boolean z3, boolean z4) {
        if (this.mExpanded != z2 || z4) {
            this.mExpanded = z2;
            this.mColumns.forEach(new Consumer() { // from class: com.android.systemui.miui.volume.x
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f1514a.lambda$updateExpandedH$6((VolumeColumn) obj);
                }
            });
            boolean backgroundBlurOpenedInDefaultTheme = MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext);
            Log.d(this.TAG, "updateExpandedH: isMiblur=" + backgroundBlurOpenedInDefaultTheme + " expanded=" + z2 + " needShowDialog=" + this.mNeedShowDialog);
            this.mTempColumn.setSliderResource(this.mExpanded);
            boolean z5 = false;
            if (this.mExpanded) {
                this.mExpandBgView.setVisibility(0);
                this.mVolumeContentView.setBackgroundResource(R.drawable.o3_miui_volume_ringer_bg_blur);
            } else {
                this.mExpandBgView.setVisibility(8);
            }
            this.mVolumeView.updateExpanded(z2, !z3);
            if (!this.isControlCenterPanel) {
                MiuiVolumeDialogView miuiVolumeDialogView = this.mVolumeView;
                int i2 = this.mActiveStream;
                if (i2 != 0 && i2 != 6 && !FlipUtils.isFlipTiny()) {
                    z5 = true;
                }
                miuiVolumeDialogView.updateFooterVisibility(z5);
            }
            this.mVolumeColumns.updateExpandedH(this.mExpanded);
            VolumeColumn activeColumn = getActiveColumn();
            updateIconResWhenExpandChanged();
            updateColumnH(activeColumn);
            if (this.mNeedShowDialog) {
                this.mVolumePanelDialogController.updateExpanded(this.mExpanded);
                this.mVolumePanelDialogController.updateWindow(z3);
            }
            rescheduleTimeoutH();
            Iterator<VolumeColumn> it = this.mColumns.iterator();
            while (it.hasNext()) {
                it.next().setSliderBlendColor(this.mLastExpanded);
            }
            this.mLastExpanded = this.mExpanded;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performHapticFeedBack(boolean z2, boolean z3, boolean z4) {
        Log.d(this.TAG, "onPerformHapticFeedback: " + z2 + " " + z3 + " " + z4);
        int i2 = this.mActiveStream;
        if (i2 == 0 || i2 == 6) {
            return;
        }
        boolean z5 = true;
        if (z2 && !z3 && !z4) {
            this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.F
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1471a.lambda$performHapticFeedBack$14();
                }
            });
        } else if ((!z3 && !z4) || z2 || this.mVolumeView.isShowAnimating()) {
            z5 = false;
        } else {
            int i3 = this.mNotLongPressCount + 1;
            this.mNotLongPressCount = i3;
            if (i3 != 1) {
                return;
            }
            if (!CURRENT_HAPTIC_VERSION.equals(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.I
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1474a.lambda$performHapticFeedBack$17();
                    }
                });
            } else if (z3) {
                this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.G
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1472a.lambda$performHapticFeedBack$15();
                    }
                });
            } else if (z4) {
                this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.miui.volume.H
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1473a.lambda$performHapticFeedBack$16();
                    }
                });
            }
        }
        Log.d(this.TAG, String.format("onPerformHapticFeedback, shouldHapticFeedback=%s, isControlCenterPanel=%s, isLongPress=%s, isMax=%s", Boolean.valueOf(z5), Boolean.valueOf(this.isControlCenterPanel), Boolean.valueOf(z2), Boolean.valueOf(z3)));
    }
}
