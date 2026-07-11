package com.android.systemui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.android.systemui.QSControlMiPlayDetailHeader;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1", f = "QSControlMiPlayDetailHeader.kt", l = {1252}, m = "invokeSuspend")
public final class QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1 extends N0.l implements Function2 {
    final /* synthetic */ Context $context;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1(Context context, L0.d dVar) {
        super(2, dVar);
        this.$context = context;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1 qSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1 = new QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1(this.$context, dVar);
        qSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1.L$0 = obj;
        return qSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            g1.E e2 = (g1.E) this.L$0;
            if (MiPlayDetailViewModel.INSTANCE.getCpDeepLink() != null) {
                QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
                if (companion.hasDeepLinkPermission()) {
                    companion.startAPPWithDeepLink(this.$context);
                    return H0.s.f314a;
                }
            }
            QSControlMiPlayDetailHeader.Companion companion2 = QSControlMiPlayDetailHeader.Companion;
            Context context = this.$context;
            this.L$0 = e2;
            this.label = 1;
            obj = companion2.getLastPlayingAppPackageName(context, this);
            if (obj == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        String str = (String) obj;
        if (str != null) {
            QSControlMiPlayDetailHeader.Companion.collapseAndJump(this.$context, str);
        } else {
            Context context2 = this.$context;
            Log.d("QSControlMiPlayDetailHeader", "jumpLastPlayingApp(): try jump to app store");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.miui.player"));
            intent.setPackage("com.xiaomi.market");
            intent.addFlags(268435456);
            QSControlMiPlayDetailHeader.Companion.collapseAndJump(context2, intent);
        }
        return H0.s.f314a;
    }
}
