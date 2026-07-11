package miuix.appcompat.app.floatingactivity;

import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SingleAppFloatingLifecycleObserver extends FloatingLifecycleObserver {

    public class CloseExitListener extends TransitionListener {
        WeakReference<AppCompatActivity> mHostActivity;

        public CloseExitListener(AppCompatActivity appCompatActivity) {
            this.mHostActivity = new WeakReference<>(appCompatActivity);
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            FloatingActivitySwitcher floatingActivitySwitcher;
            View lastActivityPanel;
            super.onComplete(obj);
            AppCompatActivity appCompatActivity = this.mHostActivity.get();
            if (appCompatActivity == null || appCompatActivity.isDestroyed() || (floatingActivitySwitcher = FloatingActivitySwitcher.getInstance()) == null || (lastActivityPanel = floatingActivitySwitcher.getLastActivityPanel()) == null) {
                return;
            }
            ((ViewGroup) appCompatActivity.getFloatingBrightPanel().getParent()).getOverlay().remove(lastActivityPanel);
        }
    }

    public SingleAppFloatingLifecycleObserver(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    private void execEnterNotInFloatingWindowMode(AppCompatActivity appCompatActivity) {
        FloatingActivitySwitcher floatingActivitySwitcher = FloatingActivitySwitcher.getInstance();
        if (FloatingAnimHelper.obtainPageIndex(appCompatActivity) < 0 || appCompatActivity.isInFloatingWindowMode() || floatingActivitySwitcher == null) {
            return;
        }
        floatingActivitySwitcher.markActivityOpenEnterAnimExecutedInternal(appCompatActivity);
        FloatingAnimHelper.preformFloatingExitAnimWithClip(appCompatActivity, false);
    }

    private void executeCloseExit(final AppCompatActivity appCompatActivity) {
        FloatingActivitySwitcher floatingActivitySwitcher;
        final View lastActivityPanel;
        if (FloatingAnimHelper.isSupportTransWithClipAnim() || (floatingActivitySwitcher = FloatingActivitySwitcher.getInstance()) == null || (lastActivityPanel = floatingActivitySwitcher.getLastActivityPanel()) == null) {
            return;
        }
        lastActivityPanel.post(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6027a.lambda$executeCloseExit$0(lastActivityPanel, appCompatActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeCloseExit$0(View view, AppCompatActivity appCompatActivity) {
        View childAt = ((ViewGroup) view).getChildAt(0);
        if (childAt != null) {
            AnimConfig animConfig = FloatingSwitcherAnimHelper.getAnimConfig(0, null);
            animConfig.addListeners(new CloseExitListener(appCompatActivity));
            FloatingSwitcherAnimHelper.executeCloseExitAnimation(childAt, animConfig);
        }
    }

    private void reenterTransition(AppCompatActivity appCompatActivity) {
        ArrayList<AppCompatActivity> activityList;
        int activityIndex;
        AppCompatActivity appCompatActivity2;
        FloatingActivitySwitcher floatingActivitySwitcher = FloatingActivitySwitcher.getInstance();
        if (floatingActivitySwitcher == null || (activityList = floatingActivitySwitcher.getActivityList(appCompatActivity.getTaskId())) == null || (activityIndex = floatingActivitySwitcher.getActivityIndex(appCompatActivity) + 1) >= activityList.size() || (appCompatActivity2 = activityList.get(activityIndex)) == null || !appCompatActivity2.isFinishing()) {
            return;
        }
        executeCloseExit(appCompatActivity);
    }

    @Override // miuix.appcompat.app.floatingactivity.FloatingLifecycleObserver
    public void onCreate() {
        AppCompatActivity activity;
        FloatingActivitySwitcher floatingActivitySwitcher = FloatingActivitySwitcher.getInstance();
        if (floatingActivitySwitcher == null || (activity = floatingActivitySwitcher.getActivity(getActivityIdentity(), getActivityTaskId())) == null) {
            return;
        }
        if (floatingActivitySwitcher.getPreviousActivity(activity) == null) {
            execEnterNotInFloatingWindowMode(activity);
            return;
        }
        if (!activity.isInFloatingWindowMode()) {
            floatingActivitySwitcher.markActivityOpenEnterAnimExecutedInternal(activity);
            FloatingAnimHelper.preformFloatingExitAnimWithClip(activity, false);
        } else {
            if (floatingActivitySwitcher.isActivityOpenEnterAnimExecuted(activity)) {
                return;
            }
            floatingActivitySwitcher.markActivityOpenEnterAnimExecutedInternal(activity);
            FloatingAnimHelper.singleAppFloatingActivityEnter(activity);
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.FloatingLifecycleObserver
    public void onDestroy() {
        FloatingActivitySwitcher floatingActivitySwitcher = FloatingActivitySwitcher.getInstance();
        if (floatingActivitySwitcher != null) {
            floatingActivitySwitcher.remove(getActivityIdentity(), getActivityTaskId());
        }
    }

    @Override // miuix.appcompat.app.floatingactivity.FloatingLifecycleObserver
    public void onResume() {
        AppCompatActivity activity;
        FloatingActivitySwitcher floatingActivitySwitcher = FloatingActivitySwitcher.getInstance();
        if (floatingActivitySwitcher == null || (activity = floatingActivitySwitcher.getActivity(getActivityIdentity(), getActivityTaskId())) == null || !activity.isInFloatingWindowMode()) {
            return;
        }
        if (floatingActivitySwitcher.getPreviousActivity(activity) != null) {
            activity.hideFloatingDimBackground();
        }
        reenterTransition(activity);
    }
}
