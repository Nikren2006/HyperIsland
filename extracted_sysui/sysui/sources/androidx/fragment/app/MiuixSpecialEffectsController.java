package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miuix.reflect.Reflects;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public class MiuixSpecialEffectsController extends SpecialEffectsController {

    /* JADX INFO: renamed from: androidx.fragment.app.MiuixSpecialEffectsController$9, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static class AnimationInfo extends SpecialEffectsInfo {

        @Nullable
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim;

        public AnimationInfo(@NonNull SpecialEffectsController.Operation operation, @NonNull CancellationSignal cancellationSignal, boolean z2) {
            super(operation, cancellationSignal);
            this.mLoadedAnim = false;
            this.mIsPop = z2;
        }

        @Nullable
        public FragmentAnim.AnimationOrAnimator getAnimation(@NonNull Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = animationOrAnimatorLoadAnimation;
            this.mLoadedAnim = true;
            return animationOrAnimatorLoadAnimation;
        }
    }

    public static class SpecialEffectsInfo {

        @NonNull
        private final SpecialEffectsController.Operation mOperation;

        @NonNull
        private final CancellationSignal mSignal;

        public SpecialEffectsInfo(@NonNull SpecialEffectsController.Operation operation, @NonNull CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }

        @NonNull
        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        @NonNull
        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State state;
            SpecialEffectsController.Operation.State stateFrom = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return stateFrom == finalState || !(stateFrom == (state = SpecialEffectsController.Operation.State.VISIBLE) || finalState == state);
        }
    }

    public MiuixSpecialEffectsController(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
    }

    public static SpecialEffectsControllerFactory createMiuixSpecialEffectsController() {
        return new SpecialEffectsControllerFactory() { // from class: androidx.fragment.app.a
            @Override // androidx.fragment.app.SpecialEffectsControllerFactory
            public final SpecialEffectsController createController(ViewGroup viewGroup) {
                return MiuixSpecialEffectsController.lambda$createMiuixSpecialEffectsController$0(viewGroup);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SpecialEffectsController lambda$createMiuixSpecialEffectsController$0(ViewGroup viewGroup) {
        return new MiuixSpecialEffectsController(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeOperations$1(List list, SpecialEffectsController.Operation operation) {
        if (list.contains(operation)) {
            list.remove(operation);
            applyContainerChanges(operation);
        }
    }

    public static void setEnabled(FragmentManager fragmentManager, boolean z2) {
        Field declaredField = Reflects.getDeclaredField((Class<?>) FragmentManager.class, "mSpecialEffectsControllerFactory");
        declaredField.setAccessible(true);
        if (!z2) {
            Reflects.set(fragmentManager, declaredField, null);
        } else {
            if (Reflects.get(fragmentManager, declaredField) instanceof MiuixSpecialEffectsController) {
                return;
            }
            Reflects.set(fragmentManager, declaredField, createMiuixSpecialEffectsController());
        }
    }

    private void startAnimations(@NonNull List<AnimationInfo> list, @NonNull List<SpecialEffectsController.Operation> list2, boolean z2, @NonNull Map<SpecialEffectsController.Operation, Boolean> map) {
        int i2;
        boolean z3;
        Context context;
        View view;
        int i3;
        final SpecialEffectsController.Operation operation;
        final ViewGroup container = getContainer();
        Context context2 = container.getContext();
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        Iterator<AnimationInfo> it = list.iterator();
        boolean z4 = false;
        while (true) {
            i2 = 2;
            if (!it.hasNext()) {
                break;
            }
            final AnimationInfo next = it.next();
            if (next.isVisibilityUnchanged()) {
                next.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context2);
                if (animation == null) {
                    next.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(next);
                    } else {
                        final SpecialEffectsController.Operation operation2 = next.getOperation();
                        Fragment fragment = operation2.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation2))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            next.completeSpecialEffect();
                        } else {
                            boolean z5 = operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z5) {
                                list2.remove(operation2);
                            }
                            final View view2 = fragment.mView;
                            container.startViewTransition(view2);
                            final boolean z6 = z5;
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    container.endViewTransition(view2);
                                    if (z6) {
                                        operation2.getFinalState().applyState(view2);
                                    }
                                    next.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation2 + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view2);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Animator from operation ");
                                operation = operation2;
                                sb.append(operation);
                                sb.append(" has started.");
                                Log.v("FragmentManager", sb.toString());
                            } else {
                                operation = operation2;
                            }
                            next.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.2
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation + " has been canceled.");
                                    }
                                }
                            });
                            z4 = true;
                        }
                    }
                }
            }
        }
        for (final AnimationInfo animationInfo : arrayList) {
            final SpecialEffectsController.Operation operation3 = animationInfo.getOperation();
            Fragment fragment2 = operation3.getFragment();
            if (z2) {
                if (FragmentManager.isLoggingEnabled(i2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo.completeSpecialEffect();
            } else if (z4) {
                if (FragmentManager.isLoggingEnabled(i2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo.completeSpecialEffect();
            } else {
                final View view3 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo.getAnimation(context2))).animation);
                if (operation3.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view3.startAnimation(animation2);
                    animationInfo.completeSpecialEffect();
                    z3 = z4;
                    context = context2;
                    i3 = i2;
                    view = view3;
                } else {
                    container.startViewTransition(view3);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view3);
                    z3 = z4;
                    context = context2;
                    view = view3;
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.3
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation3) {
                            container.post(new Runnable() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                    container.endViewTransition(view3);
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation3 + " has ended.");
                            }
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation3) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation3 + " has reached onAnimationStart.");
                            }
                        }
                    });
                    view.startAnimation(endViewTransitionAnimation);
                    i3 = 2;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + operation3 + " has started.");
                    }
                }
                final View view4 = view;
                animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.4
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view4.clearAnimation();
                        container.endViewTransition(view4);
                        animationInfo.completeSpecialEffect();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Animation from operation " + operation3 + " has been cancelled.");
                        }
                    }
                });
                i2 = i3;
                z4 = z3;
                context2 = context;
            }
        }
    }

    @NonNull
    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(@NonNull List<TransitionInfo> list, @NonNull List<SpecialEffectsController.Operation> list2, final boolean z2, @Nullable final SpecialEffectsController.Operation operation, @Nullable final SpecialEffectsController.Operation operation2) {
        String str;
        String str2;
        String str3;
        View view;
        Object objMergeTransitionsTogether;
        ArrayList<View> arrayList;
        Object objMergeTransitionsTogether2;
        ArrayList<View> arrayList2;
        SpecialEffectsController.Operation operation3;
        SpecialEffectsController.Operation operation4;
        View view2;
        ArrayMap arrayMap;
        SpecialEffectsController.Operation operation5;
        HashMap map;
        ArrayList<View> arrayList3;
        View view3;
        FragmentTransitionImpl fragmentTransitionImpl;
        ArrayList<View> arrayList4;
        SpecialEffectsController.Operation operation6;
        final Rect rect;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList5;
        int i2;
        final View view4;
        String strFindKeyForValue;
        ArrayList<String> arrayList6;
        boolean z3 = z2;
        SpecialEffectsController.Operation operation7 = operation;
        SpecialEffectsController.Operation operation8 = operation2;
        HashMap map2 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = handlingImpl;
                } else if (handlingImpl != null && fragmentTransitionImpl2 != handlingImpl) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            for (TransitionInfo transitionInfo2 : list) {
                map2.put(transitionInfo2.getOperation(), Boolean.FALSE);
                transitionInfo2.completeSpecialEffect();
            }
            return map2;
        }
        View view5 = new View(getContainer().getContext());
        Rect rect2 = new Rect();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayList<View> arrayList8 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator<TransitionInfo> it = list.iterator();
        Object obj = null;
        View view6 = null;
        boolean z4 = false;
        while (true) {
            str = "FragmentManager";
            if (!it.hasNext()) {
                break;
            }
            TransitionInfo next = it.next();
            if (!next.hasSharedElementTransition() || operation7 == null || operation8 == null) {
                arrayMap = arrayMap2;
                operation5 = operation7;
                map = map2;
                arrayList3 = arrayList7;
                view3 = view5;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                arrayList4 = arrayList8;
                operation6 = operation8;
                rect = rect2;
                view6 = view6;
            } else {
                Object objWrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(next.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                View view7 = view6;
                HashMap map3 = map2;
                int i3 = 0;
                while (i3 < sharedElementTargetNames.size()) {
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i3));
                    ArrayList<String> arrayList9 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i3));
                    }
                    i3++;
                    sharedElementTargetNames = arrayList9;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (z3) {
                    enterTransitionCallback = operation.getFragment().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getExitTransitionCallback();
                } else {
                    enterTransitionCallback = operation.getFragment().getExitTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getEnterTransitionCallback();
                }
                int size = sharedElementSourceNames.size();
                View view8 = view5;
                int i4 = 0;
                while (i4 < size) {
                    arrayMap2.put(sharedElementSourceNames.get(i4), sharedElementTargetNames2.get(i4));
                    i4++;
                    size = size;
                    rect2 = rect2;
                }
                Rect rect3 = rect2;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    for (Iterator<String> it2 = sharedElementTargetNames2.iterator(); it2.hasNext(); it2 = it2) {
                        Log.v("FragmentManager", "Name: " + it2.next());
                    }
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    for (Iterator<String> it3 = sharedElementSourceNames.iterator(); it3.hasNext(); it3 = it3) {
                        Log.v("FragmentManager", "Name: " + it3.next());
                    }
                }
                ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                findNamedViews(arrayMap3, operation.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing exit callback for operation " + operation7);
                    }
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str4 = sharedElementSourceNames.get(size2);
                        View view9 = arrayMap3.get(str4);
                        if (view9 == null) {
                            arrayMap2.remove(str4);
                            arrayList6 = sharedElementSourceNames;
                        } else {
                            arrayList6 = sharedElementSourceNames;
                            if (!str4.equals(ViewCompat.getTransitionName(view9))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view9), (String) arrayMap2.remove(str4));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList6;
                    }
                    arrayList5 = sharedElementSourceNames;
                } else {
                    arrayList5 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                findNamedViews(arrayMap4, operation2.getFragment().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (exitTransitionCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing enter callback for operation " + operation8);
                    }
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str5 = sharedElementTargetNames2.get(size3);
                        View view10 = arrayMap4.get(str5);
                        if (view10 == null) {
                            String strFindKeyForValue2 = MiuixFragmentTransition.findKeyForValue(arrayMap2, str5);
                            if (strFindKeyForValue2 != null) {
                                arrayMap2.remove(strFindKeyForValue2);
                            }
                        } else if (!str5.equals(ViewCompat.getTransitionName(view10)) && (strFindKeyForValue = MiuixFragmentTransition.findKeyForValue(arrayMap2, str5)) != null) {
                            arrayMap2.put(strFindKeyForValue, ViewCompat.getTransitionName(view10));
                        }
                    }
                } else {
                    MiuixFragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList7.clear();
                    arrayList8.clear();
                    arrayMap = arrayMap2;
                    arrayList4 = arrayList8;
                    operation5 = operation7;
                    arrayList3 = arrayList7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view6 = view7;
                    view3 = view8;
                    map = map3;
                    rect = rect3;
                    obj = null;
                    operation6 = operation8;
                } else {
                    MiuixFragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z3, arrayMap3, true);
                    arrayMap = arrayMap2;
                    ArrayList<View> arrayList10 = arrayList8;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.5
                        @Override // java.lang.Runnable
                        public void run() {
                            MiuixFragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z2, arrayMap4, false);
                        }
                    });
                    arrayList7.addAll(arrayMap3.values());
                    if (arrayList5.isEmpty()) {
                        i2 = 0;
                        view6 = view7;
                    } else {
                        i2 = 0;
                        view6 = arrayMap3.get(arrayList5.get(0));
                        fragmentTransitionImpl2.setEpicenter(objWrapTransitionInSet, view6);
                    }
                    arrayList10.addAll(arrayMap4.values());
                    if (sharedElementTargetNames2.isEmpty() || (view4 = arrayMap4.get(sharedElementTargetNames2.get(i2))) == null) {
                        rect = rect3;
                        view3 = view8;
                    } else {
                        rect = rect3;
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.6
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl2.getBoundsOnScreen(view4, rect);
                            }
                        });
                        view3 = view8;
                        z4 = true;
                    }
                    fragmentTransitionImpl2.setSharedElementTargets(objWrapTransitionInSet, view3, arrayList7);
                    arrayList3 = arrayList7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(objWrapTransitionInSet, null, null, null, null, objWrapTransitionInSet, arrayList10);
                    Boolean bool = Boolean.TRUE;
                    operation5 = operation;
                    arrayList4 = arrayList10;
                    map = map3;
                    map.put(operation5, bool);
                    operation6 = operation2;
                    map.put(operation6, bool);
                    obj = objWrapTransitionInSet;
                }
            }
            view5 = view3;
            rect2 = rect;
            arrayList7 = arrayList3;
            arrayList8 = arrayList4;
            operation8 = operation6;
            z3 = z2;
            map2 = map;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            operation7 = operation5;
            arrayMap2 = arrayMap;
        }
        View view11 = view6;
        ArrayMap arrayMap5 = arrayMap2;
        SpecialEffectsController.Operation operation9 = operation7;
        HashMap map4 = map2;
        ArrayList<View> arrayList11 = arrayList7;
        View view12 = view5;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        ArrayList<View> arrayList12 = arrayList8;
        SpecialEffectsController.Operation operation10 = operation8;
        Rect rect4 = rect2;
        ArrayList arrayList13 = new ArrayList();
        Iterator<TransitionInfo> it4 = list.iterator();
        Object obj2 = null;
        Object obj3 = null;
        while (it4.hasNext()) {
            TransitionInfo next2 = it4.next();
            if (next2.isVisibilityUnchanged()) {
                map4.put(next2.getOperation(), Boolean.FALSE);
                next2.completeSpecialEffect();
                it4 = it4;
            } else {
                Iterator<TransitionInfo> it5 = it4;
                Object objCloneTransition = fragmentTransitionImpl3.cloneTransition(next2.getTransition());
                SpecialEffectsController.Operation operation11 = next2.getOperation();
                boolean z5 = obj != null && (operation11 == operation9 || operation11 == operation10);
                if (objCloneTransition == null) {
                    if (!z5) {
                        map4.put(operation11, Boolean.FALSE);
                        next2.completeSpecialEffect();
                    }
                    view = view12;
                    str3 = str;
                    arrayList = arrayList11;
                    arrayList2 = arrayList12;
                    objMergeTransitionsTogether = obj2;
                    objMergeTransitionsTogether2 = obj3;
                    operation3 = operation10;
                    view2 = view11;
                } else {
                    str3 = str;
                    final ArrayList<View> arrayList14 = new ArrayList<>();
                    Object obj4 = obj2;
                    captureTransitioningViews(arrayList14, operation11.getFragment().mView);
                    if (z5) {
                        if (operation11 == operation9) {
                            arrayList14.removeAll(arrayList11);
                        } else {
                            arrayList14.removeAll(arrayList12);
                        }
                    }
                    if (arrayList14.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(objCloneTransition, view12);
                        view = view12;
                        arrayList = arrayList11;
                        arrayList2 = arrayList12;
                        objMergeTransitionsTogether2 = obj3;
                        operation4 = operation11;
                        operation3 = operation10;
                        objMergeTransitionsTogether = obj4;
                    } else {
                        fragmentTransitionImpl3.addTargets(objCloneTransition, arrayList14);
                        view = view12;
                        objMergeTransitionsTogether = obj4;
                        arrayList = arrayList11;
                        objMergeTransitionsTogether2 = obj3;
                        arrayList2 = arrayList12;
                        operation3 = operation10;
                        fragmentTransitionImpl3.scheduleRemoveTargets(objCloneTransition, objCloneTransition, arrayList14, null, null, null, null);
                        if (operation11.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation4 = operation11;
                            list2.remove(operation4);
                            ArrayList<View> arrayList15 = new ArrayList<>(arrayList14);
                            arrayList15.remove(operation4.getFragment().mView);
                            fragmentTransitionImpl3.scheduleHideFragmentView(objCloneTransition, operation4.getFragment().mView, arrayList15);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.7
                                @Override // java.lang.Runnable
                                public void run() {
                                    MiuixFragmentTransition.setViewVisibility(arrayList14, 4);
                                }
                            });
                        } else {
                            operation4 = operation11;
                        }
                    }
                    if (operation4.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList13.addAll(arrayList14);
                        if (z4) {
                            fragmentTransitionImpl3.setEpicenter(objCloneTransition, rect4);
                        }
                        view2 = view11;
                    } else {
                        view2 = view11;
                        fragmentTransitionImpl3.setEpicenter(objCloneTransition, view2);
                    }
                    map4.put(operation4, Boolean.TRUE);
                    if (next2.isOverlapAllowed()) {
                        objMergeTransitionsTogether2 = fragmentTransitionImpl3.mergeTransitionsTogether(objMergeTransitionsTogether2, objCloneTransition, null);
                    } else {
                        objMergeTransitionsTogether = fragmentTransitionImpl3.mergeTransitionsTogether(objMergeTransitionsTogether, objCloneTransition, null);
                    }
                }
                it4 = it5;
                obj2 = objMergeTransitionsTogether;
                view11 = view2;
                obj3 = objMergeTransitionsTogether2;
                operation10 = operation3;
                str = str3;
                view12 = view;
                arrayList11 = arrayList;
                arrayList12 = arrayList2;
            }
        }
        String str6 = str;
        ArrayList<View> arrayList16 = arrayList11;
        ArrayList<View> arrayList17 = arrayList12;
        SpecialEffectsController.Operation operation12 = operation10;
        Object objMergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj3, obj2, obj);
        if (objMergeTransitionsInSequence == null) {
            return map4;
        }
        for (final TransitionInfo transitionInfo3 : list) {
            if (!transitionInfo3.isVisibilityUnchanged()) {
                Object transition = transitionInfo3.getTransition();
                final SpecialEffectsController.Operation operation13 = transitionInfo3.getOperation();
                boolean z6 = obj != null && (operation13 == operation9 || operation13 == operation12);
                if (transition == null && !z6) {
                    str2 = str6;
                } else if (ViewCompat.isLaidOut(getContainer())) {
                    str2 = str6;
                    fragmentTransitionImpl3.setListenerForTransitionEnd(transitionInfo3.getOperation().getFragment(), objMergeTransitionsInSequence, transitionInfo3.getSignal(), new Runnable() { // from class: androidx.fragment.app.MiuixSpecialEffectsController.8
                        @Override // java.lang.Runnable
                        public void run() {
                            transitionInfo3.completeSpecialEffect();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Transition for operation " + operation13 + "has completed");
                            }
                        }
                    });
                } else {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        str2 = str6;
                        Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation13);
                    } else {
                        str2 = str6;
                    }
                    transitionInfo3.completeSpecialEffect();
                }
                str6 = str2;
            }
        }
        String str7 = str6;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return map4;
        }
        MiuixFragmentTransition.setViewVisibility(arrayList13, 4);
        ArrayList<String> arrayListPrepareSetNameOverridesReordered = fragmentTransitionImpl3.prepareSetNameOverridesReordered(arrayList17);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str7, ">>>>> Beginning transition <<<<<");
            Log.v(str7, ">>>>> SharedElementFirstOutViews <<<<<");
            for (View view13 : arrayList16) {
                Log.v(str7, "View: " + view13 + " Name: " + ViewCompat.getTransitionName(view13));
            }
            Log.v(str7, ">>>>> SharedElementLastInViews <<<<<");
            for (View view14 : arrayList17) {
                Log.v(str7, "View: " + view14 + " Name: " + ViewCompat.getTransitionName(view14));
            }
        }
        fragmentTransitionImpl3.beginDelayedTransition(getContainer(), objMergeTransitionsInSequence);
        fragmentTransitionImpl3.setNameOverridesReordered(getContainer(), arrayList16, arrayList17, arrayListPrepareSetNameOverridesReordered, arrayMap5);
        MiuixFragmentTransition.setViewVisibility(arrayList13, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj, arrayList16, arrayList17);
        return map4;
    }

    public void applyContainerChanges(@NonNull SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                captureTransitioningViews(arrayList, childAt);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a5  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeOperations(@androidx.annotation.NonNull java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.MiuixSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    public void findNamedViews(Map<String, View> map, @NonNull View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    @NonNull
    public /* bridge */ /* synthetic */ ViewGroup getContainer() {
        return super.getContainer();
    }

    public void retainMatchingViews(@NonNull ArrayMap<String, View> arrayMap, @NonNull Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    public static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;

        @Nullable
        private final Object mSharedElementTransition;

        @Nullable
        private final Object mTransition;

        public TransitionInfo(@NonNull SpecialEffectsController.Operation operation, @NonNull CancellationSignal cancellationSignal, boolean z2, boolean z3) {
            super(operation, cancellationSignal);
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                this.mTransition = z2 ? operation.getFragment().getReenterTransition() : operation.getFragment().getEnterTransition();
                this.mOverlapAllowed = z2 ? operation.getFragment().getAllowReturnTransitionOverlap() : operation.getFragment().getAllowEnterTransitionOverlap();
            } else {
                this.mTransition = z2 ? operation.getFragment().getReturnTransition() : operation.getFragment().getExitTransition();
                this.mOverlapAllowed = true;
            }
            if (!z3) {
                this.mSharedElementTransition = null;
            } else if (z2) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        @Nullable
        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        @Nullable
        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        @Nullable
        public Object getTransition() {
            return this.mTransition;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        @Nullable
        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = MiuixFragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = MiuixFragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            FragmentTransitionImpl fragmentTransitionImpl3 = MiuixFragmentTransition.MIUIX_IMPL;
            if (fragmentTransitionImpl3 != null && fragmentTransitionImpl3.canHandle(obj)) {
                return fragmentTransitionImpl3;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
