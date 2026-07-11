package miui.systemui.notification.focus.moduleV3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.net.URISyntaxException;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.HighlightInfoV3;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.PaletteUtils;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTextButton5ViewHolder extends ModuleViewHolder {
    private View action;
    private ImageView actionButtonIcon;
    private TimerTextEffectView actionTitleView;
    private View container;
    private View focusSubTextContainer;
    private TimerTextEffectView highLightTextView;
    private final boolean island;
    private TimerTextEffectView primaryTextView;
    private TimerTextEffectView secondaryTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTextButton5ViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    private final void updateParams() {
        ImageView imageView = this.actionButtonIcon;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        TimerTextEffectView timerTextEffectView = this.actionTitleView;
        if ((timerTextEffectView != null ? timerTextEffectView.getLayoutParams() : null) instanceof LinearLayout.LayoutParams) {
            TimerTextEffectView timerTextEffectView2 = this.actionTitleView;
            ViewGroup.LayoutParams layoutParams = timerTextEffectView2 != null ? timerTextEffectView2.getLayoutParams() : null;
            kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginStart((int) getCtx().getResources().getDimension(R.dimen.focus_notify_button_without_icon_margin_start));
            TimerTextEffectView timerTextEffectView3 = this.actionTitleView;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setLayoutParams(layoutParams2);
            }
        }
        TimerTextEffectView timerTextEffectView4 = this.actionTitleView;
        if ((timerTextEffectView4 != null ? timerTextEffectView4.getLayoutParams() : null) instanceof FrameLayout.LayoutParams) {
            TimerTextEffectView timerTextEffectView5 = this.actionTitleView;
            ViewGroup.LayoutParams layoutParams3 = timerTextEffectView5 != null ? timerTextEffectView5.getLayoutParams() : null;
            kotlin.jvm.internal.n.e(layoutParams3, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams3;
            layoutParams4.setMarginStart((int) getCtx().getResources().getDimension(R.dimen.focus_notify_button_without_icon_margin_start));
            TimerTextEffectView timerTextEffectView6 = this.actionTitleView;
            if (timerTextEffectView6 == null) {
                return;
            }
            timerTextEffectView6.setLayoutParams(layoutParams4);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        ActionInfo actionInfo;
        TimerTextEffectView timerTextEffectView;
        TimerTextEffectView timerTextEffectView2;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getHighlightInfoV3());
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        setActionData(template, sbn, this.island);
        String primaryText = getPrimaryText();
        if (primaryText != null && !TextUtils.isEmpty(primaryText)) {
            TimerTextEffectView timerTextEffectView3 = this.primaryTextView;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView4 = this.primaryTextView;
            if (timerTextEffectView4 != null) {
                timerTextEffectView4.setText(Html.fromHtml(primaryText), TextView.BufferType.SPANNABLE);
            }
            Integer primaryColor = getPrimaryColor();
            if (primaryColor != null) {
                int iIntValue = primaryColor.intValue();
                TimerTextEffectView timerTextEffectView5 = this.primaryTextView;
                if (timerTextEffectView5 != null) {
                    timerTextEffectView5.updateTextWithNewAppearance(Html.fromHtml(primaryText), Integer.valueOf(iIntValue));
                }
            }
        }
        String secondaryText = getSecondaryText();
        if (secondaryText != null && !TextUtils.isEmpty(secondaryText)) {
            TimerTextEffectView timerTextEffectView6 = this.secondaryTextView;
            if (timerTextEffectView6 != null) {
                timerTextEffectView6.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView7 = this.secondaryTextView;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setText(Html.fromHtml(secondaryText), TextView.BufferType.SPANNABLE);
            }
            if (kotlin.jvm.internal.n.c(getShowSecondaryLine(), Boolean.TRUE) && (timerTextEffectView2 = this.secondaryTextView) != null) {
                timerTextEffectView2.setPaintFlags(timerTextEffectView2 != null ? timerTextEffectView2.getPaintFlags() | 16 : 0);
            }
            Integer secondaryColor = getSecondaryColor();
            if (secondaryColor != null) {
                int iIntValue2 = secondaryColor.intValue();
                TimerTextEffectView timerTextEffectView8 = this.secondaryTextView;
                if (timerTextEffectView8 != null) {
                    timerTextEffectView8.updateTextWithNewAppearance(Html.fromHtml(secondaryText), Integer.valueOf(iIntValue2));
                }
            }
        }
        String highLightText = getHighLightText();
        if (highLightText != null && !TextUtils.isEmpty(highLightText)) {
            TimerTextEffectView timerTextEffectView9 = this.highLightTextView;
            if (timerTextEffectView9 != null) {
                timerTextEffectView9.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView10 = this.highLightTextView;
            if (timerTextEffectView10 != null) {
                timerTextEffectView10.setText(Html.fromHtml(highLightText), TextView.BufferType.SPANNABLE);
            }
            Integer highLightTextColor = getHighLightTextColor();
            if (highLightTextColor != null) {
                int iIntValue3 = highLightTextColor.intValue();
                TimerTextEffectView timerTextEffectView11 = this.highLightTextView;
                if (timerTextEffectView11 != null) {
                    timerTextEffectView11.updateTextWithNewAppearance(Html.fromHtml(highLightText), Integer.valueOf(iIntValue3));
                }
            }
            Integer highLightbgColor = getHighLightbgColor();
            if (highLightbgColor != null) {
                int iIntValue4 = highLightbgColor.intValue();
                Drawable drawable = getCtx().getResources().getDrawable(R.drawable.focus_tiny_text_background);
                kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                gradientDrawable.setColor(iIntValue4);
                View view2 = this.focusSubTextContainer;
                kotlin.jvm.internal.n.d(view2);
                view2.setBackground(gradientDrawable);
            }
        }
        HighlightInfoV3 highlightInfoV3 = template.getHighlightInfoV3();
        if (highlightInfoV3 == null || (actionInfo = highlightInfoV3.getActionInfo()) == null || TextUtils.isEmpty(actionInfo.getAction())) {
            return;
        }
        TimerTextEffectView timerTextEffectView12 = this.actionTitleView;
        if (timerTextEffectView12 != null) {
            timerTextEffectView12.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView13 = this.actionTitleView;
        if (timerTextEffectView13 != null) {
            timerTextEffectView13.setText(Html.fromHtml(actionInfo.getActionTitle()), TextView.BufferType.SPANNABLE);
        }
        String actionTitleColor = actionInfo.getActionTitleColor(isDark());
        if (actionTitleColor != null && (timerTextEffectView = this.actionTitleView) != null) {
            timerTextEffectView.updateTextWithNewAppearance(Html.fromHtml(actionInfo.getActionTitle()), Integer.valueOf(Color.parseColor(actionTitleColor)));
        }
        String actionBgColor = actionInfo.getActionBgColor(isDark());
        if (actionBgColor != null) {
            Drawable drawableMutate = getCtx().getResources().getDrawable(R.drawable.focus_button_background_n).mutate();
            kotlin.jvm.internal.n.e(drawableMutate, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable2 = (GradientDrawable) drawableMutate;
            gradientDrawable2.setColor(Color.parseColor(actionBgColor));
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawableMutate2 = getCtx().getResources().getDrawable(R.drawable.focus_button_background_p).mutate();
            kotlin.jvm.internal.n.e(drawableMutate2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable3 = (GradientDrawable) drawableMutate2;
            gradientDrawable3.setColor(PaletteUtils.blendWithColor(Color.parseColor(actionBgColor), isDark()));
            String actionBgPressColor = actionInfo.getActionBgPressColor(isDark());
            if (actionBgPressColor != null) {
                gradientDrawable3.setColor(Color.parseColor(actionBgPressColor));
            }
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable3);
            stateListDrawable.addState(new int[0], gradientDrawable2);
            View view3 = this.action;
            if (view3 != null) {
                view3.setBackground(stateListDrawable);
            }
        }
        if (actionInfo.getActionIcon() == null) {
            updateParams();
            return;
        }
        ImageView imageView = this.actionButtonIcon;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(0);
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_button_5, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_button_5) : null;
        View view2 = getView();
        this.primaryTextView = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.focus_primary_text) : null;
        View view3 = getView();
        this.secondaryTextView = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.focus_secondary_text) : null;
        View view4 = getView();
        this.highLightTextView = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.focus_highLight_text) : null;
        View view5 = getView();
        this.focusSubTextContainer = view5 != null ? view5.findViewById(R.id.focus_sub_text_container) : null;
        View view6 = getView();
        View viewFindViewById = view6 != null ? view6.findViewById(R.id.focus_button_container_action) : null;
        this.action = viewFindViewById;
        this.actionTitleView = viewFindViewById != null ? (TimerTextEffectView) viewFindViewById.findViewById(R.id.focus_button_title) : null;
        View view7 = this.action;
        this.actionButtonIcon = view7 != null ? (ImageView) view7.findViewById(R.id.button_icon) : null;
        TimerTextEffectView timerTextEffectView = this.primaryTextView;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.primaryTextView;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView3 = this.secondaryTextView;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.secondaryTextView;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView5 = this.highLightTextView;
        if (timerTextEffectView5 != null) {
            timerTextEffectView5.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView6 = this.highLightTextView;
        if (timerTextEffectView6 != null) {
            timerTextEffectView6.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView7 = this.actionTitleView;
        if (timerTextEffectView7 != null) {
            timerTextEffectView7.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView8 = this.actionTitleView;
        if (timerTextEffectView8 == null) {
            return;
        }
        timerTextEffectView8.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getHighlightInfoV3());
        bind(template, sbn);
    }
}
