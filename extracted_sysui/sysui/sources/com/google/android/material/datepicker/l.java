package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class l<S> extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public com.google.android.material.datepicker.a f2007c;

    public class a extends p {
        public a() {
        }
    }

    public static l c(d dVar, int i2, com.google.android.material.datepicker.a aVar) {
        l lVar = new l();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("DATE_SELECTOR_KEY", dVar);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", aVar);
        lVar.setArguments(bundle);
        return lVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f2006b = bundle.getInt("THEME_RES_ID_KEY");
        android.support.v4.media.a.a(bundle.getParcelable("DATE_SELECTOR_KEY"));
        this.f2007c = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater.cloneInContext(new ContextThemeWrapper(getContext(), this.f2006b));
        new a();
        throw null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f2006b);
        bundle.putParcelable("DATE_SELECTOR_KEY", null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f2007c);
    }
}
