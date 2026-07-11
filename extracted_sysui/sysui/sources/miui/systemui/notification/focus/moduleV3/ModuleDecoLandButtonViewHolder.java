package miui.systemui.notification.focus.moduleV3;

import I0.G;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.service.notification.StatusBarNotification;
import android.text.Html;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.AnimateImageView;
import miui.systemui.widget.StatusProgressLayout;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoLandButtonViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    private static final Map<String, Integer> MAP = G.h(H0.o.a(Const.ACTIONS.ACTION_PAUSE, Integer.valueOf(R.drawable.pause_deco)), H0.o.a(Const.ACTIONS.ACTION_PAUSE_TIMER, Integer.valueOf(R.drawable.pause_timer_deco_land_v3)), H0.o.a(Const.ACTIONS.ACTION_PAUSE_RECORD, Integer.valueOf(R.drawable.pause_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_RESTART, Integer.valueOf(R.drawable.restart_deco)), H0.o.a(Const.ACTIONS.ACTION_RESTART_TIMER, Integer.valueOf(R.drawable.restart_timer_deco_land_v3)), H0.o.a(Const.ACTIONS.ACTION_RESTART_RECORD, Integer.valueOf(R.drawable.restart_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_COPY, Integer.valueOf(R.drawable.copy_deco_land)), H0.o.a(Const.ACTIONS.ACTION_DONE, Integer.valueOf(R.drawable.done_deco_v3)), H0.o.a(Const.ACTIONS.ACTION_LATER, Integer.valueOf(R.drawable.later_deco)), H0.o.a(Const.ACTIONS.ACTION_LATER_TIMER, Integer.valueOf(R.drawable.later_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_CLOSE, Integer.valueOf(R.drawable.close_big_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_END, Integer.valueOf(R.drawable.end_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_RECORD_END, Integer.valueOf(R.drawable.record_end_deco_land_v3)), H0.o.a(Const.ACTIONS.ACTION_HANGUP, Integer.valueOf(R.drawable.hangup_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_SPEAKER, Integer.valueOf(R.drawable.speaker_deco_land_v2)), H0.o.a(Const.ACTIONS.ACTION_MARK_TIMER, Integer.valueOf(R.drawable.mark_timer_deco_land)));
    private List<ActionInfo> actions;
    private final String close;
    private final String done;
    private View focusButtonContainerAction;
    private AnimateImageView focusButtonIcon1;
    private AnimateImageView focusButtonIcon2;
    private StatusProgressLayout focusButtonProgress1;
    private StatusProgressLayout focusButtonProgress2;
    private View focusButtonSpace;
    private LinearLayout focusContainerModuleButton;
    private AnimateImageView image;
    private AnimateImageView imageDone;
    private StatusProgressLayout statusProgressLayout;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder$onDetach$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder$onDetach$1", f = "ModuleDecoLandButtonViewHolder.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleDecoLandButtonViewHolder.this.new AnonymousClass1(dVar);
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
            AnimateImageView animateImageView = ModuleDecoLandButtonViewHolder.this.image;
            if (animateImageView != null) {
                animateImageView.cancelAnimation();
            }
            AnimateImageView animateImageView2 = ModuleDecoLandButtonViewHolder.this.imageDone;
            if (animateImageView2 != null) {
                animateImageView2.cancelAnimation();
            }
            StatusProgressLayout statusProgressLayout = ModuleDecoLandButtonViewHolder.this.statusProgressLayout;
            if (statusProgressLayout != null) {
                statusProgressLayout.cancelAnimation();
            }
            return H0.s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoLandButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.done = Const.ACTIONS.ACTION_DONE;
        this.close = Const.ACTIONS.ACTION_CLOSE;
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
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder.buildAction(miui.systemui.notification.focus.model.ActionInfo, android.service.notification.StatusBarNotification):android.app.Notification$Action");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setActionData(int r5, final android.app.Notification.Action r6, final android.service.notification.StatusBarNotification r7) {
        /*
            r4 = this;
            if (r6 != 0) goto L3
            return
        L3:
            android.view.View r0 = r4.getView()
            r1 = 0
            if (r0 == 0) goto L11
            android.view.View r5 = r0.findViewById(r5)
            miui.systemui.widget.AnimateImageView r5 = (miui.systemui.widget.AnimateImageView) r5
            goto L12
        L11:
            r5 = r1
        L12:
            r4.image = r5
            android.view.View r5 = r4.getView()
            if (r5 == 0) goto L23
            int r0 = com.android.systemui.miui.notification.R.id.focus_button_icon_done
            android.view.View r5 = r5.findViewById(r0)
            r1 = r5
            miui.systemui.widget.AnimateImageView r1 = (miui.systemui.widget.AnimateImageView) r1
        L23:
            r4.imageDone = r1
            r5 = 8
            if (r1 != 0) goto L2a
            goto L2d
        L2a:
            r1.setVisibility(r5)
        L2d:
            android.os.Bundle r0 = r6.getExtras()
            java.lang.String r1 = "icon_name"
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "action_close"
            boolean r1 = kotlin.jvm.internal.n.c(r0, r1)
            r2 = 0
            if (r1 == 0) goto L50
            java.util.List<miui.systemui.notification.focus.model.ActionInfo> r1 = r4.actions
            if (r1 == 0) goto L49
            int r1 = r1.size()
            goto L4a
        L49:
            r1 = r2
        L4a:
            r3 = 1
            if (r1 <= r3) goto L50
            int r1 = com.android.systemui.miui.notification.R.drawable.close_deco_land_v2
            goto L60
        L50:
            java.util.Map<java.lang.String, java.lang.Integer> r1 = miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder.MAP
            java.lang.Object r1 = r1.get(r0)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto L5f
            int r1 = r1.intValue()
            goto L60
        L5f:
            r1 = r2
        L60:
            java.lang.String r3 = "action_done"
            boolean r3 = kotlin.jvm.internal.n.c(r0, r3)
            if (r3 == 0) goto L78
            miui.systemui.widget.AnimateImageView r3 = r4.imageDone
            if (r3 != 0) goto L6d
            goto L70
        L6d:
            r3.setVisibility(r2)
        L70:
            android.widget.LinearLayout r3 = r4.focusContainerModuleButton
            if (r3 != 0) goto L75
            goto L78
        L75:
            r3.setVisibility(r5)
        L78:
            miui.systemui.widget.AnimateImageView r5 = r4.image
            if (r5 == 0) goto L83
            android.graphics.drawable.Icon r3 = r6.getIcon()
            r5.updateImageResource(r0, r1, r3)
        L83:
            miui.systemui.widget.AnimateImageView r5 = r4.image
            if (r5 != 0) goto L88
            goto L8d
        L88:
            java.lang.CharSequence r0 = r6.title
            r5.setContentDescription(r0)
        L8d:
            miui.systemui.widget.AnimateImageView r5 = r4.image
            if (r5 == 0) goto L99
            miui.systemui.notification.focus.moduleV3.g r0 = new miui.systemui.notification.focus.moduleV3.g
            r0.<init>()
            r5.setOnClickListener(r0)
        L99:
            miui.systemui.widget.AnimateImageView r4 = r4.image
            if (r4 != 0) goto L9e
            goto La1
        L9e:
            r4.setVisibility(r2)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder.setActionData(int, android.app.Notification$Action, android.service.notification.StatusBarNotification):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionData$lambda$12(Notification.Action action, ModuleDecoLandButtonViewHolder this$0, StatusBarNotification sbn, View view) throws IOException {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(sbn, "$sbn");
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            this$0.handleBtnClick(pendingIntent, action, this$0.getSysuiCtx(), this$0.getView(), false, sbn, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setActionProgressData(int r5, final android.app.Notification.Action r6, miui.systemui.notification.focus.model.ActionInfo r7, final android.service.notification.StatusBarNotification r8) {
        /*
            r4 = this;
            if (r6 != 0) goto L3
            return
        L3:
            android.view.View r0 = r4.getView()
            r1 = 0
            if (r0 == 0) goto L11
            android.view.View r5 = r0.findViewById(r5)
            miui.systemui.widget.StatusProgressLayout r5 = (miui.systemui.widget.StatusProgressLayout) r5
            goto L12
        L11:
            r5 = r1
        L12:
            r4.statusProgressLayout = r5
            android.os.Bundle r5 = r6.getExtras()
            java.lang.String r0 = "icon_name"
            java.lang.String r5 = r5.getString(r0)
            java.lang.String r0 = "action_close"
            boolean r0 = kotlin.jvm.internal.n.c(r5, r0)
            r2 = 0
            if (r0 == 0) goto L37
            java.util.List<miui.systemui.notification.focus.model.ActionInfo> r0 = r4.actions
            if (r0 == 0) goto L30
            int r0 = r0.size()
            goto L31
        L30:
            r0 = r2
        L31:
            r3 = 1
            if (r0 <= r3) goto L37
            int r0 = com.android.systemui.miui.notification.R.drawable.close_deco_land_v2
            goto L47
        L37:
            java.util.Map<java.lang.String, java.lang.Integer> r0 = miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder.MAP
            java.lang.Object r0 = r0.get(r5)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L46
            int r0 = r0.intValue()
            goto L47
        L46:
            r0 = r2
        L47:
            if (r0 == 0) goto L4a
            goto L4e
        L4a:
            android.graphics.drawable.Icon r1 = r6.getIcon()
        L4e:
            miui.systemui.widget.StatusProgressLayout r3 = r4.statusProgressLayout
            if (r3 == 0) goto L55
            r3.updateStatusIcon(r5, r0, r1)
        L55:
            miui.systemui.widget.StatusProgressLayout r5 = r4.statusProgressLayout
            if (r5 != 0) goto L5a
            goto L68
        L5a:
            if (r7 == 0) goto L63
            java.lang.String r0 = r7.getActionTitle()
            if (r0 == 0) goto L63
            goto L65
        L63:
            java.lang.CharSequence r0 = r6.title
        L65:
            r5.setContentDescription(r0)
        L68:
            if (r7 == 0) goto L7c
            miui.systemui.notification.focus.model.ProgressInfo r5 = r7.getProgressInfo()
            if (r5 == 0) goto L7c
            int r5 = r5.getProgress()
            miui.systemui.widget.StatusProgressLayout r0 = r4.statusProgressLayout
            if (r0 == 0) goto L7c
            float r5 = (float) r5
            r0.setProgress(r5)
        L7c:
            r5 = -1
            if (r7 == 0) goto L96
            miui.systemui.notification.focus.model.ProgressInfo r0 = r7.getProgressInfo()
            if (r0 == 0) goto L96
            java.lang.String r0 = r0.getColorProgress()
            if (r0 == 0) goto L96
            miui.systemui.widget.StatusProgressLayout r1 = r4.statusProgressLayout
            if (r1 == 0) goto L96
            int r0 = android.graphics.Color.parseColor(r0)
            r1.updateColor(r5, r5, r0)
        L96:
            if (r7 == 0) goto Laf
            miui.systemui.notification.focus.model.ProgressInfo r7 = r7.getProgressInfo()
            if (r7 == 0) goto Laf
            java.lang.String r7 = r7.getColorProgressEnd()
            if (r7 == 0) goto Laf
            miui.systemui.widget.StatusProgressLayout r0 = r4.statusProgressLayout
            if (r0 == 0) goto Laf
            int r7 = android.graphics.Color.parseColor(r7)
            r0.updateColor(r5, r7, r5)
        Laf:
            miui.systemui.widget.StatusProgressLayout r5 = r4.statusProgressLayout
            if (r5 == 0) goto Lbb
            miui.systemui.notification.focus.moduleV3.h r7 = new miui.systemui.notification.focus.moduleV3.h
            r7.<init>()
            r5.setOnClickListener(r7)
        Lbb:
            miui.systemui.widget.StatusProgressLayout r4 = r4.statusProgressLayout
            if (r4 != 0) goto Lc0
            goto Lc3
        Lc0:
            r4.setVisibility(r2)
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleDecoLandButtonViewHolder.setActionProgressData(int, android.app.Notification$Action, miui.systemui.notification.focus.model.ActionInfo, android.service.notification.StatusBarNotification):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionProgressData$lambda$10(Notification.Action action, ModuleDecoLandButtonViewHolder this$0, StatusBarNotification sbn, View view) throws IOException {
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
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.notification.focus.moduleV3.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) throws IOException {
                    ModuleDecoLandButtonViewHolder.setActionTextData$lambda$5(action, this, viewFindViewById, statusBarNotification, view2);
                }
            });
        }
        if (viewFindViewById == null) {
            return;
        }
        viewFindViewById.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionTextData$lambda$5(Notification.Action action, ModuleDecoLandButtonViewHolder this$0, View view, StatusBarNotification sbn, View view2) throws IOException {
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
        ActionInfo actionInfo3;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        this.actions = template.getActions();
        View view = getView();
        LinearLayout linearLayout = view != null ? (LinearLayout) view.findViewById(R.id.focus_container_module_button) : null;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        List<ActionInfo> list = this.actions;
        if ((list != null ? Integer.valueOf(list.size()) : null) != null) {
            List<ActionInfo> list2 = this.actions;
            Integer numValueOf = list2 != null ? Integer.valueOf(list2.size()) : null;
            kotlin.jvm.internal.n.d(numValueOf);
            if (numValueOf.intValue() == 1) {
                View view2 = this.focusButtonSpace;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
                AnimateImageView animateImageView = this.focusButtonIcon2;
                if (animateImageView != null) {
                    animateImageView.setVisibility(8);
                }
                StatusProgressLayout statusProgressLayout = this.focusButtonProgress2;
                if (statusProgressLayout != null) {
                    statusProgressLayout.setVisibility(8);
                }
                List<ActionInfo> list3 = this.actions;
                Integer type = (list3 == null || (actionInfo3 = list3.get(0)) == null) ? null : actionInfo3.getType();
                if (type != null && type.intValue() == 1) {
                    AnimateImageView animateImageView2 = this.focusButtonIcon1;
                    if (animateImageView2 != null) {
                        animateImageView2.setVisibility(8);
                    }
                    StatusProgressLayout statusProgressLayout2 = this.focusButtonProgress1;
                    if (statusProgressLayout2 != null) {
                        statusProgressLayout2.setVisibility(0);
                    }
                    View view3 = this.focusButtonContainerAction;
                    if (view3 != null) {
                        view3.setVisibility(8);
                    }
                    StatusProgressLayout statusProgressLayout3 = this.focusButtonProgress1;
                    if (statusProgressLayout3 != null) {
                        CommonUtils.setLayoutWidth$default(CommonUtils.INSTANCE, statusProgressLayout3, -1, false, 2, null);
                    }
                    int i2 = R.id.focus_button_progress1;
                    List<ActionInfo> list4 = this.actions;
                    Notification.Action actionBuildAction = buildAction(list4 != null ? list4.get(0) : null, sbn);
                    List<ActionInfo> list5 = this.actions;
                    kotlin.jvm.internal.n.d(list5);
                    setActionProgressData(i2, actionBuildAction, list5.get(0), sbn);
                } else {
                    if (type != null && type.intValue() == 2) {
                        AnimateImageView animateImageView3 = this.focusButtonIcon1;
                        if (animateImageView3 != null) {
                            animateImageView3.setVisibility(8);
                        }
                        StatusProgressLayout statusProgressLayout4 = this.focusButtonProgress1;
                        if (statusProgressLayout4 != null) {
                            statusProgressLayout4.setVisibility(8);
                        }
                        View view4 = this.focusButtonContainerAction;
                        if (view4 != null) {
                            view4.setVisibility(0);
                        }
                        int i3 = R.id.focus_button_container_action;
                        List<ActionInfo> list6 = this.actions;
                        Notification.Action actionBuildAction2 = buildAction(list6 != null ? list6.get(0) : null, sbn);
                        List<ActionInfo> list7 = this.actions;
                        kotlin.jvm.internal.n.d(list7);
                        setActionTextData(i3, actionBuildAction2, list7.get(0), sbn);
                        return;
                    }
                    AnimateImageView animateImageView4 = this.focusButtonIcon1;
                    if (animateImageView4 != null) {
                        CommonUtils.setLayoutWidth$default(CommonUtils.INSTANCE, animateImageView4, -1, false, 2, null);
                    }
                    AnimateImageView animateImageView5 = this.focusButtonIcon1;
                    if (animateImageView5 != null) {
                        animateImageView5.setVisibility(0);
                    }
                    StatusProgressLayout statusProgressLayout5 = this.focusButtonProgress1;
                    if (statusProgressLayout5 != null) {
                        statusProgressLayout5.setVisibility(8);
                    }
                    View view5 = this.focusButtonContainerAction;
                    if (view5 != null) {
                        view5.setVisibility(8);
                    }
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
                View view6 = this.focusButtonSpace;
                if (view6 != null) {
                    view6.setVisibility(0);
                }
                List<ActionInfo> list11 = this.actions;
                Integer type2 = (list11 == null || (actionInfo2 = list11.get(0)) == null) ? null : actionInfo2.getType();
                if (type2 != null && type2.intValue() == 1) {
                    StatusProgressLayout statusProgressLayout6 = this.focusButtonProgress1;
                    if (statusProgressLayout6 != null) {
                        CommonUtils.setLayoutWidth$default(CommonUtils.INSTANCE, statusProgressLayout6, (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_land_action_width), false, 2, null);
                    }
                    int i5 = R.id.focus_button_progress1;
                    List<ActionInfo> list12 = this.actions;
                    Notification.Action actionBuildAction3 = buildAction(list12 != null ? list12.get(0) : null, sbn);
                    List<ActionInfo> list13 = this.actions;
                    kotlin.jvm.internal.n.d(list13);
                    setActionProgressData(i5, actionBuildAction3, list13.get(0), sbn);
                } else {
                    if (type2 != null && type2.intValue() == 2) {
                        int i6 = R.id.focus_button_container_action;
                        List<ActionInfo> list14 = this.actions;
                        Notification.Action actionBuildAction4 = buildAction(list14 != null ? list14.get(0) : null, sbn);
                        List<ActionInfo> list15 = this.actions;
                        kotlin.jvm.internal.n.d(list15);
                        setActionTextData(i6, actionBuildAction4, list15.get(0), sbn);
                        return;
                    }
                    AnimateImageView animateImageView6 = this.focusButtonIcon1;
                    if (animateImageView6 != null) {
                        CommonUtils.setLayoutWidth$default(CommonUtils.INSTANCE, animateImageView6, (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_land_action_width), false, 2, null);
                    }
                    int i7 = R.id.focus_button_icon1;
                    List<ActionInfo> list16 = this.actions;
                    setActionData(i7, buildAction(list16 != null ? list16.get(0) : null, sbn), sbn);
                }
                List<ActionInfo> list17 = this.actions;
                Integer type3 = (list17 == null || (actionInfo = list17.get(1)) == null) ? null : actionInfo.getType();
                if (type3 != null && type3.intValue() == 1) {
                    int i8 = R.id.focus_button_progress2;
                    List<ActionInfo> list18 = this.actions;
                    Notification.Action actionBuildAction5 = buildAction(list18 != null ? list18.get(1) : null, sbn);
                    List<ActionInfo> list19 = this.actions;
                    kotlin.jvm.internal.n.d(list19);
                    setActionProgressData(i8, actionBuildAction5, list19.get(1), sbn);
                    return;
                }
                if (type3 == null || type3.intValue() != 2) {
                    int i9 = R.id.focus_button_icon2;
                    List<ActionInfo> list20 = this.actions;
                    setActionData(i9, buildAction(list20 != null ? list20.get(1) : null, sbn), sbn);
                } else {
                    int i10 = R.id.focus_button_container_action;
                    List<ActionInfo> list21 = this.actions;
                    Notification.Action actionBuildAction6 = buildAction(list21 != null ? list21.get(1) : null, sbn);
                    List<ActionInfo> list22 = this.actions;
                    kotlin.jvm.internal.n.d(list22);
                    setActionTextData(i10, actionBuildAction6, list22.get(1), sbn);
                }
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_land_button_1, getRootView()));
        View view = getView();
        this.focusContainerModuleButton = view != null ? (LinearLayout) view.findViewById(R.id.focus_container_module_button) : null;
        View view2 = getView();
        this.focusButtonIcon1 = view2 != null ? (AnimateImageView) view2.findViewById(R.id.focus_button_icon1) : null;
        View view3 = getView();
        this.focusButtonProgress1 = view3 != null ? (StatusProgressLayout) view3.findViewById(R.id.focus_button_progress1) : null;
        View view4 = getView();
        this.focusButtonIcon2 = view4 != null ? (AnimateImageView) view4.findViewById(R.id.focus_button_icon2) : null;
        View view5 = getView();
        this.focusButtonProgress2 = view5 != null ? (StatusProgressLayout) view5.findViewById(R.id.focus_button_progress2) : null;
        View view6 = getView();
        this.focusButtonContainerAction = view6 != null ? view6.findViewById(R.id.focus_button_container_action) : null;
        View view7 = getView();
        this.focusButtonSpace = view7 != null ? view7.findViewById(R.id.focus_button_space) : null;
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
