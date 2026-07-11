package miui.systemui.devicecontrols.ui;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.SparseArray;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class RenderInfo {
    public static final int APP_ICON_ID = -1;
    public static final int ERROR_ICON = -1000;
    private final int enabledBackground;
    private final int foreground;
    private final Drawable icon;
    public static final Companion Companion = new Companion(null);
    private static final SparseArray<Drawable> iconMap = new SparseArray<>();
    private static final ArrayMap<ComponentName, Drawable> appIconMap = new ArrayMap<>();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ RenderInfo lookup$default(Companion companion, Context context, ComponentName componentName, int i2, int i3, int i4, Object obj) {
            if ((i4 & 8) != 0) {
                i3 = 0;
            }
            return companion.lookup(context, componentName, i2, i3);
        }

        public final void clearCache() {
            RenderInfo.iconMap.clear();
            RenderInfo.appIconMap.clear();
        }

        public final RenderInfo lookup(Context context, ComponentName componentName, int i2, int i3) {
            kotlin.jvm.internal.n.g(context, "context");
            kotlin.jvm.internal.n.g(componentName, "componentName");
            if (i3 > 0) {
                i2 = (i2 * 1000) + i3;
            }
            H0.i iVar = (H0.i) I0.G.g(RenderInfoKt.deviceColorMap, Integer.valueOf(i2));
            return new RenderInfo(null, ((Number) iVar.a()).intValue(), ((Number) iVar.b()).intValue());
        }

        public final void registerComponentIcon(ComponentName componentName, Drawable icon) {
            kotlin.jvm.internal.n.g(componentName, "componentName");
            kotlin.jvm.internal.n.g(icon, "icon");
            RenderInfo.appIconMap.put(componentName, icon);
        }

        private Companion() {
        }
    }

    public RenderInfo(Drawable drawable, int i2, int i3) {
        this.icon = drawable;
        this.foreground = i2;
        this.enabledBackground = i3;
    }

    public static /* synthetic */ RenderInfo copy$default(RenderInfo renderInfo, Drawable drawable, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            drawable = renderInfo.icon;
        }
        if ((i4 & 2) != 0) {
            i2 = renderInfo.foreground;
        }
        if ((i4 & 4) != 0) {
            i3 = renderInfo.enabledBackground;
        }
        return renderInfo.copy(drawable, i2, i3);
    }

    public final Drawable component1() {
        return this.icon;
    }

    public final int component2() {
        return this.foreground;
    }

    public final int component3() {
        return this.enabledBackground;
    }

    public final RenderInfo copy(Drawable drawable, int i2, int i3) {
        return new RenderInfo(drawable, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RenderInfo)) {
            return false;
        }
        RenderInfo renderInfo = (RenderInfo) obj;
        return kotlin.jvm.internal.n.c(this.icon, renderInfo.icon) && this.foreground == renderInfo.foreground && this.enabledBackground == renderInfo.enabledBackground;
    }

    public final int getEnabledBackground() {
        return this.enabledBackground;
    }

    public final int getForeground() {
        return this.foreground;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public int hashCode() {
        Drawable drawable = this.icon;
        return ((((drawable == null ? 0 : drawable.hashCode()) * 31) + Integer.hashCode(this.foreground)) * 31) + Integer.hashCode(this.enabledBackground);
    }

    public String toString() {
        return "RenderInfo(icon=" + this.icon + ", foreground=" + this.foreground + ", enabledBackground=" + this.enabledBackground + ")";
    }
}
