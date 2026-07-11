package com.miui.maml;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.collection.ArraySet;
import androidx.core.location.LocationRequestCompat;
import com.miui.maml.util.MamlLog;
import java.lang.ref.WeakReference;
import miuix.animation.internal.FolmeCore;

/* JADX INFO: loaded from: classes2.dex */
public class RenderVsyncUpdater {
    private static final String LOG_TAG = "RenderVsyncUpdater";
    private FrameDisplayEventReceiver mDisplayEventReceiver;
    private Handler mHandler;
    private boolean mPaused;
    private ArraySet<WeakReference<RendererController>> mRendererControllerList;
    private final Runnable mScheduleFrame;
    private boolean mStopRefresh;
    private int mSyncInterval;
    private long mVsyncLeft;

    public final class FrameDisplayEventReceiver extends MamlDisplayEventReceiver implements Runnable {
        public FrameDisplayEventReceiver(Looper looper) {
            super(looper);
        }

        @Override // com.miui.maml.MamlDisplayEventReceiver
        public void onVsync(long j2) {
            RenderVsyncUpdater.access$122(RenderVsyncUpdater.this, 1L);
            RenderVsyncUpdater.this.mHandler.sendMessageAtTime(Message.obtain(RenderVsyncUpdater.this.mHandler, this), j2 / FolmeCore.NANOS_TO_MS);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RenderVsyncUpdater.this.mVsyncLeft <= 0) {
                RenderVsyncUpdater.this.scheduleFrame();
            } else {
                if (RenderVsyncUpdater.this.mPaused || RenderVsyncUpdater.this.mStopRefresh) {
                    return;
                }
                scheduleVsync();
            }
        }
    }

    public static class RenderVsyncUpdaterHolder {
        public static final RenderVsyncUpdater INSTANCE = new RenderVsyncUpdater();

        private RenderVsyncUpdaterHolder() {
        }
    }

    public static /* synthetic */ long access$122(RenderVsyncUpdater renderVsyncUpdater, long j2) {
        long j3 = renderVsyncUpdater.mVsyncLeft - j2;
        renderVsyncUpdater.mVsyncLeft = j3;
        return j3;
    }

    private void doRunUpdater() {
        if (this.mVsyncLeft > 0) {
            this.mDisplayEventReceiver.scheduleVsync();
        } else {
            if (this.mHandler.hasCallbacks(this.mScheduleFrame)) {
                return;
            }
            this.mHandler.post(this.mScheduleFrame);
        }
    }

    public static RenderVsyncUpdater getInstance() {
        return RenderVsyncUpdaterHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleFrame() {
        long j2;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (this.mRendererControllerList) {
            try {
                int size = this.mRendererControllerList.size();
                boolean z2 = true;
                j2 = Long.MAX_VALUE;
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    RendererController rendererController = this.mRendererControllerList.valueAt(i2).get();
                    if (rendererController == null) {
                        this.mRendererControllerList.removeAt(i2);
                    } else if (!rendererController.isSelfPaused() || rendererController.hasRunnable()) {
                        if (!rendererController.hasInited()) {
                            rendererController.init();
                        }
                        long jUpdateIfNeeded = rendererController.updateIfNeeded(jElapsedRealtime);
                        if (jUpdateIfNeeded < j2) {
                            z2 = false;
                            j2 = jUpdateIfNeeded;
                        } else {
                            z2 = false;
                        }
                    }
                }
                if (size == 0 || z2) {
                    this.mPaused = true;
                    MamlLog.i(LOG_TAG, "All controllers paused.");
                } else {
                    this.mPaused = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        boolean z3 = j2 == LocationRequestCompat.PASSIVE_INTERVAL;
        this.mStopRefresh = z3;
        if (z3 || this.mPaused || j2 <= 0) {
            return;
        }
        long j3 = j2 / ((long) this.mSyncInterval);
        this.mVsyncLeft = j3;
        if (j3 > 0) {
            this.mVsyncLeft = j3 - 1;
        }
        this.mDisplayEventReceiver.scheduleVsync();
    }

    public void addRendererController(RendererController rendererController) {
        synchronized (this.mRendererControllerList) {
            try {
                int size = this.mRendererControllerList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.mRendererControllerList.valueAt(i2).get() == rendererController) {
                        return;
                    }
                }
                this.mRendererControllerList.add(new WeakReference<>(rendererController));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void forceUpdate() {
        this.mVsyncLeft = 0L;
        doRunUpdater();
    }

    public void onResume() {
        this.mPaused = false;
        this.mStopRefresh = false;
        forceUpdate();
    }

    public void removeRendererController(RendererController rendererController) {
        synchronized (this.mRendererControllerList) {
            try {
                for (int size = this.mRendererControllerList.size() - 1; size >= 0; size--) {
                    RendererController rendererController2 = this.mRendererControllerList.valueAt(size).get();
                    if (rendererController2 != null && rendererController2 != rendererController) {
                    }
                    this.mRendererControllerList.removeAt(size);
                    break;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setSyncInterval(int i2) {
        this.mSyncInterval = i2;
    }

    public void triggerUpdate() {
        doRunUpdater();
    }

    private RenderVsyncUpdater() {
        this.mRendererControllerList = new ArraySet<>();
        this.mSyncInterval = 16;
        this.mScheduleFrame = new Runnable() { // from class: com.miui.maml.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f2543a.scheduleFrame();
            }
        };
        Looper looperMyLooper = "android.ui".equals(Thread.currentThread().getName()) ? Looper.myLooper() : Looper.getMainLooper();
        this.mHandler = new Handler(looperMyLooper);
        this.mDisplayEventReceiver = new FrameDisplayEventReceiver(looperMyLooper);
    }
}
