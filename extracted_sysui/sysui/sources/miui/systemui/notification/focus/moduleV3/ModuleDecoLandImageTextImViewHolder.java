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
import miui.systemui.notification.focus.model.ChatInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.model.TimerInfo;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoLandImageTextImViewHolder extends ModuleViewHolder {
    private HyperChronometer chronometer;
    private View container;
    private TextView focusContent;
    private TextView focusTitle;
    private TextView spaceTextViewCenter1;
    private TextView spaceTextViewCenter2;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoLandImageTextImViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void showProfilePicture(Template template, StatusBarNotification statusBarNotification) {
        View view;
        ImageView imageView;
        String picProfile = null;
        if (isDark()) {
            ChatInfo chatInfo = template.getChatInfo();
            if (chatInfo != null) {
                picProfile = chatInfo.getPicProfileDark();
            }
        } else {
            ChatInfo chatInfo2 = template.getChatInfo();
            if (chatInfo2 != null) {
                picProfile = chatInfo2.getPicProfile();
            }
        }
        Icon icon = getIcon(picProfile, statusBarNotification);
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
        initTextAndColor(template.getChatInfo());
        ChatInfo chatInfo = template.getChatInfo();
        initTimerData(chatInfo != null ? chatInfo.getTimerInfo() : null);
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
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
        if (TextUtils.isEmpty(getContent())) {
            HyperChronometer hyperChronometer = this.chronometer;
            if (hyperChronometer != null) {
                hyperChronometer.setVisibility(0);
            }
            TextView textView4 = this.focusContent;
            if (textView4 != null) {
                textView4.setVisibility(8);
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                HyperChronometer hyperChronometer2 = this.chronometer;
                if (hyperChronometer2 != null) {
                    hyperChronometer2.setTextColor(iIntValue2);
                }
            }
            TextView textView5 = this.spaceTextViewCenter1;
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
            TextView textView6 = this.spaceTextViewCenter2;
            if (textView6 != null) {
                textView6.setVisibility(0);
            }
        } else {
            HyperChronometer hyperChronometer3 = this.chronometer;
            if (hyperChronometer3 != null) {
                hyperChronometer3.setVisibility(8);
            }
            TextView textView7 = this.focusContent;
            if (textView7 != null) {
                textView7.setVisibility(0);
            }
            TextView textView8 = this.focusContent;
            if (textView8 != null) {
                textView8.setText(Html.fromHtml(getContent()));
            }
            Integer contentColor2 = getContentColor();
            if (contentColor2 != null) {
                int iIntValue3 = contentColor2.intValue();
                TextView textView9 = this.focusContent;
                if (textView9 != null) {
                    textView9.setTextColor(iIntValue3);
                }
            }
            TextView textView10 = this.spaceTextViewCenter1;
            if (textView10 != null) {
                textView10.setVisibility(8);
            }
            TextView textView11 = this.spaceTextViewCenter2;
            if (textView11 != null) {
                textView11.setVisibility(8);
            }
        }
        ChatInfo chatInfo2 = template.getChatInfo();
        if ((chatInfo2 != null ? chatInfo2.getTimerInfo() : null) != null) {
            ModuleViewHolder.setTimerData$default(this, 0, sbn, 1, null);
        }
        showProfilePicture(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void checkParams(Template template) throws FocusParamsException {
        TimerInfo timerInfo;
        TimerInfo timerInfo2;
        kotlin.jvm.internal.n.g(template, "template");
        super.checkParams(template);
        ChatInfo chatInfo = template.getChatInfo();
        Integer numValueOf = null;
        if (TextUtils.isEmpty(chatInfo != null ? chatInfo.getContent() : null)) {
            ChatInfo chatInfo2 = template.getChatInfo();
            if (chatInfo2 != null && (timerInfo2 = chatInfo2.getTimerInfo()) != null) {
                numValueOf = Integer.valueOf(timerInfo2.getTimerType());
            }
            if (numValueOf == null || ((timerInfo = template.getChatInfo().getTimerInfo()) != null && timerInfo.getTimerType() == 0)) {
                throw new FocusParamsException("content is empty");
            }
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_land_image_text_im, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_image_text_im) : null;
        View view2 = getView();
        this.focusTitle = view2 != null ? (TextView) view2.findViewById(R.id.focus_title) : null;
        View view3 = getView();
        this.chronometer = view3 != null ? (HyperChronometer) view3.findViewById(R.id.chronometer) : null;
        View view4 = getView();
        this.focusContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_content) : null;
        View view5 = getView();
        this.spaceTextViewCenter1 = view5 != null ? (TextView) view5.findViewById(R.id.spaceTextViewCenter1) : null;
        View view6 = getView();
        this.spaceTextViewCenter2 = view6 != null ? (TextView) view6.findViewById(R.id.spaceTextViewCenter2) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getChatInfo());
        bind(template, sbn);
    }
}
