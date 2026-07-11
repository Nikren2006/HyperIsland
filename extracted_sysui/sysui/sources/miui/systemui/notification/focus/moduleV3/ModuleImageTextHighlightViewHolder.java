package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.HighlightInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.model.TimerInfo;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleImageTextHighlightViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ModuleImageTextHighlightViewHolder";
    private HyperChronometer chronometer;
    private View container;
    private TextView currentTitleView;
    private int extra;
    private TextView focusContent;
    private ImageView focusIcon;
    private TextView focusSubContent;
    private TextView focusTitle;
    private final boolean island;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleImageTextHighlightViewHolder$initView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m143invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m143invoke() {
            ModuleImageTextHighlightViewHolder.this.adaptTitleContentViews();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleImageTextHighlightViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adaptTitleContentViews() {
        checkAndCompressTitleViews();
    }

    private final void checkAndCompressTitleViews() {
        compressTitleViewsStep1();
        compressTitleViewsStep2();
    }

    private final void compressTitleViewsStep1() {
        int i2;
        int i3;
        int titleViewsTotalWidth = getTitleViewsTotalWidth();
        if (titleViewsTotalWidth == 0 || getView() == null || this.currentTitleView == null) {
            return;
        }
        View view = getView();
        Integer numValueOf = view != null ? Integer.valueOf(view.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue = numValueOf.intValue() - this.extra;
        Log.i(TAG, "compressTitleViewsStep1 totalWidth " + titleViewsTotalWidth + " layoutWidth " + iIntValue);
        View view2 = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view2 != null ? view2.getContext() : null, 30);
        if (titleViewsTotalWidth <= iIntValue) {
            int textViewWidth = getTextViewWidth(this.currentTitleView);
            TextView textView = this.currentTitleView;
            Integer numValueOf2 = textView != null ? Integer.valueOf(textView.getWidth()) : null;
            kotlin.jvm.internal.n.d(numValueOf2);
            if (textViewWidth > numValueOf2.intValue()) {
                TextView textView2 = this.currentTitleView;
                setViewWidth(textView2, getTextViewWidth(textView2), 0);
            }
            TextView textView3 = this.focusContent;
            setViewWidth(textView3, getTextViewWidth(textView3), 0);
            TextView textView4 = this.focusSubContent;
            setViewWidth(textView4, getTextViewWidth(textView4), 0);
            return;
        }
        int i4 = titleViewsTotalWidth - iIntValue;
        Log.i(TAG, "compressTitleViewsStep1 excessWidth " + i4);
        int textViewWidth2 = getTextViewWidth(this.focusContent);
        int textViewWidth3 = getTextViewWidth(this.focusSubContent);
        if (i4 > 0) {
            TextView textView5 = this.currentTitleView;
            Log.i(TAG, "compressTitleViewsStep1 focusContent " + (textView5 != null ? Integer.valueOf(textView5.getWidth()) : null) + " " + iDpToPx);
            int i5 = textViewWidth2 > iDpToPx ? 1 : 0;
            TextView textView6 = this.focusSubContent;
            Log.i(TAG, "compressTitleViewsStep1 focusSubContent " + (textView6 != null ? Integer.valueOf(textView6.getWidth()) : null) + " " + iDpToPx);
            if (textViewWidth3 > 0) {
                i5++;
            }
            if (i5 == 2) {
                int i6 = i4 / i5;
                i3 = iDpToPx - (textViewWidth2 - i6);
                i2 = -(textViewWidth3 - i6);
            } else {
                i2 = 0;
                i3 = 0;
            }
            if (textViewWidth2 > iDpToPx) {
                setViewWidth(this.focusContent, (iDpToPx - (i4 / i5)) - Math.max(i2, 0), iDpToPx);
            }
            if (textViewWidth3 > 0) {
                setViewWidth(this.focusSubContent, (textViewWidth3 - (i4 / i5)) - Math.max(i3, 0), iDpToPx);
            }
        }
    }

    private final void compressTitleViewsStep2() {
        int titleViewsTotalWidthAfterCompress = getTitleViewsTotalWidthAfterCompress();
        if (titleViewsTotalWidthAfterCompress == 0 || getView() == null) {
            return;
        }
        View view = getView();
        Integer numValueOf = view != null ? Integer.valueOf(view.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue = numValueOf.intValue() - this.extra;
        Log.i(TAG, "compressTitleViewsStep2 totalWidth " + titleViewsTotalWidthAfterCompress + " layoutWidth " + iIntValue);
        if (titleViewsTotalWidthAfterCompress <= iIntValue) {
            return;
        }
        View view2 = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view2 != null ? view2.getContext() : null, 100);
        int i2 = titleViewsTotalWidthAfterCompress - iIntValue;
        if (i2 > 0 && getTextViewWidth(this.currentTitleView) > iDpToPx) {
            TextView textView = this.currentTitleView;
            setViewWidth(textView, getTextViewWidth(textView) - i2, iDpToPx);
        }
        View view3 = getView();
        if (view3 != null) {
            view3.requestLayout();
        }
    }

    private final int getTitleViewsTotalWidth() {
        int iIntValue;
        if (getView() == null) {
            return 0;
        }
        View view = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view != null ? view.getContext() : null, 4);
        int textViewWidth = getTextViewWidth(this.currentTitleView) + getTextViewWidth(this.focusContent) + (getTextViewWidth(this.focusContent) > 0 ? iDpToPx : 0);
        ImageView imageView = this.focusIcon;
        if (imageView == null || imageView.getVisibility() != 0) {
            iIntValue = 0;
        } else {
            ImageView imageView2 = this.focusIcon;
            Integer numValueOf = imageView2 != null ? Integer.valueOf(imageView2.getWidth()) : null;
            kotlin.jvm.internal.n.d(numValueOf);
            iIntValue = numValueOf.intValue();
        }
        int i2 = textViewWidth + iIntValue;
        ImageView imageView3 = this.focusIcon;
        Integer numValueOf2 = imageView3 != null ? Integer.valueOf(imageView3.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf2);
        int textViewWidth2 = i2 + (numValueOf2.intValue() > 0 ? iDpToPx : 0) + getTextViewWidth(this.focusSubContent) + (getTextViewWidth(this.focusSubContent) > 0 ? iDpToPx : 0) + iDpToPx;
        int textViewWidth3 = getTextViewWidth(this.currentTitleView);
        int textViewWidth4 = getTextViewWidth(this.focusContent);
        int textViewWidth5 = getTextViewWidth(this.focusContent);
        ImageView imageView4 = this.focusIcon;
        Integer numValueOf3 = imageView4 != null ? Integer.valueOf(imageView4.getVisibility()) : null;
        ImageView imageView5 = this.focusIcon;
        Integer numValueOf4 = imageView5 != null ? Integer.valueOf(imageView5.getWidth()) : null;
        Log.d(TAG, "getTitleViewsTotalWidth: " + textViewWidth2 + " " + textViewWidth3 + " " + textViewWidth4 + " " + textViewWidth5 + "  " + numValueOf3 + " " + numValueOf4 + " " + getTextViewWidth(this.focusSubContent) + " " + iDpToPx);
        return textViewWidth2;
    }

    private final int getTitleViewsTotalWidthAfterCompress() {
        int width;
        Integer numValueOf;
        int width2;
        Integer numValueOf2;
        int iIntValue;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        if (getView() == null) {
            return 0;
        }
        View view = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view != null ? view.getContext() : null, 4);
        TextView textView = this.focusContent;
        Integer numValueOf3 = (textView == null || (layoutParams4 = textView.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams4.width);
        kotlin.jvm.internal.n.d(numValueOf3);
        if (numValueOf3.intValue() > 0) {
            TextView textView2 = this.focusContent;
            if (textView2 != null && (layoutParams3 = textView2.getLayoutParams()) != null) {
                width = layoutParams3.width;
                numValueOf = Integer.valueOf(width);
            }
            numValueOf = null;
        } else {
            TextView textView3 = this.focusContent;
            if (textView3 != null) {
                width = textView3.getWidth();
                numValueOf = Integer.valueOf(width);
            }
            numValueOf = null;
        }
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue2 = numValueOf.intValue();
        TextView textView4 = this.focusSubContent;
        Integer numValueOf4 = (textView4 == null || (layoutParams2 = textView4.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams2.width);
        kotlin.jvm.internal.n.d(numValueOf4);
        if (numValueOf4.intValue() > 0) {
            TextView textView5 = this.focusSubContent;
            if (textView5 != null && (layoutParams = textView5.getLayoutParams()) != null) {
                width2 = layoutParams.width;
                numValueOf2 = Integer.valueOf(width2);
            }
            numValueOf2 = null;
        } else {
            TextView textView6 = this.focusSubContent;
            if (textView6 != null) {
                width2 = textView6.getWidth();
                numValueOf2 = Integer.valueOf(width2);
            }
            numValueOf2 = null;
        }
        kotlin.jvm.internal.n.d(numValueOf2);
        int iIntValue3 = numValueOf2.intValue();
        int textViewWidth = getTextViewWidth(this.currentTitleView) + iIntValue2 + (iIntValue2 > 0 ? iDpToPx : 0);
        ImageView imageView = this.focusIcon;
        if (imageView == null || imageView.getVisibility() != 0) {
            iIntValue = 0;
        } else {
            ImageView imageView2 = this.focusIcon;
            Integer numValueOf5 = imageView2 != null ? Integer.valueOf(imageView2.getWidth()) : null;
            kotlin.jvm.internal.n.d(numValueOf5);
            iIntValue = numValueOf5.intValue();
        }
        int i2 = textViewWidth + iIntValue;
        ImageView imageView3 = this.focusIcon;
        Integer numValueOf6 = imageView3 != null ? Integer.valueOf(imageView3.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf6);
        int i3 = i2 + (numValueOf6.intValue() > 0 ? iDpToPx : 0) + iIntValue3 + (iIntValue3 > 0 ? iDpToPx : 0);
        int textViewWidth2 = getTextViewWidth(this.currentTitleView);
        ImageView imageView4 = this.focusIcon;
        Log.d(TAG, "getTitleViewsTotalWidthAfterCompress: " + i3 + " " + textViewWidth2 + " " + iIntValue2 + " " + (imageView4 != null ? Integer.valueOf(imageView4.getWidth()) : null) + " " + iIntValue3 + " " + iDpToPx);
        return i3;
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
        if (icon != null) {
            ImageView imageView = this.focusIcon;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            ImageView imageView2 = this.focusIcon;
            if (imageView2 != null) {
                imageView2.setImageIcon(icon);
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
            this.currentTitleView = this.chronometer;
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
            this.currentTitleView = this.focusTitle;
        }
        TextView textView5 = this.focusContent;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
        TextView textView6 = this.focusContent;
        if (textView6 != null) {
            textView6.setText(Html.fromHtml(getContent()));
        }
        Integer contentColor = getContentColor();
        if (contentColor != null) {
            int iIntValue3 = contentColor.intValue();
            TextView textView7 = this.focusContent;
            if (textView7 != null) {
                textView7.setTextColor(iIntValue3);
            }
        }
        String subContent = getSubContent();
        if (subContent != null && !TextUtils.isEmpty(subContent)) {
            TextView textView8 = this.focusSubContent;
            if (textView8 != null) {
                textView8.setVisibility(0);
            }
            TextView textView9 = this.focusSubContent;
            if (textView9 != null) {
                textView9.setText(Html.fromHtml(subContent));
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue4 = subContentColor.intValue();
                TextView textView10 = this.focusSubContent;
                if (textView10 != null) {
                    textView10.setTextColor(iIntValue4);
                }
            }
        }
        HighlightInfo highlightInfo2 = template.getHighlightInfo();
        if ((highlightInfo2 != null ? highlightInfo2.getTimerInfo() : null) != null) {
            ModuleViewHolder.setTimerData$default(this, 0, sbn, 1, null);
        }
        showFocusIcon(template, sbn);
        notifyDataChanged();
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

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_image_text_highlight, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_image_text_highlight) : null;
        View view2 = getView();
        HyperChronometer hyperChronometer = view2 != null ? (HyperChronometer) view2.findViewById(R.id.chronometer) : null;
        this.chronometer = hyperChronometer;
        if (hyperChronometer != null) {
            hyperChronometer.setEnableEffectWithInit(false);
        }
        View view3 = getView();
        this.focusTitle = view3 != null ? (TextView) view3.findViewById(R.id.focus_title) : null;
        View view4 = getView();
        this.focusContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_content) : null;
        View view5 = getView();
        this.focusSubContent = view5 != null ? (TextView) view5.findViewById(R.id.focus_sub_content) : null;
        View view6 = getView();
        this.focusIcon = view6 != null ? (ImageView) view6.findViewById(R.id.focus_function_icon) : null;
        registerCompressChanged(new AnonymousClass1());
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        super.onDetach();
        getRootView().removeView(getView());
        setView(null);
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
