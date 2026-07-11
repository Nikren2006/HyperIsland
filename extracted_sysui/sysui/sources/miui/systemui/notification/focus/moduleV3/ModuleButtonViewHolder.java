package miui.systemui.notification.focus.moduleV3;

import I0.G;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.OneShotPreDrawListener;
import com.android.systemui.miui.notification.R;
import com.mi.widget.core.Origin;
import com.mi.widget.shader.CallingShader;
import g1.AbstractC0369g;
import g1.E;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicFeatureConfig;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.ProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.PaletteUtils;
import miui.systemui.util.ShaderUtil;
import miui.systemui.widget.AnimateImageView;
import miui.systemui.widget.StatusProgressLayout;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleButtonViewHolder extends ModuleViewHolder {
    private static final Map<String, Integer> LOTTIE_RES_MAP_NEXT;
    private List<ActionInfo> actions;
    private final ModuleButtonViewHolder$buttonAccessibilityDelegate$1 buttonAccessibilityDelegate;
    private AnimateImageView image;
    private final boolean isDynamicIsland;
    private CallingShader<?> shader;
    private StatusProgressLayout statusProgressLayout;
    public static final Companion Companion = new Companion(null);
    private static final Map<String, Integer> ACTIONS_RESOURCE_MAP = G.h(H0.o.a(Const.ACTIONS.ACTION_PAUSE, Integer.valueOf(R.drawable.pause)), H0.o.a(Const.ACTIONS.ACTION_PAUSE_TIMER, Integer.valueOf(R.drawable.pause_timer_v3)), H0.o.a(Const.ACTIONS.ACTION_PAUSE_RECORD, Integer.valueOf(R.drawable.pause_v3)), H0.o.a(Const.ACTIONS.ACTION_RESTART, Integer.valueOf(R.drawable.restart)), H0.o.a(Const.ACTIONS.ACTION_RESTART_TIMER, Integer.valueOf(R.drawable.restart_timer_v3)), H0.o.a(Const.ACTIONS.ACTION_RESTART_RECORD, Integer.valueOf(R.drawable.restart_v3)), H0.o.a(Const.ACTIONS.ACTION_MARK_TIMER, Integer.valueOf(R.drawable.mark_timer)), H0.o.a(Const.ACTIONS.ACTION_DONE, Integer.valueOf(R.drawable.done_v3)), H0.o.a(Const.ACTIONS.ACTION_END, Integer.valueOf(R.drawable.end_v3)), H0.o.a(Const.ACTIONS.ACTION_RECORD_END, Integer.valueOf(R.drawable.record_end_v3)), H0.o.a(Const.ACTIONS.ACTION_COPY, Integer.valueOf(R.drawable.copy_v2)), H0.o.a(Const.ACTIONS.ACTION_CLOSE, Integer.valueOf(R.drawable.close_v3)), H0.o.a(Const.ACTIONS.ACTION_LATER, Integer.valueOf(R.drawable.later_v3)), H0.o.a(Const.ACTIONS.ACTION_ANSWER, Integer.valueOf(R.drawable.answer)), H0.o.a(Const.ACTIONS.ACTION_HANGUP, Integer.valueOf(R.drawable.hangup_v2)), H0.o.a(Const.ACTIONS.ACTION_SPEAKER, Integer.valueOf(R.drawable.speaker_v2)), H0.o.a(Const.ACTIONS.ACTION_SPEAKER_DURING, Integer.valueOf(R.drawable.speaker_during_v2)), H0.o.a(Const.ACTIONS.ACTION_CALL, Integer.valueOf(R.drawable.call)), H0.o.a(Const.ACTIONS.ACTION_AUDIO_REQUEST, Integer.valueOf(R.drawable.request_audio_v2)), H0.o.a(Const.ACTIONS.ACTION_AUDIO, Integer.valueOf(R.drawable.audio_v2)), H0.o.a(Const.ACTIONS.ACTION_AUDIO_DURING, Integer.valueOf(R.drawable.audio_during_v2)), H0.o.a(Const.ACTIONS.ACTION_VIDEO_REQUEST, Integer.valueOf(R.drawable.request_video_v2)), H0.o.a(Const.ACTIONS.ACTION_VIDEO, Integer.valueOf(R.drawable.video_v2)), H0.o.a(Const.ACTIONS.ACTION_VIDEO_DURING, Integer.valueOf(R.drawable.video_during_v2)), H0.o.a(Const.ACTIONS.ACTION_RETRY, Integer.valueOf(R.drawable.retry)));

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder$onDetach$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder$onDetach$1", f = "ModuleButtonViewHolder.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleButtonViewHolder.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            AnimateImageView animateImageView = ModuleButtonViewHolder.this.image;
            if (animateImageView != null) {
                animateImageView.cancelAnimation();
            }
            StatusProgressLayout statusProgressLayout = ModuleButtonViewHolder.this.statusProgressLayout;
            if (statusProgressLayout != null) {
                statusProgressLayout.cancelAnimation();
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder$resetViews$1, reason: invalid class name and case insensitive filesystem */
    public static final class C06741 extends kotlin.jvm.internal.o implements Function1 {
        final /* synthetic */ ViewGroup $focusContainer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06741(ViewGroup viewGroup) {
            super(1);
            this.$focusContainer = viewGroup;
        }

        public final View invoke(int i2) {
            ViewGroup viewGroup = this.$focusContainer;
            if (viewGroup != null) {
                return viewGroup.getChildAt(i2);
            }
            return null;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    static {
        int i2 = R.raw.play_island;
        H0.i iVarA = H0.o.a(Const.ACTIONS.ACTION_PAUSE, Integer.valueOf(i2));
        H0.i iVarA2 = H0.o.a(Const.ACTIONS.ACTION_PAUSE_TIMER, Integer.valueOf(R.raw.play_island_blue));
        H0.i iVarA3 = H0.o.a(Const.ACTIONS.ACTION_PAUSE_RECORD, Integer.valueOf(i2));
        int i3 = R.raw.pause_island;
        LOTTIE_RES_MAP_NEXT = G.h(iVarA, iVarA2, iVarA3, H0.o.a(Const.ACTIONS.ACTION_RESTART, Integer.valueOf(i3)), H0.o.a(Const.ACTIONS.ACTION_RESTART_TIMER, Integer.valueOf(R.raw.pause_island_blue)), H0.o.a(Const.ACTIONS.ACTION_RESTART_RECORD, Integer.valueOf(i3)));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder$buttonAccessibilityDelegate$1] */
    public ModuleButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.isDynamicIsland = z3;
        this.buttonAccessibilityDelegate = new View.AccessibilityDelegate() { // from class: miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder$buttonAccessibilityDelegate$1
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final android.app.Notification.Action buildAction(miui.systemui.notification.focus.model.ActionInfo r12, android.service.notification.StatusBarNotification r13) throws java.net.URISyntaxException {
        /*
            Method dump skipped, instruction units count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder.buildAction(miui.systemui.notification.focus.model.ActionInfo, android.service.notification.StatusBarNotification):android.app.Notification$Action");
    }

    private final void resetViews(ViewGroup viewGroup) {
        for (View view : e1.l.o(I0.u.E(c1.f.l(0, viewGroup != null ? viewGroup.getChildCount() : 0)), new C06741(viewGroup))) {
            if (view != null) {
                view.setVisibility(8);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private final void setActionNormalData(int i2, final Notification.Action action, ActionInfo actionInfo, final StatusBarNotification statusBarNotification) {
        if (action == null) {
            return;
        }
        Bundle extras = action.getExtras();
        String string = extras != null ? extras.getString("icon_name") : null;
        View view = getView();
        AnimateImageView animateImageView = view != null ? (AnimateImageView) view.findViewById(i2) : null;
        Integer num = ACTIONS_RESOURCE_MAP.get(string);
        int iIntValue = num != null ? num.intValue() : 0;
        Icon icon = iIntValue == 0 ? action.getIcon() : null;
        Integer num2 = LOTTIE_RES_MAP_NEXT.get(string);
        final int iIntValue2 = num2 != null ? num2.intValue() : -1;
        if (animateImageView != null) {
            if (shouldAnimLottie(string)) {
                animateImageView.updateAndPauseAnimation(iIntValue2, getContext(this.isDynamicIsland));
            } else {
                animateImageView.clearLottieFrame();
                animateImageView.updateImageResource(string, iIntValue, icon);
            }
            animateImageView.setContentDescription(action.title);
            animateImageView.setAccessibilityDelegate(this.buttonAccessibilityDelegate);
            final String str = string;
            final AnimateImageView animateImageView2 = animateImageView;
            animateImageView.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleButtonViewHolder.setActionNormalData$lambda$6$lambda$5(action, this, statusBarNotification, str, animateImageView2, iIntValue2, view2);
                }
            });
            animateImageView.setVisibility(0);
        }
        if (kotlin.jvm.internal.n.c(string, Const.ACTIONS.ACTION_ANSWER) && this.isDynamicIsland && animateImageView != null) {
            showShaderIcon(animateImageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionNormalData$lambda$6$lambda$5(Notification.Action action, ModuleButtonViewHolder this$0, StatusBarNotification sbn, String str, AnimateImageView this_apply, int i2, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            ModuleViewHolder.handleBtnClick$default(this$0, pendingIntent, action, this$0.getSysuiCtx(), this$0.getView(), this$0.isDynamicIsland, sbn, false, 64, null);
            if (this$0.shouldAnimLottie(str)) {
                this_apply.handleLottieAnim(i2);
            }
        }
    }

    private final void setActionProgressData(int i2, final Notification.Action action, ActionInfo actionInfo, final StatusBarNotification statusBarNotification) {
        ProgressInfo progressInfo;
        String colorProgressEnd;
        StatusProgressLayout statusProgressLayout;
        ProgressInfo progressInfo2;
        String colorProgress;
        StatusProgressLayout statusProgressLayout2;
        ProgressInfo progressInfo3;
        String colorProgressEndDark;
        StatusProgressLayout statusProgressLayout3;
        ProgressInfo progressInfo4;
        String colorProgressDark;
        StatusProgressLayout statusProgressLayout4;
        ProgressInfo progressInfo5;
        StatusProgressLayout statusProgressLayout5;
        ProgressInfo progressInfo6;
        CharSequence actionTitle;
        ProgressInfo progressInfo7;
        if (action == null) {
            return;
        }
        Bundle extras = action.getExtras();
        final String string = extras != null ? extras.getString("icon_name") : null;
        setProgressData(i2, statusBarNotification, (actionInfo == null || (progressInfo7 = actionInfo.getProgressInfo()) == null) ? null : Boolean.valueOf(progressInfo7.isAutoProgress()));
        View view = getView();
        this.statusProgressLayout = view != null ? (StatusProgressLayout) view.findViewById(i2) : null;
        Integer num = ACTIONS_RESOURCE_MAP.get(string);
        int iIntValue = num != null ? num.intValue() : 0;
        Icon icon = iIntValue == 0 ? action.getIcon() : null;
        Integer num2 = LOTTIE_RES_MAP_NEXT.get(string);
        final int iIntValue2 = num2 != null ? num2.intValue() : -1;
        StatusProgressLayout statusProgressLayout6 = this.statusProgressLayout;
        if (statusProgressLayout6 != null) {
            if (shouldAnimLottie(string)) {
                statusProgressLayout6.updateAndPauseAnimation(iIntValue2, getContext(this.isDynamicIsland));
            } else {
                statusProgressLayout6.clearLottieFrame();
                statusProgressLayout6.updateStatusIcon(string, iIntValue, icon);
            }
            if (actionInfo == null || (actionTitle = actionInfo.getActionTitle()) == null) {
                actionTitle = action.title;
            }
            statusProgressLayout6.setContentDescription(actionTitle);
            statusProgressLayout6.setAccessibilityDelegate(this.buttonAccessibilityDelegate);
        }
        if (actionInfo != null && (progressInfo6 = actionInfo.getProgressInfo()) != null) {
            boolean zIsCCW = progressInfo6.isCCW();
            StatusProgressLayout statusProgressLayout7 = this.statusProgressLayout;
            if (statusProgressLayout7 != null) {
                statusProgressLayout7.setCCW(zIsCCW);
            }
        }
        if (actionInfo != null && (progressInfo5 = actionInfo.getProgressInfo()) != null) {
            int progress = progressInfo5.getProgress();
            ProgressInfo progressInfo8 = actionInfo.getProgressInfo();
            if (progressInfo8 != null && !progressInfo8.isAutoProgress() && (statusProgressLayout5 = this.statusProgressLayout) != null) {
                statusProgressLayout5.setProgress(progress);
            }
        }
        if (isDark()) {
            if (actionInfo != null && (progressInfo4 = actionInfo.getProgressInfo()) != null && (colorProgressDark = progressInfo4.getColorProgressDark()) != null && (statusProgressLayout4 = this.statusProgressLayout) != null) {
                statusProgressLayout4.updateColor(-1, -1, Color.parseColor(colorProgressDark));
            }
            if (actionInfo != null && (progressInfo3 = actionInfo.getProgressInfo()) != null && (colorProgressEndDark = progressInfo3.getColorProgressEndDark()) != null && (statusProgressLayout3 = this.statusProgressLayout) != null) {
                statusProgressLayout3.updateColor(-1, Color.parseColor(colorProgressEndDark), -1);
            }
        } else {
            if (actionInfo != null && (progressInfo2 = actionInfo.getProgressInfo()) != null && (colorProgress = progressInfo2.getColorProgress()) != null && (statusProgressLayout2 = this.statusProgressLayout) != null) {
                statusProgressLayout2.updateColor(-1, -1, Color.parseColor(colorProgress));
            }
            if (actionInfo != null && (progressInfo = actionInfo.getProgressInfo()) != null && (colorProgressEnd = progressInfo.getColorProgressEnd()) != null && (statusProgressLayout = this.statusProgressLayout) != null) {
                statusProgressLayout.updateColor(-1, Color.parseColor(colorProgressEnd), -1);
            }
        }
        final StatusProgressLayout statusProgressLayout8 = this.statusProgressLayout;
        if (statusProgressLayout8 != null) {
            statusProgressLayout8.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleButtonViewHolder.setActionProgressData$lambda$17$lambda$16(action, this, statusBarNotification, string, statusProgressLayout8, iIntValue2, view2);
                }
            });
            statusProgressLayout8.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionProgressData$lambda$17$lambda$16(Notification.Action action, ModuleButtonViewHolder this$0, StatusBarNotification sbn, String str, StatusProgressLayout this_apply, int i2, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            ModuleViewHolder.handleBtnClick$default(this$0, pendingIntent, action, this$0.getSysuiCtx(), this$0.getView(), this$0.isDynamicIsland, sbn, false, 64, null);
            if (this$0.shouldAnimLottie(str)) {
                this_apply.handleLottieAnim(i2);
            }
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    private final void setActionTextData(int i2, final Notification.Action action, ActionInfo actionInfo, final StatusBarNotification statusBarNotification) {
        String actionTitleColor;
        String actionBgColor;
        String actionTitleColorDark;
        if (action == null) {
            return;
        }
        Log.i(Const.TAG_DEBUG_BTN_ANIM, "setActionTextData " + statusBarNotification.getKey());
        View view = getView();
        final View viewFindViewById = view != null ? view.findViewById(i2) : null;
        TextView textView = viewFindViewById != null ? (TextView) viewFindViewById.findViewById(R.id.focus_button_title) : null;
        if (textView != null) {
            textView.setText(Html.fromHtml(actionInfo != null ? actionInfo.getActionTitle() : null), TextView.BufferType.SPANNABLE);
        }
        if (isDark()) {
            if (actionInfo != null && (actionTitleColorDark = actionInfo.getActionTitleColorDark()) != null && textView != null) {
                textView.setTextColor(Color.parseColor(actionTitleColorDark));
            }
        } else if (actionInfo != null && (actionTitleColor = actionInfo.getActionTitleColor()) != null && textView != null) {
            textView.setTextColor(Color.parseColor(actionTitleColor));
        }
        if (actionInfo != null && (actionBgColor = actionInfo.getActionBgColor(isDark())) != null) {
            Drawable drawableMutate = getCtx().getResources().getDrawable(R.drawable.focus_button_background_n).mutate();
            kotlin.jvm.internal.n.e(drawableMutate, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable = (GradientDrawable) drawableMutate;
            gradientDrawable.setColor(Color.parseColor(actionBgColor));
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawableMutate2 = getCtx().getResources().getDrawable(R.drawable.focus_button_background_p).mutate();
            kotlin.jvm.internal.n.e(drawableMutate2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable2 = (GradientDrawable) drawableMutate2;
            gradientDrawable2.setColor(PaletteUtils.blendWithColor(Color.parseColor(actionBgColor), isDark()));
            String actionBgPressColor = actionInfo.getActionBgPressColor(isDark());
            if (actionBgPressColor != null) {
                gradientDrawable2.setColor(Color.parseColor(actionBgPressColor));
            }
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable2);
            stateListDrawable.addState(new int[0], gradientDrawable);
            if (viewFindViewById != null) {
                viewFindViewById.setBackground(stateListDrawable);
            }
        }
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleButtonViewHolder.setActionTextData$lambda$23(action, this, viewFindViewById, statusBarNotification, view2);
                }
            });
        }
        if (viewFindViewById == null) {
            return;
        }
        viewFindViewById.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionTextData$lambda$23(Notification.Action action, ModuleButtonViewHolder this$0, View view, StatusBarNotification sbn, View view2) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            ModuleViewHolder.handleBtnClick$default(this$0, pendingIntent, action, this$0.getSysuiCtx(), view, this$0.isDynamicIsland, sbn, false, 64, null);
        }
    }

    private final boolean shouldAnimLottie(String str) {
        View view;
        return ((this.isDynamicIsland && (view = getView()) != null && ModuleViewHolderExtKt.findViewByTagId(view, miui.systemui.dynamicisland.R.id.dynamic_island_animating_tag)) || !(str != null && LOTTIE_RES_MAP_NEXT.get(str) != null) || CommonUtils.NOT_SUPPORT_LOTTIE) ? false : true;
    }

    private final void showShaderIcon(final View view) {
        ViewParent parent = getRootView().getParent().getParent();
        kotlin.jvm.internal.n.e(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        final ViewGroup viewGroup = (ViewGroup) parent;
        OneShotPreDrawListener.add(viewGroup, new Runnable() { // from class: miui.systemui.notification.focus.moduleV3.ModuleButtonViewHolder$showShaderIcon$$inlined$doOnPreDraw$1
            @Override // java.lang.Runnable
            public final void run() {
                if (DynamicFeatureConfig.INSTANCE.getFEATURE_DYNAMIC_ISLAND_SHADER()) {
                    this.shader = ShaderUtil.setShader$default(ShaderUtil.INSTANCE, NotificationCompat.CATEGORY_CALL, viewGroup, view.getWidth(), this.getCtx().getResources().getDimension(R.dimen.focus_notify_template_margin), Origin.END, false, 32, null);
                    CallingShader callingShader = this.shader;
                    if (callingShader != null) {
                        callingShader.start();
                    }
                }
            }
        });
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        this.actions = template.getActions();
        View view = getView();
        LinearLayout linearLayout = view != null ? (LinearLayout) view.findViewById(R.id.focus_container_module_button) : null;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        resetViews(linearLayout);
        String key = sbn.getKey();
        List<ActionInfo> list = this.actions;
        Log.d(Const.TAG_DEBUG_BTN_ANIM, key + ", " + (list != null ? Integer.valueOf(list.size()) : null));
        List<ActionInfo> list2 = this.actions;
        if (list2 != null) {
            List<ActionInfo> list3 = list2.isEmpty() ? null : list2;
            if (list3 != null) {
                int i2 = 0;
                for (Object obj : I0.u.i0(list3, 3)) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        I0.m.n();
                    }
                    ActionInfo actionInfo = (ActionInfo) obj;
                    H0.i iVarA = i2 != 0 ? i2 != 1 ? H0.o.a(Integer.valueOf(R.id.focus_button_progress3), Integer.valueOf(R.id.focus_button_icon3)) : H0.o.a(Integer.valueOf(R.id.focus_button_progress2), Integer.valueOf(R.id.focus_button_icon2)) : H0.o.a(Integer.valueOf(R.id.focus_button_progress1), Integer.valueOf(R.id.focus_button_icon1));
                    int iIntValue = ((Number) iVarA.a()).intValue();
                    int iIntValue2 = ((Number) iVarA.b()).intValue();
                    Integer type = actionInfo.getType();
                    int iIntValue3 = type != null ? type.intValue() : 0;
                    if (iIntValue3 == 1) {
                        setActionProgressData(iIntValue, buildAction(actionInfo, sbn), actionInfo, sbn);
                    } else if (iIntValue3 != 2) {
                        setActionNormalData(iIntValue2, buildAction(actionInfo, sbn), actionInfo, sbn);
                    } else {
                        setActionTextData(R.id.focus_button_container_action, buildAction(actionInfo, sbn), actionInfo, sbn);
                    }
                    i2 = i3;
                }
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.isDynamicIsland)).inflate(R.layout.focus_notification_module_button_1, getRootView()));
    }

    public final boolean isDynamicIsland() {
        return this.isDynamicIsland;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        CallingShader<?> callingShader = this.shader;
        if (callingShader != null) {
            callingShader.stop();
        }
        AbstractC0369g.b(getScope(), null, null, new AnonymousClass1(null), 3, null);
        super.onDetach();
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
