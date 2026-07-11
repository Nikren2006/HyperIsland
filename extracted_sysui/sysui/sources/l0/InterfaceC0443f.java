package l0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.List;

/* JADX INFO: renamed from: l0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0443f extends IInterface {

    /* JADX INFO: renamed from: l0.f$a */
    public static abstract class a extends Binder implements InterfaceC0443f {

        /* JADX INFO: renamed from: l0.f$a$a, reason: collision with other inner class name */
        public static class C0106a implements InterfaceC0443f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f5188a;

            public C0106a(IBinder iBinder) {
                this.f5188a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5188a;
            }

            @Override // l0.InterfaceC0443f
            public void onMediaMetaChange(MediaMetaData mediaMetaData) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaChangeListener");
                    b.d(parcelObtain, mediaMetaData, 0);
                    this.f5188a.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0443f
            public void onPlaybackStateChange(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaChangeListener");
                    parcelObtain.writeInt(i2);
                    this.f5188a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0443f
            public void onPositionChange(long j2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaChangeListener");
                    parcelObtain.writeLong(j2);
                    this.f5188a.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.miui.miplay.audio.IMediaChangeListener");
        }

        public static InterfaceC0443f Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.miplay.audio.IMediaChangeListener");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0443f)) ? new C0106a(iBinder) : (InterfaceC0443f) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.miui.miplay.audio.IMediaChangeListener");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.miui.miplay.audio.IMediaChangeListener");
                return true;
            }
            switch (i2) {
                case 1:
                    onPlaybackStateChange(parcel.readInt());
                    return true;
                case 2:
                    onPositionChange(parcel.readLong());
                    return true;
                case 3:
                    onMediaMetaChange((MediaMetaData) b.c(parcel, MediaMetaData.CREATOR));
                    return true;
                case 4:
                    onBufferStateChange(parcel.readInt());
                    return true;
                case 5:
                    L0(parcel.readInt());
                    return true;
                case 6:
                    V0(parcel.readFloat());
                    return true;
                case 7:
                    onCastModeChange(parcel.readInt(), parcel.readInt());
                    return true;
                case 8:
                    onCpStateChange(parcel.readString(), parcel.readInt());
                    return true;
                case 9:
                    h0(parcel.readInt(), parcel.readString(), parcel.readInt());
                    return true;
                case 10:
                    O0(parcel.createStringArrayList());
                    return true;
                case 11:
                    H(parcel.readInt(), parcel.readString());
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    /* JADX INFO: renamed from: l0.f$b */
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

    void H(int i2, String str);

    void L0(int i2);

    void O0(List list);

    void V0(float f2);

    void h0(int i2, String str, int i3);

    void onBufferStateChange(int i2);

    void onCastModeChange(int i2, int i3);

    void onCpStateChange(String str, int i2);

    void onMediaMetaChange(MediaMetaData mediaMetaData);

    void onPlaybackStateChange(int i2);

    void onPositionChange(long j2);
}
