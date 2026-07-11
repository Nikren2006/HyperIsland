package miuix.popupwidget.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import java.util.List;
import miuix.androidbasewidget.widget.CheckedTextView;
import miuix.popupwidget.R;
import miuix.popupwidget.widget.DropDownPopupWindow;
import miuix.popupwidget.widget.DropDownSingleChoiceMenu;

/* JADX INFO: loaded from: classes5.dex */
public class DropDownSingleChoiceMenu implements DropDownPopupWindow.Controller {
    private View mAnchorView;
    private Context mContext;
    private List<String> mItems;
    private OnMenuListener mListener;
    private DropDownPopupWindow mPopupWindow;
    private int mSelectedItem;

    /* JADX INFO: renamed from: miuix.popupwidget.widget.DropDownSingleChoiceMenu$2, reason: invalid class name */
    public class AnonymousClass2 extends ArrayAdapter<String> {
        public AnonymousClass2(Context context, int i2, List list) {
            super(context, i2, list);
        }

        private View getViewInner(Context context, int i2, int i3, View view) {
            int dimensionPixelSize;
            int dimensionPixelSize2;
            view.getLayoutParams();
            int paddingStart = view.getPaddingStart();
            view.getPaddingTop();
            int paddingEnd = view.getPaddingEnd();
            view.getPaddingBottom();
            if (i2 == 1) {
                Resources resources = context.getResources();
                int i4 = R.dimen.miuix_appcompat_drop_down_menu_padding_small;
                dimensionPixelSize = resources.getDimensionPixelSize(i4);
                dimensionPixelSize2 = context.getResources().getDimensionPixelSize(i4);
            } else if (i3 == 0) {
                dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_menu_padding_large);
                dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_menu_padding_small);
            } else if (i3 == i2 - 1) {
                dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_menu_padding_small);
                dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_menu_padding_large);
            } else {
                Resources resources2 = context.getResources();
                int i5 = R.dimen.miuix_appcompat_drop_down_menu_padding_small;
                dimensionPixelSize = resources2.getDimensionPixelSize(i5);
                dimensionPixelSize2 = context.getResources().getDimensionPixelSize(i5);
            }
            view.setPaddingRelative(paddingStart, dimensionPixelSize, paddingEnd, dimensionPixelSize2);
            return view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$getView$0(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 9) {
                view.setHovered(true);
            } else if (motionEvent.getAction() == 10) {
                view.setHovered(false);
            }
            return false;
        }

        private void setAccessibilityDelegate(final CheckedTextView checkedTextView) {
            ViewCompat.setAccessibilityDelegate(checkedTextView, new AccessibilityDelegateCompat() { // from class: miuix.popupwidget.widget.DropDownSingleChoiceMenu.2.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setClassName(RadioButton.class.getName());
                    if (checkedTextView.isChecked()) {
                        accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                    } else {
                        accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                    }
                }
            });
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View viewInner = getViewInner(getContext(), getCount(), i2, super.getView(i2, view, viewGroup));
            viewInner.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.miuix_popup_window_list_item_fg));
            if (!viewInner.isClickable()) {
                viewInner.setOnHoverListener(new View.OnHoverListener() { // from class: miuix.popupwidget.widget.c
                    @Override // android.view.View.OnHoverListener
                    public final boolean onHover(View view2, MotionEvent motionEvent) {
                        return DropDownSingleChoiceMenu.AnonymousClass2.lambda$getView$0(view2, motionEvent);
                    }
                });
            }
            if (viewInner instanceof CheckedTextView) {
                setAccessibilityDelegate((CheckedTextView) viewInner);
            }
            return viewInner;
        }
    }

    public interface OnMenuListener {
        void onDismiss();

        void onItemSelected(DropDownSingleChoiceMenu dropDownSingleChoiceMenu, int i2);

        void onShow();
    }

    public DropDownSingleChoiceMenu(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalDismiss() {
        this.mPopupWindow = null;
    }

    private void setAccessibilityDelegate(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miuix.popupwidget.widget.DropDownSingleChoiceMenu.4
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityEvent(View view2, AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view2, accessibilityEvent);
                accessibilityEvent.setClassName(Spinner.class.getName());
            }

            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(Spinner.class.getName());
            }
        });
    }

    public void dismiss() {
        DropDownPopupWindow dropDownPopupWindow = this.mPopupWindow;
        if (dropDownPopupWindow != null) {
            dropDownPopupWindow.dismiss();
        }
    }

    public List<String> getItems() {
        return this.mItems;
    }

    public int getSelectedItem() {
        return this.mSelectedItem;
    }

    @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
    public void onAnimationUpdate(View view, float f2) {
    }

    @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
    public void onDismiss() {
        OnMenuListener onMenuListener = this.mListener;
        if (onMenuListener != null) {
            onMenuListener.onDismiss();
        }
    }

    @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
    public void onShow() {
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
        setAccessibilityDelegate(view);
    }

    public void setItems(List<String> list) {
        this.mItems = list;
    }

    public void setOnMenuListener(OnMenuListener onMenuListener) {
        this.mListener = onMenuListener;
    }

    public void setSelectedItem(int i2) {
        this.mSelectedItem = i2;
    }

    public void show() {
        if (this.mItems == null || this.mAnchorView == null) {
            return;
        }
        if (this.mPopupWindow == null) {
            DropDownPopupWindow dropDownPopupWindow = new DropDownPopupWindow(this.mContext, null, 0);
            this.mPopupWindow = dropDownPopupWindow;
            dropDownPopupWindow.setContainerController(new DropDownPopupWindow.DefaultContainerController() { // from class: miuix.popupwidget.widget.DropDownSingleChoiceMenu.1
                @Override // miuix.popupwidget.widget.DropDownPopupWindow.DefaultContainerController, miuix.popupwidget.widget.DropDownPopupWindow.Controller
                public void onDismiss() {
                    DropDownSingleChoiceMenu.this.internalDismiss();
                }

                @Override // miuix.popupwidget.widget.DropDownPopupWindow.DefaultContainerController, miuix.popupwidget.widget.DropDownPopupWindow.Controller
                public void onShow() {
                    if (DropDownSingleChoiceMenu.this.mListener != null) {
                        DropDownSingleChoiceMenu.this.mListener.onShow();
                    }
                }
            });
            this.mPopupWindow.setDropDownController(this);
            ListView listView = new DropDownPopupWindow.ListController(this.mPopupWindow).getListView();
            listView.setAdapter((ListAdapter) new AnonymousClass2(this.mContext, R.layout.miuix_appcompat_select_dropdown_popup_singlechoice, this.mItems));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.popupwidget.widget.DropDownSingleChoiceMenu.3
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                    DropDownSingleChoiceMenu.this.mSelectedItem = i2;
                    if (DropDownSingleChoiceMenu.this.mListener != null) {
                        DropDownSingleChoiceMenu.this.mListener.onItemSelected(DropDownSingleChoiceMenu.this, i2);
                    }
                    DropDownSingleChoiceMenu.this.dismiss();
                }
            });
            listView.setChoiceMode(1);
            listView.setItemChecked(this.mSelectedItem, true);
            this.mPopupWindow.setAnchor(this.mAnchorView);
        }
        this.mPopupWindow.show();
    }

    public void setItems(String[] strArr) {
        this.mItems = Arrays.asList(strArr);
    }
}
