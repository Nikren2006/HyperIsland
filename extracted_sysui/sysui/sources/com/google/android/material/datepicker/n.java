package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class n extends BaseAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f2016e = t.i().getMaximum(4);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f2017f = (t.i().getMaximum(5) + t.i().getMaximum(7)) - 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f2018a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Collection f2019b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f2020c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f2021d;

    public n(m mVar, d dVar, a aVar, g gVar) {
        this.f2018a = mVar;
        this.f2021d = aVar;
        this.f2019b = dVar.n();
    }

    public int a(int i2) {
        return b() + (i2 - 1);
    }

    public int b() {
        return this.f2018a.u(this.f2021d.x());
    }

    public final String c(Context context, long j2) {
        return e.a(context, j2, j(j2), i(j2), g(j2));
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i2) {
        if (i2 < b() || i2 > k()) {
            return null;
        }
        return Long.valueOf(this.f2018a.v(l(i2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0054  */
    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.f(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = t.g.f6647k
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.b()
            int r7 = r6 - r7
            if (r7 < 0) goto L54
            com.google.android.material.datepicker.m r8 = r5.f2018a
            int r2 = r8.f2013e
            if (r7 < r2) goto L2d
            goto L54
        L2d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L5d
        L54:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L5d:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L64
            return r0
        L64:
            long r1 = r6.longValue()
            r5.m(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.n.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void f(Context context) {
        if (this.f2020c == null) {
            this.f2020c = new c(context);
        }
    }

    public boolean g(long j2) {
        throw null;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return f2017f;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2 / this.f2018a.f2012d;
    }

    public final boolean h(long j2) {
        throw null;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public boolean i(long j2) {
        throw null;
    }

    public final boolean j(long j2) {
        return t.g().getTimeInMillis() == j2;
    }

    public int k() {
        return (b() + this.f2018a.f2013e) - 1;
    }

    public int l(int i2) {
        return (i2 - b()) + 1;
    }

    public final void m(TextView textView, long j2, int i2) {
        b bVar;
        if (textView == null) {
            return;
        }
        textView.setContentDescription(c(textView.getContext(), j2));
        if (this.f2021d.v().g(j2)) {
            textView.setEnabled(true);
            boolean zH = h(j2);
            textView.setSelected(zH);
            bVar = zH ? this.f2020c.f1920b : j(j2) ? this.f2020c.f1921c : this.f2020c.f1919a;
        } else {
            textView.setEnabled(false);
            bVar = this.f2020c.f1925g;
        }
        bVar.b(textView);
    }

    public final void n(MaterialCalendarGridView materialCalendarGridView, long j2) {
        if (m.s(j2).equals(this.f2018a)) {
            int iW = this.f2018a.w(j2);
            m((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().a(iW) - materialCalendarGridView.getFirstVisiblePosition()), j2, iW);
        }
    }

    public void o(MaterialCalendarGridView materialCalendarGridView) {
        Iterator it = this.f2019b.iterator();
        while (it.hasNext()) {
            n(materialCalendarGridView, ((Long) it.next()).longValue());
        }
    }

    public boolean p(int i2) {
        return i2 >= b() && i2 <= k();
    }
}
