package com.xiaomi.cast.env;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.cast.api.DeviceInfo;
import com.xiaomi.cast.api.DeviceStatusListener;
import com.xiaomi.cast.api.ICastService;
import com.xiaomi.cast.api.IInitListener;
import com.xiaomi.cast.api.IMiCastSDK;
import com.xiaomi.cast.api.MediaMetaData;
import com.xiaomi.cast.api.ServiceStatusListener;
import com.xiaomi.cast.api.runtime.DeviceConst;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import miui.systemui.util.SmartDeviceUtils;

/* JADX INFO: loaded from: classes2.dex */
class MiCastSDKImpl implements IMiCastSDK {
    private boolean isInit;
    private Context mContext;
    private ICastService mDeviceBinder;
    private DeviceStatusListener mDeviceStatusListener;
    private boolean mIsSupport;
    private ServiceStatusListener mServiceStatusListener;
    private final String TAG = "MiCastSDKImpl";
    private final ConcurrentLinkedQueue<Runnable> mActionQueue = new ConcurrentLinkedQueue<>();
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final BroadcastReceiver mCallbackReceiver = new BroadcastReceiver() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.12
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("MiCastSDKImpl", "onReceive: action " + action);
            try {
                if (DeviceConst.ACTION_DEVICE_CHANGE.equals(action)) {
                    if (MiCastSDKImpl.this.mDeviceStatusListener != null) {
                        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(DeviceConst.KEY_DEVICE_LIST);
                        if (parcelableArrayListExtra == null) {
                            Log.e("MiCastSDKImpl", "ACTION_DEVICE_CHANGE device list is null return!");
                            return;
                        } else {
                            MiCastSDKImpl.this.mDeviceStatusListener.onDeviceStatusChanged(new ArrayList(parcelableArrayListExtra));
                            return;
                        }
                    }
                    return;
                }
                if (DeviceConst.ACTION_DEVICE_TAKE_OVER.equals(action)) {
                    if (MiCastSDKImpl.this.mDeviceStatusListener != null) {
                        MiCastSDKImpl.this.mDeviceStatusListener.onDeviceTakeOver();
                    }
                } else if (DeviceConst.ACTION_DEVICE_SESSION_STATUE_CHANGE.equals(action)) {
                    int intExtra = intent.getIntExtra(DeviceConst.KEY_DEVICE_SESSION_STATUE, -1);
                    if (MiCastSDKImpl.this.mDeviceStatusListener != null) {
                        MiCastSDKImpl.this.mDeviceStatusListener.onCastSessionStatusChange(intExtra);
                    }
                }
            } catch (RuntimeException e2) {
                e2.printStackTrace();
                Log.e("MiCastSDKImpl", "onReceive: the impl is not match!");
            }
        }
    };
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.13
        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            Log.d("MiCastSDKImpl", "onBindingDied");
            MiCastSDKImpl.this.mDeviceBinder = null;
            if (MiCastSDKImpl.this.mServiceStatusListener != null) {
                MiCastSDKImpl.this.mServiceStatusListener.onServiceStatusChange(0);
            }
            super.onBindingDied(componentName);
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            Log.d("MiCastSDKImpl", "onNullBinding");
            super.onNullBinding(componentName);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("MiCastSDKImpl", "onServiceConnected");
            if (MiCastSDKImpl.this.mServiceStatusListener != null) {
                MiCastSDKImpl.this.mServiceStatusListener.onServiceStatusChange(1);
            }
            MiCastSDKImpl.this.mDeviceBinder = ICastService.Stub.asInterface(iBinder);
            while (!MiCastSDKImpl.this.mActionQueue.isEmpty()) {
                Runnable runnable = (Runnable) MiCastSDKImpl.this.mActionQueue.poll();
                if (runnable != null) {
                    runnable.run();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("MiCastSDKImpl", "onServiceDisconnected");
            MiCastSDKImpl.this.mDeviceBinder = null;
            if (MiCastSDKImpl.this.mServiceStatusListener != null) {
                MiCastSDKImpl.this.mServiceStatusListener.onServiceStatusChange(0);
            }
        }
    };

    private void ensureServiceAlive(final Runnable runnable) {
        runOnUIThread(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.11
            @Override // java.lang.Runnable
            public void run() {
                if (MiCastSDKImpl.this.mDeviceBinder != null || MiCastSDKImpl.this.mContext == null) {
                    runnable.run();
                    return;
                }
                try {
                    MiCastSDKImpl.this.mActionQueue.add(runnable);
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(SmartDeviceUtils.MI_LINK_PACKAGE_NAME, "com.xiaomi.cast.service.MediaRouterService"));
                    MiCastSDKImpl.this.mContext.bindService(intent, MiCastSDKImpl.this.mServiceConnection, 1);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    private void runOnUIThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            this.mMainHandler.post(runnable);
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void connectDevice(final DeviceInfo deviceInfo) {
        Log.d("MiCastSDKImpl", "connectDevice");
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.8
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.connectDevice(deviceInfo);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void disConnectAll() {
        Log.d("MiCastSDKImpl", "disConnectAll");
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.10
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.disConnectAll();
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void disConnectDevice(final DeviceInfo deviceInfo) {
        Log.d("MiCastSDKImpl", "disConnectDevice: " + this.isInit);
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.9
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.disConnectDevice(deviceInfo);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void enterUI() {
        Log.d("MiCastSDKImpl", "enterUI");
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(SmartDeviceUtils.MI_LINK_PACKAGE_NAME, "com.xiaomi.cast.service.MediaRouterService"));
                        MiCastSDKImpl.this.mContext.bindService(intent, MiCastSDKImpl.this.mServiceConnection, 1);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void exitUI() {
        Log.d("MiCastSDKImpl", "exitUI");
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.stopScan();
                        Log.d("MiCastSDKImpl", "exitUI-stopScan");
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void init(Context context, IInitListener iInitListener) {
        Log.d("MiCastSDKImpl", "init");
        boolean zIsEnable = MiCastSDK.isEnable(context);
        this.mIsSupport = zIsEnable;
        if (!zIsEnable) {
            Log.d("MiCastSDKImpl", "sdk do not support");
            return;
        }
        if (this.isInit) {
            Log.d("MiCastSDKImpl", "sdk has init");
            return;
        }
        try {
            this.isInit = true;
            this.mContext = context;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DeviceConst.ACTION_DEVICE_CHANGE);
            intentFilter.addAction(DeviceConst.ACTION_DEVICE_TAKE_OVER);
            intentFilter.addAction(DeviceConst.ACTION_DEVICE_SESSION_STATUE_CHANGE);
            intentFilter.addAction(DeviceConst.ACTION_DEVICE_VOLUME_CHANGE);
            this.mContext.registerReceiver(this.mCallbackReceiver, intentFilter, 2);
        } catch (Exception e2) {
            this.isInit = false;
            Log.e("MiCastSDKImpl", "init: " + e2);
            e2.printStackTrace();
        }
        if (iInitListener != null) {
            iInitListener.onInitComplete(this.isInit);
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void setDeviceChangeListener(DeviceStatusListener deviceStatusListener) {
        Log.d("MiCastSDKImpl", "setDeviceChangeListener");
        if (this.mIsSupport) {
            this.mDeviceStatusListener = deviceStatusListener;
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void setDeviceVolume(final double d2) {
        Log.d("MiCastSDKImpl", "setDeviceVolume: " + d2);
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.7
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.setDeviceVolume(d2);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void setMediaMetaData(final MediaMetaData mediaMetaData, final String str) {
        Log.d("MiCastSDKImpl", "setMediaMetaData data " + mediaMetaData + " , packageName " + str);
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.setMediaMetaData(mediaMetaData, str);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void setPlaybackState(final int i2) {
        Log.d("MiCastSDKImpl", "setPlaybackState state " + i2);
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.setPlaybackState(i2);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void setServiceStatusListener(ServiceStatusListener serviceStatusListener) {
        Log.d("MiCastSDKImpl", "setServiceStatusListener");
        if (this.mIsSupport) {
            this.mServiceStatusListener = serviceStatusListener;
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void startScan() {
        Log.d("MiCastSDKImpl", "startScan: isInit " + this.isInit);
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    if (!MiCastSDKImpl.this.isInit || MiCastSDKImpl.this.mDeviceBinder == null) {
                        return;
                    }
                    try {
                        MiCastSDKImpl.this.mDeviceBinder.startScan();
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void stopScan() {
        Log.d("MiCastSDKImpl", "stopScan: " + this.isInit);
        if (this.mIsSupport) {
            ensureServiceAlive(new Runnable() { // from class: com.xiaomi.cast.env.MiCastSDKImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MiCastSDKImpl.this.mContext.unbindService(MiCastSDKImpl.this.mServiceConnection);
                        MiCastSDKImpl.this.mDeviceBinder = null;
                        Log.d("MiCastSDKImpl", "stopScan - unbindService called");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("MiCastSDKImpl", "sdk do not support");
        }
    }

    @Override // com.xiaomi.cast.api.IMiCastSDK
    public void unInit() {
        Log.d("MiCastSDKImpl", "unInit: ");
        if (!this.mIsSupport) {
            Log.d("MiCastSDKImpl", "sdk do not support");
            return;
        }
        try {
            this.isInit = false;
            this.mContext.unregisterReceiver(this.mCallbackReceiver);
        } catch (Exception e2) {
            Log.e("MiCastSDKImpl", "unInit: " + e2);
            e2.printStackTrace();
        }
    }
}
