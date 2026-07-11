package l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.collection.LongSparseArray;
import com.miui.maml.elements.MusicLyricParser;
import d.C0307h;
import d.F;
import d.K;
import g.AbstractC0355a;
import g.o;
import g.q;
import i.b;
import j.C0408a;
import j.C0409b;
import j.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import k.p;

/* JADX INFO: loaded from: classes.dex */
public class i extends AbstractC0432b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final StringBuilder f5156D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final RectF f5157E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Matrix f5158F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Paint f5159G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Paint f5160H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final Map f5161I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final LongSparseArray f5162J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final o f5163K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final F f5164L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final C0307h f5165M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public AbstractC0355a f5166N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public AbstractC0355a f5167O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public AbstractC0355a f5168P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public AbstractC0355a f5169Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public AbstractC0355a f5170R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public AbstractC0355a f5171S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public AbstractC0355a f5172T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public AbstractC0355a f5173U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public AbstractC0355a f5174V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public AbstractC0355a f5175W;

    public class a extends Paint {
        public a(int i2) {
            super(i2);
            setStyle(Paint.Style.FILL);
        }
    }

    public class b extends Paint {
        public b(int i2) {
            super(i2);
            setStyle(Paint.Style.STROKE);
        }
    }

    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5178a;

        static {
            int[] iArr = new int[b.a.values().length];
            f5178a = iArr;
            try {
                iArr[b.a.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5178a[b.a.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5178a[b.a.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public i(F f2, C0435e c0435e) {
        C0409b c0409b;
        C0409b c0409b2;
        C0408a c0408a;
        C0408a c0408a2;
        super(f2, c0435e);
        this.f5156D = new StringBuilder(2);
        this.f5157E = new RectF();
        this.f5158F = new Matrix();
        this.f5159G = new a(1);
        this.f5160H = new b(1);
        this.f5161I = new HashMap();
        this.f5162J = new LongSparseArray();
        this.f5164L = f2;
        this.f5165M = c0435e.b();
        o oVarA = c0435e.s().a();
        this.f5163K = oVarA;
        oVarA.a(this);
        j(oVarA);
        k kVarT = c0435e.t();
        if (kVarT != null && (c0408a2 = kVarT.f4622a) != null) {
            AbstractC0355a abstractC0355aA = c0408a2.a();
            this.f5166N = abstractC0355aA;
            abstractC0355aA.a(this);
            j(this.f5166N);
        }
        if (kVarT != null && (c0408a = kVarT.f4623b) != null) {
            AbstractC0355a abstractC0355aA2 = c0408a.a();
            this.f5168P = abstractC0355aA2;
            abstractC0355aA2.a(this);
            j(this.f5168P);
        }
        if (kVarT != null && (c0409b2 = kVarT.f4624c) != null) {
            AbstractC0355a abstractC0355aA3 = c0409b2.a();
            this.f5170R = abstractC0355aA3;
            abstractC0355aA3.a(this);
            j(this.f5170R);
        }
        if (kVarT == null || (c0409b = kVarT.f4625d) == null) {
            return;
        }
        AbstractC0355a abstractC0355aA4 = c0409b.a();
        this.f5172T = abstractC0355aA4;
        abstractC0355aA4.a(this);
        j(this.f5172T);
    }

    public final void P(b.a aVar, Canvas canvas, float f2) {
        int i2 = c.f5178a[aVar.ordinal()];
        if (i2 == 2) {
            canvas.translate(-f2, 0.0f);
        } else {
            if (i2 != 3) {
                return;
            }
            canvas.translate((-f2) / 2.0f, 0.0f);
        }
    }

    public final String Q(String str, int i2) {
        int iCodePointAt = str.codePointAt(i2);
        int iCharCount = Character.charCount(iCodePointAt) + i2;
        while (iCharCount < str.length()) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (!d0(iCodePointAt2)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt2);
            iCodePointAt = (iCodePointAt * 31) + iCodePointAt2;
        }
        long j2 = iCodePointAt;
        if (this.f5162J.containsKey(j2)) {
            return (String) this.f5162J.get(j2);
        }
        this.f5156D.setLength(0);
        while (i2 < iCharCount) {
            int iCodePointAt3 = str.codePointAt(i2);
            this.f5156D.appendCodePoint(iCodePointAt3);
            i2 += Character.charCount(iCodePointAt3);
        }
        String string = this.f5156D.toString();
        this.f5162J.put(j2, string);
        return string;
    }

    public final void R(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    public final void S(i.d dVar, Matrix matrix, float f2, i.b bVar, Canvas canvas) {
        List listZ = Z(dVar);
        for (int i2 = 0; i2 < listZ.size(); i2++) {
            Path pathD = ((f.d) listZ.get(i2)).d();
            pathD.computeBounds(this.f5157E, false);
            this.f5158F.set(matrix);
            this.f5158F.preTranslate(0.0f, (-bVar.f4504g) * p.h.e());
            this.f5158F.preScale(f2, f2);
            pathD.transform(this.f5158F);
            if (bVar.f4508k) {
                V(pathD, this.f5159G, canvas);
                V(pathD, this.f5160H, canvas);
            } else {
                V(pathD, this.f5160H, canvas);
                V(pathD, this.f5159G, canvas);
            }
        }
    }

    public final void T(String str, i.b bVar, Canvas canvas) {
        if (bVar.f4508k) {
            R(str, this.f5159G, canvas);
            R(str, this.f5160H, canvas);
        } else {
            R(str, this.f5160H, canvas);
            R(str, this.f5159G, canvas);
        }
    }

    public final void U(String str, i.b bVar, Canvas canvas, float f2) {
        int length = 0;
        while (length < str.length()) {
            String strQ = Q(str, length);
            length += strQ.length();
            T(strQ, bVar, canvas);
            canvas.translate(this.f5159G.measureText(strQ) + f2, 0.0f);
        }
    }

    public final void V(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    public final void W(String str, i.b bVar, Matrix matrix, i.c cVar, Canvas canvas, float f2, float f3) {
        float fFloatValue;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i.d dVar = (i.d) this.f5165M.c().get(i.d.c(str.charAt(i2), cVar.a(), cVar.c()));
            if (dVar != null) {
                S(dVar, matrix, f3, bVar, canvas);
                float fB = ((float) dVar.b()) * f3 * p.h.e() * f2;
                float f4 = bVar.f4502e / 10.0f;
                AbstractC0355a abstractC0355a = this.f5173U;
                if (abstractC0355a != null) {
                    fFloatValue = ((Float) abstractC0355a.h()).floatValue();
                } else {
                    AbstractC0355a abstractC0355a2 = this.f5172T;
                    if (abstractC0355a2 != null) {
                        fFloatValue = ((Float) abstractC0355a2.h()).floatValue();
                    }
                    canvas.translate(fB + (f4 * f2), 0.0f);
                }
                f4 += fFloatValue;
                canvas.translate(fB + (f4 * f2), 0.0f);
            }
        }
    }

    public final void X(i.b bVar, Matrix matrix, i.c cVar, Canvas canvas) {
        AbstractC0355a abstractC0355a = this.f5174V;
        float fFloatValue = (abstractC0355a != null ? ((Float) abstractC0355a.h()).floatValue() : bVar.f4500c) / 100.0f;
        float fG = p.h.g(matrix);
        String str = bVar.f4498a;
        float fE = bVar.f4503f * p.h.e();
        List listB0 = b0(str);
        int size = listB0.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = (String) listB0.get(i2);
            float fA0 = a0(str2, cVar, fFloatValue, fG);
            canvas.save();
            P(bVar.f4501d, canvas, fA0);
            canvas.translate(0.0f, (i2 * fE) - (((size - 1) * fE) / 2.0f));
            W(str2, bVar, matrix, cVar, canvas, fG, fFloatValue);
            canvas.restore();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0084 A[LOOP:0: B:17:0x0082->B:18:0x0084, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Y(i.b r8, i.c r9, android.graphics.Canvas r10) {
        /*
            r7 = this;
            android.graphics.Typeface r9 = r7.c0(r9)
            if (r9 != 0) goto L7
            return
        L7:
            java.lang.String r0 = r8.f4498a
            d.F r1 = r7.f5164L
            r1.b0()
            android.graphics.Paint r1 = r7.f5159G
            r1.setTypeface(r9)
            g.a r9 = r7.f5174V
            if (r9 == 0) goto L22
            java.lang.Object r9 = r9.h()
            java.lang.Float r9 = (java.lang.Float) r9
            float r9 = r9.floatValue()
            goto L24
        L22:
            float r9 = r8.f4500c
        L24:
            android.graphics.Paint r1 = r7.f5159G
            float r2 = p.h.e()
            float r2 = r2 * r9
            r1.setTextSize(r2)
            android.graphics.Paint r1 = r7.f5160H
            android.graphics.Paint r2 = r7.f5159G
            android.graphics.Typeface r2 = r2.getTypeface()
            r1.setTypeface(r2)
            android.graphics.Paint r1 = r7.f5160H
            android.graphics.Paint r2 = r7.f5159G
            float r2 = r2.getTextSize()
            r1.setTextSize(r2)
            float r1 = r8.f4503f
            float r2 = p.h.e()
            float r1 = r1 * r2
            int r2 = r8.f4502e
            float r2 = (float) r2
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r3
            g.a r3 = r7.f5173U
            if (r3 == 0) goto L61
            java.lang.Object r3 = r3.h()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
        L5f:
            float r2 = r2 + r3
            goto L70
        L61:
            g.a r3 = r7.f5172T
            if (r3 == 0) goto L70
            java.lang.Object r3 = r3.h()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            goto L5f
        L70:
            float r3 = p.h.e()
            float r2 = r2 * r3
            float r2 = r2 * r9
            r9 = 1120403456(0x42c80000, float:100.0)
            float r2 = r2 / r9
            java.util.List r9 = r7.b0(r0)
            int r0 = r9.size()
            r3 = 0
        L82:
            if (r3 >= r0) goto Lb8
            java.lang.Object r4 = r9.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            android.graphics.Paint r5 = r7.f5160H
            float r5 = r5.measureText(r4)
            int r6 = r4.length()
            int r6 = r6 + (-1)
            float r6 = (float) r6
            float r6 = r6 * r2
            float r5 = r5 + r6
            r10.save()
            i.b$a r6 = r8.f4501d
            r7.P(r6, r10, r5)
            int r5 = r0 + (-1)
            float r5 = (float) r5
            float r5 = r5 * r1
            r6 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r6
            float r6 = (float) r3
            float r6 = r6 * r1
            float r6 = r6 - r5
            r5 = 0
            r10.translate(r5, r6)
            r7.U(r4, r8, r10, r2)
            r10.restore()
            int r3 = r3 + 1
            goto L82
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: l.i.Y(i.b, i.c, android.graphics.Canvas):void");
    }

    public final List Z(i.d dVar) {
        if (this.f5161I.containsKey(dVar)) {
            return (List) this.f5161I.get(dVar);
        }
        List listA = dVar.a();
        int size = listA.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new f.d(this.f5164L, this, (p) listA.get(i2)));
        }
        this.f5161I.put(dVar, arrayList);
        return arrayList;
    }

    public final float a0(String str, i.c cVar, float f2, float f3) {
        float fB = 0.0f;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i.d dVar = (i.d) this.f5165M.c().get(i.d.c(str.charAt(i2), cVar.a(), cVar.c()));
            if (dVar != null) {
                fB = (float) (((double) fB) + (dVar.b() * ((double) f2) * ((double) p.h.e()) * ((double) f3)));
            }
        }
        return fB;
    }

    public final List b0(String str) {
        return Arrays.asList(str.replaceAll(MusicLyricParser.CRLF, "\r").replaceAll("\n", "\r").split("\r"));
    }

    public final Typeface c0(i.c cVar) {
        Typeface typeface;
        AbstractC0355a abstractC0355a = this.f5175W;
        if (abstractC0355a != null && (typeface = (Typeface) abstractC0355a.h()) != null) {
            return typeface;
        }
        Typeface typefaceC0 = this.f5164L.c0(cVar.a(), cVar.c());
        return typefaceC0 != null ? typefaceC0 : cVar.d();
    }

    public final boolean d0(int i2) {
        return Character.getType(i2) == 16 || Character.getType(i2) == 27 || Character.getType(i2) == 6 || Character.getType(i2) == 28 || Character.getType(i2) == 8 || Character.getType(i2) == 19;
    }

    @Override // l.AbstractC0432b, i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        super.e(obj, cVar);
        if (obj == K.f3787a) {
            AbstractC0355a abstractC0355a = this.f5167O;
            if (abstractC0355a != null) {
                H(abstractC0355a);
            }
            if (cVar == null) {
                this.f5167O = null;
                return;
            }
            q qVar = new q(cVar);
            this.f5167O = qVar;
            qVar.a(this);
            j(this.f5167O);
            return;
        }
        if (obj == K.f3788b) {
            AbstractC0355a abstractC0355a2 = this.f5169Q;
            if (abstractC0355a2 != null) {
                H(abstractC0355a2);
            }
            if (cVar == null) {
                this.f5169Q = null;
                return;
            }
            q qVar2 = new q(cVar);
            this.f5169Q = qVar2;
            qVar2.a(this);
            j(this.f5169Q);
            return;
        }
        if (obj == K.f3805s) {
            AbstractC0355a abstractC0355a3 = this.f5171S;
            if (abstractC0355a3 != null) {
                H(abstractC0355a3);
            }
            if (cVar == null) {
                this.f5171S = null;
                return;
            }
            q qVar3 = new q(cVar);
            this.f5171S = qVar3;
            qVar3.a(this);
            j(this.f5171S);
            return;
        }
        if (obj == K.f3806t) {
            AbstractC0355a abstractC0355a4 = this.f5173U;
            if (abstractC0355a4 != null) {
                H(abstractC0355a4);
            }
            if (cVar == null) {
                this.f5173U = null;
                return;
            }
            q qVar4 = new q(cVar);
            this.f5173U = qVar4;
            qVar4.a(this);
            j(this.f5173U);
            return;
        }
        if (obj == K.f3777F) {
            AbstractC0355a abstractC0355a5 = this.f5174V;
            if (abstractC0355a5 != null) {
                H(abstractC0355a5);
            }
            if (cVar == null) {
                this.f5174V = null;
                return;
            }
            q qVar5 = new q(cVar);
            this.f5174V = qVar5;
            qVar5.a(this);
            j(this.f5174V);
            return;
        }
        if (obj != K.f3784M) {
            if (obj == K.f3786O) {
                this.f5163K.q(cVar);
                return;
            }
            return;
        }
        AbstractC0355a abstractC0355a6 = this.f5175W;
        if (abstractC0355a6 != null) {
            H(abstractC0355a6);
        }
        if (cVar == null) {
            this.f5175W = null;
            return;
        }
        q qVar6 = new q(cVar);
        this.f5175W = qVar6;
        qVar6.a(this);
        j(this.f5175W);
    }

    @Override // l.AbstractC0432b, f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        super.f(rectF, matrix, z2);
        rectF.set(0.0f, 0.0f, this.f5165M.b().width(), this.f5165M.b().height());
    }

    @Override // l.AbstractC0432b
    public void u(Canvas canvas, Matrix matrix, int i2) {
        canvas.save();
        if (!this.f5164L.p1()) {
            canvas.concat(matrix);
        }
        i.b bVar = (i.b) this.f5163K.h();
        i.c cVar = (i.c) this.f5165M.g().get(bVar.f4499b);
        if (cVar == null) {
            canvas.restore();
            return;
        }
        AbstractC0355a abstractC0355a = this.f5167O;
        if (abstractC0355a != null) {
            this.f5159G.setColor(((Integer) abstractC0355a.h()).intValue());
        } else {
            AbstractC0355a abstractC0355a2 = this.f5166N;
            if (abstractC0355a2 != null) {
                this.f5159G.setColor(((Integer) abstractC0355a2.h()).intValue());
            } else {
                this.f5159G.setColor(bVar.f4505h);
            }
        }
        AbstractC0355a abstractC0355a3 = this.f5169Q;
        if (abstractC0355a3 != null) {
            this.f5160H.setColor(((Integer) abstractC0355a3.h()).intValue());
        } else {
            AbstractC0355a abstractC0355a4 = this.f5168P;
            if (abstractC0355a4 != null) {
                this.f5160H.setColor(((Integer) abstractC0355a4.h()).intValue());
            } else {
                this.f5160H.setColor(bVar.f4506i);
            }
        }
        int iIntValue = ((this.f5089x.h() == null ? 100 : ((Integer) this.f5089x.h().h()).intValue()) * 255) / 100;
        this.f5159G.setAlpha(iIntValue);
        this.f5160H.setAlpha(iIntValue);
        AbstractC0355a abstractC0355a5 = this.f5171S;
        if (abstractC0355a5 != null) {
            this.f5160H.setStrokeWidth(((Float) abstractC0355a5.h()).floatValue());
        } else {
            AbstractC0355a abstractC0355a6 = this.f5170R;
            if (abstractC0355a6 != null) {
                this.f5160H.setStrokeWidth(((Float) abstractC0355a6.h()).floatValue());
            } else {
                this.f5160H.setStrokeWidth(bVar.f4507j * p.h.e() * p.h.g(matrix));
            }
        }
        if (this.f5164L.p1()) {
            X(bVar, matrix, cVar, canvas);
        } else {
            Y(bVar, cVar, canvas);
        }
        canvas.restore();
    }
}
