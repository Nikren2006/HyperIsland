package l0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* JADX INFO: renamed from: l0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0445h extends IInterface {

    /* JADX INFO: renamed from: l0.h$a */
    public static class a implements InterfaceC0445h {
        @Override // l0.InterfaceC0445h
        public List B0(int i2) {
            return null;
        }

        @Override // l0.InterfaceC0445h
        public void N(i iVar) {
        }

        @Override // l0.InterfaceC0445h
        public int N0(int i2) {
            return 0;
        }

        @Override // l0.InterfaceC0445h
        public void X0(i iVar) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // l0.InterfaceC0445h
        public void b(int i2) {
        }

        @Override // l0.InterfaceC0445h
        public boolean e() {
            return false;
        }

        @Override // l0.InterfaceC0445h
        public boolean f() {
            return false;
        }

        @Override // l0.InterfaceC0445h
        public boolean g(int i2) {
            return false;
        }

        @Override // l0.InterfaceC0445h
        public void j(int i2) {
        }

        @Override // l0.InterfaceC0445h
        public int k() {
            return 0;
        }

        @Override // l0.InterfaceC0445h
        public List l() {
            return null;
        }

        @Override // l0.InterfaceC0445h
        public int q0() {
            return 0;
        }
    }

    /* JADX INFO: renamed from: l0.h$b */
    public static abstract class b extends Binder implements InterfaceC0445h {

        /* JADX INFO: renamed from: l0.h$b$a */
        public static class a implements InterfaceC0445h {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f5190a;

            public a(IBinder iBinder) {
                this.f5190a = iBinder;
            }

            @Override // l0.InterfaceC0445h
            public List B0(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeInt(i2);
                    this.f5190a.transact(33, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(C0440c.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public void N(i iVar) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeStrongInterface(iVar);
                    this.f5190a.transact(9, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public int N0(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeInt(i2);
                    this.f5190a.transact(34, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public void X0(i iVar) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeStrongInterface(iVar);
                    this.f5190a.transact(8, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5190a;
            }

            @Override // l0.InterfaceC0445h
            public void b(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeInt(i2);
                    this.f5190a.transact(11, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public boolean e() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    this.f5190a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public boolean f() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    this.f5190a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public boolean g(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeInt(i2);
                    this.f5190a.transact(35, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public void j(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    parcelObtain.writeInt(i2);
                    this.f5190a.transact(10, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public int k() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    this.f5190a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public List l() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    this.f5190a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(C0438a.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // l0.InterfaceC0445h
            public int q0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.miplay.audio.IMiPlayAudioService");
                    this.f5190a.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static InterfaceC0445h Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.miplay.audio.IMiPlayAudioService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0445h)) ? new a(iBinder) : (InterfaceC0445h) iInterfaceQueryLocalInterface;
        }
    }

    List B0(int i2);

    void N(i iVar);

    int N0(int i2);

    void X0(i iVar);

    void b(int i2);

    boolean e();

    boolean f();

    boolean g(int i2);

    void j(int i2);

    int k();

    List l();

    int q0();
}
