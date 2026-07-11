package g1;

import L0.g;
import java.io.Closeable;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: g1.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0360b0 extends B implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f4412a = new a(null);

    /* JADX INFO: renamed from: g1.b0$a */
    public static final class a extends L0.b {

        /* JADX INFO: renamed from: g1.b0$a$a, reason: collision with other inner class name */
        public static final class C0083a extends kotlin.jvm.internal.o implements Function1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0083a f4413a = new C0083a();

            public C0083a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public final AbstractC0360b0 invoke(g.b bVar) {
                if (bVar instanceof AbstractC0360b0) {
                    return (AbstractC0360b0) bVar;
                }
                return null;
            }
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
            super(B.Key, C0083a.f4413a);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();
}
