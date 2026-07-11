package com.miui.misound;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {
        static final int TRANSACTION_getClientAppName = 4;
        static final int TRANSACTION_onBTStateChange = 3;
        static final int TRANSACTION_onStateChange = 1;
        static final int TRANSACTION_onVolumeChange = 2;

        /* JADX INFO: renamed from: com.miui.misound.b$a$a, reason: collision with other inner class name */
        public static class C0066a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f2572a;

            public C0066a(IBinder iBinder) {
                this.f2572a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2572a;
            }
        }

        public a() {
            attachInterface(this, "com.miui.misound.StateCallBack");
        }

        public static b asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.miui.misound.StateCallBack");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0066a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.miui.misound.StateCallBack");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.miui.misound.StateCallBack");
                return true;
            }
            if (i2 == 1) {
                onStateChange(parcel.readInt() != 0);
            } else if (i2 == 2) {
                onVolumeChange(parcel.readInt());
            } else if (i2 == 3) {
                onBTStateChange(parcel.readInt() != 0);
            } else {
                if (i2 != 4) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                String clientAppName = getClientAppName();
                parcel2.writeNoException();
                parcel2.writeString(clientAppName);
            }
            return true;
        }
    }

    String getClientAppName();

    void onBTStateChange(boolean z2);

    void onStateChange(boolean z2);

    void onVolumeChange(int i2);
}
