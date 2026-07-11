package androidx.recyclerview.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleScaleItemAnimator extends SimpleItemAnimator {
    @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        if (itemHolderInfo != null) {
            int i2 = itemHolderInfo.left;
            int i3 = itemHolderInfo.top;
            int i4 = itemHolderInfo2.left;
            int i5 = itemHolderInfo2.top;
            int i6 = itemHolderInfo.right - i2;
            int i7 = itemHolderInfo.bottom - i3;
            int i8 = itemHolderInfo2.right - i4;
            int i9 = itemHolderInfo2.bottom - i5;
            if (i2 != i4 || i3 != i5 || i6 != i8 || i7 != i9) {
                return animateMove(viewHolder, i2, i3, i4, i5, i6, i8, i7, i9);
            }
        }
        return animateAdd(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2 = itemHolderInfo.left;
        int i3 = itemHolderInfo.top;
        View view = viewHolder.itemView;
        int left = itemHolderInfo2 == null ? view.getLeft() : itemHolderInfo2.left;
        int top = itemHolderInfo2 == null ? view.getTop() : itemHolderInfo2.top;
        int right = itemHolderInfo2 == null ? view.getRight() : itemHolderInfo2.right;
        int bottom = itemHolderInfo2 == null ? view.getBottom() : itemHolderInfo2.bottom;
        int width = itemHolderInfo2 == null ? view.getWidth() : itemHolderInfo.right - itemHolderInfo.left;
        int height = itemHolderInfo2 == null ? view.getHeight() : itemHolderInfo.bottom - itemHolderInfo.top;
        int width2 = itemHolderInfo2 == null ? view.getWidth() : itemHolderInfo2.right - itemHolderInfo2.left;
        int height2 = itemHolderInfo2 == null ? view.getHeight() : itemHolderInfo2.bottom - itemHolderInfo2.top;
        if (viewHolder.isRemoved() || (i2 == left && i3 == top && width == width2 && height == height2)) {
            return animateRemove(viewHolder);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return animateMove(viewHolder, i2, i3, left, top, itemHolderInfo.right - itemHolderInfo.left, right - left, itemHolderInfo.bottom - itemHolderInfo.top, bottom - top);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
        int width = viewHolder.itemView.getWidth();
        int height = viewHolder.itemView.getHeight();
        return animateMove(viewHolder, i2, i3, i4, i5, width, width, height, height);
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i2 = itemHolderInfo.left;
        int i3 = itemHolderInfo.top;
        int i4 = itemHolderInfo2.left;
        int i5 = itemHolderInfo2.top;
        int i6 = itemHolderInfo.right - i2;
        int i7 = itemHolderInfo.bottom - i3;
        int i8 = itemHolderInfo2.right - i4;
        int i9 = itemHolderInfo2.bottom - i5;
        if (i2 != i4 || i3 != i5 || i6 != i8 || i7 != i9) {
            return animateMove(viewHolder, i2, i3, i4, i5, i6, i8, i7, i9);
        }
        dispatchMoveFinished(viewHolder);
        return false;
    }
}
