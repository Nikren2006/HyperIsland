package androidx.mediarouter.media;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
class MediaRouterActiveScanThrottlingHelper {
    static final long MAX_ACTIVE_SCAN_DURATION_MS = 30000;
    private boolean mActiveScan;
    private long mCurrentTime;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mSuppressActiveScanTimeout;
    private final Runnable mUpdateDiscoveryRequestRunnable;

    public MediaRouterActiveScanThrottlingHelper(Runnable runnable) {
        this.mUpdateDiscoveryRequestRunnable = runnable;
    }

    public boolean finalizeActiveScanAndScheduleSuppressActiveScanRunnable() {
        if (this.mActiveScan) {
            long j2 = this.mSuppressActiveScanTimeout;
            if (j2 > 0) {
                this.mHandler.postDelayed(this.mUpdateDiscoveryRequestRunnable, j2);
            }
        }
        return this.mActiveScan;
    }

    public void requestActiveScan(boolean z2, long j2) {
        if (z2) {
            long j3 = this.mCurrentTime;
            if (j3 - j2 >= MAX_ACTIVE_SCAN_DURATION_MS) {
                return;
            }
            this.mSuppressActiveScanTimeout = Math.max(this.mSuppressActiveScanTimeout, (j2 + MAX_ACTIVE_SCAN_DURATION_MS) - j3);
            this.mActiveScan = true;
        }
    }

    public void reset() {
        this.mSuppressActiveScanTimeout = 0L;
        this.mActiveScan = false;
        this.mCurrentTime = SystemClock.elapsedRealtime();
        this.mHandler.removeCallbacks(this.mUpdateDiscoveryRequestRunnable);
    }
}
