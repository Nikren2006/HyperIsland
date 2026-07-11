package com.android.systemui.miui;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ViewStateGroup {
    private SparseArray<ViewState> mStates;

    public static class ViewState {
        public static final int GRAVITY = 12;
        public static final int LAYOUT_GRAVITY = 1;
        public static final int LAYOUT_HEIGHT = 3;
        public static final int LAYOUT_MARGIN_BOTTOM = 8;
        public static final int LAYOUT_MARGIN_LEFT = 5;
        public static final int LAYOUT_MARGIN_RIGHT = 7;
        public static final int LAYOUT_MARGIN_TOP = 6;
        public static final int LAYOUT_WEIGHT = 4;
        public static final int LAYOUT_WIDTH = 2;
        public static final int ORIENTATION = 11;
        public static final int PADDING = 9;
        public static final int VISIBILITY = 10;
        private int mViewId;
        private SparseIntArray mIntStates = new SparseIntArray();
        private SparseArray<Float> mFloatStates = new SparseArray<>();

        public ViewState(int i2) {
            this.mViewId = i2;
        }

        private static void applyFloatProperty(View view, int i2, float f2) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (i2 == 4 && (layoutParams instanceof LinearLayout.LayoutParams)) {
                ((LinearLayout.LayoutParams) layoutParams).weight = f2;
            }
        }

        private static void applyIntProperty(View view, int i2, int i3) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            switch (i2) {
                case 1:
                    setLayoutGravity(layoutParams, i3);
                    break;
                case 2:
                    layoutParams.width = i3;
                    break;
                case 3:
                    layoutParams.height = i3;
                    break;
                case 5:
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = i3;
                    }
                    break;
                case 6:
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i3;
                    }
                    break;
                case 7:
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = i3;
                    }
                    break;
                case 8:
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = i3;
                    }
                    break;
                case 9:
                    view.setPadding(i3, i3, i3, i3);
                    break;
                case 10:
                    view.setVisibility(i3);
                    break;
                case 11:
                    if (view instanceof LinearLayout) {
                        ((LinearLayout) view).setOrientation(i3);
                    }
                    break;
                case 12:
                    if (view instanceof LinearLayout) {
                        ((LinearLayout) view).setGravity(i3);
                    }
                    break;
            }
        }

        private static void setLayoutGravity(ViewGroup.LayoutParams layoutParams, int i2) {
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) layoutParams).gravity = i2;
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).gravity = i2;
            }
        }

        public void apply(View view) {
            if (view == null || this.mViewId != view.getId()) {
                return;
            }
            for (int i2 = 0; i2 < this.mIntStates.size(); i2++) {
                int iKeyAt = this.mIntStates.keyAt(i2);
                applyIntProperty(view, iKeyAt, this.mIntStates.get(iKeyAt));
            }
            for (int i3 = 0; i3 < this.mFloatStates.size(); i3++) {
                int iKeyAt2 = this.mFloatStates.keyAt(i3);
                applyFloatProperty(view, iKeyAt2, this.mFloatStates.get(iKeyAt2).floatValue());
            }
        }
    }

    public void apply(ViewGroup viewGroup) {
        for (int i2 = 0; i2 < this.mStates.size(); i2++) {
            SparseArray<ViewState> sparseArray = this.mStates;
            ViewState viewState = sparseArray.get(sparseArray.keyAt(i2), null);
            if (viewState != null) {
                viewState.apply(viewGroup.findViewById(viewState.mViewId));
            }
        }
    }

    private ViewStateGroup() {
        this.mStates = new SparseArray<>();
    }

    public static class Builder {
        private Context mContext;
        ViewStateGroup mResult = new ViewStateGroup();

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder addState(int i2, int i3, int i4) {
            ViewState viewState = (ViewState) this.mResult.mStates.get(i2);
            if (viewState == null) {
                viewState = new ViewState(i2);
                this.mResult.mStates.put(i2, viewState);
            }
            viewState.mIntStates.put(i3, i4);
            return this;
        }

        public Builder addStateWithFloatDimen(int i2, int i3, int i4) {
            return addState(i2, i3, this.mContext.getResources().getDimension(i4));
        }

        public Builder addStateWithIntDimen(int i2, int i3, int i4) {
            return addState(i2, i3, this.mContext.getResources().getDimensionPixelSize(i4));
        }

        public Builder addStateWithIntRes(int i2, int i3, int i4) {
            return addState(i2, i3, this.mContext.getResources().getInteger(i4));
        }

        public ViewStateGroup build() {
            return this.mResult;
        }

        public Builder addState(int i2, int i3, float f2) {
            ViewState viewState = (ViewState) this.mResult.mStates.get(i2);
            if (viewState == null) {
                viewState = new ViewState(i2);
                this.mResult.mStates.put(i2, viewState);
            }
            viewState.mFloatStates.put(i3, Float.valueOf(f2));
            return this;
        }
    }
}
