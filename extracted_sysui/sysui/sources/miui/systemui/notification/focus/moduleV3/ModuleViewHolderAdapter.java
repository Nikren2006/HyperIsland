package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.ViewGroup;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.Q;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.notification.focus.model.Template;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleViewHolderAdapter {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "ModuleViewHolderAdapter";
    private final Context context;
    private final Map<String, Template> dataMap;
    private final Map<String, ModuleViewHolder> decoHolders;
    private final Map<String, ModuleViewHolder> decoLandHolders;
    private final Map<String, ModuleViewHolder> holders;
    private E scope;
    private final Context sysuiContext;
    private final Map<String, ModuleViewHolder> tinyHolders;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter$bindData$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter$bindData$1", f = "ModuleViewHolderAdapter.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        final /* synthetic */ String $moduleType;
        final /* synthetic */ StatusBarNotification $sbn;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, StatusBarNotification statusBarNotification, L0.d dVar) {
            super(2, dVar);
            this.$moduleType = str;
            this.$sbn = statusBarNotification;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleViewHolderAdapter.this.new AnonymousClass1(this.$moduleType, this.$sbn, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            Template template = (Template) ModuleViewHolderAdapter.this.dataMap.get(this.$moduleType);
            if (template != null) {
                ModuleViewHolderAdapter moduleViewHolderAdapter = ModuleViewHolderAdapter.this;
                String str = this.$moduleType;
                StatusBarNotification statusBarNotification = this.$sbn;
                ModuleViewHolder moduleViewHolder = (ModuleViewHolder) moduleViewHolderAdapter.holders.get(str);
                if (moduleViewHolder != null) {
                    moduleViewHolder.bind(template, statusBarNotification);
                }
            }
            if (template != null) {
                ModuleViewHolderAdapter moduleViewHolderAdapter2 = ModuleViewHolderAdapter.this;
                String str2 = this.$moduleType;
                StatusBarNotification statusBarNotification2 = this.$sbn;
                ModuleViewHolder moduleViewHolder2 = (ModuleViewHolder) moduleViewHolderAdapter2.tinyHolders.get(str2);
                if (moduleViewHolder2 != null) {
                    moduleViewHolder2.bind(template, statusBarNotification2);
                }
            }
            if (template != null) {
                ModuleViewHolderAdapter moduleViewHolderAdapter3 = ModuleViewHolderAdapter.this;
                String str3 = this.$moduleType;
                StatusBarNotification statusBarNotification3 = this.$sbn;
                ModuleViewHolder moduleViewHolder3 = (ModuleViewHolder) moduleViewHolderAdapter3.decoHolders.get(str3);
                if (moduleViewHolder3 != null) {
                    moduleViewHolder3.bind(template, statusBarNotification3);
                }
            }
            if (template != null) {
                ModuleViewHolderAdapter moduleViewHolderAdapter4 = ModuleViewHolderAdapter.this;
                String str4 = this.$moduleType;
                StatusBarNotification statusBarNotification4 = this.$sbn;
                ModuleViewHolder moduleViewHolder4 = (ModuleViewHolder) moduleViewHolderAdapter4.decoLandHolders.get(str4);
                if (moduleViewHolder4 != null) {
                    moduleViewHolder4.bind(template, statusBarNotification4);
                }
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter$removeView$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter$removeView$1", f = "ModuleViewHolderAdapter.kt", l = {}, m = "invokeSuspend")
    public static final class C06811 extends N0.l implements Function2 {
        final /* synthetic */ String $moduleType;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06811(String str, L0.d dVar) {
            super(2, dVar);
            this.$moduleType = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleViewHolderAdapter.this.new C06811(this.$moduleType, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06811) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            ModuleViewHolder moduleViewHolder = (ModuleViewHolder) ModuleViewHolderAdapter.this.holders.remove(this.$moduleType);
            if (moduleViewHolder != null) {
                moduleViewHolder.onDetach();
            }
            ModuleViewHolder moduleViewHolder2 = (ModuleViewHolder) ModuleViewHolderAdapter.this.tinyHolders.remove(this.$moduleType);
            if (moduleViewHolder2 != null) {
                moduleViewHolder2.onDetach();
            }
            ModuleViewHolder moduleViewHolder3 = (ModuleViewHolder) ModuleViewHolderAdapter.this.decoHolders.remove(this.$moduleType);
            if (moduleViewHolder3 != null) {
                moduleViewHolder3.onDetach();
            }
            ModuleViewHolder moduleViewHolder4 = (ModuleViewHolder) ModuleViewHolderAdapter.this.decoLandHolders.remove(this.$moduleType);
            if (moduleViewHolder4 != null) {
                moduleViewHolder4.onDetach();
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter$updateView$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter$updateView$1", f = "ModuleViewHolderAdapter.kt", l = {}, m = "invokeSuspend")
    public static final class C06821 extends N0.l implements Function2 {
        final /* synthetic */ String $moduleType;
        final /* synthetic */ StatusBarNotification $sbn;
        final /* synthetic */ Template $template;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06821(String str, Template template, StatusBarNotification statusBarNotification, L0.d dVar) {
            super(2, dVar);
            this.$moduleType = str;
            this.$template = template;
            this.$sbn = statusBarNotification;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return ModuleViewHolderAdapter.this.new C06821(this.$moduleType, this.$template, this.$sbn, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06821) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            ModuleViewHolderAdapter.this.dataMap.put(this.$moduleType, this.$template);
            ModuleViewHolder moduleViewHolder = (ModuleViewHolder) ModuleViewHolderAdapter.this.holders.get(this.$moduleType);
            if (moduleViewHolder != null) {
                moduleViewHolder.updatePartial(this.$template, this.$sbn);
            }
            ModuleViewHolder moduleViewHolder2 = (ModuleViewHolder) ModuleViewHolderAdapter.this.tinyHolders.get(this.$moduleType);
            if (moduleViewHolder2 != null) {
                moduleViewHolder2.updatePartial(this.$template, this.$sbn);
            }
            ModuleViewHolder moduleViewHolder3 = (ModuleViewHolder) ModuleViewHolderAdapter.this.decoHolders.get(this.$moduleType);
            if (moduleViewHolder3 != null) {
                moduleViewHolder3.updatePartial(this.$template, this.$sbn);
            }
            ModuleViewHolder moduleViewHolder4 = (ModuleViewHolder) ModuleViewHolderAdapter.this.decoLandHolders.get(this.$moduleType);
            if (moduleViewHolder4 != null) {
                moduleViewHolder4.updatePartial(this.$template, this.$sbn);
            }
            Log.e(ModuleViewHolderAdapter.TAG, "updateView" + this.$moduleType + ModuleViewHolderAdapter.this.holders.size());
            return H0.s.f314a;
        }
    }

    public ModuleViewHolderAdapter(Context context, @SystemUI Context sysuiContext) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(sysuiContext, "sysuiContext");
        this.context = context;
        this.sysuiContext = sysuiContext;
        this.holders = new LinkedHashMap();
        this.tinyHolders = new LinkedHashMap();
        this.decoHolders = new LinkedHashMap();
        this.decoLandHolders = new LinkedHashMap();
        this.dataMap = new LinkedHashMap();
        this.scope = F.a(Q.c());
    }

    public static /* synthetic */ void createDecoLandModuleViewHolder$default(ModuleViewHolderAdapter moduleViewHolderAdapter, Template template, String str, boolean z2, ViewGroup viewGroup, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        moduleViewHolderAdapter.createDecoLandModuleViewHolder(template, str, z2, viewGroup);
    }

    public static /* synthetic */ void createDecoModuleViewHolder$default(ModuleViewHolderAdapter moduleViewHolderAdapter, Template template, String str, boolean z2, ViewGroup viewGroup, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        moduleViewHolderAdapter.createDecoModuleViewHolder(template, str, z2, viewGroup);
    }

    public static /* synthetic */ void createTinyModuleViewHolder$default(ModuleViewHolderAdapter moduleViewHolderAdapter, Template template, String str, boolean z2, ViewGroup viewGroup, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        moduleViewHolderAdapter.createTinyModuleViewHolder(template, str, z2, viewGroup);
    }

    public final void bindData(String moduleType, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(moduleType, "moduleType");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(moduleType, sbn, null), 3, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0189  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void createDecoLandModuleViewHolder(miui.systemui.notification.focus.model.Template r8, java.lang.String r9, boolean r10, android.view.ViewGroup r11) {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter.createDecoLandModuleViewHolder(miui.systemui.notification.focus.model.Template, java.lang.String, boolean, android.view.ViewGroup):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x017f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void createDecoModuleViewHolder(miui.systemui.notification.focus.model.Template r8, java.lang.String r9, boolean r10, android.view.ViewGroup r11) {
        /*
            Method dump skipped, instruction units count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter.createDecoModuleViewHolder(miui.systemui.notification.focus.model.Template, java.lang.String, boolean, android.view.ViewGroup):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void createModuleViewHolder(miui.systemui.notification.focus.model.Template r12, android.service.notification.StatusBarNotification r13, java.lang.String r14, boolean r15, boolean r16, boolean r17, android.view.ViewGroup r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter.createModuleViewHolder(miui.systemui.notification.focus.model.Template, android.service.notification.StatusBarNotification, java.lang.String, boolean, boolean, boolean, android.view.ViewGroup, boolean, boolean):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0194  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void createTinyModuleViewHolder(miui.systemui.notification.focus.model.Template r8, java.lang.String r9, boolean r10, android.view.ViewGroup r11) {
        /*
            Method dump skipped, instruction units count: 520
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter.createTinyModuleViewHolder(miui.systemui.notification.focus.model.Template, java.lang.String, boolean, android.view.ViewGroup):void");
    }

    public final void removeView(String moduleType) {
        kotlin.jvm.internal.n.g(moduleType, "moduleType");
        AbstractC0369g.b(this.scope, null, null, new C06811(moduleType, null), 3, null);
    }

    public final void updateView(String moduleType, Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(moduleType, "moduleType");
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        AbstractC0369g.b(this.scope, null, null, new C06821(moduleType, template, sbn, null), 3, null);
    }
}
