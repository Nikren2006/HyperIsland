package l0;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.DeviceInfo;
import l0.InterfaceC0441d;

/* JADX INFO: renamed from: l0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0439b implements Parcelable {
    public static final Parcelable.Creator<C0439b> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5181a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DeviceInfo f5182b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InterfaceC0441d f5183c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5184d;

    /* JADX INFO: renamed from: l0.b$a */
    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0439b createFromParcel(Parcel parcel) {
            return new C0439b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0439b[] newArray(int i2) {
            return new C0439b[i2];
        }
    }

    public C0439b(String str, DeviceInfo deviceInfo, InterfaceC0441d interfaceC0441d, int i2) {
        this.f5181a = str;
        this.f5182b = deviceInfo;
        this.f5183c = interfaceC0441d;
        this.f5184d = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InterfaceC0441d q() {
        return this.f5183c;
    }

    public String r() {
        return this.f5181a;
    }

    public DeviceInfo s() {
        return this.f5182b;
    }

    public int t() {
        return this.f5184d;
    }

    public int u() {
        return this.f5182b.getType();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f5181a);
        parcel.writeParcelable(this.f5182b, i2);
        parcel.writeStrongBinder(this.f5183c.asBinder());
        parcel.writeInt(this.f5184d);
    }

    public C0439b(Parcel parcel) {
        this.f5181a = parcel.readString();
        this.f5182b = (DeviceInfo) parcel.readParcelable(DeviceInfo.class.getClassLoader());
        this.f5183c = InterfaceC0441d.b.Z0(parcel.readStrongBinder());
        this.f5184d = parcel.readInt();
    }
}
