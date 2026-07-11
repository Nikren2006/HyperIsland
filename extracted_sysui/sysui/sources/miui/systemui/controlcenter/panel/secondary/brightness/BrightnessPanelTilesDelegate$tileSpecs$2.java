package miui.systemui.controlcenter.panel.secondary.brightness;

import I0.m;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelTilesDelegate$tileSpecs$2 extends o implements Function0 {
    public static final BrightnessPanelTilesDelegate$tileSpecs$2 INSTANCE = new BrightnessPanelTilesDelegate$tileSpecs$2();

    public BrightnessPanelTilesDelegate$tileSpecs$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<String> invoke() {
        return m.j("autobrightness", "night", "papermode");
    }
}
