package v0;

import android.media.session.MediaController;

/* JADX INFO: renamed from: v0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0762j implements InterfaceC0763k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0763k f6957a;

    /* JADX INFO: renamed from: v0.j$a */
    public static class a implements InterfaceC0763k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaController f6958a;

        public a(MediaController mediaController) {
            this.f6958a = mediaController;
        }

        @Override // v0.InterfaceC0763k
        public void a(long j2) {
            z0.e.c("TransportController", "transportControls, seekTo");
            this.f6958a.getTransportControls().seekTo(j2);
        }

        @Override // v0.InterfaceC0763k
        public void d() {
            z0.e.c("TransportController", "transportControls, play");
            this.f6958a.getTransportControls().play();
        }

        @Override // v0.InterfaceC0763k
        public void next() {
            z0.e.c("TransportController", "transportControls, next");
            this.f6958a.getTransportControls().skipToNext();
        }

        @Override // v0.InterfaceC0763k
        public void pause() {
            z0.e.c("TransportController", "transportControls, pause");
            this.f6958a.getTransportControls().pause();
        }

        @Override // v0.InterfaceC0763k
        public void previous() {
            z0.e.c("TransportController", "transportControls, previous");
            this.f6958a.getTransportControls().skipToPrevious();
        }
    }

    public C0762j(MediaController mediaController) {
        this.f6957a = b(mediaController);
    }

    public static InterfaceC0763k b(MediaController mediaController) {
        return new a(mediaController);
    }

    @Override // v0.InterfaceC0763k
    public void a(long j2) {
        this.f6957a.a(j2);
    }

    @Override // v0.InterfaceC0763k
    public void d() {
        this.f6957a.d();
    }

    @Override // v0.InterfaceC0763k
    public void next() {
        this.f6957a.next();
    }

    @Override // v0.InterfaceC0763k
    public void pause() {
        this.f6957a.pause();
    }

    @Override // v0.InterfaceC0763k
    public void previous() {
        this.f6957a.previous();
    }
}
