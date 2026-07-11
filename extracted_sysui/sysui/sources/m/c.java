package m;

/* JADX INFO: loaded from: classes.dex */
public enum c {
    JSON(".json"),
    ZIP(".zip");


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5262a;

    c(String str) {
        this.f5262a = str;
    }

    public String a() {
        return ".temp" + this.f5262a;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f5262a;
    }
}
