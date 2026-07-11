package miui.systemui.util;

import android.content.Context;
import android.graphics.HardwareRenderer;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import android.view.ThreadedRenderer;
import android.view.View;
import d0.AbstractC0320a;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class BoostHelper {
    public static final int MIBRIDGE_BIG_CORE = 2;
    public static final int MIBRIDGE_MAX_FREQ = 1;
    public static final int MIBRIDGE_RT_PRIORITY = 1;
    public static final int SCHED_MODE_DEFAULT = 1;
    public static final int SCHED_MODE_OPEN_PASSWORD = 15;
    private static final int SCHED_MODE_WITH_CPU_BIG = 7;
    private static final int SCHED_MODE_WITH_CPU_HIGH_FREQ = 6;
    private static final long SKIP_PRIORITY_UPDATE_DURATION = 10;
    private static final String TAG = "plugin_BoostHelper";
    private long mLastPriorityRequestTime;
    private long mLastPriorityUpdateTime;
    private int mPid;
    private static final BoostHelper sInjector = new BoostHelper();
    public static int sHwuiTaskTid0 = 0;
    public static int sHwuiTaskTid1 = 0;
    private boolean mIsSchedThreadDisabled = false;
    private Class<?> mProcessManagerClass = null;
    private Method mSchedThreadsMethod = null;
    private Method mSchedThreadsMethodLegacy = null;
    private Method mDynamicVIPTaskIfNeededMethod = null;
    private Object mTurboSchedManager = null;
    private int mRenderThreadTid = 0;
    private int mDynamicIslandRenderThreadTid = 0;
    private boolean mMiBridgePermission = false;

    private BoostHelper() {
    }

    private void assembleHwuiThread(ArrayList<Integer> arrayList) {
        int i2 = sHwuiTaskTid0;
        int i3 = sHwuiTaskTid1;
        if (i2 > 0) {
            arrayList.add(Integer.valueOf(i2));
        }
        if (i3 > 0) {
            arrayList.add(Integer.valueOf(i3));
        }
    }

    private void boostThreadInternal(int[] iArr, long j2, int i2) {
        try {
            if (this.mProcessManagerClass == null || (this.mSchedThreadsMethod == null && this.mSchedThreadsMethodLegacy == null)) {
                synchronized (BoostHelper.class) {
                    if (this.mProcessManagerClass == null) {
                        Class<?> cls = Class.forName("miui.process.ProcessManager");
                        this.mProcessManagerClass = cls;
                        try {
                            this.mSchedThreadsMethod = cls.getDeclaredMethod("beginSchedThreads", int[].class, Long.TYPE, Integer.TYPE);
                        } catch (NoSuchMethodException unused) {
                        }
                        if (this.mSchedThreadsMethod == null) {
                            this.mSchedThreadsMethodLegacy = this.mProcessManagerClass.getDeclaredMethod("beginSchedThreads", int[].class, Long.TYPE);
                        }
                    }
                    if (this.mProcessManagerClass != null && (this.mSchedThreadsMethod != null || this.mSchedThreadsMethodLegacy != null)) {
                    }
                    this.mIsSchedThreadDisabled = true;
                    return;
                }
            }
            if (iArr.length > 0) {
                Method method = this.mSchedThreadsMethod;
                if (method != null) {
                    method.setAccessible(true);
                    this.mSchedThreadsMethod.invoke(null, iArr, Long.valueOf(j2), Integer.valueOf(i2));
                    Log.i(TAG, "boostThread sched thread " + Arrays.toString(iArr) + " is run,mod=" + i2);
                    return;
                }
                this.mSchedThreadsMethodLegacy.setAccessible(true);
                this.mSchedThreadsMethodLegacy.invoke(null, iArr, Long.valueOf(j2));
                Log.i(TAG, "boostThreadLegacy sched thread " + Arrays.toString(iArr) + " is run,mod=" + i2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean checkMiBridgePermission() {
        if (!this.mMiBridgePermission) {
            this.mMiBridgePermission = AbstractC0320a.a("miui.systemui.plugin", Process.myUid());
        }
        return this.mMiBridgePermission;
    }

    private int getDynamicIslandRenderThreadId(View view) {
        Class<? super Object> superclass;
        if (this.mDynamicIslandRenderThreadTid == 0) {
            int iIntValue = 0;
            try {
                ThreadedRenderer threadedRenderer = view.getThreadedRenderer();
                if (threadedRenderer != null && (superclass = threadedRenderer.getClass().getSuperclass()) != null) {
                    Method declaredMethod = superclass.getDeclaredMethod("nGetRenderThreadTid", Long.TYPE);
                    declaredMethod.setAccessible(true);
                    Field declaredField = superclass.getDeclaredField("mNativeProxy");
                    declaredField.setAccessible(true);
                    iIntValue = ((Integer) declaredMethod.invoke(threadedRenderer, Long.valueOf(declaredField.getLong(threadedRenderer)))).intValue();
                    Log.d(TAG, "getDynamicIslandRenderThreadId  tid=" + iIntValue);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mDynamicIslandRenderThreadTid = iIntValue;
        }
        return this.mDynamicIslandRenderThreadTid;
    }

    public static BoostHelper getInstance() {
        return sInjector;
    }

    private int getRenderThreadId(View view) {
        if (this.mRenderThreadTid == 0) {
            int iIntValue = 0;
            try {
                ThreadedRenderer threadedRenderer = view.getThreadedRenderer();
                Class<? super Object> superclass = threadedRenderer.getClass().getSuperclass();
                Method declaredMethod = superclass.getDeclaredMethod("nGetRenderThreadTid", Long.TYPE);
                declaredMethod.setAccessible(true);
                Field declaredField = superclass.getDeclaredField("mNativeProxy");
                declaredField.setAccessible(true);
                iIntValue = ((Integer) declaredMethod.invoke(threadedRenderer, Long.valueOf(declaredField.getLong(threadedRenderer)))).intValue();
                Log.d(TAG, "getRenderThreadId  tid=" + iIntValue);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mRenderThreadTid = iIntValue;
        }
        return this.mRenderThreadTid;
    }

    private static Object getViewRootImpl(View view) {
        if (view != null) {
            try {
                return view.getClass().getMethod("getViewRootImpl", null).invoke(view, null);
            } catch (Exception unused) {
                Log.e(TAG, "get ViewRootImpl failed");
            }
        }
        return null;
    }

    public static void initHwuiTaskId(View view) {
        final Object viewRootImpl = getViewRootImpl(view);
        if (viewRootImpl == null) {
            viewRootImpl = getViewRootImpl(view.getRootView());
        }
        if (viewRootImpl == null) {
            Log.d(TAG, "viewRootImpl is null");
            return;
        }
        try {
            ReflectUtil.callObjectMethod(viewRootImpl, "registerRtFrameCallback", new Class[]{HardwareRenderer.FrameDrawingCallback.class}, new HardwareRenderer.FrameDrawingCallback() { // from class: miui.systemui.util.i
                public final void onFrameDraw(long j2) {
                    BoostHelper.lambda$initHwuiTaskId$0(viewRootImpl, j2);
                }
            });
        } catch (Exception unused) {
            Log.e(TAG, "initHwuiTaskId failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initHwuiTaskId$0(Object obj, long j2) {
        Log.d(TAG, "onFrameDraw processId:" + Process.myTid() + ",viewRoot:" + System.identityHashCode(obj));
        if (sHwuiTaskTid0 == 0) {
            sHwuiTaskTid0 = Process.myTid();
        }
        if (sHwuiTaskTid1 != 0 || Process.myTid() == sHwuiTaskTid0) {
            return;
        }
        sHwuiTaskTid1 = Process.myTid();
    }

    public void boost(long j2, View view) {
        boost(j2, view, 1);
    }

    public void boostWithBigKernel(long j2, View view) {
        boost(j2, view, 7);
    }

    public void boostWithCpuFreq(long j2, View view) {
        boost(j2, view, 6);
    }

    public void initDynamicVIPTaskMethod(Context context) {
        if (this.mDynamicVIPTaskIfNeededMethod == null || this.mTurboSchedManager == null) {
            try {
                Class<?> cls = Class.forName("miui.turbosched.TurboSchedManager");
                this.mTurboSchedManager = cls.getDeclaredConstructor(Context.class).newInstance(context);
                Class cls2 = Integer.TYPE;
                this.mDynamicVIPTaskIfNeededMethod = cls.getDeclaredMethod("setDynamicVIPTaskIfNeeded", cls2, cls2);
            } catch (Exception e2) {
                Log.d(TAG, "initDynamicVIPTaskMethod exception: " + e2);
            }
        }
    }

    public void requestDynamicIslandThreadLevelPriority(int i2, View view) {
        int i3;
        if (i2 <= 0) {
            Log.d(TAG, "持续时间必须大于0");
            return;
        }
        if (view == null) {
            Log.d(TAG, "无效的View");
            return;
        }
        this.mPid = Process.myPid();
        getDynamicIslandRenderThreadId(view);
        if (this.mPid <= 0) {
            Log.d(TAG, "无效的进程ID");
            return;
        }
        if (this.mDynamicIslandRenderThreadTid <= 0) {
            Log.d(TAG, "无效的DynamicIslandRenderThreadTid");
            return;
        }
        if (System.currentTimeMillis() - this.mLastPriorityRequestTime < SKIP_PRIORITY_UPDATE_DURATION) {
            return;
        }
        try {
            try {
                Trace.beginSection("RequestDynamicIslandThreadLevelPriority");
                if (!checkMiBridgePermission()) {
                    Trace.endSection();
                    return;
                }
                AbstractC0320a.c(Process.myUid(), 1, i2);
                AbstractC0320a.d(Process.myUid(), 1, i2);
                int i4 = this.mRenderThreadTid;
                int[] iArr = (i4 <= 0 || i4 == (i3 = this.mDynamicIslandRenderThreadTid)) ? new int[]{this.mPid, this.mDynamicIslandRenderThreadTid} : new int[]{this.mPid, i3, i4};
                AbstractC0320a.e(Process.myUid(), iArr, i2, 1);
                AbstractC0320a.b(Process.myUid(), iArr, 2, i2);
                this.mLastPriorityRequestTime = System.currentTimeMillis();
            } catch (Exception e2) {
                Log.d(TAG, "requestDynamicIslandThreadLevelPriority exception: " + e2);
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public void setDynamicVIPTaskIfNeeded(int i2, View view) {
        if (i2 <= 0) {
            Log.d(TAG, "持续时间必须大于0");
            return;
        }
        if (view == null) {
            Log.d(TAG, "无效的View");
            return;
        }
        this.mPid = Process.myPid();
        getRenderThreadId(view);
        initDynamicVIPTaskMethod(view.getContext());
        if (this.mPid <= 0) {
            Log.d(TAG, "无效的进程ID");
            return;
        }
        if (this.mRenderThreadTid <= 0) {
            Log.d(TAG, "无效的RenderThreadID");
            return;
        }
        if (System.currentTimeMillis() - this.mLastPriorityUpdateTime < SKIP_PRIORITY_UPDATE_DURATION) {
            return;
        }
        if (this.mDynamicVIPTaskIfNeededMethod == null) {
            Log.d(TAG, "无效的DynamicVIPTaskIfNeededMethod");
            return;
        }
        try {
            if (this.mTurboSchedManager == null) {
                Log.d(TAG, "无效的TurboSchedManager");
                return;
            }
            try {
                Trace.beginSection("BoostHelper.Plugin.setDynamicVIPTaskIfNeeded");
                this.mDynamicVIPTaskIfNeededMethod.invoke(this.mTurboSchedManager, Integer.valueOf(this.mPid), Integer.valueOf(i2));
                this.mDynamicVIPTaskIfNeededMethod.invoke(this.mTurboSchedManager, Integer.valueOf(this.mRenderThreadTid), Integer.valueOf(i2));
                this.mLastPriorityUpdateTime = System.currentTimeMillis();
            } catch (Exception e2) {
                Log.d(TAG, "setDynamicVIPTaskIfNeeded exception: " + e2);
            }
        } finally {
            Trace.endSection();
        }
    }

    public void boost(long j2, View view, int i2) {
        boost(j2, view, i2, Process.myPid());
    }

    public void boost(long j2, View view, int i2, int i3) {
        if (this.mIsSchedThreadDisabled) {
            Log.e(TAG, "boost sched thread is disabled");
            return;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (i3 > 0) {
            arrayList.add(Integer.valueOf(i3));
            Log.i(TAG, "boost: add myTid: " + i3);
        }
        if (view != null) {
            getRenderThreadId(view);
        }
        int i4 = this.mRenderThreadTid;
        if (i4 > 0) {
            arrayList.add(Integer.valueOf(i4));
            Log.i(TAG, "boost: add mRenderThreadTid: " + this.mRenderThreadTid);
        }
        assembleHwuiThread(arrayList);
        if (arrayList.size() > 0) {
            int[] iArr = new int[arrayList.size()];
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                iArr[i5] = arrayList.get(i5).intValue();
            }
            boostThreadInternal(iArr, j2, i2);
        }
    }
}
