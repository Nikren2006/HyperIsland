package miui.systemui.notification.focus.templateV3;

import G0.a;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import g1.AbstractC0369g;
import g1.E;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.model.IconTextInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateBuilderV3 {
    public static final Companion Companion = new Companion(null);
    public static final String DEBUG_TAG = "EmptyNotif";
    public static final String TAG = "TemplateBuilderV3";
    private final Context context;
    private ModuleViewHolderAdapter focusAdapter;
    private ModuleViewHolderAdapter focusDarkAdapter;
    private View focusDarkLayout;
    private View focusLayout;
    private ModuleViewHolderAdapter focusModalAdapter;
    private ModuleViewHolderAdapter focusModalDarkAdapter;
    private View focusModalDarkLayout;
    private View focusModalLayout;
    private boolean iconTextInfoHasIcon;
    private boolean isFlipDevice;
    private ModuleViewHolderAdapter islandAdapter;
    private ModuleViewHolderAdapter islandFakeAdapter;
    private View islandLayout;
    private View islandLayoutFake;
    private String moduleA;
    private String moduleC;
    private String moduleD;
    private final a moduleViewHolderAdapterProvider;
    private final Map<Integer, View> viewMap;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        TemplateBuilderV3 create(boolean z2);
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateBuilderV3$setAreaViewVisible$1, reason: invalid class name */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateBuilderV3$setAreaViewVisible$1", f = "TemplateBuilderV3.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ int $areaId;
        final /* synthetic */ int $visible;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i2, int i3, d dVar) {
            super(2, dVar);
            this.$areaId = i2;
            this.$visible = i3;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return TemplateBuilderV3.this.new AnonymousClass1(this.$areaId, this.$visible, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            try {
                View view = TemplateBuilderV3.this.islandLayout;
                FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(this.$areaId) : null;
                if (frameLayout != null) {
                    frameLayout.setVisibility(this.$visible);
                }
                View view2 = TemplateBuilderV3.this.islandLayoutFake;
                FrameLayout frameLayout2 = view2 != null ? (FrameLayout) view2.findViewById(this.$areaId) : null;
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(this.$visible);
                }
                View view3 = TemplateBuilderV3.this.focusLayout;
                FrameLayout frameLayout3 = view3 != null ? (FrameLayout) view3.findViewById(this.$areaId) : null;
                if (frameLayout3 != null) {
                    frameLayout3.setVisibility(this.$visible);
                }
                View view4 = TemplateBuilderV3.this.focusDarkLayout;
                FrameLayout frameLayout4 = view4 != null ? (FrameLayout) view4.findViewById(this.$areaId) : null;
                if (frameLayout4 != null) {
                    frameLayout4.setVisibility(this.$visible);
                }
                View view5 = TemplateBuilderV3.this.focusModalLayout;
                FrameLayout frameLayout5 = view5 != null ? (FrameLayout) view5.findViewById(this.$areaId) : null;
                if (frameLayout5 != null) {
                    frameLayout5.setVisibility(this.$visible);
                }
                View view6 = TemplateBuilderV3.this.focusModalDarkLayout;
                FrameLayout frameLayout6 = view6 != null ? (FrameLayout) view6.findViewById(this.$areaId) : null;
                if (frameLayout6 != null) {
                    frameLayout6.setVisibility(this.$visible);
                }
            } catch (Exception e2) {
                Log.d(TemplateBuilderV3.TAG, e2.toString());
            }
            return s.f314a;
        }
    }

    public TemplateBuilderV3(Context context, boolean z2, a moduleViewHolderAdapterProvider) {
        n.g(context, "context");
        n.g(moduleViewHolderAdapterProvider, "moduleViewHolderAdapterProvider");
        this.context = context;
        this.isFlipDevice = z2;
        this.moduleViewHolderAdapterProvider = moduleViewHolderAdapterProvider;
        this.viewMap = new LinkedHashMap();
        this.moduleA = "";
        this.moduleC = "";
        this.moduleD = "";
        this.iconTextInfoHasIcon = true;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i2 = R.layout.focus_notification_template_standard;
        this.islandLayout = layoutInflaterFrom.inflate(i2, (ViewGroup) null);
        this.islandLayoutFake = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        this.focusLayout = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        this.focusDarkLayout = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        View viewInflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        int i3 = R.id.dynamic_island_modal_tag;
        Boolean bool = Boolean.TRUE;
        viewInflate.setTag(i3, bool);
        this.focusModalLayout = viewInflate;
        View viewInflate2 = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        viewInflate2.setTag(i3, bool);
        this.focusModalDarkLayout = viewInflate2;
    }

    private final void addFocusDarkView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        View view = this.focusDarkLayout;
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(i2) : null;
        if (this.focusDarkAdapter == null) {
            this.focusDarkAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.focusDarkAdapter;
        if (moduleViewHolderAdapter != null) {
            boolean z3 = this.isFlipDevice;
            n.e(frameLayout, "null cannot be cast to non-null type android.view.ViewGroup");
            moduleViewHolderAdapter.createModuleViewHolder(template, statusBarNotification, str, (384 & 8) != 0 ? false : true, (384 & 16) != 0 ? true : z2, (384 & 32) != 0 ? false : z3, frameLayout, (384 & 128) != 0 ? false : false, (384 & 256) != 0 ? false : false);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.focusDarkAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addFocusModalDarkView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        View view = this.focusModalDarkLayout;
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(i2) : null;
        if (this.focusModalDarkAdapter == null) {
            this.focusModalDarkAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.focusModalDarkAdapter;
        if (moduleViewHolderAdapter != null) {
            boolean z3 = this.isFlipDevice;
            n.e(frameLayout, "null cannot be cast to non-null type android.view.ViewGroup");
            moduleViewHolderAdapter.createModuleViewHolder(template, statusBarNotification, str, (384 & 8) != 0 ? false : true, (384 & 16) != 0 ? true : z2, (384 & 32) != 0 ? false : z3, frameLayout, (384 & 128) != 0 ? false : false, (384 & 256) != 0 ? false : false);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.focusModalDarkAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addFocusModalView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        View view = this.focusModalLayout;
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(i2) : null;
        if (this.focusModalAdapter == null) {
            this.focusModalAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.focusModalAdapter;
        if (moduleViewHolderAdapter != null) {
            boolean z3 = this.isFlipDevice;
            n.e(frameLayout, "null cannot be cast to non-null type android.view.ViewGroup");
            moduleViewHolderAdapter.createModuleViewHolder(template, statusBarNotification, str, (384 & 8) != 0 ? false : false, (384 & 16) != 0 ? true : z2, (384 & 32) != 0 ? false : z3, frameLayout, (384 & 128) != 0 ? false : false, (384 & 256) != 0 ? false : false);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.focusModalAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addFocusView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        View view = this.focusLayout;
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(i2) : null;
        if (this.focusAdapter == null) {
            this.focusAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.focusAdapter;
        if (moduleViewHolderAdapter != null) {
            boolean z3 = this.isFlipDevice;
            n.e(frameLayout, "null cannot be cast to non-null type android.view.ViewGroup");
            moduleViewHolderAdapter.createModuleViewHolder(template, statusBarNotification, str, (384 & 8) != 0 ? false : false, (384 & 16) != 0 ? true : z2, (384 & 32) != 0 ? false : z3, frameLayout, (384 & 128) != 0 ? false : false, (384 & 256) != 0 ? false : false);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.focusAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addIsLandView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        if (n.c(str, Const.Module.MODULE_BACKGROUND)) {
            return;
        }
        View view = this.islandLayout;
        FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(i2) : null;
        if (this.islandAdapter == null) {
            this.islandAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.islandAdapter;
        if (moduleViewHolderAdapter != null) {
            boolean z3 = this.isFlipDevice;
            n.e(frameLayout, "null cannot be cast to non-null type android.view.ViewGroup");
            moduleViewHolderAdapter.createModuleViewHolder(template, statusBarNotification, str, (384 & 8) != 0 ? false : true, (384 & 16) != 0 ? true : z2, (384 & 32) != 0 ? false : z3, frameLayout, (384 & 128) != 0 ? false : true, (384 & 256) != 0 ? false : false);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.islandAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addIsLandViewFake(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        if (n.c(str, Const.Module.MODULE_BACKGROUND)) {
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.islandLayoutFake.findViewById(i2);
        if (this.islandFakeAdapter == null) {
            this.islandFakeAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.islandFakeAdapter;
        if (moduleViewHolderAdapter != null) {
            boolean z3 = this.isFlipDevice;
            n.d(frameLayout);
            moduleViewHolderAdapter.createModuleViewHolder(template, statusBarNotification, str, true, z2, z3, frameLayout, true, true);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.islandFakeAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final boolean isSameElement(String str, Template template) {
        boolean z2 = false;
        if (n.c(str, Const.Module.MODULE_NEW_IMAGE_TEXT)) {
            boolean z3 = this.iconTextInfoHasIcon;
            IconTextInfo iconTextInfo = template.getIconTextInfo();
            if (z3 != ((iconTextInfo != null ? iconTextInfo.getAnimIconInfo() : null) != null)) {
                z2 = true;
            }
        }
        return !z2;
    }

    public final TemplateBuilderV3 addModuleView(int i2, String moduleType, boolean z2, Template template, StatusBarNotification sbn) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        n.g(sbn, "sbn");
        if (i2 == R.id.area_a) {
            if (n.c(moduleType, Const.Module.MODULE_NEW_IMAGE_TEXT)) {
                IconTextInfo iconTextInfo = template.getIconTextInfo();
                this.iconTextInfoHasIcon = (iconTextInfo != null ? iconTextInfo.getAnimIconInfo() : null) != null;
            }
            this.moduleA = moduleType;
        } else if (i2 == R.id.area_c) {
            this.moduleC = moduleType;
        } else if (i2 == R.id.area_d) {
            this.moduleD = moduleType;
        }
        addIsLandView(i2, moduleType, z2, template, sbn);
        addIsLandViewFake(i2, moduleType, z2, template, sbn);
        addFocusView(i2, moduleType, z2, template, sbn);
        addFocusDarkView(i2, moduleType, z2, template, sbn);
        addFocusModalView(i2, moduleType, z2, template, sbn);
        addFocusModalDarkView(i2, moduleType, z2, template, sbn);
        return this;
    }

    public final void buildView(FocusNotificationContent focusNotificationContent, Template template) {
        n.g(focusNotificationContent, "focusNotificationContent");
        n.g(template, "template");
        Log.d(DEBUG_TAG, "buildView: " + this.focusLayout + "   " + Thread.currentThread().getName());
        focusNotificationContent.setIslandExpandedView(this.islandLayout);
        focusNotificationContent.setIslandExpandedViewFake(this.islandLayoutFake);
        focusNotificationContent.setFocusNotification(this.focusLayout);
        focusNotificationContent.setFocusNotificationDark(this.focusDarkLayout);
        focusNotificationContent.setFocusNotificationModal(this.focusModalLayout);
        focusNotificationContent.setFocusNotificationDarkModal(this.focusModalDarkLayout);
    }

    public final boolean isSameModuleA(String moduleType, Template template) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        return n.c(moduleType, this.moduleA) && isSameElement(moduleType, template);
    }

    public final boolean isSameModuleC(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleC);
    }

    public final boolean isSameModuleD(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleD);
    }

    public final TemplateBuilderV3 removeModuleView(String moduleType) {
        n.g(moduleType, "moduleType");
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.islandAdapter;
        if (moduleViewHolderAdapter != null) {
            moduleViewHolderAdapter.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.islandFakeAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter3 = this.focusAdapter;
        if (moduleViewHolderAdapter3 != null) {
            moduleViewHolderAdapter3.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter4 = this.focusDarkAdapter;
        if (moduleViewHolderAdapter4 != null) {
            moduleViewHolderAdapter4.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter5 = this.focusModalAdapter;
        if (moduleViewHolderAdapter5 != null) {
            moduleViewHolderAdapter5.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter6 = this.focusModalDarkAdapter;
        if (moduleViewHolderAdapter6 != null) {
            moduleViewHolderAdapter6.removeView(moduleType);
        }
        return this;
    }

    public final void removeView(FocusNotificationContent focusNotificationContent) {
        n.g(focusNotificationContent, "focusNotificationContent");
        Log.d(DEBUG_TAG, "removeView: " + Thread.currentThread().getName());
        focusNotificationContent.setIslandExpandedView((View) null);
        focusNotificationContent.setIslandExpandedViewFake((View) null);
        focusNotificationContent.setFocusNotification((View) null);
        focusNotificationContent.setFocusNotificationDark((View) null);
        try {
            Map focusNotificationViewMap = focusNotificationContent.getFocusNotificationViewMap();
            if (focusNotificationViewMap != null) {
                focusNotificationViewMap.clear();
            }
            focusNotificationContent.setFocusNotificationViewMap((Map) null);
        } catch (Throwable unused) {
        }
        focusNotificationContent.setFocusNotificationModal((View) null);
        focusNotificationContent.setFocusNotificationDarkModal((View) null);
        this.islandLayout = null;
        this.islandLayoutFake = null;
        this.focusLayout = null;
        this.focusDarkLayout = null;
        this.focusModalLayout = null;
        this.focusModalDarkLayout = null;
        this.islandAdapter = null;
        this.islandFakeAdapter = null;
        this.focusAdapter = null;
        this.focusDarkAdapter = null;
        this.focusModalAdapter = null;
        this.focusModalDarkAdapter = null;
    }

    public final TemplateBuilderV3 setAreaViewVisible(int i2, int i3) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(i2, i3, null), 3, null);
        return this;
    }

    public final TemplateBuilderV3 updateModuleView(String moduleType, boolean z2, Template template, StatusBarNotification sbn) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        n.g(sbn, "sbn");
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.islandAdapter;
        if (moduleViewHolderAdapter != null) {
            moduleViewHolderAdapter.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.islandFakeAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter3 = this.focusAdapter;
        if (moduleViewHolderAdapter3 != null) {
            moduleViewHolderAdapter3.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter4 = this.focusDarkAdapter;
        if (moduleViewHolderAdapter4 != null) {
            moduleViewHolderAdapter4.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter5 = this.focusModalAdapter;
        if (moduleViewHolderAdapter5 != null) {
            moduleViewHolderAdapter5.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter6 = this.focusModalDarkAdapter;
        if (moduleViewHolderAdapter6 != null) {
            moduleViewHolderAdapter6.updateView(moduleType, template, sbn);
        }
        return this;
    }
}
