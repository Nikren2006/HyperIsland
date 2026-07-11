package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.Color;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.net.URISyntaxException;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.Template;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoPortTextButtonViewHolder extends ModuleViewHolder {
    private HyperChronometer chronometerHintView;
    private FrameLayout focusButtonContainer;
    private TextView focusButtonTitleView;
    private TextView focusSmallSubtitleView;
    private TextView focusSmallTitleView;
    private TextView focusTextTitleView;
    private LinearLayout hintLayout;
    private FrameLayout line;
    private FrameLayout titleHintLayout;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortTextButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void setLineColor(Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout;
        if (!hasCustomBackground(template, statusBarNotification) || (frameLayout = this.line) == null) {
            return;
        }
        frameLayout.setBackgroundColor(getCtx().getResources().getColor(R.color.lineColorDark));
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        Integer numValueOf;
        String subtitle;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getHintInfo());
        try {
            HintInfo hintInfo = template.getHintInfo();
            numValueOf = Integer.valueOf(Color.parseColor(hintInfo != null ? hintInfo.getColorContentBg() : null));
        } catch (Exception unused) {
            numValueOf = null;
        }
        setColorContentBg(numValueOf);
        HintInfo hintInfo2 = template.getHintInfo();
        initTimerData(hintInfo2 != null ? hintInfo2.getTimerInfo() : null);
        if (TextUtils.isEmpty(getTitle())) {
            HyperChronometer hyperChronometer = this.chronometerHintView;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(0);
            }
            TextView textView = this.focusSmallTitleView;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            HyperChronometer hyperChronometer2 = this.chronometerHintView;
            if (hyperChronometer2 != null) {
                hyperChronometer2.setVisibility(8);
            }
            TextView textView2 = this.focusSmallTitleView;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.focusSmallTitleView;
            if (textView3 != null) {
                textView3.setText(Html.fromHtml(getTitle()));
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                TextView textView4 = this.focusSmallTitleView;
                if (textView4 != null) {
                    textView4.setTextColor(iIntValue);
                }
            }
            String title = getTitle();
            Integer numValueOf2 = title != null ? Integer.valueOf(title.length()) : null;
            kotlin.jvm.internal.n.d(numValueOf2);
            if (numValueOf2.intValue() <= 6 || (subtitle = getSubtitle()) == null || subtitle.length() != 0) {
                HintInfo hintInfo3 = template.getHintInfo();
                if (hintInfo3 != null) {
                    hintInfo3.setTitleLineCount(1);
                }
                TextView textView5 = this.focusSmallTitleView;
                if (textView5 != null) {
                    textView5.setMaxLines(1);
                }
            } else {
                HintInfo hintInfo4 = template.getHintInfo();
                if (hintInfo4 != null) {
                    hintInfo4.setTitleLineCount(2);
                }
                TextView textView6 = this.focusSmallTitleView;
                if (textView6 != null) {
                    textView6.setMaxLines(2);
                }
            }
        }
        if (!TextUtils.isEmpty(getSubtitle())) {
            TextView textView7 = this.focusSmallSubtitleView;
            if (textView7 != null) {
                textView7.setVisibility(0);
            }
            TextView textView8 = this.focusSmallSubtitleView;
            if (textView8 != null) {
                textView8.setText(Html.fromHtml(getSubtitle()));
            }
            HintInfo hintInfo5 = template.getHintInfo();
            if (hintInfo5 != null) {
                hintInfo5.setTitleLineCount(2);
            }
            TextView textView9 = this.focusSmallSubtitleView;
            if (textView9 != null) {
                textView9.setMaxLines(1);
            }
            Integer subTitleColor = getSubTitleColor();
            if (subTitleColor != null) {
                int iIntValue2 = subTitleColor.intValue();
                TextView textView10 = this.focusSmallSubtitleView;
                if (textView10 != null) {
                    textView10.setTextColor(iIntValue2);
                }
            }
        }
        HintInfo hintInfo6 = template.getHintInfo();
        if ((hintInfo6 != null ? hintInfo6.getTimerInfo() : null) != null) {
            setTimerData(R.id.chronometer_hint, sbn);
        }
        ModuleViewHolder.setActionData$default(this, template, sbn, false, 4, null);
        setLineColor(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_button_2, getRootView()));
        View view = getView();
        this.line = view != null ? (FrameLayout) view.findViewById(R.id.line) : null;
        View view2 = getView();
        this.hintLayout = view2 != null ? (LinearLayout) view2.findViewById(R.id.layout_hint) : null;
        View view3 = getView();
        this.titleHintLayout = view3 != null ? (FrameLayout) view3.findViewById(R.id.title_hint) : null;
        View view4 = getView();
        this.focusSmallTitleView = view4 != null ? (TextView) view4.findViewById(R.id.focus_small_title) : null;
        View view5 = getView();
        this.chronometerHintView = view5 != null ? (HyperChronometer) view5.findViewById(R.id.chronometer_hint) : null;
        View view6 = getView();
        this.focusSmallSubtitleView = view6 != null ? (TextView) view6.findViewById(R.id.focus_small_subtitle) : null;
        View view7 = getView();
        this.focusTextTitleView = view7 != null ? (TextView) view7.findViewById(R.id.focus_text_title) : null;
        View view8 = getView();
        this.focusButtonContainer = view8 != null ? (FrameLayout) view8.findViewById(R.id.focus_button_container) : null;
        View view9 = getView();
        this.focusButtonTitleView = view9 != null ? (TextView) view9.findViewById(R.id.focus_button_title) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
