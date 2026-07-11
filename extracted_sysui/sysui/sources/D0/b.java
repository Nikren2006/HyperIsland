package D0;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: D0.b$a$a, reason: collision with other inner class name */
        public static class C0003a implements b {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static b f62b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f63a;

            public C0003a(IBinder iBinder) {
                this.f63a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f63a;
            }

            @Override // D0.b
            public Bundle c(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.xiaomi.smarthome.control.IServiceMijiaControl");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f63a.transact(1, parcelObtain, parcelObtain2, 0) && a.a1() != null) {
                        Bundle bundleC = a.a1().c(str, bundle);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return bundleC;
                    }
                    parcelObtain2.readException();
                    Bundle bundle2 = parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }
        }

        public static b Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.xiaomi.smarthome.control.IServiceMijiaControl");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0003a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b a1() {
            return C0003a.f62b;
        }
    }

    Bundle c(String str, Bundle bundle);
}
