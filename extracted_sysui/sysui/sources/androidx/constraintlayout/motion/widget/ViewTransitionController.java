package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.SharedValues;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ViewTransitionController {
    ArrayList<ViewTransition.Animate> mAnimations;
    private final MotionLayout mMotionLayout;
    private HashSet<View> mRelatedViews;
    private ArrayList<ViewTransition> mViewTransitions = new ArrayList<>();
    private String mTAG = "ViewTransitionController";
    ArrayList<ViewTransition.Animate> mRemoveList = new ArrayList<>();

    public ViewTransitionController(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
    }

    private void listenForSharedVariable(final ViewTransition viewTransition, final boolean z2) {
        final int sharedValueID = viewTransition.getSharedValueID();
        final int sharedValue = viewTransition.getSharedValue();
        ConstraintLayout.getSharedValues().addListener(viewTransition.getSharedValueID(), new SharedValues.SharedValuesListener() { // from class: androidx.constraintlayout.motion.widget.ViewTransitionController.1
            @Override // androidx.constraintlayout.widget.SharedValues.SharedValuesListener
            public void onNewValue(int i2, int i3, int i4) {
                int sharedValueCurrent = viewTransition.getSharedValueCurrent();
                viewTransition.setSharedValueCurrent(i3);
                if (sharedValueID != i2 || sharedValueCurrent == i3) {
                    return;
                }
                int i5 = 0;
                if (z2) {
                    if (sharedValue == i3) {
                        int childCount = ViewTransitionController.this.mMotionLayout.getChildCount();
                        while (i5 < childCount) {
                            View childAt = ViewTransitionController.this.mMotionLayout.getChildAt(i5);
                            if (viewTransition.matchesView(childAt)) {
                                int currentState = ViewTransitionController.this.mMotionLayout.getCurrentState();
                                ConstraintSet constraintSet = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState);
                                ViewTransition viewTransition2 = viewTransition;
                                ViewTransitionController viewTransitionController = ViewTransitionController.this;
                                viewTransition2.applyTransition(viewTransitionController, viewTransitionController.mMotionLayout, currentState, constraintSet, childAt);
                            }
                            i5++;
                        }
                        return;
                    }
                    return;
                }
                if (sharedValue != i3) {
                    int childCount2 = ViewTransitionController.this.mMotionLayout.getChildCount();
                    while (i5 < childCount2) {
                        View childAt2 = ViewTransitionController.this.mMotionLayout.getChildAt(i5);
                        if (viewTransition.matchesView(childAt2)) {
                            int currentState2 = ViewTransitionController.this.mMotionLayout.getCurrentState();
                            ConstraintSet constraintSet2 = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState2);
                            ViewTransition viewTransition3 = viewTransition;
                            ViewTransitionController viewTransitionController2 = ViewTransitionController.this;
                            viewTransition3.applyTransition(viewTransitionController2, viewTransitionController2.mMotionLayout, currentState2, constraintSet2, childAt2);
                        }
                        i5++;
                    }
                }
            }
        });
    }

    private void viewTransition(ViewTransition viewTransition, View... viewArr) {
        int currentState = this.mMotionLayout.getCurrentState();
        if (viewTransition.mViewTransitionMode == 2) {
            viewTransition.applyTransition(this, this.mMotionLayout, currentState, null, viewArr);
            return;
        }
        if (currentState != -1) {
            ConstraintSet constraintSet = this.mMotionLayout.getConstraintSet(currentState);
            if (constraintSet == null) {
                return;
            }
            viewTransition.applyTransition(this, this.mMotionLayout, currentState, constraintSet, viewArr);
            return;
        }
        Log.w(this.mTAG, "No support for ViewTransition within transition yet. Currently: " + this.mMotionLayout.toString());
    }

    public void add(ViewTransition viewTransition) {
        this.mViewTransitions.add(viewTransition);
        this.mRelatedViews = null;
        if (viewTransition.getStateTransition() == 4) {
            listenForSharedVariable(viewTransition, true);
        } else if (viewTransition.getStateTransition() == 5) {
            listenForSharedVariable(viewTransition, false);
        }
    }

    public void addAnimation(ViewTransition.Animate animate) {
        if (this.mAnimations == null) {
            this.mAnimations = new ArrayList<>();
        }
        this.mAnimations.add(animate);
    }

    public void animate() {
        ArrayList<ViewTransition.Animate> arrayList = this.mAnimations;
        if (arrayList == null) {
            return;
        }
        Iterator<ViewTransition.Animate> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().mutate();
        }
        this.mAnimations.removeAll(this.mRemoveList);
        this.mRemoveList.clear();
        if (this.mAnimations.isEmpty()) {
            this.mAnimations = null;
        }
    }

    public boolean applyViewTransition(int i2, MotionController motionController) {
        for (ViewTransition viewTransition : this.mViewTransitions) {
            if (viewTransition.getId() == i2) {
                viewTransition.mKeyFrames.addAllFrames(motionController);
                return true;
            }
        }
        return false;
    }

    public void enableViewTransition(int i2, boolean z2) {
        for (ViewTransition viewTransition : this.mViewTransitions) {
            if (viewTransition.getId() == i2) {
                viewTransition.setEnabled(z2);
                return;
            }
        }
    }

    public void invalidate() {
        this.mMotionLayout.invalidate();
    }

    public boolean isViewTransitionEnabled(int i2) {
        for (ViewTransition viewTransition : this.mViewTransitions) {
            if (viewTransition.getId() == i2) {
                return viewTransition.isEnabled();
            }
        }
        return false;
    }

    public void remove(int i2) {
        ViewTransition next;
        Iterator<ViewTransition> it = this.mViewTransitions.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.getId() == i2) {
                    break;
                }
            }
        }
        if (next != null) {
            this.mRelatedViews = null;
            this.mViewTransitions.remove(next);
        }
    }

    public void removeAnimation(ViewTransition.Animate animate) {
        this.mRemoveList.add(animate);
    }

    public void touchEvent(MotionEvent motionEvent) {
        int currentState = this.mMotionLayout.getCurrentState();
        if (currentState == -1) {
            return;
        }
        if (this.mRelatedViews == null) {
            this.mRelatedViews = new HashSet<>();
            for (ViewTransition viewTransition : this.mViewTransitions) {
                int childCount = this.mMotionLayout.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = this.mMotionLayout.getChildAt(i2);
                    if (viewTransition.matchesView(childAt)) {
                        childAt.getId();
                        this.mRelatedViews.add(childAt);
                    }
                }
            }
        }
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        Rect rect = new Rect();
        int action = motionEvent.getAction();
        ArrayList<ViewTransition.Animate> arrayList = this.mAnimations;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<ViewTransition.Animate> it = this.mAnimations.iterator();
            while (it.hasNext()) {
                it.next().reactTo(action, x2, y2);
            }
        }
        if (action == 0 || action == 1) {
            ConstraintSet constraintSet = this.mMotionLayout.getConstraintSet(currentState);
            for (ViewTransition viewTransition2 : this.mViewTransitions) {
                if (viewTransition2.supports(action)) {
                    for (View view : this.mRelatedViews) {
                        if (viewTransition2.matchesView(view)) {
                            view.getHitRect(rect);
                            if (rect.contains((int) x2, (int) y2)) {
                                viewTransition2.applyTransition(this, this.mMotionLayout, currentState, constraintSet, view);
                            }
                        }
                    }
                }
            }
        }
    }

    public void viewTransition(int i2, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        ViewTransition viewTransition = null;
        for (ViewTransition viewTransition2 : this.mViewTransitions) {
            if (viewTransition2.getId() == i2) {
                for (View view : viewArr) {
                    if (viewTransition2.checkTags(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    viewTransition(viewTransition2, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                viewTransition = viewTransition2;
            }
        }
        if (viewTransition == null) {
            Log.e(this.mTAG, " Could not find ViewTransition");
        }
    }
}
