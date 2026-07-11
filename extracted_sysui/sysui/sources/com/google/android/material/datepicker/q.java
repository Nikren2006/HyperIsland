package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q extends Fragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f2029a = new LinkedHashSet();

    public boolean a(p pVar) {
        return this.f2029a.add(pVar);
    }

    public void b() {
        this.f2029a.clear();
    }
}
