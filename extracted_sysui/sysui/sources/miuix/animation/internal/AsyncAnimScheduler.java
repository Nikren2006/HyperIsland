package miuix.animation.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import miui.systemui.devicecontrols.ui.TouchBehavior;
import miuix.animation.Folme;
import miuix.animation.IAnimTarget;
import miuix.animation.controller.AnimState;
import miuix.animation.internal.AnimScheduler;
import miuix.animation.utils.BoostHelper;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
class AsyncAnimScheduler extends AnimScheduler {
    private final Handler mScheduleHandler;
    protected final Map<Integer, TransitionInfo> mTempInfoMap;
    private final HandlerThread mThread;

    public class ScheduleHandler extends Handler {
        public ScheduleHandler(@NonNull Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                AsyncAnimScheduler.this.lambda$executeTo$1(AsyncAnimScheduler.this.mTempInfoMap.get(Integer.valueOf(message.arg1)));
            } else if (i2 == 2) {
                AsyncAnimScheduler.this.update();
            } else if (i2 == 3) {
                Object obj = message.obj;
                if (obj instanceof TimeInfo) {
                    TimeInfo timeInfo = (TimeInfo) obj;
                    AsyncAnimScheduler.this.lambda$executeDoAnimOnFrame$2(timeInfo.frameTime, timeInfo.deltaT);
                }
            } else if (i2 == 4) {
                AsyncAnimScheduler.this.pendingSetTo((AnimScheduler.SetToInfo) message.obj);
            }
            message.obj = null;
        }
    }

    public class TimeInfo {
        long deltaT;
        long frameTime;

        public TimeInfo() {
        }
    }

    public AsyncAnimScheduler(FolmeEngine folmeEngine) {
        super(folmeEngine);
        this.mTempInfoMap = new ConcurrentHashMap();
        HandlerThread handlerThread = new HandlerThread("SubAnimSchedulerThread", ThreadPoolUtil.sThreadPriority);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mScheduleHandler = new ScheduleHandler(handlerThread.getLooper());
    }

    @Override // miuix.animation.internal.AnimScheduler
    public void destroy() {
        this.mThread.quitSafely();
    }

    @Override // miuix.animation.internal.AnimScheduler
    public void executeDoAnimOnFrame(long j2, long j3) {
        Message messageObtainMessage = this.mScheduleHandler.obtainMessage();
        messageObtainMessage.what = 3;
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.frameTime = j2;
        timeInfo.deltaT = j3;
        messageObtainMessage.obj = timeInfo;
        this.mScheduleHandler.sendMessage(messageObtainMessage);
    }

    @Override // miuix.animation.internal.AnimScheduler
    public void executePendingSetTo(IAnimTarget iAnimTarget, AnimState animState) {
        AnimScheduler.SetToInfo setToInfo = new AnimScheduler.SetToInfo();
        setToInfo.target = iAnimTarget;
        if (animState.needDuplicate) {
            AnimState animState2 = new AnimState();
            setToInfo.state = animState2;
            animState2.set(animState);
        } else {
            setToInfo.state = animState;
        }
        this.mScheduleHandler.obtainMessage(4, setToInfo).sendToTarget();
    }

    @Override // miuix.animation.internal.AnimScheduler
    public void executeTo(TransitionInfo transitionInfo) {
        this.mTempInfoMap.put(Integer.valueOf(transitionInfo.id), transitionInfo);
        this.mScheduleHandler.obtainMessage(1, transitionInfo.id, 0).sendToTarget();
    }

    @Override // miuix.animation.internal.AnimScheduler
    public void executeUpdate() {
        Message messageObtainMessage = this.mScheduleHandler.obtainMessage();
        messageObtainMessage.what = 2;
        this.mScheduleHandler.sendMessage(messageObtainMessage);
    }

    @Override // miuix.animation.internal.AnimScheduler
    public void runAnimTaskOnFrame(long j2, long j3, long j4) {
        if (!BoostHelper.hasBindBigCpu.get() && !BoostHelper.getInstance().isTurboSchedDisabled && Folme.appContext != null) {
            try {
                BoostHelper.getInstance().setTurboSchedActionWithoutBlock(new int[]{this.mThread.getThreadId()}, TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS, Folme.appContext);
                BoostHelper.hasBindBigCpu.set(true);
            } catch (Exception unused) {
            }
        }
        super.runAnimTaskOnFrame(j2, j3, j4);
    }
}
