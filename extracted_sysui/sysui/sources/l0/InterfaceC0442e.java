package l0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: renamed from: l0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0442e extends IInterface {

    /* JADX INFO: renamed from: l0.e$a */
    public static abstract class a extends Binder implements InterfaceC0442e {

        /* JADX INFO: renamed from: l0.e$a$a, reason: collision with other inner class name */
        public static class C0105a implements InterfaceC0442e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f5187a;

            public C0105a(IBinder iBinder) {
                this.f5187a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5187a;
            }

            @Override // l0.InterfaceC0442e
            public void onDeviceConnectionStateChange(int i2, int i3) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IDeviceChangeListener");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.f5187a.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0442e
            public void onDeviceInfoChange(DeviceInfo deviceInfo) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IDeviceChangeListener");
                    b.d(parcelObtain, deviceInfo, 0);
                    this.f5187a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0442e
            public void onVolumeChange(int i2, int i3) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IDeviceChangeListener");
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    this.f5187a.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.miui.miplay.audio.IDeviceChangeListener");
        }

        public static InterfaceC0442e Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.miplay.audio.IDeviceChangeListener");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0442e)) ? new C0105a(iBinder) : (InterfaceC0442e) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.miui.miplay.audio.IDeviceChangeListener");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.miui.miplay.audio.IDeviceChangeListener");
                return true;
            }
            if (i2 == 1) {
                onDeviceInfoChange((DeviceInfo) b.c(parcel, DeviceInfo.CREATOR));
            } else if (i2 == 2) {
                onDeviceConnectionStateChange(parcel.readInt(), parcel.readInt());
            } else if (i2 == 3) {
                onDeviceSelectStatusChange(parcel.readInt(), parcel.readInt());
            } else {
                if (i2 != 4) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                onVolumeChange(parcel.readInt(), parcel.readInt());
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: l0.e$b */
    public static class b {
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

    void onDeviceConnectionStateChange(int i2, int i3);

    void onDeviceInfoChange(DeviceInfo deviceInfo);

    void onDeviceSelectStatusChange(int i2, int i3);

    void onVolumeChange(int i2, int i3);
}
