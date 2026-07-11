package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaDescription;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.b;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.DoNotInline;
import androidx.core.os.BuildCompat;
import androidx.media.MediaSessionManager;
import androidx.media.VolumeProviderCompat;
import androidx.mediarouter.media.MediaItemMetadata;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaSessionCompat {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f1052d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f1053a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaControllerCompat f1054b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f1055c = new ArrayList();

    @SuppressLint({"BanParcelableUsage"})
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ResultReceiver f1059a;

        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper[] newArray(int i2) {
                return new ResultReceiverWrapper[i2];
            }
        }

        public ResultReceiverWrapper(Parcel parcel) {
            this.f1059a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            this.f1059a.writeToParcel(parcel, i2);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1060a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f1061b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public android.support.v4.media.session.b f1062c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public VersionedParcelable f1063d;

        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Token createFromParcel(Parcel parcel) {
                return new Token(parcel.readParcelable(null));
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Token[] newArray(int i2) {
                return new Token[i2];
            }
        }

        public Token(Object obj) {
            this(obj, null, null);
        }

        public static Token q(Object obj) {
            return r(obj, null);
        }

        public static Token r(Object obj, android.support.v4.media.session.b bVar) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof MediaSession.Token) {
                return new Token(obj, bVar);
            }
            throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.f1061b;
            if (obj2 == null) {
                return token.f1061b == null;
            }
            Object obj3 = token.f1061b;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        public int hashCode() {
            Object obj = this.f1061b;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public android.support.v4.media.session.b s() {
            android.support.v4.media.session.b bVar;
            synchronized (this.f1060a) {
                bVar = this.f1062c;
            }
            return bVar;
        }

        public Object t() {
            return this.f1061b;
        }

        public void u(android.support.v4.media.session.b bVar) {
            synchronized (this.f1060a) {
                this.f1062c = bVar;
            }
        }

        public void v(VersionedParcelable versionedParcelable) {
            synchronized (this.f1060a) {
                this.f1063d = versionedParcelable;
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeParcelable((Parcelable) this.f1061b, i2);
        }

        public Token(Object obj, android.support.v4.media.session.b bVar) {
            this(obj, bVar, null);
        }

        public Token(Object obj, android.support.v4.media.session.b bVar, VersionedParcelable versionedParcelable) {
            this.f1060a = new Object();
            this.f1061b = obj;
            this.f1062c = bVar;
            this.f1063d = versionedParcelable;
        }
    }

    public interface a {
        Token a();

        void b(int i2);

        Object c();

        void d(VolumeProviderCompat volumeProviderCompat);

        boolean isActive();
    }

    public static class b implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaSession f1064a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Token f1065b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bundle f1067d;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public PlaybackStateCompat f1070g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public MediaMetadataCompat f1071h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1072i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1073j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1074k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1075l;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Object f1066c = new Object();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1068e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final RemoteCallbackList f1069f = new RemoteCallbackList();

        public class a extends b.a {
            public a() {
            }

            @Override // android.support.v4.media.session.b
            public void C(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void D(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void E(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void G(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void G0(float f2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void I0(int i2, int i3, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean J(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void K(RatingCompat ratingCompat, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void K0(boolean z2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void L(MediaDescriptionCompat mediaDescriptionCompat, int i2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int P() {
                return b.this.f1075l;
            }

            @Override // android.support.v4.media.session.b
            public void P0(int i2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void Q(int i2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean R() {
                return b.this.f1073j;
            }

            @Override // android.support.v4.media.session.b
            public int S0() {
                return b.this.f1074k;
            }

            @Override // android.support.v4.media.session.b
            public void W(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void Z(long j2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void a(long j2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void a0(boolean z2) {
            }

            @Override // android.support.v4.media.session.b
            public void b0(int i2) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void d() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public long getFlags() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public String getPackageName() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat getPlaybackState() {
                b bVar = b.this;
                return MediaSessionCompat.f(bVar.f1070g, bVar.f1071h);
            }

            @Override // android.support.v4.media.session.b
            public List getQueue() {
                return null;
            }

            @Override // android.support.v4.media.session.b
            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public int getRatingType() {
                return b.this.f1072i;
            }

            @Override // android.support.v4.media.session.b
            public Bundle getSessionInfo() {
                if (b.this.f1067d == null) {
                    return null;
                }
                return new Bundle(b.this.f1067d);
            }

            @Override // android.support.v4.media.session.b
            public String getTag() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void h() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void i() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void k0(android.support.v4.media.session.a aVar) {
                if (b.this.f1068e) {
                    return;
                }
                b.this.f1069f.register(aVar, new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.b
            public void m(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void m0(RatingCompat ratingCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void next() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean o() {
                return false;
            }

            @Override // android.support.v4.media.session.b
            public void o0(int i2, int i3, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void p(Uri uri, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void prepare() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void previous() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void s0(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public boolean t0() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void u0(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void v0(String str, Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.b
            public void z0(android.support.v4.media.session.a aVar) {
                b.this.f1069f.unregister(aVar);
            }
        }

        public b(Object obj) {
            if (!(obj instanceof MediaSession)) {
                throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
            }
            MediaSession mediaSession = (MediaSession) obj;
            this.f1064a = mediaSession;
            this.f1065b = new Token(mediaSession.getSessionToken(), new a());
            this.f1067d = null;
            e(3);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.a
        public Token a() {
            return this.f1065b;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.a
        public void b(int i2) {
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(i2);
            this.f1064a.setPlaybackToLocal(builder.build());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.a
        public Object c() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.a
        public void d(VolumeProviderCompat volumeProviderCompat) {
            this.f1064a.setPlaybackToRemote((VolumeProvider) volumeProviderCompat.getVolumeProvider());
        }

        public void e(int i2) {
            this.f1064a.setFlags(i2 | 3);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.a
        public boolean isActive() {
            return this.f1064a.isActive();
        }
    }

    public static class c extends b {
        public c(Object obj) {
            super(obj);
        }
    }

    public static class d extends c {
        public d(Object obj) {
            super(obj);
        }
    }

    public static class e extends d {
        public e(Object obj) {
            super(obj);
            this.f1067d = ((MediaSession) obj).getController().getSessionInfo();
        }
    }

    public interface f {
    }

    static {
        f1052d = BuildCompat.isAtLeastS() ? 33554432 : 0;
    }

    public MediaSessionCompat(Context context, a aVar) {
        this.f1053a = aVar;
        this.f1054b = new MediaControllerCompat(context, this);
    }

    public static void a(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static MediaSessionCompat b(Context context, Object obj) {
        if (context == null || obj == null) {
            return null;
        }
        return new MediaSessionCompat(context, new e(obj));
    }

    public static PlaybackStateCompat f(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long jU = -1;
        if (playbackStateCompat.u() == -1) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.v() != 3 && playbackStateCompat.v() != 4 && playbackStateCompat.v() != 5) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.s() <= 0) {
            return playbackStateCompat;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jT = ((long) (playbackStateCompat.t() * (jElapsedRealtime - r0))) + playbackStateCompat.u();
        if (mediaMetadataCompat != null && mediaMetadataCompat.q(MediaItemMetadata.KEY_DURATION)) {
            jU = mediaMetadataCompat.u(MediaItemMetadata.KEY_DURATION);
        }
        return new PlaybackStateCompat.d(playbackStateCompat).b(playbackStateCompat.v(), (jU < 0 || jT <= jU) ? jT < 0 ? 0L : jT : jU, playbackStateCompat.t(), jElapsedRealtime).a();
    }

    public static Bundle j(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        a(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public MediaControllerCompat c() {
        return this.f1054b;
    }

    public Object d() {
        return this.f1053a.c();
    }

    public Token e() {
        return this.f1053a.a();
    }

    public boolean g() {
        return this.f1053a.isActive();
    }

    public void h(int i2) {
        this.f1053a.b(i2);
    }

    public void i(VolumeProviderCompat volumeProviderCompat) {
        if (volumeProviderCompat == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.f1053a.d(volumeProviderCompat);
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaDescriptionCompat f1056a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f1057b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public MediaSession.QueueItem f1058c;

        public class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public QueueItem[] newArray(int i2) {
                return new QueueItem[i2];
            }
        }

        public static class b {
            @DoNotInline
            public static MediaSession.QueueItem a(MediaDescription mediaDescription, long j2) {
                return new MediaSession.QueueItem(mediaDescription, j2);
            }

            @DoNotInline
            public static MediaDescription b(MediaSession.QueueItem queueItem) {
                return queueItem.getDescription();
            }

            @DoNotInline
            public static long c(MediaSession.QueueItem queueItem) {
                return queueItem.getQueueId();
            }
        }

        public QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j2) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            if (j2 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.f1056a = mediaDescriptionCompat;
            this.f1057b = j2;
            this.f1058c = queueItem;
        }

        public static QueueItem q(Object obj) {
            if (obj == null) {
                return null;
            }
            MediaSession.QueueItem queueItem = (MediaSession.QueueItem) obj;
            return new QueueItem(queueItem, MediaDescriptionCompat.q(b.b(queueItem)), b.c(queueItem));
        }

        public static List r(List list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(q(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.f1056a + ", Id=" + this.f1057b + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            this.f1056a.writeToParcel(parcel, i2);
            parcel.writeLong(this.f1057b);
        }

        public QueueItem(Parcel parcel) {
            this.f1056a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.f1057b = parcel.readLong();
        }
    }
}
