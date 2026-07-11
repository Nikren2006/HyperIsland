package miuix.popupwidget.widget;

import android.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.WindowInsetsCompat;
import miuix.core.util.WindowUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ListPopupWindow {
    private static final boolean DEBUG = false;
    private static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    private DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private boolean mForceIgnoreOutsideTouch;
    private Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private ArrowPopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    private final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    public static class DropDownListView extends ListView {
        public static final int INVALID_POSITION = -1;
        static final int NO_POSITION = -1;
        private boolean mHijackFocus;
        private boolean mListSelectionHidden;

        public DropDownListView(Context context, boolean z2) {
            super(context, null, R.attr.dropDownListViewStyle);
            this.mHijackFocus = z2;
            setCacheColorHint(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int lookForSelectablePosition(int i2, boolean z2) {
            int iMin;
            ListAdapter adapter = getAdapter();
            if (adapter != null && !isInTouchMode()) {
                int count = adapter.getCount();
                if (!getAdapter().areAllItemsEnabled()) {
                    if (z2) {
                        iMin = Math.max(0, i2);
                        while (iMin < count && !adapter.isEnabled(iMin)) {
                            iMin++;
                        }
                    } else {
                        iMin = Math.min(i2, count - 1);
                        while (iMin >= 0 && !adapter.isEnabled(iMin)) {
                            iMin--;
                        }
                    }
                    if (iMin < 0 || iMin >= count) {
                        return -1;
                    }
                    return iMin;
                }
                if (i2 >= 0 && i2 < count) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean hasFocus() {
            return this.mHijackFocus || super.hasFocus();
        }

        @Override // android.view.View
        public boolean hasWindowFocus() {
            return this.mHijackFocus || super.hasWindowFocus();
        }

        @Override // android.view.View
        public boolean isFocused() {
            return this.mHijackFocus || super.isFocused();
        }

        @Override // android.view.View
        public boolean isInTouchMode() {
            return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
        }

        public final int measureHeightOfChildrenCompact(int i2, int i3, int i4, int i5, int i6) {
            int listPaddingTop = getListPaddingTop();
            int listPaddingBottom = getListPaddingBottom();
            int dividerHeight = getDividerHeight();
            Drawable divider = getDivider();
            ListAdapter adapter = getAdapter();
            if (adapter == null) {
                return listPaddingTop + listPaddingBottom;
            }
            int measuredHeight = listPaddingTop + listPaddingBottom;
            if (dividerHeight <= 0 || divider == null) {
                dividerHeight = 0;
            }
            int count = adapter.getCount();
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            View view = null;
            while (i7 < count) {
                int itemViewType = adapter.getItemViewType(i7);
                if (itemViewType != i8) {
                    view = null;
                    i8 = itemViewType;
                }
                view = adapter.getView(i7, view, this);
                int i10 = view.getLayoutParams().height;
                view.measure(i2, i10 > 0 ? View.MeasureSpec.makeMeasureSpec(i10, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0));
                if (i7 > 0) {
                    measuredHeight += dividerHeight;
                }
                measuredHeight += view.getMeasuredHeight();
                if (measuredHeight >= i5) {
                    return (i6 < 0 || i7 <= i6 || i9 <= 0 || measuredHeight == i5) ? i5 : i9;
                }
                if (i6 >= 0 && i7 >= i6) {
                    i9 = measuredHeight;
                }
                i7++;
            }
            return measuredHeight;
        }
    }

    public class ListSelectorHider implements Runnable {
        private ListSelectorHider() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    public class PopupDataSetObserver extends DataSetObserver {
        private PopupDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss(true);
        }
    }

    public class PopupScrollListener implements AbsListView.OnScrollListener {
        private PopupScrollListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || ListPopupWindow.this.isInputMethodNotNeeded() || ListPopupWindow.this.mPopup.getContentView() == null) {
                return;
            }
            ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
            ListPopupWindow.this.mResizePopupRunnable.run();
        }
    }

    public class PopupTouchInterceptor implements View.OnTouchListener {
        private PopupTouchInterceptor() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x2 = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && x2 >= 0 && x2 < ListPopupWindow.this.mPopup.getContentWidth() && y2 >= 0 && y2 < ListPopupWindow.this.mPopup.getContentHeight()) {
                ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
            return false;
        }
    }

    public class ResizePopupRunnable implements Runnable {
        private ResizePopupRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ListPopupWindow.this.mDropDownList == null || ListPopupWindow.this.mDropDownList.getCount() <= ListPopupWindow.this.mDropDownList.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.mDropDownList.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.mListItemExpandMaximum) {
                listPopupWindow.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private int buildDropDown() {
        int measuredHeight;
        int iMakeMeasureSpec;
        View view;
        int i2 = 0;
        if (this.mDropDownList == null) {
            Context context = this.mContext;
            this.mShowDropDownRunnable = new Runnable() { // from class: miuix.popupwidget.widget.ListPopupWindow.1
                @Override // java.lang.Runnable
                public void run() {
                    View anchorView = ListPopupWindow.this.getAnchorView();
                    if (anchorView == null || anchorView.getWindowToken() == null) {
                        return;
                    }
                    ListPopupWindow.this.show();
                }
            };
            DropDownListView dropDownListView = new DropDownListView(context, !this.mModal);
            this.mDropDownList = dropDownListView;
            Drawable drawable = this.mDropDownListHighlight;
            if (drawable != null) {
                dropDownListView.setSelector(drawable);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: miuix.popupwidget.widget.ListPopupWindow.2
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view2, int i3, long j2) {
                    DropDownListView dropDownListView2;
                    if (i3 == -1 || (dropDownListView2 = ListPopupWindow.this.mDropDownList) == null) {
                        return;
                    }
                    dropDownListView2.mListSelectionHidden = false;
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mItemSelectedListener;
            if (onItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(onItemSelectedListener);
            }
            DropDownListView dropDownListView2 = this.mDropDownList;
            View view2 = this.mPromptView;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i3 = this.mPromptPosition;
                if (i3 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(dropDownListView2, layoutParams);
                } else if (i3 != 1) {
                    Log.e(TAG, "Invalid hint position " + this.mPromptPosition);
                } else {
                    linearLayout.addView(dropDownListView2, layoutParams);
                    linearLayout.addView(view2);
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(this.mDropDownWidth, Integer.MIN_VALUE), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = dropDownListView2;
            }
            this.mPopup.setContentView(view);
        } else {
            View view3 = this.mPromptView;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            int i4 = rect.top;
            i2 = rect.bottom + i4;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -i4;
            }
        } else {
            this.mTempRect.setEmpty();
        }
        int maxAvailableHeight = getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset);
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1) {
            return maxAvailableHeight + i2;
        }
        int i5 = this.mDropDownWidth;
        if (i5 == -2) {
            int i6 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.mTempRect;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i5 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, BasicMeasure.EXACTLY);
        } else {
            int i7 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.mTempRect;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect3.left + rect3.right), BasicMeasure.EXACTLY);
        }
        int iMeasureHeightOfChildrenCompact = this.mDropDownList.measureHeightOfChildrenCompact(iMakeMeasureSpec, 0, -1, maxAvailableHeight - measuredHeight, -1);
        if (iMeasureHeightOfChildrenCompact > 0) {
            measuredHeight += i2;
        }
        return iMeasureHeightOfChildrenCompact + measuredHeight;
    }

    private void removePromptView() {
        View view = this.mPromptView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    public void clearListSelection() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.mListSelectionHidden = true;
            dropDownListView.requestLayout();
        }
    }

    public void dismiss() {
        dismiss(true);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    public ListView getListView() {
        return this.mDropDownList;
    }

    public int getMaxAvailableHeight(View view, int i2) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int i3 = WindowUtils.getWindowSize(this.mContext).y;
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        int maxAvailableHeight = this.mPopup.getMaxAvailableHeight(((i3 - (rootWindowInsets != null ? WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets).getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()).bottom : 0)) - (iArr[1] + view.getHeight())) - i2, (iArr[1] - rect.top) + i2);
        if (this.mPopup.getBackground() == null) {
            return maxAvailableHeight;
        }
        this.mPopup.getBackground().getPadding(this.mTempRect);
        Rect rect2 = this.mTempRect;
        return maxAvailableHeight - (rect2.top + rect2.bottom);
    }

    public ArrowPopupWindow getPopupWindow() {
        return this.mPopup;
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public Object getSelectedItem() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItem();
        }
        return null;
    }

    public long getSelectedItemId() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int getSelectedItemPosition() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedItemPosition();
        }
        return -1;
    }

    public View getSelectedView() {
        if (isShowing()) {
            return this.mDropDownList.getSelectedView();
        }
        return null;
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if (this.mDropDownVerticalOffsetSet) {
            return this.mDropDownVerticalOffset;
        }
        return 0;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        int iLookForSelectablePosition;
        int count;
        if (isShowing() && i2 != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || (i2 != 66 && i2 != 23))) {
            int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
            boolean zIsAboveAnchor = this.mPopup.isAboveAnchor();
            ListAdapter listAdapter = this.mAdapter;
            if (listAdapter != null) {
                boolean zAreAllItemsEnabled = listAdapter.areAllItemsEnabled();
                iLookForSelectablePosition = zAreAllItemsEnabled ? 0 : this.mDropDownList.lookForSelectablePosition(0, true);
                count = zAreAllItemsEnabled ? listAdapter.getCount() - 1 : this.mDropDownList.lookForSelectablePosition(listAdapter.getCount() - 1, false);
            } else {
                iLookForSelectablePosition = Integer.MAX_VALUE;
                count = Integer.MIN_VALUE;
            }
            if ((!zIsAboveAnchor && i2 == 19 && selectedItemPosition <= iLookForSelectablePosition) || (zIsAboveAnchor && i2 == 20 && selectedItemPosition >= count)) {
                clearListSelection();
                this.mPopup.setInputMethodMode(1);
                show();
                return true;
            }
            this.mDropDownList.mListSelectionHidden = false;
            if (this.mDropDownList.onKeyDown(i2, keyEvent)) {
                this.mPopup.setInputMethodMode(2);
                this.mDropDownList.requestFocusFromTouch();
                show();
                if (i2 == 19 || i2 == 20 || i2 == 23 || i2 == 66) {
                    return true;
                }
            } else if (zIsAboveAnchor || i2 != 20) {
                if (zIsAboveAnchor && i2 == 19 && selectedItemPosition == iLookForSelectablePosition) {
                    return true;
                }
            } else if (selectedItemPosition == count) {
                return true;
            }
        }
        return false;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (!isShowing() || this.mDropDownList.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean zOnKeyUp = this.mDropDownList.onKeyUp(i2, keyEvent);
        if (zOnKeyUp && (i2 == 23 || i2 == 66)) {
            dismiss(true);
        }
        return zOnKeyUp;
    }

    public boolean performItemClick(int i2) {
        if (!isShowing()) {
            return false;
        }
        if (this.mItemClickListener == null) {
            return true;
        }
        DropDownListView dropDownListView = this.mDropDownList;
        this.mItemClickListener.onItemClick(dropDownListView, dropDownListView.getChildAt(i2 - dropDownListView.getFirstVisiblePosition()), i2, dropDownListView.getAdapter().getItemId(i2));
        return true;
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(View view) {
        this.mDropDownAnchorView = view;
    }

    public void setAnimationStyle(int i2) {
        this.mPopup.setAnimationStyle(i2);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i2) {
        Drawable background = this.mPopup.getBackground();
        if (background == null) {
            setWidth(i2);
            return;
        }
        background.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        this.mDropDownWidth = rect.left + rect.right + i2;
    }

    public void setDropDownAlwaysVisible(boolean z2) {
        this.mDropDownAlwaysVisible = z2;
    }

    public void setForceIgnoreOutsideTouch(boolean z2) {
        this.mForceIgnoreOutsideTouch = z2;
    }

    public void setHeight(int i2) {
        this.mDropDownHeight = i2;
    }

    public void setHorizontalOffset(int i2) {
        this.mDropDownHorizontalOffset = i2;
    }

    public void setInputMethodMode(int i2) {
        this.mPopup.setInputMethodMode(i2);
    }

    public void setListItemExpandMax(int i2) {
        this.mListItemExpandMaximum = i2;
    }

    public void setListSelector(Drawable drawable) {
        this.mDropDownListHighlight = drawable;
    }

    public void setModal(boolean z2) {
        this.mModal = true;
        this.mPopup.setFocusable(z2);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setPromptPosition(int i2) {
        this.mPromptPosition = i2;
    }

    public void setPromptView(View view) {
        boolean zIsShowing = isShowing();
        if (zIsShowing) {
            removePromptView();
        }
        this.mPromptView = view;
        if (zIsShowing) {
            show();
        }
    }

    public void setSelection(int i2) {
        DropDownListView dropDownListView = this.mDropDownList;
        if (!isShowing() || dropDownListView == null) {
            return;
        }
        dropDownListView.mListSelectionHidden = false;
        dropDownListView.setSelection(i2);
        if (dropDownListView.getChoiceMode() != 0) {
            dropDownListView.setItemChecked(i2, true);
        }
    }

    public void setSoftInputMode(int i2) {
        this.mPopup.setSoftInputMode(i2);
    }

    public void setVerticalOffset(int i2) {
        this.mDropDownVerticalOffset = i2;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i2) {
        this.mDropDownWidth = i2;
    }

    public void show() {
        int iBuildDropDown = buildDropDown();
        int i2 = this.mDropDownWidth;
        if (i2 != -1) {
            if (i2 == -2) {
                this.mPopup.setContentWidth(getAnchorView().getWidth());
            } else {
                this.mPopup.setContentWidth(i2);
            }
        }
        int i3 = this.mDropDownHeight;
        if (i3 != -1) {
            if (i3 == -2) {
                this.mPopup.setContentHeight(iBuildDropDown);
            } else {
                this.mPopup.setContentHeight(i3);
            }
        }
        this.mPopup.setFocusable(true);
        if (this.mPopup.isShowing()) {
            this.mPopup.setOutsideTouchable((this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) ? false : true);
            this.mPopup.update(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownWidth, iBuildDropDown);
            return;
        }
        this.mPopup.setWindowLayoutMode(-1, -1);
        this.mPopup.setOutsideTouchable((this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) ? false : true);
        this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
        this.mPopup.show(getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset);
        this.mDropDownList.setSelection(-1);
        if (!this.mModal || this.mDropDownList.isInTouchMode()) {
            clearListSelection();
        }
        if (this.mModal) {
            return;
        }
        this.mHandler.post(this.mHideSelector);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public void dismiss(boolean z2) {
        this.mPopup.dismiss(z2);
        removePromptView();
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mPromptPosition = 0;
        this.mHandler = new Handler();
        this.mTempRect = new Rect();
        this.mContext = context;
        this.mPopup = new ArrowPopupWindow(context, attributeSet, i2);
    }
}
