package Q;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextInputLayout f628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.google.android.material.textfield.a f629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CheckableImageButton f631d;

    public r(com.google.android.material.textfield.a aVar) {
        this.f628a = aVar.f2212a;
        this.f629b = aVar;
        this.f630c = aVar.getContext();
        this.f631d = aVar.r();
    }

    public void a(Editable editable) {
    }

    public void b(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public int c() {
        return 0;
    }

    public int d() {
        return 0;
    }

    public View.OnFocusChangeListener e() {
        return null;
    }

    public View.OnClickListener f() {
        return null;
    }

    public View.OnFocusChangeListener g() {
        return null;
    }

    public AccessibilityManagerCompat.TouchExplorationStateChangeListener h() {
        return null;
    }

    public boolean i(int i2) {
        return true;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    public boolean m() {
        return false;
    }

    public void n(EditText editText) {
    }

    public void o(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public void p(View view, AccessibilityEvent accessibilityEvent) {
    }

    public void q(boolean z2) {
    }

    public final void r() {
        this.f629b.L(false);
    }

    public void s() {
    }

    public boolean t() {
        return false;
    }

    public void u() {
    }
}
