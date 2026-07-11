package androidx.mediarouter.media;

import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MediaRouter.PrepareTransferNotifier f1267a;

    @Override // java.lang.Runnable
    public final void run() {
        this.f1267a.finishTransfer();
    }
}
