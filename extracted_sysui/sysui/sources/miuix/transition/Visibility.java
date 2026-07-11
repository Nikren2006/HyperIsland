package miuix.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.animation.listener.TransitionListener;

/* JADX INFO: loaded from: classes5.dex */
public class Visibility extends MiuixTransition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final int OVERLAY_VIEW_TAG = 1;
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    private boolean mForceUseOverlay;
    private int mMode;
    private static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String[] sTransitionProperties = {PROPNAME_VISIBILITY, PROPNAME_PARENT};

    public static class VisibilityInfo {
        ViewGroup endParent;
        int endVisibility;
        boolean fadeIn;
        ViewGroup startParent;
        int startVisibility;
        boolean visibilityChange;

        private VisibilityInfo() {
        }
    }

    public Visibility() {
        this.mMode = 3;
        this.mForceUseOverlay = false;
    }

    private static VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo();
        visibilityInfo.visibilityChange = false;
        visibilityInfo.fadeIn = false;
        if (transitionValues == null || !transitionValues.values.containsKey(PROPNAME_VISIBILITY)) {
            visibilityInfo.startVisibility = -1;
            visibilityInfo.startParent = null;
        } else {
            visibilityInfo.startVisibility = ((Integer) transitionValues.values.get(PROPNAME_VISIBILITY)).intValue();
            visibilityInfo.startParent = (ViewGroup) transitionValues.values.get(PROPNAME_PARENT);
        }
        if (transitionValues2 == null || !transitionValues2.values.containsKey(PROPNAME_VISIBILITY)) {
            visibilityInfo.endVisibility = -1;
            visibilityInfo.endParent = null;
        } else {
            visibilityInfo.endVisibility = ((Integer) transitionValues2.values.get(PROPNAME_VISIBILITY)).intValue();
            visibilityInfo.endParent = (ViewGroup) transitionValues2.values.get(PROPNAME_PARENT);
        }
        if (transitionValues != null && transitionValues2 != null) {
            int i2 = visibilityInfo.startVisibility;
            int i3 = visibilityInfo.endVisibility;
            if (i2 == i3 && visibilityInfo.startParent == visibilityInfo.endParent) {
                return visibilityInfo;
            }
            if (i2 == i3) {
                ViewGroup viewGroup = visibilityInfo.startParent;
                ViewGroup viewGroup2 = visibilityInfo.endParent;
                if (viewGroup != viewGroup2) {
                    if (viewGroup2 == null) {
                        visibilityInfo.fadeIn = false;
                        visibilityInfo.visibilityChange = true;
                    } else if (viewGroup == null) {
                        visibilityInfo.fadeIn = true;
                        visibilityInfo.visibilityChange = true;
                    }
                }
            } else if (i2 == 0) {
                visibilityInfo.fadeIn = false;
                visibilityInfo.visibilityChange = true;
            } else if (i3 == 0) {
                visibilityInfo.fadeIn = true;
                visibilityInfo.visibilityChange = true;
            }
        } else if (transitionValues == null && visibilityInfo.endVisibility == 0) {
            visibilityInfo.fadeIn = true;
            visibilityInfo.visibilityChange = true;
        } else if (transitionValues2 == null && visibilityInfo.startVisibility == 0) {
            visibilityInfo.fadeIn = false;
            visibilityInfo.visibilityChange = true;
        }
        return visibilityInfo;
    }

    @Override // miuix.transition.MiuixTransition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // miuix.transition.MiuixTransition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        transitionValues.values.put(PROPNAME_VISIBILITY, Integer.valueOf(view.getVisibility()));
        transitionValues.values.put(PROPNAME_PARENT, view.getParent());
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put(PROPNAME_SCREEN_LOCATION, iArr);
    }

    @Override // miuix.transition.MiuixTransition
    public void createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (visibilityChangeInfo.visibilityChange) {
            if (visibilityChangeInfo.startParent == null && visibilityChangeInfo.endParent == null) {
                return;
            }
            if (visibilityChangeInfo.fadeIn) {
                onAppear(viewGroup, transitionValues, visibilityChangeInfo.startVisibility, transitionValues2, visibilityChangeInfo.endVisibility);
            } else {
                onDisappear(viewGroup, transitionValues, visibilityChangeInfo.startVisibility, transitionValues2, visibilityChangeInfo.endVisibility);
            }
        }
    }

    public int getMode() {
        return this.mMode;
    }

    @Override // miuix.transition.MiuixTransition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // miuix.transition.MiuixTransition
    public boolean isTransitionRequired(@Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.values.containsKey(PROPNAME_VISIBILITY) != transitionValues.values.containsKey(PROPNAME_VISIBILITY)) {
            return false;
        }
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (visibilityChangeInfo.visibilityChange) {
            return visibilityChangeInfo.startVisibility == 0 || visibilityChangeInfo.endVisibility == 0;
        }
        return false;
    }

    public boolean isVisible(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        return ((Integer) transitionValues.values.get(PROPNAME_VISIBILITY)).intValue() == 0 && ((View) transitionValues.values.get(PROPNAME_PARENT)) != null;
    }

    public void onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
    }

    public void onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2, TransitionListener transitionListener) {
    }

    public Visibility setForceUseOverlay(boolean z2) {
        this.mForceUseOverlay = z2;
        return this;
    }

    public void setMode(int i2) {
        if ((i2 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.mMode = i2;
    }

    public void onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i2, TransitionValues transitionValues2, int i3) {
        if ((this.mMode & 1) != 1 || transitionValues2 == null) {
            return;
        }
        if (transitionValues == null) {
            View view = (View) transitionValues2.view.getParent();
            if (getVisibilityChangeInfo(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).visibilityChange) {
                return;
            }
        }
        onAppear(viewGroup, transitionValues2.view, transitionValues, transitionValues2);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDisappear(android.view.ViewGroup r15, miuix.transition.TransitionValues r16, int r17, miuix.transition.TransitionValues r18, int r19) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.transition.Visibility.onDisappear(android.view.ViewGroup, miuix.transition.TransitionValues, int, miuix.transition.TransitionValues, int):void");
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 3;
        this.mForceUseOverlay = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VisibilityTransition);
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.VisibilityTransition_transitionVisibilityMode, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (i2 != 0) {
            setMode(i2);
        }
    }

    public Visibility(int i2) {
        this.mMode = 3;
        this.mForceUseOverlay = false;
        setMode(i2);
    }
}
