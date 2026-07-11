package b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public interface c extends IInterface {

    public static abstract class a extends Binder implements c {
        public a() {
            attachInterface(this, "android.app.IHyperDropInfoNotifier");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("android.app.IHyperDropInfoNotifier");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("android.app.IHyperDropInfoNotifier");
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            n(parcel.readString(), (Bundle) b.b(parcel, Bundle.CREATOR));
            return true;
        }
    }

    public static class b {
        public static Object b(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }
    }

    void n(String str, Bundle bundle);
}
