package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f987u = "android$support$v4$app$INotificationSideChannel".replace('$', '.');

    /* JADX INFO: renamed from: android.support.v4.app.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0029a extends Binder implements a {
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        /* JADX INFO: renamed from: android.support.v4.app.a$a$a, reason: collision with other inner class name */
        public static class C0030a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f988a;

            public C0030a(IBinder iBinder) {
                this.f988a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f988a;
            }

            @Override // android.support.v4.app.a
            public void cancel(String str, int i2, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.f987u);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    this.f988a.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.app.a
            public void cancelAll(String str) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.f987u);
                    parcelObtain.writeString(str);
                    this.f988a.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.app.a
            public void notify(String str, int i2, String str2, Notification notification) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.f987u);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    b.d(parcelObtain, notification, 0);
                    this.f988a.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0029a() {
            attachInterface(this, a.f987u);
        }

        public static a asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(a.f987u);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0030a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            String str = a.f987u;
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i2 == 1) {
                notify(parcel.readString(), parcel.readInt(), parcel.readString(), (Notification) b.c(parcel, Notification.CREATOR));
            } else if (i2 == 2) {
                cancel(parcel.readString(), parcel.readInt(), parcel.readString());
            } else {
                if (i2 != 3) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                cancelAll(parcel.readString());
            }
            return true;
        }
    }

    public static class b {
        public static Object c(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        public static void d(Parcel parcel, Parcelable parcelable, int i2) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcelable.writeToParcel(parcel, i2);
            }
        }
    }

    void cancel(String str, int i2, String str2);

    void cancelAll(String str);

    void notify(String str, int i2, String str2, Notification notification);
}
