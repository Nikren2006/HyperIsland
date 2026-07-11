package O;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final O.c f504m = new i(0.5f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f507c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f508d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public O.c f509e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public O.c f510f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public O.c f511g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public O.c f512h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f513i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f f514j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f f515k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f f516l;

    public interface c {
        O.c a(O.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i2, int i3) {
        return c(context, i2, i3, 0);
    }

    public static b c(Context context, int i2, int i3, int i4) {
        return d(context, i2, i3, new O.a(i4));
    }

    public static b d(Context context, int i2, int i3, O.c cVar) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
        if (i3 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i3);
        }
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(t.j.i4);
        try {
            int i4 = typedArrayObtainStyledAttributes.getInt(t.j.j4, 0);
            int i5 = typedArrayObtainStyledAttributes.getInt(t.j.m4, i4);
            int i6 = typedArrayObtainStyledAttributes.getInt(t.j.n4, i4);
            int i7 = typedArrayObtainStyledAttributes.getInt(t.j.l4, i4);
            int i8 = typedArrayObtainStyledAttributes.getInt(t.j.k4, i4);
            O.c cVarM = m(typedArrayObtainStyledAttributes, t.j.o4, cVar);
            O.c cVarM2 = m(typedArrayObtainStyledAttributes, t.j.r4, cVarM);
            O.c cVarM3 = m(typedArrayObtainStyledAttributes, t.j.s4, cVarM);
            O.c cVarM4 = m(typedArrayObtainStyledAttributes, t.j.q4, cVarM);
            return new b().y(i5, cVarM2).C(i6, cVarM3).u(i7, cVarM4).q(i8, m(typedArrayObtainStyledAttributes, t.j.p4, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i2, int i3) {
        return f(context, attributeSet, i2, i3, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i2, int i3, int i4) {
        return g(context, attributeSet, i2, i3, new O.a(i4));
    }

    public static b g(Context context, AttributeSet attributeSet, int i2, int i3, O.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.j.m3, i2, i3);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(t.j.n3, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(t.j.o3, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    public static O.c m(TypedArray typedArray, int i2, O.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i3 = typedValuePeekValue.type;
        return i3 == 5 ? new O.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i3 == 6 ? new i(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.f515k;
    }

    public d i() {
        return this.f508d;
    }

    public O.c j() {
        return this.f512h;
    }

    public d k() {
        return this.f507c;
    }

    public O.c l() {
        return this.f511g;
    }

    public f n() {
        return this.f516l;
    }

    public f o() {
        return this.f514j;
    }

    public f p() {
        return this.f513i;
    }

    public d q() {
        return this.f505a;
    }

    public O.c r() {
        return this.f509e;
    }

    public d s() {
        return this.f506b;
    }

    public O.c t() {
        return this.f510f;
    }

    public boolean u(RectF rectF) {
        boolean z2 = this.f516l.getClass().equals(f.class) && this.f514j.getClass().equals(f.class) && this.f513i.getClass().equals(f.class) && this.f515k.getClass().equals(f.class);
        float fA = this.f509e.a(rectF);
        return z2 && ((this.f510f.a(rectF) > fA ? 1 : (this.f510f.a(rectF) == fA ? 0 : -1)) == 0 && (this.f512h.a(rectF) > fA ? 1 : (this.f512h.a(rectF) == fA ? 0 : -1)) == 0 && (this.f511g.a(rectF) > fA ? 1 : (this.f511g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f506b instanceof j) && (this.f505a instanceof j) && (this.f507c instanceof j) && (this.f508d instanceof j));
    }

    public b v() {
        return new b(this);
    }

    public k w(float f2) {
        return v().o(f2).m();
    }

    public k x(O.c cVar) {
        return v().p(cVar).m();
    }

    public k y(c cVar) {
        return v().B(cVar.a(r())).F(cVar.a(t())).t(cVar.a(j())).x(cVar.a(l())).m();
    }

    public k(b bVar) {
        this.f505a = bVar.f517a;
        this.f506b = bVar.f518b;
        this.f507c = bVar.f519c;
        this.f508d = bVar.f520d;
        this.f509e = bVar.f521e;
        this.f510f = bVar.f522f;
        this.f511g = bVar.f523g;
        this.f512h = bVar.f524h;
        this.f513i = bVar.f525i;
        this.f514j = bVar.f526j;
        this.f515k = bVar.f527k;
        this.f516l = bVar.f528l;
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f517a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f518b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f519c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f520d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public O.c f521e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public O.c f522f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public O.c f523g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public O.c f524h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public f f525i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public f f526j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public f f527k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public f f528l;

        public b() {
            this.f517a = h.b();
            this.f518b = h.b();
            this.f519c = h.b();
            this.f520d = h.b();
            this.f521e = new O.a(0.0f);
            this.f522f = new O.a(0.0f);
            this.f523g = new O.a(0.0f);
            this.f524h = new O.a(0.0f);
            this.f525i = h.c();
            this.f526j = h.c();
            this.f527k = h.c();
            this.f528l = h.c();
        }

        public static float n(d dVar) {
            if (dVar instanceof j) {
                return ((j) dVar).f503a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f451a;
            }
            return -1.0f;
        }

        public b A(float f2) {
            this.f521e = new O.a(f2);
            return this;
        }

        public b B(O.c cVar) {
            this.f521e = cVar;
            return this;
        }

        public b C(int i2, O.c cVar) {
            return D(h.a(i2)).F(cVar);
        }

        public b D(d dVar) {
            this.f518b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                E(fN);
            }
            return this;
        }

        public b E(float f2) {
            this.f522f = new O.a(f2);
            return this;
        }

        public b F(O.c cVar) {
            this.f522f = cVar;
            return this;
        }

        public k m() {
            return new k(this);
        }

        public b o(float f2) {
            return A(f2).E(f2).w(f2).s(f2);
        }

        public b p(O.c cVar) {
            return B(cVar).F(cVar).x(cVar).t(cVar);
        }

        public b q(int i2, O.c cVar) {
            return r(h.a(i2)).t(cVar);
        }

        public b r(d dVar) {
            this.f520d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                s(fN);
            }
            return this;
        }

        public b s(float f2) {
            this.f524h = new O.a(f2);
            return this;
        }

        public b t(O.c cVar) {
            this.f524h = cVar;
            return this;
        }

        public b u(int i2, O.c cVar) {
            return v(h.a(i2)).x(cVar);
        }

        public b v(d dVar) {
            this.f519c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                w(fN);
            }
            return this;
        }

        public b w(float f2) {
            this.f523g = new O.a(f2);
            return this;
        }

        public b x(O.c cVar) {
            this.f523g = cVar;
            return this;
        }

        public b y(int i2, O.c cVar) {
            return z(h.a(i2)).B(cVar);
        }

        public b z(d dVar) {
            this.f517a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                A(fN);
            }
            return this;
        }

        public b(k kVar) {
            this.f517a = h.b();
            this.f518b = h.b();
            this.f519c = h.b();
            this.f520d = h.b();
            this.f521e = new O.a(0.0f);
            this.f522f = new O.a(0.0f);
            this.f523g = new O.a(0.0f);
            this.f524h = new O.a(0.0f);
            this.f525i = h.c();
            this.f526j = h.c();
            this.f527k = h.c();
            this.f528l = h.c();
            this.f517a = kVar.f505a;
            this.f518b = kVar.f506b;
            this.f519c = kVar.f507c;
            this.f520d = kVar.f508d;
            this.f521e = kVar.f509e;
            this.f522f = kVar.f510f;
            this.f523g = kVar.f511g;
            this.f524h = kVar.f512h;
            this.f525i = kVar.f513i;
            this.f526j = kVar.f514j;
            this.f527k = kVar.f515k;
            this.f528l = kVar.f516l;
        }
    }

    public k() {
        this.f505a = h.b();
        this.f506b = h.b();
        this.f507c = h.b();
        this.f508d = h.b();
        this.f509e = new O.a(0.0f);
        this.f510f = new O.a(0.0f);
        this.f511g = new O.a(0.0f);
        this.f512h = new O.a(0.0f);
        this.f513i = h.c();
        this.f514j = h.c();
        this.f515k = h.c();
        this.f516l = h.c();
    }
}
