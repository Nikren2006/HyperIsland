package androidx.core.os;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public final class StackSamplingRequestBuilder extends ProfilingRequestBuilder<StackSamplingRequestBuilder> {
    private final Bundle mParams = new Bundle();

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public Bundle getParams() {
        return this.mParams;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public int getProfilingType() {
        return 3;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public StackSamplingRequestBuilder getThis() {
        return this;
    }

    public final StackSamplingRequestBuilder setBufferSizeKb(int i2) {
        this.mParams.putInt("KEY_SIZE_KB", i2);
        return this;
    }

    public final StackSamplingRequestBuilder setDurationMs(int i2) {
        this.mParams.putInt("KEY_DURATION_MS", i2);
        return this;
    }

    public final StackSamplingRequestBuilder setSamplingFrequencyHz(int i2) {
        this.mParams.putInt("KEY_FREQUENCY_HZ", i2);
        return this;
    }
}
