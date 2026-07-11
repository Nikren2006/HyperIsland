package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class SystemVolumeController$mSystemVolumeReceiver$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final SystemVolumeController$mSystemVolumeReceiver$2 INSTANCE = new SystemVolumeController$mSystemVolumeReceiver$2();

    public SystemVolumeController$mSystemVolumeReceiver$2() {
        super(0);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.SystemVolumeController$mSystemVolumeReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        return new BroadcastReceiver() { // from class: com.android.systemui.SystemVolumeController$mSystemVolumeReceiver$2.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                kotlin.jvm.internal.n.g(intent, "intent");
                long jCurrentTimeMillis = System.currentTimeMillis();
                String action = intent.getAction();
                SystemVolumeController systemVolumeController = SystemVolumeController.INSTANCE;
                if (kotlin.jvm.internal.n.c(action, systemVolumeController.getSTREAM_DEVICES_CHANGED_ACTION()) || kotlin.jvm.internal.n.c(intent.getAction(), systemVolumeController.getVOLUME_CHANGED_ACTION())) {
                    Bundle extras = intent.getExtras();
                    Integer numValueOf = null;
                    Integer numValueOf2 = extras != null ? Integer.valueOf(extras.getInt(systemVolumeController.getEXTRA_VOLUME_STREAM_TYPE())) : null;
                    if (numValueOf2 != null && numValueOf2.intValue() == 3) {
                        if (kotlin.jvm.internal.n.c(intent.getAction(), systemVolumeController.getSTREAM_DEVICES_CHANGED_ACTION())) {
                            numValueOf = Integer.valueOf(systemVolumeController.getMAudioManager().getStreamVolume(3));
                        } else {
                            Bundle extras2 = intent.getExtras();
                            if (extras2 != null) {
                                numValueOf = Integer.valueOf(extras2.getInt(systemVolumeController.getEXTRA_VOLUME_STREAM_VALUE(), -1));
                            }
                        }
                        if (numValueOf != null && numValueOf.intValue() >= 0) {
                            systemVolumeController.updateVolumeProgress(numValueOf.intValue());
                        }
                    }
                }
                Log.d(MiPlayDetailViewModel.TAG, "mSystemVolumeReceiver.onReceive(): time cost = " + (System.currentTimeMillis() - jCurrentTimeMillis));
            }
        };
    }
}
