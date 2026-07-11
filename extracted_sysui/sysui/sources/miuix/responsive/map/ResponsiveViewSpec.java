package miuix.responsive.map;

import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class ResponsiveViewSpec {
    private int mEffectiveScreenOrientation;
    private int mHideInScreenMode;

    @Nullable
    private View mView;
    private int mViewId;

    public ResponsiveViewSpec(int i2) {
        this.mViewId = i2;
    }

    public int getEffectiveScreenOrientation() {
        return this.mEffectiveScreenOrientation;
    }

    public int getHideInScreenMode() {
        return this.mHideInScreenMode;
    }

    public View getView() {
        return this.mView;
    }

    public int getViewId() {
        return this.mViewId;
    }

    public void onResponsiveState(ScreenSpec screenSpec) {
        int i2 = screenSpec.screenMode & 7;
        View view = this.mView;
        if (view != null) {
            view.setVisibility(this.mHideInScreenMode < i2 ? 0 : 8);
        }
    }

    public void setEffectiveScreenOrientation(int i2) {
        this.mEffectiveScreenOrientation = i2;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public ResponsiveViewSpec(int i2, int i3) {
        this.mViewId = i2;
        this.mHideInScreenMode = i3;
    }
}
