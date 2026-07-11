package com.android.systemui;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class QuickClickListener implements View.OnClickListener {
    private final Runnable callback;
    private long lastClickTime;
    private final long timeThreshold;

    public QuickClickListener(Runnable callback, long j2) {
        kotlin.jvm.internal.n.g(callback, "callback");
        this.callback = callback;
        this.timeThreshold = j2;
    }

    public final Runnable getCallback() {
        return this.callback;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime >= this.timeThreshold) {
            this.lastClickTime = jCurrentTimeMillis;
            this.callback.run();
        }
    }
}
