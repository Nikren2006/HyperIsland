package miui.systemui.dagger;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ContextComponentResolver implements ContextComponentHelper {
    private final Optional<Map<Class<?>, G0.a>> activityCreators;
    private final Optional<Map<Class<?>, G0.a>> broadcastReceiverCreators;
    private final Optional<Map<Class<?>, G0.a>> serviceCreators;

    /* JADX INFO: renamed from: miui.systemui.dagger.ContextComponentResolver$resolveActivity$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        final /* synthetic */ String $className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str) {
            super(1);
            this.$className = str;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Activity invoke(Map<Class<?>, ? extends G0.a> map) {
            ContextComponentResolver contextComponentResolver = ContextComponentResolver.this;
            String str = this.$className;
            n.d(map);
            return (Activity) contextComponentResolver.resolve(str, map);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dagger.ContextComponentResolver$resolveBroadcastReceiver$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04891 extends o implements Function1 {
        final /* synthetic */ String $className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04891(String str) {
            super(1);
            this.$className = str;
        }

        @Override // kotlin.jvm.functions.Function1
        public final BroadcastReceiver invoke(Map<Class<?>, ? extends G0.a> map) {
            ContextComponentResolver contextComponentResolver = ContextComponentResolver.this;
            String str = this.$className;
            n.d(map);
            return (BroadcastReceiver) contextComponentResolver.resolve(str, map);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dagger.ContextComponentResolver$resolveService$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04901 extends o implements Function1 {
        final /* synthetic */ String $className;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04901(String str) {
            super(1);
            this.$className = str;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Service invoke(Map<Class<?>, ? extends G0.a> map) {
            ContextComponentResolver contextComponentResolver = ContextComponentResolver.this;
            String str = this.$className;
            n.d(map);
            return (Service) contextComponentResolver.resolve(str, map);
        }
    }

    public ContextComponentResolver(Optional<Map<Class<?>, G0.a>> activityCreators, Optional<Map<Class<?>, G0.a>> serviceCreators, Optional<Map<Class<?>, G0.a>> broadcastReceiverCreators) {
        n.g(activityCreators, "activityCreators");
        n.g(serviceCreators, "serviceCreators");
        n.g(broadcastReceiverCreators, "broadcastReceiverCreators");
        this.activityCreators = activityCreators;
        this.serviceCreators = serviceCreators;
        this.broadcastReceiverCreators = broadcastReceiverCreators;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> T resolve(String str, Map<Class<?>, ? extends G0.a> map) {
        try {
            G0.a aVar = map.get(Class.forName(str));
            if (aVar != null) {
                return (T) aVar.get();
            }
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Activity resolveActivity$lambda$0(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return (Activity) tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BroadcastReceiver resolveBroadcastReceiver$lambda$1(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return (BroadcastReceiver) tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Service resolveService$lambda$2(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return (Service) tmp0.invoke(obj);
    }

    @Override // miui.systemui.dagger.ContextComponentHelper
    public Activity resolveActivity(String className) {
        n.g(className, "className");
        Optional<Map<Class<?>, G0.a>> optional = this.activityCreators;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(className);
        return (Activity) optional.map(new Function() { // from class: miui.systemui.dagger.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ContextComponentResolver.resolveActivity$lambda$0(anonymousClass1, obj);
            }
        }).orElse(null);
    }

    @Override // miui.systemui.dagger.ContextComponentHelper
    public BroadcastReceiver resolveBroadcastReceiver(String className) {
        n.g(className, "className");
        Optional<Map<Class<?>, G0.a>> optional = this.broadcastReceiverCreators;
        final C04891 c04891 = new C04891(className);
        return (BroadcastReceiver) optional.map(new Function() { // from class: miui.systemui.dagger.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ContextComponentResolver.resolveBroadcastReceiver$lambda$1(c04891, obj);
            }
        }).orElse(null);
    }

    @Override // miui.systemui.dagger.ContextComponentHelper
    public Service resolveService(String className) {
        n.g(className, "className");
        Optional<Map<Class<?>, G0.a>> optional = this.serviceCreators;
        final C04901 c04901 = new C04901(className);
        return (Service) optional.map(new Function() { // from class: miui.systemui.dagger.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ContextComponentResolver.resolveService$lambda$2(c04901, obj);
            }
        }).orElse(null);
    }
}
