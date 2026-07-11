package com.miui.maml;

import android.view.MotionEvent;
import androidx.core.location.LocationRequestCompat;
import com.miui.maml.FramerateTokenList;
import com.miui.maml.elements.FramerateController;
import com.miui.maml.util.HideSdkDependencyUtils;
import com.miui.maml.util.MamlLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class RendererController implements FramerateTokenList.FramerateChangeListener {
    private static final String LOG_TAG = "RendererController";
    private static final int MAX_MSG_COUNT = 3;
    private float mCurFramerate;
    private FramerateTokenList mFramerateTokenList;
    private boolean mInited;
    private long mLastUpdateSystemTime;
    private Listener mListener;
    private LinkedList<MotionEvent> mMsgQueue;
    private boolean mNeedReset;
    private boolean mPendingRender;
    private boolean mSelfRender;
    private boolean mShouldUpdate;
    private ArrayList<FramerateController> mFramerateControllers = new ArrayList<>();
    private boolean mSelfPaused = true;
    private byte[] mLock = new byte[0];
    private long mFrameTime = LocationRequestCompat.PASSIVE_INTERVAL;
    private Object mMsgLock = new Object();
    private float mTouchX = -1.0f;
    private float mTouchY = -1.0f;
    private ArrayList<Runnable> mWriteRunnableQueue = new ArrayList<>();
    private ArrayList<Runnable> mReadRunnableQueue = new ArrayList<>();
    private Object mWriteRunnableQueueLock = new Object();

    public static abstract class EmptyListener implements Listener {
        @Override // com.miui.maml.RendererController.IRenderable
        public void doRender() {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void finish() {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void init() {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void onHover(MotionEvent motionEvent) {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void onTouch(MotionEvent motionEvent) {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void pause() {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void resume() {
        }

        @Override // com.miui.maml.RendererController.Listener
        public void tick(long j2) {
        }
    }

    public interface IRenderable {
        void doRender();
    }

    public interface ISelfUpdateRenderable extends IRenderable {
        void forceUpdate();

        void triggerUpdate();
    }

    public interface Listener extends ISelfUpdateRenderable {
        void finish();

        void init();

        void onHover(MotionEvent motionEvent);

        void onTouch(MotionEvent motionEvent);

        void pause();

        void resume();

        void tick(long j2);
    }

    public RendererController() {
        this.mFramerateTokenList = new FramerateTokenList();
        this.mFramerateTokenList = new FramerateTokenList(this);
    }

    private void runRunnables() {
        ArrayList<Runnable> arrayList;
        if (this.mNeedReset) {
            return;
        }
        synchronized (this.mWriteRunnableQueueLock) {
            arrayList = this.mWriteRunnableQueue;
            this.mWriteRunnableQueue = this.mReadRunnableQueue;
            this.mReadRunnableQueue = arrayList;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mReadRunnableQueue.get(i2).run();
        }
        this.mReadRunnableQueue.clear();
    }

    public void addFramerateController(FramerateController framerateController) {
        if (this.mFramerateControllers.contains(framerateController)) {
            return;
        }
        this.mFramerateControllers.add(framerateController);
    }

    public final FramerateTokenList.FramerateToken createToken(String str) {
        return this.mFramerateTokenList.createToken(str);
    }

    public final void doRender() {
        Listener listener = this.mListener;
        if (listener != null) {
            this.mPendingRender = true;
            listener.doRender();
        }
    }

    public final void doneRender() {
        this.mPendingRender = false;
        triggerUpdate();
    }

    public void finish() {
        synchronized (this.mLock) {
            try {
                if (this.mInited) {
                    Listener listener = this.mListener;
                    if (listener != null) {
                        try {
                            listener.finish();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            MamlLog.e(LOG_TAG, e2.toString());
                        }
                    }
                    synchronized (this.mMsgLock) {
                        try {
                            if (this.mMsgQueue != null) {
                                while (this.mMsgQueue.size() > 0) {
                                    this.mMsgQueue.poll().recycle();
                                }
                            }
                        } finally {
                        }
                    }
                    synchronized (this.mWriteRunnableQueueLock) {
                        this.mWriteRunnableQueue.clear();
                    }
                    this.mInited = false;
                    this.mFramerateTokenList.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void forceUpdate() {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.forceUpdate();
        }
        RenderVsyncUpdater.getInstance().forceUpdate();
    }

    public final MotionEvent getMessage() {
        MotionEvent motionEventPoll = null;
        if (this.mMsgQueue == null) {
            return null;
        }
        synchronized (this.mMsgLock) {
            LinkedList<MotionEvent> linkedList = this.mMsgQueue;
            if (linkedList != null) {
                motionEventPoll = linkedList.poll();
            }
        }
        return motionEventPoll;
    }

    public final boolean hasInited() {
        return this.mInited;
    }

    public final boolean hasMessage() {
        boolean z2 = false;
        if (this.mMsgQueue == null) {
            return false;
        }
        synchronized (this.mMsgLock) {
            LinkedList<MotionEvent> linkedList = this.mMsgQueue;
            if (linkedList != null && linkedList.size() > 0) {
                z2 = true;
            }
        }
        return z2;
    }

    public final boolean hasRunnable() {
        boolean z2;
        synchronized (this.mWriteRunnableQueueLock) {
            z2 = !this.mWriteRunnableQueue.isEmpty();
        }
        return z2;
    }

    public void init() {
        synchronized (this.mLock) {
            try {
                if (this.mInited) {
                    return;
                }
                Listener listener = this.mListener;
                if (listener != null) {
                    try {
                        listener.init();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        MamlLog.e(LOG_TAG, e2.toString());
                    }
                }
                this.mInited = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isSelfPaused() {
        return this.mSelfPaused;
    }

    @Override // com.miui.maml.FramerateTokenList.FramerateChangeListener
    public void onFrameRateChage(float f2, float f3) {
        if (f3 > 0.0f) {
            triggerUpdate();
        }
    }

    public void onHover(MotionEvent motionEvent) {
        Listener listener;
        if (motionEvent == null || (listener = this.mListener) == null) {
            return;
        }
        try {
            listener.onHover(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            MamlLog.e(LOG_TAG, e2.toString());
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            MamlLog.e(LOG_TAG, e3.toString());
        }
    }

    public void onTouch(MotionEvent motionEvent) {
        Listener listener;
        if (motionEvent == null || (listener = this.mListener) == null) {
            return;
        }
        try {
            listener.onTouch(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            MamlLog.e(LOG_TAG, e2.toString());
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            MamlLog.e(LOG_TAG, e3.toString());
        }
    }

    public final boolean pendingRender() {
        return this.mPendingRender;
    }

    @Deprecated
    public void postMessage(MotionEvent motionEvent) {
        MotionEvent next;
        synchronized (this.mMsgLock) {
            try {
                if (this.mMsgQueue == null) {
                    this.mMsgQueue = new LinkedList<>();
                }
                if (motionEvent.getActionMasked() != 2 || motionEvent.getX() != this.mTouchX || motionEvent.getY() != this.mTouchY) {
                    this.mMsgQueue.add(motionEvent);
                    this.mTouchX = motionEvent.getX();
                    this.mTouchY = motionEvent.getY();
                }
                if (this.mMsgQueue.size() > 3) {
                    Iterator<MotionEvent> it = this.mMsgQueue.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        } else {
                            next = it.next();
                            if (next.getActionMasked() == 2) {
                                break;
                            }
                        }
                    }
                    if (next != null) {
                        this.mMsgQueue.remove(next);
                        next.recycle();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        forceUpdate();
    }

    public void postRunnable(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("postRunnable null");
        }
        synchronized (this.mWriteRunnableQueueLock) {
            try {
                if (!this.mWriteRunnableQueue.contains(runnable)) {
                    this.mWriteRunnableQueue.add(runnable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        requestUpdate();
    }

    public void postRunnableAtFrontOfQueue(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("postRunnable null");
        }
        synchronized (this.mWriteRunnableQueueLock) {
            try {
                if (!this.mWriteRunnableQueue.contains(runnable)) {
                    this.mWriteRunnableQueue.add(0, runnable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        requestUpdate();
    }

    public void removeFramerateController(FramerateController framerateController) {
        this.mFramerateControllers.remove(framerateController);
    }

    public final void removeToken(FramerateTokenList.FramerateToken framerateToken) {
        this.mFramerateTokenList.removeToken(framerateToken);
    }

    public final void requestUpdate() {
        this.mShouldUpdate = true;
        forceUpdate();
    }

    public void selfPause() {
        if (this.mInited) {
            synchronized (this.mLock) {
                try {
                    if (!this.mSelfPaused) {
                        this.mSelfPaused = true;
                        Listener listener = this.mListener;
                        if (listener != null) {
                            listener.pause();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.mPendingRender = false;
        }
    }

    public void selfResume() {
        if (this.mInited) {
            synchronized (this.mLock) {
                try {
                    if (this.mSelfPaused) {
                        this.mSelfPaused = false;
                        Listener listener = this.mListener;
                        if (listener != null) {
                            listener.resume();
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            RenderVsyncUpdater.getInstance().onResume();
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setNeedReset(boolean z2) {
        this.mNeedReset = z2;
    }

    public void setSelfRender(boolean z2) {
        this.mSelfRender = z2;
    }

    public void tick(long j2) {
        this.mShouldUpdate = false;
        Listener listener = this.mListener;
        if (listener != null) {
            try {
                listener.tick(j2);
            } catch (Exception e2) {
                e2.printStackTrace();
                MamlLog.e(LOG_TAG, e2.toString());
            }
        }
        this.mLastUpdateSystemTime = j2;
    }

    public void triggerUpdate() {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.triggerUpdate();
        }
        RenderVsyncUpdater.getInstance().triggerUpdate();
    }

    public long update(long j2) {
        long jUpdateFramerate = updateFramerate(j2);
        boolean zHasRunnable = hasRunnable();
        if (this.mPendingRender && !zHasRunnable) {
            return jUpdateFramerate;
        }
        runRunnables();
        MotionEvent message = getMessage();
        if (message != null) {
            if (HideSdkDependencyUtils.MotionEvent_isTouchEvent(message)) {
                onTouch(message);
            } else {
                onHover(message);
            }
        }
        tick(j2);
        if (!this.mSelfRender) {
            doRender();
        }
        if (this.mShouldUpdate || hasMessage()) {
            return 0L;
        }
        return jUpdateFramerate;
    }

    public final long updateFramerate(long j2) {
        int size = this.mFramerateControllers.size();
        long j3 = LocationRequestCompat.PASSIVE_INTERVAL;
        long j4 = Long.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            long jUpdateFramerate = this.mFramerateControllers.get(i2).updateFramerate(j2);
            if (jUpdateFramerate < j4) {
                j4 = jUpdateFramerate;
            }
        }
        float framerate = this.mFramerateTokenList.getFramerate();
        float f2 = this.mCurFramerate;
        if (f2 != framerate) {
            if (f2 >= 1.0f && framerate < 1.0f) {
                requestUpdate();
            }
            this.mCurFramerate = framerate;
            if (framerate != 0.0f) {
                j3 = (long) (1000.0f / framerate);
            }
            this.mFrameTime = j3;
        }
        long j5 = this.mFrameTime;
        return j5 < j4 ? j5 : j4;
    }

    public long updateIfNeeded(long j2) {
        long jUpdateFramerate = updateFramerate(j2);
        long j3 = this.mFrameTime;
        long j4 = LocationRequestCompat.PASSIVE_INTERVAL;
        if (j3 < LocationRequestCompat.PASSIVE_INTERVAL) {
            j4 = j3 - (j2 - this.mLastUpdateSystemTime);
        }
        boolean zHasRunnable = hasRunnable();
        if (j4 > 0 && !this.mShouldUpdate && !hasMessage() && !zHasRunnable) {
            return j4 < jUpdateFramerate ? j4 : jUpdateFramerate;
        }
        if (this.mPendingRender && !zHasRunnable) {
            return jUpdateFramerate;
        }
        runRunnables();
        MotionEvent message = getMessage();
        if (message != null) {
            if (HideSdkDependencyUtils.MotionEvent_isTouchEvent(message)) {
                onTouch(message);
            } else {
                onHover(message);
            }
        }
        tick(j2);
        if (!this.mSelfRender) {
            doRender();
        }
        if (this.mShouldUpdate || hasMessage()) {
            return 0L;
        }
        return jUpdateFramerate;
    }
}
