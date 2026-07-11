package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.core.view.GravityCompat;
import c1.C0232d;
import com.android.systemui.miui.notification.R;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.ProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoPortProgressViewHolder extends ModuleViewHolder {
    private View container;
    private ProgressBar progressBar;
    private ImageView progressIcon;
    private ImageView progressPoint1Se;
    private ImageView progressPoint1Un;
    private ImageView progressPoint2Se;
    private ImageView progressPoint2Un;
    private ImageView progressPointEnd;
    private ImageView progressPointMiddle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortProgressViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final boolean hasProgressIcon(Template template, StatusBarNotification statusBarNotification) {
        ProgressInfo progressInfo = template.getProgressInfo();
        Icon icon = getIcon(progressInfo != null ? progressInfo.getPicForward() : null, statusBarNotification);
        ProgressInfo progressInfo2 = template.getProgressInfo();
        Icon icon2 = getIcon(progressInfo2 != null ? progressInfo2.getPicEnd() : null, statusBarNotification);
        ProgressInfo progressInfo3 = template.getProgressInfo();
        return (icon == null && icon2 == null && getIcon(progressInfo3 != null ? progressInfo3.getPicMiddle() : null, statusBarNotification) == null) ? false : true;
    }

    private final void setProgressColor(ProgressBar progressBar, Integer num, Integer num2) {
        if (num == null || num2 == null || num.intValue() == -1 || num2.intValue() == -1) {
            return;
        }
        Drawable progressDrawable = progressBar != null ? progressBar.getProgressDrawable() : null;
        if (progressDrawable instanceof LayerDrawable) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(progressBar.getContext().getResources().getDimensionPixelSize(R.dimen.notification_custom_progress_view_corner_radius));
            Context context = progressBar.getContext();
            kotlin.jvm.internal.n.f(context, "getContext(...)");
            if (CommonUtils.isLayoutRtl(context)) {
                gradientDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
            } else {
                gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            }
            gradientDrawable.setColors(new int[]{num.intValue(), num2.intValue()});
            Context context2 = progressBar.getContext();
            kotlin.jvm.internal.n.f(context2, "getContext(...)");
            ScaleDrawable scaleDrawable = new ScaleDrawable(gradientDrawable, CommonUtils.isLayoutRtl(context2) ? GravityCompat.END : 8388611, 1.0f, -1.0f);
            scaleDrawable.setLevel(progressBar.getProgress() * 100);
            ((LayerDrawable) progressDrawable).setDrawableByLayerId(android.R.id.progress, scaleDrawable);
            progressBar.setProgressDrawable(progressDrawable);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        Integer numValueOf;
        Integer numValueOf2;
        ProgressBar progressBar;
        ImageView imageView;
        ProgressBar progressBar2;
        ImageView imageView2;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        try {
            ProgressInfo progressInfo = template.getProgressInfo();
            numValueOf = Integer.valueOf(Color.parseColor(progressInfo != null ? progressInfo.getColorProgress() : null));
        } catch (Exception unused) {
            numValueOf = null;
        }
        try {
            ProgressInfo progressInfo2 = template.getProgressInfo();
            numValueOf2 = Integer.valueOf(Color.parseColor(progressInfo2 != null ? progressInfo2.getColorProgressEnd() : null));
        } catch (Exception unused2) {
            numValueOf2 = null;
        }
        ProgressInfo progressInfo3 = template.getProgressInfo();
        Integer numValueOf3 = progressInfo3 != null ? Integer.valueOf(progressInfo3.getProgress()) : null;
        if (numValueOf3 != null) {
            View view = this.container;
            if (view != null) {
                view.setVisibility(0);
            }
            int i2 = hasProgressIcon(template, sbn) ? R.id.focus_progress_info1 : R.id.focus_progress_info2;
            View view2 = getView();
            ProgressBar progressBar3 = view2 != null ? (ProgressBar) view2.findViewById(i2) : null;
            this.progressBar = progressBar3;
            if (progressBar3 != null) {
                progressBar3.setVisibility(0);
            }
            ProgressBar progressBar4 = this.progressBar;
            if (progressBar4 != null) {
                progressBar4.setMax(100);
            }
            ProgressBar progressBar5 = this.progressBar;
            if (progressBar5 != null) {
                progressBar5.setProgress(numValueOf3.intValue(), false);
            }
            if (numValueOf2 == null) {
                ProgressBar progressBar6 = this.progressBar;
                if (progressBar6 != null) {
                    progressBar6.setProgressTintList(numValueOf != null ? ColorStateList.valueOf(numValueOf.intValue()) : null);
                }
            } else {
                if (numValueOf != null) {
                    sbn.getNotification().extras.putInt(Const.Param.COLOR_PROGRESS, numValueOf.intValue());
                }
                sbn.getNotification().extras.putInt(Const.Param.COLOR_PROGRESS_END, numValueOf2.intValue());
            }
            if (numValueOf3.intValue() < 50) {
                ProgressInfo progressInfo4 = template.getProgressInfo();
                Icon icon = getIcon(progressInfo4 != null ? progressInfo4.getPicMiddleUnselected() : null, sbn);
                if (icon == null) {
                    ProgressInfo progressInfo5 = template.getProgressInfo();
                    icon = getIcon(progressInfo5 != null ? progressInfo5.getPicMiddle() : null, sbn);
                }
                ProgressInfo progressInfo6 = template.getProgressInfo();
                if (getIcon(progressInfo6 != null ? progressInfo6.getPicEndUnselected() : null, sbn) == null) {
                    ProgressInfo progressInfo7 = template.getProgressInfo();
                    getIcon(progressInfo7 != null ? progressInfo7.getPicEnd() : null, sbn);
                }
                ImageView imageView3 = this.progressPoint1Un;
                if (imageView3 != null) {
                    imageView3.setVisibility(0);
                }
                ImageView imageView4 = this.progressPoint1Un;
                if (imageView4 != null) {
                    imageView4.setImageIcon(icon);
                }
                if (icon != null && (imageView2 = this.progressPointEnd) != null) {
                    imageView2.setVisibility(0);
                }
                if (new C0232d(1, 20).h(numValueOf3.intValue()) && (progressBar2 = this.progressBar) != null) {
                    progressBar2.setProgress(numValueOf3.intValue() + 10, false);
                }
            } else if (numValueOf3.intValue() == 50) {
                ProgressInfo progressInfo8 = template.getProgressInfo();
                Icon icon2 = getIcon(progressInfo8 != null ? progressInfo8.getPicMiddle() : null, sbn);
                if (icon2 == null) {
                    ProgressInfo progressInfo9 = template.getProgressInfo();
                    icon2 = getIcon(progressInfo9 != null ? progressInfo9.getPicMiddleUnselected() : null, sbn);
                }
                ImageView imageView5 = this.progressPoint1Se;
                if (imageView5 != null) {
                    imageView5.setVisibility(0);
                }
                ImageView imageView6 = this.progressPoint1Se;
                if (imageView6 != null) {
                    imageView6.setImageIcon(icon2);
                }
                if (icon2 != null) {
                    ImageView imageView7 = this.progressPointMiddle;
                    if (imageView7 != null) {
                        imageView7.setVisibility(0);
                    }
                    ImageView imageView8 = this.progressPointEnd;
                    if (imageView8 != null) {
                        imageView8.setVisibility(0);
                    }
                }
                ProgressBar progressBar7 = this.progressBar;
                if (progressBar7 != null) {
                    progressBar7.setProgress(numValueOf3.intValue() + 10, false);
                }
            } else if (new C0232d(51, 99).h(numValueOf3.intValue())) {
                ProgressInfo progressInfo10 = template.getProgressInfo();
                Icon icon3 = getIcon(progressInfo10 != null ? progressInfo10.getPicEndUnselected() : null, sbn);
                if (icon3 == null) {
                    ProgressInfo progressInfo11 = template.getProgressInfo();
                    icon3 = getIcon(progressInfo11 != null ? progressInfo11.getPicEnd() : null, sbn);
                }
                if (icon3 != null && (imageView = this.progressPointMiddle) != null) {
                    imageView.setVisibility(0);
                }
                ImageView imageView9 = this.progressPoint2Un;
                if (imageView9 != null) {
                    imageView9.setVisibility(0);
                }
                ImageView imageView10 = this.progressPoint2Un;
                if (imageView10 != null) {
                    imageView10.setImageIcon(icon3);
                }
                ProgressBar progressBar8 = this.progressBar;
                ViewGroup.LayoutParams layoutParams = progressBar8 != null ? progressBar8.getLayoutParams() : null;
                kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.setMarginEnd((int) getCtx().getResources().getDimension(R.dimen.focus_notify_template_deco_progress_area_a_margin));
                ProgressBar progressBar9 = this.progressBar;
                if (progressBar9 != null) {
                    progressBar9.setLayoutParams(layoutParams2);
                }
                if (new C0232d(51, 70).h(numValueOf3.intValue()) && (progressBar = this.progressBar) != null) {
                    progressBar.setProgress(numValueOf3.intValue() + 10, false);
                }
            } else {
                ProgressInfo progressInfo12 = template.getProgressInfo();
                Icon icon4 = getIcon(progressInfo12 != null ? progressInfo12.getPicEnd() : null, sbn);
                if (icon4 == null) {
                    ProgressInfo progressInfo13 = template.getProgressInfo();
                    icon4 = getIcon(progressInfo13 != null ? progressInfo13.getPicEndUnselected() : null, sbn);
                }
                ImageView imageView11 = this.progressPoint2Se;
                if (imageView11 != null) {
                    imageView11.setVisibility(0);
                }
                ImageView imageView12 = this.progressPoint2Se;
                if (imageView12 != null) {
                    imageView12.setImageIcon(icon4);
                }
                ProgressBar progressBar10 = this.progressBar;
                ViewGroup.LayoutParams layoutParams3 = progressBar10 != null ? progressBar10.getLayoutParams() : null;
                kotlin.jvm.internal.n.e(layoutParams3, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams3;
                layoutParams4.setMarginEnd((int) getCtx().getResources().getDimension(R.dimen.focus_notify_template_deco_area_a_margin));
                ProgressBar progressBar11 = this.progressBar;
                if (progressBar11 != null) {
                    progressBar11.setLayoutParams(layoutParams4);
                }
            }
            setProgressColor(this.progressBar, numValueOf, numValueOf2);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_progress, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.progressbar_container) : null;
        View view2 = getView();
        this.progressPointMiddle = view2 != null ? (ImageView) view2.findViewById(R.id.progress_point_middle) : null;
        View view3 = getView();
        this.progressPointEnd = view3 != null ? (ImageView) view3.findViewById(R.id.progress_point_end) : null;
        View view4 = getView();
        this.progressPoint1Se = view4 != null ? (ImageView) view4.findViewById(R.id.progress_point1_se) : null;
        View view5 = getView();
        this.progressPoint2Se = view5 != null ? (ImageView) view5.findViewById(R.id.progress_point2_se) : null;
        View view6 = getView();
        this.progressPoint1Un = view6 != null ? (ImageView) view6.findViewById(R.id.progress_point1_un) : null;
        View view7 = getView();
        this.progressPoint2Un = view7 != null ? (ImageView) view7.findViewById(R.id.progress_point2_un) : null;
        View view8 = getView();
        this.progressIcon = view8 != null ? (ImageView) view8.findViewById(R.id.progress_icon) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
