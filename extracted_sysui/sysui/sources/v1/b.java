package v1;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f6963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6964b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f6965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f6966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f6967e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f6968f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f6969g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f6970h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f6971i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f6972j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f6973k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f6974l;

    public b(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String prettyPrintIndent, boolean z8, boolean z9, String classDiscriminator, boolean z10, boolean z11) {
        n.g(prettyPrintIndent, "prettyPrintIndent");
        n.g(classDiscriminator, "classDiscriminator");
        this.f6963a = z2;
        this.f6964b = z3;
        this.f6965c = z4;
        this.f6966d = z5;
        this.f6967e = z6;
        this.f6968f = z7;
        this.f6969g = prettyPrintIndent;
        this.f6970h = z8;
        this.f6971i = z9;
        this.f6972j = classDiscriminator;
        this.f6973k = z10;
        this.f6974l = z11;
    }

    public final boolean a() {
        return this.f6973k;
    }

    public final boolean b() {
        return this.f6966d;
    }

    public final boolean c() {
        return this.f6970h;
    }

    public final boolean d() {
        return this.f6968f;
    }

    public final boolean e() {
        return this.f6964b;
    }

    public final boolean f() {
        return this.f6974l;
    }

    public final boolean g() {
        return this.f6965c;
    }

    public String toString() {
        return "JsonConfiguration(encodeDefaults=" + this.f6963a + ", ignoreUnknownKeys=" + this.f6964b + ", isLenient=" + this.f6965c + ", allowStructuredMapKeys=" + this.f6966d + ", prettyPrint=" + this.f6967e + ", explicitNulls=" + this.f6968f + ", prettyPrintIndent='" + this.f6969g + "', coerceInputValues=" + this.f6970h + ", useArrayPolymorphism=" + this.f6971i + ", classDiscriminator='" + this.f6972j + "', allowSpecialFloatingPointValues=" + this.f6973k + ')';
    }

    public /* synthetic */ b(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str, boolean z8, boolean z9, String str2, boolean z10, boolean z11, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z2, (i2 & 2) != 0 ? false : z3, (i2 & 4) != 0 ? false : z4, (i2 & 8) != 0 ? false : z5, (i2 & 16) != 0 ? false : z6, (i2 & 32) != 0 ? true : z7, (i2 & 64) != 0 ? "    " : str, (i2 & 128) != 0 ? false : z8, (i2 & 256) != 0 ? false : z9, (i2 & 512) != 0 ? "type" : str2, (i2 & 1024) == 0 ? z10 : false, (i2 & 2048) == 0 ? z11 : true);
    }
}
