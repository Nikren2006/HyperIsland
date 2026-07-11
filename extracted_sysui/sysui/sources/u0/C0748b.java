package u0;

import android.os.IBinder;
import android.os.RemoteException;
import com.miui.miplay.audio.data.MediaMetaData;
import java.lang.ref.WeakReference;
import l0.InterfaceC0443f;
import l0.InterfaceC0444g;
import z0.e;

/* JADX INFO: renamed from: u0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0748b implements InterfaceC0443f, IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0443f f6846a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference f6847b;

    public C0748b(InterfaceC0443f interfaceC0443f, InterfaceC0444g interfaceC0444g) {
        this.f6846a = interfaceC0443f;
        try {
            interfaceC0443f.asBinder().linkToDeath(this, 0);
        } catch (RemoteException e2) {
            e.b("MediaChangeListenerWrapper", "linkToDeath", e2);
        }
        this.f6847b = new WeakReference(interfaceC0444g);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.f6846a.asBinder();
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        e.c("MediaChangeListenerWrapper", "binderDied...");
        InterfaceC0444g interfaceC0444g = (InterfaceC0444g) this.f6847b.get();
        if (interfaceC0444g != null) {
            try {
                interfaceC0444g.V(this);
            } catch (RemoteException e2) {
                e.b("MediaChangeListenerWrapper", "binderDied", e2);
            }
        }
    }

    @Override // l0.InterfaceC0443f
    public void onMediaMetaChange(MediaMetaData mediaMetaData) {
        this.f6846a.onMediaMetaChange(mediaMetaData);
    }

    @Override // l0.InterfaceC0443f
    public void onPlaybackStateChange(int i2) {
        this.f6846a.onPlaybackStateChange(i2);
    }

    @Override // l0.InterfaceC0443f
    public void onPositionChange(long j2) {
        this.f6846a.onPositionChange(j2);
    }

    public void release() {
        IBinder iBinderAsBinder = this.f6846a.asBinder();
        if (iBinderAsBinder != null) {
            iBinderAsBinder.unlinkToDeath(this, 0);
        }
    }
}
