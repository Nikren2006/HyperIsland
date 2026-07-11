package O;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public abstract class h {
    public static d a(int i2) {
        return i2 != 0 ? i2 != 1 ? b() : new e() : new j();
    }

    public static d b() {
        return new j();
    }

    public static f c() {
        return new f();
    }

    public static void d(View view, float f2) {
        Drawable background = view.getBackground();
        if (background instanceof g) {
            ((g) background).S(f2);
        }
    }

    public static void e(View view) {
        Drawable background = view.getBackground();
        if (background instanceof g) {
            f(view, (g) background);
        }
    }

    public static void f(View view, g gVar) {
        if (gVar.L()) {
            gVar.W(H.n.f(view));
        }
    }
}
