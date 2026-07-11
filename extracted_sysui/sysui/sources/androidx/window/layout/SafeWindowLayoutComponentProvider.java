package androidx.window.layout;

import H0.d;
import H0.e;
import androidx.annotation.RequiresApi;
import androidx.window.extensions.layout.WindowLayoutComponent;
import d1.InterfaceC0324c;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SafeWindowLayoutComponentProvider {
    public static final SafeWindowLayoutComponentProvider INSTANCE = new SafeWindowLayoutComponentProvider();
    private static final d windowLayoutComponent$delegate = e.b(SafeWindowLayoutComponentProvider$windowLayoutComponent$2.INSTANCE);

    /* JADX INFO: renamed from: androidx.window.layout.SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ ClassLoader $classLoader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ClassLoader classLoader) {
            super(0);
            this.$classLoader = classLoader;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Boolean invoke() throws java.lang.NoSuchMethodException {
            /*
                r5 = this;
                androidx.window.layout.SafeWindowLayoutComponentProvider r0 = androidx.window.layout.SafeWindowLayoutComponentProvider.INSTANCE
                java.lang.ClassLoader r5 = r5.$classLoader
                java.lang.Class r5 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$foldingFeatureClass(r0, r5)
                java.lang.String r1 = "getBounds"
                r2 = 0
                java.lang.reflect.Method r1 = r5.getMethod(r1, r2)
                java.lang.String r3 = "getType"
                java.lang.reflect.Method r3 = r5.getMethod(r3, r2)
                java.lang.String r4 = "getState"
                java.lang.reflect.Method r5 = r5.getMethod(r4, r2)
                java.lang.String r2 = "getBoundsMethod"
                kotlin.jvm.internal.n.f(r1, r2)
                java.lang.Class<android.graphics.Rect> r2 = android.graphics.Rect.class
                d1.c r2 = kotlin.jvm.internal.z.b(r2)
                boolean r2 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$doesReturn(r0, r1, r2)
                if (r2 == 0) goto L60
                boolean r1 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$isPublic(r0, r1)
                if (r1 == 0) goto L60
                java.lang.String r1 = "getTypeMethod"
                kotlin.jvm.internal.n.f(r3, r1)
                java.lang.Class r1 = java.lang.Integer.TYPE
                d1.c r2 = kotlin.jvm.internal.z.b(r1)
                boolean r2 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$doesReturn(r0, r3, r2)
                if (r2 == 0) goto L60
                boolean r2 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$isPublic(r0, r3)
                if (r2 == 0) goto L60
                java.lang.String r2 = "getStateMethod"
                kotlin.jvm.internal.n.f(r5, r2)
                d1.c r1 = kotlin.jvm.internal.z.b(r1)
                boolean r1 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$doesReturn(r0, r5, r1)
                if (r1 == 0) goto L60
                boolean r5 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$isPublic(r0, r5)
                if (r5 == 0) goto L60
                r5 = 1
                goto L61
            L60:
                r5 = 0
            L61:
                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.SafeWindowLayoutComponentProvider.AnonymousClass1.invoke():java.lang.Boolean");
        }
    }

    /* JADX INFO: renamed from: androidx.window.layout.SafeWindowLayoutComponentProvider$isWindowExtensionsValid$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02191 extends o implements Function0 {
        final /* synthetic */ ClassLoader $classLoader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02191(ClassLoader classLoader) {
            super(0);
            this.$classLoader = classLoader;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x002d  */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Boolean invoke() throws java.lang.NoSuchMethodException {
            /*
                r4 = this;
                androidx.window.layout.SafeWindowLayoutComponentProvider r0 = androidx.window.layout.SafeWindowLayoutComponentProvider.INSTANCE
                java.lang.ClassLoader r1 = r4.$classLoader
                java.lang.Class r1 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$windowExtensionsClass(r0, r1)
                java.lang.String r2 = "getWindowLayoutComponent"
                r3 = 0
                java.lang.reflect.Method r1 = r1.getMethod(r2, r3)
                java.lang.ClassLoader r4 = r4.$classLoader
                java.lang.Class r4 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$windowLayoutComponentClass(r0, r4)
                java.lang.String r2 = "getWindowLayoutComponentMethod"
                kotlin.jvm.internal.n.f(r1, r2)
                boolean r2 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$isPublic(r0, r1)
                if (r2 == 0) goto L2d
                java.lang.String r2 = "windowLayoutComponentClass"
                kotlin.jvm.internal.n.f(r4, r2)
                boolean r4 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$doesReturn(r0, r1, r4)
                if (r4 == 0) goto L2d
                r4 = 1
                goto L2e
            L2d:
                r4 = 0
            L2e:
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.SafeWindowLayoutComponentProvider.C02191.invoke():java.lang.Boolean");
        }
    }

    /* JADX INFO: renamed from: androidx.window.layout.SafeWindowLayoutComponentProvider$isWindowLayoutComponentValid$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02201 extends o implements Function0 {
        final /* synthetic */ ClassLoader $classLoader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02201(ClassLoader classLoader) {
            super(0);
            this.$classLoader = classLoader;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0038  */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Boolean invoke() throws java.lang.NoSuchMethodException {
            /*
                r4 = this;
                androidx.window.layout.SafeWindowLayoutComponentProvider r0 = androidx.window.layout.SafeWindowLayoutComponentProvider.INSTANCE
                java.lang.ClassLoader r4 = r4.$classLoader
                java.lang.Class r4 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$windowLayoutComponentClass(r0, r4)
                java.lang.Class<android.app.Activity> r1 = android.app.Activity.class
                java.lang.Class<java.util.function.Consumer> r2 = java.util.function.Consumer.class
                java.lang.Class[] r1 = new java.lang.Class[]{r1, r2}
                java.lang.String r3 = "addWindowLayoutInfoListener"
                java.lang.reflect.Method r1 = r4.getMethod(r3, r1)
                java.lang.String r3 = "removeWindowLayoutInfoListener"
                java.lang.Class[] r2 = new java.lang.Class[]{r2}
                java.lang.reflect.Method r4 = r4.getMethod(r3, r2)
                java.lang.String r2 = "addListenerMethod"
                kotlin.jvm.internal.n.f(r1, r2)
                boolean r1 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$isPublic(r0, r1)
                if (r1 == 0) goto L38
                java.lang.String r1 = "removeListenerMethod"
                kotlin.jvm.internal.n.f(r4, r1)
                boolean r4 = androidx.window.layout.SafeWindowLayoutComponentProvider.access$isPublic(r0, r4)
                if (r4 == 0) goto L38
                r4 = 1
                goto L39
            L38:
                r4 = 0
            L39:
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.SafeWindowLayoutComponentProvider.C02201.invoke():java.lang.Boolean");
        }
    }

    /* JADX INFO: renamed from: androidx.window.layout.SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02211 extends o implements Function0 {
        final /* synthetic */ ClassLoader $classLoader;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02211(ClassLoader classLoader) {
            super(0);
            this.$classLoader = classLoader;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() throws NoSuchMethodException {
            SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = SafeWindowLayoutComponentProvider.INSTANCE;
            Method getWindowExtensionsMethod = safeWindowLayoutComponentProvider.windowExtensionsProviderClass(this.$classLoader).getDeclaredMethod("getWindowExtensions", null);
            Class windowExtensionsClass = safeWindowLayoutComponentProvider.windowExtensionsClass(this.$classLoader);
            n.f(getWindowExtensionsMethod, "getWindowExtensionsMethod");
            n.f(windowExtensionsClass, "windowExtensionsClass");
            return Boolean.valueOf(safeWindowLayoutComponentProvider.doesReturn(getWindowExtensionsMethod, (Class<?>) windowExtensionsClass) && safeWindowLayoutComponentProvider.isPublic(getWindowExtensionsMethod));
        }
    }

    private SafeWindowLayoutComponentProvider() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean canUseWindowLayoutComponent(ClassLoader classLoader) {
        return isWindowLayoutProviderValid(classLoader) && isWindowExtensionsValid(classLoader) && isWindowLayoutComponentValid(classLoader) && isFoldingFeatureValid(classLoader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean doesReturn(Method method, InterfaceC0324c interfaceC0324c) {
        return doesReturn(method, U0.a.a(interfaceC0324c));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> foldingFeatureClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.layout.FoldingFeature");
    }

    private final boolean isFoldingFeatureValid(ClassLoader classLoader) {
        return validate(new AnonymousClass1(classLoader));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    private final boolean isWindowExtensionsValid(ClassLoader classLoader) {
        return validate(new C02191(classLoader));
    }

    @RequiresApi(24)
    private final boolean isWindowLayoutComponentValid(ClassLoader classLoader) {
        return validate(new C02201(classLoader));
    }

    private final boolean isWindowLayoutProviderValid(ClassLoader classLoader) {
        return validate(new C02211(classLoader));
    }

    private final boolean validate(Function0 function0) {
        try {
            return ((Boolean) function0.invoke()).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> windowExtensionsClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.WindowExtensions");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> windowExtensionsProviderClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> windowLayoutComponentClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        return (WindowLayoutComponent) windowLayoutComponent$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean doesReturn(Method method, Class<?> cls) {
        return method.getReturnType().equals(cls);
    }
}
