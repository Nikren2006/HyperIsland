package androidx.core.os;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public final class JavaHeapDumpRequestBuilder extends ProfilingRequestBuilder<JavaHeapDumpRequestBuilder> {
    private final Bundle mParams = new Bundle();

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public Bundle getParams() {
        return this.mParams;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public int getProfilingType() {
        return 1;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public JavaHeapDumpRequestBuilder getThis() {
        return this;
    }

    public final JavaHeapDumpRequestBuilder setBufferSizeKb(int i2) {
        this.mParams.putInt("KEY_SIZE_KB", i2);
        return this;
    }
}
