package miui.systemui.devicecontrols.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.management.BaseHolder;
import miui.systemui.devicecontrols.management.ControlAdapter;
import miui.systemui.devicecontrols.management.ControlStatusWrapper;
import miui.systemui.devicecontrols.management.ElementWrapper;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.util.ThreadUtils;
import miuix.animation.Folme;
import miuix.animation.IVisibleStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes3.dex */
public final class EditControlViewHolder extends BaseHolder {
    public static final Companion Companion = new Companion(null);
    public static final int FAST_CLICK_TIME_DELAY = 1000;
    private final IVisibleStyle anim;
    private final Context context;
    private final Function2 favoriteChangeCallback;
    private final ImageView icon;
    private long lastClickTime;
    private final View layout;
    private final ImageView mark;
    private final TextView subtitle;
    private final TextView title;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditControlViewHolder(Context context, View layout, Function2 favoriteChangeCallback) {
        super(layout);
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(layout, "layout");
        kotlin.jvm.internal.n.g(favoriteChangeCallback, "favoriteChangeCallback");
        this.context = context;
        this.layout = layout;
        this.favoriteChangeCallback = favoriteChangeCallback;
        View viewRequireViewById = layout.requireViewById(R.id.title);
        kotlin.jvm.internal.n.f(viewRequireViewById, "requireViewById(...)");
        this.title = (TextView) viewRequireViewById;
        View viewRequireViewById2 = layout.requireViewById(R.id.subtitle);
        kotlin.jvm.internal.n.f(viewRequireViewById2, "requireViewById(...)");
        this.subtitle = (TextView) viewRequireViewById2;
        View viewRequireViewById3 = layout.requireViewById(R.id.mark);
        kotlin.jvm.internal.n.f(viewRequireViewById3, "requireViewById(...)");
        ImageView imageView = (ImageView) viewRequireViewById3;
        this.mark = imageView;
        View viewRequireViewById4 = layout.requireViewById(R.id.icon);
        kotlin.jvm.internal.n.f(viewRequireViewById4, "requireViewById(...)");
        this.icon = (ImageView) viewRequireViewById4;
        this.anim = Folme.use((View) imageView).visible();
    }

    private static final void bindData$hideMark(EditControlViewHolder editControlViewHolder, boolean z2) {
        editControlViewHolder.mark.setClickable(false);
        IVisibleStyle iVisibleStyle = editControlViewHolder.anim;
        if (z2) {
            iVisibleStyle.hide(new AnimConfig[0]);
        } else {
            iVisibleStyle.setHide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1$lambda$0(EditControlViewHolder this$0, Drawable drawable) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.icon.setImageDrawable(drawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void bindData$lambda$3$lambda$2(EditControlViewHolder this$0, ElementWrapper wrapper, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(wrapper, "$wrapper");
        if (System.currentTimeMillis() - this$0.lastClickTime > 1000) {
            this$0.lastClickTime = System.currentTimeMillis();
            this$0.favoriteChangeCallback.invoke(((ControlInterface) wrapper).getControlId(), Boolean.valueOf(!r5.getFavorite()));
        }
    }

    private static final void bindData$showMark(EditControlViewHolder editControlViewHolder, boolean z2) {
        editControlViewHolder.mark.setClickable(true);
        IVisibleStyle iVisibleStyle = editControlViewHolder.anim;
        if (z2) {
            iVisibleStyle.show(new AnimConfig[0]);
        } else {
            iVisibleStyle.setShow();
        }
    }

    private final Drawable getMarkDrawable(boolean z2) {
        return this.context.getDrawable(z2 ? R.drawable.ic_controls_edit_remove : R.drawable.ic_controls_edit_add);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.devicecontrols.management.BaseHolder
    public void bindData(final ElementWrapper wrapper) {
        Icon customIcon;
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        super.bindData(wrapper);
        if (!(wrapper instanceof ControlInterface)) {
            throw new IllegalStateException("binding wrong element type.");
        }
        if ((wrapper instanceof ControlStatusWrapper) && (customIcon = ((ControlStatusWrapper) wrapper).getControlStatus().getControl().getCustomIcon()) != null) {
            customIcon.loadDrawableAsync(this.context, new Icon.OnDrawableLoadedListener() { // from class: miui.systemui.devicecontrols.ui.o
                @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                public final void onDrawableLoaded(Drawable drawable) {
                    EditControlViewHolder.bindData$lambda$1$lambda$0(this.f5691a, drawable);
                }
            }, ThreadUtils.getUiThreadHandler());
        }
        ControlInterface controlInterface = (ControlInterface) wrapper;
        this.title.setText(controlInterface.getTitle());
        ControlsUtils controlsUtils = ControlsUtils.INSTANCE;
        if (controlsUtils.checkSenseType(controlInterface.getControlId()) || TextUtils.isEmpty(controlInterface.getSubtitle())) {
            this.subtitle.setVisibility(8);
        } else {
            this.subtitle.setVisibility(0);
            this.subtitle.setText(controlInterface.getSubtitle());
        }
        ImageView imageView = this.mark;
        imageView.setVisibility(controlsUtils.getMarkVisible(wrapper) ? 0 : 8);
        imageView.setClickable(controlsUtils.getMarkVisible(wrapper));
        imageView.setContentDescription(imageView.getContext().getString(controlInterface.getFavorite() ? R.string.accessibility_desc_remove : R.string.accessibility_desc_add));
        imageView.setImageDrawable(getMarkDrawable(controlInterface.getFavorite()));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditControlViewHolder.bindData$lambda$3$lambda$2(this.f5692a, wrapper, view);
            }
        });
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
    }

    @Override // miui.systemui.devicecontrols.management.BaseHolder
    public void bindData(ElementWrapper wrapper, int i2, List<Object> payloads) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        kotlin.jvm.internal.n.g(payloads, "payloads");
        boolean zContains = payloads.contains(ControlAdapter.MARK_PAYLOAD);
        if (ControlsUtils.INSTANCE.getMarkVisible(wrapper)) {
            bindData$showMark(this, zContains);
        } else {
            bindData$hideMark(this, zContains);
        }
    }
}
