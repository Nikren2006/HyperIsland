package com.xiaomi.onetrack.util.oaid.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface f extends IInterface {

    public static abstract class a extends Binder implements f {

        /* JADX INFO: renamed from: com.xiaomi.onetrack.util.oaid.a.f$a$a, reason: collision with other inner class name */
        public static class C0074a implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f3544a;

            public C0074a(IBinder iBinder) {
                this.f3544a = iBinder;
            }

            public String a(String str, String str2, String str3) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                        parcelObtain.writeString(str);
                        parcelObtain.writeString(str2);
                        parcelObtain.writeString(str3);
                        this.f3544a.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3544a;
            }
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof f)) ? new C0074a(iBinder) : (f) iInterfaceQueryLocalInterface;
        }
    }
}
