package miuix.appcompat.internal.app;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public class NavigatorSwitchPresenter {
    private float mAlpha;
    private final View mNavigatorSwitch;
    private boolean mSuppressAlpha;
    private boolean mSuppressVisibility;
    private int mVisibility;

    public NavigatorSwitchPresenter(View view) {
        this.mNavigatorSwitch = view;
        this.mVisibility = view.getVisibility();
        this.mAlpha = view.getAlpha();
    }

    public void setAlpha(float f2) {
        this.mAlpha = f2;
        if (this.mSuppressAlpha) {
            return;
        }
        this.mNavigatorSwitch.setAlpha(f2);
    }

    public void setVisibility(int i2) {
        this.mVisibility = i2;
        if (this.mSuppressVisibility) {
            return;
        }
        this.mNavigatorSwitch.setVisibility(i2);
    }

    public void suppressAlpha(boolean z2, float f2) {
        this.mSuppressAlpha = z2;
        if (z2) {
            this.mNavigatorSwitch.setAlpha(f2);
        } else {
            this.mNavigatorSwitch.setAlpha(this.mAlpha);
        }
    }

    public void suppressVisibility(boolean z2, int i2) {
        this.mSuppressVisibility = z2;
        if (z2) {
            this.mNavigatorSwitch.setVisibility(i2);
        } else {
            this.mNavigatorSwitch.setVisibility(this.mVisibility);
        }
    }
}
