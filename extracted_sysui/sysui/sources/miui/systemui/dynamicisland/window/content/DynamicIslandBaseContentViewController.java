package miui.systemui.dynamicisland.window.content;

import H0.i;
import H0.k;
import H0.s;
import I0.m;
import L0.d;
import N0.f;
import N0.l;
import V0.p;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import b.BinderC0222a;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import f1.e;
import f1.o;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.C;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandShareUtils;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor;
import miui.systemui.dynamicisland.event.DynamicIslandEvent;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.model.IslandTemplate;
import miui.systemui.dynamicisland.model.ShareData;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.DynamicIslandViewModel;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.lifecycle.RepeatWhenAttachedKt;
import miui.systemui.util.kotlin.DisposableHandles;

/* JADX INFO: loaded from: classes3.dex */
public class DynamicIslandBaseContentViewController<T extends DynamicIslandBaseContentView> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandBaseContentViewController";
    private boolean clickIsBigLand;
    private final Context context;
    private final DisposableHandles disposables;
    private boolean isProviderAccessible;
    private final IslandTemplateFactory islandTemplateFactory;
    private float marginCardRight;
    private float marginCardTop;
    private final DynamicIslandRegionSamplingInteractor regionSamplingInteractor;
    private final E scope;
    private final DynamicIslandTouchInteractor touchInteractor;
    private final T view;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$1", f = "DynamicIslandBaseContentViewController.kt", l = {82}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function3 {
        int label;
        final /* synthetic */ DynamicIslandBaseContentViewController<T> this$0;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$1$1", f = "DynamicIslandBaseContentViewController.kt", l = {}, m = "invokeSuspend")
        public static final class C01291 extends l implements p {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            /* synthetic */ boolean Z$2;
            int label;

            public C01291(d dVar) {
                super(6, dVar);
            }

            @Override // V0.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                return invoke((String) obj, (String) obj2, ((Boolean) obj3).booleanValue(), ((Boolean) obj4).booleanValue(), ((Boolean) obj5).booleanValue(), (d) obj6);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                return m.f((String) this.L$0, (String) this.L$1, N0.b.a(!this.Z$0), N0.b.a(this.Z$1), N0.b.a(this.Z$2));
            }

            public final Object invoke(String str, String str2, boolean z2, boolean z3, boolean z4, d dVar) {
                C01291 c01291 = new C01291(dVar);
                c01291.L$0 = str;
                c01291.L$1 = str2;
                c01291.Z$0 = z2;
                c01291.Z$1 = z3;
                c01291.Z$2 = z4;
                return c01291.invokeSuspend(s.f314a);
            }
        }

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$1$2, reason: invalid class name */
        @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$1$2", f = "DynamicIslandBaseContentViewController.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends l implements Function2 {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DynamicIslandBaseContentViewController<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(DynamicIslandBaseContentViewController<? extends T> dynamicIslandBaseContentViewController, d dVar) {
                super(2, dVar);
                this.this$0 = dynamicIslandBaseContentViewController;
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                String str;
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                ArrayList arrayList = (ArrayList) this.L$0;
                boolean z2 = true;
                if (arrayList.get(1) != null) {
                    Object obj2 = arrayList.get(1);
                    n.e(obj2, "null cannot be cast to non-null type kotlin.String");
                    str = (String) obj2;
                } else {
                    str = "";
                }
                Object obj3 = arrayList.get(2);
                n.e(obj3, "null cannot be cast to non-null type kotlin.Boolean");
                boolean zBooleanValue = ((Boolean) obj3).booleanValue();
                Object obj4 = arrayList.get(3);
                n.e(obj4, "null cannot be cast to non-null type kotlin.Boolean");
                if (!((Boolean) obj4).booleanValue()) {
                    Object obj5 = arrayList.get(4);
                    n.e(obj5, "null cannot be cast to non-null type kotlin.Boolean");
                    if (!((Boolean) obj5).booleanValue()) {
                        z2 = false;
                    }
                }
                this.this$0.getView().updateDarkLightMode(this.this$0.getView().getState(), str, zBooleanValue, z2);
                return s.f314a;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(ArrayList<? extends Object> arrayList, d dVar) {
                return ((AnonymousClass2) create(arrayList, dVar)).invokeSuspend(s.f314a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(DynamicIslandBaseContentViewController<? extends T> dynamicIslandBaseContentViewController, d dVar) {
            super(3, dVar);
            this.this$0 = dynamicIslandBaseContentViewController;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            return new AnonymousClass1(this.this$0, dVar).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f interfaceC0418fK = AbstractC0420h.k(this.this$0.getView().getViewModel().getStateName(), this.this$0.getView().getHighlightColor(), ((DynamicIslandBaseContentViewController) this.this$0).regionSamplingInteractor.isRegionDark(), this.this$0.getView().getIslandAppAnimRunning(), this.this$0.getView().getIslandFreeformAnimRunning(), new C01291(null));
                DynamicIslandBaseContentViewController$sam$kotlinx_coroutines_flow_FlowCollector$0 dynamicIslandBaseContentViewController$sam$kotlinx_coroutines_flow_FlowCollector$0 = new DynamicIslandBaseContentViewController$sam$kotlinx_coroutines_flow_FlowCollector$0(new AnonymousClass2(this.this$0, null));
                this.label = 1;
                if (interfaceC0418fK.collect(dynamicIslandBaseContentViewController$sam$kotlinx_coroutines_flow_FlowCollector$0, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$2, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$2", f = "DynamicIslandBaseContentViewController.kt", l = {94}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function3 {
        int label;
        final /* synthetic */ DynamicIslandBaseContentViewController<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(DynamicIslandBaseContentViewController<? extends T> dynamicIslandBaseContentViewController, d dVar) {
            super(3, dVar);
            this.this$0 = dynamicIslandBaseContentViewController;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            return new AnonymousClass2(this.this$0, dVar).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I swipeInfo = ((DynamicIslandBaseContentViewController) this.this$0).touchInteractor.getSwipeInfo();
                final DynamicIslandBaseContentViewController<T> dynamicIslandBaseContentViewController = this.this$0;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController.2.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(i iVar, d dVar) {
                        dynamicIslandBaseContentViewController.getView().setSwipeInfo(iVar);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (swipeInfo.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$3, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$3", f = "DynamicIslandBaseContentViewController.kt", l = {101}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends l implements Function3 {
        int label;
        final /* synthetic */ DynamicIslandBaseContentViewController<T> this$0;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$3$1, reason: invalid class name */
        @f(c = "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$3$1", f = "DynamicIslandBaseContentViewController.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends l implements Function3 {
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            int label;

            public AnonymousClass1(d dVar) {
                super(3, dVar);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), (d) obj3);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                return N0.b.a(this.Z$0 || this.Z$1);
            }

            public final Object invoke(boolean z2, boolean z3, d dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
                anonymousClass1.Z$0 = z2;
                anonymousClass1.Z$1 = z3;
                return anonymousClass1.invokeSuspend(s.f314a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(DynamicIslandBaseContentViewController<? extends T> dynamicIslandBaseContentViewController, d dVar) {
            super(3, dVar);
            this.this$0 = dynamicIslandBaseContentViewController;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            return new AnonymousClass3(this.this$0, dVar).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                if (this.this$0.getView() instanceof DynamicIslandContentFakeView) {
                    InterfaceC0418f interfaceC0418fL = AbstractC0420h.l(((DynamicIslandContentFakeView) this.this$0.getView()).getTrackingToOpenMW(), ((DynamicIslandContentFakeView) this.this$0.getView()).getStartEnterMiniWindowBeforeAnimation(), new AnonymousClass1(null));
                    final DynamicIslandBaseContentViewController<T> dynamicIslandBaseContentViewController = this.this$0;
                    InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController.3.2
                        @Override // j1.InterfaceC0419g
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                            return emit(((Boolean) obj2).booleanValue(), dVar);
                        }

                        public final Object emit(boolean z2, d dVar) {
                            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = dynamicIslandBaseContentViewController.getView().getDynamicIslandEventCoordinator();
                            if (dynamicIslandEventCoordinator != null) {
                                dynamicIslandEventCoordinator.updateTouchRegion();
                            }
                            return s.f314a;
                        }
                    };
                    this.label = 1;
                    if (interfaceC0418fL.collect(interfaceC0419g, this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface Factory<T extends DynamicIslandBaseContentView> {
        DynamicIslandBaseContentViewController<T> create(T t2);
    }

    public interface FactoryImpl extends Factory<DynamicIslandBaseContentView> {
    }

    public DynamicIslandBaseContentViewController(T view, @DynamicIsland E scope, DynamicIslandRegionSamplingInteractor regionSamplingInteractor, IslandTemplateFactory islandTemplateFactory, DynamicIslandTouchInteractor touchInteractor) {
        Context context;
        Resources resources;
        Context context2;
        Resources resources2;
        n.g(view, "view");
        n.g(scope, "scope");
        n.g(regionSamplingInteractor, "regionSamplingInteractor");
        n.g(islandTemplateFactory, "islandTemplateFactory");
        n.g(touchInteractor, "touchInteractor");
        this.view = view;
        this.scope = scope;
        this.regionSamplingInteractor = regionSamplingInteractor;
        this.islandTemplateFactory = islandTemplateFactory;
        this.touchInteractor = touchInteractor;
        Context context3 = view.getContext();
        this.context = context3;
        DisposableHandles disposableHandles = new DisposableHandles();
        this.disposables = disposableHandles;
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached$default(view, null, new AnonymousClass1(this, null), 1, null));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached$default(view, null, new AnonymousClass2(this, null), 1, null));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached$default(view, null, new AnonymousClass3(this, null), 1, null));
        View mask = view.getMask();
        float dimension = 0.0f;
        this.marginCardRight = (mask == null || (context2 = mask.getContext()) == null || (resources2 = context2.getResources()) == null) ? 0.0f : resources2.getDimension(R.dimen.isLand_drag_card_hand_margin_right);
        View mask2 = view.getMask();
        if (mask2 != null && (context = mask2.getContext()) != null && (resources = context.getResources()) != null) {
            dimension = resources.getDimension(R.dimen.isLand_drag_card_hand_margin_top);
        }
        this.marginCardTop = dimension;
        DynamicIslandShareUtils dynamicIslandShareUtils = DynamicIslandShareUtils.INSTANCE;
        n.f(context3, "context");
        this.isProviderAccessible = dynamicIslandShareUtils.isProviderAccessible(context3, Uri.parse(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_URI));
    }

    private final i calculateViewPosition(View view, float f2) {
        int[] iArr = new int[2];
        if (view != null) {
            view.getLocationOnScreen(iArr);
        }
        int i2 = (int) (iArr[1] + f2);
        int i3 = iArr[0];
        Integer numValueOf = view != null ? Integer.valueOf(view.getWidth()) : null;
        n.d(numValueOf);
        return new i(Integer.valueOf(i3 + (numValueOf.intValue() / 2)), Integer.valueOf(i2));
    }

    private final int dragShareAddFlags() {
        try {
            Field declaredField = View.class.getDeclaredField("DRAG_FLAG_DYNAMIC_ISLAND_CUSTOM_ANIMATION");
            declaredField.setAccessible(true);
            return 768 | declaredField.getInt(null);
        } catch (Exception e2) {
            Log.e(TAG, "反射获取 DRAG_FLAG_DYNAMIC_ISLAND_CUSTOM_ANIMATION 失败", e2);
            return 768;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @SuppressLint({"InflateParams"})
    private final View.DragShadowBuilder dragShareCard(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandData dynamicIslandData, ShareData shareData) {
        Bundle extras;
        Bundle extras2;
        Drawable appIcon = null;
        final View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.dynamic_island_share_view, (ViewGroup) null);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        String string = (dynamicIslandData == null || (extras2 = dynamicIslandData.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        Bundle bundle = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getBundle("miui.focus.pics");
        Icon icon = bundle != null ? (Icon) bundle.getParcelable(DynamicIslandConstants.MEDIA_SHARE_BITMAP) : null;
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.share_icon);
        ((TextView) viewInflate.findViewById(R.id.share_title)).setText(shareData != null ? shareData.getTitle() : null);
        ((TextView) viewInflate.findViewById(R.id.share_content)).setText(shareData != null ? shareData.getContent() : null);
        if (icon != null) {
            Drawable drawableLoadDrawable = icon.loadDrawable(this.context);
            if (drawableLoadDrawable != null) {
                DynamicIslandShareUtils dynamicIslandShareUtils = DynamicIslandShareUtils.INSTANCE;
                Context context = this.context;
                n.f(context, "context");
                appIcon = dynamicIslandShareUtils.drawableAddRounded(context, drawableLoadDrawable, this.context.getResources().getDimensionPixelSize(R.dimen.island_share_pic_radius));
            }
        } else {
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            Context context2 = this.context;
            n.f(context2, "context");
            appIcon = dynamicIslandUtils.getAppIcon(context2, string, dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getUserId() : null);
        }
        imageView.setImageDrawable(appIcon);
        final int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.island_share_view_width);
        final int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(R.dimen.island_share_view_height);
        viewInflate.measure(dimensionPixelSize, dimensionPixelSize2);
        viewInflate.layout(0, 0, dimensionPixelSize, dimensionPixelSize2);
        return new View.DragShadowBuilder(viewInflate, dimensionPixelSize, dimensionPixelSize2, this) { // from class: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController$dragShareCard$shadowBuilder$1
            final /* synthetic */ View $dragView;
            final /* synthetic */ int $height;
            final /* synthetic */ int $width;
            final /* synthetic */ DynamicIslandBaseContentViewController<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(viewInflate);
                this.$dragView = viewInflate;
                this.$width = dimensionPixelSize;
                this.$height = dimensionPixelSize2;
                this.this$0 = this;
            }

            @Override // android.view.View.DragShadowBuilder
            public void onDrawShadow(Canvas canvas) {
                n.g(canvas, "canvas");
                this.$dragView.draw(canvas);
            }

            @Override // android.view.View.DragShadowBuilder
            public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                n.g(outShadowSize, "outShadowSize");
                n.g(outShadowTouchPoint, "outShadowTouchPoint");
                super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
                outShadowSize.set(this.$width, this.$height);
                outShadowTouchPoint.set(this.$width - ((int) this.this$0.getMarginCardRight()), (this.$height / 2) - ((int) this.this$0.getMarginCardTop()));
            }
        };
    }

    private final Intent dragShareIntent(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandData dynamicIslandData, ShareData shareData, float f2) {
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        Bundle extras;
        Bundle extras2;
        i iVarCalculateViewPosition = calculateViewPosition(dynamicIslandBaseContentView, f2);
        int iIntValue = ((Number) iVarCalculateViewPosition.a()).intValue();
        int iIntValue2 = ((Number) iVarCalculateViewPosition.b()).intValue();
        ShareProviderData shareProviderData = DynamicIslandShareData.INSTANCE.getShareProviderData();
        BinderC0222a hyperDropInfoNotifierService = null;
        String string = (dynamicIslandData == null || (extras2 = dynamicIslandData.getExtras()) == null) ? null : extras2.getString("miui.pkg.name");
        shareProviderData.setPackageName(string);
        for (String str : dragShareSplitByLinks(String.valueOf(shareData != null ? shareData.getShareContent() : null))) {
            if (o.v(str, "http", false, 2, null)) {
                shareProviderData.setShareUrl(str);
            }
        }
        shareProviderData.setShareMessage(shareData != null ? shareData.getContent() : null);
        if (shareProviderData.getShareMessage() == null) {
            C c2 = C.f5034a;
            DynamicIslandUtils dynamicIslandUtils = DynamicIslandUtils.INSTANCE;
            Context context = this.context;
            n.f(context, "context");
            String str2 = String.format(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_WX_TITLE, Arrays.copyOf(new Object[]{dynamicIslandUtils.getAppName(context, dynamicIslandData)}, 1));
            n.f(str2, "format(...)");
            shareProviderData.setShareMessage(str2);
        }
        Bundle bundle = (dynamicIslandData == null || (extras = dynamicIslandData.getExtras()) == null) ? null : extras.getBundle("miui.focus.pics");
        Icon icon = bundle != null ? (Icon) bundle.getParcelable(DynamicIslandConstants.MEDIA_SHARE_BITMAP) : null;
        if (icon != null) {
            DynamicIslandShareUtils dynamicIslandShareUtils = DynamicIslandShareUtils.INSTANCE;
            Context context2 = this.context;
            n.f(context2, "context");
            shareProviderData.setShareIconByteArray(dynamicIslandShareUtils.iconToByteArrayAndCompress(icon, context2));
        } else {
            DynamicIslandShareUtils dynamicIslandShareUtils2 = DynamicIslandShareUtils.INSTANCE;
            DynamicIslandUtils dynamicIslandUtils2 = DynamicIslandUtils.INSTANCE;
            Context context3 = this.context;
            n.f(context3, "context");
            Bitmap bitmapDrawableToBitmap = dynamicIslandShareUtils2.drawableToBitmap(dynamicIslandUtils2.getAppIcon(context3, string, dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getUserId() : null));
            shareProviderData.setShareIconByteArray(bitmapDrawableToBitmap != null ? dynamicIslandShareUtils2.bitmapToByteArray(bitmapDrawableToBitmap) : null);
        }
        DynamicIslandUtils dynamicIslandUtils3 = DynamicIslandUtils.INSTANCE;
        Context context4 = this.context;
        n.f(context4, "context");
        shareProviderData.setTitle("【" + dynamicIslandUtils3.getAppName(context4, dynamicIslandData) + "】 " + (shareData != null ? shareData.getTitle() : null));
        shareProviderData.setValue(0);
        Bundle bundle2 = new Bundle();
        if (shareProviderData.getShareUrl() != null && this.isProviderAccessible) {
            if (dynamicIslandBaseContentView != null && (dynamicIslandEventCoordinator = dynamicIslandBaseContentView.getDynamicIslandEventCoordinator()) != null && (windowView = dynamicIslandEventCoordinator.getWindowView()) != null && (windowViewController = windowView.getWindowViewController()) != null) {
                hyperDropInfoNotifierService = windowViewController.getHyperDropInfoNotifierService();
            }
            bundle2.putBinder("IHyperDropInfoNotifier", hyperDropInfoNotifierService);
            Log.e(TAG, "Call fw to determine if the page is WX and send the provider.");
        }
        Intent intent = new Intent();
        intent.putExtra("centerX", iIntValue);
        intent.putExtra("centerY", iIntValue2);
        intent.putExtra("isMainIslandDrag", this.clickIsBigLand);
        intent.putExtras(bundle2);
        return intent;
    }

    private final List<String> dragShareSplitByLinks(String str) {
        List listB = new e("(?=https?://\\S+)").b(str, 0);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listB) {
            if (!f1.n.n((String) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void destroy() {
        this.disposables.dispose();
    }

    public final DisposableHandles getDisposables$miui_dynamicisland_release() {
        return this.disposables;
    }

    public final IslandTemplateFactory getIslandTemplateFactory() {
        return this.islandTemplateFactory;
    }

    public final float getMarginCardRight() {
        return this.marginCardRight;
    }

    public final float getMarginCardTop() {
        return this.marginCardTop;
    }

    public final T getView() {
        return this.view;
    }

    public final void onLongPressed(DynamicIslandBaseContentView dynamicIslandBaseContentView, DynamicIslandData dynamicIslandData, float f2) {
        I state;
        I state2;
        DynamicIslandEventCoordinator dynamicIslandEventCoordinator;
        Log.d(DynamicIslandContentView.TAG, "onLongPressed");
        if (dynamicIslandBaseContentView != null && (dynamicIslandEventCoordinator = dynamicIslandBaseContentView.getDynamicIslandEventCoordinator()) != null) {
            DynamicIslandEventCoordinator.dispatchEvent$default(dynamicIslandEventCoordinator, DynamicIslandEvent.IslandLongPressed.INSTANCE, null, 2, null);
        }
        IslandTemplate template = dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getTemplate() : null;
        DynamicIslandViewModel viewModel = dynamicIslandBaseContentView != null ? dynamicIslandBaseContentView.getViewModel() : null;
        if ((((viewModel == null || (state2 = viewModel.getState()) == null) ? null : (DynamicIslandState) state2.getValue()) instanceof DynamicIslandState.Expanded) || template == null) {
            return;
        }
        try {
            ShareData shareData = template.getShareData();
            if (TextUtils.isEmpty(shareData != null ? shareData.getShareContent() : null)) {
                DynamicIslandAnimationDelegate animatorDelegate = dynamicIslandBaseContentView.getAnimatorDelegate();
                if (animatorDelegate != null) {
                    DynamicIslandEventCoordinator dynamicIslandEventCoordinator2 = dynamicIslandBaseContentView.getDynamicIslandEventCoordinator();
                    animatorDelegate.isLandDragShake(dynamicIslandEventCoordinator2 != null ? dynamicIslandEventCoordinator2.getBigIsLandAndSmallIsLandList() : null);
                    return;
                }
                return;
            }
            boolean z2 = ((viewModel == null || (state = viewModel.getState()) == null) ? null : (DynamicIslandState) state.getValue()) instanceof DynamicIslandState.BigIsland;
            this.clickIsBigLand = z2;
            Log.d(DynamicIslandContentView.TAG, "onLongPressed: dragDynamicIsLandIs" + (z2 ? "BigIsland" : "SmallIsland"));
            View.DragShadowBuilder dragShadowBuilderDragShareCard = dragShareCard(dynamicIslandBaseContentView, dynamicIslandData, template.getShareData());
            Intent intentDragShareIntent = dragShareIntent(dynamicIslandBaseContentView, dynamicIslandData, template.getShareData(), f2);
            ShareData shareData2 = template.getShareData();
            dynamicIslandBaseContentView.startDragAndDrop(new ClipData("Label", new String[]{"text/plain"}, new ClipData.Item(shareData2 != null ? shareData2.getShareContent() : null, intentDragShareIntent, null)), dragShadowBuilderDragShareCard, null, dragShareAddFlags());
        } catch (Exception unused) {
            DynamicIslandAnimationDelegate animatorDelegate2 = dynamicIslandBaseContentView.getAnimatorDelegate();
            if (animatorDelegate2 != null) {
                DynamicIslandEventCoordinator dynamicIslandEventCoordinator3 = dynamicIslandBaseContentView.getDynamicIslandEventCoordinator();
                animatorDelegate2.isLandDragShake(dynamicIslandEventCoordinator3 != null ? dynamicIslandEventCoordinator3.getBigIsLandAndSmallIsLandList() : null);
            }
        }
    }

    public final void setMarginCardRight(float f2) {
        this.marginCardRight = f2;
    }

    public final void setMarginCardTop(float f2) {
        this.marginCardTop = f2;
    }
}
