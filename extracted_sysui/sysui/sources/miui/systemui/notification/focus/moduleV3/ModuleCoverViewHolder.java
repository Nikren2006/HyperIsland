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
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.CoverInfo;
import miui.systemui.notification.focus.model.Template;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleCoverViewHolder extends ModuleViewHolder {
    private View container;
    private TimerTextEffectView focusContent;
    private TimerTextEffectView focusSubContent;
    private TimerTextEffectView focusTitle;
    private final boolean island;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleCoverViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    private final void showCover(Template template, StatusBarNotification statusBarNotification) {
        View view;
        ImageView imageView;
        CoverInfo coverInfo = template.getCoverInfo();
        Icon icon = getIcon(coverInfo != null ? coverInfo.getPicCover() : null, statusBarNotification);
        if (icon == null || (view = getView()) == null || (imageView = (ImageView) view.findViewById(R.id.focus_profile)) == null) {
            return;
        }
        imageView.setImageIcon(icon);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getCoverInfo());
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView = this.focusTitle;
        if (timerTextEffectView != null) {
            timerTextEffectView.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView2 = this.focusTitle;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
        }
        Integer titleColor = getTitleColor();
        if (titleColor != null) {
            int iIntValue = titleColor.intValue();
            TimerTextEffectView timerTextEffectView3 = this.focusTitle;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.updateTextWithNewAppearance(Html.fromHtml(getTitle()), Integer.valueOf(iIntValue));
            }
        }
        if (!TextUtils.isEmpty(getContent())) {
            TimerTextEffectView timerTextEffectView4 = this.focusContent;
            if (timerTextEffectView4 != null) {
                timerTextEffectView4.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView5 = this.focusContent;
            if (timerTextEffectView5 != null) {
                timerTextEffectView5.setText(Html.fromHtml(getContent()), TextView.BufferType.SPANNABLE);
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                TimerTextEffectView timerTextEffectView6 = this.focusContent;
                if (timerTextEffectView6 != null) {
                    timerTextEffectView6.updateTextWithNewAppearance(Html.fromHtml(getContent()), Integer.valueOf(iIntValue2));
                }
            }
        }
        if (TextUtils.isEmpty(getSubContent())) {
            TimerTextEffectView timerTextEffectView7 = this.focusContent;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView8 = this.focusSubContent;
            if (timerTextEffectView8 != null) {
                timerTextEffectView8.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView9 = this.focusSubContent;
            if (timerTextEffectView9 != null) {
                timerTextEffectView9.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView10 = this.focusSubContent;
            if (timerTextEffectView10 != null) {
                timerTextEffectView10.setText(Html.fromHtml(getSubContent()), TextView.BufferType.SPANNABLE);
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue3 = subContentColor.intValue();
                TimerTextEffectView timerTextEffectView11 = this.focusSubContent;
                if (timerTextEffectView11 != null) {
                    timerTextEffectView11.updateTextWithNewAppearance(Html.fromHtml(getSubContent()), Integer.valueOf(iIntValue3));
                }
            }
        }
        showCover(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void checkParams(Template template) throws FocusParamsException {
        kotlin.jvm.internal.n.g(template, "template");
        super.checkParams(template);
        CoverInfo coverInfo = template.getCoverInfo();
        if (TextUtils.isEmpty(coverInfo != null ? coverInfo.getTitle() : null)) {
            throw new FocusParamsException("title is empty");
        }
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_cover, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_cover) : null;
        View view2 = getView();
        this.focusTitle = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.focus_title) : null;
        View view3 = getView();
        this.focusContent = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.focus_content) : null;
        View view4 = getView();
        this.focusSubContent = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.focus_sub_content) : null;
        TimerTextEffectView timerTextEffectView = this.focusTitle;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.focusTitle;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView3 = this.focusContent;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.focusContent;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView5 = this.focusSubContent;
        if (timerTextEffectView5 != null) {
            timerTextEffectView5.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView6 = this.focusSubContent;
        if (timerTextEffectView6 == null) {
            return;
        }
        timerTextEffectView6.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getCoverInfo());
        bind(template, sbn);
    }
}
