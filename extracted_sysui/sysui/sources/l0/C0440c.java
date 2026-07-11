package l0;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: renamed from: l0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0440c extends C0439b implements Parcelable {
    public static final Parcelable.Creator<C0440c> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5185e;

    /* JADX INFO: renamed from: l0.c$a */
    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0440c createFromParcel(Parcel parcel) {
            return new C0440c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0440c[] newArray(int i2) {
            return new C0440c[i2];
        }
    }

    public C0440c(String str, DeviceInfo deviceInfo, InterfaceC0441d interfaceC0441d, int i2, int i3) {
        super(str, deviceInfo, interfaceC0441d, i2);
        this.f5185e = i3;
    }

    @Override // l0.C0439b, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int v() {
        return this.f5185e;
    }

    @Override // l0.C0439b, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f5185e);
    }

    public C0440c(Parcel parcel) {
        super(parcel);
        this.f5185e = parcel.readInt();
    }
}
