package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.Color;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.net.URISyntaxException;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.Template;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoLandTextButtonViewHolder extends ModuleViewHolder {
    private HyperChronometer chronometerHint;
    private TextView focusSmallTitle;
    private boolean showContentDivider;
    private TextView smallSubTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoLandTextButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final Boolean containsHtml(String str) {
        if (str == null) {
            return null;
        }
        return Boolean.valueOf(new f1.e(".*<(\"[^\"]*\"|'[^']*'|[^'\">])*>.*").a(str));
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        Integer numValueOf;
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
        BaseInfo baseInfo = template.getBaseInfo();
        this.showContentDivider = baseInfo != null ? kotlin.jvm.internal.n.c(baseInfo.getShowContentDivider(), Boolean.TRUE) : false;
        HintInfo hintInfo2 = template.getHintInfo();
        initTimerData(hintInfo2 != null ? hintInfo2.getTimerInfo() : null);
        if (TextUtils.isEmpty(getTitle())) {
            HyperChronometer hyperChronometer = this.chronometerHint;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(0);
            }
            TextView textView = this.focusSmallTitle;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            HyperChronometer hyperChronometer2 = this.chronometerHint;
            if (hyperChronometer2 != null) {
                hyperChronometer2.setVisibility(8);
            }
            TextView textView2 = this.focusSmallTitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (getTitleColor() != null) {
                Integer titleColor = getTitleColor();
                kotlin.jvm.internal.n.d(titleColor);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(titleColor.intValue());
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append((CharSequence) getTitle());
                spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 17);
            } else {
                spannableStringBuilder.append((CharSequence) getTitle());
            }
            CharSequence spannedString = new SpannedString(spannableStringBuilder);
            TextView textView3 = this.focusSmallTitle;
            if (textView3 != null) {
                if (kotlin.jvm.internal.n.c(containsHtml(spannedString.toString()), Boolean.TRUE)) {
                    spannedString = Html.fromHtml(spannedString.toString());
                }
                textView3.setText(spannedString);
            }
        }
        HintInfo hintInfo3 = template.getHintInfo();
        if ((hintInfo3 != null ? hintInfo3.getTimerInfo() : null) != null) {
            setTimerData(R.id.chronometer_hint, sbn);
        }
        ModuleViewHolder.setActionData$default(this, template, sbn, false, 4, null);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_land_button_2, getRootView()));
        View view = getView();
        this.focusSmallTitle = view != null ? (TextView) view.findViewById(R.id.focus_small_title) : null;
        View view2 = getView();
        this.chronometerHint = view2 != null ? (HyperChronometer) view2.findViewById(R.id.chronometer_hint) : null;
        View view3 = getView();
        this.smallSubTitle = view3 != null ? (TextView) view3.findViewById(R.id.focus_small_subtitle) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
