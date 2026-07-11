package androidx.activity.contextaware;

import H0.j;
import H0.k;
import android.content.Context;
import g1.InterfaceC0377k;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ContextAwareKt$withContextAvailable$2$listener$1 implements OnContextAvailableListener {
    final /* synthetic */ InterfaceC0377k $co;
    final /* synthetic */ Function1 $onContextAvailable;

    public ContextAwareKt$withContextAvailable$2$listener$1(InterfaceC0377k interfaceC0377k, Function1 function1) {
        this.$co = interfaceC0377k;
        this.$onContextAvailable = function1;
    }

    @Override // androidx.activity.contextaware.OnContextAvailableListener
    public void onContextAvailable(Context context) {
        Object objA;
        n.g(context, "context");
        InterfaceC0377k interfaceC0377k = this.$co;
        Function1 function1 = this.$onContextAvailable;
        try {
            j.a aVar = j.f299a;
            objA = j.a(function1.invoke(context));
        } catch (Throwable th) {
            j.a aVar2 = j.f299a;
            objA = j.a(k.a(th));
        }
        interfaceC0377k.resumeWith(objA);
    }
}
