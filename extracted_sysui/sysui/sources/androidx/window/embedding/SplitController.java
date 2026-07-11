package androidx.window.embedding;

import H0.s;
import I0.K;
import I0.u;
import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.core.ExperimentalWindowApi;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
public final class SplitController {
    private static volatile SplitController globalInstance = null;
    public static final boolean sDebug = false;
    private final EmbeddingBackend embeddingBackend;
    private Set<? extends EmbeddingRule> staticSplitRules;
    public static final Companion Companion = new Companion(null);
    private static final ReentrantLock globalLock = new ReentrantLock();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SplitController getInstance() {
            if (SplitController.globalInstance == null) {
                ReentrantLock reentrantLock = SplitController.globalLock;
                reentrantLock.lock();
                try {
                    if (SplitController.globalInstance == null) {
                        SplitController.globalInstance = new SplitController(null);
                    }
                    s sVar = s.f314a;
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
            SplitController splitController = SplitController.globalInstance;
            n.d(splitController);
            return splitController;
        }

        public final void initialize(Context context, int i2) {
            n.g(context, "context");
            Set<EmbeddingRule> splitRules$window_release = new SplitRuleParser().parseSplitRules$window_release(context, i2);
            SplitController companion = getInstance();
            if (splitRules$window_release == null) {
                splitRules$window_release = K.b();
            }
            companion.setStaticSplitRules(splitRules$window_release);
        }

        private Companion() {
        }
    }

    public /* synthetic */ SplitController(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final SplitController getInstance() {
        return Companion.getInstance();
    }

    public static final void initialize(Context context, int i2) {
        Companion.initialize(context, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStaticSplitRules(Set<? extends EmbeddingRule> set) {
        this.staticSplitRules = set;
        this.embeddingBackend.setSplitRules(set);
    }

    public final void addSplitListener(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer) {
        n.g(activity, "activity");
        n.g(executor, "executor");
        n.g(consumer, "consumer");
        this.embeddingBackend.registerSplitListenerForActivity(activity, executor, consumer);
    }

    public final void clearRegisteredRules() {
        this.embeddingBackend.setSplitRules(this.staticSplitRules);
    }

    public final Set<EmbeddingRule> getSplitRules() {
        return u.o0(this.embeddingBackend.getSplitRules());
    }

    public final boolean isSplitSupported() {
        return this.embeddingBackend.isSplitSupported();
    }

    public final void registerRule(EmbeddingRule rule) {
        n.g(rule, "rule");
        this.embeddingBackend.registerRule(rule);
    }

    public final void removeSplitListener(Consumer<List<SplitInfo>> consumer) {
        n.g(consumer, "consumer");
        this.embeddingBackend.unregisterSplitListenerForActivity(consumer);
    }

    public final void unregisterRule(EmbeddingRule rule) {
        n.g(rule, "rule");
        this.embeddingBackend.unregisterRule(rule);
    }

    private SplitController() {
        this.embeddingBackend = ExtensionEmbeddingBackend.Companion.getInstance();
        this.staticSplitRules = K.b();
    }
}
