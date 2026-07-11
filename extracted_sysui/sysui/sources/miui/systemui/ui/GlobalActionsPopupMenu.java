package miui.systemui.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.ListPopupWindow;
import c1.f;
import kotlin.jvm.internal.n;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public final class GlobalActionsPopupMenu extends ListPopupWindow {
    private ListAdapter adapter;
    private final Context context;
    private int globalActionsSidePadding;
    private final boolean mIsDropDownMode;
    private int menuVerticalPadding;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlobalActionsPopupMenu(Context context, boolean z2) {
        super(context, null, 0);
        n.g(context, "context");
        this.context = context;
        this.mIsDropDownMode = z2;
        this.menuVerticalPadding = context.getResources().getDimensionPixelSize(R.dimen.control_menu_vertical_padding);
        this.globalActionsSidePadding = context.getResources().getDimensionPixelSize(R.dimen.global_actions_side_margin);
        setInputMethodMode(2);
        setModal(true);
    }

    @Override // androidx.appcompat.widget.ListPopupWindow, androidx.appcompat.widget.AppCompatSpinner.SpinnerPopup
    public void setAdapter(ListAdapter listAdapter) {
        this.adapter = listAdapter;
        super.setAdapter(listAdapter);
    }

    public final void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override // androidx.appcompat.widget.ListPopupWindow, androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        View anchorView;
        super.show();
        ListView listView = getListView();
        if (listView == null || (anchorView = getAnchorView()) == null) {
            return;
        }
        final float dimension = this.context.getResources().getDimension(R.dimen.list_popup_background_corner_radius);
        listView.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.ui.GlobalActionsPopupMenu.show.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                n.g(view, "view");
                n.g(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), dimension);
            }
        });
        listView.setClipToOutline(true);
        AdapterView.OnItemLongClickListener onItemLongClickListener = this.onItemLongClickListener;
        if (onItemLongClickListener != null) {
            listView.setOnItemLongClickListener(onItemLongClickListener);
        }
        setVerticalOffset(anchorView.getHeight() / 2);
        setHorizontalOffset((anchorView.getWidth() - getWidth()) / 2);
        if (!this.mIsDropDownMode) {
            if (this.adapter == null) {
                return;
            }
            double d2 = Resources.getSystem().getDisplayMetrics().widthPixels;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (0.9d * d2), Integer.MIN_VALUE);
            ListAdapter listAdapter = this.adapter;
            n.d(listAdapter);
            int count = listAdapter.getCount();
            int iC = 0;
            for (int i2 = 0; i2 < count; i2++) {
                ListAdapter listAdapter2 = this.adapter;
                n.d(listAdapter2);
                View view = listAdapter2.getView(i2, null, listView);
                view.measure(iMakeMeasureSpec, 0);
                iC = f.c(view.getMeasuredWidth(), iC);
            }
            int iC2 = f.c(iC, (int) (d2 * 0.5d));
            int i3 = this.menuVerticalPadding;
            listView.setPadding(0, i3, 0, i3);
            setWidth(iC2);
            if (anchorView.getLayoutDirection() == 0) {
                iC2 = (anchorView.getWidth() - this.globalActionsSidePadding) - iC2;
            }
            setHorizontalOffset(iC2);
        }
        super.show();
    }
}
