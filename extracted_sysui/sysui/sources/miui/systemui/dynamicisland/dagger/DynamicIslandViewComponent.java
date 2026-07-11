package miui.systemui.dynamicisland.dagger;

import java.util.Map;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.data.repository.DynamicIslandSizeRepository;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public interface DynamicIslandViewComponent {

    public interface Factory {
        DynamicIslandViewComponent create(DynamicIslandWindowView dynamicIslandWindowView);
    }

    DynamicIslandBaseContentViewController.FactoryImpl getBaseContentViewControllerFactory();

    DynamicIslandContentViewController.Factory getContentViewControllerFactory();

    DynamicIslandEventCoordinator getDynamicIslandEventCoordinator();

    DynamicIslandWindowViewController getDynamicIslandWindowViewController();

    IslandTemplateFactory getIslandTemplateFactory();

    DynamicIslandSizeRepository getSizeRepository();

    Map<Class<?>, DynamicIslandStartable> getStartables();
}
