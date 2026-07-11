package com.android.systemui.miui.volume;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.media.AudioServiceInjector;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.android.systemui.miui.volume.VolumeColumn;
import com.android.systemui.miui.volume.VolumeColumn$onTouchListener$2;
import com.android.systemui.miui.volume.widget.MediaIconAnim;
import com.android.systemui.plugins.VolumeDialogController;
import com.miui.circulate.device.api.Column;
import com.xiaomi.onetrack.api.ah;
import d1.InterfaceC0330i;
import g1.AbstractC0369g;
import g1.InterfaceC0380l0;
import j1.InterfaceC0419g;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.animation.drawable.SVGUtilsExt;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.VolumeUtils;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeColumn {
    static final /* synthetic */ InterfaceC0330i[] $$delegatedProperties = {kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "stream", "getStream()I", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "ss", "getSs()Lcom/android/systemui/plugins/VolumeDialogController$StreamState;", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "isMute", "isMute()Ljava/lang/Boolean;", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "isLowRatio", "isLowRatio()Ljava/lang/Boolean;", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "isExpanded", "isExpanded()Z", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "sliderRatio", "getSliderRatio()F", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "iconBlendColor", "getIconBlendColor()Lmiuix/theme/token/ColorBlendToken;", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "isInCCMainPage", "isInCCMainPage()Z", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "iconColorRes", "getIconColorRes()Ljava/lang/Integer;", 0)), kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(VolumeColumn.class, "isSupportSuperXiaoai", "isSupportSuperXiaoai()Z", 0))};
    public static final Companion Companion = new Companion(null);
    private static final Map<Integer, j1.u> SliderRatioRatioFlowMap = new LinkedHashMap();
    public static final String TAG = "MIUI_VolumeColumn";
    private ObjectAnimator anim;
    private int animTargetProgress;
    private int cachedIconRes;
    public ImageView icon;
    private final Z0.c iconBlendColor$delegate;
    private final H0.d iconBlendColorTransition$delegate;
    private final Z0.c iconColorRes$delegate;
    private final H0.d iconColorTransition$delegate;
    private int iconMuteRes;
    private int iconRes;
    private int iconState;
    private boolean important;
    private final Z0.c isExpanded$delegate;
    private final Z0.c isInCCMainPage$delegate;
    private final Z0.c isLowRatio$delegate;
    private final Z0.c isMute$delegate;
    private boolean isNeedShowDialog;
    private final Z0.c isSupportSuperXiaoai$delegate;
    private int lastAudibleLevel;
    private boolean lastMuteState;
    private int mVoiceAssistStreamType;
    private final H0.d mainScope$delegate;
    private MediaIconAnim mediaIconAnim;
    private final H0.d onTouchListener$delegate;
    public MiuiVolumeSeekBarProgressView progressView;
    public RoundRectFrameLayout progressViewBg;
    private int radius;
    private int requestedLevel;
    private InterfaceC0380l0 sharedSliderRatioJob;
    public SeekBar slider;
    private final Z0.c sliderRatio$delegate;
    private final Z0.c ss$delegate;
    private final Z0.c stream$delegate;
    private int superAddIndex;
    public TextView superVolume;
    private boolean tracking;
    private long userAttempt;
    public View view;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final j1.u getSliderRatioFlow(int i2) {
            j1.u uVar = (j1.u) VolumeColumn.SliderRatioRatioFlowMap.getOrDefault(Integer.valueOf(i2), j1.K.a(Float.valueOf(-1.0f)));
            VolumeColumn.SliderRatioRatioFlowMap.put(Integer.valueOf(i2), uVar);
            return uVar;
        }

        public final float roundTo3DecimalPlaces(float f2) {
            return ((int) (f2 * 1000)) / 1000.0f;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.VolumeColumn$sharedSliderRatio$1, reason: invalid class name */
    @N0.f(c = "com.android.systemui.miui.volume.VolumeColumn$sharedSliderRatio$1", f = "VolumeColumn.kt", l = {VolumePanelViewController.HAPTIC_V2_VOLUME_MAX}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return VolumeColumn.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                j1.u sliderRatioFlow = VolumeColumn.Companion.getSliderRatioFlow(VolumeColumn.this.getStream());
                final VolumeColumn volumeColumn = VolumeColumn.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: com.android.systemui.miui.volume.VolumeColumn.sharedSliderRatio.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Number) obj2).floatValue(), dVar);
                    }

                    public final Object emit(float f2, L0.d dVar) {
                        if (f2 == -1.0f) {
                            return H0.s.f314a;
                        }
                        Companion companion = VolumeColumn.Companion;
                        if (companion.roundTo3DecimalPlaces(volumeColumn.getSliderRatio()) != companion.roundTo3DecimalPlaces(f2) && !volumeColumn.getTracking()) {
                            Log.d(VolumeColumn.TAG, "On slider ratio change! stream: " + volumeColumn.getStream() + " sliderRatio: " + f2);
                            volumeColumn.getSlider().setProgress((int) (f2 * ((float) volumeColumn.getSlider().getMax())));
                        }
                        return H0.s.f314a;
                    }
                };
                this.label = 1;
                if (sliderRatioFlow.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    public VolumeColumn() {
        Z0.a aVar = Z0.a.f970a;
        final int i2 = Integer.MIN_VALUE;
        this.stream$delegate = new Z0.b(i2) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$1
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Integer num, Integer num2) {
                kotlin.jvm.internal.n.g(property, "property");
                if (num.intValue() != num2.intValue()) {
                    this.resetIconRes();
                }
            }
        };
        final Object obj = null;
        this.ss$delegate = new Z0.b(obj) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$2
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, VolumeDialogController.StreamState streamState, VolumeDialogController.StreamState streamState2) {
                kotlin.jvm.internal.n.g(property, "property");
                VolumeDialogController.StreamState streamState3 = streamState2;
                VolumeColumn volumeColumn = this;
                boolean z2 = true;
                if ((streamState3 == null || !streamState3.muted) && (streamState3 == null || streamState3.level != 0)) {
                    z2 = false;
                }
                volumeColumn.setMute(Boolean.valueOf(z2));
            }
        };
        this.isMute$delegate = new Z0.b(obj) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$3
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Boolean bool, Boolean bool2) {
                kotlin.jvm.internal.n.g(property, "property");
                if (kotlin.jvm.internal.n.c(bool, bool2)) {
                    return;
                }
                this.updateIconColor();
                VolumeColumn.updateMediaIconAnimProgress$default(this, true, 0.0f, 2, null);
            }
        };
        this.requestedLevel = -1;
        this.lastAudibleLevel = 1;
        this.superAddIndex = -1;
        this.isNeedShowDialog = true;
        this.onTouchListener$delegate = H0.e.b(new VolumeColumn$onTouchListener$2(this));
        this.isLowRatio$delegate = new Z0.b(obj) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$4
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Boolean bool, Boolean bool2) {
                kotlin.jvm.internal.n.g(property, "property");
                if (kotlin.jvm.internal.n.c(bool, bool2)) {
                    return;
                }
                this.updateIconColor();
            }
        };
        final Boolean bool = Boolean.FALSE;
        this.isExpanded$delegate = new Z0.b(bool) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$5
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Boolean bool2, Boolean bool3) {
                kotlin.jvm.internal.n.g(property, "property");
                if (bool2.booleanValue() != bool3.booleanValue()) {
                    this.updateIconColor();
                }
            }
        };
        this.mainScope$delegate = H0.e.b(VolumeColumn$mainScope$2.INSTANCE);
        final Float fValueOf = Float.valueOf(-1.0f);
        this.sliderRatio$delegate = new Z0.b(fValueOf) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$6
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Float f2, Float f3) {
                kotlin.jvm.internal.n.g(property, "property");
                float fFloatValue = f3.floatValue();
                if (f2.floatValue() == fFloatValue) {
                    return;
                }
                Log.d(VolumeColumn.TAG, "Send slider ratio! stream: " + this.getStream() + " sliderRatio: " + fFloatValue);
                VolumeColumn volumeColumn = this;
                VolumeColumn.Companion companion = VolumeColumn.Companion;
                volumeColumn.setLowRatio(Boolean.valueOf(companion.roundTo3DecimalPlaces(fFloatValue) < 0.12f));
                VolumeColumn.updateMediaIconAnimProgress$default(this, true, 0.0f, 2, null);
                if (this.getTracking()) {
                    companion.getSliderRatioFlow(this.getStream()).setValue(Float.valueOf(fFloatValue));
                }
            }
        };
        this.iconBlendColorTransition$delegate = H0.e.b(new VolumeColumn$iconBlendColorTransition$2(this));
        this.iconBlendColor$delegate = new Z0.b(obj) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$7
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, ColorBlendToken colorBlendToken, ColorBlendToken colorBlendToken2) {
                kotlin.jvm.internal.n.g(property, "property");
                ColorBlendToken colorBlendToken3 = colorBlendToken2;
                ColorBlendToken colorBlendToken4 = colorBlendToken;
                if (colorBlendToken3 != null) {
                    MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
                    this.getIconBlendColorTransition().transition(colorBlendToken4, colorBlendToken3, (!kotlin.jvm.internal.n.c(colorBlendToken3, miuiColorBlendToken.getVC_ICON_CC_MAIN())) & (!kotlin.jvm.internal.n.c(colorBlendToken4, miuiColorBlendToken.getVC_ICON_CC_MAIN())));
                }
            }
        };
        this.isInCCMainPage$delegate = new Z0.b(bool) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$8
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Boolean bool2, Boolean bool3) {
                kotlin.jvm.internal.n.g(property, "property");
                if (bool2.booleanValue() != bool3.booleanValue()) {
                    this.updateIconColor();
                }
            }
        };
        this.iconColorTransition$delegate = H0.e.b(new VolumeColumn$iconColorTransition$2(this));
        this.iconColorRes$delegate = new Z0.b(obj) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$9
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Integer num, Integer num2) {
                ColorStateList colorStateList;
                ColorStateList colorStateList2;
                kotlin.jvm.internal.n.g(property, "property");
                Integer num3 = num2;
                Integer num4 = num;
                if (num3 != null) {
                    ColorStateList colorStateList3 = ContextCompat.getColorStateList(this.getIcon().getContext(), num3.intValue());
                    if (kotlin.jvm.internal.n.c(num4, num3)) {
                        colorStateList2 = colorStateList3;
                    } else {
                        if (num4 != null) {
                            colorStateList = ContextCompat.getColorStateList(this.getIcon().getContext(), num4.intValue());
                        } else {
                            colorStateList = null;
                        }
                        colorStateList2 = colorStateList;
                    }
                    if (colorStateList3 != null) {
                        MiuiColorTransitionAnim.transition$default(this.getIconColorTransition(), colorStateList2, colorStateList3, false, 4, null);
                    }
                }
            }
        };
        this.isSupportSuperXiaoai$delegate = new Z0.b(bool) { // from class: com.android.systemui.miui.volume.VolumeColumn$special$$inlined$observable$10
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Boolean bool2, Boolean bool3) {
                kotlin.jvm.internal.n.g(property, "property");
                if (bool2.booleanValue() != bool3.booleanValue()) {
                    this.setXiaoAiIconRes();
                }
            }
        };
        this.mVoiceAssistStreamType = -2;
    }

    private final ColorBlendToken getIconBlendColor() {
        return (ColorBlendToken) this.iconBlendColor$delegate.getValue(this, $$delegatedProperties[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiuiColorTransitionAnim<ColorBlendToken> getIconBlendColorTransition() {
        return (MiuiColorTransitionAnim) this.iconBlendColorTransition$delegate.getValue();
    }

    private final Integer getIconColorRes() {
        return (Integer) this.iconColorRes$delegate.getValue(this, $$delegatedProperties[8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MiuiColorTransitionAnim<ColorStateList> getIconColorTransition() {
        return (MiuiColorTransitionAnim) this.iconColorTransition$delegate.getValue();
    }

    private final g1.E getMainScope() {
        return (g1.E) this.mainScope$delegate.getValue();
    }

    private final VolumeColumn$onTouchListener$2.AnonymousClass1 getOnTouchListener() {
        return (VolumeColumn$onTouchListener$2.AnonymousClass1) this.onTouchListener$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getSliderRatio() {
        return ((Number) this.sliderRatio$delegate.getValue(this, $$delegatedProperties[5])).floatValue();
    }

    private final int getVoiceAssistStreamType() {
        if (this.mVoiceAssistStreamType == -2) {
            this.mVoiceAssistStreamType = AudioServiceInjector.getVoiceAssistNum();
        }
        return this.mVoiceAssistStreamType;
    }

    private final Boolean isLowRatio() {
        return (Boolean) this.isLowRatio$delegate.getValue(this, $$delegatedProperties[3]);
    }

    private final Boolean isMute() {
        return (Boolean) this.isMute$delegate.getValue(this, $$delegatedProperties[2]);
    }

    private final H0.s releaseMediaIconAnim() {
        MediaIconAnim mediaIconAnim = this.mediaIconAnim;
        if (mediaIconAnim == null) {
            return null;
        }
        mediaIconAnim.release();
        this.mediaIconAnim = null;
        return H0.s.f314a;
    }

    private final void setAccessibilityIconRes() {
        this.iconRes = R.drawable.ic_miui_volume_accessibility;
        this.iconMuteRes = R.drawable.ic_miui_volume_accessibility_mute;
    }

    private final void setIconBlendColor(ColorBlendToken colorBlendToken) {
        this.iconBlendColor$delegate.setValue(this, $$delegatedProperties[6], colorBlendToken);
    }

    private final void setIconColorRes(Integer num) {
        this.iconColorRes$delegate.setValue(this, $$delegatedProperties[8], num);
    }

    private final void setIconParma(boolean z2) {
        ViewGroup.LayoutParams layoutParams = getIcon().getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        Context context = getIcon().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        int iconSize = VolumeColumnRes.getIconSize(context, this.isNeedShowDialog);
        marginLayoutParams.height = iconSize;
        marginLayoutParams.width = iconSize;
        Context context2 = getIcon().getContext();
        kotlin.jvm.internal.n.f(context2, "getContext(...)");
        marginLayoutParams.bottomMargin = VolumeColumnRes.getIconMarginBottom(context2, z2, this.isNeedShowDialog);
        getIcon().setLayoutParams(marginLayoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLowRatio(Boolean bool) {
        this.isLowRatio$delegate.setValue(this, $$delegatedProperties[3], bool);
    }

    private final void setMargin(boolean z2, boolean z3) {
        ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.leftMargin = 0;
        Context context = getView().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        marginLayoutParams.rightMargin = VolumeColumnRes.getMarginRight(context, z2, this.isNeedShowDialog, z3, getStream());
        getView().setLayoutParams(marginLayoutParams);
    }

    public static /* synthetic */ void setMediaIconRes$default(VolumeColumn volumeColumn, boolean z2, int i2, boolean z3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z2 = false;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MIN_VALUE;
        }
        if ((i3 & 4) != 0) {
            z3 = false;
        }
        volumeColumn.setMediaIconRes(z2, i2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMute(Boolean bool) {
        this.isMute$delegate.setValue(this, $$delegatedProperties[2], bool);
    }

    private final void setSliderRatio(float f2) {
        this.sliderRatio$delegate.setValue(this, $$delegatedProperties[5], Float.valueOf(f2));
    }

    public static /* synthetic */ boolean setSliderTintColorList$default(VolumeColumn volumeColumn, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        return volumeColumn.setSliderTintColorList(z2);
    }

    private final void setSuperVolumeParma() {
        ViewGroup.LayoutParams layoutParams = getSuperVolume().getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        Context context = getSuperVolume().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        marginLayoutParams.topMargin = VolumeColumnRes.getSuperVolumeTextMarginTo(context, this.isNeedShowDialog);
        getSuperVolume().setLayoutParams(marginLayoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setXiaoAiIconRes() {
        this.iconRes = isSupportSuperXiaoai() ? R.drawable.ic_miui_volume_super_xiaoai : R.drawable.ic_miui_volume_assist;
        this.iconMuteRes = isSupportSuperXiaoai() ? R.drawable.ic_miui_volume_super_xiaoai_mute : R.drawable.ic_miui_volume_assist_mute;
    }

    private final void sharedSliderRatio() {
        InterfaceC0380l0 interfaceC0380l0 = this.sharedSliderRatioJob;
        if (interfaceC0380l0 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        }
        this.sharedSliderRatioJob = AbstractC0369g.b(getMainScope(), null, null, new AnonymousClass1(null), 3, null);
    }

    public static /* synthetic */ void updateIcon$default(VolumeColumn volumeColumn, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        volumeColumn.updateIcon(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateIconColor() {
        int stream = getStream();
        boolean zIsExpanded = isExpanded();
        boolean z2 = this.isNeedShowDialog;
        Boolean boolIsMute = isMute();
        Boolean boolIsLowRatio = isLowRatio();
        boolean zIsInCCMainPage = isInCCMainPage();
        Context context = getIcon().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        Log.d(TAG, "updateIconColor: stream: " + stream + " isExpanded: " + zIsExpanded + ", isNeedShowDialog: " + z2 + ", isMute: " + boolIsMute + ", isLowRatio: " + boolIsLowRatio + ", isInCCMainPage: " + zIsInCCMainPage + " isBlur: " + MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context));
        Context context2 = getIcon().getContext();
        kotlin.jvm.internal.n.f(context2, "getContext(...)");
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context2)) {
            VolumeColumnRes volumeColumnRes = VolumeColumnRes.INSTANCE;
            Boolean boolIsMute2 = isMute();
            Boolean bool = Boolean.TRUE;
            setIconColorRes(Integer.valueOf(volumeColumnRes.getIconColorRes(kotlin.jvm.internal.n.c(boolIsMute2, bool), kotlin.jvm.internal.n.c(isLowRatio(), bool), this.isNeedShowDialog)));
            return;
        }
        boolean zIsExpanded2 = isExpanded();
        boolean z3 = this.isNeedShowDialog;
        Boolean boolIsMute3 = isMute();
        Boolean bool2 = Boolean.TRUE;
        setIconBlendColor(VolumeColumnRes.getIconBlendColor(zIsExpanded2, z3, kotlin.jvm.internal.n.c(boolIsMute3, bool2), kotlin.jvm.internal.n.c(isLowRatio(), bool2), isInCCMainPage()));
    }

    private final H0.s updateMediaIconAnimProgress(boolean z2, float f2) {
        MediaIconAnim mediaIconAnim = this.mediaIconAnim;
        if (mediaIconAnim == null) {
            return null;
        }
        float max = getSlider().getMax();
        VolumeDialogController.StreamState ss = getSs();
        mediaIconAnim.updateMediaIconAnimProgress(z2, max, f2, ss != null && ss.muted, getView().getVisibility() == 0);
        return H0.s.f314a;
    }

    public static /* synthetic */ H0.s updateMediaIconAnimProgress$default(VolumeColumn volumeColumn, boolean z2, float f2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f2 = volumeColumn.getSlider().getProgress();
        }
        return volumeColumn.updateMediaIconAnimProgress(z2, f2);
    }

    public final ObjectAnimator getAnim() {
        return this.anim;
    }

    public final int getAnimTargetProgress() {
        return this.animTargetProgress;
    }

    public final ImageView getIcon() {
        ImageView imageView = this.icon;
        if (imageView != null) {
            return imageView;
        }
        kotlin.jvm.internal.n.w(Column.ICON);
        return null;
    }

    public final int getIconMuteRes() {
        return this.iconMuteRes;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final int getIconState() {
        return this.iconState;
    }

    public final boolean getImportant() {
        return this.important;
    }

    public final int getLastAudibleLevel() {
        return this.lastAudibleLevel;
    }

    public final boolean getLastMuteState() {
        return this.lastMuteState;
    }

    public final MiuiVolumeSeekBarProgressView getProgressView() {
        MiuiVolumeSeekBarProgressView miuiVolumeSeekBarProgressView = this.progressView;
        if (miuiVolumeSeekBarProgressView != null) {
            return miuiVolumeSeekBarProgressView;
        }
        kotlin.jvm.internal.n.w("progressView");
        return null;
    }

    public final RoundRectFrameLayout getProgressViewBg() {
        RoundRectFrameLayout roundRectFrameLayout = this.progressViewBg;
        if (roundRectFrameLayout != null) {
            return roundRectFrameLayout;
        }
        kotlin.jvm.internal.n.w("progressViewBg");
        return null;
    }

    public final int getRadius() {
        return this.radius;
    }

    public final int getRequestedLevel() {
        return this.requestedLevel;
    }

    public final SeekBar getSlider() {
        SeekBar seekBar = this.slider;
        if (seekBar != null) {
            return seekBar;
        }
        kotlin.jvm.internal.n.w("slider");
        return null;
    }

    public final VolumeDialogController.StreamState getSs() {
        return (VolumeDialogController.StreamState) this.ss$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final int getStream() {
        return ((Number) this.stream$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    public final int getSuperAddIndex() {
        return this.superAddIndex;
    }

    public final TextView getSuperVolume() {
        TextView textView = this.superVolume;
        if (textView != null) {
            return textView;
        }
        kotlin.jvm.internal.n.w("superVolume");
        return null;
    }

    public final boolean getTracking() {
        return this.tracking;
    }

    public final long getUserAttempt() {
        return this.userAttempt;
    }

    public final View getView() {
        View view = this.view;
        if (view != null) {
            return view;
        }
        kotlin.jvm.internal.n.w(ah.ae);
        return null;
    }

    public final void initColumn(Context mContext, ViewGroup parent, int i2, boolean z2, boolean z3, boolean z4) {
        kotlin.jvm.internal.n.g(mContext, "mContext");
        kotlin.jvm.internal.n.g(parent, "parent");
        this.isNeedShowDialog = z3;
        setExpanded(z4);
        View viewInflate = LayoutInflater.from(mContext).inflate(R.layout.miui_volume_dialog_column, parent, false);
        kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
        setView(viewInflate);
        setStream(i2);
        this.important = z2;
        getView().setId(i2);
        getView().setTag(this);
        getView().setOnTouchListener(getOnTouchListener());
        View viewFindViewById = getView().findViewById(R.id.volume_column_slider);
        kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
        setSlider((SeekBar) viewFindViewById);
        View viewFindViewById2 = getView().findViewById(R.id.volume_progress_view);
        kotlin.jvm.internal.n.f(viewFindViewById2, "findViewById(...)");
        setProgressView((MiuiVolumeSeekBarProgressView) viewFindViewById2);
        View viewFindViewById3 = getView().findViewById(R.id.volume_column_view);
        kotlin.jvm.internal.n.f(viewFindViewById3, "findViewById(...)");
        setProgressViewBg((RoundRectFrameLayout) viewFindViewById3);
        View viewFindViewById4 = getView().findViewById(R.id.volume_column_icon);
        kotlin.jvm.internal.n.f(viewFindViewById4, "findViewById(...)");
        setIcon((ImageView) viewFindViewById4);
        View viewFindViewById5 = getView().findViewById(R.id.miui_super_volume_expanded);
        kotlin.jvm.internal.n.f(viewFindViewById5, "findViewById(...)");
        setSuperVolume((TextView) viewFindViewById5);
        getSuperVolume().setText(VolumeUtils.getSuperVolumePercent(mContext));
        if (i2 == 10) {
            getIcon().setImportantForAccessibility(2);
        }
        setSliderTintColorList$default(this, false, 1, null);
        this.anim = null;
        setIconBlendColor(null);
        setIconColorRes(null);
        updateIconColor();
        sharedSliderRatio();
    }

    public final boolean isExpanded() {
        return ((Boolean) this.isExpanded$delegate.getValue(this, $$delegatedProperties[4])).booleanValue();
    }

    public final boolean isInCCMainPage() {
        return ((Boolean) this.isInCCMainPage$delegate.getValue(this, $$delegatedProperties[7])).booleanValue();
    }

    public final boolean isSupportSuperXiaoai() {
        return ((Boolean) this.isSupportSuperXiaoai$delegate.getValue(this, $$delegatedProperties[9])).booleanValue();
    }

    public final void release() {
        releaseMediaIconAnim();
        getIconBlendColorTransition().release();
        getIconColorTransition().release();
        g1.F.e(getMainScope(), null, 1, null);
    }

    public final void resetCache() {
        this.cachedIconRes = 0;
        setSliderTintColorList$default(this, false, 1, null);
    }

    public final void resetIconRes() {
        int stream = getStream();
        if (stream == 3) {
            setMediaIconRes$default(this, false, 0, false, 7, null);
            return;
        }
        if (stream == 10) {
            setAccessibilityIconRes();
            return;
        }
        if (stream == 2) {
            setRingIconRes();
            return;
        }
        if (stream == 4) {
            setAlarmIconRes();
            return;
        }
        if (stream == 5) {
            setNotificationIconRes();
            return;
        }
        if (stream == 0) {
            setSpeakerIconRes();
        } else if (stream == 6) {
            setSpeakerIconRes();
        } else if (stream == getVoiceAssistStreamType()) {
            setXiaoAiIconRes();
        }
    }

    public final void setAlarmIconRes() {
        int i2 = R.drawable.ic_miui_volume_alarm;
        this.iconRes = i2;
        this.iconMuteRes = i2;
    }

    public final void setAnim(ObjectAnimator objectAnimator) {
        this.anim = objectAnimator;
    }

    public final void setAnimTargetProgress(int i2) {
        this.animTargetProgress = i2;
    }

    public final void setExpanded(boolean z2) {
        this.isExpanded$delegate.setValue(this, $$delegatedProperties[4], Boolean.valueOf(z2));
    }

    public final void setHeadSetIconRes() {
        this.iconRes = R.drawable.ic_miui_volume_headset;
        this.iconMuteRes = R.drawable.ic_miui_volume_headset_mute;
    }

    public final void setIcon(ImageView imageView) {
        kotlin.jvm.internal.n.g(imageView, "<set-?>");
        this.icon = imageView;
    }

    public final void setIconColorTransitionProgress(float f2) {
        Context context = getIcon().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context)) {
            getIconBlendColorTransition().setProgress(f2);
        }
    }

    public final void setIconState(int i2) {
        this.iconState = i2;
    }

    public final void setImportant(boolean z2) {
        this.important = z2;
    }

    public final void setInCCMainPage(boolean z2) {
        this.isInCCMainPage$delegate.setValue(this, $$delegatedProperties[7], Boolean.valueOf(z2));
    }

    public final void setLastAudibleLevel(int i2) {
        this.lastAudibleLevel = i2;
    }

    public final void setLastMuteState(boolean z2) {
        this.lastMuteState = z2;
    }

    public final void setMediaIconRes() {
        setMediaIconRes$default(this, false, 0, false, 7, null);
    }

    public final void setNotificationIconRes() {
        this.iconRes = R.drawable.ic_miui_volume_notification;
        this.iconMuteRes = R.drawable.ic_miui_volume_notification_mute;
    }

    public final void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        kotlin.jvm.internal.n.g(listener, "listener");
        getSlider().setOnSeekBarChangeListener(listener);
    }

    public final void setProgressView(MiuiVolumeSeekBarProgressView miuiVolumeSeekBarProgressView) {
        kotlin.jvm.internal.n.g(miuiVolumeSeekBarProgressView, "<set-?>");
        this.progressView = miuiVolumeSeekBarProgressView;
    }

    public final void setProgressViewBg(RoundRectFrameLayout roundRectFrameLayout) {
        kotlin.jvm.internal.n.g(roundRectFrameLayout, "<set-?>");
        this.progressViewBg = roundRectFrameLayout;
    }

    public final void setProgressViewBgSize(boolean z2, boolean z3) {
        getProgressViewBg().setNeedShowDialog(this.isNeedShowDialog);
        ViewGroup.LayoutParams layoutParams = getProgressViewBg().getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        Context context = getProgressViewBg().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        marginLayoutParams.width = VolumeColumnRes.getWidth(context, this.isNeedShowDialog, z2, z3);
        Context context2 = getProgressViewBg().getContext();
        kotlin.jvm.internal.n.f(context2, "getContext(...)");
        marginLayoutParams.height = VolumeColumnRes.getHeight(context2, this.isNeedShowDialog, z2);
        getProgressViewBg().setLayoutParams(marginLayoutParams);
    }

    public final void setProgressViewSize(boolean z2, boolean z3) {
        getProgressView().setNeedShowDialog(this.isNeedShowDialog);
        ViewGroup.LayoutParams layoutParams = getProgressView().getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        Context context = getProgressView().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        marginLayoutParams.width = VolumeColumnRes.getWidth(context, this.isNeedShowDialog, z2, z3);
        Context context2 = getProgressView().getContext();
        kotlin.jvm.internal.n.f(context2, "getContext(...)");
        marginLayoutParams.height = VolumeColumnRes.getHeight(context2, this.isNeedShowDialog, z2);
        getProgressView().setLayoutParams(marginLayoutParams);
        getProgressView().setHeight(marginLayoutParams.height);
    }

    public final void setRadius(int i2) {
        this.radius = i2;
    }

    public final void setRequestedLevel(int i2) {
        this.requestedLevel = i2;
    }

    public final void setRingIconRes() {
        this.iconRes = R.drawable.ic_miui_volume_ringer;
        this.iconMuteRes = R.drawable.ic_miui_volume_ringer_mute;
    }

    public final void setSize(boolean z2, boolean z3) {
        setSliderSize(z2, z3);
        setProgressViewSize(z2, z3);
        setProgressViewBgSize(z2, z3);
        setRadius(z3);
        setMargin(z2, z3);
        setIconParma(z2);
        setSuperVolumeParma();
    }

    public final void setSlider(SeekBar seekBar) {
        kotlin.jvm.internal.n.g(seekBar, "<set-?>");
        this.slider = seekBar;
    }

    public final void setSliderBlendColor(boolean z2) {
        VolumeDialogController.StreamState ss;
        Context context = getSlider().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context)) {
            if (this.important || getView().getVisibility() == 0) {
                if (isExpanded() != z2) {
                    Util.setMiViewBlurAndBlendColor(getSlider(), 1, VolumeColumnRes.getSliderBlendColor(isExpanded(), this.isNeedShowDialog));
                }
                if (isExpanded() == z2 && (ss = getSs()) != null && this.lastMuteState == ss.muted) {
                    return;
                }
                VolumeDialogController.StreamState ss2 = getSs();
                boolean z3 = false;
                this.lastMuteState = ss2 != null && ss2.muted;
                MiuiVolumeSeekBarProgressView progressView = getProgressView();
                boolean zIsExpanded = isExpanded();
                boolean z4 = this.isNeedShowDialog;
                VolumeDialogController.StreamState ss3 = getSs();
                if (ss3 != null && ss3.muted) {
                    z3 = true;
                }
                Util.setMiViewBlurAndBlendColor(progressView, 1, VolumeColumnRes.getProgressViewBlendColor(zIsExpanded, z4, z3));
            }
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void setSliderResource(boolean z2) {
        Context context = getSlider().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context)) {
            getSlider().setBackgroundResource(VolumeColumnRes.getSliderBackgroundResId(getView(), z2, this.isNeedShowDialog));
            return;
        }
        SeekBar slider = getSlider();
        Context context2 = getSlider().getContext();
        kotlin.jvm.internal.n.f(context2, "getContext(...)");
        slider.setProgressDrawable(VolumeColumnRes.getSliderProgressDrawableBlend(context2, z2));
    }

    public final void setSliderSize(boolean z2, boolean z3) {
        ViewGroup.LayoutParams layoutParams = getSlider().getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        Context context = getSlider().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        marginLayoutParams.width = VolumeColumnRes.getWidth(context, this.isNeedShowDialog, z2, z3);
        Context context2 = getSlider().getContext();
        kotlin.jvm.internal.n.f(context2, "getContext(...)");
        marginLayoutParams.height = VolumeColumnRes.getHeight(context2, this.isNeedShowDialog, z2);
        getSlider().setLayoutParams(marginLayoutParams);
    }

    public final boolean setSliderTintColorList() {
        return setSliderTintColorList$default(this, false, 1, null);
    }

    public final void setSpeakerIconRes() {
        this.iconRes = R.drawable.ic_miui_volume_speaker;
        this.iconMuteRes = R.drawable.ic_miui_volume_speaker_mute;
    }

    public final void setSs(VolumeDialogController.StreamState streamState) {
        this.ss$delegate.setValue(this, $$delegatedProperties[1], streamState);
    }

    public final void setStream(int i2) {
        this.stream$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i2));
    }

    public final void setSuperAddIndex(int i2) {
        this.superAddIndex = i2;
    }

    public final void setSuperVolume(TextView textView) {
        kotlin.jvm.internal.n.g(textView, "<set-?>");
        this.superVolume = textView;
    }

    public final void setSupportSuperXiaoai(boolean z2) {
        this.isSupportSuperXiaoai$delegate.setValue(this, $$delegatedProperties[9], Boolean.valueOf(z2));
    }

    public final void setTracking(boolean z2) {
        this.tracking = z2;
    }

    public final void setUserAttempt(long j2) {
        this.userAttempt = j2;
    }

    public final void setView(View view) {
        kotlin.jvm.internal.n.g(view, "<set-?>");
        this.view = view;
    }

    public final void updateIcon() {
        updateIcon$default(this, false, 1, null);
    }

    public final void updateSliderRatio() {
        setSliderRatio(getSlider().getProgress() / getSlider().getMax());
    }

    private final void setRadius(boolean z2) {
        Context context = getSlider().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        this.radius = VolumeColumnRes.getRadius(context, this.isNeedShowDialog, z2);
        Util.setRoundRect(getView(), this.radius);
    }

    public final void setMediaIconRes(boolean z2) {
        setMediaIconRes$default(this, z2, 0, false, 6, null);
    }

    public final boolean setSliderTintColorList(boolean z2) {
        Context context = getSlider().getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        boolean z3 = this.isNeedShowDialog;
        VolumeDialogController.StreamState ss = getSs();
        ColorStateList sliderMutedColorList = VolumeColumnRes.getSliderMutedColorList(context, z2, z3, ss != null ? Boolean.valueOf(ss.muted) : null);
        if (kotlin.jvm.internal.n.c(getSlider().getProgressTintList(), sliderMutedColorList)) {
            return false;
        }
        getSlider().setProgressTintList(sliderMutedColorList);
        return true;
    }

    public final void updateIcon(boolean z2) {
        int i2 = kotlin.jvm.internal.n.c(isMute(), Boolean.TRUE) ? this.iconMuteRes : this.iconRes;
        if (i2 != this.cachedIconRes || z2) {
            this.cachedIconRes = i2;
            getIcon().setImageResource(i2);
            this.iconState = i2 == this.iconMuteRes ? 2 : i2 == this.iconRes ? 1 : 0;
            releaseMediaIconAnim();
            if (i2 == R.drawable.ic_miui_volume_media_animate_icon) {
                this.mediaIconAnim = new MediaIconAnim(getIcon());
                updateMediaIconAnimProgress$default(this, false, 0.0f, 2, null);
            }
        }
    }

    public final void setMediaIconRes(boolean z2, int i2) {
        setMediaIconRes$default(this, z2, i2, false, 4, null);
    }

    public final void setMediaIconRes(boolean z2, int i2, boolean z3) {
        if (z2 && (i2 == getStream() || z3)) {
            setHeadSetIconRes();
            return;
        }
        if (SVGUtilsExt.INSTANCE.getSupportVectorDrawableParams()) {
            int i3 = R.drawable.ic_miui_volume_media_animate_icon;
            this.iconRes = i3;
            this.iconMuteRes = i3;
        } else {
            this.iconRes = R.drawable.ic_miui_volume_media;
            this.iconMuteRes = R.drawable.ic_miui_volume_media_mute;
        }
    }
}
