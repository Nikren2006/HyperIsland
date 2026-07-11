package s1;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    public static final class a extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6478a = new a();

        public a() {
            super(null);
        }
    }

    public static final class b extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6479a = new b();

        public b() {
            super(null);
        }
    }

    public /* synthetic */ e(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        String strC = z.b(getClass()).c();
        n.d(strC);
        return strC;
    }

    public e() {
    }
}
