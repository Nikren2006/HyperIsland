package miui.systemui.controlcenter.panel.main.volume;

import android.media.AudioManager;
import com.miui.miplay.audio.data.DeviceInfo;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class VolumeSliderController$audioManager$2 extends o implements Function0 {
    final /* synthetic */ VolumeSliderController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeSliderController$audioManager$2(VolumeSliderController volumeSliderController) {
        super(0);
        this.this$0 = volumeSliderController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AudioManager invoke() {
        Object systemService = this.this$0.getContext().getSystemService(DeviceInfo.AUDIO_SUPPORT);
        n.e(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        return (AudioManager) systemService;
    }
}
