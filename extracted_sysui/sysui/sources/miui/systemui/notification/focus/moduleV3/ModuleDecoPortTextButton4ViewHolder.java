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
public final class ModuleDecoPortTextButton4ViewHolder extends ModuleViewHolder {
    private View action1;
    private TextView action1TitleView;
    private View action2;
    private TextView action2TitleView;
    private View container;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortTextButton4ViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
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
        String actionBgColor;
        String actionBgColor2;
        String actionTitleColor3;
        TextView textView3;
        String actionBgColor3;
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
            List<ActionInfo> textButton2 = template.getTextButton();
            ActionInfo actionInfo = textButton2 != null ? textButton2.get(0) : null;
            if (actionInfo != null && (actionBgColor3 = actionInfo.getActionBgColor()) != null) {
                Drawable drawable = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
                kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                gradientDrawable.setColor(Color.parseColor(actionBgColor3));
                View view3 = this.action1;
                kotlin.jvm.internal.n.d(view3);
                view3.setBackground(gradientDrawable);
            }
            if (actionInfo != null && (actionTitleColor3 = actionInfo.getActionTitleColor()) != null && (textView3 = this.action1TitleView) != null) {
                textView3.setTextColor(Color.parseColor(actionTitleColor3));
            }
            actionTitle = actionInfo != null ? actionInfo.getActionTitle() : null;
            TextView textView4 = this.action1TitleView;
            if (textView4 == null) {
                return;
            }
            textView4.setText(Html.fromHtml(actionTitle));
            return;
        }
        if (numValueOf == null || numValueOf.intValue() != 2) {
            View view4 = this.action1;
            if (view4 != null) {
                view4.setVisibility(8);
            }
            View view5 = this.action2;
            if (view5 == null) {
                return;
            }
            view5.setVisibility(8);
            return;
        }
        View view6 = this.action1;
        if (view6 != null) {
            view6.setVisibility(0);
        }
        View view7 = this.action2;
        if (view7 != null) {
            view7.setVisibility(0);
        }
        List<ActionInfo> textButton3 = template.getTextButton();
        ActionInfo actionInfo2 = textButton3 != null ? textButton3.get(0) : null;
        if (actionInfo2 != null && (actionBgColor2 = actionInfo2.getActionBgColor()) != null) {
            Drawable drawable2 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable2 = (GradientDrawable) drawable2;
            gradientDrawable2.setColor(Color.parseColor(actionBgColor2));
            View view8 = this.action1;
            kotlin.jvm.internal.n.d(view8);
            view8.setBackground(gradientDrawable2);
        }
        List<ActionInfo> textButton4 = template.getTextButton();
        ActionInfo actionInfo3 = textButton4 != null ? textButton4.get(1) : null;
        if (actionInfo3 != null && (actionBgColor = actionInfo3.getActionBgColor()) != null) {
            Drawable drawable3 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable3, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable3 = (GradientDrawable) drawable3;
            gradientDrawable3.setColor(Color.parseColor(actionBgColor));
            View view9 = this.action2;
            kotlin.jvm.internal.n.d(view9);
            view9.setBackground(gradientDrawable3);
        }
        if (actionInfo2 != null && (actionTitleColor2 = actionInfo2.getActionTitleColor()) != null && (textView2 = this.action2TitleView) != null) {
            textView2.setTextColor(Color.parseColor(actionTitleColor2));
        }
        String actionTitle2 = actionInfo2 != null ? actionInfo2.getActionTitle() : null;
        TextView textView5 = this.action1TitleView;
        if (textView5 != null) {
            textView5.setText(Html.fromHtml(actionTitle2));
        }
        if (actionInfo3 != null && (actionTitleColor = actionInfo3.getActionTitleColor()) != null && (textView = this.action2TitleView) != null) {
            textView.setTextColor(Color.parseColor(actionTitleColor));
        }
        actionTitle = actionInfo3 != null ? actionInfo3.getActionTitle() : null;
        TextView textView6 = this.action2TitleView;
        if (textView6 == null) {
            return;
        }
        textView6.setText(Html.fromHtml(actionTitle));
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
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_button_4, getRootView()));
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
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
