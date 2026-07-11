package miuix.theme.symbol;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import androidx.annotation.IntRange;
import androidx.collection.ScatterMapKt;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SymbolPaint<T extends Paint> {
    private final T paint;
    private int[] state = null;
    private ColorStateList colorsList = null;

    public SymbolPaint(T t2) {
        this.paint = t2;
        t2.setAlpha(255);
    }

    public boolean applyState(int[] iArr) {
        this.state = iArr;
        int colorForCurrentState = getColorForCurrentState();
        int color = this.paint.getColor();
        this.paint.setColor(colorForCurrentState);
        return colorForCurrentState != color;
    }

    public int getAlpha() {
        return this.paint.getAlpha();
    }

    public int getColorForCurrentState() {
        ColorStateList colorStateList = this.colorsList;
        return getColorForCurrentState(colorStateList != null ? colorStateList.getDefaultColor() : 0);
    }

    public ColorStateList getColorsList() {
        return this.colorsList;
    }

    public T getPaint() {
        return this.paint;
    }

    public boolean isStateful() {
        ColorStateList colorStateList = this.colorsList;
        return colorStateList != null && colorStateList.isStateful();
    }

    public void setAlpha(@IntRange(from = 0, to = ScatterMapKt.Sentinel) int i2) {
        if (this.paint.getAlpha() != i2) {
            this.paint.setAlpha(i2);
        }
    }

    public void setColorsList(ColorStateList colorStateList) {
        this.colorsList = colorStateList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("color=#");
        sb.append(Integer.toHexString(this.paint.getColor()));
        sb.append(", state=");
        int[] iArr = this.state;
        sb.append(iArr != null ? Arrays.toString(iArr) : "null");
        sb.append(", colorList=");
        sb.append(this.colorsList);
        return sb.toString();
    }

    private int getColorForCurrentState(int i2) {
        ColorStateList colorStateList = this.colorsList;
        return colorStateList != null ? colorStateList.getColorForState(this.state, i2) : i2;
    }
}
