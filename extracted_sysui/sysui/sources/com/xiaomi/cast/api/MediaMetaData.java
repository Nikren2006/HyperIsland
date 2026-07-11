package com.xiaomi.cast.api;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaMetaData implements Parcelable {
    public static final Parcelable.Creator<MediaMetaData> CREATOR = new Parcelable.Creator<MediaMetaData>() { // from class: com.xiaomi.cast.api.MediaMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaMetaData createFromParcel(Parcel parcel) {
            return new MediaMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaMetaData[] newArray(int i2) {
            return new MediaMetaData[i2];
        }
    };
    private String mAlbum;
    private String mArtist;
    private Bitmap mBitmap;
    private long mDuration;
    private boolean mIsSequel;
    private int mMediaType;
    private String mTitle;

    public MediaMetaData() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public int getMediaType() {
        return this.mMediaType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isIsSequel() {
        return this.mIsSequel;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setDuration(long j2) {
        this.mDuration = j2;
    }

    public void setIsSequel(boolean z2) {
        this.mIsSequel = z2;
    }

    public void setMediaType(int i2) {
        this.mMediaType = i2;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String toString() {
        return "MediaMetaData{mArtist='" + this.mArtist + "', mAlbum='" + this.mAlbum + "', mTitle='" + this.mTitle + "', mBitmap=" + this.mBitmap + ", mDuration=" + this.mDuration + ", mMediaType=" + this.mMediaType + ", mIsSequel=" + this.mIsSequel + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mArtist);
        parcel.writeString(this.mAlbum);
        parcel.writeString(this.mTitle);
        parcel.writeParcelable(this.mBitmap, i2);
        parcel.writeLong(this.mDuration);
        parcel.writeInt(this.mMediaType);
        parcel.writeByte(this.mIsSequel ? (byte) 1 : (byte) 0);
    }

    public MediaMetaData(Parcel parcel) {
        this.mArtist = parcel.readString();
        this.mAlbum = parcel.readString();
        this.mTitle = parcel.readString();
        this.mBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mDuration = parcel.readLong();
        this.mMediaType = parcel.readInt();
        this.mIsSequel = parcel.readByte() != 0;
    }
}
