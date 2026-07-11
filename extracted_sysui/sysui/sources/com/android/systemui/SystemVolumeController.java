package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.MutableLiveData;
import com.android.systemui.SystemVolumeController$mPackageInstallerReceiver$2;
import g1.AbstractC0369g;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.devicecontrols.ui.TouchBehavior;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes.dex */
public final class SystemVolumeController implements VolumeController {
    public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
    private static final String EXTRA_VOLUME_STREAM_TYPE;
    private static final String EXTRA_VOLUME_STREAM_VALUE;
    public static final SystemVolumeController INSTANCE;
    public static final String MILINK_PACKAGE = "package:com.milink.service";
    private static final long NOTIFY_DALAY;
    private static final String STREAM_DEVICES_CHANGED_ACTION;
    private static final String STREAM_MUTE_CHANGED_ACTION;
    private static final String VOLUME_CHANGED_ACTION;
    private static final H0.d mAudioManager$delegate;
    private static final H0.d mPackageInstallerReceiver$delegate;
    private static final H0.d mSystemMaxVolume$delegate;
    private static final H0.d mSystemMinVolume$delegate;
    private static final H0.d mSystemVolumeReceiver$delegate;
    private static final MutableLiveData<Integer> mVolumeLiveData;
    private static final Handler mainHandler;
    private static final Runnable notifyRunnable;
    private static boolean shouldDoSetVolume;

    static {
        SystemVolumeController systemVolumeController = new SystemVolumeController();
        INSTANCE = systemVolumeController;
        VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
        STREAM_DEVICES_CHANGED_ACTION = "android.media.STREAM_DEVICES_CHANGED_ACTION";
        STREAM_MUTE_CHANGED_ACTION = "android.media.STREAM_MUTE_CHANGED_ACTION";
        EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
        EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
        mAudioManager$delegate = H0.e.b(SystemVolumeController$mAudioManager$2.INSTANCE);
        mSystemMinVolume$delegate = H0.e.b(SystemVolumeController$mSystemMinVolume$2.INSTANCE);
        mSystemMaxVolume$delegate = H0.e.b(SystemVolumeController$mSystemMaxVolume$2.INSTANCE);
        mainHandler = new Handler(Looper.getMainLooper());
        notifyRunnable = new Runnable() { // from class: com.android.systemui.a0
            @Override // java.lang.Runnable
            public final void run() {
                SystemVolumeController.notifyRunnable$lambda$0();
            }
        };
        NOTIFY_DALAY = TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS;
        mSystemVolumeReceiver$delegate = H0.e.b(SystemVolumeController$mSystemVolumeReceiver$2.INSTANCE);
        shouldDoSetVolume = true;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Integer.valueOf(systemVolumeController.getMAudioManager().getStreamVolume(3)));
        mVolumeLiveData = mutableLiveData;
        mPackageInstallerReceiver$delegate = H0.e.b(SystemVolumeController$mPackageInstallerReceiver$2.INSTANCE);
    }

    private SystemVolumeController() {
    }

    private final SystemVolumeController$mPackageInstallerReceiver$2.AnonymousClass1 getMPackageInstallerReceiver() {
        return (SystemVolumeController$mPackageInstallerReceiver$2.AnonymousClass1) mPackageInstallerReceiver$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyRunnable$lambda$0() {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new SystemVolumeController$notifyRunnable$1$1(null), 3, null);
    }

    @Override // com.android.systemui.VolumeController
    public void doAdjustVolume(boolean z2) {
        getMAudioManager().adjustStreamVolume(3, z2 ? 1 : -1, 0);
    }

    @Override // com.android.systemui.VolumeController
    public void doSetVolume(int i2) {
        if (shouldDoSetVolume) {
            getMAudioManager().setStreamVolume(3, i2, 0);
            Handler handler = mainHandler;
            Runnable runnable = notifyRunnable;
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, NOTIFY_DALAY);
        }
    }

    public final String getEXTRA_VOLUME_STREAM_TYPE() {
        return EXTRA_VOLUME_STREAM_TYPE;
    }

    public final String getEXTRA_VOLUME_STREAM_VALUE() {
        return EXTRA_VOLUME_STREAM_VALUE;
    }

    public final AudioManager getMAudioManager() {
        return (AudioManager) mAudioManager$delegate.getValue();
    }

    public final int getMSystemMaxVolume() {
        return ((Number) mSystemMaxVolume$delegate.getValue()).intValue();
    }

    public final int getMSystemMinVolume() {
        return ((Number) mSystemMinVolume$delegate.getValue()).intValue();
    }

    public final BroadcastReceiver getMSystemVolumeReceiver() {
        return (BroadcastReceiver) mSystemVolumeReceiver$delegate.getValue();
    }

    public final MutableLiveData<Integer> getMVolumeLiveData() {
        return mVolumeLiveData;
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

    public final String getSTREAM_DEVICES_CHANGED_ACTION() {
        return STREAM_DEVICES_CHANGED_ACTION;
    }

    public final String getSTREAM_MUTE_CHANGED_ACTION() {
        return STREAM_MUTE_CHANGED_ACTION;
    }

    public final boolean getShouldDoSetVolume() {
        return shouldDoSetVolume;
    }

    public final String getVOLUME_CHANGED_ACTION() {
        return VOLUME_CHANGED_ACTION;
    }

    @Override // com.android.systemui.VolumeController
    public Integer getVolume() {
        return mVolumeLiveData.getValue();
    }

    @Override // com.android.systemui.VolumeController
    public MutableLiveData<Integer> getVolumeLiveData() {
        return mVolumeLiveData;
    }

    public final void init() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(VOLUME_CHANGED_ACTION);
        intentFilter.addAction(STREAM_DEVICES_CHANGED_ACTION);
        intentFilter.addAction(STREAM_MUTE_CHANGED_ACTION);
        MiPlayController miPlayController = MiPlayController.INSTANCE;
        BroadcastDispatcher.registerReceiver$default(miPlayController.getBroadcastDispatcher(), getMSystemVolumeReceiver(), intentFilter, null, null, 12, null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ACTION_PACKAGE_REPLACED);
        intentFilter2.addDataScheme("package");
        try {
            miPlayController.getContext().registerReceiver(getMPackageInstallerReceiver(), intentFilter2, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.android.systemui.VolumeController
    public int progressToVolume(int i2) {
        return MiPlayDetailViewModelKt.progressToVolume(i2, getMSystemMinVolume(), getMSystemMaxVolume());
    }

    @Override // com.android.systemui.VolumeController
    public void release() {
        MiPlayController miPlayController = MiPlayController.INSTANCE;
        miPlayController.getBroadcastDispatcher().unregisterReceiver(getMSystemVolumeReceiver());
        try {
            miPlayController.getContext().unregisterReceiver(getMPackageInstallerReceiver());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void setShouldDoSetVolume(boolean z2) {
        shouldDoSetVolume = z2;
    }

    public final void updateVolumeProgress(int i2) {
        Integer value;
        mVolumeLiveData.setValue(Integer.valueOf(i2));
        shouldDoSetVolume = false;
        SystemVolumeController systemVolumeController = INSTANCE;
        int iVolumeToProgress = systemVolumeController.volumeToProgress(i2);
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        if (kotlin.jvm.internal.n.c(miPlayDetailViewModel.getMController().getValue(), systemVolumeController) && !miPlayDetailViewModel.getMOverAllVolumeBarUserTouching() && ((value = miPlayDetailViewModel.getMOverAllVolumeProgress().getValue()) == null || value.intValue() != iVolumeToProgress)) {
            miPlayDetailViewModel.updateOverAllVolumeProgress(iVolumeToProgress);
        }
        shouldDoSetVolume = true;
    }

    @Override // com.android.systemui.VolumeController
    public int volumeToProgress(int i2) {
        return MiPlayDetailViewModelKt.volumeToProgress(i2, getMSystemMinVolume(), getMSystemMaxVolume());
    }
}
