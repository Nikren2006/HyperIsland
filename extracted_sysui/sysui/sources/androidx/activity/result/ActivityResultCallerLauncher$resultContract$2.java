package androidx.activity.result;

import H0.s;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityResultCallerLauncher$resultContract$2 extends o implements Function0 {
    final /* synthetic */ ActivityResultCallerLauncher<I, O> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityResultCallerLauncher$resultContract$2(ActivityResultCallerLauncher<I, O> activityResultCallerLauncher) {
        super(0);
        this.this$0 = activityResultCallerLauncher;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.activity.result.ActivityResultCallerLauncher$resultContract$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final ActivityResultCallerLauncher<I, O> activityResultCallerLauncher = this.this$0;
        return new ActivityResultContract<s, O>() { // from class: androidx.activity.result.ActivityResultCallerLauncher$resultContract$2.1
            @Override // androidx.activity.result.contract.ActivityResultContract
            public O parseResult(int i2, Intent intent) {
                return (O) activityResultCallerLauncher.getCallerContract().parseResult(i2, intent);
            }

            @Override // androidx.activity.result.contract.ActivityResultContract
            public Intent createIntent(Context context, s sVar) {
                return activityResultCallerLauncher.getCallerContract().createIntent(context, activityResultCallerLauncher.getCallerInput());
            }
        };
    }
}
