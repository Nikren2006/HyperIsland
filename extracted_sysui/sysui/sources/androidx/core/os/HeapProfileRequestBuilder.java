package androidx.core.os;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public final class HeapProfileRequestBuilder extends ProfilingRequestBuilder<HeapProfileRequestBuilder> {
    private final Bundle mParams = new Bundle();

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public Bundle getParams() {
        return this.mParams;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public int getProfilingType() {
        return 2;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public HeapProfileRequestBuilder getThis() {
        return this;
    }

    public final HeapProfileRequestBuilder setBufferSizeKb(int i2) {
        this.mParams.putInt("KEY_SIZE_KB", i2);
        return this;
    }

    public final HeapProfileRequestBuilder setDurationMs(int i2) {
        this.mParams.putInt("KEY_DURATION_MS", i2);
        return this;
    }

    public final HeapProfileRequestBuilder setSamplingIntervalBytes(long j2) {
        this.mParams.putLong("KEY_SAMPLING_INTERVAL_BYTES", j2);
        return this;
    }

    public final HeapProfileRequestBuilder setTrackJavaAllocations(boolean z2) {
        this.mParams.putBoolean("KEY_TRACK_JAVA_ALLOCATIONS", z2);
        return this;
    }
}
