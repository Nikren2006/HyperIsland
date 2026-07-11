package miuix.appcompat.app.strategy;

import android.util.TypedValue;
import android.view.View;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import miuix.appcompat.app.DialogContract;

/* JADX INFO: loaded from: classes2.dex */
public class PanelMeasureRuleImpl implements IPanelMeasureRule {
    @Override // miuix.appcompat.app.strategy.IPanelMeasureRule
    public int measurePanelHeight(int i2, int i3, int i4, int i5, boolean z2) {
        if (View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return i2;
        }
        if (i3 > 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, BasicMeasure.EXACTLY);
        }
        if (z2) {
            i4 = i5;
        }
        int iMin = Math.min(i4, i5);
        return iMin > 0 ? View.MeasureSpec.makeMeasureSpec(Math.min(iMin, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE) : i2;
    }

    @Override // miuix.appcompat.app.strategy.IPanelMeasureRule
    public int measurePanelWidth(int i2, int i3, int i4) {
        return View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE ? i3 > 0 ? View.MeasureSpec.makeMeasureSpec(i3, BasicMeasure.EXACTLY) : i4 > 0 ? View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE) : i2 : i2;
    }

    @Override // miuix.appcompat.app.strategy.IPanelMeasureRule
    public TypedValue selectLimitValue(boolean z2, boolean z3, int i2, DialogContract.ValueList valueList) {
        if (valueList == null) {
            return null;
        }
        if (z2) {
            return valueList.getFull();
        }
        if (!z3 && i2 < 500) {
            return valueList.getMajor();
        }
        return valueList.getMinor();
    }
}
