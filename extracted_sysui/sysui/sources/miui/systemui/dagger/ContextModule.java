package miui.systemui.dagger;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.UserManager;
import java.util.Optional;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.ContextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class ContextModule {
    private final ContextModule$pluginContext$1 pluginContext;
    private final Context sysUIContext;

    /* JADX WARN: Type inference failed for: r3v2, types: [miui.systemui.dagger.ContextModule$pluginContext$1] */
    public ContextModule(final Context pluginContext, Context context) {
        n.g(pluginContext, "pluginContext");
        this.sysUIContext = context;
        ContextUtils.INSTANCE.fixClassLoader(pluginContext);
        this.pluginContext = new ContextWrapper(pluginContext) { // from class: miui.systemui.dagger.ContextModule$pluginContext$1
            @Override // android.content.ContextWrapper, android.content.Context
            public Context getApplicationContext() {
                return getBaseContext();
            }
        };
    }

    public final ContentResolver provideContentResolver(Context context) {
        n.g(context, "context");
        ContentResolver contentResolver = context.getContentResolver();
        n.f(contentResolver, "getContentResolver(...)");
        return contentResolver;
    }

    public final Context provideContext() {
        return this.pluginContext;
    }

    @SystemUI
    public final Optional<Context> provideOptionalSystemUIContext() {
        Optional<Context> optionalOfNullable = Optional.ofNullable(this.sysUIContext);
        n.f(optionalOfNullable, "ofNullable(...)");
        return optionalOfNullable;
    }

    @Plugin
    public final Context providePluginContext() {
        return this.pluginContext;
    }

    @SystemUI
    public final Context provideSystemUIContext() {
        Context context = this.sysUIContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("SystemUI Context is only available in SystemUI process.");
    }

    public final UserManager provideUserManager(Context context) {
        n.g(context, "context");
        Object systemService = context.getSystemService((Class<Object>) UserManager.class);
        n.f(systemService, "getSystemService(...)");
        return (UserManager) systemService;
    }

    public /* synthetic */ ContextModule(Context context, Context context2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : context2);
    }
}
