package Q;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes2.dex */
public class x extends r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f679e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public EditText f680f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View.OnClickListener f681g;

    public x(com.google.android.material.textfield.a aVar, int i2) {
        super(aVar);
        this.f679e = t.d.f6584a;
        this.f681g = new View.OnClickListener() { // from class: Q.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f678a.y(view);
            }
        };
        if (i2 != 0) {
            this.f679e = i2;
        }
    }

    public static boolean x(EditText editText) {
        return editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(View view) {
        EditText editText = this.f680f;
        if (editText == null) {
            return;
        }
        int selectionEnd = editText.getSelectionEnd();
        if (w()) {
            this.f680f.setTransformationMethod(null);
        } else {
            this.f680f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (selectionEnd >= 0) {
            this.f680f.setSelection(selectionEnd);
        }
        r();
    }

    @Override // Q.r
    public void b(CharSequence charSequence, int i2, int i3, int i4) {
        r();
    }

    @Override // Q.r
    public int c() {
        return t.h.f6676v;
    }

    @Override // Q.r
    public int d() {
        return this.f679e;
    }

    @Override // Q.r
    public View.OnClickListener f() {
        return this.f681g;
    }

    @Override // Q.r
    public boolean l() {
        return true;
    }

    @Override // Q.r
    public boolean m() {
        return !w();
    }

    @Override // Q.r
    public void n(EditText editText) {
        this.f680f = editText;
        r();
    }

    @Override // Q.r
    public void s() {
        if (x(this.f680f)) {
            this.f680f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override // Q.r
    public void u() {
        EditText editText = this.f680f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public final boolean w() {
        EditText editText = this.f680f;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }
}
