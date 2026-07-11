package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.datepicker.a;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public final class k<S> extends DialogFragment {

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final Object f1970I = "CONFIRM_BUTTON_TAG";

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final Object f1971J = "CANCEL_BUTTON_TAG";

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final Object f1972K = "TOGGLE_BUTTON_TAG";

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f1973A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public CheckableImageButton f1974B;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public O.g f1975D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Button f1976E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f1977F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public CharSequence f1978G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public CharSequence f1979H;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f1980a = new LinkedHashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f1981b = new LinkedHashSet();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f1982c = new LinkedHashSet();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f1983d = new LinkedHashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1984e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public q f1985f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public com.google.android.material.datepicker.a f1986g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public i f1987h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1988i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f1989j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1990k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1991l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1992m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f1993n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1994o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f1995p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1996q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f1997r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1998s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public CharSequence f1999x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f2000y;

    public class a implements OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2001a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2002b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2003c;

        public a(int i2, View view, int i3) {
            this.f2001a = i2;
            this.f2002b = view;
            this.f2003c = i3;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            int i2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).top;
            if (this.f2001a >= 0) {
                this.f2002b.getLayoutParams().height = this.f2001a + i2;
                View view2 = this.f2002b;
                view2.setLayoutParams(view2.getLayoutParams());
            }
            View view3 = this.f2002b;
            view3.setPadding(view3.getPaddingLeft(), this.f2003c + i2, this.f2002b.getPaddingRight(), this.f2002b.getPaddingBottom());
            return windowInsetsCompat;
        }
    }

    public class b extends p {
        public b() {
        }
    }

    public static Drawable b(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, AppCompatResources.getDrawable(context, t.d.f6585b));
        stateListDrawable.addState(new int[0], AppCompatResources.getDrawable(context, t.d.f6586c));
        return stateListDrawable;
    }

    private d d() {
        android.support.v4.media.a.a(getArguments().getParcelable("DATE_SELECTOR_KEY"));
        return null;
    }

    public static CharSequence e(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        String[] strArrSplit = TextUtils.split(String.valueOf(charSequence), "\n");
        return strArrSplit.length > 1 ? strArrSplit[0] : charSequence;
    }

    public static int h(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(t.c.f6539G);
        int i2 = m.t().f2012d;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(t.c.f6541I) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(t.c.f6544L));
    }

    public static boolean k(Context context) {
        return o(context, R.attr.windowFullscreen);
    }

    public static boolean m(Context context) {
        return o(context, AbstractC0741a.f6492G);
    }

    public static boolean o(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(L.b.d(context, AbstractC0741a.f6520t, i.class.getCanonicalName()), new int[]{i2});
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z2;
    }

    public final void c(Window window) {
        if (this.f1977F) {
            return;
        }
        View viewFindViewById = requireView().findViewById(t.e.f6616g);
        H.c.a(window, true, H.n.d(viewFindViewById), null);
        ViewCompat.setOnApplyWindowInsetsListener(viewFindViewById, new a(viewFindViewById.getLayoutParams().height, viewFindViewById, viewFindViewById.getPaddingTop()));
        this.f1977F = true;
    }

    public final String f() {
        d();
        requireContext();
        throw null;
    }

    public String g() {
        d();
        getContext();
        throw null;
    }

    public final int i(Context context) {
        int i2 = this.f1984e;
        if (i2 != 0) {
            return i2;
        }
        d();
        throw null;
    }

    public final void j(Context context) {
        this.f1974B.setTag(f1972K);
        this.f1974B.setImageDrawable(b(context));
        this.f1974B.setChecked(this.f1991l != 0);
        ViewCompat.setAccessibilityDelegate(this.f1974B, null);
        s(this.f1974B);
        this.f1974B.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f1969a.n(view);
            }
        });
    }

    public final boolean l() {
        return getResources().getConfiguration().orientation == 2;
    }

    public final /* synthetic */ void n(View view) {
        d();
        throw null;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.f1982c.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f1984e = bundle.getInt("OVERRIDE_THEME_RES_ID");
        android.support.v4.media.a.a(bundle.getParcelable("DATE_SELECTOR_KEY"));
        this.f1986g = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        android.support.v4.media.a.a(bundle.getParcelable("DAY_VIEW_DECORATOR_KEY"));
        this.f1988i = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.f1989j = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.f1991l = bundle.getInt("INPUT_MODE_KEY");
        this.f1992m = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.f1993n = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.f1994o = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.f1995p = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.f1996q = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.f1997r = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.f1998s = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.f1999x = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence text = this.f1989j;
        if (text == null) {
            text = requireContext().getResources().getText(this.f1988i);
        }
        this.f1978G = text;
        this.f1979H = e(text);
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), i(requireContext()));
        Context context = dialog.getContext();
        this.f1990k = k(context);
        int i2 = AbstractC0741a.f6520t;
        int i3 = t.i.f6690m;
        this.f1975D = new O.g(context, null, i2, i3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, t.j.A2, i2, i3);
        int color = typedArrayObtainStyledAttributes.getColor(t.j.B2, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f1975D.J(context);
        this.f1975D.T(ColorStateList.valueOf(color));
        this.f1975D.S(ViewCompat.getElevation(dialog.getWindow().getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.f1990k ? t.g.f6654r : t.g.f6653q, viewGroup);
        Context context = viewInflate.getContext();
        if (this.f1990k) {
            viewInflate.findViewById(t.e.f6633x).setLayoutParams(new LinearLayout.LayoutParams(h(context), -2));
        } else {
            viewInflate.findViewById(t.e.f6634y).setLayoutParams(new LinearLayout.LayoutParams(h(context), -1));
        }
        TextView textView = (TextView) viewInflate.findViewById(t.e.f6595B);
        this.f1973A = textView;
        ViewCompat.setAccessibilityLiveRegion(textView, 1);
        this.f1974B = (CheckableImageButton) viewInflate.findViewById(t.e.f6596C);
        this.f2000y = (TextView) viewInflate.findViewById(t.e.f6597D);
        j(context);
        this.f1976E = (Button) viewInflate.findViewById(t.e.f6613d);
        d();
        throw null;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.f1983d.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.f1984e);
        bundle.putParcelable("DATE_SELECTOR_KEY", null);
        a.b bVar = new a.b(this.f1986g);
        i iVar = this.f1987h;
        m mVarL = iVar == null ? null : iVar.l();
        if (mVarL != null) {
            bVar.b(mVarL.f2014f);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar.a());
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", null);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.f1988i);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.f1989j);
        bundle.putInt("INPUT_MODE_KEY", this.f1991l);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.f1992m);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.f1993n);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.f1994o);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.f1995p);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.f1996q);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.f1997r);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.f1998s);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.f1999x);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.f1990k) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.f1975D);
            c(window);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(t.c.f6543K);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.f1975D, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new D.a(requireDialog(), rect));
        }
        p();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        this.f1985f.b();
        super.onStop();
    }

    public final void p() {
        int i2 = i(requireContext());
        d();
        i iVarQ = i.q(null, i2, this.f1986g, null);
        this.f1987h = iVarQ;
        q qVarC = iVarQ;
        if (this.f1991l == 1) {
            d();
            qVarC = l.c(null, i2, this.f1986g);
        }
        this.f1985f = qVarC;
        r();
        q(g());
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(t.e.f6633x, this.f1985f);
        fragmentTransactionBeginTransaction.commitNow();
        this.f1985f.a(new b());
    }

    public void q(String str) {
        this.f1973A.setContentDescription(f());
        this.f1973A.setText(str);
    }

    public final void r() {
        this.f2000y.setText((this.f1991l == 1 && l()) ? this.f1979H : this.f1978G);
    }

    public final void s(CheckableImageButton checkableImageButton) {
        this.f1974B.setContentDescription(this.f1991l == 1 ? checkableImageButton.getContext().getString(t.h.f6672r) : checkableImageButton.getContext().getString(t.h.f6674t));
    }
}
