package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f1008b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CharSequence f1009c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f1010d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bitmap f1011e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Uri f1012f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bundle f1013g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Uri f1014h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public MediaDescription f1015i;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return MediaDescriptionCompat.q(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i2) {
            return new MediaDescriptionCompat[i2];
        }
    }

    public static class b {
        @DoNotInline
        public static MediaDescription a(MediaDescription.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        public static MediaDescription.Builder b() {
            return new MediaDescription.Builder();
        }

        @Nullable
        @DoNotInline
        public static CharSequence c(MediaDescription mediaDescription) {
            return mediaDescription.getDescription();
        }

        @Nullable
        @DoNotInline
        public static Bundle d(MediaDescription mediaDescription) {
            return mediaDescription.getExtras();
        }

        @Nullable
        @DoNotInline
        public static Bitmap e(MediaDescription mediaDescription) {
            return mediaDescription.getIconBitmap();
        }

        @Nullable
        @DoNotInline
        public static Uri f(MediaDescription mediaDescription) {
            return mediaDescription.getIconUri();
        }

        @Nullable
        @DoNotInline
        public static String g(MediaDescription mediaDescription) {
            return mediaDescription.getMediaId();
        }

        @Nullable
        @DoNotInline
        public static CharSequence h(MediaDescription mediaDescription) {
            return mediaDescription.getSubtitle();
        }

        @Nullable
        @DoNotInline
        public static CharSequence i(MediaDescription mediaDescription) {
            return mediaDescription.getTitle();
        }

        @DoNotInline
        public static void j(MediaDescription.Builder builder, @Nullable CharSequence charSequence) {
            builder.setDescription(charSequence);
        }

        @DoNotInline
        public static void k(MediaDescription.Builder builder, @Nullable Bundle bundle) {
            builder.setExtras(bundle);
        }

        @DoNotInline
        public static void l(MediaDescription.Builder builder, @Nullable Bitmap bitmap) {
            builder.setIconBitmap(bitmap);
        }

        @DoNotInline
        public static void m(MediaDescription.Builder builder, @Nullable Uri uri) {
            builder.setIconUri(uri);
        }

        @DoNotInline
        public static void n(MediaDescription.Builder builder, @Nullable String str) {
            builder.setMediaId(str);
        }

        @DoNotInline
        public static void o(MediaDescription.Builder builder, @Nullable CharSequence charSequence) {
            builder.setSubtitle(charSequence);
        }

        @DoNotInline
        public static void p(MediaDescription.Builder builder, @Nullable CharSequence charSequence) {
            builder.setTitle(charSequence);
        }
    }

    public static class c {
        @Nullable
        @DoNotInline
        public static Uri a(MediaDescription mediaDescription) {
            return mediaDescription.getMediaUri();
        }

        @DoNotInline
        public static void b(MediaDescription.Builder builder, @Nullable Uri uri) {
            builder.setMediaUri(uri);
        }
    }

    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1016a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public CharSequence f1017b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f1018c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f1019d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bitmap f1020e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Uri f1021f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Bundle f1022g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Uri f1023h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.f1016a, this.f1017b, this.f1018c, this.f1019d, this.f1020e, this.f1021f, this.f1022g, this.f1023h);
        }

        public d b(CharSequence charSequence) {
            this.f1019d = charSequence;
            return this;
        }

        public d c(Bundle bundle) {
            this.f1022g = bundle;
            return this;
        }

        public d d(Bitmap bitmap) {
            this.f1020e = bitmap;
            return this;
        }

        public d e(Uri uri) {
            this.f1021f = uri;
            return this;
        }

        public d f(String str) {
            this.f1016a = str;
            return this;
        }

        public d g(Uri uri) {
            this.f1023h = uri;
            return this;
        }

        public d h(CharSequence charSequence) {
            this.f1018c = charSequence;
            return this;
        }

        public d i(CharSequence charSequence) {
            this.f1017b = charSequence;
            return this;
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f1007a = str;
        this.f1008b = charSequence;
        this.f1009c = charSequence2;
        this.f1010d = charSequence3;
        this.f1011e = bitmap;
        this.f1012f = uri;
        this.f1013g = bundle;
        this.f1014h = uri2;
    }

    public static MediaDescriptionCompat q(Object obj) {
        Bundle bundle = null;
        if (obj == null) {
            return null;
        }
        d dVar = new d();
        MediaDescription mediaDescription = (MediaDescription) obj;
        dVar.f(b.g(mediaDescription));
        dVar.i(b.i(mediaDescription));
        dVar.h(b.h(mediaDescription));
        dVar.b(b.c(mediaDescription));
        dVar.d(b.e(mediaDescription));
        dVar.e(b.f(mediaDescription));
        Bundle bundleD = b.d(mediaDescription);
        if (bundleD != null) {
            bundleD = MediaSessionCompat.j(bundleD);
        }
        Uri uri = bundleD != null ? (Uri) bundleD.getParcelable("android.support.v4.media.description.MEDIA_URI") : null;
        if (uri == null) {
            bundle = bundleD;
        } else if (!bundleD.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") || bundleD.size() != 2) {
            bundleD.remove("android.support.v4.media.description.MEDIA_URI");
            bundleD.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = bundleD;
        }
        dVar.c(bundle);
        if (uri != null) {
            dVar.g(uri);
        } else {
            dVar.g(c.a(mediaDescription));
        }
        MediaDescriptionCompat mediaDescriptionCompatA = dVar.a();
        mediaDescriptionCompatA.f1015i = mediaDescription;
        return mediaDescriptionCompatA;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap r() {
        return this.f1011e;
    }

    public Uri s() {
        return this.f1012f;
    }

    public Object t() {
        MediaDescription mediaDescription = this.f1015i;
        if (mediaDescription != null) {
            return mediaDescription;
        }
        MediaDescription.Builder builderB = b.b();
        b.n(builderB, this.f1007a);
        b.p(builderB, this.f1008b);
        b.o(builderB, this.f1009c);
        b.j(builderB, this.f1010d);
        b.l(builderB, this.f1011e);
        b.m(builderB, this.f1012f);
        b.k(builderB, this.f1013g);
        c.b(builderB, this.f1014h);
        MediaDescription mediaDescriptionA = b.a(builderB);
        this.f1015i = mediaDescriptionA;
        return mediaDescriptionA;
    }

    public String toString() {
        return ((Object) this.f1008b) + ", " + ((Object) this.f1009c) + ", " + ((Object) this.f1010d);
    }

    public CharSequence u() {
        return this.f1009c;
    }

    public CharSequence v() {
        return this.f1008b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        ((MediaDescription) t()).writeToParcel(parcel, i2);
    }
}
