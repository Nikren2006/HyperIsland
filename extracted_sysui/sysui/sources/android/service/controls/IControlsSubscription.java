package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IControlsSubscription extends IInterface {

    public static class Default implements IControlsSubscription {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.controls.IControlsSubscription
        public void cancel() {
        }

        @Override // android.service.controls.IControlsSubscription
        public void request(long j2) {
        }
    }

    public static abstract class Stub extends Binder implements IControlsSubscription {
        private static final String DESCRIPTOR = "android.service.controls.IControlsSubscription";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_request = 1;

        public static class Proxy implements IControlsSubscription {
            public static IControlsSubscription sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.service.controls.IControlsSubscription
            public void cancel() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancel();
                } finally {
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.service.controls.IControlsSubscription
            public void request(long j2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().request(j2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControlsSubscription asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IControlsSubscription)) ? new Proxy(iBinder) : (IControlsSubscription) iInterfaceQueryLocalInterface;
        }

        public static IControlsSubscription getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IControlsSubscription iControlsSubscription) {
            if (Proxy.sDefaultImpl != null || iControlsSubscription == null) {
                return false;
            }
            Proxy.sDefaultImpl = iControlsSubscription;
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
                request(parcel.readLong());
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                cancel();
                return true;
            }
            if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void cancel();

    void request(long j2);
}
