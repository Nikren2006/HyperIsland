package com.android.systemui;

import com.android.systemui.MLCardManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class MLCardManager$cardStateListener$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final MLCardManager$cardStateListener$2 INSTANCE = new MLCardManager$cardStateListener$2();

    public MLCardManager$cardStateListener$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final MLCardManager.CardStateListener invoke() {
        return new MLCardManager.CardStateListener();
    }
}
