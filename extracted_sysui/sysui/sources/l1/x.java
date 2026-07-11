package l1;

import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class x {

    public static final class a extends kotlin.jvm.internal.o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Function1 f5254a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f5255b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ L0.g f5256c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Function1 function1, Object obj, L0.g gVar) {
            super(1);
            this.f5254a = function1;
            this.f5255b = obj;
            this.f5256c = gVar;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
            invoke((Throwable) obj);
            return H0.s.f314a;
        }

        public final void invoke(Throwable th) throws IllegalAccessException, InvocationTargetException {
            x.b(this.f5254a, this.f5255b, this.f5256c);
        }
    }

    public static final Function1 a(Function1 function1, Object obj, L0.g gVar) {
        return new a(function1, obj, gVar);
    }

    public static final void b(Function1 function1, Object obj, L0.g gVar) throws IllegalAccessException, InvocationTargetException {
        O oC = c(function1, obj, null);
        if (oC != null) {
            g1.D.a(gVar, oC);
        }
    }

    public static final O c(Function1 function1, Object obj, O o2) throws IllegalAccessException, InvocationTargetException {
        try {
            function1.invoke(obj);
        } catch (Throwable th) {
            if (o2 == null || o2.getCause() == th) {
                return new O("Exception in undelivered element handler for " + obj, th);
            }
            H0.a.a(o2, th);
        }
        return o2;
    }

    public static /* synthetic */ O d(Function1 function1, Object obj, O o2, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            o2 = null;
        }
        return c(function1, obj, o2);
    }
}
