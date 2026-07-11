package miuix.appcompat.internal.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.View;
import android.widget.EditText;
import java.lang.ref.WeakReference;
import miuix.appcompat.internal.app.widget.ActionModeView;
import miuix.appcompat.internal.app.widget.SearchActionModeView;
import miuix.view.ActionModeAnimationListener;
import miuix.view.SearchActionMode;

/* JADX INFO: loaded from: classes3.dex */
public class SearchActionModeImpl extends ActionModeImpl implements SearchActionMode {
    public SearchActionModeImpl(Context context, ActionMode.Callback callback) {
        super(context, callback);
    }

    @Override // miuix.view.SearchActionMode
    public void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        this.mActionModeView.get().addAnimationListener(actionModeAnimationListener);
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public View getCustomView() {
        return ((SearchActionModeView) this.mActionModeView.get()).getCustomView();
    }

    @Override // miuix.view.SearchActionMode
    public EditText getSearchInput() {
        return ((SearchActionModeView) this.mActionModeView.get()).getSearchInput();
    }

    @Override // miuix.view.SearchActionMode
    public void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        this.mActionModeView.get().removeAnimationListener(actionModeAnimationListener);
    }

    @Override // miuix.view.SearchActionMode
    public void resetCustomView() {
        ((SearchActionModeView) this.mActionModeView.get()).resetCustomView();
    }

    @Override // miuix.view.SearchActionMode
    public void setAnchorApplyExtraPaddingByUser(boolean z2) {
        ((SearchActionModeView) this.mActionModeView.get()).setAnchorApplyExtraPaddingByUser(z2);
    }

    @Override // miuix.view.SearchActionMode
    public void setAnchorView(View view) {
        ((SearchActionModeView) this.mActionModeView.get()).setAnchorView(view);
    }

    @Override // miuix.view.SearchActionMode
    public void setAnimateView(View view) {
        ((SearchActionModeView) this.mActionModeView.get()).setAnimateView(view);
    }

    @Override // miuix.view.SearchActionMode
    public void setAnimatedViewListener(SearchActionMode.AnimatedViewListener animatedViewListener) {
        ((SearchActionModeView) this.mActionModeView.get()).setAnimatedViewListener(animatedViewListener);
    }

    @Override // miuix.appcompat.internal.view.ActionModeImpl, android.view.ActionMode
    public void setCustomView(View view) {
        ((SearchActionModeView) this.mActionModeView.get()).setCustomView(view);
    }

    public void setPendingInsets(Rect rect) {
        WeakReference<ActionModeView> weakReference = this.mActionModeView;
        SearchActionModeView searchActionModeView = weakReference != null ? (SearchActionModeView) weakReference.get() : null;
        if (searchActionModeView != null) {
            searchActionModeView.rePaddingAndRelayout(rect);
        }
    }

    @Override // miuix.view.SearchActionMode
    public void setResultView(View view) {
        ((SearchActionModeView) this.mActionModeView.get()).setResultView(view);
    }
}
