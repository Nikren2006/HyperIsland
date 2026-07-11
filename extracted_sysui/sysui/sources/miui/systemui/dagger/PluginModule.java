package miui.systemui.dagger;

import android.content.Context;
import java.util.Map;
import java.util.Optional;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;
import miui.systemui.dagger.qualifiers.RunningAsPlugin;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.devicecontrols.DeviceControlsPresenter;
import miui.systemui.plugins.data.repository.PluginInstancesRepository;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.HapticFeedbackImpl;
import miui.systemui.util.InjectionInflationController;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.util.SystemUIResourcesHelperImpl;

/* JADX INFO: loaded from: classes.dex */
public abstract class PluginModule {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @RunningAsPlugin
        public final boolean providesRunningAsPlugin(@SystemUI Optional<Context> optionalSysuiContext) {
            n.g(optionalSysuiContext, "optionalSysuiContext");
            return optionalSysuiContext.isPresent();
        }

        private Companion() {
        }
    }

    public abstract ContextComponentHelper bindComponentHelper(ContextComponentResolver contextComponentResolver);

    public abstract HapticFeedback bindHapticFeedBack(HapticFeedbackImpl hapticFeedbackImpl);

    public abstract InjectionInflationController bindInjectionInflationController(InjectionInflationControllerImpl injectionInflationControllerImpl);

    public abstract SystemUIResourcesHelper bindSystemUIResourceHelper(SystemUIResourcesHelperImpl systemUIResourcesHelperImpl);

    public abstract PluginInstancesRepository bindsPluginInstanceRepository(PluginInstancesRepositoryImpl pluginInstancesRepositoryImpl);

    public abstract Map<Class<?>, G0.a> optionalActivities();

    public abstract Map<Class<?>, G0.a> optionalBroadcastReceivers();

    public abstract DeviceControlsPresenter optionalDeviceControlsPresenter();

    public abstract MediaPlayerAdapter optionalMediaPlayerAdapter();

    public abstract Map<Class<?>, G0.a> optionalServices();
}
