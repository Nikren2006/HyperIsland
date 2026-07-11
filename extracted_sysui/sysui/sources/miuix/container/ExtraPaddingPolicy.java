package miuix.container;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.miui.maml.folme.AnimatedPropertyType;
import miuix.internal.util.ViewUtils;
import miuix.responsive.ResponsivePolicy;

/* JADX INFO: loaded from: classes3.dex */
public class ExtraPaddingPolicy {
    public static boolean DEBUGGABLE = true;
    public static final int EXTRA_PADDING_DEFAULT = 0;
    private boolean mEnable;
    private int mLevel;

    @NonNull
    private int[] mPaddingsDp;

    @Nullable
    private int[] mPaddingsDpInMultiColumns;
    private int mPaddingHorizontalCommonDp = 0;
    private int mLastWindowWidthDp = 0;
    private int mLastWindowHeightDp = 0;
    private int mLastContainerWidth = 0;
    private int mLastContainerHeight = 0;
    private boolean mIsFullWindow = true;

    @NonNull
    private int[] mLevelThresholds = null;

    @Nullable
    private int[] mLevelThresholdsInMultiColumns = null;
    private int mWidthLimitedThreshold = 0;
    private int mWidthLimitedExtraPaddingDp = 0;

    public static class Builder {
        ExtraPaddingPolicy mPolicy = new ExtraPaddingPolicy();

        public static ExtraPaddingPolicy createDefault(int i2, int i3, int i4) {
            if (i2 == 2 || i2 == 3) {
                return new Builder().setPaddingHorizontalCommonDp(i4).setThresholds(TypedValues.CycleType.TYPE_EASING, ResponsivePolicy.THRESHOLD_REGULAR_WINDOW, 800).setPaddingsDp(0, i3 * 2, i3 * 4, i3 * 11).setWidthLimitedThreshold(AnimatedPropertyType.RESERVE).create();
            }
            return null;
        }

        public static ExtraPaddingPolicy createLegacyDefault(int i2, int i3, int i4) {
            if (i2 == 2) {
                return new Builder().setPaddingHorizontalCommonDp(i4).setThresholds(ResponsivePolicy.THRESHOLD_REGULAR_WINDOW, ResponsivePolicy.THRESHOLD_LARGE_WINDOW).setPaddingsDp(0, i3 * 9, i3 * 25).setThresholdsInMultiColumns(ResponsivePolicy.THRESHOLD_REGULAR_WINDOW).setPaddingsDpInMultiColumns(0, i3 * 11).create();
            }
            if (i2 == 3) {
                return new Builder().setThresholds(ResponsivePolicy.THRESHOLD_REGULAR_WINDOW).setPaddingsDp(0, i3 * 7).create();
            }
            return null;
        }

        public ExtraPaddingPolicy create() {
            return this.mPolicy;
        }

        public Builder setPaddingHorizontalCommonDp(int i2) {
            this.mPolicy.mPaddingHorizontalCommonDp = i2;
            return this;
        }

        public Builder setPaddingsDp(int... iArr) {
            this.mPolicy.mPaddingsDp = iArr;
            return this;
        }

        public Builder setPaddingsDpInMultiColumns(int... iArr) {
            this.mPolicy.mPaddingsDpInMultiColumns = iArr;
            return this;
        }

        public Builder setThresholds(int... iArr) {
            this.mPolicy.mLevelThresholds = iArr;
            return this;
        }

        public Builder setThresholdsInMultiColumns(int... iArr) {
            this.mPolicy.mLevelThresholdsInMultiColumns = iArr;
            return this;
        }

        public Builder setWidthLimitedThreshold(int i2) {
            this.mPolicy.mWidthLimitedThreshold = i2;
            return this;
        }
    }

    public void applyExtraPadding(View view) {
        int i2;
        int i3;
        if (this.mEnable) {
            int left = view.getLeft();
            int top = view.getTop();
            int right = view.getRight();
            int bottom = view.getBottom();
            int extraPaddingDp = (int) (getExtraPaddingDp() * (view.getResources().getConfiguration().densityDpi / 160.0f));
            if (ViewUtils.isLayoutRtl(view)) {
                i2 = left - extraPaddingDp;
                i3 = right - extraPaddingDp;
            } else {
                i2 = left + extraPaddingDp;
                i3 = right + extraPaddingDp;
            }
            view.layout(i2, top, i3, bottom);
        }
    }

    public int getExtraPaddingDp() {
        return getExtraPaddingDp(true);
    }

    public int getLevel() {
        return this.mLevel;
    }

    public int getWidthLimitedThreshold() {
        return this.mWidthLimitedThreshold;
    }

    public boolean isEnable() {
        return this.mEnable;
    }

    public void onContainerSizeChanged(int i2, int i3, int i4, int i5, float f2, boolean z2) {
        if (this.mLastContainerWidth == i4 && this.mLastWindowWidthDp == i2) {
            return;
        }
        if (DEBUGGABLE) {
            Log.d("ExtraPaddingPolicy", "onContainerSizeChanged new Win w = " + i2 + " h = " + i3 + " new C w = " + i4 + " h = " + i5);
            Log.d("ExtraPaddingPolicy", "onContainerSizeChanged old Win w = " + this.mLastWindowWidthDp + " h = " + this.mLastWindowHeightDp + " old C w = " + this.mLastContainerWidth + " h = " + this.mLastContainerHeight);
            StringBuilder sb = new StringBuilder();
            sb.append("onContainerSizeChanged density ");
            sb.append(f2);
            sb.append(" isInFloatingWindow = ");
            sb.append(z2);
            Log.d("ExtraPaddingPolicy", sb.toString());
        }
        this.mLastWindowWidthDp = i2;
        this.mLastWindowHeightDp = i3;
        this.mLastContainerWidth = i4;
        this.mLastContainerHeight = i5;
        this.mIsFullWindow = (((float) i4) * 1.0f) / (((float) i2) * f2) >= 0.95f || z2;
        if (DEBUGGABLE) {
            Log.d("ExtraPaddingPolicy", "onContainerSizeChanged isFullWindow " + this.mIsFullWindow);
        }
        if (this.mLastWindowHeightDp <= 550) {
            this.mLevel = 0;
            return;
        }
        if (!this.mIsFullWindow && this.mLevelThresholdsInMultiColumns != null) {
            int i6 = 0;
            while (true) {
                int[] iArr = this.mLevelThresholdsInMultiColumns;
                if (i6 >= iArr.length) {
                    break;
                }
                int i7 = (int) (iArr[i6] * f2);
                if (i6 == 0 && i4 < i7) {
                    this.mLevel = i6;
                    break;
                } else if (i4 <= i7) {
                    this.mLevel = i6;
                    break;
                } else {
                    if (i6 == iArr.length - 1) {
                        this.mLevel = i6 + 1;
                    }
                    i6++;
                }
            }
        } else {
            int i8 = 0;
            while (true) {
                int[] iArr2 = this.mLevelThresholds;
                if (i8 >= iArr2.length) {
                    break;
                }
                int i9 = (int) (iArr2[i8] * f2);
                if (i8 == 0 && i4 < i9) {
                    this.mLevel = i8;
                    break;
                } else if (i4 <= i9) {
                    this.mLevel = i8;
                    break;
                } else {
                    if (i8 == iArr2.length - 1) {
                        this.mLevel = i8 + 1;
                    }
                    i8++;
                }
            }
        }
        int i10 = this.mWidthLimitedThreshold;
        if (i10 > 0) {
            float f3 = (i4 / f2) + 0.5f;
            if (f3 > i10) {
                this.mWidthLimitedExtraPaddingDp = (int) ((f3 - i10) / 2.0f);
                return;
            }
        }
        this.mWidthLimitedExtraPaddingDp = 0;
    }

    public void setEnable(boolean z2) {
        this.mEnable = z2;
    }

    public int getExtraPaddingDp(boolean z2) {
        int i2;
        int[] iArr;
        int i3 = (this.mIsFullWindow || (iArr = this.mPaddingsDpInMultiColumns) == null) ? this.mPaddingsDp[this.mLevel] : iArr[this.mLevel];
        if (i3 == 0) {
            return i3;
        }
        if (z2) {
            i2 = this.mWidthLimitedExtraPaddingDp;
        } else {
            i3 += this.mPaddingHorizontalCommonDp;
            i2 = this.mWidthLimitedExtraPaddingDp;
        }
        return i3 + i2;
    }
}
