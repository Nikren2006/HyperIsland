package miui.systemui.devicecontrols.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.TemperatureControlTemplate;
import android.service.controls.templates.ToggleTemplate;
import android.util.Log;
import android.view.View;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.util.ControlsUtils;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class ToggleBehavior implements Behavior {
    public Drawable clipLayer;
    public Control control;
    public ControlViewHolder cvh;
    public ToggleTemplate template;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialize$lambda$0(ControlViewHolder cvh, ToggleBehavior this$0, View view) {
        kotlin.jvm.internal.n.g(cvh, "$cvh");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ControlActionCoordinator controlActionCoordinator = cvh.getControlActionCoordinator();
        String templateId = this$0.getTemplate().getTemplateId();
        kotlin.jvm.internal.n.f(templateId, "getTemplateId(...)");
        controlActionCoordinator.toggle(cvh, templateId, this$0.getTemplate().isChecked());
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, int i2) {
        ToggleTemplate toggleTemplate;
        kotlin.jvm.internal.n.g(cws, "cws");
        Control control = cws.getControl();
        kotlin.jvm.internal.n.d(control);
        setControl(control);
        ControlViewHolder cvh = getCvh();
        CharSequence statusText = getControl().getStatusText();
        kotlin.jvm.internal.n.f(statusText, "getStatusText(...)");
        ControlViewHolder.setStatusText$default(cvh, statusText, false, 2, null);
        ControlTemplate controlTemplate = getControl().getControlTemplate();
        if (controlTemplate instanceof ToggleTemplate) {
            kotlin.jvm.internal.n.d(controlTemplate);
            toggleTemplate = (ToggleTemplate) controlTemplate;
        } else {
            if (!(controlTemplate instanceof TemperatureControlTemplate)) {
                Log.e("ControlsUiController", "Unsupported template type: " + controlTemplate);
                return;
            }
            ControlTemplate template = ((TemperatureControlTemplate) controlTemplate).getTemplate();
            kotlin.jvm.internal.n.e(template, "null cannot be cast to non-null type android.service.controls.templates.ToggleTemplate");
            toggleTemplate = (ToggleTemplate) template;
        }
        setTemplate(toggleTemplate);
        Drawable background = getCvh().getLayout().getBackground();
        kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.clip_layer);
        kotlin.jvm.internal.n.f(drawableFindDrawableByLayerId, "findDrawableByLayerId(...)");
        setClipLayer(drawableFindDrawableByLayerId);
        getClipLayer().setLevel(10000);
        ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), ControlsUtils.INSTANCE.checkSenseType(getCvh().getCws().getCi().getControlId()) ? false : getTemplate().isChecked(), i2, false, 4, null);
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

    public final ToggleTemplate getTemplate() {
        ToggleTemplate toggleTemplate = this.template;
        if (toggleTemplate != null) {
            return toggleTemplate;
        }
        kotlin.jvm.internal.n.w("template");
        return null;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(final ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
        cvh.getLayout().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.K
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToggleBehavior.initialize$lambda$0(cvh, this, view);
            }
        });
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

    public final void setTemplate(ToggleTemplate toggleTemplate) {
        kotlin.jvm.internal.n.g(toggleTemplate, "<set-?>");
        this.template = toggleTemplate;
    }
}
