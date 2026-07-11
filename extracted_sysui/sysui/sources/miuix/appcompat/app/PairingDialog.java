package miuix.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import miuix.appcompat.R;
import miuix.appcompat.internal.widget.DialogButtonPanel;
import miuix.appcompat.internal.widget.DialogParentPanel2;
import miuix.appcompat.internal.widget.PairingParentPanel;
import miuix.core.util.MiuixUIUtils;
import miuix.core.widget.NestedScrollView;
import miuix.internal.util.AnimHelper;
import miuix.springback.view.SpringBackLayout;

/* JADX INFO: loaded from: classes2.dex */
public class PairingDialog extends AlertDialog {
    private LinearLayout mCheckboxContainer;
    private CharSequence mCheckboxMessage;
    private DialogInterface.OnClickListener mCloseClickListener;
    private Context mContext;
    private float mCornerRadiusThreshold;
    private int mCustomLayoutResId;
    private View mCustomView;
    private boolean mCustomViewVerticalCenterEnabled;
    private AppCompatCheckBox mDefaultCheckbox;
    private ViewGroup mDialogButtonPanel;
    private int mDialogButtonPanelHPadding;
    private ViewGroup mDialogContentPanel;
    private DialogParentPanel2 mDialogParentPanel;
    private int mDialogParentPanelFixedHeight;
    private int mDialogParentPanelFlipTinyFixedHeight;
    private int mDialogParentPanelLargeFontFixedHeight;
    private int mDialogParentPanelPaddingBottom;
    private TextView mFeedBackMessageView;
    private LinearLayout mFeedbackContainer;
    private View mInflatedCustomView;
    private boolean mIsChecked;
    private boolean mIsFullScreenGestureMode;
    private Drawable mLabelDrawable;
    private int mLabelDrawableResId;
    private AppCompatImageView mLabelImage;
    private int mLabelImageHeight;
    private int mLabelImageWidth;
    private CharSequence mMessage;
    private AppCompatTextView mMessageView;
    private boolean mNavigationBarHiddenEnabled;
    private float mNormalCornerRadius;
    private LinearLayout mPairingContentView;
    private ViewGroup mPairingCustom;
    private int mPairingPanelPaddingBottom;
    private PairingParentPanel mPairingParentPanel;
    private NestedScrollView mPairingScrollView;
    private SpringBackLayout mPairingSpringBack;
    private int mPanelBottomMargin;
    private CharSequence mTitle;
    private TextView mTitleView;
    private WindowManager mWindowManager;

    public PairingDialog(@NonNull Context context) {
        super(context);
        this.mWindowManager = null;
        this.mLabelImageWidth = -2;
        this.mLabelImageHeight = -2;
        this.mCustomViewVerticalCenterEnabled = true;
        this.mNavigationBarHiddenEnabled = false;
        init(context);
    }

    private void adjustPairingParentPanelPaddingBottom() {
        ViewGroup viewGroup = this.mDialogButtonPanel;
        if (viewGroup == null || this.mFeedbackContainer == null || this.mPairingParentPanel == null) {
            return;
        }
        if (viewGroup.getVisibility() == 8 && this.mFeedbackContainer.getVisibility() == 8) {
            PairingParentPanel pairingParentPanel = this.mPairingParentPanel;
            pairingParentPanel.setPadding(pairingParentPanel.getPaddingStart(), this.mPairingParentPanel.getPaddingTop(), this.mPairingParentPanel.getPaddingEnd(), 0);
        } else {
            PairingParentPanel pairingParentPanel2 = this.mPairingParentPanel;
            pairingParentPanel2.setPadding(pairingParentPanel2.getPaddingStart(), this.mPairingParentPanel.getPaddingTop(), this.mPairingParentPanel.getPaddingEnd(), this.mPairingPanelPaddingBottom);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustSpringBackEnabled() {
        PairingParentPanel pairingParentPanel = this.mPairingParentPanel;
        if (pairingParentPanel == null || this.mPairingSpringBack == null || this.mPairingScrollView == null || this.mPairingContentView == null) {
            return;
        }
        pairingParentPanel.post(new Runnable() { // from class: miuix.appcompat.app.t
            @Override // java.lang.Runnable
            public final void run() {
                this.f6057a.lambda$adjustSpringBackEnabled$0();
            }
        });
    }

    private void afterInstallDialogContent() {
        this.mDialogContentPanel = (ViewGroup) findViewById(R.id.contentPanel);
        this.mDialogParentPanel = (DialogParentPanel2) findViewById(R.id.parentPanel);
        this.mDialogButtonPanel = (ViewGroup) findViewById(R.id.buttonPanel);
        installDefaultCheckbox();
        installFeedbackMessageView();
    }

    private void beforeInstallDialogContent(View view) {
        if (view instanceof PairingParentPanel) {
            PairingParentPanel pairingParentPanel = (PairingParentPanel) view;
            pairingParentPanel.setCustomViewVerticalCenterEnabled(this.mCustomViewVerticalCenterEnabled);
            this.mPairingParentPanel = pairingParentPanel;
        }
        this.mPairingSpringBack = (SpringBackLayout) view.findViewById(R.id.pairingSpringBack);
        this.mPairingScrollView = (NestedScrollView) view.findViewById(R.id.pairingScrollView);
        this.mPairingContentView = (LinearLayout) view.findViewById(R.id.pairingContentView);
        this.mTitleView = (TextView) view.findViewById(R.id.pairingTitle);
        this.mPairingCustom = (ViewGroup) view.findViewById(R.id.pairingCustom);
        this.mMessageView = (AppCompatTextView) view.findViewById(R.id.pairingMessage);
        this.mLabelImage = (AppCompatImageView) view.findViewById(R.id.pairingLabelImage);
    }

    private void fixedButtonPanelToBottom(ViewGroup viewGroup, ViewGroup viewGroup2, ViewGroup viewGroup3) {
        if (viewGroup == null || viewGroup2 == null || viewGroup3 == null) {
            return;
        }
        int i2 = R.id.buttonPanel;
        ViewGroup viewGroup4 = (ViewGroup) viewGroup.findViewById(i2);
        ViewGroup viewGroup5 = (ViewGroup) viewGroup2.findViewById(i2);
        if (viewGroup4 != null || viewGroup5 == null) {
            return;
        }
        this.mAlert.safeMoveView(viewGroup3, viewGroup);
    }

    private float getPanelCornerRadius(Context context) {
        if (context == null) {
            return this.mNormalCornerRadius;
        }
        Display display = context.getDisplay();
        if (display == null) {
            display = this.mWindowManager.getDefaultDisplay();
        }
        float fMax = this.mNormalCornerRadius;
        if (display.getRoundedCorner(3) != null) {
            fMax = Math.max(0.0f, r3.getRadius() - this.mPanelBottomMargin);
        }
        return fMax < this.mCornerRadiusThreshold ? this.mNormalCornerRadius : fMax;
    }

    private void init(Context context) {
        this.mContext = context;
        this.mDialogParentPanelPaddingBottom = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_parent_panel_padding_bottom);
        this.mNormalCornerRadius = context.getResources().getDimensionPixelSize(R.dimen.miuix_theme_radius_big);
        this.mCornerRadiusThreshold = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_corner_radius_threshold);
        this.mDialogButtonPanelHPadding = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_content_padding_horizontal);
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mDialogParentPanelFixedHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_parent_panel_fixed_height);
        this.mDialogParentPanelLargeFontFixedHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_parent_panel_fixed_height_large_font);
        this.mDialogParentPanelFlipTinyFixedHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_parent_panel_fixed_height_flip_tiny);
        this.mPairingPanelPaddingBottom = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_pairing_dialog_panel_padding_bottom);
        this.mIsFullScreenGestureMode = MiuixUIUtils.isFullScreenGestureMode(this.mContext);
        prepareDefaultCheckbox(this.mContext);
        prepareFeedbackMessageView(this.mContext);
    }

    private void installDefaultCheckbox() {
        LinearLayout linearLayout;
        if (this.mDialogParentPanel == null || (linearLayout = this.mCheckboxContainer) == null) {
            return;
        }
        ViewGroup viewGroup = linearLayout.getParent() instanceof ViewGroup ? (ViewGroup) this.mCheckboxContainer.getParent() : null;
        if (viewGroup != null) {
            viewGroup.removeView(this.mCheckboxContainer);
        } else {
            this.mDialogParentPanel.addView(this.mCheckboxContainer, this.mDialogParentPanel.indexOfChild(this.mDialogButtonPanel));
        }
        if (TextUtils.isEmpty(this.mCheckboxMessage)) {
            this.mCheckboxContainer.setVisibility(8);
            return;
        }
        this.mDefaultCheckbox.setText(this.mCheckboxMessage);
        this.mDefaultCheckbox.setChecked(this.mIsChecked);
        this.mCheckboxContainer.setVisibility(0);
    }

    private void installFeedbackMessageView() {
        LinearLayout linearLayout;
        if (this.mDialogParentPanel == null || (linearLayout = this.mFeedbackContainer) == null) {
            return;
        }
        ViewGroup viewGroup = linearLayout.getParent() instanceof ViewGroup ? (ViewGroup) this.mFeedbackContainer.getParent() : null;
        if (viewGroup != null) {
            viewGroup.removeView(this.mFeedbackContainer);
        } else {
            this.mFeedbackContainer.setVisibility(8);
            this.mDialogParentPanel.addView(this.mFeedbackContainer, this.mDialogParentPanel.indexOfChild(this.mDialogButtonPanel) + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustSpringBackEnabled$0() {
        setScrollViewToExpectedHeight();
        ViewGroup.LayoutParams layoutParams = this.mPairingContentView.getLayoutParams();
        this.mPairingSpringBack.setSpringBackEnable((layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).topMargin : 0) + this.mPairingContentView.getHeight() > this.mPairingScrollView.getHeight());
    }

    private void prepareDefaultCheckbox(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.miuix_appcompat_pairing_dialog_checkbox, (ViewGroup) null);
        this.mCheckboxContainer = linearLayout;
        this.mDefaultCheckbox = (AppCompatCheckBox) linearLayout.findViewById(R.id.pairing_checkbox_stub);
    }

    private void prepareFeedbackMessageView(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.miuix_appcompat_pairing_dialog_feedback_message, (ViewGroup) null);
        this.mFeedbackContainer = linearLayout;
        this.mFeedBackMessageView = (TextView) linearLayout.findViewById(R.id.pairingDialogFeedback);
    }

    private void setParentPanelConfigChangedCallback(DialogParentPanel2 dialogParentPanel2) {
        if (dialogParentPanel2 == null) {
            return;
        }
        dialogParentPanel2.setConfigurationChangedCallback(new DialogParentPanel2.ConfigurationChangedCallback() { // from class: miuix.appcompat.app.PairingDialog.2
            @Override // miuix.appcompat.internal.widget.DialogParentPanel2.ConfigurationChangedCallback
            public void onConfigurationChanged(Configuration configuration) {
                int iUpdateParentPanelFixedHeight = PairingDialog.this.updateParentPanelFixedHeight(configuration);
                if (PairingDialog.this.mDialogParentPanel != null) {
                    PairingDialog.this.mDialogParentPanel.setPanelFixedHeight(iUpdateParentPanelFixedHeight);
                    PairingDialog.this.mDialogParentPanel.requestLayout();
                }
                PairingDialog.this.adjustSpringBackEnabled();
            }
        });
    }

    private void setScrollViewToExpectedHeight() {
        PairingParentPanel pairingParentPanel;
        ViewGroup.LayoutParams layoutParams;
        if (this.mPairingScrollView == null || (pairingParentPanel = this.mPairingParentPanel) == null || pairingParentPanel.getScrollExpectedHeight() == 0 || (layoutParams = this.mPairingScrollView.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = this.mPairingParentPanel.getScrollExpectedHeight();
        this.mPairingScrollView.setLayoutParams(layoutParams);
    }

    private void setupCustomContent() {
        if (this.mPairingCustom == null) {
            this.mPairingCustom = (ViewGroup) findViewById(R.id.pairingCustom);
        }
        View view = this.mInflatedCustomView;
        View viewInflate = null;
        if (view != null && view.getParent() != null) {
            this.mAlert.safeRemoveFromParent(this.mInflatedCustomView);
            this.mInflatedCustomView = null;
        }
        View view2 = this.mCustomView;
        if (view2 != null) {
            viewInflate = view2;
        } else if (this.mCustomLayoutResId != 0) {
            viewInflate = LayoutInflater.from(this.mContext).inflate(this.mCustomLayoutResId, this.mPairingCustom, false);
            this.mInflatedCustomView = viewInflate;
        }
        if (viewInflate != null) {
            this.mAlert.safeMoveView(viewInflate, this.mPairingCustom);
        } else {
            this.mAlert.safeRemoveFromParent(this.mPairingCustom);
        }
    }

    private void setupLabelImage() {
        AppCompatImageView appCompatImageView = this.mLabelImage;
        if (appCompatImageView == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) appCompatImageView.getLayoutParams();
        if (marginLayoutParams != null) {
            marginLayoutParams.width = this.mLabelImageWidth;
            marginLayoutParams.height = this.mLabelImageHeight;
            this.mLabelImage.setLayoutParams(marginLayoutParams);
        }
        Drawable drawable = this.mLabelDrawable;
        if (drawable != null) {
            this.mLabelImage.setImageDrawable(drawable);
            this.mLabelImage.setVisibility(0);
            return;
        }
        int i2 = this.mLabelDrawableResId;
        if (i2 == 0) {
            this.mLabelImage.setVisibility(8);
        } else {
            this.mLabelImage.setImageResource(i2);
            this.mLabelImage.setVisibility(0);
        }
    }

    private void setupMessageView() {
        if (this.mMessageView == null) {
            return;
        }
        if (TextUtils.isEmpty(this.mMessage)) {
            this.mMessageView.setVisibility(8);
        } else {
            this.mMessageView.setText(this.mMessage);
            this.mMessageView.setVisibility(0);
        }
    }

    private void setupTitle() {
        if (this.mTitleView == null) {
            return;
        }
        if (TextUtils.isEmpty(this.mTitle)) {
            this.mTitleView.setVisibility(8);
        } else {
            this.mTitleView.setVisibility(0);
            this.mTitleView.setText(this.mTitle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int updateParentPanelFixedHeight(@androidx.annotation.NonNull android.content.res.Configuration r11) {
        /*
            r10 = this;
            android.content.Context r0 = r10.mContext
            android.graphics.Point r0 = miuix.core.util.WindowUtils.getScreenSize(r0)
            android.content.Context r1 = r10.mContext
            int r1 = miuix.core.util.MiuixUIUtils.getStatusBarHeight(r1)
            android.content.Context r2 = r10.mContext
            int r2 = miuix.core.util.MiuixUIUtils.getNavigationBarHeight(r2)
            android.content.Context r3 = r10.mContext
            boolean r3 = miuix.core.util.MiuixUIUtils.isFullScreenGestureMode(r3)
            int r11 = r11.orientation
            r4 = 0
            r5 = 1
            r6 = 2
            if (r11 != r6) goto L21
            r11 = r5
            goto L22
        L21:
            r11 = r4
        L22:
            boolean r7 = miuix.os.Build.IS_FLIP
            if (r7 == 0) goto L30
            android.content.Context r7 = r10.mContext
            boolean r7 = miuix.os.DeviceHelper.isTinyScreen(r7)
            if (r7 == 0) goto L30
            r7 = r5
            goto L31
        L30:
            r7 = r4
        L31:
            if (r7 == 0) goto L40
            android.content.Context r8 = r10.mContext
            android.content.res.Resources r8 = r8.getResources()
            int r9 = miuix.appcompat.R.dimen.miuix_appcompat_dialog_width_small_margin
            int r8 = r8.getDimensionPixelSize(r9)
            goto L4c
        L40:
            android.content.Context r8 = r10.mContext
            android.content.res.Resources r8 = r8.getResources()
            int r9 = miuix.appcompat.R.dimen.miuix_appcompat_dialog_ime_margin
            int r8 = r8.getDimensionPixelSize(r9)
        L4c:
            r10.mPanelBottomMargin = r8
            if (r3 == 0) goto L5a
            int r11 = r0.y
            int r11 = r11 - r1
            boolean r0 = r10.mNavigationBarHiddenEnabled
            if (r0 == 0) goto L58
            r2 = r4
        L58:
            int r11 = r11 - r2
            goto L64
        L5a:
            if (r11 == 0) goto L60
            int r11 = r0.y
            int r11 = r11 - r1
            goto L64
        L60:
            int r11 = r0.y
            int r11 = r11 - r1
            goto L58
        L64:
            if (r7 == 0) goto L69
            int r0 = r10.mDialogParentPanelFlipTinyFixedHeight
            goto L76
        L69:
            android.content.Context r0 = r10.mContext
            int r0 = miuix.core.util.MiuixUIUtils.getFontLevel(r0)
            if (r0 != r6) goto L74
            int r0 = r10.mDialogParentPanelLargeFontFixedHeight
            goto L76
        L74:
            int r0 = r10.mDialogParentPanelFixedHeight
        L76:
            int r1 = r0 + r8
            if (r11 >= r1) goto L7c
            int r0 = r11 - r8
        L7c:
            miuix.appcompat.app.AlertController r11 = r10.mAlert
            r11.setPanelFixedSizeEnabled(r5)
            miuix.appcompat.app.AlertController r10 = r10.mAlert
            r10.setPanelFixedHeight(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.app.PairingDialog.updateParentPanelFixedHeight(android.content.res.Configuration):int");
    }

    public ViewGroup getButtonPanel() {
        return this.mDialogButtonPanel;
    }

    public LinearLayout getFeedbackContainer() {
        return this.mFeedbackContainer;
    }

    public TextView getFeedbackMessageView() {
        return this.mFeedBackMessageView;
    }

    public AppCompatImageView getLabelImageView() {
        return this.mLabelImage;
    }

    public TextView getTitleView() {
        return this.mTitleView;
    }

    public void hideFeedbackMessage() {
        showOrHideDialogButtonPanel(true);
        showOrHideFeedbackMessage(false);
    }

    public boolean isButtonPanelVisible() {
        ViewGroup viewGroup = this.mDialogButtonPanel;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    @Override // miuix.appcompat.app.AlertDialog
    public boolean isChecked() {
        LinearLayout linearLayout = this.mCheckboxContainer;
        if (linearLayout == null || linearLayout.getVisibility() == 8) {
            return false;
        }
        return this.mDefaultCheckbox.isChecked();
    }

    public boolean isFeedbackMessageVisible() {
        LinearLayout linearLayout = this.mFeedbackContainer;
        return linearLayout != null && linearLayout.getVisibility() == 0;
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        this.mAlert.setNavigationHiddenEnabled(this.mNavigationBarHiddenEnabled && this.mIsFullScreenGestureMode);
        updateParentPanelFixedHeight(this.mContext.getResources().getConfiguration());
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.miuix_appcompat_pairing_dialog_content, (ViewGroup) null);
        beforeInstallDialogContent(viewInflate);
        setupTitle();
        setupMessageView();
        setupCustomContent();
        setupLabelImage();
        setView(viewInflate);
        super.onCreate(bundle);
        afterInstallDialogContent();
        setParentPanelConfigChangedCallback(this.mDialogParentPanel);
        fixedButtonPanelToBottom(this.mDialogParentPanel, this.mDialogContentPanel, this.mDialogButtonPanel);
        DialogParentPanel2 dialogParentPanel2 = this.mDialogParentPanel;
        if (dialogParentPanel2 != null) {
            dialogParentPanel2.setCornerRadius(getPanelCornerRadius(this.mContext));
            DialogParentPanel2 dialogParentPanel22 = this.mDialogParentPanel;
            dialogParentPanel22.setPadding(dialogParentPanel22.getPaddingStart(), 0, this.mDialogParentPanel.getPaddingEnd(), this.mDialogParentPanelPaddingBottom);
        }
        ViewGroup viewGroup = this.mDialogButtonPanel;
        if (viewGroup != null) {
            if (viewGroup instanceof DialogButtonPanel) {
                ((DialogButtonPanel) viewGroup).setCustomPaddingEnabled(true);
                ((DialogButtonPanel) this.mDialogButtonPanel).setCustomPaddingHorizontal(this.mDialogButtonPanelHPadding);
            }
            ViewGroup viewGroup2 = this.mDialogButtonPanel;
            viewGroup2.setPadding(this.mDialogButtonPanelHPadding, viewGroup2.getPaddingTop(), this.mDialogButtonPanelHPadding, this.mDialogButtonPanel.getPaddingBottom());
            ViewGroup.LayoutParams layoutParams = this.mDialogButtonPanel.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = 0;
            }
        }
        ImageView imageView = (ImageView) findViewById(R.id.pairingClosable);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: miuix.appcompat.app.PairingDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (PairingDialog.this.mCloseClickListener != null) {
                        PairingDialog.this.mCloseClickListener.onClick(PairingDialog.this.mAlert.mDialog, -2);
                    }
                    PairingDialog.this.dismiss();
                }
            });
            AnimHelper.addPressAnim(imageView);
        }
        adjustSpringBackEnabled();
    }

    public void setButton(int i2, CharSequence charSequence, boolean z2, DialogInterface.OnClickListener onClickListener) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i2;
        messageObtain.obj = onClickListener;
        Bundle bundle = new Bundle();
        bundle.putBoolean(AlertDialog.KEY_BUTTON_CLICK_AUTO_DISMISSIBLE, z2);
        messageObtain.setData(bundle);
        this.mAlert.setButton(i2, charSequence, null, messageObtain);
    }

    public void setCheckbox(boolean z2, CharSequence charSequence) {
        this.mIsChecked = z2;
        this.mCheckboxMessage = charSequence;
    }

    public void setCloseClickListener(DialogInterface.OnClickListener onClickListener) {
        this.mCloseClickListener = onClickListener;
    }

    public void setCustomView(View view) {
        this.mCustomView = view;
        this.mCustomLayoutResId = 0;
    }

    public void setCustomViewVerticalCenterEnabled(boolean z2) {
        this.mCustomViewVerticalCenterEnabled = z2;
    }

    public void setFeedbackMessage(CharSequence charSequence) {
        TextView textView = this.mFeedBackMessageView;
        if (textView == null) {
            return;
        }
        textView.setText(charSequence);
    }

    public void setLabelImage(Drawable drawable) {
        this.mLabelDrawable = drawable;
        this.mLabelDrawableResId = 0;
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
    }

    public void setNavigationBarHiddenEnabled(boolean z2) {
        this.mNavigationBarHiddenEnabled = z2;
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
    }

    public void showFeedbackMessage() {
        showOrHideDialogButtonPanel(false);
        showOrHideFeedbackMessage(true);
    }

    public void showOrHideDialogButtonPanel(boolean z2) {
        ViewGroup viewGroup = this.mDialogButtonPanel;
        if (viewGroup == null) {
            return;
        }
        if (z2 && viewGroup.getVisibility() == 8) {
            this.mDialogButtonPanel.setVisibility(0);
        } else if (!z2 && this.mDialogButtonPanel.getVisibility() == 0) {
            this.mDialogButtonPanel.setVisibility(8);
        }
        adjustPairingParentPanelPaddingBottom();
    }

    public void showOrHideFeedbackMessage(boolean z2) {
        if (this.mDialogParentPanel == null) {
            return;
        }
        if (z2 && this.mFeedbackContainer.getVisibility() == 8) {
            this.mFeedbackContainer.setVisibility(0);
        } else if (!z2 && this.mFeedbackContainer.getVisibility() == 0) {
            this.mFeedbackContainer.setVisibility(8);
        }
        adjustPairingParentPanelPaddingBottom();
    }

    @Override // miuix.appcompat.app.AlertDialog
    public AppCompatTextView getMessageView() {
        return this.mMessageView;
    }

    public void setMessage(@StringRes int i2) {
        this.mMessage = this.mContext.getResources().getString(i2);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(@StringRes int i2) {
        this.mTitle = this.mContext.getResources().getString(i2);
    }

    public void setCustomView(@LayoutRes int i2) {
        this.mCustomView = null;
        this.mCustomLayoutResId = i2;
    }

    public void setLabelImage(Drawable drawable, int i2, int i3) {
        setLabelImage(drawable);
        this.mLabelImageWidth = i2;
        this.mLabelImageHeight = i3;
    }

    public void setLabelImage(int i2) {
        this.mLabelDrawable = null;
        this.mLabelDrawableResId = i2;
    }

    public PairingDialog(@NonNull Context context, int i2) {
        super(context, i2);
        this.mWindowManager = null;
        this.mLabelImageWidth = -2;
        this.mLabelImageHeight = -2;
        this.mCustomViewVerticalCenterEnabled = true;
        this.mNavigationBarHiddenEnabled = false;
        init(context);
    }

    public void setButton(int i2, @StringRes int i3, boolean z2, DialogInterface.OnClickListener onClickListener) {
        setButton(i2, this.mContext.getResources().getString(i3), z2, onClickListener);
    }

    public void setLabelImage(int i2, int i3, int i4) {
        setLabelImage(i2);
        this.mLabelImageWidth = i3;
        this.mLabelImageHeight = i4;
    }

    public PairingDialog(@NonNull Context context, boolean z2, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z2, onCancelListener);
        this.mWindowManager = null;
        this.mLabelImageWidth = -2;
        this.mLabelImageHeight = -2;
        this.mCustomViewVerticalCenterEnabled = true;
        this.mNavigationBarHiddenEnabled = false;
        init(context);
    }
}
