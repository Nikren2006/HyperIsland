package com.android.systemui;

import android.util.Log;
import android.view.View;
import com.miui.miplay.audio.data.AppMetaData;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$addObservers$4$1$1", f = "QSControlMiPlayDetailHeader.kt", l = {926, 932}, m = "invokeSuspend")
public final class QSControlMiPlayDetailHeader$addObservers$4$1$1 extends N0.l implements Function2 {
    final /* synthetic */ AppMetaData $appMetaData;
    int label;
    final /* synthetic */ QSControlMiPlayDetailHeader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$addObservers$4$1$1(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, AppMetaData appMetaData, L0.d dVar) {
        super(2, dVar);
        this.this$0 = qSControlMiPlayDetailHeader;
        this.$appMetaData = appMetaData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(Function1 function1, QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, View view) {
        Log.d("QSControlMiPlayDetailHeader", "cover click");
        kotlin.jvm.internal.n.d(view);
        function1.invoke(view);
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_COVER, MiPlayEventsKt.PAGE_MIPLAY_CARD, qSControlMiPlayDetailHeader.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(Function1 function1, QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, View view) {
        Log.d("QSControlMiPlayDetailHeader", "title click");
        kotlin.jvm.internal.n.d(view);
        function1.invoke(view);
        MiPlayEventTracker.trackClick("title", MiPlayEventsKt.PAGE_MIPLAY_CARD, qSControlMiPlayDetailHeader.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$2(Function1 function1, QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, View view) {
        Log.d("QSControlMiPlayDetailHeader", "subtitle click");
        kotlin.jvm.internal.n.d(view);
        function1.invoke(view);
        MiPlayEventTracker.trackClick("subtitle", MiPlayEventsKt.PAGE_MIPLAY_CARD, qSControlMiPlayDetailHeader.mRef);
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new QSControlMiPlayDetailHeader$addObservers$4$1$1(this.this$0, this.$appMetaData, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailHeader$addObservers$4$1$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00db  */
    @Override // N0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$4$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
