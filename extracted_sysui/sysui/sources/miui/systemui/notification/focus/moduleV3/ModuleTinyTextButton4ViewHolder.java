package miui.systemui.notification.focus.moduleV3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.net.URISyntaxException;
import java.util.List;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyTextButton4ViewHolder extends ModuleViewHolder {
    private View action1;
    private TextView action1TitleView;
    private View action2;
    private TextView action2TitleView;
    private View action3;
    private TextView action3TitleView;
    private View container;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyTextButton4ViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    private final void checkAndCompressModuleButton4Views(Template template, StatusBarNotification statusBarNotification) {
        String actionTitle;
        String actionTitleColor;
        TextView textView;
        String actionTitleColor2;
        TextView textView2;
        String actionTitleColor3;
        TextView textView3;
        String actionBgColor;
        String actionBgColor2;
        String actionBgColor3;
        String actionTitleColor4;
        TextView textView4;
        String actionTitleColor5;
        TextView textView5;
        String actionBgColor4;
        String actionBgColor5;
        String actionTitleColor6;
        TextView textView6;
        String actionBgColor6;
        List<ActionInfo> textButton = template.getTextButton();
        Integer numValueOf = textButton != null ? Integer.valueOf(textButton.size()) : null;
        if (numValueOf != null && numValueOf.intValue() == 1) {
            View view = this.action1;
            if (view != null) {
                view.setVisibility(0);
            }
            View view2 = this.action2;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            View view3 = this.action3;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            List<ActionInfo> textButton2 = template.getTextButton();
            ActionInfo actionInfo = textButton2 != null ? textButton2.get(0) : null;
            if (actionInfo != null && (actionBgColor6 = actionInfo.getActionBgColor()) != null) {
                Drawable drawable = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
                kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                gradientDrawable.setColor(Color.parseColor(actionBgColor6));
                View view4 = this.action1;
                kotlin.jvm.internal.n.d(view4);
                view4.setBackground(gradientDrawable);
            }
            if (actionInfo != null && (actionTitleColor6 = actionInfo.getActionTitleColor()) != null && (textView6 = this.action1TitleView) != null) {
                textView6.setTextColor(Color.parseColor(actionTitleColor6));
            }
            actionTitle = actionInfo != null ? actionInfo.getActionTitle() : null;
            TextView textView7 = this.action1TitleView;
            if (textView7 == null) {
                return;
            }
            textView7.setText(Html.fromHtml(actionTitle));
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 2) {
            View view5 = this.action1;
            if (view5 != null) {
                view5.setVisibility(0);
            }
            View view6 = this.action2;
            if (view6 != null) {
                view6.setVisibility(0);
            }
            View view7 = this.action3;
            if (view7 != null) {
                view7.setVisibility(8);
            }
            List<ActionInfo> textButton3 = template.getTextButton();
            ActionInfo actionInfo2 = textButton3 != null ? textButton3.get(0) : null;
            if (actionInfo2 != null && (actionBgColor5 = actionInfo2.getActionBgColor()) != null) {
                Drawable drawable2 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
                kotlin.jvm.internal.n.e(drawable2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                GradientDrawable gradientDrawable2 = (GradientDrawable) drawable2;
                gradientDrawable2.setColor(Color.parseColor(actionBgColor5));
                View view8 = this.action1;
                kotlin.jvm.internal.n.d(view8);
                view8.setBackground(gradientDrawable2);
            }
            List<ActionInfo> textButton4 = template.getTextButton();
            ActionInfo actionInfo3 = textButton4 != null ? textButton4.get(1) : null;
            if (actionInfo3 != null && (actionBgColor4 = actionInfo3.getActionBgColor()) != null) {
                Drawable drawable3 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
                kotlin.jvm.internal.n.e(drawable3, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                GradientDrawable gradientDrawable3 = (GradientDrawable) drawable3;
                gradientDrawable3.setColor(Color.parseColor(actionBgColor4));
                View view9 = this.action2;
                kotlin.jvm.internal.n.d(view9);
                view9.setBackground(gradientDrawable3);
            }
            if (actionInfo2 != null && (actionTitleColor5 = actionInfo2.getActionTitleColor()) != null && (textView5 = this.action1TitleView) != null) {
                textView5.setTextColor(Color.parseColor(actionTitleColor5));
            }
            String actionTitle2 = actionInfo2 != null ? actionInfo2.getActionTitle() : null;
            TextView textView8 = this.action1TitleView;
            if (textView8 != null) {
                textView8.setText(Html.fromHtml(actionTitle2));
            }
            if (actionInfo3 != null && (actionTitleColor4 = actionInfo3.getActionTitleColor()) != null && (textView4 = this.action2TitleView) != null) {
                textView4.setTextColor(Color.parseColor(actionTitleColor4));
            }
            actionTitle = actionInfo3 != null ? actionInfo3.getActionTitle() : null;
            TextView textView9 = this.action2TitleView;
            if (textView9 == null) {
                return;
            }
            textView9.setText(Html.fromHtml(actionTitle));
            return;
        }
        if (numValueOf == null || numValueOf.intValue() != 3) {
            View view10 = this.action1;
            if (view10 != null) {
                view10.setVisibility(8);
            }
            View view11 = this.action2;
            if (view11 != null) {
                view11.setVisibility(8);
            }
            View view12 = this.action3;
            if (view12 == null) {
                return;
            }
            view12.setVisibility(8);
            return;
        }
        View view13 = this.action1;
        if (view13 != null) {
            view13.setVisibility(0);
        }
        View view14 = this.action2;
        if (view14 != null) {
            view14.setVisibility(0);
        }
        View view15 = this.action3;
        if (view15 != null) {
            view15.setVisibility(0);
        }
        List<ActionInfo> textButton5 = template.getTextButton();
        ActionInfo actionInfo4 = textButton5 != null ? textButton5.get(0) : null;
        if (actionInfo4 != null && (actionBgColor3 = actionInfo4.getActionBgColor()) != null) {
            Drawable drawable4 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable4, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable4 = (GradientDrawable) drawable4;
            gradientDrawable4.setColor(Color.parseColor(actionBgColor3));
            View view16 = this.action1;
            kotlin.jvm.internal.n.d(view16);
            view16.setBackground(gradientDrawable4);
        }
        List<ActionInfo> textButton6 = template.getTextButton();
        ActionInfo actionInfo5 = textButton6 != null ? textButton6.get(1) : null;
        if (actionInfo5 != null && (actionBgColor2 = actionInfo5.getActionBgColor()) != null) {
            Drawable drawable5 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable5, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable5 = (GradientDrawable) drawable5;
            gradientDrawable5.setColor(Color.parseColor(actionBgColor2));
            View view17 = this.action2;
            kotlin.jvm.internal.n.d(view17);
            view17.setBackground(gradientDrawable5);
        }
        List<ActionInfo> textButton7 = template.getTextButton();
        ActionInfo actionInfo6 = textButton7 != null ? textButton7.get(2) : null;
        if (actionInfo6 != null && (actionBgColor = actionInfo6.getActionBgColor()) != null) {
            Drawable drawable6 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable6, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable6 = (GradientDrawable) drawable6;
            gradientDrawable6.setColor(Color.parseColor(actionBgColor));
            View view18 = this.action3;
            kotlin.jvm.internal.n.d(view18);
            view18.setBackground(gradientDrawable6);
        }
        if (actionInfo4 != null && (actionTitleColor3 = actionInfo4.getActionTitleColor()) != null && (textView3 = this.action1TitleView) != null) {
            textView3.setTextColor(Color.parseColor(actionTitleColor3));
        }
        String actionTitle3 = actionInfo4 != null ? actionInfo4.getActionTitle() : null;
        TextView textView10 = this.action1TitleView;
        if (textView10 != null) {
            textView10.setText(Html.fromHtml(actionTitle3));
        }
        if (actionInfo5 != null && (actionTitleColor2 = actionInfo5.getActionTitleColor()) != null && (textView2 = this.action2TitleView) != null) {
            textView2.setTextColor(Color.parseColor(actionTitleColor2));
        }
        String actionTitle4 = actionInfo5 != null ? actionInfo5.getActionTitle() : null;
        TextView textView11 = this.action2TitleView;
        if (textView11 != null) {
            textView11.setText(Html.fromHtml(actionTitle4));
        }
        if (actionInfo6 != null && (actionTitleColor = actionInfo6.getActionTitleColor()) != null && (textView = this.action3TitleView) != null) {
            textView.setTextColor(Color.parseColor(actionTitleColor));
        }
        actionTitle = actionInfo6 != null ? actionInfo6.getActionTitle() : null;
        TextView textView12 = this.action3TitleView;
        if (textView12 == null) {
            return;
        }
        textView12.setText(Html.fromHtml(actionTitle));
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        ModuleViewHolder.setActionData$default(this, template, sbn, false, 4, null);
        checkAndCompressModuleButton4Views(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_button_4, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_button_4) : null;
        View view2 = getView();
        View viewFindViewById = view2 != null ? view2.findViewById(R.id.focus_button_container_action1) : null;
        this.action1 = viewFindViewById;
        this.action1TitleView = viewFindViewById != null ? (TextView) viewFindViewById.findViewById(R.id.focus_button_title) : null;
        View view3 = getView();
        View viewFindViewById2 = view3 != null ? view3.findViewById(R.id.focus_button_container_action2) : null;
        this.action2 = viewFindViewById2;
        this.action2TitleView = viewFindViewById2 != null ? (TextView) viewFindViewById2.findViewById(R.id.focus_button_title) : null;
        View view4 = getView();
        View viewFindViewById3 = view4 != null ? view4.findViewById(R.id.focus_button_container_action3) : null;
        this.action3 = viewFindViewById3;
        this.action3TitleView = viewFindViewById3 != null ? (TextView) viewFindViewById3.findViewById(R.id.focus_button_title) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
