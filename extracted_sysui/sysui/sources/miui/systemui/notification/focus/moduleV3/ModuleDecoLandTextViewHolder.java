package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.content.res.ColorStateList;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoLandTextViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ModuleDecoLandTextViewHolder";
    private Integer colorSpecialBg;
    private TextView focusContentView;
    private TextView focusTitleView;
    private boolean showContentDivider;
    private boolean showDivider;
    private TextView specialTitleView;
    private TextView subContentView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoLandTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
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

    private final void setContentTxt() {
        String strValueOf;
        String strValueOf2;
        TextView textView = this.focusContentView;
        if (textView != null) {
            textView.setVisibility(0);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) getContent());
        if (getContentColor() != null) {
            Integer contentColor = getContentColor();
            kotlin.jvm.internal.n.d(contentColor);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(contentColor.intValue());
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) String.valueOf(getContent()));
            spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 17);
        } else {
            spannableStringBuilder.append((CharSequence) String.valueOf(getContent()));
        }
        CharSequence spannedString = new SpannedString(spannableStringBuilder);
        if (spannedString.length() > 0) {
            TextView textView2 = this.focusContentView;
            if (textView2 != null) {
                if (kotlin.jvm.internal.n.c(containsHtml(spannedString.toString()), Boolean.TRUE)) {
                    spannedString = Html.fromHtml(spannedString.toString());
                }
                textView2.setText(spannedString);
            }
        } else {
            TextView textView3 = this.focusContentView;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            TextView textView4 = this.focusTitleView;
            if (textView4 != null) {
                textView4.setMaxLines(2);
            }
        }
        String subContent = getSubContent();
        if (subContent == null || subContent.length() <= 0) {
            return;
        }
        TextView textView5 = this.subContentView;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
        if (getSubContentColor() != null) {
            Integer subContentColor = getSubContentColor();
            kotlin.jvm.internal.n.d(subContentColor);
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(subContentColor.intValue());
            int length2 = spannableStringBuilder2.length();
            if (this.showContentDivider) {
                strValueOf2 = " | " + getSubContent();
            } else {
                strValueOf2 = String.valueOf(getSubContent());
            }
            spannableStringBuilder2.append((CharSequence) strValueOf2);
            spannableStringBuilder2.setSpan(foregroundColorSpan2, length2, spannableStringBuilder2.length(), 17);
        } else {
            if (this.showContentDivider) {
                strValueOf = " | " + getSubContent();
            } else {
                strValueOf = String.valueOf(getSubContent());
            }
            spannableStringBuilder2.append((CharSequence) strValueOf);
        }
        CharSequence spannedString2 = new SpannedString(spannableStringBuilder2);
        TextView textView6 = this.subContentView;
        if (textView6 == null) {
            return;
        }
        if (kotlin.jvm.internal.n.c(containsHtml(spannedString2.toString()), Boolean.TRUE)) {
            spannedString2 = Html.fromHtml(spannedString2.toString());
        }
        textView6.setText(spannedString2);
    }

    private final void setTextContainerBackground(Template template) {
        Integer numValueOf = null;
        try {
            BaseInfo baseInfo = template.getBaseInfo();
            numValueOf = Integer.valueOf(Color.parseColor(baseInfo != null ? baseInfo.getColorSpecialBg() : null));
        } catch (Exception unused) {
        }
        if (numValueOf != null) {
            TextView textView = this.specialTitleView;
            if (textView != null) {
                textView.setBackgroundResource(R.drawable.focus_text_background_no_alpha);
            }
            TextView textView2 = this.specialTitleView;
            if (textView2 == null) {
                return;
            }
            textView2.setBackgroundTintList(ColorStateList.valueOf(numValueOf.intValue()));
        }
    }

    private final void setTitleTxt() {
        Boolean boolValueOf;
        String strValueOf;
        String strValueOf2;
        String strValueOf3;
        TextView textView = this.focusTitleView;
        if (textView != null) {
            textView.setVisibility(0);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getTitleColor() != null) {
            Integer titleColor = getTitleColor();
            kotlin.jvm.internal.n.d(titleColor);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(titleColor.intValue());
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) String.valueOf(getTitle()));
            spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 17);
        } else {
            spannableStringBuilder.append((CharSequence) String.valueOf(getTitle()));
        }
        String subtitle = getSubtitle();
        if (subtitle != null) {
            boolValueOf = Boolean.valueOf(subtitle.length() > 0);
        } else {
            boolValueOf = null;
        }
        kotlin.jvm.internal.n.d(boolValueOf);
        if (boolValueOf.booleanValue()) {
            if (getSubTitleColor() != null) {
                Integer subTitleColor = getSubTitleColor();
                kotlin.jvm.internal.n.d(subTitleColor);
                ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(subTitleColor.intValue());
                int length2 = spannableStringBuilder.length();
                if (this.showDivider) {
                    strValueOf3 = " | " + getSubtitle();
                } else {
                    strValueOf3 = String.valueOf(getSubtitle());
                }
                spannableStringBuilder.append((CharSequence) strValueOf3);
                spannableStringBuilder.setSpan(foregroundColorSpan2, length2, spannableStringBuilder.length(), 17);
            } else {
                if (this.showDivider) {
                    strValueOf2 = " | " + getSubtitle();
                } else {
                    strValueOf2 = String.valueOf(getSubtitle());
                }
                spannableStringBuilder.append((CharSequence) strValueOf2);
            }
        }
        String extraTitle = getExtraTitle();
        if (extraTitle != null && extraTitle.length() > 0 && getExtraTitleColor() != null) {
            Integer extraTitleColor = getExtraTitleColor();
            kotlin.jvm.internal.n.d(extraTitleColor);
            ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(extraTitleColor.intValue());
            int length3 = spannableStringBuilder.length();
            if (this.showDivider) {
                strValueOf = " | " + getExtraTitle();
            } else {
                strValueOf = String.valueOf(getExtraTitle());
            }
            spannableStringBuilder.append((CharSequence) strValueOf);
            spannableStringBuilder.setSpan(foregroundColorSpan3, length3, spannableStringBuilder.length(), 17);
        }
        CharSequence spannedString = new SpannedString(spannableStringBuilder);
        TextView textView2 = this.focusTitleView;
        if (textView2 != null) {
            if (kotlin.jvm.internal.n.c(containsHtml(spannedString.toString()), Boolean.TRUE)) {
                spannedString = Html.fromHtml(spannedString.toString());
            }
            textView2.setText(spannedString);
        }
        if (TextUtils.isEmpty(getSpecialTitle())) {
            TextView textView3 = this.specialTitleView;
            if (textView3 == null) {
                return;
            }
            textView3.setVisibility(4);
            return;
        }
        TextView textView4 = this.specialTitleView;
        if (textView4 != null) {
            textView4.setVisibility(0);
        }
        TextView textView5 = this.specialTitleView;
        if (textView5 != null) {
            textView5.setText(Html.fromHtml(getSpecialTitle()));
        }
        Integer specialTitleColor = getSpecialTitleColor();
        if (specialTitleColor != null) {
            int iIntValue = specialTitleColor.intValue();
            TextView textView6 = this.specialTitleView;
            if (textView6 != null) {
                textView6.setTextColor(iIntValue);
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getBaseInfo());
        BaseInfo baseInfo = template.getBaseInfo();
        this.showDivider = baseInfo != null ? kotlin.jvm.internal.n.c(baseInfo.getShowDivider(), Boolean.TRUE) : false;
        BaseInfo baseInfo2 = template.getBaseInfo();
        this.showContentDivider = baseInfo2 != null ? kotlin.jvm.internal.n.c(baseInfo2.getShowContentDivider(), Boolean.TRUE) : false;
        Integer numValueOf = null;
        try {
            BaseInfo baseInfo3 = template.getBaseInfo();
            numValueOf = Integer.valueOf(Color.parseColor(baseInfo3 != null ? baseInfo3.getColorSpecialBg() : null));
        } catch (Exception unused) {
        }
        this.colorSpecialBg = numValueOf;
        setTitleTxt();
        setContentTxt();
        setTextContainerBackground(template);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(kotlin.jvm.internal.n.c(module, Const.Module.MODULE_TEXT_1) ? LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_land_text_1, getRootView()) : LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_land_text_2, getRootView()));
        View view = getView();
        this.focusContentView = view != null ? (TextView) view.findViewById(R.id.focus_content) : null;
        View view2 = getView();
        this.subContentView = view2 != null ? (TextView) view2.findViewById(R.id.focus_sub_content) : null;
        View view3 = getView();
        this.focusTitleView = view3 != null ? (TextView) view3.findViewById(R.id.focus_title) : null;
        View view4 = getView();
        this.specialTitleView = view4 != null ? (TextView) view4.findViewById(R.id.focus_special_title) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getBaseInfo());
        bind(template, sbn);
    }
}
