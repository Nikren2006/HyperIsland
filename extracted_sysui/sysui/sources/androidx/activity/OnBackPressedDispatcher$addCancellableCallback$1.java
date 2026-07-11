package androidx.activity;

import H0.s;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.l;

/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class OnBackPressedDispatcher$addCancellableCallback$1 extends l implements Function0 {
    public OnBackPressedDispatcher$addCancellableCallback$1(Object obj) {
        super(0, obj, OnBackPressedDispatcher.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m4invoke();
        return s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m4invoke() {
        ((OnBackPressedDispatcher) this.receiver).updateEnabledCallbacks();
    }
}
