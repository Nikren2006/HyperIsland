package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.dynamicisland.R;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleTextBinding implements ViewBinding {

    @NonNull
    public final FrameLayout islandContainerModuleText;

    @NonNull
    public final TimerTextEffectView islandContent;

    @NonNull
    public final TimerTextEffectView islandFrontTitle;

    @NonNull
    public final View islandTextShade;

    @NonNull
    public final TimerTextEffectView islandTitle;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleTextBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull TimerTextEffectView timerTextEffectView, @NonNull TimerTextEffectView timerTextEffectView2, @NonNull View view, @NonNull TimerTextEffectView timerTextEffectView3) {
        this.rootView = frameLayout;
        this.islandContainerModuleText = frameLayout2;
        this.islandContent = timerTextEffectView;
        this.islandFrontTitle = timerTextEffectView2;
        this.islandTextShade = view;
        this.islandTitle = timerTextEffectView3;
    }

    @NonNull
    public static DynamicIslandModuleTextBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        FrameLayout frameLayout = (FrameLayout) view;
        int i2 = R.id.island_content;
        TimerTextEffectView timerTextEffectView = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
        if (timerTextEffectView != null) {
            i2 = R.id.island_front_title;
            TimerTextEffectView timerTextEffectView2 = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
            if (timerTextEffectView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.island_text_shade))) != null) {
                i2 = R.id.island_title;
                TimerTextEffectView timerTextEffectView3 = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
                if (timerTextEffectView3 != null) {
                    return new DynamicIslandModuleTextBinding(frameLayout, frameLayout, timerTextEffectView, timerTextEffectView2, viewFindChildViewById, timerTextEffectView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleTextBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleTextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_text, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
