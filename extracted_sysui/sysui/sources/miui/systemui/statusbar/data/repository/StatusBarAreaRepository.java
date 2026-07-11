package miui.systemui.statusbar.data.repository;

import android.content.Context;
import android.graphics.Rect;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.settings.data.repository.OneHandedModeRepository;
import miui.systemui.ui.data.repository.ConfigurationRepository;

/* JADX INFO: loaded from: classes4.dex */
public final class StatusBarAreaRepository {
    private final I statusBarArea;

    public StatusBarAreaRepository(@Plugin E scope, Context context, ConfigurationRepository configurationRepository, OneHandedModeRepository oneHandedModeRepository) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(configurationRepository, "configurationRepository");
        n.g(oneHandedModeRepository, "oneHandedModeRepository");
        this.statusBarArea = AbstractC0420h.B(AbstractC0420h.i(AbstractC0420h.y(configurationRepository.getOnConfigChanged(), new StatusBarAreaRepository$statusBarArea$1(null)), oneHandedModeRepository.isActivated(), oneHandedModeRepository.getOffset(), new StatusBarAreaRepository$statusBarArea$2(context, null)), scope, E.a.b(j1.E.f4648a, 0L, 0L, 3, null), new Rect());
    }

    public final I getStatusBarArea() {
        return this.statusBarArea;
    }
}
