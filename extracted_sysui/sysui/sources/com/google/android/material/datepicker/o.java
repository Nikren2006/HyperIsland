package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.i;

/* JADX INFO: loaded from: classes2.dex */
public class o extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.android.material.datepicker.a f2022a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i.m f2023b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2024c;

    public class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ MaterialCalendarGridView f2025a;

        public a(MaterialCalendarGridView materialCalendarGridView) {
            this.f2025a = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (this.f2025a.getAdapter().p(i2)) {
                o.this.f2023b.a(this.f2025a.getAdapter().getItem(i2).longValue());
            }
        }
    }

    public static class b extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f2027a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final MaterialCalendarGridView f2028b;

        public b(LinearLayout linearLayout, boolean z2) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(t.e.f6630u);
            this.f2027a = textView;
            ViewCompat.setAccessibilityHeading(textView, true);
            this.f2028b = (MaterialCalendarGridView) linearLayout.findViewById(t.e.f6626q);
            if (z2) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    public o(Context context, d dVar, com.google.android.material.datepicker.a aVar, g gVar, i.m mVar) {
        m mVarA = aVar.A();
        m mVarW = aVar.w();
        m mVarZ = aVar.z();
        if (mVarA.compareTo(mVarZ) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (mVarZ.compareTo(mVarW) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f2024c = (n.f2016e * i.n(context)) + (k.k(context) ? i.n(context) : 0);
        this.f2022a = aVar;
        this.f2023b = mVar;
        setHasStableIds(true);
    }

    public m b(int i2) {
        return this.f2022a.A().z(i2);
    }

    public CharSequence c(int i2) {
        return b(i2).x();
    }

    public int d(m mVar) {
        return this.f2022a.A().A(mVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i2) {
        m mVarZ = this.f2022a.A().z(i2);
        bVar.f2027a.setText(mVarZ.x());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.f2028b.findViewById(t.e.f6626q);
        if (materialCalendarGridView.getAdapter() == null || !mVarZ.equals(materialCalendarGridView.getAdapter().f2018a)) {
            n nVar = new n(mVarZ, null, this.f2022a, null);
            materialCalendarGridView.setNumColumns(mVarZ.f2012d);
            materialCalendarGridView.setAdapter((ListAdapter) nVar);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter().o(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(t.g.f6650n, viewGroup, false);
        if (!k.k(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.f2024c));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2022a.y();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return this.f2022a.A().z(i2).y();
    }
}
