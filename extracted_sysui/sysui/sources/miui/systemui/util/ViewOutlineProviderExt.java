package miui.systemui.util;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: loaded from: classes4.dex */
public final class ViewOutlineProviderExt {
    public static final ViewOutlineProviderExt INSTANCE = new ViewOutlineProviderExt();
    private static final ViewOutlineProvider SOLID_BACKGROUND = new ViewOutlineProvider() { // from class: miui.systemui.util.ViewOutlineProviderExt$SOLID_BACKGROUND$1
        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            kotlin.jvm.internal.n.g(view, "view");
            kotlin.jvm.internal.n.g(outline, "outline");
            Drawable background = view.getBackground();
            if (background != null) {
                background.getOutline(outline);
                outline.setAlpha(1.0f);
            } else {
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
                outline.setAlpha(0.0f);
            }
        }
    };

    private ViewOutlineProviderExt() {
    }

    public final ViewOutlineProvider getOutlineProvider(final float f2) {
        return new ViewOutlineProvider() { // from class: miui.systemui.util.ViewOutlineProviderExt.getOutlineProvider.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                kotlin.jvm.internal.n.g(view, "view");
                kotlin.jvm.internal.n.g(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), f2);
            }
        };
    }

    public final ViewOutlineProvider getSOLID_BACKGROUND() {
        return SOLID_BACKGROUND;
    }
}
