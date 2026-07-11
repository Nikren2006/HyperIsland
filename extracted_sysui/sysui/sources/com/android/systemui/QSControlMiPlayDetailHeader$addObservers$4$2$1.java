package com.android.systemui;

import android.content.Context;
import android.view.View;
import com.android.systemui.QSControlMiPlayDetailHeader;
import kotlin.jvm.functions.Function2;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$addObservers$4$2$1", f = "QSControlMiPlayDetailHeader.kt", l = {982, 983, 989}, m = "invokeSuspend")
public final class QSControlMiPlayDetailHeader$addObservers$4$2$1 extends N0.l implements Function2 {
    final /* synthetic */ QSControlMiPlayDetailHeader $this_run;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$addObservers$4$2$1(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, L0.d dVar) {
        super(2, dVar);
        this.$this_run = qSControlMiPlayDetailHeader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$2(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, View view) {
        QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
        Context context = qSControlMiPlayDetailHeader.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        companion.jumpLastPlayingApp(context);
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_COVER, MiPlayEventsKt.PAGE_MIPLAY_CARD, qSControlMiPlayDetailHeader.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$3(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, View view) {
        QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
        Context context = qSControlMiPlayDetailHeader.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        companion.jumpLastPlayingApp(context);
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_NOT_PLAY_TITLE, MiPlayEventsKt.PAGE_MIPLAY_CARD, qSControlMiPlayDetailHeader.mRef);
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        QSControlMiPlayDetailHeader$addObservers$4$2$1 qSControlMiPlayDetailHeader$addObservers$4$2$1 = new QSControlMiPlayDetailHeader$addObservers$4$2$1(this.$this_run, dVar);
        qSControlMiPlayDetailHeader$addObservers$4$2$1.L$0 = obj;
        return qSControlMiPlayDetailHeader$addObservers$4$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailHeader$addObservers$4$2$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0117  */
    @Override // N0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$4$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
