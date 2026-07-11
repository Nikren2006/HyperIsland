package miuix.core.util;

import androidx.annotation.NonNull;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SoftReferenceSingleton<T> {
    private SoftReference<T> mInstance = null;

    public T createInstance() {
        return null;
    }

    public final T get() {
        T tCreateInstance;
        synchronized (this) {
            try {
                SoftReference<T> softReference = this.mInstance;
                if (softReference == null || (tCreateInstance = softReference.get()) == null) {
                    tCreateInstance = createInstance();
                    this.mInstance = new SoftReference<>(tCreateInstance);
                } else {
                    updateInstance(tCreateInstance);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return tCreateInstance;
    }

    public void updateInstance(@NonNull T t2) {
    }

    public T createInstance(Object obj) {
        return null;
    }

    public void updateInstance(@NonNull T t2, Object obj) {
    }

    public final T get(Object obj) {
        T tCreateInstance;
        synchronized (this) {
            try {
                SoftReference<T> softReference = this.mInstance;
                if (softReference != null && (tCreateInstance = softReference.get()) != null) {
                    updateInstance(tCreateInstance, obj);
                } else {
                    tCreateInstance = createInstance(obj);
                    this.mInstance = new SoftReference<>(tCreateInstance);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return tCreateInstance;
    }
}
