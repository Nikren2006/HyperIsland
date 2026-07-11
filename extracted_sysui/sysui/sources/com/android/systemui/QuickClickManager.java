package com.android.systemui;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class QuickClickManager {
    private static final long CLICK_THRESHOLD = 400;
    public static final QuickClickManager INSTANCE = new QuickClickManager();

    private QuickClickManager() {
    }

    public final void setViewClickListener(View view, Runnable callback) {
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(callback, "callback");
        view.setOnClickListener(new QuickClickListener(callback, CLICK_THRESHOLD));
    }
}
