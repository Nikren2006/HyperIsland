package com.android.systemui.settings;

import H0.i;
import H0.o;
import H0.s;
import I0.m;
import I0.u;
import Z0.c;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import com.android.systemui.settings.UserTracker;
import d1.InterfaceC0330i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.q;
import kotlin.jvm.internal.z;
import miui.systemui.controls.ExposeUtils;
import miui.systemui.util.Assert;

/* JADX INFO: loaded from: classes2.dex */
public final class UserTrackerImpl extends BroadcastReceiver implements UserTracker {
    static final /* synthetic */ InterfaceC0330i[] $$delegatedProperties = {z.d(new q(UserTrackerImpl.class, "userId", "getUserId()I", 0)), z.d(new q(UserTrackerImpl.class, "userHandle", "getUserHandle()Landroid/os/UserHandle;", 0)), z.d(new q(UserTrackerImpl.class, "userContext", "getUserContext()Landroid/content/Context;", 0)), z.d(new q(UserTrackerImpl.class, "userProfiles", "getUserProfiles()Ljava/util/List;", 0))};
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UserTrackerImpl";
    private final Handler backgroundHandler;

    @GuardedBy("callbacks")
    private final List<DataItem> callbacks;
    private final Context context;
    private boolean initialized;
    private final Object mutex;
    private final SynchronizedDelegate userContext$delegate;
    private final SynchronizedDelegate userHandle$delegate;
    private final SynchronizedDelegate userId$delegate;
    private final UserManager userManager;
    private final SynchronizedDelegate userProfiles$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class SynchronizedDelegate<T> implements c {
        private T value;

        public SynchronizedDelegate(T value) {
            n.g(value, "value");
            this.value = value;
        }

        @Override // Z0.c
        @GuardedBy("mutex")
        public T getValue(UserTrackerImpl thisRef, InterfaceC0330i property) {
            T t2;
            n.g(thisRef, "thisRef");
            n.g(property, "property");
            if (thisRef.getInitialized()) {
                synchronized (thisRef.mutex) {
                    t2 = this.value;
                }
                return t2;
            }
            throw new IllegalStateException("Must initialize before getting " + property.getName());
        }

        @Override // Z0.c
        @GuardedBy("mutex")
        public void setValue(UserTrackerImpl thisRef, InterfaceC0330i property, T value) {
            n.g(thisRef, "thisRef");
            n.g(property, "property");
            n.g(value, "value");
            synchronized (thisRef.mutex) {
                this.value = value;
                s sVar = s.f314a;
            }
        }
    }

    public UserTrackerImpl(Context context, UserManager userManager, Handler backgroundHandler) {
        n.g(context, "context");
        n.g(userManager, "userManager");
        n.g(backgroundHandler, "backgroundHandler");
        this.context = context;
        this.userManager = userManager;
        this.backgroundHandler = backgroundHandler;
        this.mutex = new Object();
        this.userId$delegate = new SynchronizedDelegate(Integer.valueOf(context.getUserId()));
        this.userHandle$delegate = new SynchronizedDelegate(ExposeUtils.INSTANCE.getUserExpose(context));
        this.userContext$delegate = new SynchronizedDelegate(context);
        this.userProfiles$delegate = new SynchronizedDelegate(m.h());
        this.callbacks = new ArrayList();
    }

    @WorkerThread
    private final void handleProfilesChanged() {
        List<DataItem> listK0;
        Assert.isNotMainThread();
        final List profiles = this.userManager.getProfiles(getUserId());
        synchronized (this.mutex) {
            try {
                n.d(profiles);
                ArrayList arrayList = new ArrayList(I0.n.o(profiles, 10));
                Iterator it = profiles.iterator();
                while (it.hasNext()) {
                    arrayList.add(new UserInfo((UserInfo) it.next()));
                }
                setUserProfiles(arrayList);
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        synchronized (this.callbacks) {
            listK0 = u.k0(this.callbacks);
        }
        for (final DataItem dataItem : listK0) {
            if (dataItem.getCallback().get() != null) {
                dataItem.getExecutor().execute(new Runnable() { // from class: com.android.systemui.settings.UserTrackerImpl$handleProfilesChanged$$inlined$notifySubscribers$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserTracker.Callback callback = dataItem.getCallback().get();
                        if (callback != null) {
                            n.d(profiles);
                            callback.onProfilesChanged(profiles);
                        }
                    }
                });
            }
        }
    }

    @WorkerThread
    private final void handleSwitchUser(final int i2) {
        List<DataItem> listK0;
        Assert.isNotMainThread();
        if (i2 == -10000) {
            Log.w(TAG, "handleSwitchUser - Couldn't get new id from intent");
            return;
        }
        if (i2 == getUserId()) {
            return;
        }
        Log.i(TAG, "Switching to user " + i2);
        i userIdInternal = setUserIdInternal(i2);
        final Context context = (Context) userIdInternal.a();
        final List list = (List) userIdInternal.b();
        synchronized (this.callbacks) {
            listK0 = u.k0(this.callbacks);
        }
        for (final DataItem dataItem : listK0) {
            if (dataItem.getCallback().get() != null) {
                dataItem.getExecutor().execute(new Runnable() { // from class: com.android.systemui.settings.UserTrackerImpl$handleSwitchUser$$inlined$notifySubscribers$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserTracker.Callback callback = dataItem.getCallback().get();
                        if (callback != null) {
                            callback.onUserChanged(i2, context);
                            callback.onProfilesChanged(list);
                        }
                    }
                });
            }
        }
    }

    private final void notifySubscribers(final Function1 function1) {
        List<DataItem> listK0;
        synchronized (this.callbacks) {
            try {
                listK0 = u.k0(this.callbacks);
                kotlin.jvm.internal.m.b(1);
            } catch (Throwable th) {
                kotlin.jvm.internal.m.b(1);
                kotlin.jvm.internal.m.a(1);
                throw th;
            }
        }
        kotlin.jvm.internal.m.a(1);
        for (final DataItem dataItem : listK0) {
            if (dataItem.getCallback().get() != null) {
                dataItem.getExecutor().execute(new Runnable() { // from class: com.android.systemui.settings.UserTrackerImpl$notifySubscribers$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserTracker.Callback callback = dataItem.getCallback().get();
                        if (callback != null) {
                            function1.invoke(callback);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeCallback$lambda$10$lambda$9(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    private void setUserContext(Context context) {
        this.userContext$delegate.setValue(this, $$delegatedProperties[2], context);
    }

    private void setUserHandle(UserHandle userHandle) {
        this.userHandle$delegate.setValue(this, $$delegatedProperties[1], userHandle);
    }

    private void setUserId(int i2) {
        this.userId$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i2));
    }

    private final i setUserIdInternal(int i2) {
        List profiles = this.userManager.getProfiles(i2);
        UserHandle userHandle = new UserHandle(i2);
        Context contextCreateContextAsUser = this.context.createContextAsUser(userHandle, 0);
        synchronized (this.mutex) {
            try {
                setUserId(i2);
                setUserHandle(userHandle);
                Context contextCreateDeviceProtectedStorageContext = contextCreateContextAsUser.createDeviceProtectedStorageContext();
                n.f(contextCreateDeviceProtectedStorageContext, "createDeviceProtectedStorageContext(...)");
                setUserContext(contextCreateDeviceProtectedStorageContext);
                n.d(profiles);
                ArrayList arrayList = new ArrayList(I0.n.o(profiles, 10));
                Iterator it = profiles.iterator();
                while (it.hasNext()) {
                    arrayList.add(new UserInfo((UserInfo) it.next()));
                }
                setUserProfiles(arrayList);
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return o.a(contextCreateContextAsUser, profiles);
    }

    private void setUserProfiles(List<? extends UserInfo> list) {
        this.userProfiles$delegate.setValue(this, $$delegatedProperties[3], list);
    }

    @Override // com.android.systemui.settings.UserTracker
    public void addCallback(UserTracker.Callback callback, Executor executor) {
        n.g(callback, "callback");
        n.g(executor, "executor");
        synchronized (this.callbacks) {
            this.callbacks.add(new DataItem(new WeakReference(callback), executor));
        }
    }

    public final boolean getInitialized() {
        return this.initialized;
    }

    @Override // com.android.systemui.settings.UserContentResolverProvider
    public ContentResolver getUserContentResolver() {
        ContentResolver contentResolver = getUserContext().getContentResolver();
        n.f(contentResolver, "getContentResolver(...)");
        return contentResolver;
    }

    @Override // com.android.systemui.settings.UserContextProvider
    public Context getUserContext() {
        return (Context) this.userContext$delegate.getValue(this, $$delegatedProperties[2]);
    }

    @Override // com.android.systemui.settings.UserTracker
    public UserHandle getUserHandle() {
        return (UserHandle) this.userHandle$delegate.getValue(this, $$delegatedProperties[1]);
    }

    @Override // com.android.systemui.settings.UserTracker
    public int getUserId() {
        return ((Number) this.userId$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    @Override // com.android.systemui.settings.UserTracker
    public UserInfo getUserInfo() {
        int userId = getUserId();
        for (UserInfo userInfo : getUserProfiles()) {
            if (userInfo.id == userId) {
                return userInfo;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // com.android.systemui.settings.UserTracker
    public List<UserInfo> getUserProfiles() {
        return (List) this.userProfiles$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final void initialize(int i2) {
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        setUserIdInternal(i2);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_REMOVED");
        this.context.registerReceiverForAllUsers(this, intentFilter, null, this.backgroundHandler);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        n.g(context, "context");
        n.g(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            int iHashCode = action.hashCode();
            if (iHashCode != -1238404651) {
                if (iHashCode != -864107122) {
                    if (iHashCode == 959232034 && action.equals("android.intent.action.USER_SWITCHED")) {
                        handleSwitchUser(intent.getIntExtra("android.intent.extra.user_handle", -10000));
                        return;
                    }
                    return;
                }
                if (!action.equals("android.intent.action.MANAGED_PROFILE_AVAILABLE")) {
                    return;
                }
            } else if (!action.equals("android.intent.action.MANAGED_PROFILE_UNAVAILABLE")) {
                return;
            }
            handleProfilesChanged();
        }
    }

    @Override // com.android.systemui.settings.UserTracker
    public void removeCallback(UserTracker.Callback callback) {
        n.g(callback, "callback");
        synchronized (this.callbacks) {
            List<DataItem> list = this.callbacks;
            final UserTrackerImpl$removeCallback$1$1 userTrackerImpl$removeCallback$1$1 = new UserTrackerImpl$removeCallback$1$1(callback);
            list.removeIf(new Predicate() { // from class: com.android.systemui.settings.a
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return UserTrackerImpl.removeCallback$lambda$10$lambda$9(userTrackerImpl$removeCallback$1$1, obj);
                }
            });
        }
    }
}
