package miui.systemui.dynamicisland.module;

import H0.k;
import H0.s;
import N0.l;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.core.view.OneShotPreDrawListener;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.mi.widget.core.Origin;
import com.mi.widget.shader.CallingShader;
import com.mi.widget.view.FlashLightIcon;
import d.AbstractC0315p;
import d.C0307h;
import d.H;
import d.K;
import g1.AbstractC0360b0;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.dynamicisland.DynamicFeatureConfig;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.model.BigIslandArea;
import miui.systemui.dynamicisland.model.ImageTextInfo;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.PicInfo;
import miui.systemui.dynamicisland.model.SmallIslandArea;
import miui.systemui.dynamicisland.module.IslandIconViewHolder$roundedCornerOutlineProvider$2;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.LottieResUtils;
import miui.systemui.util.PaletteUtils;
import miui.systemui.util.ShaderUtil;
import miui.systemui.util.StaticResUtils;
import miui.systemui.util.VideoResUtils;
import miui.systemui.widget.TextureVideoView;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandIconViewHolder extends BaseIslandModuleViewHolder {
    private static final String TAG = "IslandIconViewHolder";
    private boolean alreadyRemoveFaceRecognition;
    private ImageView appIcon;
    private String currentShaderEffectSrc;
    private DynamicIslandData data;
    private boolean effectEnable;
    private ImageView fixIcon;
    private FlashLightIcon flashIcon;
    private ImageView icon;
    private FrameLayout iconContainer;
    private final E islandScope;
    private TextureVideoView islandVideo;
    private LottieAnimationView lottieBigView;
    private ImageView lottieBigViewStatic;
    private LottieAnimationView lottieView;
    private ImageView lottieViewStatic;
    private float maxFrame;
    private PicInfo picInfo;
    private final H0.d roundedCornerOutlineProvider$delegate;
    private CallingShader<?> shader;
    private ImageView smallIcon;
    private FrameLayout smallTextOverIconContainer;
    public static final Companion Companion = new Companion(null);
    private static int colorPrimary = -1;
    private static int colorSecondary = -1;
    private static int colorTertiary = -1;

    public static final class Companion {

        public static final class lottieValueCallback extends com.airbnb.lottie.value.c {
            public static final lottieValueCallback INSTANCE = new lottieValueCallback();

            private lottieValueCallback() {
            }

            @Override // com.airbnb.lottie.value.c
            public Integer[] getValue(com.airbnb.lottie.value.b frameInfo) {
                n.g(frameInfo, "frameInfo");
                Companion companion = IslandIconViewHolder.Companion;
                return new Integer[]{Integer.valueOf(ColorUtils.setAlphaComponent(companion.getColorTertiary(), 217)), Integer.valueOf(ColorUtils.setAlphaComponent(companion.getColorPrimary(), 179))};
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getColorPrimary() {
            return IslandIconViewHolder.colorPrimary;
        }

        public final int getColorSecondary() {
            return IslandIconViewHolder.colorSecondary;
        }

        public final int getColorTertiary() {
            return IslandIconViewHolder.colorTertiary;
        }

        public final void setColorPrimary(int i2) {
            IslandIconViewHolder.colorPrimary = i2;
        }

        public final void setColorSecondary(int i2) {
            IslandIconViewHolder.colorSecondary = i2;
        }

        public final void setColorTertiary(int i2) {
            IslandIconViewHolder.colorTertiary = i2;
        }

        private Companion() {
        }
    }

    public interface Factory {
        IslandIconViewHolder create(ViewGroup viewGroup, Function2 function2);
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandIconViewHolder$setFixIcon$2, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.module.IslandIconViewHolder$setFixIcon$2", f = "IslandIconViewHolder.kt", l = {238}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        final /* synthetic */ Drawable $drawable;
        int label;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandIconViewHolder$setFixIcon$2$1, reason: invalid class name */
        @N0.f(c = "miui.systemui.dynamicisland.module.IslandIconViewHolder$setFixIcon$2$1", f = "IslandIconViewHolder.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends l implements Function2 {
            final /* synthetic */ Drawable $drawable;
            int label;
            final /* synthetic */ IslandIconViewHolder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(IslandIconViewHolder islandIconViewHolder, Drawable drawable, L0.d dVar) {
                super(2, dVar);
                this.this$0 = islandIconViewHolder;
                this.$drawable = drawable;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new AnonymousClass1(this.this$0, this.$drawable, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                this.this$0.setLottieColor(DrawableKt.toBitmap$default(this.$drawable, 0, 0, null, 7, null));
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Drawable drawable, L0.d dVar) {
            super(2, dVar);
            this.$drawable = drawable;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return IslandIconViewHolder.this.new AnonymousClass2(this.$drawable, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(IslandIconViewHolder.this, this.$drawable, null);
                this.label = 1;
                if (AbstractC0367f.c(io, anonymousClass1, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IslandIconViewHolder(Context context, @DynamicIsland E scope, ViewGroup rootView, Function2 emitEvent) {
        super(context, rootView, emitEvent);
        n.g(context, "context");
        n.g(scope, "scope");
        n.g(rootView, "rootView");
        n.g(emitEvent, "emitEvent");
        this.maxFrame = -1.0f;
        this.effectEnable = true;
        this.roundedCornerOutlineProvider$delegate = H0.e.b(new IslandIconViewHolder$roundedCornerOutlineProvider$2(context));
        this.islandScope = scope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearFaceRecognition(DynamicIslandData dynamicIslandData) {
        PicInfo picInfo = this.picInfo;
        if (!n.c(picInfo != null ? picInfo.getPic() : null, DynamicIslandConstants.FACE_RECOGNITION_SUCCESS_SMALL)) {
            PicInfo picInfo2 = this.picInfo;
            if (!n.c(picInfo2 != null ? picInfo2.getPic() : null, DynamicIslandConstants.FACE_RECOGNITION_FAILED_SMALL)) {
                return;
            }
        }
        if (this.alreadyRemoveFaceRecognition) {
            return;
        }
        this.alreadyRemoveFaceRecognition = true;
        Log.d(TAG, "clearFaceRecognition destroyBigIsland");
        destroyBigIsland(dynamicIslandData);
        LottieResUtils.INSTANCE.cancelLottieAnimate(this.lottieBigView, false);
        PicInfo picInfo3 = this.picInfo;
        if (picInfo3 == null) {
            return;
        }
        picInfo3.setPic(null);
    }

    private final void destroyBigIsland(DynamicIslandData dynamicIslandData) {
        super.getEmitEvent().invoke(dynamicIslandData, DynamicIslandEvent.DeletedDynamicIsland.INSTANCE);
    }

    private final Icon getIconSafe(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return (Icon) bundle.getParcelable(str, Icon.class);
        } catch (Exception e2) {
            Log.e(TAG, "getIconSafe failed for key=" + str, e2);
            return null;
        }
    }

    private final IslandIconViewHolder$roundedCornerOutlineProvider$2.AnonymousClass1 getRoundedCornerOutlineProvider() {
        return (IslandIconViewHolder$roundedCornerOutlineProvider$2.AnonymousClass1) this.roundedCornerOutlineProvider$delegate.getValue();
    }

    private final Drawable loadDrawableSafe(Icon icon, Context context) {
        if (icon == null) {
            return null;
        }
        try {
            return icon.loadDrawable(context);
        } catch (Exception e2) {
            Log.e(TAG, "loadDrawableSafe failed", e2);
            return null;
        }
    }

    private final void playAnimation() {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(0);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        LottieAnimationView lottieAnimationView3 = this.lottieView;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView4 = this.lottieView;
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.clearAnimation();
        }
        LottieResUtils lottieResUtils = LottieResUtils.INSTANCE;
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        PicInfo picInfo2 = this.picInfo;
        Integer numValueOf = picInfo2 != null ? Integer.valueOf(picInfo2.getNumber()) : null;
        n.d(numValueOf);
        AbstractC0315p.s(getContext(), lottieResUtils.getLottieRes(pic, numValueOf.intValue())).d(new H() { // from class: miui.systemui.dynamicisland.module.b
            @Override // d.H
            public final void onResult(Object obj) {
                IslandIconViewHolder.playAnimation$lambda$5(this.f5721a, (C0307h) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void playAnimation$lambda$5(miui.systemui.dynamicisland.module.IslandIconViewHolder r4, d.C0307h r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.n.g(r4, r0)
            com.airbnb.lottie.LottieAnimationView r0 = r4.lottieView
            if (r0 == 0) goto Lc
            r0.setComposition(r5)
        Lc:
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            r0 = 0
            if (r5 == 0) goto L16
            float r5 = r5.getMaxFrame()
            goto L17
        L16:
            r5 = r0
        L17:
            r4.maxFrame = r5
            miui.systemui.dynamicisland.model.PicInfo r5 = r4.picInfo
            r1 = 0
            if (r5 == 0) goto L27
            int r5 = r5.getNumber()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L28
        L27:
            r5 = r1
        L28:
            r2 = 1
            if (r5 == 0) goto L5f
            miui.systemui.dynamicisland.model.PicInfo r5 = r4.picInfo
            if (r5 == 0) goto L38
            int r5 = r5.getNumber()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L39
        L38:
            r5 = r1
        L39:
            kotlin.jvm.internal.n.d(r5)
            int r5 = r5.intValue()
            if (r5 <= 0) goto L5f
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            if (r5 != 0) goto L47
            goto L68
        L47:
            miui.systemui.dynamicisland.model.PicInfo r3 = r4.picInfo
            if (r3 == 0) goto L53
            int r1 = r3.getNumber()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L53:
            kotlin.jvm.internal.n.d(r1)
            int r1 = r1.intValue()
            int r1 = r1 - r2
            r5.setRepeatCount(r1)
            goto L68
        L5f:
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            if (r5 != 0) goto L64
            goto L68
        L64:
            r1 = -1
            r5.setRepeatCount(r1)
        L68:
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            if (r5 != 0) goto L6d
            goto L70
        L6d:
            r5.setRepeatMode(r2)
        L70:
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            if (r5 != 0) goto L75
            goto L78
        L75:
            r5.setProgress(r0)
        L78:
            miui.systemui.dynamicisland.model.PicInfo r5 = r4.picInfo
            r0 = 0
            if (r5 == 0) goto L88
            java.lang.Boolean r5 = r5.getAutoplay()
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            boolean r5 = kotlin.jvm.internal.n.c(r5, r1)
            goto L89
        L88:
            r5 = r0
        L89:
            if (r5 != 0) goto La6
            boolean r5 = miui.systemui.util.CommonUtils.NOT_SUPPORT_LOTTIE
            if (r5 == 0) goto L9e
            miui.systemui.dynamicisland.model.PicInfo r5 = r4.picInfo
            if (r5 == 0) goto L9a
            int r5 = r5.getNumber()
            if (r5 != r2) goto L9a
            goto L9b
        L9a:
            r2 = r0
        L9b:
            if (r2 != 0) goto L9e
            goto La6
        L9e:
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            if (r5 == 0) goto Lad
            r5.playAnimation()
            goto Lad
        La6:
            com.airbnb.lottie.LottieAnimationView r5 = r4.lottieView
            if (r5 == 0) goto Lad
            r5.pauseAnimation()
        Lad:
            r4.registerLottieCallback()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.module.IslandIconViewHolder.playAnimation$lambda$5(miui.systemui.dynamicisland.module.IslandIconViewHolder, d.h):void");
    }

    private final void playBigAnimation(final DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(0);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        LottieAnimationView lottieAnimationView3 = this.lottieBigView;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.removeAllAnimatorListeners();
        }
        LottieAnimationView lottieAnimationView4 = this.lottieBigView;
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView5 = this.lottieBigView;
        if (lottieAnimationView5 != null) {
            lottieAnimationView5.clearAnimation();
        }
        LottieResUtils lottieResUtils = LottieResUtils.INSTANCE;
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        PicInfo picInfo2 = this.picInfo;
        Integer numValueOf = picInfo2 != null ? Integer.valueOf(picInfo2.getNumber()) : null;
        n.d(numValueOf);
        AbstractC0315p.s(getContext(), lottieResUtils.getLottieRes(pic, numValueOf.intValue())).d(new H() { // from class: miui.systemui.dynamicisland.module.d
            @Override // d.H
            public final void onResult(Object obj) {
                IslandIconViewHolder.playBigAnimation$lambda$6(this.f5722a, dynamicIslandData, (C0307h) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x009d, code lost:
    
        if ((r6 != null && r6.getNumber() == 1) == false) goto L73;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void playBigAnimation$lambda$6(final miui.systemui.dynamicisland.module.IslandIconViewHolder r4, final com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData r5, d.C0307h r6) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.module.IslandIconViewHolder.playBigAnimation$lambda$6(miui.systemui.dynamicisland.module.IslandIconViewHolder, com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData, d.h):void");
    }

    private final void registerLottieCallback() {
        LottieResUtils lottieResUtils = LottieResUtils.INSTANCE;
        PicInfo picInfo = this.picInfo;
        if (!lottieResUtils.isNeedUpdateColor(picInfo != null ? picInfo.getPic() : null) || this.lottieView == null) {
            return;
        }
        i.e eVar = new i.e("**", "Gradient Fill 1");
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.addValueCallback(eVar, K.f3783L, Companion.lottieValueCallback.INSTANCE);
        }
    }

    private final void setAppIcon(DynamicIslandData dynamicIslandData) {
        Bundle extras;
        Bundle extras2;
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        if (!n.c(getModule(), DynamicIslandConstants.MODULE_IMAGE_TEXT_1) && !n.c(getModule(), DynamicIslandConstants.MODULE_IMAGE_TEXT_3) && !n.c(getModule(), DynamicIslandConstants.MODULE_PIC_SMALL_ISLAND) && !n.c(getModule(), DynamicIslandConstants.MODULE_COMBINE_PIC)) {
            FrameLayout frameLayout = this.iconContainer;
            if (frameLayout == null) {
                return;
            }
            frameLayout.setVisibility(8);
            return;
        }
        ImageView imageView = this.appIcon;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        ImageView imageView2 = this.icon;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView3 = this.lottieViewStatic;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView4 = this.lottieBigViewStatic;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        Integer numValueOf = null;
        String string = (dynamicIslandData == null || (extras2 = dynamicIslandData.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        if (dynamicIslandData != null && (extras = dynamicIslandData.getExtras()) != null) {
            numValueOf = Integer.valueOf(extras.getInt("miui.user.id"));
        }
        Drawable appIcon = DynamicIslandUtils.INSTANCE.getAppIcon(getContext(), string, numValueOf);
        if (appIcon != null) {
            appIcon.setAutoMirrored(false);
        }
        if (appIcon != null) {
            ImageView imageView7 = this.appIcon;
            if (imageView7 != null) {
                imageView7.setImageDrawable(appIcon);
                return;
            }
            return;
        }
        FrameLayout frameLayout2 = this.iconContainer;
        if (frameLayout2 == null) {
            return;
        }
        frameLayout2.setVisibility(8);
    }

    private final void setFixIcon(DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(0);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        Bundle bitmapBundle = getBitmapBundle();
        Icon iconSafe = bitmapBundle != null ? getIconSafe(bitmapBundle, pic) : null;
        Drawable drawableLoadDrawableSafe = iconSafe != null ? loadDrawableSafe(iconSafe, getContext()) : null;
        Log.d(TAG, "setFixIcon: key: " + pic + ", pic: " + iconSafe);
        if (iconSafe == null || drawableLoadDrawableSafe == null) {
            setAppIcon(dynamicIslandData);
            return;
        }
        drawableLoadDrawableSafe.setAutoMirrored(false);
        ImageView imageView7 = this.fixIcon;
        if (imageView7 != null) {
            imageView7.setImageDrawable(drawableLoadDrawableSafe);
            imageView7.setOutlineProvider(getRoundedCornerOutlineProvider());
            imageView7.setClipToOutline(true);
            imageView7.invalidate();
        }
        if (LottieResUtils.INSTANCE.isNeedToGetLottieColor(pic)) {
            AbstractC0369g.b(this.islandScope, null, null, new AnonymousClass2(drawableLoadDrawableSafe, null), 3, null);
        }
        ImageView imageView8 = this.fixIcon;
        if (imageView8 == null) {
            return;
        }
        PicInfo picInfo2 = this.picInfo;
        imageView8.setContentDescription(picInfo2 != null ? picInfo2.getContentDescription() : null);
    }

    private final void setIcon(DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(0);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        Bundle bitmapBundle = getBitmapBundle();
        Icon iconSafe = bitmapBundle != null ? getIconSafe(bitmapBundle, pic) : null;
        Drawable drawableLoadDrawableSafe = iconSafe != null ? loadDrawableSafe(iconSafe, getContext()) : null;
        Log.d(TAG, "setIcon: key: " + pic + ", pic: " + iconSafe);
        if (iconSafe == null || drawableLoadDrawableSafe == null) {
            setAppIcon(dynamicIslandData);
            return;
        }
        drawableLoadDrawableSafe.setAutoMirrored(false);
        int intrinsicWidth = (drawableLoadDrawableSafe.getIntrinsicWidth() * ((int) getContext().getResources().getDimension(R.dimen.island_fix_icon_size))) / drawableLoadDrawableSafe.getIntrinsicHeight();
        ImageView imageView7 = this.icon;
        ViewGroup.LayoutParams layoutParams = imageView7 != null ? imageView7.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.width = intrinsicWidth;
        }
        ImageView imageView8 = this.icon;
        if (imageView8 != null) {
            imageView8.setImageDrawable(drawableLoadDrawableSafe);
        }
        ImageView imageView9 = this.icon;
        if (imageView9 == null) {
            return;
        }
        PicInfo picInfo2 = this.picInfo;
        imageView9.setContentDescription(picInfo2 != null ? picInfo2.getContentDescription() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLottieColor(Bitmap bitmap) {
        PaletteUtils.extractTopThreeTargetColors(bitmap, new PaletteUtils.OnThreeColorsExtractedListener() { // from class: miui.systemui.dynamicisland.module.c
            @Override // miui.systemui.util.PaletteUtils.OnThreeColorsExtractedListener
            public final void onColorsExtracted(Integer num, Integer num2, Integer num3) {
                IslandIconViewHolder.setLottieColor$lambda$1(num, num2, num3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setLottieColor$lambda$1(Integer num, Integer num2, Integer num3) {
        if (num != null) {
            colorPrimary = num.intValue();
        }
        if (num2 != null) {
            colorSecondary = num2.intValue();
        }
        if (num3 != null) {
            colorTertiary = num3.intValue();
        }
    }

    private final void setMP4Icon(DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(0);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(0);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        PicInfo picInfo2 = this.picInfo;
        Log.d(TAG, "setMP4Icon: " + (picInfo2 != null ? picInfo2.getPic() : null) + " " + pic);
        if (pic == null) {
            setAppIcon(dynamicIslandData);
            return;
        }
        TextureVideoView textureVideoView2 = this.islandVideo;
        if (textureVideoView2 != null) {
            textureVideoView2.setVideoURI(Uri.parse(VideoResUtils.INSTANCE.getVideoRes(pic, getContext())));
        }
        TextureVideoView textureVideoView3 = this.islandVideo;
        if (textureVideoView3 != null) {
            textureVideoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: miui.systemui.dynamicisland.module.e
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    IslandIconViewHolder.setMP4Icon$lambda$2(this.f5724a, mediaPlayer);
                }
            });
        }
        TextureVideoView textureVideoView4 = this.islandVideo;
        if (textureVideoView4 != null) {
            textureVideoView4.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: miui.systemui.dynamicisland.module.f
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                    return IslandIconViewHolder.setMP4Icon$lambda$3(this.f5725a, mediaPlayer, i2, i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setMP4Icon$lambda$2(IslandIconViewHolder this$0, MediaPlayer mediaPlayer) {
        n.g(this$0, "this$0");
        PicInfo picInfo = this$0.picInfo;
        mediaPlayer.setLooping(picInfo != null ? n.c(picInfo.getLoop(), Boolean.TRUE) : false);
        TextureVideoView textureVideoView = this$0.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setMP4Icon$lambda$3(IslandIconViewHolder this$0, MediaPlayer mediaPlayer, int i2, int i3) {
        n.g(this$0, "this$0");
        TextureVideoView textureVideoView = this$0.islandVideo;
        if (textureVideoView == null) {
            return true;
        }
        textureVideoView.stopPlayback();
        return true;
    }

    private final void setSmallIcon(DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(0);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        Bundle bitmapBundle = getBitmapBundle();
        Icon iconSafe = bitmapBundle != null ? getIconSafe(bitmapBundle, pic) : null;
        Drawable drawableLoadDrawableSafe = iconSafe != null ? loadDrawableSafe(iconSafe, getContext()) : null;
        Log.d(TAG, "setSmallIcon: key: " + pic + ", pic: " + iconSafe);
        if (iconSafe == null || drawableLoadDrawableSafe == null) {
            setAppIcon(dynamicIslandData);
            return;
        }
        drawableLoadDrawableSafe.setAutoMirrored(false);
        ImageView imageView7 = this.smallIcon;
        if (imageView7 != null) {
            imageView7.setImageDrawable(drawableLoadDrawableSafe);
            imageView7.setOutlineProvider(getRoundedCornerOutlineProvider());
            imageView7.setClipToOutline(true);
            imageView7.invalidate();
        }
        ImageView imageView8 = this.smallIcon;
        if (imageView8 == null) {
            return;
        }
        PicInfo picInfo2 = this.picInfo;
        imageView8.setContentDescription(picInfo2 != null ? picInfo2.getContentDescription() : null);
    }

    private final void showBigStaticImage(DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        LottieAnimationView lottieAnimationView3 = this.lottieBigView;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView4 = this.lottieBigView;
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.clearAnimation();
        }
        StaticResUtils staticResUtils = StaticResUtils.INSTANCE;
        PicInfo picInfo = this.picInfo;
        int staticRes = staticResUtils.getStaticRes(picInfo != null ? picInfo.getPic() : null);
        ImageView imageView7 = this.lottieBigViewStatic;
        if (imageView7 != null) {
            imageView7.setImageResource(staticRes);
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    private final void showShaderIcon(DynamicIslandData dynamicIslandData) {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.fixIcon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.smallIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        PicInfo picInfo = this.picInfo;
        String pic = picInfo != null ? picInfo.getPic() : null;
        if (n.c(pic, "flash_light_icon")) {
            FlashLightIcon flashLightIcon = this.flashIcon;
            if (flashLightIcon != null) {
                flashLightIcon.setVisibility(0);
            }
            ImageView imageView6 = this.icon;
            if (imageView6 != null) {
                imageView6.setVisibility(8);
            }
        } else if (n.c(pic, NotificationCompat.CATEGORY_CALL)) {
            FlashLightIcon flashLightIcon2 = this.flashIcon;
            if (flashLightIcon2 != null) {
                flashLightIcon2.setVisibility(8);
            }
            ImageView imageView7 = this.icon;
            if (imageView7 != null) {
                imageView7.setVisibility(0);
            }
            ImageView imageView8 = this.icon;
            if (imageView8 != null) {
                imageView8.setImageDrawable(getContext().getResources().getDrawable(R.drawable.call_icon));
            }
        } else {
            FlashLightIcon flashLightIcon3 = this.flashIcon;
            if (flashLightIcon3 != null) {
                flashLightIcon3.setVisibility(8);
            }
            ImageView imageView9 = this.icon;
            if (imageView9 != null) {
                imageView9.setVisibility(0);
            }
            setIcon(dynamicIslandData);
        }
        if (getRootView().getParent() == null || !this.effectEnable) {
            return;
        }
        ViewParent parent = getRootView().getParent();
        n.e(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        final ViewGroup viewGroup = (ViewGroup) parent;
        OneShotPreDrawListener.add(viewGroup, new Runnable() { // from class: miui.systemui.dynamicisland.module.IslandIconViewHolder$showShaderIcon$$inlined$doOnPreDraw$1
            @Override // java.lang.Runnable
            public final void run() {
                PicInfo picInfo2;
                String effectSrc;
                Drawable drawable;
                Rect bounds;
                Drawable drawable2;
                Rect bounds2;
                if (!DynamicFeatureConfig.INSTANCE.getFEATURE_DYNAMIC_ISLAND_SHADER() || (picInfo2 = this.getPicInfo()) == null || (effectSrc = picInfo2.getEffectSrc()) == null) {
                    return;
                }
                if (this.shader == null || this.currentShaderEffectSrc == null || !n.c(effectSrc, this.currentShaderEffectSrc)) {
                    this.currentShaderEffectSrc = effectSrc;
                    float fWidth = 0.0f;
                    if (n.c(effectSrc, NotificationCompat.CATEGORY_CALL)) {
                        IslandIconViewHolder islandIconViewHolder = this;
                        ShaderUtil shaderUtil = ShaderUtil.INSTANCE;
                        ViewGroup viewGroup2 = viewGroup;
                        ImageView imageView10 = islandIconViewHolder.icon;
                        if (imageView10 != null && (drawable2 = imageView10.getDrawable()) != null && (bounds2 = drawable2.getBounds()) != null) {
                            fWidth = bounds2.width();
                        }
                        islandIconViewHolder.shader = shaderUtil.setShader(effectSrc, viewGroup2, fWidth, this.getContext().getResources().getDimension(R.dimen.island_area_padding), Origin.START, false);
                        CallingShader callingShader = this.shader;
                        if (callingShader != null) {
                            callingShader.start();
                            return;
                        }
                        return;
                    }
                    IslandIconViewHolder islandIconViewHolder2 = this;
                    ShaderUtil shaderUtil2 = ShaderUtil.INSTANCE;
                    ViewGroup viewGroup3 = viewGroup;
                    ImageView imageView11 = islandIconViewHolder2.icon;
                    if (imageView11 != null && (drawable = imageView11.getDrawable()) != null && (bounds = drawable.getBounds()) != null) {
                        fWidth = bounds.width();
                    }
                    islandIconViewHolder2.shader = ShaderUtil.setShader$default(shaderUtil2, effectSrc, viewGroup3, fWidth, this.getContext().getResources().getDimension(R.dimen.island_area_padding), Origin.END, false, 32, null);
                    CallingShader callingShader2 = this.shader;
                    if (callingShader2 != null) {
                        callingShader2.start();
                    }
                }
            }
        });
    }

    private final void showStaticImage() {
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.lottieViewStatic;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        LottieAnimationView lottieAnimationView2 = this.lottieBigView;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(8);
        }
        ImageView imageView2 = this.lottieBigViewStatic;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.setVisibility(8);
        }
        ImageView imageView3 = this.appIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        ImageView imageView4 = this.icon;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = this.fixIcon;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        ImageView imageView6 = this.smallIcon;
        if (imageView6 != null) {
            imageView6.setVisibility(8);
        }
        FlashLightIcon flashLightIcon = this.flashIcon;
        if (flashLightIcon != null) {
            flashLightIcon.setVisibility(8);
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        LottieAnimationView lottieAnimationView3 = this.lottieView;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView4 = this.lottieView;
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.clearAnimation();
        }
        StaticResUtils staticResUtils = StaticResUtils.INSTANCE;
        PicInfo picInfo = this.picInfo;
        int staticRes = staticResUtils.getStaticRes(picInfo != null ? picInfo.getPic() : null);
        ImageView imageView7 = this.lottieViewStatic;
        if (imageView7 != null) {
            imageView7.setImageResource(staticRes);
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void bind(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        PicInfo picInfo;
        ImageTextInfo imageTextInfoRight;
        Integer type;
        Integer numValueOf;
        Integer type2;
        Integer type3;
        Integer type4;
        Integer type5;
        Integer type6;
        Integer type7;
        PicInfo picInfo2;
        ImageTextInfo imageTextInfoLeft;
        FlashLightIcon flashLightIcon;
        n.g(template, "template");
        super.bind(template, dynamicIslandData);
        LottieResUtils lottieResUtils = LottieResUtils.INSTANCE;
        lottieResUtils.cancelLottieAnimate(this.lottieBigView, false);
        lottieResUtils.cancelLottieAnimate(this.lottieView, false);
        this.alreadyRemoveFaceRecognition = false;
        this.data = dynamicIslandData;
        if (n.c(getModule(), DynamicIslandConstants.MODULE_PIC_SMALL_ISLAND)) {
            this.effectEnable = false;
            SmallIslandArea smallIslandArea = template.getSmallIslandArea();
            if (smallIslandArea == null || (picInfo2 = smallIslandArea.getPicInfo()) == null) {
                BigIslandArea bigIslandArea = template.getBigIslandArea();
                picInfo2 = (bigIslandArea == null || (imageTextInfoLeft = bigIslandArea.getImageTextInfoLeft()) == null) ? null : imageTextInfoLeft.getPicInfo();
                if (picInfo2 == null) {
                    picInfo2 = new PicInfo();
                }
            }
            this.picInfo = picInfo2;
            FrameLayout frameLayout = this.iconContainer;
            if ((frameLayout != null ? frameLayout.getLayoutParams() : null) instanceof FrameLayout.LayoutParams) {
                FrameLayout frameLayout2 = this.iconContainer;
                ViewGroup.LayoutParams layoutParams = frameLayout2 != null ? frameLayout2.getLayoutParams() : null;
                n.e(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                ((FrameLayout.LayoutParams) layoutParams).gravity = 17;
            }
            PicInfo picInfo3 = this.picInfo;
            if (n.c(picInfo3 != null ? picInfo3.getPic() : null, "flash_light_icon") && (flashLightIcon = this.flashIcon) != null) {
                CommonUtils.INSTANCE.setMarginStart(flashLightIcon, 0, true);
            }
        } else if (n.c(getModule(), DynamicIslandConstants.MODULE_PIC)) {
            BigIslandArea bigIslandArea2 = template.getBigIslandArea();
            this.picInfo = bigIslandArea2 != null ? bigIslandArea2.getPicInfo() : null;
        } else if (n.c(getModule(), DynamicIslandConstants.MODULE_SMALL_TEXT_OVER_ICON)) {
            BigIslandArea bigIslandArea3 = template.getBigIslandArea();
            if (bigIslandArea3 == null || (imageTextInfoRight = bigIslandArea3.getImageTextInfoRight()) == null || (picInfo = imageTextInfoRight.getPicInfo()) == null) {
                picInfo = new PicInfo();
            }
            this.picInfo = picInfo;
            FrameLayout frameLayout3 = this.smallTextOverIconContainer;
            if ((frameLayout3 != null ? frameLayout3.getLayoutParams() : null) instanceof FrameLayout.LayoutParams) {
                FrameLayout frameLayout4 = this.smallTextOverIconContainer;
                ViewGroup.LayoutParams layoutParams2 = frameLayout4 != null ? frameLayout4.getLayoutParams() : null;
                n.e(layoutParams2, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                ((FrameLayout.LayoutParams) layoutParams2).gravity = 17;
            }
        }
        if (this.picInfo == null) {
            FrameLayout frameLayout5 = this.iconContainer;
            if (frameLayout5 == null) {
                return;
            }
            frameLayout5.setVisibility(8);
            return;
        }
        FrameLayout frameLayout6 = this.iconContainer;
        if (frameLayout6 != null) {
            frameLayout6.setVisibility(0);
        }
        PicInfo picInfo4 = this.picInfo;
        if (picInfo4 != null && (type7 = picInfo4.getType()) != null && type7.intValue() == 1) {
            setFixIcon(dynamicIslandData);
            return;
        }
        PicInfo picInfo5 = this.picInfo;
        if (picInfo5 != null && (type6 = picInfo5.getType()) != null && type6.intValue() == 2) {
            PicInfo picInfo6 = this.picInfo;
            String pic = picInfo6 != null ? picInfo6.getPic() : null;
            PicInfo picInfo7 = this.picInfo;
            numValueOf = picInfo7 != null ? Integer.valueOf(picInfo7.getNumber()) : null;
            n.d(numValueOf);
            if (lottieResUtils.isNoNeedToGetLottieRes(pic, numValueOf.intValue())) {
                showStaticImage();
                return;
            } else {
                playAnimation();
                return;
            }
        }
        PicInfo picInfo8 = this.picInfo;
        if (picInfo8 != null && (type5 = picInfo8.getType()) != null && type5.intValue() == 3) {
            showShaderIcon(dynamicIslandData);
            return;
        }
        PicInfo picInfo9 = this.picInfo;
        if (picInfo9 != null && (type4 = picInfo9.getType()) != null && type4.intValue() == 4) {
            setIcon(dynamicIslandData);
            return;
        }
        PicInfo picInfo10 = this.picInfo;
        if (picInfo10 != null && (type3 = picInfo10.getType()) != null && type3.intValue() == 5) {
            setSmallIcon(dynamicIslandData);
            return;
        }
        PicInfo picInfo11 = this.picInfo;
        if (picInfo11 != null && (type2 = picInfo11.getType()) != null && type2.intValue() == 6) {
            setMP4Icon(dynamicIslandData);
            return;
        }
        PicInfo picInfo12 = this.picInfo;
        if (picInfo12 == null || (type = picInfo12.getType()) == null || type.intValue() != 7) {
            setAppIcon(dynamicIslandData);
            return;
        }
        PicInfo picInfo13 = this.picInfo;
        String pic2 = picInfo13 != null ? picInfo13.getPic() : null;
        PicInfo picInfo14 = this.picInfo;
        numValueOf = picInfo14 != null ? Integer.valueOf(picInfo14.getNumber()) : null;
        n.d(numValueOf);
        if (lottieResUtils.isNoNeedToGetLottieRes(pic2, numValueOf.intValue())) {
            showBigStaticImage(dynamicIslandData);
        } else {
            playBigAnimation(dynamicIslandData);
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void diff(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.diff(template, dynamicIslandData);
    }

    public final PicInfo getPicInfo() {
        return this.picInfo;
    }

    public final int getWidth() {
        FrameLayout frameLayout = this.iconContainer;
        Integer numValueOf = frameLayout != null ? Integer.valueOf(frameLayout.getWidth()) : null;
        FrameLayout frameLayout2 = this.iconContainer;
        Integer numValueOf2 = frameLayout2 != null ? Integer.valueOf(frameLayout2.getMeasuredWidth()) : null;
        FrameLayout frameLayout3 = this.iconContainer;
        Log.d(TAG, "getWidth: " + numValueOf + " " + numValueOf2 + " " + (frameLayout3 != null ? Integer.valueOf(frameLayout3.getVisibility()) : null));
        FrameLayout frameLayout4 = this.iconContainer;
        if (frameLayout4 == null || frameLayout4.getVisibility() == 8) {
            return 0;
        }
        Resources resources = getContext().getResources();
        int i2 = R.dimen.island_flash_icon_size;
        frameLayout4.measure(View.MeasureSpec.makeMeasureSpec(resources.getDimensionPixelSize(i2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getContext().getResources().getDimensionPixelSize(i2), Integer.MIN_VALUE));
        return frameLayout4.getMeasuredWidth();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void initView(String module) {
        n.g(module, "module");
        super.initView(module);
        View view = getView();
        this.icon = view != null ? (ImageView) view.findViewById(R.id.island_icon) : null;
        View view2 = getView();
        this.fixIcon = view2 != null ? (ImageView) view2.findViewById(R.id.island_fix_icon) : null;
        View view3 = getView();
        this.smallIcon = view3 != null ? (ImageView) view3.findViewById(R.id.island_small_icon) : null;
        View view4 = getView();
        this.flashIcon = view4 != null ? (FlashLightIcon) view4.findViewById(R.id.island_flash_icon) : null;
        View view5 = getView();
        this.appIcon = view5 != null ? (ImageView) view5.findViewById(R.id.island_app_icon) : null;
        View view6 = getView();
        this.lottieView = view6 != null ? (LottieAnimationView) view6.findViewById(R.id.island_lottie) : null;
        View view7 = getView();
        this.lottieViewStatic = view7 != null ? (ImageView) view7.findViewById(R.id.island_lottie_static) : null;
        View view8 = getView();
        this.lottieBigView = view8 != null ? (LottieAnimationView) view8.findViewById(R.id.island_lottie_bigger) : null;
        View view9 = getView();
        this.lottieBigViewStatic = view9 != null ? (ImageView) view9.findViewById(R.id.island_lottie_bigger_static) : null;
        View view10 = getView();
        this.islandVideo = view10 != null ? (TextureVideoView) view10.findViewById(R.id.island_video) : null;
        View view11 = getView();
        this.iconContainer = view11 != null ? (FrameLayout) view11.findViewById(R.id.island_container_module_icon) : null;
        if (n.c(module, DynamicIslandConstants.MODULE_SMALL_TEXT_OVER_ICON)) {
            View view12 = getView();
            this.smallTextOverIconContainer = view12 != null ? (FrameLayout) view12.findViewById(R.id.island_container_module_text_over_icon) : null;
        }
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onDetach() {
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        TextureVideoView textureVideoView = this.islandVideo;
        if (textureVideoView != null) {
            textureVideoView.stopPlayback();
        }
        LottieResUtils.cancelLottieAnimate$default(LottieResUtils.INSTANCE, this.lottieView, false, 2, null);
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onHidden() {
        TextureVideoView textureVideoView;
        TextureVideoView textureVideoView2;
        Log.d(TAG, "onHidden");
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null && lottieAnimationView.getVisibility() == 0) {
            LottieResUtils.INSTANCE.cancelLottieAnimate(this.lottieView, false);
        }
        clearFaceRecognition(this.data);
        TextureVideoView textureVideoView3 = this.islandVideo;
        if (textureVideoView3 != null && textureVideoView3.getVisibility() == 0 && (textureVideoView = this.islandVideo) != null && textureVideoView.isPlaying() && (textureVideoView2 = this.islandVideo) != null) {
            textureVideoView2.pause();
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null && callingShader != null) {
            callingShader.stop();
        }
        super.onHidden();
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void onShow() {
        TextureVideoView textureVideoView;
        Log.d(TAG, "onShow");
        LottieAnimationView lottieAnimationView = this.lottieView;
        if (lottieAnimationView != null && lottieAnimationView.getVisibility() == 0 && !CommonUtils.NOT_SUPPORT_LOTTIE) {
            playAnimation();
        }
        TextureVideoView textureVideoView2 = this.islandVideo;
        if (textureVideoView2 != null && textureVideoView2.getVisibility() == 0 && (textureVideoView = this.islandVideo) != null) {
            textureVideoView.start();
        }
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null && callingShader != null) {
            callingShader.start();
        }
        super.onShow();
    }

    public final void setPicInfo(PicInfo picInfo) {
        this.picInfo = picInfo;
    }

    @Override // miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder
    public void updatePartial(IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(template, "template");
        super.updatePartial(template, dynamicIslandData);
        bind(template, dynamicIslandData);
    }
}
