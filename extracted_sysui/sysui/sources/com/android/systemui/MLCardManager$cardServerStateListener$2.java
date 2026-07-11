package com.android.systemui;

import com.android.systemui.MLCardManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class MLCardManager$cardServerStateListener$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final MLCardManager$cardServerStateListener$2 INSTANCE = new MLCardManager$cardServerStateListener$2();

    public MLCardManager$cardServerStateListener$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final MLCardManager.CardServerStateListener invoke() {
        return new MLCardManager.CardServerStateListener();
    }
}
