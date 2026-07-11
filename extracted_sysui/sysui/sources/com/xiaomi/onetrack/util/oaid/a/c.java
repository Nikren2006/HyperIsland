package com.xiaomi.onetrack.util.oaid.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends IInterface {

    public interface a extends IInterface {

        /* JADX INFO: renamed from: com.xiaomi.onetrack.util.oaid.a.c$a$a, reason: collision with other inner class name */
        public static abstract class AbstractBinderC0071a extends Binder implements a {
            public AbstractBinderC0071a() {
                attachInterface(this, "com.hihonor.cloudservice.oaid.IOAIDCallBack");
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this;
            }

            @Override // android.os.Binder
            public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
                if (i2 == 1) {
                    parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                    a(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                if (i2 != 2) {
                    if (i2 != 1598968902) {
                        return super.onTransact(i2, parcel, parcel2, i3);
                    }
                    parcel2.writeString("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                    return true;
                }
                parcel.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                a(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
        }

        void a(int i2, long j2, boolean z2, float f2, double d2, String str);

        void a(int i2, Bundle bundle);
    }

    public static final class b implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f3541a;

        public b(IBinder iBinder) {
            this.f3541a = iBinder;
        }

        @Override // com.xiaomi.onetrack.util.oaid.a.c
        public void a(a.AbstractBinderC0071a abstractBinderC0071a) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDService");
                parcelObtain.writeStrongBinder(abstractBinderC0071a);
                this.f3541a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                parcelObtain2.recycle();
                parcelObtain.recycle();
            } catch (Throwable th) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                th.printStackTrace();
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f3541a;
        }
    }

    void a(a.AbstractBinderC0071a abstractBinderC0071a);
}
