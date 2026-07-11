package miui.systemui.devicecontrols.ui;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import android.service.controls.templates.ThumbnailTemplate;
import android.util.TypedValue;
import android.view.View;
import miui.systemui.devicecontrols.R;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class ThumbnailBehavior implements Behavior {
    public Control control;
    public ControlViewHolder cvh;
    private int shadowColor;
    private float shadowOffsetX;
    private float shadowOffsetY;
    private float shadowRadius;
    public ThumbnailTemplate template;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$2(final ThumbnailBehavior this$0, final ClipDrawable clipLayer, final int i2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(clipLayer, "$clipLayer");
        final Drawable drawableLoadDrawable = this$0.getTemplate().getThumbnail().loadDrawable(this$0.getCvh().getContext());
        this$0.getCvh().getUiExecutor().execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.J
            @Override // java.lang.Runnable
            public final void run() {
                ThumbnailBehavior.bind$lambda$2$lambda$1(this.f5651a, clipLayer, drawableLoadDrawable, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$2$lambda$1(ThumbnailBehavior this$0, ClipDrawable clipLayer, Drawable drawable, int i2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(clipLayer, "$clipLayer");
        float dimensionPixelSize = this$0.getCvh().getContext().getResources().getDimensionPixelSize(R.dimen.control_corner_radius);
        kotlin.jvm.internal.n.d(drawable);
        clipLayer.setDrawable(new CornerDrawable(drawable, dimensionPixelSize));
        clipLayer.setColorFilter(new BlendModeColorFilter(this$0.getCvh().getContext().getResources().getColor(R.color.control_thumbnail_tint), BlendMode.LUMINOSITY));
        ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(this$0.getCvh(), this$0.getEnabled(), i2, false, 4, null);
    }

    private final boolean getEnabled() {
        return getTemplate().isActive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialize$lambda$0(ControlViewHolder cvh, ThumbnailBehavior this$0, View view) {
        kotlin.jvm.internal.n.g(cvh, "$cvh");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ControlActionCoordinator controlActionCoordinator = cvh.getControlActionCoordinator();
        String templateId = this$0.getTemplate().getTemplateId();
        kotlin.jvm.internal.n.f(templateId, "getTemplateId(...)");
        controlActionCoordinator.touch(cvh, templateId, this$0.getControl());
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void bind(ControlWithState cws, final int i2) {
        kotlin.jvm.internal.n.g(cws, "cws");
        Control control = cws.getControl();
        kotlin.jvm.internal.n.d(control);
        setControl(control);
        ControlViewHolder cvh = getCvh();
        CharSequence statusText = getControl().getStatusText();
        kotlin.jvm.internal.n.f(statusText, "getStatusText(...)");
        ControlViewHolder.setStatusText$default(cvh, statusText, false, 2, null);
        ControlTemplate controlTemplate = getControl().getControlTemplate();
        kotlin.jvm.internal.n.e(controlTemplate, "null cannot be cast to non-null type android.service.controls.templates.ThumbnailTemplate");
        setTemplate((ThumbnailTemplate) controlTemplate);
        Drawable background = getCvh().getLayout().getBackground();
        kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.clip_layer);
        kotlin.jvm.internal.n.e(drawableFindDrawableByLayerId, "null cannot be cast to non-null type android.graphics.drawable.ClipDrawable");
        final ClipDrawable clipDrawable = (ClipDrawable) drawableFindDrawableByLayerId;
        clipDrawable.setLevel(getEnabled() ? 10000 : 0);
        if (getTemplate().isActive()) {
            getCvh().getTitle().setVisibility(4);
            getCvh().getSubtitle().setVisibility(4);
            getCvh().getStatus().setShadowLayer(this.shadowOffsetX, this.shadowOffsetY, this.shadowRadius, this.shadowColor);
            getCvh().getBgExecutor().execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.I
                @Override // java.lang.Runnable
                public final void run() {
                    ThumbnailBehavior.bind$lambda$2(this.f5648a, clipDrawable, i2);
                }
            });
        } else {
            getCvh().getTitle().setVisibility(0);
            getCvh().getSubtitle().setVisibility(0);
            getCvh().getStatus().setShadowLayer(0.0f, 0.0f, 0.0f, this.shadowColor);
        }
        ControlViewHolder.applyRenderInfo$miui_devicecontrols_release$default(getCvh(), getEnabled(), i2, false, 4, null);
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

    public final ThumbnailTemplate getTemplate() {
        ThumbnailTemplate thumbnailTemplate = this.template;
        if (thumbnailTemplate != null) {
            return thumbnailTemplate;
        }
        kotlin.jvm.internal.n.w("template");
        return null;
    }

    @Override // miui.systemui.devicecontrols.ui.Behavior
    public void initialize(final ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        setCvh(cvh);
        TypedValue typedValue = new TypedValue();
        cvh.getContext().getResources().getValue(R.dimen.controls_thumbnail_shadow_x, typedValue, true);
        this.shadowOffsetX = typedValue.getFloat();
        cvh.getContext().getResources().getValue(R.dimen.controls_thumbnail_shadow_y, typedValue, true);
        this.shadowOffsetY = typedValue.getFloat();
        cvh.getContext().getResources().getValue(R.dimen.controls_thumbnail_shadow_radius, typedValue, true);
        this.shadowRadius = typedValue.getFloat();
        this.shadowColor = cvh.getContext().getResources().getColor(R.color.control_thumbnail_shadow_color);
        cvh.getLayout().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.H
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThumbnailBehavior.initialize$lambda$0(cvh, this, view);
            }
        });
    }

    public final void setControl(Control control) {
        kotlin.jvm.internal.n.g(control, "<set-?>");
        this.control = control;
    }

    public final void setCvh(ControlViewHolder controlViewHolder) {
        kotlin.jvm.internal.n.g(controlViewHolder, "<set-?>");
        this.cvh = controlViewHolder;
    }

    public final void setTemplate(ThumbnailTemplate thumbnailTemplate) {
        kotlin.jvm.internal.n.g(thumbnailTemplate, "<set-?>");
        this.template = thumbnailTemplate;
    }
}
