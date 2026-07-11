package androidx.window.layout;

import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.layout.WindowLayoutComponent;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SafeWindowLayoutComponentProvider$windowLayoutComponent$2 extends o implements Function0 {
    public static final SafeWindowLayoutComponentProvider$windowLayoutComponent$2 INSTANCE = new SafeWindowLayoutComponentProvider$windowLayoutComponent$2();

    public SafeWindowLayoutComponentProvider$windowLayoutComponent$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final WindowLayoutComponent invoke() {
        ClassLoader classLoader = SafeWindowLayoutComponentProvider.class.getClassLoader();
        if (classLoader == null || !SafeWindowLayoutComponentProvider.INSTANCE.canUseWindowLayoutComponent(classLoader)) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
