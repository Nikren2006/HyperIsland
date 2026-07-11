package miuix.animation.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.LinkNode;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
public class AnimTask extends LinkNode<AnimTask> implements Runnable {
    public static final int MAX_ANIM_COUNT_SINGLE_TASK = 100;
    public static final int MAX_MAIN_THREAD_TASK_SIZE = 4000;
    public static final int MAX_SUB_THREAD_TASK_SIZE = Math.round(56000.0f / (ThreadPoolUtil.MAX_SPLIT_COUNT - 1));
    public static final byte OP_CANCEL = 4;
    public static final byte OP_END = 3;
    public static final byte OP_FAILED = 5;
    public static final byte OP_INVALID = 0;
    public static final byte OP_REUSE = 6;
    public static final byte OP_START = 1;
    public static final byte OP_UPDATE = 2;
    public final AnimStats animStats = new AnimStats();
    public double delta;
    public long deltaTNanos;
    public int frameCount;
    public TransitionInfo info;
    public boolean runInMainThread;

    @Nullable
    public AnimScheduler scheduler;
    public int startPos;
    public long totalTNanos;

    public static void asyncStart(AnimTask animTask, AnimScheduler animScheduler, long j2, long j3, int i2, double d2) {
        animTask.totalTNanos = j2;
        animTask.deltaTNanos = j3;
        animTask.runInMainThread = false;
        animTask.scheduler = animScheduler;
        animTask.frameCount = i2;
        animTask.delta = d2;
        ThreadPoolUtil.post(animTask);
    }

    public static int getAnimCountOfTaskStack(AnimTask animTask) {
        int i2 = 0;
        while (animTask != null) {
            i2 += animTask.animStats.animCount;
            animTask = (AnimTask) animTask.next;
        }
        return i2;
    }

    public static boolean isRunning(byte b2) {
        return b2 == 1 || b2 == 2;
    }

    public static void start(AnimTask animTask, AnimScheduler animScheduler, long j2, long j3, int i2, double d2) {
        animTask.totalTNanos = j2;
        animTask.deltaTNanos = j3;
        animTask.runInMainThread = true;
        animTask.scheduler = animScheduler;
        animTask.frameCount = i2;
        animTask.delta = d2;
        animTask.run();
    }

    public int getAnimCount() {
        return this.animStats.animCount;
    }

    @Override // java.lang.Runnable
    public void run() {
        long j2;
        String str;
        String str2;
        long j3;
        long j4;
        int i2;
        double d2;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long id = Thread.currentThread().getId();
        boolean zIsLogDetailEnable = LogUtils.isLogDetailEnable();
        if (zIsLogDetailEnable) {
            LogUtils.logThread(CommonUtils.TAG, "++++ AnimTask run stack onFrame start belong to Scheduler@" + this.scheduler.hashCode() + "-" + id);
        }
        try {
            j3 = this.totalTNanos;
            j4 = this.deltaTNanos;
            i2 = this.frameCount;
            d2 = this.delta;
            j2 = id;
            str = CommonUtils.TAG;
            str2 = "-";
        } catch (Exception e2) {
            e = e2;
            j2 = id;
            str = CommonUtils.TAG;
            str2 = "-";
        }
        try {
            AnimTaskStackRunner.doAnimationFrame(this, j3, j4, i2, d2, true);
        } catch (Exception e3) {
            e = e3;
            LogUtils.logThread(str, "---- AnimTaskRunner.doAnimationFrame failed", Log.getStackTraceString(e));
        }
        int iDecrementAndGet = this.scheduler.runningStackCount.decrementAndGet();
        if (zIsLogDetailEnable) {
            LogUtils.logThread(str, "---- AnimTask run stack onFrame end cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " runStackCount " + iDecrementAndGet + " belong to Scheduler@" + this.scheduler.hashCode() + str2 + j2);
        }
        if (iDecrementAndGet == 0) {
            this.scheduler.executeUpdate();
        }
    }

    public void setup(int i2, int i3, int i4) {
        this.animStats.clear();
        AnimStats animStats = this.animStats;
        animStats.animCount = i3;
        animStats.focusCount = i4;
        this.startPos = i2;
    }

    @NonNull
    public String toString() {
        return "AnimTask@" + hashCode() + "{info.id=" + this.info.id + " start=" + this.startPos + " animStats=" + this.animStats + "}";
    }

    public void updateAnimStats() {
        List<UpdateInfo> list = this.info.updateList;
        int i2 = this.startPos;
        int i3 = this.animStats.animCount + i2;
        while (i2 < i3) {
            UpdateInfo updateInfo = list.get(i2);
            if (updateInfo != null) {
                if (updateInfo.animInfo.op == 0 || updateInfo.animInfo.op == 1) {
                    this.animStats.prepareCount++;
                } else {
                    this.animStats.startedCount++;
                    byte b2 = updateInfo.animInfo.op;
                    if (b2 == 3) {
                        this.animStats.endCount++;
                    } else if (b2 == 4) {
                        this.animStats.cancelCount++;
                    } else if (b2 == 5 || b2 == 6) {
                        this.animStats.failCount++;
                    }
                }
            }
            i2++;
        }
    }
}
