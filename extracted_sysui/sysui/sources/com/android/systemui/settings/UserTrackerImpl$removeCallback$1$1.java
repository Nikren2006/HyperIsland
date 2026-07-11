package com.android.systemui.settings;

import com.android.systemui.settings.UserTracker;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class UserTrackerImpl$removeCallback$1$1 extends o implements Function1 {
    final /* synthetic */ UserTracker.Callback $callback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserTrackerImpl$removeCallback$1$1(UserTracker.Callback callback) {
        super(1);
        this.$callback = callback;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(DataItem it) {
        n.g(it, "it");
        return Boolean.valueOf(it.sameOrEmpty(this.$callback));
    }
}
