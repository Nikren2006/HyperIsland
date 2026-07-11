package miuix.mgl.android;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes3.dex */
public final class FrameSync {
    public static final long DELTA_60FPS = 15000000;
    public static final double NANO_TO_SEC = 1.0E-9d;
    private boolean mEnable;
    private IFrameListener mFrameListener;
    private long mRunningLastTime;
    private long mRunningTime;
    private final FrameCallback mFrameScheduler = new FrameCallback();
    private final Choreographer mChoreographer = Choreographer.getInstance();
    private int mFrameInterval = 1;
    private long mFrameCount = -1;
    private long mDelta = DELTA_60FPS;

    public final class FrameCallback implements Choreographer.FrameCallback {
        public FrameCallback() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j2) {
            if (FrameSync.this.mEnable) {
                FrameSync.this.mChoreographer.postFrameCallback(this);
                FrameSync.this.sync(j2);
            }
        }
    }

    public interface IFrameListener {
        void onTick(float f2, float f3, long j2);
    }

    public FrameSync(IFrameListener iFrameListener) {
        setFrameListener(iFrameListener);
    }

    private void enableCallBack(boolean z2) {
        if (z2) {
            this.mChoreographer.postFrameCallback(this.mFrameScheduler);
        } else {
            this.mChoreographer.removeFrameCallback(this.mFrameScheduler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sync(long j2) {
        boolean z2;
        if (this.mRunningLastTime == 0) {
            this.mRunningLastTime = j2;
            z2 = false;
        } else {
            z2 = true;
        }
        long j3 = j2 - this.mRunningLastTime;
        if (j3 > this.mDelta || !z2) {
            long j4 = this.mFrameCount + 1;
            this.mFrameCount = j4;
            if (j4 % ((long) this.mFrameInterval) == 0) {
                float f2 = (float) (j3 * 1.0E-9d);
                float f3 = (float) (j2 * 1.0E-9d);
                this.mRunningTime += j3;
                this.mRunningLastTime = j2;
                IFrameListener iFrameListener = this.mFrameListener;
                if (iFrameListener != null) {
                    iFrameListener.onTick(f2, f3, j2);
                }
            }
        }
    }

    public void enable(boolean z2) {
        if (this.mEnable != z2) {
            this.mEnable = z2;
            if (z2) {
                this.mRunningLastTime = 0L;
                this.mFrameCount = -1L;
            }
            enableCallBack(z2);
        }
    }

    public void enableFrameLimit(boolean z2) {
        this.mDelta = z2 ? DELTA_60FPS : 0L;
    }

    public int getFrameInterval() {
        return this.mFrameInterval;
    }

    public IFrameListener getFrameListener() {
        return this.mFrameListener;
    }

    public long getRunningTime() {
        return this.mRunningTime;
    }

    public void setFrameInterval(int i2) {
        this.mFrameInterval = i2;
        if (i2 < 1) {
            this.mFrameInterval = 1;
        }
    }

    public void setFrameListener(IFrameListener iFrameListener) {
        this.mFrameListener = iFrameListener;
    }

    public FrameSync() {
    }
}
