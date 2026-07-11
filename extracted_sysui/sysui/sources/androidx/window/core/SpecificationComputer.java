package androidx.window.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class SpecificationComputer<T> {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ SpecificationComputer startSpecification$default(Companion companion, Object obj, String str, VerificationMode verificationMode, Logger logger, int i2, Object obj2) {
            if ((i2 & 2) != 0) {
                verificationMode = BuildConfig.INSTANCE.getVerificationMode();
            }
            if ((i2 & 4) != 0) {
                logger = AndroidLogger.INSTANCE;
            }
            return companion.startSpecification(obj, str, verificationMode, logger);
        }

        public final <T> SpecificationComputer<T> startSpecification(T t2, String tag, VerificationMode verificationMode, Logger logger) {
            n.g(t2, "<this>");
            n.g(tag, "tag");
            n.g(verificationMode, "verificationMode");
            n.g(logger, "logger");
            return new ValidSpecification(t2, tag, verificationMode, logger);
        }

        private Companion() {
        }
    }

    public enum VerificationMode {
        STRICT,
        LOG,
        QUIET
    }

    public abstract T compute();

    public final String createMessage(Object value, String message) {
        n.g(value, "value");
        n.g(message, "message");
        return message + " value: " + value;
    }

    public abstract SpecificationComputer<T> require(String str, Function1 function1);
}
