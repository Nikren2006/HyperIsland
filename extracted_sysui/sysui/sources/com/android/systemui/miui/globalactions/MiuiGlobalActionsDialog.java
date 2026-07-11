package com.android.systemui.miui.globalactions;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.HandlerExecutor;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.systemui.miui.globalactions.SliderView;
import com.android.systemui.plugins.GlobalActions;
import java.util.function.Consumer;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.PhoneUtils;
import miui.systemui.util.ThreadUtils;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import systemui.plugin.eventtracking.trackers.GlobalActionEventTracker;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiGlobalActionsDialog implements SliderView.Callback {
    private static final float CLICK_MOVE_Y = 20.0f;
    private static final int CLICK_TIME = 200;
    private static final String[] DISMISS_REASONS = {"unknown", "touch_outside", "screen_off", "back_clicked", "config_changed", "emergency_call", "system_update"};
    private static final int DISMISS_REASON_BACK_CLICKED = 3;
    private static final int DISMISS_REASON_CLICK_EMERGENCY_CALL = 5;
    private static final int DISMISS_REASON_CLICK_SYSTEM_UPDATE = 6;
    private static final int DISMISS_REASON_CONFIGURATION_CHANGED = 4;
    private static final int DISMISS_REASON_SCREEN_OFF = 2;
    private static final int DISMISS_REASON_TOUCH_OUTSIDE = 1;
    private static final int DISMISS_REASON_UNKNOWN = 0;
    private static final String KEYGUARD_DISMISS_MESSAGE = "dismiss by global actions";
    private static final String KEY_TALKBACK = "com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService";
    private static final String SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String TAG = "MiuiGlobalActionsDialog";
    private final AccessibilityManager mAccessibilityMgr;
    private TextView mAlarmTextView;
    private final Context mContext;
    private Object mDeviceStateManager;
    private Dialog mDialog;
    private Button mEmergencyCallButton;
    private Object mFoldStateListener;
    private Boolean mIsFolded;
    private FrameLayout mRoot;
    private int mScreenWidth;
    private SliderView mSliderView;
    private final Context mSysUIContext;
    private Button mSystemUpdateButton;
    private FrameLayout mTalkbackLayout;
    private Window mWindow;
    private final WindowManager mWindowManager;
    private final GlobalActions.GlobalActionsManager mWindowManagerFuncs;
    private boolean mIsDismissing = false;
    private float mDownY = 0.0f;
    private float mMoveY = 0.0f;
    private long mCurrentMS = 0;
    private boolean mIsForcePortrait = false;
    private boolean mShowDelayForOrientation = false;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) || "android.intent.action.SCREEN_OFF".equals(action)) && !MiuiGlobalActionsDialog.SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS.equals(intent.getStringExtra(MiuiGlobalActionsDialog.SYSTEM_DIALOG_REASON_KEY))) {
                MiuiGlobalActionsDialog.this.dismiss(2);
            }
        }
    };

    public MiuiGlobalActionsDialog(Context context, Context context2, GlobalActions.GlobalActionsManager globalActionsManager) {
        this.mContext = context;
        this.mSysUIContext = context2;
        this.mWindowManagerFuncs = globalActionsManager;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mAccessibilityMgr = (AccessibilityManager) context.getSystemService("accessibility");
        FlipUtils.setFlip(BlurUtils.isFlipDevice(context));
        FlipUtils.updateFlipTiny(BlurUtils.isFlipTinyScreen(context));
        if (Util.IS_MUILT_DISPLAY || FlipUtils.isFlip()) {
            DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
            this.mDeviceStateManager = deviceStateManagerCompat.getDeviceStateManagerInstance();
            Object foldStateListenerInstance = deviceStateManagerCompat.getFoldStateListenerInstance(context, new Consumer() { // from class: com.android.systemui.miui.globalactions.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f1460a.lambda$new$0((Boolean) obj);
                }
            });
            this.mFoldStateListener = foldStateListenerInstance;
            if (foldStateListenerInstance != null) {
                deviceStateManagerCompat.registerCallbackCompat(this.mDeviceStateManager, new HandlerExecutor(ThreadUtils.getUiThreadHandler()), this.mFoldStateListener);
            }
        }
    }

    private void addAlarmTextView() {
        this.mAlarmTextView = new TextView(this.mContext);
        String alarmTime = Util.getAlarmTime(this.mContext);
        if (alarmTime == null) {
            alarmTime = Util.getRestartHint(this.mContext);
        }
        this.mAlarmTextView.setText(alarmTime);
        setViewAttr(this.mAlarmTextView, this.mContext.getResources().getDimension(R.dimen.alarm_text_size), 0, FlipUtils.isFlipTiny() ? this.mContext.getResources().getDimensionPixelSize(R.dimen.flip_tiny_alarm_text_margin_bottom) : this.mContext.getResources().getDimensionPixelSize(R.dimen.alarm_text_margin_bottom));
        this.mAlarmTextView.setFocusableInTouchMode(true);
        this.mAlarmTextView.setClickable(false);
        this.mAlarmTextView.setAccessibilityDelegate(new View.AccessibilityDelegate(this) { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.8
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.setLongClickable(false);
                accessibilityNodeInfo.setFocusable(false);
            }
        });
    }

    private void addEmergencyCallButton() {
        Button button = new Button(this.mContext);
        this.mEmergencyCallButton = button;
        button.setText(this.mContext.getResources().getString(R.string.emergency_call));
        setButtonAttr(this.mEmergencyCallButton, !FlipUtils.isFlipTiny() && CommonUtils.IS_KDDI_VERSION);
        this.mEmergencyCallButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(MiuiGlobalActionsDialog.TAG, "onClick: takeEmergencyCallAction");
                MiuiGlobalActionsDialog.this.dismiss(5);
                PhoneUtils.takeEmergencyCallAction(MiuiGlobalActionsDialog.this.mSysUIContext);
            }
        });
    }

    private void addSystemUpdateButton() {
        Button button = new Button(this.mContext);
        this.mSystemUpdateButton = button;
        button.setText(this.mContext.getResources().getString(R.string.system_update));
        setButtonAttr(this.mSystemUpdateButton, false);
        this.mSystemUpdateButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(MiuiGlobalActionsDialog.TAG, "onClick: launchSystemUpdater");
                MiuiGlobalActionsDialog.this.dismiss(6);
                MiuiGlobalActionsDialog.this.launchSystemUpdater();
            }
        });
    }

    private int computeLeftMargin() {
        return ((this.mScreenWidth / 2) - ((int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_margin_end)))) - (((int) this.mContext.getResources().getDimension(R.dimen.slider_width)) / 2);
    }

    private ObjectAnimator createAlphaAnimator(float f2, float f3, int i2, View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", f2, f3);
        objectAnimatorOfFloat.setDuration(i2);
        objectAnimatorOfFloat.setInterpolator(SliderView.QUART_EASE_OUT);
        return objectAnimatorOfFloat;
    }

    private WindowManager.LayoutParams createWindowParams() {
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mWindow.addFlags(16809988);
        attributes.width = -1;
        attributes.height = -1;
        attributes.type = 2020;
        attributes.extraFlags = 268468226;
        attributes.privateFlags |= 2;
        String str = TAG;
        attributes.setTitle(str);
        attributes.format = 1;
        attributes.layoutInDisplayCutoutMode = 3;
        if (!isLargeMuiltDisplay() && !Util.IS_PAD) {
            Log.i(str, "createWindowParams: 1");
            attributes.screenOrientation = 1;
            this.mIsForcePortrait = true;
        } else if (FlipUtils.isFlipTiny()) {
            Log.i(str, "createWindowParams: 2");
            attributes.screenOrientation = 7;
            this.mIsForcePortrait = true;
        } else {
            Log.i(str, "createWindowParams: 3");
            this.mIsForcePortrait = false;
        }
        return attributes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss(int i2) {
        SliderView sliderView = this.mSliderView;
        if (sliderView != null && !sliderView.isAnimating() && !this.mIsDismissing) {
            Log.i(TAG, "dismiss: " + i2);
            if (i2 == 2 || i2 == 4) {
                this.mSliderView.dismissWithoutAnimation();
            } else {
                textViewAlphaAnimator(false);
                this.mSliderView.dismiss();
                this.mIsDismissing = true;
            }
        }
        GlobalActionEventTracker.trackDismissReason(DISMISS_REASONS[i2]);
    }

    private void dismissKeyguardThenExecute(final Runnable runnable) {
        IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
        try {
            if (windowManagerService.isKeyguardLocked()) {
                windowManagerService.dismissKeyguard(new IKeyguardDismissCallback.Stub(this) { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.10
                    public void onDismissCancelled() {
                    }

                    public void onDismissError() {
                    }

                    public void onDismissSucceeded() {
                        runnable.run();
                    }
                }, KEYGUARD_DISMISS_MESSAGE);
            } else {
                runnable.run();
            }
        } catch (RemoteException e2) {
            Log.e(TAG, "dismissKeyguard Exception:" + e2);
        }
    }

    private void initDialog() {
        Dialog dialog = new Dialog(this.mContext, R.style.Theme_MiuiGlobalAcionsDialog);
        this.mDialog = dialog;
        Window window = dialog.getWindow();
        this.mWindow = window;
        window.requestFeature(1);
        if (isLargeMuiltDisplay() || FlipUtils.isFlipTiny()) {
            updateScreenSize();
        }
        initViews();
        createWindowParams();
        this.mDialog.setContentView(this.mRoot);
    }

    private void initViews() {
        FrameLayout frameLayout = new FrameLayout(this.mContext);
        this.mRoot = frameLayout;
        frameLayout.setClipChildren(false);
        this.mRoot.setClipToPadding(false);
        FrameLayout frameLayout2 = new FrameLayout(this.mContext);
        frameLayout2.setBackgroundColor(this.mContext.getColor(R.color.dark_bg_color));
        frameLayout2.setAlpha(0.0f);
        this.mRoot.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout3 = new FrameLayout(this.mContext);
        this.mTalkbackLayout = frameLayout3;
        frameLayout3.setBackgroundColor(this.mContext.getColor(R.color.parent_bg_color));
        this.mTalkbackLayout.setContentDescription(this.mContext.getResources().getString(R.string.accessibility_back));
        this.mTalkbackLayout.setClipChildren(false);
        this.mTalkbackLayout.setClipToPadding(false);
        this.mTalkbackLayout.setAccessibilityPaneTitle(this.mContext.getResources().getString(R.string.slider_view_content_description));
        this.mRoot.setAccessibilityHeading(true);
        boolean z2 = this.mContext.getResources().getConfiguration().orientation == 2;
        int i2 = provideDisplayMetrics().widthPixels - 1;
        int i3 = provideDisplayMetrics().heightPixels - 1;
        if (z2) {
            i3 = i2;
            i2 = i3;
        }
        if (Util.inLargeScreen(this.mContext)) {
            this.mRoot.addView(this.mTalkbackLayout, new FrameLayout.LayoutParams(-1, -1));
        } else {
            this.mRoot.addView(this.mTalkbackLayout, new FrameLayout.LayoutParams(i2, i3));
        }
        SliderView sliderView = new SliderView(this.mContext, this, frameLayout2);
        this.mSliderView = sliderView;
        sliderView.setClipChildren(false);
        this.mSliderView.setClipToPadding(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) this.mContext.getResources().getDimension(R.dimen.slider_width), (int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_height)));
        layoutParams.gravity = 17;
        if (isLargeMuiltDisplay()) {
            layoutParams.gravity |= 5;
            layoutParams.setMarginEnd((int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_margin_end)));
        }
        addAlarmTextView();
        if (FlipUtils.isFlipTiny()) {
            updateTinyScreenSliderViewLp(layoutParams);
            if (Util.showEmergencyCall()) {
                layoutParams.bottomMargin = (int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_margin_bottom_with_emergency));
            }
        } else if (CommonUtils.IS_KDDI_VERSION) {
            Log.i(TAG, "showSystemUpdate");
            addSystemUpdateButton();
        }
        this.mSliderView.setBackground(this.mContext.getDrawable(R.drawable.ic_slider));
        this.mSliderView.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.mSliderView.getBackground().setAlpha(0);
        this.mSliderView.requestFocus();
        this.mSliderView.setFocusableInTouchMode(true);
        this.mTalkbackLayout.addView(this.mSliderView, layoutParams);
        if (Util.showEmergencyCall()) {
            Log.i(TAG, "showEmergencyCall");
            addEmergencyCallButton();
        }
        this.mRoot.setLayoutDirection(0);
        this.mRoot.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mRoot.setSystemUiVisibility(5634);
        this.mRoot.setOnKeyListener(new View.OnKeyListener() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i4, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1 || (keyEvent.getKeyCode() != 4 && keyEvent.getKeyCode() != 3)) {
                    return keyEvent.getKeyCode() == 25 || keyEvent.getKeyCode() == 24;
                }
                MiuiGlobalActionsDialog.this.dismiss(3);
                return true;
            }
        });
        this.mRoot.setFocusableInTouchMode(true);
        this.mRoot.requestFocus();
        this.mTalkbackLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MiuiGlobalActionsDialog.this.dismiss(1);
            }
        });
        this.mTalkbackLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MiuiGlobalActionsDialog miuiGlobalActionsDialog = MiuiGlobalActionsDialog.this;
                if (miuiGlobalActionsDialog.isEnableTalkBack(miuiGlobalActionsDialog.mContext) && MiuiGlobalActionsDialog.this.isMoveTouch(motionEvent)) {
                    return true;
                }
                MiuiGlobalActionsDialog.this.dismiss(1);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEnableTalkBack(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString("com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService");
        if (string != null && !"".equals(string)) {
            for (String str : string.split(MethodCodeHelper.IDENTITY_INFO_SEPARATOR)) {
                if (componentNameUnflattenFromString.equals(ComponentName.unflattenFromString(str))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLargeMuiltDisplay() {
        return Util.inLargeScreen(this.mContext) && Util.IS_MUILT_DISPLAY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isMoveTouch(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getAction()
            r1 = 1
            if (r0 == 0) goto L51
            if (r0 == r1) goto L34
            r2 = 2
            if (r0 == r2) goto L10
            r7 = 3
            if (r0 == r7) goto L34
            goto L69
        L10:
            float r0 = r6.mMoveY
            float r2 = r7.getY()
            float r3 = r6.mDownY
            float r2 = r2 - r3
            float r2 = java.lang.Math.abs(r2)
            float r0 = r0 + r2
            r6.mMoveY = r0
            float r0 = r7.getY()
            r6.mDownY = r0
            com.android.systemui.miui.globalactions.SliderView r6 = r6.mSliderView
            float r0 = r7.getY()
            float r7 = r7.getRawY()
            r6.handleActionMove(r0, r7, r1)
            goto L69
        L34:
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = r6.mCurrentMS
            long r2 = r2 - r4
            r4 = 200(0xc8, double:9.9E-322)
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 >= 0) goto L4b
            float r7 = r6.mMoveY
            r0 = 1101004800(0x41a00000, float:20.0)
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L4b
            r6 = 0
            return r6
        L4b:
            com.android.systemui.miui.globalactions.SliderView r6 = r6.mSliderView
            r6.handleActionUp()
            goto L69
        L51:
            float r0 = r7.getY()
            r6.mDownY = r0
            r0 = 0
            r6.mMoveY = r0
            long r2 = java.lang.System.currentTimeMillis()
            r6.mCurrentMS = r2
            com.android.systemui.miui.globalactions.SliderView r6 = r6.mSliderView
            float r7 = r7.getY()
            r6.handleActionDown(r7, r1)
        L69:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.isMoveTouch(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Boolean bool) {
        updateFoldState(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchSystemUpdater() {
        dismissKeyguardThenExecute(new Runnable() { // from class: com.android.systemui.miui.globalactions.MiuiGlobalActionsDialog.9
            @Override // java.lang.Runnable
            public void run() {
                Log.d(MiuiGlobalActionsDialog.TAG, " launchSystemUpdater ");
                Intent intent = new Intent();
                if (!Util.isUseGota() || Util.isUpdaterEnable(MiuiGlobalActionsDialog.this.mSysUIContext)) {
                    intent.setClassName("com.android.updater", "com.android.updater.MainActivity");
                } else {
                    intent.setClassName("com.google.android.gms", "com.google.android.gms.update.SystemUpdateActivity");
                }
                intent.setFlags(335544320);
                if (MiuiGlobalActionsDialog.this.mContext.getPackageManager().resolveActivity(intent, 65536) != null) {
                    MiuiGlobalActionsDialog.this.mContext.startActivity(intent);
                }
            }
        });
    }

    private DisplayMetrics provideDisplayMetrics() {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    private void setButtonAttr(Button button, boolean z2) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.emergency_call_text_padding_left);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.emergency_call_text_padding_top);
        button.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        setViewAttr(button, this.mContext.getResources().getDimension(R.dimen.emergency_call_text_size), 1, z2 ? this.mContext.getResources().getDimensionPixelSize(R.dimen.emergency_call_text_margin_bottom_when_kddi) : this.mContext.getResources().getDimensionPixelSize(Util.checkFlipDimen(R.dimen.emergency_call_text_margin_bottom)));
        button.setFocusableInTouchMode(false);
        button.setBackgroundResource(R.drawable.emergency_call_bg);
        button.setClickable(true);
    }

    private void setViewAttr(TextView textView, float f2, int i2, int i3) {
        textView.setTextColor(this.mContext.getResources().getColor(R.color.text_view_color));
        textView.setTextSize(1, f2);
        textView.setTypeface(Typeface.create("mipro-medium", i2));
        textView.requestFocus();
        textView.setGravity(17);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 81;
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.alarm_text_margin_left_right);
        layoutParams.leftMargin = dimensionPixelSize;
        layoutParams.rightMargin = dimensionPixelSize;
        if (isLargeMuiltDisplay()) {
            layoutParams.leftMargin = computeLeftMargin();
        }
        if (FlipUtils.isFlipTiny()) {
            updateTinyScreenButtonLp(layoutParams);
        }
        layoutParams.bottomMargin = i3;
        this.mTalkbackLayout.addView(textView, layoutParams);
    }

    private void updateFoldState(boolean z2) {
        Log.d(TAG, " updateFoldState = " + z2);
        FlipUtils.updateFlipTiny(z2);
        Boolean bool = this.mIsFolded;
        if (bool == null || bool.booleanValue() == z2) {
            this.mIsFolded = Boolean.valueOf(z2);
        } else {
            this.mIsFolded = Boolean.valueOf(z2);
            dismiss(4);
        }
    }

    private void updateMargin(View view, boolean z2) {
        if (view != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (z2) {
                layoutParams.leftMargin = computeLeftMargin();
            } else {
                layoutParams.setMarginEnd((int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_margin_end)));
            }
            this.mTalkbackLayout.updateViewLayout(view, layoutParams);
        }
    }

    private void updateScreenSize() {
        this.mScreenWidth = provideDisplayMetrics().widthPixels;
    }

    private void updateTinyScreenButtonLp(FrameLayout.LayoutParams layoutParams) {
        int iComputeLeftMargin = computeLeftMargin();
        if (FlipUtils.isCutoutLeft(this.mContext.getDisplay())) {
            layoutParams.leftMargin = iComputeLeftMargin;
            layoutParams.rightMargin = 0;
        }
        if (FlipUtils.isCutoutRight(this.mContext.getDisplay())) {
            layoutParams.rightMargin = iComputeLeftMargin;
            layoutParams.leftMargin = 0;
        }
    }

    private void updateTinyScreenSliderViewLp(FrameLayout.LayoutParams layoutParams) {
        if (FlipUtils.isCutoutLeft(this.mContext.getDisplay())) {
            layoutParams.gravity = 21;
            layoutParams.setMarginEnd((int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_margin_end)));
            layoutParams.setMarginStart(0);
        }
        if (FlipUtils.isCutoutRight(this.mContext.getDisplay())) {
            layoutParams.gravity = 19;
            layoutParams.setMarginStart((int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_margin_end)));
            layoutParams.setMarginEnd(0);
        }
    }

    private void updateTinyScreenViewLp(View view, boolean z2) {
        if (view != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (z2) {
                updateTinyScreenSliderViewLp(layoutParams);
            } else {
                updateTinyScreenButtonLp(layoutParams);
            }
            this.mTalkbackLayout.updateViewLayout(view, layoutParams);
        }
    }

    @Override // com.android.systemui.miui.globalactions.SliderView.Callback
    public void action(int i2, boolean z2) {
        Log.i(TAG, "action: " + i2 + " shutDownPasswordEnabled " + z2);
        if (z2) {
            dismiss(1);
        }
        if (i2 == 0) {
            this.mWindowManagerFuncs.shutdown();
        } else {
            this.mWindowManagerFuncs.reboot(false);
        }
        GlobalActionEventTracker.trackMoveAction(i2 == 0 ? "reboot" : "shutdown");
    }

    public void destroy() {
        Object obj;
        if ((Util.IS_MUILT_DISPLAY || FlipUtils.isFlip()) && (obj = this.mFoldStateListener) != null) {
            DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.mDeviceStateManager, obj);
        }
    }

    @Override // com.android.systemui.miui.globalactions.SliderView.Callback
    public void onConfigChanged(Configuration configuration) {
        Log.i(TAG, "onConfigChanged showDelay:" + this.mShowDelayForOrientation + ",orientation:" + configuration.orientation);
        if (this.mShowDelayForOrientation && configuration.orientation == 1) {
            this.mSliderView.show(this.mWindow);
            this.mShowDelayForOrientation = false;
        }
        if (isLargeMuiltDisplay()) {
            updateScreenSize();
            updateMargin(this.mSliderView, false);
            updateMargin(this.mAlarmTextView, true);
            updateMargin(this.mEmergencyCallButton, true);
        }
        if (FlipUtils.isFlipTiny()) {
            updateTinyScreenViewLp(this.mSliderView, true);
            updateTinyScreenViewLp(this.mAlarmTextView, false);
            updateTinyScreenViewLp(this.mEmergencyCallButton, false);
        }
    }

    public void showDialog() {
        this.mWindowManagerFuncs.onGlobalActionsShown();
        int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "com.xiaomi.system.devicelock.locked", 0);
        String str = TAG;
        Log.d(str, "showDialog: devicelock = " + i2);
        if (i2 != 0) {
            return;
        }
        this.mIsDismissing = false;
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            initDialog();
            this.mDialog.show();
            if (this.mContext.getResources().getConfiguration().orientation == 2 && this.mIsForcePortrait) {
                Log.i(str, "showDialog: showDelayForOrientation");
                this.mShowDelayForOrientation = true;
            } else {
                this.mSliderView.show(this.mWindow);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter, 2);
        }
    }

    @Override // com.android.systemui.miui.globalactions.SliderView.Callback
    public void sliderViewDismiss() {
        Dialog dialog = this.mDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mDialog.dismiss();
            this.mDialog = null;
            this.mRoot = null;
            this.mSliderView = null;
            this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        }
        this.mWindowManagerFuncs.onGlobalActionsHidden();
    }

    @Override // com.android.systemui.miui.globalactions.SliderView.Callback
    public void textViewAlphaAnimator(boolean z2) {
        int i2;
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (z2) {
            i2 = 300;
        } else {
            i2 = 200;
            f3 = 0.0f;
            f2 = 1.0f;
        }
        ObjectAnimator objectAnimatorCreateAlphaAnimator = createAlphaAnimator(f2, f3, i2, this.mAlarmTextView);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorCreateAlphaAnimator);
        if (Util.showEmergencyCall()) {
            animatorSet.playTogether(createAlphaAnimator(f2, f3, i2, this.mEmergencyCallButton));
        }
        if (CommonUtils.IS_KDDI_VERSION) {
            animatorSet.playTogether(createAlphaAnimator(f2, f3, i2, this.mSystemUpdateButton));
        }
        animatorSet.start();
    }
}
