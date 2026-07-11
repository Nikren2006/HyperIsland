package y1;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0172a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Intent f7108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f7109b;

    /* JADX INFO: renamed from: y1.a$a, reason: collision with other inner class name */
    public static class C0172a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i2) {
            return new a[i2];
        }
    }

    public a(Intent intent, String str) {
        this.f7108a = intent;
        this.f7109b = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f7108a, i2);
        parcel.writeString(this.f7109b);
    }

    public a(Parcel parcel) {
        this.f7108a = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.f7109b = parcel.readString();
    }
}
