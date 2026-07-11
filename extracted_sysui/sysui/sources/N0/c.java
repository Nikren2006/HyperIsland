package N0;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements L0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f441a = new c();

    @Override // L0.d
    public L0.g getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // L0.d
    public void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
