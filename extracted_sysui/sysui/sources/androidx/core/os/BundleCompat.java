package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class BundleCompat {

    @RequiresApi(33)
    public static class Api33Impl {
        private Api33Impl() {
        }

        public static <T> T getParcelable(Bundle bundle, String str, Class<T> cls) {
            return (T) bundle.getParcelable(str, cls);
        }

        public static <T> T[] getParcelableArray(Bundle bundle, String str, Class<T> cls) {
            return (T[]) bundle.getParcelableArray(str, cls);
        }

        public static <T> ArrayList<T> getParcelableArrayList(Bundle bundle, String str, Class<? extends T> cls) {
            return bundle.getParcelableArrayList(str, cls);
        }

        public static <T extends Serializable> T getSerializable(Bundle bundle, String str, Class<T> cls) {
            return (T) bundle.getSerializable(str, cls);
        }

        public static <T> SparseArray<T> getSparseParcelableArray(Bundle bundle, String str, Class<? extends T> cls) {
            return bundle.getSparseParcelableArray(str, cls);
        }
    }

    private BundleCompat() {
    }

    @ReplaceWith(expression = "bundle.getBinder(key)")
    @Deprecated
    public static IBinder getBinder(Bundle bundle, String str) {
        return bundle.getBinder(str);
    }

    public static <T> T getParcelable(Bundle bundle, String str, Class<T> cls) {
        return (T) Api33Impl.getParcelable(bundle, str, cls);
    }

    @SuppressLint({"ArrayReturn", "NullableCollection"})
    public static Parcelable[] getParcelableArray(Bundle bundle, String str, Class<? extends Parcelable> cls) {
        return (Parcelable[]) Api33Impl.getParcelableArray(bundle, str, cls);
    }

    @SuppressLint({"ConcreteCollection", "NullableCollection"})
    public static <T> ArrayList<T> getParcelableArrayList(Bundle bundle, String str, Class<? extends T> cls) {
        return Api33Impl.getParcelableArrayList(bundle, str, cls);
    }

    public static <T extends Serializable> T getSerializable(Bundle bundle, String str, Class<T> cls) {
        return (T) Api33Impl.getSerializable(bundle, str, cls);
    }

    public static <T> SparseArray<T> getSparseParcelableArray(Bundle bundle, String str, Class<? extends T> cls) {
        return Api33Impl.getSparseParcelableArray(bundle, str, cls);
    }

    @ReplaceWith(expression = "bundle.putBinder(key, binder)")
    @Deprecated
    public static void putBinder(Bundle bundle, String str, IBinder iBinder) {
        bundle.putBinder(str, iBinder);
    }
}
