package androidx.activity.result;

import H0.d;
import H0.e;
import H0.s;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityResultCallerLauncher<I, O> extends ActivityResultLauncher<s> {
    private final ActivityResultContract<I, O> callerContract;
    private final I callerInput;
    private final ActivityResultLauncher<I> launcher;
    private final d resultContract$delegate = e.b(new ActivityResultCallerLauncher$resultContract$2(this));

    public ActivityResultCallerLauncher(ActivityResultLauncher<I> activityResultLauncher, ActivityResultContract<I, O> activityResultContract, I i2) {
        this.launcher = activityResultLauncher;
        this.callerContract = activityResultContract;
        this.callerInput = i2;
    }

    public final ActivityResultContract<I, O> getCallerContract() {
        return this.callerContract;
    }

    public final I getCallerInput() {
        return this.callerInput;
    }

    @Override // androidx.activity.result.ActivityResultLauncher
    public ActivityResultContract<s, ?> getContract() {
        return getResultContract();
    }

    public final ActivityResultLauncher<I> getLauncher() {
        return this.launcher;
    }

    public final ActivityResultContract<s, O> getResultContract() {
        return (ActivityResultContract) this.resultContract$delegate.getValue();
    }

    @Override // androidx.activity.result.ActivityResultLauncher
    public void unregister() {
        this.launcher.unregister();
    }

    @Override // androidx.activity.result.ActivityResultLauncher
    public void launch(s sVar, ActivityOptionsCompat activityOptionsCompat) {
        this.launcher.launch(this.callerInput, activityOptionsCompat);
    }
}
