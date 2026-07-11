package com.miui.misound;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.miui.misound.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0064a extends Binder implements a {

        /* JADX INFO: renamed from: com.miui.misound.a$a$a, reason: collision with other inner class name */
        public static class C0065a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f2571a;

            public C0065a(IBinder iBinder) {
                this.f2571a = iBinder;
            }

            @Override // com.miui.misound.a
            public void A0(b bVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.misound.ITransmitAidlInterface");
                    parcelObtain.writeStrongInterface(bVar);
                    this.f2571a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.miui.misound.a
            public void E0(boolean z2, int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.misound.ITransmitAidlInterface");
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    this.f2571a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.miui.misound.a
            public void J0(b bVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.misound.ITransmitAidlInterface");
                    parcelObtain.writeStrongInterface(bVar);
                    this.f2571a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2571a;
            }
        }

        public static a Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.misound.ITransmitAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0065a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }

    void A0(b bVar);

    void E0(boolean z2, int i2);

    void J0(b bVar);
}
