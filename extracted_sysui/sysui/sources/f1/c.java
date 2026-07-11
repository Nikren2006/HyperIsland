package f1;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f4237a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f4238b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f4239c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f4240d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Charset f4241e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Charset f4242f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Charset f4243g;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        kotlin.jvm.internal.n.f(charsetForName, "forName(...)");
        f4238b = charsetForName;
        Charset charsetForName2 = Charset.forName("UTF-16");
        kotlin.jvm.internal.n.f(charsetForName2, "forName(...)");
        f4239c = charsetForName2;
        Charset charsetForName3 = Charset.forName("UTF-16BE");
        kotlin.jvm.internal.n.f(charsetForName3, "forName(...)");
        f4240d = charsetForName3;
        Charset charsetForName4 = Charset.forName("UTF-16LE");
        kotlin.jvm.internal.n.f(charsetForName4, "forName(...)");
        f4241e = charsetForName4;
        Charset charsetForName5 = Charset.forName("US-ASCII");
        kotlin.jvm.internal.n.f(charsetForName5, "forName(...)");
        f4242f = charsetForName5;
        Charset charsetForName6 = Charset.forName("ISO-8859-1");
        kotlin.jvm.internal.n.f(charsetForName6, "forName(...)");
        f4243g = charsetForName6;
    }
}
