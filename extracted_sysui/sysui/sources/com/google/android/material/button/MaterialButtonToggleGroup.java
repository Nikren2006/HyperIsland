package com.google.android.material.button;

import H.n;
import O.k;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import androidx.annotation.BoolRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import t.AbstractC0741a;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int f1738k = i.f6689l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f1739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f1740b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f1741c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Comparator f1742d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Integer[] f1743e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1744f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1745g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1746h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1747i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Set f1748j;

    public class a implements Comparator {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            int iCompareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int iCompareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
            return iCompareTo2 != 0 ? iCompareTo2 : Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton2)));
        }
    }

    public class b extends AccessibilityDelegateCompat {
        public b() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, MaterialButtonToggleGroup.this.i(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public static class c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final O.c f1751e = new O.a(0.0f);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public O.c f1752a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public O.c f1753b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public O.c f1754c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public O.c f1755d;

        public c(O.c cVar, O.c cVar2, O.c cVar3, O.c cVar4) {
            this.f1752a = cVar;
            this.f1753b = cVar3;
            this.f1754c = cVar4;
            this.f1755d = cVar2;
        }

        public static c a(c cVar) {
            O.c cVar2 = f1751e;
            return new c(cVar2, cVar.f1755d, cVar2, cVar.f1754c);
        }

        public static c b(c cVar, View view) {
            return n.g(view) ? c(cVar) : d(cVar);
        }

        public static c c(c cVar) {
            O.c cVar2 = cVar.f1752a;
            O.c cVar3 = cVar.f1755d;
            O.c cVar4 = f1751e;
            return new c(cVar2, cVar3, cVar4, cVar4);
        }

        public static c d(c cVar) {
            O.c cVar2 = f1751e;
            return new c(cVar2, cVar2, cVar.f1753b, cVar.f1754c);
        }

        public static c e(c cVar, View view) {
            return n.g(view) ? d(cVar) : c(cVar);
        }

        public static c f(c cVar) {
            O.c cVar2 = cVar.f1752a;
            O.c cVar3 = f1751e;
            return new c(cVar2, cVar3, cVar.f1753b, cVar3);
        }
    }

    public interface d {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z2);
    }

    public class e implements MaterialButton.a {
        public e() {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(MaterialButton materialButton, boolean z2) {
            MaterialButtonToggleGroup.this.invalidate();
        }

        public /* synthetic */ e(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    public MaterialButtonToggleGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6519s);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (k(i2)) {
                return i2;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (k(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if ((getChildAt(i3) instanceof MaterialButton) && k(i3)) {
                i2++;
            }
        }
        return i2;
    }

    public static void p(k.b bVar, c cVar) {
        if (cVar == null) {
            bVar.o(0.0f);
        } else {
            bVar.B(cVar.f1752a).t(cVar.f1755d).F(cVar.f1753b).x(cVar.f1754c);
        }
    }

    private void setGeneratedIdIfNeeded(@NonNull MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.generateViewId());
        }
    }

    private void setupButtonChild(@NonNull MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.f1740b);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i2, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        e(materialButton.getId(), materialButton.isChecked());
        k shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f1739a.add(new c(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
        materialButton.setEnabled(isEnabled());
        ViewCompat.setAccessibilityDelegate(materialButton, new b());
    }

    public void b(d dVar) {
        this.f1741c.add(dVar);
    }

    public final void c() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i2 = firstVisibleChildIndex + 1; i2 < getChildCount(); i2++) {
            MaterialButton materialButtonH = h(i2);
            int iMin = Math.min(materialButtonH.getStrokeWidth(), h(i2 - 1).getStrokeWidth());
            LinearLayout.LayoutParams layoutParamsD = d(materialButtonH);
            if (getOrientation() == 0) {
                MarginLayoutParamsCompat.setMarginEnd(layoutParamsD, 0);
                MarginLayoutParamsCompat.setMarginStart(layoutParamsD, -iMin);
                layoutParamsD.topMargin = 0;
            } else {
                layoutParamsD.bottomMargin = 0;
                layoutParamsD.topMargin = -iMin;
                MarginLayoutParamsCompat.setMarginStart(layoutParamsD, 0);
            }
            materialButtonH.setLayoutParams(layoutParamsD);
        }
        n(firstVisibleChildIndex);
    }

    public final LinearLayout.LayoutParams d(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        r();
        super.dispatchDraw(canvas);
    }

    public final void e(int i2, boolean z2) {
        if (i2 == -1) {
            Log.e("MButtonToggleGroup", "Button ID is not valid: " + i2);
            return;
        }
        HashSet hashSet = new HashSet(this.f1748j);
        if (z2 && !hashSet.contains(Integer.valueOf(i2))) {
            if (this.f1745g && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i2));
        } else {
            if (z2 || !hashSet.contains(Integer.valueOf(i2))) {
                return;
            }
            if (!this.f1746h || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i2));
            }
        }
        q(hashSet);
    }

    public void f() {
        q(new HashSet());
    }

    public final void g(int i2, boolean z2) {
        Iterator it = this.f1741c.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(this, i2, z2);
        }
    }

    @IdRes
    public int getCheckedButtonId() {
        if (!this.f1745g || this.f1748j.isEmpty()) {
            return -1;
        }
        return ((Integer) this.f1748j.iterator().next()).intValue();
    }

    @NonNull
    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int id = h(i2).getId();
            if (this.f1748j.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        Integer[] numArr = this.f1743e;
        if (numArr != null && i3 < numArr.length) {
            return numArr[i3].intValue();
        }
        Log.w("MButtonToggleGroup", "Child order wasn't updated");
        return i3;
    }

    public final MaterialButton h(int i2) {
        return (MaterialButton) getChildAt(i2);
    }

    public final int i(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == view) {
                return i2;
            }
            if ((getChildAt(i3) instanceof MaterialButton) && k(i3)) {
                i2++;
            }
        }
        return -1;
    }

    public final c j(int i2, int i3, int i4) {
        c cVar = (c) this.f1739a.get(i2);
        if (i3 == i4) {
            return cVar;
        }
        boolean z2 = getOrientation() == 0;
        if (i2 == i3) {
            return z2 ? c.e(cVar, this) : c.f(cVar);
        }
        if (i2 == i4) {
            return z2 ? c.b(cVar, this) : c.a(cVar);
        }
        return null;
    }

    public final boolean k(int i2) {
        return getChildAt(i2).getVisibility() != 8;
    }

    public boolean l() {
        return this.f1745g;
    }

    public void m(MaterialButton materialButton, boolean z2) {
        if (this.f1744f) {
            return;
        }
        e(materialButton.getId(), z2);
    }

    public final void n(int i2) {
        if (getChildCount() == 0 || i2 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) h(i2).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            MarginLayoutParamsCompat.setMarginEnd(layoutParams, 0);
            MarginLayoutParamsCompat.setMarginStart(layoutParams, 0);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
    }

    public final void o(int i2, boolean z2) {
        View viewFindViewById = findViewById(i2);
        if (viewFindViewById instanceof MaterialButton) {
            this.f1744f = true;
            ((MaterialButton) viewFindViewById).setChecked(z2);
            this.f1744f = false;
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.f1747i;
        if (i2 != -1) {
            q(Collections.singleton(Integer.valueOf(i2)));
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getVisibleButtonCount(), false, l() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        s();
        c();
        super.onMeasure(i2, i3);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f1739a.remove(iIndexOfChild);
        }
        s();
        c();
    }

    public final void q(Set set) {
        Set set2 = this.f1748j;
        this.f1748j = new HashSet(set);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int id = h(i2).getId();
            o(id, set.contains(Integer.valueOf(id)));
            if (set2.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                g(id, set.contains(Integer.valueOf(id)));
            }
        }
        invalidate();
    }

    public final void r() {
        TreeMap treeMap = new TreeMap(this.f1742d);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            treeMap.put(h(i2), Integer.valueOf(i2));
        }
        this.f1743e = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    public void s() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i2 = 0; i2 < childCount; i2++) {
            MaterialButton materialButtonH = h(i2);
            if (materialButtonH.getVisibility() != 8) {
                k.b bVarV = materialButtonH.getShapeAppearanceModel().v();
                p(bVarV, j(i2, firstVisibleChildIndex, lastVisibleChildIndex));
                materialButtonH.setShapeAppearanceModel(bVarV.m());
            }
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            h(i2).setEnabled(z2);
        }
    }

    public void setSelectionRequired(boolean z2) {
        this.f1746h = z2;
    }

    public void setSingleSelection(boolean z2) {
        if (this.f1745g != z2) {
            this.f1745g = z2;
            f();
        }
        t();
    }

    public final void t() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            h(i2).setA11yClassName((this.f1745g ? RadioButton.class : ToggleButton.class).getName());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f1738k;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f1739a = new ArrayList();
        this.f1740b = new e(this, null);
        this.f1741c = new LinkedHashSet();
        this.f1742d = new a();
        this.f1744f = false;
        this.f1748j = new HashSet();
        TypedArray typedArrayI = H.k.i(getContext(), attributeSet, j.v2, i2, i3, new int[0]);
        setSingleSelection(typedArrayI.getBoolean(j.z2, false));
        this.f1747i = typedArrayI.getResourceId(j.x2, -1);
        this.f1746h = typedArrayI.getBoolean(j.y2, false);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(typedArrayI.getBoolean(j.w2, true));
        typedArrayI.recycle();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public void setSingleSelection(@BoolRes int i2) {
        setSingleSelection(getResources().getBoolean(i2));
    }
}
