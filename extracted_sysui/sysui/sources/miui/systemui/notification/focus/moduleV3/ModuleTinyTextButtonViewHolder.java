package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Icon;
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
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.Template;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyTextButtonViewHolder extends ModuleViewHolder {
    private HyperChronometer chronometerHint;
    private TextView focusSmallTitle;
    private TextView frontContent;
    private TextView frontSubContent;
    private ImageView functionIconView;
    private LinearLayout layout;
    private TextView smallSubTitle;
    private TextView specialTitleView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyTextButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void setLineColor(Template template, StatusBarNotification statusBarNotification) {
        View view;
        FrameLayout frameLayout;
        if (!hasCustomBackground(template, statusBarNotification) || (view = getView()) == null || (frameLayout = (FrameLayout) view.findViewById(R.id.line)) == null) {
            return;
        }
        frameLayout.setBackgroundColor(getCtx().getResources().getColor(R.color.lineColorDark));
    }

    private final void setSpecialTextContainerData(Template template, StatusBarNotification statusBarNotification) {
        View view = getView();
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(R.id.focus_text_container) : null;
        if (TextUtils.isEmpty(getContent())) {
            if (frameLayout == null) {
                return;
            }
            frameLayout.setVisibility(8);
            return;
        }
        TextView textView = this.specialTitleView;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.specialTitleView;
        if (textView2 != null) {
            textView2.setText(Html.fromHtml(getContent()));
        }
        Integer contentColor = getContentColor();
        if (contentColor != null) {
            int iIntValue = contentColor.intValue();
            TextView textView3 = this.specialTitleView;
            if (textView3 != null) {
                textView3.setTextColor(iIntValue);
            }
        }
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        HintInfo hintInfo = template.getHintInfo();
        Icon icon = getIcon(hintInfo != null ? hintInfo.getPicContent() : null, statusBarNotification);
        TextView textView4 = this.specialTitleView;
        if (textView4 != null) {
            ViewGroup.LayoutParams layoutParams = textView4 != null ? textView4.getLayoutParams() : null;
            kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            if (icon != null) {
                ImageView imageView = this.functionIconView;
                if (imageView != null) {
                    imageView.setVisibility(0);
                }
                ImageView imageView2 = this.functionIconView;
                if (imageView2 != null) {
                    imageView2.setImageIcon(icon);
                }
                layoutParams2.setMarginStart((int) getCtx().getResources().getDimension(R.dimen.focus_notify_text_with_icon_margin_start));
            } else {
                ImageView imageView3 = this.functionIconView;
                if (imageView3 != null) {
                    imageView3.setVisibility(8);
                }
                layoutParams2.setMarginStart((int) getCtx().getResources().getDimension(R.dimen.focus_notify_text_without_icon_margin_start));
            }
            TextView textView5 = this.specialTitleView;
            if (textView5 != null) {
                textView5.setLayoutParams(layoutParams2);
            }
        }
        setTextContainerBackground();
    }

    private final void setTextContainerBackground() {
        if (getColorContentBg() != null) {
            View view = getView();
            FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(R.id.focus_text_container) : null;
            if (frameLayout != null) {
                frameLayout.setBackgroundResource(R.drawable.focus_text_background_no_alpha);
            }
            if (frameLayout == null) {
                return;
            }
            Integer colorContentBg = getColorContentBg();
            kotlin.jvm.internal.n.d(colorContentBg);
            frameLayout.setBackgroundTintList(ColorStateList.valueOf(colorContentBg.intValue()));
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getHintInfo());
        if (TextUtils.isEmpty(getTitle())) {
            HyperChronometer hyperChronometer = this.chronometerHint;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(0);
            }
            TextView textView = this.focusSmallTitle;
            if (textView != null) {
                textView.setVisibility(8);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                HyperChronometer hyperChronometer2 = this.chronometerHint;
                if (hyperChronometer2 != null) {
                    hyperChronometer2.setTextColor(iIntValue);
                }
            }
        } else {
            HyperChronometer hyperChronometer3 = this.chronometerHint;
            if (hyperChronometer3 != null) {
                hyperChronometer3.setVisibility(8);
            }
            TextView textView2 = this.focusSmallTitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.focusSmallTitle;
            if (textView3 != null) {
                textView3.setText(Html.fromHtml(getTitle()));
            }
            Integer titleColor2 = getTitleColor();
            if (titleColor2 != null) {
                int iIntValue2 = titleColor2.intValue();
                TextView textView4 = this.focusSmallTitle;
                if (textView4 != null) {
                    textView4.setTextColor(iIntValue2);
                }
            }
        }
        TextView textView5 = this.frontContent;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
        TextView textView6 = this.frontContent;
        if (textView6 != null) {
            textView6.setText(Html.fromHtml(getContent()));
        }
        Integer contentColor = getContentColor();
        if (contentColor != null) {
            int iIntValue3 = contentColor.intValue();
            TextView textView7 = this.frontContent;
            if (textView7 != null) {
                textView7.setTextColor(iIntValue3);
            }
        }
        String subtitle = getSubtitle();
        if (subtitle != null && !TextUtils.isEmpty(subtitle)) {
            TextView textView8 = this.smallSubTitle;
            if (textView8 != null) {
                textView8.setVisibility(0);
            }
            TextView textView9 = this.smallSubTitle;
            if (textView9 != null) {
                textView9.setText(Html.fromHtml(subtitle));
            }
            Integer subTitleColor = getSubTitleColor();
            if (subTitleColor != null) {
                int iIntValue4 = subTitleColor.intValue();
                TextView textView10 = this.smallSubTitle;
                if (textView10 != null) {
                    textView10.setTextColor(iIntValue4);
                }
            }
        }
        String subContent = getSubContent();
        if (subContent != null && !TextUtils.isEmpty(subContent)) {
            TextView textView11 = this.frontSubContent;
            if (textView11 != null) {
                textView11.setVisibility(0);
            }
            TextView textView12 = this.frontSubContent;
            if (textView12 != null) {
                textView12.setText(Html.fromHtml(subContent));
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue5 = subContentColor.intValue();
                TextView textView13 = this.frontSubContent;
                if (textView13 != null) {
                    textView13.setTextColor(iIntValue5);
                }
            }
        }
        if (TextUtils.isEmpty(getSubtitle()) || TextUtils.isEmpty(getSubContent())) {
            TextView textView14 = this.smallSubTitle;
            if (textView14 != null) {
                textView14.setVisibility(8);
            }
            TextView textView15 = this.frontSubContent;
            if (textView15 != null) {
                textView15.setVisibility(8);
            }
        }
        HintInfo hintInfo = template.getHintInfo();
        if ((hintInfo != null ? hintInfo.getTimerInfo() : null) != null) {
            setTimerData(R.id.chronometer_hint, sbn);
        }
        ModuleViewHolder.setActionData$default(this, template, sbn, false, 4, null);
        setSpecialTextContainerData(template, sbn);
        setLineColor(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(kotlin.jvm.internal.n.c(module, Const.Module.MODULE_BUTTON_2) ? LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_button_2, getRootView()) : LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_button_3, getRootView()));
        View view = getView();
        this.layout = view != null ? (LinearLayout) view.findViewById(R.id.layout_hint) : null;
        View view2 = getView();
        this.chronometerHint = view2 != null ? (HyperChronometer) view2.findViewById(R.id.chronometer_hint) : null;
        View view3 = getView();
        this.focusSmallTitle = view3 != null ? (TextView) view3.findViewById(R.id.focus_small_title) : null;
        View view4 = getView();
        this.frontContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_front_content) : null;
        View view5 = getView();
        this.smallSubTitle = view5 != null ? (TextView) view5.findViewById(R.id.focus_small_subtitle) : null;
        View view6 = getView();
        this.frontSubContent = view6 != null ? (TextView) view6.findViewById(R.id.focus_front_sub_content) : null;
        View view7 = getView();
        this.functionIconView = view7 != null ? (ImageView) view7.findViewById(R.id.text_icon) : null;
        View view8 = getView();
        this.specialTitleView = view8 != null ? (TextView) view8.findViewById(R.id.focus_text_title) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
