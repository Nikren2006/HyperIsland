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
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import g1.AbstractC0369g;
import g1.E;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.ProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.widget.AnimateImageView;
import miui.systemui.widget.StatusProgressLayout;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyButtonViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    private static final Map<String, Integer> MAP = G.h(H0.o.a(Const.ACTIONS.ACTION_PAUSE, Integer.valueOf(R.drawable.pause)), H0.o.a(Const.ACTIONS.ACTION_PAUSE_TIMER, Integer.valueOf(R.drawable.pause_timer_tiny_v3)), H0.o.a(Const.ACTIONS.ACTION_PAUSE_RECORD, Integer.valueOf(R.drawable.pause_tiny)), H0.o.a(Const.ACTIONS.ACTION_RESTART, Integer.valueOf(R.drawable.restart_tiny)), H0.o.a(Const.ACTIONS.ACTION_RESTART_TIMER, Integer.valueOf(R.drawable.restart_timer_tiny_v3)), H0.o.a(Const.ACTIONS.ACTION_COPY, Integer.valueOf(R.drawable.copy_tiny)), H0.o.a(Const.ACTIONS.ACTION_DONE, Integer.valueOf(R.drawable.done_tiny_v3)), H0.o.a(Const.ACTIONS.ACTION_LATER, Integer.valueOf(R.drawable.later_v2_tiny)), H0.o.a(Const.ACTIONS.ACTION_CLOSE, Integer.valueOf(R.drawable.close_tiny)), H0.o.a(Const.ACTIONS.ACTION_END, Integer.valueOf(R.drawable.end_tiny)), H0.o.a(Const.ACTIONS.ACTION_RECORD_END, Integer.valueOf(R.drawable.record_tiny_end_v3)), H0.o.a(Const.ACTIONS.ACTION_HANGUP, Integer.valueOf(R.drawable.hangup_tiny)), H0.o.a(Const.ACTIONS.ACTION_SPEAKER, Integer.valueOf(R.drawable.speaker_v2_tiny)), H0.o.a(Const.ACTIONS.ACTION_MARK_TIMER, Integer.valueOf(R.drawable.mark_timer_tiny)));
    private List<ActionInfo> actions;
    private AnimateImageView image;
    private StatusProgressLayout statusProgressLayout;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleTinyButtonViewHolder$onDetach$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleTinyButtonViewHolder$onDetach$1", f = "ModuleTinyButtonViewHolder.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleTinyButtonViewHolder.this.new AnonymousClass1(dVar);
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
            AnimateImageView animateImageView = ModuleTinyButtonViewHolder.this.image;
            if (animateImageView != null) {
                animateImageView.cancelAnimation();
            }
            StatusProgressLayout statusProgressLayout = ModuleTinyButtonViewHolder.this.statusProgressLayout;
            if (statusProgressLayout != null) {
                statusProgressLayout.cancelAnimation();
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleTinyButtonViewHolder$resetViews$1, reason: invalid class name and case insensitive filesystem */
    public static final class C06791 extends kotlin.jvm.internal.o implements Function1 {
        final /* synthetic */ ViewGroup $focusContainer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06791(ViewGroup viewGroup) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
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
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleTinyButtonViewHolder.buildAction(miui.systemui.notification.focus.model.ActionInfo, android.service.notification.StatusBarNotification):android.app.Notification$Action");
    }

    private final void resetViews(ViewGroup viewGroup) {
        for (View view : e1.l.o(I0.u.E(c1.f.l(0, viewGroup != null ? viewGroup.getChildCount() : 0)), new C06791(viewGroup))) {
            if (view != null) {
                view.setVisibility(8);
            }
        }
    }

    private final void setActionData(int i2, final Notification.Action action, final StatusBarNotification statusBarNotification) {
        if (action == null) {
            return;
        }
        View view = getView();
        this.image = view != null ? (AnimateImageView) view.findViewById(i2) : null;
        String string = action.getExtras().getString("icon_name");
        Integer num = MAP.get(string);
        int iIntValue = num != null ? num.intValue() : 0;
        AnimateImageView animateImageView = this.image;
        if (animateImageView != null) {
            animateImageView.updateImageResource(string, iIntValue, action.getIcon());
        }
        AnimateImageView animateImageView2 = this.image;
        if (animateImageView2 != null) {
            animateImageView2.setContentDescription(action.title);
        }
        AnimateImageView animateImageView3 = this.image;
        if (animateImageView3 != null) {
            animateImageView3.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleTinyButtonViewHolder.setActionData$lambda$14(action, this, statusBarNotification, view2);
                }
            });
        }
        AnimateImageView animateImageView4 = this.image;
        if (animateImageView4 == null) {
            return;
        }
        animateImageView4.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionData$lambda$14(Notification.Action action, ModuleTinyButtonViewHolder this$0, StatusBarNotification sbn, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            this$0.handleBtnClick(pendingIntent, action, this$0.getSysuiCtx(), this$0.getView(), false, sbn, false);
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
        StatusProgressLayout statusProgressLayout3;
        ProgressInfo progressInfo4;
        CharSequence actionTitle;
        ProgressInfo progressInfo5;
        if (action == null) {
            return;
        }
        View view = getView();
        this.statusProgressLayout = view != null ? (StatusProgressLayout) view.findViewById(i2) : null;
        setProgressData(i2, statusBarNotification, (actionInfo == null || (progressInfo5 = actionInfo.getProgressInfo()) == null) ? null : Boolean.valueOf(progressInfo5.isAutoProgress()));
        String string = action.getExtras().getString("icon_name");
        Integer num = MAP.get(string);
        int iIntValue = num != null ? num.intValue() : 0;
        Icon icon = iIntValue == 0 ? action.getIcon() : null;
        StatusProgressLayout statusProgressLayout4 = this.statusProgressLayout;
        if (statusProgressLayout4 != null) {
            statusProgressLayout4.updateStatusIcon(string, iIntValue, icon);
        }
        StatusProgressLayout statusProgressLayout5 = this.statusProgressLayout;
        if (statusProgressLayout5 != null) {
            if (actionInfo == null || (actionTitle = actionInfo.getActionTitle()) == null) {
                actionTitle = action.title;
            }
            statusProgressLayout5.setContentDescription(actionTitle);
        }
        if (actionInfo != null && (progressInfo4 = actionInfo.getProgressInfo()) != null) {
            boolean zIsCCW = progressInfo4.isCCW();
            StatusProgressLayout statusProgressLayout6 = this.statusProgressLayout;
            if (statusProgressLayout6 != null) {
                statusProgressLayout6.setCCW(zIsCCW);
            }
        }
        if (actionInfo != null && (progressInfo3 = actionInfo.getProgressInfo()) != null) {
            int progress = progressInfo3.getProgress();
            ProgressInfo progressInfo6 = actionInfo.getProgressInfo();
            if (progressInfo6 != null && !progressInfo6.isAutoProgress() && (statusProgressLayout3 = this.statusProgressLayout) != null) {
                statusProgressLayout3.setProgress(progress);
            }
        }
        if (actionInfo != null && (progressInfo2 = actionInfo.getProgressInfo()) != null && (colorProgress = progressInfo2.getColorProgress()) != null && (statusProgressLayout2 = this.statusProgressLayout) != null) {
            statusProgressLayout2.updateColor(-1, -1, Color.parseColor(colorProgress));
        }
        if (actionInfo != null && (progressInfo = actionInfo.getProgressInfo()) != null && (colorProgressEnd = progressInfo.getColorProgressEnd()) != null && (statusProgressLayout = this.statusProgressLayout) != null) {
            statusProgressLayout.updateColor(-1, Color.parseColor(colorProgressEnd), -1);
        }
        StatusProgressLayout statusProgressLayout7 = this.statusProgressLayout;
        if (statusProgressLayout7 != null) {
            statusProgressLayout7.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.v
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleTinyButtonViewHolder.setActionProgressData$lambda$6(action, this, statusBarNotification, view2);
                }
            });
        }
        StatusProgressLayout statusProgressLayout8 = this.statusProgressLayout;
        if (statusProgressLayout8 == null) {
            return;
        }
        statusProgressLayout8.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionProgressData$lambda$6(Notification.Action action, ModuleTinyButtonViewHolder this$0, StatusBarNotification sbn, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            this$0.handleBtnClick(pendingIntent, action, this$0.getSysuiCtx(), this$0.getView(), false, sbn, false);
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    private final void setActionTextData(int i2, final Notification.Action action, ActionInfo actionInfo, final StatusBarNotification statusBarNotification) {
        String actionTitleColor;
        String actionBgColor;
        String actionBgColorDark;
        String actionTitleColorDark;
        if (action == null) {
            return;
        }
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
        if (isDark()) {
            if (actionInfo != null && (actionBgColorDark = actionInfo.getActionBgColorDark()) != null) {
                Drawable drawable = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
                kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                gradientDrawable.setColor(Color.parseColor(actionBgColorDark));
                if (viewFindViewById != null) {
                    viewFindViewById.setBackground(gradientDrawable);
                }
            }
        } else if (actionInfo != null && (actionBgColor = actionInfo.getActionBgColor()) != null) {
            Drawable drawable2 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable2 = (GradientDrawable) drawable2;
            gradientDrawable2.setColor(Color.parseColor(actionBgColor));
            if (viewFindViewById != null) {
                viewFindViewById.setBackground(gradientDrawable2);
            }
        }
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.x
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleTinyButtonViewHolder.setActionTextData$lambda$12(action, this, viewFindViewById, statusBarNotification, view2);
                }
            });
        }
        if (viewFindViewById == null) {
            return;
        }
        viewFindViewById.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionTextData$lambda$12(Notification.Action action, ModuleTinyButtonViewHolder this$0, View view, StatusBarNotification sbn, View view2) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            ModuleViewHolder.handleBtnClick$default(this$0, pendingIntent, action, this$0.getSysuiCtx(), view, false, sbn, false, 64, null);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        ActionInfo actionInfo;
        ActionInfo actionInfo2;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        List<ActionInfo> actions = template.getActions();
        Log.d(ModuleViewHolder.TAG, "ModuleTinyButtonViewHolder bind: " + (actions != null ? Integer.valueOf(actions.size()) : null));
        this.actions = template.getActions();
        View view = getView();
        LinearLayout linearLayout = view != null ? (LinearLayout) view.findViewById(R.id.focus_container_module_button) : null;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        resetViews(linearLayout);
        List<ActionInfo> list = this.actions;
        if ((list != null ? Integer.valueOf(list.size()) : null) != null) {
            List<ActionInfo> list2 = this.actions;
            Integer numValueOf = list2 != null ? Integer.valueOf(list2.size()) : null;
            kotlin.jvm.internal.n.d(numValueOf);
            if (numValueOf.intValue() > 0) {
                List<ActionInfo> list3 = this.actions;
                Integer type = (list3 == null || (actionInfo2 = list3.get(0)) == null) ? null : actionInfo2.getType();
                if (type != null && type.intValue() == 1) {
                    int i2 = R.id.focus_button_progress1;
                    List<ActionInfo> list4 = this.actions;
                    Notification.Action actionBuildAction = buildAction(list4 != null ? list4.get(0) : null, sbn);
                    List<ActionInfo> list5 = this.actions;
                    kotlin.jvm.internal.n.d(list5);
                    setActionProgressData(i2, actionBuildAction, list5.get(0), sbn);
                } else if (type != null && type.intValue() == 2) {
                    int i3 = R.id.focus_button_container_action;
                    List<ActionInfo> list6 = this.actions;
                    Notification.Action actionBuildAction2 = buildAction(list6 != null ? list6.get(0) : null, sbn);
                    List<ActionInfo> list7 = this.actions;
                    kotlin.jvm.internal.n.d(list7);
                    setActionTextData(i3, actionBuildAction2, list7.get(0), sbn);
                } else {
                    int i4 = R.id.focus_button_icon1;
                    List<ActionInfo> list8 = this.actions;
                    setActionData(i4, buildAction(list8 != null ? list8.get(0) : null, sbn), sbn);
                }
            }
        }
        List<ActionInfo> list9 = this.actions;
        if ((list9 != null ? Integer.valueOf(list9.size()) : null) != null) {
            List<ActionInfo> list10 = this.actions;
            Integer numValueOf2 = list10 != null ? Integer.valueOf(list10.size()) : null;
            kotlin.jvm.internal.n.d(numValueOf2);
            if (numValueOf2.intValue() > 1) {
                List<ActionInfo> list11 = this.actions;
                Integer type2 = (list11 == null || (actionInfo = list11.get(1)) == null) ? null : actionInfo.getType();
                if (type2 != null && type2.intValue() == 1) {
                    int i5 = R.id.focus_button_progress2;
                    List<ActionInfo> list12 = this.actions;
                    Notification.Action actionBuildAction3 = buildAction(list12 != null ? list12.get(1) : null, sbn);
                    List<ActionInfo> list13 = this.actions;
                    kotlin.jvm.internal.n.d(list13);
                    setActionProgressData(i5, actionBuildAction3, list13.get(1), sbn);
                    return;
                }
                if (type2 == null || type2.intValue() != 2) {
                    int i6 = R.id.focus_button_icon2;
                    List<ActionInfo> list14 = this.actions;
                    setActionData(i6, buildAction(list14 != null ? list14.get(1) : null, sbn), sbn);
                } else {
                    int i7 = R.id.focus_button_container_action;
                    List<ActionInfo> list15 = this.actions;
                    Notification.Action actionBuildAction4 = buildAction(list15 != null ? list15.get(1) : null, sbn);
                    List<ActionInfo> list16 = this.actions;
                    kotlin.jvm.internal.n.d(list16);
                    setActionTextData(i7, actionBuildAction4, list16.get(1), sbn);
                }
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_button_1, getRootView()));
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        AbstractC0369g.b(getScope(), null, null, new AnonymousClass1(null), 3, null);
        super.onDetach();
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
