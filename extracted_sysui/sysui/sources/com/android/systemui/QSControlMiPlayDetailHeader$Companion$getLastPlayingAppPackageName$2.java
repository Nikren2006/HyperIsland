package com.android.systemui;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.QSControlMiPlayDetailHeader;
import kotlin.jvm.functions.Function2;
import miui.util.NotificationFilterHelper;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$Companion$getLastPlayingAppPackageName$2", f = "QSControlMiPlayDetailHeader.kt", l = {1206}, m = "invokeSuspend")
public final class QSControlMiPlayDetailHeader$Companion$getLastPlayingAppPackageName$2 extends N0.l implements Function2 {
    final /* synthetic */ Context $context;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$Companion$getLastPlayingAppPackageName$2(Context context, L0.d dVar) {
        super(2, dVar);
        this.$context = context;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new QSControlMiPlayDetailHeader$Companion$getLastPlayingAppPackageName$2(this.$context, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailHeader$Companion$getLastPlayingAppPackageName$2) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            this.label = 1;
            obj = miPlayDetailViewModel.getPlayingPackageName(this);
            if (obj == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        String string = (String) obj;
        if (TextUtils.isEmpty(string)) {
            string = NotificationFilterHelper.getSharedPreferences(this.$context).getString("last_playing_package_name", "");
        }
        Log.d("QSControlMiPlayDetailHeader", "getLastPlayingAppPackageName(): last_playing_package_name " + string);
        QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
        if (companion.isInstalledAndVisible(this.$context, string)) {
            return string;
        }
        Log.d("QSControlMiPlayDetailHeader", "getLastPlayingAppPackageName(): try jump to xiaomi music");
        if (companion.isInstalledAndVisible(this.$context, "com.miui.player")) {
            return "com.miui.player";
        }
        return null;
    }
}
