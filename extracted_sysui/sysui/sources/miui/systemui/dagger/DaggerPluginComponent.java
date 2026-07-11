package miui.systemui.dagger;

import F0.f;
import F0.g;
import F0.h;
import F0.i;
import android.content.ContentProvider;
import android.content.Context;
import android.view.View;
import com.android.systemui.BaseMiPlayController;
import com.android.systemui.BaseMiPlayController_MembersInjector;
import com.android.systemui.dagger.MiPlayModule;
import com.android.systemui.dagger.MiPlayModule_ProvideMiPlayAudioManagerFactory;
import com.android.systemui.dagger.MiPlayModule_ProvideMiPlayMediaPlayerAdapterFactory;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.util.Map;
import java.util.Optional;
import miui.systemui.PluginAppComponentFactory;
import miui.systemui.PluginAppComponentFactory_MembersInjector;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.autodensity.AutoDensityControllerImpl_Factory;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.broadcast.BroadcastDispatcher_Factory;
import miui.systemui.clouddata.CloudDataManager_Factory;
import miui.systemui.controlcenter.MiuiControlCenter;
import miui.systemui.controlcenter.MiuiControlCenter_MembersInjector;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideControlCenterHeaderFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideControlCenterSecondaryFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideMiuiSecurityControllerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvidePluginDumpManagerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideQSHostFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideUserTrackerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterPluginInstance_ProvideVolumeDialogControllerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewComponent;
import miui.systemui.controlcenter.dagger.ControlCenterViewInstanceCreator;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_CreateLeftMainPanelFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_CreateRightMainPanelFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideBrightnessMirrorLifecycleFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideBrightnessPanelBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideContextFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideControlCenterBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideControlCenterLifecycleFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideControlCenterSecondaryBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideCoroutineScopeFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideCustomizerBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideDetailPanelBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideLayoutInflaterFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideLifecycleCoroutineScopeFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideMainPanelContainerFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideMainPanelCustomizeHeaderBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideMainPanelHeaderBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideMainPanelMsgHeaderBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideMediaPanelBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideSmartHomeBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideVolumePanelBindingFactory;
import miui.systemui.controlcenter.dagger.ControlCenterViewModule_ProvideWindowViewFactory;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepositoryImpl_Factory;
import miui.systemui.controlcenter.data.repository.StatusBarStateRepositoryImpl_Factory;
import miui.systemui.controlcenter.events.ControlCenterEventTracker_Factory;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter_Factory;
import miui.systemui.controlcenter.panel.SecondaryPanelTouchController_Factory;
import miui.systemui.controlcenter.panel.customize.CustomizePanelController_Factory;
import miui.systemui.controlcenter.panel.customize.CustomizePanelLinkageController_Factory;
import miui.systemui.controlcenter.panel.devicecontrol.DeviceControlPanelController_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelAnimController;
import miui.systemui.controlcenter.panel.main.MainPanelAnimController_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelController_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController_Factory;
import miui.systemui.controlcenter.panel.main.MainPanelTouchController_Factory;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator_Factory;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController_Factory;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController_Factory;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController_Factory;
import miui.systemui.controlcenter.panel.main.devicecontrol.DeviceControlsEntryController_Factory;
import miui.systemui.controlcenter.panel.main.footer.FooterSpaceController_Factory_Factory;
import miui.systemui.controlcenter.panel.main.header.CustomizeHeaderController_Factory;
import miui.systemui.controlcenter.panel.main.header.EmptyHeaderController_Factory;
import miui.systemui.controlcenter.panel.main.header.EmptyHeaderMirrorController_Factory;
import miui.systemui.controlcenter.panel.main.header.HeaderSpaceController_Factory_Factory;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController_Factory;
import miui.systemui.controlcenter.panel.main.header.MessageHeaderController_Factory;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController_Factory;
import miui.systemui.controlcenter.panel.main.qs.CompactQSCardController_Factory;
import miui.systemui.controlcenter.panel.main.qs.CompactQSCardViewHolder_Factory_Factory;
import miui.systemui.controlcenter.panel.main.qs.CompactQSListController_Factory;
import miui.systemui.controlcenter.panel.main.qs.EditButtonController_Factory;
import miui.systemui.controlcenter.panel.main.qs.QSCardsController_Factory;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder_Factory_Factory;
import miui.systemui.controlcenter.panel.main.qs.QSListController_Factory;
import miui.systemui.controlcenter.panel.main.qs.QSRecord_Factory_Factory;
import miui.systemui.controlcenter.panel.main.qs.WordlessModeController_Factory;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter_Factory_Factory;
import miui.systemui.controlcenter.panel.main.recyclerview.ToggleSliderViewHolder_Factory_Factory;
import miui.systemui.controlcenter.panel.main.security.SecurityFooterController_Factory;
import miui.systemui.controlcenter.panel.main.volume.VolumeSliderController_Factory;
import miui.systemui.controlcenter.panel.secondary.SecondaryContainerController_Factory;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelAnimator_Factory;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelController_Factory;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelDelegate_Factory;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelSliderDelegate_Factory;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelTilesDelegate_Factory;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator_Factory;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator_Factory;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelController_Factory;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelDelegate_Factory;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelTilesDelegate_Factory;
import miui.systemui.controlcenter.panel.secondary.media.MediaPanelAnimator_Factory;
import miui.systemui.controlcenter.panel.secondary.media.MediaPanelController_Factory;
import miui.systemui.controlcenter.panel.secondary.media.MediaPanelDelegate_Factory;
import miui.systemui.controlcenter.panel.secondary.volume.VolumePanelAnimator_Factory;
import miui.systemui.controlcenter.panel.secondary.volume.VolumePanelController_Factory;
import miui.systemui.controlcenter.panel.secondary.volume.VolumePanelDelegate_Factory;
import miui.systemui.controlcenter.policy.SecurityController;
import miui.systemui.controlcenter.policy.SecurityController_Factory;
import miui.systemui.controlcenter.qs.QSController_Factory;
import miui.systemui.controlcenter.qs.customize.TileQueryHelper_Factory;
import miui.systemui.controlcenter.windowview.BlurController;
import miui.systemui.controlcenter.windowview.BlurController_Factory;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController_Factory;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot_Factory;
import miui.systemui.controlcenter.windowview.ControlCenterSuperPowerController_Factory;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController_Factory;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator_Factory;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miui.systemui.controlcenter.windowview.GestureDispatcher_Factory;
import miui.systemui.devicecenter.DeviceCenterController_Factory;
import miui.systemui.devicecontrols.CustomIconCache_Factory;
import miui.systemui.devicecontrols.DeviceControlsModel;
import miui.systemui.devicecontrols.DeviceControlsModelImpl_Factory;
import miui.systemui.devicecontrols.DeviceControlsPresenterImpl_Factory;
import miui.systemui.devicecontrols.controller.ControlsBindingControllerImpl_Factory;
import miui.systemui.devicecontrols.controller.ControlsControllerImpl_Factory;
import miui.systemui.devicecontrols.controller.PrefDeviceControlsController_Factory;
import miui.systemui.devicecontrols.dagger.DeviceControlsComponent;
import miui.systemui.devicecontrols.management.ControlsListingControllerImpl_Factory;
import miui.systemui.devicecontrols.management.EditControlsModelController_Factory;
import miui.systemui.devicecontrols.management.ViewHolderFactory_Factory;
import miui.systemui.devicecontrols.ui.ControlActionCoordinatorImpl_Factory;
import miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl_Factory;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.anim.C0594DynamicIslandAnimationDelegate_Factory;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationController_Factory;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate_Factory_Impl;
import miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent;
import miui.systemui.dynamicisland.dagger.DynamicIslandViewModule_Companion_ProvidesLifecycleCoroutineScopeFactory;
import miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository_Factory;
import miui.systemui.dynamicisland.data.repository.DynamicIslandSizeRepository;
import miui.systemui.dynamicisland.data.repository.DynamicIslandSizeRepository_Factory;
import miui.systemui.dynamicisland.display.AntiBurnInManager_Factory;
import miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper_Factory;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor_Factory;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandStateInteractor;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandStateInteractor_Factory;
import miui.systemui.dynamicisland.event.AddEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.AppEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.AvoidScreenBurnInEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.ClickEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.CollapseEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.ConfigChangedEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.DeletedEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.IslandTempHiddenEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.MiniWindowEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.SwipeEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.UpdateEventCoordinator_Factory;
import miui.systemui.dynamicisland.event.handler.AppStateHandler_Factory;
import miui.systemui.dynamicisland.event.handler.BigIslandStateHandler_Factory;
import miui.systemui.dynamicisland.event.handler.ExpandedStateHandler_Factory;
import miui.systemui.dynamicisland.event.handler.HiddenStateHandler_Factory;
import miui.systemui.dynamicisland.event.handler.MiniWindowStateHandler_Factory;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler_Factory;
import miui.systemui.dynamicisland.events.DynamicIslandExposureManager_Factory;
import miui.systemui.dynamicisland.module.C0607IslandCombineImageViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0609IslandFixedWidthDigitViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0610IslandIconFixedWidthTextHolder_Factory;
import miui.systemui.dynamicisland.module.C0611IslandIconViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0612IslandImageTextView2Holder_Factory;
import miui.systemui.dynamicisland.module.C0613IslandImageTextView3Holder_Factory;
import miui.systemui.dynamicisland.module.C0614IslandImageTextView4Holder_Factory;
import miui.systemui.dynamicisland.module.C0615IslandImageTextViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0616IslandProgressTextViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0617IslandProgressViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0621IslandRightTextViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0623IslandSameWidthDigitViewHolder_Factory;
import miui.systemui.dynamicisland.module.C0624IslandTextOverIconHolder_Factory;
import miui.systemui.dynamicisland.module.C0627IslandTextViewHolder_Factory;
import miui.systemui.dynamicisland.module.IslandCombineImageViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandIconFixedWidthTextHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandIconViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandImageTextView2Holder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandImageTextView3Holder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandImageTextView4Holder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandImageTextViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandModuleViewHolderAdapter_Factory;
import miui.systemui.dynamicisland.module.IslandProgressTextViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandProgressViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandTextOverIconHolder_Factory_Impl;
import miui.systemui.dynamicisland.module.IslandTextViewHolder_Factory_Impl;
import miui.systemui.dynamicisland.template.C0628IslandTemplateBuilder_Factory;
import miui.systemui.dynamicisland.template.IslandTemplateBuilder_Factory_Impl;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.template.IslandTemplateFactory_Factory;
import miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository_Factory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher_Factory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor_Factory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor_Factory;
import miui.systemui.dynamicisland.window.AppLockController_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController;
import miui.systemui.dynamicisland.window.DynamicIslandSafeguardsController_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandWindowController;
import miui.systemui.dynamicisland.window.DynamicIslandWindowController_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewCreator;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewCreator_Factory;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor_Factory;
import miui.systemui.dynamicisland.window.content.C0645DynamicIslandBaseContentViewController_Factory;
import miui.systemui.dynamicisland.window.content.C0657DynamicIslandContentViewController_Factory;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController_FactoryImpl_Impl;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController_Factory_Impl;
import miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController;
import miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentViewController_Factory;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor_Factory;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor_Factory;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor_Factory;
import miui.systemui.eventtracking.dagger.EventTrackingModule;
import miui.systemui.eventtracking.dagger.EventTrackingModule_ProvidesEventTrackingContextFactory;
import miui.systemui.flashlight.MiFlashlightActivity;
import miui.systemui.flashlight.MiFlashlightActivity_Factory;
import miui.systemui.flashlight.MiFlashlightController;
import miui.systemui.flashlight.MiFlashlightController_Factory;
import miui.systemui.flashlight.MiFlashlightManager_Factory;
import miui.systemui.flashlight.MiFlashlightOperationReceiver;
import miui.systemui.flashlight.MiFlashlightOperationReceiver_Factory;
import miui.systemui.flashlight.MiFlashlightReceiver;
import miui.systemui.flashlight.MiFlashlightReceiver_Factory;
import miui.systemui.flashlight.dagger.MiFlashlightComponent;
import miui.systemui.flashlight.dagger.MiFlashlightComponentModule;
import miui.systemui.flashlight.dagger.MiFlashlightComponentModule_ProvideLifecycleFactory;
import miui.systemui.flashlight.dagger.MiFlashlightComponentModule_ProvideViewFactory;
import miui.systemui.flashlight.effect.MiFlashlightUiOpenGl_Factory;
import miui.systemui.flashlight.view.MiFlashlightLayout;
import miui.systemui.handles.C0669RegionSamplingHelperRefactor_Factory;
import miui.systemui.handles.RegionSamplingHelperRefactor_Factory_Impl;
import miui.systemui.notification.FocusNotificationPluginImpl;
import miui.systemui.notification.FocusNotificationPluginImpl_MembersInjector;
import miui.systemui.notification.LottieProgressManager_Factory;
import miui.systemui.notification.NotificationChronometerManager_Factory;
import miui.systemui.notification.NotificationDynamicIslandPluginImpl;
import miui.systemui.notification.NotificationDynamicIslandPluginImpl_MembersInjector;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.notification.NotificationSettingsManager_Factory;
import miui.systemui.notification.focus.FocusNotifPreHandler_Factory;
import miui.systemui.notification.focus.FocusNotifUtils_Factory;
import miui.systemui.notification.focus.FocusNotificationController;
import miui.systemui.notification.focus.FocusNotificationController_Factory;
import miui.systemui.notification.focus.HideDeletedFocusController_Factory;
import miui.systemui.notification.focus.SignatureChecker_Factory;
import miui.systemui.notification.focus.moduleV3.ModuleViewHolderAdapter_Factory;
import miui.systemui.notification.focus.templateV3.C0686TemplateBuilderV3_Factory;
import miui.systemui.notification.focus.templateV3.C0689TemplateDecoBuilderV3_Factory;
import miui.systemui.notification.focus.templateV3.C0691TemplateDecoLandBuilderV3_Factory;
import miui.systemui.notification.focus.templateV3.C0694TemplateTinyBuilderV3_Factory;
import miui.systemui.notification.focus.templateV3.TemplateBuilderV3_Factory_Impl;
import miui.systemui.notification.focus.templateV3.TemplateDecoBuilderV3_Factory_Impl;
import miui.systemui.notification.focus.templateV3.TemplateDecoLandBuilderV3_Factory_Impl;
import miui.systemui.notification.focus.templateV3.TemplateFactoryV3_Factory;
import miui.systemui.notification.focus.templateV3.TemplateTinyBuilderV3_Factory_Impl;
import miui.systemui.plugins.PluginBase;
import miui.systemui.plugins.PluginBase_MembersInjector;
import miui.systemui.plugins.PluginDependencyHolderImpl_Factory_Factory;
import miui.systemui.plugins.PluginDependencyModule;
import miui.systemui.plugins.PluginDependencyModule_ProvidesActivityStarterFactory;
import miui.systemui.plugins.PluginDependencyModule_ProvidesActivityStarterHolderFactory;
import miui.systemui.plugins.PluginDependencyModule_ProvidesSuperSaveModeControllerFactory;
import miui.systemui.plugins.PluginDependencyModule_ProvidesSuperSaveModeControllerHolderFactory;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl_Factory;
import miui.systemui.plugins.domain.interactor.PluginLifecycleInteractorModule;
import miui.systemui.plugins.domain.interactor.PluginLifecycleInteractor_Factory;
import miui.systemui.quicksettings.LocalMiuiQSTilePlugin;
import miui.systemui.quicksettings.LocalMiuiQSTilePlugin_MembersInjector;
import miui.systemui.settings.data.repository.OneHandedModeRepository_Factory;
import miui.systemui.statusbar.data.repository.StatusBarAreaRepository_Factory;
import miui.systemui.ui.data.repository.ConfigurationRepository_Factory;
import miui.systemui.util.AlarmScheduler_Factory_Impl;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.BlurUtilsExt_Factory;
import miui.systemui.util.C0695AlarmScheduler_Factory;
import miui.systemui.util.HapticFeedbackImpl_Factory;
import miui.systemui.util.MiLinkController;
import miui.systemui.util.MiLinkController_Factory;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.util.SystemUIResourcesHelperImpl_Factory;
import miui.systemui.util.TalkBackUtils_Factory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideBackgroundDelayableExecutorFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideBackgroundExecutorFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideBgHandlerFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideBgLooperFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideDelayableExecutorFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideExecutorFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideMainDelayableExecutorFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideMainExecutorFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideMainHandlerFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideMainLooperFactory;
import miui.systemui.util.concurrency.ConcurrencyModule_ProvideUiBackgroundExecutorFactory;
import miui.systemui.util.kotlin.CoroutinesModule_MainCoroutineContextFactory;
import miui.systemui.util.kotlin.CoroutinesModule_PluginScopeFactory;
import miui.systemui.util.settings.GlobalSettings;
import miui.systemui.util.settings.GlobalSettingsImpl_Factory;
import miui.systemui.util.settings.SecureSettingsImpl_Factory;
import miui.systemui.volume.VolumeDialogPlugin;
import miui.systemui.volume.VolumeDialogPlugin_MembersInjector;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.EventTracker_Factory;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;
import systemui.plugin.eventtracking.trackers.BaseEventTracker_MembersInjector;

/* JADX INFO: loaded from: classes.dex */
public final class DaggerPluginComponent {
    private static final G0.a ABSENT_JDK_OPTIONAL_PROVIDER = f.a(Optional.empty());

    public static final class Builder {
        private ContextModule contextModule;
        private DependencyProvider dependencyProvider;
        private EventTrackingModule eventTrackingModule;
        private MiPlayModule miPlayModule;
        private PluginDependencyModule pluginDependencyModule;

        public PluginComponent build() {
            if (this.miPlayModule == null) {
                this.miPlayModule = new MiPlayModule();
            }
            if (this.pluginDependencyModule == null) {
                this.pluginDependencyModule = new PluginDependencyModule();
            }
            i.a(this.contextModule, ContextModule.class);
            if (this.dependencyProvider == null) {
                this.dependencyProvider = new DependencyProvider();
            }
            if (this.eventTrackingModule == null) {
                this.eventTrackingModule = new EventTrackingModule();
            }
            return new PluginComponentImpl(this.miPlayModule, this.pluginDependencyModule, this.contextModule, this.dependencyProvider, this.eventTrackingModule);
        }

        public Builder contextModule(ContextModule contextModule) {
            this.contextModule = (ContextModule) i.b(contextModule);
            return this;
        }

        public Builder dependencyProvider(DependencyProvider dependencyProvider) {
            this.dependencyProvider = (DependencyProvider) i.b(dependencyProvider);
            return this;
        }

        public Builder eventTrackingModule(EventTrackingModule eventTrackingModule) {
            this.eventTrackingModule = (EventTrackingModule) i.b(eventTrackingModule);
            return this;
        }

        public Builder miPlayModule(MiPlayModule miPlayModule) {
            this.miPlayModule = (MiPlayModule) i.b(miPlayModule);
            return this;
        }

        public Builder pluginDependencyModule(PluginDependencyModule pluginDependencyModule) {
            this.pluginDependencyModule = (PluginDependencyModule) i.b(pluginDependencyModule);
            return this;
        }

        @Deprecated
        public Builder pluginLifecycleInteractorModule(PluginLifecycleInteractorModule pluginLifecycleInteractorModule) {
            i.b(pluginLifecycleInteractorModule);
            return this;
        }

        private Builder() {
        }
    }

    public static final class ControlCenterViewComponentFactory implements ControlCenterViewComponent.Factory {
        private final ControlCenterViewInstanceCreatorImpl controlCenterViewInstanceCreatorImpl;
        private final PluginComponentImpl pluginComponentImpl;
        private final ViewCreatorImpl viewCreatorImpl;

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent.Factory
        public ControlCenterViewComponent create(ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
            i.b(controlCenterWindowViewImpl);
            return new ControlCenterViewComponentImpl(this.pluginComponentImpl, this.viewCreatorImpl, this.controlCenterViewInstanceCreatorImpl, new ControlCenterViewModule(), controlCenterWindowViewImpl);
        }

        private ControlCenterViewComponentFactory(PluginComponentImpl pluginComponentImpl, ViewCreatorImpl viewCreatorImpl, ControlCenterViewInstanceCreatorImpl controlCenterViewInstanceCreatorImpl) {
            this.pluginComponentImpl = pluginComponentImpl;
            this.viewCreatorImpl = viewCreatorImpl;
            this.controlCenterViewInstanceCreatorImpl = controlCenterViewInstanceCreatorImpl;
        }
    }

    public static final class ControlCenterViewComponentImpl implements ControlCenterViewComponent {
        private G0.a blurControllerProvider;
        private G0.a brightnessPanelAnimatorProvider;
        private G0.a brightnessPanelControllerProvider;
        private G0.a brightnessPanelDelegateProvider;
        private G0.a brightnessPanelSliderDelegateProvider;
        private G0.a brightnessPanelTilesDelegateProvider;
        private G0.a brightnessSliderControllerProvider;
        private G0.a compactQSCardControllerProvider;
        private G0.a compactQSListControllerProvider;
        private G0.a controlCenterEventTrackerProvider;
        private G0.a controlCenterExpandControllerProvider;
        private G0.a controlCenterScreenshotProvider;
        private G0.a controlCenterSuperPowerControllerProvider;
        private final ControlCenterViewComponentImpl controlCenterViewComponentImpl;
        private final ControlCenterViewInstanceCreatorImpl controlCenterViewInstanceCreatorImpl;
        private G0.a controlCenterWindowViewControllerProvider;
        private G0.a createLeftMainPanelProvider;
        private G0.a createRightMainPanelProvider;
        private G0.a customizeHeaderControllerProvider;
        private G0.a customizePanelControllerProvider;
        private G0.a customizePanelLinkageControllerProvider;
        private G0.a detailPanelAnimatorProvider;
        private G0.a detailPanelCellAnimatorProvider;
        private G0.a detailPanelControllerProvider;
        private G0.a detailPanelDelegateProvider;
        private G0.a detailPanelTilesDelegateProvider;
        private G0.a deviceCenterCardControllerProvider;
        private G0.a deviceCenterEntryControllerProvider;
        private G0.a deviceControlPanelControllerProvider;
        private G0.a deviceControlsEntryControllerProvider;
        private G0.a editButtonControllerProvider;
        private G0.a emptyHeaderControllerProvider;
        private G0.a emptyHeaderMirrorControllerProvider;
        private G0.a factoryProvider;
        private G0.a factoryProvider2;
        private G0.a factoryProvider3;
        private G0.a factoryProvider4;
        private G0.a factoryProvider5;
        private G0.a factoryProvider6;
        private G0.a factoryProvider7;
        private G0.a gestureDispatcherProvider;
        private G0.a mainPanelAnimControllerProvider;
        private G0.a mainPanelContentDistributorProvider;
        private G0.a mainPanelControllerProvider;
        private G0.a mainPanelFrameCallbackProvider;
        private G0.a mainPanelHeaderControllerProvider;
        private G0.a mainPanelModeControllerProvider;
        private G0.a mainPanelStyleControllerProvider;
        private G0.a mainPanelTouchControllerProvider;
        private G0.a mediaPanelAnimatorProvider;
        private G0.a mediaPanelControllerProvider;
        private G0.a mediaPanelDelegateProvider;
        private G0.a mediaPlayerControllerProvider;
        private G0.a messageHeaderControllerProvider;
        private G0.a optionalOfDeviceControlsPresenterProvider;
        private G0.a optionalOfMediaPlayerAdapterProvider;
        private G0.a p0Provider;
        private final PluginComponentImpl pluginComponentImpl;
        private G0.a provideBrightnessMirrorLifecycleProvider;
        private G0.a provideBrightnessPanelBindingProvider;
        private G0.a provideContextProvider;
        private G0.a provideControlCenterBindingProvider;
        private G0.a provideControlCenterLifecycleProvider;
        private G0.a provideControlCenterSecondaryBindingProvider;
        private G0.a provideCoroutineScopeProvider;
        private G0.a provideCustomizerBindingProvider;
        private G0.a provideDetailPanelBindingProvider;
        private G0.a provideLayoutInflaterProvider;
        private G0.a provideLifecycleCoroutineScopeProvider;
        private G0.a provideMainPanelContainerProvider;
        private G0.a provideMainPanelCustomizeHeaderBindingProvider;
        private G0.a provideMainPanelHeaderBindingProvider;
        private G0.a provideMainPanelMsgHeaderBindingProvider;
        private G0.a provideMediaPanelBindingProvider;
        private G0.a provideSmartHomeBindingProvider;
        private G0.a provideVolumePanelBindingProvider;
        private G0.a provideWindowViewProvider;
        private G0.a qSCardsControllerProvider;
        private G0.a qSControllerProvider;
        private G0.a qSListControllerProvider;
        private G0.a secondaryContainerControllerProvider;
        private G0.a secondaryPanelRouterProvider;
        private G0.a secondaryPanelTouchControllerProvider;
        private G0.a securityFooterControllerProvider;
        private G0.a spreadRowsAnimatorProvider;
        private G0.a tileQueryHelperProvider;
        private final ViewCreatorImpl viewCreatorImpl;
        private G0.a volumePanelAnimatorProvider;
        private G0.a volumePanelControllerProvider;
        private G0.a volumePanelDelegateProvider;
        private G0.a volumeSliderControllerProvider;
        private G0.a wordlessModeControllerProvider;

        private void initialize(ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
            F0.e eVarA = f.a(controlCenterWindowViewImpl);
            this.p0Provider = eVarA;
            this.provideControlCenterBindingProvider = F0.d.b(ControlCenterViewModule_ProvideControlCenterBindingFactory.create(controlCenterViewModule, eVarA));
            G0.a aVarB = F0.d.b(ControlCenterViewModule_ProvideControlCenterLifecycleFactory.create(controlCenterViewModule, this.p0Provider));
            this.provideControlCenterLifecycleProvider = aVarB;
            this.provideLifecycleCoroutineScopeProvider = F0.d.b(ControlCenterViewModule_ProvideLifecycleCoroutineScopeFactory.create(controlCenterViewModule, aVarB));
            G0.a aVarB2 = F0.d.b(ControlCenterViewModule_ProvideWindowViewFactory.create(controlCenterViewModule, this.p0Provider));
            this.provideWindowViewProvider = aVarB2;
            this.controlCenterScreenshotProvider = F0.d.b(ControlCenterScreenshot_Factory.create(aVarB2, this.pluginComponentImpl.broadcastDispatcherProvider, this.pluginComponentImpl.provideBackgroundExecutorProvider));
            this.controlCenterWindowViewControllerProvider = new F0.c();
            this.provideCoroutineScopeProvider = F0.d.b(ControlCenterViewModule_ProvideCoroutineScopeFactory.create(controlCenterViewModule, this.provideControlCenterLifecycleProvider));
            G0.a aVarB3 = F0.d.b(ControlCenterViewModule_ProvideContextFactory.create(controlCenterViewModule, this.p0Provider));
            this.provideContextProvider = aVarB3;
            G0.a aVarB4 = F0.d.b(ControlCenterViewModule_ProvideLayoutInflaterFactory.create(controlCenterViewModule, aVarB3));
            this.provideLayoutInflaterProvider = aVarB4;
            this.createRightMainPanelProvider = F0.d.b(ControlCenterViewModule_CreateRightMainPanelFactory.create(controlCenterViewModule, aVarB4));
            this.createLeftMainPanelProvider = F0.d.b(ControlCenterViewModule_CreateLeftMainPanelFactory.create(controlCenterViewModule, this.provideLayoutInflaterProvider));
            this.provideMainPanelContainerProvider = F0.d.b(ControlCenterViewModule_ProvideMainPanelContainerFactory.create(controlCenterViewModule, this.provideControlCenterBindingProvider));
            this.mainPanelContentDistributorProvider = new F0.c();
            F0.c cVar = new F0.c();
            this.controlCenterExpandControllerProvider = cVar;
            this.mainPanelModeControllerProvider = F0.d.b(MainPanelModeController_Factory.create(this.provideWindowViewProvider, this.createRightMainPanelProvider, this.createLeftMainPanelProvider, this.mainPanelContentDistributorProvider, this.provideControlCenterLifecycleProvider, cVar));
            this.secondaryPanelTouchControllerProvider = F0.d.b(SecondaryPanelTouchController_Factory.create(this.provideWindowViewProvider, this.controlCenterWindowViewControllerProvider));
            this.provideMainPanelHeaderBindingProvider = F0.d.b(ControlCenterViewModule_ProvideMainPanelHeaderBindingFactory.create(controlCenterViewModule, this.provideControlCenterBindingProvider));
            this.mainPanelControllerProvider = new F0.c();
            this.provideMainPanelMsgHeaderBindingProvider = F0.d.b(ControlCenterViewModule_ProvideMainPanelMsgHeaderBindingFactory.create(controlCenterViewModule, this.provideLayoutInflaterProvider));
            this.mainPanelHeaderControllerProvider = new F0.c();
            this.secondaryPanelRouterProvider = new F0.c();
            this.messageHeaderControllerProvider = F0.d.b(MessageHeaderController_Factory.create(this.provideMainPanelMsgHeaderBindingProvider, ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory.create(), ConcurrencyModule_ProvideMainHandlerFactory.create(), this.mainPanelHeaderControllerProvider, this.controlCenterExpandControllerProvider, this.mainPanelControllerProvider, this.provideControlCenterLifecycleProvider, this.secondaryPanelRouterProvider));
            this.provideMainPanelCustomizeHeaderBindingProvider = F0.d.b(ControlCenterViewModule_ProvideMainPanelCustomizeHeaderBindingFactory.create(controlCenterViewModule, this.provideLayoutInflaterProvider));
            this.qSControllerProvider = new F0.c();
            this.customizeHeaderControllerProvider = F0.d.b(CustomizeHeaderController_Factory.create(this.provideMainPanelCustomizeHeaderBindingProvider, this.provideControlCenterLifecycleProvider, ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory.create(), this.mainPanelControllerProvider, this.qSControllerProvider, this.mainPanelHeaderControllerProvider));
            G0.a aVarB5 = F0.d.b(GestureDispatcher_Factory.create(this.p0Provider, this.controlCenterWindowViewControllerProvider));
            this.gestureDispatcherProvider = aVarB5;
            this.factoryProvider = F0.d.b(ToggleSliderViewHolder_Factory_Factory.create(aVarB5, this.pluginComponentImpl.providesActivityStarterProvider, ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory.create(), ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory.create(), ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.pluginComponentImpl.blurUtilsExtProvider, this.controlCenterExpandControllerProvider));
            this.mainPanelStyleControllerProvider = new F0.c();
            G0.a aVarB6 = F0.d.b(ControlCenterViewModule_ProvideControlCenterSecondaryBindingFactory.create(controlCenterViewModule, this.provideLayoutInflaterProvider));
            this.provideControlCenterSecondaryBindingProvider = aVarB6;
            G0.a aVarB7 = F0.d.b(ControlCenterViewModule_ProvideBrightnessPanelBindingFactory.create(controlCenterViewModule, aVarB6));
            this.provideBrightnessPanelBindingProvider = aVarB7;
            this.brightnessPanelSliderDelegateProvider = F0.d.b(BrightnessPanelSliderDelegate_Factory.create(this.provideControlCenterSecondaryBindingProvider, aVarB7, this.gestureDispatcherProvider, this.pluginComponentImpl.providesActivityStarterProvider, ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory.create(), this.pluginComponentImpl.hapticFeedbackImplProvider));
            this.brightnessPanelControllerProvider = new F0.c();
            this.brightnessPanelAnimatorProvider = F0.d.b(BrightnessPanelAnimator_Factory.create(this.pluginComponentImpl.provideContextProvider, this.brightnessPanelControllerProvider, this.brightnessPanelSliderDelegateProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelControllerProvider));
            G0.a aVarB8 = F0.d.b(BrightnessPanelTilesDelegate_Factory.create(this.pluginComponentImpl.provideSystemUIContextProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.provideControlCenterSecondaryBindingProvider, this.provideBrightnessPanelBindingProvider, ControlCenterPluginInstance_ProvideQSHostFactory.create(), this.pluginComponentImpl.hapticFeedbackImplProvider, this.brightnessPanelAnimatorProvider));
            this.brightnessPanelTilesDelegateProvider = aVarB8;
            G0.a aVarB9 = F0.d.b(BrightnessPanelDelegate_Factory.create(this.provideControlCenterSecondaryBindingProvider, this.brightnessPanelSliderDelegateProvider, aVarB8));
            this.brightnessPanelDelegateProvider = aVarB9;
            F0.c.a(this.brightnessPanelControllerProvider, F0.d.b(BrightnessPanelController_Factory.create(this.provideControlCenterSecondaryBindingProvider, this.provideBrightnessPanelBindingProvider, this.mainPanelControllerProvider, aVarB9, this.brightnessPanelAnimatorProvider, this.provideControlCenterLifecycleProvider)));
            this.brightnessSliderControllerProvider = F0.d.b(BrightnessSliderController_Factory.create(this.provideWindowViewProvider, this.provideControlCenterLifecycleProvider, this.factoryProvider, this.pluginComponentImpl.provideMainExecutorProvider, this.pluginComponentImpl.provideBackgroundExecutorProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, this.pluginComponentImpl.hapticFeedbackImplProvider, this.secondaryPanelRouterProvider, this.brightnessPanelControllerProvider, ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory.create(), this.pluginComponentImpl.providesSuperSaveModeControllerProvider));
            this.emptyHeaderControllerProvider = F0.d.b(EmptyHeaderController_Factory.create(this.pluginComponentImpl.provideContextProvider, ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory.create(), this.provideControlCenterLifecycleProvider, this.brightnessSliderControllerProvider, this.secondaryPanelRouterProvider));
            this.emptyHeaderMirrorControllerProvider = F0.d.b(EmptyHeaderMirrorController_Factory.create(this.pluginComponentImpl.provideContextProvider, ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory.create(), this.provideControlCenterLifecycleProvider, this.brightnessSliderControllerProvider, this.secondaryPanelRouterProvider));
            F0.c.a(this.mainPanelHeaderControllerProvider, F0.d.b(MainPanelHeaderController_Factory.create(this.p0Provider, ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory.create(), this.messageHeaderControllerProvider, this.customizeHeaderControllerProvider, this.mainPanelControllerProvider, this.controlCenterExpandControllerProvider, this.mainPanelContentDistributorProvider, ControlCenterPluginInstance_ProvideControlCenterHeaderFactory.create(), this.emptyHeaderControllerProvider, this.emptyHeaderMirrorControllerProvider, this.secondaryPanelRouterProvider, this.mainPanelModeControllerProvider)));
            this.detailPanelTilesDelegateProvider = F0.d.b(DetailPanelTilesDelegate_Factory.create(this.pluginComponentImpl.provideSystemUIContextProvider, this.provideControlCenterSecondaryBindingProvider, this.qSControllerProvider, this.controlCenterWindowViewControllerProvider));
            this.factoryProvider2 = F0.d.b(QSRecord_Factory_Factory.create(ControlCenterPluginInstance_ProvideQSHostFactory.create(), this.pluginComponentImpl.provideContextProvider, this.qSControllerProvider, this.provideControlCenterLifecycleProvider, this.pluginComponentImpl.hapticFeedbackImplProvider, ConcurrencyModule_ProvideMainLooperFactory.create(), this.detailPanelTilesDelegateProvider, this.secondaryPanelRouterProvider));
            this.qSCardsControllerProvider = F0.d.b(QSCardsController_Factory.create(this.provideWindowViewProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.qSControllerProvider, ControlCenterPluginInstance_ProvideQSHostFactory.create(), this.mainPanelContentDistributorProvider, this.factoryProvider2, this.mainPanelModeControllerProvider, this.mainPanelStyleControllerProvider));
            this.mainPanelAnimControllerProvider = F0.d.b(MainPanelAnimController_Factory.create(this.provideMainPanelHeaderBindingProvider, this.provideMainPanelContainerProvider, this.mainPanelControllerProvider, this.mainPanelHeaderControllerProvider, this.secondaryPanelRouterProvider, this.pluginComponentImpl.blurUtilsExtProvider, this.qSCardsControllerProvider));
            this.provideSmartHomeBindingProvider = F0.d.b(ControlCenterViewModule_ProvideSmartHomeBindingFactory.create(controlCenterViewModule, this.provideControlCenterBindingProvider));
            G0.a aVarOf = PresentJdkOptionalInstanceProvider.of(this.pluginComponentImpl.deviceControlsPresenterImplProvider);
            this.optionalOfDeviceControlsPresenterProvider = aVarOf;
            this.deviceControlPanelControllerProvider = F0.d.b(DeviceControlPanelController_Factory.create(this.provideSmartHomeBindingProvider, this.secondaryPanelRouterProvider, this.controlCenterScreenshotProvider, aVarOf, this.mainPanelControllerProvider, this.controlCenterWindowViewControllerProvider));
            G0.a aVarB10 = F0.d.b(ControlCenterViewModule_ProvideCustomizerBindingFactory.create(controlCenterViewModule, this.provideControlCenterBindingProvider));
            this.provideCustomizerBindingProvider = aVarB10;
            CustomizePanelLinkageController_Factory customizePanelLinkageController_FactoryCreate = CustomizePanelLinkageController_Factory.create(aVarB10);
            this.customizePanelLinkageControllerProvider = customizePanelLinkageController_FactoryCreate;
            this.customizePanelControllerProvider = F0.d.b(CustomizePanelController_Factory.create(this.provideCustomizerBindingProvider, this.mainPanelControllerProvider, customizePanelLinkageController_FactoryCreate, this.controlCenterScreenshotProvider));
            G0.a aVarB11 = F0.d.b(ControlCenterViewModule_ProvideDetailPanelBindingFactory.create(controlCenterViewModule, this.provideControlCenterSecondaryBindingProvider));
            this.provideDetailPanelBindingProvider = aVarB11;
            this.detailPanelCellAnimatorProvider = F0.d.b(DetailPanelCellAnimator_Factory.create(this.provideControlCenterSecondaryBindingProvider, aVarB11));
            this.detailPanelDelegateProvider = DetailPanelDelegate_Factory.create(this.pluginComponentImpl.provideSystemUIContextProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.provideControlCenterSecondaryBindingProvider, this.provideDetailPanelBindingProvider, this.pluginComponentImpl.providesActivityStarterProvider, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.controlCenterExpandControllerProvider, this.detailPanelTilesDelegateProvider, this.detailPanelCellAnimatorProvider);
            this.detailPanelControllerProvider = new F0.c();
            G0.a aVarB12 = F0.d.b(DetailPanelAnimator_Factory.create(this.pluginComponentImpl.provideContextProvider, this.detailPanelControllerProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelControllerProvider, this.detailPanelTilesDelegateProvider));
            this.detailPanelAnimatorProvider = aVarB12;
            F0.c.a(this.detailPanelControllerProvider, F0.d.b(DetailPanelController_Factory.create(this.provideControlCenterSecondaryBindingProvider, this.provideDetailPanelBindingProvider, this.mainPanelControllerProvider, this.detailPanelDelegateProvider, aVarB12, this.provideControlCenterLifecycleProvider)));
            G0.a aVarB13 = F0.d.b(ControlCenterViewModule_ProvideMediaPanelBindingFactory.create(controlCenterViewModule, this.provideControlCenterSecondaryBindingProvider));
            this.provideMediaPanelBindingProvider = aVarB13;
            this.mediaPanelDelegateProvider = F0.d.b(MediaPanelDelegate_Factory.create(this.provideControlCenterSecondaryBindingProvider, aVarB13));
            this.mediaPanelControllerProvider = new F0.c();
            G0.a aVarB14 = F0.d.b(MediaPanelAnimator_Factory.create(this.pluginComponentImpl.provideContextProvider, this.mediaPanelControllerProvider, this.mediaPanelDelegateProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelControllerProvider));
            this.mediaPanelAnimatorProvider = aVarB14;
            F0.c.a(this.mediaPanelControllerProvider, F0.d.b(MediaPanelController_Factory.create(this.provideControlCenterSecondaryBindingProvider, this.provideMediaPanelBindingProvider, this.mainPanelControllerProvider, this.mediaPanelDelegateProvider, aVarB14, this.provideControlCenterLifecycleProvider)));
            this.provideVolumePanelBindingProvider = F0.d.b(ControlCenterViewModule_ProvideVolumePanelBindingFactory.create(controlCenterViewModule, this.provideControlCenterSecondaryBindingProvider));
            this.volumePanelDelegateProvider = F0.d.b(VolumePanelDelegate_Factory.create(this.pluginComponentImpl.provideSystemUIContextProvider, this.provideControlCenterSecondaryBindingProvider, this.provideVolumePanelBindingProvider, ControlCenterPluginInstance_ProvideVolumeDialogControllerFactory.create(), ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create()));
            this.volumePanelControllerProvider = new F0.c();
            G0.a aVarB15 = F0.d.b(VolumePanelAnimator_Factory.create(this.pluginComponentImpl.provideContextProvider, this.volumePanelControllerProvider, this.volumePanelDelegateProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelControllerProvider));
            this.volumePanelAnimatorProvider = aVarB15;
            F0.c.a(this.volumePanelControllerProvider, F0.d.b(VolumePanelController_Factory.create(this.provideControlCenterSecondaryBindingProvider, this.provideVolumePanelBindingProvider, this.mainPanelControllerProvider, this.volumePanelDelegateProvider, aVarB15, this.provideControlCenterLifecycleProvider)));
            F0.c.a(this.secondaryPanelRouterProvider, F0.d.b(SecondaryPanelRouter_Factory.create(this.provideControlCenterLifecycleProvider, this.provideWindowViewProvider, this.controlCenterScreenshotProvider, this.secondaryPanelTouchControllerProvider, this.mainPanelAnimControllerProvider, this.deviceControlPanelControllerProvider, this.customizePanelControllerProvider, this.detailPanelControllerProvider, this.mediaPanelControllerProvider, this.brightnessPanelControllerProvider, this.volumePanelControllerProvider, this.controlCenterWindowViewControllerProvider, ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory.create())));
            this.qSListControllerProvider = new F0.c();
            F0.c.a(this.qSControllerProvider, F0.d.b(QSController_Factory.create(this.provideWindowViewProvider, ConcurrencyModule_ProvideMainLooperFactory.create(), ControlCenterPluginInstance_ProvideQSHostFactory.create(), this.secondaryPanelRouterProvider, this.detailPanelControllerProvider, this.qSListControllerProvider, this.controlCenterWindowViewControllerProvider, this.messageHeaderControllerProvider, this.mainPanelModeControllerProvider)));
            this.tileQueryHelperProvider = F0.d.b(TileQueryHelper_Factory.create(this.provideContextProvider, this.pluginComponentImpl.systemUIResourcesHelperImplProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider, this.qSControllerProvider, ControlCenterPluginInstance_ProvideQSHostFactory.create(), this.pluginComponentImpl.provideUserTrackerProvider));
            this.factoryProvider3 = F0.d.b(QSItemViewHolder_Factory_Factory.create(this.mainPanelControllerProvider));
            F0.c.a(this.qSListControllerProvider, F0.d.b(QSListController_Factory.create(this.provideWindowViewProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.qSControllerProvider, ControlCenterPluginInstance_ProvideQSHostFactory.create(), this.mainPanelContentDistributorProvider, this.mainPanelControllerProvider, this.factoryProvider2, this.tileQueryHelperProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), ConcurrencyModule_ProvideBgHandlerFactory.create(), this.qSCardsControllerProvider, this.factoryProvider3)));
            G0.a aVarB16 = F0.d.b(CompactQSCardViewHolder_Factory_Factory.create(this.factoryProvider3, this.pluginComponentImpl.provideSystemUIContextProvider));
            this.factoryProvider4 = aVarB16;
            this.compactQSCardControllerProvider = F0.d.b(CompactQSCardController_Factory.create(this.provideWindowViewProvider, this.qSControllerProvider, this.factoryProvider2, aVarB16, this.mainPanelControllerProvider));
            this.compactQSListControllerProvider = F0.d.b(CompactQSListController_Factory.create(this.provideWindowViewProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.qSControllerProvider, this.factoryProvider2, this.factoryProvider3, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.mainPanelControllerProvider, this.qSListControllerProvider, this.mainPanelContentDistributorProvider, this.pluginComponentImpl.providesSuperSaveModeControllerProvider));
            G0.a aVarOf2 = PresentJdkOptionalInstanceProvider.of(this.pluginComponentImpl.provideMiPlayMediaPlayerAdapterProvider);
            this.optionalOfMediaPlayerAdapterProvider = aVarOf2;
            this.mediaPlayerControllerProvider = MediaPlayerController_Factory.create(this.provideWindowViewProvider, this.provideControlCenterLifecycleProvider, this.secondaryPanelRouterProvider, aVarOf2, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, this.pluginComponentImpl.hapticFeedbackImplProvider, this.mediaPanelDelegateProvider, this.pluginComponentImpl.providesSuperSaveModeControllerProvider, ControlCenterPluginInstance_ProvideUserTrackerFactory.create());
            this.volumeSliderControllerProvider = F0.d.b(VolumeSliderController_Factory.create(this.provideWindowViewProvider, this.provideControlCenterLifecycleProvider, this.factoryProvider, ConcurrencyModule_ProvideMainLooperFactory.create(), this.pluginComponentImpl.provideMainExecutorProvider, this.pluginComponentImpl.provideBgLooperProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider, this.pluginComponentImpl.broadcastDispatcherProvider, this.pluginComponentImpl.hapticFeedbackImplProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelControllerProvider, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, this.secondaryPanelRouterProvider, this.volumePanelControllerProvider, this.messageHeaderControllerProvider, ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory.create(), this.pluginComponentImpl.providesSuperSaveModeControllerProvider));
            this.editButtonControllerProvider = F0.d.b(EditButtonController_Factory.create(this.provideWindowViewProvider, this.mainPanelContentDistributorProvider, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.qSListControllerProvider, this.mainPanelControllerProvider, this.pluginComponentImpl.providesSuperSaveModeControllerProvider, this.secondaryPanelRouterProvider, ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory.create()));
            this.wordlessModeControllerProvider = F0.d.b(WordlessModeController_Factory.create(this.provideWindowViewProvider, this.mainPanelContentDistributorProvider, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.qSListControllerProvider, this.mainPanelControllerProvider, this.mainPanelModeControllerProvider));
            this.deviceControlsEntryControllerProvider = F0.d.b(DeviceControlsEntryController_Factory.create(this.provideWindowViewProvider, this.provideControlCenterLifecycleProvider, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.pluginComponentImpl.provideMainExecutorProvider, this.secondaryPanelRouterProvider, this.optionalOfDeviceControlsPresenterProvider, this.mainPanelContentDistributorProvider, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, this.pluginComponentImpl.miLinkControllerProvider, this.pluginComponentImpl.hapticFeedbackImplProvider, this.pluginComponentImpl.providesActivityStarterProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelModeControllerProvider));
            this.deviceCenterEntryControllerProvider = new F0.c();
            G0.a aVarB17 = F0.d.b(DeviceCenterCardController_Factory.create(this.provideWindowViewProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.pluginComponentImpl.deviceCenterControllerProvider, this.pluginComponentImpl.providesActivityStarterProvider, this.deviceCenterEntryControllerProvider, this.pluginComponentImpl.hapticFeedbackImplProvider));
            this.deviceCenterCardControllerProvider = aVarB17;
            F0.c.a(this.deviceCenterEntryControllerProvider, F0.d.b(DeviceCenterEntryController_Factory.create(this.provideWindowViewProvider, this.provideControlCenterLifecycleProvider, aVarB17, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.pluginComponentImpl.provideMainExecutorProvider, this.pluginComponentImpl.providesActivityStarterProvider, this.mainPanelContentDistributorProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, this.pluginComponentImpl.miLinkControllerProvider, this.pluginComponentImpl.hapticFeedbackImplProvider, ControlCenterPluginInstance_ProvideUserTrackerFactory.create(), this.pluginComponentImpl.providesSuperSaveModeControllerProvider)));
            this.securityFooterControllerProvider = F0.d.b(SecurityFooterController_Factory.create(this.provideWindowViewProvider, this.provideControlCenterLifecycleProvider, this.pluginComponentImpl.securityControllerProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.pluginComponentImpl.provideBgLooperProvider, this.mainPanelContentDistributorProvider, this.pluginComponentImpl.systemUIResourcesHelperImplProvider, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, ControlCenterPluginInstance_ProvideMiuiSecurityControllerFactory.create()));
            this.factoryProvider5 = F0.d.b(HeaderSpaceController_Factory_Factory.create(this.p0Provider, this.mainPanelHeaderControllerProvider));
            this.factoryProvider6 = F0.d.b(FooterSpaceController_Factory_Factory.create(this.p0Provider));
            this.provideBrightnessMirrorLifecycleProvider = F0.d.b(ControlCenterViewModule_ProvideBrightnessMirrorLifecycleFactory.create(controlCenterViewModule, this.brightnessSliderControllerProvider));
            F0.c cVar2 = new F0.c();
            this.spreadRowsAnimatorProvider = cVar2;
            G0.a aVarB18 = F0.d.b(MainPanelFrameCallback_Factory.create(this.mainPanelContentDistributorProvider, cVar2));
            this.mainPanelFrameCallbackProvider = aVarB18;
            F0.c.a(this.spreadRowsAnimatorProvider, F0.d.b(SpreadRowsAnimator_Factory.create(this.provideWindowViewProvider, this.controlCenterWindowViewControllerProvider, this.controlCenterExpandControllerProvider, this.provideBrightnessMirrorLifecycleProvider, aVarB18, ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory.create(), this.mainPanelHeaderControllerProvider, this.brightnessSliderControllerProvider, this.provideMainPanelContainerProvider)));
            G0.a aVarB19 = F0.d.b(MainPanelAdapter_Factory_Factory.create(this.pluginComponentImpl.provideContextProvider, this.spreadRowsAnimatorProvider, this.provideControlCenterLifecycleProvider, this.mainPanelFrameCallbackProvider, this.mainPanelControllerProvider, ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory.create(), this.qSListControllerProvider, this.controlCenterExpandControllerProvider, this.provideBrightnessMirrorLifecycleProvider));
            this.factoryProvider7 = aVarB19;
            F0.c.a(this.mainPanelContentDistributorProvider, F0.d.b(MainPanelContentDistributor_Factory.create(this.provideWindowViewProvider, this.mainPanelModeControllerProvider, this.qSListControllerProvider, this.qSCardsControllerProvider, this.compactQSCardControllerProvider, this.compactQSListControllerProvider, this.mediaPlayerControllerProvider, this.brightnessSliderControllerProvider, this.volumeSliderControllerProvider, this.editButtonControllerProvider, this.wordlessModeControllerProvider, this.deviceControlsEntryControllerProvider, this.deviceCenterEntryControllerProvider, this.securityFooterControllerProvider, this.factoryProvider5, this.factoryProvider6, aVarB19, ConcurrencyModule_ProvideMainLooperFactory.create())));
            F0.c.a(this.mainPanelStyleControllerProvider, F0.d.b(MainPanelStyleController_Factory.create(this.provideWindowViewProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.mainPanelContentDistributorProvider, this.mainPanelControllerProvider)));
            this.mainPanelTouchControllerProvider = F0.d.b(MainPanelTouchController_Factory.create(this.provideWindowViewProvider, this.controlCenterWindowViewControllerProvider, this.controlCenterExpandControllerProvider, this.gestureDispatcherProvider, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.mainPanelControllerProvider, this.mainPanelHeaderControllerProvider));
            this.secondaryContainerControllerProvider = F0.d.b(SecondaryContainerController_Factory.create(this.provideWindowViewProvider, ControlCenterPluginInstance_ProvideControlCenterSecondaryFactory.create(), this.provideControlCenterSecondaryBindingProvider, this.secondaryPanelTouchControllerProvider));
            F0.c.a(this.mainPanelControllerProvider, F0.d.b(MainPanelController_Factory.create(this.provideCoroutineScopeProvider, this.provideWindowViewProvider, this.createRightMainPanelProvider, this.createLeftMainPanelProvider, this.provideMainPanelContainerProvider, this.provideControlCenterLifecycleProvider, this.mainPanelStyleControllerProvider, this.mainPanelModeControllerProvider, this.mainPanelAnimControllerProvider, this.mainPanelTouchControllerProvider, this.controlCenterExpandControllerProvider, this.volumeSliderControllerProvider, this.controlCenterWindowViewControllerProvider, this.spreadRowsAnimatorProvider, this.gestureDispatcherProvider, this.qSControllerProvider, this.mainPanelContentDistributorProvider, this.secondaryPanelRouterProvider, this.mainPanelHeaderControllerProvider, this.pluginComponentImpl.systemUIResourcesHelperImplProvider, this.pluginComponentImpl.blurUtilsExtProvider, this.secondaryContainerControllerProvider, this.brightnessSliderControllerProvider)));
            G0.a aVarB20 = F0.d.b(BlurController_Factory.create(this.p0Provider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.controlCenterScreenshotProvider, this.brightnessSliderControllerProvider, this.controlCenterExpandControllerProvider, this.controlCenterWindowViewControllerProvider));
            this.blurControllerProvider = aVarB20;
            F0.c.a(this.controlCenterExpandControllerProvider, F0.d.b(ControlCenterExpandController_Factory.create(this.provideWindowViewProvider, this.controlCenterWindowViewControllerProvider, this.mainPanelControllerProvider, this.secondaryPanelRouterProvider, aVarB20, this.spreadRowsAnimatorProvider, this.brightnessSliderControllerProvider)));
        }

        private void initialize2(ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
            this.controlCenterEventTrackerProvider = F0.d.b(ControlCenterEventTracker_Factory.create(this.p0Provider));
            this.controlCenterSuperPowerControllerProvider = F0.d.b(ControlCenterSuperPowerController_Factory.create(this.provideWindowViewProvider, this.pluginComponentImpl.providesSuperSaveModeControllerProvider, ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory.create(), this.controlCenterExpandControllerProvider));
            F0.c.a(this.controlCenterWindowViewControllerProvider, F0.d.b(ControlCenterWindowViewController_Factory.create(this.provideControlCenterBindingProvider, this.provideControlCenterLifecycleProvider, this.pluginComponentImpl.broadcastDispatcherProvider, this.pluginComponentImpl.provideBackgroundExecutorProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.pluginComponentImpl.provideMainExecutorProvider, this.provideLifecycleCoroutineScopeProvider, this.controlCenterScreenshotProvider, this.controlCenterExpandControllerProvider, this.mainPanelControllerProvider, this.blurControllerProvider, this.gestureDispatcherProvider, this.secondaryPanelRouterProvider, this.pluginComponentImpl.provideUserManagerProvider, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.pluginComponentImpl.autoDensityControllerImplProvider, this.controlCenterEventTrackerProvider, this.mediaPanelControllerProvider, this.brightnessPanelControllerProvider, this.volumePanelControllerProvider, ControlCenterPluginInstance_ProvideUserTrackerFactory.create(), this.pluginComponentImpl.providesSuperSaveModeControllerProvider, ControlCenterPluginInstance_ProvidePluginDumpManagerFactory.create(), this.controlCenterSuperPowerControllerProvider, this.pluginComponentImpl.blurUtilsExtProvider)));
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public BlurController getBlurController() {
            return (BlurController) this.blurControllerProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public ControlCenterExpandController getControlCenterExpandController() {
            return (ControlCenterExpandController) this.controlCenterExpandControllerProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public ControlCenterScreenshot getControlCenterScreenshot() {
            return (ControlCenterScreenshot) this.controlCenterScreenshotProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public ControlCenterWindowViewController getControlCenterWindowViewController() {
            return (ControlCenterWindowViewController) this.controlCenterWindowViewControllerProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public GestureDispatcher getGestureDispatcher() {
            return (GestureDispatcher) this.gestureDispatcherProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public MainPanelAnimController getMainPanelAnimController() {
            return (MainPanelAnimController) this.mainPanelAnimControllerProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public MainPanelController getMainPanelController() {
            return (MainPanelController) this.mainPanelControllerProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public MainPanelModeController getMainPanelModeController() {
            return (MainPanelModeController) this.mainPanelModeControllerProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public SecondaryPanelRouter getSecondaryPanelRouter() {
            return (SecondaryPanelRouter) this.secondaryPanelRouterProvider.get();
        }

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewComponent
        public StatusBarStateController getStatusBarStateController() {
            return ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.provideStatusBarStateController();
        }

        private ControlCenterViewComponentImpl(PluginComponentImpl pluginComponentImpl, ViewCreatorImpl viewCreatorImpl, ControlCenterViewInstanceCreatorImpl controlCenterViewInstanceCreatorImpl, ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
            this.controlCenterViewComponentImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
            this.viewCreatorImpl = viewCreatorImpl;
            this.controlCenterViewInstanceCreatorImpl = controlCenterViewInstanceCreatorImpl;
            initialize(controlCenterViewModule, controlCenterWindowViewImpl);
            initialize2(controlCenterViewModule, controlCenterWindowViewImpl);
        }
    }

    public static final class ControlCenterViewInstanceCreatorImpl implements ControlCenterViewInstanceCreator {
        private final ControlCenterViewInstanceCreatorImpl controlCenterViewInstanceCreatorImpl;
        private final PluginComponentImpl pluginComponentImpl;
        private final ViewAttributeProvider viewAttributeProvider;
        private final ViewCreatorImpl viewCreatorImpl;

        @Override // miui.systemui.controlcenter.dagger.ControlCenterViewInstanceCreator
        public ControlCenterWindowViewImpl createControlCenterWindowView() {
            return new ControlCenterWindowViewImpl(ViewAttributeProvider_ProvideContextFactory.provideContext(this.viewAttributeProvider), ViewAttributeProvider_ProvideAttributeSetFactory.provideAttributeSet(this.viewAttributeProvider), new ControlCenterViewComponentFactory(this.pluginComponentImpl, this.viewCreatorImpl, this.controlCenterViewInstanceCreatorImpl));
        }

        private ControlCenterViewInstanceCreatorImpl(PluginComponentImpl pluginComponentImpl, ViewCreatorImpl viewCreatorImpl, ViewAttributeProvider viewAttributeProvider) {
            this.controlCenterViewInstanceCreatorImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
            this.viewCreatorImpl = viewCreatorImpl;
            this.viewAttributeProvider = viewAttributeProvider;
        }
    }

    public static final class DeviceControlsComponentFactory implements DeviceControlsComponent.Factory {
        private final PluginComponentImpl pluginComponentImpl;

        @Override // miui.systemui.devicecontrols.dagger.DeviceControlsComponent.Factory
        public DeviceControlsComponent create(Context context) {
            i.b(context);
            return new DeviceControlsComponentImpl(this.pluginComponentImpl, context);
        }

        private DeviceControlsComponentFactory(PluginComponentImpl pluginComponentImpl) {
            this.pluginComponentImpl = pluginComponentImpl;
        }
    }

    public static final class DeviceControlsComponentImpl implements DeviceControlsComponent {
        private G0.a contextProvider;
        private G0.a controlActionCoordinatorImplProvider;
        private G0.a controlsBindingControllerImplProvider;
        private G0.a controlsControllerImplProvider;
        private G0.a controlsListingControllerImplProvider;
        private G0.a customIconCacheProvider;
        private final DeviceControlsComponentImpl deviceControlsComponentImpl;
        private G0.a deviceControlsModelImplProvider;
        private G0.a editControlsModelControllerProvider;
        private G0.a miuiControlsUiControllerImplProvider;
        private G0.a optionalOfControlsFavoritePersistenceWrapperProvider;
        private final PluginComponentImpl pluginComponentImpl;
        private G0.a prefDeviceControlsControllerProvider;
        private G0.a viewHolderFactoryProvider;

        private void initialize(Context context) {
            this.controlsControllerImplProvider = new F0.c();
            this.contextProvider = f.a(context);
            this.controlsListingControllerImplProvider = F0.d.b(ControlsListingControllerImpl_Factory.create(this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideExecutorProvider, this.pluginComponentImpl.provideUserTrackerProvider));
            this.controlActionCoordinatorImplProvider = F0.d.b(ControlActionCoordinatorImpl_Factory.create(this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideDelayableExecutorProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.pluginComponentImpl.providesActivityStarterProvider, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.pluginComponentImpl.broadcastDispatcherProvider, this.pluginComponentImpl.hapticFeedbackImplProvider));
            this.customIconCacheProvider = F0.d.b(CustomIconCache_Factory.create());
            F0.c cVar = new F0.c();
            this.miuiControlsUiControllerImplProvider = cVar;
            this.editControlsModelControllerProvider = F0.d.b(EditControlsModelController_Factory.create(this.contextProvider, this.controlsControllerImplProvider, cVar, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider));
            this.viewHolderFactoryProvider = F0.d.b(ViewHolderFactory_Factory.create(this.contextProvider, this.controlsControllerImplProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider, this.pluginComponentImpl.providesActivityStarterProvider, this.customIconCacheProvider, this.controlActionCoordinatorImplProvider, this.editControlsModelControllerProvider, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create()));
            F0.c.a(this.miuiControlsUiControllerImplProvider, F0.d.b(MiuiControlsUiControllerImpl_Factory.create(this.controlsControllerImplProvider, this.contextProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider, this.controlsListingControllerImplProvider, this.pluginComponentImpl.provideSharePreferencesProvider, this.controlActionCoordinatorImplProvider, this.pluginComponentImpl.providesActivityStarterProvider, this.customIconCacheProvider, ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory.create(), this.viewHolderFactoryProvider, this.editControlsModelControllerProvider)));
            this.controlsBindingControllerImplProvider = F0.d.b(ControlsBindingControllerImpl_Factory.create(this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider, this.controlsControllerImplProvider, this.pluginComponentImpl.provideUserTrackerProvider));
            this.optionalOfControlsFavoritePersistenceWrapperProvider = DaggerPluginComponent.absentJdkOptionalProvider();
            F0.c.a(this.controlsControllerImplProvider, F0.d.b(ControlsControllerImpl_Factory.create(this.pluginComponentImpl.provideSystemUIContextProvider, this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.miuiControlsUiControllerImplProvider, this.controlsBindingControllerImplProvider, this.controlsListingControllerImplProvider, this.pluginComponentImpl.broadcastDispatcherProvider, this.optionalOfControlsFavoritePersistenceWrapperProvider, this.pluginComponentImpl.provideUserTrackerProvider)));
            G0.a aVarB = F0.d.b(PrefDeviceControlsController_Factory.create(this.pluginComponentImpl.provideContextProvider, this.controlsControllerImplProvider, this.controlsListingControllerImplProvider, this.pluginComponentImpl.provideUserTrackerProvider, this.pluginComponentImpl.bindSecureSettingsProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider));
            this.prefDeviceControlsControllerProvider = aVarB;
            this.deviceControlsModelImplProvider = F0.d.b(DeviceControlsModelImpl_Factory.create(aVarB, this.controlsListingControllerImplProvider, this.miuiControlsUiControllerImplProvider, this.controlsControllerImplProvider, this.pluginComponentImpl.provideBackgroundDelayableExecutorProvider));
        }

        @Override // miui.systemui.devicecontrols.dagger.DeviceControlsComponent
        public DeviceControlsModel getDeviceControlsModel() {
            return (DeviceControlsModel) this.deviceControlsModelImplProvider.get();
        }

        private DeviceControlsComponentImpl(PluginComponentImpl pluginComponentImpl, Context context) {
            this.deviceControlsComponentImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
            initialize(context);
        }
    }

    public static final class DynamicIslandViewComponentFactory implements DynamicIslandViewComponent.Factory {
        private final PluginComponentImpl pluginComponentImpl;

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent.Factory
        public DynamicIslandViewComponent create(DynamicIslandWindowView dynamicIslandWindowView) {
            i.b(dynamicIslandWindowView);
            return new DynamicIslandViewComponentImpl(this.pluginComponentImpl, dynamicIslandWindowView);
        }

        private DynamicIslandViewComponentFactory(PluginComponentImpl pluginComponentImpl) {
            this.pluginComponentImpl = pluginComponentImpl;
        }
    }

    public static final class DynamicIslandViewComponentImpl implements DynamicIslandViewComponent {
        private G0.a addEventCoordinatorProvider;
        private G0.a antiBurnInManagerProvider;
        private G0.a appEventCoordinatorProvider;
        private G0.a appLockControllerProvider;
        private G0.a appStateHandlerProvider;
        private G0.a avoidScreenBurnInEventCoordinatorProvider;
        private G0.a bigIslandStateHandlerProvider;
        private G0.a bindsLifecycleOwnerProvider;
        private G0.a clickEventCoordinatorProvider;
        private G0.a collapseEventCoordinatorProvider;
        private G0.a configChangedEventCoordinatorProvider;
        private G0.a deletedEventCoordinatorProvider;
        private G0.a dynamicIslandAnimationControllerProvider;
        private C0594DynamicIslandAnimationDelegate_Factory dynamicIslandAnimationDelegateProvider;
        private C0645DynamicIslandBaseContentViewController_Factory<DynamicIslandBaseContentView> dynamicIslandBaseContentViewControllerProvider;
        private C0657DynamicIslandContentViewController_Factory dynamicIslandContentViewControllerProvider;
        private G0.a dynamicIslandDesktopStateControllerProvider;
        private G0.a dynamicIslandEmptyContentViewControllerProvider;
        private G0.a dynamicIslandEventCoordinatorProvider;
        private G0.a dynamicIslandExposureManagerProvider;
        private G0.a dynamicIslandExternalStateRepositoryProvider;
        private G0.a dynamicIslandExternalTouchDispatcherProvider;
        private G0.a dynamicIslandExternalTouchInteractorProvider;
        private G0.a dynamicIslandRegionSamplingInteractorProvider;
        private G0.a dynamicIslandSafeguardsControllerProvider;
        private G0.a dynamicIslandSizeRepositoryProvider;
        private G0.a dynamicIslandStateInteractorProvider;
        private G0.a dynamicIslandTouchConstantsRepositoryProvider;
        private G0.a dynamicIslandTouchInteractorProvider;
        private G0.a dynamicIslandTouchRegionInteractorProvider;
        private final DynamicIslandViewComponentImpl dynamicIslandViewComponentImpl;
        private G0.a dynamicIslandWindowControllerProvider;
        private G0.a dynamicIslandWindowStateInteractorProvider;
        private G0.a dynamicIslandWindowStateProvider;
        private G0.a dynamicIslandWindowViewControllerProvider;
        private G0.a dynamicIslandWindowViewRefactorProvider;
        private G0.a dynamicIslandWindowViewTouchInteractorProvider;
        private G0.a expandedStateHandlerProvider;
        private G0.a factoryImplProvider;
        private G0.a factoryProvider;
        private G0.a factoryProvider10;
        private G0.a factoryProvider11;
        private G0.a factoryProvider12;
        private G0.a factoryProvider13;
        private G0.a factoryProvider14;
        private G0.a factoryProvider15;
        private G0.a factoryProvider16;
        private G0.a factoryProvider17;
        private G0.a factoryProvider18;
        private G0.a factoryProvider2;
        private G0.a factoryProvider3;
        private G0.a factoryProvider4;
        private G0.a factoryProvider5;
        private G0.a factoryProvider6;
        private G0.a factoryProvider7;
        private G0.a factoryProvider8;
        private G0.a factoryProvider9;
        private G0.a hiddenStateHandlerProvider;
        private G0.a hyperDropInfoNotifierServiceProvider;
        private C0607IslandCombineImageViewHolder_Factory islandCombineImageViewHolderProvider;
        private C0609IslandFixedWidthDigitViewHolder_Factory islandFixedWidthDigitViewHolderProvider;
        private C0610IslandIconFixedWidthTextHolder_Factory islandIconFixedWidthTextHolderProvider;
        private C0611IslandIconViewHolder_Factory islandIconViewHolderProvider;
        private C0612IslandImageTextView2Holder_Factory islandImageTextView2HolderProvider;
        private C0613IslandImageTextView3Holder_Factory islandImageTextView3HolderProvider;
        private C0614IslandImageTextView4Holder_Factory islandImageTextView4HolderProvider;
        private C0615IslandImageTextViewHolder_Factory islandImageTextViewHolderProvider;
        private G0.a islandModuleViewHolderAdapterProvider;
        private C0616IslandProgressTextViewHolder_Factory islandProgressTextViewHolderProvider;
        private C0617IslandProgressViewHolder_Factory islandProgressViewHolderProvider;
        private C0621IslandRightTextViewHolder_Factory islandRightTextViewHolderProvider;
        private C0623IslandSameWidthDigitViewHolder_Factory islandSameWidthDigitViewHolderProvider;
        private G0.a islandTempHiddenEventCoordinatorProvider;
        private C0628IslandTemplateBuilder_Factory islandTemplateBuilderProvider;
        private G0.a islandTemplateFactoryProvider;
        private C0624IslandTextOverIconHolder_Factory islandTextOverIconHolderProvider;
        private C0627IslandTextViewHolder_Factory islandTextViewHolderProvider;
        private G0.a miniWindowEventCoordinatorProvider;
        private G0.a miniWindowStateHandlerProvider;
        private final PluginComponentImpl pluginComponentImpl;
        private G0.a providesLifecycleCoroutineScopeProvider;
        private C0669RegionSamplingHelperRefactor_Factory regionSamplingHelperRefactorProvider;
        private G0.a smallIslandStateHandlerProvider;
        private G0.a swipeEventCoordinatorProvider;
        private G0.a updateEventCoordinatorProvider;
        private G0.a windowViewProvider;

        private void initialize(DynamicIslandWindowView dynamicIslandWindowView) {
            F0.e eVarA = f.a(dynamicIslandWindowView);
            this.windowViewProvider = eVarA;
            G0.a aVarB = F0.d.b(eVarA);
            this.bindsLifecycleOwnerProvider = aVarB;
            G0.a aVarB2 = F0.d.b(DynamicIslandViewModule_Companion_ProvidesLifecycleCoroutineScopeFactory.create(aVarB));
            this.providesLifecycleCoroutineScopeProvider = aVarB2;
            G0.a aVarB3 = F0.d.b(DynamicIslandExternalStateRepository_Factory.create(aVarB2));
            this.dynamicIslandExternalStateRepositoryProvider = aVarB3;
            this.dynamicIslandWindowStateProvider = F0.d.b(DynamicIslandWindowState_Factory.create(this.providesLifecycleCoroutineScopeProvider, aVarB3));
            this.dynamicIslandWindowViewControllerProvider = new F0.c();
            this.dynamicIslandTouchConstantsRepositoryProvider = F0.d.b(DynamicIslandTouchConstantsRepository_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.autoDensityControllerImplProvider));
            this.dynamicIslandExternalTouchInteractorProvider = new F0.c();
            this.dynamicIslandWindowViewTouchInteractorProvider = F0.d.b(DynamicIslandWindowViewTouchInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.windowViewProvider, this.pluginComponentImpl.statusBarAreaRepositoryProvider));
            this.dynamicIslandEventCoordinatorProvider = new F0.c();
            F0.c cVar = new F0.c();
            this.dynamicIslandTouchInteractorProvider = cVar;
            this.bigIslandStateHandlerProvider = F0.d.b(BigIslandStateHandler_Factory.create(cVar, this.pluginComponentImpl.provideContextProvider));
            this.smallIslandStateHandlerProvider = F0.d.b(SmallIslandStateHandler_Factory.create(this.dynamicIslandTouchInteractorProvider, this.pluginComponentImpl.provideContextProvider));
            this.expandedStateHandlerProvider = new F0.c();
            F0.c cVar2 = new F0.c();
            this.dynamicIslandAnimationControllerProvider = cVar2;
            C0594DynamicIslandAnimationDelegate_Factory c0594DynamicIslandAnimationDelegate_FactoryCreate = C0594DynamicIslandAnimationDelegate_Factory.create(cVar2, this.smallIslandStateHandlerProvider, this.bigIslandStateHandlerProvider, this.expandedStateHandlerProvider, this.windowViewProvider);
            this.dynamicIslandAnimationDelegateProvider = c0594DynamicIslandAnimationDelegate_FactoryCreate;
            G0.a aVarCreate = DynamicIslandAnimationDelegate_Factory_Impl.create(c0594DynamicIslandAnimationDelegate_FactoryCreate);
            this.factoryProvider = aVarCreate;
            F0.c.a(this.dynamicIslandAnimationControllerProvider, F0.d.b(DynamicIslandAnimationController_Factory.create(this.dynamicIslandEventCoordinatorProvider, this.dynamicIslandWindowStateProvider, aVarCreate)));
            this.dynamicIslandTouchRegionInteractorProvider = new F0.c();
            F0.c.a(this.dynamicIslandTouchInteractorProvider, F0.d.b(DynamicIslandTouchInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.dynamicIslandTouchConstantsRepositoryProvider, this.windowViewProvider, this.dynamicIslandExternalTouchInteractorProvider, this.dynamicIslandWindowViewTouchInteractorProvider, this.dynamicIslandEventCoordinatorProvider, this.bigIslandStateHandlerProvider, this.smallIslandStateHandlerProvider, this.expandedStateHandlerProvider, this.dynamicIslandWindowStateProvider, this.dynamicIslandWindowViewControllerProvider, this.dynamicIslandAnimationControllerProvider, this.dynamicIslandTouchRegionInteractorProvider)));
            F0.c.a(this.expandedStateHandlerProvider, F0.d.b(ExpandedStateHandler_Factory.create(this.dynamicIslandTouchInteractorProvider)));
            this.hiddenStateHandlerProvider = F0.d.b(HiddenStateHandler_Factory.create(this.dynamicIslandTouchInteractorProvider));
            this.appStateHandlerProvider = F0.d.b(AppStateHandler_Factory.create(this.dynamicIslandTouchInteractorProvider));
            this.miniWindowStateHandlerProvider = F0.d.b(MiniWindowStateHandler_Factory.create(this.dynamicIslandTouchInteractorProvider));
            this.appLockControllerProvider = F0.d.b(AppLockController_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideUserTrackerProvider, this.pluginComponentImpl.provideBackgroundExecutorProvider, ConcurrencyModule_ProvideBgHandlerFactory.create()));
            this.antiBurnInManagerProvider = F0.d.b(AntiBurnInManager_Factory.create(this.pluginComponentImpl.provideContextProvider, this.bigIslandStateHandlerProvider, this.smallIslandStateHandlerProvider, this.pluginComponentImpl.pluginScopeProvider));
            this.addEventCoordinatorProvider = F0.d.b(AddEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.clickEventCoordinatorProvider = F0.d.b(ClickEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.deletedEventCoordinatorProvider = F0.d.b(DeletedEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.collapseEventCoordinatorProvider = F0.d.b(CollapseEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.swipeEventCoordinatorProvider = F0.d.b(SwipeEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.updateEventCoordinatorProvider = F0.d.b(UpdateEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.appEventCoordinatorProvider = F0.d.b(AppEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.miniWindowEventCoordinatorProvider = F0.d.b(MiniWindowEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.islandTempHiddenEventCoordinatorProvider = F0.d.b(IslandTempHiddenEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.configChangedEventCoordinatorProvider = F0.d.b(ConfigChangedEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            this.avoidScreenBurnInEventCoordinatorProvider = F0.d.b(AvoidScreenBurnInEventCoordinator_Factory.create(this.dynamicIslandEventCoordinatorProvider));
            F0.c.a(this.dynamicIslandEventCoordinatorProvider, F0.d.b(DynamicIslandEventCoordinator_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.windowViewProvider, this.pluginComponentImpl.avoidScreenBurnInHelperProvider, this.expandedStateHandlerProvider, this.bigIslandStateHandlerProvider, this.smallIslandStateHandlerProvider, this.hiddenStateHandlerProvider, this.appStateHandlerProvider, this.miniWindowStateHandlerProvider, this.dynamicIslandWindowStateProvider, this.pluginComponentImpl.providesSuperSaveModeControllerProvider, this.appLockControllerProvider, this.antiBurnInManagerProvider, this.addEventCoordinatorProvider, this.clickEventCoordinatorProvider, this.deletedEventCoordinatorProvider, this.collapseEventCoordinatorProvider, this.swipeEventCoordinatorProvider, this.updateEventCoordinatorProvider, this.appEventCoordinatorProvider, this.miniWindowEventCoordinatorProvider, this.islandTempHiddenEventCoordinatorProvider, this.configChangedEventCoordinatorProvider, this.avoidScreenBurnInEventCoordinatorProvider, this.dynamicIslandAnimationControllerProvider, this.pluginComponentImpl.notificationSettingsManagerProvider)));
            F0.c.a(this.dynamicIslandTouchRegionInteractorProvider, F0.d.b(DynamicIslandTouchRegionInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.windowViewProvider, this.dynamicIslandWindowViewControllerProvider, this.dynamicIslandEventCoordinatorProvider)));
            F0.c.a(this.dynamicIslandExternalTouchInteractorProvider, F0.d.b(DynamicIslandExternalTouchInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.dynamicIslandTouchRegionInteractorProvider)));
            this.dynamicIslandDesktopStateControllerProvider = F0.d.b(DynamicIslandDesktopStateController_Factory.create(this.pluginComponentImpl.provideContextProvider, this.pluginComponentImpl.provideSystemUIContextProvider, this.windowViewProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideBackgroundExecutorProvider));
            this.dynamicIslandSafeguardsControllerProvider = F0.d.b(DynamicIslandSafeguardsController_Factory.create(this.windowViewProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.providesLifecycleCoroutineScopeProvider, this.dynamicIslandWindowViewControllerProvider));
            this.hyperDropInfoNotifierServiceProvider = F0.d.b(b.b.a(this.pluginComponentImpl.provideUserTrackerProvider));
            this.dynamicIslandSizeRepositoryProvider = F0.d.b(DynamicIslandSizeRepository_Factory.create(this.providesLifecycleCoroutineScopeProvider));
            F0.c.a(this.dynamicIslandWindowViewControllerProvider, F0.d.b(DynamicIslandWindowViewController_Factory.create(this.windowViewProvider, this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.provideMainDelayableExecutorProvider, this.pluginComponentImpl.notificationChronometerManagerProvider, this.pluginComponentImpl.avoidScreenBurnInHelperProvider, this.dynamicIslandWindowStateProvider, this.dynamicIslandExternalTouchInteractorProvider, this.dynamicIslandDesktopStateControllerProvider, this.dynamicIslandSafeguardsControllerProvider, this.dynamicIslandExternalStateRepositoryProvider, this.pluginComponentImpl.autoDensityControllerImplProvider, this.pluginComponentImpl.lottieProgressManagerProvider, this.hyperDropInfoNotifierServiceProvider, this.dynamicIslandSizeRepositoryProvider, ConcurrencyModule_ProvideBgHandlerFactory.create())));
            this.dynamicIslandStateInteractorProvider = F0.d.b(DynamicIslandStateInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.dynamicIslandWindowViewControllerProvider, this.pluginComponentImpl.controlCenterExpandRepositoryImplProvider));
            this.dynamicIslandWindowStateInteractorProvider = F0.d.b(DynamicIslandWindowStateInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.dynamicIslandEventCoordinatorProvider, this.dynamicIslandWindowViewControllerProvider, this.dynamicIslandWindowViewTouchInteractorProvider));
            this.dynamicIslandWindowControllerProvider = F0.d.b(DynamicIslandWindowController_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.windowViewProvider, this.pluginComponentImpl.provideContextProvider, this.dynamicIslandWindowStateInteractorProvider));
            this.dynamicIslandWindowViewRefactorProvider = F0.d.b(DynamicIslandWindowViewRefactor_Factory.create(this.pluginComponentImpl.provideSystemUIContextProvider, this.pluginComponentImpl.provideContextProvider, this.providesLifecycleCoroutineScopeProvider, this.windowViewProvider, this.dynamicIslandEventCoordinatorProvider, this.pluginComponentImpl.provideUserTrackerProvider, this.pluginComponentImpl.provideMainExecutorProvider, this.pluginComponentImpl.configurationRepositoryProvider));
            C0669RegionSamplingHelperRefactor_Factory c0669RegionSamplingHelperRefactor_FactoryCreate = C0669RegionSamplingHelperRefactor_Factory.create(this.pluginComponentImpl.pluginScopeProvider, this.pluginComponentImpl.provideMainExecutorProvider, this.pluginComponentImpl.provideBackgroundExecutorProvider);
            this.regionSamplingHelperRefactorProvider = c0669RegionSamplingHelperRefactor_FactoryCreate;
            this.factoryProvider2 = RegionSamplingHelperRefactor_Factory_Impl.create(c0669RegionSamplingHelperRefactor_FactoryCreate);
            this.dynamicIslandRegionSamplingInteractorProvider = F0.d.b(DynamicIslandRegionSamplingInteractor_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.windowViewProvider, this.pluginComponentImpl.provideContextProvider, this.dynamicIslandWindowStateInteractorProvider, this.factoryProvider2, this.pluginComponentImpl.statusBarAreaRepositoryProvider, this.pluginComponentImpl.statusBarStateRepositoryImplProvider, this.pluginComponentImpl.controlCenterExpandRepositoryImplProvider, this.dynamicIslandExternalStateRepositoryProvider));
            this.dynamicIslandExternalTouchDispatcherProvider = F0.d.b(DynamicIslandExternalTouchDispatcher_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.windowViewProvider, this.dynamicIslandWindowViewTouchInteractorProvider));
            this.dynamicIslandEmptyContentViewControllerProvider = F0.d.b(DynamicIslandEmptyContentViewController_Factory.create(this.windowViewProvider, this.providesLifecycleCoroutineScopeProvider, this.dynamicIslandTouchInteractorProvider));
            C0611IslandIconViewHolder_Factory c0611IslandIconViewHolder_FactoryCreate = C0611IslandIconViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.providesLifecycleCoroutineScopeProvider);
            this.islandIconViewHolderProvider = c0611IslandIconViewHolder_FactoryCreate;
            this.factoryProvider3 = IslandIconViewHolder_Factory_Impl.create(c0611IslandIconViewHolder_FactoryCreate);
            C0627IslandTextViewHolder_Factory c0627IslandTextViewHolder_FactoryCreate = C0627IslandTextViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider);
            this.islandTextViewHolderProvider = c0627IslandTextViewHolder_FactoryCreate;
            this.factoryProvider4 = IslandTextViewHolder_Factory_Impl.create(c0627IslandTextViewHolder_FactoryCreate);
            C0615IslandImageTextViewHolder_Factory c0615IslandImageTextViewHolder_FactoryCreate = C0615IslandImageTextViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider3, this.factoryProvider4);
            this.islandImageTextViewHolderProvider = c0615IslandImageTextViewHolder_FactoryCreate;
            this.factoryProvider5 = IslandImageTextViewHolder_Factory_Impl.create(c0615IslandImageTextViewHolder_FactoryCreate);
            C0621IslandRightTextViewHolder_Factory c0621IslandRightTextViewHolder_FactoryCreate = C0621IslandRightTextViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider);
            this.islandRightTextViewHolderProvider = c0621IslandRightTextViewHolder_FactoryCreate;
            this.factoryProvider6 = IslandRightTextViewHolder_Factory_Impl.create(c0621IslandRightTextViewHolder_FactoryCreate);
            C0612IslandImageTextView2Holder_Factory c0612IslandImageTextView2Holder_FactoryCreate = C0612IslandImageTextView2Holder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider3, this.factoryProvider6);
            this.islandImageTextView2HolderProvider = c0612IslandImageTextView2Holder_FactoryCreate;
            this.factoryProvider7 = IslandImageTextView2Holder_Factory_Impl.create(c0612IslandImageTextView2Holder_FactoryCreate);
            C0613IslandImageTextView3Holder_Factory c0613IslandImageTextView3Holder_FactoryCreate = C0613IslandImageTextView3Holder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider3, this.factoryProvider6);
            this.islandImageTextView3HolderProvider = c0613IslandImageTextView3Holder_FactoryCreate;
            this.factoryProvider8 = IslandImageTextView3Holder_Factory_Impl.create(c0613IslandImageTextView3Holder_FactoryCreate);
            C0614IslandImageTextView4Holder_Factory c0614IslandImageTextView4Holder_FactoryCreate = C0614IslandImageTextView4Holder_Factory.create(this.pluginComponentImpl.provideContextProvider);
            this.islandImageTextView4HolderProvider = c0614IslandImageTextView4Holder_FactoryCreate;
            this.factoryProvider9 = IslandImageTextView4Holder_Factory_Impl.create(c0614IslandImageTextView4Holder_FactoryCreate);
            C0617IslandProgressViewHolder_Factory c0617IslandProgressViewHolder_FactoryCreate = C0617IslandProgressViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider);
            this.islandProgressViewHolderProvider = c0617IslandProgressViewHolder_FactoryCreate;
            this.factoryProvider10 = IslandProgressViewHolder_Factory_Impl.create(c0617IslandProgressViewHolder_FactoryCreate);
            C0616IslandProgressTextViewHolder_Factory c0616IslandProgressTextViewHolder_FactoryCreate = C0616IslandProgressTextViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider10, this.factoryProvider6);
            this.islandProgressTextViewHolderProvider = c0616IslandProgressTextViewHolder_FactoryCreate;
            this.factoryProvider11 = IslandProgressTextViewHolder_Factory_Impl.create(c0616IslandProgressTextViewHolder_FactoryCreate);
            C0609IslandFixedWidthDigitViewHolder_Factory c0609IslandFixedWidthDigitViewHolder_FactoryCreate = C0609IslandFixedWidthDigitViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider);
            this.islandFixedWidthDigitViewHolderProvider = c0609IslandFixedWidthDigitViewHolder_FactoryCreate;
            this.factoryProvider12 = IslandFixedWidthDigitViewHolder_Factory_Impl.create(c0609IslandFixedWidthDigitViewHolder_FactoryCreate);
            C0623IslandSameWidthDigitViewHolder_Factory c0623IslandSameWidthDigitViewHolder_FactoryCreate = C0623IslandSameWidthDigitViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider);
            this.islandSameWidthDigitViewHolderProvider = c0623IslandSameWidthDigitViewHolder_FactoryCreate;
            this.factoryProvider13 = IslandSameWidthDigitViewHolder_Factory_Impl.create(c0623IslandSameWidthDigitViewHolder_FactoryCreate);
            C0607IslandCombineImageViewHolder_Factory c0607IslandCombineImageViewHolder_FactoryCreate = C0607IslandCombineImageViewHolder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider10, this.factoryProvider3);
            this.islandCombineImageViewHolderProvider = c0607IslandCombineImageViewHolder_FactoryCreate;
            this.factoryProvider14 = IslandCombineImageViewHolder_Factory_Impl.create(c0607IslandCombineImageViewHolder_FactoryCreate);
            C0610IslandIconFixedWidthTextHolder_Factory c0610IslandIconFixedWidthTextHolder_FactoryCreate = C0610IslandIconFixedWidthTextHolder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider3);
            this.islandIconFixedWidthTextHolderProvider = c0610IslandIconFixedWidthTextHolder_FactoryCreate;
            this.factoryProvider15 = IslandIconFixedWidthTextHolder_Factory_Impl.create(c0610IslandIconFixedWidthTextHolder_FactoryCreate);
            C0624IslandTextOverIconHolder_Factory c0624IslandTextOverIconHolder_FactoryCreate = C0624IslandTextOverIconHolder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider3);
            this.islandTextOverIconHolderProvider = c0624IslandTextOverIconHolder_FactoryCreate;
            this.factoryProvider16 = IslandTextOverIconHolder_Factory_Impl.create(c0624IslandTextOverIconHolder_FactoryCreate);
            this.islandModuleViewHolderAdapterProvider = IslandModuleViewHolderAdapter_Factory.create(this.pluginComponentImpl.provideContextProvider, this.factoryProvider3, this.factoryProvider5, this.factoryProvider7, this.factoryProvider8, this.factoryProvider9, this.factoryProvider11, this.factoryProvider6, this.factoryProvider12, this.factoryProvider13, this.factoryProvider14, this.factoryProvider15, this.factoryProvider16);
            C0628IslandTemplateBuilder_Factory c0628IslandTemplateBuilder_FactoryCreate = C0628IslandTemplateBuilder_Factory.create(this.pluginComponentImpl.provideContextProvider, this.islandModuleViewHolderAdapterProvider);
            this.islandTemplateBuilderProvider = c0628IslandTemplateBuilder_FactoryCreate;
            G0.a aVarCreate2 = IslandTemplateBuilder_Factory_Impl.create(c0628IslandTemplateBuilder_FactoryCreate);
            this.factoryProvider17 = aVarCreate2;
            G0.a aVarB4 = F0.d.b(IslandTemplateFactory_Factory.create(this.providesLifecycleCoroutineScopeProvider, aVarCreate2));
            this.islandTemplateFactoryProvider = aVarB4;
            C0645DynamicIslandBaseContentViewController_Factory<DynamicIslandBaseContentView> c0645DynamicIslandBaseContentViewController_FactoryCreate = C0645DynamicIslandBaseContentViewController_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.dynamicIslandRegionSamplingInteractorProvider, aVarB4, this.dynamicIslandTouchInteractorProvider);
            this.dynamicIslandBaseContentViewControllerProvider = c0645DynamicIslandBaseContentViewController_FactoryCreate;
            this.factoryImplProvider = DynamicIslandBaseContentViewController_FactoryImpl_Impl.create(c0645DynamicIslandBaseContentViewController_FactoryCreate);
            this.dynamicIslandExposureManagerProvider = F0.d.b(DynamicIslandExposureManager_Factory.create(this.pluginComponentImpl.provideContextProvider, this.dynamicIslandEventCoordinatorProvider, this.antiBurnInManagerProvider));
            C0657DynamicIslandContentViewController_Factory c0657DynamicIslandContentViewController_FactoryCreate = C0657DynamicIslandContentViewController_Factory.create(this.providesLifecycleCoroutineScopeProvider, this.pluginComponentImpl.mainCoroutineContextProvider, this.windowViewProvider, this.dynamicIslandRegionSamplingInteractorProvider, this.islandTemplateFactoryProvider, this.dynamicIslandTouchInteractorProvider, this.dynamicIslandExposureManagerProvider);
            this.dynamicIslandContentViewControllerProvider = c0657DynamicIslandContentViewController_FactoryCreate;
            this.factoryProvider18 = DynamicIslandContentViewController_Factory_Impl.create(c0657DynamicIslandContentViewController_FactoryCreate);
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public DynamicIslandBaseContentViewController.FactoryImpl getBaseContentViewControllerFactory() {
            return (DynamicIslandBaseContentViewController.FactoryImpl) this.factoryImplProvider.get();
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public DynamicIslandContentViewController.Factory getContentViewControllerFactory() {
            return (DynamicIslandContentViewController.Factory) this.factoryProvider18.get();
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public DynamicIslandEventCoordinator getDynamicIslandEventCoordinator() {
            return (DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get();
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public DynamicIslandWindowViewController getDynamicIslandWindowViewController() {
            return (DynamicIslandWindowViewController) this.dynamicIslandWindowViewControllerProvider.get();
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public IslandTemplateFactory getIslandTemplateFactory() {
            return (IslandTemplateFactory) this.islandTemplateFactoryProvider.get();
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public DynamicIslandSizeRepository getSizeRepository() {
            return (DynamicIslandSizeRepository) this.dynamicIslandSizeRepositoryProvider.get();
        }

        @Override // miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent
        public Map<Class<?>, DynamicIslandStartable> getStartables() {
            return g.b(11).c(DynamicIslandStateInteractor.class, (DynamicIslandStartable) this.dynamicIslandStateInteractorProvider.get()).c(DynamicIslandWindowController.class, (DynamicIslandStartable) this.dynamicIslandWindowControllerProvider.get()).c(DynamicIslandWindowViewRefactor.class, (DynamicIslandStartable) this.dynamicIslandWindowViewRefactorProvider.get()).c(DynamicIslandWindowViewController.class, (DynamicIslandStartable) this.dynamicIslandWindowViewControllerProvider.get()).c(DynamicIslandTouchRegionInteractor.class, (DynamicIslandStartable) this.dynamicIslandTouchRegionInteractorProvider.get()).c(DynamicIslandRegionSamplingInteractor.class, (DynamicIslandStartable) this.dynamicIslandRegionSamplingInteractorProvider.get()).c(DynamicIslandExternalTouchDispatcher.class, (DynamicIslandStartable) this.dynamicIslandExternalTouchDispatcherProvider.get()).c(DynamicIslandWindowViewTouchInteractor.class, (DynamicIslandStartable) this.dynamicIslandWindowViewTouchInteractorProvider.get()).c(DynamicIslandTouchInteractor.class, (DynamicIslandStartable) this.dynamicIslandTouchInteractorProvider.get()).c(DynamicIslandEmptyContentViewController.class, (DynamicIslandStartable) this.dynamicIslandEmptyContentViewControllerProvider.get()).c(DynamicIslandSafeguardsController.class, (DynamicIslandStartable) this.dynamicIslandSafeguardsControllerProvider.get()).a();
        }

        private DynamicIslandViewComponentImpl(PluginComponentImpl pluginComponentImpl, DynamicIslandWindowView dynamicIslandWindowView) {
            this.dynamicIslandViewComponentImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
            initialize(dynamicIslandWindowView);
        }
    }

    public static final class MiFlashlightComponentFactory implements MiFlashlightComponent.Factory {
        private final PluginComponentImpl pluginComponentImpl;

        @Override // miui.systemui.flashlight.dagger.MiFlashlightComponent.Factory
        public MiFlashlightComponent create(MiFlashlightLayout miFlashlightLayout) {
            i.b(miFlashlightLayout);
            return new MiFlashlightComponentImpl(this.pluginComponentImpl, new MiFlashlightComponentModule(), miFlashlightLayout);
        }

        private MiFlashlightComponentFactory(PluginComponentImpl pluginComponentImpl) {
            this.pluginComponentImpl = pluginComponentImpl;
        }
    }

    public static final class MiFlashlightComponentImpl implements MiFlashlightComponent {
        private final MiFlashlightComponentImpl miFlashlightComponentImpl;
        private G0.a miFlashlightControllerProvider;
        private final PluginComponentImpl pluginComponentImpl;
        private G0.a provideLifecycleProvider;
        private G0.a provideViewProvider;
        private G0.a rootViewProvider;
        private G0.a talkBackUtilsProvider;

        private void initialize(MiFlashlightComponentModule miFlashlightComponentModule, MiFlashlightLayout miFlashlightLayout) {
            F0.e eVarA = f.a(miFlashlightLayout);
            this.rootViewProvider = eVarA;
            this.provideViewProvider = F0.d.b(MiFlashlightComponentModule_ProvideViewFactory.create(miFlashlightComponentModule, eVarA));
            this.provideLifecycleProvider = F0.d.b(MiFlashlightComponentModule_ProvideLifecycleFactory.create(miFlashlightComponentModule, this.rootViewProvider));
            this.talkBackUtilsProvider = TalkBackUtils_Factory.create(this.pluginComponentImpl.provideContextProvider, ConcurrencyModule_ProvideMainHandlerFactory.create());
            this.miFlashlightControllerProvider = F0.d.b(MiFlashlightController_Factory.create(this.pluginComponentImpl.provideContextProvider, this.rootViewProvider, this.provideLifecycleProvider, this.pluginComponentImpl.miFlashlightManagerProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), MiFlashlightUiOpenGl_Factory.create(), this.pluginComponentImpl.hapticFeedbackImplProvider, this.talkBackUtilsProvider));
        }

        @Override // miui.systemui.flashlight.dagger.MiFlashlightComponent
        public MiFlashlightController createController() {
            return (MiFlashlightController) this.miFlashlightControllerProvider.get();
        }

        @Override // miui.systemui.flashlight.dagger.MiFlashlightComponent
        public View getRootView() {
            return (View) this.provideViewProvider.get();
        }

        private MiFlashlightComponentImpl(PluginComponentImpl pluginComponentImpl, MiFlashlightComponentModule miFlashlightComponentModule, MiFlashlightLayout miFlashlightLayout) {
            this.miFlashlightComponentImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
            initialize(miFlashlightComponentModule, miFlashlightLayout);
        }
    }

    public static final class PluginComponentImpl implements PluginComponent {
        private C0695AlarmScheduler_Factory alarmSchedulerProvider;
        private G0.a autoDensityControllerImplProvider;
        private G0.a avoidScreenBurnInHelperProvider;
        private G0.a bindGlobalSettingsProvider;
        private G0.a bindSecureSettingsProvider;
        private G0.a blurUtilsExtProvider;
        private G0.a broadcastDispatcherProvider;
        private G0.a cloudDataManagerProvider;
        private G0.a configurationRepositoryProvider;
        private G0.a contextComponentResolverProvider;
        private final ContextModule contextModule;
        private G0.a controlCenterExpandRepositoryImplProvider;
        private G0.a controlCenterWindowViewCreatorProvider;
        private G0.a deviceCenterControllerProvider;
        private G0.a deviceControlsComponentFactoryProvider;
        private G0.a deviceControlsPresenterImplProvider;
        private G0.a dynamicIslandViewComponentFactoryProvider;
        private G0.a dynamicIslandWindowViewCreatorProvider;
        private G0.a eventTrackerProvider;
        private G0.a factoryProvider;
        private G0.a factoryProvider2;
        private G0.a factoryProvider3;
        private G0.a factoryProvider4;
        private G0.a factoryProvider5;
        private G0.a factoryProvider6;
        private G0.a focusNotifPreHandlerProvider;
        private G0.a focusNotifUtilsProvider;
        private G0.a focusNotificationControllerProvider;
        private G0.a globalSettingsImplProvider;
        private G0.a hapticFeedbackImplProvider;
        private G0.a hideDeletedFocusControllerProvider;
        private G0.a injectionInflationControllerImplProvider;
        private G0.a lottieProgressManagerProvider;
        private G0.a mainCoroutineContextProvider;
        private G0.a mapOfClassOfAndProviderOfActivityProvider;
        private G0.a mapOfClassOfAndProviderOfBroadcastReceiverProvider;
        private G0.a miFlashlightActivityProvider;
        private G0.a miFlashlightComponentFactoryProvider;
        private G0.a miFlashlightManagerProvider;
        private G0.a miFlashlightOperationReceiverProvider;
        private G0.a miFlashlightReceiverProvider;
        private G0.a miLinkControllerProvider;
        private G0.a moduleViewHolderAdapterProvider;
        private G0.a notificationChronometerManagerProvider;
        private G0.a notificationSettingsManagerProvider;
        private G0.a oneHandedModeRepositoryProvider;
        private G0.a optionalOfMapOfClassOfAndProviderOfActivityProvider;
        private G0.a optionalOfMapOfClassOfAndProviderOfBroadcastReceiverProvider;
        private G0.a optionalOfMapOfClassOfAndProviderOfServiceProvider;
        private final PluginComponentImpl pluginComponentImpl;
        private G0.a pluginComponentProvider;
        private G0.a pluginInstancesRepositoryImplProvider;
        private G0.a pluginLifecycleInteractorProvider;
        private G0.a pluginScopeProvider;
        private G0.a provideBackgroundDelayableExecutorProvider;
        private G0.a provideBackgroundExecutorProvider;
        private G0.a provideBgLooperProvider;
        private G0.a provideContentResolverProvider;
        private G0.a provideContextProvider;
        private G0.a provideDelayableExecutorProvider;
        private G0.a provideExecutorProvider;
        private G0.a provideHandlerProvider;
        private G0.a provideMainDelayableExecutorProvider;
        private G0.a provideMainExecutorProvider;
        private G0.a provideMiPlayAudioManagerProvider;
        private G0.a provideMiPlayMediaPlayerAdapterProvider;
        private G0.a provideOptionalSystemUIContextProvider;
        private G0.a providePluginContextProvider;
        private G0.a provideSharePreferencesProvider;
        private G0.a provideSystemUIContextProvider;
        private G0.a provideUiBackgroundExecutorProvider;
        private G0.a provideUserManagerProvider;
        private G0.a provideUserTrackerProvider;
        private G0.a providesActivityStarterHolderProvider;
        private G0.a providesActivityStarterProvider;
        private G0.a providesEventTrackingContextProvider;
        private G0.a providesRunningAsPluginProvider;
        private G0.a providesSuperSaveModeControllerHolderProvider;
        private G0.a providesSuperSaveModeControllerProvider;
        private G0.a secureSettingsImplProvider;
        private G0.a securityControllerProvider;
        private G0.a signatureCheckerProvider;
        private G0.a statusBarAreaRepositoryProvider;
        private G0.a statusBarStateRepositoryImplProvider;
        private G0.a systemUIResourcesHelperImplProvider;
        private C0686TemplateBuilderV3_Factory templateBuilderV3Provider;
        private C0689TemplateDecoBuilderV3_Factory templateDecoBuilderV3Provider;
        private C0691TemplateDecoLandBuilderV3_Factory templateDecoLandBuilderV3Provider;
        private G0.a templateFactoryV3Provider;
        private C0694TemplateTinyBuilderV3_Factory templateTinyBuilderV3Provider;

        private void initialize(MiPlayModule miPlayModule, PluginDependencyModule pluginDependencyModule, ContextModule contextModule, DependencyProvider dependencyProvider, EventTrackingModule eventTrackingModule) {
            this.provideContextProvider = ContextModule_ProvideContextFactory.create(contextModule);
            G0.a aVar = new G0.a() { // from class: miui.systemui.dagger.DaggerPluginComponent.PluginComponentImpl.1
                @Override // G0.a
                public MiFlashlightComponent.Factory get() {
                    return new MiFlashlightComponentFactory(PluginComponentImpl.this.pluginComponentImpl);
                }
            };
            this.miFlashlightComponentFactoryProvider = aVar;
            G0.a aVarB = F0.d.b(MiFlashlightManager_Factory.create(this.provideContextProvider, aVar));
            this.miFlashlightManagerProvider = aVarB;
            this.miFlashlightActivityProvider = MiFlashlightActivity_Factory.create(aVarB);
            h hVarB = h.b(1).c(MiFlashlightActivity.class, this.miFlashlightActivityProvider).b();
            this.mapOfClassOfAndProviderOfActivityProvider = hVarB;
            this.optionalOfMapOfClassOfAndProviderOfActivityProvider = PresentJdkOptionalInstanceProvider.of(hVarB);
            this.optionalOfMapOfClassOfAndProviderOfServiceProvider = DaggerPluginComponent.absentJdkOptionalProvider();
            this.miFlashlightReceiverProvider = MiFlashlightReceiver_Factory.create(this.miFlashlightManagerProvider);
            h hVarB2 = h.b(1).c(MiFlashlightReceiver.class, this.miFlashlightReceiverProvider).b();
            this.mapOfClassOfAndProviderOfBroadcastReceiverProvider = hVarB2;
            G0.a aVarOf = PresentJdkOptionalInstanceProvider.of(hVarB2);
            this.optionalOfMapOfClassOfAndProviderOfBroadcastReceiverProvider = aVarOf;
            this.contextComponentResolverProvider = F0.d.b(ContextComponentResolver_Factory.create(this.optionalOfMapOfClassOfAndProviderOfActivityProvider, this.optionalOfMapOfClassOfAndProviderOfServiceProvider, aVarOf));
            ContextModule_ProvideSystemUIContextFactory contextModule_ProvideSystemUIContextFactoryCreate = ContextModule_ProvideSystemUIContextFactory.create(contextModule);
            this.provideSystemUIContextProvider = contextModule_ProvideSystemUIContextFactoryCreate;
            this.systemUIResourcesHelperImplProvider = F0.d.b(SystemUIResourcesHelperImpl_Factory.create(contextModule_ProvideSystemUIContextFactoryCreate));
            this.provideMiPlayAudioManagerProvider = F0.d.b(MiPlayModule_ProvideMiPlayAudioManagerFactory.create(miPlayModule, this.provideSystemUIContextProvider));
            this.provideBgLooperProvider = F0.d.b(ConcurrencyModule_ProvideBgLooperFactory.create());
            this.broadcastDispatcherProvider = F0.d.b(BroadcastDispatcher_Factory.create(this.provideContextProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.provideBgLooperProvider));
            G0.a aVarB2 = F0.d.b(ContextModule_ProvideContentResolverFactory.create(contextModule, this.provideContextProvider));
            this.provideContentResolverProvider = aVarB2;
            GlobalSettingsImpl_Factory globalSettingsImpl_FactoryCreate = GlobalSettingsImpl_Factory.create(aVarB2);
            this.globalSettingsImplProvider = globalSettingsImpl_FactoryCreate;
            this.bindGlobalSettingsProvider = F0.d.b(globalSettingsImpl_FactoryCreate);
            ContextModule_ProvideOptionalSystemUIContextFactory contextModule_ProvideOptionalSystemUIContextFactoryCreate = ContextModule_ProvideOptionalSystemUIContextFactory.create(contextModule);
            this.provideOptionalSystemUIContextProvider = contextModule_ProvideOptionalSystemUIContextFactoryCreate;
            this.providesRunningAsPluginProvider = F0.d.b(PluginModule_Companion_ProvidesRunningAsPluginFactory.create(contextModule_ProvideOptionalSystemUIContextFactoryCreate));
            this.mainCoroutineContextProvider = F0.d.b(CoroutinesModule_MainCoroutineContextFactory.create());
            this.providePluginContextProvider = ContextModule_ProvidePluginContextFactory.create(contextModule);
            F0.c cVar = new F0.c();
            this.pluginInstancesRepositoryImplProvider = cVar;
            G0.a aVarB3 = F0.d.b(PluginLifecycleInteractor_Factory.create(this.mainCoroutineContextProvider, this.providePluginContextProvider, this.provideSystemUIContextProvider, cVar));
            this.pluginLifecycleInteractorProvider = aVarB3;
            G0.a aVarB4 = F0.d.b(CoroutinesModule_PluginScopeFactory.create(this.providesRunningAsPluginProvider, aVarB3));
            this.pluginScopeProvider = aVarB4;
            F0.c.a(this.pluginInstancesRepositoryImplProvider, F0.d.b(PluginInstancesRepositoryImpl_Factory.create(aVarB4)));
            this.autoDensityControllerImplProvider = F0.d.b(AutoDensityControllerImpl_Factory.create(this.providePluginContextProvider, this.provideOptionalSystemUIContextProvider, ConcurrencyModule_ProvideMainHandlerFactory.create(), this.pluginScopeProvider));
            F0.e eVarA = f.a(this.pluginComponentImpl);
            this.pluginComponentProvider = eVarA;
            G0.a aVarB5 = F0.d.b(InjectionInflationControllerImpl_Factory.create(eVarA));
            this.injectionInflationControllerImplProvider = aVarB5;
            this.controlCenterWindowViewCreatorProvider = F0.d.b(ControlCenterWindowViewCreator_Factory.create(this.providePluginContextProvider, aVarB5));
            this.provideMainDelayableExecutorProvider = F0.d.b(ConcurrencyModule_ProvideMainDelayableExecutorFactory.create());
            this.miLinkControllerProvider = F0.d.b(MiLinkController_Factory.create(this.providePluginContextProvider, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.provideMainDelayableExecutorProvider));
            this.provideBackgroundExecutorProvider = F0.d.b(ConcurrencyModule_ProvideBackgroundExecutorFactory.create());
            this.securityControllerProvider = F0.d.b(SecurityController_Factory.create(this.provideSystemUIContextProvider, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.provideBackgroundExecutorProvider, this.broadcastDispatcherProvider, this.systemUIResourcesHelperImplProvider));
            this.blurUtilsExtProvider = F0.d.b(BlurUtilsExt_Factory.create(this.providePluginContextProvider));
            this.miFlashlightOperationReceiverProvider = F0.d.b(MiFlashlightOperationReceiver_Factory.create());
            G0.a aVarB6 = F0.d.b(CloudDataManager_Factory.create(this.provideContextProvider));
            this.cloudDataManagerProvider = aVarB6;
            this.notificationSettingsManagerProvider = F0.d.b(NotificationSettingsManager_Factory.create(this.provideContextProvider, aVarB6, ConcurrencyModule_ProvideBgHandlerFactory.create()));
            this.provideHandlerProvider = DependencyProvider_ProvideHandlerFactory.create(dependencyProvider);
            G0.a aVar2 = new G0.a() { // from class: miui.systemui.dagger.DaggerPluginComponent.PluginComponentImpl.2
                @Override // G0.a
                public DynamicIslandViewComponent.Factory get() {
                    return new DynamicIslandViewComponentFactory(PluginComponentImpl.this.pluginComponentImpl);
                }
            };
            this.dynamicIslandViewComponentFactoryProvider = aVar2;
            this.dynamicIslandWindowViewCreatorProvider = F0.d.b(DynamicIslandWindowViewCreator_Factory.create(this.providePluginContextProvider, aVar2));
            C0695AlarmScheduler_Factory c0695AlarmScheduler_FactoryCreate = C0695AlarmScheduler_Factory.create(this.provideSystemUIContextProvider, ConcurrencyModule_ProvideBgHandlerFactory.create());
            this.alarmSchedulerProvider = c0695AlarmScheduler_FactoryCreate;
            G0.a aVarCreate = AlarmScheduler_Factory_Impl.create(c0695AlarmScheduler_FactoryCreate);
            this.factoryProvider = aVarCreate;
            this.hideDeletedFocusControllerProvider = F0.d.b(HideDeletedFocusController_Factory.create(aVarCreate, this.notificationSettingsManagerProvider));
            this.notificationChronometerManagerProvider = F0.d.b(NotificationChronometerManager_Factory.create(this.pluginScopeProvider, this.providePluginContextProvider));
            PluginDependencyHolderImpl_Factory_Factory pluginDependencyHolderImpl_Factory_FactoryCreate = PluginDependencyHolderImpl_Factory_Factory.create(this.pluginScopeProvider, this.pluginInstancesRepositoryImplProvider);
            this.factoryProvider2 = pluginDependencyHolderImpl_Factory_FactoryCreate;
            G0.a aVarB7 = F0.d.b(PluginDependencyModule_ProvidesActivityStarterHolderFactory.create(pluginDependencyModule, pluginDependencyHolderImpl_Factory_FactoryCreate));
            this.providesActivityStarterHolderProvider = aVarB7;
            this.providesActivityStarterProvider = PluginDependencyModule_ProvidesActivityStarterFactory.create(pluginDependencyModule, aVarB7);
            this.focusNotifUtilsProvider = F0.d.b(FocusNotifUtils_Factory.create());
            ModuleViewHolderAdapter_Factory moduleViewHolderAdapter_FactoryCreate = ModuleViewHolderAdapter_Factory.create(this.provideContextProvider, this.provideSystemUIContextProvider);
            this.moduleViewHolderAdapterProvider = moduleViewHolderAdapter_FactoryCreate;
            C0686TemplateBuilderV3_Factory c0686TemplateBuilderV3_FactoryCreate = C0686TemplateBuilderV3_Factory.create(this.provideContextProvider, moduleViewHolderAdapter_FactoryCreate);
            this.templateBuilderV3Provider = c0686TemplateBuilderV3_FactoryCreate;
            this.factoryProvider3 = TemplateBuilderV3_Factory_Impl.create(c0686TemplateBuilderV3_FactoryCreate);
            C0689TemplateDecoBuilderV3_Factory c0689TemplateDecoBuilderV3_FactoryCreate = C0689TemplateDecoBuilderV3_Factory.create(this.provideContextProvider, this.moduleViewHolderAdapterProvider);
            this.templateDecoBuilderV3Provider = c0689TemplateDecoBuilderV3_FactoryCreate;
            this.factoryProvider4 = TemplateDecoBuilderV3_Factory_Impl.create(c0689TemplateDecoBuilderV3_FactoryCreate);
            C0691TemplateDecoLandBuilderV3_Factory c0691TemplateDecoLandBuilderV3_FactoryCreate = C0691TemplateDecoLandBuilderV3_Factory.create(this.provideContextProvider, this.moduleViewHolderAdapterProvider);
            this.templateDecoLandBuilderV3Provider = c0691TemplateDecoLandBuilderV3_FactoryCreate;
            this.factoryProvider5 = TemplateDecoLandBuilderV3_Factory_Impl.create(c0691TemplateDecoLandBuilderV3_FactoryCreate);
            C0694TemplateTinyBuilderV3_Factory c0694TemplateTinyBuilderV3_FactoryCreate = C0694TemplateTinyBuilderV3_Factory.create(this.provideContextProvider, this.moduleViewHolderAdapterProvider);
            this.templateTinyBuilderV3Provider = c0694TemplateTinyBuilderV3_FactoryCreate;
            G0.a aVarCreate2 = TemplateTinyBuilderV3_Factory_Impl.create(c0694TemplateTinyBuilderV3_FactoryCreate);
            this.factoryProvider6 = aVarCreate2;
            this.templateFactoryV3Provider = F0.d.b(TemplateFactoryV3_Factory.create(this.focusNotifUtilsProvider, this.factoryProvider3, this.factoryProvider4, this.factoryProvider5, aVarCreate2));
            G0.a aVarB8 = F0.d.b(ControlCenterExpandRepositoryImpl_Factory.create(this.pluginScopeProvider, this.controlCenterWindowViewCreatorProvider));
            this.controlCenterExpandRepositoryImplProvider = aVarB8;
            this.focusNotifPreHandlerProvider = F0.d.b(FocusNotifPreHandler_Factory.create(this.notificationSettingsManagerProvider, this.provideContextProvider, this.providesActivityStarterProvider, this.templateFactoryV3Provider, this.focusNotifUtilsProvider, aVarB8));
            this.lottieProgressManagerProvider = F0.d.b(LottieProgressManager_Factory.create(this.pluginScopeProvider, this.autoDensityControllerImplProvider));
            G0.a aVarB9 = F0.d.b(SignatureChecker_Factory.create(this.provideContextProvider));
            this.signatureCheckerProvider = aVarB9;
            this.focusNotificationControllerProvider = F0.d.b(FocusNotificationController_Factory.create(this.pluginScopeProvider, this.provideContextProvider, this.provideHandlerProvider, this.dynamicIslandWindowViewCreatorProvider, this.hideDeletedFocusControllerProvider, this.notificationChronometerManagerProvider, this.notificationSettingsManagerProvider, this.focusNotifPreHandlerProvider, this.lottieProgressManagerProvider, this.focusNotifUtilsProvider, aVarB9));
            EventTrackingModule_ProvidesEventTrackingContextFactory eventTrackingModule_ProvidesEventTrackingContextFactoryCreate = EventTrackingModule_ProvidesEventTrackingContextFactory.create(eventTrackingModule, this.provideOptionalSystemUIContextProvider, this.providePluginContextProvider);
            this.providesEventTrackingContextProvider = eventTrackingModule_ProvidesEventTrackingContextFactoryCreate;
            this.eventTrackerProvider = F0.d.b(EventTracker_Factory.create(eventTrackingModule_ProvidesEventTrackingContextFactoryCreate));
            G0.a aVarB10 = F0.d.b(ConcurrencyModule_ProvideUiBackgroundExecutorFactory.create());
            this.provideUiBackgroundExecutorProvider = aVarB10;
            this.hapticFeedbackImplProvider = F0.d.b(HapticFeedbackImpl_Factory.create(this.provideContextProvider, aVarB10));
            this.avoidScreenBurnInHelperProvider = F0.d.b(AvoidScreenBurnInHelper_Factory.create(this.pluginScopeProvider));
            this.configurationRepositoryProvider = F0.d.b(ConfigurationRepository_Factory.create(this.pluginScopeProvider, this.provideContextProvider, this.autoDensityControllerImplProvider));
            G0.a aVarB11 = F0.d.b(OneHandedModeRepository_Factory.create(this.pluginScopeProvider, this.provideContextProvider, ConcurrencyModule_ProvideBgHandlerFactory.create(), this.configurationRepositoryProvider, this.systemUIResourcesHelperImplProvider));
            this.oneHandedModeRepositoryProvider = aVarB11;
            this.statusBarAreaRepositoryProvider = F0.d.b(StatusBarAreaRepository_Factory.create(this.pluginScopeProvider, this.provideContextProvider, this.configurationRepositoryProvider, aVarB11));
            G0.a aVarB12 = F0.d.b(PluginDependencyModule_ProvidesSuperSaveModeControllerHolderFactory.create(pluginDependencyModule, this.factoryProvider2));
            this.providesSuperSaveModeControllerHolderProvider = aVarB12;
            this.providesSuperSaveModeControllerProvider = PluginDependencyModule_ProvidesSuperSaveModeControllerFactory.create(pluginDependencyModule, aVarB12);
            G0.a aVarB13 = F0.d.b(ContextModule_ProvideUserManagerFactory.create(contextModule, this.provideContextProvider));
            this.provideUserManagerProvider = aVarB13;
            this.provideUserTrackerProvider = F0.d.b(SettingsModule_ProvideUserTrackerFactory.create(this.provideContextProvider, aVarB13, ConcurrencyModule_ProvideBgHandlerFactory.create()));
            this.provideMainExecutorProvider = ConcurrencyModule_ProvideMainExecutorFactory.create(this.provideContextProvider);
            this.statusBarStateRepositoryImplProvider = F0.d.b(StatusBarStateRepositoryImpl_Factory.create(this.pluginScopeProvider, this.controlCenterWindowViewCreatorProvider));
            this.provideBackgroundDelayableExecutorProvider = F0.d.b(ConcurrencyModule_ProvideBackgroundDelayableExecutorFactory.create());
            this.provideExecutorProvider = F0.d.b(ConcurrencyModule_ProvideExecutorFactory.create());
            this.provideSharePreferencesProvider = DependencyProvider_ProvideSharePreferencesFactory.create(dependencyProvider, this.providePluginContextProvider);
            this.provideDelayableExecutorProvider = F0.d.b(ConcurrencyModule_ProvideDelayableExecutorFactory.create());
            SecureSettingsImpl_Factory secureSettingsImpl_FactoryCreate = SecureSettingsImpl_Factory.create(this.provideContentResolverProvider);
            this.secureSettingsImplProvider = secureSettingsImpl_FactoryCreate;
            this.bindSecureSettingsProvider = F0.d.b(secureSettingsImpl_FactoryCreate);
            G0.a aVar3 = new G0.a() { // from class: miui.systemui.dagger.DaggerPluginComponent.PluginComponentImpl.3
                @Override // G0.a
                public DeviceControlsComponent.Factory get() {
                    return new DeviceControlsComponentFactory(PluginComponentImpl.this.pluginComponentImpl);
                }
            };
            this.deviceControlsComponentFactoryProvider = aVar3;
            this.deviceControlsPresenterImplProvider = F0.d.b(DeviceControlsPresenterImpl_Factory.create(aVar3, this.providePluginContextProvider));
            this.provideMiPlayMediaPlayerAdapterProvider = F0.d.b(MiPlayModule_ProvideMiPlayMediaPlayerAdapterFactory.create(miPlayModule, this.provideContextProvider));
            this.deviceCenterControllerProvider = F0.d.b(DeviceCenterController_Factory.create(this.providePluginContextProvider, this.provideBgLooperProvider, this.provideMainExecutorProvider, this.provideSharePreferencesProvider));
        }

        private BaseEventTracker injectBaseEventTracker(BaseEventTracker baseEventTracker) {
            BaseEventTracker_MembersInjector.injectSetEventTracker(baseEventTracker, (EventTracker) this.eventTrackerProvider.get());
            return baseEventTracker;
        }

        private BaseMiPlayController injectBaseMiPlayController(BaseMiPlayController baseMiPlayController) {
            BaseMiPlayController_MembersInjector.injectContext(baseMiPlayController, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            BaseMiPlayController_MembersInjector.injectSystemUIContext(baseMiPlayController, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            BaseMiPlayController_MembersInjector.injectMainHandler(baseMiPlayController, ConcurrencyModule_ProvideMainHandlerFactory.provideMainHandler());
            BaseMiPlayController_MembersInjector.inject_MIPLAY_AUDIO_MANAGER(baseMiPlayController, F0.d.a(this.provideMiPlayAudioManagerProvider));
            BaseMiPlayController_MembersInjector.injectBroadcastDispatcher(baseMiPlayController, (BroadcastDispatcher) this.broadcastDispatcherProvider.get());
            BaseMiPlayController_MembersInjector.injectGlobalSettings(baseMiPlayController, (GlobalSettings) this.bindGlobalSettingsProvider.get());
            return baseMiPlayController;
        }

        private FocusNotificationPluginImpl injectFocusNotificationPluginImpl(FocusNotificationPluginImpl focusNotificationPluginImpl) {
            PluginBase_MembersInjector.injectPluginInstancesRepository(focusNotificationPluginImpl, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryImplProvider.get());
            PluginBase_MembersInjector.injectAutoDensityController(focusNotificationPluginImpl, (AutoDensityControllerImpl) this.autoDensityControllerImplProvider.get());
            PluginBase_MembersInjector.injectPluginContext(focusNotificationPluginImpl, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            PluginBase_MembersInjector.injectSysuiContext(focusNotificationPluginImpl, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            FocusNotificationPluginImpl_MembersInjector.injectMNotifSettingsMgr(focusNotificationPluginImpl, (NotificationSettingsManager) this.notificationSettingsManagerProvider.get());
            FocusNotificationPluginImpl_MembersInjector.injectFocusNotificationController(focusNotificationPluginImpl, F0.d.a(this.focusNotificationControllerProvider));
            FocusNotificationPluginImpl_MembersInjector.injectMFocusNotifUtils(focusNotificationPluginImpl, F0.d.a(this.focusNotifUtilsProvider));
            return focusNotificationPluginImpl;
        }

        private LocalMiuiQSTilePlugin injectLocalMiuiQSTilePlugin(LocalMiuiQSTilePlugin localMiuiQSTilePlugin) {
            PluginBase_MembersInjector.injectPluginInstancesRepository(localMiuiQSTilePlugin, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryImplProvider.get());
            PluginBase_MembersInjector.injectAutoDensityController(localMiuiQSTilePlugin, (AutoDensityControllerImpl) this.autoDensityControllerImplProvider.get());
            PluginBase_MembersInjector.injectPluginContext(localMiuiQSTilePlugin, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            PluginBase_MembersInjector.injectSysuiContext(localMiuiQSTilePlugin, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            LocalMiuiQSTilePlugin_MembersInjector.injectMMiLinkController(localMiuiQSTilePlugin, (MiLinkController) this.miLinkControllerProvider.get());
            LocalMiuiQSTilePlugin_MembersInjector.injectMMiFlashlightOperationReceiver(localMiuiQSTilePlugin, (MiFlashlightOperationReceiver) this.miFlashlightOperationReceiverProvider.get());
            return localMiuiQSTilePlugin;
        }

        private MiuiControlCenter injectMiuiControlCenter(MiuiControlCenter miuiControlCenter) {
            PluginBase_MembersInjector.injectPluginInstancesRepository(miuiControlCenter, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryImplProvider.get());
            PluginBase_MembersInjector.injectAutoDensityController(miuiControlCenter, (AutoDensityControllerImpl) this.autoDensityControllerImplProvider.get());
            PluginBase_MembersInjector.injectPluginContext(miuiControlCenter, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            PluginBase_MembersInjector.injectSysuiContext(miuiControlCenter, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            MiuiControlCenter_MembersInjector.injectWindowViewCreator(miuiControlCenter, (ControlCenterWindowViewCreator) this.controlCenterWindowViewCreatorProvider.get());
            MiuiControlCenter_MembersInjector.injectMiLinkController(miuiControlCenter, (MiLinkController) this.miLinkControllerProvider.get());
            MiuiControlCenter_MembersInjector.injectSecurityController(miuiControlCenter, (SecurityController) this.securityControllerProvider.get());
            MiuiControlCenter_MembersInjector.injectBlurUtilsExt(miuiControlCenter, (BlurUtilsExt) this.blurUtilsExtProvider.get());
            return miuiControlCenter;
        }

        private NotificationDynamicIslandPluginImpl injectNotificationDynamicIslandPluginImpl(NotificationDynamicIslandPluginImpl notificationDynamicIslandPluginImpl) {
            PluginBase_MembersInjector.injectPluginInstancesRepository(notificationDynamicIslandPluginImpl, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryImplProvider.get());
            PluginBase_MembersInjector.injectAutoDensityController(notificationDynamicIslandPluginImpl, (AutoDensityControllerImpl) this.autoDensityControllerImplProvider.get());
            PluginBase_MembersInjector.injectPluginContext(notificationDynamicIslandPluginImpl, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            PluginBase_MembersInjector.injectSysuiContext(notificationDynamicIslandPluginImpl, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            NotificationDynamicIslandPluginImpl_MembersInjector.injectWindowViewCreator(notificationDynamicIslandPluginImpl, (DynamicIslandWindowViewCreator) this.dynamicIslandWindowViewCreatorProvider.get());
            NotificationDynamicIslandPluginImpl_MembersInjector.injectFocusNotificationController(notificationDynamicIslandPluginImpl, (FocusNotificationController) this.focusNotificationControllerProvider.get());
            return notificationDynamicIslandPluginImpl;
        }

        private PluginAppComponentFactory injectPluginAppComponentFactory(PluginAppComponentFactory pluginAppComponentFactory) {
            PluginAppComponentFactory_MembersInjector.injectMComponentHelper(pluginAppComponentFactory, (ContextComponentHelper) this.contextComponentResolverProvider.get());
            return pluginAppComponentFactory;
        }

        private PluginBase injectPluginBase(PluginBase pluginBase) {
            PluginBase_MembersInjector.injectPluginInstancesRepository(pluginBase, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryImplProvider.get());
            PluginBase_MembersInjector.injectAutoDensityController(pluginBase, (AutoDensityControllerImpl) this.autoDensityControllerImplProvider.get());
            PluginBase_MembersInjector.injectPluginContext(pluginBase, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            PluginBase_MembersInjector.injectSysuiContext(pluginBase, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            return pluginBase;
        }

        private VolumeDialogPlugin injectVolumeDialogPlugin(VolumeDialogPlugin volumeDialogPlugin) {
            PluginBase_MembersInjector.injectPluginInstancesRepository(volumeDialogPlugin, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryImplProvider.get());
            PluginBase_MembersInjector.injectAutoDensityController(volumeDialogPlugin, (AutoDensityControllerImpl) this.autoDensityControllerImplProvider.get());
            PluginBase_MembersInjector.injectPluginContext(volumeDialogPlugin, ContextModule_ProvideContextFactory.provideContext(this.contextModule));
            PluginBase_MembersInjector.injectSysuiContext(volumeDialogPlugin, ContextModule_ProvideSystemUIContextFactory.provideSystemUIContext(this.contextModule));
            VolumeDialogPlugin_MembersInjector.injectControlCenterExpandRepository(volumeDialogPlugin, (ControlCenterExpandRepository) this.controlCenterExpandRepositoryImplProvider.get());
            return volumeDialogPlugin;
        }

        @Override // miui.systemui.dagger.PluginComponent
        public ViewCreator createViewCreator() {
            return new ViewCreatorImpl(this.pluginComponentImpl);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public ContextComponentHelper getContextComponentHelper() {
            return (ContextComponentHelper) this.contextComponentResolverProvider.get();
        }

        @Override // miui.systemui.dagger.PluginComponent
        public SystemUIResourcesHelper getSystemUIResourcesHelper() {
            return (SystemUIResourcesHelper) this.systemUIResourcesHelperImplProvider.get();
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(ContentProvider contentProvider) {
        }

        private PluginComponentImpl(MiPlayModule miPlayModule, PluginDependencyModule pluginDependencyModule, ContextModule contextModule, DependencyProvider dependencyProvider, EventTrackingModule eventTrackingModule) {
            this.pluginComponentImpl = this;
            this.contextModule = contextModule;
            initialize(miPlayModule, pluginDependencyModule, contextModule, dependencyProvider, eventTrackingModule);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(PluginAppComponentFactory pluginAppComponentFactory) {
            injectPluginAppComponentFactory(pluginAppComponentFactory);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(BaseMiPlayController baseMiPlayController) {
            injectBaseMiPlayController(baseMiPlayController);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(MiuiControlCenter miuiControlCenter) {
            injectMiuiControlCenter(miuiControlCenter);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(LocalMiuiQSTilePlugin localMiuiQSTilePlugin) {
            injectLocalMiuiQSTilePlugin(localMiuiQSTilePlugin);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(FocusNotificationPluginImpl focusNotificationPluginImpl) {
            injectFocusNotificationPluginImpl(focusNotificationPluginImpl);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(NotificationDynamicIslandPluginImpl notificationDynamicIslandPluginImpl) {
            injectNotificationDynamicIslandPluginImpl(notificationDynamicIslandPluginImpl);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(VolumeDialogPlugin volumeDialogPlugin) {
            injectVolumeDialogPlugin(volumeDialogPlugin);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(PluginBase pluginBase) {
            injectPluginBase(pluginBase);
        }

        @Override // miui.systemui.dagger.PluginComponent
        public void inject(BaseEventTracker baseEventTracker) {
            injectBaseEventTracker(baseEventTracker);
        }
    }

    public static final class PluginViewInstanceCreatorImpl implements PluginViewInstanceCreator {
        private final PluginComponentImpl pluginComponentImpl;
        private final PluginViewInstanceCreatorImpl pluginViewInstanceCreatorImpl;
        private final ViewCreatorImpl viewCreatorImpl;

        private PluginViewInstanceCreatorImpl(PluginComponentImpl pluginComponentImpl, ViewCreatorImpl viewCreatorImpl, ViewAttributeProvider viewAttributeProvider) {
            this.pluginViewInstanceCreatorImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
            this.viewCreatorImpl = viewCreatorImpl;
        }
    }

    public static final class PresentJdkOptionalInstanceProvider<T> implements G0.a {
        private final G0.a delegate;

        private PresentJdkOptionalInstanceProvider(G0.a aVar) {
            this.delegate = (G0.a) i.b(aVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> G0.a of(G0.a aVar) {
            return new PresentJdkOptionalInstanceProvider(aVar);
        }

        @Override // G0.a
        public Optional<T> get() {
            return Optional.of(this.delegate.get());
        }
    }

    public static final class ViewCreatorImpl implements ViewCreator {
        private final PluginComponentImpl pluginComponentImpl;
        private final ViewCreatorImpl viewCreatorImpl;

        @Override // miui.systemui.dagger.ViewCreator
        public ControlCenterViewInstanceCreator createControlCenterViewInstanceCreator(ViewAttributeProvider viewAttributeProvider) {
            i.b(viewAttributeProvider);
            return new ControlCenterViewInstanceCreatorImpl(this.pluginComponentImpl, this.viewCreatorImpl, viewAttributeProvider);
        }

        @Override // miui.systemui.dagger.ViewCreator
        public PluginViewInstanceCreator createPluginViewInstanceCreator(ViewAttributeProvider viewAttributeProvider) {
            i.b(viewAttributeProvider);
            return new PluginViewInstanceCreatorImpl(this.pluginComponentImpl, this.viewCreatorImpl, viewAttributeProvider);
        }

        private ViewCreatorImpl(PluginComponentImpl pluginComponentImpl) {
            this.viewCreatorImpl = this;
            this.pluginComponentImpl = pluginComponentImpl;
        }
    }

    private DaggerPluginComponent() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> G0.a absentJdkOptionalProvider() {
        return ABSENT_JDK_OPTIONAL_PROVIDER;
    }

    public static Builder builder() {
        return new Builder();
    }
}
