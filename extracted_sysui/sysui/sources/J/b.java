package J;

import android.content.res.Resources;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class b extends a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f346f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f347g;

    public b(View view) {
        super(view);
        Resources resources = view.getResources();
        this.f346f = resources.getDimension(t.c.f6563f);
        this.f347g = resources.getDimension(t.c.f6564g);
    }
}
