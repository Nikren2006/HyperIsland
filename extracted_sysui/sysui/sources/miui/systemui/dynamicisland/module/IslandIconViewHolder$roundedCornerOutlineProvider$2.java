package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.R;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandIconViewHolder$roundedCornerOutlineProvider$2 extends o implements Function0 {
    final /* synthetic */ Context $context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandIconViewHolder$roundedCornerOutlineProvider$2(Context context) {
        super(0);
        this.$context = context;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.dynamicisland.module.IslandIconViewHolder$roundedCornerOutlineProvider$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        return new ViewOutlineProvider(this.$context) { // from class: miui.systemui.dynamicisland.module.IslandIconViewHolder$roundedCornerOutlineProvider$2.1
            private final float cornerRadius;

            {
                this.cornerRadius = context.getResources().getDimension(R.dimen.island_icon_radius);
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (outline == null || view == null) {
                    return;
                }
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.cornerRadius);
            }
        };
    }
}
