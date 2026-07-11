package androidx.window.layout;

import H0.s;
import I0.m;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Consumer;
import androidx.window.core.Version;
import androidx.window.layout.ExtensionInterfaceCompat;
import androidx.window.layout.SidecarWindowBackend;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class SidecarWindowBackend implements WindowBackend {
    public static final boolean DEBUG = false;
    private static final String TAG = "WindowServer";
    private static volatile SidecarWindowBackend globalInstance;

    @GuardedBy("globalLock")
    @VisibleForTesting
    private ExtensionInterfaceCompat windowExtension;
    private final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> windowLayoutChangeCallbacks = new CopyOnWriteArrayList<>();
    public static final Companion Companion = new Companion(null);
    private static final ReentrantLock globalLock = new ReentrantLock();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SidecarWindowBackend getInstance(Context context) {
            n.g(context, "context");
            if (SidecarWindowBackend.globalInstance == null) {
                ReentrantLock reentrantLock = SidecarWindowBackend.globalLock;
                reentrantLock.lock();
                try {
                    if (SidecarWindowBackend.globalInstance == null) {
                        SidecarWindowBackend.globalInstance = new SidecarWindowBackend(SidecarWindowBackend.Companion.initAndVerifyExtension(context));
                    }
                    s sVar = s.f314a;
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
            SidecarWindowBackend sidecarWindowBackend = SidecarWindowBackend.globalInstance;
            n.d(sidecarWindowBackend);
            return sidecarWindowBackend;
        }

        public final ExtensionInterfaceCompat initAndVerifyExtension(Context context) {
            n.g(context, "context");
            try {
                if (!isSidecarVersionSupported(SidecarCompat.Companion.getSidecarVersion())) {
                    return null;
                }
                SidecarCompat sidecarCompat = new SidecarCompat(context);
                if (sidecarCompat.validateExtensionInterface()) {
                    return sidecarCompat;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }

        @VisibleForTesting
        public final boolean isSidecarVersionSupported(Version version) {
            return version != null && version.compareTo(Version.Companion.getVERSION_0_1()) >= 0;
        }

        @VisibleForTesting
        public final void resetInstance() {
            SidecarWindowBackend.globalInstance = null;
        }

        private Companion() {
        }
    }

    @VisibleForTesting
    public final class ExtensionListenerImpl implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        final /* synthetic */ SidecarWindowBackend this$0;

        public ExtensionListenerImpl(SidecarWindowBackend this$0) {
            n.g(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // androidx.window.layout.ExtensionInterfaceCompat.ExtensionCallbackInterface
        @SuppressLint({"SyntheticAccessor"})
        public void onWindowLayoutChanged(Activity activity, WindowLayoutInfo newLayout) {
            n.g(activity, "activity");
            n.g(newLayout, "newLayout");
            for (WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper : this.this$0.getWindowLayoutChangeCallbacks()) {
                if (n.c(windowLayoutChangeCallbackWrapper.getActivity(), activity)) {
                    windowLayoutChangeCallbackWrapper.accept(newLayout);
                }
            }
        }
    }

    public static final class WindowLayoutChangeCallbackWrapper {
        private final Activity activity;
        private final Consumer<WindowLayoutInfo> callback;
        private final Executor executor;
        private WindowLayoutInfo lastInfo;

        public WindowLayoutChangeCallbackWrapper(Activity activity, Executor executor, Consumer<WindowLayoutInfo> callback) {
            n.g(activity, "activity");
            n.g(executor, "executor");
            n.g(callback, "callback");
            this.activity = activity;
            this.executor = executor;
            this.callback = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: accept$lambda-0, reason: not valid java name */
        public static final void m54accept$lambda0(WindowLayoutChangeCallbackWrapper this$0, WindowLayoutInfo newLayoutInfo) {
            n.g(this$0, "this$0");
            n.g(newLayoutInfo, "$newLayoutInfo");
            this$0.callback.accept(newLayoutInfo);
        }

        public final void accept(final WindowLayoutInfo newLayoutInfo) {
            n.g(newLayoutInfo, "newLayoutInfo");
            this.lastInfo = newLayoutInfo;
            this.executor.execute(new Runnable() { // from class: androidx.window.layout.a
                @Override // java.lang.Runnable
                public final void run() {
                    SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.m54accept$lambda0(this.f1297a, newLayoutInfo);
                }
            });
        }

        public final Activity getActivity() {
            return this.activity;
        }

        public final Consumer<WindowLayoutInfo> getCallback() {
            return this.callback;
        }

        public final WindowLayoutInfo getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(WindowLayoutInfo windowLayoutInfo) {
            this.lastInfo = windowLayoutInfo;
        }
    }

    @VisibleForTesting
    public SidecarWindowBackend(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
        ExtensionInterfaceCompat extensionInterfaceCompat2 = this.windowExtension;
        if (extensionInterfaceCompat2 == null) {
            return;
        }
        extensionInterfaceCompat2.setExtensionCallback(new ExtensionListenerImpl(this));
    }

    @GuardedBy("sLock")
    private final void callbackRemovedForActivity(Activity activity) {
        CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
        if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
            Iterator<T> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                if (n.c(((WindowLayoutChangeCallbackWrapper) it.next()).getActivity(), activity)) {
                    return;
                }
            }
        }
        ExtensionInterfaceCompat extensionInterfaceCompat = this.windowExtension;
        if (extensionInterfaceCompat == null) {
            return;
        }
        extensionInterfaceCompat.onWindowLayoutChangeListenerRemoved(activity);
    }

    @VisibleForTesting
    public static /* synthetic */ void getWindowLayoutChangeCallbacks$annotations() {
    }

    private final boolean isActivityRegistered(Activity activity) {
        CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            if (n.c(((WindowLayoutChangeCallbackWrapper) it.next()).getActivity(), activity)) {
                return true;
            }
        }
        return false;
    }

    public final ExtensionInterfaceCompat getWindowExtension() {
        return this.windowExtension;
    }

    public final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> getWindowLayoutChangeCallbacks() {
        return this.windowLayoutChangeCallbacks;
    }

    @Override // androidx.window.layout.WindowBackend
    public void registerLayoutChangeCallback(Activity activity, Executor executor, Consumer<WindowLayoutInfo> callback) {
        WindowLayoutInfo lastInfo;
        Object next;
        n.g(activity, "activity");
        n.g(executor, "executor");
        n.g(callback, "callback");
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            ExtensionInterfaceCompat windowExtension = getWindowExtension();
            if (windowExtension == null) {
                callback.accept(new WindowLayoutInfo(m.h()));
                return;
            }
            boolean zIsActivityRegistered = isActivityRegistered(activity);
            WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper = new WindowLayoutChangeCallbackWrapper(activity, executor, callback);
            getWindowLayoutChangeCallbacks().add(windowLayoutChangeCallbackWrapper);
            if (zIsActivityRegistered) {
                Iterator<T> it = getWindowLayoutChangeCallbacks().iterator();
                while (true) {
                    lastInfo = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (n.c(activity, ((WindowLayoutChangeCallbackWrapper) next).getActivity())) {
                            break;
                        }
                    }
                }
                WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper2 = (WindowLayoutChangeCallbackWrapper) next;
                if (windowLayoutChangeCallbackWrapper2 != null) {
                    lastInfo = windowLayoutChangeCallbackWrapper2.getLastInfo();
                }
                if (lastInfo != null) {
                    windowLayoutChangeCallbackWrapper.accept(lastInfo);
                }
            } else {
                windowExtension.onWindowLayoutChangeListenerAdded(activity);
            }
            s sVar = s.f314a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setWindowExtension(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
    }

    @Override // androidx.window.layout.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback) {
        n.g(callback, "callback");
        synchronized (globalLock) {
            try {
                if (getWindowExtension() == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (WindowLayoutChangeCallbackWrapper callbackWrapper : getWindowLayoutChangeCallbacks()) {
                    if (callbackWrapper.getCallback() == callback) {
                        n.f(callbackWrapper, "callbackWrapper");
                        arrayList.add(callbackWrapper);
                    }
                }
                getWindowLayoutChangeCallbacks().removeAll(arrayList);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    callbackRemovedForActivity(((WindowLayoutChangeCallbackWrapper) it.next()).getActivity());
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
