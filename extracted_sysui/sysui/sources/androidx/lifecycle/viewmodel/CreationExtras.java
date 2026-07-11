package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class CreationExtras {
    private final Map<Key<?>, Object> map = new LinkedHashMap();

    public static final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        public <T> T get(Key<T> key) {
            n.g(key, "key");
            return null;
        }
    }

    public interface Key<T> {
    }

    public abstract <T> T get(Key<T> key);

    public final Map<Key<?>, Object> getMap$lifecycle_viewmodel_release() {
        return this.map;
    }
}
