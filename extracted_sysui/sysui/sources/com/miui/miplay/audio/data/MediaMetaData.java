package com.miui.miplay.audio.data;

import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaItemMetadata;

/* JADX INFO: loaded from: classes2.dex */
public class MediaMetaData implements Parcelable {
    public static final Parcelable.Creator<MediaMetaData> CREATOR = new Parcelable.Creator<MediaMetaData>() { // from class: com.miui.miplay.audio.data.MediaMetaData.1
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

    @Nullable
    private final String mAlbum;

    @Nullable
    private final Bitmap mArt;

    @Nullable
    private final String mArtist;
    private final String mAudioId;
    private final String mCodec;
    private final long mDuration;
    private final int mIsSequel;
    private String mMediaId;
    private final int mMediaType;
    private final String mMux;
    private final String mPackageId;
    private final String mPropertiesInfo;
    private final String mReverso;

    @Nullable
    private final String mTitle;
    private final String mTvId;
    private final String mVideoUrl;
    private final String mVideoUrn;

    public MediaMetaData() {
        this.mMediaId = "";
        this.mArtist = "";
        this.mAlbum = "";
        this.mTitle = "";
        this.mArt = null;
        this.mDuration = 0L;
        this.mMediaType = 0;
        this.mIsSequel = 1;
        this.mAudioId = "";
        this.mPackageId = "";
        this.mVideoUrl = "";
        this.mMux = "";
        this.mCodec = "";
        this.mReverso = "";
        this.mPropertiesInfo = "";
        this.mVideoUrn = "";
        this.mTvId = "";
    }

    public static boolean isMediaMetadataInvalid(@Nullable MediaMetadata mediaMetadata) {
        if (mediaMetadata == null) {
            return true;
        }
        return TextUtils.isEmpty(mediaMetadata.getString(MediaItemMetadata.KEY_ARTIST)) && TextUtils.isEmpty(mediaMetadata.getString(MediaItemMetadata.KEY_TITLE)) && TextUtils.isEmpty(mediaMetadata.getString("android.media.metadata.ALBUM")) && mediaMetadata.getLong(MediaItemMetadata.KEY_DURATION) <= 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public String getAlbum() {
        return this.mAlbum;
    }

    @Nullable
    public Bitmap getArt() {
        return this.mArt;
    }

    @Nullable
    public String getArtist() {
        return this.mArtist;
    }

    public String getAudioId() {
        return this.mAudioId;
    }

    public String getCodec() {
        return this.mCodec;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public int getMediaType() {
        return this.mMediaType;
    }

    public String getMux() {
        return this.mMux;
    }

    public String getPackageId() {
        return this.mPackageId;
    }

    public String getPropertiesInfo() {
        return this.mPropertiesInfo;
    }

    public String getReverso() {
        return this.mReverso;
    }

    public String getTVId() {
        return this.mTvId;
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    public String getVideoUrl() {
        return this.mVideoUrl;
    }

    public String getVideoUrn() {
        return this.mVideoUrn;
    }

    public boolean isSequel() {
        return this.mIsSequel == 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaMetaData{mArtist='");
        sb.append(this.mArtist);
        sb.append('\'');
        sb.append(", mAlbum='");
        sb.append(this.mAlbum);
        sb.append('\'');
        sb.append(", mTitle='");
        sb.append(this.mTitle);
        sb.append('\'');
        sb.append(", mDuration=");
        sb.append(this.mDuration);
        sb.append(", art:");
        sb.append(this.mArt == null ? "null" : "valid");
        sb.append(", mediaType:");
        sb.append(this.mMediaType);
        sb.append('\'');
        sb.append(", isSequel:");
        sb.append(this.mIsSequel);
        sb.append('\'');
        sb.append(", videoUrn:");
        sb.append(this.mVideoUrn);
        sb.append('\'');
        sb.append(", tvid:");
        sb.append(this.mTvId);
        sb.append('\'');
        sb.append(", mediaId:");
        sb.append(this.mMediaId);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mArtist);
        parcel.writeString(this.mAlbum);
        parcel.writeString(this.mTitle);
        parcel.writeParcelable(this.mArt, i2);
        parcel.writeLong(this.mDuration);
        parcel.writeInt(this.mMediaType);
        parcel.writeInt(this.mIsSequel);
        parcel.writeString(this.mAudioId);
        parcel.writeString(this.mPackageId);
        parcel.writeString(this.mVideoUrl);
        parcel.writeString(this.mMux);
        parcel.writeString(this.mCodec);
        parcel.writeString(this.mReverso);
        parcel.writeString(this.mPropertiesInfo);
        parcel.writeString(this.mVideoUrn);
        parcel.writeString(this.mTvId);
        parcel.writeString(this.mMediaId);
    }

    public MediaMetaData(@Nullable MediaMetadata mediaMetadata) {
        this.mMediaId = "";
        if (mediaMetadata != null) {
            Bitmap bitmap = mediaMetadata.getBitmap("android.media.metadata.ART");
            bitmap = bitmap == null ? mediaMetadata.getBitmap("android.media.metadata.ALBUM_ART") : bitmap;
            bitmap = bitmap == null ? mediaMetadata.getBitmap("android.media.metadata.DISPLAY_ICON") : bitmap;
            String string = mediaMetadata.getString("android.media.metadata.DISPLAY_TITLE");
            string = TextUtils.isEmpty(string) ? mediaMetadata.getString(MediaItemMetadata.KEY_TITLE) : string;
            this.mArtist = mediaMetadata.getString(MediaItemMetadata.KEY_ARTIST);
            this.mAlbum = mediaMetadata.getString("android.media.metadata.ALBUM");
            this.mDuration = mediaMetadata.getLong(MediaItemMetadata.KEY_DURATION);
            this.mMediaId = mediaMetadata.getString("android.media.metadata.MEDIA_ID");
            this.mTitle = string;
            this.mArt = bitmap;
            this.mMediaType = 0;
            this.mIsSequel = 1;
            this.mAudioId = "";
            this.mPackageId = "";
            this.mVideoUrl = "";
            this.mMux = "";
            this.mCodec = "";
            this.mReverso = "";
            this.mPropertiesInfo = "";
            this.mVideoUrn = "";
            this.mTvId = "";
            return;
        }
        this.mArtist = "";
        this.mAlbum = "";
        this.mTitle = "";
        this.mArt = null;
        this.mDuration = 0L;
        this.mMediaType = 0;
        this.mIsSequel = 1;
        this.mAudioId = "";
        this.mPackageId = "";
        this.mVideoUrl = "";
        this.mMux = "";
        this.mCodec = "";
        this.mReverso = "";
        this.mPropertiesInfo = "";
        this.mVideoUrn = "";
        this.mTvId = "";
    }

    public MediaMetaData(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Bitmap bitmap, long j2, int i2, int i3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.mMediaId = "";
        this.mArtist = str;
        this.mAlbum = str2;
        this.mTitle = str3;
        this.mArt = bitmap;
        this.mDuration = j2;
        this.mMediaType = i2;
        this.mIsSequel = i3;
        this.mAudioId = str4;
        this.mPackageId = str5;
        this.mVideoUrl = str6;
        this.mMux = str7;
        this.mCodec = str8;
        this.mReverso = str9;
        this.mPropertiesInfo = str10;
        this.mVideoUrn = str11;
        this.mTvId = str12;
    }

    public MediaMetaData(Parcel parcel) {
        this.mMediaId = "";
        this.mArtist = parcel.readString();
        this.mAlbum = parcel.readString();
        this.mTitle = parcel.readString();
        this.mArt = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mDuration = parcel.readLong();
        this.mMediaType = parcel.readInt();
        this.mIsSequel = parcel.readInt();
        this.mAudioId = parcel.readString();
        this.mPackageId = parcel.readString();
        this.mVideoUrl = parcel.readString();
        this.mMux = parcel.readString();
        this.mCodec = parcel.readString();
        this.mReverso = parcel.readString();
        this.mPropertiesInfo = parcel.readString();
        this.mVideoUrn = parcel.readString();
        this.mTvId = parcel.readString();
        this.mMediaId = parcel.readString();
    }
}
