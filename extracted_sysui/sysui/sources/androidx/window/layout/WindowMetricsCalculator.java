package androidx.window.layout;

import android.app.Activity;
import androidx.annotation.RestrictTo;
import androidx.window.core.ExperimentalWindowApi;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public interface WindowMetricsCalculator {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static Function1 decorator = WindowMetricsCalculator$Companion$decorator$1.INSTANCE;

        private Companion() {
        }

        public final WindowMetricsCalculator getOrCreate() {
            return (WindowMetricsCalculator) decorator.invoke(WindowMetricsCalculatorCompat.INSTANCE);
        }

        @ExperimentalWindowApi
        @RestrictTo({RestrictTo.Scope.TESTS})
        public final void overrideDecorator(WindowMetricsCalculatorDecorator overridingDecorator) {
            n.g(overridingDecorator, "overridingDecorator");
            decorator = new WindowMetricsCalculator$Companion$overrideDecorator$1(overridingDecorator);
        }

        @ExperimentalWindowApi
        @RestrictTo({RestrictTo.Scope.TESTS})
        public final void reset() {
            decorator = WindowMetricsCalculator$Companion$reset$1.INSTANCE;
        }
    }

    static WindowMetricsCalculator getOrCreate() {
        return Companion.getOrCreate();
    }

    @ExperimentalWindowApi
    @RestrictTo({RestrictTo.Scope.TESTS})
    static void overrideDecorator(WindowMetricsCalculatorDecorator windowMetricsCalculatorDecorator) {
        Companion.overrideDecorator(windowMetricsCalculatorDecorator);
    }

    @ExperimentalWindowApi
    @RestrictTo({RestrictTo.Scope.TESTS})
    static void reset() {
        Companion.reset();
    }

    WindowMetrics computeCurrentWindowMetrics(Activity activity);

    WindowMetrics computeMaximumWindowMetrics(Activity activity);
}
