package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.DoNotInline;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1083b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1084c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1085d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f1086e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1087f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CharSequence f1088g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f1089h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List f1090i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f1091j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Bundle f1092k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public PlaybackState f1093l;

    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlaybackStateCompat[] newArray(int i2) {
            return new PlaybackStateCompat[i2];
        }
    }

    public static class b {
        @DoNotInline
        public static void a(PlaybackState.Builder builder, PlaybackState.CustomAction customAction) {
            builder.addCustomAction(customAction);
        }

        @DoNotInline
        public static PlaybackState.CustomAction b(PlaybackState.CustomAction.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        public static PlaybackState c(PlaybackState.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        public static PlaybackState.Builder d() {
            return new PlaybackState.Builder();
        }

        @DoNotInline
        public static PlaybackState.CustomAction.Builder e(String str, CharSequence charSequence, int i2) {
            return new PlaybackState.CustomAction.Builder(str, charSequence, i2);
        }

        @DoNotInline
        public static String f(PlaybackState.CustomAction customAction) {
            return customAction.getAction();
        }

        @DoNotInline
        public static long g(PlaybackState playbackState) {
            return playbackState.getActions();
        }

        @DoNotInline
        public static long h(PlaybackState playbackState) {
            return playbackState.getActiveQueueItemId();
        }

        @DoNotInline
        public static long i(PlaybackState playbackState) {
            return playbackState.getBufferedPosition();
        }

        @DoNotInline
        public static List<PlaybackState.CustomAction> j(PlaybackState playbackState) {
            return playbackState.getCustomActions();
        }

        @DoNotInline
        public static CharSequence k(PlaybackState playbackState) {
            return playbackState.getErrorMessage();
        }

        @DoNotInline
        public static Bundle l(PlaybackState.CustomAction customAction) {
            return customAction.getExtras();
        }

        @DoNotInline
        public static int m(PlaybackState.CustomAction customAction) {
            return customAction.getIcon();
        }

        @DoNotInline
        public static long n(PlaybackState playbackState) {
            return playbackState.getLastPositionUpdateTime();
        }

        @DoNotInline
        public static CharSequence o(PlaybackState.CustomAction customAction) {
            return customAction.getName();
        }

        @DoNotInline
        public static float p(PlaybackState playbackState) {
            return playbackState.getPlaybackSpeed();
        }

        @DoNotInline
        public static long q(PlaybackState playbackState) {
            return playbackState.getPosition();
        }

        @DoNotInline
        public static int r(PlaybackState playbackState) {
            return playbackState.getState();
        }

        @DoNotInline
        public static void s(PlaybackState.Builder builder, long j2) {
            builder.setActions(j2);
        }

        @DoNotInline
        public static void t(PlaybackState.Builder builder, long j2) {
            builder.setActiveQueueItemId(j2);
        }

        @DoNotInline
        public static void u(PlaybackState.Builder builder, long j2) {
            builder.setBufferedPosition(j2);
        }

        @DoNotInline
        public static void v(PlaybackState.Builder builder, CharSequence charSequence) {
            builder.setErrorMessage(charSequence);
        }

        @DoNotInline
        public static void w(PlaybackState.CustomAction.Builder builder, Bundle bundle) {
            builder.setExtras(bundle);
        }

        @DoNotInline
        public static void x(PlaybackState.Builder builder, int i2, long j2, float f2, long j3) {
            builder.setState(i2, j2, f2, j3);
        }
    }

    public static class c {
        @DoNotInline
        public static Bundle a(PlaybackState playbackState) {
            return playbackState.getExtras();
        }

        @DoNotInline
        public static void b(PlaybackState.Builder builder, Bundle bundle) {
            builder.setExtras(bundle);
        }
    }

    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List f1099a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1100b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1101c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1102d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1103e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1104f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1105g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public CharSequence f1106h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f1107i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public long f1108j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public Bundle f1109k;

        public d(PlaybackStateCompat playbackStateCompat) {
            ArrayList arrayList = new ArrayList();
            this.f1099a = arrayList;
            this.f1108j = -1L;
            this.f1100b = playbackStateCompat.f1082a;
            this.f1101c = playbackStateCompat.f1083b;
            this.f1103e = playbackStateCompat.f1085d;
            this.f1107i = playbackStateCompat.f1089h;
            this.f1102d = playbackStateCompat.f1084c;
            this.f1104f = playbackStateCompat.f1086e;
            this.f1105g = playbackStateCompat.f1087f;
            this.f1106h = playbackStateCompat.f1088g;
            List list = playbackStateCompat.f1090i;
            if (list != null) {
                arrayList.addAll(list);
            }
            this.f1108j = playbackStateCompat.f1091j;
            this.f1109k = playbackStateCompat.f1092k;
        }

        public PlaybackStateCompat a() {
            return new PlaybackStateCompat(this.f1100b, this.f1101c, this.f1102d, this.f1103e, this.f1104f, this.f1105g, this.f1106h, this.f1107i, this.f1099a, this.f1108j, this.f1109k);
        }

        public d b(int i2, long j2, float f2, long j3) {
            this.f1100b = i2;
            this.f1101c = j2;
            this.f1107i = j3;
            this.f1103e = f2;
            return this;
        }
    }

    public PlaybackStateCompat(int i2, long j2, long j3, float f2, long j4, int i3, CharSequence charSequence, long j5, List list, long j6, Bundle bundle) {
        this.f1082a = i2;
        this.f1083b = j2;
        this.f1084c = j3;
        this.f1085d = f2;
        this.f1086e = j4;
        this.f1087f = i3;
        this.f1088g = charSequence;
        this.f1089h = j5;
        this.f1090i = new ArrayList(list);
        this.f1091j = j6;
        this.f1092k = bundle;
    }

    public static PlaybackStateCompat q(Object obj) {
        ArrayList arrayList = null;
        if (obj == null) {
            return null;
        }
        PlaybackState playbackState = (PlaybackState) obj;
        List<PlaybackState.CustomAction> listJ = b.j(playbackState);
        if (listJ != null) {
            arrayList = new ArrayList(listJ.size());
            Iterator<PlaybackState.CustomAction> it = listJ.iterator();
            while (it.hasNext()) {
                arrayList.add(CustomAction.q(it.next()));
            }
        }
        Bundle bundleA = c.a(playbackState);
        MediaSessionCompat.a(bundleA);
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(b.r(playbackState), b.q(playbackState), b.i(playbackState), b.p(playbackState), b.g(playbackState), 0, b.k(playbackState), b.n(playbackState), arrayList, b.h(playbackState), bundleA);
        playbackStateCompat.f1093l = playbackState;
        return playbackStateCompat;
    }

    public static int w(long j2) {
        if (j2 == 4) {
            return 126;
        }
        if (j2 == 2) {
            return 127;
        }
        if (j2 == 32) {
            return 87;
        }
        if (j2 == 16) {
            return 88;
        }
        if (j2 == 1) {
            return 86;
        }
        if (j2 == 64) {
            return 90;
        }
        if (j2 == 8) {
            return 89;
        }
        return j2 == 512 ? 85 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long r() {
        return this.f1086e;
    }

    public long s() {
        return this.f1089h;
    }

    public float t() {
        return this.f1085d;
    }

    public String toString() {
        return "PlaybackState {state=" + this.f1082a + ", position=" + this.f1083b + ", buffered position=" + this.f1084c + ", speed=" + this.f1085d + ", updated=" + this.f1089h + ", actions=" + this.f1086e + ", error code=" + this.f1087f + ", error message=" + this.f1088g + ", custom actions=" + this.f1090i + ", active item id=" + this.f1091j + "}";
    }

    public long u() {
        return this.f1083b;
    }

    public int v() {
        return this.f1082a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f1082a);
        parcel.writeLong(this.f1083b);
        parcel.writeFloat(this.f1085d);
        parcel.writeLong(this.f1089h);
        parcel.writeLong(this.f1084c);
        parcel.writeLong(this.f1086e);
        TextUtils.writeToParcel(this.f1088g, parcel, i2);
        parcel.writeTypedList(this.f1090i);
        parcel.writeLong(this.f1091j);
        parcel.writeBundle(this.f1092k);
        parcel.writeInt(this.f1087f);
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1094a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CharSequence f1095b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1096c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Bundle f1097d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public PlaybackState.CustomAction f1098e;

        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public CustomAction[] newArray(int i2) {
                return new CustomAction[i2];
            }
        }

        public CustomAction(String str, CharSequence charSequence, int i2, Bundle bundle) {
            this.f1094a = str;
            this.f1095b = charSequence;
            this.f1096c = i2;
            this.f1097d = bundle;
        }

        public static CustomAction q(Object obj) {
            if (obj == null) {
                return null;
            }
            PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
            Bundle bundleL = b.l(customAction);
            MediaSessionCompat.a(bundleL);
            CustomAction customAction2 = new CustomAction(b.f(customAction), b.o(customAction), b.m(customAction), bundleL);
            customAction2.f1098e = customAction;
            return customAction2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.f1095b) + ", mIcon=" + this.f1096c + ", mExtras=" + this.f1097d;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f1094a);
            TextUtils.writeToParcel(this.f1095b, parcel, i2);
            parcel.writeInt(this.f1096c);
            parcel.writeBundle(this.f1097d);
        }

        public CustomAction(Parcel parcel) {
            this.f1094a = parcel.readString();
            this.f1095b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f1096c = parcel.readInt();
            this.f1097d = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f1082a = parcel.readInt();
        this.f1083b = parcel.readLong();
        this.f1085d = parcel.readFloat();
        this.f1089h = parcel.readLong();
        this.f1084c = parcel.readLong();
        this.f1086e = parcel.readLong();
        this.f1088g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1090i = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f1091j = parcel.readLong();
        this.f1092k = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.f1087f = parcel.readInt();
    }
}
