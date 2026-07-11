package miuix.recyclerview.tool;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import androidx.annotation.RequiresApi;
import miuix.appcompat.app.DialogContract;
import miuix.core.util.SystemProperties;
import miuix.mimotion.MiMotionCloudConfig;
import miuix.mimotion.MiMotionHelper;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
@RequiresApi(api = 30)
public class MiMotionRecyclerViewHelper {
    private static final boolean DEBUG = Boolean.parseBoolean(SystemProperties.get(MiMotionHelper.SYSTEM_PROPERTY_MIMOTION_DEBUG, "false"));
    private static final String TAG = "MiMotionHelper";
    private Context mContext;
    private int mCurrentRefreshRate;
    private float mDensity;
    private FrameReduction mFrameReduction;
    private Handler mHandler;
    private String mPackageName;
    private int[] mRefreshRateList = null;
    private int[] mRefreshRateSpeedLimits = null;
    private int[] mRefreshRateSpeedLimitsDp = null;
    private int[] mTouchRefreshRateList = null;
    private int[] mTouchRefreshRateSpeedLimits = null;
    private volatile boolean mIsTouch = false;
    private boolean mNeedAbandon = false;
    private boolean mHasFocus = false;
    private int mOldScrollState = 0;

    public class FrameReduction implements Runnable {
        private int mRefreshRate;

        public FrameReduction(int i2) {
            this.mRefreshRate = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MiMotionHelper.getInstance().setPreferredRefreshRate(this, this.mRefreshRate);
            MiMotionRecyclerViewHelper.this.mCurrentRefreshRate = this.mRefreshRate;
        }
    }

    private void adjustRefreshRateSpeedLimits(float f2) {
        if (this.mRefreshRateSpeedLimits == null) {
            this.mRefreshRateSpeedLimits = new int[this.mRefreshRateSpeedLimitsDp.length];
        }
        if (f2 == this.mDensity) {
            return;
        }
        this.mDensity = f2;
        int i2 = 0;
        while (true) {
            int[] iArr = this.mRefreshRateSpeedLimits;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = (int) (this.mRefreshRateSpeedLimitsDp[i2] * f2);
            i2++;
        }
    }

    private int calculateTouchRefreshRate(int i2) {
        int i3 = 0;
        int i4 = this.mTouchRefreshRateList[0];
        if (i2 == 0) {
            return i4;
        }
        while (true) {
            int[] iArr = this.mTouchRefreshRateSpeedLimits;
            if (i3 >= iArr.length) {
                break;
            }
            if (i2 > iArr[i3]) {
                i4 = this.mTouchRefreshRateList[i3];
                break;
            }
            i3++;
        }
        int i5 = this.mCurrentRefreshRate;
        if (i4 > i5) {
            FrameReduction frameReduction = this.mFrameReduction;
            if (frameReduction != null) {
                this.mHandler.removeCallbacks(frameReduction);
            }
            this.mFrameReduction = null;
            this.mCurrentRefreshRate = i4;
            return i4;
        }
        if (i4 >= i5 || this.mHandler.hasCallbacks(this.mFrameReduction)) {
            return -1;
        }
        FrameReduction frameReduction2 = new FrameReduction(i4);
        this.mFrameReduction = frameReduction2;
        this.mHandler.postDelayed(frameReduction2, 200L);
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$calculateSpeed$0(int i2) {
        MiMotionHelper.getInstance().setPreferredRefreshRate(this, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$touchEvent$1() {
        this.mIsTouch = false;
    }

    public int calculateRefreshRate(int i2) {
        int i3 = this.mRefreshRateList[r0.length - 1];
        if (!this.mHasFocus || this.mNeedAbandon) {
            return this.mCurrentRefreshRate;
        }
        if (i2 == 0) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int[] iArr = this.mRefreshRateSpeedLimits;
            if (i4 >= iArr.length) {
                break;
            }
            if (i2 > iArr[i4]) {
                i3 = this.mRefreshRateList[i4];
                break;
            }
            i4++;
        }
        int i5 = this.mCurrentRefreshRate;
        if (i3 >= i5) {
            int[] iArr2 = this.mRefreshRateList;
            if (i5 != iArr2[iArr2.length - 1] || i3 != iArr2[0]) {
                return i3;
            }
        }
        this.mCurrentRefreshRate = i3;
        return i3;
    }

    public void calculateSpeed(int i2, int i3, int i4, int i5) {
        if (DEBUG) {
            Log.d(TAG, "calculateSpeed---> velocityX:" + i2 + " velocityY:" + i3 + " isTouch:" + this.mIsTouch);
        }
        final int iCalculateRefreshRate = !this.mIsTouch ? calculateRefreshRate(Math.max(Math.abs(i2), Math.abs(i3))) : this.mRefreshRateList[0];
        this.mHandler.post(new Runnable() { // from class: miuix.recyclerview.tool.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f6152a.lambda$calculateSpeed$0(iCalculateRefreshRate);
            }
        });
    }

    public void calculateTouchSpeed(int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return;
        }
        MiMotionHelper.getInstance().setPreferredRefreshRate(this, calculateTouchRefreshRate(Math.max(Math.abs(i2), Math.abs(i3))));
    }

    public boolean initMiMotion(RecyclerView recyclerView) {
        this.mPackageName = recyclerView.getContext().getPackageName();
        this.mContext = recyclerView.getContext();
        if (!MiMotionHelper.getInstance().isEnabled()) {
            return false;
        }
        this.mRefreshRateList = new int[]{120, 60, 40, 30, 24, 0};
        int[] refreshRateSpeedLimitsDp = MiMotionCloudConfig.getInstance().getRefreshRateSpeedLimitsDp();
        this.mRefreshRateSpeedLimitsDp = refreshRateSpeedLimitsDp;
        if (refreshRateSpeedLimitsDp == null) {
            this.mRefreshRateSpeedLimitsDp = new int[]{135, 35, 15, 5, 1, 0};
        }
        adjustRefreshRateSpeedLimits(this.mContext.getResources().getDisplayMetrics().density);
        if (DEBUG) {
            Log.d(TAG, "===========RefreshRateSpeedLimits===========");
            for (int i2 = 0; i2 < this.mRefreshRateSpeedLimits.length; i2++) {
                Log.d(TAG, "RefreshRateSpeedLimits[" + i2 + "] = " + this.mRefreshRateSpeedLimits[i2]);
            }
            Log.d(TAG, "===========RefreshRateSpeedLimits===========");
        }
        this.mTouchRefreshRateList = new int[]{120, 60, 40, 30, 24};
        this.mTouchRefreshRateSpeedLimits = new int[]{DialogContract.BUTTON_SCROLL_WINDOW_HEIGHT_LIMIT_DP, 95, 48, 10, 0};
        Handler handler = recyclerView.getHandler();
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        this.mHandler = handler;
        return true;
    }

    public void onFocusChange(boolean z2) {
        this.mHasFocus = z2;
        this.mNeedAbandon = true;
        MiMotionHelper.getInstance().setPreferredRefreshRate(this, this.mRefreshRateList[0]);
    }

    public void scrollState(RecyclerView recyclerView, int i2) {
        if (this.mNeedAbandon || this.mIsTouch || this.mOldScrollState != 2) {
            this.mOldScrollState = i2;
        } else {
            this.mOldScrollState = i2;
        }
    }

    public void touchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            if (motionEvent.getActionMasked() == 1) {
                this.mHandler.postDelayed(new Runnable() { // from class: miuix.recyclerview.tool.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6151a.lambda$touchEvent$1();
                    }
                }, 800L);
                return;
            }
            return;
        }
        this.mIsTouch = true;
        int i2 = this.mCurrentRefreshRate;
        int i3 = this.mRefreshRateList[0];
        if (i2 != i3) {
            this.mCurrentRefreshRate = i3;
            MiMotionHelper.getInstance().setPreferredRefreshRate(this, this.mRefreshRateList[0]);
        }
        this.mHasFocus = true;
        this.mNeedAbandon = false;
        adjustRefreshRateSpeedLimits(this.mContext.getResources().getDisplayMetrics().density);
    }
}
