package miui.systemui.controlcenter.panel.main.recyclerview;

import android.view.View;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class MultipleAnimViewHolder extends MainPanelItemViewHolder {
    private final ControlCenterExpandController expandController;
    private float preItemScale;
    private float preItemTranslationX;
    private float preItemTranslationY;
    private final ShadeSwitchController shadeSwitchController;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipleAnimViewHolder(View itemView, ShadeSwitchController shadeSwitchController, ControlCenterExpandController expandController) {
        super(itemView);
        n.g(itemView, "itemView");
        n.g(shadeSwitchController, "shadeSwitchController");
        n.g(expandController, "expandController");
        this.shadeSwitchController = shadeSwitchController;
        this.expandController = expandController;
        this.preItemScale = 1.0f;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder, miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onFrameCallback() {
        float f2;
        float f3;
        float f4;
        float translationY = this.itemView.getTranslationY();
        float translationX = this.itemView.getTranslationX();
        float scaleX = this.itemView.getScaleX();
        boolean ignoreHolderTranslation = getIgnoreHolderTranslation();
        boolean ignoreHolderScale = getIgnoreHolderScale();
        setIgnoreHolderTranslation(false);
        setIgnoreHolderScale(false);
        super.onFrameCallback();
        setIgnoreHolderTranslation(ignoreHolderTranslation);
        setIgnoreHolderScale(ignoreHolderScale);
        float translationY2 = this.itemView.getTranslationY();
        float translationX2 = this.itemView.getTranslationX();
        float scaleX2 = this.itemView.getScaleX();
        float f5 = this.preItemTranslationX;
        if (f5 == translationX2 && this.preItemTranslationY == translationY2 && this.preItemScale == scaleX2) {
            f2 = 0.0f;
            f3 = 0.0f;
            f4 = 0.0f;
        } else {
            f2 = translationX2 - f5;
            f3 = translationY2 - this.preItemTranslationY;
            f4 = scaleX2 - this.preItemScale;
        }
        float f6 = scaleX + f4;
        this.itemView.setTranslationY(translationY + f3);
        this.itemView.setTranslationX(translationX + f2);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        View itemView = this.itemView;
        n.f(itemView, "itemView");
        commonUtils.setScaleXEx(itemView, f6);
        View itemView2 = this.itemView;
        n.f(itemView2, "itemView");
        commonUtils.setScaleYEx(itemView2, f6);
        this.preItemTranslationX = translationX2;
        this.preItemTranslationY = translationY2;
        this.preItemScale = scaleX2;
        if (this.expandController.getAppearance() || this.shadeSwitchController.getSwitchProgress() != 0.0f) {
            return;
        }
        if (getShrinkY() == 0.0f) {
            this.preItemTranslationY = translationY2;
            this.itemView.setTranslationY(translationY2);
        }
        if (getShrinkX() == 0.0f) {
            this.preItemTranslationX = translationX2;
            this.itemView.setTranslationX(translationX2);
        }
        if (getExpandScaleRatio() == 0.0f) {
            this.preItemScale = scaleX2;
            this.itemView.setScaleX(scaleX2);
            this.itemView.setScaleY(this.preItemScale);
        }
    }
}
