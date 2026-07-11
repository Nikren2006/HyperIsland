package miui.systemui.controlcenter.panel.secondary.brightness;

import I0.m;
import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.panel.secondary.BrightnessPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class BrightnessPanelDelegate extends SecondaryPanelDelegateBase<BrightnessPanelParams> {
    private final ArrayList<SecondaryPanelDelegateBase<BrightnessPanelParams>> childControllers;
    private final ArrayList<SecondaryPanelDelegateBase<BrightnessPanelParams>> childDelegates;
    private boolean listening;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private final BrightnessPanelSliderDelegate sliderDelegate;
    private final BrightnessPanelTilesDelegate tilesDelegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelDelegate(ControlCenterSecondaryBinding secondaryBinding, BrightnessPanelSliderDelegate sliderDelegate, BrightnessPanelTilesDelegate tilesDelegate) {
        super(secondaryBinding);
        n.g(secondaryBinding, "secondaryBinding");
        n.g(sliderDelegate, "sliderDelegate");
        n.g(tilesDelegate, "tilesDelegate");
        this.secondaryBinding = secondaryBinding;
        this.sliderDelegate = sliderDelegate;
        this.tilesDelegate = tilesDelegate;
        this.childControllers = m.f(sliderDelegate, tilesDelegate);
        this.childDelegates = m.f(sliderDelegate, tilesDelegate);
    }

    public final int getCurrentProgress() {
        return this.sliderDelegate.getVSlider().getProgress();
    }

    public final boolean getListening() {
        return this.listening;
    }

    public final float getOutlineRadius() {
        return this.sliderDelegate.getOutlineRadius();
    }

    public final float getProgressRadius() {
        return this.sliderDelegate.getProgressRadius();
    }

    public final void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
        this.sliderDelegate.setListening(z2);
        this.tilesDelegate.setListening(z2);
    }

    public final void setOutlineRadius(float f2) {
        this.sliderDelegate.setOutlineRadius(f2);
    }

    public final void setProgressRadius(float f2) {
        this.sliderDelegate.setProgressRadius(f2);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<SecondaryPanelDelegateBase<BrightnessPanelParams>> getChildControllers() {
        return this.childControllers;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public ArrayList<SecondaryPanelDelegateBase<BrightnessPanelParams>> getChildDelegates() {
        return this.childDelegates;
    }
}
