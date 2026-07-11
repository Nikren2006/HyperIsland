package miuix.appcompat.app.floatingactivity.multiapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import miuix.appcompat.R;
import miuix.appcompat.app.AppCompatActivity;
import miuix.appcompat.app.floatingactivity.FloatingActivitySwitcher;
import miuix.appcompat.app.floatingactivity.FloatingAnimHelper;
import miuix.appcompat.app.floatingactivity.MemoryFileUtil;
import miuix.appcompat.app.floatingactivity.OnFloatingCallback;
import miuix.appcompat.app.floatingactivity.SnapShotViewHelper;
import miuix.appcompat.app.floatingactivity.helper.FloatingHelperFactory;
import miuix.appcompat.app.floatingactivity.multiapp.IFloatingService;
import miuix.appcompat.app.floatingactivity.multiapp.IServiceNotify;

/* JADX INFO: loaded from: classes2.dex */
public final class MultiAppFloatingActivitySwitcher {
    private static final long INVOKE_THRESHOLD = 100;
    private static final String SAVED_INSTANCE_KEY = "floating_switcher_saved_key";
    public static final String SERVICE_FIRST_FLOATING = "first_floating_activity";
    public static final String SERVICE_ORIGINAL_PAGE_INDEX = "floating_service_original_page_index";
    public static final String SERVICE_PAGE_INDEX = "service_page_index";
    public static final String SERVICE_PATH = "floating_service_path";
    public static final String SERVICE_PKG = "floating_service_pkg";
    private static final String TAG = "MFloatingSwitcher";
    private static String[] mAllowedPackageList;
    private static MultiAppFloatingActivitySwitcher sInstance;
    private long mCloseAllActivityTime;
    private IFloatingService mIFloatingService;
    private WeakReference<View> mLastActivityPanel;
    private long mOnDragEndTime;
    private long mOnDragStartTime;
    private boolean mServiceConnected;
    private final Handler mExitAnimationHandler = new Handler(Looper.getMainLooper());
    private final SparseArray<ArrayList<ActivitySpec>> mActivityCache = new SparseArray<>();
    private boolean mEnableDragToDismiss = true;
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: miuix.appcompat.app.floatingactivity.multiapp.MultiAppFloatingActivitySwitcher.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(MultiAppFloatingActivitySwitcher.TAG, "onServiceConnected");
            if (MultiAppFloatingActivitySwitcher.sInstance != null) {
                MultiAppFloatingActivitySwitcher.sInstance.setIFloatingService(IFloatingService.Stub.asInterface(iBinder));
                MultiAppFloatingActivitySwitcher.this.checkRegister();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(MultiAppFloatingActivitySwitcher.TAG, "onServiceDisconnected");
            if (MultiAppFloatingActivitySwitcher.sInstance != null) {
                MultiAppFloatingActivitySwitcher.sInstance.unRegisterAll();
                MultiAppFloatingActivitySwitcher.this.clear();
                MultiAppFloatingActivitySwitcher.this.destroy();
            }
        }
    };

    public class DefineOnFloatingActivityCallback implements OnFloatingCallback {
        protected int mAppCompatActivityTaskId;
        protected String mAppCompatIdentity;

        public DefineOnFloatingActivityCallback(AppCompatActivity appCompatActivity) {
            this.mAppCompatIdentity = appCompatActivity.getActivityIdentity();
            this.mAppCompatActivityTaskId = appCompatActivity.getTaskId();
        }

        private boolean checkFinishEnable(int i2) {
            return !MultiAppFloatingActivitySwitcher.this.mEnableDragToDismiss && (i2 == 1 || i2 == 2);
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public void closeAllPage() {
            MultiAppFloatingActivitySwitcher.this.notifyService(11);
        }

        public String getActivityIdentity() {
            return this.mAppCompatIdentity;
        }

        public int getActivityTaskId() {
            return this.mAppCompatActivityTaskId;
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public int getPageCount() {
            return Math.max(MultiAppFloatingActivitySwitcher.this.getServicePageCount(getActivityTaskId()), MultiAppFloatingActivitySwitcher.this.getCurrentPageCount(getActivityTaskId()));
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public void getSnapShotAndSetPanel(AppCompatActivity appCompatActivity) throws Throwable {
            if (appCompatActivity != null) {
                try {
                    MultiAppFloatingActivitySwitcher multiAppFloatingActivitySwitcher = MultiAppFloatingActivitySwitcher.getInstance();
                    if (multiAppFloatingActivitySwitcher != null) {
                        multiAppFloatingActivitySwitcher.saveBitmap(SnapShotViewHelper.getSnapShot(appCompatActivity.getFloatingBrightPanel()), appCompatActivity.getTaskId(), appCompatActivity.getActivityIdentity());
                    }
                } catch (Exception e2) {
                    Log.d(MultiAppFloatingActivitySwitcher.TAG, "saveBitmap exception", e2);
                }
            }
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public boolean isFirstPage() {
            ArrayList arrayList = (ArrayList) MultiAppFloatingActivitySwitcher.this.mActivityCache.get(getActivityTaskId());
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ActivitySpec activitySpec = (ActivitySpec) arrayList.get(i2);
                    AppCompatActivity appCompatActivity = activitySpec.activity;
                    if (appCompatActivity != null && activitySpec.index == 0) {
                        return appCompatActivity.getActivityIdentity().equals(getActivityIdentity());
                    }
                }
            }
            return false;
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public boolean isFirstPageEnterAnimExecuteEnable() {
            ArrayList arrayList = (ArrayList) MultiAppFloatingActivitySwitcher.this.mActivityCache.get(getActivityTaskId());
            if (arrayList == null) {
                return false;
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (((ActivitySpec) arrayList.get(i2)).index == 0) {
                    return !r2.isOpenEnterAnimExecuted;
                }
            }
            return false;
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public boolean isFirstPageExitAnimExecuteEnable() {
            return getPageCount() == 1;
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public void markActivityOpenEnterAnimExecuted(AppCompatActivity appCompatActivity) {
            MultiAppFloatingActivitySwitcher.this.markActivityOpenEnterAnimExecutedInternal(appCompatActivity.getTaskId(), appCompatActivity.getActivityIdentity());
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public void onDragEnd() {
            MultiAppFloatingActivitySwitcher.this.notifyService(2);
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public void onDragStart() {
            MultiAppFloatingActivitySwitcher.this.notifyService(1);
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingActivityCallback
        public boolean onFinish(int i2) {
            if (!checkFinishEnable(i2) && MultiAppFloatingActivitySwitcher.this.shouldAllFloatingClose(i2, getActivityTaskId())) {
                MultiAppFloatingActivitySwitcher.this.notifyService(5);
            }
            return false;
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingCallback
        public void onHideBehindPage() {
            MultiAppFloatingActivitySwitcher.this.notifyService(5);
        }
    }

    public static class OpenExitAnimationExecutor implements Runnable {
        private WeakReference<AppCompatActivity> mAppCompatActivity;

        public OpenExitAnimationExecutor(AppCompatActivity appCompatActivity) {
            this.mAppCompatActivity = null;
            this.mAppCompatActivity = new WeakReference<>(appCompatActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatActivity appCompatActivity = this.mAppCompatActivity.get();
            if (appCompatActivity != null) {
                appCompatActivity.executeOpenExitAnimation();
            }
        }
    }

    public class ServiceNotify extends IServiceNotify.Stub {
        protected String mActivityIdentity;
        protected int mActivityTaskId;

        public ServiceNotify(AppCompatActivity appCompatActivity) {
            this.mActivityIdentity = appCompatActivity.getActivityIdentity();
            this.mActivityTaskId = appCompatActivity.getTaskId();
        }

        @Nullable
        private AppCompatActivity getActivity() {
            MultiAppFloatingActivitySwitcher multiAppFloatingActivitySwitcher = MultiAppFloatingActivitySwitcher.getInstance();
            if (multiAppFloatingActivitySwitcher != null) {
                return multiAppFloatingActivitySwitcher.getActivity(getActivityTaskId(), getActivityIdentity());
            }
            return null;
        }

        public String getActivityIdentity() {
            return this.mActivityIdentity;
        }

        public int getActivityTaskId() {
            return this.mActivityTaskId;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // miuix.appcompat.app.floatingactivity.multiapp.IServiceNotify
        public Bundle notifyFromService(int i2, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            if (i2 == 1) {
                MultiAppFloatingActivitySwitcher.sInstance.hideBehindPages();
            } else if (i2 == 2) {
                MultiAppFloatingActivitySwitcher.sInstance.onDragEnd();
            } else if (i2 == 3) {
                MultiAppFloatingActivitySwitcher.sInstance.closeAllActivity();
                AppCompatActivity activity = getActivity();
                if (activity != null) {
                    MultiAppFloatingActivitySwitcher.sInstance.unbindService(activity);
                }
            } else if (i2 != 5) {
                switch (i2) {
                    case 8:
                        AppCompatActivity activity2 = getActivity();
                        if (bundle != null && activity2 != null) {
                            View floatingBrightPanel = activity2.getFloatingBrightPanel();
                            MultiAppFloatingActivitySwitcher.this.setLastActivityPanel(SnapShotViewHelper.generateSnapShotView(floatingBrightPanel, MemoryFileUtil.readBitmap(bundle)));
                            if (MultiAppFloatingActivitySwitcher.this.mLastActivityPanel != null && MultiAppFloatingActivitySwitcher.this.mLastActivityPanel.get() != null) {
                                ((ViewGroup) floatingBrightPanel.getParent()).getOverlay().add((View) MultiAppFloatingActivitySwitcher.this.mLastActivityPanel.get());
                            }
                        }
                        break;
                    case 9:
                        AppCompatActivity activity3 = getActivity();
                        bundle2.putBoolean(MethodCodeHelper.METHOD_RESULT_CHECK_FINISHNING, activity3 != null && activity3.isFinishing());
                        break;
                    case 10:
                        AppCompatActivity activity4 = getActivity();
                        if (activity4 != null) {
                            MultiAppFloatingActivitySwitcher.this.mExitAnimationHandler.postDelayed(new OpenExitAnimationExecutor(activity4), 160L);
                        }
                        break;
                    case 11:
                        MultiAppFloatingActivitySwitcher.sInstance.closeAllPage();
                        break;
                }
            } else {
                MultiAppFloatingActivitySwitcher.sInstance.hideBehindPages();
            }
            return bundle2;
        }

        public void resetAppCompatActivity(AppCompatActivity appCompatActivity) {
            this.mActivityIdentity = appCompatActivity.getActivityIdentity();
            this.mActivityTaskId = appCompatActivity.getTaskId();
        }
    }

    private MultiAppFloatingActivitySwitcher() {
    }

    private void bindService(Context context, Intent intent) {
        Intent intent2 = new Intent();
        String stringExtra = intent.getStringExtra(SERVICE_PKG);
        if (isPackageAllowed(stringExtra)) {
            intent2.setPackage(stringExtra);
            String stringExtra2 = intent.getStringExtra(SERVICE_PATH);
            if (TextUtils.isEmpty(stringExtra2)) {
                return;
            }
            intent2.setComponent(new ComponentName(stringExtra, stringExtra2));
            context.getApplicationContext().bindService(intent2, this.mServiceConnection, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkRegister() {
        for (int i2 = 0; i2 < this.mActivityCache.size(); i2++) {
            for (ActivitySpec activitySpec : this.mActivityCache.valueAt(i2)) {
                if (!activitySpec.register) {
                    invokeRegister(activitySpec);
                    checkBg(activitySpec.taskId, activitySpec.identity);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAllActivity() {
        if (isCalled(this.mCloseAllActivityTime)) {
            return;
        }
        this.mCloseAllActivityTime = System.currentTimeMillis();
        for (int i2 = 0; i2 < this.mActivityCache.size(); i2++) {
            ArrayList<ActivitySpec> arrayListValueAt = this.mActivityCache.valueAt(i2);
            for (int size = arrayListValueAt.size() - 1; size >= 0; size--) {
                AppCompatActivity appCompatActivity = arrayListValueAt.get(size).activity;
                int i3 = arrayListValueAt.get(size).index;
                int servicePageCount = getServicePageCount(arrayListValueAt.get(size).taskId);
                if (appCompatActivity != null && i3 != servicePageCount - 1) {
                    appCompatActivity.realFinish();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAllPage() {
        if (isCalled(this.mCloseAllActivityTime)) {
            return;
        }
        this.mCloseAllActivityTime = System.currentTimeMillis();
        for (int i2 = 0; i2 < this.mActivityCache.size(); i2++) {
            ArrayList<ActivitySpec> arrayListValueAt = this.mActivityCache.valueAt(i2);
            for (int size = arrayListValueAt.size() - 1; size >= 0; size--) {
                AppCompatActivity appCompatActivity = arrayListValueAt.get(size).activity;
                int i3 = arrayListValueAt.get(size).index;
                int servicePageCount = getServicePageCount(arrayListValueAt.get(size).taskId);
                if (appCompatActivity != null && i3 != servicePageCount - 1) {
                    appCompatActivity.realFinish();
                }
            }
        }
    }

    public static void configureFloatingService(Intent intent, String str) {
        configureFloatingService(intent, str, (String) null);
    }

    @Nullable
    private ActivitySpec getActivitySpec(int i2, String str) {
        ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(i2);
        if (arrayList == null) {
            return null;
        }
        for (ActivitySpec activitySpec : arrayList) {
            if (TextUtils.equals(activitySpec.identity, str)) {
                return activitySpec;
            }
        }
        return null;
    }

    public static MultiAppFloatingActivitySwitcher getInstance() {
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideBehindPages() {
        final AppCompatActivity appCompatActivity;
        if (isCalled(this.mOnDragStartTime)) {
            return;
        }
        this.mOnDragStartTime = System.currentTimeMillis();
        for (int i2 = 0; i2 < this.mActivityCache.size(); i2++) {
            for (ActivitySpec activitySpec : this.mActivityCache.valueAt(i2)) {
                if (!activitySpec.resumed && (appCompatActivity = activitySpec.activity) != null) {
                    appCompatActivity.runOnUiThread(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.multiapp.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            appCompatActivity.hideFloatingBrightPanel();
                        }
                    });
                }
            }
        }
    }

    private void hideTopBgs(int i2) {
        ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(i2);
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                int i4 = arrayList.get(i3).index;
                AppCompatActivity appCompatActivity = arrayList.get(i3).activity;
                if (appCompatActivity != null && i4 != 0) {
                    appCompatActivity.hideFloatingDimBackground();
                }
            }
        }
    }

    private void init(AppCompatActivity appCompatActivity, Intent intent, Bundle bundle) {
        if (FloatingHelperFactory.getFloatingHelperType(appCompatActivity) == 0) {
            return;
        }
        stashActivity(appCompatActivity, intent, bundle);
        registerActivityToService(appCompatActivity);
        appCompatActivity.getLifecycle().addObserver(new MultiAppFloatingLifecycleObserver(appCompatActivity));
        appCompatActivity.setEnableSwipToDismiss(this.mEnableDragToDismiss);
        appCompatActivity.setOnFloatingCallback(new DefineOnFloatingActivityCallback(appCompatActivity));
    }

    @Deprecated
    public static void install(AppCompatActivity appCompatActivity, Intent intent) {
        install(appCompatActivity, intent, null);
    }

    private void invokeRegister(@Nullable ActivitySpec activitySpec) {
        IFloatingService iFloatingService;
        if (activitySpec == null || (iFloatingService = this.mIFloatingService) == null) {
            return;
        }
        try {
            ServiceNotify serviceNotify = activitySpec.serviceNotify;
            iFloatingService.registerServiceNotify(serviceNotify, getIdentity(serviceNotify, activitySpec.taskId));
            updateServerActivityIndex(getIdentity(activitySpec.serviceNotify, activitySpec.taskId), activitySpec.index);
            if (!activitySpec.register) {
                activitySpec.register = true;
                activitySpec.serviceNotifyIndex = activitySpec.index;
            }
            Iterator<Runnable> it = activitySpec.pendingTasks.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            activitySpec.pendingTasks.clear();
        } catch (RemoteException e2) {
            Log.w(TAG, "catch register service notify exception", e2);
        }
    }

    private boolean isActivityStashed(AppCompatActivity appCompatActivity) {
        return (appCompatActivity == null || getActivitySpec(appCompatActivity.getTaskId(), appCompatActivity.getActivityIdentity()) == null) ? false : true;
    }

    private boolean isCalled(long j2) {
        return System.currentTimeMillis() - j2 <= INVOKE_THRESHOLD;
    }

    public static boolean isFromMultiApp(Intent intent) {
        return (TextUtils.isEmpty(intent.getStringExtra(SERVICE_PKG)) || TextUtils.isEmpty(intent.getStringExtra(SERVICE_PATH))) ? false : true;
    }

    private boolean isPackageAllowed(String str) {
        for (String str2 : mAllowedPackageList) {
            if (str2.equals(str)) {
                return true;
            }
        }
        Log.w(TAG, "Package is not allowed:" + str + ". Please contact the MIUIX developer!");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle notifyService(int i2) {
        return notifyService(i2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDragEnd() {
        final AppCompatActivity appCompatActivity;
        if (isCalled(this.mOnDragEndTime)) {
            return;
        }
        this.mOnDragEndTime = System.currentTimeMillis();
        for (int i2 = 0; i2 < this.mActivityCache.size(); i2++) {
            for (ActivitySpec activitySpec : this.mActivityCache.valueAt(i2)) {
                if (!activitySpec.resumed && (appCompatActivity = activitySpec.activity) != null) {
                    appCompatActivity.runOnUiThread(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.multiapp.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            appCompatActivity.showFloatingBrightPanel();
                        }
                    });
                }
            }
        }
    }

    public static void onSaveInstanceState(int i2, String str, Bundle bundle) {
        ActivitySpec activitySpec;
        MultiAppFloatingActivitySwitcher multiAppFloatingActivitySwitcher = getInstance();
        if (multiAppFloatingActivitySwitcher == null || (activitySpec = multiAppFloatingActivitySwitcher.getActivitySpec(i2, str)) == null) {
            return;
        }
        bundle.putParcelable(SAVED_INSTANCE_KEY, activitySpec);
    }

    private void registerActivityToService(AppCompatActivity appCompatActivity) {
        ActivitySpec activitySpec = getActivitySpec(appCompatActivity.getTaskId(), appCompatActivity.getActivityIdentity());
        if (activitySpec != null && activitySpec.serviceNotify == null) {
            activitySpec.serviceNotify = new ServiceNotify(appCompatActivity);
        } else if (activitySpec != null) {
            activitySpec.serviceNotify.resetAppCompatActivity(appCompatActivity);
        }
        invokeRegister(activitySpec);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIFloatingService(IFloatingService iFloatingService) {
        this.mIFloatingService = iFloatingService;
        this.mServiceConnected = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldAllFloatingClose(int i2, int i3) {
        return !(i2 == 4 || i2 == 3) || getServicePageCount(i3) <= 1;
    }

    private void stashActivity(AppCompatActivity appCompatActivity, Intent intent, Bundle bundle) {
        if (!isActivityStashed(appCompatActivity)) {
            ActivitySpec activitySpec = bundle != null ? (ActivitySpec) bundle.getParcelable(SAVED_INSTANCE_KEY) : null;
            int i2 = 0;
            if (activitySpec == null) {
                activitySpec = new ActivitySpec(true);
                if (intent == null) {
                    intent = appCompatActivity.getIntent();
                }
                activitySpec.index = intent.getIntExtra(SERVICE_PAGE_INDEX, 0);
            }
            activitySpec.activity = appCompatActivity;
            activitySpec.taskId = appCompatActivity.getTaskId();
            activitySpec.identity = appCompatActivity.getActivityIdentity();
            ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(activitySpec.taskId);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.mActivityCache.put(activitySpec.taskId, arrayList);
            }
            int i3 = activitySpec.index;
            int size = arrayList.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                if (i3 > arrayList.get(size).index) {
                    i2 = size + 1;
                    break;
                }
                size--;
            }
            arrayList.add(i2, activitySpec);
            FloatingAnimHelper.markedPageIndex(appCompatActivity, activitySpec.index);
        }
        hideTopBgs(appCompatActivity.getTaskId());
    }

    private void unRegisterActivityFromService(int i2, String str) {
        if (this.mIFloatingService != null) {
            try {
                ActivitySpec activitySpec = getActivitySpec(i2, str);
                if (activitySpec != null) {
                    IFloatingService iFloatingService = this.mIFloatingService;
                    ServiceNotify serviceNotify = activitySpec.serviceNotify;
                    iFloatingService.unregisterServiceNotify(serviceNotify, String.valueOf(serviceNotify.hashCode()));
                }
            } catch (RemoteException e2) {
                Log.w(TAG, "catch unregister service notify exception", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterAll() {
        for (int i2 = 0; i2 < this.mActivityCache.size(); i2++) {
            for (ActivitySpec activitySpec : this.mActivityCache.valueAt(i2)) {
                unRegisterActivityFromService(activitySpec.taskId, activitySpec.identity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindService(Context context) {
        if (this.mServiceConnected) {
            this.mServiceConnected = false;
            context.getApplicationContext().unbindService(this.mServiceConnection);
        }
    }

    private void updateServerActivityIndex(@NonNull String str, int i2) {
        IFloatingService iFloatingService = this.mIFloatingService;
        if (iFloatingService != null) {
            try {
                iFloatingService.upDateRemoteActivityInfo(str, i2);
            } catch (RemoteException e2) {
                Log.w(TAG, "catch updateServerActivityIndex service notify exception", e2);
            }
        }
    }

    public void checkBg(int i2, String str) {
        ActivitySpec activitySpec;
        AppCompatActivity appCompatActivity;
        ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(i2);
        if (((arrayList == null || arrayList.size() <= 1) && getServicePageCount(i2) <= 1) || (activitySpec = getActivitySpec(i2, str)) == null || activitySpec.serviceNotifyIndex <= 0 || (appCompatActivity = activitySpec.activity) == null) {
            return;
        }
        appCompatActivity.hideFloatingDimBackground();
    }

    public void clear() {
        this.mActivityCache.clear();
        this.mLastActivityPanel = null;
    }

    public void clearActivitySpecTask(int i2, String str) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec != null) {
            activitySpec.pendingTasks.clear();
        }
    }

    public void destroy() {
        if (this.mActivityCache.size() == 0) {
            sInstance = null;
        }
    }

    public AppCompatActivity getActivity(int i2, String str) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec != null) {
            return activitySpec.activity;
        }
        return null;
    }

    public int getCurrentPageCount(int i2) {
        ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(i2);
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public String getIdentity(Object obj, int i2) {
        return obj.hashCode() + MethodCodeHelper.IDENTITY_INFO_SEPARATOR + i2;
    }

    public View getLastActivityPanel() {
        WeakReference<View> weakReference = this.mLastActivityPanel;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public int getServicePageCount(int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(MethodCodeHelper.KEY_TASK_ID, i2);
        Bundle bundleNotifyService = notifyService(6, bundle);
        int i3 = bundleNotifyService != null ? bundleNotifyService.getInt(String.valueOf(6)) : 0;
        ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(i2);
        if (arrayList != null) {
            Iterator<ActivitySpec> it = arrayList.iterator();
            while (it.hasNext()) {
                int i4 = it.next().index;
                if (i4 + 1 > i3) {
                    i3 = i4 + 1;
                }
            }
        }
        return i3;
    }

    public boolean isAboveActivityFinishing(int i2, String str) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MethodCodeHelper.KEY_REQUEST_IDENTITY, String.valueOf(activitySpec.serviceNotify.hashCode()));
        bundle.putInt(MethodCodeHelper.KEY_TASK_ID, i2);
        Bundle bundleNotifyService = notifyService(9, bundle);
        return bundleNotifyService != null && bundleNotifyService.getBoolean(MethodCodeHelper.METHOD_RESULT_CHECK_FINISHNING);
    }

    public boolean isActivityOpenEnterAnimExecuted(int i2, String str) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec != null) {
            return activitySpec.isOpenEnterAnimExecuted;
        }
        return false;
    }

    public boolean isServiceAvailable() {
        return this.mIFloatingService != null;
    }

    public void markActivityOpenEnterAnimExecutedInternal(int i2, String str) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec != null) {
            activitySpec.isOpenEnterAnimExecuted = true;
        }
    }

    public void notifyPreviousActivitySlide(int i2, String str) {
        final ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec == null) {
            return;
        }
        Runnable runnable = new Runnable() { // from class: miuix.appcompat.app.floatingactivity.multiapp.MultiAppFloatingActivitySwitcher.2
            @Override // java.lang.Runnable
            public void run() {
                String strValueOf = String.valueOf(activitySpec.serviceNotify.hashCode());
                Bundle bundle = new Bundle();
                bundle.putInt(MethodCodeHelper.KEY_TASK_ID, activitySpec.taskId);
                bundle.putString(MethodCodeHelper.METHOD_EXECUTE_SLIDE, strValueOf);
                MultiAppFloatingActivitySwitcher.this.notifyService(10, bundle);
            }
        };
        if (isServiceAvailable()) {
            runnable.run();
        } else {
            activitySpec.pendingTasks.add(runnable);
        }
    }

    public void postEnterAnimationTask(int i2, String str, Runnable runnable) {
        if (isActivityOpenEnterAnimExecuted(i2, str)) {
            return;
        }
        if (getCurrentPageCount(i2) > 1 || getServicePageCount(i2) > 1) {
            markActivityOpenEnterAnimExecutedInternal(i2, str);
        }
        if (isServiceAvailable()) {
            runnable.run();
            return;
        }
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec != null) {
            activitySpec.pendingTasks.add(runnable);
        }
    }

    public void remove(int i2, String str) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec == null || activitySpec.activity == null) {
            return;
        }
        unRegisterActivityFromService(i2, str);
        ArrayList<ActivitySpec> arrayList = this.mActivityCache.get(i2);
        if (arrayList != null) {
            arrayList.remove(activitySpec);
            if (arrayList.isEmpty()) {
                this.mActivityCache.remove(i2);
            }
        }
        if (this.mActivityCache.size() == 0) {
            unbindService(activitySpec.activity);
            clear();
        }
    }

    public void saveBitmap(Bitmap bitmap, int i2, String str) throws Throwable {
        ActivitySpec activitySpec;
        if (bitmap == null || (activitySpec = getActivitySpec(i2, str)) == null) {
            return;
        }
        int byteCount = bitmap.getByteCount();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteCount);
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        MemoryFileUtil.sendToFdServer(this.mIFloatingService, byteBufferAllocate.array(), byteCount, bitmap.getWidth(), bitmap.getHeight(), String.valueOf(activitySpec.serviceNotify.hashCode()), i2);
    }

    public void setLastActivityPanel(View view) {
        this.mLastActivityPanel = new WeakReference<>(view);
    }

    public void updateResumeState(int i2, String str, boolean z2) {
        ActivitySpec activitySpec = getActivitySpec(i2, str);
        if (activitySpec != null) {
            activitySpec.resumed = z2;
        }
    }

    public static void configureFloatingService(Intent intent, String str, String str2) {
        intent.putExtra(SERVICE_PKG, str);
        if (TextUtils.isEmpty(str2)) {
            str2 = FloatingService.class.getName();
        }
        intent.putExtra(SERVICE_PATH, str2);
        if (intent.getIntExtra(SERVICE_PAGE_INDEX, -1) < 0) {
            intent.putExtra(SERVICE_FIRST_FLOATING, true);
            intent.putExtra(SERVICE_PAGE_INDEX, 0);
        }
    }

    public static void install(AppCompatActivity appCompatActivity, Intent intent, Bundle bundle) {
        if (!isFromMultiApp(intent)) {
            FloatingActivitySwitcher.install(appCompatActivity, bundle);
            return;
        }
        if (sInstance == null) {
            sInstance = new MultiAppFloatingActivitySwitcher();
            if (mAllowedPackageList == null) {
                mAllowedPackageList = appCompatActivity.getResources().getStringArray(R.array.multi_floating_package_allow_list);
            }
            sInstance.bindService(appCompatActivity, intent);
        }
        sInstance.init(appCompatActivity, intent, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle notifyService(int i2, Bundle bundle) {
        IFloatingService iFloatingService = this.mIFloatingService;
        if (iFloatingService == null) {
            Log.d(TAG, "ifloatingservice is null");
            return null;
        }
        try {
            return iFloatingService.callServiceMethod(i2, bundle);
        } catch (RemoteException e2) {
            Log.w(TAG, "catch call service method exception", e2);
            return null;
        }
    }

    public static class ActivitySpec implements Parcelable {
        public static final Parcelable.Creator<ActivitySpec> CREATOR = new Parcelable.Creator<ActivitySpec>() { // from class: miuix.appcompat.app.floatingactivity.multiapp.MultiAppFloatingActivitySwitcher.ActivitySpec.1
            @Override // android.os.Parcelable.Creator
            public ActivitySpec createFromParcel(Parcel parcel) {
                return new ActivitySpec(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public ActivitySpec[] newArray(int i2) {
                return new ActivitySpec[i2];
            }
        };
        AppCompatActivity activity;
        String identity;
        int index;
        boolean isOpenEnterAnimExecuted;
        List<Runnable> pendingTasks;
        boolean register;
        boolean resumed;
        ServiceNotify serviceNotify;
        int serviceNotifyIndex;
        int taskId;

        public ActivitySpec(boolean z2) {
            this.index = -1;
            this.register = false;
            this.isOpenEnterAnimExecuted = false;
            this.resumed = z2;
            this.pendingTasks = new LinkedList();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NonNull
        public String toString() {
            return "{ index : " + this.index + "; taskId : " + this.taskId + "; taskId : " + this.taskId + "; identity : " + this.identity + "; serviceNotifyIndex : " + this.serviceNotifyIndex + "; register : " + this.register + "; isOpenEnterAnimExecuted : " + this.isOpenEnterAnimExecuted + "; }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.index);
            parcel.writeInt(this.taskId);
            parcel.writeString(this.identity);
            parcel.writeByte(this.resumed ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.serviceNotifyIndex);
            parcel.writeByte(this.register ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.isOpenEnterAnimExecuted ? (byte) 1 : (byte) 0);
        }

        public ActivitySpec(Parcel parcel) {
            this.index = -1;
            this.register = false;
            this.isOpenEnterAnimExecuted = false;
            this.index = parcel.readInt();
            this.taskId = parcel.readInt();
            this.identity = parcel.readString();
            this.resumed = parcel.readByte() != 0;
            this.serviceNotifyIndex = parcel.readInt();
            this.register = parcel.readByte() != 0;
            this.isOpenEnterAnimExecuted = parcel.readByte() != 0;
            this.pendingTasks = new LinkedList();
        }
    }

    @Deprecated
    public static void configureFloatingService(Intent intent, Intent intent2) {
        MultiAppFloatingActivitySwitcher multiAppFloatingActivitySwitcher = sInstance;
        int iKeyAt = 0;
        if (multiAppFloatingActivitySwitcher != null && multiAppFloatingActivitySwitcher.mActivityCache.size() > 0) {
            iKeyAt = sInstance.mActivityCache.keyAt(0);
        }
        configureFloatingService(intent, intent2, iKeyAt);
    }

    public static void configureFloatingService(Intent intent, AppCompatActivity appCompatActivity) {
        configureFloatingService(intent, appCompatActivity.getIntent(), appCompatActivity.getTaskId());
    }

    private static void configureFloatingService(Intent intent, Intent intent2, int i2) {
        intent.putExtra(SERVICE_PKG, intent2.getStringExtra(SERVICE_PKG));
        intent.putExtra(SERVICE_PATH, intent2.getStringExtra(SERVICE_PATH));
        if (!intent.getBooleanExtra(SERVICE_FIRST_FLOATING, false)) {
            int intExtra = intent2.getIntExtra(SERVICE_PAGE_INDEX, -1);
            if (intExtra < 0) {
                Log.w(TAG, "the value of SERVICE_PAGE_INDEX is invalid  , index = " + intExtra + " , please check it");
            }
            intent.putExtra(SERVICE_PAGE_INDEX, intExtra + 1);
        } else {
            intent.putExtra(SERVICE_PAGE_INDEX, 0);
        }
        MultiAppFloatingActivitySwitcher multiAppFloatingActivitySwitcher = getInstance();
        if (multiAppFloatingActivitySwitcher != null) {
            intent.putExtra(SERVICE_ORIGINAL_PAGE_INDEX, multiAppFloatingActivitySwitcher.getServicePageCount(i2));
        }
    }
}
