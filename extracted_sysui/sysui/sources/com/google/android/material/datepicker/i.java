package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.RestrictTo;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class i<S> extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Object f1932m = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Object f1933n = "NAVIGATION_PREV_TAG";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Object f1934o = "NAVIGATION_NEXT_TAG";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Object f1935p = "SELECTOR_TOGGLE_TAG";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public com.google.android.material.datepicker.a f1937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public com.google.android.material.datepicker.m f1938d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l f1939e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public com.google.android.material.datepicker.c f1940f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RecyclerView f1941g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RecyclerView f1942h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f1943i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f1944j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public View f1945k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f1946l;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f1947a;

        public a(o oVar) {
            this.f1947a = oVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iFindLastVisibleItemPosition = i.this.p().findLastVisibleItemPosition() - 1;
            if (iFindLastVisibleItemPosition >= 0) {
                i.this.s(this.f1947a.b(iFindLastVisibleItemPosition));
            }
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1949a;

        public b(int i2) {
            this.f1949a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.f1942h.smoothScrollToPosition(this.f1949a);
        }
    }

    public class c extends AccessibilityDelegateCompat {
        public c() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(null);
        }
    }

    public class d extends r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1952a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Context context, int i2, boolean z2, int i3) {
            super(context, i2, z2);
            this.f1952a = i3;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
            if (this.f1952a == 0) {
                iArr[0] = i.this.f1942h.getWidth();
                iArr[1] = i.this.f1942h.getWidth();
            } else {
                iArr[0] = i.this.f1942h.getHeight();
                iArr[1] = i.this.f1942h.getHeight();
            }
        }
    }

    public class e implements m {
        public e() {
        }

        @Override // com.google.android.material.datepicker.i.m
        public void a(long j2) {
            if (i.this.f1937c.v().g(j2)) {
                i.e(i.this);
                throw null;
            }
        }
    }

    public class f extends AccessibilityDelegateCompat {
        public f() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setScrollable(false);
        }
    }

    public class g extends RecyclerView.ItemDecoration {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Calendar f1956a = t.i();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Calendar f1957b = t.i();

        public g() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            if ((recyclerView.getAdapter() instanceof u) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                i.e(i.this);
                throw null;
            }
        }
    }

    public class h extends AccessibilityDelegateCompat {
        public h() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setHintText(i.this.f1946l.getVisibility() == 0 ? i.this.getString(t.h.f6675u) : i.this.getString(t.h.f6673s));
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.datepicker.i$i, reason: collision with other inner class name */
    public class C0057i extends RecyclerView.OnScrollListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f1960a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MaterialButton f1961b;

        public C0057i(o oVar, MaterialButton materialButton) {
            this.f1960a = oVar;
            this.f1961b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                recyclerView.announceForAccessibility(this.f1961b.getText());
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            int iFindFirstVisibleItemPosition = i2 < 0 ? i.this.p().findFirstVisibleItemPosition() : i.this.p().findLastVisibleItemPosition();
            i.this.f1938d = this.f1960a.b(iFindFirstVisibleItemPosition);
            this.f1961b.setText(this.f1960a.c(iFindFirstVisibleItemPosition));
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i.this.v();
        }
    }

    public class k implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f1964a;

        public k(o oVar) {
            this.f1964a = oVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iFindFirstVisibleItemPosition = i.this.p().findFirstVisibleItemPosition() + 1;
            if (iFindFirstVisibleItemPosition < i.this.f1942h.getAdapter().getItemCount()) {
                i.this.s(this.f1964a.b(iFindFirstVisibleItemPosition));
            }
        }
    }

    public enum l {
        DAY,
        YEAR
    }

    public interface m {
        void a(long j2);
    }

    public static /* synthetic */ com.google.android.material.datepicker.d e(i iVar) {
        iVar.getClass();
        return null;
    }

    public static int n(Context context) {
        return context.getResources().getDimensionPixelSize(t.c.f6540H);
    }

    public static int o(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(t.c.f6547O) + resources.getDimensionPixelOffset(t.c.f6548P) + resources.getDimensionPixelOffset(t.c.f6546N);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(t.c.f6542J);
        int i2 = n.f2016e;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(t.c.f6540H) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(t.c.f6545M)) + resources.getDimensionPixelOffset(t.c.f6538F);
    }

    public static i q(com.google.android.material.datepicker.d dVar, int i2, com.google.android.material.datepicker.a aVar, com.google.android.material.datepicker.g gVar) {
        i iVar = new i();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dVar);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", aVar);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", gVar);
        bundle.putParcelable("CURRENT_MONTH_KEY", aVar.z());
        iVar.setArguments(bundle);
        return iVar;
    }

    @Override // com.google.android.material.datepicker.q
    public boolean a(p pVar) {
        return super.a(pVar);
    }

    public final void h(View view, o oVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(t.e.f6627r);
        materialButton.setTag(f1935p);
        ViewCompat.setAccessibilityDelegate(materialButton, new h());
        View viewFindViewById = view.findViewById(t.e.f6629t);
        this.f1943i = viewFindViewById;
        viewFindViewById.setTag(f1933n);
        View viewFindViewById2 = view.findViewById(t.e.f6628s);
        this.f1944j = viewFindViewById2;
        viewFindViewById2.setTag(f1934o);
        this.f1945k = view.findViewById(t.e.f6594A);
        this.f1946l = view.findViewById(t.e.f6631v);
        t(l.DAY);
        materialButton.setText(this.f1938d.x());
        this.f1942h.addOnScrollListener(new C0057i(oVar, materialButton));
        materialButton.setOnClickListener(new j());
        this.f1944j.setOnClickListener(new k(oVar));
        this.f1943i.setOnClickListener(new a(oVar));
    }

    public final RecyclerView.ItemDecoration i() {
        return new g();
    }

    public com.google.android.material.datepicker.a j() {
        return this.f1937c;
    }

    public com.google.android.material.datepicker.c k() {
        return this.f1940f;
    }

    public com.google.android.material.datepicker.m l() {
        return this.f1938d;
    }

    public com.google.android.material.datepicker.d m() {
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f1936b = bundle.getInt("THEME_RES_ID_KEY");
        android.support.v4.media.a.a(bundle.getParcelable("GRID_SELECTOR_KEY"));
        this.f1937c = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        android.support.v4.media.a.a(bundle.getParcelable("DAY_VIEW_DECORATOR_KEY"));
        this.f1938d = (com.google.android.material.datepicker.m) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f1936b);
        this.f1940f = new com.google.android.material.datepicker.c(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        com.google.android.material.datepicker.m mVarA = this.f1937c.A();
        if (com.google.android.material.datepicker.k.k(contextThemeWrapper)) {
            i2 = t.g.f6651o;
            i3 = 1;
        } else {
            i2 = t.g.f6649m;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        viewInflate.setMinimumHeight(o(requireContext()));
        GridView gridView = (GridView) viewInflate.findViewById(t.e.f6632w);
        ViewCompat.setAccessibilityDelegate(gridView, new c());
        int iX = this.f1937c.x();
        gridView.setAdapter((ListAdapter) (iX > 0 ? new com.google.android.material.datepicker.h(iX) : new com.google.android.material.datepicker.h()));
        gridView.setNumColumns(mVarA.f2012d);
        gridView.setEnabled(false);
        this.f1942h = (RecyclerView) viewInflate.findViewById(t.e.f6635z);
        this.f1942h.setLayoutManager(new d(getContext(), i3, false, i3));
        this.f1942h.setTag(f1932m);
        o oVar = new o(contextThemeWrapper, null, this.f1937c, null, new e());
        this.f1942h.setAdapter(oVar);
        int integer = contextThemeWrapper.getResources().getInteger(t.f.f6636a);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(t.e.f6594A);
        this.f1941g = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f1941g.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.f1941g.setAdapter(new u(this));
            this.f1941g.addItemDecoration(i());
        }
        if (viewInflate.findViewById(t.e.f6627r) != null) {
            h(viewInflate, oVar);
        }
        if (!com.google.android.material.datepicker.k.k(contextThemeWrapper)) {
            new PagerSnapHelper().attachToRecyclerView(this.f1942h);
        }
        this.f1942h.scrollToPosition(oVar.d(this.f1938d));
        u();
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f1936b);
        bundle.putParcelable("GRID_SELECTOR_KEY", null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f1937c);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", null);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f1938d);
    }

    public LinearLayoutManager p() {
        return (LinearLayoutManager) this.f1942h.getLayoutManager();
    }

    public final void r(int i2) {
        this.f1942h.post(new b(i2));
    }

    public void s(com.google.android.material.datepicker.m mVar) {
        o oVar = (o) this.f1942h.getAdapter();
        int iD = oVar.d(mVar);
        int iD2 = iD - oVar.d(this.f1938d);
        boolean z2 = Math.abs(iD2) > 3;
        boolean z3 = iD2 > 0;
        this.f1938d = mVar;
        if (z2 && z3) {
            this.f1942h.scrollToPosition(iD - 3);
            r(iD);
        } else if (!z2) {
            r(iD);
        } else {
            this.f1942h.scrollToPosition(iD + 3);
            r(iD);
        }
    }

    public void t(l lVar) {
        this.f1939e = lVar;
        if (lVar == l.YEAR) {
            this.f1941g.getLayoutManager().scrollToPosition(((u) this.f1941g.getAdapter()).a(this.f1938d.f2011c));
            this.f1945k.setVisibility(0);
            this.f1946l.setVisibility(8);
            this.f1943i.setVisibility(8);
            this.f1944j.setVisibility(8);
            return;
        }
        if (lVar == l.DAY) {
            this.f1945k.setVisibility(8);
            this.f1946l.setVisibility(0);
            this.f1943i.setVisibility(0);
            this.f1944j.setVisibility(0);
            s(this.f1938d);
        }
    }

    public final void u() {
        ViewCompat.setAccessibilityDelegate(this.f1942h, new f());
    }

    public void v() {
        l lVar = this.f1939e;
        l lVar2 = l.YEAR;
        if (lVar == lVar2) {
            t(l.DAY);
        } else if (lVar == l.DAY) {
            t(lVar2);
        }
    }
}
