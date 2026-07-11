package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public final class TvManger {
    private final TvInfoCallback tvCallback;

    public interface TvInfoCallback {
        boolean onGetSupportVideoFlowCapacity();

        String onGetTvId();

        String onGetTvMac();

        String onGetTvName();
    }

    public TvManger(TvInfoCallback tvCallback) {
        kotlin.jvm.internal.n.g(tvCallback, "tvCallback");
        this.tvCallback = tvCallback;
    }

    public final String getMac() {
        return this.tvCallback.onGetTvMac();
    }

    public final String getTvId() {
        return this.tvCallback.onGetTvId();
    }

    public final String getTvName() {
        return this.tvCallback.onGetTvName();
    }

    public final boolean supportVideoFlowCapacity() {
        return this.tvCallback.onGetSupportVideoFlowCapacity();
    }
}
