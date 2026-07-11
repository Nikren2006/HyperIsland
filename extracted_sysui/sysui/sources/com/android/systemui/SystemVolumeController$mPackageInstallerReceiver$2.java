package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import g1.AbstractC0369g;
import g1.C0366e0;
import kotlin.jvm.functions.Function0;
import miui.systemui.coroutines.Dispatchers;

/* JADX INFO: loaded from: classes.dex */
public final class SystemVolumeController$mPackageInstallerReceiver$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final SystemVolumeController$mPackageInstallerReceiver$2 INSTANCE = new SystemVolumeController$mPackageInstallerReceiver$2();

    public SystemVolumeController$mPackageInstallerReceiver$2() {
        super(0);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.SystemVolumeController$mPackageInstallerReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        return new BroadcastReceiver() { // from class: com.android.systemui.SystemVolumeController$mPackageInstallerReceiver$2.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                kotlin.jvm.internal.n.g(intent, "intent");
                if (kotlin.jvm.internal.n.c(SystemVolumeController.ACTION_PACKAGE_REPLACED, intent.getAction()) && kotlin.jvm.internal.n.c(SystemVolumeController.MILINK_PACKAGE, intent.getDataString())) {
                    int mState = MiPlayController.INSTANCE.getMState();
                    Log.i(MiPlayDetailViewModel.TAG, "package install, state: " + mState);
                    if (mState == 6) {
                        AbstractC0369g.b(C0366e0.f4417a, Dispatchers.INSTANCE.getIO(), null, new SystemVolumeController$mPackageInstallerReceiver$2$1$onReceive$1(null), 2, null);
                    }
                }
            }
        };
    }
}
