package androidx.window.layout;

import androidx.annotation.RestrictTo;
import androidx.window.core.ExperimentalWindowApi;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
@RestrictTo({RestrictTo.Scope.TESTS})
public interface WindowMetricsCalculatorDecorator {
    @ExperimentalWindowApi
    @RestrictTo({RestrictTo.Scope.TESTS})
    WindowMetricsCalculator decorate(WindowMetricsCalculator windowMetricsCalculator);
}
