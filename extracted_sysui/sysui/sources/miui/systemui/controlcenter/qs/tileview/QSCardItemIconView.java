package miui.systemui.controlcenter.qs.tileview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import java.util.function.Supplier;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.qs.tileview.QSItemView;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class QSCardItemIconView extends QSIconView implements QSItemIconView {
    public static final Companion Companion = new Companion(null);
    public static final String TAG_MIUI_CELLULAR_TILE = "cell";
    private AnimatedVectorDrawable _iconDrawable;
    private float customTileSize;
    private int defaultIconColor;
    private int defaultIconColorOff;
    private int defaultIconColorRestricted;
    private int defaultIconColorUnavailable;
    private final ImageView icon;
    private int miuiCellularTileIconColor;
    private final Context pluginContext;
    private float tileSize;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ QSCardItemIconView(Context context, Context context2, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? context : context2, (i2 & 4) != 0 ? null : attributeSet);
    }

    private final LayerDrawable getProperDrawable(Drawable drawable) {
        int i2 = (int) (drawable instanceof AnimatedVectorDrawable ? this.tileSize : this.customTileSize);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable});
        layerDrawable.setLayerGravity(0, 17);
        layerDrawable.setLayerSize(0, i2, i2);
        return layerDrawable;
    }

    public void disableAnimation() {
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemIconView
    public void forceStopIconAnimate() {
        AnimatedVectorDrawable animatedVectorDrawable = this._iconDrawable;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.stop();
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        this.icon.layout(0, 0, i4 - i2, i5 - i3);
    }

    public void setAnimationEnabled(boolean z2) {
    }

    public void setIcon(QSTile.State state, boolean z2) {
        QSTile.Icon icon;
        Drawable invisibleDrawableCompat;
        if (state == null) {
            return;
        }
        Supplier supplier = state.iconSupplier;
        if ((supplier == null || (icon = (QSTile.Icon) supplier.get()) == null) && (icon = state.icon) == null) {
            return;
        }
        ImageView imageView = this.icon;
        int i2 = R.id.qs_icon_state_tag;
        Integer num = (Integer) imageView.getTag(i2);
        ImageView imageView2 = this.icon;
        int i3 = R.id.qs_icon_state_bg_color_tag;
        Integer num2 = (Integer) imageView2.getTag(i3);
        ImageView imageView3 = this.icon;
        int i4 = R.id.qs_icon_state_restrict_tag;
        Boolean bool = (Boolean) imageView3.getTag(i4);
        int i5 = state.state;
        if (num != null && num.intValue() == i5 && n.c(icon, this.icon.getTag(R.id.qs_icon_tag))) {
            int i6 = state.activeBgColor;
            if (num2 != null && num2.intValue() == i6 && n.c(bool, Boolean.valueOf(QSItemView.Companion.isRestrictedCompat(state)))) {
                return;
            }
        }
        if (z2) {
            invisibleDrawableCompat = icon.getDrawable(getContext());
        } else {
            QSItemView.Companion companion = QSItemView.Companion;
            Context context = getContext();
            n.f(context, "getContext(...)");
            invisibleDrawableCompat = companion.getInvisibleDrawableCompat(icon, context);
        }
        invisibleDrawableCompat.mutate();
        invisibleDrawableCompat.setAutoMirrored(false);
        int i7 = state.state;
        if (i7 == 0) {
            invisibleDrawableCompat.setTint(this.defaultIconColorUnavailable);
        } else if (i7 == 1) {
            invisibleDrawableCompat.setTint(QSItemView.Companion.isRestrictedCompat(state) ? this.defaultIconColorRestricted : this.defaultIconColorOff);
        } else if (i7 == 2) {
            if (n.c(state.spec, "cell")) {
                invisibleDrawableCompat.setTint(this.miuiCellularTileIconColor);
            } else {
                invisibleDrawableCompat.setTint(this.defaultIconColor);
            }
        }
        ImageView imageView4 = this.icon;
        n.d(invisibleDrawableCompat);
        imageView4.setImageDrawable(getProperDrawable(invisibleDrawableCompat));
        AnimatedVectorDrawable animatedVectorDrawable = invisibleDrawableCompat instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) invisibleDrawableCompat : null;
        if (animatedVectorDrawable != null) {
            if (!animatedVectorDrawable.isRunning()) {
                animatedVectorDrawable.start();
            }
            if (!z2) {
                animatedVectorDrawable.stop();
            }
            this._iconDrawable = (AnimatedVectorDrawable) invisibleDrawableCompat;
        }
        this.icon.setTag(R.id.qs_icon_tag, icon);
        this.icon.setTag(i2, Integer.valueOf(state.state));
        this.icon.setTag(i3, Integer.valueOf(state.activeBgColor));
        this.icon.setTag(i4, Boolean.valueOf(QSItemView.Companion.isRestrictedCompat(state)));
    }

    public void setIsCustomTile(boolean z2) {
    }

    public void updateResources() {
        this.defaultIconColor = this.pluginContext.getColor(R.color.qs_icon_enabled_color);
        this.defaultIconColorOff = this.pluginContext.getColor(R.color.qs_icon_disabled_color);
        this.defaultIconColorUnavailable = this.pluginContext.getColor(R.color.qs_icon_unavailable_color);
        this.defaultIconColorRestricted = this.pluginContext.getColor(R.color.qs_icon_restrict_color);
        this.customTileSize = this.pluginContext.getResources().getDimension(R.dimen.qs_tile_item_icon_inner_size);
        this.tileSize = this.pluginContext.getResources().getDimension(R.dimen.control_center_universal_1_row_size);
        this.icon.setTag(R.id.qs_icon_tag, null);
        this.icon.setTag(R.id.qs_icon_state_tag, null);
        this.icon.setTag(R.id.qs_icon_state_bg_color_tag, null);
        this.icon.setTag(R.id.qs_icon_state_restrict_tag, null);
        this.miuiCellularTileIconColor = this.pluginContext.getColor(R.color.qs_miui_cellular_icon_enabled_color);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSCardItemIconView(Context pluginContext, Context sysUIContext, AttributeSet attributeSet) {
        super(sysUIContext, attributeSet);
        n.g(pluginContext, "pluginContext");
        n.g(sysUIContext, "sysUIContext");
        this.pluginContext = pluginContext;
        ImageView imageView = new ImageView(getContext());
        imageView.setId(R.id.icon);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(imageView);
        this.icon = imageView;
        updateResources();
    }

    public ImageView getIconView() {
        return this.icon;
    }
}
