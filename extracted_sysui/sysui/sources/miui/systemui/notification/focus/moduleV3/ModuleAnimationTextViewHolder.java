package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.miui.notification.R;
import com.mi.widget.view.LottieView;
import d.AbstractC0315p;
import d.C0307h;
import d.H;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import miui.systemui.notification.FullAodStatusManager;
import miui.systemui.notification.focus.model.AnimIconInfo;
import miui.systemui.notification.focus.model.AnimTextInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.LottieResUtils;
import miui.systemui.util.StaticResUtils;
import miuix.colorful.texteffect.HyperChronometer;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleAnimationTextViewHolder extends ModuleViewHolder implements FullAodStatusManager.IFullAodStatusObserver {
    private HyperChronometer chronometer;
    private View container;
    private final boolean dynamicIsland;
    private final boolean fakeDynamicIsland;
    private LottieAnimationView focusAnimation;
    private ImageView focusAnimationStatic;
    private TimerTextEffectView focusContent;
    private TimerTextEffectView focusTitle;
    private boolean fullAodStatus;
    private boolean isAutoPlay;
    private float maxFrame;

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder$fullAodStatusChanged$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder$fullAodStatusChanged$1", f = "ModuleAnimationTextViewHolder.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        final /* synthetic */ boolean $fullAodStatus;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z2, L0.d dVar) {
            super(2, dVar);
            this.$fullAodStatus = z2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleAnimationTextViewHolder.this.new AnonymousClass1(this.$fullAodStatus, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            LottieAnimationView lottieAnimationView;
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            if (ModuleAnimationTextViewHolder.this.focusAnimation == null || CommonUtils.NOT_SUPPORT_LOTTIE) {
                return H0.s.f314a;
            }
            if (this.$fullAodStatus) {
                LottieAnimationView lottieAnimationView2 = ModuleAnimationTextViewHolder.this.focusAnimation;
                if (lottieAnimationView2 != null) {
                    lottieAnimationView2.setProgress(1.0f);
                }
                LottieAnimationView lottieAnimationView3 = ModuleAnimationTextViewHolder.this.focusAnimation;
                if (lottieAnimationView3 != null) {
                    lottieAnimationView3.pauseAnimation();
                }
            } else if (ModuleAnimationTextViewHolder.this.isAutoPlay && (lottieAnimationView = ModuleAnimationTextViewHolder.this.focusAnimation) != null) {
                lottieAnimationView.resumeAnimation();
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder$onDetach$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder$onDetach$1", f = "ModuleAnimationTextViewHolder.kt", l = {}, m = "invokeSuspend")
    public static final class C06731 extends N0.l implements Function2 {
        int label;

        public C06731(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleAnimationTextViewHolder.this.new C06731(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06731) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            LottieResUtils.cancelLottieAnimate$default(LottieResUtils.INSTANCE, ModuleAnimationTextViewHolder.this.focusAnimation, false, 2, null);
            return H0.s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleAnimationTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3, boolean z4) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.dynamicIsland = z3;
        this.fakeDynamicIsland = z4;
        this.maxFrame = -1.0f;
    }

    private final void playAnimation(final Template template, StatusBarNotification statusBarNotification) {
        LottieAnimationView lottieAnimationView;
        AnimIconInfo animIconInfo;
        AnimIconInfo animIconInfo2;
        AnimIconInfo animIconInfo3;
        LottieAnimationView lottieAnimationView2 = this.focusAnimation;
        boolean zC = false;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setVisibility(0);
        }
        ImageView imageView = this.focusAnimationStatic;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView3 = this.focusAnimation;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView4 = this.focusAnimation;
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.clearAnimation();
        }
        LottieResUtils lottieResUtils = LottieResUtils.INSTANCE;
        AnimTextInfo animTextInfo = template.getAnimTextInfo();
        String src = (animTextInfo == null || (animIconInfo3 = animTextInfo.getAnimIconInfo()) == null) ? null : animIconInfo3.getSrc();
        AnimTextInfo animTextInfo2 = template.getAnimTextInfo();
        Integer numValueOf = (animTextInfo2 == null || (animIconInfo2 = animTextInfo2.getAnimIconInfo()) == null) ? null : Integer.valueOf(animIconInfo2.getNumber());
        kotlin.jvm.internal.n.d(numValueOf);
        AbstractC0315p.s(ModuleViewHolder.getContext$default(this, false, 1, null), lottieResUtils.getLottieRes(src, numValueOf.intValue())).d(new H() { // from class: miui.systemui.notification.focus.moduleV3.a
            @Override // d.H
            public final void onResult(Object obj) {
                ModuleAnimationTextViewHolder.playAnimation$lambda$2(this.f5811a, template, (C0307h) obj);
            }
        });
        AnimTextInfo animTextInfo3 = template.getAnimTextInfo();
        if (animTextInfo3 != null && (animIconInfo = animTextInfo3.getAnimIconInfo()) != null) {
            zC = kotlin.jvm.internal.n.c(animIconInfo.getAutoplay(), Boolean.TRUE);
        }
        this.isAutoPlay = zC;
        if (zC) {
            if (this.fullAodStatus || (lottieAnimationView = this.focusAnimation) == null) {
                return;
            }
            lottieAnimationView.resumeAnimation();
            return;
        }
        LottieAnimationView lottieAnimationView5 = this.focusAnimation;
        if (lottieAnimationView5 != null) {
            lottieAnimationView5.pauseAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void playAnimation$lambda$2(miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder r3, miui.systemui.notification.focus.model.Template r4, d.C0307h r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.n.g(r3, r0)
            java.lang.String r0 = "$template"
            kotlin.jvm.internal.n.g(r4, r0)
            com.airbnb.lottie.LottieAnimationView r0 = r3.focusAnimation
            if (r0 == 0) goto L11
            r0.setComposition(r5)
        L11:
            com.airbnb.lottie.LottieAnimationView r5 = r3.focusAnimation
            r0 = 0
            if (r5 == 0) goto L1b
            float r5 = r5.getMaxFrame()
            goto L1c
        L1b:
            r5 = r0
        L1c:
            r3.maxFrame = r5
            com.airbnb.lottie.LottieAnimationView r5 = r3.focusAnimation
            if (r5 != 0) goto L23
            goto L77
        L23:
            miui.systemui.notification.focus.model.AnimTextInfo r1 = r4.getAnimTextInfo()
            r2 = 0
            if (r1 == 0) goto L39
            miui.systemui.notification.focus.model.AnimIconInfo r1 = r1.getAnimIconInfo()
            if (r1 == 0) goto L39
            int r1 = r1.getNumber()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L3a
        L39:
            r1 = r2
        L3a:
            if (r1 == 0) goto L73
            miui.systemui.notification.focus.model.AnimTextInfo r1 = r4.getAnimTextInfo()
            miui.systemui.notification.focus.model.AnimIconInfo r1 = r1.getAnimIconInfo()
            if (r1 == 0) goto L4f
            int r1 = r1.getNumber()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L50
        L4f:
            r1 = r2
        L50:
            kotlin.jvm.internal.n.d(r1)
            int r1 = r1.intValue()
            if (r1 <= 0) goto L73
            miui.systemui.notification.focus.model.AnimTextInfo r4 = r4.getAnimTextInfo()
            miui.systemui.notification.focus.model.AnimIconInfo r4 = r4.getAnimIconInfo()
            if (r4 == 0) goto L6b
            int r4 = r4.getNumber()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
        L6b:
            kotlin.jvm.internal.n.d(r2)
            int r4 = r2.intValue()
            goto L74
        L73:
            r4 = -1
        L74:
            r5.setRepeatCount(r4)
        L77:
            com.airbnb.lottie.LottieAnimationView r4 = r3.focusAnimation
            if (r4 != 0) goto L7c
            goto L80
        L7c:
            r5 = 1
            r4.setRepeatMode(r5)
        L80:
            com.airbnb.lottie.LottieAnimationView r3 = r3.focusAnimation
            if (r3 != 0) goto L85
            goto L88
        L85:
            r3.setProgress(r0)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder.playAnimation$lambda$2(miui.systemui.notification.focus.moduleV3.ModuleAnimationTextViewHolder, miui.systemui.notification.focus.model.Template, d.h):void");
    }

    private final void showStaticImage(Template template, StatusBarNotification statusBarNotification) {
        AnimIconInfo animIconInfo;
        LottieAnimationView lottieAnimationView = this.focusAnimation;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
        }
        ImageView imageView = this.focusAnimationStatic;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        ImageView imageView2 = this.focusAnimationStatic;
        if (imageView2 != null) {
            StaticResUtils staticResUtils = StaticResUtils.INSTANCE;
            AnimTextInfo animTextInfo = template.getAnimTextInfo();
            imageView2.setImageResource(staticResUtils.getStaticRes((animTextInfo == null || (animIconInfo = animTextInfo.getAnimIconInfo()) == null) ? null : animIconInfo.getSrc()));
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        AnimIconInfo animIconInfo;
        AnimIconInfo animIconInfo2;
        AnimIconInfo animIconInfo3;
        AnimIconInfo animIconInfo4;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getAnimTextInfo());
        AnimTextInfo animTextInfo = template.getAnimTextInfo();
        Integer numValueOf = null;
        initTimerData(animTextInfo != null ? animTextInfo.getTimerInfo() : null);
        if (this.fakeDynamicIsland) {
            sbn.getNotification().extras.putInt("miui.focus.lottieView.fake.id", R.id.focus_animation);
            Bundle bundle = sbn.getNotification().extras;
            AnimTextInfo animTextInfo2 = template.getAnimTextInfo();
            bundle.putBoolean("miui.focus.lottieView.isPlay", (animTextInfo2 == null || (animIconInfo4 = animTextInfo2.getAnimIconInfo()) == null) ? false : kotlin.jvm.internal.n.c(animIconInfo4.getAutoplay(), Boolean.TRUE));
        } else if (this.dynamicIsland) {
            sbn.getNotification().extras.putInt("miui.focus.lottieView.id", R.id.focus_animation);
            Bundle bundle2 = sbn.getNotification().extras;
            AnimTextInfo animTextInfo3 = template.getAnimTextInfo();
            bundle2.putBoolean("miui.focus.lottieView.isPlay", (animTextInfo3 == null || (animIconInfo = animTextInfo3.getAnimIconInfo()) == null) ? false : kotlin.jvm.internal.n.c(animIconInfo.getAutoplay(), Boolean.TRUE));
        }
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        adaptTimerDelay();
        if (TextUtils.isEmpty(getTitle())) {
            TimerTextEffectView timerTextEffectView = this.focusTitle;
            if (timerTextEffectView != null) {
                timerTextEffectView.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView2 = this.focusTitle;
            if (timerTextEffectView2 != null) {
                timerTextEffectView2.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView3 = this.focusTitle;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                TimerTextEffectView timerTextEffectView4 = this.focusTitle;
                if (timerTextEffectView4 != null) {
                    timerTextEffectView4.updateTextWithNewAppearance(Html.fromHtml(getTitle()), Integer.valueOf(iIntValue));
                }
            }
        }
        if (TextUtils.isEmpty(getContent())) {
            TimerTextEffectView timerTextEffectView5 = this.focusContent;
            if (timerTextEffectView5 != null) {
                timerTextEffectView5.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView6 = this.focusContent;
            if (timerTextEffectView6 != null) {
                timerTextEffectView6.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView7 = this.focusContent;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setText(Html.fromHtml(getContent()), TextView.BufferType.SPANNABLE);
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                TimerTextEffectView timerTextEffectView8 = this.focusContent;
                if (timerTextEffectView8 != null) {
                    timerTextEffectView8.updateTextWithNewAppearance(Html.fromHtml(getContent()), Integer.valueOf(iIntValue2));
                }
            }
        }
        AnimTextInfo animTextInfo4 = template.getAnimTextInfo();
        if ((animTextInfo4 != null ? animTextInfo4.getTimerInfo() : null) != null) {
            HyperChronometer hyperChronometer = this.chronometer;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(0);
            }
            ModuleViewHolder.setTimerData$default(this, 0, sbn, 1, null);
        } else {
            HyperChronometer hyperChronometer2 = this.chronometer;
            if (hyperChronometer2 != null) {
                hyperChronometer2.setVisibility(8);
            }
        }
        LottieResUtils lottieResUtils = LottieResUtils.INSTANCE;
        AnimTextInfo animTextInfo5 = template.getAnimTextInfo();
        String src = (animTextInfo5 == null || (animIconInfo3 = animTextInfo5.getAnimIconInfo()) == null) ? null : animIconInfo3.getSrc();
        AnimTextInfo animTextInfo6 = template.getAnimTextInfo();
        if (animTextInfo6 != null && (animIconInfo2 = animTextInfo6.getAnimIconInfo()) != null) {
            numValueOf = Integer.valueOf(animIconInfo2.getNumber());
        }
        kotlin.jvm.internal.n.d(numValueOf);
        if (lottieResUtils.isNoNeedToGetLottieRes(src, numValueOf.intValue())) {
            showStaticImage(template, sbn);
        } else {
            playAnimation(template, sbn);
        }
    }

    @Override // miui.systemui.notification.FullAodStatusManager.IFullAodStatusObserver
    public void fullAodStatusChanged(boolean z2) {
        this.fullAodStatus = z2;
        AbstractC0369g.b(getScope(), null, null, new AnonymousClass1(z2, null), 3, null);
    }

    public final boolean getDynamicIsland() {
        return this.dynamicIsland;
    }

    public final boolean getFakeDynamicIsland() {
        return this.fakeDynamicIsland;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.dynamicIsland)).inflate(R.layout.focus_notification_module_animation_text, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_animation_text) : null;
        View view2 = getView();
        this.focusAnimation = view2 != null ? (LottieAnimationView) view2.findViewById(R.id.focus_animation) : null;
        View view3 = getView();
        this.focusAnimationStatic = view3 != null ? (ImageView) view3.findViewById(R.id.focus_animation_static) : null;
        View view4 = getView();
        this.focusTitle = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.focus_title) : null;
        View view5 = getView();
        this.focusContent = view5 != null ? (TimerTextEffectView) view5.findViewById(R.id.focus_content) : null;
        View view6 = getView();
        HyperChronometer hyperChronometer = view6 != null ? (HyperChronometer) view6.findViewById(R.id.chronometer) : null;
        this.chronometer = hyperChronometer;
        if (hyperChronometer != null) {
            hyperChronometer.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView = this.focusTitle;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.focusTitle;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView3 = this.focusContent;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.focusContent;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setEnableEffectWithInit(false);
        }
        FullAodStatusManager.INSTANCE.addObserver(this);
        if (this.dynamicIsland || this.fakeDynamicIsland) {
            LottieAnimationView lottieAnimationView = this.focusAnimation;
            kotlin.jvm.internal.n.e(lottieAnimationView, "null cannot be cast to non-null type com.mi.widget.view.LottieView");
            ((LottieView) lottieAnimationView).setAdaptiveFrameRate(true);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        FullAodStatusManager.INSTANCE.removeObserver(this);
        AbstractC0369g.b(getScope(), null, null, new C06731(null), 3, null);
        super.onDetach();
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getAnimTextInfo());
        bind(template, sbn);
    }
}
