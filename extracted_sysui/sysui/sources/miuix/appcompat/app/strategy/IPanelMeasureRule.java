package miuix.appcompat.app.strategy;

import android.util.TypedValue;
import miuix.appcompat.app.DialogContract;

/* JADX INFO: loaded from: classes2.dex */
public interface IPanelMeasureRule {
    int measurePanelHeight(int i2, int i3, int i4, int i5, boolean z2);

    int measurePanelWidth(int i2, int i3, int i4);

    TypedValue selectLimitValue(boolean z2, boolean z3, int i2, DialogContract.ValueList valueList);
}
