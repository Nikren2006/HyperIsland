package miui.systemui.controlcenter.panel.main;

import android.os.Looper;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.footer.FooterSpaceController;
import miui.systemui.controlcenter.panel.main.header.HeaderSpaceController;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;
import miui.systemui.controlcenter.panel.main.qs.CompactQSCardController;
import miui.systemui.controlcenter.panel.main.qs.CompactQSListController;
import miui.systemui.controlcenter.panel.main.qs.EditButtonController;
import miui.systemui.controlcenter.panel.main.qs.QSCardsController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.qs.WordlessModeController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter;
import miui.systemui.controlcenter.panel.main.security.SecurityFooterController;
import miui.systemui.controlcenter.panel.main.volume.VolumeSliderController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelContentDistributor_Factory implements F0.e {
    private final G0.a adapterFactoryProvider;
    private final G0.a brightnessSliderControllerProvider;
    private final G0.a compactQSCardProvider;
    private final G0.a compactQSListProvider;
    private final G0.a deviceCenterControllerProvider;
    private final G0.a deviceControlsEntryControllerProvider;
    private final G0.a editButtonControllerProvider;
    private final G0.a footerFactoryProvider;
    private final G0.a headerFactoryProvider;
    private final G0.a mediaPlayerControllerProvider;
    private final G0.a modeControllerProvider;
    private final G0.a qsCardProvider;
    private final G0.a qsListControllerProvider;
    private final G0.a securityFooterControllerProvider;
    private final G0.a uiLooperProvider;
    private final G0.a volumeSliderControllerProvider;
    private final G0.a windowViewProvider;
    private final G0.a wordlessModeControllerProvider;

    public MainPanelContentDistributor_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18) {
        this.windowViewProvider = aVar;
        this.modeControllerProvider = aVar2;
        this.qsListControllerProvider = aVar3;
        this.qsCardProvider = aVar4;
        this.compactQSCardProvider = aVar5;
        this.compactQSListProvider = aVar6;
        this.mediaPlayerControllerProvider = aVar7;
        this.brightnessSliderControllerProvider = aVar8;
        this.volumeSliderControllerProvider = aVar9;
        this.editButtonControllerProvider = aVar10;
        this.wordlessModeControllerProvider = aVar11;
        this.deviceControlsEntryControllerProvider = aVar12;
        this.deviceCenterControllerProvider = aVar13;
        this.securityFooterControllerProvider = aVar14;
        this.headerFactoryProvider = aVar15;
        this.footerFactoryProvider = aVar16;
        this.adapterFactoryProvider = aVar17;
        this.uiLooperProvider = aVar18;
    }

    public static MainPanelContentDistributor_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18) {
        return new MainPanelContentDistributor_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18);
    }

    public static MainPanelContentDistributor newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, QSListController qSListController, QSCardsController qSCardsController, CompactQSCardController compactQSCardController, CompactQSListController compactQSListController, MediaPlayerController mediaPlayerController, BrightnessSliderController brightnessSliderController, VolumeSliderController volumeSliderController, EditButtonController editButtonController, WordlessModeController wordlessModeController, E0.a aVar2, E0.a aVar3, SecurityFooterController securityFooterController, HeaderSpaceController.Factory factory, FooterSpaceController.Factory factory2, MainPanelAdapter.Factory factory3, Looper looper) {
        return new MainPanelContentDistributor(controlCenterWindowViewImpl, aVar, qSListController, qSCardsController, compactQSCardController, compactQSListController, mediaPlayerController, brightnessSliderController, volumeSliderController, editButtonController, wordlessModeController, aVar2, aVar3, securityFooterController, factory, factory2, factory3, looper);
    }

    @Override // G0.a
    public MainPanelContentDistributor get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), F0.d.a(this.modeControllerProvider), (QSListController) this.qsListControllerProvider.get(), (QSCardsController) this.qsCardProvider.get(), (CompactQSCardController) this.compactQSCardProvider.get(), (CompactQSListController) this.compactQSListProvider.get(), (MediaPlayerController) this.mediaPlayerControllerProvider.get(), (BrightnessSliderController) this.brightnessSliderControllerProvider.get(), (VolumeSliderController) this.volumeSliderControllerProvider.get(), (EditButtonController) this.editButtonControllerProvider.get(), (WordlessModeController) this.wordlessModeControllerProvider.get(), F0.d.a(this.deviceControlsEntryControllerProvider), F0.d.a(this.deviceCenterControllerProvider), (SecurityFooterController) this.securityFooterControllerProvider.get(), (HeaderSpaceController.Factory) this.headerFactoryProvider.get(), (FooterSpaceController.Factory) this.footerFactoryProvider.get(), (MainPanelAdapter.Factory) this.adapterFactoryProvider.get(), (Looper) this.uiLooperProvider.get());
    }
}
