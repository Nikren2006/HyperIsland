package com.android.systemui.miui.volume;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Region;
import android.hardware.display.DisplayManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.android.systemui.miui.volume.MiuiVolumeDialogMotion;
import com.android.systemui.miui.volume.widget.ExpandCollapseLinearLayout;
import java.util.List;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes2.dex */
public class MiuiVolumeDialogView extends ExpandCollapseLinearLayout implements DisplayManager.DisplayListener, ViewTreeObserver.OnComputeInternalInsetsListener {
    private String TAG;
    private View.OnClickListener expandListener;
    private boolean isControlCenterPanel;
    private boolean isTackBackOn;
    private boolean mAttached;
    private MiuiVolumeDialogMotion.Callback mCallback;
    private Context mContext;
    private ViewGroup mDialogContentView;
    private Display mDisplay;
    private int[] mDisplayLocation;
    private ColorStateList mExapndIconTint;
    private View mExpandButton;
    private int mLastDensity;
    private int mLastRotation;
    private MiuiVolumeDialogMotion mMotion;
    private boolean mNeedShowDialog;
    private boolean mObservingInternalInsets;
    private MiuiRingerModeLayout mRingerModeLayout;
    private FrameLayout mTempColumnContainer;
    private int mVisibleNumber;

    public MiuiVolumeDialogView(Context context) {
        this(context, null);
    }

    private int getInsetTop() {
        Display display = this.mDisplay;
        if (display != null && display.getCutout() != null) {
            if (FlipUtils.isCutoutTop(this.mDisplay)) {
                return this.mDisplay.getCutout().getSafeInsetTop();
            }
            if (getRootWindowInsets() != null) {
                return getRootWindowInsets().getStableInsetRight();
            }
        }
        return 0;
    }

    private void setInternalInsetsListener() {
        boolean z2 = this.mAttached && !isExpanded();
        if (!this.mNeedShowDialog || z2 == this.mObservingInternalInsets) {
            return;
        }
        this.mObservingInternalInsets = z2;
        if (!z2) {
            getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
        } else {
            getViewTreeObserver().addOnComputeInternalInsetsListener(this);
            requestLayout();
        }
    }

    private void updateExpandButtonH(boolean z2) {
        this.mExpandButton.setContentDescription(getContext().getString(z2 ? R.string.accessibility_volume_collapse : R.string.accessibility_volume_expand));
    }

    public void cancelShowAnimation() {
        this.mMotion.cancelShowAnimation();
    }

    public void destroy() {
        this.mMotion.destroy();
        setMotionCallback(null);
        Log.d(this.TAG, " destroy ");
    }

    public void dismissH(boolean z2, Runnable runnable) {
        this.mMotion.dismissVolumePanel(z2, runnable);
        this.mRingerModeLayout.cleanUp();
        Log.d(this.TAG, " dismissH " + z2);
    }

    public View getExpandButton() {
        return this.mExpandButton;
    }

    public ViewGroup getRingerModeLayout() {
        return this.mRingerModeLayout;
    }

    public boolean isAnimating() {
        return this.mMotion.isAnimating();
    }

    public boolean isExpandCollapsedAnimating() {
        return this.mMotion.isExpandCollapsedAnimating();
    }

    public boolean isFooterVisibility() {
        return this.mRingerModeLayout.getVisibility() == 0;
    }

    public boolean isOffMode() {
        return this.mRingerModeLayout.isOffMode();
    }

    public boolean isShowAnimating() {
        return this.mMotion.isShowAnimating();
    }

    public boolean isSuperAnimating() {
        return this.mMotion.isSuperAnimating();
    }

    public void notifyAccessibilityChanged(boolean z2) {
        this.isTackBackOn = z2;
        if (z2) {
            this.mExpandButton.setOnClickListener(this.expandListener);
        } else {
            this.mExpandButton.setOnClickListener(null);
            this.mExpandButton.setClickable(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Display display = getDisplay();
        this.mDisplay = display;
        this.mMotion.setDisplay(display);
        this.mLastRotation = this.mDisplay.getRotation();
        ((DisplayManager) getContext().getSystemService("display")).registerDisplayListener(this, null);
        this.mAttached = true;
        setInternalInsetsListener();
        updateDialogViewLP();
    }

    public void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        if (isExpanded()) {
            return;
        }
        internalInsetsInfo.setTouchableInsets(3);
        if (this.mDisplay.getRotation() == 3) {
            getLocationOnScreen(this.mDisplayLocation);
        } else {
            this.mDisplayLocation[0] = getLeft();
            this.mDisplayLocation[1] = getTop();
        }
        Region region = internalInsetsInfo.touchableRegion;
        int[] iArr = this.mDisplayLocation;
        int i2 = iArr[0];
        region.set(i2, iArr[1], getWidth() + i2, this.mDisplayLocation[1] + getHeight());
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDisplay = null;
        this.mMotion.setDisplay(null);
        this.mMotion.clean();
        ((DisplayManager) getContext().getSystemService("display")).unregisterDisplayListener(this);
        this.mAttached = false;
        setInternalInsetsListener();
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int i2) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int i2) {
        Display display = this.mDisplay;
        if (display != null) {
            int rotation = display.getRotation();
            if (this.mLastRotation != rotation) {
                this.mMotion.updateRotation();
                this.mMotion.updateStates(true);
            }
            this.mLastRotation = rotation;
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int i2) {
    }

    @Override // com.android.systemui.miui.volume.widget.ExpandCollapseLinearLayout, com.android.systemui.miui.volume.widget.ExpandCollapseStateHelper.OnExpandStateUpdatedListener
    public void onExpandStateUpdated(boolean z2) {
        super.onExpandStateUpdated(z2);
        updateSuperVolumeVisibility(false);
        this.mMotion.updateStateToExpand(z2);
        this.mRingerModeLayout.updateExpandedH(z2);
        updateExpandButtonH(z2);
        setInternalInsetsListener();
        Log.d(this.TAG, " onExpandStateUpdated " + z2);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        View viewFindViewById = findViewById(R.id.volume_expand_button);
        this.mExpandButton = viewFindViewById;
        viewFindViewById.requestFocus();
        this.mExpandButton.setFocusableInTouchMode(true);
        boolean zIsEnableAccessibility = Util.isEnableAccessibility(this.mContext);
        this.isTackBackOn = zIsEnableAccessibility;
        if (zIsEnableAccessibility) {
            this.mExpandButton.setOnClickListener(this.expandListener);
        } else {
            this.mExpandButton.setOnClickListener(null);
            this.mExpandButton.setClickable(false);
        }
        this.mDialogContentView = (ViewGroup) findViewById(R.id.volume_dialog_content);
        this.mRingerModeLayout = (MiuiRingerModeLayout) findViewById(R.id.miui_volume_ringer_layout);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.volume_dialog_column_temp);
        this.mTempColumnContainer = frameLayout;
        this.mMotion = new MiuiVolumeDialogMotion(this, this.mDialogContentView, frameLayout, this.mExpandButton, this.mRingerModeLayout);
        this.mExapndIconTint = this.mContext.getResources().getColorStateList(R.color.miui_volume_expand_button_color);
        Log.d(this.TAG, " onFinishInflate ");
    }

    public void setActiveStreamPosition(int i2, int i3) {
        this.mMotion.setActiveStreamPosition(i2, i3);
        if (i3 != this.mVisibleNumber) {
            this.mVisibleNumber = i3;
            updateDialogViewLP();
        }
    }

    public void setContentView(View view, View view2) {
        this.mMotion.setContentView(view, view2);
    }

    public void setControlCenterPanel(boolean z2) {
        this.isControlCenterPanel = z2;
        MiuiVolumeDialogMotion miuiVolumeDialogMotion = this.mMotion;
        if (miuiVolumeDialogMotion != null) {
            miuiVolumeDialogMotion.setControlCenterPanel(z2);
        }
    }

    public void setLockTaskModeState(int i2) {
        this.mRingerModeLayout.setLockTaskModeState(i2);
    }

    public void setMotionCallback(MiuiVolumeDialogMotion.Callback callback) {
        this.mMotion.setCallback(callback);
        this.mCallback = callback;
    }

    public void setNeedShowDialog(boolean z2) {
        this.mNeedShowDialog = z2;
        this.mMotion.setNeedShowDialog(z2);
        this.mRingerModeLayout.setNeedShowDialog(z2);
        Log.d(this.TAG, " setNeedShowDialog " + z2);
    }

    public void setSilenceMode(boolean z2, boolean z3, boolean z4) {
        this.mRingerModeLayout.setSilenceMode(z2, z3, z4);
    }

    public void setSilenceModeByTimer(boolean z2) {
        this.mRingerModeLayout.setSlienceMode(z2);
    }

    public void setVolumeColumns(List<VolumeColumn> list) {
        this.mMotion.setVolumeColumns(list);
    }

    public void showH(Runnable runnable) {
        this.mRingerModeLayout.init();
        if (this.mNeedShowDialog) {
            this.mMotion.showVolumePanel(runnable);
        } else {
            onExpandStateUpdated(true);
        }
        Log.d(this.TAG, " showH " + this.mNeedShowDialog);
    }

    public void updateDialogViewLP() {
        if (FlipUtils.isFlipTiny()) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            if (this.mMotion.volumeShowLeft()) {
                layoutParams.gravity = 83;
                layoutParams.leftMargin = MiuiVolumeDialogRes.getMarginRight(this.mContext, this.mNeedShowDialog, false);
                layoutParams.bottomMargin = MiuiVolumeDialogRes.getMarginTop(this.mContext);
            }
            if (this.mMotion.isLandscape()) {
                layoutParams.gravity = 5;
                layoutParams.topMargin = MiuiVolumeDialogRes.getMarginTop(this.mContext) + getInsetTop();
            } else if (this.mVisibleNumber == 2) {
                int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.miui_volume_offset_end_with_temp);
                layoutParams.rightMargin = dimensionPixelSize;
                layoutParams.leftMargin = dimensionPixelSize;
            }
        }
    }

    public void updateExpandButtonColor(int i2, boolean z2) {
        if (this.mExpandButton == null || isExpanded()) {
            return;
        }
        if (z2 && MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this.mContext)) {
            Util.setMiViewBlurAndBlendColor(this.mExpandButton, 3, MiuiVolumeDialogRes.getExpandedIconBlandColor());
            return;
        }
        ColorStateList colorStateList = ContextCompat.getColorStateList(this.mContext, i2);
        if (this.mExapndIconTint != colorStateList) {
            ((ImageView) this.mExpandButton).setImageTintList(colorStateList);
            this.mExapndIconTint = colorStateList;
        }
    }

    @Override // com.android.systemui.miui.volume.widget.ExpandCollapseLinearLayout
    public void updateExpanded(boolean z2, boolean z3) {
        super.updateExpanded(z2, false);
    }

    public void updateFooterVisibility(boolean z2) {
        Util.setVisOrGone(this.mRingerModeLayout, z2);
        View view = this.mExpandButton;
        if (isExpanded()) {
            z2 = false;
        }
        Util.setVisOrGone(view, z2);
    }

    public void updateSuperVolumeShowX(boolean z2) {
        this.mMotion.updateSuperVolumeShowX(z2);
    }

    public void updateSuperVolumeVisibility(boolean z2) {
        this.mMotion.updateSuperVolumeVisibility(z2, isExpanded());
    }

    public void updateVolumePanelSize() {
        this.mRingerModeLayout.updateResources();
        this.mMotion.updateVolumePanelSize();
    }

    public MiuiVolumeDialogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiuiVolumeDialogView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "MiuiVolumeDialogView";
        this.mDisplayLocation = new int[2];
        this.mLastDensity = 0;
        this.isTackBackOn = false;
        this.mNeedShowDialog = true;
        this.isControlCenterPanel = false;
        this.mVisibleNumber = 1;
        this.expandListener = new View.OnClickListener() { // from class: com.android.systemui.miui.volume.MiuiVolumeDialogView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MiuiVolumeDialogView.this.mCallback != null) {
                    MiuiVolumeDialogView.this.mCallback.onExpandClicked();
                }
            }
        };
        this.mContext = context;
    }
}
