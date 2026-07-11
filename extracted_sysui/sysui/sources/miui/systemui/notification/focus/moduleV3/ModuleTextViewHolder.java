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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTextViewHolder extends ModuleViewHolder {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ModuleTextViewHolder";
    private LinearLayout contentContainer;
    private int extra;
    private TextView extraTitleDivider;
    private TextView extraTitleView;
    private TextView focusContent;
    private TextView focusTitle;
    private TextView functionIconDivider;
    private ImageView functionIconView;
    private final boolean island;
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

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleTextViewHolder$initView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m146invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m146invoke() {
            ModuleTextViewHolder.this.checkAndCompressTitleViews();
            ModuleTextViewHolder.this.checkAndCompressContentViews();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
        this.extra = DynamicIslandUtils.INSTANCE.dpToPx(2, ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAndCompressContentViews() {
        int i2;
        int i3;
        if (getView() == null) {
            return;
        }
        int contentViewsTotalWidth = getContentViewsTotalWidth();
        View view = getView();
        int measuredWidth = (view != null ? view.getMeasuredWidth() : 0) - this.extra;
        View view2 = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view2 != null ? view2.getContext() : null, 84);
        if (contentViewsTotalWidth <= measuredWidth) {
            TextView textView = this.focusContent;
            setViewWidth(textView, getTextViewWidth(textView), Math.min(getTextViewWidth(this.focusContent), iDpToPx));
            if (TextUtils.isEmpty(getSubContent())) {
                return;
            }
            TextView textView2 = this.subContentView;
            setViewWidth(textView2, getTextViewWidth(textView2), Math.min(getTextViewWidth(this.subContentView), iDpToPx));
            return;
        }
        int i4 = contentViewsTotalWidth - measuredWidth;
        int textViewWidth = getTextViewWidth(this.focusContent);
        int textViewWidth2 = getTextViewWidth(this.subContentView);
        Log.d(TAG, "checkAndCompressContentViews: excessWidth " + i4);
        if (i4 > 0) {
            int i5 = textViewWidth > iDpToPx ? 1 : 0;
            if (textViewWidth2 > iDpToPx) {
                i5++;
            }
            if (i5 == 2) {
                int i6 = i4 / i5;
                i3 = iDpToPx - (textViewWidth - i6);
                i2 = iDpToPx - (textViewWidth2 - i6);
            } else {
                i2 = 0;
                i3 = 0;
            }
            if (textViewWidth > iDpToPx) {
                setViewWidth(this.focusContent, (textViewWidth - (i4 / i5)) - Math.max(i2, 0), iDpToPx);
            }
            if (textViewWidth2 > iDpToPx) {
                setViewWidth(this.subContentView, (textViewWidth2 - (i4 / i5)) - Math.max(i3, 0), iDpToPx);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAndCompressTitleViews() {
        compressTitleViewsStep1(false);
        compressTitleViewsStep2();
        compressTitleViewsStep1(true);
        compressTitleViewsStep1(true);
    }

    private final void compressTitleViewsStep1(boolean z2) {
        int textViewWidth;
        int textViewWidth2;
        int textViewWidth3;
        TextView textView;
        TextView textView2;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        if (getView() == null) {
            return;
        }
        int titleViewsTotalWidthAfterCompress = getTitleViewsTotalWidthAfterCompress();
        if (!z2) {
            titleViewsTotalWidthAfterCompress = getTitleViewsTotalWidth();
        }
        View view = getView();
        int measuredWidth = (view != null ? view.getMeasuredWidth() : 0) - this.extra;
        View view2 = getView();
        Integer numValueOf = null;
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view2 != null ? view2.getContext() : null, 80);
        if (titleViewsTotalWidthAfterCompress <= measuredWidth) {
            if (z2) {
                return;
            }
            TextView textView3 = this.focusTitle;
            setViewWidth(textView3, getTextViewWidth(textView3), Math.min(getTextViewWidth(this.focusTitle), iDpToPx));
            TextView textView4 = this.subTitleView;
            setViewWidth(textView4, getTextViewWidth(textView4), Math.min(getTextViewWidth(this.subTitleView), iDpToPx));
            TextView textView5 = this.extraTitleView;
            setViewWidth(textView5, getTextViewWidth(textView5), Math.min(getTextViewWidth(this.extraTitleView), 0));
            return;
        }
        int i2 = titleViewsTotalWidthAfterCompress - measuredWidth;
        Log.i(TAG, "compressTitleViewsStep1 excessWidth " + i2 + " " + z2);
        TextView textView6 = this.focusTitle;
        if (z2) {
            Integer numValueOf2 = (textView6 == null || (layoutParams3 = textView6.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams3.width);
            kotlin.jvm.internal.n.d(numValueOf2);
            textViewWidth = numValueOf2.intValue();
        } else {
            textViewWidth = getTextViewWidth(textView6);
        }
        TextView textView7 = this.subTitleView;
        if (z2) {
            Integer numValueOf3 = (textView7 == null || (layoutParams2 = textView7.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams2.width);
            kotlin.jvm.internal.n.d(numValueOf3);
            textViewWidth2 = numValueOf3.intValue();
        } else {
            textViewWidth2 = getTextViewWidth(textView7);
        }
        if (z2) {
            TextView textView8 = this.extraTitleView;
            if (textView8 != null && (layoutParams = textView8.getLayoutParams()) != null) {
                numValueOf = Integer.valueOf(layoutParams.width);
            }
            kotlin.jvm.internal.n.d(numValueOf);
            textViewWidth3 = numValueOf.intValue();
        } else {
            textViewWidth3 = getTextViewWidth(this.extraTitleView);
        }
        if (i2 > 0) {
            Log.i(TAG, "compressTitleViewsStep1 focusTitle " + textViewWidth + " " + iDpToPx);
            int i3 = textViewWidth > iDpToPx ? 1 : 0;
            Log.i(TAG, "compressTitleViewsStep1 subTitleView " + textViewWidth2 + " " + iDpToPx);
            if (textViewWidth2 > iDpToPx) {
                i3++;
            }
            Log.i(TAG, "compressTitleViewsStep1 extraTitleView " + textViewWidth3 + " " + iDpToPx);
            if (textViewWidth3 > iDpToPx) {
                i3++;
            }
            if (i3 > 0) {
                if (i2 >= i3) {
                    i2 /= i3;
                }
                if (textViewWidth > iDpToPx) {
                    setViewWidth(this.focusTitle, textViewWidth - i2, iDpToPx);
                } else {
                    TextView textView9 = this.focusTitle;
                    setViewWidth(textView9, getTextViewWidth(textView9), Math.min(getTextViewWidth(this.focusTitle), iDpToPx));
                }
                if (textViewWidth2 > iDpToPx) {
                    setViewWidth(this.subTitleView, textViewWidth2 - i2, iDpToPx);
                } else {
                    TextView textView10 = this.subTitleView;
                    setViewWidth(textView10, getTextViewWidth(textView10), Math.min(getTextViewWidth(this.subTitleView), iDpToPx));
                }
                if (textViewWidth3 <= iDpToPx) {
                    TextView textView11 = this.extraTitleView;
                    setViewWidth(textView11, getTextViewWidth(textView11), Math.min(getTextViewWidth(this.extraTitleView), 0));
                    return;
                }
                setViewWidth(this.extraTitleView, textViewWidth3 - i2, iDpToPx);
                TextView textView12 = this.subTitleDivider;
                if (textView12 == null || textView12.getVisibility() != 0 || (textView = this.extraTitleView) == null || textView.getVisibility() != 0 || (textView2 = this.extraTitleDivider) == null) {
                    return;
                }
                textView2.setVisibility(0);
            }
        }
    }

    private final void compressTitleViewsStep2() {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        if (getView() == null) {
            return;
        }
        int titleViewsTotalWidthAfterCompress = getTitleViewsTotalWidthAfterCompress();
        View view = getView();
        int measuredWidth = (view != null ? view.getMeasuredWidth() : 0) - this.extra;
        if (titleViewsTotalWidthAfterCompress <= measuredWidth) {
            return;
        }
        int i2 = titleViewsTotalWidthAfterCompress - measuredWidth;
        Log.i(TAG, "compressTitleViewsStep2 excessWidth " + i2);
        if (i2 > 0) {
            TextView textView = this.extraTitleView;
            Integer numValueOf = null;
            Integer numValueOf2 = (textView == null || (layoutParams2 = textView.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams2.width);
            kotlin.jvm.internal.n.d(numValueOf2);
            if (numValueOf2.intValue() <= 0) {
                View view2 = getView();
                if (view2 != null) {
                    view2.requestLayout();
                    return;
                }
                return;
            }
            View view3 = getView();
            int iDpToPx = ModuleViewHolderExtKt.dpToPx(view3 != null ? view3.getContext() : null, 45);
            TextView textView2 = this.extraTitleView;
            TextView textView3 = this.extraTitleDivider;
            if (textView2 != null && (layoutParams = textView2.getLayoutParams()) != null) {
                numValueOf = Integer.valueOf(layoutParams.width);
            }
            kotlin.jvm.internal.n.d(numValueOf);
            setViewWidthMinZero(textView2, textView3, numValueOf.intValue() - i2, iDpToPx);
        }
    }

    private final int getContentViewsTotalWidth() {
        if (getView() == null) {
            return 0;
        }
        View view = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view != null ? view.getContext() : null, 8);
        int textViewWidth = getTextViewWidth(this.focusContent);
        TextView textView = this.subContentDivider;
        Integer numValueOf = textView != null ? Integer.valueOf(textView.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue = textViewWidth + numValueOf.intValue();
        TextView textView2 = this.subContentDivider;
        Integer numValueOf2 = textView2 != null ? Integer.valueOf(textView2.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf2);
        int textViewWidth2 = iIntValue + (numValueOf2.intValue() > 0 ? iDpToPx : 0) + getTextViewWidth(this.subContentView) + (getTextViewWidth(this.subContentView) > 0 ? iDpToPx : 0);
        TextView textView3 = this.functionIconDivider;
        Integer numValueOf3 = textView3 != null ? Integer.valueOf(textView3.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf3);
        int iIntValue2 = textViewWidth2 + numValueOf3.intValue();
        TextView textView4 = this.functionIconDivider;
        Integer numValueOf4 = textView4 != null ? Integer.valueOf(textView4.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf4);
        int i2 = iIntValue2 + (numValueOf4.intValue() > 0 ? iDpToPx : 0);
        ImageView imageView = this.functionIconView;
        Integer numValueOf5 = imageView != null ? Integer.valueOf(imageView.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf5);
        int iIntValue3 = i2 + numValueOf5.intValue();
        ImageView imageView2 = this.functionIconView;
        Integer numValueOf6 = imageView2 != null ? Integer.valueOf(imageView2.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf6);
        int i3 = iIntValue3 + (numValueOf6.intValue() > 0 ? iDpToPx : 0);
        int textViewWidth3 = getTextViewWidth(this.focusContent);
        TextView textView5 = this.subContentDivider;
        Integer numValueOf7 = textView5 != null ? Integer.valueOf(textView5.getWidth()) : null;
        int textViewWidth4 = getTextViewWidth(this.subContentView);
        TextView textView6 = this.functionIconDivider;
        Integer numValueOf8 = textView6 != null ? Integer.valueOf(textView6.getWidth()) : null;
        ImageView imageView3 = this.functionIconView;
        Log.d(TAG, "getContentViewsTotalWidth: " + i3 + " " + textViewWidth3 + " " + numValueOf7 + " " + textViewWidth4 + " " + numValueOf8 + " " + (imageView3 != null ? Integer.valueOf(imageView3.getVisibility()) : null) + " " + iDpToPx);
        return i3;
    }

    private final int getTitleViewsTotalWidth() {
        int iIntValue = 0;
        if (getView() == null) {
            return 0;
        }
        View view = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view != null ? view.getContext() : null, 8);
        int textViewWidth = getTextViewWidth(this.focusTitle);
        TextView textView = this.subTitleDivider;
        Integer numValueOf = textView != null ? Integer.valueOf(textView.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue2 = textViewWidth + numValueOf.intValue();
        TextView textView2 = this.subTitleDivider;
        Integer numValueOf2 = textView2 != null ? Integer.valueOf(textView2.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf2);
        int textViewWidth2 = iIntValue2 + (numValueOf2.intValue() > 0 ? iDpToPx : 0) + getTextViewWidth(this.subTitleView) + (getTextViewWidth(this.subTitleView) > 0 ? iDpToPx : 0);
        TextView textView3 = this.extraTitleDivider;
        Integer numValueOf3 = textView3 != null ? Integer.valueOf(textView3.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf3);
        int iIntValue3 = textViewWidth2 + numValueOf3.intValue();
        TextView textView4 = this.extraTitleDivider;
        Integer numValueOf4 = textView4 != null ? Integer.valueOf(textView4.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf4);
        int textViewWidth3 = iIntValue3 + (numValueOf4.intValue() > 0 ? iDpToPx : 0) + getTextViewWidth(this.extraTitleView) + (getTextViewWidth(this.extraTitleView) > 0 ? iDpToPx : 0);
        TextView textView5 = this.specialTitleView;
        if (textView5 == null || textView5.getVisibility() != 0) {
            TextView textView6 = this.specialTitleView;
            if (textView6 != null && textView6.getVisibility() == 0) {
                iIntValue = iDpToPx;
            }
        } else {
            TextView textView7 = this.specialTitleView;
            Integer numValueOf5 = textView7 != null ? Integer.valueOf(textView7.getWidth()) : null;
            kotlin.jvm.internal.n.d(numValueOf5);
            iIntValue = numValueOf5.intValue();
        }
        int i2 = textViewWidth3 + iIntValue;
        TextView textView8 = this.focusTitle;
        Integer numValueOf6 = textView8 != null ? Integer.valueOf(textView8.getWidth()) : null;
        TextView textView9 = this.subTitleDivider;
        Integer numValueOf7 = textView9 != null ? Integer.valueOf(textView9.getWidth()) : null;
        TextView textView10 = this.subTitleView;
        Integer numValueOf8 = textView10 != null ? Integer.valueOf(textView10.getWidth()) : null;
        TextView textView11 = this.extraTitleView;
        Integer numValueOf9 = textView11 != null ? Integer.valueOf(textView11.getWidth()) : null;
        TextView textView12 = this.specialTitleView;
        Integer numValueOf10 = textView12 != null ? Integer.valueOf(textView12.getVisibility()) : null;
        TextView textView13 = this.specialTitleView;
        Log.d(TAG, "getTitleViewsTotalWidth: " + i2 + " " + numValueOf6 + " " + numValueOf7 + " " + numValueOf8 + " " + numValueOf9 + " " + numValueOf10 + " " + (textView13 != null ? Integer.valueOf(textView13.getWidth()) : null) + " " + iDpToPx);
        return i2;
    }

    private final int getTitleViewsTotalWidthAfterCompress() {
        int width;
        Integer numValueOf;
        int width2;
        Integer numValueOf2;
        int width3;
        Integer numValueOf3;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        ViewGroup.LayoutParams layoutParams5;
        ViewGroup.LayoutParams layoutParams6;
        int iIntValue = 0;
        if (getView() == null) {
            return 0;
        }
        View view = getView();
        int iDpToPx = ModuleViewHolderExtKt.dpToPx(view != null ? view.getContext() : null, 8);
        TextView textView = this.focusTitle;
        Integer numValueOf4 = (textView == null || (layoutParams6 = textView.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams6.width);
        kotlin.jvm.internal.n.d(numValueOf4);
        if (numValueOf4.intValue() >= 0) {
            TextView textView2 = this.focusTitle;
            if (textView2 != null && (layoutParams5 = textView2.getLayoutParams()) != null) {
                width = layoutParams5.width;
                numValueOf = Integer.valueOf(width);
            }
            numValueOf = null;
        } else {
            TextView textView3 = this.focusTitle;
            if (textView3 != null) {
                width = textView3.getWidth();
                numValueOf = Integer.valueOf(width);
            }
            numValueOf = null;
        }
        kotlin.jvm.internal.n.d(numValueOf);
        int iIntValue2 = numValueOf.intValue();
        TextView textView4 = this.subTitleView;
        Integer numValueOf5 = (textView4 == null || (layoutParams4 = textView4.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams4.width);
        kotlin.jvm.internal.n.d(numValueOf5);
        if (numValueOf5.intValue() >= 0) {
            TextView textView5 = this.subTitleView;
            if (textView5 != null && (layoutParams3 = textView5.getLayoutParams()) != null) {
                width2 = layoutParams3.width;
                numValueOf2 = Integer.valueOf(width2);
            }
            numValueOf2 = null;
        } else {
            TextView textView6 = this.subTitleView;
            if (textView6 != null) {
                width2 = textView6.getWidth();
                numValueOf2 = Integer.valueOf(width2);
            }
            numValueOf2 = null;
        }
        kotlin.jvm.internal.n.d(numValueOf2);
        int iIntValue3 = numValueOf2.intValue();
        TextView textView7 = this.extraTitleView;
        Integer numValueOf6 = (textView7 == null || (layoutParams2 = textView7.getLayoutParams()) == null) ? null : Integer.valueOf(layoutParams2.width);
        kotlin.jvm.internal.n.d(numValueOf6);
        if (numValueOf6.intValue() >= 0) {
            TextView textView8 = this.extraTitleView;
            if (textView8 != null && (layoutParams = textView8.getLayoutParams()) != null) {
                width3 = layoutParams.width;
                numValueOf3 = Integer.valueOf(width3);
            }
            numValueOf3 = null;
        } else {
            TextView textView9 = this.extraTitleView;
            if (textView9 != null) {
                width3 = textView9.getWidth();
                numValueOf3 = Integer.valueOf(width3);
            }
            numValueOf3 = null;
        }
        kotlin.jvm.internal.n.d(numValueOf3);
        int iIntValue4 = numValueOf3.intValue();
        TextView textView10 = this.subTitleDivider;
        Integer numValueOf7 = textView10 != null ? Integer.valueOf(textView10.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf7);
        int iIntValue5 = numValueOf7.intValue() + iIntValue2;
        TextView textView11 = this.subTitleDivider;
        Integer numValueOf8 = textView11 != null ? Integer.valueOf(textView11.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf8);
        int i2 = iIntValue5 + (numValueOf8.intValue() > 0 ? iDpToPx : 0) + iIntValue3 + (iIntValue3 > 0 ? iDpToPx : 0);
        TextView textView12 = this.extraTitleDivider;
        Integer numValueOf9 = textView12 != null ? Integer.valueOf(textView12.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf9);
        int iIntValue6 = i2 + numValueOf9.intValue();
        TextView textView13 = this.extraTitleDivider;
        Integer numValueOf10 = textView13 != null ? Integer.valueOf(textView13.getWidth()) : null;
        kotlin.jvm.internal.n.d(numValueOf10);
        int iMax = iIntValue6 + (numValueOf10.intValue() > 0 ? iDpToPx : 0) + Math.max(iIntValue4, 0) + (iIntValue4 > 0 ? iDpToPx : 0);
        TextView textView14 = this.specialTitleView;
        if (textView14 == null || textView14.getVisibility() != 0) {
            TextView textView15 = this.specialTitleView;
            if (textView15 != null && textView15.getVisibility() == 0) {
                iIntValue = iDpToPx;
            }
        } else {
            TextView textView16 = this.specialTitleView;
            Integer numValueOf11 = textView16 != null ? Integer.valueOf(textView16.getWidth()) : null;
            kotlin.jvm.internal.n.d(numValueOf11);
            iIntValue = numValueOf11.intValue();
        }
        int i3 = iMax + iIntValue;
        TextView textView17 = this.subTitleDivider;
        Integer numValueOf12 = textView17 != null ? Integer.valueOf(textView17.getWidth()) : null;
        TextView textView18 = this.specialTitleView;
        Integer numValueOf13 = textView18 != null ? Integer.valueOf(textView18.getVisibility()) : null;
        TextView textView19 = this.specialTitleView;
        Log.d(TAG, "getTitleViewsTotalWidthAfterCompress: " + i3 + " " + iIntValue2 + " " + numValueOf12 + " " + iIntValue3 + " " + iIntValue4 + " " + numValueOf13 + " " + (textView19 != null ? Integer.valueOf(textView19.getWidth()) : null) + " " + iDpToPx);
        return i3;
    }

    private final void resetViewLayoutParam(View view) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        if (layoutParams == null) {
            return;
        }
        layoutParams.width = -2;
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

    private final void setViewWidthMinZero(TextView textView, View view, int i2, int i3) {
        Log.i(TAG, "setViewWidthMinZero " + i2 + " " + textView);
        if (textView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = (int) Math.max(i2 < i3 ? 0 : i2, 0.0d);
        if (i2 < i3) {
            textView.setVisibility(8);
            if (view != null) {
                view.setVisibility(8);
            }
        }
        textView.setLayoutParams(layoutParams);
        textView.requestLayout();
    }

    private final void showFocusIcon(Template template, StatusBarNotification statusBarNotification) {
        BaseInfo baseInfo = template.getBaseInfo();
        Icon icon = getIcon(baseInfo != null ? baseInfo.getPicFunction() : null, statusBarNotification);
        BaseInfo baseInfo2 = template.getBaseInfo();
        boolean zC = baseInfo2 != null ? kotlin.jvm.internal.n.c(baseInfo2.getShowContentDivider(), Boolean.TRUE) : false;
        if (icon == null) {
            ImageView imageView = this.functionIconView;
            if (imageView != null) {
                imageView.setVisibility(8);
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
        if (TextUtils.isEmpty(getTitle())) {
            TextView textView = this.focusTitle;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            TextView textView2 = this.focusTitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            TextView textView3 = this.focusTitle;
            if (textView3 != null) {
                textView3.setText(Html.fromHtml(getTitle()));
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                TextView textView4 = this.focusTitle;
                if (textView4 != null) {
                    textView4.setTextColor(iIntValue);
                }
            }
        }
        if (TextUtils.isEmpty(getContent())) {
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
                layoutParams2.topMargin = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_button_without_icon_margin_start);
            }
            BaseInfo baseInfo4 = template.getBaseInfo();
            if (baseInfo4 != null ? kotlin.jvm.internal.n.c(baseInfo4.getSetMarginBottom(), Boolean.TRUE) : false) {
                layoutParams2.bottomMargin = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_button_without_icon_margin_start);
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
                int iIntValue2 = contentColor.intValue();
                TextView textView7 = this.focusContent;
                if (textView7 != null) {
                    textView7.setTextColor(iIntValue2);
                }
            }
        }
        if (TextUtils.isEmpty(getSubtitle())) {
            TextView textView8 = this.subTitleView;
            if (textView8 != null) {
                textView8.setVisibility(8);
            }
            TextView textView9 = this.subTitleDivider;
            if (textView9 != null) {
                textView9.setVisibility(8);
            }
        } else {
            TextView textView10 = this.subTitleView;
            if (textView10 != null) {
                textView10.setVisibility(0);
            }
            TextView textView11 = this.subTitleView;
            if (textView11 != null) {
                textView11.setText(Html.fromHtml(getSubtitle()));
            }
            Integer subTitleColor = getSubTitleColor();
            if (subTitleColor != null) {
                int iIntValue3 = subTitleColor.intValue();
                TextView textView12 = this.subTitleView;
                if (textView12 != null) {
                    textView12.setTextColor(iIntValue3);
                }
            }
            if (zC) {
                TextView textView13 = this.subTitleDivider;
                if (textView13 != null) {
                    textView13.setVisibility(0);
                }
                Integer subTitleColor2 = getSubTitleColor();
                if (subTitleColor2 != null) {
                    int iIntValue4 = subTitleColor2.intValue();
                    TextView textView14 = this.subTitleDivider;
                    if (textView14 != null) {
                        textView14.setTextColor(iIntValue4);
                    }
                }
            } else {
                TextView textView15 = this.subTitleDivider;
                if (textView15 != null) {
                    textView15.setVisibility(8);
                }
            }
        }
        if (TextUtils.isEmpty(getSubContent())) {
            TextView textView16 = this.subContentView;
            if (textView16 != null) {
                textView16.setVisibility(8);
            }
            TextView textView17 = this.subContentDivider;
            if (textView17 != null) {
                textView17.setVisibility(8);
            }
        } else {
            TextView textView18 = this.subContentView;
            if (textView18 != null) {
                textView18.setVisibility(0);
            }
            TextView textView19 = this.subContentView;
            if (textView19 != null) {
                textView19.setText(Html.fromHtml(getSubContent()));
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue5 = subContentColor.intValue();
                TextView textView20 = this.subContentView;
                if (textView20 != null) {
                    textView20.setTextColor(iIntValue5);
                }
            }
            if (zC2) {
                TextView textView21 = this.subContentDivider;
                if (textView21 != null) {
                    textView21.setVisibility(0);
                }
                Integer subContentColor2 = getSubContentColor();
                if (subContentColor2 != null) {
                    int iIntValue6 = subContentColor2.intValue();
                    TextView textView22 = this.subContentDivider;
                    if (textView22 != null) {
                        textView22.setTextColor(iIntValue6);
                    }
                }
            } else {
                TextView textView23 = this.subContentDivider;
                if (textView23 != null) {
                    textView23.setVisibility(8);
                }
            }
        }
        if (TextUtils.isEmpty(getExtraTitle())) {
            TextView textView24 = this.extraTitleView;
            if (textView24 != null) {
                textView24.setVisibility(8);
            }
            TextView textView25 = this.extraTitleDivider;
            if (textView25 != null) {
                textView25.setVisibility(8);
            }
        } else {
            TextView textView26 = this.extraTitleView;
            if (textView26 != null) {
                textView26.setVisibility(0);
            }
            TextView textView27 = this.extraTitleView;
            if (textView27 != null) {
                textView27.setText(Html.fromHtml(getExtraTitle()));
            }
            Integer extraTitleColor = getExtraTitleColor();
            if (extraTitleColor != null) {
                int iIntValue7 = extraTitleColor.intValue();
                TextView textView28 = this.extraTitleView;
                if (textView28 != null) {
                    textView28.setTextColor(iIntValue7);
                }
            }
            if (zC) {
                Integer extraTitleColor2 = getExtraTitleColor();
                if (extraTitleColor2 != null) {
                    int iIntValue8 = extraTitleColor2.intValue();
                    TextView textView29 = this.extraTitleDivider;
                    if (textView29 != null) {
                        textView29.setTextColor(iIntValue8);
                    }
                }
                TextView textView30 = this.extraTitleDivider;
                if (textView30 != null) {
                    textView30.setVisibility(0);
                }
            } else {
                TextView textView31 = this.extraTitleDivider;
                if (textView31 != null) {
                    textView31.setVisibility(8);
                }
            }
        }
        Log.e(TAG, "specialTitle " + getSpecialTitle());
        if (TextUtils.isEmpty(getSpecialTitle())) {
            Log.e(TAG, "specialTitle2 " + getSpecialTitle());
            TextView textView32 = this.specialTitleView;
            if (textView32 != null) {
                textView32.setVisibility(8);
            }
        } else {
            Log.e(TAG, "specialTitle1 " + getSpecialTitle());
            TextView textView33 = this.specialTitleView;
            if (textView33 != null) {
                textView33.setVisibility(0);
            }
            TextView textView34 = this.specialTitleView;
            if (textView34 != null) {
                textView34.setText(Html.fromHtml(getSpecialTitle()));
            }
            Integer specialTitleColor = getSpecialTitleColor();
            if (specialTitleColor != null) {
                int iIntValue9 = specialTitleColor.intValue();
                TextView textView35 = this.specialTitleView;
                if (textView35 != null) {
                    textView35.setTextColor(iIntValue9);
                }
            }
        }
        showFocusIcon(template, sbn);
        setTextContainerBackground(template);
        notifyDataChanged();
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        if (kotlin.jvm.internal.n.c(module, Const.Module.MODULE_TEXT_1)) {
            setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_text_1, getRootView()));
        } else {
            setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_text_2, getRootView()));
        }
        View view = getView();
        this.focusTitle = view != null ? (TextView) view.findViewById(R.id.focus_title) : null;
        View view2 = getView();
        this.contentContainer = view2 != null ? (LinearLayout) view2.findViewById(R.id.content_container) : null;
        View view3 = getView();
        this.focusContent = view3 != null ? (TextView) view3.findViewById(R.id.focus_content) : null;
        View view4 = getView();
        this.titleContainer = view4 != null ? (LinearLayout) view4.findViewById(R.id.title_container) : null;
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
        initTextAndColor(template.getBaseInfo());
        bind(template, sbn);
    }
}
