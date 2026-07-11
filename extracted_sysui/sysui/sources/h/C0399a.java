package h;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import d.AbstractC0300a;
import i.i;
import java.util.HashMap;
import java.util.Map;
import p.AbstractC0724d;

/* JADX INFO: renamed from: h.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0399a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AssetManager f4472d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f4469a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f4470b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f4471c = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4473e = ".ttf";

    public C0399a(Drawable.Callback callback, AbstractC0300a abstractC0300a) {
        if (callback instanceof View) {
            this.f4472d = ((View) callback).getContext().getAssets();
        } else {
            AbstractC0724d.c("LottieDrawable must be inside of a view for images to work.");
            this.f4472d = null;
        }
    }

    public final Typeface a(String str) {
        Typeface typeface = (Typeface) this.f4471c.get(str);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.f4472d, "fonts/" + str + this.f4473e);
        this.f4471c.put(str, typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }

    public Typeface b(String str, String str2) {
        this.f4469a.b(str, str2);
        Typeface typeface = (Typeface) this.f4470b.get(this.f4469a);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceD = d(a(str), str2);
        this.f4470b.put(this.f4469a, typefaceD);
        return typefaceD;
    }

    public void c(AbstractC0300a abstractC0300a) {
    }

    public final Typeface d(Typeface typeface, String str) {
        boolean zContains = str.contains("Italic");
        boolean zContains2 = str.contains("Bold");
        int i2 = (zContains && zContains2) ? 3 : zContains ? 2 : zContains2 ? 1 : 0;
        return typeface.getStyle() == i2 ? typeface : Typeface.create(typeface, i2);
    }
}
