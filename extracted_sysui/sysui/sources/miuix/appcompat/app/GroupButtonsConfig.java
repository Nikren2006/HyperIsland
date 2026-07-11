package miuix.appcompat.app;

import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import miuix.appcompat.widget.Button;

/* JADX INFO: loaded from: classes2.dex */
public class GroupButtonsConfig {
    private Button mPrimaryButton;
    private Button mSecondaryButton;
    private Button mTertiaryButton;
    private int mOrientation = 1;
    private CharSequence mPrimaryButtonText = "";
    private int mPrimaryButtonVisibility = 8;
    private boolean mPrimaryButtonEnableState = true;
    private View.OnClickListener mOnPrimaryButtonClickListener = null;
    private View.OnLongClickListener mOnPrimaryButtonLongClickListener = null;
    private CharSequence mSecondaryButtonText = "";
    private int mSecondaryButtonVisibility = 8;
    private boolean mSecondaryButtonEnableState = true;
    private View.OnClickListener mOnSecondaryButtonClickListener = null;
    private View.OnLongClickListener mOnSecondaryButtonLongClickListener = null;
    private CharSequence mTertiaryButtonText = "";
    private int mTertiaryButtonVisibility = 8;
    private boolean mTertiaryButtonEnableState = true;
    private View.OnClickListener mOnTertiaryButtonClickListener = null;
    private View.OnLongClickListener mOnTertiaryButtonLongClickListener = null;

    public static class Builder {
        private final GroupButtonsConfig mConfig = new GroupButtonsConfig();

        public GroupButtonsConfig build() {
            return this.mConfig;
        }

        public Builder setButton(int i2, CharSequence charSequence) {
            return setButton(i2, charSequence, null, null);
        }

        public Builder setOrientation(int i2) {
            if (i2 == 1 || i2 == 0) {
                this.mConfig.mOrientation = i2;
            }
            return this;
        }

        public Builder setButton(int i2, CharSequence charSequence, View.OnClickListener onClickListener) {
            return setButton(i2, charSequence, onClickListener, null);
        }

        public Builder setButton(int i2, CharSequence charSequence, View.OnLongClickListener onLongClickListener) {
            return setButton(i2, charSequence, null, onLongClickListener);
        }

        public Builder setButton(int i2, CharSequence charSequence, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
            return setButton(i2, charSequence, onClickListener, onLongClickListener, true);
        }

        public Builder setButton(int i2, CharSequence charSequence, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, boolean z2) {
            return setButton(i2, charSequence, onClickListener, onLongClickListener, z2, true);
        }

        public Builder setButton(int i2, CharSequence charSequence, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, boolean z2, boolean z3) {
            int i3 = z3 ? 0 : 8;
            if (i2 == 0) {
                this.mConfig.mPrimaryButtonText = charSequence;
                this.mConfig.mOnPrimaryButtonClickListener = onClickListener;
                this.mConfig.mOnPrimaryButtonLongClickListener = onLongClickListener;
                this.mConfig.mPrimaryButtonEnableState = z2;
                this.mConfig.mPrimaryButtonVisibility = i3;
            } else if (i2 == 1) {
                this.mConfig.mSecondaryButtonText = charSequence;
                this.mConfig.mOnSecondaryButtonClickListener = onClickListener;
                this.mConfig.mOnSecondaryButtonLongClickListener = onLongClickListener;
                this.mConfig.mSecondaryButtonEnableState = z2;
                this.mConfig.mSecondaryButtonVisibility = i3;
            } else if (i2 == 2) {
                this.mConfig.mTertiaryButtonText = charSequence;
                this.mConfig.mOnTertiaryButtonClickListener = onClickListener;
                this.mConfig.mOnTertiaryButtonLongClickListener = onLongClickListener;
                this.mConfig.mTertiaryButtonEnableState = z2;
                this.mConfig.mTertiaryButtonVisibility = i3;
            }
            return this;
        }
    }

    public static class ButtonLayoutType {
        public static final int HORIZONTAL = 0;
        public static final int VERTICAL = 1;
    }

    public static class ButtonType {
        public static final int PRIMARY = 0;
        public static final int SECONDARY = 1;
        public static final int TERTIARY = 2;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    @Nullable
    public Button getPrimaryButton() {
        return this.mPrimaryButton;
    }

    @Nullable
    public Button getSecondaryButton() {
        return this.mSecondaryButton;
    }

    @Nullable
    public Button getTertiaryButton() {
        return this.mTertiaryButton;
    }

    public void initButton(int i2, Button button) {
        if (i2 == 0) {
            button.setText(this.mPrimaryButtonText);
            button.setOnClickListener(this.mOnPrimaryButtonClickListener);
            button.setOnLongClickListener(this.mOnPrimaryButtonLongClickListener);
            button.setEnabled(this.mPrimaryButtonEnableState);
            button.setVisibility(this.mPrimaryButtonVisibility);
            this.mPrimaryButton = button;
            return;
        }
        if (i2 == 1) {
            button.setText(this.mSecondaryButtonText);
            button.setOnClickListener(this.mOnSecondaryButtonClickListener);
            button.setOnLongClickListener(this.mOnSecondaryButtonLongClickListener);
            button.setEnabled(this.mSecondaryButtonEnableState);
            button.setVisibility(this.mSecondaryButtonVisibility);
            this.mSecondaryButton = button;
            return;
        }
        if (i2 != 2) {
            return;
        }
        button.setText(this.mTertiaryButtonText);
        button.setOnClickListener(this.mOnTertiaryButtonClickListener);
        button.setOnLongClickListener(this.mOnTertiaryButtonLongClickListener);
        button.setEnabled(this.mTertiaryButtonEnableState);
        button.setVisibility(this.mTertiaryButtonVisibility);
        this.mTertiaryButton = button;
    }

    public void setButtonEnabled(int i2, boolean z2) {
        if (i2 == 0) {
            if (this.mPrimaryButtonEnableState != z2) {
                this.mPrimaryButtonEnableState = z2;
                this.mPrimaryButton.setEnabled(z2);
                return;
            }
            return;
        }
        if (i2 == 1) {
            if (this.mSecondaryButtonEnableState != z2) {
                this.mSecondaryButtonEnableState = z2;
                this.mSecondaryButton.setEnabled(z2);
                return;
            }
            return;
        }
        if (i2 == 2 && this.mTertiaryButtonEnableState != z2) {
            this.mTertiaryButtonEnableState = z2;
            this.mTertiaryButton.setEnabled(z2);
        }
    }

    public void setButtonVisible(int i2, boolean z2) {
        int i3 = z2 ? 0 : 8;
        if (i2 == 0) {
            if (this.mPrimaryButtonVisibility != i3) {
                this.mPrimaryButtonVisibility = i3;
                this.mPrimaryButton.setVisibility(i3);
                return;
            }
            return;
        }
        if (i2 == 1) {
            if (this.mSecondaryButtonVisibility != i3) {
                this.mSecondaryButtonVisibility = i3;
                this.mSecondaryButton.setVisibility(i3);
                return;
            }
            return;
        }
        if (i2 == 2 && this.mTertiaryButtonVisibility != i3) {
            this.mTertiaryButtonVisibility = i3;
            this.mTertiaryButton.setVisibility(i3);
        }
    }

    public void updateContentDescription(int i2, CharSequence charSequence) {
        if (i2 == 0) {
            if (TextUtils.equals(this.mPrimaryButton.getContentDescription(), charSequence)) {
                return;
            }
            this.mPrimaryButton.setContentDescription(charSequence);
        } else if (i2 == 1) {
            if (TextUtils.equals(this.mSecondaryButton.getContentDescription(), charSequence)) {
                return;
            }
            this.mSecondaryButton.setContentDescription(charSequence);
        } else if (i2 == 2 && !TextUtils.equals(this.mTertiaryButton.getContentDescription(), charSequence)) {
            this.mTertiaryButton.setContentDescription(charSequence);
        }
    }

    public void updateOnClickListener(int i2, View.OnClickListener onClickListener) {
        if (i2 == 0) {
            if (this.mOnPrimaryButtonClickListener != onClickListener) {
                this.mOnPrimaryButtonClickListener = onClickListener;
                this.mPrimaryButton.setOnClickListener(onClickListener);
                return;
            }
            return;
        }
        if (i2 == 1) {
            if (this.mOnSecondaryButtonClickListener != onClickListener) {
                this.mOnSecondaryButtonClickListener = onClickListener;
                this.mSecondaryButton.setOnClickListener(onClickListener);
                return;
            }
            return;
        }
        if (i2 == 2 && this.mOnTertiaryButtonClickListener != onClickListener) {
            this.mOnTertiaryButtonClickListener = onClickListener;
            this.mTertiaryButton.setOnClickListener(onClickListener);
        }
    }

    public void updateOnLongClickListener(int i2, View.OnLongClickListener onLongClickListener) {
        if (i2 == 0) {
            if (this.mOnPrimaryButtonLongClickListener != onLongClickListener) {
                this.mOnPrimaryButtonLongClickListener = onLongClickListener;
                this.mPrimaryButton.setOnLongClickListener(onLongClickListener);
                return;
            }
            return;
        }
        if (i2 == 1) {
            if (this.mOnSecondaryButtonLongClickListener != onLongClickListener) {
                this.mOnSecondaryButtonLongClickListener = onLongClickListener;
                this.mSecondaryButton.setOnLongClickListener(onLongClickListener);
                return;
            }
            return;
        }
        if (i2 == 2 && this.mOnTertiaryButtonLongClickListener != onLongClickListener) {
            this.mOnTertiaryButtonLongClickListener = onLongClickListener;
            this.mTertiaryButton.setOnLongClickListener(onLongClickListener);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateText(int r2, java.lang.CharSequence r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L1f
            r0 = 1
            if (r2 == r0) goto L12
            java.lang.CharSequence r2 = r1.mTertiaryButtonText
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L2c
            r1.mTertiaryButtonText = r3
            miuix.appcompat.widget.Button r1 = r1.mTertiaryButton
            goto L2d
        L12:
            java.lang.CharSequence r2 = r1.mSecondaryButtonText
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L2c
            r1.mSecondaryButtonText = r3
            miuix.appcompat.widget.Button r1 = r1.mSecondaryButton
            goto L2d
        L1f:
            java.lang.CharSequence r2 = r1.mPrimaryButtonText
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L2c
            r1.mPrimaryButtonText = r3
            miuix.appcompat.widget.Button r1 = r1.mPrimaryButton
            goto L2d
        L2c:
            r1 = 0
        L2d:
            if (r1 == 0) goto L35
            r1.setText(r3)
            r1.requestLayout()
        L35:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.app.GroupButtonsConfig.updateText(int, java.lang.CharSequence):void");
    }
}
