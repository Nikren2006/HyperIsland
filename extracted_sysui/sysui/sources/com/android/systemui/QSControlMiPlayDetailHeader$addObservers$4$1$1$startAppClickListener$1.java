package com.android.systemui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.miui.miplay.audio.data.AppMetaData;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailHeader$addObservers$4$1$1$startAppClickListener$1 extends kotlin.jvm.internal.o implements Function1 {
    final /* synthetic */ AppMetaData $appMetaData;
    final /* synthetic */ QSControlMiPlayDetailHeader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$addObservers$4$1$1$startAppClickListener$1(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, AppMetaData appMetaData) {
        super(1);
        this.this$0 = qSControlMiPlayDetailHeader;
        this.$appMetaData = appMetaData;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return H0.s.f314a;
    }

    public final void invoke(View it) {
        kotlin.jvm.internal.n.g(it, "it");
        if (MiPlayDetailViewModel.INSTANCE.getCpDeepLink() != null) {
            QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
            if (companion.hasDeepLinkPermission()) {
                Context context = this.this$0.getContext();
                kotlin.jvm.internal.n.f(context, "getContext(...)");
                companion.startAPPWithDeepLink(context);
                return;
            }
        }
        Intent launchIntentForPackage = this.this$0.getContext().getPackageManager().getLaunchIntentForPackage(this.$appMetaData.getPackageName());
        if (launchIntentForPackage != null) {
            QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.this$0;
            QSControlMiPlayDetailHeader.Companion companion2 = QSControlMiPlayDetailHeader.Companion;
            Context context2 = qSControlMiPlayDetailHeader.getContext();
            kotlin.jvm.internal.n.f(context2, "getContext(...)");
            companion2.collapseAndJump(context2, launchIntentForPackage);
        }
    }
}
