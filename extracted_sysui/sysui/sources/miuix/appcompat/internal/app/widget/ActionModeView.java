package miuix.appcompat.internal.app.widget;

import android.view.ActionMode;
import miuix.view.ActionModeAnimationListener;

/* JADX INFO: loaded from: classes3.dex */
public interface ActionModeView {
    public static final int ANIMATION_DURATION = 300;

    void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener);

    void animateToVisibility(boolean z2);

    void closeMode();

    int getViewHeight();

    void initForMode(ActionMode actionMode);

    void killMode();

    void notifyAnimationEnd(boolean z2);

    void notifyAnimationStart(boolean z2);

    void notifyAnimationUpdate(boolean z2, float f2);

    void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener);
}
