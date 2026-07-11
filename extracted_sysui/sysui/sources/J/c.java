package J;

import android.content.res.Resources;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class c extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f348f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f349g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f350h;

    public c(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f348f = resources.getDimension(t.c.f6566i);
        this.f349g = resources.getDimension(t.c.f6565h);
        this.f350h = resources.getDimension(t.c.f6567j);
    }
}
