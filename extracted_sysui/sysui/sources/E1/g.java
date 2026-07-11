package E1;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes5.dex */
public interface g extends IInterface {

    public static abstract class a extends Binder implements g {
        public a() {
            attachInterface(this, "se.dirac.acs.api.IAudioControlServiceCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface("se.dirac.acs.api.IAudioControlServiceCallback");
                x(parcel.readLong(), parcel.createIntArray());
                parcel2.writeNoException();
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface("se.dirac.acs.api.IAudioControlServiceCallback");
                w();
                parcel2.writeNoException();
                return true;
            }
            if (i2 == 3) {
                parcel.enforceInterface("se.dirac.acs.api.IAudioControlServiceCallback");
                R0(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i2 == 4) {
                parcel.enforceInterface("se.dirac.acs.api.IAudioControlServiceCallback");
                l0(parcel.readInt() != 0 ? (h) h.f141c.createFromParcel(parcel) : null, parcel.readInt() != 0 ? i.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i2 != 5) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("se.dirac.acs.api.IAudioControlServiceCallback");
                return true;
            }
            parcel.enforceInterface("se.dirac.acs.api.IAudioControlServiceCallback");
            r(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void R0(String str);

    void l0(h hVar, i iVar);

    void r(int i2);

    void w();

    void x(long j2, int[] iArr);
}
