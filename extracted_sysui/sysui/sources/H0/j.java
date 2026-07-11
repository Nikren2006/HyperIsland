package H0;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f299a = new a(null);

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public static final class b implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f300a;

        public b(Throwable exception) {
            kotlin.jvm.internal.n.g(exception, "exception");
            this.f300a = exception;
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && kotlin.jvm.internal.n.c(this.f300a, ((b) obj).f300a);
        }

        public int hashCode() {
            return this.f300a.hashCode();
        }

        public String toString() {
            return "Failure(" + this.f300a + ')';
        }
    }

    public static Object a(Object obj) {
        return obj;
    }

    public static final Throwable b(Object obj) {
        if (obj instanceof b) {
            return ((b) obj).f300a;
        }
        return null;
    }

    public static final boolean c(Object obj) {
        return obj instanceof b;
    }
}
