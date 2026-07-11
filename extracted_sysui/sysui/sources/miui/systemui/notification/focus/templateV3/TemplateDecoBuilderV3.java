package miui.systemui.notification.focus.templateV3;

import G0.a;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import android.content.Context;
import android.content.res.Resources;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.model.IconTextInfo;
import miui.systemui.notification.focus.model.ProgressInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateDecoBuilderV3 {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "TemplateDecoBuilderV3";
    private final Context context;
    private ModuleViewHolderAdapter decoAdapter;
    private ModuleViewHolderAdapter decoDarkAdapter;
    private View decoDarkLayout;
    private View decoLayout;
    private boolean isFlipDevice;
    private String moduleA;
    private String moduleC;
    private String moduleD;
    private String moduleE;
    private final a moduleViewHolderAdapterProvider;
    private final Template template;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        TemplateDecoBuilderV3 create(Template template, boolean z2);
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3$setAreaViewVisible$1, reason: invalid class name */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3$setAreaViewVisible$1", f = "TemplateDecoBuilderV3.kt", l = {}, m = "invokeSuspend")
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
            return TemplateDecoBuilderV3.this.new AnonymousClass1(this.$areaId, this.$visible, dVar);
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
                FrameLayout frameLayout = (FrameLayout) TemplateDecoBuilderV3.this.decoLayout.findViewById(this.$areaId);
                if (frameLayout != null) {
                    frameLayout.setVisibility(this.$visible);
                }
                FrameLayout frameLayout2 = (FrameLayout) TemplateDecoBuilderV3.this.decoDarkLayout.findViewById(this.$areaId);
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(this.$visible);
                }
            } catch (Exception e2) {
                Log.e(TemplateDecoBuilderV3.TAG, e2.toString());
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3$setRemoteViewsPadding$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3$setRemoteViewsPadding$1", f = "TemplateDecoBuilderV3.kt", l = {}, m = "invokeSuspend")
    public static final class C06871 extends l implements Function2 {
        final /* synthetic */ View $view;
        int label;
        final /* synthetic */ TemplateDecoBuilderV3 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06871(View view, TemplateDecoBuilderV3 templateDecoBuilderV3, d dVar) {
            super(2, dVar);
            this.$view = view;
            this.this$0 = templateDecoBuilderV3;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new C06871(this.$view, this.this$0, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06871) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            float dimension;
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            RelativeLayout relativeLayout = (RelativeLayout) this.$view.findViewById(R.id.area_container);
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            n.e(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            Resources resources = this.this$0.context.getResources();
            int i2 = R.dimen.focus_notify_template_deco_margin;
            layoutParams2.setMarginStart((int) resources.getDimension(i2));
            layoutParams2.topMargin = (int) this.this$0.context.getResources().getDimension(i2);
            layoutParams2.bottomMargin = (int) this.this$0.context.getResources().getDimension(i2);
            ProgressInfo progressInfo = this.this$0.template.getProgressInfo();
            if ((progressInfo != null ? b.c(progressInfo.getProgress()) : null) != null) {
                FrameLayout frameLayout = (FrameLayout) this.$view.findViewById(R.id.area_a);
                ViewGroup.LayoutParams layoutParams3 = frameLayout.getLayoutParams();
                n.e(layoutParams3, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
                ProgressInfo progressInfo2 = this.this$0.template.getProgressInfo();
                Integer numC = progressInfo2 != null ? b.c(progressInfo2.getProgress()) : null;
                n.d(numC);
                if (numC.intValue() == 100) {
                    dimension = this.this$0.context.getResources().getDimension(R.dimen.focus_notify_template_deco_progress_margin);
                    layoutParams4.setMarginEnd((int) this.this$0.context.getResources().getDimension(R.dimen.focus_notify_template_deco_area_a_margin));
                } else {
                    ProgressInfo progressInfo3 = this.this$0.template.getProgressInfo();
                    Integer numC2 = progressInfo3 != null ? b.c(progressInfo3.getProgress()) : null;
                    n.d(numC2);
                    int iIntValue = numC2.intValue();
                    if (51 > iIntValue || iIntValue >= 100) {
                        dimension = this.this$0.context.getResources().getDimension(i2);
                    } else {
                        dimension = this.this$0.context.getResources().getDimension(R.dimen.focus_notify_template_deco_progress_margin_end);
                        layoutParams4.setMarginEnd((int) this.this$0.context.getResources().getDimension(R.dimen.focus_notify_template_deco_progress_area_a_margin));
                    }
                }
                frameLayout.setLayoutParams(layoutParams4);
            } else {
                dimension = this.this$0.context.getResources().getDimension(i2);
            }
            layoutParams2.setMarginEnd((int) dimension);
            relativeLayout.setLayoutParams(layoutParams2);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3$updateTopGravity$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3$updateTopGravity$1", f = "TemplateDecoBuilderV3.kt", l = {}, m = "invokeSuspend")
    public static final class C06881 extends l implements Function2 {
        final /* synthetic */ boolean $isCenterVertical;
        final /* synthetic */ View $parent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06881(View view, boolean z2, d dVar) {
            super(2, dVar);
            this.$parent = view;
            this.$isCenterVertical = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new C06881(this.$parent, this.$isCenterVertical, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06881) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            LinearLayout linearLayout = (LinearLayout) this.$parent.findViewById(R.id.area_top);
            if (linearLayout != null) {
                boolean z2 = this.$isCenterVertical;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(2, R.id.area_bottom);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(z2 ? 16 : 48);
            }
            return s.f314a;
        }
    }

    public TemplateDecoBuilderV3(Context context, Template template, boolean z2, a moduleViewHolderAdapterProvider) {
        n.g(context, "context");
        n.g(template, "template");
        n.g(moduleViewHolderAdapterProvider, "moduleViewHolderAdapterProvider");
        this.context = context;
        this.template = template;
        this.isFlipDevice = z2;
        this.moduleViewHolderAdapterProvider = moduleViewHolderAdapterProvider;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i2 = R.layout.focus_notification_template_deco_port_center;
        this.decoLayout = layoutInflaterFrom.inflate(i2, (ViewGroup) null);
        this.decoDarkLayout = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        this.moduleA = "";
        this.moduleC = "";
        this.moduleE = "";
        this.moduleD = "";
    }

    private final void addDecoDarkView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.decoDarkLayout.findViewById(i2);
        if (this.decoDarkAdapter == null) {
            this.decoDarkAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.decoDarkAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createDecoModuleViewHolder(template, str, true, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.decoDarkAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void addDecoView(int i2, String str, boolean z2, Template template, StatusBarNotification statusBarNotification) {
        FrameLayout frameLayout = (FrameLayout) this.decoLayout.findViewById(i2);
        if (this.decoAdapter == null) {
            this.decoAdapter = (ModuleViewHolderAdapter) this.moduleViewHolderAdapterProvider.get();
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.decoAdapter;
        if (moduleViewHolderAdapter != null) {
            n.d(frameLayout);
            moduleViewHolderAdapter.createDecoModuleViewHolder(template, str, false, frameLayout);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.decoAdapter;
        if (moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.bindData(str, statusBarNotification);
        }
    }

    private final void setRemoteViewsPadding(View view) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C06871(view, this, null), 3, null);
    }

    private final void updateTopGravity(View view, boolean z2) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C06881(view, z2, null), 3, null);
    }

    public final TemplateDecoBuilderV3 addModuleView(int i2, String moduleType, boolean z2, Template template, StatusBarNotification sbn) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        n.g(sbn, "sbn");
        if (i2 == R.id.area_a) {
            this.moduleA = moduleType;
        } else if (i2 == R.id.area_c) {
            this.moduleC = moduleType;
        } else if (i2 == R.id.area_d) {
            this.moduleD = moduleType;
        } else if (i2 == R.id.area_e) {
            this.moduleE = moduleType;
        }
        addDecoView(i2, moduleType, z2, template, sbn);
        addDecoDarkView(i2, moduleType, z2, template, sbn);
        return this;
    }

    public final void buildView(FocusNotificationContent focusNotificationContent) {
        n.g(focusNotificationContent, "focusNotificationContent");
        focusNotificationContent.setDeco(this.decoLayout);
        focusNotificationContent.setDecoDark(this.decoDarkLayout);
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

    public final boolean isSameModuleE(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleE);
    }

    public final TemplateDecoBuilderV3 removeModuleView(String moduleType) {
        n.g(moduleType, "moduleType");
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.decoAdapter;
        if (moduleViewHolderAdapter != null && moduleViewHolderAdapter != null) {
            moduleViewHolderAdapter.removeView(moduleType);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.decoDarkAdapter;
        if (moduleViewHolderAdapter2 != null && moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.removeView(moduleType);
        }
        return this;
    }

    public final void removeView(FocusNotificationContent focusNotificationContent) {
        n.g(focusNotificationContent, "focusNotificationContent");
        focusNotificationContent.setDeco((View) null);
        focusNotificationContent.setDecoDark((View) null);
    }

    public final TemplateDecoBuilderV3 setAreaTopGravity() {
        Integer type;
        IconTextInfo iconTextInfo = this.template.getIconTextInfo();
        boolean z2 = false;
        if (iconTextInfo != null && (type = iconTextInfo.getType()) != null && type.intValue() == 1) {
            z2 = true;
        }
        View decoLayout = this.decoLayout;
        n.f(decoLayout, "decoLayout");
        updateTopGravity(decoLayout, z2);
        View decoDarkLayout = this.decoDarkLayout;
        n.f(decoDarkLayout, "decoDarkLayout");
        updateTopGravity(decoDarkLayout, z2);
        return this;
    }

    public final TemplateDecoBuilderV3 setAreaViewVisible(int i2, int i3) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(i2, i3, null), 3, null);
        return this;
    }

    public final TemplateDecoBuilderV3 setViewLayoutPadding() {
        View decoLayout = this.decoLayout;
        n.f(decoLayout, "decoLayout");
        setRemoteViewsPadding(decoLayout);
        View decoDarkLayout = this.decoDarkLayout;
        n.f(decoDarkLayout, "decoDarkLayout");
        setRemoteViewsPadding(decoDarkLayout);
        return this;
    }

    public final TemplateDecoBuilderV3 updateModuleView(String moduleType, boolean z2, Template template, StatusBarNotification sbn) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        n.g(sbn, "sbn");
        ModuleViewHolderAdapter moduleViewHolderAdapter = this.decoAdapter;
        if (moduleViewHolderAdapter != null && moduleViewHolderAdapter != null) {
            moduleViewHolderAdapter.updateView(moduleType, template, sbn);
        }
        ModuleViewHolderAdapter moduleViewHolderAdapter2 = this.decoDarkAdapter;
        if (moduleViewHolderAdapter2 != null && moduleViewHolderAdapter2 != null) {
            moduleViewHolderAdapter2.updateView(moduleType, template, sbn);
        }
        return this;
    }
}
