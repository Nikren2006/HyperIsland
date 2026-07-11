package c;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import c.InterfaceC0225a;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f1306a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f1307b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public InterfaceC0225a f1308c;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i2) {
            return new b[i2];
        }
    }

    /* JADX INFO: renamed from: c.b$b, reason: collision with other inner class name */
    public class BinderC0046b extends InterfaceC0225a.AbstractBinderC0044a {
        public BinderC0046b() {
        }

        @Override // c.InterfaceC0225a
        public void send(int i2, Bundle bundle) {
            b bVar = b.this;
            Handler handler = bVar.f1307b;
            if (handler != null) {
                handler.post(bVar.new c(i2, bundle));
            } else {
                bVar.q(i2, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1310a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f1311b;

        public c(int i2, Bundle bundle) {
            this.f1310a = i2;
            this.f1311b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.q(this.f1310a, this.f1311b);
        }
    }

    public b(Parcel parcel) {
        this.f1308c = InterfaceC0225a.AbstractBinderC0044a.Z0(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void q(int i2, Bundle bundle) {
    }

    public void r(int i2, Bundle bundle) {
        if (this.f1306a) {
            Handler handler = this.f1307b;
            if (handler != null) {
                handler.post(new c(i2, bundle));
                return;
            } else {
                q(i2, bundle);
                return;
            }
        }
        InterfaceC0225a interfaceC0225a = this.f1308c;
        if (interfaceC0225a != null) {
            try {
                interfaceC0225a.send(i2, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        synchronized (this) {
            try {
                if (this.f1308c == null) {
                    this.f1308c = new BinderC0046b();
                }
                parcel.writeStrongBinder(this.f1308c.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
