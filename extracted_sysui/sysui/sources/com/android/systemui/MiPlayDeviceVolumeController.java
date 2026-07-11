package com.android.systemui;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.ui.TouchBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDeviceVolumeController implements VolumeController {
    private static final int MIPLAY_DEVICE_VOLUME_MIN = 0;
    private final int INTERVAL;
    private final long NOTIFY_DALAY;
    private boolean acceptNotify;
    private m0.i device;
    private long lastSet;
    private final Observer<Integer> mDeviceObserver;
    private final MutableLiveData<Integer> mVolumeLiveData;
    private final Handler mainHandler;
    private final Runnable notifyRunnable;
    private Integer pendingVolume;
    public static final Companion Companion = new Companion(null);
    private static final int MIPLAY_DEVICE_VOLUME_MAX = 100;
    private static final int MIPLAY_DEVICE_VOLUME_KEY_ADJUST = 6;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getMIPLAY_DEVICE_VOLUME_KEY_ADJUST() {
            return MiPlayDeviceVolumeController.MIPLAY_DEVICE_VOLUME_KEY_ADJUST;
        }

        public final int getMIPLAY_DEVICE_VOLUME_MAX() {
            return MiPlayDeviceVolumeController.MIPLAY_DEVICE_VOLUME_MAX;
        }

        public final int getMIPLAY_DEVICE_VOLUME_MIN() {
            return MiPlayDeviceVolumeController.MIPLAY_DEVICE_VOLUME_MIN;
        }

        private Companion() {
        }
    }

    public MiPlayDeviceVolumeController(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        this.device = device;
        Observer<Integer> observer = new Observer() { // from class: com.android.systemui.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MiPlayDeviceVolumeController.mDeviceObserver$lambda$0(this.f1459a, ((Integer) obj).intValue());
            }
        };
        this.mDeviceObserver = observer;
        this.mVolumeLiveData = new MutableLiveData<>();
        MutableLiveData<Integer> liveData = MiPlayDeviceVolumeCache.INSTANCE.getLiveData(this.device);
        if (liveData != null) {
            liveData.observeForever(observer);
        }
        this.acceptNotify = true;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.notifyRunnable = new Runnable() { // from class: com.android.systemui.n
            @Override // java.lang.Runnable
            public final void run() {
                MiPlayDeviceVolumeController.notifyRunnable$lambda$1(this.f1518a);
            }
        };
        this.NOTIFY_DALAY = TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS;
        this.INTERVAL = 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mDeviceObserver$lambda$0(MiPlayDeviceVolumeController this$0, int i2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.acceptNotify) {
            this$0.mVolumeLiveData.setValue(Integer.valueOf(i2));
        }
    }

    private final boolean needUseCallbackVolume(int i2) {
        return i2 <= 5 || i2 >= 95;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyRunnable$lambda$1(MiPlayDeviceVolumeController this$0) {
        Integer value;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.acceptNotify = true;
        if (!this$0.device.k().isAudioSharing()) {
            MutableLiveData<Integer> mutableLiveData = this$0.mVolumeLiveData;
            MutableLiveData<Integer> liveData = MiPlayDeviceVolumeCache.INSTANCE.getLiveData(this$0.device);
            mutableLiveData.setValue(liveData != null ? liveData.getValue() : null);
            return;
        }
        MiPlayVolumeBar miPlayVolumeBar = MiPlayDeviceVolumeBar.INSTANCE.getMiPlayDeviceVolumeBarMap().get(this$0.device);
        MiPlayDeviceVolumeCache miPlayDeviceVolumeCache = MiPlayDeviceVolumeCache.INSTANCE;
        MutableLiveData<Integer> liveData2 = miPlayDeviceVolumeCache.getLiveData(this$0.device);
        if (liveData2 == null || (value = liveData2.getValue()) == null) {
            value = 0;
        }
        int iIntValue = value.intValue();
        if (miPlayVolumeBar == null || this$0.needUseCallbackVolume(iIntValue) || Math.abs(this$0.progressToVolume(miPlayVolumeBar.getProgress()) - iIntValue) >= 10) {
            MutableLiveData<Integer> mutableLiveData2 = this$0.mVolumeLiveData;
            MutableLiveData<Integer> liveData3 = miPlayDeviceVolumeCache.getLiveData(this$0.device);
            mutableLiveData2.setValue(liveData3 != null ? liveData3.getValue() : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void safeSetVolume$lambda$3(MiPlayDeviceVolumeController this$0, m0.i this_safeSetVolume) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(this_safeSetVolume, "$this_safeSetVolume");
        Integer num = this$0.pendingVolume;
        if (num != null) {
            int iIntValue = num.intValue();
            this$0.lastSet = System.currentTimeMillis();
            this_safeSetVolume.x(iIntValue, 0);
            this$0.pendingVolume = null;
        }
    }

    @Override // com.android.systemui.VolumeController
    public void doAdjustVolume(boolean z2) {
    }

    @Override // com.android.systemui.VolumeController
    public void doSetVolume(int i2) {
        this.acceptNotify = false;
        this.mainHandler.removeCallbacks(this.notifyRunnable);
        this.mainHandler.postDelayed(this.notifyRunnable, this.NOTIFY_DALAY);
        safeSetVolume(this.device, i2);
    }

    public final boolean getAcceptNotify() {
        return this.acceptNotify;
    }

    public final m0.i getDevice() {
        return this.device;
    }

    public final int getINTERVAL() {
        return this.INTERVAL;
    }

    public final long getLastSet() {
        return this.lastSet;
    }

    public final Observer<Integer> getMDeviceObserver() {
        return this.mDeviceObserver;
    }

    public final MutableLiveData<Integer> getMVolumeLiveData() {
        return this.mVolumeLiveData;
    }

    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    public final long getNOTIFY_DALAY() {
        return this.NOTIFY_DALAY;
    }

    public final Runnable getNotifyRunnable() {
        return this.notifyRunnable;
    }

    public final Integer getPendingVolume() {
        return this.pendingVolume;
    }

    @Override // com.android.systemui.VolumeController
    public Integer getVolume() {
        MutableLiveData<Integer> liveData = MiPlayDeviceVolumeCache.INSTANCE.getLiveData(this.device);
        if (liveData != null) {
            return liveData.getValue();
        }
        return null;
    }

    @Override // com.android.systemui.VolumeController
    public MutableLiveData<Integer> getVolumeLiveData() {
        return this.mVolumeLiveData;
    }

    @Override // com.android.systemui.VolumeController
    public int progressToVolume(int i2) {
        return MiPlayDetailViewModelKt.progressToVolume(i2, MIPLAY_DEVICE_VOLUME_MIN, MIPLAY_DEVICE_VOLUME_MAX);
    }

    @Override // com.android.systemui.VolumeController
    public void release() {
        MutableLiveData<Integer> liveData = MiPlayDeviceVolumeCache.INSTANCE.getLiveData(this.device);
        if (liveData != null) {
            liveData.removeObserver(this.mDeviceObserver);
        }
    }

    public final void safeSetVolume(final m0.i iVar, int i2) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        long jCurrentTimeMillis = System.currentTimeMillis() - this.lastSet;
        int i3 = this.INTERVAL;
        if (jCurrentTimeMillis >= i3) {
            this.pendingVolume = null;
            this.lastSet = System.currentTimeMillis();
            iVar.x(i2, 0);
        } else {
            this.pendingVolume = Integer.valueOf(i2);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.android.systemui.o
                @Override // java.lang.Runnable
                public final void run() {
                    MiPlayDeviceVolumeController.safeSetVolume$lambda$3(this.f1519a, iVar);
                }
            }, ((long) i3) - jCurrentTimeMillis);
        }
    }

    public final void setAcceptNotify(boolean z2) {
        this.acceptNotify = z2;
    }

    public final void setDevice(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<set-?>");
        this.device = iVar;
    }

    public final void setLastSet(long j2) {
        this.lastSet = j2;
    }

    public final void setPendingVolume(Integer num) {
        this.pendingVolume = num;
    }

    @Override // com.android.systemui.VolumeController
    public int volumeToProgress(int i2) {
        return MiPlayDetailViewModelKt.volumeToProgress(i2, MIPLAY_DEVICE_VOLUME_MIN, MIPLAY_DEVICE_VOLUME_MAX);
    }
}
