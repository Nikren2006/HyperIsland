package com.mi.widget.core;

import androidx.annotation.CheckResult;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface IDriverShareStructure {
    @CheckResult
    @UiThread
    boolean isInitialized();
}
