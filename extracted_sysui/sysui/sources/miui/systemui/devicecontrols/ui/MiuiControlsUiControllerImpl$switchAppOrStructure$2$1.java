package miui.systemui.devicecontrols.ui;

import I0.AbstractC0184l;
import android.content.ComponentName;
import kotlin.jvm.functions.Function0;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class MiuiControlsUiControllerImpl$switchAppOrStructure$2$1 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ ComponentName $componentName;
    final /* synthetic */ CharSequence $structure;
    final /* synthetic */ MiuiControlsUiControllerImpl $this_run;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiControlsUiControllerImpl$switchAppOrStructure$2$1(MiuiControlsUiControllerImpl miuiControlsUiControllerImpl, ComponentName componentName, CharSequence charSequence) {
        super(0);
        this.$this_run = miuiControlsUiControllerImpl;
        this.$componentName = componentName;
        this.$structure = charSequence;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(MiuiControlsUiControllerImpl this_run) {
        kotlin.jvm.internal.n.g(this_run, "$this_run");
        this_run.loadStructure(new MiuiControlsUiControllerImpl$switchAppOrStructure$2$1$1$1(this_run));
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m128invoke();
        return H0.s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m128invoke() {
        MiuiControlsUiControllerImpl miuiControlsUiControllerImpl = this.$this_run;
        miuiControlsUiControllerImpl.updatePreferences(miuiControlsUiControllerImpl.getSelectedStructure());
        DelayableExecutor uiExecutor = this.$this_run.getUiExecutor();
        final MiuiControlsUiControllerImpl miuiControlsUiControllerImpl2 = this.$this_run;
        uiExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.B
            @Override // java.lang.Runnable
            public final void run() {
                MiuiControlsUiControllerImpl$switchAppOrStructure$2$1.invoke$lambda$0(miuiControlsUiControllerImpl2);
            }
        });
        if (kotlin.jvm.internal.n.c(this.$componentName.getPackageName(), "com.xiaomi.smarthome")) {
            ((ControlsController) this.$this_run.getControlsController().get()).seedFavoritesForComponents(AbstractC0184l.d(this.$componentName), this.$this_run.onSeedingComplete(this.$componentName, this.$structure));
        }
    }
}
