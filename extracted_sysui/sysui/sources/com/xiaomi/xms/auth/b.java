package com.xiaomi.xms.auth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {
        static final int TRANSACTION_onAuthResult = 1;

        /* JADX INFO: renamed from: com.xiaomi.xms.auth.b$a$a, reason: collision with other inner class name */
        public static class C0078a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f3717a;

            public C0078a(IBinder iBinder) {
                this.f3717a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3717a;
            }
        }

        public a() {
            attachInterface(this, "com.xiaomi.xms.auth.IAuthServiceCallback");
        }

        public static b asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.xiaomi.xms.auth.IAuthServiceCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0078a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.xiaomi.xms.auth.IAuthServiceCallback");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.xiaomi.xms.auth.IAuthServiceCallback");
                return true;
            }
            if (i2 != 1) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            onAuthResult((Bundle) C0079b.b(parcel, Bundle.CREATOR));
            return true;
        }
    }

    /* JADX INFO: renamed from: com.xiaomi.xms.auth.b$b, reason: collision with other inner class name */
    public static class C0079b {
        public static Object b(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }
    }

    void onAuthResult(Bundle bundle);
}
