package miui.systemui.devicecontrols.controller;

/* JADX INFO: loaded from: classes3.dex */
public final class SeedResponse {
    private final boolean accepted;
    private final String packageName;

    public SeedResponse(String packageName, boolean z2) {
        kotlin.jvm.internal.n.g(packageName, "packageName");
        this.packageName = packageName;
        this.accepted = z2;
    }

    public static /* synthetic */ SeedResponse copy$default(SeedResponse seedResponse, String str, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = seedResponse.packageName;
        }
        if ((i2 & 2) != 0) {
            z2 = seedResponse.accepted;
        }
        return seedResponse.copy(str, z2);
    }

    public final String component1() {
        return this.packageName;
    }

    public final boolean component2() {
        return this.accepted;
    }

    public final SeedResponse copy(String packageName, boolean z2) {
        kotlin.jvm.internal.n.g(packageName, "packageName");
        return new SeedResponse(packageName, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeedResponse)) {
            return false;
        }
        SeedResponse seedResponse = (SeedResponse) obj;
        return kotlin.jvm.internal.n.c(this.packageName, seedResponse.packageName) && this.accepted == seedResponse.accepted;
    }

    public final boolean getAccepted() {
        return this.accepted;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + Boolean.hashCode(this.accepted);
    }

    public String toString() {
        return "SeedResponse(packageName=" + this.packageName + ", accepted=" + this.accepted + ")";
    }
}
