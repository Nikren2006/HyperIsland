package miui.systemui.devicecontrols.management;

import H0.s;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
public /* synthetic */ class EditControlsModelController$changeFavoriteStatus$1$1 extends kotlin.jvm.internal.l implements Function2 {
    public EditControlsModelController$changeFavoriteStatus$1$1(Object obj) {
        super(2, obj, EditControlsModelController.class, "updateMarkVisible", "updateMarkVisible(ZZ)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
        return s.f314a;
    }

    public final void invoke(boolean z2, boolean z3) {
        ((EditControlsModelController) this.receiver).updateMarkVisible(z2, z3);
    }
}
