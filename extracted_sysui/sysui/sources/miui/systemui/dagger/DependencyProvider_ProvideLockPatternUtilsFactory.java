package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
public final class DependencyProvider_ProvideLockPatternUtilsFactory implements F0.e {
    private final G0.a contextProvider;
    private final DependencyProvider module;

    public DependencyProvider_ProvideLockPatternUtilsFactory(DependencyProvider dependencyProvider, G0.a aVar) {
        this.module = dependencyProvider;
        this.contextProvider = aVar;
    }

    public static DependencyProvider_ProvideLockPatternUtilsFactory create(DependencyProvider dependencyProvider, G0.a aVar) {
        return new DependencyProvider_ProvideLockPatternUtilsFactory(dependencyProvider, aVar);
    }

    public static LockPatternUtils provideLockPatternUtils(DependencyProvider dependencyProvider, Context context) {
        return (LockPatternUtils) i.d(dependencyProvider.provideLockPatternUtils(context));
    }

    @Override // G0.a
    public LockPatternUtils get() {
        return provideLockPatternUtils(this.module, (Context) this.contextProvider.get());
    }
}
