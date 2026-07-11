package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailHeader$tvControlVisibilityRunnable$1 implements Runnable {
    private boolean message;
    final /* synthetic */ QSControlMiPlayDetailHeader this$0;

    public QSControlMiPlayDetailHeader$tvControlVisibilityRunnable$1(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader) {
        this.this$0 = qSControlMiPlayDetailHeader;
    }

    public final boolean getMessage() {
        return this.message;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.this$0.setTvControlVisibility(this.message);
    }

    /* JADX INFO: renamed from: setMessage, reason: collision with other method in class */
    public final void m62setMessage(boolean z2) {
        this.message = z2;
    }

    public final Runnable setMessage(boolean z2) {
        this.message = z2;
        return this;
    }
}
