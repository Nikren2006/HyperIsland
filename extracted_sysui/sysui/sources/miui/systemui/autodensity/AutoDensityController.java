package miui.systemui.autodensity;

import H0.s;
import android.content.Context;
import android.content.res.Configuration;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public interface AutoDensityController {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static AutoDensityController impl;

        private Companion() {
        }

        public final s addOnDensityChangeListener(OnDensityChangeListener listener) {
            n.g(listener, "listener");
            AutoDensityController autoDensityController = impl;
            if (autoDensityController == null) {
                return null;
            }
            autoDensityController.addOnDensityChangeListener(listener);
            return s.f314a;
        }

        public final AutoDensityController getImpl() {
            return impl;
        }

        public final s removeOnDensityChangeListener(OnDensityChangeListener listener) {
            n.g(listener, "listener");
            AutoDensityController autoDensityController = impl;
            if (autoDensityController == null) {
                return null;
            }
            autoDensityController.removeOnDensityChangeListener(listener);
            return s.f314a;
        }

        public final void setImpl(AutoDensityController autoDensityController) {
            impl = autoDensityController;
        }
    }

    public interface OnDensityChangeListener {
        void onConfigChanged(Configuration configuration);
    }

    void addOnDensityChangeListener(OnDensityChangeListener onDensityChangeListener);

    void onUserChanged(int i2, Context context);

    void removeOnDensityChangeListener(OnDensityChangeListener onDensityChangeListener);
}
