package androidx.activity.contextaware;

import H0.s;
import L0.d;
import M0.b;
import M0.c;
import N0.h;
import android.content.Context;
import g1.C0379l;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes.dex */
public final class ContextAwareKt {
    public static final <R> Object withContextAvailable(ContextAware contextAware, Function1 function1, d dVar) {
        Context contextPeekAvailableContext = contextAware.peekAvailableContext();
        if (contextPeekAvailableContext != null) {
            return function1.invoke(contextPeekAvailableContext);
        }
        C0379l c0379l = new C0379l(b.b(dVar), 1);
        c0379l.A();
        ContextAwareKt$withContextAvailable$2$listener$1 contextAwareKt$withContextAvailable$2$listener$1 = new ContextAwareKt$withContextAvailable$2$listener$1(c0379l, function1);
        contextAware.addOnContextAvailableListener(contextAwareKt$withContextAvailable$2$listener$1);
        c0379l.g(new ContextAwareKt$withContextAvailable$2$1(contextAware, contextAwareKt$withContextAvailable$2$listener$1));
        Object objX = c0379l.x();
        if (objX == c.c()) {
            h.c(dVar);
        }
        return objX;
    }

    private static final <R> Object withContextAvailable$$forInline(ContextAware contextAware, Function1 function1, d dVar) {
        Context contextPeekAvailableContext = contextAware.peekAvailableContext();
        if (contextPeekAvailableContext != null) {
            return function1.invoke(contextPeekAvailableContext);
        }
        m.c(0);
        C0379l c0379l = new C0379l(b.b(dVar), 1);
        c0379l.A();
        ContextAwareKt$withContextAvailable$2$listener$1 contextAwareKt$withContextAvailable$2$listener$1 = new ContextAwareKt$withContextAvailable$2$listener$1(c0379l, function1);
        contextAware.addOnContextAvailableListener(contextAwareKt$withContextAvailable$2$listener$1);
        c0379l.g(new ContextAwareKt$withContextAvailable$2$1(contextAware, contextAwareKt$withContextAvailable$2$listener$1));
        s sVar = s.f314a;
        Object objX = c0379l.x();
        if (objX == c.c()) {
            h.c(dVar);
        }
        m.c(1);
        return objX;
    }
}
