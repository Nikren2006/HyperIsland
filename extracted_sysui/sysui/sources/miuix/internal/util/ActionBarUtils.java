package miuix.internal.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.internal.app.widget.ActionBarMovableLayout;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarUtils {
    private ActionBarUtils() {
    }

    @Nullable
    public static ViewGroup getActionBarOverlayLayout(View view) {
        while (view != null) {
            if (view instanceof ActionBarOverlayLayout) {
                return (ActionBarOverlayLayout) view;
            }
            view = view.getParent() instanceof View ? (View) view.getParent() : null;
        }
        return null;
    }

    public static void setOnScrollListener(Activity activity, ActionBar.OnScrollListener onScrollListener) {
        ((ActionBarMovableLayout) activity.findViewById(R.id.action_bar_overlay_layout)).setOnScrollListener(onScrollListener);
    }
}
