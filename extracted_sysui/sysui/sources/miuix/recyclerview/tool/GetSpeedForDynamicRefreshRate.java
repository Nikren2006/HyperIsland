package miuix.recyclerview.tool;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import com.xiaomi.onetrack.util.aa;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import miuix.mimotion.MiMotionHelper;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
@RequiresApi(api = 30)
public class GetSpeedForDynamicRefreshRate {
    private static final int COUNT = 3;
    private static final String TAG = "DynamicRefreshRate recy";
    private static int sControlViewHashCode = 0;
    private static boolean sHasGetProperty = false;
    private static int[] sRefreshRateList;
    private static int[] sRefreshRateSpeedLimits;
    private int mCurrentRefreshRate;
    private final Display mDisplay;
    private final boolean mIsEnable;
    private MiMotionRecyclerViewHelper mMiMotionRecyclerViewHelper;
    private RecyclerView mRecyclerView;
    private final Window mWindow;
    private volatile boolean mIsTouch = false;
    private boolean mHasFocus = false;
    private int mCountIndex = 0;
    private long mStartTime = -1;
    private long mTotalDistance = 0;
    private boolean mNeedAbandon = false;
    private int mOldScrollState = 0;
    private int mRefreshRate = -1;

    public GetSpeedForDynamicRefreshRate(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        Activity activity = getActivity(recyclerView.getContext());
        Display display = activity != null ? activity.getDisplay() : null;
        this.mDisplay = display;
        Window window = activity != null ? activity.getWindow() : null;
        this.mWindow = window;
        boolean z2 = (!getParam() || display == null || window == null) ? false : true;
        this.mIsEnable = z2;
        if (!z2) {
            Log.e(TAG, "dynamic is not enable");
        }
        if (display == null || window == null) {
            return;
        }
        if (MiMotionHelper.isSupportMiMotion()) {
            MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = new MiMotionRecyclerViewHelper();
            this.mMiMotionRecyclerViewHelper = miMotionRecyclerViewHelper;
            if (!miMotionRecyclerViewHelper.initMiMotion(recyclerView)) {
                this.mMiMotionRecyclerViewHelper = null;
            }
        }
        int[] iArr = sRefreshRateList;
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        this.mCurrentRefreshRate = iArr[0];
    }

    private int calculateRefreshRate(int i2) {
        int i3 = sRefreshRateList[r0.length - 1];
        if (!this.mHasFocus || this.mNeedAbandon) {
            return -1;
        }
        if (i2 == 0) {
            return i3;
        }
        if (this.mCountIndex == 0) {
            this.mTotalDistance = 0L;
            this.mStartTime = System.currentTimeMillis();
        }
        int i4 = this.mCountIndex + 1;
        this.mCountIndex = i4;
        this.mTotalDistance += (long) i2;
        if (i4 < 3) {
            return -1;
        }
        int iAbs = Math.abs(Math.round(this.mTotalDistance / ((System.currentTimeMillis() - this.mStartTime) / 1000.0f)));
        this.mCountIndex = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = sRefreshRateSpeedLimits;
            if (i5 >= iArr.length) {
                break;
            }
            if (iAbs > iArr[i5]) {
                i3 = sRefreshRateList[i5];
                break;
            }
            i5++;
        }
        int i6 = this.mCurrentRefreshRate;
        if (i3 >= i6) {
            int[] iArr2 = sRefreshRateList;
            if (i6 != iArr2[iArr2.length - 1] || i3 != iArr2[0]) {
                return -1;
            }
        }
        this.mCurrentRefreshRate = i3;
        return i3;
    }

    private void checkMiMotionRecyclerViewHelper() {
        if (!MiMotionHelper.isSupportMiMotion() || !MiMotionHelper.getInstance().isEnabled()) {
            this.mMiMotionRecyclerViewHelper = null;
            return;
        }
        if (this.mMiMotionRecyclerViewHelper == null) {
            MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = new MiMotionRecyclerViewHelper();
            this.mMiMotionRecyclerViewHelper = miMotionRecyclerViewHelper;
            if (miMotionRecyclerViewHelper.initMiMotion(this.mRecyclerView)) {
                return;
            }
            this.mMiMotionRecyclerViewHelper = null;
        }
    }

    private static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private static boolean getParam() {
        boolean z2 = false;
        if (sHasGetProperty) {
            return (sRefreshRateList == null || sRefreshRateSpeedLimits == null) ? false : true;
        }
        try {
            try {
                String str = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, "ro.vendor.display.dynamic_refresh_rate");
                if (str == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("dynamic params is ");
                    sb.append((sRefreshRateList == null || sRefreshRateSpeedLimits == null) ? false : true);
                    Log.i(TAG, sb.toString());
                    sHasGetProperty = true;
                    return false;
                }
                String[] strArrSplit = str.split(MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
                if (strArrSplit.length != 2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("dynamic params is ");
                    sb2.append((sRefreshRateList == null || sRefreshRateSpeedLimits == null) ? false : true);
                    Log.i(TAG, sb2.toString());
                    sHasGetProperty = true;
                    return false;
                }
                String[] strArrSplit2 = strArrSplit[0].split(aa.f3429b);
                String[] strArrSplit3 = strArrSplit[1].split(aa.f3429b);
                if (strArrSplit3.length != strArrSplit2.length - 1) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("dynamic params is ");
                    sb3.append((sRefreshRateList == null || sRefreshRateSpeedLimits == null) ? false : true);
                    Log.i(TAG, sb3.toString());
                    sHasGetProperty = true;
                    return false;
                }
                sRefreshRateList = new int[strArrSplit2.length];
                for (int i2 = 0; i2 < strArrSplit2.length; i2++) {
                    sRefreshRateList[i2] = Integer.parseInt(strArrSplit2[i2]);
                }
                sRefreshRateSpeedLimits = new int[strArrSplit3.length];
                for (int i3 = 0; i3 < strArrSplit3.length; i3++) {
                    sRefreshRateSpeedLimits[i3] = Integer.parseInt(strArrSplit3[i3]);
                }
                StringBuilder sb4 = new StringBuilder();
                sb4.append("dynamic params is ");
                if (sRefreshRateList != null && sRefreshRateSpeedLimits != null) {
                    z2 = true;
                }
                sb4.append(z2);
                Log.i(TAG, sb4.toString());
                sHasGetProperty = true;
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                StringBuilder sb5 = new StringBuilder();
                sb5.append("dynamic params is ");
                sb5.append((sRefreshRateList == null || sRefreshRateSpeedLimits == null) ? false : true);
                Log.i(TAG, sb5.toString());
                sHasGetProperty = true;
                sRefreshRateList = null;
                sRefreshRateSpeedLimits = null;
                return false;
            }
        } catch (Throwable th) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("dynamic params is ");
            if (sRefreshRateList != null) {
                z2 = true;
            }
            sb6.append(z2);
            Log.i(TAG, sb6.toString());
            sHasGetProperty = true;
            throw th;
        }
        StringBuilder sb62 = new StringBuilder();
        sb62.append("dynamic params is ");
        if (sRefreshRateList != null && sRefreshRateSpeedLimits != null) {
            z2 = true;
        }
        sb62.append(z2);
        Log.i(TAG, sb62.toString());
        sHasGetProperty = true;
        throw th;
    }

    private void setRefreshRate(int i2, boolean z2) {
        Display.Mode[] supportedModes = this.mDisplay.getSupportedModes();
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        if (i2 == this.mRefreshRate || supportedModes == null) {
            return;
        }
        this.mRefreshRate = i2;
        for (Display.Mode mode : supportedModes) {
            if (Math.abs(mode.getRefreshRate() - i2) <= 1.0f) {
                if (z2 || hashCode() == sControlViewHashCode || mode.getRefreshRate() > this.mRefreshRate) {
                    sControlViewHashCode = hashCode();
                    Log.i(TAG, sControlViewHashCode + " set Refresh rate to: " + i2 + ", mode is: " + mode.getModeId());
                    attributes.preferredDisplayModeId = mode.getModeId();
                    this.mWindow.setAttributes(attributes);
                    return;
                }
                return;
            }
        }
    }

    public void calculateSpeed(int i2, int i3, int i4, int i5) {
        int iCalculateRefreshRate;
        MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = this.mMiMotionRecyclerViewHelper;
        if (miMotionRecyclerViewHelper != null) {
            miMotionRecyclerViewHelper.calculateSpeed(i4, i5, i2, i3);
            return;
        }
        if (this.mIsEnable) {
            if ((i2 == 0 && i3 == 0) || this.mIsTouch || (iCalculateRefreshRate = calculateRefreshRate(Math.max(Math.abs(i2), Math.abs(i3)))) == -1) {
                return;
            }
            setRefreshRate(iCalculateRefreshRate, false);
        }
    }

    public void calculateTouchSpeed(int i2, int i3, int i4, int i5) {
        MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = this.mMiMotionRecyclerViewHelper;
        if (miMotionRecyclerViewHelper != null) {
            miMotionRecyclerViewHelper.calculateTouchSpeed(i4, i5);
        }
    }

    public void onFocusChange(boolean z2) {
        MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = this.mMiMotionRecyclerViewHelper;
        if (miMotionRecyclerViewHelper != null) {
            miMotionRecyclerViewHelper.onFocusChange(z2);
        } else if (this.mIsEnable) {
            this.mHasFocus = z2;
            this.mNeedAbandon = true;
            setRefreshRate(sRefreshRateList[0], false);
        }
    }

    public void scrollState(RecyclerView recyclerView, int i2) {
        MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = this.mMiMotionRecyclerViewHelper;
        if (miMotionRecyclerViewHelper != null) {
            miMotionRecyclerViewHelper.scrollState(recyclerView, i2);
            return;
        }
        if (this.mIsEnable) {
            if (this.mNeedAbandon || this.mIsTouch || this.mOldScrollState != 2) {
                this.mOldScrollState = i2;
                return;
            }
            this.mOldScrollState = i2;
            if ((recyclerView.canScrollVertically(-1) && recyclerView.canScrollVertically(1)) || (recyclerView.canScrollHorizontally(-1) && recyclerView.canScrollVertically(1))) {
                int[] iArr = sRefreshRateList;
                setRefreshRate(iArr[iArr.length - 1], false);
            }
        }
    }

    public void touchEvent(MotionEvent motionEvent) {
        checkMiMotionRecyclerViewHelper();
        MiMotionRecyclerViewHelper miMotionRecyclerViewHelper = this.mMiMotionRecyclerViewHelper;
        if (miMotionRecyclerViewHelper != null) {
            miMotionRecyclerViewHelper.touchEvent(motionEvent);
            return;
        }
        if (this.mIsEnable) {
            if (motionEvent.getActionMasked() != 0) {
                if (motionEvent.getActionMasked() == 1) {
                    this.mIsTouch = false;
                    return;
                }
                return;
            }
            this.mIsTouch = true;
            int i2 = sRefreshRateList[0];
            this.mCurrentRefreshRate = i2;
            this.mCountIndex = 0;
            setRefreshRate(i2, true);
            this.mHasFocus = true;
            this.mNeedAbandon = false;
        }
    }
}
