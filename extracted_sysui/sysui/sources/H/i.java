package H;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f274c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f275d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public L.d f278g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f272a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L.f f273b = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f276e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WeakReference f277f = new WeakReference(null);

    public class a extends L.f {
        public a() {
        }

        @Override // L.f
        public void a(int i2) {
            i.this.f276e = true;
            b bVar = (b) i.this.f277f.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // L.f
        public void b(Typeface typeface, boolean z2) {
            if (z2) {
                return;
            }
            i.this.f276e = true;
            b bVar = (b) i.this.f277f.get();
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    public interface b {
        void a();

        int[] getState();

        boolean onStateChange(int[] iArr);
    }

    public i(b bVar) {
        i(bVar);
    }

    public final float c(String str) {
        if (str == null) {
            return 0.0f;
        }
        return Math.abs(this.f272a.getFontMetrics().ascent);
    }

    public final float d(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f272a.measureText(charSequence, 0, charSequence.length());
    }

    public L.d e() {
        return this.f278g;
    }

    public TextPaint f() {
        return this.f272a;
    }

    public float g(String str) {
        if (!this.f276e) {
            return this.f274c;
        }
        h(str);
        return this.f274c;
    }

    public final void h(String str) {
        this.f274c = d(str);
        this.f275d = c(str);
        this.f276e = false;
    }

    public void i(b bVar) {
        this.f277f = new WeakReference(bVar);
    }

    public void j(L.d dVar, Context context) {
        if (this.f278g != dVar) {
            this.f278g = dVar;
            if (dVar != null) {
                dVar.o(context, this.f272a, this.f273b);
                b bVar = (b) this.f277f.get();
                if (bVar != null) {
                    this.f272a.drawableState = bVar.getState();
                }
                dVar.n(context, this.f272a, this.f273b);
                this.f276e = true;
            }
            b bVar2 = (b) this.f277f.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void k(boolean z2) {
        this.f276e = z2;
    }

    public void l(Context context) {
        this.f278g.n(context, this.f272a, this.f273b);
    }
}
