package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoPortTextViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ModuleDecoPortTextViewHolder";
    private Integer colorSpecialBg;
    private int extra;
    private LinearLayout focusContainerModuleText1;
    private LinearLayout focusContainerModuleText2;
    private TextView focusContentView;
    private TextView focusSpecialTitleView;
    private TextView focusSubContentView;
    private TextView focusTitleView;
    private boolean showContentDivider;
    private boolean showDivider;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.extra = DynamicIslandUtils.INSTANCE.dpToPx(2, ctx);
    }

    private final Boolean containsHtml(String str) {
        if (str == null) {
            return null;
        }
        return Boolean.valueOf(new f1.e(".*<(\"[^\"]*\"|'[^']*'|[^'\">])*>.*").a(str));
    }

    private final void setTextContainerBackground(Template template) {
        Integer numValueOf = null;
        try {
            BaseInfo baseInfo = template.getBaseInfo();
            numValueOf = Integer.valueOf(Color.parseColor(baseInfo != null ? baseInfo.getColorSpecialBg() : null));
        } catch (Exception unused) {
        }
        if (numValueOf != null) {
            TextView textView = this.focusSpecialTitleView;
            if (textView != null) {
                textView.setBackgroundResource(R.drawable.focus_text_background_no_alpha);
            }
            TextView textView2 = this.focusSpecialTitleView;
            if (textView2 == null) {
                return;
            }
            textView2.setBackgroundTintList(ColorStateList.valueOf(numValueOf.intValue()));
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        Integer numValueOf;
        String strValueOf;
        String strValueOf2;
        String strValueOf3;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getBaseInfo());
        BaseInfo baseInfo = template.getBaseInfo();
        this.showDivider = baseInfo != null ? kotlin.jvm.internal.n.c(baseInfo.getShowDivider(), Boolean.TRUE) : false;
        BaseInfo baseInfo2 = template.getBaseInfo();
        this.showContentDivider = baseInfo2 != null ? kotlin.jvm.internal.n.c(baseInfo2.getShowContentDivider(), Boolean.TRUE) : false;
        Boolean boolValueOf = null;
        try {
            BaseInfo baseInfo3 = template.getBaseInfo();
            numValueOf = Integer.valueOf(Color.parseColor(baseInfo3 != null ? baseInfo3.getColorSpecialBg() : null));
        } catch (Exception unused) {
            numValueOf = null;
        }
        this.colorSpecialBg = numValueOf;
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
        SpannedString spannedString = new SpannedString(spannableStringBuilder);
        int length4 = spannedString.length();
        TextView textView2 = this.focusTitleView;
        if (textView2 != null) {
            boolean zC = kotlin.jvm.internal.n.c(containsHtml(spannedString.toString()), Boolean.TRUE);
            Spanned spannedFromHtml = spannedString;
            if (zC) {
                spannedFromHtml = Html.fromHtml(spannedString.toString());
            }
            textView2.setText(spannedFromHtml);
        }
        if (TextUtils.isEmpty(getSpecialTitle())) {
            TextView textView3 = this.focusSpecialTitleView;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        } else {
            TextView textView4 = this.focusSpecialTitleView;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
            TextView textView5 = this.focusSpecialTitleView;
            if (textView5 != null) {
                textView5.setText(Html.fromHtml(getSpecialTitle()));
            }
            Integer specialTitleColor = getSpecialTitleColor();
            if (specialTitleColor != null) {
                int iIntValue = specialTitleColor.intValue();
                TextView textView6 = this.focusSpecialTitleView;
                if (textView6 != null) {
                    textView6.setTextColor(iIntValue);
                }
            }
        }
        if (!TextUtils.isEmpty(getContent())) {
            TextView textView7 = this.focusContentView;
            if (textView7 != null) {
                textView7.setVisibility(0);
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                TextView textView8 = this.focusContentView;
                if (textView8 != null) {
                    textView8.setTextColor(iIntValue2);
                }
            }
            TextView textView9 = this.focusContentView;
            if (textView9 != null) {
                textView9.setText(Html.fromHtml(getContent()));
            }
            if (!TextUtils.isEmpty(getSubContent())) {
                TextView textView10 = this.focusSubContentView;
                if (textView10 != null) {
                    textView10.setVisibility(0);
                }
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
                if (getSubContentColor() != null) {
                    Integer subContentColor = getSubContentColor();
                    kotlin.jvm.internal.n.d(subContentColor);
                    ForegroundColorSpan foregroundColorSpan4 = new ForegroundColorSpan(subContentColor.intValue());
                    int length5 = spannableStringBuilder2.length();
                    spannableStringBuilder2.append((CharSequence) String.valueOf(getSubContent()));
                    spannableStringBuilder2.setSpan(foregroundColorSpan4, length5, spannableStringBuilder2.length(), 17);
                } else {
                    spannableStringBuilder2.append((CharSequence) String.valueOf(getSubContent()));
                }
                CharSequence spannedString2 = new SpannedString(spannableStringBuilder2);
                TextView textView11 = this.focusSubContentView;
                if (textView11 != null) {
                    if (kotlin.jvm.internal.n.c(containsHtml(spannedString2.toString()), Boolean.TRUE)) {
                        spannedString2 = Html.fromHtml(spannedString2.toString());
                    }
                    textView11.setText(spannedString2);
                }
            }
        }
        String specialTitle = getSpecialTitle();
        int i2 = 3;
        if (specialTitle == null || specialTitle.length() <= 0) {
            String subContent = getSubContent();
            if (subContent == null || subContent.length() <= 0) {
                i2 = 4;
            } else {
                TextView textView12 = this.focusSubContentView;
                if (textView12 != null) {
                    textView12.setMaxLines(1);
                }
            }
            if (1 <= length4 && length4 < 6) {
                TextView textView13 = this.focusTitleView;
                if (textView13 != null) {
                    textView13.setMaxLines(1);
                }
                HintInfo hintInfo = template.getHintInfo();
                if (hintInfo == null || hintInfo.getTitleLineCount() != 2) {
                    TextView textView14 = this.focusContentView;
                    if (textView14 != null) {
                        textView14.setMaxLines(i2 - 1);
                    }
                } else {
                    TextView textView15 = this.focusContentView;
                    if (textView15 != null) {
                        textView15.setMaxLines(i2 - 2);
                    }
                }
            } else if (5 > length4 || length4 >= 15) {
                HintInfo hintInfo2 = template.getHintInfo();
                if (hintInfo2 == null || hintInfo2.getTitleLineCount() != 2) {
                    TextView textView16 = this.focusTitleView;
                    if (textView16 != null) {
                        textView16.setMaxLines(i2 - 1);
                    }
                } else {
                    TextView textView17 = this.focusTitleView;
                    if (textView17 != null) {
                        textView17.setMaxLines(i2 - 2);
                    }
                }
                TextView textView18 = this.focusContentView;
                if (textView18 != null) {
                    textView18.setMaxLines(1);
                }
            } else {
                HintInfo hintInfo3 = template.getHintInfo();
                if (hintInfo3 == null || hintInfo3.getTitleLineCount() != 2) {
                    TextView textView19 = this.focusTitleView;
                    if (textView19 != null) {
                        textView19.setMaxLines(2);
                    }
                    TextView textView20 = this.focusContentView;
                    if (textView20 != null) {
                        textView20.setMaxLines(i2 - 2);
                    }
                } else {
                    TextView textView21 = this.focusContentView;
                    if (textView21 != null) {
                        textView21.setMaxLines(1);
                    }
                    TextView textView22 = this.focusTitleView;
                    if (textView22 != null) {
                        textView22.setMaxLines(i2 - 2);
                    }
                }
            }
        } else if (length4 > 14) {
            TextView textView23 = this.focusTitleView;
            if (textView23 != null) {
                textView23.setMaxLines(3);
            }
            TextView textView24 = this.focusContentView;
            if (textView24 != null) {
                textView24.setMaxLines(1);
            }
            TextView textView25 = this.focusSubContentView;
            if (textView25 != null) {
                textView25.setVisibility(8);
            }
        } else {
            TextView textView26 = this.focusSubContentView;
            if (textView26 != null) {
                textView26.setVisibility(0);
            }
            TextView textView27 = this.focusTitleView;
            if (textView27 != null) {
                textView27.setMaxLines(2);
            }
            TextView textView28 = this.focusContentView;
            if (textView28 != null) {
                textView28.setMaxLines(1);
            }
            TextView textView29 = this.focusSubContentView;
            if (textView29 != null) {
                textView29.setMaxLines(1);
            }
        }
        setTextContainerBackground(template);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(kotlin.jvm.internal.n.c(module, Const.Module.MODULE_TEXT_1) ? LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_text_1, getRootView()) : LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_text_2, getRootView()));
        View view = getView();
        this.focusContainerModuleText1 = view != null ? (LinearLayout) view.findViewById(R.id.focus_container_module_text_1) : null;
        View view2 = getView();
        this.focusContainerModuleText2 = view2 != null ? (LinearLayout) view2.findViewById(R.id.focus_container_module_text_2) : null;
        View view3 = getView();
        this.focusContentView = view3 != null ? (TextView) view3.findViewById(R.id.focus_content) : null;
        View view4 = getView();
        this.focusSubContentView = view4 != null ? (TextView) view4.findViewById(R.id.focus_sub_content) : null;
        View view5 = getView();
        this.focusSpecialTitleView = view5 != null ? (TextView) view5.findViewById(R.id.focus_special_title) : null;
        View view6 = getView();
        this.focusTitleView = view6 != null ? (TextView) view6.findViewById(R.id.focus_title) : null;
        if (kotlin.jvm.internal.n.c(module, Const.Module.MODULE_TEXT_1)) {
            LinearLayout linearLayout = this.focusContainerModuleText1;
            if (linearLayout == null) {
                return;
            }
            linearLayout.setVisibility(0);
            return;
        }
        LinearLayout linearLayout2 = this.focusContainerModuleText2;
        if (linearLayout2 == null) {
            return;
        }
        linearLayout2.setVisibility(0);
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
