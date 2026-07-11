package androidx.viewpager2.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.viewpager2.widget.OriginalViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class OriginalCompositeOnPageChangeCallback extends OriginalViewPager2.OnPageChangeCallback {

    @NonNull
    private final List<OriginalViewPager2.OnPageChangeCallback> mCallbacks;

    public OriginalCompositeOnPageChangeCallback(int i2) {
        this.mCallbacks = new ArrayList(i2);
    }

    private void throwCallbackListModifiedWhileInUse(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    public void addOnPageChangeCallback(OriginalViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallbacks.add(onPageChangeCallback);
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i2) {
        try {
            Iterator<OriginalViewPager2.OnPageChangeCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPageScrollStateChanged(i2);
            }
        } catch (ConcurrentModificationException e2) {
            throwCallbackListModifiedWhileInUse(e2);
        }
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i2, float f2, @Px int i3) {
        try {
            Iterator<OriginalViewPager2.OnPageChangeCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPageScrolled(i2, f2, i3);
            }
        } catch (ConcurrentModificationException e2) {
            throwCallbackListModifiedWhileInUse(e2);
        }
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
    public void onPageSelected(int i2) {
        try {
            Iterator<OriginalViewPager2.OnPageChangeCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPageSelected(i2);
            }
        } catch (ConcurrentModificationException e2) {
            throwCallbackListModifiedWhileInUse(e2);
        }
    }

    public void removeOnPageChangeCallback(OriginalViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallbacks.remove(onPageChangeCallback);
    }
}
