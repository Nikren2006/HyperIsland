package miui.systemui.controlcenter.panel.main.volume;

import android.content.BroadcastReceiver;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class VolumeSliderController$volumeReceiver$2 extends o implements Function0 {
    final /* synthetic */ VolumeSliderController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeSliderController$volumeReceiver$2(VolumeSliderController volumeSliderController) {
        super(0);
        this.this$0 = volumeSliderController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.controlcenter.panel.main.volume.VolumeSliderController$volumeReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final VolumeSliderController volumeSliderController = this.this$0;
        return new BroadcastReceiver() { // from class: miui.systemui.controlcenter.panel.main.volume.VolumeSliderController$volumeReceiver$2.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onReceive(android.content.Context r7, android.content.Intent r8) {
                /*
                    Method dump skipped, instruction units count: 214
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.main.volume.VolumeSliderController$volumeReceiver$2.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
    }
}
