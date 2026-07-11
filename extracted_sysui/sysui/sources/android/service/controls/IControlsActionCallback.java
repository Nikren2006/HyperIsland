package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IControlsActionCallback extends IInterface {

    public static class Default implements IControlsActionCallback {
        @Override // android.service.controls.IControlsActionCallback
        public void accept(IBinder iBinder, String str, int i2) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IControlsActionCallback {
        private static final String DESCRIPTOR = "android.service.controls.IControlsActionCallback";
        static final int TRANSACTION_accept = 1;

        public static class Proxy implements IControlsActionCallback {
            public static IControlsActionCallback sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.service.controls.IControlsActionCallback
            public void accept(IBinder iBinder, String str, int i2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().accept(iBinder, str, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControlsActionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IControlsActionCallback)) ? new Proxy(iBinder) : (IControlsActionCallback) iInterfaceQueryLocalInterface;
        }

        public static IControlsActionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IControlsActionCallback iControlsActionCallback) {
            if (Proxy.sDefaultImpl != null || iControlsActionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iControlsActionCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                accept(parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                return true;
            }
            if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void accept(IBinder iBinder, String str, int i2);
}
