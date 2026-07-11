package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class u extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f2035a;

    public static class a extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f2036a;

        public a(TextView textView) {
            super(textView);
            this.f2036a = textView;
        }
    }

    public u(i iVar) {
        this.f2035a = iVar;
    }

    public int a(int i2) {
        return i2 - this.f2035a.j().A().f2011c;
    }

    public int b(int i2) {
        return this.f2035a.j().A().f2011c + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i2) {
        int iB = b(i2);
        aVar.f2036a.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iB)));
        TextView textView = aVar.f2036a;
        textView.setContentDescription(e.e(textView.getContext(), iB));
        c cVarK = this.f2035a.k();
        if (t.g().get(1) == iB) {
            b bVar = cVarK.f1924f;
        } else {
            b bVar2 = cVarK.f1922d;
        }
        this.f2035a.m();
        throw null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new a((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(t.g.f6652p, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2035a.j().B();
    }
}
