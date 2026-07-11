package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.collection.ArraySet;
import androidx.core.os.CancellationSignal;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransition;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentManager implements FragmentResultOwner {
    private static boolean DEBUG = false;
    private static final String EXTRA_CREATED_FILLIN_INTENT = "androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE";
    public static final int POP_BACK_STACK_INCLUSIVE = 1;
    static final String TAG = "FragmentManager";
    static boolean USE_STATE_MANAGER = true;
    ArrayList<BackStackRecord> mBackStack;
    private ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    private FragmentContainer mContainer;
    private ArrayList<Fragment> mCreatedMenus;
    private boolean mDestroyed;
    private boolean mExecutingActions;
    private boolean mHavePendingDeferredStart;
    private FragmentHostCallback<?> mHost;
    private boolean mNeedMenuInvalidate;
    private FragmentManagerViewModel mNonConfig;
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private Fragment mParent;
    private ArrayList<StartEnterTransitionListener> mPostponedTransactions;

    @Nullable
    Fragment mPrimaryNav;
    private ActivityResultLauncher<String[]> mRequestPermissions;
    private ActivityResultLauncher<Intent> mStartActivityForResult;
    private ActivityResultLauncher<IntentSenderRequest> mStartIntentSenderForResult;
    private boolean mStateSaved;
    private boolean mStopped;
    private ArrayList<Fragment> mTmpAddedFragments;
    private ArrayList<Boolean> mTmpIsPop;
    private ArrayList<BackStackRecord> mTmpRecords;
    private final ArrayList<OpGenerator> mPendingActions = new ArrayList<>();
    private final FragmentStore mFragmentStore = new FragmentStore();
    private final FragmentLayoutInflaterFactory mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) { // from class: androidx.fragment.app.FragmentManager.1
        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FragmentManager.this.handleOnBackPressed();
        }
    };
    private final AtomicInteger mBackStackIndex = new AtomicInteger();
    private final Map<String, Bundle> mResults = Collections.synchronizedMap(new HashMap());
    private final Map<String, LifecycleAwareResultListener> mResultListeners = Collections.synchronizedMap(new HashMap());
    private Map<Fragment, HashSet<CancellationSignal>> mExitAnimationCancellationSignals = Collections.synchronizedMap(new HashMap());
    private final FragmentTransition.Callback mFragmentTransitionCallback = new FragmentTransition.Callback() { // from class: androidx.fragment.app.FragmentManager.2
        @Override // androidx.fragment.app.FragmentTransition.Callback
        public void onComplete(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            if (cancellationSignal.isCanceled()) {
                return;
            }
            FragmentManager.this.removeCancellationSignal(fragment, cancellationSignal);
        }

        @Override // androidx.fragment.app.FragmentTransition.Callback
        public void onStart(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            FragmentManager.this.addCancellationSignal(fragment, cancellationSignal);
        }
    };
    private final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
    private final CopyOnWriteArrayList<FragmentOnAttachListener> mOnAttachListeners = new CopyOnWriteArrayList<>();
    int mCurState = -1;
    private FragmentFactory mFragmentFactory = null;
    private FragmentFactory mHostFragmentFactory = new FragmentFactory() { // from class: androidx.fragment.app.FragmentManager.3
        @Override // androidx.fragment.app.FragmentFactory
        @NonNull
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String str) {
            return FragmentManager.this.getHost().instantiate(FragmentManager.this.getHost().getContext(), str, null);
        }
    };
    private SpecialEffectsControllerFactory mSpecialEffectsControllerFactory = null;
    private SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory = new SpecialEffectsControllerFactory() { // from class: androidx.fragment.app.FragmentManager.4
        @Override // androidx.fragment.app.SpecialEffectsControllerFactory
        @NonNull
        public SpecialEffectsController createController(@NonNull ViewGroup viewGroup) {
            return new DefaultSpecialEffectsController(viewGroup);
        }
    };
    ArrayDeque<LaunchedFragmentInfo> mLaunchedFragments = new ArrayDeque<>();
    private Runnable mExecCommit = new Runnable() { // from class: androidx.fragment.app.FragmentManager.5
        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.execPendingActions(true);
        }
    };

    public interface BackStackEntry {
        @Nullable
        @Deprecated
        CharSequence getBreadCrumbShortTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbShortTitleRes();

        @Nullable
        @Deprecated
        CharSequence getBreadCrumbTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbTitleRes();

        int getId();

        @Nullable
        String getName();
    }

    public static class FragmentIntentSenderContract extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public Intent createIntent(@NonNull Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST);
            Intent fillInIntent = intentSenderRequest.getFillInIntent();
            if (fillInIntent != null && (bundleExtra = fillInIntent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) != null) {
                intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundleExtra);
                fillInIntent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
                if (fillInIntent.getBooleanExtra(FragmentManager.EXTRA_CREATED_FILLIN_INTENT, false)) {
                    intentSenderRequest = new IntentSenderRequest.Builder(intentSenderRequest.getIntentSender()).setFillInIntent(null).setFlags(intentSenderRequest.getFlagsValues(), intentSenderRequest.getFlagsMask()).build();
                }
            }
            intent.putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST, intentSenderRequest);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public ActivityResult parseResult(int i2, @Nullable Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    public static abstract class FragmentLifecycleCallbacks {
        @Deprecated
        public void onFragmentActivityCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentPreCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle) {
        }

        public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
        }

        public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }
    }

    public static class LifecycleAwareResultListener implements FragmentResultListener {
        private final Lifecycle mLifecycle;
        private final FragmentResultListener mListener;
        private final LifecycleEventObserver mObserver;

        public LifecycleAwareResultListener(@NonNull Lifecycle lifecycle, @NonNull FragmentResultListener fragmentResultListener, @NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle = lifecycle;
            this.mListener = fragmentResultListener;
            this.mObserver = lifecycleEventObserver;
        }

        public boolean isAtLeast(Lifecycle.State state) {
            return this.mLifecycle.getCurrentState().isAtLeast(state);
        }

        @Override // androidx.fragment.app.FragmentResultListener
        public void onFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
            this.mListener.onFragmentResult(str, bundle);
        }

        public void removeObserver() {
            this.mLifecycle.removeObserver(this.mObserver);
        }
    }

    public interface OnBackStackChangedListener {
        @MainThread
        void onBackStackChanged();
    }

    public interface OpGenerator {
        boolean generateOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2);
    }

    public class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        public PopBackStackState(@Nullable String str, int i2, int i3) {
            this.mName = str;
            this.mId = i2;
            this.mFlags = i3;
        }

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean generateOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.mPrimaryNav;
            if (fragment == null || this.mId >= 0 || this.mName != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
                return FragmentManager.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
            }
            return false;
        }
    }

    public static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        final boolean mIsBack;
        private int mNumPostponed;
        final BackStackRecord mRecord;

        public StartEnterTransitionListener(@NonNull BackStackRecord backStackRecord, boolean z2) {
            this.mIsBack = z2;
            this.mRecord = backStackRecord;
        }

        public void cancelTransaction() {
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, false, false);
        }

        public void completeTransaction() {
            boolean z2 = this.mNumPostponed > 0;
            for (Fragment fragment : this.mRecord.mManager.getFragments()) {
                fragment.setOnStartEnterTransitionListener(null);
                if (z2 && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, !z2, true);
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        @Override // androidx.fragment.app.Fragment.OnStartEnterTransitionListener
        public void onStartEnterTransition() {
            int i2 = this.mNumPostponed - 1;
            this.mNumPostponed = i2;
            if (i2 != 0) {
                return;
            }
            this.mRecord.mManager.scheduleCommit();
        }

        @Override // androidx.fragment.app.Fragment.OnStartEnterTransitionListener
        public void startListening() {
            this.mNumPostponed++;
        }
    }

    private void addAddedFragments(@NonNull ArraySet<Fragment> arraySet) {
        int i2 = this.mCurState;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 5);
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment.mState < iMin) {
                moveToState(fragment, iMin);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    arraySet.add(fragment);
                }
            }
        }
    }

    private void cancelExitAnimation(@NonNull Fragment fragment) {
        HashSet<CancellationSignal> hashSet = this.mExitAnimationCancellationSignals.get(fragment);
        if (hashSet != null) {
            Iterator<CancellationSignal> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            hashSet.clear();
            destroyFragmentView(fragment);
            this.mExitAnimationCancellationSignals.remove(fragment);
        }
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private Set<SpecialEffectsController> collectAllSpecialEffectsController() {
        HashSet hashSet = new HashSet();
        Iterator<FragmentStateManager> it = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = it.next().getFragment().mContainer;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, getSpecialEffectsControllerFactory()));
            }
        }
        return hashSet;
    }

    private Set<SpecialEffectsController> collectChangedControllers(@NonNull ArrayList<BackStackRecord> arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator<FragmentTransaction.Op> it = arrayList.get(i2).mOps.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().mFragment;
                if (fragment != null && (viewGroup = fragment.mContainer) != null) {
                    hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    private void completeShowHideFragment(@NonNull final Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(this.mHost.getContext(), fragment, !fragment.mHidden, fragment.getPopDirection());
            if (animationOrAnimatorLoadAnimation == null || (animator = animationOrAnimatorLoadAnimation.animator) == null) {
                if (animationOrAnimatorLoadAnimation != null) {
                    fragment.mView.startAnimation(animationOrAnimatorLoadAnimation.animation);
                    animationOrAnimatorLoadAnimation.animation.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    final ViewGroup viewGroup = fragment.mContainer;
                    final View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    animationOrAnimatorLoadAnimation.animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.FragmentManager.7
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator2) {
                            viewGroup.endViewTransition(view);
                            animator2.removeListener(this);
                            Fragment fragment2 = fragment;
                            View view2 = fragment2.mView;
                            if (view2 == null || !fragment2.mHidden) {
                                return;
                            }
                            view2.setVisibility(8);
                        }
                    });
                }
                animationOrAnimatorLoadAnimation.animator.start();
            }
        }
        invalidateMenuForFragment(fragment);
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    private void destroyFragmentView(@NonNull Fragment fragment) {
        fragment.performDestroyView();
        this.mLifecycleCallbacksDispatcher.dispatchOnFragmentViewDestroyed(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.setValue(null);
        fragment.mInLayout = false;
    }

    private void dispatchParentPrimaryNavigationFragmentChanged(@Nullable Fragment fragment) {
        if (fragment == null || !fragment.equals(findActiveFragment(fragment.mWho))) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    private void dispatchStateChange(int i2) {
        try {
            this.mExecutingActions = true;
            this.mFragmentStore.dispatchStateChange(i2);
            moveToState(i2, false);
            if (USE_STATE_MANAGER) {
                Iterator<SpecialEffectsController> it = collectAllSpecialEffectsController().iterator();
                while (it.hasNext()) {
                    it.next().forceCompleteAllOperations();
                }
            }
            this.mExecutingActions = false;
            execPendingActions(true);
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    private void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    @Deprecated
    public static void enableDebugLogging(boolean z2) {
        DEBUG = z2;
    }

    @FragmentStateManagerControl
    public static void enableNewStateManager(boolean z2) {
        USE_STATE_MANAGER = z2;
    }

    private void endAnimatingAwayFragments() {
        if (USE_STATE_MANAGER) {
            Iterator<SpecialEffectsController> it = collectAllSpecialEffectsController().iterator();
            while (it.hasNext()) {
                it.next().forceCompleteAllOperations();
            }
        } else {
            if (this.mExitAnimationCancellationSignals.isEmpty()) {
                return;
            }
            for (Fragment fragment : this.mExitAnimationCancellationSignals.keySet()) {
                cancelExitAnimation(fragment);
                moveToState(fragment);
            }
        }
    }

    private void ensureExecReady(boolean z2) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.mHost == null) {
            if (!this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z2) {
            checkStateLoss();
        }
        if (this.mTmpRecords == null) {
            this.mTmpRecords = new ArrayList<>();
            this.mTmpIsPop = new ArrayList<>();
        }
        this.mExecutingActions = true;
        try {
            executePostponedTransaction(null, null);
        } finally {
            this.mExecutingActions = false;
        }
    }

    private static void executeOps(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            BackStackRecord backStackRecord = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                backStackRecord.bumpBackStackNesting(-1);
                backStackRecord.executePopOps(i2 == i3 + (-1));
            } else {
                backStackRecord.bumpBackStackNesting(1);
                backStackRecord.executeOps();
            }
            i2++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r17v0, types: [androidx.fragment.app.FragmentManager] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [boolean] */
    private void executeOpsTogether(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        ?? r12;
        ?? r14;
        boolean z2;
        int i4;
        int i5;
        ArrayList<Boolean> arrayList3;
        int iPostponePostponableTransactions;
        ArrayList<Boolean> arrayList4;
        int i6;
        ?? r13;
        boolean z3 = arrayList.get(i2).mReorderingAllowed;
        ArrayList<Fragment> arrayList5 = this.mTmpAddedFragments;
        if (arrayList5 == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z4 = false;
        for (int i7 = i2; i7 < i3; i7++) {
            BackStackRecord backStackRecord = arrayList.get(i7);
            primaryNavigationFragment = !arrayList2.get(i7).booleanValue() ? backStackRecord.expandOps(this.mTmpAddedFragments, primaryNavigationFragment) : backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, primaryNavigationFragment);
            z4 = z4 || backStackRecord.mAddToBackStack;
        }
        this.mTmpAddedFragments.clear();
        if (z3 || this.mCurState < 1) {
            r12 = 1;
        } else if (USE_STATE_MANAGER) {
            for (int i8 = i2; i8 < i3; i8++) {
                Iterator<FragmentTransaction.Op> it = arrayList.get(i8).mOps.iterator();
                while (it.hasNext()) {
                    Fragment fragment = it.next().mFragment;
                    if (fragment != null && fragment.mFragmentManager != null) {
                        this.mFragmentStore.makeActive(createOrGetFragmentStateManager(fragment));
                    }
                }
            }
            r12 = 1;
        } else {
            r12 = 1;
            FragmentTransition.startTransitions(this.mHost.getContext(), this.mContainer, arrayList, arrayList2, i2, i3, false, this.mFragmentTransitionCallback);
        }
        executeOps(arrayList, arrayList2, i2, i3);
        if (USE_STATE_MANAGER) {
            boolean zBooleanValue = arrayList2.get(i3 - 1).booleanValue();
            for (int i9 = i2; i9 < i3; i9++) {
                BackStackRecord backStackRecord2 = arrayList.get(i9);
                if (zBooleanValue) {
                    for (int size = backStackRecord2.mOps.size() - r12; size >= 0; size--) {
                        Fragment fragment2 = backStackRecord2.mOps.get(size).mFragment;
                        if (fragment2 != null) {
                            createOrGetFragmentStateManager(fragment2).moveToExpectedState();
                        }
                    }
                } else {
                    Iterator<FragmentTransaction.Op> it2 = backStackRecord2.mOps.iterator();
                    while (it2.hasNext()) {
                        Fragment fragment3 = it2.next().mFragment;
                        if (fragment3 != null) {
                            createOrGetFragmentStateManager(fragment3).moveToExpectedState();
                        }
                    }
                }
            }
            moveToState(this.mCurState, r12);
            for (SpecialEffectsController specialEffectsController : collectChangedControllers(arrayList, i2, i3)) {
                specialEffectsController.updateOperationDirection(zBooleanValue);
                specialEffectsController.markPostponedState();
                specialEffectsController.executePendingOperations();
            }
            i6 = i3;
            arrayList4 = arrayList2;
        } else {
            if (z3) {
                ArraySet arraySet = new ArraySet();
                addAddedFragments(arraySet);
                r14 = r12;
                z2 = z3;
                i4 = i3;
                i5 = i2;
                arrayList3 = arrayList2;
                iPostponePostponableTransactions = postponePostponableTransactions(arrayList, arrayList2, i2, i3, arraySet);
                makeRemovedFragmentsInvisible(arraySet);
            } else {
                r14 = r12;
                z2 = z3;
                i4 = i3;
                i5 = i2;
                arrayList3 = arrayList2;
                iPostponePostponableTransactions = i4;
            }
            if (iPostponePostponableTransactions == i5 || !z2) {
                arrayList4 = arrayList3;
                i6 = i4;
            } else {
                if (this.mCurState >= r14) {
                    arrayList4 = arrayList3;
                    int i10 = iPostponePostponableTransactions;
                    i6 = i4;
                    r13 = r14;
                    FragmentTransition.startTransitions(this.mHost.getContext(), this.mContainer, arrayList, arrayList2, i2, i10, true, this.mFragmentTransitionCallback);
                } else {
                    arrayList4 = arrayList3;
                    i6 = i4;
                    r13 = r14;
                }
                moveToState(this.mCurState, r13);
            }
        }
        for (int i11 = i2; i11 < i6; i11++) {
            BackStackRecord backStackRecord3 = arrayList.get(i11);
            if (arrayList4.get(i11).booleanValue() && backStackRecord3.mIndex >= 0) {
                backStackRecord3.mIndex = -1;
            }
            backStackRecord3.runOnCommitRunnables();
        }
        if (z4) {
            reportBackStackChanged();
        }
    }

    private void executePostponedTransaction(@Nullable ArrayList<BackStackRecord> arrayList, @Nullable ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<StartEnterTransitionListener> arrayList3 = this.mPostponedTransactions;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.mPostponedTransactions.get(i2);
            if (arrayList != null && !startEnterTransitionListener.mIsBack && (iIndexOf2 = arrayList.indexOf(startEnterTransitionListener.mRecord)) != -1 && arrayList2 != null && arrayList2.get(iIndexOf2).booleanValue()) {
                this.mPostponedTransactions.remove(i2);
                i2--;
                size--;
                startEnterTransitionListener.cancelTransaction();
            } else if (startEnterTransitionListener.isReady() || (arrayList != null && startEnterTransitionListener.mRecord.interactsWith(arrayList, 0, arrayList.size()))) {
                this.mPostponedTransactions.remove(i2);
                i2--;
                size--;
                if (arrayList == null || startEnterTransitionListener.mIsBack || (iIndexOf = arrayList.indexOf(startEnterTransitionListener.mRecord)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf).booleanValue()) {
                    startEnterTransitionListener.completeTransaction();
                } else {
                    startEnterTransitionListener.cancelTransaction();
                }
            }
            i2++;
        }
    }

    @NonNull
    public static <F extends Fragment> F findFragment(@NonNull View view) {
        F f2 = (F) findViewFragment(view);
        if (f2 != null) {
            return f2;
        }
        throw new IllegalStateException("View " + view + " does not have a Fragment set");
    }

    @NonNull
    public static FragmentManager findFragmentManager(@NonNull View view) {
        FragmentActivity fragmentActivity;
        Fragment fragmentFindViewFragment = findViewFragment(view);
        if (fragmentFindViewFragment != null) {
            if (fragmentFindViewFragment.isAdded()) {
                return fragmentFindViewFragment.getChildFragmentManager();
            }
            throw new IllegalStateException("The Fragment " + fragmentFindViewFragment + " that owns View " + view + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
        }
        Context context = view.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                fragmentActivity = null;
                break;
            }
            if (context instanceof FragmentActivity) {
                fragmentActivity = (FragmentActivity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (fragmentActivity != null) {
            return fragmentActivity.getSupportFragmentManager();
        }
        throw new IllegalStateException("View " + view + " is not within a subclass of FragmentActivity.");
    }

    @Nullable
    private static Fragment findViewFragment(@NonNull View view) {
        while (view != null) {
            Fragment viewFragment = getViewFragment(view);
            if (viewFragment != null) {
                return viewFragment;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    private void forcePostponedTransactions() {
        if (USE_STATE_MANAGER) {
            Iterator<SpecialEffectsController> it = collectAllSpecialEffectsController().iterator();
            while (it.hasNext()) {
                it.next().forcePostponedExecutePendingOperations();
            }
        } else if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    private boolean generateOpsForPendingActions(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        synchronized (this.mPendingActions) {
            try {
                if (this.mPendingActions.isEmpty()) {
                    return false;
                }
                int size = this.mPendingActions.size();
                boolean zGenerateOps = false;
                for (int i2 = 0; i2 < size; i2++) {
                    zGenerateOps |= this.mPendingActions.get(i2).generateOps(arrayList, arrayList2);
                }
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                return zGenerateOps;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @NonNull
    private FragmentManagerViewModel getChildNonConfig(@NonNull Fragment fragment) {
        return this.mNonConfig.getChildNonConfig(fragment);
    }

    private ViewGroup getFragmentContainer(@NonNull Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.mContainer.onHasView()) {
            View viewOnFindViewById = this.mContainer.onFindViewById(fragment.mContainerId);
            if (viewOnFindViewById instanceof ViewGroup) {
                return (ViewGroup) viewOnFindViewById;
            }
        }
        return null;
    }

    @Nullable
    public static Fragment getViewFragment(@NonNull View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    public static boolean isLoggingEnabled(int i2) {
        return DEBUG || Log.isLoggable(TAG, i2);
    }

    private boolean isMenuAvailable(@NonNull Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.checkForMenus();
    }

    private void makeRemovedFragmentsInvisible(@NonNull ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentValueAt = arraySet.valueAt(i2);
            if (!fragmentValueAt.mAdded) {
                View viewRequireView = fragmentValueAt.requireView();
                fragmentValueAt.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    private int postponePostponableTransactions(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3, @NonNull ArraySet<Fragment> arraySet) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            BackStackRecord backStackRecord = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(arrayList, i5 + 1, i3)) {
                if (this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList<>();
                }
                StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, zBooleanValue);
                this.mPostponedTransactions.add(startEnterTransitionListener);
                backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
                if (zBooleanValue) {
                    backStackRecord.executeOps();
                } else {
                    backStackRecord.executePopOps(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, backStackRecord);
                }
                addAddedFragments(arraySet);
            }
        }
        return i4;
    }

    private void removeRedundantOperationsAndExecute(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        executePostponedTransaction(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).mReorderingAllowed) {
                if (i3 != i2) {
                    executeOpsTogether(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).mReorderingAllowed) {
                        i3++;
                    }
                }
                executeOpsTogether(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            executeOpsTogether(arrayList, arrayList2, i3, size);
        }
    }

    private void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i2 = 0; i2 < this.mBackStackChangeListeners.size(); i2++) {
                this.mBackStackChangeListeners.get(i2).onBackStackChanged();
            }
        }
    }

    public static int reverseTransit(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private void setVisibleRemovingFragment(@NonNull Fragment fragment) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer == null || fragment.getEnterAnim() + fragment.getExitAnim() + fragment.getPopEnterAnim() + fragment.getPopExitAnim() <= 0) {
            return;
        }
        int i2 = R.id.visible_removing_fragment_view_tag;
        if (fragmentContainer.getTag(i2) == null) {
            fragmentContainer.setTag(i2, fragment);
        }
        ((Fragment) fragmentContainer.getTag(i2)).setPopDirection(fragment.getPopDirection());
    }

    private void startPendingDeferredFragments() {
        Iterator<FragmentStateManager> it = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
        while (it.hasNext()) {
            performPendingDeferredStart(it.next());
        }
    }

    private void throwException(RuntimeException runtimeException) {
        Log.e(TAG, runtimeException.getMessage());
        Log.e(TAG, "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter(TAG));
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            try {
                fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e(TAG, "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            dump("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e(TAG, "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    private void updateOnBackPressedCallbackEnabled() {
        synchronized (this.mPendingActions) {
            try {
                if (this.mPendingActions.isEmpty()) {
                    this.mOnBackPressedCallback.setEnabled(getBackStackEntryCount() > 0 && isPrimaryNavigation(this.mParent));
                } else {
                    this.mOnBackPressedCallback.setEnabled(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addBackStackState(BackStackRecord backStackRecord) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<>();
        }
        this.mBackStack.add(backStackRecord);
    }

    public void addCancellationSignal(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        if (this.mExitAnimationCancellationSignals.get(fragment) == null) {
            this.mExitAnimationCancellationSignals.put(fragment, new HashSet<>());
        }
        this.mExitAnimationCancellationSignals.get(fragment).add(cancellationSignal);
    }

    public FragmentStateManager addFragment(@NonNull Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "add: " + fragment);
        }
        FragmentStateManager fragmentStateManagerCreateOrGetFragmentStateManager = createOrGetFragmentStateManager(fragment);
        fragment.mFragmentManager = this;
        this.mFragmentStore.makeActive(fragmentStateManagerCreateOrGetFragmentStateManager);
        if (!fragment.mDetached) {
            this.mFragmentStore.addFragment(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
        return fragmentStateManagerCreateOrGetFragmentStateManager;
    }

    public void addFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.mOnAttachListeners.add(fragmentOnAttachListener);
    }

    public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<>();
        }
        this.mBackStackChangeListeners.add(onBackStackChangedListener);
    }

    public void addRetainedFragment(@NonNull Fragment fragment) {
        this.mNonConfig.addRetainedFragment(fragment);
    }

    public int allocBackStackIndex() {
        return this.mBackStackIndex.getAndIncrement();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"SyntheticAccessor"})
    public void attachController(@NonNull FragmentHostCallback<?> fragmentHostCallback, @NonNull FragmentContainer fragmentContainer, @Nullable final Fragment fragment) {
        String str;
        if (this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = fragmentHostCallback;
        this.mContainer = fragmentContainer;
        this.mParent = fragment;
        if (fragment != null) {
            addFragmentOnAttachListener(new FragmentOnAttachListener() { // from class: androidx.fragment.app.FragmentManager.8
                @Override // androidx.fragment.app.FragmentOnAttachListener
                public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment2) {
                    fragment.onAttachFragment(fragment2);
                }
            });
        } else if (fragmentHostCallback instanceof FragmentOnAttachListener) {
            addFragmentOnAttachListener((FragmentOnAttachListener) fragmentHostCallback);
        }
        if (this.mParent != null) {
            updateOnBackPressedCallbackEnabled();
        }
        if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
            OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) fragmentHostCallback;
            OnBackPressedDispatcher onBackPressedDispatcher = onBackPressedDispatcherOwner.getOnBackPressedDispatcher();
            this.mOnBackPressedDispatcher = onBackPressedDispatcher;
            LifecycleOwner lifecycleOwner = onBackPressedDispatcherOwner;
            if (fragment != null) {
                lifecycleOwner = fragment;
            }
            onBackPressedDispatcher.addCallback(lifecycleOwner, this.mOnBackPressedCallback);
        }
        if (fragment != null) {
            this.mNonConfig = fragment.mFragmentManager.getChildNonConfig(fragment);
        } else if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            this.mNonConfig = FragmentManagerViewModel.getInstance(((ViewModelStoreOwner) fragmentHostCallback).getViewModelStore());
        } else {
            this.mNonConfig = new FragmentManagerViewModel(false);
        }
        this.mNonConfig.setIsStateSaved(isStateSaved());
        this.mFragmentStore.setNonConfig(this.mNonConfig);
        Object obj = this.mHost;
        if (obj instanceof ActivityResultRegistryOwner) {
            ActivityResultRegistry activityResultRegistry = ((ActivityResultRegistryOwner) obj).getActivityResultRegistry();
            if (fragment != null) {
                str = fragment.mWho + MethodCodeHelper.IDENTITY_INFO_SEPARATOR;
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.mStartActivityForResult = activityResultRegistry.register(str2 + "StartActivityForResult", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.9
                @Override // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(ActivityResult activityResult) {
                    LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.mLaunchedFragments.pollFirst();
                    if (launchedFragmentInfoPollFirst == null) {
                        Log.w(FragmentManager.TAG, "No Activities were started for result for " + this);
                        return;
                    }
                    String str3 = launchedFragmentInfoPollFirst.mWho;
                    int i2 = launchedFragmentInfoPollFirst.mRequestCode;
                    Fragment fragmentFindFragmentByWho = FragmentManager.this.mFragmentStore.findFragmentByWho(str3);
                    if (fragmentFindFragmentByWho != null) {
                        fragmentFindFragmentByWho.onActivityResult(i2, activityResult.getResultCode(), activityResult.getData());
                        return;
                    }
                    Log.w(FragmentManager.TAG, "Activity result delivered for unknown Fragment " + str3);
                }
            });
            this.mStartIntentSenderForResult = activityResultRegistry.register(str2 + "StartIntentSenderForResult", new FragmentIntentSenderContract(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.10
                @Override // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(ActivityResult activityResult) {
                    LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.mLaunchedFragments.pollFirst();
                    if (launchedFragmentInfoPollFirst == null) {
                        Log.w(FragmentManager.TAG, "No IntentSenders were started for " + this);
                        return;
                    }
                    String str3 = launchedFragmentInfoPollFirst.mWho;
                    int i2 = launchedFragmentInfoPollFirst.mRequestCode;
                    Fragment fragmentFindFragmentByWho = FragmentManager.this.mFragmentStore.findFragmentByWho(str3);
                    if (fragmentFindFragmentByWho != null) {
                        fragmentFindFragmentByWho.onActivityResult(i2, activityResult.getResultCode(), activityResult.getData());
                        return;
                    }
                    Log.w(FragmentManager.TAG, "Intent Sender result delivered for unknown Fragment " + str3);
                }
            });
            this.mRequestPermissions = activityResultRegistry.register(str2 + "RequestPermissions", new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() { // from class: androidx.fragment.app.FragmentManager.11
                @Override // androidx.activity.result.ActivityResultCallback
                @SuppressLint({"SyntheticAccessor"})
                public void onActivityResult(Map<String, Boolean> map) {
                    String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                    ArrayList arrayList = new ArrayList(map.values());
                    int[] iArr = new int[arrayList.size()];
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
                    }
                    LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.mLaunchedFragments.pollFirst();
                    if (launchedFragmentInfoPollFirst == null) {
                        Log.w(FragmentManager.TAG, "No permissions were requested for " + this);
                        return;
                    }
                    String str3 = launchedFragmentInfoPollFirst.mWho;
                    int i3 = launchedFragmentInfoPollFirst.mRequestCode;
                    Fragment fragmentFindFragmentByWho = FragmentManager.this.mFragmentStore.findFragmentByWho(str3);
                    if (fragmentFindFragmentByWho != null) {
                        fragmentFindFragmentByWho.onRequestPermissionsResult(i3, strArr, iArr);
                        return;
                    }
                    Log.w(FragmentManager.TAG, "Permission request result delivered for unknown Fragment " + str3);
                }
            });
        }
    }

    public void attachFragment(@NonNull Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            this.mFragmentStore.addFragment(fragment);
            if (isLoggingEnabled(2)) {
                Log.v(TAG, "add from attach: " + fragment);
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
    }

    @NonNull
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public boolean checkForMenus() {
        boolean zIsMenuAvailable = false;
        for (Fragment fragment : this.mFragmentStore.getActiveFragments()) {
            if (fragment != null) {
                zIsMenuAvailable = isMenuAvailable(fragment);
            }
            if (zIsMenuAvailable) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResult(@NonNull String str) {
        this.mResults.remove(str);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResultListener(@NonNull String str) {
        LifecycleAwareResultListener lifecycleAwareResultListenerRemove = this.mResultListeners.remove(str);
        if (lifecycleAwareResultListenerRemove != null) {
            lifecycleAwareResultListenerRemove.removeObserver();
        }
    }

    public void completeExecute(@NonNull BackStackRecord backStackRecord, boolean z2, boolean z3, boolean z4) {
        if (z2) {
            backStackRecord.executePopOps(z4);
        } else {
            backStackRecord.executeOps();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z2));
        if (z3 && this.mCurState >= 1) {
            FragmentTransition.startTransitions(this.mHost.getContext(), this.mContainer, arrayList, arrayList2, 0, 1, true, this.mFragmentTransitionCallback);
        }
        if (z4) {
            moveToState(this.mCurState, true);
        }
        for (Fragment fragment : this.mFragmentStore.getActiveFragments()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && backStackRecord.interactsWith(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z4) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    @NonNull
    public FragmentStateManager createOrGetFragmentStateManager(@NonNull Fragment fragment) {
        FragmentStateManager fragmentStateManager = this.mFragmentStore.getFragmentStateManager(fragment.mWho);
        if (fragmentStateManager != null) {
            return fragmentStateManager;
        }
        FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment);
        fragmentStateManager2.restoreState(this.mHost.getContext().getClassLoader());
        fragmentStateManager2.setFragmentManagerState(this.mCurState);
        return fragmentStateManager2;
    }

    public void detachFragment(@NonNull Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (isLoggingEnabled(2)) {
                Log.v(TAG, "remove from detach: " + fragment);
            }
            this.mFragmentStore.removeFragment(fragment);
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
            setVisibleRemovingFragment(fragment);
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(4);
    }

    public void dispatchAttach() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(0);
    }

    public void dispatchConfigurationChanged(@NonNull Configuration configuration) {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean dispatchContextItemSelected(@NonNull MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z2 = false;
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && isParentMenuVisible(fragment) && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z2 = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment fragment2 = this.mCreatedMenus.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z2;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions(true);
        endAnimatingAwayFragments();
        dispatchStateChange(-1);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            this.mOnBackPressedCallback.remove();
            this.mOnBackPressedDispatcher = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this.mStartActivityForResult;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.mStartIntentSenderForResult.unregister();
            this.mRequestPermissions.unregister();
        }
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory() {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z2) {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z2);
            }
        }
    }

    public void dispatchOnAttachFragment(@NonNull Fragment fragment) {
        Iterator<FragmentOnAttachListener> it = this.mOnAttachListeners.iterator();
        while (it.hasNext()) {
            it.next().onAttachFragment(this, fragment);
        }
    }

    public boolean dispatchOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(@NonNull Menu menu) {
        if (this.mCurState < 1) {
            return;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public void dispatchPause() {
        dispatchStateChange(5);
    }

    public void dispatchPictureInPictureModeChanged(boolean z2) {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z2);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(@NonNull Menu menu) {
        boolean z2 = false;
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && isParentMenuVisible(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z2 = true;
            }
        }
        return z2;
    }

    public void dispatchPrimaryNavigationFragmentChanged() {
        updateOnBackPressedCallbackEnabled();
        dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(7);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        dispatchStateChange(5);
    }

    public void dispatchStop() {
        this.mStopped = true;
        this.mNonConfig.setIsStateSaved(true);
        dispatchStateChange(4);
    }

    public void dispatchViewCreated() {
        dispatchStateChange(2);
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.mFragmentStore.dump(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.mCreatedMenus;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = this.mCreatedMenus.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<BackStackRecord> arrayList2 = this.mBackStack;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                BackStackRecord backStackRecord = this.mBackStack.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.mBackStackIndex.get());
        synchronized (this.mPendingActions) {
            try {
                int size3 = this.mPendingActions.size();
                if (size3 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i4 = 0; i4 < size3; i4++) {
                        OpGenerator opGenerator = this.mPendingActions.get(i4);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i4);
                        printWriter.print(": ");
                        printWriter.println(opGenerator);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
    }

    public void enqueueAction(@NonNull OpGenerator opGenerator, boolean z2) {
        if (!z2) {
            if (this.mHost == null) {
                if (!this.mDestroyed) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            checkStateLoss();
        }
        synchronized (this.mPendingActions) {
            try {
                if (this.mHost == null) {
                    if (!z2) {
                        throw new IllegalStateException("Activity has been destroyed");
                    }
                } else {
                    this.mPendingActions.add(opGenerator);
                    scheduleCommit();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean execPendingActions(boolean z2) {
        ensureExecReady(z2);
        boolean z3 = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            z3 = true;
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return z3;
    }

    public void execSingleAction(@NonNull OpGenerator opGenerator, boolean z2) {
        if (z2 && (this.mHost == null || this.mDestroyed)) {
            return;
        }
        ensureExecReady(z2);
        if (opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
    }

    public boolean executePendingTransactions() {
        boolean zExecPendingActions = execPendingActions(true);
        forcePostponedTransactions();
        return zExecPendingActions;
    }

    @Nullable
    public Fragment findActiveFragment(@NonNull String str) {
        return this.mFragmentStore.findActiveFragment(str);
    }

    @Nullable
    public Fragment findFragmentById(@IdRes int i2) {
        return this.mFragmentStore.findFragmentById(i2);
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        return this.mFragmentStore.findFragmentByTag(str);
    }

    public Fragment findFragmentByWho(@NonNull String str) {
        return this.mFragmentStore.findFragmentByWho(str);
    }

    public int getActiveFragmentCount() {
        return this.mFragmentStore.getActiveFragmentCount();
    }

    @NonNull
    public List<Fragment> getActiveFragments() {
        return this.mFragmentStore.getActiveFragments();
    }

    @NonNull
    public BackStackEntry getBackStackEntryAt(int i2) {
        return this.mBackStack.get(i2);
    }

    public int getBackStackEntryCount() {
        ArrayList<BackStackRecord> arrayList = this.mBackStack;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @NonNull
    public FragmentContainer getContainer() {
        return this.mContainer;
    }

    @Nullable
    public Fragment getFragment(@NonNull Bundle bundle, @NonNull String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragmentFindActiveFragment = findActiveFragment(string);
        if (fragmentFindActiveFragment == null) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragmentFindActiveFragment;
    }

    @NonNull
    public FragmentFactory getFragmentFactory() {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory != null) {
            return fragmentFactory;
        }
        Fragment fragment = this.mParent;
        return fragment != null ? fragment.mFragmentManager.getFragmentFactory() : this.mHostFragmentFactory;
    }

    @NonNull
    public FragmentStore getFragmentStore() {
        return this.mFragmentStore;
    }

    @NonNull
    public List<Fragment> getFragments() {
        return this.mFragmentStore.getFragments();
    }

    @NonNull
    public FragmentHostCallback<?> getHost() {
        return this.mHost;
    }

    @NonNull
    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this.mLayoutInflaterFactory;
    }

    @NonNull
    public FragmentLifecycleCallbacksDispatcher getLifecycleCallbacksDispatcher() {
        return this.mLifecycleCallbacksDispatcher;
    }

    @Nullable
    public Fragment getParent() {
        return this.mParent;
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    @NonNull
    public SpecialEffectsControllerFactory getSpecialEffectsControllerFactory() {
        SpecialEffectsControllerFactory specialEffectsControllerFactory = this.mSpecialEffectsControllerFactory;
        if (specialEffectsControllerFactory != null) {
            return specialEffectsControllerFactory;
        }
        Fragment fragment = this.mParent;
        return fragment != null ? fragment.mFragmentManager.getSpecialEffectsControllerFactory() : this.mDefaultSpecialEffectsControllerFactory;
    }

    @NonNull
    public ViewModelStore getViewModelStore(@NonNull Fragment fragment) {
        return this.mNonConfig.getViewModelStore(fragment);
    }

    public void handleOnBackPressed() {
        execPendingActions(true);
        if (this.mOnBackPressedCallback.isEnabled()) {
            popBackStackImmediate();
        } else {
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    public void hideFragment(@NonNull Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        setVisibleRemovingFragment(fragment);
    }

    public void invalidateMenuForFragment(@NonNull Fragment fragment) {
        if (fragment.mAdded && isMenuAvailable(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isParentMenuVisible(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    public boolean isPrimaryNavigation(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        return fragment.equals(fragmentManager.getPrimaryNavigationFragment()) && isPrimaryNavigation(fragmentManager.mParent);
    }

    public boolean isStateAtLeast(int i2) {
        return this.mCurState >= i2;
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    public void launchRequestPermissions(@NonNull Fragment fragment, @NonNull String[] strArr, int i2) {
        if (this.mRequestPermissions == null) {
            this.mHost.onRequestPermissionsFromFragment(fragment, strArr, i2);
            return;
        }
        this.mLaunchedFragments.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        this.mRequestPermissions.launch(strArr);
    }

    public void launchStartActivityForResult(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, @Nullable Bundle bundle) {
        if (this.mStartActivityForResult == null) {
            this.mHost.onStartActivityFromFragment(fragment, intent, i2, bundle);
            return;
        }
        this.mLaunchedFragments.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        if (intent != null && bundle != null) {
            intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        }
        this.mStartActivityForResult.launch(intent);
    }

    public void launchStartIntentSenderForResult(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, @Nullable Intent intent, int i3, int i4, int i5, @Nullable Bundle bundle) {
        Intent intent2;
        if (this.mStartIntentSenderForResult == null) {
            this.mHost.onStartIntentSenderFromFragment(fragment, intentSender, i2, intent, i3, i4, i5, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra(EXTRA_CREATED_FILLIN_INTENT, true);
            } else {
                intent2 = intent;
            }
            if (isLoggingEnabled(2)) {
                Log.v(TAG, "ActivityOptions " + bundle + " were added to fillInIntent " + intent2 + " for fragment " + fragment);
            }
            intent2.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        } else {
            intent2 = intent;
        }
        IntentSenderRequest intentSenderRequestBuild = new IntentSenderRequest.Builder(intentSender).setFillInIntent(intent2).setFlags(i4, i3).build();
        this.mLaunchedFragments.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "Fragment " + fragment + "is launching an IntentSender for result ");
        }
        this.mStartIntentSenderForResult.launch(intentSenderRequestBuild);
    }

    public void moveFragmentToExpectedState(@NonNull Fragment fragment) {
        if (!this.mFragmentStore.containsActiveFragment(fragment.mWho)) {
            if (isLoggingEnabled(3)) {
                Log.d(TAG, "Ignoring moving " + fragment + " to state " + this.mCurState + "since it is not added to " + this);
                return;
            }
            return;
        }
        moveToState(fragment);
        View view = fragment.mView;
        if (view != null && fragment.mIsNewlyAdded && fragment.mContainer != null) {
            float f2 = fragment.mPostponedAlpha;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.mPostponedAlpha = 0.0f;
            fragment.mIsNewlyAdded = false;
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(this.mHost.getContext(), fragment, true, fragment.getPopDirection());
            if (animationOrAnimatorLoadAnimation != null) {
                Animation animation = animationOrAnimatorLoadAnimation.animation;
                if (animation != null) {
                    fragment.mView.startAnimation(animation);
                } else {
                    animationOrAnimatorLoadAnimation.animator.setTarget(fragment.mView);
                    animationOrAnimatorLoadAnimation.animator.start();
                }
            }
        }
        if (fragment.mHiddenChanged) {
            completeShowHideFragment(fragment);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void moveToState(@androidx.annotation.NonNull androidx.fragment.app.Fragment r11, int r12) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.moveToState(androidx.fragment.app.Fragment, int):void");
    }

    public void noteStateNotSaved() {
        if (this.mHost == null) {
            return;
        }
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void onContainerAvailable(@NonNull FragmentContainerView fragmentContainerView) {
        View view;
        for (FragmentStateManager fragmentStateManager : this.mFragmentStore.getActiveFragmentStateManagers()) {
            Fragment fragment = fragmentStateManager.getFragment();
            if (fragment.mContainerId == fragmentContainerView.getId() && (view = fragment.mView) != null && view.getParent() == null) {
                fragment.mContainer = fragmentContainerView;
                fragmentStateManager.addViewToContainer();
            }
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public FragmentTransaction openTransaction() {
        return beginTransaction();
    }

    public void performPendingDeferredStart(@NonNull FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.getFragment();
        if (fragment.mDeferStart) {
            if (this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
                return;
            }
            fragment.mDeferStart = false;
            if (USE_STATE_MANAGER) {
                fragmentStateManager.moveToExpectedState();
            } else {
                moveToState(fragment);
            }
        }
    }

    public void popBackStack() {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackState(@NonNull ArrayList<BackStackRecord> arrayList, @NonNull ArrayList<Boolean> arrayList2, @Nullable String str, int i2, int i3) {
        int i4;
        ArrayList<BackStackRecord> arrayList3 = this.mBackStack;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.mBackStack.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    BackStackRecord backStackRecord = this.mBackStack.get(size2);
                    if ((str != null && str.equals(backStackRecord.getName())) || (i2 >= 0 && i2 == backStackRecord.mIndex)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        BackStackRecord backStackRecord2 = this.mBackStack.get(size2);
                        if (str == null || !str.equals(backStackRecord2.getName())) {
                            if (i2 < 0 || i2 != backStackRecord2.mIndex) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.mBackStack.size() - 1) {
                return false;
            }
            for (int size3 = this.mBackStack.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.mBackStack.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void putFragment(@NonNull Bundle bundle, @NonNull String str, @NonNull Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z2) {
        this.mLifecycleCallbacksDispatcher.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, z2);
    }

    public void removeCancellationSignal(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        HashSet<CancellationSignal> hashSet = this.mExitAnimationCancellationSignals.get(fragment);
        if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
            this.mExitAnimationCancellationSignals.remove(fragment);
            if (fragment.mState < 5) {
                destroyFragmentView(fragment);
                moveToState(fragment);
            }
        }
    }

    public void removeFragment(@NonNull Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean zIsInBackStack = fragment.isInBackStack();
        if (fragment.mDetached && zIsInBackStack) {
            return;
        }
        this.mFragmentStore.removeFragment(fragment);
        if (isMenuAvailable(fragment)) {
            this.mNeedMenuInvalidate = true;
        }
        fragment.mRemoving = true;
        setVisibleRemovingFragment(fragment);
    }

    public void removeFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.mOnAttachListeners.remove(fragmentOnAttachListener);
    }

    public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<OnBackStackChangedListener> arrayList = this.mBackStackChangeListeners;
        if (arrayList != null) {
            arrayList.remove(onBackStackChangedListener);
        }
    }

    public void removeRetainedFragment(@NonNull Fragment fragment) {
        this.mNonConfig.removeRetainedFragment(fragment);
    }

    public void restoreAllState(@Nullable Parcelable parcelable, @Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (this.mHost instanceof ViewModelStoreOwner) {
            throwException(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
        }
        this.mNonConfig.restoreFromSnapshot(fragmentManagerNonConfig);
        restoreSaveState(parcelable);
    }

    public void restoreSaveState(@Nullable Parcelable parcelable) {
        FragmentStateManager fragmentStateManager;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.mActive == null) {
            return;
        }
        this.mFragmentStore.resetActiveFragments();
        for (FragmentState fragmentState : fragmentManagerState.mActive) {
            if (fragmentState != null) {
                Fragment fragmentFindRetainedFragmentByWho = this.mNonConfig.findRetainedFragmentByWho(fragmentState.mWho);
                if (fragmentFindRetainedFragmentByWho != null) {
                    if (isLoggingEnabled(2)) {
                        Log.v(TAG, "restoreSaveState: re-attaching retained " + fragmentFindRetainedFragmentByWho);
                    }
                    fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragmentFindRetainedFragmentByWho, fragmentState);
                } else {
                    fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, this.mHost.getContext().getClassLoader(), getFragmentFactory(), fragmentState);
                }
                Fragment fragment = fragmentStateManager.getFragment();
                fragment.mFragmentManager = this;
                if (isLoggingEnabled(2)) {
                    Log.v(TAG, "restoreSaveState: active (" + fragment.mWho + "): " + fragment);
                }
                fragmentStateManager.restoreState(this.mHost.getContext().getClassLoader());
                this.mFragmentStore.makeActive(fragmentStateManager);
                fragmentStateManager.setFragmentManagerState(this.mCurState);
            }
        }
        for (Fragment fragment2 : this.mNonConfig.getRetainedFragments()) {
            if (!this.mFragmentStore.containsActiveFragment(fragment2.mWho)) {
                if (isLoggingEnabled(2)) {
                    Log.v(TAG, "Discarding retained Fragment " + fragment2 + " that was not found in the set of active Fragments " + fragmentManagerState.mActive);
                }
                this.mNonConfig.removeRetainedFragment(fragment2);
                fragment2.mFragmentManager = this;
                FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment2);
                fragmentStateManager2.setFragmentManagerState(1);
                fragmentStateManager2.moveToExpectedState();
                fragment2.mRemoving = true;
                fragmentStateManager2.moveToExpectedState();
            }
        }
        this.mFragmentStore.restoreAddedFragments(fragmentManagerState.mAdded);
        if (fragmentManagerState.mBackStack != null) {
            this.mBackStack = new ArrayList<>(fragmentManagerState.mBackStack.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.mBackStack;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                BackStackRecord backStackRecordInstantiate = backStackStateArr[i2].instantiate(this);
                if (isLoggingEnabled(2)) {
                    Log.v(TAG, "restoreAllState: back stack #" + i2 + " (index " + backStackRecordInstantiate.mIndex + "): " + backStackRecordInstantiate);
                    PrintWriter printWriter = new PrintWriter(new LogWriter(TAG));
                    backStackRecordInstantiate.dump("  ", printWriter, false);
                    printWriter.close();
                }
                this.mBackStack.add(backStackRecordInstantiate);
                i2++;
            }
        } else {
            this.mBackStack = null;
        }
        this.mBackStackIndex.set(fragmentManagerState.mBackStackIndex);
        String str = fragmentManagerState.mPrimaryNavActiveWho;
        if (str != null) {
            Fragment fragmentFindActiveFragment = findActiveFragment(str);
            this.mPrimaryNav = fragmentFindActiveFragment;
            dispatchParentPrimaryNavigationFragmentChanged(fragmentFindActiveFragment);
        }
        ArrayList<String> arrayList = fragmentManagerState.mResultKeys;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Bundle bundle = fragmentManagerState.mResults.get(i3);
                bundle.setClassLoader(this.mHost.getContext().getClassLoader());
                this.mResults.put(arrayList.get(i3), bundle);
            }
        }
        this.mLaunchedFragments = new ArrayDeque<>(fragmentManagerState.mLaunchedFragments);
    }

    @Deprecated
    public FragmentManagerNonConfig retainNonConfig() {
        if (this.mHost instanceof ViewModelStoreOwner) {
            throwException(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
        }
        return this.mNonConfig.getSnapshot();
    }

    public Parcelable saveAllState() {
        int size;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions(true);
        this.mStateSaved = true;
        this.mNonConfig.setIsStateSaved(true);
        ArrayList<FragmentState> arrayListSaveActiveFragments = this.mFragmentStore.saveActiveFragments();
        BackStackState[] backStackStateArr = null;
        if (arrayListSaveActiveFragments.isEmpty()) {
            if (isLoggingEnabled(2)) {
                Log.v(TAG, "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> arrayListSaveAddedFragments = this.mFragmentStore.saveAddedFragments();
        ArrayList<BackStackRecord> arrayList = this.mBackStack;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState(this.mBackStack.get(i2));
                if (isLoggingEnabled(2)) {
                    Log.v(TAG, "saveAllState: adding back stack #" + i2 + ": " + this.mBackStack.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.mActive = arrayListSaveActiveFragments;
        fragmentManagerState.mAdded = arrayListSaveAddedFragments;
        fragmentManagerState.mBackStack = backStackStateArr;
        fragmentManagerState.mBackStackIndex = this.mBackStackIndex.get();
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null) {
            fragmentManagerState.mPrimaryNavActiveWho = fragment.mWho;
        }
        fragmentManagerState.mResultKeys.addAll(this.mResults.keySet());
        fragmentManagerState.mResults.addAll(this.mResults.values());
        fragmentManagerState.mLaunchedFragments = new ArrayList<>(this.mLaunchedFragments);
        return fragmentManagerState;
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment fragment) {
        FragmentStateManager fragmentStateManager = this.mFragmentStore.getFragmentStateManager(fragment.mWho);
        if (fragmentStateManager == null || !fragmentStateManager.getFragment().equals(fragment)) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        return fragmentStateManager.saveInstanceState();
    }

    public void scheduleCommit() {
        synchronized (this.mPendingActions) {
            try {
                ArrayList<StartEnterTransitionListener> arrayList = this.mPostponedTransactions;
                boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
                boolean z3 = this.mPendingActions.size() == 1;
                if (z2 || z3) {
                    this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                    this.mHost.getHandler().post(this.mExecCommit);
                    updateOnBackPressedCallbackEnabled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setExitAnimationOrder(@NonNull Fragment fragment, boolean z2) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer == null || !(fragmentContainer instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) fragmentContainer).setDrawDisappearingViewsLast(!z2);
    }

    public void setFragmentFactory(@NonNull FragmentFactory fragmentFactory) {
        this.mFragmentFactory = fragmentFactory;
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void setFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
        LifecycleAwareResultListener lifecycleAwareResultListener = this.mResultListeners.get(str);
        if (lifecycleAwareResultListener == null || !lifecycleAwareResultListener.isAtLeast(Lifecycle.State.STARTED)) {
            this.mResults.put(str, bundle);
        } else {
            lifecycleAwareResultListener.onFragmentResult(str, bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    @SuppressLint({"SyntheticAccessor"})
    public final void setFragmentResultListener(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final FragmentResultListener fragmentResultListener) {
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.fragment.app.FragmentManager.6
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                Bundle bundle;
                if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) FragmentManager.this.mResults.get(str)) != null) {
                    fragmentResultListener.onFragmentResult(str, bundle);
                    FragmentManager.this.clearFragmentResult(str);
                }
                if (event == Lifecycle.Event.ON_DESTROY) {
                    lifecycle.removeObserver(this);
                    FragmentManager.this.mResultListeners.remove(str);
                }
            }
        };
        lifecycle.addObserver(lifecycleEventObserver);
        LifecycleAwareResultListener lifecycleAwareResultListenerPut = this.mResultListeners.put(str, new LifecycleAwareResultListener(lifecycle, fragmentResultListener, lifecycleEventObserver));
        if (lifecycleAwareResultListenerPut != null) {
            lifecycleAwareResultListenerPut.removeObserver();
        }
    }

    public void setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.equals(findActiveFragment(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        if (fragment == null || (fragment.equals(findActiveFragment(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.mPrimaryNav;
            this.mPrimaryNav = fragment;
            dispatchParentPrimaryNavigationFragmentChanged(fragment2);
            dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void setSpecialEffectsControllerFactory(@NonNull SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        this.mSpecialEffectsControllerFactory = specialEffectsControllerFactory;
    }

    public void showFragment(@NonNull Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v(TAG, "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.mParent)));
            sb.append("}");
        } else {
            FragmentHostCallback<?> fragmentHostCallback = this.mHost;
            if (fragmentHostCallback != null) {
                sb.append(fragmentHostCallback.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.mHost)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        this.mLifecycleCallbacksDispatcher.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks);
    }

    public void popBackStack(@Nullable String str, int i2) {
        enqueueAction(new PopBackStackState(str, -1, i2), false);
    }

    public boolean popBackStackImmediate(@Nullable String str, int i2) {
        return popBackStackImmediate(str, -1, i2);
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new Parcelable.Creator<LaunchedFragmentInfo>() { // from class: androidx.fragment.app.FragmentManager.LaunchedFragmentInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo[] newArray(int i2) {
                return new LaunchedFragmentInfo[i2];
            }
        };
        int mRequestCode;
        String mWho;

        public LaunchedFragmentInfo(@NonNull String str, int i2) {
            this.mWho = str;
            this.mRequestCode = i2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.mWho);
            parcel.writeInt(this.mRequestCode);
        }

        public LaunchedFragmentInfo(@NonNull Parcel parcel) {
            this.mWho = parcel.readString();
            this.mRequestCode = parcel.readInt();
        }
    }

    public void popBackStack(int i2, int i3) {
        if (i2 >= 0) {
            enqueueAction(new PopBackStackState(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean popBackStackImmediate(int i2, int i3) {
        if (i2 >= 0) {
            return popBackStackImmediate(null, i2, i3);
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    private boolean popBackStackImmediate(@Nullable String str, int i2, int i3) {
        execPendingActions(false);
        ensureExecReady(true);
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean zPopBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, str, i2, i3);
        if (zPopBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return zPopBackStackState;
    }

    public void moveToState(@NonNull Fragment fragment) {
        moveToState(fragment, this.mCurState);
    }

    public void moveToState(int i2, boolean z2) {
        FragmentHostCallback<?> fragmentHostCallback;
        if (this.mHost == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z2 || i2 != this.mCurState) {
            this.mCurState = i2;
            if (USE_STATE_MANAGER) {
                this.mFragmentStore.moveToExpectedState();
            } else {
                Iterator<Fragment> it = this.mFragmentStore.getFragments().iterator();
                while (it.hasNext()) {
                    moveFragmentToExpectedState(it.next());
                }
                for (FragmentStateManager fragmentStateManager : this.mFragmentStore.getActiveFragmentStateManagers()) {
                    Fragment fragment = fragmentStateManager.getFragment();
                    if (!fragment.mIsNewlyAdded) {
                        moveFragmentToExpectedState(fragment);
                    }
                    if (fragment.mRemoving && !fragment.isInBackStack()) {
                        this.mFragmentStore.makeInactive(fragmentStateManager);
                    }
                }
            }
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.mCurState == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }
}
