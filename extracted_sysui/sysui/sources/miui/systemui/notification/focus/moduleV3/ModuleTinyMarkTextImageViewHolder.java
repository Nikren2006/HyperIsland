package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import miui.systemui.notification.focus.model.PicInfo;
import miui.systemui.notification.focus.model.Template;
import miuix.colorful.texteffect.CharChangeProcessor;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyMarkTextImageViewHolder extends ModuleViewHolder {
    private View buttonContainer;
    private View container;
    private HyperChronometer focusTitle;
    private final boolean island;
    private ImageView mark;
    private String pic;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyMarkTextImageViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    private final void showMarkIcon(StatusBarNotification statusBarNotification) {
        Icon icon = getIcon(this.pic, statusBarNotification);
        View view = getView();
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.focus_mark_small_icon) : null;
        if (icon == null) {
            if (imageView == null) {
                return;
            }
            imageView.setVisibility(8);
        } else {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView != null) {
                imageView.setImageIcon(icon);
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getPicInfo());
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        if (TextUtils.isEmpty(getTitle())) {
            HyperChronometer hyperChronometer = this.focusTitle;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(8);
            }
        } else {
            HyperChronometer hyperChronometer2 = this.focusTitle;
            if (hyperChronometer2 != null) {
                hyperChronometer2.setVisibility(0);
            }
            HyperChronometer hyperChronometer3 = this.focusTitle;
            if (hyperChronometer3 != null) {
                hyperChronometer3.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                HyperChronometer hyperChronometer4 = this.focusTitle;
                if (hyperChronometer4 != null) {
                    hyperChronometer4.updateTextWithNewAppearance(Html.fromHtml(getTitle()), Integer.valueOf(iIntValue));
                }
            }
        }
        String pic = null;
        if (isDark()) {
            PicInfo picInfo = template.getPicInfo();
            if (picInfo != null) {
                pic = picInfo.getPicDark();
            }
        } else {
            PicInfo picInfo2 = template.getPicInfo();
            if (picInfo2 != null) {
                pic = picInfo2.getPic();
            }
        }
        this.pic = pic;
        View view2 = this.container;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        showMarkIcon(sbn);
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setModule(module);
        setView(LayoutInflater.from(getCtx()).inflate(R.layout.focus_notification_module_tiny_mark_text_image, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_mark) : null;
        View view2 = getView();
        this.focusTitle = view2 != null ? (HyperChronometer) view2.findViewById(R.id.focus_title) : null;
        View view3 = getView();
        this.mark = view3 != null ? (ImageView) view3.findViewById(R.id.focus_mark_small_icon) : null;
        View view4 = getView();
        this.buttonContainer = view4 != null ? view4.findViewById(R.id.focus_button_container) : null;
        HyperChronometer hyperChronometer = this.focusTitle;
        if (hyperChronometer != null) {
            hyperChronometer.setTextChangeProcessor(new CharChangeProcessor());
        }
        HyperChronometer hyperChronometer2 = this.focusTitle;
        if (hyperChronometer2 == null) {
            return;
        }
        hyperChronometer2.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
