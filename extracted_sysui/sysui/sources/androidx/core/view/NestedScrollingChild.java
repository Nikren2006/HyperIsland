package androidx.core.view;

/* JADX INFO: loaded from: classes.dex */
public interface NestedScrollingChild {
    boolean dispatchNestedFling(float f2, float f3, boolean z2);

    boolean dispatchNestedPreFling(float f2, float f3);

    boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2);

    boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr);

    boolean hasNestedScrollingParent();

    boolean isNestedScrollingEnabled();

    void setNestedScrollingEnabled(boolean z2);

    boolean startNestedScroll(int i2);

    void stopNestedScroll();
}
