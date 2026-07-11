package com.android.systemui.miui.volume;

import kotlin.jvm.functions.Function0;
import miui.systemui.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeColumn$mainScope$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final VolumeColumn$mainScope$2 INSTANCE = new VolumeColumn$mainScope$2();

    public VolumeColumn$mainScope$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final g1.E invoke() {
        return CoroutineScopeKt.MainScope();
    }
}
