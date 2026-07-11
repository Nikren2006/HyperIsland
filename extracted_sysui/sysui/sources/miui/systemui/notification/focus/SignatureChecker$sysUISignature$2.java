package miui.systemui.notification.focus;

import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class SignatureChecker$sysUISignature$2 extends o implements Function0 {
    final /* synthetic */ SignatureChecker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignatureChecker$sysUISignature$2(SignatureChecker signatureChecker) {
        super(0);
        this.this$0 = signatureChecker;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Set<String> invoke() {
        SignatureChecker signatureChecker = this.this$0;
        return signatureChecker.getSysUISignatures(signatureChecker.getPackageManager());
    }
}
