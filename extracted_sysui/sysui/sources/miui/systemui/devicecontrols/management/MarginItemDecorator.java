package miui.systemui.devicecontrols.management;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class MarginItemDecorator extends RecyclerView.ItemDecoration {
    private int dividerPosition;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        kotlin.jvm.internal.n.g(outRect, "outRect");
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(parent, "parent");
        kotlin.jvm.internal.n.g(state, "state");
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition < 0) {
            return;
        }
        int dimensionPixelOffset = view.getContext().getResources().getDimensionPixelOffset(R.dimen.device_controls_item_decoration_margin);
        RecyclerView.Adapter adapter = parent.getAdapter();
        Integer numValueOf = adapter != null ? Integer.valueOf(adapter.getItemViewType(childAdapterPosition)) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            int i2 = dimensionPixelOffset * 2;
            outRect.top = i2;
            outRect.left = dimensionPixelOffset;
            outRect.right = dimensionPixelOffset;
            outRect.bottom = i2;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 1) {
            outRect.top = dimensionPixelOffset;
            outRect.left = dimensionPixelOffset;
            outRect.right = dimensionPixelOffset;
            outRect.bottom = dimensionPixelOffset;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 2) {
            outRect.top = dimensionPixelOffset;
            outRect.left = dimensionPixelOffset;
            outRect.right = dimensionPixelOffset;
            outRect.bottom = dimensionPixelOffset;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 3) {
            outRect.top = dimensionPixelOffset;
            outRect.left = dimensionPixelOffset;
            outRect.right = dimensionPixelOffset;
            outRect.bottom = dimensionPixelOffset;
            this.dividerPosition = childAdapterPosition;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 4) {
            outRect.top = 0;
            outRect.left = dimensionPixelOffset;
            outRect.right = dimensionPixelOffset;
            outRect.bottom = dimensionPixelOffset;
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 5) {
            outRect.top = 0;
            outRect.left = dimensionPixelOffset;
            outRect.right = dimensionPixelOffset;
            outRect.bottom = dimensionPixelOffset;
        }
    }
}
