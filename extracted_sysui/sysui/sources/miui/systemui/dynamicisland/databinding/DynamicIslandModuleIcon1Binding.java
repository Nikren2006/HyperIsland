package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mi.widget.view.FlashLightIcon;
import com.mi.widget.view.LottieView;
import miui.systemui.dynamicisland.R;
import miui.systemui.widget.TextureVideoView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleIcon1Binding implements ViewBinding {

    @NonNull
    public final ImageView islandAppIcon;

    @NonNull
    public final FrameLayout islandContainerModuleIcon;

    @NonNull
    public final ImageView islandFixIcon;

    @NonNull
    public final FlashLightIcon islandFlashIcon;

    @NonNull
    public final ImageView islandIcon;

    @NonNull
    public final LottieView islandLottie;

    @NonNull
    public final LottieView islandLottieBigger;

    @NonNull
    public final ImageView islandLottieBiggerStatic;

    @NonNull
    public final ImageView islandLottieStatic;

    @NonNull
    public final ImageView islandSmallIcon;

    @NonNull
    public final TextureVideoView islandVideo;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleIcon1Binding(@NonNull FrameLayout frameLayout, @NonNull ImageView imageView, @NonNull FrameLayout frameLayout2, @NonNull ImageView imageView2, @NonNull FlashLightIcon flashLightIcon, @NonNull ImageView imageView3, @NonNull LottieView lottieView, @NonNull LottieView lottieView2, @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull ImageView imageView6, @NonNull TextureVideoView textureVideoView) {
        this.rootView = frameLayout;
        this.islandAppIcon = imageView;
        this.islandContainerModuleIcon = frameLayout2;
        this.islandFixIcon = imageView2;
        this.islandFlashIcon = flashLightIcon;
        this.islandIcon = imageView3;
        this.islandLottie = lottieView;
        this.islandLottieBigger = lottieView2;
        this.islandLottieBiggerStatic = imageView4;
        this.islandLottieStatic = imageView5;
        this.islandSmallIcon = imageView6;
        this.islandVideo = textureVideoView;
    }

    @NonNull
    public static DynamicIslandModuleIcon1Binding bind(@NonNull View view) {
        int i2 = R.id.island_app_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            i2 = R.id.island_fix_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
            if (imageView2 != null) {
                i2 = R.id.island_flash_icon;
                FlashLightIcon flashLightIcon = (FlashLightIcon) ViewBindings.findChildViewById(view, i2);
                if (flashLightIcon != null) {
                    i2 = R.id.island_icon;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i2);
                    if (imageView3 != null) {
                        i2 = R.id.island_lottie;
                        LottieView lottieView = (LottieView) ViewBindings.findChildViewById(view, i2);
                        if (lottieView != null) {
                            i2 = R.id.island_lottie_bigger;
                            LottieView lottieView2 = (LottieView) ViewBindings.findChildViewById(view, i2);
                            if (lottieView2 != null) {
                                i2 = R.id.island_lottie_bigger_static;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i2);
                                if (imageView4 != null) {
                                    i2 = R.id.island_lottie_static;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i2);
                                    if (imageView5 != null) {
                                        i2 = R.id.island_small_icon;
                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i2);
                                        if (imageView6 != null) {
                                            i2 = R.id.island_video;
                                            TextureVideoView textureVideoView = (TextureVideoView) ViewBindings.findChildViewById(view, i2);
                                            if (textureVideoView != null) {
                                                return new DynamicIslandModuleIcon1Binding(frameLayout, imageView, frameLayout, imageView2, flashLightIcon, imageView3, lottieView, lottieView2, imageView4, imageView5, imageView6, textureVideoView);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleIcon1Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleIcon1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_icon_1, viewGroup, false);
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
