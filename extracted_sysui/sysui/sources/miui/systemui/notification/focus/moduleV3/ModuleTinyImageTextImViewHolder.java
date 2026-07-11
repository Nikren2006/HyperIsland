package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.ChatInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.model.TimerInfo;
import miuix.colorful.texteffect.HyperChronometer;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyImageTextImViewHolder extends ModuleViewHolder {
    private HyperChronometer chronometer;
    private View container;
    private TextView focusContent;
    private TextView focusTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyImageTextImViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void showProfilePicture(miui.systemui.notification.focus.model.Template r6, android.service.notification.StatusBarNotification r7) {
        /*
            r5 = this;
            boolean r0 = r5.isDark()
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L15
            miui.systemui.notification.focus.model.ChatInfo r0 = r6.getChatInfo()
            if (r0 == 0) goto L13
            java.lang.String r0 = r0.getPicProfileDark()
            goto L1f
        L13:
            r0 = r2
            goto L1f
        L15:
            miui.systemui.notification.focus.model.ChatInfo r0 = r6.getChatInfo()
            if (r0 == 0) goto L13
            java.lang.String r0 = r0.getPicProfile()
        L1f:
            miui.systemui.notification.focus.model.ChatInfo r1 = r6.getChatInfo()
            if (r1 == 0) goto L2b
            java.lang.String r1 = r1.getAppIconPkg()
            if (r1 != 0) goto L2f
        L2b:
            java.lang.String r1 = r7.getPackageName()
        L2f:
            android.graphics.drawable.Icon r0 = r5.getIcon(r0, r7)
            if (r0 == 0) goto L8b
            android.view.View r3 = r5.getView()
            if (r3 == 0) goto L48
            int r4 = com.android.systemui.miui.notification.R.id.focus_profile
            android.view.View r3 = r3.findViewById(r4)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            if (r3 == 0) goto L48
            r3.setImageIcon(r0)
        L48:
            miui.systemui.notification.focus.model.ChatInfo r6 = r6.getChatInfo()
            if (r6 == 0) goto L53
            java.lang.String r6 = r6.getAppIconPkg()
            goto L54
        L53:
            r6 = r2
        L54:
            android.graphics.drawable.Icon r6 = r5.getIcon(r6, r7)
            android.view.View r0 = r5.getView()
            if (r0 == 0) goto L67
            int r2 = com.android.systemui.miui.notification.R.id.focus_app_icon
            android.view.View r0 = r0.findViewById(r2)
            r2 = r0
            android.widget.ImageView r2 = (android.widget.ImageView) r2
        L67:
            if (r6 == 0) goto L6f
            if (r2 == 0) goto Lb4
            r2.setImageIcon(r6)
            goto Lb4
        L6f:
            if (r2 == 0) goto Lb4
            miui.systemui.dynamicisland.DynamicIslandUtils r6 = miui.systemui.dynamicisland.DynamicIslandUtils.INSTANCE
            android.content.Context r5 = r5.getCtx()
            android.os.UserHandle r7 = r7.getUser()
            int r7 = r7.getIdentifier()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            android.graphics.drawable.Drawable r5 = r6.getAppIcon(r5, r1, r7)
            r2.setImageDrawable(r5)
            goto Lb4
        L8b:
            android.view.View r6 = r5.getView()
            if (r6 == 0) goto Lb4
            int r0 = com.android.systemui.miui.notification.R.id.focus_profile
            android.view.View r6 = r6.findViewById(r0)
            android.widget.ImageView r6 = (android.widget.ImageView) r6
            if (r6 == 0) goto Lb4
            miui.systemui.dynamicisland.DynamicIslandUtils r0 = miui.systemui.dynamicisland.DynamicIslandUtils.INSTANCE
            android.content.Context r5 = r5.getCtx()
            android.os.UserHandle r7 = r7.getUser()
            int r7 = r7.getIdentifier()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            android.graphics.drawable.Drawable r5 = r0.getAppIcon(r5, r1, r7)
            r6.setImageDrawable(r5)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleTinyImageTextImViewHolder.showProfilePicture(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification):void");
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
        } else {
            HyperChronometer hyperChronometer3 = this.chronometer;
            if (hyperChronometer3 != null) {
                hyperChronometer3.setVisibility(8);
            }
            TextView textView5 = this.focusContent;
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
            TextView textView6 = this.focusContent;
            if (textView6 != null) {
                textView6.setText(Html.fromHtml(getContent()));
            }
            Integer contentColor2 = getContentColor();
            if (contentColor2 != null) {
                int iIntValue3 = contentColor2.intValue();
                TextView textView7 = this.focusContent;
                if (textView7 != null) {
                    textView7.setTextColor(iIntValue3);
                }
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
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_image_text_im, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_image_text_im) : null;
        View view2 = getView();
        this.focusTitle = view2 != null ? (TextView) view2.findViewById(R.id.focus_title) : null;
        View view3 = getView();
        this.chronometer = view3 != null ? (HyperChronometer) view3.findViewById(R.id.chronometer) : null;
        View view4 = getView();
        this.focusContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_content) : null;
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
