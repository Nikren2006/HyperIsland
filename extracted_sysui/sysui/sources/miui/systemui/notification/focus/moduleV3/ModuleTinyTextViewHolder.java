package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyTextViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ModuleTinyTextViewHolder";
    private LinearLayout contentContainer;
    private int extra;
    private TextView extraTitleDivider;
    private TextView extraTitleView;
    private TextView focusContent;
    private TextView focusTitle;
    private TextView functionIconDivider;
    private ImageView functionIconView;
    private TextView specialTitleView;
    private TextView subContentDivider;
    private TextView subContentView;
    private TextView subTitleDivider;
    private TextView subTitleView;
    private LinearLayout titleContainer;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.extra = DynamicIslandUtils.INSTANCE.dpToPx(2, ctx);
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

    private final void showFocusIcon(Template template, StatusBarNotification statusBarNotification) {
        BaseInfo baseInfo = template.getBaseInfo();
        Icon icon = getIcon(baseInfo != null ? baseInfo.getPicFunction() : null, statusBarNotification);
        BaseInfo baseInfo2 = template.getBaseInfo();
        boolean zC = baseInfo2 != null ? kotlin.jvm.internal.n.c(baseInfo2.getShowContentDivider(), Boolean.TRUE) : false;
        if (icon == null) {
            ImageView imageView = this.functionIconView;
            if (imageView != null) {
                imageView.setVisibility(4);
            }
            TextView textView = this.functionIconDivider;
            if (textView == null) {
                return;
            }
            textView.setVisibility(8);
            return;
        }
        ImageView imageView2 = this.functionIconView;
        if (imageView2 != null) {
            imageView2.setImageIcon(icon);
        }
        ImageView imageView3 = this.functionIconView;
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        if (!zC) {
            TextView textView2 = this.functionIconDivider;
            if (textView2 == null) {
                return;
            }
            textView2.setVisibility(8);
            return;
        }
        TextView textView3 = this.functionIconDivider;
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        Integer subContentColor = getSubContentColor();
        if (subContentColor != null) {
            int iIntValue = subContentColor.intValue();
            TextView textView4 = this.functionIconDivider;
            if (textView4 != null) {
                textView4.setTextColor(iIntValue);
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
        boolean zC = baseInfo != null ? kotlin.jvm.internal.n.c(baseInfo.getShowDivider(), Boolean.TRUE) : false;
        BaseInfo baseInfo2 = template.getBaseInfo();
        boolean zC2 = baseInfo2 != null ? kotlin.jvm.internal.n.c(baseInfo2.getShowContentDivider(), Boolean.TRUE) : false;
        TextView textView = this.focusTitle;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.focusTitle;
        if (textView2 != null) {
            textView2.setText(Html.fromHtml(getTitle()));
        }
        Integer titleColor = getTitleColor();
        if (titleColor != null) {
            int iIntValue = titleColor.intValue();
            TextView textView3 = this.focusTitle;
            if (textView3 != null) {
                textView3.setTextColor(iIntValue);
            }
        }
        String content = getContent();
        if (content != null) {
            if (TextUtils.isEmpty(content)) {
                LinearLayout linearLayout = this.contentContainer;
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
                LinearLayout linearLayout2 = this.titleContainer;
                ViewGroup.LayoutParams layoutParams = linearLayout2 != null ? linearLayout2.getLayoutParams() : null;
                kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                BaseInfo baseInfo3 = template.getBaseInfo();
                if (baseInfo3 != null ? kotlin.jvm.internal.n.c(baseInfo3.getSetMarginTop(), Boolean.TRUE) : false) {
                    layoutParams2.topMargin = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_tiny_button_without_icon_margin_start);
                }
                BaseInfo baseInfo4 = template.getBaseInfo();
                if (baseInfo4 != null ? kotlin.jvm.internal.n.c(baseInfo4.getSetMarginBottom(), Boolean.TRUE) : false) {
                    layoutParams2.bottomMargin = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_tiny_button_without_icon_margin_start);
                } else {
                    layoutParams2.bottomMargin = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_tiny_button_margin_start);
                }
                LinearLayout linearLayout3 = this.titleContainer;
                if (linearLayout3 != null) {
                    linearLayout3.setLayoutParams(layoutParams2);
                }
            } else {
                LinearLayout linearLayout4 = this.contentContainer;
                if (linearLayout4 != null) {
                    linearLayout4.setVisibility(0);
                }
                TextView textView4 = this.focusContent;
                if (textView4 != null) {
                    textView4.setVisibility(0);
                }
                TextView textView5 = this.focusContent;
                if (textView5 != null) {
                    textView5.setText(Html.fromHtml(content));
                }
                Integer contentColor = getContentColor();
                if (contentColor != null) {
                    int iIntValue2 = contentColor.intValue();
                    TextView textView6 = this.focusContent;
                    if (textView6 != null) {
                        textView6.setTextColor(iIntValue2);
                    }
                }
            }
        }
        String subtitle = getSubtitle();
        if (subtitle != null) {
            if (TextUtils.isEmpty(subtitle)) {
                TextView textView7 = this.subTitleView;
                if (textView7 != null) {
                    textView7.setVisibility(8);
                }
                TextView textView8 = this.subTitleDivider;
                if (textView8 != null) {
                    textView8.setVisibility(8);
                }
            } else {
                TextView textView9 = this.subTitleView;
                if (textView9 != null) {
                    textView9.setVisibility(0);
                }
                TextView textView10 = this.subTitleView;
                if (textView10 != null) {
                    textView10.setText(Html.fromHtml(subtitle));
                }
                Integer subTitleColor = getSubTitleColor();
                if (subTitleColor != null) {
                    int iIntValue3 = subTitleColor.intValue();
                    TextView textView11 = this.subTitleView;
                    if (textView11 != null) {
                        textView11.setTextColor(iIntValue3);
                    }
                }
                if (zC) {
                    TextView textView12 = this.subTitleDivider;
                    if (textView12 != null) {
                        textView12.setVisibility(0);
                    }
                    Integer subTitleColor2 = getSubTitleColor();
                    if (subTitleColor2 != null) {
                        int iIntValue4 = subTitleColor2.intValue();
                        TextView textView13 = this.subTitleDivider;
                        if (textView13 != null) {
                            textView13.setTextColor(iIntValue4);
                        }
                    }
                } else {
                    TextView textView14 = this.subTitleDivider;
                    if (textView14 != null) {
                        textView14.setVisibility(8);
                    }
                }
            }
        }
        String subContent = getSubContent();
        if (subContent != null) {
            if (TextUtils.isEmpty(subContent)) {
                TextView textView15 = this.subContentView;
                if (textView15 != null) {
                    textView15.setVisibility(8);
                }
                TextView textView16 = this.subContentDivider;
                if (textView16 != null) {
                    textView16.setVisibility(8);
                }
            } else {
                TextView textView17 = this.subContentView;
                if (textView17 != null) {
                    textView17.setVisibility(0);
                }
                TextView textView18 = this.subContentView;
                if (textView18 != null) {
                    textView18.setText(Html.fromHtml(subContent));
                }
                Integer subContentColor = getSubContentColor();
                if (subContentColor != null) {
                    int iIntValue5 = subContentColor.intValue();
                    TextView textView19 = this.subContentView;
                    if (textView19 != null) {
                        textView19.setTextColor(iIntValue5);
                    }
                }
                if (zC2) {
                    TextView textView20 = this.subContentDivider;
                    if (textView20 != null) {
                        textView20.setVisibility(0);
                    }
                    Integer subContentColor2 = getSubContentColor();
                    if (subContentColor2 != null) {
                        int iIntValue6 = subContentColor2.intValue();
                        TextView textView21 = this.subContentDivider;
                        if (textView21 != null) {
                            textView21.setTextColor(iIntValue6);
                        }
                    }
                } else {
                    TextView textView22 = this.subContentDivider;
                    if (textView22 != null) {
                        textView22.setVisibility(8);
                    }
                }
            }
        }
        String extraTitle = getExtraTitle();
        if (extraTitle != null) {
            if (TextUtils.isEmpty(extraTitle)) {
                TextView textView23 = this.extraTitleView;
                if (textView23 != null) {
                    textView23.setVisibility(8);
                }
                TextView textView24 = this.extraTitleDivider;
                if (textView24 != null) {
                    textView24.setVisibility(8);
                }
            } else {
                TextView textView25 = this.extraTitleView;
                if (textView25 != null) {
                    textView25.setVisibility(0);
                }
                TextView textView26 = this.extraTitleView;
                if (textView26 != null) {
                    textView26.setText(Html.fromHtml(extraTitle));
                }
                Integer extraTitleColor = getExtraTitleColor();
                if (extraTitleColor != null) {
                    int iIntValue7 = extraTitleColor.intValue();
                    TextView textView27 = this.extraTitleView;
                    if (textView27 != null) {
                        textView27.setTextColor(iIntValue7);
                    }
                }
                if (zC) {
                    Integer extraTitleColor2 = getExtraTitleColor();
                    if (extraTitleColor2 != null) {
                        int iIntValue8 = extraTitleColor2.intValue();
                        TextView textView28 = this.extraTitleDivider;
                        if (textView28 != null) {
                            textView28.setTextColor(iIntValue8);
                        }
                    }
                    TextView textView29 = this.extraTitleDivider;
                    if (textView29 != null) {
                        textView29.setVisibility(0);
                    }
                } else {
                    TextView textView30 = this.extraTitleDivider;
                    if (textView30 != null) {
                        textView30.setVisibility(8);
                    }
                }
            }
        }
        Log.e(ModuleTextViewHolder.TAG, "specialTitle " + getSpecialTitle());
        String specialTitle = getSpecialTitle();
        if (specialTitle != null) {
            if (TextUtils.isEmpty(specialTitle)) {
                Log.e(ModuleTextViewHolder.TAG, "specialTitle2 " + getSpecialTitle());
                TextView textView31 = this.specialTitleView;
                if (textView31 != null) {
                    textView31.setVisibility(8);
                }
            } else {
                Log.e(ModuleTextViewHolder.TAG, "specialTitle1 " + getSpecialTitle());
                TextView textView32 = this.specialTitleView;
                if (textView32 != null) {
                    textView32.setVisibility(0);
                }
                TextView textView33 = this.specialTitleView;
                if (textView33 != null) {
                    textView33.setText(Html.fromHtml(specialTitle));
                }
                Integer specialTitleColor = getSpecialTitleColor();
                if (specialTitleColor != null) {
                    int iIntValue9 = specialTitleColor.intValue();
                    TextView textView34 = this.specialTitleView;
                    if (textView34 != null) {
                        textView34.setTextColor(iIntValue9);
                    }
                }
            }
        }
        String specialTitle2 = getSpecialTitle();
        Integer numValueOf = specialTitle2 != null ? Integer.valueOf(specialTitle2.length()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        if (numValueOf.intValue() > 0) {
            TextView textView35 = this.focusTitle;
            if (textView35 != null) {
                textView35.setMaxEms(6);
            }
        } else {
            TextView textView36 = this.focusTitle;
            if (textView36 != null) {
                textView36.setMaxEms(10);
            }
        }
        String subContent2 = getSubContent();
        Integer numValueOf2 = subContent2 != null ? Integer.valueOf(subContent2.length()) : null;
        kotlin.jvm.internal.n.d(numValueOf2);
        if (numValueOf2.intValue() > 3) {
            TextView textView37 = this.focusContent;
            if (textView37 != null) {
                textView37.setMaxEms(6);
            }
        } else {
            TextView textView38 = this.focusContent;
            if (textView38 != null) {
                textView38.setMaxEms(10);
            }
        }
        showFocusIcon(template, sbn);
        setTextContainerBackground(template);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        if (kotlin.jvm.internal.n.c(module, Const.Module.MODULE_TEXT_1)) {
            setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_text_1, getRootView()));
        } else {
            setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_text_2, getRootView()));
        }
        View view = getView();
        this.focusTitle = view != null ? (TextView) view.findViewById(R.id.focus_title) : null;
        View view2 = getView();
        this.contentContainer = view2 != null ? (LinearLayout) view2.findViewById(R.id.tiny_content_container) : null;
        View view3 = getView();
        this.focusContent = view3 != null ? (TextView) view3.findViewById(R.id.focus_content) : null;
        View view4 = getView();
        this.titleContainer = view4 != null ? (LinearLayout) view4.findViewById(R.id.tiny_title_container) : null;
        View view5 = getView();
        this.subTitleView = view5 != null ? (TextView) view5.findViewById(R.id.focus_subtitle) : null;
        View view6 = getView();
        this.subTitleDivider = view6 != null ? (TextView) view6.findViewById(R.id.focus_subtitle_divider) : null;
        View view7 = getView();
        this.subContentView = view7 != null ? (TextView) view7.findViewById(R.id.focus_sub_content) : null;
        View view8 = getView();
        this.subContentDivider = view8 != null ? (TextView) view8.findViewById(R.id.focus_sub_content_divider) : null;
        View view9 = getView();
        this.extraTitleView = view9 != null ? (TextView) view9.findViewById(R.id.focus_extra_title) : null;
        View view10 = getView();
        this.extraTitleDivider = view10 != null ? (TextView) view10.findViewById(R.id.focus_extra_title_divider) : null;
        View view11 = getView();
        this.specialTitleView = view11 != null ? (TextView) view11.findViewById(R.id.focus_special_title) : null;
        View view12 = getView();
        this.functionIconView = view12 != null ? (ImageView) view12.findViewById(R.id.focus_function_icon) : null;
        View view13 = getView();
        this.functionIconDivider = view13 != null ? (TextView) view13.findViewById(R.id.focus_function_icon_divider) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        super.onDetach();
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
