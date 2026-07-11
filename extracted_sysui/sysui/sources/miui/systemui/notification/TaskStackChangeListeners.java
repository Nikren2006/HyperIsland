package miui.systemui.notification;

import android.app.TaskStackListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.android.internal.os.SomeArgs;
import java.util.ArrayList;
import java.util.List;
import miui.systemui.util.IActivityTaskManagerCompat;

/* JADX INFO: loaded from: classes4.dex */
public class TaskStackChangeListeners {
    private static final TaskStackChangeListeners INSTANCE = new TaskStackChangeListeners();
    private static final String TAG = "TaskStackChangeListeners";
    private final Impl mImpl;

    public static class TestSyncHandler extends Handler {
        private Handler.Callback mCb;

        public TestSyncHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(@NonNull Message message, long j2) {
            return this.mCb.handleMessage(message);
        }

        public void setCallback(Handler.Callback callback) {
            this.mCb = callback;
        }
    }

    private TaskStackChangeListeners() {
        this.mImpl = new Impl(Looper.getMainLooper());
    }

    public static TaskStackChangeListeners getInstance() {
        return INSTANCE;
    }

    @VisibleForTesting
    public static TaskStackChangeListeners getTestInstance() {
        TestSyncHandler testSyncHandler = new TestSyncHandler();
        TaskStackChangeListeners taskStackChangeListeners = new TaskStackChangeListeners(testSyncHandler);
        testSyncHandler.setCallback(taskStackChangeListeners.mImpl);
        return taskStackChangeListeners;
    }

    @VisibleForTesting
    public TaskStackListener getListenerImpl() {
        return this.mImpl;
    }

    public void registerTaskStackListener(TaskStackChangeListener taskStackChangeListener) {
        synchronized (this.mImpl) {
            this.mImpl.addListener(taskStackChangeListener);
        }
    }

    public void unregisterTaskStackListener(TaskStackChangeListener taskStackChangeListener) {
        synchronized (this.mImpl) {
            this.mImpl.removeListener(taskStackChangeListener);
        }
    }

    public class Impl extends TaskStackListener implements Handler.Callback {
        private static final int ON_LOCK_TASK_MODE_CHANGED = 23;
        private final Handler mHandler;
        private boolean mRegistered;
        private final List<TaskStackChangeListener> mTaskStackListeners;

        public void addListener(TaskStackChangeListener taskStackChangeListener) {
            synchronized (this.mTaskStackListeners) {
                this.mTaskStackListeners.add(taskStackChangeListener);
            }
            if (this.mRegistered) {
                return;
            }
            try {
                IActivityTaskManagerCompat.registerTaskStackListener(this);
                this.mRegistered = true;
            } catch (Exception e2) {
                Log.w(TaskStackChangeListeners.TAG, "Failed to call registerTaskStackListener", e2);
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            synchronized (this.mTaskStackListeners) {
                try {
                    if (message.what == 23) {
                        for (int size = this.mTaskStackListeners.size() - 1; size >= 0; size--) {
                            this.mTaskStackListeners.get(size).onLockTaskModeChanged(message.arg1);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            Object obj = message.obj;
            if (obj instanceof SomeArgs) {
                ((SomeArgs) obj).recycle();
            }
            return true;
        }

        public void onLockTaskModeChanged(int i2) {
            this.mHandler.obtainMessage(23, i2, 0).sendToTarget();
        }

        public void removeListener(TaskStackChangeListener taskStackChangeListener) {
            boolean zIsEmpty;
            synchronized (this.mTaskStackListeners) {
                this.mTaskStackListeners.remove(taskStackChangeListener);
                zIsEmpty = this.mTaskStackListeners.isEmpty();
            }
            if (zIsEmpty && this.mRegistered) {
                try {
                    IActivityTaskManagerCompat.unregisterTaskStackListener(this);
                    this.mRegistered = false;
                } catch (Exception e2) {
                    Log.w(TaskStackChangeListeners.TAG, "Failed to call unregisterTaskStackListener", e2);
                }
            }
        }

        private Impl(TaskStackChangeListeners taskStackChangeListeners, Looper looper) {
            this.mTaskStackListeners = new ArrayList();
            this.mHandler = new Handler(looper, this);
        }

        private Impl(TaskStackChangeListeners taskStackChangeListeners, Handler handler) {
            this.mTaskStackListeners = new ArrayList();
            this.mHandler = handler;
        }
    }

    private TaskStackChangeListeners(Handler handler) {
        this.mImpl = new Impl(handler);
    }
}
