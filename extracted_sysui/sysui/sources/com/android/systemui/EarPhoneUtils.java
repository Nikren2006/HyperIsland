package com.android.systemui;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class EarPhoneUtils {
    public static final EarPhoneUtils INSTANCE = new EarPhoneUtils();

    private EarPhoneUtils() {
    }

    public final int getMainEarphoneVolume(ArrayList<m0.i> audioShareSelectedDevices) {
        kotlin.jvm.internal.n.g(audioShareSelectedDevices, "audioShareSelectedDevices");
        int iO = -1;
        for (m0.i iVar : audioShareSelectedDevices) {
            if (iVar.k().getType() == 2 && !iVar.k().isAudioSharing()) {
                iO = iVar.o();
            }
        }
        return iO;
    }

    public final boolean isAudioShare(ArrayList<m0.i> audioShareSelectedDevices) {
        kotlin.jvm.internal.n.g(audioShareSelectedDevices, "audioShareSelectedDevices");
        if (kotlin.jvm.internal.n.c(MiPlayDetailViewModel.INSTANCE.getSupportAudioShared(), Boolean.TRUE)) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : audioShareSelectedDevices) {
                if (((m0.i) obj).k().isAudioSharing()) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
