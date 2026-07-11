package miui.systemui.devicecontrols.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.view.View;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.ui.ControlViewHolder;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class TemperatureControlBehavior implements Behavior {
    public Drawable clipLayer;
    public Control control;
    public ControlViewHolder cvh;
    private Behavior subBehavior;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(TemperatureControlBehavior this$0, TemperatureControlTemplate template, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(template, "$template");
        ControlActionCoordinator controlActionCoordinator = this$0.getCvh().getControlActionCoordinator();
        ControlViewHolder cvh = this$0.getCvh();
        String templateId = template.getTemplateId();
        kotlin.jvm.internal.n.f(templateId, "getTemplateId(...)");
        controlActionCoordinator.touch(cvh, templateId, this$0.getControl());
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, int i2) {
        kotlin.jvm.internal.n.g(cws, "cws");
        Control control = cws.getControl();
        kotlin.jvm.internal.n.d(control);
        setControl(control);
        ControlViewHolder cvh = getCvh();
        CharSequence statusText = getControl().getStatusText();
        kotlin.jvm.internal.n.f(statusText, "getStatusText(...)");
        ControlViewHolder.setStatusText$default(cvh, statusText, false, 2, null);
        Drawable background = getCvh().getLayout().getBackground();
        kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.clip_layer);
        kotlin.jvm.internal.n.f(drawableFindDrawableByLayerId, "findDrawableByLayerId(...)");
        setClipLayer(drawableFindDrawableByLayerId);
        ControlTemplate controlTemplate = getControl().getControlTemplate();
        kotlin.jvm.internal.n.e(controlTemplate, "null cannot be cast to non-null type android.service.controls.templates.TemperatureControlTemplate");
        final TemperatureControlTemplate temperatureControlTemplate = (TemperatureControlTemplate) controlTemplate;
        int currentActiveMode = temperatureControlTemplate.getCurrentActiveMode();
        ControlTemplate template = temperatureControlTemplate.getTemplate();
        if (kotlin.jvm.internal.n.c(template, ControlTemplate.getNoTemplateObject()) || kotlin.jvm.internal.n.c(template, ControlTemplate.getErrorTemplate())) {
            boolean z2 = (currentActiveMode == 0 || currentActiveMode == 1) ? false : true;
            getClipLayer().setLevel(z2 ? 10000 : 0);
            ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), z2, currentActiveMode, false, 4, null);
            getCvh().getLayout().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.G
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TemperatureControlBehavior.bind$lambda$0(this.f5644a, temperatureControlTemplate, view);
                }
            });
            return;
        }
        ControlViewHolder cvh2 = getCvh();
        Behavior behavior = this.subBehavior;
        ControlViewHolder.Companion companion = ControlViewHolder.Companion;
        int status = getControl().getStatus();
        kotlin.jvm.internal.n.d(template);
        this.subBehavior = cvh2.bindBehavior(behavior, ControlViewHolder.Companion.findBehaviorClass$default(companion, status, template, getControl().getDeviceType(), null, 8, null), currentActiveMode);
    }

    public final Drawable getClipLayer() {
        Drawable drawable = this.clipLayer;
        if (drawable != null) {
            return drawable;
        }
        kotlin.jvm.internal.n.w("clipLayer");
        return null;
    }

    public final Control getControl() {
        Control control = this.control;
        if (control != null) {
            return control;
        }
        kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_TV_CONTROLLER);
        return null;
    }

    public final ControlViewHolder getCvh() {
        ControlViewHolder controlViewHolder = this.cvh;
        if (controlViewHolder != null) {
            return controlViewHolder;
        }
        kotlin.jvm.internal.n.w("cvh");
        return null;
    }

    public final Behavior getSubBehavior() {
        return this.subBehavior;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
    }

    public final void setClipLayer(Drawable drawable) {
        kotlin.jvm.internal.n.g(drawable, "<set-?>");
        this.clipLayer = drawable;
    }

    public final void setControl(Control control) {
        kotlin.jvm.internal.n.g(control, "<set-?>");
        this.control = control;
    }

    public final void setCvh(ControlViewHolder controlViewHolder) {
        kotlin.jvm.internal.n.g(controlViewHolder, "<set-?>");
        this.cvh = controlViewHolder;
    }

    public final void setSubBehavior(Behavior behavior) {
        this.subBehavior = behavior;
    }
}
