package L;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorStateList f370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f371b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f372c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f373d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f374e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f375f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f376g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f377h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f378i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f379j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f380k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f381l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f382m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f383n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f384o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f385p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Typeface f386q;

    public class a extends ResourcesCompat.FontCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f f387a;

        public a(f fVar) {
            this.f387a = fVar;
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        /* JADX INFO: renamed from: onFontRetrievalFailed */
        public void lambda$callbackFailAsync$1(int i2) {
            d.this.f385p = true;
            this.f387a.a(i2);
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        /* JADX INFO: renamed from: onFontRetrieved */
        public void lambda$callbackSuccessAsync$0(Typeface typeface) {
            d dVar = d.this;
            dVar.f386q = Typeface.create(typeface, dVar.f374e);
            d.this.f385p = true;
            this.f387a.b(d.this.f386q, false);
        }
    }

    public class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f389a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextPaint f390b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ f f391c;

        public b(Context context, TextPaint textPaint, f fVar) {
            this.f389a = context;
            this.f390b = textPaint;
            this.f391c = fVar;
        }

        @Override // L.f
        public void a(int i2) {
            this.f391c.a(i2);
        }

        @Override // L.f
        public void b(Typeface typeface, boolean z2) {
            d.this.p(this.f389a, this.f390b, typeface);
            this.f391c.b(typeface, z2);
        }
    }

    public d(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, j.W4);
        l(typedArrayObtainStyledAttributes.getDimension(j.X4, 0.0f));
        k(c.a(context, typedArrayObtainStyledAttributes, j.a5));
        this.f370a = c.a(context, typedArrayObtainStyledAttributes, j.b5);
        this.f371b = c.a(context, typedArrayObtainStyledAttributes, j.c5);
        this.f374e = typedArrayObtainStyledAttributes.getInt(j.Z4, 0);
        this.f375f = typedArrayObtainStyledAttributes.getInt(j.Y4, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, j.i5, j.h5);
        this.f384o = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.f373d = typedArrayObtainStyledAttributes.getString(iE);
        this.f376g = typedArrayObtainStyledAttributes.getBoolean(j.j5, false);
        this.f372c = c.a(context, typedArrayObtainStyledAttributes, j.d5);
        this.f377h = typedArrayObtainStyledAttributes.getFloat(j.e5, 0.0f);
        this.f378i = typedArrayObtainStyledAttributes.getFloat(j.f5, 0.0f);
        this.f379j = typedArrayObtainStyledAttributes.getFloat(j.g5, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(i2, j.q3);
        int i3 = j.r3;
        this.f380k = typedArrayObtainStyledAttributes2.hasValue(i3);
        this.f381l = typedArrayObtainStyledAttributes2.getFloat(i3, 0.0f);
        typedArrayObtainStyledAttributes2.recycle();
    }

    public final void d() {
        String str;
        if (this.f386q == null && (str = this.f373d) != null) {
            this.f386q = Typeface.create(str, this.f374e);
        }
        if (this.f386q == null) {
            int i2 = this.f375f;
            if (i2 == 1) {
                this.f386q = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.f386q = Typeface.SERIF;
            } else if (i2 != 3) {
                this.f386q = Typeface.DEFAULT;
            } else {
                this.f386q = Typeface.MONOSPACE;
            }
            this.f386q = Typeface.create(this.f386q, this.f374e);
        }
    }

    public Typeface e() {
        d();
        return this.f386q;
    }

    public Typeface f(Context context) {
        if (this.f385p) {
            return this.f386q;
        }
        if (!context.isRestricted()) {
            try {
                Typeface font = ResourcesCompat.getFont(context, this.f384o);
                this.f386q = font;
                if (font != null) {
                    this.f386q = Typeface.create(font, this.f374e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                Log.d("TextAppearance", "Error loading font " + this.f373d, e2);
            }
        }
        d();
        this.f385p = true;
        return this.f386q;
    }

    public void g(Context context, f fVar) {
        if (m(context)) {
            f(context);
        } else {
            d();
        }
        int i2 = this.f384o;
        if (i2 == 0) {
            this.f385p = true;
        }
        if (this.f385p) {
            fVar.b(this.f386q, true);
            return;
        }
        try {
            ResourcesCompat.getFont(context, i2, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.f385p = true;
            fVar.a(1);
        } catch (Exception e2) {
            Log.d("TextAppearance", "Error loading font " + this.f373d, e2);
            this.f385p = true;
            fVar.a(-3);
        }
    }

    public void h(Context context, TextPaint textPaint, f fVar) {
        p(context, textPaint, e());
        g(context, new b(context, textPaint, fVar));
    }

    public ColorStateList i() {
        return this.f382m;
    }

    public float j() {
        return this.f383n;
    }

    public void k(ColorStateList colorStateList) {
        this.f382m = colorStateList;
    }

    public void l(float f2) {
        this.f383n = f2;
    }

    public final boolean m(Context context) {
        if (e.a()) {
            return true;
        }
        int i2 = this.f384o;
        return (i2 != 0 ? ResourcesCompat.getCachedFont(context, i2) : null) != null;
    }

    public void n(Context context, TextPaint textPaint, f fVar) {
        o(context, textPaint, fVar);
        ColorStateList colorStateList = this.f382m;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : ViewCompat.MEASURED_STATE_MASK);
        float f2 = this.f379j;
        float f3 = this.f377h;
        float f4 = this.f378i;
        ColorStateList colorStateList2 = this.f372c;
        textPaint.setShadowLayer(f2, f3, f4, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void o(Context context, TextPaint textPaint, f fVar) {
        if (m(context)) {
            p(context, textPaint, f(context));
        } else {
            h(context, textPaint, fVar);
        }
    }

    public void p(Context context, TextPaint textPaint, Typeface typeface) {
        Typeface typefaceA = g.a(context, typeface);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        textPaint.setTypeface(typeface);
        int i2 = this.f374e & (~typeface.getStyle());
        textPaint.setFakeBoldText((i2 & 1) != 0);
        textPaint.setTextSkewX((i2 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f383n);
        if (this.f380k) {
            textPaint.setLetterSpacing(this.f381l);
        }
    }
}
