package com.google.android.flexbox;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.widget.CompoundButtonCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.android.flexbox.a f1578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean[] f1579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f1580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f1581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long[] f1582e;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List f1583a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1584b;

        public void a() {
            this.f1583a = null;
            this.f1584b = 0;
        }
    }

    public d(com.google.android.flexbox.a aVar) {
        this.f1578a = aVar;
    }

    public final int A(boolean z2) {
        return z2 ? this.f1578a.getPaddingBottom() : this.f1578a.getPaddingEnd();
    }

    public final int B(boolean z2) {
        return z2 ? this.f1578a.getPaddingEnd() : this.f1578a.getPaddingBottom();
    }

    public final int C(boolean z2) {
        return z2 ? this.f1578a.getPaddingTop() : this.f1578a.getPaddingStart();
    }

    public final int D(boolean z2) {
        return z2 ? this.f1578a.getPaddingStart() : this.f1578a.getPaddingTop();
    }

    public final int E(View view, boolean z2) {
        return z2 ? view.getMeasuredHeight() : view.getMeasuredWidth();
    }

    public final int F(View view, boolean z2) {
        return z2 ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    public final boolean G(int i2, int i3, c cVar) {
        return i2 == i3 - 1 && cVar.c() != 0;
    }

    public final boolean H(View view, int i2, int i3, int i4, int i5, b bVar, int i6, int i7, int i8) {
        if (this.f1578a.getFlexWrap() == 0) {
            return false;
        }
        if (bVar.i()) {
            return true;
        }
        if (i2 == 0) {
            return false;
        }
        int maxLine = this.f1578a.getMaxLine();
        if (maxLine != -1 && maxLine <= i8 + 1) {
            return false;
        }
        int decorationLengthMainAxis = this.f1578a.getDecorationLengthMainAxis(view, i6, i7);
        if (decorationLengthMainAxis > 0) {
            i5 += decorationLengthMainAxis;
        }
        return i3 < i4 + i5;
    }

    public void I(View view, c cVar, int i2, int i3, int i4, int i5) {
        b bVar = (b) view.getLayoutParams();
        int alignItems = this.f1578a.getAlignItems();
        if (bVar.a() != -1) {
            alignItems = bVar.a();
        }
        int i6 = cVar.f1566g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (this.f1578a.getFlexWrap() == 2) {
                    view.layout(i2, (i3 - i6) + view.getMeasuredHeight() + bVar.d(), i4, (i5 - i6) + view.getMeasuredHeight() + bVar.d());
                    return;
                } else {
                    int i7 = i3 + i6;
                    view.layout(i2, (i7 - view.getMeasuredHeight()) - bVar.l(), i4, i7 - bVar.l());
                    return;
                }
            }
            if (alignItems == 2) {
                int measuredHeight = (((i6 - view.getMeasuredHeight()) + bVar.d()) - bVar.l()) / 2;
                if (this.f1578a.getFlexWrap() != 2) {
                    int i8 = i3 + measuredHeight;
                    view.layout(i2, i8, i4, view.getMeasuredHeight() + i8);
                    return;
                } else {
                    int i9 = i3 - measuredHeight;
                    view.layout(i2, i9, i4, view.getMeasuredHeight() + i9);
                    return;
                }
            }
            if (alignItems == 3) {
                if (this.f1578a.getFlexWrap() != 2) {
                    int iMax = Math.max(cVar.f1571l - view.getBaseline(), bVar.d());
                    view.layout(i2, i3 + iMax, i4, i5 + iMax);
                    return;
                } else {
                    int iMax2 = Math.max((cVar.f1571l - view.getMeasuredHeight()) + view.getBaseline(), bVar.l());
                    view.layout(i2, i3 - iMax2, i4, i5 - iMax2);
                    return;
                }
            }
            if (alignItems != 4) {
                return;
            }
        }
        if (this.f1578a.getFlexWrap() != 2) {
            view.layout(i2, i3 + bVar.d(), i4, i5 + bVar.d());
        } else {
            view.layout(i2, i3 - bVar.l(), i4, i5 - bVar.l());
        }
    }

    public void J(View view, c cVar, boolean z2, int i2, int i3, int i4, int i5) {
        b bVar = (b) view.getLayoutParams();
        int alignItems = this.f1578a.getAlignItems();
        if (bVar.a() != -1) {
            alignItems = bVar.a();
        }
        int i6 = cVar.f1566g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (z2) {
                    view.layout((i2 - i6) + view.getMeasuredWidth() + bVar.m(), i3, (i4 - i6) + view.getMeasuredWidth() + bVar.m(), i5);
                    return;
                } else {
                    view.layout(((i2 + i6) - view.getMeasuredWidth()) - bVar.o(), i3, ((i4 + i6) - view.getMeasuredWidth()) - bVar.o(), i5);
                    return;
                }
            }
            if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i6 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (z2) {
                    view.layout(i2 - measuredWidth, i3, i4 - measuredWidth, i5);
                    return;
                } else {
                    view.layout(i2 + measuredWidth, i3, i4 + measuredWidth, i5);
                    return;
                }
            }
            if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (z2) {
            view.layout(i2 - bVar.o(), i3, i4 - bVar.o(), i5);
        } else {
            view.layout(i2 + bVar.m(), i3, i4 + bVar.m(), i5);
        }
    }

    public long K(int i2, int i3) {
        return (((long) i2) & 4294967295L) | (((long) i3) << 32);
    }

    public final void L(int i2, int i3, c cVar, int i4, int i5, boolean z2) {
        int iMax;
        int i6 = cVar.f1564e;
        float f2 = cVar.f1570k;
        float f3 = 0.0f;
        if (f2 <= 0.0f || i4 > i6) {
            return;
        }
        float f4 = (i6 - i4) / f2;
        cVar.f1564e = i5 + cVar.f1565f;
        if (!z2) {
            cVar.f1566g = Integer.MIN_VALUE;
        }
        int i7 = 0;
        boolean z3 = false;
        int i8 = 0;
        float f5 = 0.0f;
        while (i7 < cVar.f1567h) {
            int i9 = cVar.f1574o + i7;
            View reorderedFlexItemAt = this.f1578a.getReorderedFlexItemAt(i9);
            if (reorderedFlexItemAt != null && reorderedFlexItemAt.getVisibility() != 8) {
                b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.f1578a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr = this.f1582e;
                    if (jArr != null) {
                        measuredWidth = r(jArr[i9]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr2 = this.f1582e;
                    if (jArr2 != null) {
                        measuredHeight = q(jArr2[i9]);
                    }
                    if (!this.f1579b[i9] && bVar.b() > 0.0f) {
                        float fB = measuredWidth - (bVar.b() * f4);
                        if (i7 == cVar.f1567h - 1) {
                            fB += f5;
                            f5 = 0.0f;
                        }
                        int iRound = Math.round(fB);
                        if (iRound < bVar.c()) {
                            iRound = bVar.c();
                            this.f1579b[i9] = true;
                            cVar.f1570k -= bVar.b();
                            z3 = true;
                        } else {
                            f5 += fB - iRound;
                            double d2 = f5;
                            if (d2 > 1.0d) {
                                iRound++;
                                f5 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                iRound--;
                                f5 += 1.0f;
                            }
                        }
                        int iS = s(i3, bVar, cVar.f1572m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(iMakeMeasureSpec, iS);
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        Q(i9, iMakeMeasureSpec, iS, reorderedFlexItemAt);
                        this.f1578a.updateViewCache(i9, reorderedFlexItemAt);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i8, measuredHeight + bVar.d() + bVar.l() + this.f1578a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    cVar.f1564e += measuredWidth + bVar.m() + bVar.o();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr3 = this.f1582e;
                    if (jArr3 != null) {
                        measuredHeight3 = q(jArr3[i9]);
                    }
                    int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr4 = this.f1582e;
                    if (jArr4 != null) {
                        measuredWidth3 = r(jArr4[i9]);
                    }
                    if (!this.f1579b[i9] && bVar.b() > f3) {
                        float fB2 = measuredHeight3 - (bVar.b() * f4);
                        if (i7 == cVar.f1567h - 1) {
                            fB2 += f5;
                            f5 = f3;
                        }
                        int iRound2 = Math.round(fB2);
                        if (iRound2 < bVar.p()) {
                            iRound2 = bVar.p();
                            this.f1579b[i9] = true;
                            cVar.f1570k -= bVar.b();
                            z3 = true;
                        } else {
                            f5 += fB2 - iRound2;
                            double d3 = f5;
                            if (d3 > 1.0d) {
                                iRound2++;
                                f5 -= 1.0f;
                            } else if (d3 < -1.0d) {
                                iRound2--;
                                f5 += 1.0f;
                            }
                        }
                        int iT = t(i2, bVar, cVar.f1572m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(iT, iMakeMeasureSpec2);
                        measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        Q(i9, iT, iMakeMeasureSpec2, reorderedFlexItemAt);
                        this.f1578a.updateViewCache(i9, reorderedFlexItemAt);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i8, measuredWidth3 + bVar.m() + bVar.o() + this.f1578a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    cVar.f1564e += measuredHeight3 + bVar.d() + bVar.l();
                }
                cVar.f1566g = Math.max(cVar.f1566g, iMax);
                i8 = iMax;
            }
            i7++;
            f3 = 0.0f;
        }
        if (!z3 || i6 == cVar.f1564e) {
            return;
        }
        L(i2, i3, cVar, i4, i5, true);
    }

    public final void M(View view, int i2, int i3) {
        b bVar = (b) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i2 - bVar.m()) - bVar.o()) - this.f1578a.getDecorationLengthCrossAxis(view), bVar.c()), bVar.j());
        long[] jArr = this.f1582e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? q(jArr[i3]) : view.getMeasuredHeight(), BasicMeasure.EXACTLY);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, BasicMeasure.EXACTLY);
        view.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
        Q(i3, iMakeMeasureSpec2, iMakeMeasureSpec, view);
        this.f1578a.updateViewCache(i3, view);
    }

    public final void N(View view, int i2, int i3) {
        b bVar = (b) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i2 - bVar.d()) - bVar.l()) - this.f1578a.getDecorationLengthCrossAxis(view), bVar.p()), bVar.getMaxHeight());
        long[] jArr = this.f1582e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? r(jArr[i3]) : view.getMeasuredWidth(), BasicMeasure.EXACTLY);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, BasicMeasure.EXACTLY);
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        Q(i3, iMakeMeasureSpec, iMakeMeasureSpec2, view);
        this.f1578a.updateViewCache(i3, view);
    }

    public void O() {
        P(0);
    }

    public void P(int i2) {
        View reorderedFlexItemAt;
        if (i2 >= this.f1578a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f1578a.getFlexDirection();
        if (this.f1578a.getAlignItems() != 4) {
            for (c cVar : this.f1578a.getFlexLinesInternal()) {
                for (Integer num : cVar.f1573n) {
                    View reorderedFlexItemAt2 = this.f1578a.getReorderedFlexItemAt(num.intValue());
                    if (flexDirection == 0 || flexDirection == 1) {
                        N(reorderedFlexItemAt2, cVar.f1566g, num.intValue());
                    } else {
                        if (flexDirection != 2 && flexDirection != 3) {
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                        }
                        M(reorderedFlexItemAt2, cVar.f1566g, num.intValue());
                    }
                }
            }
            return;
        }
        int[] iArr = this.f1580c;
        List flexLinesInternal = this.f1578a.getFlexLinesInternal();
        int size = flexLinesInternal.size();
        for (int i3 = iArr != null ? iArr[i2] : 0; i3 < size; i3++) {
            c cVar2 = (c) flexLinesInternal.get(i3);
            int i4 = cVar2.f1567h;
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = cVar2.f1574o + i5;
                if (i5 < this.f1578a.getFlexItemCount() && (reorderedFlexItemAt = this.f1578a.getReorderedFlexItemAt(i6)) != null && reorderedFlexItemAt.getVisibility() != 8) {
                    b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                    if (bVar.a() == -1 || bVar.a() == 4) {
                        if (flexDirection == 0 || flexDirection == 1) {
                            N(reorderedFlexItemAt, cVar2.f1566g, i6);
                        } else {
                            if (flexDirection != 2 && flexDirection != 3) {
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                            M(reorderedFlexItemAt, cVar2.f1566g, i6);
                        }
                    }
                }
            }
        }
    }

    public final void Q(int i2, int i3, int i4, View view) {
        long[] jArr = this.f1581d;
        if (jArr != null) {
            jArr[i2] = K(i3, i4);
        }
        long[] jArr2 = this.f1582e;
        if (jArr2 != null) {
            jArr2[i2] = K(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public final void a(List list, c cVar, int i2, int i3) {
        cVar.f1572m = i3;
        this.f1578a.onNewFlexLineAdded(cVar);
        cVar.f1575p = i2;
        list.add(cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b(a aVar, int i2, int i3, int i4, int i5, int i6, List list) {
        int i7;
        a aVar2;
        int i8;
        int i9;
        int i10;
        List list2;
        int i11;
        View view;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20 = i2;
        int i21 = i3;
        int i22 = i6;
        boolean zIsMainAxisDirectionHorizontal = this.f1578a.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        List arrayList = list == null ? new ArrayList() : list;
        aVar.f1583a = arrayList;
        int i23 = i22 == -1 ? 1 : 0;
        int iD = D(zIsMainAxisDirectionHorizontal);
        int iB = B(zIsMainAxisDirectionHorizontal);
        int iC = C(zIsMainAxisDirectionHorizontal);
        int iA = A(zIsMainAxisDirectionHorizontal);
        c cVar = new c();
        int i24 = i5;
        cVar.f1574o = i24;
        int i25 = iB + iD;
        cVar.f1564e = i25;
        int flexItemCount = this.f1578a.getFlexItemCount();
        int i26 = i23;
        int i27 = Integer.MIN_VALUE;
        int i28 = 0;
        int iCombineMeasuredStates = 0;
        int i29 = 0;
        while (true) {
            if (i24 >= flexItemCount) {
                i7 = iCombineMeasuredStates;
                aVar2 = aVar;
                break;
            }
            View reorderedFlexItemAt = this.f1578a.getReorderedFlexItemAt(i24);
            if (reorderedFlexItemAt != null) {
                if (reorderedFlexItemAt.getVisibility() != 8) {
                    if (reorderedFlexItemAt instanceof CompoundButton) {
                        o((CompoundButton) reorderedFlexItemAt);
                    }
                    b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                    int i30 = flexItemCount;
                    if (bVar.a() == 4) {
                        cVar.f1573n.add(Integer.valueOf(i24));
                    }
                    int iZ = z(bVar, zIsMainAxisDirectionHorizontal);
                    if (bVar.h() != -1.0f && mode == 1073741824) {
                        iZ = Math.round(size * bVar.h());
                    }
                    if (zIsMainAxisDirectionHorizontal) {
                        int childWidthMeasureSpec = this.f1578a.getChildWidthMeasureSpec(i20, i25 + x(bVar, true) + v(bVar, true), iZ);
                        i8 = size;
                        i9 = mode;
                        int childHeightMeasureSpec = this.f1578a.getChildHeightMeasureSpec(i21, iC + iA + w(bVar, true) + u(bVar, true) + i28, y(bVar, true));
                        reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        Q(i24, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                        i10 = childWidthMeasureSpec;
                    } else {
                        i8 = size;
                        i9 = mode;
                        int childWidthMeasureSpec2 = this.f1578a.getChildWidthMeasureSpec(i21, iC + iA + w(bVar, false) + u(bVar, false) + i28, y(bVar, false));
                        int childHeightMeasureSpec2 = this.f1578a.getChildHeightMeasureSpec(i20, x(bVar, false) + i25 + v(bVar, false), iZ);
                        reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                        Q(i24, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                        i10 = childHeightMeasureSpec2;
                    }
                    this.f1578a.updateViewCache(i24, reorderedFlexItemAt);
                    g(reorderedFlexItemAt, i24);
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, reorderedFlexItemAt.getMeasuredState());
                    int i31 = i28;
                    int i32 = i25;
                    c cVar2 = cVar;
                    int i33 = i24;
                    list2 = arrayList;
                    int i34 = i10;
                    if (H(reorderedFlexItemAt, i9, i8, cVar.f1564e, v(bVar, zIsMainAxisDirectionHorizontal) + F(reorderedFlexItemAt, zIsMainAxisDirectionHorizontal) + x(bVar, zIsMainAxisDirectionHorizontal), bVar, i33, i29, arrayList.size())) {
                        i24 = i33;
                        if (cVar2.c() > 0) {
                            a(list2, cVar2, i24 > 0 ? i24 - 1 : 0, i31);
                            i28 = cVar2.f1566g + i31;
                        } else {
                            i28 = i31;
                        }
                        if (!zIsMainAxisDirectionHorizontal) {
                            i11 = i3;
                            view = reorderedFlexItemAt;
                            i12 = -1;
                            if (bVar.getWidth() == -1) {
                                com.google.android.flexbox.a aVar3 = this.f1578a;
                                view.measure(aVar3.getChildWidthMeasureSpec(i11, aVar3.getPaddingLeft() + this.f1578a.getPaddingRight() + bVar.m() + bVar.o() + i28, bVar.getWidth()), i34);
                                g(view, i24);
                            }
                        } else if (bVar.getHeight() == -1) {
                            com.google.android.flexbox.a aVar4 = this.f1578a;
                            i11 = i3;
                            i12 = -1;
                            view = reorderedFlexItemAt;
                            view.measure(i34, aVar4.getChildHeightMeasureSpec(i11, aVar4.getPaddingTop() + this.f1578a.getPaddingBottom() + bVar.d() + bVar.l() + i28, bVar.getHeight()));
                            g(view, i24);
                        } else {
                            i11 = i3;
                            view = reorderedFlexItemAt;
                            i12 = -1;
                        }
                        cVar = new c();
                        i14 = 1;
                        cVar.f1567h = 1;
                        i13 = i32;
                        cVar.f1564e = i13;
                        cVar.f1574o = i24;
                        i16 = Integer.MIN_VALUE;
                        i15 = 0;
                    } else {
                        i11 = i3;
                        i24 = i33;
                        view = reorderedFlexItemAt;
                        i12 = -1;
                        cVar = cVar2;
                        i13 = i32;
                        i14 = 1;
                        cVar.f1567h++;
                        i15 = i29 + 1;
                        i28 = i31;
                        i16 = i27;
                    }
                    cVar.f1576q = (cVar.f1576q ? 1 : 0) | (bVar.f() != 0.0f ? i14 : 0);
                    cVar.f1577r = (cVar.f1577r ? 1 : 0) | (bVar.b() != 0.0f ? i14 : 0);
                    int[] iArr = this.f1580c;
                    if (iArr != null) {
                        iArr[i24] = list2.size();
                    }
                    cVar.f1564e += F(view, zIsMainAxisDirectionHorizontal) + x(bVar, zIsMainAxisDirectionHorizontal) + v(bVar, zIsMainAxisDirectionHorizontal);
                    cVar.f1569j += bVar.f();
                    cVar.f1570k += bVar.b();
                    this.f1578a.onNewFlexItemAdded(view, i24, i15, cVar);
                    int iMax = Math.max(i16, E(view, zIsMainAxisDirectionHorizontal) + w(bVar, zIsMainAxisDirectionHorizontal) + u(bVar, zIsMainAxisDirectionHorizontal) + this.f1578a.getDecorationLengthCrossAxis(view));
                    cVar.f1566g = Math.max(cVar.f1566g, iMax);
                    if (zIsMainAxisDirectionHorizontal) {
                        if (this.f1578a.getFlexWrap() != 2) {
                            cVar.f1571l = Math.max(cVar.f1571l, view.getBaseline() + bVar.d());
                        } else {
                            cVar.f1571l = Math.max(cVar.f1571l, (view.getMeasuredHeight() - view.getBaseline()) + bVar.l());
                        }
                    }
                    i17 = i30;
                    if (G(i24, i17, cVar)) {
                        a(list2, cVar, i24, i28);
                        i28 += cVar.f1566g;
                    }
                    i18 = i6;
                    if (i18 == i12 || list2.size() <= 0 || ((c) list2.get(list2.size() - i14)).f1575p < i18 || i24 < i18 || i26 != 0) {
                        i19 = i4;
                    } else {
                        i28 = -cVar.a();
                        i19 = i4;
                        i26 = i14;
                    }
                    if (i28 > i19 && i26 != 0) {
                        aVar2 = aVar;
                        i7 = iCombineMeasuredStates;
                        break;
                    }
                    i29 = i15;
                    i27 = iMax;
                    i24++;
                    i20 = i2;
                    flexItemCount = i17;
                    i21 = i11;
                    i25 = i13;
                    arrayList = list2;
                    size = i8;
                    mode = i9;
                    i22 = i18;
                } else {
                    cVar.f1568i++;
                    cVar.f1567h++;
                    if (G(i24, flexItemCount, cVar)) {
                        a(arrayList, cVar, i24, i28);
                    }
                }
            } else if (G(i24, flexItemCount, cVar)) {
                a(arrayList, cVar, i24, i28);
            }
            i8 = size;
            i9 = mode;
            i11 = i21;
            i18 = i22;
            i13 = i25;
            list2 = arrayList;
            i17 = flexItemCount;
            i24++;
            i20 = i2;
            flexItemCount = i17;
            i21 = i11;
            i25 = i13;
            arrayList = list2;
            size = i8;
            mode = i9;
            i22 = i18;
        }
        aVar2.f1584b = i7;
    }

    public void c(a aVar, int i2, int i3, int i4, int i5, List list) {
        b(aVar, i2, i3, i4, i5, -1, list);
    }

    public void d(a aVar, int i2, int i3, int i4, int i5, List list) {
        b(aVar, i2, i3, i4, 0, i5, list);
    }

    public void e(a aVar, int i2, int i3, int i4, int i5, List list) {
        b(aVar, i3, i2, i4, i5, -1, list);
    }

    public void f(a aVar, int i2, int i3, int i4, int i5, List list) {
        b(aVar, i3, i2, i4, 0, i5, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.b r0 = (com.google.android.flexbox.b) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.c()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.c()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.j()
            if (r1 <= r3) goto L26
            int r1 = r0.j()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.p()
            if (r2 >= r5) goto L32
            int r2 = r0.p()
            goto L3e
        L32:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L3d
            int r2 = r0.getMaxHeight()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.Q(r8, r1, r0, r7)
            com.google.android.flexbox.a r6 = r6.f1578a
            r6.updateViewCache(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.d.g(android.view.View, int):void");
    }

    public void h(List list, int i2) {
        int i3 = this.f1580c[i2];
        if (i3 == -1) {
            i3 = 0;
        }
        for (int size = list.size() - 1; size >= i3; size--) {
            list.remove(size);
        }
        int[] iArr = this.f1580c;
        int length = iArr.length - 1;
        if (i2 > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i2, length, -1);
        }
        long[] jArr = this.f1581d;
        int length2 = jArr.length - 1;
        if (i2 > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i2, length2, 0L);
        }
    }

    public void i(int i2, int i3) {
        j(i2, i3, 0);
    }

    public void j(int i2, int i3, int i4) {
        int size;
        int paddingLeft;
        int paddingRight;
        k(this.f1578a.getFlexItemCount());
        if (i4 >= this.f1578a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f1578a.getFlexDirection();
        int flexDirection2 = this.f1578a.getFlexDirection();
        if (flexDirection2 == 0 || flexDirection2 == 1) {
            int mode = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            int largestMainSize = this.f1578a.getLargestMainSize();
            if (mode != 1073741824 && largestMainSize <= size) {
                size = largestMainSize;
            }
            paddingLeft = this.f1578a.getPaddingLeft();
            paddingRight = this.f1578a.getPaddingRight();
        } else {
            if (flexDirection2 != 2 && flexDirection2 != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            int mode2 = View.MeasureSpec.getMode(i3);
            size = View.MeasureSpec.getSize(i3);
            if (mode2 != 1073741824) {
                size = this.f1578a.getLargestMainSize();
            }
            paddingLeft = this.f1578a.getPaddingTop();
            paddingRight = this.f1578a.getPaddingBottom();
        }
        int i5 = paddingLeft + paddingRight;
        int[] iArr = this.f1580c;
        List flexLinesInternal = this.f1578a.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i6 = iArr != null ? iArr[i4] : 0; i6 < size2; i6++) {
            c cVar = (c) flexLinesInternal.get(i6);
            int i7 = cVar.f1564e;
            if (i7 < size && cVar.f1576q) {
                p(i2, i3, cVar, size, i5, false);
            } else if (i7 > size && cVar.f1577r) {
                L(i2, i3, cVar, size, i5, false);
            }
        }
    }

    public final void k(int i2) {
        boolean[] zArr = this.f1579b;
        if (zArr == null) {
            if (i2 < 10) {
                i2 = 10;
            }
            this.f1579b = new boolean[i2];
        } else {
            if (zArr.length >= i2) {
                Arrays.fill(zArr, false);
                return;
            }
            int length = zArr.length * 2;
            if (length >= i2) {
                i2 = length;
            }
            this.f1579b = new boolean[i2];
        }
    }

    public void l(int i2) {
        int[] iArr = this.f1580c;
        if (iArr == null) {
            if (i2 < 10) {
                i2 = 10;
            }
            this.f1580c = new int[i2];
        } else if (iArr.length < i2) {
            int length = iArr.length * 2;
            if (length >= i2) {
                i2 = length;
            }
            this.f1580c = Arrays.copyOf(iArr, i2);
        }
    }

    public void m(int i2) {
        long[] jArr = this.f1581d;
        if (jArr == null) {
            if (i2 < 10) {
                i2 = 10;
            }
            this.f1581d = new long[i2];
        } else if (jArr.length < i2) {
            int length = jArr.length * 2;
            if (length >= i2) {
                i2 = length;
            }
            this.f1581d = Arrays.copyOf(jArr, i2);
        }
    }

    public void n(int i2) {
        long[] jArr = this.f1582e;
        if (jArr == null) {
            if (i2 < 10) {
                i2 = 10;
            }
            this.f1582e = new long[i2];
        } else if (jArr.length < i2) {
            int length = jArr.length * 2;
            if (length >= i2) {
                i2 = length;
            }
            this.f1582e = Arrays.copyOf(jArr, i2);
        }
    }

    public final void o(CompoundButton compoundButton) {
        b bVar = (b) compoundButton.getLayoutParams();
        int iC = bVar.c();
        int iP = bVar.p();
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
        int minimumWidth = buttonDrawable == null ? 0 : buttonDrawable.getMinimumWidth();
        int minimumHeight = buttonDrawable != null ? buttonDrawable.getMinimumHeight() : 0;
        if (iC == -1) {
            iC = minimumWidth;
        }
        bVar.k(iC);
        if (iP == -1) {
            iP = minimumHeight;
        }
        bVar.e(iP);
    }

    public final void p(int i2, int i3, c cVar, int i4, int i5, boolean z2) {
        int i6;
        int i7;
        int iMax;
        double d2;
        double d3;
        float f2 = cVar.f1569j;
        float f3 = 0.0f;
        if (f2 <= 0.0f || i4 < (i6 = cVar.f1564e)) {
            return;
        }
        float f4 = (i4 - i6) / f2;
        cVar.f1564e = i5 + cVar.f1565f;
        if (!z2) {
            cVar.f1566g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z3 = false;
        int i9 = 0;
        float f5 = 0.0f;
        while (i8 < cVar.f1567h) {
            int i10 = cVar.f1574o + i8;
            View reorderedFlexItemAt = this.f1578a.getReorderedFlexItemAt(i10);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i7 = i6;
            } else {
                b bVar = (b) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.f1578a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i7 = i6;
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr = this.f1582e;
                    if (jArr != null) {
                        measuredWidth = r(jArr[i10]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr2 = this.f1582e;
                    if (jArr2 != null) {
                        measuredHeight = q(jArr2[i10]);
                    }
                    if (!this.f1579b[i10] && bVar.f() > 0.0f) {
                        float f6 = measuredWidth + (bVar.f() * f4);
                        if (i8 == cVar.f1567h - 1) {
                            f6 += f5;
                            f5 = 0.0f;
                        }
                        int iRound = Math.round(f6);
                        if (iRound > bVar.j()) {
                            iRound = bVar.j();
                            this.f1579b[i10] = true;
                            cVar.f1569j -= bVar.f();
                            z3 = true;
                        } else {
                            f5 += f6 - iRound;
                            double d4 = f5;
                            if (d4 > 1.0d) {
                                iRound++;
                                d2 = d4 - 1.0d;
                            } else if (d4 < -1.0d) {
                                iRound--;
                                d2 = d4 + 1.0d;
                            }
                            f5 = (float) d2;
                        }
                        int iS = s(i3, bVar, cVar.f1572m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(iMakeMeasureSpec, iS);
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        Q(i10, iMakeMeasureSpec, iS, reorderedFlexItemAt);
                        this.f1578a.updateViewCache(i10, reorderedFlexItemAt);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + bVar.d() + bVar.l() + this.f1578a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    cVar.f1564e += measuredWidth + bVar.m() + bVar.o();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr3 = this.f1582e;
                    if (jArr3 != null) {
                        measuredHeight3 = q(jArr3[i10]);
                    }
                    int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr4 = this.f1582e;
                    if (jArr4 != null) {
                        measuredWidth3 = r(jArr4[i10]);
                    }
                    if (this.f1579b[i10] || bVar.f() <= f3) {
                        i7 = i6;
                    } else {
                        float f7 = measuredHeight3 + (bVar.f() * f4);
                        if (i8 == cVar.f1567h - 1) {
                            f7 += f5;
                            f5 = f3;
                        }
                        int iRound2 = Math.round(f7);
                        if (iRound2 > bVar.getMaxHeight()) {
                            iRound2 = bVar.getMaxHeight();
                            this.f1579b[i10] = true;
                            cVar.f1569j -= bVar.f();
                            i7 = i6;
                            z3 = true;
                        } else {
                            f5 += f7 - iRound2;
                            i7 = i6;
                            double d5 = f5;
                            if (d5 > 1.0d) {
                                iRound2++;
                                d3 = d5 - 1.0d;
                            } else if (d5 < -1.0d) {
                                iRound2--;
                                d3 = d5 + 1.0d;
                            }
                            f5 = (float) d3;
                        }
                        int iT = t(i2, bVar, cVar.f1572m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, BasicMeasure.EXACTLY);
                        reorderedFlexItemAt.measure(iT, iMakeMeasureSpec2);
                        measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        Q(i10, iT, iMakeMeasureSpec2, reorderedFlexItemAt);
                        this.f1578a.updateViewCache(i10, reorderedFlexItemAt);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + bVar.m() + bVar.o() + this.f1578a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    cVar.f1564e += measuredHeight3 + bVar.d() + bVar.l();
                }
                cVar.f1566g = Math.max(cVar.f1566g, iMax);
                i9 = iMax;
            }
            i8++;
            i6 = i7;
            f3 = 0.0f;
        }
        int i11 = i6;
        if (!z3 || i11 == cVar.f1564e) {
            return;
        }
        p(i2, i3, cVar, i4, i5, true);
    }

    public int q(long j2) {
        return (int) (j2 >> 32);
    }

    public int r(long j2) {
        return (int) j2;
    }

    public final int s(int i2, b bVar, int i3) {
        com.google.android.flexbox.a aVar = this.f1578a;
        int childHeightMeasureSpec = aVar.getChildHeightMeasureSpec(i2, aVar.getPaddingTop() + this.f1578a.getPaddingBottom() + bVar.d() + bVar.l() + i3, bVar.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        return size > bVar.getMaxHeight() ? View.MeasureSpec.makeMeasureSpec(bVar.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : size < bVar.p() ? View.MeasureSpec.makeMeasureSpec(bVar.p(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : childHeightMeasureSpec;
    }

    public final int t(int i2, b bVar, int i3) {
        com.google.android.flexbox.a aVar = this.f1578a;
        int childWidthMeasureSpec = aVar.getChildWidthMeasureSpec(i2, aVar.getPaddingLeft() + this.f1578a.getPaddingRight() + bVar.m() + bVar.o() + i3, bVar.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        return size > bVar.j() ? View.MeasureSpec.makeMeasureSpec(bVar.j(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : size < bVar.c() ? View.MeasureSpec.makeMeasureSpec(bVar.c(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : childWidthMeasureSpec;
    }

    public final int u(b bVar, boolean z2) {
        return z2 ? bVar.l() : bVar.o();
    }

    public final int v(b bVar, boolean z2) {
        return z2 ? bVar.o() : bVar.l();
    }

    public final int w(b bVar, boolean z2) {
        return z2 ? bVar.d() : bVar.m();
    }

    public final int x(b bVar, boolean z2) {
        return z2 ? bVar.m() : bVar.d();
    }

    public final int y(b bVar, boolean z2) {
        return z2 ? bVar.getHeight() : bVar.getWidth();
    }

    public final int z(b bVar, boolean z2) {
        return z2 ? bVar.getWidth() : bVar.getHeight();
    }
}
