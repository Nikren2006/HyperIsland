package v1;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import w1.o;
import w1.p;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0170a f6959d = new C0170a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f6960a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x1.b f6961b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w1.e f6962c;

    /* JADX INFO: renamed from: v1.a$a, reason: collision with other inner class name */
    public static final class C0170a extends a {
        public /* synthetic */ C0170a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public C0170a() {
            super(new b(false, false, false, false, false, false, null, false, false, null, false, false, 4095, null), x1.c.a(), null);
        }
    }

    public /* synthetic */ a(b bVar, x1.b bVar2, DefaultConstructorMarker defaultConstructorMarker) {
        this(bVar, bVar2);
    }

    public final Object a(q1.a deserializer, String string) {
        n.g(deserializer, "deserializer");
        n.g(string, "string");
        o oVar = new o(string);
        Object objJ = new w1.n(this, p.OBJ, oVar, deserializer.getDescriptor()).j(deserializer);
        oVar.p();
        return objJ;
    }

    public final b b() {
        return this.f6960a;
    }

    public x1.b c() {
        return this.f6961b;
    }

    public final w1.e d() {
        return this.f6962c;
    }

    public a(b bVar, x1.b bVar2) {
        this.f6960a = bVar;
        this.f6961b = bVar2;
        this.f6962c = new w1.e();
    }
}
