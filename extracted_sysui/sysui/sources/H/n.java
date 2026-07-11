package H;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n {

    public class a implements OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f285a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f286b;

        public a(c cVar, d dVar) {
            this.f285a = cVar;
            this.f286b = dVar;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return this.f285a.a(view, windowInsetsCompat, new d(this.f286b));
        }
    }

    public class b implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            ViewCompat.requestApplyInsets(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public interface c {
        WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat, d dVar);
    }

    public static void b(View view, c cVar) {
        ViewCompat.setOnApplyWindowInsetsListener(view, new a(cVar, new d(ViewCompat.getPaddingStart(view), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom())));
        j(view);
    }

    public static float c(Context context, int i2) {
        return TypedValue.applyDimension(1, i2, context.getResources().getDisplayMetrics());
    }

    public static Integer d(View view) {
        ColorStateList colorStateListF = E.a.f(view.getBackground());
        if (colorStateListF != null) {
            return Integer.valueOf(colorStateListF.getDefaultColor());
        }
        return null;
    }

    public static InputMethodManager e(View view) {
        return (InputMethodManager) ContextCompat.getSystemService(view.getContext(), InputMethodManager.class);
    }

    public static float f(View view) {
        float elevation = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            elevation += ViewCompat.getElevation((View) parent);
        }
        return elevation;
    }

    public static boolean g(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static PorterDuff.Mode i(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static void j(View view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            ViewCompat.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new b());
        }
    }

    public static void k(final View view, final boolean z2) {
        view.requestFocus();
        view.post(new Runnable() { // from class: H.m
            @Override // java.lang.Runnable
            public final void run() {
                n.l(view, z2);
            }
        });
    }

    public static void l(View view, boolean z2) {
        WindowInsetsControllerCompat windowInsetsController;
        if (!z2 || (windowInsetsController = ViewCompat.getWindowInsetsController(view)) == null) {
            e(view).showSoftInput(view, 1);
        } else {
            windowInsetsController.show(WindowInsetsCompat.Type.ime());
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f287a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f288b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f289c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f290d;

        public d(int i2, int i3, int i4, int i5) {
            this.f287a = i2;
            this.f288b = i3;
            this.f289c = i4;
            this.f290d = i5;
        }

        public d(d dVar) {
            this.f287a = dVar.f287a;
            this.f288b = dVar.f288b;
            this.f289c = dVar.f289c;
            this.f290d = dVar.f290d;
        }
    }
}
