package miuix.stretchablewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class WidgetContainer extends LinearLayout {
    public WidgetContainer(Context context) {
        this(context, null);
    }

    public int getWidgetHeight() {
        return getHeight();
    }

    public void setWidgetHeight(int i2) {
        if (i2 < 0) {
            return;
        }
        getLayoutParams().height = i2;
        requestLayout();
    }

    public WidgetContainer(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WidgetContainer(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
