package i;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final char f4519b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f4520c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f4521d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f4522e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4523f;

    public d(List list, char c2, double d2, double d3, String str, String str2) {
        this.f4518a = list;
        this.f4519b = c2;
        this.f4520c = d2;
        this.f4521d = d3;
        this.f4522e = str;
        this.f4523f = str2;
    }

    public static int c(char c2, String str, String str2) {
        return (((c2 * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List a() {
        return this.f4518a;
    }

    public double b() {
        return this.f4521d;
    }

    public int hashCode() {
        return c(this.f4519b, this.f4523f, this.f4522e);
    }
}
