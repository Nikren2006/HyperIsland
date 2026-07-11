package androidx.activity.result;

import H0.s;
import androidx.activity.result.contract.ActivityResultContract;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityResultCallerKt {
    public static final <I, O> ActivityResultLauncher<s> registerForActivityResult(ActivityResultCaller activityResultCaller, ActivityResultContract<I, O> activityResultContract, I i2, ActivityResultRegistry activityResultRegistry, final Function1 function1) {
        return new ActivityResultCallerLauncher(activityResultCaller.registerForActivityResult(activityResultContract, activityResultRegistry, new ActivityResultCallback() { // from class: androidx.activity.result.a
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                function1.invoke(obj);
            }
        }), activityResultContract, i2);
    }

    public static final <I, O> ActivityResultLauncher<s> registerForActivityResult(ActivityResultCaller activityResultCaller, ActivityResultContract<I, O> activityResultContract, I i2, final Function1 function1) {
        return new ActivityResultCallerLauncher(activityResultCaller.registerForActivityResult(activityResultContract, new ActivityResultCallback() { // from class: androidx.activity.result.b
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                function1.invoke(obj);
            }
        }), activityResultContract, i2);
    }
}
