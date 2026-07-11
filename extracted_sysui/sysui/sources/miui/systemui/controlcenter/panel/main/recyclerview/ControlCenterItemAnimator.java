package miui.systemui.controlcenter.panel.main.recyclerview;

import android.util.ArraySet;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterItemAnimator extends SimpleItemAnimator {
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG = false;
    private static final String TAG = "ControlCenterItemAnimator";
    private static final int TYPE_FOOTER_SPACE = 366837;
    private float editModeNotAddTilesTransY;
    private final ArraySet<ControlCenterViewHolder> pendingAnimate = new ArraySet<>();
    private final ArraySet<ControlCenterViewHolder> animating = new ArraySet<>();
    private boolean suppressAnimation = true;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void dispatchFinishedWhenDone() {
        if (isRunning()) {
            return;
        }
        dispatchAnimationsFinished();
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        n.g(holder, "holder");
        if (!(holder instanceof ControlCenterViewHolder) || this.suppressAnimation) {
            dispatchAddFinished(holder);
            return false;
        }
        ((ControlCenterViewHolder) holder).prepareAdd(this, this.editModeNotAddTilesTransY);
        this.pendingAnimate.add(holder);
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateAppearance(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo postLayoutInfo) {
        n.g(viewHolder, "viewHolder");
        n.g(postLayoutInfo, "postLayoutInfo");
        return itemHolderInfo != null ? animateMove(viewHolder, itemHolderInfo.left, itemHolderInfo.top, postLayoutInfo.left, postLayoutInfo.top) : animateAdd(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int i2, int i3, int i4, int i5) {
        n.g(oldHolder, "oldHolder");
        n.g(newHolder, "newHolder");
        if (!(oldHolder instanceof ControlCenterViewHolder) || !(newHolder instanceof ControlCenterViewHolder) || this.suppressAnimation) {
            dispatchChangeFinished(oldHolder, true);
            dispatchChangeFinished(newHolder, false);
            return false;
        }
        if (oldHolder == newHolder) {
            return animateMove(newHolder, i2, i3, i4, i5);
        }
        ((ControlCenterViewHolder) oldHolder).prepareRemoveByChange(this, i2, i3, i4, i5);
        ((ControlCenterViewHolder) newHolder).prepareAddByChange(this, i2, i3, i4, i5);
        this.pendingAnimate.add(oldHolder);
        this.pendingAnimate.add(newHolder);
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateDisappearance(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo preLayoutInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        n.g(viewHolder, "viewHolder");
        n.g(preLayoutInfo, "preLayoutInfo");
        return itemHolderInfo != null ? animateMove(viewHolder, preLayoutInfo.left, preLayoutInfo.top, itemHolderInfo.left, itemHolderInfo.top) : animateRemove(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder holder, int i2, int i3, int i4, int i5) {
        n.g(holder, "holder");
        if (!(holder instanceof ControlCenterViewHolder) || this.suppressAnimation) {
            dispatchMoveFinished(holder);
            return false;
        }
        float fAbs = Math.abs(i3 - i5);
        if (fAbs > this.editModeNotAddTilesTransY && ((ControlCenterViewHolder) holder).getItemViewType() != TYPE_FOOTER_SPACE) {
            this.editModeNotAddTilesTransY = fAbs;
        }
        Log.i(TAG, "animateMove: " + fAbs + " " + this.editModeNotAddTilesTransY);
        ((ControlCenterViewHolder) holder).prepareMove(this, i2, i3, i4, i5);
        this.pendingAnimate.add(holder);
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        n.g(holder, "holder");
        if (!(holder instanceof ControlCenterViewHolder) || this.suppressAnimation) {
            dispatchRemoveFinished(holder);
            return false;
        }
        ((ControlCenterViewHolder) holder).prepareRemove(this, this.editModeNotAddTilesTransY);
        this.pendingAnimate.add(holder);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List<? extends Object> payloads) {
        n.g(viewHolder, "viewHolder");
        n.g(payloads, "payloads");
        return !payloads.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, payloads);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(RecyclerView.ViewHolder item) {
        n.g(item, "item");
        ControlCenterViewHolder controlCenterViewHolder = item instanceof ControlCenterViewHolder ? (ControlCenterViewHolder) item : null;
        if (controlCenterViewHolder != null) {
            controlCenterViewHolder.endAnimation();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
        ArrayList arrayList = new ArrayList(this.pendingAnimate);
        ArrayList arrayList2 = new ArrayList(this.animating);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ControlCenterViewHolder) it.next()).endAnimation();
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((ControlCenterViewHolder) it2.next()).endAnimation();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public long getAddDuration() {
        return 300L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public long getChangeDuration() {
        return 300L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public long getMoveDuration() {
        return 300L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public long getRemoveDuration() {
        return 300L;
    }

    public final boolean getSuppressAnimation() {
        return this.suppressAnimation;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean isRunning() {
        return (this.pendingAnimate.isEmpty() && this.animating.isEmpty()) ? false : true;
    }

    public final void performFinished(ControlCenterViewHolder viewHolder, boolean z2) {
        n.g(viewHolder, "viewHolder");
        if (z2) {
            this.pendingAnimate.remove(viewHolder);
            this.animating.remove(viewHolder);
        }
        dispatchAnimationFinished(viewHolder);
        dispatchFinishedWhenDone();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public RecyclerView.ItemAnimator.ItemHolderInfo recordPreLayoutInformation(RecyclerView.State state, RecyclerView.ViewHolder viewHolder, int i2, List<Object> payloads) {
        n.g(state, "state");
        n.g(viewHolder, "viewHolder");
        n.g(payloads, "payloads");
        viewHolder.getAbsoluteAdapterPosition();
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfoRecordPreLayoutInformation = super.recordPreLayoutInformation(state, viewHolder, i2, payloads);
        n.f(itemHolderInfoRecordPreLayoutInformation, "recordPreLayoutInformation(...)");
        return itemHolderInfoRecordPreLayoutInformation;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
        if (this.suppressAnimation) {
            endAnimations();
            return;
        }
        Iterator<ControlCenterViewHolder> it = this.pendingAnimate.iterator();
        n.f(it, "iterator(...)");
        while (it.hasNext()) {
            ControlCenterViewHolder next = it.next();
            next.performAnimation();
            this.animating.add(next);
            it.remove();
        }
        this.editModeNotAddTilesTransY = 0.0f;
    }

    public final void setSuppressAnimation(boolean z2) {
        Log.i(TAG, "suppress " + this.suppressAnimation + " to " + z2);
        if (this.suppressAnimation == z2) {
            return;
        }
        this.suppressAnimation = z2;
        if (z2) {
            endAnimations();
        }
    }
}
