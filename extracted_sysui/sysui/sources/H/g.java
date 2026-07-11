package H;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int f258n = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f259a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextPaint f260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f261c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f263e;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f270l;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f262d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Layout.Alignment f264f = Layout.Alignment.ALIGN_NORMAL;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f265g = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f266h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f267i = 1.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f268j = f258n;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f269k = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TextUtils.TruncateAt f271m = null;

    public static class a extends Exception {
    }

    public g(CharSequence charSequence, TextPaint textPaint, int i2) {
        this.f259a = charSequence;
        this.f260b = textPaint;
        this.f261c = i2;
        this.f263e = charSequence.length();
    }

    public static g b(CharSequence charSequence, TextPaint textPaint, int i2) {
        return new g(charSequence, textPaint, i2);
    }

    public StaticLayout a() {
        if (this.f259a == null) {
            this.f259a = "";
        }
        int iMax = Math.max(0, this.f261c);
        CharSequence charSequenceEllipsize = this.f259a;
        if (this.f265g == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f260b, iMax, this.f271m);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.f263e);
        this.f263e = iMin;
        if (this.f270l && this.f265g == 1) {
            this.f264f = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, this.f262d, iMin, this.f260b, iMax);
        builderObtain.setAlignment(this.f264f);
        builderObtain.setIncludePad(this.f269k);
        builderObtain.setTextDirection(this.f270l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f271m;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.f265g);
        float f2 = this.f266h;
        if (f2 != 0.0f || this.f267i != 1.0f) {
            builderObtain.setLineSpacing(f2, this.f267i);
        }
        if (this.f265g > 1) {
            builderObtain.setHyphenationFrequency(this.f268j);
        }
        return builderObtain.build();
    }

    public g c(Layout.Alignment alignment) {
        this.f264f = alignment;
        return this;
    }

    public g d(TextUtils.TruncateAt truncateAt) {
        this.f271m = truncateAt;
        return this;
    }

    public g e(int i2) {
        this.f268j = i2;
        return this;
    }

    public g f(boolean z2) {
        this.f269k = z2;
        return this;
    }

    public g g(boolean z2) {
        this.f270l = z2;
        return this;
    }

    public g h(float f2, float f3) {
        this.f266h = f2;
        this.f267i = f3;
        return this;
    }

    public g i(int i2) {
        this.f265g = i2;
        return this;
    }

    public g j(h hVar) {
        return this;
    }
}
