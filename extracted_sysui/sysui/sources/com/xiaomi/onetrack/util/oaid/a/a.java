package com.xiaomi.onetrack.util.oaid.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.xiaomi.onetrack.util.oaid.a.a$a, reason: collision with other inner class name */
    public static final class C0070a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f3539a;

        public C0070a(IBinder iBinder) {
            this.f3539a = iBinder;
        }

        @Override // com.xiaomi.onetrack.util.oaid.a.a
        public String a() {
            String string;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                this.f3539a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                string = parcelObtain2.readString();
            } catch (Throwable th) {
                parcelObtain.recycle();
                parcelObtain2.recycle();
                th.printStackTrace();
                string = null;
            }
            parcelObtain.recycle();
            parcelObtain2.recycle();
            return string;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f3539a;
        }
    }

    String a();
}
