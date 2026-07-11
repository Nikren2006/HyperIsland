package com.xiaomi.onetrack.util.oaid.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface d extends IInterface {
    String a();

    String a(String str);

    boolean b();

    public static abstract class a extends Binder implements d {
        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0072a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                String strA = a();
                parcel2.writeNoException();
                parcel2.writeString(strA);
                return true;
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.zui.deviceidservice.IDeviceidInterface");
                return true;
            }
            if (i2 == 3) {
                parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                boolean zB = b();
                parcel2.writeNoException();
                parcel2.writeInt(zB ? 1 : 0);
                return true;
            }
            if (i2 != 4) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
            String strA2 = a(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(strA2);
            return true;
        }

        /* JADX INFO: renamed from: com.xiaomi.onetrack.util.oaid.a.d$a$a, reason: collision with other inner class name */
        public static class C0072a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f3542a;

            public C0072a(IBinder iBinder) {
                this.f3542a = iBinder;
            }

            @Override // com.xiaomi.onetrack.util.oaid.a.d
            public String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                        this.f3542a.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return null;
            }

            @Override // com.xiaomi.onetrack.util.oaid.a.d
            public boolean b() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    this.f3542a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable unused) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
                return z;
            }

            @Override // com.xiaomi.onetrack.util.oaid.a.d
            public String a(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                        this.f3542a.transact(4, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return null;
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
