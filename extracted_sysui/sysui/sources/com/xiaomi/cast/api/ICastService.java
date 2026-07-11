package com.xiaomi.cast.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public interface ICastService extends IInterface {
    public static final String DESCRIPTOR = "com.xiaomi.cast.api.ICastService";

    public static class Default implements ICastService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void connectDevice(DeviceInfo deviceInfo) {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void disConnectAll() {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void disConnectDevice(DeviceInfo deviceInfo) {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void setDeviceVolume(double d2) {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void setMediaMetaData(MediaMetaData mediaMetaData, String str) {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void setPlaybackState(int i2) {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void startScan() {
        }

        @Override // com.xiaomi.cast.api.ICastService
        public void stopScan() {
        }
    }

    public static abstract class Stub extends Binder implements ICastService {
        static final int TRANSACTION_connectDevice = 5;
        static final int TRANSACTION_disConnectAll = 7;
        static final int TRANSACTION_disConnectDevice = 6;
        static final int TRANSACTION_setDeviceVolume = 4;
        static final int TRANSACTION_setMediaMetaData = 3;
        static final int TRANSACTION_setPlaybackState = 8;
        static final int TRANSACTION_startScan = 1;
        static final int TRANSACTION_stopScan = 2;

        public static class Proxy implements ICastService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void connectDevice(DeviceInfo deviceInfo) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, deviceInfo, 0);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void disConnectAll() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void disConnectDevice(DeviceInfo deviceInfo) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, deviceInfo, 0);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return ICastService.DESCRIPTOR;
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void setDeviceVolume(double d2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    parcelObtain.writeDouble(d2);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void setMediaMetaData(MediaMetaData mediaMetaData, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, mediaMetaData, 0);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void setPlaybackState(int i2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void startScan() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.xiaomi.cast.api.ICastService
            public void stopScan() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(ICastService.DESCRIPTOR);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, ICastService.DESCRIPTOR);
        }

        public static ICastService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(ICastService.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICastService)) ? new Proxy(iBinder) : (ICastService) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ICastService.DESCRIPTOR);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ICastService.DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    startScan();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    stopScan();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    setMediaMetaData((MediaMetaData) _Parcel.readTypedObject(parcel, MediaMetaData.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    setDeviceVolume(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    connectDevice((DeviceInfo) _Parcel.readTypedObject(parcel, DeviceInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    disConnectDevice((DeviceInfo) _Parcel.readTypedObject(parcel, DeviceInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    disConnectAll();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    setPlaybackState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t2, int i2) {
            if (t2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t2.writeToParcel(parcel, i2);
            }
        }
    }

    void connectDevice(DeviceInfo deviceInfo);

    void disConnectAll();

    void disConnectDevice(DeviceInfo deviceInfo);

    void setDeviceVolume(double d2);

    void setMediaMetaData(MediaMetaData mediaMetaData, String str);

    void setPlaybackState(int i2);

    void startScan();

    void stopScan();
}
