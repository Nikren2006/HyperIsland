package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.net.URISyntaxException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.HintInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTextButtonViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ModuleTextButtonViewHolder";
    private Chronometer chronometerHint;
    private FrameLayout focusButtonContainer;
    private TextView focusSmallTitle;
    private TextView frontContent;
    private TextView frontSubContent;
    private ImageView functionIconView;
    private final boolean island;
    private LinearLayout layout;
    private TextView smallSubTitle;
    private TextView specialTitleView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleTextButtonViewHolder$initView$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ String $module;
        final /* synthetic */ ModuleTextButtonViewHolder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, ModuleTextButtonViewHolder moduleTextButtonViewHolder) {
            super(0);
            this.$module = str;
            this.this$0 = moduleTextButtonViewHolder;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m145invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m145invoke() {
            String str = this.$module;
            if (kotlin.jvm.internal.n.c(str, Const.Module.MODULE_BUTTON_2)) {
                this.this$0.adaptModuleButton2Views();
            } else if (kotlin.jvm.internal.n.c(str, Const.Module.MODULE_BUTTON_3)) {
                this.this$0.adaptModuleButton3Views();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTextButtonViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adaptModuleButton2Views() {
        Log.d(TAG, "adaptModuleButton2Views");
        checkAndCompressModuleButton2Views();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adaptModuleButton3Views() {
        Log.d(TAG, "adaptModuleButton3Views");
        checkAndCompressModuleButton3Views();
    }

    private final void checkAndCompressModuleButton2Views() {
        int i2;
        int i3;
        int i4;
        int iMax;
        LinearLayout linearLayout = this.layout;
        Integer numValueOf = linearLayout != null ? Integer.valueOf(linearLayout.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue = numValueOf.intValue();
        View view = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view != null ? view.getContext() : null, 25);
        View view2 = getView();
        int iDpToPx2 = ModuleViewHolderExtKt.dpToPx(view2 != null ? view2.getContext() : null, 80);
        int iDpToPx3 = DynamicIslandUtils.INSTANCE.dpToPx(30, getCtx());
        int textViewWidth = getTextViewWidth(this.frontContent);
        int textViewWidth2 = getTextViewWidth(this.focusSmallTitle);
        int textViewWidth3 = getTextViewWidth(this.frontSubContent);
        int textViewWidth4 = getTextViewWidth(this.smallSubTitle);
        int iMax2 = Math.max(textViewWidth, textViewWidth2);
        int iMax3 = Math.max(textViewWidth3, textViewWidth4);
        int i5 = iMax2 + iMax3;
        if (textViewWidth3 <= 0) {
            iDpToPx3 = 0;
        }
        int i6 = i5 + iDpToPx3;
        Log.d(TAG, "checkAndCompressModuleButton2Views: totalWidth " + i6 + " layoutWidth " + iIntValue);
        if (i6 <= iIntValue) {
            TextView textView = this.frontContent;
            setViewWidth(textView, getTextViewWidth(textView), Math.min(getTextViewWidth(this.frontContent), iDpToPx2));
            TextView textView2 = this.focusSmallTitle;
            setViewWidth(textView2, getTextViewWidth(textView2), Math.min(getTextViewWidth(this.focusSmallTitle), iDpToPx2));
            setViewWidth(this.frontSubContent, textViewWidth3, iDpToPx);
            setViewWidth(this.smallSubTitle, textViewWidth2, iDpToPx2);
            return;
        }
        int i7 = i6 - iIntValue;
        Log.d(ModuleTextViewHolder.TAG, "checkAndCompressContentViews: excessWidth " + i7);
        if (i7 > 0) {
            int i8 = iMax2 > iDpToPx2 ? 1 : 0;
            if (iMax3 > iDpToPx2) {
                i8++;
            } else {
                setViewWidth(this.frontSubContent, textViewWidth3, iDpToPx);
                setViewWidth(this.smallSubTitle, textViewWidth2, iDpToPx2);
            }
            if (i8 == 2) {
                int i9 = i7 / i8;
                i3 = iDpToPx2 - (iMax2 - i9);
                i2 = iDpToPx - (iMax3 - i9);
            } else {
                i2 = 0;
                i3 = 0;
            }
            Log.i(TAG, "ComplexHintTitleViews extra1 " + i3 + " extra2 " + i2);
            if (iMax2 > iDpToPx2) {
                int iMax4 = (iMax2 - (i7 / i8)) - Math.max(i2, 0);
                i4 = iDpToPx2 - iMax4;
                iMax = (int) Math.max(iMax4, iDpToPx2);
                setViewWidth(this.frontContent, iMax, Math.min(textViewWidth, iDpToPx2));
                setViewWidth(this.focusSmallTitle, iMax, iDpToPx2);
            } else {
                i4 = 0;
                iMax = 0;
            }
            if (iMax3 > iDpToPx2) {
                int iMax5 = ((iMax3 - (i7 / i8)) - Math.max(i3, 0)) - Math.max(i4, 0);
                int i10 = iDpToPx2 - iMax5;
                setViewWidth(this.frontSubContent, Math.max(iMax5, iDpToPx2), Math.min(textViewWidth, iDpToPx2));
                setViewWidth(this.smallSubTitle, iMax5, iDpToPx2);
                if (i10 > 0) {
                    int i11 = iMax - i10;
                    setViewWidth(this.frontContent, i11, Math.min(textViewWidth, iDpToPx2));
                    setViewWidth(this.focusSmallTitle, i11, Math.min(textViewWidth2, iDpToPx2));
                }
            }
        }
    }

    private final void checkAndCompressModuleButton3Views() {
        int i2;
        int iIntValue;
        DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
        int iDpToPx = dynamicIslandUtils.dpToPx(70, getCtx());
        int iDpToPx2 = dynamicIslandUtils.dpToPx(8, getCtx());
        TextView textView = this.specialTitleView;
        int iMin = Math.min((textView == null || textView.getVisibility() != 0) ? 0 : getTextViewWidth(this.specialTitleView), iDpToPx);
        setViewWidth(this.specialTitleView, iMin, 0);
        LinearLayout linearLayout = this.layout;
        Integer numValueOf = linearLayout != null ? Integer.valueOf(linearLayout.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue2 = numValueOf.intValue() - iMin;
        if (iMin > 0) {
            int i3 = iDpToPx2 * 3;
            ImageView imageView = this.functionIconView;
            if (imageView == null || imageView.getVisibility() != 0) {
                iIntValue = 0;
            } else {
                ImageView imageView2 = this.functionIconView;
                Integer numValueOf2 = imageView2 != null ? Integer.valueOf(imageView2.getWidth()) : null;
                kotlin.jvm.internal.n.d(numValueOf2);
                iIntValue = numValueOf2.intValue() + iDpToPx2;
            }
            i2 = i3 + iIntValue;
        } else {
            i2 = 0;
        }
        int i4 = iIntValue2 - i2;
        Log.i(TAG, "checkAndCompressModuleButton3Views layoutWidth " + numValueOf + " titleMaxWidth " + i4);
        if (numValueOf.intValue() == 0) {
            return;
        }
        TextView textView2 = this.focusSmallTitle;
        Integer numValueOf3 = textView2 != null ? Integer.valueOf(textView2.getMeasuredWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf3);
        if (numValueOf3.intValue() > i4) {
            setViewWidth(this.focusSmallTitle, i4, 0);
        }
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
        HintInfo hintInfo = template.getHintInfo();
        initTimerData(hintInfo != null ? hintInfo.getTimerInfo() : null);
        if (TextUtils.isEmpty(getTitle())) {
            Chronometer chronometer = this.chronometerHint;
            if (chronometer != null) {
                chronometer.setVisibility(0);
            }
            TextView textView = this.focusSmallTitle;
            if (textView != null) {
                textView.setVisibility(8);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                Chronometer chronometer2 = this.chronometerHint;
                if (chronometer2 != null) {
                    chronometer2.setTextColor(iIntValue);
                }
            }
        } else {
            Chronometer chronometer3 = this.chronometerHint;
            if (chronometer3 != null) {
                chronometer3.setVisibility(8);
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
        HintInfo hintInfo2 = template.getHintInfo();
        if ((hintInfo2 != null ? hintInfo2.getTimerInfo() : null) != null) {
            setTimerData(R.id.chronometer_hint, sbn);
        }
        setActionData(template, sbn, this.island);
        setSpecialTextContainerData(template, sbn);
        setLineColor(template, sbn);
        notifyDataChanged();
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setModule(module);
        if (kotlin.jvm.internal.n.c(module, Const.Module.MODULE_BUTTON_2)) {
            setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_button_2, getRootView()));
        } else if (kotlin.jvm.internal.n.c(module, Const.Module.MODULE_BUTTON_3)) {
            setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_button_3, getRootView()));
        }
        View view = getView();
        this.layout = view != null ? (LinearLayout) view.findViewById(R.id.layout_hint) : null;
        View view2 = getView();
        this.chronometerHint = view2 != null ? (Chronometer) view2.findViewById(R.id.chronometer_hint) : null;
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
        View view9 = getView();
        FrameLayout frameLayout = view9 != null ? (FrameLayout) view9.findViewById(R.id.focus_button_container) : null;
        this.focusButtonContainer = frameLayout;
        if (frameLayout != null) {
            frameLayout.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.notification.focus.moduleV3.ModuleTextButtonViewHolder.initView.1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    kotlin.jvm.internal.n.g(host, "host");
                    kotlin.jvm.internal.n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                }
            });
        }
        registerCompressChanged(new AnonymousClass2(module, this));
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        super.onDetach();
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getHintInfo());
        bind(template, sbn);
    }
}
