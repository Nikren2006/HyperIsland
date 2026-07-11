package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
final class MaterialCalendarGridView extends GridView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f1896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f1897b;

    public class a extends AccessibilityDelegateCompat {
        public a() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(null);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void a(int i2, Rect rect) {
        if (i2 == 33) {
            setSelection(getAdapter().k());
        } else if (i2 == 130) {
            setSelection(getAdapter().b());
        } else {
            super.onFocusChanged(true, i2, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public n getAdapter() {
        return (n) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        n adapter = getAdapter();
        adapter.getClass();
        int iMax = Math.max(adapter.b(), getFirstVisiblePosition());
        int iMin = Math.min(adapter.k(), getLastVisiblePosition());
        adapter.getItem(iMax);
        adapter.getItem(iMin);
        throw null;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onFocusChanged(boolean z2, int i2, Rect rect) {
        if (z2) {
            a(i2, rect);
        } else {
            super.onFocusChanged(false, i2, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!super.onKeyDown(i2, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().b()) {
            return true;
        }
        if (19 != i2) {
            return false;
        }
        setSelection(getAdapter().b());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i2, int i3) {
        if (!this.f1897b) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(ViewCompat.MEASURED_SIZE_MASK, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i2) {
        if (i2 < getAdapter().b()) {
            super.setSelection(getAdapter().b());
        } else {
            super.setSelection(i2);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1896a = t.i();
        if (k.k(getContext())) {
            setNextFocusLeftId(t.e.f6610a);
            setNextFocusRightId(t.e.f6613d);
        }
        this.f1897b = k.m(getContext());
        ViewCompat.setAccessibilityDelegate(this, new a());
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof n)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), n.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }
}
