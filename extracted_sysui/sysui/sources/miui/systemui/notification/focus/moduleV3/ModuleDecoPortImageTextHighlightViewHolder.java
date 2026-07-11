package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.HighlightInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.model.TimerInfo;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoPortImageTextHighlightViewHolder extends ModuleViewHolder {
    private HyperChronometer chronometer;
    private View container;
    private TextView focusContent;
    private ImageView focusIcon;
    private TextView focusSubContent;
    private TextView focusTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortImageTextHighlightViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void showFocusIcon(Template template, StatusBarNotification statusBarNotification) {
        Icon icon;
        if (isDark()) {
            HighlightInfo highlightInfo = template.getHighlightInfo();
            icon = getIcon(highlightInfo != null ? highlightInfo.getPicFunctionDark() : null, statusBarNotification);
            if (icon == null) {
                HighlightInfo highlightInfo2 = template.getHighlightInfo();
                icon = getIcon(highlightInfo2 != null ? highlightInfo2.getPicFunction() : null, statusBarNotification);
            }
        } else {
            HighlightInfo highlightInfo3 = template.getHighlightInfo();
            icon = getIcon(highlightInfo3 != null ? highlightInfo3.getPicFunction() : null, statusBarNotification);
        }
        View view = getView();
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.focus_function_icon) : null;
        if (icon != null) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView != null) {
                imageView.setImageIcon(icon);
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getHighlightInfo());
        HighlightInfo highlightInfo = template.getHighlightInfo();
        initTimerData(highlightInfo != null ? highlightInfo.getTimerInfo() : null);
        adaptTimerDelay();
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        if (TextUtils.isEmpty(getTitle())) {
            HyperChronometer hyperChronometer = this.chronometer;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(0);
            }
            TextView textView = this.focusTitle;
            if (textView != null) {
                textView.setVisibility(8);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                HyperChronometer hyperChronometer2 = this.chronometer;
                if (hyperChronometer2 != null) {
                    hyperChronometer2.setTextColor(iIntValue);
                }
            }
        } else {
            HyperChronometer hyperChronometer3 = this.chronometer;
            if (hyperChronometer3 != null) {
                hyperChronometer3.setVisibility(8);
            }
            TextView textView2 = this.focusTitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.focusTitle;
            if (textView3 != null) {
                textView3.setText(Html.fromHtml(getTitle()));
            }
            Integer titleColor2 = getTitleColor();
            if (titleColor2 != null) {
                int iIntValue2 = titleColor2.intValue();
                TextView textView4 = this.focusTitle;
                if (textView4 != null) {
                    textView4.setTextColor(iIntValue2);
                }
            }
        }
        HighlightInfo highlightInfo2 = template.getHighlightInfo();
        if (highlightInfo2 == null || highlightInfo2.getType() != 1) {
            TextView textView5 = this.focusContent;
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
        } else {
            TextView textView6 = this.focusContent;
            if (textView6 != null) {
                textView6.setVisibility(8);
            }
        }
        String content = getContent();
        if (content != null && content.length() > 0) {
            TextView textView7 = this.focusContent;
            if (textView7 != null) {
                textView7.setVisibility(0);
            }
            TextView textView8 = this.focusContent;
            if (textView8 != null) {
                textView8.setText(Html.fromHtml(getContent()));
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue3 = contentColor.intValue();
                TextView textView9 = this.focusContent;
                if (textView9 != null) {
                    textView9.setTextColor(iIntValue3);
                }
            }
        }
        String subContent = getSubContent();
        if (subContent != null && subContent.length() > 0) {
            TextView textView10 = this.focusSubContent;
            if (textView10 != null) {
                textView10.setVisibility(0);
            }
            TextView textView11 = this.focusSubContent;
            if (textView11 != null) {
                textView11.setText(Html.fromHtml(getSubContent()));
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue4 = subContentColor.intValue();
                TextView textView12 = this.focusSubContent;
                if (textView12 != null) {
                    textView12.setTextColor(iIntValue4);
                }
            }
        }
        HighlightInfo highlightInfo3 = template.getHighlightInfo();
        if ((highlightInfo3 != null ? highlightInfo3.getTimerInfo() : null) != null) {
            ModuleViewHolder.setTimerData$default(this, 0, sbn, 1, null);
        }
        showFocusIcon(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void checkParams(Template template) throws FocusParamsException {
        TimerInfo timerInfo;
        TimerInfo timerInfo2;
        kotlin.jvm.internal.n.g(template, "template");
        super.checkParams(template);
        HighlightInfo highlightInfo = template.getHighlightInfo();
        Integer numValueOf = null;
        if (TextUtils.isEmpty(highlightInfo != null ? highlightInfo.getTitle() : null)) {
            HighlightInfo highlightInfo2 = template.getHighlightInfo();
            if (highlightInfo2 != null && (timerInfo2 = highlightInfo2.getTimerInfo()) != null) {
                numValueOf = Integer.valueOf(timerInfo2.getTimerType());
            }
            if (numValueOf == null || ((timerInfo = template.getHighlightInfo().getTimerInfo()) != null && timerInfo.getTimerType() == 0)) {
                throw new FocusParamsException("title is empty");
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(kotlin.jvm.internal.n.c(module, Const.Module.MODULE_IMAGE_TEXT_HIGHLIGHT_TIME) ? LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_image_text_highlight_timer, getRootView()) : LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_image_text_highlight_sport, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_image_text_highlight) : null;
        View view2 = getView();
        this.chronometer = view2 != null ? (HyperChronometer) view2.findViewById(R.id.chronometer) : null;
        View view3 = getView();
        this.focusTitle = view3 != null ? (TextView) view3.findViewById(R.id.focus_title) : null;
        View view4 = getView();
        this.focusContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_content) : null;
        View view5 = getView();
        this.focusSubContent = view5 != null ? (TextView) view5.findViewById(R.id.focus_sub_content) : null;
        View view6 = getView();
        this.focusIcon = view6 != null ? (ImageView) view6.findViewById(R.id.focus_function_icon) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getHighlightInfo());
        bind(template, sbn);
    }
}
