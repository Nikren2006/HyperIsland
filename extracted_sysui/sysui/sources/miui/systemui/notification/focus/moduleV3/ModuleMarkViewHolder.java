package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.net.URISyntaxException;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleMarkViewHolder extends ModuleViewHolder {
    private View buttonContainer;
    private View container;
    private final boolean island;
    private ImageView mark;
    private String pic;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleMarkViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    private final void showMarkIcon(StatusBarNotification statusBarNotification, ImageView imageView) {
        Icon icon = getIcon(this.pic, statusBarNotification);
        if (icon != null) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView != null) {
                imageView.setImageIcon(icon);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0020  */
    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bind(miui.systemui.notification.focus.model.Template r11, android.service.notification.StatusBarNotification r12) throws java.net.URISyntaxException {
        /*
            r10 = this;
            java.lang.String r0 = "template"
            kotlin.jvm.internal.n.g(r11, r0)
            java.lang.String r0 = "sbn"
            kotlin.jvm.internal.n.g(r12, r0)
            super.bind(r11, r12)
            boolean r0 = r10.isDark()
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L22
            miui.systemui.notification.focus.model.PicInfo r0 = r11.getPicInfo()
            if (r0 == 0) goto L20
            java.lang.String r0 = r0.getPicDark()
            goto L2c
        L20:
            r0 = r1
            goto L2c
        L22:
            miui.systemui.notification.focus.model.PicInfo r0 = r11.getPicInfo()
            if (r0 == 0) goto L20
            java.lang.String r0 = r0.getPic()
        L2c:
            r10.pic = r0
            android.view.View r0 = r10.container
            r3 = 0
            if (r0 != 0) goto L34
            goto L37
        L34:
            r0.setVisibility(r3)
        L37:
            java.lang.String r0 = r10.getModule()
            java.lang.String r4 = "moduleMark1"
            boolean r0 = kotlin.jvm.internal.n.c(r0, r4)
            if (r0 == 0) goto L5f
            android.widget.ImageView r11 = r10.mark
            if (r11 != 0) goto L48
            goto L4b
        L48:
            r11.setVisibility(r3)
        L4b:
            android.widget.ImageView r11 = r10.mark
            if (r11 == 0) goto L9e
            java.lang.String r0 = r10.pic
            android.graphics.drawable.Icon r0 = r10.getIcon(r0, r12)
            if (r0 == 0) goto L5b
            r10.showMarkIcon(r12, r11)
            goto L9e
        L5b:
            r10.showAppIcon(r11, r12)
            goto L9e
        L5f:
            java.lang.String r0 = r10.getModule()
            java.lang.String r4 = "moduleMark2"
            boolean r0 = kotlin.jvm.internal.n.c(r0, r4)
            if (r0 != 0) goto L8a
            java.lang.String r0 = r10.getModule()
            java.lang.String r4 = "moduleMark3"
            boolean r0 = kotlin.jvm.internal.n.c(r0, r4)
            if (r0 == 0) goto L78
            goto L8a
        L78:
            android.view.View r0 = r10.buttonContainer
            if (r0 != 0) goto L7d
            goto L80
        L7d:
            r0.setVisibility(r3)
        L80:
            r8 = 4
            r9 = 0
            r7 = 0
            r4 = r10
            r5 = r11
            r6 = r12
            miui.systemui.notification.focus.moduleV3.ModuleViewHolder.setActionData$default(r4, r5, r6, r7, r8, r9)
            goto L9e
        L8a:
            android.view.View r11 = r10.getView()
            if (r11 == 0) goto L99
            int r0 = com.android.systemui.miui.notification.R.id.focus_mark_large_icon
            android.view.View r11 = r11.findViewById(r0)
            r1 = r11
            android.widget.ImageView r1 = (android.widget.ImageView) r1
        L99:
            if (r1 == 0) goto L9e
            r10.showMarkIcon(r12, r1)
        L9e:
            android.content.Context r11 = r10.getCtx()
            boolean r11 = miui.systemui.util.CommonUtils.isLayoutRtl(r11)
            if (r11 == 0) goto Lb3
            android.view.View r10 = r10.getView()
            if (r10 != 0) goto Laf
            goto Lbd
        Laf:
            r10.setLayoutDirection(r2)
            goto Lbd
        Lb3:
            android.view.View r10 = r10.getView()
            if (r10 != 0) goto Lba
            goto Lbd
        Lba:
            r10.setLayoutDirection(r3)
        Lbd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleMarkViewHolder.bind(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification):void");
    }

    public final boolean getIsland() {
        return this.island;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0059  */
    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initView(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "module"
            kotlin.jvm.internal.n.g(r3, r0)
            super.initView(r3)
            r2.setModule(r3)
            int r0 = r3.hashCode()
            switch(r0) {
                case -1211998216: goto L51;
                case -1211998215: goto L32;
                case -1211998214: goto L13;
                default: goto L12;
            }
        L12:
            goto L59
        L13:
            java.lang.String r0 = "moduleMark3"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L1c
            goto L59
        L1c:
            android.content.Context r3 = r2.getCtx()
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_mark_3
            android.view.ViewGroup r1 = r2.getRootView()
            android.view.View r3 = r3.inflate(r0, r1)
            r2.setView(r3)
            goto L84
        L32:
            java.lang.String r0 = "moduleMark2"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L3b
            goto L59
        L3b:
            android.content.Context r3 = r2.getCtx()
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_mark_2
            android.view.ViewGroup r1 = r2.getRootView()
            android.view.View r3 = r3.inflate(r0, r1)
            r2.setView(r3)
            goto L84
        L51:
            java.lang.String r0 = "moduleMark1"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L6f
        L59:
            android.content.Context r3 = r2.getCtx()
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_mark_4
            android.view.ViewGroup r1 = r2.getRootView()
            android.view.View r3 = r3.inflate(r0, r1)
            r2.setView(r3)
            goto L84
        L6f:
            android.content.Context r3 = r2.getCtx()
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_mark_1
            android.view.ViewGroup r1 = r2.getRootView()
            android.view.View r3 = r3.inflate(r0, r1)
            r2.setView(r3)
        L84:
            android.view.View r3 = r2.getView()
            r0 = 0
            if (r3 == 0) goto L92
            int r1 = com.android.systemui.miui.notification.R.id.focus_container_module_mark
            android.view.View r3 = r3.findViewById(r1)
            goto L93
        L92:
            r3 = r0
        L93:
            r2.container = r3
            android.view.View r3 = r2.getView()
            if (r3 == 0) goto La4
            int r1 = com.android.systemui.miui.notification.R.id.focus_mark_small_icon
            android.view.View r3 = r3.findViewById(r1)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            goto La5
        La4:
            r3 = r0
        La5:
            r2.mark = r3
            android.view.View r3 = r2.getView()
            if (r3 == 0) goto Lb3
            int r0 = com.android.systemui.miui.notification.R.id.focus_button_container
            android.view.View r0 = r3.findViewById(r0)
        Lb3:
            r2.buttonContainer = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleMarkViewHolder.initView(java.lang.String):void");
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
