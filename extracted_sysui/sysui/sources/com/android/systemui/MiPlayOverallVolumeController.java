package com.android.systemui;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.android.systemui.MiPlayDeviceVolumeController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function2;
import miui.systemui.devicecontrols.ui.TouchBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayOverallVolumeController implements VolumeController {
    public static final MiPlayOverallVolumeController INSTANCE = new MiPlayOverallVolumeController();
    private static final String TAG = "MiPlayOverallVolumeController";
    private static final HashMap<m0.i, MiPlayDeviceVolumeController> deviceControllers = new HashMap<>();
    private static final MutableLiveData<Integer> mVolumeLiveData = new MutableLiveData<>();
    private static final Observer<Integer> volumeLiveDataObserver = new Observer() { // from class: com.android.systemui.q
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            MiPlayOverallVolumeController.volumeLiveDataObserver$lambda$0(((Integer) obj).intValue());
        }
    };
    private static boolean acceptNotify = true;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final Runnable notifyRunnable = new Runnable() { // from class: com.android.systemui.r
        @Override // java.lang.Runnable
        public final void run() {
            MiPlayOverallVolumeController.notifyRunnable$lambda$1();
        }
    };
    private static final long NOTIFY_DALAY = TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS;

    /* JADX INFO: renamed from: com.android.systemui.MiPlayOverallVolumeController$doSetVolume$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function2 {
        final /* synthetic */ int $volume;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i2) {
            super(2);
            this.$volume = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((m0.i) obj, (MiPlayDeviceVolumeController) obj2);
            return H0.s.f314a;
        }

        public final void invoke(m0.i device, MiPlayDeviceVolumeController controller) {
            kotlin.jvm.internal.n.g(device, "device");
            kotlin.jvm.internal.n.g(controller, "controller");
            if (device.k().isAudioSharing()) {
                return;
            }
            Float fValueOf = MiPlayDeviceVolumeCache.INSTANCE.getDeviceVisualMaxVolumeMap().get(device);
            if (fValueOf == null) {
                fValueOf = Float.valueOf(1.0f);
            }
            int iFloatValue = (int) (this.$volume * fValueOf.floatValue());
            MutableLiveData<Integer> volumeLiveData = controller.getVolumeLiveData();
            if (volumeLiveData != null) {
                volumeLiveData.setValue(Integer.valueOf(iFloatValue));
            }
            controller.doSetVolume(iFloatValue);
        }
    }

    private MiPlayOverallVolumeController() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doSetVolume$lambda$6(Function2 tmp0, Object obj, Object obj2) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke(obj, obj2);
    }

    private final boolean needUseCallbackVolume(int i2) {
        return i2 <= 5 || i2 >= 95;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyRunnable$lambda$1() {
        acceptNotify = true;
        MiPlayVolumeBar totalVolumeBar = MiPlayDeviceVolumeBar.INSTANCE.getTotalVolumeBar();
        if (totalVolumeBar != null) {
            MiPlayOverallVolumeController miPlayOverallVolumeController = INSTANCE;
            Integer volume = miPlayOverallVolumeController.getVolume();
            kotlin.jvm.internal.n.d(volume);
            if (!miPlayOverallVolumeController.needUseCallbackVolume(volume.intValue())) {
                int iProgressToVolume = miPlayOverallVolumeController.progressToVolume(totalVolumeBar.getProgress());
                Integer volume2 = miPlayOverallVolumeController.getVolume();
                kotlin.jvm.internal.n.d(volume2);
                if (Math.abs(iProgressToVolume - volume2.intValue()) < 10) {
                    return;
                }
            }
        }
        MutableLiveData<Integer> mutableLiveData = mVolumeLiveData;
        Integer volume3 = INSTANCE.getVolume();
        kotlin.jvm.internal.n.d(volume3);
        mutableLiveData.setValue(volume3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void volumeLiveDataObserver$lambda$0(int i2) {
        if (acceptNotify) {
            mVolumeLiveData.setValue(INSTANCE.getVolume());
        }
    }

    @Override // com.android.systemui.VolumeController
    public void doAdjustVolume(boolean z2) {
        Integer volume = getVolume();
        if (volume != null) {
            int iIntValue = volume.intValue();
            acceptNotify = false;
            Handler handler = mainHandler;
            Runnable runnable = notifyRunnable;
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, NOTIFY_DALAY);
            int miplay_device_volume_key_adjust = iIntValue + (z2 ? MiPlayDeviceVolumeController.Companion.getMIPLAY_DEVICE_VOLUME_KEY_ADJUST() : -MiPlayDeviceVolumeController.Companion.getMIPLAY_DEVICE_VOLUME_KEY_ADJUST());
            MiPlayDeviceVolumeController.Companion companion = MiPlayDeviceVolumeController.Companion;
            int iMin = Math.min(Math.max(miplay_device_volume_key_adjust, companion.getMIPLAY_DEVICE_VOLUME_MIN()), companion.getMIPLAY_DEVICE_VOLUME_MAX());
            MiPlayDetailViewModel.INSTANCE.updateOverAllVolumeProgress(volumeToProgress(iMin));
            doSetVolume(iMin);
        }
    }

    @Override // com.android.systemui.VolumeController
    public void doSetVolume(int i2) {
        acceptNotify = false;
        Handler handler = mainHandler;
        Runnable runnable = notifyRunnable;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, NOTIFY_DALAY);
        HashMap<m0.i, MiPlayDeviceVolumeController> map = deviceControllers;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(i2);
        map.forEach(new BiConsumer() { // from class: com.android.systemui.s
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MiPlayOverallVolumeController.doSetVolume$lambda$6(anonymousClass1, obj, obj2);
            }
        });
    }

    public final boolean getAcceptNotify() {
        return acceptNotify;
    }

    public final HashMap<m0.i, MiPlayDeviceVolumeController> getDeviceControllers() {
        return deviceControllers;
    }

    public final Handler getMainHandler() {
        return mainHandler;
    }

    public final long getNOTIFY_DALAY() {
        return NOTIFY_DALAY;
    }

    public final Runnable getNotifyRunnable() {
        return notifyRunnable;
    }

    public final String getTAG() {
        return TAG;
    }

    @Override // com.android.systemui.VolumeController
    public Integer getVolume() {
        Object obj;
        Integer volume;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        if (miPlayDetailViewModel.isAudioShare()) {
            return Integer.valueOf(EarPhoneUtils.INSTANCE.getMainEarphoneVolume(miPlayDetailViewModel.getMAudioShareSelectedDevices()));
        }
        Collection<MiPlayDeviceVolumeController> collectionValues = deviceControllers.values();
        kotlin.jvm.internal.n.f(collectionValues, "<get-values>(...)");
        Iterator<T> it = collectionValues.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                Integer volume2 = ((MiPlayDeviceVolumeController) next).getVolume();
                int iIntValue = volume2 != null ? volume2.intValue() : 0;
                do {
                    Object next2 = it.next();
                    Integer volume3 = ((MiPlayDeviceVolumeController) next2).getVolume();
                    int iIntValue2 = volume3 != null ? volume3.intValue() : 0;
                    if (iIntValue < iIntValue2) {
                        next = next2;
                        iIntValue = iIntValue2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        MiPlayDeviceVolumeController miPlayDeviceVolumeController = (MiPlayDeviceVolumeController) obj;
        if (miPlayDeviceVolumeController == null || (volume = miPlayDeviceVolumeController.getVolume()) == null) {
            return 0;
        }
        return volume;
    }

    @Override // com.android.systemui.VolumeController
    public MutableLiveData<Integer> getVolumeLiveData() {
        return mVolumeLiveData;
    }

    public final Observer<Integer> getVolumeLiveDataObserver() {
        return volumeLiveDataObserver;
    }

    @Override // com.android.systemui.VolumeController
    public int progressToVolume(int i2) {
        MiPlayDeviceVolumeController.Companion companion = MiPlayDeviceVolumeController.Companion;
        return MiPlayDetailViewModelKt.progressToVolume(i2, companion.getMIPLAY_DEVICE_VOLUME_MIN(), companion.getMIPLAY_DEVICE_VOLUME_MAX());
    }

    @Override // com.android.systemui.VolumeController
    public void release() {
    }

    public final void setAcceptNotify(boolean z2) {
        acceptNotify = z2;
    }

    public final void updateDevices(List<? extends m0.i> devices) {
        MutableLiveData<Integer> volumeLiveData;
        MutableLiveData<Integer> volumeLiveData2;
        kotlin.jvm.internal.n.g(devices, "devices");
        ArrayList<m0.i> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<m0.i, MiPlayDeviceVolumeController> entry : deviceControllers.entrySet()) {
            if (MiPlayExtentionsKt.isForeignCastDevice(entry.getKey()) || MiPlayExtentionsKt.isForeignBlueToothDevice(entry.getKey())) {
                arrayList.add(entry.getKey());
            } else {
                int iIndexOf = devices.indexOf(entry.getKey());
                if (iIndexOf >= 0) {
                    entry.getValue().setDevice(devices.get(iIndexOf));
                } else {
                    arrayList.add(entry.getKey());
                }
            }
        }
        for (m0.i iVar : arrayList) {
            HashMap<m0.i, MiPlayDeviceVolumeController> map = deviceControllers;
            MiPlayDeviceVolumeController miPlayDeviceVolumeController = map.get(iVar);
            if (miPlayDeviceVolumeController != null && (volumeLiveData2 = miPlayDeviceVolumeController.getVolumeLiveData()) != null) {
                volumeLiveData2.removeObserver(volumeLiveDataObserver);
            }
            MiPlayDeviceVolumeController miPlayDeviceVolumeController2 = map.get(iVar);
            if (miPlayDeviceVolumeController2 != null) {
                miPlayDeviceVolumeController2.release();
            }
            map.remove(iVar);
        }
        for (m0.i iVar2 : devices) {
            HashMap<m0.i, MiPlayDeviceVolumeController> map2 = deviceControllers;
            if (!map2.keySet().contains(iVar2) || MiPlayExtentionsKt.isForeignCastDevice(iVar2) || MiPlayExtentionsKt.isForeignBlueToothDevice(iVar2)) {
                arrayList2.add(iVar2);
                map2.put(iVar2, new MiPlayDeviceVolumeController(iVar2));
                MiPlayDeviceVolumeController miPlayDeviceVolumeController3 = map2.get(iVar2);
                if (miPlayDeviceVolumeController3 != null && (volumeLiveData = miPlayDeviceVolumeController3.getVolumeLiveData()) != null) {
                    volumeLiveData.observeForever(volumeLiveDataObserver);
                }
            }
        }
        Log.d(TAG, "updateDevices(): deviceControllers.size = " + deviceControllers.size());
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            return;
        }
        mVolumeLiveData.setValue(getVolume());
    }

    @Override // com.android.systemui.VolumeController
    public int volumeToProgress(int i2) {
        MiPlayDeviceVolumeController.Companion companion = MiPlayDeviceVolumeController.Companion;
        return MiPlayDetailViewModelKt.volumeToProgress(i2, companion.getMIPLAY_DEVICE_VOLUME_MIN(), companion.getMIPLAY_DEVICE_VOLUME_MAX());
    }
}
