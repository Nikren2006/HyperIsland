package miui.systemui.dynamicisland.template;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.module.IslandModuleViewHolderAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTemplateBuilder {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "IslandTemplateBuilder";
    private final boolean bigIsland;
    private FrameLayout cutout;
    private final Function2 emitEvent;
    private final boolean isFakeView;
    private final IslandModuleViewHolderAdapter islandAdapter;
    private final View islandLayout;
    private String leftModule;
    private String moduleLeft;
    private String moduleRight;
    private String moduleSmall;
    private String rightModule;
    private final ViewGroup root;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory {
        IslandTemplateBuilder create(ViewGroup viewGroup, boolean z2, boolean z3, Function2 function2);
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.template.IslandTemplateBuilder$setIslandAreaViewVisible$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.template.IslandTemplateBuilder$setIslandAreaViewVisible$1", f = "IslandTemplateBuilder.kt", l = {}, m = "invokeSuspend")
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
            return IslandTemplateBuilder.this.new AnonymousClass1(this.$areaId, this.$visible, dVar);
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
                FrameLayout frameLayout = (FrameLayout) IslandTemplateBuilder.this.islandLayout.findViewById(this.$areaId);
                if (frameLayout != null) {
                    frameLayout.setVisibility(this.$visible);
                }
            } catch (Exception e2) {
                Log.e(IslandTemplateBuilder.TAG, e2.toString());
            }
            return s.f314a;
        }
    }

    public IslandTemplateBuilder(Context context, ViewGroup root, boolean z2, boolean z3, Function2 emitEvent, IslandModuleViewHolderAdapter islandAdapter) {
        View viewInflate;
        n.g(context, "context");
        n.g(root, "root");
        n.g(emitEvent, "emitEvent");
        n.g(islandAdapter, "islandAdapter");
        this.root = root;
        this.bigIsland = z2;
        this.isFakeView = z3;
        this.emitEvent = emitEvent;
        this.islandAdapter = islandAdapter;
        if (z2) {
            if (z3) {
                viewInflate = LayoutInflater.from(context).inflate(R.layout.fake_dynamic_big_island_template, root);
                viewInflate.setLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
            } else {
                viewInflate = LayoutInflater.from(context).inflate(R.layout.dynamic_big_island_template_standard, root);
                viewInflate.setLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
            }
            n.d(viewInflate);
        } else {
            if (z3) {
                viewInflate = LayoutInflater.from(context).inflate(R.layout.fake_dynamic_small_island_template, root);
                viewInflate.setLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
            } else {
                viewInflate = LayoutInflater.from(context).inflate(R.layout.dynamic_small_island_template_standard, root);
                viewInflate.setLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
            }
            n.d(viewInflate);
        }
        this.islandLayout = viewInflate;
        this.cutout = z3 ? (FrameLayout) viewInflate.findViewById(R.id.fake_area_cutout) : (FrameLayout) viewInflate.findViewById(R.id.area_cutout);
        this.moduleLeft = "";
        this.moduleRight = "";
        this.moduleSmall = "";
    }

    public final IslandTemplateBuilder addIslandModuleView(int i2, String moduleType, IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        int i3 = R.id.area_left;
        if (i2 == i3 || i2 == R.id.fake_area_left) {
            this.moduleLeft = moduleType;
        } else if (i2 == R.id.area_right || i2 == R.id.fake_area_right) {
            this.moduleRight = moduleType;
        } else if (i2 == R.id.small_container || i2 == R.id.fake_small_container) {
            this.moduleSmall = moduleType;
        }
        FrameLayout frameLayout = (FrameLayout) this.islandLayout.findViewById(i2);
        if (i2 == i3 || i2 == R.id.fake_area_left) {
            this.leftModule = moduleType;
        } else if (i2 == R.id.area_right || i2 == R.id.fake_area_right) {
            this.rightModule = moduleType;
        }
        IslandModuleViewHolderAdapter islandModuleViewHolderAdapter = this.islandAdapter;
        n.d(frameLayout);
        islandModuleViewHolderAdapter.createModuleViewHolder(template, moduleType, frameLayout, this.emitEvent);
        this.islandAdapter.bindData(moduleType, dynamicIslandData);
        return this;
    }

    public final View buildIslandView() {
        return this.islandLayout;
    }

    public final IslandTemplateBuilder hideModuleView() {
        this.islandAdapter.hiddenView(this.leftModule);
        this.islandAdapter.hiddenView(this.rightModule);
        return this;
    }

    public final boolean isSameModule(String moduleType) {
        n.g(moduleType, "moduleType");
        return n.c(moduleType, this.moduleLeft) || n.c(moduleType, this.moduleRight) || n.c(moduleType, this.moduleSmall);
    }

    public final void removeIslandView() {
        this.root.removeAllViews();
    }

    public final IslandTemplateBuilder removeModuleView(String moduleType) {
        n.g(moduleType, "moduleType");
        this.islandAdapter.removeView(moduleType);
        return this;
    }

    public final IslandTemplateBuilder setIslandAreaViewVisible(int i2, int i3, E uiScope) {
        n.g(uiScope, "uiScope");
        AbstractC0369g.b(uiScope, null, null, new AnonymousClass1(i2, i3, null), 3, null);
        return this;
    }

    public final IslandTemplateBuilder showModuleView() {
        this.islandAdapter.showView(this.leftModule);
        this.islandAdapter.showView(this.rightModule);
        return this;
    }

    public final void updateCutoutWidth(int i2) {
        FrameLayout frameLayout = this.cutout;
        ViewGroup.LayoutParams layoutParams = frameLayout != null ? frameLayout.getLayoutParams() : null;
        if (layoutParams == null) {
            return;
        }
        layoutParams.width = i2;
    }

    public final void updateLeftWidth(int i2) {
        this.islandAdapter.updateLeftWidth(this.leftModule, i2);
    }

    public final IslandTemplateBuilder updateModuleView(String moduleType, IslandTemplate template, DynamicIslandData dynamicIslandData) {
        n.g(moduleType, "moduleType");
        n.g(template, "template");
        this.islandAdapter.updateView(moduleType, template, dynamicIslandData);
        return this;
    }

    public final void updateRightWidth(int i2) {
        this.islandAdapter.updateRightWidth(this.rightModule, i2);
    }
}
