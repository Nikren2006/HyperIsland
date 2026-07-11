package miuix.appcompat.internal.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.View;
import androidx.annotation.StringRes;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarContextView;
import miuix.appcompat.internal.app.widget.ActionModeView;
import miuix.view.ActionModeAnimationListener;
import miuix.view.EditActionMode;

/* JADX INFO: loaded from: classes3.dex */
public class EditActionModeImpl extends ActionModeImpl implements EditActionMode {
    private boolean mAnnounceAccessibilityEnabled;
    private String mFinishEditActionModeDescription;
    private String mStartEditActionModeDescription;

    public EditActionModeImpl(Context context, ActionMode.Callback callback) {
        super(context, callback);
        this.mAnnounceAccessibilityEnabled = true;
    }

    private String getFinishEditActionModeDescription() {
        return TextUtils.isEmpty(this.mFinishEditActionModeDescription) ? this.mContext.getResources().getString(R.string.miuix_appcompat_accessibility_finish_edit_action_mode) : this.mFinishEditActionModeDescription;
    }

    private String getStartEditActionModeDescription() {
        return TextUtils.isEmpty(this.mStartEditActionModeDescription) ? this.mContext.getResources().getString(R.string.miuix_appcompat_accessibility_start_edit_action_mode) : this.mStartEditActionModeDescription;
    }

    @Override // miuix.view.EditActionMode
    public void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        this.mActionModeView.get().addAnimationListener(actionModeAnimationListener);
    }

    @Override // miuix.view.EditActionMode
    public void announceAccessibilityEvent(String str) {
        ActionModeView actionModeView = this.mActionModeView.get();
        if (actionModeView instanceof ActionBarContextView) {
            ((ActionBarContextView) actionModeView).announceForAccessibility(str);
        }
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl
    public boolean dispatchOnCreate() {
        boolean zDispatchOnCreate = super.dispatchOnCreate();
        if (this.mAnnounceAccessibilityEnabled && zDispatchOnCreate) {
            announceAccessibilityEvent(getStartEditActionModeDescription());
        }
        return zDispatchOnCreate;
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void finish() {
        super.finish();
        if (this.mAnnounceAccessibilityEnabled) {
            announceAccessibilityEvent(getFinishEditActionModeDescription());
        }
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public CharSequence getTitle() {
        return ((ActionBarContextView) this.mActionModeView.get()).getTitle();
    }

    @Override // miuix.view.EditActionMode
    public void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        this.mActionModeView.get().removeAnimationListener(actionModeAnimationListener);
    }

    @Override // miuix.view.EditActionMode
    public void setAnnounceAccessibilityEnabled(boolean z2) {
        this.mAnnounceAccessibilityEnabled = z2;
    }

    @Override // miuix.view.EditActionMode
    public void setButton(int i2, CharSequence charSequence) {
        ((ActionBarContextView) this.mActionModeView.get()).setButton(i2, charSequence);
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void setCustomView(View view) {
    }

    @Override // miuix.view.EditActionMode
    public void setFinishEditActionModeDescription(@StringRes int i2) {
        this.mFinishEditActionModeDescription = this.mContext.getResources().getString(i2);
    }

    @Override // miuix.view.EditActionMode
    public void setStartEditActionModeDescription(@StringRes int i2) {
        this.mStartEditActionModeDescription = this.mContext.getResources().getString(i2);
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void setSubtitle(int i2) {
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        ((ActionBarContextView) this.mActionModeView.get()).setTitle(charSequence);
    }

    @Override // miuix.view.EditActionMode
    public void setButton(int i2, CharSequence charSequence, CharSequence charSequence2, int i3) {
        ((ActionBarContextView) this.mActionModeView.get()).setButton(i2, charSequence, charSequence2, i3);
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void setTitle(int i2) {
        setTitle(this.mContext.getResources().getString(i2));
    }

    @Override // miuix.view.EditActionMode
    public void setButton(int i2, CharSequence charSequence, int i3, CharSequence charSequence2, int i4) {
        ((ActionBarContextView) this.mActionModeView.get()).setButton(i2, charSequence, i3, charSequence2, i4);
    }

    @Override // miuix.view.EditActionMode
    public void setButton(int i2, int i3) {
        setButton(i2, this.mContext.getResources().getString(i3));
    }

    @Override // miuix.view.EditActionMode
    public void setButton(int i2, CharSequence charSequence, int i3) {
        ((ActionBarContextView) this.mActionModeView.get()).setButton(i2, charSequence, i3);
    }

    @Override // miuix.view.EditActionMode
    public void setButton(int i2, int i3, int i4) {
        setButton(i2, this.mContext.getResources().getString(i3), i4);
    }
}
