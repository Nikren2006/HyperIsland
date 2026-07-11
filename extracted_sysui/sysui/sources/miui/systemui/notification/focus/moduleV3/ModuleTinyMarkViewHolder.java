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
public final class ModuleTinyMarkViewHolder extends ModuleViewHolder {
    private View buttonContainer;
    private View container;
    private ImageView mark;
    private String pic;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyMarkViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void showMarkIcon(StatusBarNotification statusBarNotification, ImageView imageView) {
        Icon icon = getIcon(this.pic, statusBarNotification);
        if (icon != null) {
            imageView.setVisibility(0);
            imageView.setImageIcon(icon);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
    
        if (r0.equals(miui.systemui.notification.focus.Const.Module.MODULE_MARK_3) == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
    
        if (r0.equals(miui.systemui.notification.focus.Const.Module.MODULE_MARK_2) == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0057, code lost:
    
        r9 = getView();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        if (r9 == null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005d, code lost:
    
        r2 = (android.widget.ImageView) r9.findViewById(com.android.systemui.miui.notification.R.id.focus_mark_large_icon);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
    
        if (r2 == null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
    
        showMarkIcon(r10, r2);
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0020  */
    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bind(miui.systemui.notification.focus.model.Template r9, android.service.notification.StatusBarNotification r10) throws java.net.URISyntaxException {
        /*
            r8 = this;
            java.lang.String r0 = "template"
            kotlin.jvm.internal.n.g(r9, r0)
            java.lang.String r0 = "sbn"
            kotlin.jvm.internal.n.g(r10, r0)
            super.bind(r9, r10)
            boolean r0 = r8.isDark()
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L22
            miui.systemui.notification.focus.model.PicInfo r0 = r9.getPicInfo()
            if (r0 == 0) goto L20
            java.lang.String r0 = r0.getPicDark()
            goto L2c
        L20:
            r0 = r2
            goto L2c
        L22:
            miui.systemui.notification.focus.model.PicInfo r0 = r9.getPicInfo()
            if (r0 == 0) goto L20
            java.lang.String r0 = r0.getPic()
        L2c:
            r8.pic = r0
            android.view.View r0 = r8.container
            r1 = 0
            if (r0 != 0) goto L34
            goto L37
        L34:
            r0.setVisibility(r1)
        L37:
            java.lang.String r0 = r8.getModule()
            if (r0 == 0) goto L91
            int r3 = r0.hashCode()
            switch(r3) {
                case -1211998216: goto L6c;
                case -1211998215: goto L4e;
                case -1211998214: goto L45;
                default: goto L44;
            }
        L44:
            goto L91
        L45:
            java.lang.String r3 = "moduleMark3"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L57
            goto L91
        L4e:
            java.lang.String r3 = "moduleMark2"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L57
            goto L91
        L57:
            android.view.View r9 = r8.getView()
            if (r9 == 0) goto L66
            int r0 = com.android.systemui.miui.notification.R.id.focus_mark_large_icon
            android.view.View r9 = r9.findViewById(r0)
            r2 = r9
            android.widget.ImageView r2 = (android.widget.ImageView) r2
        L66:
            if (r2 == 0) goto La2
            r8.showMarkIcon(r10, r2)
            goto La2
        L6c:
            java.lang.String r2 = "moduleMark1"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L75
            goto L91
        L75:
            android.widget.ImageView r9 = r8.mark
            if (r9 != 0) goto L7a
            goto L7d
        L7a:
            r9.setVisibility(r1)
        L7d:
            android.widget.ImageView r9 = r8.mark
            if (r9 == 0) goto La2
            java.lang.String r0 = r8.pic
            android.graphics.drawable.Icon r0 = r8.getIcon(r0, r10)
            if (r0 == 0) goto L8d
            r8.showMarkIcon(r10, r9)
            goto La2
        L8d:
            r8.showAppIcon(r9, r10)
            goto La2
        L91:
            android.view.View r0 = r8.buttonContainer
            if (r0 != 0) goto L96
            goto L99
        L96:
            r0.setVisibility(r1)
        L99:
            r6 = 4
            r7 = 0
            r5 = 0
            r2 = r8
            r3 = r9
            r4 = r10
            miui.systemui.notification.focus.moduleV3.ModuleViewHolder.setActionData$default(r2, r3, r4, r5, r6, r7)
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleTinyMarkViewHolder.bind(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification):void");
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
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_tiny_mark_3
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
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_tiny_mark_2
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
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_tiny_mark_4
            android.view.ViewGroup r1 = r2.getRootView()
            android.view.View r3 = r3.inflate(r0, r1)
            r2.setView(r3)
            goto L84
        L6f:
            android.content.Context r3 = r2.getCtx()
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r0 = com.android.systemui.miui.notification.R.layout.focus_notification_module_tiny_mark_1
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
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleTinyMarkViewHolder.initView(java.lang.String):void");
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        bind(template, sbn);
    }
}
