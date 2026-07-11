package miui.systemui.devicecontrols.ui;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.service.controls.Control;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlViewHolder$applyRenderInfo$1 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ Control $control;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ ColorStateList $fg;
    final /* synthetic */ CharSequence $newText;
    final /* synthetic */ RenderInfo $ri;
    final /* synthetic */ ControlViewHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlViewHolder$applyRenderInfo$1(ControlViewHolder controlViewHolder, boolean z2, CharSequence charSequence, RenderInfo renderInfo, ColorStateList colorStateList, Control control) {
        super(0);
        this.this$0 = controlViewHolder;
        this.$enabled = z2;
        this.$newText = charSequence;
        this.$ri = renderInfo;
        this.$fg = colorStateList;
        this.$control = control;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m123invoke();
        return H0.s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m123invoke() {
        ControlViewHolder controlViewHolder = this.this$0;
        boolean z2 = this.$enabled;
        CharSequence charSequence = this.$newText;
        Drawable icon = this.$ri.getIcon();
        ColorStateList fg = this.$fg;
        kotlin.jvm.internal.n.f(fg, "$fg");
        controlViewHolder.updateStatusRow$miui_devicecontrols_release(z2, charSequence, icon, fg, this.$control);
    }
}
