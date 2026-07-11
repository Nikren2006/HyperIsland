package miuix.appcompat.internal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import miuix.androidbasewidget.widget.CheckedTextView;
import miuix.appcompat.R;
import miuix.appcompat.adapter.SpinnerDoubleLineContentAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class SpinnerCheckableArrayAdapter extends ArrayAdapter {
    public static final int TAG_VIEW = R.id.tag_spinner_dropdown_view;
    private CheckedStateProvider mCheckProvider;
    private ArrayAdapter mContentAdapter;
    private LayoutInflater mInflater;

    public interface AccessibilityBehavior {
        boolean isExtraContentDescriptionEnabled();
    }

    public interface CheckedStateProvider {
        boolean isChecked(int i2);
    }

    public static class ViewHolder {
        FrameLayout container;
        RadioButton radioButton;

        private ViewHolder() {
        }
    }

    public SpinnerCheckableArrayAdapter(@NonNull Context context, int i2, ArrayAdapter arrayAdapter, CheckedStateProvider checkedStateProvider) {
        super(context, i2, android.R.id.text1);
        this.mInflater = LayoutInflater.from(context);
        this.mContentAdapter = arrayAdapter;
        this.mCheckProvider = checkedStateProvider;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.mContentAdapter.getCount();
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
        boolean z2 = false;
        if (view == null || view.getTag(TAG_VIEW) == null) {
            view = this.mInflater.inflate(R.layout.miuix_appcompat_spinner_dropdown_checkable_item, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.container = (FrameLayout) view.findViewById(R.id.spinner_dropdown_container);
            viewHolder.radioButton = (RadioButton) view.findViewById(android.R.id.checkbox);
            view.setTag(TAG_VIEW, viewHolder);
        }
        Object tag = view.getTag(TAG_VIEW);
        if (tag != null) {
            ViewHolder viewHolder2 = (ViewHolder) tag;
            View dropDownView = this.mContentAdapter.getDropDownView(i2, viewHolder2.container.getChildAt(0), viewGroup);
            viewHolder2.container.removeAllViews();
            viewHolder2.container.addView(dropDownView);
            CheckedStateProvider checkedStateProvider = this.mCheckProvider;
            if (checkedStateProvider != null && checkedStateProvider.isChecked(i2)) {
                z2 = true;
            }
            viewHolder2.radioButton.setChecked(z2);
            view.setActivated(z2);
        }
        return view;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @Nullable
    public Object getItem(int i2) {
        return this.mContentAdapter.getItem(i2);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i2) {
        return this.mContentAdapter.getItemId(i2);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return this.mContentAdapter.hasStableIds();
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mContentAdapter.notifyDataSetChanged();
    }

    public void setAccessibilityDelegate(View view, final int i2) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miuix.appcompat.internal.adapter.SpinnerCheckableArrayAdapter.1
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(@NonNull View view2, @NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                CheckedTextView checkedTextView = (CheckedTextView) view2.findViewById(android.R.id.title);
                CheckedTextView checkedTextView2 = (CheckedTextView) view2.findViewById(android.R.id.summary);
                AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) view2.findViewById(android.R.id.checkbox);
                accessibilityNodeInfo.setClassName(RadioButton.class.getName());
                accessibilityNodeInfo.setClickable(true);
                accessibilityNodeInfo.setCheckable(true);
                if (appCompatRadioButton == null || !appCompatRadioButton.isActivated()) {
                    accessibilityNodeInfo.setChecked(false);
                } else {
                    accessibilityNodeInfo.setClickable(false);
                    accessibilityNodeInfo.setChecked(true);
                    accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
                }
                String string = checkedTextView != null ? checkedTextView.getText().toString() : null;
                String string2 = checkedTextView2 != null ? checkedTextView2.getText().toString() : null;
                if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2)) {
                    accessibilityNodeInfo.setContentDescription(string + " " + string2);
                }
                if ((SpinnerCheckableArrayAdapter.this.mContentAdapter instanceof SpinnerDoubleLineContentAdapter) && ((SpinnerDoubleLineContentAdapter) SpinnerCheckableArrayAdapter.this.mContentAdapter).isIconOnlyEnabled()) {
                    accessibilityNodeInfo.setContentDescription(SpinnerCheckableArrayAdapter.this.mContentAdapter.getItem(i2).toString());
                }
                if ((SpinnerCheckableArrayAdapter.this.mContentAdapter instanceof AccessibilityBehavior) && ((AccessibilityBehavior) SpinnerCheckableArrayAdapter.this.mContentAdapter).isExtraContentDescriptionEnabled()) {
                    accessibilityNodeInfo.setContentDescription(SpinnerCheckableArrayAdapter.this.mContentAdapter.getItem(i2).toString());
                }
            }
        });
    }

    public SpinnerCheckableArrayAdapter(@NonNull Context context, ArrayAdapter arrayAdapter, CheckedStateProvider checkedStateProvider) {
        this(context, R.layout.miuix_appcompat_simple_spinner_layout_integrated, arrayAdapter, checkedStateProvider);
    }
}
