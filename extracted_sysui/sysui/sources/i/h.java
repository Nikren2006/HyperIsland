package i;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f4531c;

    public h(String str, float f2, float f3) {
        this.f4529a = str;
        this.f4531c = f3;
        this.f4530b = f2;
    }

    public boolean a(String str) {
        if (this.f4529a.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f4529a.endsWith("\r")) {
            String str2 = this.f4529a;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
