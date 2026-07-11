package miuix.transition;

import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.transition.MiuixTransitionManager;
import java.util.LinkedList;
import miuix.animation.base.AnimConfig;
import miuix.animation.base.AnimSpecialConfig;

/* JADX INFO: loaded from: classes5.dex */
public class AlertDialogAnimator extends LayoutAnimator {
    private static final String TAG = "AlertDialogAnimator";
    private final LinearLayout mButtonPanel;
    private final ViewGroup mDialogRootView;
    private boolean mDuringTransition;
    private ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    private LinkedList<Runnable> mPendingViewConfig = new LinkedList<>();
    private AnimConfig mTransitionConfig;

    public AlertDialogAnimator(ViewGroup viewGroup, ViewGroup viewGroup2) {
        this.mDialogRootView = viewGroup;
        this.mButtonPanel = (LinearLayout) viewGroup2;
    }

    @Override // miuix.transition.LayoutAnimator
    public void prepareTransition() {
        if (this.mTransition == null) {
            this.mTransitionConfig = new AnimConfig().setEase(LayoutAnimator.spring(0.95f, 0.35f));
            AnimConfig ease = new AnimConfig().setEase(LayoutAnimator.spring(0.95f, 0.15f));
            AnimConfig ease2 = new AnimConfig().setEase(LayoutAnimator.spring(0.95f, 0.35f));
            ease.enableStartImmediately(true);
            ease2.enableStartImmediately(true);
            MiuixTransitionSet miuixTransitionSetAddTransition = new MiuixTransitionSet().addTransition(new Fade(2).setForceUseOverlay(true).setAnimConfig(ease)).addTransition(new ChangeBounds().setAnimConfig(this.mTransitionConfig)).addTransition(new Fade(1).setAnimConfig(ease2));
            this.mTransition = miuixTransitionSetAddTransition;
            miuixTransitionSetAddTransition.addListener(new TransitionListenerAdapter() { // from class: miuix.transition.AlertDialogAnimator.2
                @Override // miuix.transition.TransitionListenerAdapter, miuix.transition.MiuixTransition.MiuixTransitionListener
                public void onTransitionEnd(MiuixTransition miuixTransition) {
                    Log.i(AlertDialogAnimator.TAG, "on alertdialog animator end");
                    AlertDialogAnimator.this.mDuringTransition = false;
                    if (AlertDialogAnimator.this.mPendingViewConfig.isEmpty()) {
                        return;
                    }
                    Log.i(AlertDialogAnimator.TAG, "dequeue an animation form the pending list and execute it.");
                    Runnable runnable = (Runnable) AlertDialogAnimator.this.mPendingViewConfig.remove();
                    if (runnable != null) {
                        MiuixTransitionManager.beginDelayedTransition(AlertDialogAnimator.this.mDialogRootView, AlertDialogAnimator.this.mTransition);
                        runnable.run();
                    }
                }

                @Override // miuix.transition.TransitionListenerAdapter, miuix.transition.MiuixTransition.MiuixTransitionListener
                public void onTransitionStart(MiuixTransition miuixTransition) {
                    Log.i(AlertDialogAnimator.TAG, "on alertdialog animator start");
                    AlertDialogAnimator.this.mDuringTransition = true;
                }
            });
        }
    }

    @Override // miuix.transition.LayoutAnimator
    public void traceChangeToTransition(Runnable runnable) {
        Log.i(TAG, "ready for Animator");
        prepareTransition();
        if (this.mButtonPanel != null) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: miuix.transition.AlertDialogAnimator.1
                    boolean lastUseVertical;

                    @Override // android.view.ViewTreeObserver.OnPreDrawListener
                    public boolean onPreDraw() {
                        boolean z2 = AlertDialogAnimator.this.mButtonPanel.getOrientation() == 1;
                        if (z2 != this.lastUseVertical) {
                            if (z2) {
                                AlertDialogAnimator.this.mTransitionConfig.setSpecial("left", (AnimSpecialConfig) null).setSpecial("right", (AnimSpecialConfig) null);
                            } else {
                                AlertDialogAnimator.this.mTransitionConfig.setSpecial("left", LayoutAnimator.spring(0.95f, 0.25f), new float[0]).setSpecial("right", LayoutAnimator.spring(0.95f, 0.25f), new float[0]);
                            }
                        }
                        this.lastUseVertical = z2;
                        AlertDialogAnimator.this.mButtonPanel.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                };
            }
            this.mButtonPanel.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (runnable == null) {
            MiuixTransitionManager.beginDelayedTransition(this.mDialogRootView, this.mTransition);
            return;
        }
        if (this.mDuringTransition) {
            Log.i(TAG, "add view config task to pending list.");
            this.mPendingViewConfig.add(runnable);
        } else {
            Log.i(TAG, "execute view config task immediately.");
            MiuixTransitionManager.beginDelayedTransition(this.mDialogRootView, this.mTransition);
            runnable.run();
        }
    }
}
