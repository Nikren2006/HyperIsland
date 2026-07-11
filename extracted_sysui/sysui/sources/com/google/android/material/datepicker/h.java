package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class h extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f1928d = 4;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f1929a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1930b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1931c;

    public h() {
        Calendar calendarI = t.i();
        this.f1929a = calendarI;
        this.f1930b = calendarI.getMaximum(7);
        this.f1931c = calendarI.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i2) {
        if (i2 >= this.f1930b) {
            return null;
        }
        return Integer.valueOf(b(i2));
    }

    public final int b(int i2) {
        int i3 = i2 + this.f1931c;
        int i4 = this.f1930b;
        return i3 > i4 ? i3 - i4 : i3;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f1930b;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(t.g.f6648l, viewGroup, false);
        }
        this.f1929a.set(7, b(i2));
        textView.setText(this.f1929a.getDisplayName(7, f1928d, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(t.h.f6666l), this.f1929a.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }

    public h(int i2) {
        Calendar calendarI = t.i();
        this.f1929a = calendarI;
        this.f1930b = calendarI.getMaximum(7);
        this.f1931c = i2;
    }
}
