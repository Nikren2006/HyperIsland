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
import kotlin.jvm.functions.Function0;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.ProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyProgressViewHolder extends ModuleViewHolder {
    private View container;
    private ProgressBar progressBar;
    private ImageView progressThumb;

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleTinyProgressViewHolder$initView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m148invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m148invoke() {
            ModuleTinyProgressViewHolder moduleTinyProgressViewHolder = ModuleTinyProgressViewHolder.this;
            moduleTinyProgressViewHolder.setProgressThumb(moduleTinyProgressViewHolder.progressBar, ModuleTinyProgressViewHolder.this.progressThumb);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyProgressViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void setProgressThumb(ProgressBar progressBar, ImageView imageView) {
        if (imageView == null || progressBar == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.setMarginStart((int) Math.max(Math.min(((progressBar.getWidth() * progressBar.getProgress()) / 100) - (imageView.getWidth() / 2), progressBar.getWidth() - imageView.getWidth()), 0.0d));
        imageView.setLayoutParams(layoutParams2);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        Integer numValueOf;
        Integer numValueOf2;
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
            ProgressBar progressBar = view2 != null ? (ProgressBar) view2.findViewById(i2) : null;
            this.progressBar = progressBar;
            if (progressBar != null) {
                progressBar.setVisibility(0);
            }
            ProgressBar progressBar2 = this.progressBar;
            if (progressBar2 != null) {
                progressBar2.setMax(100);
            }
            ProgressBar progressBar3 = this.progressBar;
            if (progressBar3 != null) {
                progressBar3.setProgress(numValueOf3.intValue(), false);
            }
            if (numValueOf2 == null) {
                ProgressBar progressBar4 = this.progressBar;
                if (progressBar4 != null) {
                    progressBar4.setProgressTintList(numValueOf != null ? ColorStateList.valueOf(numValueOf.intValue()) : null);
                }
            } else {
                if (numValueOf != null) {
                    sbn.getNotification().extras.putInt(Const.Param.COLOR_PROGRESS, numValueOf.intValue());
                }
                sbn.getNotification().extras.putInt(Const.Param.COLOR_PROGRESS_END, numValueOf2.intValue());
            }
            View view3 = getView();
            ImageView imageView = view3 != null ? (ImageView) view3.findViewById(R.id.progress_point1) : null;
            View view4 = getView();
            ImageView imageView2 = view4 != null ? (ImageView) view4.findViewById(R.id.progress_point2) : null;
            View view5 = getView();
            ImageView imageView3 = view5 != null ? (ImageView) view5.findViewById(R.id.progress_point_middle) : null;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            if (numValueOf3.intValue() < 50) {
                ProgressInfo progressInfo4 = template.getProgressInfo();
                Icon icon = getIcon(progressInfo4 != null ? progressInfo4.getPicMiddleUnselected() : null, sbn);
                if (icon == null) {
                    ProgressInfo progressInfo5 = template.getProgressInfo();
                    icon = getIcon(progressInfo5 != null ? progressInfo5.getPicMiddle() : null, sbn);
                }
                ProgressInfo progressInfo6 = template.getProgressInfo();
                Icon icon2 = getIcon(progressInfo6 != null ? progressInfo6.getPicEndUnselected() : null, sbn);
                if (icon2 == null) {
                    ProgressInfo progressInfo7 = template.getProgressInfo();
                    icon2 = getIcon(progressInfo7 != null ? progressInfo7.getPicEnd() : null, sbn);
                }
                if (icon2 != null && imageView3 != null) {
                    imageView3.setVisibility(8);
                }
                if (imageView != null) {
                    imageView.setImageIcon(icon);
                }
                if (imageView2 != null) {
                    imageView2.setImageIcon(icon2);
                }
            } else if (numValueOf3.intValue() == 50) {
                if (imageView3 != null) {
                    imageView3.setVisibility(8);
                }
                if (imageView != null) {
                    imageView.setVisibility(0);
                }
                ProgressInfo progressInfo8 = template.getProgressInfo();
                Icon icon3 = getIcon(progressInfo8 != null ? progressInfo8.getPicMiddle() : null, sbn);
                if (icon3 == null) {
                    ProgressInfo progressInfo9 = template.getProgressInfo();
                    icon3 = getIcon(progressInfo9 != null ? progressInfo9.getPicMiddleUnselected() : null, sbn);
                }
                if (imageView != null) {
                    imageView.setImageIcon(icon3);
                }
                ProgressInfo progressInfo10 = template.getProgressInfo();
                Icon icon4 = getIcon(progressInfo10 != null ? progressInfo10.getPicEndUnselected() : null, sbn);
                if (icon4 == null) {
                    ProgressInfo progressInfo11 = template.getProgressInfo();
                    icon4 = getIcon(progressInfo11 != null ? progressInfo11.getPicEnd() : null, sbn);
                }
                if (imageView2 != null) {
                    imageView2.setImageIcon(icon4);
                }
            } else if (new C0232d(51, 99).h(numValueOf3.intValue())) {
                ProgressInfo progressInfo12 = template.getProgressInfo();
                Icon icon5 = getIcon(progressInfo12 != null ? progressInfo12.getPicEndUnselected() : null, sbn);
                if (icon5 == null) {
                    ProgressInfo progressInfo13 = template.getProgressInfo();
                    icon5 = getIcon(progressInfo13 != null ? progressInfo13.getPicEnd() : null, sbn);
                }
                if (icon5 != null) {
                    if (imageView3 != null) {
                        imageView3.setVisibility(0);
                    }
                } else if (imageView3 != null) {
                    imageView3.setVisibility(8);
                }
                if (imageView3 != null) {
                    imageView3.setVisibility(icon5 != null ? 0 : 8);
                }
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                if (imageView2 != null) {
                    imageView2.setImageIcon(icon5);
                }
            } else {
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                ProgressInfo progressInfo14 = template.getProgressInfo();
                Icon icon6 = getIcon(progressInfo14 != null ? progressInfo14.getPicEnd() : null, sbn);
                if (icon6 == null) {
                    ProgressInfo progressInfo15 = template.getProgressInfo();
                    icon6 = getIcon(progressInfo15 != null ? progressInfo15.getPicEndUnselected() : null, sbn);
                }
                if (imageView3 != null) {
                    imageView3.setVisibility(icon6 != null ? 0 : 8);
                }
                if (imageView2 != null) {
                    imageView2.setImageIcon(icon6);
                }
            }
            ProgressInfo progressInfo16 = template.getProgressInfo();
            Icon icon7 = getIcon(progressInfo16 != null ? progressInfo16.getPicForward() : null, sbn);
            ImageView imageView4 = this.progressThumb;
            if (imageView4 != null) {
                imageView4.setVisibility(0);
                imageView4.setImageIcon(icon7);
            }
            setProgressColor(this.progressBar, numValueOf, numValueOf2);
            notifyDataChanged();
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_progress, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.progressbar_container) : null;
        View view2 = getView();
        this.progressThumb = view2 != null ? (ImageView) view2.findViewById(R.id.progress_icon) : null;
        registerCompressChanged(new AnonymousClass1());
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
        bind(template, sbn);
    }
}
