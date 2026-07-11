package v0;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Handler;
import com.miui.miplay.audio.data.AppMetaData;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import l0.C0438a;
import l0.InterfaceC0443f;
import l0.InterfaceC0444g;
import u0.C0748b;
import z0.h;

/* JADX INFO: renamed from: v0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0759g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0438a f6945a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaSession.Token f6946b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MediaController f6947c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0762j f6948d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f6949e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0755c f6950f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AppMetaData f6951g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final z0.h f6952h = new z0.h();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f6953i = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: v0.g$b */
    public final class b extends MediaController.Callback {
        public b() {
        }

        public final boolean a() {
            return z0.d.a(C0759g.this.f6950f.l());
        }

        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            super.onMetadataChanged(mediaMetadata);
            z0.e.c("ActiveSessionRecord_export-api", "onMediaMetaChanged:" + new MediaMetaData(mediaMetadata).toString());
            C0759g.this.f6950f.r(C0759g.this, mediaMetadata);
            if (a()) {
                z0.e.c("ActiveSessionRecord_export-api", "onMediaMetaChange, screen locked");
            } else {
                C0759g.this.l(mediaMetadata);
            }
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            super.onPlaybackStateChanged(playbackState);
            if (playbackState != null) {
                z0.e.c("ActiveSessionRecord_export-api", "onPlaybackStateChanged: " + playbackState.toString());
                C0759g.this.f6950f.s(C0759g.this, playbackState);
                C0759g.this.m(playbackState.getState());
            }
        }

        @Override // android.media.session.MediaController.Callback
        public void onSessionDestroyed() {
            super.onSessionDestroyed();
            z0.e.c("ActiveSessionRecord_export-api", "onSessionDestroyed");
        }
    }

    /* JADX INFO: renamed from: v0.g$c */
    public static final class c extends InterfaceC0444g.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final C0759g f6955a;

        @Override // l0.InterfaceC0444g
        public MediaMetaData B() {
            return new MediaMetaData(this.f6955a.o().getMetadata());
        }

        @Override // l0.InterfaceC0444g
        public void F() {
        }

        @Override // l0.InterfaceC0444g
        public void V(InterfaceC0443f interfaceC0443f) {
            this.f6955a.y(interfaceC0443f);
        }

        @Override // l0.InterfaceC0444g
        public void X() {
        }

        @Override // l0.InterfaceC0444g
        public void a(long j2) {
            z0.e.c("ActiveSessionRecord_export-api", "binder call seekTo");
            this.f6955a.f6948d.a(j2);
        }

        @Override // l0.InterfaceC0444g
        public void c0(InterfaceC0443f interfaceC0443f) {
            this.f6955a.k(new C0748b(interfaceC0443f, this));
        }

        @Override // l0.InterfaceC0444g
        public void d() {
            z0.e.c("ActiveSessionRecord_export-api", "binder call play");
            this.f6955a.w();
        }

        @Override // l0.InterfaceC0444g
        public int getPlaybackState() {
            PlaybackState playbackState = this.f6955a.f6947c.getPlaybackState();
            if (playbackState != null) {
                return playbackState.getState();
            }
            return 0;
        }

        @Override // l0.InterfaceC0444g
        public long getPosition() {
            PlaybackState playbackState = this.f6955a.f6947c.getPlaybackState();
            if (playbackState == null) {
                return -1L;
            }
            return playbackState.getPosition();
        }

        @Override // l0.InterfaceC0444g
        public void h() {
        }

        @Override // l0.InterfaceC0444g
        public void i() {
        }

        @Override // l0.InterfaceC0444g
        public void next() {
            z0.e.c("ActiveSessionRecord_export-api", "binder call next");
            this.f6955a.f6948d.next();
        }

        @Override // l0.InterfaceC0444g
        public void pause() {
            z0.e.c("ActiveSessionRecord_export-api", "binder call pause");
            this.f6955a.v();
        }

        @Override // l0.InterfaceC0444g
        public void previous() {
            z0.e.c("ActiveSessionRecord_export-api", "binder call previous");
            this.f6955a.f6948d.previous();
        }

        @Override // l0.InterfaceC0444g
        public void r0(float f2) {
        }

        @Override // l0.InterfaceC0444g
        public void stop() {
        }

        public c(C0759g c0759g) {
            this.f6955a = c0759g;
        }
    }

    public C0759g(C0755c c0755c, MediaController mediaController, AppMetaData appMetaData, Handler handler) {
        this.f6950f = c0755c;
        this.f6947c = mediaController;
        this.f6946b = mediaController.getSessionToken();
        this.f6948d = new C0762j(mediaController);
        b bVar = new b();
        this.f6949e = bVar;
        mediaController.registerCallback(bVar, handler);
        this.f6951g = appMetaData;
        this.f6945a = new C0438a(appMetaData, new c());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f6946b.equals(((C0759g) obj).f6946b);
    }

    public int hashCode() {
        return Objects.hash(this.f6946b);
    }

    public final void k(C0748b c0748b) {
        this.f6953i.add(c0748b);
    }

    public final void l(MediaMetadata mediaMetadata) {
        for (final InterfaceC0443f interfaceC0443f : this.f6953i) {
            final MediaMetaData mediaMetaData = new MediaMetaData(mediaMetadata);
            this.f6952h.b("ActiveSessionRecord_export-api", "onMediaMetaChange", new h.a() { // from class: v0.d
                @Override // z0.h.a
                public final void invoke() {
                    interfaceC0443f.onMediaMetaChange(mediaMetaData);
                }
            });
        }
    }

    public final void m(final int i2) {
        for (final InterfaceC0443f interfaceC0443f : this.f6953i) {
            this.f6952h.b("ActiveSessionRecord_export-api", "onPlaybackStateChange", new h.a() { // from class: v0.f
                @Override // z0.h.a
                public final void invoke() {
                    interfaceC0443f.onPlaybackStateChange(i2);
                }
            });
        }
    }

    public void n() {
        PlaybackState playbackState = this.f6947c.getPlaybackState();
        if (playbackState == null) {
            return;
        }
        final long position = playbackState.getPosition();
        for (final InterfaceC0443f interfaceC0443f : this.f6953i) {
            z0.e.a("position", "position:" + position);
            this.f6952h.b("ActiveSessionRecord_export-api", "onPositionChange", new h.a() { // from class: v0.e
                @Override // z0.h.a
                public final void invoke() {
                    interfaceC0443f.onPositionChange(position);
                }
            });
        }
    }

    public MediaController o() {
        return this.f6947c;
    }

    public String p() {
        return this.f6951g.getPackageName();
    }

    public int q() {
        PlaybackState playbackState = this.f6947c.getPlaybackState();
        if (playbackState == null) {
            return 0;
        }
        return playbackState.getState();
    }

    public C0438a r() {
        return this.f6945a;
    }

    public void v() {
        this.f6948d.pause();
    }

    public void w() {
        this.f6948d.d();
    }

    public void x() {
        this.f6947c.unregisterCallback(this.f6949e);
    }

    public final void y(InterfaceC0443f interfaceC0443f) {
        C0748b c0748b;
        Iterator it = this.f6953i.iterator();
        while (true) {
            if (!it.hasNext()) {
                c0748b = null;
                break;
            } else {
                c0748b = (C0748b) it.next();
                if (c0748b.asBinder() == interfaceC0443f.asBinder()) {
                    break;
                }
            }
        }
        if (c0748b != null) {
            c0748b.release();
            this.f6953i.remove(c0748b);
        }
    }
}
