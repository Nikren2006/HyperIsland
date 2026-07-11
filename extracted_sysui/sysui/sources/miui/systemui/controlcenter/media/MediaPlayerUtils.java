package miui.systemui.controlcenter.media;

import android.content.Context;
import android.text.TextUtils;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerUtils {
    public static final MediaPlayerUtils INSTANCE = new MediaPlayerUtils();

    private MediaPlayerUtils() {
    }

    public final CharSequence betterArtistAlbum(MediaPlayerMetaData mediaPlayerMetaData) {
        n.g(mediaPlayerMetaData, "<this>");
        if (TextUtils.isEmpty(mediaPlayerMetaData.getArtist()) || TextUtils.isEmpty(mediaPlayerMetaData.getAlbum())) {
            return !TextUtils.isEmpty(mediaPlayerMetaData.getArtist()) ? mediaPlayerMetaData.getArtist() : !TextUtils.isEmpty(mediaPlayerMetaData.getAlbum()) ? mediaPlayerMetaData.getAlbum() : "";
        }
        return ((Object) mediaPlayerMetaData.getArtist()) + " - " + ((Object) mediaPlayerMetaData.getAlbum());
    }

    public final CharSequence betterTitle(MediaPlayerMetaData mediaPlayerMetaData, Context context) {
        n.g(mediaPlayerMetaData, "<this>");
        n.g(context, "context");
        return TextUtils.isEmpty(mediaPlayerMetaData.getTitle()) ? "暂无歌名" : mediaPlayerMetaData.getTitle();
    }
}
