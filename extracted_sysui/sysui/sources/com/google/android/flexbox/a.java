package com.google.android.flexbox;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface a {
    int getAlignItems();

    int getChildHeightMeasureSpec(int i2, int i3, int i4);

    int getChildWidthMeasureSpec(int i2, int i3, int i4);

    int getDecorationLengthCrossAxis(View view);

    int getDecorationLengthMainAxis(View view, int i2, int i3);

    int getFlexDirection();

    int getFlexItemCount();

    List getFlexLinesInternal();

    int getFlexWrap();

    int getLargestMainSize();

    int getMaxLine();

    int getPaddingBottom();

    int getPaddingEnd();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingStart();

    int getPaddingTop();

    View getReorderedFlexItemAt(int i2);

    boolean isMainAxisDirectionHorizontal();

    void onNewFlexItemAdded(View view, int i2, int i3, c cVar);

    void onNewFlexLineAdded(c cVar);

    void updateViewCache(int i2, View view);
}
