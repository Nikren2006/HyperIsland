package miuix.view;

import android.view.ActionMode;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes5.dex */
public interface SearchActionMode {

    public interface AnimatedViewListener {
        void onInSearchMode(boolean z2);

        void onUpdateOffsetY(int i2);
    }

    public interface Callback extends ActionMode.Callback {
    }

    void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener);

    View getCustomView();

    EditText getSearchInput();

    void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener);

    void resetCustomView();

    void setAnchorApplyExtraPaddingByUser(boolean z2);

    void setAnchorView(View view);

    void setAnimateView(View view);

    void setAnimatedViewListener(AnimatedViewListener animatedViewListener);

    void setCustomView(View view);

    void setResultView(View view);
}
