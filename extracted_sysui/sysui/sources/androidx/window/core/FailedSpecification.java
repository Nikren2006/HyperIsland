package androidx.window.core;

import H0.g;
import I0.AbstractC0181i;
import androidx.window.core.SpecificationComputer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
final class FailedSpecification<T> extends SpecificationComputer<T> {
    private final WindowStrictModeException exception;
    private final Logger logger;
    private final String message;
    private final String tag;
    private final T value;
    private final SpecificationComputer.VerificationMode verificationMode;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SpecificationComputer.VerificationMode.values().length];
            iArr[SpecificationComputer.VerificationMode.STRICT.ordinal()] = 1;
            iArr[SpecificationComputer.VerificationMode.LOG.ordinal()] = 2;
            iArr[SpecificationComputer.VerificationMode.QUIET.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FailedSpecification(T value, String tag, String message, Logger logger, SpecificationComputer.VerificationMode verificationMode) {
        n.g(value, "value");
        n.g(tag, "tag");
        n.g(message, "message");
        n.g(logger, "logger");
        n.g(verificationMode, "verificationMode");
        this.value = value;
        this.tag = tag;
        this.message = message;
        this.logger = logger;
        this.verificationMode = verificationMode;
        WindowStrictModeException windowStrictModeException = new WindowStrictModeException(createMessage(value, message));
        StackTraceElement[] stackTrace = windowStrictModeException.getStackTrace();
        n.f(stackTrace, "stackTrace");
        Object[] array = AbstractC0181i.x(stackTrace, 2).toArray(new StackTraceElement[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        windowStrictModeException.setStackTrace((StackTraceElement[]) array);
        this.exception = windowStrictModeException;
    }

    @Override // androidx.window.core.SpecificationComputer
    public T compute() throws WindowStrictModeException {
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.verificationMode.ordinal()];
        if (i2 == 1) {
            throw this.exception;
        }
        if (i2 == 2) {
            this.logger.debug(this.tag, createMessage(this.value, this.message));
            return null;
        }
        if (i2 == 3) {
            return null;
        }
        throw new g();
    }

    public final WindowStrictModeException getException() {
        return this.exception;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getTag() {
        return this.tag;
    }

    public final T getValue() {
        return this.value;
    }

    public final SpecificationComputer.VerificationMode getVerificationMode() {
        return this.verificationMode;
    }

    @Override // androidx.window.core.SpecificationComputer
    public SpecificationComputer<T> require(String message, Function1 condition) {
        n.g(message, "message");
        n.g(condition, "condition");
        return this;
    }
}
