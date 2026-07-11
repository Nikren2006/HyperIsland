package miui.systemui.flashlight;

import H0.d;
import H0.e;
import H0.k;
import H0.s;
import N0.f;
import N0.l;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import android.widget.FrameLayout;
import f1.o;
import g1.AbstractC0360b0;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import g1.E;
import g1.InterfaceC0380l0;
import j1.A;
import j1.t;
import j1.y;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.flashlight.dagger.MiFlashlightComponent;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightManager {
    public static final Companion Companion = new Companion(null);
    public static final int FLASH_TORCH_LOGIC_STRENGTH_MAX_LEVEL = 100;
    private static final String KEYGUARD_SHORTCUT_LEFT = "keyguard_shortcut_left";
    private static final String KEYGUARD_SHORTCUT_RIGHT = "keyguard_shortcut_right";
    private static final String KEYGUARD_SHORTCUT_TAG = "FLASHLIGHT_SET";
    private static final String SP_KEY_IS_TORCH_ON = "mi_flashlight_is_torch_on";
    private static final String SP_KEY_STRENGTH = "mi_flashlight_strength";
    private static final String TAG = "MiFlash_MiFlashlightManager";
    private static final String WINDOW_NAME = "MiFlashlightWindow";
    private final t _miFlashlightEventFlow;
    private String cameraId;
    private final d cameraManager$delegate;
    private final Context context;
    private int curStrength;
    private Boolean isTorchOn;
    private final d layoutParams$delegate;
    private final d mainScope$delegate;
    private int maxStrength;
    private MiFlashlightComponent miFlashlightComponent;
    private final MiFlashlightComponent.Factory miFlashlightComponentFactory;
    private final y miFlashlightEventFlow;
    private InterfaceC0380l0 registerTorchCallbackJob;
    private InterfaceC0380l0 toggleJob;
    private final d windowManager$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$asyncReduceBy25$1, reason: invalid class name */
    @f(c = "miui.systemui.flashlight.MiFlashlightManager$asyncReduceBy25$1", f = "MiFlashlightManager.kt", l = {284, 294}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Function0 $asyncResult;
        float F$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function0 function0, L0.d dVar) {
            super(2, dVar);
            this.$asyncResult = function0;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightManager.this.new AnonymousClass1(this.$asyncResult, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            float f2;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                MiFlashlightManager miFlashlightManager = MiFlashlightManager.this;
                this.label = 1;
                obj = miFlashlightManager.isSupportStrengthLevel(this);
                if (obj == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    f2 = this.F$0;
                    k.b(obj);
                    MiFlashlightManager.this.saveLogicStrength(f2);
                    MiFlashlightManager.this.asyncToggleFlashLight(f2, this.$asyncResult);
                    return s.f314a;
                }
                k.b(obj);
            }
            if (((Boolean) obj).booleanValue() && MiFlashlightManager.this.isTorchOn()) {
                Log.i(MiFlashlightManager.TAG, "The temperature is too high, and the brightness of the flashlight decreases by 25%");
                float logicStrength = MiFlashlightManager.this.getLogicStrength();
                if (logicStrength == 0.01f) {
                    Function0 function0 = this.$asyncResult;
                    if (function0 != null) {
                        function0.invoke();
                    }
                } else {
                    float f3 = logicStrength - 0.25f;
                    if (f3 <= 0.01f) {
                        f3 = 0.01f;
                    }
                    t tVar = MiFlashlightManager.this._miFlashlightEventFlow;
                    MiFlashlightTempChangeEvent miFlashlightTempChangeEvent = MiFlashlightTempChangeEvent.INSTANCE;
                    miFlashlightTempChangeEvent.setProgress(f3);
                    this.F$0 = f3;
                    this.label = 2;
                    if (tVar.emit(miFlashlightTempChangeEvent, this) == objC) {
                        return objC;
                    }
                    f2 = f3;
                    MiFlashlightManager.this.saveLogicStrength(f2);
                    MiFlashlightManager.this.asyncToggleFlashLight(f2, this.$asyncResult);
                }
            } else {
                Function0 function02 = this.$asyncResult;
                if (function02 != null) {
                    function02.invoke();
                }
            }
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$asyncToggleFlashLight$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightManager$asyncToggleFlashLight$1", f = "MiFlashlightManager.kt", l = {222, 223, 225}, m = "invokeSuspend")
    public static final class C06641 extends l implements Function2 {
        final /* synthetic */ Function0 $asyncResult;
        final /* synthetic */ InterfaceC0380l0 $preJob;
        final /* synthetic */ int $strength;
        Object L$0;
        int label;
        final /* synthetic */ MiFlashlightManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06641(InterfaceC0380l0 interfaceC0380l0, MiFlashlightManager miFlashlightManager, Function0 function0, int i2, L0.d dVar) {
            super(2, dVar);
            this.$preJob = interfaceC0380l0;
            this.this$0 = miFlashlightManager;
            this.$asyncResult = function0;
            this.$strength = i2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C06641(this.$preJob, this.this$0, this.$asyncResult, this.$strength, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06641) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = M0.c.c()
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L29
                if (r1 == r4) goto L25
                if (r1 == r3) goto L1d
                if (r1 != r2) goto L15
                H0.k.b(r8)
                goto L6e
            L15:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L1d:
                java.lang.Object r1 = r7.L$0
                miui.systemui.flashlight.MiFlashlightManager r1 = (miui.systemui.flashlight.MiFlashlightManager) r1
                H0.k.b(r8)
                goto L46
            L25:
                H0.k.b(r8)
                goto L39
            L29:
                H0.k.b(r8)
                g1.l0 r8 = r7.$preJob
                if (r8 == 0) goto L39
                r7.label = r4
                java.lang.Object r8 = g1.AbstractC0388p0.e(r8, r7)
                if (r8 != r0) goto L39
                return r0
            L39:
                miui.systemui.flashlight.MiFlashlightManager r1 = r7.this$0
                r7.L$0 = r1
                r7.label = r3
                java.lang.Object r8 = r1.getCameraId(r7)
                if (r8 != r0) goto L46
                return r0
            L46:
                java.lang.String r8 = (java.lang.String) r8
                miui.systemui.flashlight.MiFlashlightManager.access$setCameraId$p(r1, r8)
                miui.systemui.flashlight.MiFlashlightManager r8 = r7.this$0
                java.lang.String r8 = miui.systemui.flashlight.MiFlashlightManager.access$getCameraId$p(r8)
                if (r8 == 0) goto L6e
                int r1 = r7.$strength
                miui.systemui.flashlight.MiFlashlightManager r3 = r7.this$0
                miui.systemui.coroutines.Dispatchers r4 = miui.systemui.coroutines.Dispatchers.INSTANCE
                g1.b0 r4 = r4.getIO()
                miui.systemui.flashlight.MiFlashlightManager$asyncToggleFlashLight$1$1$1 r5 = new miui.systemui.flashlight.MiFlashlightManager$asyncToggleFlashLight$1$1$1
                r6 = 0
                r5.<init>(r8, r1, r3, r6)
                r7.L$0 = r6
                r7.label = r2
                java.lang.Object r8 = g1.AbstractC0367f.c(r4, r5, r7)
                if (r8 != r0) goto L6e
                return r0
            L6e:
                kotlin.jvm.functions.Function0 r7 = r7.$asyncResult
                if (r7 == 0) goto L75
                r7.invoke()
            L75:
                H0.s r7 = H0.s.f314a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.flashlight.MiFlashlightManager.C06641.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$getCameraId$2, reason: invalid class name */
    @f(c = "miui.systemui.flashlight.MiFlashlightManager$getCameraId$2", f = "MiFlashlightManager.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightManager.this.new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            if (MiFlashlightManager.this.cameraId != null) {
                return MiFlashlightManager.this.cameraId;
            }
            try {
                String[] cameraIdList = MiFlashlightManager.this.getCameraManager().getCameraIdList();
                n.d(cameraIdList);
                int iIntValue = 0;
                for (String str : cameraIdList) {
                    CameraCharacteristics cameraCharacteristics = MiFlashlightManager.this.getCameraManager().getCameraCharacteristics(str);
                    Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if (bool != null && bool.booleanValue() && num != null) {
                        int iIntValue2 = num.intValue();
                        int iIntValue3 = 1;
                        if (iIntValue2 == 1) {
                            MiFlashlightManager miFlashlightManager = MiFlashlightManager.this;
                            Integer num2 = (Integer) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_STRENGTH_DEFAULT_LEVEL);
                            if (num2 != null) {
                                iIntValue = num2.intValue();
                            }
                            miFlashlightManager.curStrength = iIntValue;
                            MiFlashlightManager miFlashlightManager2 = MiFlashlightManager.this;
                            Integer num3 = (Integer) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_STRENGTH_MAXIMUM_LEVEL);
                            if (num3 != null) {
                                iIntValue3 = num3.intValue();
                            }
                            miFlashlightManager2.maxStrength = iIntValue3;
                            Log.i(MiFlashlightManager.TAG, "getCameraId: cameraId = " + str + " defaultStrength= " + MiFlashlightManager.this.curStrength + "  maxStrength = " + MiFlashlightManager.this.maxStrength);
                            return str;
                        }
                    }
                }
                return null;
            } catch (Exception e2) {
                Log.w(MiFlashlightManager.TAG, e2);
                return null;
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$isSupportStrengthLevel$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightManager", f = "MiFlashlightManager.kt", l = {332}, m = "isSupportStrengthLevel")
    public static final class C06651 extends N0.d {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06651(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MiFlashlightManager.this.isSupportStrengthLevel(this);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$registerTorchCallback$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightManager$registerTorchCallback$1", f = "MiFlashlightManager.kt", l = {376}, m = "invokeSuspend")
    public static final class C06661 extends l implements Function2 {
        final /* synthetic */ CameraManager.TorchCallback $callback;
        final /* synthetic */ Handler $handler;
        int label;

        /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$registerTorchCallback$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.flashlight.MiFlashlightManager$registerTorchCallback$1$1", f = "MiFlashlightManager.kt", l = {}, m = "invokeSuspend")
        public static final class C01461 extends l implements Function2 {
            final /* synthetic */ CameraManager.TorchCallback $callback;
            final /* synthetic */ Handler $handler;
            int label;
            final /* synthetic */ MiFlashlightManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01461(MiFlashlightManager miFlashlightManager, CameraManager.TorchCallback torchCallback, Handler handler, L0.d dVar) {
                super(2, dVar);
                this.this$0 = miFlashlightManager;
                this.$callback = torchCallback;
                this.$handler = handler;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C01461(this.this$0, this.$callback, this.$handler, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((C01461) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                this.this$0.getCameraManager().registerTorchCallback(this.$callback, this.$handler);
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06661(CameraManager.TorchCallback torchCallback, Handler handler, L0.d dVar) {
            super(2, dVar);
            this.$callback = torchCallback;
            this.$handler = handler;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightManager.this.new C06661(this.$callback, this.$handler, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06661) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
                C01461 c01461 = new C01461(MiFlashlightManager.this, this.$callback, this.$handler, null);
                this.label = 1;
                if (AbstractC0367f.c(io, c01461, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$unregisterTorchCallback$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.flashlight.MiFlashlightManager$unregisterTorchCallback$1", f = "MiFlashlightManager.kt", l = {384, 385}, m = "invokeSuspend")
    public static final class C06671 extends l implements Function2 {
        final /* synthetic */ CameraManager.TorchCallback $callback;
        int label;

        /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightManager$unregisterTorchCallback$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.flashlight.MiFlashlightManager$unregisterTorchCallback$1$1", f = "MiFlashlightManager.kt", l = {}, m = "invokeSuspend")
        public static final class C01471 extends l implements Function2 {
            final /* synthetic */ CameraManager.TorchCallback $callback;
            int label;
            final /* synthetic */ MiFlashlightManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01471(MiFlashlightManager miFlashlightManager, CameraManager.TorchCallback torchCallback, L0.d dVar) {
                super(2, dVar);
                this.this$0 = miFlashlightManager;
                this.$callback = torchCallback;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C01471(this.this$0, this.$callback, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((C01471) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                this.this$0.getCameraManager().unregisterTorchCallback(this.$callback);
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06671(CameraManager.TorchCallback torchCallback, L0.d dVar) {
            super(2, dVar);
            this.$callback = torchCallback;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return MiFlashlightManager.this.new C06671(this.$callback, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06671) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0380l0 interfaceC0380l0 = MiFlashlightManager.this.registerTorchCallbackJob;
                if (interfaceC0380l0 != null) {
                    this.label = 1;
                    if (interfaceC0380l0.c(this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k.b(obj);
                    return s.f314a;
                }
                k.b(obj);
            }
            AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
            C01471 c01471 = new C01471(MiFlashlightManager.this, this.$callback, null);
            this.label = 2;
            if (AbstractC0367f.c(io, c01471, this) == objC) {
                return objC;
            }
            return s.f314a;
        }
    }

    public MiFlashlightManager(Context context, MiFlashlightComponent.Factory miFlashlightComponentFactory) {
        n.g(context, "context");
        n.g(miFlashlightComponentFactory, "miFlashlightComponentFactory");
        this.context = context;
        this.miFlashlightComponentFactory = miFlashlightComponentFactory;
        this.windowManager$delegate = e.b(new MiFlashlightManager$windowManager$2(this));
        this.layoutParams$delegate = e.b(MiFlashlightManager$layoutParams$2.INSTANCE);
        this.mainScope$delegate = e.b(MiFlashlightManager$mainScope$2.INSTANCE);
        this.cameraManager$delegate = e.b(new MiFlashlightManager$cameraManager$2(this));
        this.maxStrength = 1;
        t tVarB = A.b(0, 0, null, 6, null);
        this._miFlashlightEventFlow = tVarB;
        this.miFlashlightEventFlow = tVarB;
    }

    public static final /* synthetic */ Context access$getContext$p(MiFlashlightManager miFlashlightManager) {
        return miFlashlightManager.context;
    }

    public static /* synthetic */ void asyncOperate$default(MiFlashlightManager miFlashlightManager, boolean z2, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        miFlashlightManager.asyncOperate(z2, function0);
    }

    public static /* synthetic */ InterfaceC0380l0 asyncReduceBy25$default(MiFlashlightManager miFlashlightManager, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function0 = null;
        }
        return miFlashlightManager.asyncReduceBy25(function0);
    }

    public static /* synthetic */ void asyncToggleFlashLight$default(MiFlashlightManager miFlashlightManager, float f2, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        miFlashlightManager.asyncToggleFlashLight(f2, function0);
    }

    public static /* synthetic */ void dismiss$default(MiFlashlightManager miFlashlightManager, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = -1;
        }
        miFlashlightManager.dismiss(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CameraManager getCameraManager() {
        return (CameraManager) this.cameraManager$delegate.getValue();
    }

    private final WindowManager.LayoutParams getLayoutParams() {
        return (WindowManager.LayoutParams) this.layoutParams$delegate.getValue();
    }

    private final E getMainScope() {
        return (E) this.mainScope$delegate.getValue();
    }

    public static /* synthetic */ MiFlashlightLayout getMiFlashlightLayout$default(MiFlashlightManager miFlashlightManager, Context context, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        return miFlashlightManager.getMiFlashlightLayout(context, z2);
    }

    private final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    private final boolean isNoExitAnim() {
        String string = Settings.Secure.getString(this.context.getContentResolver(), KEYGUARD_SHORTCUT_LEFT);
        String string2 = Settings.Secure.getString(this.context.getContentResolver(), KEYGUARD_SHORTCUT_RIGHT);
        Log.i(TAG, "keyguard_shortcut_left = " + string + " \nkeyguard_shortcut_right = " + string2);
        Object systemService = this.context.getSystemService("keyguard");
        n.e(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        if (!((KeyguardManager) systemService).isKeyguardLocked()) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" ");
        sb.append(string2);
        return o.v(sb.toString(), KEYGUARD_SHORTCUT_TAG, false, 2, null);
    }

    public static /* synthetic */ void showFlashlight$default(MiFlashlightManager miFlashlightManager, boolean z2, FrameLayout frameLayout, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        if ((i2 & 2) != 0) {
            frameLayout = null;
        }
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        miFlashlightManager.showFlashlight(z2, frameLayout, z3);
    }

    public final void asyncOperate(boolean z2, Function0 function0) {
        asyncToggleFlashLight(z2 ? getLogicStrength() : 0.0f, function0);
    }

    public final InterfaceC0380l0 asyncReduceBy25(Function0 function0) {
        return AbstractC0369g.b(getMainScope(), null, null, new AnonymousClass1(function0, null), 3, null);
    }

    public final void asyncToggleFlashLight(float f2, Function0 function0) {
        int i2 = (int) (this.maxStrength * f2);
        Log.i(TAG, "toggleFlashLight Torch strength target " + i2 + " mCurStrength=" + this.curStrength);
        this.toggleJob = AbstractC0369g.b(getMainScope(), null, null, new C06641(this.toggleJob, this, function0, i2, null), 3, null);
    }

    public final void dismiss() {
        dismiss$default(this, 0, 1, null);
    }

    public final Object getCameraId(L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new AnonymousClass2(null), dVar);
    }

    public final float getLogicStrength() {
        float f2 = Settings.Global.getFloat(this.context.getContentResolver(), SP_KEY_STRENGTH, 1.0f);
        int i2 = this.maxStrength;
        if (f2 < 1.0f / i2) {
            return 1.0f / i2;
        }
        if (f2 > 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public final y getMiFlashlightEventFlow() {
        return this.miFlashlightEventFlow;
    }

    @SuppressLint({"InflateParams"})
    public final MiFlashlightLayout getMiFlashlightLayout(Context context, boolean z2) {
        n.g(context, "context");
        if (this.miFlashlightComponent != null) {
            dismiss$default(this, 0, 1, null);
        }
        MiFlashlightLayout miFlashlightLayout = new MiFlashlightLayout(context, null, 2, null);
        miFlashlightLayout.setNeedAnim(z2);
        MiFlashlightComponent miFlashlightComponentCreate = this.miFlashlightComponentFactory.create(miFlashlightLayout);
        miFlashlightComponentCreate.createController();
        this.miFlashlightComponent = miFlashlightComponentCreate;
        return miFlashlightLayout;
    }

    public final Object hideFlashlight() {
        View rootView;
        Object objB;
        MiFlashlightComponent miFlashlightComponent = this.miFlashlightComponent;
        if (miFlashlightComponent == null || (rootView = miFlashlightComponent.getRootView()) == null) {
            return null;
        }
        if (isNoExitAnim()) {
            dismiss(rootView.hashCode());
            objB = s.f314a;
        } else {
            objB = AbstractC0369g.b(getMainScope(), null, null, new MiFlashlightManager$hideFlashlight$1$1(this, rootView, null), 3, null);
        }
        return objB;
    }

    public final boolean isHasFlashlightWindow() {
        return this.miFlashlightComponent != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isSupportStrengthLevel(L0.d r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof miui.systemui.flashlight.MiFlashlightManager.C06651
            if (r0 == 0) goto L13
            r0 = r5
            miui.systemui.flashlight.MiFlashlightManager$isSupportStrengthLevel$1 r0 = (miui.systemui.flashlight.MiFlashlightManager.C06651) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            miui.systemui.flashlight.MiFlashlightManager$isSupportStrengthLevel$1 r0 = new miui.systemui.flashlight.MiFlashlightManager$isSupportStrengthLevel$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r4 = r0.L$1
            miui.systemui.flashlight.MiFlashlightManager r4 = (miui.systemui.flashlight.MiFlashlightManager) r4
            java.lang.Object r0 = r0.L$0
            miui.systemui.flashlight.MiFlashlightManager r0 = (miui.systemui.flashlight.MiFlashlightManager) r0
            H0.k.b(r5)
            goto L4a
        L31:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L39:
            H0.k.b(r5)
            r0.L$0 = r4
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r5 = r4.getCameraId(r0)
            if (r5 != r1) goto L49
            return r1
        L49:
            r0 = r4
        L4a:
            java.lang.String r5 = (java.lang.String) r5
            r4.cameraId = r5
            int r4 = r0.maxStrength
            r5 = 100
            if (r4 < r5) goto L55
            goto L56
        L55:
            r3 = 0
        L56:
            java.lang.Boolean r4 = N0.b.a(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.flashlight.MiFlashlightManager.isSupportStrengthLevel(L0.d):java.lang.Object");
    }

    public final boolean isTorchOn() {
        if (this.isTorchOn == null) {
            this.isTorchOn = Boolean.valueOf(Settings.Global.getInt(this.context.getContentResolver(), SP_KEY_IS_TORCH_ON, 0) == 1);
        }
        Log.i(TAG, "isTorchOn: " + this.isTorchOn);
        return n.c(this.isTorchOn, Boolean.TRUE);
    }

    public final void onLowPowerControl(int i2, int i3) {
        if (i2 > 5 || i3 != 1) {
            return;
        }
        hideFlashlight();
    }

    public final void registerTorchCallback(CameraManager.TorchCallback callback, Handler handler) {
        n.g(callback, "callback");
        n.g(handler, "handler");
        this.registerTorchCallbackJob = AbstractC0369g.b(getMainScope(), null, null, new C06661(callback, handler, null), 3, null);
    }

    public final void removeFocusNotification() {
        Log.d(TAG, "removeFocusNotification");
        this.context.stopService(new Intent(this.context, (Class<?>) MiFlashlightNotificationService.class));
    }

    public final void resetStatus() {
        saveTorchStatus(false);
        removeFocusNotification();
    }

    public final void saveLogicStrength(float f2) {
        Settings.Global.putFloat(this.context.getContentResolver(), SP_KEY_STRENGTH, f2);
    }

    public final void saveTorchStatus(boolean z2) {
        if (n.c(this.isTorchOn, Boolean.valueOf(z2))) {
            return;
        }
        this.isTorchOn = Boolean.valueOf(z2);
        Settings.Global.putInt(this.context.getContentResolver(), SP_KEY_IS_TORCH_ON, z2 ? 1 : 0);
        Log.i(TAG, "saveTorchStatus isTorchOn: " + this.isTorchOn);
    }

    public final void sendFocusNotification() {
        Log.d(TAG, "sendFocusNotification");
        this.context.startForegroundService(new Intent(this.context, (Class<?>) MiFlashlightNotificationService.class));
    }

    @SuppressLint({"InflateParams"})
    public final void showFlashlight() {
        showFlashlight$default(this, false, null, false, 7, null);
    }

    public final void unregisterTorchCallback(CameraManager.TorchCallback callback) {
        n.g(callback, "callback");
        AbstractC0369g.b(getMainScope(), null, null, new C06671(callback, null), 3, null);
    }

    public final void dismiss(int i2) {
        View rootView;
        MiFlashlightComponent miFlashlightComponent = this.miFlashlightComponent;
        Log.i(TAG, "dismiss miFlashlightLayoutHashCode: " + i2 + " current: " + ((miFlashlightComponent == null || (rootView = miFlashlightComponent.getRootView()) == null) ? null : Integer.valueOf(rootView.hashCode())));
        MiFlashlightComponent miFlashlightComponent2 = this.miFlashlightComponent;
        if (miFlashlightComponent2 != null) {
            if (i2 == -1 || i2 == miFlashlightComponent2.getRootView().hashCode()) {
                if (miFlashlightComponent2.getRootView().getParent() instanceof ViewRootImpl) {
                    getWindowManager().removeView(miFlashlightComponent2.getRootView());
                } else {
                    ViewParent parent = miFlashlightComponent2.getRootView().getParent();
                    ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                    if (viewGroup != null) {
                        viewGroup.removeAllViews();
                    }
                }
                this.miFlashlightComponent = null;
                AbstractC0369g.b(getMainScope(), null, null, new MiFlashlightManager$dismiss$1$1(this, miFlashlightComponent2, null), 3, null);
            }
        }
    }

    @SuppressLint({"InflateParams"})
    public final void showFlashlight(boolean z2) {
        showFlashlight$default(this, z2, null, false, 6, null);
    }

    @SuppressLint({"InflateParams"})
    public final void showFlashlight(boolean z2, FrameLayout frameLayout) {
        showFlashlight$default(this, z2, frameLayout, false, 4, null);
    }

    @SuppressLint({"InflateParams"})
    public final void showFlashlight(boolean z2, FrameLayout frameLayout, boolean z3) {
        if (this.miFlashlightComponent == null || z2) {
            dismiss$default(this, 0, 1, null);
            Context context = frameLayout != null ? frameLayout.getContext() : null;
            if (context == null) {
                context = this.context;
            }
            MiFlashlightLayout miFlashlightLayout = getMiFlashlightLayout(context, z3);
            if (frameLayout != null) {
                frameLayout.addView(miFlashlightLayout);
                return;
            }
            try {
                getWindowManager().addView(miFlashlightLayout, getLayoutParams());
                s sVar = s.f314a;
            } catch (Exception e2) {
                Log.e(TAG, "add failed.", e2);
            }
        }
    }
}
