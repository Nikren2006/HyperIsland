package j0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: renamed from: j0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0412a extends IInterface {

    /* JADX INFO: renamed from: j0.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0087a extends Binder implements InterfaceC0412a {

        /* JADX INFO: renamed from: j0.a$a$a, reason: collision with other inner class name */
        public static class C0088a implements InterfaceC0412a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f4636a;

            public C0088a(IBinder iBinder) {
                this.f4636a = iBinder;
            }

            @Override // j0.InterfaceC0412a
            public void O(String str, String str2, String str3, String str4) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.analytics.ITrack");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    this.f4636a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4636a;
            }

            @Override // j0.InterfaceC0412a
            public void y0(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.miui.analytics.ITrack");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeString(str5);
                    parcelObtain.writeString(str6);
                    parcelObtain.writeString(str7);
                    parcelObtain.writeString(str8);
                    this.f4636a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static InterfaceC0412a Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.analytics.ITrack");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0412a)) ? new C0088a(iBinder) : (InterfaceC0412a) iInterfaceQueryLocalInterface;
        }
    }

    void O(String str, String str2, String str3, String str4);

    void y0(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8);
}
