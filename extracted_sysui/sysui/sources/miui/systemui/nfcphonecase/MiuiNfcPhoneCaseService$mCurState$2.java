package miui.systemui.nfcphonecase;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$mCurState$2 extends o implements Function0 {
    final /* synthetic */ MiuiNfcPhoneCaseService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiNfcPhoneCaseService$mCurState$2(MiuiNfcPhoneCaseService miuiNfcPhoneCaseService) {
        super(0);
        this.this$0 = miuiNfcPhoneCaseService;
    }

    @Override // kotlin.jvm.functions.Function0
    public final float[] invoke() {
        float[] fArr = new float[this.this$0.mPositionMap.size()];
        fArr[0] = this.this$0.getScreenRadius();
        return fArr;
    }
}
