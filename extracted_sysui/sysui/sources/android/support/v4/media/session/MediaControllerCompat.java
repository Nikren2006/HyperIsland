package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.a;
import android.support.v4.media.session.b;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.media.AudioAttributesCompat;
import androidx.versionedparcelable.ParcelUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1033a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaSessionCompat.Token f1034b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap f1035c = new ConcurrentHashMap();

    public static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaController f1036a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f1037b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List f1038c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public HashMap f1039d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final MediaSessionCompat.Token f1040e;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public WeakReference f1041a;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f1041a = new WeakReference(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i2, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = (MediaControllerImplApi21) this.f1041a.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f1037b) {
                    mediaControllerImplApi21.f1040e.u(b.a.Z0(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.f1040e.v(ParcelUtils.getVersionedParcelable(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.f();
                }
            }
        }

        public static class a extends a.c {
            public a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void M(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void f0(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void onExtrasChanged(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void onQueueTitleChanged(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void onSessionDestroyed() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void z(List list) {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.f1040e = token;
            this.f1036a = new MediaController(context, (MediaSession.Token) token.t());
            if (token.s() == null) {
                g();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void a(a aVar) {
            this.f1036a.unregisterCallback(aVar.mCallbackFwk);
            synchronized (this.f1037b) {
                if (this.f1040e.s() != null) {
                    try {
                        a aVar2 = (a) this.f1039d.remove(aVar);
                        if (aVar2 != null) {
                            aVar.mIControllerCallback = null;
                            this.f1040e.s().z0(aVar2);
                        }
                    } catch (RemoteException e2) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e2);
                    }
                }
                this.f1038c.remove(aVar);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent b() {
            return this.f1036a.getSessionActivity();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public e c() {
            return new i(this.f1036a.getTransportControls());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean d(KeyEvent keyEvent) {
            return this.f1036a.dispatchMediaButtonEvent(keyEvent);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void e(a aVar, Handler handler) {
            this.f1036a.registerCallback(aVar.mCallbackFwk, handler);
            synchronized (this.f1037b) {
                if (this.f1040e.s() != null) {
                    a aVar2 = new a(aVar);
                    this.f1039d.put(aVar, aVar2);
                    aVar.mIControllerCallback = aVar2;
                    try {
                        this.f1040e.s().k0(aVar2);
                        aVar.postToHandler(13, null, null);
                    } catch (RemoteException e2) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                    }
                } else {
                    aVar.mIControllerCallback = null;
                    this.f1038c.add(aVar);
                }
            }
        }

        public void f() {
            if (this.f1040e.s() == null) {
                return;
            }
            for (a aVar : this.f1038c) {
                a aVar2 = new a(aVar);
                this.f1039d.put(aVar, aVar2);
                aVar.mIControllerCallback = aVar2;
                try {
                    this.f1040e.s().k0(aVar2);
                    aVar.postToHandler(13, null, null);
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                }
            }
            this.f1038c.clear();
        }

        public final void g() {
            h("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata = this.f1036a.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.r(metadata);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat getPlaybackState() {
            if (this.f1040e.s() != null) {
                try {
                    return this.f1040e.s().getPlaybackState();
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e2);
                }
            }
            PlaybackState playbackState = this.f1036a.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.q(playbackState);
            }
            return null;
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.f1036a.sendCommand(str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {
        final MediaController.Callback mCallbackFwk = new C0032a(this);
        b mHandler;
        android.support.v4.media.session.a mIControllerCallback;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        public static class C0032a extends MediaController.Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference f1042a;

            public C0032a(a aVar) {
                this.f1042a = new WeakReference(aVar);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onAudioInfoChanged(new d(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.a(bundle);
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onExtrasChanged(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onMetadataChanged(MediaMetadataCompat.r(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                a aVar = (a) this.f1042a.get();
                if (aVar == null || aVar.mIControllerCallback != null) {
                    return;
                }
                aVar.onPlaybackStateChanged(PlaybackStateCompat.q(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List list) {
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onQueueChanged(MediaSessionCompat.QueueItem.r(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onQueueTitleChanged(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onSessionDestroyed();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                a aVar = (a) this.f1042a.get();
                if (aVar != null) {
                    aVar.onSessionEvent(str, bundle);
                }
            }
        }

        public class b extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f1043a;

            public b(Looper looper) {
                super(looper);
                this.f1043a = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.f1043a) {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            MediaSessionCompat.a(data);
                            a.this.onSessionEvent((String) message.obj, data);
                            break;
                        case 2:
                            a.this.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                            break;
                        case 3:
                            a.this.onMetadataChanged((MediaMetadataCompat) message.obj);
                            break;
                        case 4:
                            a.this.onAudioInfoChanged((d) message.obj);
                            break;
                        case 5:
                            a.this.onQueueChanged((List) message.obj);
                            break;
                        case 6:
                            a.this.onQueueTitleChanged((CharSequence) message.obj);
                            break;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.a(bundle);
                            a.this.onExtrasChanged(bundle);
                            break;
                        case 8:
                            a.this.onSessionDestroyed();
                            break;
                        case 9:
                            a.this.onRepeatModeChanged(((Integer) message.obj).intValue());
                            break;
                        case 11:
                            a.this.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                            break;
                        case 12:
                            a.this.onShuffleModeChanged(((Integer) message.obj).intValue());
                            break;
                        case 13:
                            a.this.onSessionReady();
                            break;
                    }
                }
            }
        }

        public static class c extends a.AbstractBinderC0033a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference f1045a;

            public c(a aVar) {
                this.f1045a = new WeakReference(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void C0(boolean z2) {
                a aVar = (a) this.f1045a.get();
                if (aVar != null) {
                    aVar.postToHandler(11, Boolean.valueOf(z2), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void H0(boolean z2) {
            }

            @Override // android.support.v4.media.session.a
            public void M0(int i2) {
                a aVar = (a) this.f1045a.get();
                if (aVar != null) {
                    aVar.postToHandler(9, Integer.valueOf(i2), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void U(int i2) {
                a aVar = (a) this.f1045a.get();
                if (aVar != null) {
                    aVar.postToHandler(12, Integer.valueOf(i2), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void Y0(PlaybackStateCompat playbackStateCompat) {
                a aVar = (a) this.f1045a.get();
                if (aVar != null) {
                    aVar.postToHandler(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void onEvent(String str, Bundle bundle) {
                a aVar = (a) this.f1045a.get();
                if (aVar != null) {
                    aVar.postToHandler(1, str, bundle);
                }
            }

            @Override // android.support.v4.media.session.a
            public void v() {
                a aVar = (a) this.f1045a.get();
                if (aVar != null) {
                    aVar.postToHandler(13, null, null);
                }
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            postToHandler(8, null, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public android.support.v4.media.session.a getIControllerCallback() {
            return this.mIControllerCallback;
        }

        public void onAudioInfoChanged(d dVar) {
        }

        public void onCaptioningEnabledChanged(boolean z2) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public abstract void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat);

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onRepeatModeChanged(int i2) {
        }

        public abstract void onSessionDestroyed();

        public void onSessionEvent(String str, Bundle bundle) {
        }

        public void onSessionReady() {
        }

        public void onShuffleModeChanged(int i2) {
        }

        public void postToHandler(int i2, Object obj, Bundle bundle) {
            b bVar = this.mHandler;
            if (bVar != null) {
                Message messageObtainMessage = bVar.obtainMessage(i2, obj);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        }

        public void setHandler(Handler handler) {
            if (handler != null) {
                b bVar = new b(handler.getLooper());
                this.mHandler = bVar;
                bVar.f1043a = true;
            } else {
                b bVar2 = this.mHandler;
                if (bVar2 != null) {
                    bVar2.f1043a = false;
                    bVar2.removeCallbacksAndMessages(null);
                    this.mHandler = null;
                }
            }
        }
    }

    public interface b {
        void a(a aVar);

        PendingIntent b();

        e c();

        boolean d(KeyEvent keyEvent);

        void e(a aVar, Handler handler);

        MediaMetadataCompat getMetadata();

        PlaybackStateCompat getPlaybackState();
    }

    public static class c extends MediaControllerImplApi21 {
        public c(Context context, MediaSessionCompat.Token token) {
            super(context, token);
        }
    }

    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1046a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AudioAttributesCompat f1047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1048c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f1049d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1050e;

        public d(int i2, AudioAttributesCompat audioAttributesCompat, int i3, int i4, int i5) {
            this.f1046a = i2;
            this.f1047b = audioAttributesCompat;
            this.f1048c = i3;
            this.f1049d = i4;
            this.f1050e = i5;
        }
    }

    public static abstract class e {
        public abstract void a();

        public abstract void b();

        public abstract void c();
    }

    public static class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaController.TransportControls f1051a;

        public f(MediaController.TransportControls transportControls) {
            this.f1051a = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            this.f1051a.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            this.f1051a.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            this.f1051a.stop();
        }
    }

    public static class g extends f {
        public g(MediaController.TransportControls transportControls) {
            super(transportControls);
        }
    }

    public static class h extends g {
        public h(MediaController.TransportControls transportControls) {
            super(transportControls);
        }
    }

    public static class i extends h {
        public i(MediaController.TransportControls transportControls) {
            super(transportControls);
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        MediaSessionCompat.Token tokenE = mediaSessionCompat.e();
        this.f1034b = tokenE;
        this.f1033a = new c(context, tokenE);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f1033a.d(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public MediaMetadataCompat b() {
        return this.f1033a.getMetadata();
    }

    public PlaybackStateCompat c() {
        return this.f1033a.getPlaybackState();
    }

    public PendingIntent d() {
        return this.f1033a.b();
    }

    public MediaSessionCompat.Token e() {
        return this.f1034b;
    }

    public e f() {
        return this.f1033a.c();
    }

    public void g(a aVar) {
        h(aVar, null);
    }

    public void h(a aVar, Handler handler) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f1035c.putIfAbsent(aVar, Boolean.TRUE) != null) {
            Log.w("MediaControllerCompat", "the callback has already been registered");
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        aVar.setHandler(handler);
        this.f1033a.e(aVar, handler);
    }

    public void i(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f1035c.remove(aVar) == null) {
            Log.w("MediaControllerCompat", "the callback has never been registered");
            return;
        }
        try {
            this.f1033a.a(aVar);
        } finally {
            aVar.setHandler(null);
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        if (token != null) {
            this.f1034b = token;
            this.f1033a = new MediaControllerImplApi21(context, token);
            return;
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }
}
