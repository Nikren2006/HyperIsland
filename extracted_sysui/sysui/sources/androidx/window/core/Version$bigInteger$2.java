package androidx.window.core;

import java.math.BigInteger;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class Version$bigInteger$2 extends o implements Function0 {
    final /* synthetic */ Version this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Version$bigInteger$2(Version version) {
        super(0);
        this.this$0 = version;
    }

    @Override // kotlin.jvm.functions.Function0
    public final BigInteger invoke() {
        return BigInteger.valueOf(this.this$0.getMajor()).shiftLeft(32).or(BigInteger.valueOf(this.this$0.getMinor())).shiftLeft(32).or(BigInteger.valueOf(this.this$0.getPatch()));
    }
}
