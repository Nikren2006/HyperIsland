package miuix.viewpager.widget;

/* JADX INFO: loaded from: classes5.dex */
public interface OnPageChangeListener {
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;

    void onPageScrollStateChanged(int i2);

    void onPageScrolled(int i2, float f2, int i3);

    void onPageSelected(int i2);
}
