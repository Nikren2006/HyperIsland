package l0;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface i extends IInterface {

    public static abstract class a extends Binder implements i {
        public a() {
            attachInterface(this, "com.miui.miplay.audio.IMiPlayServiceCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface("com.miui.miplay.audio.IMiPlayServiceCallback");
            }
            if (i2 == 1598968902) {
                parcel2.writeString("com.miui.miplay.audio.IMiPlayServiceCallback");
                return true;
            }
            if (i2 == 1) {
                onActiveAudioSessionChange(parcel.createTypedArrayList(C0438a.CREATOR));
            } else if (i2 == 2) {
                onAudioDeviceListChange(parcel.createTypedArrayList(C0439b.CREATOR));
            } else if (i2 == 3) {
                onServiceStateChange(parcel.readInt());
            } else if (i2 == 4) {
                onProjectionStateChange(parcel.readInt());
            } else if (i2 == 5) {
                onError(parcel.readInt(), parcel.readString());
            } else if (i2 != 21) {
                switch (i2) {
                    case 23:
                        A(parcel.createTypedArrayList(C0440c.CREATOR));
                        break;
                    case 24:
                        F0(parcel.readInt(), parcel.readInt());
                        break;
                    case 25:
                        onVideoCastModeChange(parcel.readInt(), parcel.readInt());
                        break;
                    default:
                        switch (i2) {
                            case 27:
                                onVideoCpAppStateChange(parcel.readInt(), parcel.readString());
                                break;
                            case 28:
                                onDeviceStartPlaying((Bundle) b.b(parcel, Bundle.CREATOR));
                                break;
                            case 29:
                                onBluetoothDeviceConnectFail(parcel.readString());
                                break;
                            case 30:
                                onBluetoothDeviceConnectSuccess(parcel.readString());
                                break;
                            default:
                                return super.onTransact(i2, parcel, parcel2, i3);
                        }
                        break;
                }
            } else {
                s(parcel.readInt());
            }
            return true;
        }
    }

    public static class b {
        public static Object b(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }
    }

    void A(List list);

    void F0(int i2, int i3);

    void onActiveAudioSessionChange(List list);

    void onAudioDeviceListChange(List list);

    void onBluetoothDeviceConnectFail(String str);

    void onBluetoothDeviceConnectSuccess(String str);

    void onDeviceStartPlaying(Bundle bundle);

    void onError(int i2, String str);

    void onProjectionStateChange(int i2);

    void onServiceStateChange(int i2);

    void onVideoCastModeChange(int i2, int i3);

    void onVideoCpAppStateChange(int i2, String str);

    void s(int i2);
}
