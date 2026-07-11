package com.android.systemui;

import androidx.lifecycle.MutableLiveData;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDetailViewModel$mController$1$2$1 extends kotlin.jvm.internal.o implements Function1 {
    final /* synthetic */ MutableLiveData<VolumeController> $controllerLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlayDetailViewModel$mController$1$2$1(MutableLiveData<VolumeController> mutableLiveData) {
        super(1);
        this.$controllerLiveData = mutableLiveData;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Integer) obj);
        return H0.s.f314a;
    }

    public final void invoke(Integer num) {
        VolumeController value = this.$controllerLiveData.getValue();
        if (value != null) {
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            if (miPlayDetailViewModel.getAudioShareSecondaryEarphoneSeeking()) {
                return;
            }
            boolean zIsAudioShare = miPlayDetailViewModel.isAudioShare();
            int mainEarphoneVolume = EarPhoneUtils.INSTANCE.getMainEarphoneVolume(miPlayDetailViewModel.getMAudioShareSelectedDevices());
            if (zIsAudioShare) {
                num = Integer.valueOf(mainEarphoneVolume);
            }
            kotlin.jvm.internal.n.d(num);
            miPlayDetailViewModel.updateOverAllVolumeProgress(value.volumeToProgress(num.intValue()));
        }
    }
}
