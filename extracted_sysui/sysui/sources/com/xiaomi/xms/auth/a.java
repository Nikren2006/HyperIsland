package com.xiaomi.xms.auth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.xiaomi.xms.auth.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0076a extends Binder implements a {

        /* JADX INFO: renamed from: com.xiaomi.xms.auth.a$a$a, reason: collision with other inner class name */
        public static class C0077a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f3716a;

            public C0077a(IBinder iBinder) {
                this.f3716a = iBinder;
            }

            @Override // com.xiaomi.xms.auth.a
            public void U0(Bundle bundle, com.xiaomi.xms.auth.b bVar) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xiaomi.xms.auth.IAuthService");
                    b.d(parcelObtain, bundle, 0);
                    parcelObtain.writeStrongInterface(bVar);
                    this.f3716a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3716a;
            }

            @Override // com.xiaomi.xms.auth.a
            public Bundle j0(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xiaomi.xms.auth.IAuthService");
                    b.d(parcelObtain, bundle, 0);
                    this.f3716a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (Bundle) b.c(parcelObtain2, Bundle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.xiaomi.xms.auth.IAuthService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0077a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }

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

    void U0(Bundle bundle, com.xiaomi.xms.auth.b bVar);

    Bundle j0(Bundle bundle);
}
