package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ParcelCompat {

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static <T extends Parcelable> List<T> readParcelableList(Parcel parcel, List<T> list, ClassLoader classLoader) {
            return parcel.readParcelableList(list, classLoader);
        }

        public static void writeBoolean(Parcel parcel, boolean z2) {
            parcel.writeBoolean(z2);
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static Parcelable.Creator<?> readParcelableCreator(Parcel parcel, ClassLoader classLoader) {
            return parcel.readParcelableCreator(classLoader);
        }
    }

    @RequiresApi(33)
    public static class Api33Impl {
        private Api33Impl() {
        }

        public static <T> T[] readArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T[]) parcel.readArray(classLoader, cls);
        }

        public static <T> ArrayList<T> readArrayList(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
            return parcel.readArrayList(classLoader, cls);
        }

        public static <V, K> HashMap<K, V> readHashMap(Parcel parcel, ClassLoader classLoader, Class<? extends K> cls, Class<? extends V> cls2) {
            return parcel.readHashMap(classLoader, cls, cls2);
        }

        public static <T> void readList(Parcel parcel, List<? super T> list, ClassLoader classLoader, Class<T> cls) {
            parcel.readList(list, classLoader, cls);
        }

        public static <K, V> void readMap(Parcel parcel, Map<? super K, ? super V> map, ClassLoader classLoader, Class<K> cls, Class<V> cls2) {
            parcel.readMap(map, classLoader, cls, cls2);
        }

        public static <T extends Parcelable> T readParcelable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T) parcel.readParcelable(classLoader, cls);
        }

        public static <T> T[] readParcelableArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T[]) parcel.readParcelableArray(classLoader, cls);
        }

        public static <T> Parcelable.Creator<T> readParcelableCreator(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return parcel.readParcelableCreator(classLoader, cls);
        }

        public static <T> List<T> readParcelableList(Parcel parcel, List<T> list, ClassLoader classLoader, Class<T> cls) {
            return parcel.readParcelableList(list, classLoader, cls);
        }

        public static <T extends Serializable> T readSerializable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T) parcel.readSerializable(classLoader, cls);
        }

        public static <T> SparseArray<T> readSparseArray(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
            return parcel.readSparseArray(classLoader, cls);
        }
    }

    private ParcelCompat() {
    }

    @SuppressLint({"ArrayReturn", "NullableCollection"})
    public static <T> Object[] readArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return Api33Impl.readArray(parcel, classLoader, cls);
    }

    @SuppressLint({"ConcreteCollection", "NullableCollection"})
    public static <T> ArrayList<T> readArrayList(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
        return Api33Impl.readArrayList(parcel, classLoader, cls);
    }

    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    @SuppressLint({"ConcreteCollection", "NullableCollection"})
    public static <K, V> HashMap<K, V> readHashMap(Parcel parcel, ClassLoader classLoader, Class<? extends K> cls, Class<? extends V> cls2) {
        return Api33Impl.readHashMap(parcel, classLoader, cls, cls2);
    }

    public static <T> void readList(Parcel parcel, List<? super T> list, ClassLoader classLoader, Class<T> cls) {
        Api33Impl.readList(parcel, list, classLoader, cls);
    }

    public static <K, V> void readMap(Parcel parcel, Map<? super K, ? super V> map, ClassLoader classLoader, Class<K> cls, Class<V> cls2) {
        Api33Impl.readMap(parcel, map, classLoader, cls, cls2);
    }

    public static <T extends Parcelable> T readParcelable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (T) Api33Impl.readParcelable(parcel, classLoader, cls);
    }

    @SuppressLint({"ArrayReturn", "NullableCollection"})
    @Deprecated
    public static <T> T[] readParcelableArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (T[]) Api33Impl.readParcelableArray(parcel, classLoader, cls);
    }

    @SuppressLint({"ArrayReturn", "NullableCollection"})
    public static <T> Parcelable[] readParcelableArrayTyped(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (Parcelable[]) Api33Impl.readParcelableArray(parcel, classLoader, cls);
    }

    @RequiresApi(30)
    public static <T> Parcelable.Creator<T> readParcelableCreator(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return Api33Impl.readParcelableCreator(parcel, classLoader, cls);
    }

    @RequiresApi(api = 29)
    public static <T> List<T> readParcelableList(Parcel parcel, List<T> list, ClassLoader classLoader, Class<T> cls) {
        return Api33Impl.readParcelableList(parcel, list, classLoader, cls);
    }

    public static <T extends Serializable> T readSerializable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (T) Api33Impl.readSerializable(parcel, classLoader, cls);
    }

    public static <T> SparseArray<T> readSparseArray(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
        return Api33Impl.readSparseArray(parcel, classLoader, cls);
    }

    public static void writeBoolean(Parcel parcel, boolean z2) {
        Api29Impl.writeBoolean(parcel, z2);
    }
}
