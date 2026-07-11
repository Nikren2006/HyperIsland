package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1079c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1080d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1081e;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
            return new ParcelableVolumeInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelableVolumeInfo[] newArray(int i2) {
            return new ParcelableVolumeInfo[i2];
        }
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.f1077a = parcel.readInt();
        this.f1079c = parcel.readInt();
        this.f1080d = parcel.readInt();
        this.f1081e = parcel.readInt();
        this.f1078b = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f1077a);
        parcel.writeInt(this.f1079c);
        parcel.writeInt(this.f1080d);
        parcel.writeInt(this.f1081e);
        parcel.writeInt(this.f1078b);
    }
}
