package l0;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.miplay.audio.data.AppMetaData;
import l0.InterfaceC0444g;

/* JADX INFO: renamed from: l0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0438a implements Parcelable {
    public static final Parcelable.Creator<C0438a> CREATOR = new C0104a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AppMetaData f5179a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0444g f5180b;

    /* JADX INFO: renamed from: l0.a$a, reason: collision with other inner class name */
    public class C0104a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0438a createFromParcel(Parcel parcel) {
            return new C0438a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0438a[] newArray(int i2) {
            return new C0438a[i2];
        }
    }

    public C0438a(AppMetaData appMetaData, InterfaceC0444g interfaceC0444g) {
        this.f5179a = appMetaData;
        this.f5180b = interfaceC0444g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InterfaceC0444g q() {
        return this.f5180b;
    }

    public AppMetaData r() {
        return this.f5179a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f5179a, i2);
        parcel.writeStrongBinder(this.f5180b.asBinder());
    }

    public C0438a(Parcel parcel) {
        this.f5179a = (AppMetaData) parcel.readParcelable(AppMetaData.class.getClassLoader());
        this.f5180b = InterfaceC0444g.b.Z0(parcel.readStrongBinder());
    }
}
