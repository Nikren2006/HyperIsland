package com.mi.widget.core;

import androidx.annotation.AnyThread;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface IShaderDriven {

    public static final class a {
        public static void a(IShaderDriven iShaderDriven) {
        }

        public static void b(IShaderDriven iShaderDriven) {
        }
    }

    @AnyThread
    DriverShareStrategy getDriverShareStrategy();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    IDriverShareStructure onDriveFrameParameters(boolean z2, long j2, float f2, IDriverShareStructure iDriverShareStructure);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    void onInitFrameParameters();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    void onResetFrameParameters();
}
