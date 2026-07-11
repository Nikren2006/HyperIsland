package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.service.controls.IControlsSubscription;

/* JADX INFO: loaded from: classes.dex */
public interface IControlsSubscriber extends IInterface {

    public static class Default implements IControlsSubscriber {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onComplete(IBinder iBinder) {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onError(IBinder iBinder, String str) {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onNext(IBinder iBinder, Control control) {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) {
        }
    }

    public static abstract class Stub extends Binder implements IControlsSubscriber {
        private static final String DESCRIPTOR = "android.service.controls.IControlsSubscriber";
        static final int TRANSACTION_onComplete = 4;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onNext = 2;
        static final int TRANSACTION_onSubscribe = 1;

        public static class Proxy implements IControlsSubscriber {
            public static IControlsSubscriber sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onComplete(IBinder iBinder) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onComplete(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onError(IBinder iBinder, String str) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(iBinder, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onNext(IBinder iBinder, Control control) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (control != null) {
                        parcelObtain.writeInt(1);
                        control.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onNext(iBinder, control);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th) {
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iControlsSubscription != null ? iControlsSubscription.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onSubscribe(iBinder, iControlsSubscription);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th) {
                    parcelObtain.recycle();
                    throw th;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControlsSubscriber asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IControlsSubscriber)) ? new Proxy(iBinder) : (IControlsSubscriber) iInterfaceQueryLocalInterface;
        }

        public static IControlsSubscriber getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IControlsSubscriber iControlsSubscriber) {
            if (Proxy.sDefaultImpl != null || iControlsSubscriber == null) {
                return false;
            }
            Proxy.sDefaultImpl = iControlsSubscriber;
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
                onSubscribe(parcel.readStrongBinder(), IControlsSubscription.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNext(parcel.readStrongBinder(), parcel.readInt() != 0 ? (Control) Control.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i2 == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readStrongBinder(), parcel.readString());
                return true;
            }
            if (i2 == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onComplete(parcel.readStrongBinder());
                return true;
            }
            if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void onComplete(IBinder iBinder);

    void onError(IBinder iBinder, String str);

    void onNext(IBinder iBinder, Control control);

    void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription);
}
