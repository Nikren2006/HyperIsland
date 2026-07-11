package c;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: c.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0225a extends IInterface {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String f1304v = "android$support$v4$os$IResultReceiver".replace('$', '.');

    /* JADX INFO: renamed from: c.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0044a extends Binder implements InterfaceC0225a {

        /* JADX INFO: renamed from: c.a$a$a, reason: collision with other inner class name */
        public static class C0045a implements InterfaceC0225a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f1305a;

            public C0045a(IBinder iBinder) {
                this.f1305a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1305a;
            }

            @Override // c.InterfaceC0225a
            public void send(int i2, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(InterfaceC0225a.f1304v);
                    parcelObtain.writeInt(i2);
                    b.d(parcelObtain, bundle, 0);
                    this.f1305a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0044a() {
            attachInterface(this, InterfaceC0225a.f1304v);
        }

        public static InterfaceC0225a Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(InterfaceC0225a.f1304v);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0225a)) ? new C0045a(iBinder) : (InterfaceC0225a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            String str = InterfaceC0225a.f1304v;
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            send(parcel.readInt(), (Bundle) b.c(parcel, Bundle.CREATOR));
            return true;
        }
    }

    /* JADX INFO: renamed from: c.a$b */
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

    void send(int i2, Bundle bundle);
}
