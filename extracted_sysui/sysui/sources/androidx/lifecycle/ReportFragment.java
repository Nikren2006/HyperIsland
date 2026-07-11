package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ReportFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private static final String REPORT_FRAGMENT_TAG = "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private ActivityInitializationListener processListener;

    public interface ActivityInitializationListener {
        void onCreate();

        void onResume();

        void onStart();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void get$annotations(Activity activity) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void dispatch$lifecycle_runtime_release(Activity activity, Lifecycle.Event event) {
            n.g(activity, "activity");
            n.g(event, "event");
            if (activity instanceof LifecycleRegistryOwner) {
                ((LifecycleRegistryOwner) activity).getLifecycle().handleLifecycleEvent(event);
            } else if (activity instanceof LifecycleOwner) {
                Lifecycle lifecycle = ((LifecycleOwner) activity).getLifecycle();
                if (lifecycle instanceof LifecycleRegistry) {
                    ((LifecycleRegistry) lifecycle).handleLifecycleEvent(event);
                }
            }
        }

        public final ReportFragment get(Activity activity) {
            n.g(activity, "<this>");
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag(ReportFragment.REPORT_FRAGMENT_TAG);
            n.e(fragmentFindFragmentByTag, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            return (ReportFragment) fragmentFindFragmentByTag;
        }

        public final void injectIfNeededIn(Activity activity) {
            n.g(activity, "activity");
            LifecycleCallbacks.Companion.registerIn(activity);
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.findFragmentByTag(ReportFragment.REPORT_FRAGMENT_TAG) == null) {
                fragmentManager.beginTransaction().add(new ReportFragment(), ReportFragment.REPORT_FRAGMENT_TAG).commit();
                fragmentManager.executePendingTransactions();
            }
        }

        private Companion() {
        }
    }

    @RequiresApi(29)
    public static final class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        public static final Companion Companion = new Companion(null);

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final void registerIn(Activity activity) {
                n.g(activity, "activity");
                activity.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
            }

            private Companion() {
            }
        }

        public static final void registerIn(Activity activity) {
            Companion.registerIn(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            n.g(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            n.g(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            n.g(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            n.g(activity, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            n.g(activity, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            n.g(activity, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            n.g(activity, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            n.g(activity, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            n.g(activity, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            n.g(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            n.g(activity, "activity");
            n.g(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            n.g(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            n.g(activity, "activity");
        }
    }

    private final void dispatch(Lifecycle.Event event) {
    }

    private final void dispatchCreate(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.onCreate();
        }
    }

    private final void dispatchResume(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.onResume();
        }
    }

    private final void dispatchStart(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.onStart();
        }
    }

    public static final ReportFragment get(Activity activity) {
        return Companion.get(activity);
    }

    public static final void injectIfNeededIn(Activity activity) {
        Companion.injectIfNeededIn(activity);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        dispatchCreate(this.processListener);
        dispatch(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dispatch(Lifecycle.Event.ON_DESTROY);
        this.processListener = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        dispatch(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        dispatchResume(this.processListener);
        dispatch(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        dispatchStart(this.processListener);
        dispatch(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        dispatch(Lifecycle.Event.ON_STOP);
    }

    public final void setProcessListener(ActivityInitializationListener activityInitializationListener) {
        this.processListener = activityInitializationListener;
    }
}
