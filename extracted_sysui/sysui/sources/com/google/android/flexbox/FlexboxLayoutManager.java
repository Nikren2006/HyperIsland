package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements com.google.android.flexbox.a, RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = false;
    private static final String TAG = "FlexboxLayoutManager";
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private b mAnchorInfo;
    private final Context mContext;
    private int mDirtyPosition;
    private int mFlexDirection;
    private List<com.google.android.flexbox.c> mFlexLines;
    private d.a mFlexLinesResult;
    private int mFlexWrap;
    private final com.google.android.flexbox.d mFlexboxHelper;
    private boolean mFromBottomToTop;
    private boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight;
    private int mLastWidth;
    private d mLayoutState;
    private int mMaxLine;
    private OrientationHelper mOrientationHelper;
    private View mParent;
    private e mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private RecyclerView.Recycler mRecycler;
    private RecyclerView.State mState;
    private OrientationHelper mSubOrientationHelper;
    private SparseArray<View> mViewCache;

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1531a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1532b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1533c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1534d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1535e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1536f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1537g;

        public b() {
            this.f1534d = 0;
        }

        public final void q() {
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                this.f1533c = this.f1535e ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            } else {
                this.f1533c = this.f1535e ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
        }

        public final void r(View view) {
            OrientationHelper orientationHelper = FlexboxLayoutManager.this.mFlexWrap == 0 ? FlexboxLayoutManager.this.mSubOrientationHelper : FlexboxLayoutManager.this.mOrientationHelper;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                if (this.f1535e) {
                    this.f1533c = orientationHelper.getDecoratedEnd(view) + orientationHelper.getTotalSpaceChange();
                } else {
                    this.f1533c = orientationHelper.getDecoratedStart(view);
                }
            } else if (this.f1535e) {
                this.f1533c = orientationHelper.getDecoratedStart(view) + orientationHelper.getTotalSpaceChange();
            } else {
                this.f1533c = orientationHelper.getDecoratedEnd(view);
            }
            this.f1531a = FlexboxLayoutManager.this.getPosition(view);
            this.f1537g = false;
            int[] iArr = FlexboxLayoutManager.this.mFlexboxHelper.f1580c;
            int i2 = this.f1531a;
            if (i2 == -1) {
                i2 = 0;
            }
            int i3 = iArr[i2];
            this.f1532b = i3 != -1 ? i3 : 0;
            if (FlexboxLayoutManager.this.mFlexLines.size() > this.f1532b) {
                this.f1531a = ((com.google.android.flexbox.c) FlexboxLayoutManager.this.mFlexLines.get(this.f1532b)).f1574o;
            }
        }

        public final void s() {
            this.f1531a = -1;
            this.f1532b = -1;
            this.f1533c = Integer.MIN_VALUE;
            this.f1536f = false;
            this.f1537g = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    this.f1535e = FlexboxLayoutManager.this.mFlexDirection == 1;
                    return;
                } else {
                    this.f1535e = FlexboxLayoutManager.this.mFlexWrap == 2;
                    return;
                }
            }
            if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                this.f1535e = FlexboxLayoutManager.this.mFlexDirection == 3;
            } else {
                this.f1535e = FlexboxLayoutManager.this.mFlexWrap == 2;
            }
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1531a + ", mFlexLinePosition=" + this.f1532b + ", mCoordinate=" + this.f1533c + ", mPerpendicularCoordinate=" + this.f1534d + ", mLayoutFromEnd=" + this.f1535e + ", mValid=" + this.f1536f + ", mAssignedFromSavedState=" + this.f1537g + '}';
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1548a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1549b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1550c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1551d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1552e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1553f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1554g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1555h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1556i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1557j;

        public d() {
            this.f1555h = 1;
            this.f1556i = 1;
        }

        public static /* synthetic */ int i(d dVar) {
            int i2 = dVar.f1550c;
            dVar.f1550c = i2 + 1;
            return i2;
        }

        public static /* synthetic */ int j(d dVar) {
            int i2 = dVar.f1550c;
            dVar.f1550c = i2 - 1;
            return i2;
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.f1548a + ", mFlexLinePosition=" + this.f1550c + ", mPosition=" + this.f1551d + ", mOffset=" + this.f1552e + ", mScrollingOffset=" + this.f1553f + ", mLastScrollDelta=" + this.f1554g + ", mItemDirection=" + this.f1555h + ", mLayoutDirection=" + this.f1556i + '}';
        }

        public final boolean w(RecyclerView.State state, List list) {
            int i2;
            int i3 = this.f1551d;
            return i3 >= 0 && i3 < state.getItemCount() && (i2 = this.f1550c) >= 0 && i2 < list.size();
        }
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    private void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new d();
        }
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    public static boolean isMeasurementUpToDate(int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (i4 > 0 && i2 != i4) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i2;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i2;
        }
        return true;
    }

    private boolean shouldMeasureChild(View view, int i2, int i3, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
    }

    public final void A(RecyclerView.Recycler recycler, d dVar) {
        int childCount;
        if (dVar.f1553f >= 0 && (childCount = getChildCount()) != 0) {
            int i2 = this.mFlexboxHelper.f1580c[getPosition(getChildAt(0))];
            int i3 = -1;
            if (i2 == -1) {
                return;
            }
            com.google.android.flexbox.c cVar = this.mFlexLines.get(i2);
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    break;
                }
                View childAt = getChildAt(i4);
                if (!e(childAt, dVar.f1553f)) {
                    break;
                }
                if (cVar.f1575p == getPosition(childAt)) {
                    if (i2 >= this.mFlexLines.size() - 1) {
                        i3 = i4;
                        break;
                    } else {
                        i2 += dVar.f1556i;
                        cVar = this.mFlexLines.get(i2);
                        i3 = i4;
                    }
                }
                i4++;
            }
            recycleChildren(recycler, 0, i3);
        }
    }

    public final void B() {
        int heightMode = isMainAxisDirectionHorizontal() ? getHeightMode() : getWidthMode();
        this.mLayoutState.f1549b = heightMode == 0 || heightMode == Integer.MIN_VALUE;
    }

    public final void C() {
        int layoutDirection = getLayoutDirection();
        int i2 = this.mFlexDirection;
        if (i2 == 0) {
            this.mIsRtl = layoutDirection == 1;
            this.mFromBottomToTop = this.mFlexWrap == 2;
            return;
        }
        if (i2 == 1) {
            this.mIsRtl = layoutDirection != 1;
            this.mFromBottomToTop = this.mFlexWrap == 2;
            return;
        }
        if (i2 == 2) {
            boolean z2 = layoutDirection == 1;
            this.mIsRtl = z2;
            if (this.mFlexWrap == 2) {
                this.mIsRtl = !z2;
            }
            this.mFromBottomToTop = false;
            return;
        }
        if (i2 != 3) {
            this.mIsRtl = false;
            this.mFromBottomToTop = false;
            return;
        }
        boolean z3 = layoutDirection == 1;
        this.mIsRtl = z3;
        if (this.mFlexWrap == 2) {
            this.mIsRtl = !z3;
        }
        this.mFromBottomToTop = true;
    }

    public final boolean D(RecyclerView.State state, b bVar) {
        if (getChildCount() == 0) {
            return false;
        }
        View viewK = bVar.f1535e ? k(state.getItemCount()) : i(state.getItemCount());
        if (viewK == null) {
            return false;
        }
        bVar.r(viewK);
        if (state.isPreLayout() || !supportsPredictiveItemAnimations()) {
            return true;
        }
        if (this.mOrientationHelper.getDecoratedStart(viewK) < this.mOrientationHelper.getEndAfterPadding() && this.mOrientationHelper.getDecoratedEnd(viewK) >= this.mOrientationHelper.getStartAfterPadding()) {
            return true;
        }
        bVar.f1533c = bVar.f1535e ? this.mOrientationHelper.getEndAfterPadding() : this.mOrientationHelper.getStartAfterPadding();
        return true;
    }

    public final boolean E(RecyclerView.State state, b bVar, e eVar) {
        int i2;
        if (!state.isPreLayout() && (i2 = this.mPendingScrollPosition) != -1) {
            if (i2 >= 0 && i2 < state.getItemCount()) {
                bVar.f1531a = this.mPendingScrollPosition;
                bVar.f1532b = this.mFlexboxHelper.f1580c[bVar.f1531a];
                e eVar2 = this.mPendingSavedState;
                if (eVar2 != null && eVar2.w(state.getItemCount())) {
                    bVar.f1533c = this.mOrientationHelper.getStartAfterPadding() + eVar.f1559b;
                    bVar.f1537g = true;
                    bVar.f1532b = -1;
                    return true;
                }
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                        bVar.f1533c = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                    } else {
                        bVar.f1533c = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
                    }
                    return true;
                }
                View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                if (viewFindViewByPosition == null) {
                    if (getChildCount() > 0) {
                        bVar.f1535e = this.mPendingScrollPosition < getPosition(getChildAt(0));
                    }
                    bVar.q();
                } else {
                    if (this.mOrientationHelper.getDecoratedMeasurement(viewFindViewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                        bVar.q();
                        return true;
                    }
                    if (this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                        bVar.f1533c = this.mOrientationHelper.getStartAfterPadding();
                        bVar.f1535e = false;
                        return true;
                    }
                    if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition) < 0) {
                        bVar.f1533c = this.mOrientationHelper.getEndAfterPadding();
                        bVar.f1535e = true;
                        return true;
                    }
                    bVar.f1533c = bVar.f1535e ? this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition) + this.mOrientationHelper.getTotalSpaceChange() : this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition);
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    public final void F(RecyclerView.State state, b bVar) {
        if (E(state, bVar, this.mPendingSavedState) || D(state, bVar)) {
            return;
        }
        bVar.q();
        bVar.f1531a = 0;
        bVar.f1532b = 0;
    }

    public final void G(int i2) {
        if (i2 >= findLastVisibleItemPosition()) {
            return;
        }
        int childCount = getChildCount();
        this.mFlexboxHelper.m(childCount);
        this.mFlexboxHelper.n(childCount);
        this.mFlexboxHelper.l(childCount);
        if (i2 >= this.mFlexboxHelper.f1580c.length) {
            return;
        }
        this.mDirtyPosition = i2;
        View childClosestToStart = getChildClosestToStart();
        if (childClosestToStart == null) {
            return;
        }
        this.mPendingScrollPosition = getPosition(childClosestToStart);
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToStart) + this.mOrientationHelper.getEndPadding();
        }
    }

    public final void H(int i2) {
        int i3;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z2 = false;
        if (isMainAxisDirectionHorizontal()) {
            int i4 = this.mLastWidth;
            if (i4 != Integer.MIN_VALUE && i4 != width) {
                z2 = true;
            }
            i3 = this.mLayoutState.f1549b ? this.mContext.getResources().getDisplayMetrics().heightPixels : this.mLayoutState.f1548a;
        } else {
            int i5 = this.mLastHeight;
            if (i5 != Integer.MIN_VALUE && i5 != height) {
                z2 = true;
            }
            i3 = this.mLayoutState.f1549b ? this.mContext.getResources().getDisplayMetrics().widthPixels : this.mLayoutState.f1548a;
        }
        int i6 = i3;
        this.mLastWidth = width;
        this.mLastHeight = height;
        int i7 = this.mDirtyPosition;
        if (i7 == -1 && (this.mPendingScrollPosition != -1 || z2)) {
            if (this.mAnchorInfo.f1535e) {
                return;
            }
            this.mFlexLines.clear();
            this.mFlexLinesResult.a();
            if (isMainAxisDirectionHorizontal()) {
                this.mFlexboxHelper.d(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i6, this.mAnchorInfo.f1531a, this.mFlexLines);
            } else {
                this.mFlexboxHelper.f(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i6, this.mAnchorInfo.f1531a, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.f1583a;
            this.mFlexboxHelper.i(iMakeMeasureSpec, iMakeMeasureSpec2);
            this.mFlexboxHelper.O();
            b bVar = this.mAnchorInfo;
            bVar.f1532b = this.mFlexboxHelper.f1580c[bVar.f1531a];
            this.mLayoutState.f1550c = this.mAnchorInfo.f1532b;
            return;
        }
        int iMin = i7 != -1 ? Math.min(i7, this.mAnchorInfo.f1531a) : this.mAnchorInfo.f1531a;
        this.mFlexLinesResult.a();
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.h(this.mFlexLines, iMin);
                this.mFlexboxHelper.b(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i6, iMin, this.mAnchorInfo.f1531a, this.mFlexLines);
            } else {
                this.mFlexboxHelper.l(i2);
                this.mFlexboxHelper.c(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i6, 0, this.mFlexLines);
            }
        } else if (this.mFlexLines.size() > 0) {
            this.mFlexboxHelper.h(this.mFlexLines, iMin);
            this.mFlexboxHelper.b(this.mFlexLinesResult, iMakeMeasureSpec2, iMakeMeasureSpec, i6, iMin, this.mAnchorInfo.f1531a, this.mFlexLines);
        } else {
            this.mFlexboxHelper.l(i2);
            this.mFlexboxHelper.e(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i6, 0, this.mFlexLines);
        }
        this.mFlexLines = this.mFlexLinesResult.f1583a;
        this.mFlexboxHelper.j(iMakeMeasureSpec, iMakeMeasureSpec2, iMin);
        this.mFlexboxHelper.P(iMin);
    }

    public final void I(int i2, int i3) {
        this.mLayoutState.f1556i = i2;
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        boolean z2 = !zIsMainAxisDirectionHorizontal && this.mIsRtl;
        if (i2 == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            this.mLayoutState.f1552e = this.mOrientationHelper.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View viewL = l(childAt, this.mFlexLines.get(this.mFlexboxHelper.f1580c[position]));
            this.mLayoutState.f1555h = 1;
            d dVar = this.mLayoutState;
            dVar.f1551d = position + dVar.f1555h;
            if (this.mFlexboxHelper.f1580c.length <= this.mLayoutState.f1551d) {
                this.mLayoutState.f1550c = -1;
            } else {
                d dVar2 = this.mLayoutState;
                dVar2.f1550c = this.mFlexboxHelper.f1580c[dVar2.f1551d];
            }
            if (z2) {
                this.mLayoutState.f1552e = this.mOrientationHelper.getDecoratedStart(viewL);
                this.mLayoutState.f1553f = (-this.mOrientationHelper.getDecoratedStart(viewL)) + this.mOrientationHelper.getStartAfterPadding();
                d dVar3 = this.mLayoutState;
                dVar3.f1553f = dVar3.f1553f >= 0 ? this.mLayoutState.f1553f : 0;
            } else {
                this.mLayoutState.f1552e = this.mOrientationHelper.getDecoratedEnd(viewL);
                this.mLayoutState.f1553f = this.mOrientationHelper.getDecoratedEnd(viewL) - this.mOrientationHelper.getEndAfterPadding();
            }
            if ((this.mLayoutState.f1550c == -1 || this.mLayoutState.f1550c > this.mFlexLines.size() - 1) && this.mLayoutState.f1551d <= getFlexItemCount()) {
                int i4 = i3 - this.mLayoutState.f1553f;
                this.mFlexLinesResult.a();
                if (i4 > 0) {
                    if (zIsMainAxisDirectionHorizontal) {
                        this.mFlexboxHelper.c(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i4, this.mLayoutState.f1551d, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.e(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i4, this.mLayoutState.f1551d, this.mFlexLines);
                    }
                    this.mFlexboxHelper.j(iMakeMeasureSpec, iMakeMeasureSpec2, this.mLayoutState.f1551d);
                    this.mFlexboxHelper.P(this.mLayoutState.f1551d);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            this.mLayoutState.f1552e = this.mOrientationHelper.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View viewJ = j(childAt2, this.mFlexLines.get(this.mFlexboxHelper.f1580c[position2]));
            this.mLayoutState.f1555h = 1;
            int i5 = this.mFlexboxHelper.f1580c[position2];
            if (i5 == -1) {
                i5 = 0;
            }
            if (i5 > 0) {
                this.mLayoutState.f1551d = position2 - this.mFlexLines.get(i5 - 1).b();
            } else {
                this.mLayoutState.f1551d = -1;
            }
            this.mLayoutState.f1550c = i5 > 0 ? i5 - 1 : 0;
            if (z2) {
                this.mLayoutState.f1552e = this.mOrientationHelper.getDecoratedEnd(viewJ);
                this.mLayoutState.f1553f = this.mOrientationHelper.getDecoratedEnd(viewJ) - this.mOrientationHelper.getEndAfterPadding();
                d dVar4 = this.mLayoutState;
                dVar4.f1553f = dVar4.f1553f >= 0 ? this.mLayoutState.f1553f : 0;
            } else {
                this.mLayoutState.f1552e = this.mOrientationHelper.getDecoratedStart(viewJ);
                this.mLayoutState.f1553f = (-this.mOrientationHelper.getDecoratedStart(viewJ)) + this.mOrientationHelper.getStartAfterPadding();
            }
        }
        d dVar5 = this.mLayoutState;
        dVar5.f1548a = i3 - dVar5.f1553f;
    }

    public final void J(b bVar, boolean z2, boolean z3) {
        if (z3) {
            B();
        } else {
            this.mLayoutState.f1549b = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.f1548a = this.mOrientationHelper.getEndAfterPadding() - bVar.f1533c;
        } else {
            this.mLayoutState.f1548a = bVar.f1533c - getPaddingRight();
        }
        this.mLayoutState.f1551d = bVar.f1531a;
        this.mLayoutState.f1555h = 1;
        this.mLayoutState.f1556i = 1;
        this.mLayoutState.f1552e = bVar.f1533c;
        this.mLayoutState.f1553f = Integer.MIN_VALUE;
        this.mLayoutState.f1550c = bVar.f1532b;
        if (!z2 || this.mFlexLines.size() <= 1 || bVar.f1532b < 0 || bVar.f1532b >= this.mFlexLines.size() - 1) {
            return;
        }
        com.google.android.flexbox.c cVar = this.mFlexLines.get(bVar.f1532b);
        d.i(this.mLayoutState);
        this.mLayoutState.f1551d += cVar.b();
    }

    public final void K(b bVar, boolean z2, boolean z3) {
        if (z3) {
            B();
        } else {
            this.mLayoutState.f1549b = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.f1548a = bVar.f1533c - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.f1548a = (this.mParent.getWidth() - bVar.f1533c) - this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.f1551d = bVar.f1531a;
        this.mLayoutState.f1555h = 1;
        this.mLayoutState.f1556i = -1;
        this.mLayoutState.f1552e = bVar.f1533c;
        this.mLayoutState.f1553f = Integer.MIN_VALUE;
        this.mLayoutState.f1550c = bVar.f1532b;
        if (!z2 || bVar.f1532b <= 0 || this.mFlexLines.size() <= bVar.f1532b) {
            return;
        }
        com.google.android.flexbox.c cVar = this.mFlexLines.get(bVar.f1532b);
        d.j(this.mLayoutState);
        this.mLayoutState.f1551d -= cVar.b();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        if (this.mFlexWrap == 0) {
            return isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            int width = getWidth();
            View view = this.mParent;
            if (width <= (view != null ? view.getWidth() : 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        if (this.mFlexWrap == 0) {
            return !isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            return true;
        }
        int height = getHeight();
        View view = this.mParent;
        return height > (view != null ? view.getHeight() : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        g();
        View viewI = i(itemCount);
        View viewK = k(itemCount);
        if (state.getItemCount() == 0 || viewI == null || viewK == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(viewK) - this.mOrientationHelper.getDecoratedStart(viewI));
    }

    public final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View viewI = i(itemCount);
        View viewK = k(itemCount);
        if (state.getItemCount() != 0 && viewI != null && viewK != null) {
            int position = getPosition(viewI);
            int position2 = getPosition(viewK);
            int iAbs = Math.abs(this.mOrientationHelper.getDecoratedEnd(viewK) - this.mOrientationHelper.getDecoratedStart(viewI));
            int i2 = this.mFlexboxHelper.f1580c[position];
            if (i2 != 0 && i2 != -1) {
                return Math.round((i2 * (iAbs / ((r4[position2] - i2) + 1))) + (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(viewI)));
            }
        }
        return 0;
    }

    public final int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View viewI = i(itemCount);
        View viewK = k(itemCount);
        if (state.getItemCount() == 0 || viewI == null || viewK == null) {
            return 0;
        }
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((Math.abs(this.mOrientationHelper.getDecoratedEnd(viewK) - this.mOrientationHelper.getDecoratedStart(viewI)) / ((findLastVisibleItemPosition() - iFindFirstVisibleItemPosition) + 1)) * state.getItemCount());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i2) {
        if (getChildCount() == 0) {
            return null;
        }
        int i3 = i2 < getPosition(getChildAt(0)) ? -1 : 1;
        return isMainAxisDirectionHorizontal() ? new PointF(0.0f, i3) : new PointF(i3, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final boolean d(View view, int i2) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEnd() - i2 : this.mOrientationHelper.getDecoratedEnd(view) <= i2;
    }

    public final boolean e(View view, int i2) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedEnd(view) <= i2 : this.mOrientationHelper.getEnd() - this.mOrientationHelper.getDecoratedStart(view) <= i2;
    }

    public final void f() {
        this.mFlexLines.clear();
        this.mAnchorInfo.s();
        this.mAnchorInfo.f1534d = 0;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View viewM = m(0, getChildCount(), true);
        if (viewM == null) {
            return -1;
        }
        return getPosition(viewM);
    }

    public int findFirstVisibleItemPosition() {
        View viewM = m(0, getChildCount(), false);
        if (viewM == null) {
            return -1;
        }
        return getPosition(viewM);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View viewM = m(getChildCount() - 1, -1, true);
        if (viewM == null) {
            return -1;
        }
        return getPosition(viewM);
    }

    public int findLastVisibleItemPosition() {
        View viewM = m(getChildCount() - 1, -1, false);
        if (viewM == null) {
            return -1;
        }
        return getPosition(viewM);
    }

    public final int fixLayoutEndGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z2) {
        int iS;
        int endAfterPadding;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i2;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            iS = -s(-endAfterPadding2, recycler, state);
        } else {
            int startAfterPadding = i2 - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            iS = s(startAfterPadding, recycler, state);
        }
        int i3 = i2 + iS;
        if (!z2 || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) <= 0) {
            return iS;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + iS;
    }

    public final int fixLayoutStartGap(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z2) {
        int iS;
        int startAfterPadding;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int startAfterPadding2 = i2 - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            iS = -s(startAfterPadding2, recycler, state);
        } else {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i2;
            if (endAfterPadding <= 0) {
                return 0;
            }
            iS = s(-endAfterPadding, recycler, state);
        }
        int i3 = i2 + iS;
        if (!z2 || (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return iS;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return iS - startAfterPadding;
    }

    public final void g() {
        if (this.mOrientationHelper != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
                return;
            } else {
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                return;
            }
        }
        if (this.mFlexWrap == 0) {
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
            this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
        } else {
            this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new c(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new c(context, attributeSet);
    }

    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.mAlignItems;
    }

    @Override // com.google.android.flexbox.a
    public int getChildHeightMeasureSpec(int i2, int i3, int i4) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), i3, i4, canScrollVertically());
    }

    @Override // com.google.android.flexbox.a
    public int getChildWidthMeasureSpec(int i2, int i3, int i4) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), i3, i4, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthMainAxis(View view, int i2, int i3) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public View getFlexItemAt(int i2) {
        View view = this.mViewCache.get(i2);
        return view != null ? view : this.mRecycler.getViewForPosition(i2);
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    public List<com.google.android.flexbox.c> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        int size = this.mFlexLines.size();
        for (int i2 = 0; i2 < size; i2++) {
            com.google.android.flexbox.c cVar = this.mFlexLines.get(i2);
            if (cVar.b() != 0) {
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public List<com.google.android.flexbox.c> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int size = this.mFlexLines.size();
        int iMax = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            iMax = Math.max(iMax, this.mFlexLines.get(i2).f1564e);
        }
        return iMax;
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.mMaxLine;
    }

    public int getPositionToFlexLineIndex(int i2) {
        return this.mFlexboxHelper.f1580c[i2];
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    @Override // com.google.android.flexbox.a
    public View getReorderedFlexItemAt(int i2) {
        return getFlexItemAt(i2);
    }

    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += this.mFlexLines.get(i3).f1566g;
        }
        return i2;
    }

    public final int h(RecyclerView.Recycler recycler, RecyclerView.State state, d dVar) {
        if (dVar.f1553f != Integer.MIN_VALUE) {
            if (dVar.f1548a < 0) {
                dVar.f1553f += dVar.f1548a;
            }
            y(recycler, dVar);
        }
        int i2 = dVar.f1548a;
        int iA = dVar.f1548a;
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int iV = 0;
        while (true) {
            if ((iA <= 0 && !this.mLayoutState.f1549b) || !dVar.w(state, this.mFlexLines)) {
                break;
            }
            com.google.android.flexbox.c cVar = this.mFlexLines.get(dVar.f1550c);
            dVar.f1551d = cVar.f1574o;
            iV += v(cVar, dVar);
            if (zIsMainAxisDirectionHorizontal || !this.mIsRtl) {
                dVar.f1552e += cVar.a() * dVar.f1556i;
            } else {
                dVar.f1552e -= cVar.a() * dVar.f1556i;
            }
            iA -= cVar.a();
        }
        dVar.f1548a -= iV;
        if (dVar.f1553f != Integer.MIN_VALUE) {
            dVar.f1553f += iV;
            if (dVar.f1548a < 0) {
                dVar.f1553f += dVar.f1548a;
            }
            y(recycler, dVar);
        }
        return i2 - dVar.f1548a;
    }

    public final View i(int i2) {
        View viewN = n(0, getChildCount(), i2);
        if (viewN == null) {
            return null;
        }
        int i3 = this.mFlexboxHelper.f1580c[getPosition(viewN)];
        if (i3 == -1) {
            return null;
        }
        return j(viewN, this.mFlexLines.get(i3));
    }

    public boolean isLayoutRtl() {
        return this.mIsRtl;
    }

    @Override // com.google.android.flexbox.a
    public boolean isMainAxisDirectionHorizontal() {
        int i2 = this.mFlexDirection;
        return i2 == 0 || i2 == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View j(android.view.View r6, com.google.android.flexbox.c r7) {
        /*
            r5 = this;
            boolean r0 = r5.isMainAxisDirectionHorizontal()
            int r7 = r7.f1567h
            r1 = 1
        L7:
            if (r1 >= r7) goto L3f
            android.view.View r2 = r5.getChildAt(r1)
            if (r2 == 0) goto L3c
            int r3 = r2.getVisibility()
            r4 = 8
            if (r3 != r4) goto L18
            goto L3c
        L18:
            boolean r3 = r5.mIsRtl
            if (r3 == 0) goto L2d
            if (r0 != 0) goto L2d
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedEnd(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedEnd(r2)
            if (r3 >= r4) goto L3c
            goto L3b
        L2d:
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedStart(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedStart(r2)
            if (r3 <= r4) goto L3c
        L3b:
            r6 = r2
        L3c:
            int r1 = r1 + 1
            goto L7
        L3f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.j(android.view.View, com.google.android.flexbox.c):android.view.View");
    }

    public final View k(int i2) {
        View viewN = n(getChildCount() - 1, -1, i2);
        if (viewN == null) {
            return null;
        }
        return l(viewN, this.mFlexLines.get(this.mFlexboxHelper.f1580c[getPosition(viewN)]));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View l(android.view.View r6, com.google.android.flexbox.c r7) {
        /*
            r5 = this;
            boolean r0 = r5.isMainAxisDirectionHorizontal()
            int r1 = r5.getChildCount()
            int r1 = r1 + (-2)
            int r2 = r5.getChildCount()
            int r7 = r7.f1567h
            int r2 = r2 - r7
            int r2 = r2 + (-1)
        L13:
            if (r1 <= r2) goto L4b
            android.view.View r7 = r5.getChildAt(r1)
            if (r7 == 0) goto L48
            int r3 = r7.getVisibility()
            r4 = 8
            if (r3 != r4) goto L24
            goto L48
        L24:
            boolean r3 = r5.mIsRtl
            if (r3 == 0) goto L39
            if (r0 != 0) goto L39
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedStart(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedStart(r7)
            if (r3 <= r4) goto L48
            goto L47
        L39:
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedEnd(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedEnd(r7)
            if (r3 >= r4) goto L48
        L47:
            r6 = r7
        L48:
            int r1 = r1 + (-1)
            goto L13
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.l(android.view.View, com.google.android.flexbox.c):android.view.View");
    }

    public final View m(int i2, int i3, boolean z2) {
        int i4 = i3 > i2 ? 1 : -1;
        while (i2 != i3) {
            View childAt = getChildAt(i2);
            if (u(childAt, z2)) {
                return childAt;
            }
            i2 += i4;
        }
        return null;
    }

    public final View n(int i2, int i3, int i4) {
        g();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i5 = i3 > i2 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View childAt = getChildAt(i2);
            int position = getPosition(childAt);
            if (position >= 0 && position < i4) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(childAt) >= startAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) <= endAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i2 += i5;
        }
        return view != null ? view : view2;
    }

    public final int o(View view) {
        return getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(@NonNull RecyclerView recyclerView, int i2, int i3) {
        super.onItemsAdded(recyclerView, i2, i3);
        G(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(@NonNull RecyclerView recyclerView, int i2, int i3, int i4) {
        super.onItemsMoved(recyclerView, i2, i3, i4);
        G(Math.min(i2, i3));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i2, int i3) {
        super.onItemsRemoved(recyclerView, i2, i3);
        G(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i2, int i3, Object obj) {
        super.onItemsUpdated(recyclerView, i2, i3, obj);
        G(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        int i3;
        this.mRecycler = recycler;
        this.mState = state;
        int itemCount = state.getItemCount();
        if (itemCount == 0 && state.isPreLayout()) {
            return;
        }
        C();
        g();
        ensureLayoutState();
        this.mFlexboxHelper.m(itemCount);
        this.mFlexboxHelper.n(itemCount);
        this.mFlexboxHelper.l(itemCount);
        this.mLayoutState.f1557j = false;
        e eVar = this.mPendingSavedState;
        if (eVar != null && eVar.w(itemCount)) {
            this.mPendingScrollPosition = this.mPendingSavedState.f1558a;
        }
        if (!this.mAnchorInfo.f1536f || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.s();
            F(state, this.mAnchorInfo);
            this.mAnchorInfo.f1536f = true;
        }
        detachAndScrapAttachedViews(recycler);
        if (this.mAnchorInfo.f1535e) {
            K(this.mAnchorInfo, false, true);
        } else {
            J(this.mAnchorInfo, false, true);
        }
        H(itemCount);
        if (this.mAnchorInfo.f1535e) {
            h(recycler, state, this.mLayoutState);
            i3 = this.mLayoutState.f1552e;
            J(this.mAnchorInfo, true, false);
            h(recycler, state, this.mLayoutState);
            i2 = this.mLayoutState.f1552e;
        } else {
            h(recycler, state, this.mLayoutState);
            i2 = this.mLayoutState.f1552e;
            K(this.mAnchorInfo, true, false);
            h(recycler, state, this.mLayoutState);
            i3 = this.mLayoutState.f1552e;
        }
        if (getChildCount() > 0) {
            if (this.mAnchorInfo.f1535e) {
                fixLayoutStartGap(i3 + fixLayoutEndGap(i2, recycler, state, true), recycler, state, false);
            } else {
                fixLayoutEndGap(i2 + fixLayoutStartGap(i3, recycler, state, true), recycler, state, false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.s();
        this.mViewCache.clear();
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexItemAdded(View view, int i2, int i3, com.google.android.flexbox.c cVar) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            cVar.f1564e += leftDecorationWidth;
            cVar.f1565f += leftDecorationWidth;
        } else {
            int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
            cVar.f1564e += topDecorationHeight;
            cVar.f1565f += topDecorationHeight;
        }
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexLineAdded(com.google.android.flexbox.c cVar) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof e) {
            this.mPendingSavedState = (e) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new e(this.mPendingSavedState);
        }
        e eVar = new e();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            eVar.f1558a = getPosition(childClosestToStart);
            eVar.f1559b = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            eVar.x();
        }
        return eVar;
    }

    public final int p(View view) {
        return getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
    }

    public final int q(View view) {
        return getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
    }

    public final int r(View view) {
        return getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
    }

    public final void recycleChildren(RecyclerView.Recycler recycler, int i2, int i3) {
        while (i3 >= i2) {
            removeAndRecycleViewAt(i3, recycler);
            i3--;
        }
    }

    public final int s(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        g();
        int i3 = 1;
        this.mLayoutState.f1557j = true;
        boolean z2 = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (!z2 ? i2 <= 0 : i2 >= 0) {
            i3 = -1;
        }
        int iAbs = Math.abs(i2);
        I(i3, iAbs);
        int iH = this.mLayoutState.f1553f + h(recycler, state, this.mLayoutState);
        if (iH < 0) {
            return 0;
        }
        if (z2) {
            if (iAbs > iH) {
                i2 = (-i3) * iH;
            }
        } else if (iAbs > iH) {
            i2 = i3 * iH;
        }
        this.mOrientationHelper.offsetChildren(-i2);
        this.mLayoutState.f1554g = i2;
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal() || (this.mFlexWrap == 0 && isMainAxisDirectionHorizontal())) {
            int iS = s(i2, recycler, state);
            this.mViewCache.clear();
            return iS;
        }
        int iT = t(i2);
        this.mAnchorInfo.f1534d += iT;
        this.mSubOrientationHelper.offsetChildren(-iT);
        return iT;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        e eVar = this.mPendingSavedState;
        if (eVar != null) {
            eVar.x();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal() || (this.mFlexWrap == 0 && !isMainAxisDirectionHorizontal())) {
            int iS = s(i2, recycler, state);
            this.mViewCache.clear();
            return iS;
        }
        int iT = t(i2);
        this.mAnchorInfo.f1534d += iT;
        this.mSubOrientationHelper.offsetChildren(-iT);
        return iT;
    }

    public void setAlignContent(int i2) {
        throw new UnsupportedOperationException("Setting the alignContent in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to use this attribute.");
    }

    public void setAlignItems(int i2) {
        int i3 = this.mAlignItems;
        if (i3 != i2) {
            if (i3 == 4 || i2 == 4) {
                removeAllViews();
                f();
            }
            this.mAlignItems = i2;
            requestLayout();
        }
    }

    public void setFlexDirection(int i2) {
        if (this.mFlexDirection != i2) {
            removeAllViews();
            this.mFlexDirection = i2;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            f();
            requestLayout();
        }
    }

    public void setFlexLines(List<com.google.android.flexbox.c> list) {
        this.mFlexLines = list;
    }

    public void setFlexWrap(int i2) {
        if (i2 == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        int i3 = this.mFlexWrap;
        if (i3 != i2) {
            if (i3 == 0 || i2 == 0) {
                removeAllViews();
                f();
            }
            this.mFlexWrap = i2;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            requestLayout();
        }
    }

    public void setJustifyContent(int i2) {
        if (this.mJustifyContent != i2) {
            this.mJustifyContent = i2;
            requestLayout();
        }
    }

    public void setMaxLine(int i2) {
        if (this.mMaxLine != i2) {
            this.mMaxLine = i2;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z2) {
        this.mRecycleChildrenOnDetach = z2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    public final int t(int i2) {
        int i3;
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        g();
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        View view = this.mParent;
        int width = zIsMainAxisDirectionHorizontal ? view.getWidth() : view.getHeight();
        int width2 = zIsMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int iAbs = Math.abs(i2);
            if (i2 < 0) {
                return -Math.min((width2 + this.mAnchorInfo.f1534d) - width, iAbs);
            }
            if (this.mAnchorInfo.f1534d + i2 > 0) {
                i3 = this.mAnchorInfo.f1534d;
                i2 = -i3;
            }
        } else {
            if (i2 > 0) {
                return Math.min((width2 - this.mAnchorInfo.f1534d) - width, i2);
            }
            if (this.mAnchorInfo.f1534d + i2 < 0) {
                i3 = this.mAnchorInfo.f1534d;
                i2 = -i3;
            }
        }
        return i2;
    }

    public final boolean u(View view, boolean z2) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int iP = p(view);
        int iR = r(view);
        int iQ = q(view);
        int iO = o(view);
        return z2 ? (paddingLeft <= iP && width >= iQ) && (paddingTop <= iR && height >= iO) : (iP >= width || iQ >= paddingLeft) && (iR >= height || iO >= paddingTop);
    }

    @Override // com.google.android.flexbox.a
    public void updateViewCache(int i2, View view) {
        this.mViewCache.put(i2, view);
    }

    public final int v(com.google.android.flexbox.c cVar, d dVar) {
        return isMainAxisDirectionHorizontal() ? w(cVar, dVar) : x(cVar, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int w(com.google.android.flexbox.c r22, com.google.android.flexbox.FlexboxLayoutManager.d r23) {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.w(com.google.android.flexbox.c, com.google.android.flexbox.FlexboxLayoutManager$d):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int x(com.google.android.flexbox.c r26, com.google.android.flexbox.FlexboxLayoutManager.d r27) {
        /*
            Method dump skipped, instruction units count: 543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.x(com.google.android.flexbox.c, com.google.android.flexbox.FlexboxLayoutManager$d):int");
    }

    public final void y(RecyclerView.Recycler recycler, d dVar) {
        if (dVar.f1557j) {
            if (dVar.f1556i == -1) {
                z(recycler, dVar);
            } else {
                A(recycler, dVar);
            }
        }
    }

    public final void z(RecyclerView.Recycler recycler, d dVar) {
        if (dVar.f1553f < 0) {
            return;
        }
        this.mOrientationHelper.getEnd();
        int unused = dVar.f1553f;
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int i2 = childCount - 1;
        int i3 = this.mFlexboxHelper.f1580c[getPosition(getChildAt(i2))];
        if (i3 == -1) {
            return;
        }
        com.google.android.flexbox.c cVar = this.mFlexLines.get(i3);
        int i4 = i2;
        while (true) {
            if (i4 < 0) {
                break;
            }
            View childAt = getChildAt(i4);
            if (!d(childAt, dVar.f1553f)) {
                break;
            }
            if (cVar.f1574o == getPosition(childAt)) {
                if (i3 <= 0) {
                    childCount = i4;
                    break;
                } else {
                    i3 += dVar.f1556i;
                    cVar = this.mFlexLines.get(i3);
                    childCount = i4;
                }
            }
            i4--;
        }
        recycleChildren(recycler, childCount, i2);
    }

    public static class e implements Parcelable {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1558a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1559b;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public e[] newArray(int i2) {
                return new e[i2];
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.f1558a + ", mAnchorOffset=" + this.f1559b + '}';
        }

        public final boolean w(int i2) {
            int i3 = this.f1558a;
            return i3 >= 0 && i3 < i2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1558a);
            parcel.writeInt(this.f1559b);
        }

        public final void x() {
            this.f1558a = -1;
        }

        public e() {
        }

        public e(Parcel parcel) {
            this.f1558a = parcel.readInt();
            this.f1559b = parcel.readInt();
        }

        public e(e eVar) {
            this.f1558a = eVar.f1558a;
            this.f1559b = eVar.f1559b;
        }
    }

    public FlexboxLayoutManager(Context context, int i2, int i3) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new com.google.android.flexbox.d(this);
        this.mAnchorInfo = new b();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new d.a();
        setFlexDirection(i2);
        setFlexWrap(i3);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i2, int i3) {
        super.onItemsUpdated(recyclerView, i2, i3);
        G(i2);
    }

    public static class c extends RecyclerView.LayoutParams implements com.google.android.flexbox.b {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1539a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1540b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1541c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1542d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1543e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1544f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1545g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1546h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1547i;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public c[] newArray(int i2) {
                return new c[i2];
            }
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1539a = 0.0f;
            this.f1540b = 1.0f;
            this.f1541c = -1;
            this.f1542d = -1.0f;
            this.f1545g = ViewCompat.MEASURED_SIZE_MASK;
            this.f1546h = ViewCompat.MEASURED_SIZE_MASK;
        }

        @Override // com.google.android.flexbox.b
        public int a() {
            return this.f1541c;
        }

        @Override // com.google.android.flexbox.b
        public float b() {
            return this.f1540b;
        }

        @Override // com.google.android.flexbox.b
        public int c() {
            return this.f1543e;
        }

        @Override // com.google.android.flexbox.b
        public int d() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.b
        public void e(int i2) {
            this.f1544f = i2;
        }

        @Override // com.google.android.flexbox.b
        public float f() {
            return this.f1539a;
        }

        @Override // com.google.android.flexbox.b
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.b
        public int getMaxHeight() {
            return this.f1546h;
        }

        @Override // com.google.android.flexbox.b
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.b
        public float h() {
            return this.f1542d;
        }

        @Override // com.google.android.flexbox.b
        public boolean i() {
            return this.f1547i;
        }

        @Override // com.google.android.flexbox.b
        public int j() {
            return this.f1545g;
        }

        @Override // com.google.android.flexbox.b
        public void k(int i2) {
            this.f1543e = i2;
        }

        @Override // com.google.android.flexbox.b
        public int l() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.b
        public int m() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.b
        public int o() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.b
        public int p() {
            return this.f1544f;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeFloat(this.f1539a);
            parcel.writeFloat(this.f1540b);
            parcel.writeInt(this.f1541c);
            parcel.writeFloat(this.f1542d);
            parcel.writeInt(this.f1543e);
            parcel.writeInt(this.f1544f);
            parcel.writeInt(this.f1545g);
            parcel.writeInt(this.f1546h);
            parcel.writeByte(this.f1547i ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public c(int i2, int i3) {
            super(i2, i3);
            this.f1539a = 0.0f;
            this.f1540b = 1.0f;
            this.f1541c = -1;
            this.f1542d = -1.0f;
            this.f1545g = ViewCompat.MEASURED_SIZE_MASK;
            this.f1546h = ViewCompat.MEASURED_SIZE_MASK;
        }

        public c(Parcel parcel) {
            super(-2, -2);
            this.f1539a = 0.0f;
            this.f1540b = 1.0f;
            this.f1541c = -1;
            this.f1542d = -1.0f;
            this.f1545g = ViewCompat.MEASURED_SIZE_MASK;
            this.f1546h = ViewCompat.MEASURED_SIZE_MASK;
            this.f1539a = parcel.readFloat();
            this.f1540b = parcel.readFloat();
            this.f1541c = parcel.readInt();
            this.f1542d = parcel.readFloat();
            this.f1543e = parcel.readInt();
            this.f1544f = parcel.readInt();
            this.f1545g = parcel.readInt();
            this.f1546h = parcel.readInt();
            this.f1547i = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new com.google.android.flexbox.d(this);
        this.mAnchorInfo = new b();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new d.a();
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i3);
        int i4 = properties.orientation;
        if (i4 != 0) {
            if (i4 == 1) {
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.reverseLayout) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        setFlexWrap(1);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }
}
