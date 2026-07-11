package O;

/* JADX INFO: loaded from: classes2.dex */
public class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f451a = -1.0f;

    @Override // O.d
    public void a(m mVar, float f2, float f3, float f4) {
        mVar.o(0.0f, f4 * f3, 180.0f, 180.0f - f2);
        double d2 = f4;
        double d3 = f3;
        mVar.m((float) (Math.sin(Math.toRadians(f2)) * d2 * d3), (float) (Math.sin(Math.toRadians(90.0f - f2)) * d2 * d3));
    }
}
