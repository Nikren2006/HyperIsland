package l0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.MediaMetaData;
import l0.InterfaceC0442e;
import l0.InterfaceC0444g;

/* JADX INFO: renamed from: l0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0441d extends IInterface {

    /* JADX INFO: renamed from: l0.d$a */
    public static class a implements InterfaceC0441d {
        @Override // l0.InterfaceC0441d
        public int W0(int i2, MediaMetaData mediaMetaData) {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // l0.InterfaceC0441d
        public void e0(InterfaceC0442e interfaceC0442e) {
        }

        @Override // l0.InterfaceC0441d
        public int getVolume() {
            return 0;
        }

        @Override // l0.InterfaceC0441d
        public void n0(InterfaceC0442e interfaceC0442e) {
        }

        @Override // l0.InterfaceC0441d
        public int q() {
            return 0;
        }

        @Override // l0.InterfaceC0441d
        public void setStreamVolume(int i2, int i3) {
        }

        @Override // l0.InterfaceC0441d
        public int y(int i2) {
            return 0;
        }
    }

    /* JADX INFO: renamed from: l0.d$b */
    public static abstract class b extends Binder implements InterfaceC0441d {

        /* JADX INFO: renamed from: l0.d$b$a */
        public static class a implements InterfaceC0441d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f5186a;

            public a(IBinder iBinder) {
                this.f5186a = iBinder;
            }

            @Override // l0.InterfaceC0441d
            public int D0(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    parcelObtain.writeInt(i2);
                    this.f5186a.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public InterfaceC0444g T0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    this.f5186a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return InterfaceC0444g.b.Z0(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public int W0(int i2, MediaMetaData mediaMetaData) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    parcelObtain.writeInt(i2);
                    c.d(parcelObtain, mediaMetaData, 0);
                    this.f5186a.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5186a;
            }

            @Override // l0.InterfaceC0441d
            public void e0(InterfaceC0442e interfaceC0442e) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    parcelObtain.writeStrongInterface(interfaceC0442e);
                    this.f5186a.transact(10, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public int getVolume() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    this.f5186a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public int i0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    this.f5186a.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public void n0(InterfaceC0442e interfaceC0442e) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    parcelObtain.writeStrongInterface(interfaceC0442e);
                    this.f5186a.transact(11, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public int q() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    this.f5186a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public void setStreamVolume(int i2, int i3) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.f5186a.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0441d
            public int y(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IAudioDeviceController");
                    parcelObtain.writeInt(i2);
                    this.f5186a.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public b() {
            attachInterface(this, "com.miui.miplay.audio.IAudioDeviceController");
        }

        public static InterfaceC0441d Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.miplay.audio.IAudioDeviceController");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0441d)) ? new a(iBinder) : (InterfaceC0441d) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.miui.miplay.audio.IAudioDeviceController");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.miui.miplay.audio.IAudioDeviceController");
                return true;
            }
            switch (i2) {
                case 1:
                    InterfaceC0444g interfaceC0444gT0 = T0();
                    parcel2.writeNoException();
                    parcel2.writeStrongInterface(interfaceC0444gT0);
                    return true;
                case 2:
                    int iW0 = w0();
                    parcel2.writeNoException();
                    parcel2.writeInt(iW0);
                    return true;
                case 3:
                    int iQ = q();
                    parcel2.writeNoException();
                    parcel2.writeInt(iQ);
                    return true;
                case 4:
                    int iT = t();
                    parcel2.writeNoException();
                    parcel2.writeInt(iT);
                    return true;
                case 5:
                    int volume = getVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(volume);
                    return true;
                case 6:
                    int iD0 = d0();
                    parcel2.writeNoException();
                    parcel2.writeInt(iD0);
                    return true;
                case 7:
                    int iG0 = g0();
                    parcel2.writeNoException();
                    parcel2.writeInt(iG0);
                    return true;
                case 8:
                    setStreamVolume(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    int iI0 = i0();
                    parcel2.writeNoException();
                    parcel2.writeInt(iI0);
                    return true;
                case 10:
                    e0(InterfaceC0442e.a.Z0(parcel.readStrongBinder()));
                    return true;
                case 11:
                    n0(InterfaceC0442e.a.Z0(parcel.readStrongBinder()));
                    return true;
                case 12:
                    int iY = y(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iY);
                    return true;
                case 13:
                    int iW02 = W0(parcel.readInt(), (MediaMetaData) c.c(parcel, MediaMetaData.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(iW02);
                    return true;
                case 14:
                    int iT2 = T(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iT2);
                    return true;
                case 15:
                    int iD02 = D0(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iD02);
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    /* JADX INFO: renamed from: l0.d$c */
    public static class c {
        public static Object c(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        public static void d(Parcel parcel, Parcelable parcelable, int i2) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcelable.writeToParcel(parcel, i2);
            }
        }
    }

    int D0(int i2);

    int T(int i2);

    InterfaceC0444g T0();

    int W0(int i2, MediaMetaData mediaMetaData);

    int d0();

    void e0(InterfaceC0442e interfaceC0442e);

    int g0();

    int getVolume();

    int i0();

    void n0(InterfaceC0442e interfaceC0442e);

    int q();

    void setStreamVolume(int i2, int i3);

    int t();

    int w0();

    int y(int i2);
}
