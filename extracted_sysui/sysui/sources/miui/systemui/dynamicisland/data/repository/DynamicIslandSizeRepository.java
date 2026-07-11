package miui.systemui.dynamicisland.data.repository;

import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandSizeRepository {
    private final u _cutoutY;
    private final u _islandBatteryWidth;
    private final u _islandClockWidth;
    private final u _islandMaxWidth;
    private final I cutoutY;
    private final I islandBatteryWidth;
    private final I islandClockWidth;
    private final I islandMaxWidth;
    private final E scope;

    public DynamicIslandSizeRepository(@DynamicIsland E scope) {
        n.g(scope, "scope");
        this.scope = scope;
        Float fValueOf = Float.valueOf(0.0f);
        u uVarA = K.a(fValueOf);
        this._cutoutY = uVarA;
        this.cutoutY = AbstractC0420h.b(uVarA);
        u uVarA2 = K.a(fValueOf);
        this._islandMaxWidth = uVarA2;
        this.islandMaxWidth = AbstractC0420h.b(uVarA2);
        u uVarA3 = K.a(fValueOf);
        this._islandClockWidth = uVarA3;
        this.islandClockWidth = AbstractC0420h.b(uVarA3);
        u uVarA4 = K.a(fValueOf);
        this._islandBatteryWidth = uVarA4;
        this.islandBatteryWidth = AbstractC0420h.b(uVarA4);
    }

    public final I getCutoutY() {
        return this.cutoutY;
    }

    public final I getIslandBatteryWidth() {
        return this.islandBatteryWidth;
    }

    public final I getIslandClockWidth() {
        return this.islandClockWidth;
    }

    public final I getIslandMaxWidth() {
        return this.islandMaxWidth;
    }

    public final void updateCutoutY(float f2) {
        this._cutoutY.setValue(Float.valueOf(f2));
    }

    public final void updateIslandMaxWidth(float f2, float f3, float f4) {
        this._islandMaxWidth.setValue(Float.valueOf(f2));
        this._islandClockWidth.setValue(Float.valueOf(f3));
        this._islandBatteryWidth.setValue(Float.valueOf(f4));
    }
}
