package g0;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f4351c = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f4352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4353b;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final f a(float f2) {
            double d2 = f2;
            return new f((float) Math.sin(d2), (float) Math.cos(d2), null);
        }

        public a() {
        }
    }

    public /* synthetic */ f(float f2, float f3, DefaultConstructorMarker defaultConstructorMarker) {
        this(f2, f3);
    }

    public final float a() {
        return this.f4352a;
    }

    public final float b() {
        return this.f4353b;
    }

    public f(float f2, float f3) {
        this.f4352a = f2;
        this.f4353b = f3;
    }
}
