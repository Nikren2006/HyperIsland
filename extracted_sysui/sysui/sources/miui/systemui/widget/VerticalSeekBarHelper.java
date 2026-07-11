package miui.systemui.widget;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalSeekBarHelper {
    public static int calcTop(float f2, int i2, int i3, int i4) {
        int i5 = (((int) (f2 * i2)) + i3) - i4;
        int i6 = i3 - i4;
        if (i5 < i6) {
            i5 = i6;
        }
        int i7 = i6 + i2;
        return i5 > i7 ? i7 : i5;
    }
}
