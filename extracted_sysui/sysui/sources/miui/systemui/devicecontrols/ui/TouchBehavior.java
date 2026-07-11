package miui.systemui.devicecontrols.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.view.View;
import android.widget.Toast;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.R;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class TouchBehavior implements Behavior {
    public static final Companion Companion = new Companion(null);
    public static final long STATELESS_ENABLE_TIMEOUT_IN_MILLIS = 3000;
    public Drawable clipLayer;
    public Control control;
    public ControlViewHolder cvh;
    private int lastColorOffset;
    private boolean statelessTouch;
    public ControlTemplate template;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final boolean getEnabled() {
        return this.lastColorOffset > 0 || this.statelessTouch;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialize$lambda$0(ControlViewHolder cvh, TouchBehavior this$0, View view) {
        kotlin.jvm.internal.n.g(cvh, "$cvh");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ControlActionCoordinator controlActionCoordinator = cvh.getControlActionCoordinator();
        String templateId = this$0.getTemplate().getTemplateId();
        kotlin.jvm.internal.n.f(templateId, "getTemplateId(...)");
        controlActionCoordinator.touch(cvh, templateId, this$0.getControl());
        if (this$0.getTemplate() instanceof StatelessTemplate) {
            Toast.makeText(cvh.getLayout().getContext(), cvh.getLayout().getContext().getString(R.string.controls_unsupport_toggle_toast), 0).show();
        }
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, int i2) {
        kotlin.jvm.internal.n.g(cws, "cws");
        Control control = cws.getControl();
        kotlin.jvm.internal.n.d(control);
        setControl(control);
        this.lastColorOffset = i2;
        ControlViewHolder cvh = getCvh();
        CharSequence statusText = getControl().getStatusText();
        kotlin.jvm.internal.n.f(statusText, "getStatusText(...)");
        ControlViewHolder.setStatusText$default(cvh, statusText, false, 2, null);
        ControlTemplate controlTemplate = getControl().getControlTemplate();
        kotlin.jvm.internal.n.f(controlTemplate, "getControlTemplate(...)");
        setTemplate(controlTemplate);
        Drawable background = getCvh().getLayout().getBackground();
        kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.clip_layer);
        kotlin.jvm.internal.n.f(drawableFindDrawableByLayerId, "findDrawableByLayerId(...)");
        setClipLayer(drawableFindDrawableByLayerId);
        getClipLayer().setLevel(getEnabled() ? 10000 : 0);
        ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), getEnabled(), i2, false, 4, null);
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

    public final ControlTemplate getTemplate() {
        ControlTemplate controlTemplate = this.template;
        if (controlTemplate != null) {
            return controlTemplate;
        }
        kotlin.jvm.internal.n.w("template");
        return null;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(final ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
        cvh.getLayout().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.N
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TouchBehavior.initialize$lambda$0(cvh, this, view);
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

    public final void setTemplate(ControlTemplate controlTemplate) {
        kotlin.jvm.internal.n.g(controlTemplate, "<set-?>");
        this.template = controlTemplate;
    }
}
