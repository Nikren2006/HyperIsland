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
import android.widget.RelativeLayout;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateTinyBuilderV3 {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "TemplateTinyBuilderV3";
    private final Context context;
    private boolean isFlipDevice;
    private String moduleA;
    private String moduleC;
    private String moduleD;
    private final a moduleViewHolderAdapterProvider;
    private ModuleViewHolderAdapter tinyAdapter;
    private ModuleViewHolderAdapter tinyDarkAdapter;
    private final View tinyDarkLayout;
    private ModuleViewHolderAdapter tinyKeyguardAdapter;
    private ModuleViewHolderAdapter tinyKeyguardDarkAdapter;
    private final View tinyKeyguardDarkLayout;
    private final View tinyKeyguardLayout;
    private final View tinyLayout;
    private ModuleViewHolderAdapter tinyModalAdapter;
    private ModuleViewHolderAdapter tinyModalDarkAdapter;
    private final View tinyModalDarkLayout;
    private final View tinyModalLayout;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        TemplateTinyBuilderV3 create(boolean z2);
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3$setAreaViewVisible$1, reason: invalid class name */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3$setAreaViewVisible$1", f = "TemplateTinyBuilderV3.kt", l = {}, m = "invokeSuspend")
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
            return TemplateTinyBuilderV3.this.new AnonymousClass1(this.$areaId, this.$visible, dVar);
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
                FrameLayout frameLayout = (FrameLayout) TemplateTinyBuilderV3.this.tinyLayout.findViewById(this.$areaId);
                if (frameLayout != null) {
                    frameLayout.setVisibility(this.$visible);
                }
                FrameLayout frameLayout2 = (FrameLayout) TemplateTinyBuilderV3.this.tinyDarkLayout.findViewById(this.$areaId);
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(this.$visible);
                }
                FrameLayout frameLayout3 = (FrameLayout) TemplateTinyBuilderV3.this.tinyKeyguardLayout.findViewById(this.$areaId);
                if (frameLayout3 != null) {
                    frameLayout3.setVisibility(this.$visible);
                }
                FrameLayout frameLayout4 = (FrameLayout) TemplateTinyBuilderV3.this.tinyKeyguardDarkLayout.findViewById(this.$areaId);
                if (frameLayout4 != null) {
                    frameLayout4.setVisibility(this.$visible);
                }
                FrameLayout frameLayout5 = (FrameLayout) TemplateTinyBuilderV3.this.tinyModalLayout.findViewById(this.$areaId);
                if (frameLayout5 != null) {
                    frameLayout5.setVisibility(this.$visible);
                }
                FrameLayout frameLayout6 = (FrameLayout) TemplateTinyBuilderV3.this.tinyModalDarkLayout.findViewById(this.$areaId);
                if (frameLayout6 != null) {
                    frameLayout6.setVisibility(this.$visible);
                }
            } catch (Exception e2) {
                Log.e(TemplateTinyBuilderV3.TAG, e2.toString());
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3$updateAreaCenterGravity$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3$updateAreaCenterGravity$1", f = "TemplateTinyBuilderV3.kt", l = {}, m = "invokeSuspend")
    public static final class C06921 extends l implements Function2 {
        final /* synthetic */ boolean $isCenterVertical;
        final /* synthetic */ View $parent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06921(View view, boolean z2, d dVar) {
            super(2, dVar);
            this.$parent = view;
            this.$isCenterVertical = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new C06921(this.$parent, this.$isCenterVertical, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06921) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            FrameLayout frameLayout = (FrameLayout) this.$parent.findViewById(R.id.area_a);
            if (frameLayout != null) {
                boolean z2 = this.$isCenterVertical;
                ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
                if (layoutParams2 != null) {
                    if (z2) {
                        layoutParams2.addRule(15);
                        layoutParams2.removeRule(10);
                        layoutParams2.removeRule(12);
                    } else {
                        layoutParams2.addRule(10);
                        layoutParams2.removeRule(15);
                        layoutParams2.removeRule(12);
                    }
                    frameLayout.setLayoutParams(layoutParams2);
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3$updateCenterGravity$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3$updateCenterGravity$1", f = "TemplateTinyBuilderV3.kt", l = {}, m = "invokeSuspend")
    public static final class C06931 extends l implements Function2 {
        final /* synthetic */ boolean $isCenterVertical;
        final /* synthetic */ View $parent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06931(View view, boolean z2, d dVar) {
            super(2, dVar);
            this.$parent = view;
            this.$isCenterVertical = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new C06931(this.$parent, this.$isCenterVertical, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06931) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            FrameLayout frameLayout = (FrameLayout) this.$parent.findViewById(R.id.area_c);
            if (frameLayout != null) {
                boolean z2 = this.$isCenterVertical;
                ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
                if (layoutParams2 != null) {
                    if (z2) {
                        layoutParams2.addRule(15);
                        layoutParams2.removeRule(10);
                        layoutParams2.removeRule(12);
                    } else {
                        layoutParams2.addRule(10);
                        layoutParams2.removeRule(15);
                        layoutParams2.removeRule(12);
                    }
                    frameLayout.setLayoutParams(layoutParams2);
                }
            }
            return s.f314a;
        }
    }

    public TemplateTinyBuilderV3(Context context, boolean z2, a moduleViewHolderAdapterProvider) {
        n.g(context, "context");
        n.g(moduleViewHolderAdapterProvider, "moduleViewHolderAdapterProvider");
        this.context = context;
        this.isFlipDevice = z2;
        this.moduleViewHolderAdapterProvider = moduleViewHolderAdapterProvider;
        this.moduleA = "";
        this.moduleC = "";
        this.moduleD = "";
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i2 = R.layout.focus_notification_template_tiny;
        this.tinyLayout = layoutInflaterFrom.inflate(i2, (ViewGroup) null);
        this.tinyDarkLayout = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        View viewInflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        int i3 = R.id.dynamic_island_modal_tag;
        Boolean bool = Boolean.TRUE;
        viewInflate.setTag(i3, bool);
        this.tinyModalLayout = viewInflate;
        View viewInflate2 = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        viewInflate2.setTag(i3, bool);
        this.tinyModalDarkLayout = viewInflate2;
        this.tinyKeyguardLayout = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        this.tinyKeyguardDarkLayout = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
    }

    private final void addTinyDarkView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.tinyDarkLayout.findViewById(i2);
        if (this.tinyDarkAdapter == null) {
            this.tinyDarkAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyDarkAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, true, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyDarkAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addTinyKeyguardDarkView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.tinyKeyguardDarkLayout.findViewById(i2);
        if (this.tinyKeyguardDarkAdapter == null) {
            this.tinyKeyguardDarkAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyKeyguardDarkAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, true, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyKeyguardDarkAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addTinyKeyguardView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.tinyKeyguardLayout.findViewById(i2);
        if (this.tinyKeyguardAdapter == null) {
            this.tinyKeyguardAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyKeyguardAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, false, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyKeyguardAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addTinyModalDarkView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.tinyModalDarkLayout.findViewById(i2);
        if (this.tinyModalDarkAdapter == null) {
            this.tinyModalDarkAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyModalDarkAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, true, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyModalDarkAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addTinyModalView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.tinyModalLayout.findViewById(i2);
        if (this.tinyModalAdapter == null) {
            this.tinyModalAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyModalAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, false, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyModalAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addTinyView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.tinyLayout.findViewById(i2);
        if (this.tinyAdapter == null) {
            this.tinyAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, false, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void updateAreaCenterGravity(View view, boolean z2) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C06921(view, z2, null), 3, null);
    }

    private final void updateCenterGravity(View view, boolean z2) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C06931(view, z2, null), 3, null);
    }

    public final TemplateTinyBuilderV3 addModuleView(int i2, String moduleType, boolean z2, Template template, StatusBarNotification sbn) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        n.g(sbn, "sbn");
        if (i2 == R.id.area_a) {
            this.moduleA = moduleType;
        } else if (i2 == R.id.area_c) {
            this.moduleC = moduleType;
        } else if (i2 == R.id.area_d) {
            this.moduleD = moduleType;
        }
        addTinyView(i2, moduleType, z2, template, sbn);
        addTinyDarkView(i2, moduleType, z2, template, sbn);
        addTinyModalView(i2, moduleType, z2, template, sbn);
        addTinyModalDarkView(i2, moduleType, z2, template, sbn);
        addTinyKeyguardView(i2, moduleType, z2, template, sbn);
        addTinyKeyguardDarkView(i2, moduleType, z2, template, sbn);
        return this;
    }

    public final void buildView(FocusNotificationContent focusNotificationContent) {
        n.g(focusNotificationContent, "focusNotificationContent");
        focusNotificationContent.setTinyView(this.tinyLayout);
        focusNotificationContent.setTinyViewDark(this.tinyDarkLayout);
        focusNotificationContent.setTinyKeyguardView(this.tinyKeyguardLayout);
        focusNotificationContent.setTinyViewKeyguardDark(this.tinyKeyguardDarkLayout);
        focusNotificationContent.setTinyViewModal(this.tinyModalLayout);
        focusNotificationContent.setTinyViewDarkModal(this.tinyModalDarkLayout);
    }

    public final boolean isSameModuleA(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleA);
    }

    public final boolean isSameModuleC(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleC);
    }

    public final boolean isSameModuleD(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleD);
    }

    public final TemplateTinyBuilderV3 removeModuleView(String moduleType) {
        n.g(moduleType, "moduleType");
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyAdapter;
        if (moduleViewHolderAdapter != null && moduleViewHolderAdapter != null) {
            moduleViewHolderAdapter.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyDarkAdapter;
        if (moduleViewHolderAdapter2 != null && moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter3 = this.tinyKeyguardAdapter;
        if (moduleViewHolderAdapter3 != null && moduleViewHolderAdapter3 != null) {
            moduleViewHolderAdapter3.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter4 = this.tinyKeyguardDarkAdapter;
        if (moduleViewHolderAdapter4 != null && moduleViewHolderAdapter4 != null) {
            moduleViewHolderAdapter4.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter5 = this.tinyModalAdapter;
        if (moduleViewHolderAdapter5 != null && moduleViewHolderAdapter5 != null) {
            moduleViewHolderAdapter5.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter6 = this.tinyModalDarkAdapter;
        if (moduleViewHolderAdapter6 != null && moduleViewHolderAdapter6 != null) {
            moduleViewHolderAdapter6.removeView(moduleType);
        }
        return this;
    }

    public final void removeView(FocusNotificationContent focusNotificationContent) {
        n.g(focusNotificationContent, "focusNotificationContent");
        focusNotificationContent.setTinyView((View) null);
        focusNotificationContent.setTinyViewDark((View) null);
        focusNotificationContent.setTinyKeyguardView((View) null);
        focusNotificationContent.setTinyViewKeyguardDark((View) null);
        focusNotificationContent.setTinyViewModal((View) null);
        focusNotificationContent.setTinyViewDarkModal((View) null);
    }

    public final TemplateTinyBuilderV3 setAreaCenterGravity(boolean z2) {
        View tinyLayout = this.tinyLayout;
        n.f(tinyLayout, "tinyLayout");
        updateAreaCenterGravity(tinyLayout, z2);
        View tinyDarkLayout = this.tinyDarkLayout;
        n.f(tinyDarkLayout, "tinyDarkLayout");
        updateAreaCenterGravity(tinyDarkLayout, z2);
        View tinyKeyguardLayout = this.tinyKeyguardLayout;
        n.f(tinyKeyguardLayout, "tinyKeyguardLayout");
        updateAreaCenterGravity(tinyKeyguardLayout, z2);
        View tinyKeyguardDarkLayout = this.tinyKeyguardDarkLayout;
        n.f(tinyKeyguardDarkLayout, "tinyKeyguardDarkLayout");
        updateAreaCenterGravity(tinyKeyguardDarkLayout, z2);
        View tinyModalLayout = this.tinyModalLayout;
        n.f(tinyModalLayout, "tinyModalLayout");
        updateAreaCenterGravity(tinyModalLayout, z2);
        View tinyModalDarkLayout = this.tinyModalDarkLayout;
        n.f(tinyModalDarkLayout, "tinyModalDarkLayout");
        updateAreaCenterGravity(tinyModalDarkLayout, z2);
        return this;
    }

    public final TemplateTinyBuilderV3 setAreaViewVisible(int i2, int i3) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(i2, i3, null), 3, null);
        return this;
    }

    public final TemplateTinyBuilderV3 setArecCenterGravity(boolean z2) {
        View tinyLayout = this.tinyLayout;
        n.f(tinyLayout, "tinyLayout");
        updateCenterGravity(tinyLayout, z2);
        View tinyDarkLayout = this.tinyDarkLayout;
        n.f(tinyDarkLayout, "tinyDarkLayout");
        updateCenterGravity(tinyDarkLayout, z2);
        View tinyKeyguardLayout = this.tinyKeyguardLayout;
        n.f(tinyKeyguardLayout, "tinyKeyguardLayout");
        updateCenterGravity(tinyKeyguardLayout, z2);
        View tinyKeyguardDarkLayout = this.tinyKeyguardDarkLayout;
        n.f(tinyKeyguardDarkLayout, "tinyKeyguardDarkLayout");
        updateCenterGravity(tinyKeyguardDarkLayout, z2);
        View tinyModalLayout = this.tinyModalLayout;
        n.f(tinyModalLayout, "tinyModalLayout");
        updateCenterGravity(tinyModalLayout, z2);
        View tinyModalDarkLayout = this.tinyModalDarkLayout;
        n.f(tinyModalDarkLayout, "tinyModalDarkLayout");
        updateCenterGravity(tinyModalDarkLayout, z2);
        return this;
    }

    public final TemplateTinyBuilderV3 updateModuleView(String moduleType, boolean z2, Template template, StatusBarNotification sbn) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        n.g(sbn, "sbn");
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.tinyAdapter;
        if (moduleViewHolderAdapter != null && moduleViewHolderAdapter != null) {
            moduleViewHolderAdapter.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.tinyDarkAdapter;
        if (moduleViewHolderAdapter2 != null && moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter3 = this.tinyKeyguardAdapter;
        if (moduleViewHolderAdapter3 != null && moduleViewHolderAdapter3 != null) {
            moduleViewHolderAdapter3.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter4 = this.tinyKeyguardDarkAdapter;
        if (moduleViewHolderAdapter4 != null && moduleViewHolderAdapter4 != null) {
            moduleViewHolderAdapter4.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter5 = this.tinyModalAdapter;
        if (moduleViewHolderAdapter5 != null && moduleViewHolderAdapter5 != null) {
            moduleViewHolderAdapter5.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter6 = this.tinyModalDarkAdapter;
        if (moduleViewHolderAdapter6 != null && moduleViewHolderAdapter6 != null) {
            moduleViewHolderAdapter6.updateView(moduleType, template, sbn);
        }
        return this;
    }
}
