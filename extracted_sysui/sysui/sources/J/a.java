package J;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import androidx.core.view.animation.PathInterpolatorCompat;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TimeInterpolator f341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f344d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f345e;

    public a(View view) {
        this.f342b = view;
        Context context = view.getContext();
        this.f341a = d.g(context, AbstractC0741a.f6491F, PathInterpolatorCompat.create(0.0f, 0.0f, 0.0f, 1.0f));
        this.f343c = d.f(context, AbstractC0741a.f6524x, 300);
        this.f344d = d.f(context, AbstractC0741a.f6486A, SecondaryParamsKt.FROM_BT);
        this.f345e = d.f(context, AbstractC0741a.f6526z, 100);
    }
}
