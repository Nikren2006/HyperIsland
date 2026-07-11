package com.miui.miplay.audio.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class AppMetaData implements Parcelable {
    public static final Parcelable.Creator<AppMetaData> CREATOR = new Parcelable.Creator<AppMetaData>() { // from class: com.miui.miplay.audio.data.AppMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppMetaData createFromParcel(Parcel parcel) {
            return new AppMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppMetaData[] newArray(int i2) {
            return new AppMetaData[i2];
        }
    };

    @NonNull
    private final String mPackageName;
    private final int mUid;

    public AppMetaData(@NonNull String str, int i2) {
        this.mPackageName = str;
        this.mUid = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String getPackageName() {
        return this.mPackageName;
    }

    public int getUid() {
        return this.mUid;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mPackageName);
        parcel.writeInt(this.mUid);
    }

    public AppMetaData(Parcel parcel) {
        this.mPackageName = parcel.readString();
        this.mUid = parcel.readInt();
    }
}
