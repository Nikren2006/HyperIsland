package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.media.utils.MediaConstants;
import androidx.mediarouter.media.MediaItemMetadata;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ArrayMap f1024d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f1025e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String[] f1026f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String[] f1027g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f1028a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public MediaMetadata f1029b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public MediaDescriptionCompat f1030c;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaMetadataCompat createFromParcel(Parcel parcel) {
            return new MediaMetadataCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaMetadataCompat[] newArray(int i2) {
            return new MediaMetadataCompat[i2];
        }
    }

    static {
        ArrayMap arrayMap = new ArrayMap();
        f1024d = arrayMap;
        arrayMap.put(MediaItemMetadata.KEY_TITLE, 1);
        arrayMap.put(MediaItemMetadata.KEY_ARTIST, 1);
        arrayMap.put(MediaItemMetadata.KEY_DURATION, 0);
        arrayMap.put("android.media.metadata.ALBUM", 1);
        arrayMap.put(MediaItemMetadata.KEY_AUTHOR, 1);
        arrayMap.put("android.media.metadata.WRITER", 1);
        arrayMap.put(MediaItemMetadata.KEY_COMPOSER, 1);
        arrayMap.put("android.media.metadata.COMPILATION", 1);
        arrayMap.put("android.media.metadata.DATE", 1);
        arrayMap.put(MediaItemMetadata.KEY_YEAR, 0);
        arrayMap.put("android.media.metadata.GENRE", 1);
        arrayMap.put(MediaItemMetadata.KEY_TRACK_NUMBER, 0);
        arrayMap.put("android.media.metadata.NUM_TRACKS", 0);
        arrayMap.put(MediaItemMetadata.KEY_DISC_NUMBER, 0);
        arrayMap.put(MediaItemMetadata.KEY_ALBUM_ARTIST, 1);
        arrayMap.put("android.media.metadata.ART", 2);
        arrayMap.put("android.media.metadata.ART_URI", 1);
        arrayMap.put("android.media.metadata.ALBUM_ART", 2);
        arrayMap.put("android.media.metadata.ALBUM_ART_URI", 1);
        arrayMap.put("android.media.metadata.USER_RATING", 3);
        arrayMap.put("android.media.metadata.RATING", 3);
        arrayMap.put("android.media.metadata.DISPLAY_TITLE", 1);
        arrayMap.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        arrayMap.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        arrayMap.put("android.media.metadata.DISPLAY_ICON", 2);
        arrayMap.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        arrayMap.put("android.media.metadata.MEDIA_ID", 1);
        arrayMap.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        arrayMap.put("android.media.metadata.MEDIA_URI", 1);
        arrayMap.put(MediaConstants.METADATA_KEY_IS_ADVERTISEMENT, 0);
        arrayMap.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        f1025e = new String[]{MediaItemMetadata.KEY_TITLE, MediaItemMetadata.KEY_ARTIST, "android.media.metadata.ALBUM", MediaItemMetadata.KEY_ALBUM_ARTIST, "android.media.metadata.WRITER", MediaItemMetadata.KEY_AUTHOR, MediaItemMetadata.KEY_COMPOSER};
        f1026f = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        f1027g = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        CREATOR = new a();
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.f1028a = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public static MediaMetadataCompat r(Object obj) {
        if (obj == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        mediaMetadata.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompatCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        mediaMetadataCompatCreateFromParcel.f1029b = mediaMetadata;
        return mediaMetadataCompatCreateFromParcel;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean q(String str) {
        return this.f1028a.containsKey(str);
    }

    public Bitmap s(String str) {
        try {
            return (Bitmap) this.f1028a.getParcelable(str);
        } catch (Exception e2) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", e2);
            return null;
        }
    }

    public MediaDescriptionCompat t() {
        Bitmap bitmapS;
        Uri uri;
        MediaDescriptionCompat mediaDescriptionCompat = this.f1030c;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String strV = v("android.media.metadata.MEDIA_ID");
        CharSequence[] charSequenceArr = new CharSequence[3];
        CharSequence charSequenceW = w("android.media.metadata.DISPLAY_TITLE");
        if (TextUtils.isEmpty(charSequenceW)) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < 3) {
                String[] strArr = f1025e;
                if (i3 >= strArr.length) {
                    break;
                }
                int i4 = i3 + 1;
                CharSequence charSequenceW2 = w(strArr[i3]);
                if (!TextUtils.isEmpty(charSequenceW2)) {
                    charSequenceArr[i2] = charSequenceW2;
                    i2++;
                }
                i3 = i4;
            }
        } else {
            charSequenceArr[0] = charSequenceW;
            charSequenceArr[1] = w("android.media.metadata.DISPLAY_SUBTITLE");
            charSequenceArr[2] = w("android.media.metadata.DISPLAY_DESCRIPTION");
        }
        int i5 = 0;
        while (true) {
            String[] strArr2 = f1026f;
            if (i5 >= strArr2.length) {
                bitmapS = null;
                break;
            }
            bitmapS = s(strArr2[i5]);
            if (bitmapS != null) {
                break;
            }
            i5++;
        }
        int i6 = 0;
        while (true) {
            String[] strArr3 = f1027g;
            if (i6 >= strArr3.length) {
                uri = null;
                break;
            }
            String strV2 = v(strArr3[i6]);
            if (!TextUtils.isEmpty(strV2)) {
                uri = Uri.parse(strV2);
                break;
            }
            i6++;
        }
        String strV3 = v("android.media.metadata.MEDIA_URI");
        Uri uri2 = TextUtils.isEmpty(strV3) ? null : Uri.parse(strV3);
        MediaDescriptionCompat.d dVar = new MediaDescriptionCompat.d();
        dVar.f(strV);
        dVar.i(charSequenceArr[0]);
        dVar.h(charSequenceArr[1]);
        dVar.b(charSequenceArr[2]);
        dVar.d(bitmapS);
        dVar.e(uri);
        dVar.g(uri2);
        Bundle bundle = new Bundle();
        if (this.f1028a.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            bundle.putLong("android.media.extra.BT_FOLDER_TYPE", u("android.media.metadata.BT_FOLDER_TYPE"));
        }
        if (this.f1028a.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            bundle.putLong("android.media.extra.DOWNLOAD_STATUS", u("android.media.metadata.DOWNLOAD_STATUS"));
        }
        if (!bundle.isEmpty()) {
            dVar.c(bundle);
        }
        MediaDescriptionCompat mediaDescriptionCompatA = dVar.a();
        this.f1030c = mediaDescriptionCompatA;
        return mediaDescriptionCompatA;
    }

    public long u(String str) {
        return this.f1028a.getLong(str, 0L);
    }

    public String v(String str) {
        CharSequence charSequence = this.f1028a.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence w(String str) {
        return this.f1028a.getCharSequence(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.f1028a);
    }
}
