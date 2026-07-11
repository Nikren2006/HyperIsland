package miui.systemui.controlcenter.media;

import android.graphics.Bitmap;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerMetaData {
    private CharSequence album;
    private Bitmap art;
    private CharSequence artist;
    private long duration;
    private String mediaId;
    private CharSequence title;
    private String tvId;

    public MediaPlayerMetaData(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, long j2, String str, String str2) {
        this.artist = charSequence;
        this.album = charSequence2;
        this.title = charSequence3;
        this.art = bitmap;
        this.duration = j2;
        this.mediaId = str;
        this.tvId = str2;
    }

    public final CharSequence component1() {
        return this.artist;
    }

    public final CharSequence component2() {
        return this.album;
    }

    public final CharSequence component3() {
        return this.title;
    }

    public final Bitmap component4() {
        return this.art;
    }

    public final long component5() {
        return this.duration;
    }

    public final String component6() {
        return this.mediaId;
    }

    public final String component7() {
        return this.tvId;
    }

    public final MediaPlayerMetaData copy(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, long j2, String str, String str2) {
        return new MediaPlayerMetaData(charSequence, charSequence2, charSequence3, bitmap, j2, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPlayerMetaData)) {
            return false;
        }
        MediaPlayerMetaData mediaPlayerMetaData = (MediaPlayerMetaData) obj;
        return n.c(this.artist, mediaPlayerMetaData.artist) && n.c(this.album, mediaPlayerMetaData.album) && n.c(this.title, mediaPlayerMetaData.title) && n.c(this.art, mediaPlayerMetaData.art) && this.duration == mediaPlayerMetaData.duration && n.c(this.mediaId, mediaPlayerMetaData.mediaId) && n.c(this.tvId, mediaPlayerMetaData.tvId);
    }

    public final CharSequence getAlbum() {
        return this.album;
    }

    public final Bitmap getArt() {
        return this.art;
    }

    public final CharSequence getArtist() {
        return this.artist;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final CharSequence getTitle() {
        return this.title;
    }

    public final String getTvId() {
        return this.tvId;
    }

    public int hashCode() {
        CharSequence charSequence = this.artist;
        int iHashCode = (charSequence == null ? 0 : charSequence.hashCode()) * 31;
        CharSequence charSequence2 = this.album;
        int iHashCode2 = (iHashCode + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
        CharSequence charSequence3 = this.title;
        int iHashCode3 = (iHashCode2 + (charSequence3 == null ? 0 : charSequence3.hashCode())) * 31;
        Bitmap bitmap = this.art;
        int iHashCode4 = (((iHashCode3 + (bitmap == null ? 0 : bitmap.hashCode())) * 31) + Long.hashCode(this.duration)) * 31;
        String str = this.mediaId;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.tvId;
        return iHashCode5 + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setAlbum(CharSequence charSequence) {
        this.album = charSequence;
    }

    public final void setArt(Bitmap bitmap) {
        this.art = bitmap;
    }

    public final void setArtist(CharSequence charSequence) {
        this.artist = charSequence;
    }

    public final void setDuration(long j2) {
        this.duration = j2;
    }

    public final void setMediaId(String str) {
        this.mediaId = str;
    }

    public final void setTitle(CharSequence charSequence) {
        this.title = charSequence;
    }

    public final void setTvId(String str) {
        this.tvId = str;
    }

    public String toString() {
        CharSequence charSequence = this.artist;
        CharSequence charSequence2 = this.album;
        CharSequence charSequence3 = this.title;
        return "MediaPlayerMetaData(artist=" + ((Object) charSequence) + ", album=" + ((Object) charSequence2) + ", title=" + ((Object) charSequence3) + ", art=" + this.art + ", duration=" + this.duration + ", mediaId=" + this.mediaId + ", tvId=" + this.tvId + ")";
    }
}
