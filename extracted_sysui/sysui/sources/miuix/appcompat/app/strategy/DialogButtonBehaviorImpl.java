package miuix.appcompat.app.strategy;

import miuix.appcompat.app.DialogContract;

/* JADX INFO: loaded from: classes2.dex */
public class DialogButtonBehaviorImpl implements IDialogButtonBehavior {
    @Override // miuix.appcompat.app.strategy.IDialogButtonBehavior
    public boolean isButtonScrollable(DialogContract.ButtonScrollSpec buttonScrollSpec) {
        if (buttonScrollSpec.mButtonFVHeight <= 0) {
            return false;
        }
        float fMax = Math.max(buttonScrollSpec.mWindowHeight, 1);
        float fMax2 = (Math.max(buttonScrollSpec.mButtonPanelHeight, buttonScrollSpec.mButtonFVHeight) * 1.0f) / fMax;
        float f2 = (buttonScrollSpec.mTopPanelHeight * 1.0f) / fMax;
        boolean z2 = buttonScrollSpec.mIsFlipTiny;
        boolean z3 = z2 && buttonScrollSpec.mWindowOrientation == 2;
        boolean z4 = !buttonScrollSpec.mHasListView && (z2 || buttonScrollSpec.mRootViewSizeYDp <= 480) && buttonScrollSpec.mVisibleButtonCount >= 3;
        boolean z5 = buttonScrollSpec.mIsLargeFont;
        return fMax2 >= (z5 ? 0.3f : 0.4f) || f2 >= (z5 ? 0.35f : 0.45f) || z4 || z3;
    }
}
