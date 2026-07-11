package l0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.MediaMetaData;
import l0.InterfaceC0443f;

/* JADX INFO: renamed from: l0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0444g extends IInterface {

    /* JADX INFO: renamed from: l0.g$a */
    public static class a implements InterfaceC0444g {
        @Override // l0.InterfaceC0444g
        public MediaMetaData B() {
            return null;
        }

        @Override // l0.InterfaceC0444g
        public void F() {
        }

        @Override // l0.InterfaceC0444g
        public void V(InterfaceC0443f interfaceC0443f) {
        }

        @Override // l0.InterfaceC0444g
        public void X() {
        }

        @Override // l0.InterfaceC0444g
        public void a(long j2) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // l0.InterfaceC0444g
        public void c0(InterfaceC0443f interfaceC0443f) {
        }

        @Override // l0.InterfaceC0444g
        public void d() {
        }

        @Override // l0.InterfaceC0444g
        public int getPlaybackState() {
            return 0;
        }

        @Override // l0.InterfaceC0444g
        public long getPosition() {
            return 0L;
        }

        @Override // l0.InterfaceC0444g
        public void h() {
        }

        @Override // l0.InterfaceC0444g
        public void i() {
        }

        @Override // l0.InterfaceC0444g
        public void next() {
        }

        @Override // l0.InterfaceC0444g
        public void pause() {
        }

        @Override // l0.InterfaceC0444g
        public void previous() {
        }

        @Override // l0.InterfaceC0444g
        public void r0(float f2) {
        }
    }

    /* JADX INFO: renamed from: l0.g$b */
    public static abstract class b extends Binder implements InterfaceC0444g {

        /* JADX INFO: renamed from: l0.g$b$a */
        public static class a implements InterfaceC0444g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f5189a;

            public a(IBinder iBinder) {
                this.f5189a = iBinder;
            }

            @Override // l0.InterfaceC0444g
            public MediaMetaData B() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (MediaMetaData) c.c(parcelObtain2, MediaMetaData.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void F() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(15, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void V(InterfaceC0443f interfaceC0443f) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    parcelObtain.writeStrongInterface(interfaceC0443f);
                    this.f5189a.transact(11, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void X() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(16, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void a(long j2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    parcelObtain.writeLong(j2);
                    this.f5189a.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5189a;
            }

            @Override // l0.InterfaceC0444g
            public void c0(InterfaceC0443f interfaceC0443f) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    parcelObtain.writeStrongInterface(interfaceC0443f);
                    this.f5189a.transact(10, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void d() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public int getPlaybackState() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public long getPosition() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void h() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(13, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void i() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(12, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void next() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(5, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void pause() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void previous() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    this.f5189a.transact(6, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0444g
            public void r0(float f2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMediaController");
                    parcelObtain.writeFloat(f2);
                    this.f5189a.transact(14, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public b() {
            attachInterface(this, "com.miui.miplay.audio.IMediaController");
        }

        public static InterfaceC0444g Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.miplay.audio.IMediaController");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0444g)) ? new a(iBinder) : (InterfaceC0444g) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.miui.miplay.audio.IMediaController");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.miui.miplay.audio.IMediaController");
                return true;
            }
            switch (i2) {
                case 1:
                    d();
                    return true;
                case 2:
                    pause();
                    return true;
                case 3:
                    stop();
                    return true;
                case 4:
                    a(parcel.readLong());
                    return true;
                case 5:
                    next();
                    return true;
                case 6:
                    previous();
                    return true;
                case 7:
                    long position = getPosition();
                    parcel2.writeNoException();
                    parcel2.writeLong(position);
                    return true;
                case 8:
                    int playbackState = getPlaybackState();
                    parcel2.writeNoException();
                    parcel2.writeInt(playbackState);
                    return true;
                case 9:
                    MediaMetaData mediaMetaDataB = B();
                    parcel2.writeNoException();
                    c.d(parcel2, mediaMetaDataB, 1);
                    return true;
                case 10:
                    c0(InterfaceC0443f.a.Z0(parcel.readStrongBinder()));
                    return true;
                case 11:
                    V(InterfaceC0443f.a.Z0(parcel.readStrongBinder()));
                    return true;
                case 12:
                    i();
                    return true;
                case 13:
                    h();
                    return true;
                case 14:
                    r0(parcel.readFloat());
                    return true;
                case 15:
                    F();
                    return true;
                case 16:
                    X();
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    /* JADX INFO: renamed from: l0.g$c */
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

    MediaMetaData B();

    void F();

    void V(InterfaceC0443f interfaceC0443f);

    void X();

    void a(long j2);

    void c0(InterfaceC0443f interfaceC0443f);

    void d();

    int getPlaybackState();

    long getPosition();

    void h();

    void i();

    void next();

    void pause();

    void previous();

    void r0(float f2);

    void stop();
}
