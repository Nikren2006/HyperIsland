package miui.systemui.flashlight.effect;

import java.util.Arrays;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class Beam {
    private float beamAlpha;
    private float beamDispersion;
    private float beamE;
    private float beamHighBand;
    private float beamLineY;
    private final float[] beamPointF;
    private final float[] beamRgb;
    private float beamScaleX;
    private float beamShift;

    public Beam(float[] beamPointF, float f2, float f3, float f4, float[] beamRgb, float f5, float f6, float f7, float f8) {
        n.g(beamPointF, "beamPointF");
        n.g(beamRgb, "beamRgb");
        this.beamPointF = beamPointF;
        this.beamLineY = f2;
        this.beamHighBand = f3;
        this.beamE = f4;
        this.beamRgb = beamRgb;
        this.beamScaleX = f5;
        this.beamAlpha = f6;
        this.beamShift = f7;
        this.beamDispersion = f8;
    }

    public final float[] component1() {
        return this.beamPointF;
    }

    public final float component2() {
        return this.beamLineY;
    }

    public final float component3() {
        return this.beamHighBand;
    }

    public final float component4() {
        return this.beamE;
    }

    public final float[] component5() {
        return this.beamRgb;
    }

    public final float component6() {
        return this.beamScaleX;
    }

    public final float component7() {
        return this.beamAlpha;
    }

    public final float component8() {
        return this.beamShift;
    }

    public final float component9() {
        return this.beamDispersion;
    }

    public final Beam copy(float[] beamPointF, float f2, float f3, float f4, float[] beamRgb, float f5, float f6, float f7, float f8) {
        n.g(beamPointF, "beamPointF");
        n.g(beamRgb, "beamRgb");
        return new Beam(beamPointF, f2, f3, f4, beamRgb, f5, f6, f7, f8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Beam)) {
            return false;
        }
        Beam beam = (Beam) obj;
        return n.c(this.beamPointF, beam.beamPointF) && Float.compare(this.beamLineY, beam.beamLineY) == 0 && Float.compare(this.beamHighBand, beam.beamHighBand) == 0 && Float.compare(this.beamE, beam.beamE) == 0 && n.c(this.beamRgb, beam.beamRgb) && Float.compare(this.beamScaleX, beam.beamScaleX) == 0 && Float.compare(this.beamAlpha, beam.beamAlpha) == 0 && Float.compare(this.beamShift, beam.beamShift) == 0 && Float.compare(this.beamDispersion, beam.beamDispersion) == 0;
    }

    public final float getBeamAlpha() {
        return this.beamAlpha;
    }

    public final float getBeamDispersion() {
        return this.beamDispersion;
    }

    public final float getBeamE() {
        return this.beamE;
    }

    public final float getBeamHighBand() {
        return this.beamHighBand;
    }

    public final float getBeamLineY() {
        return this.beamLineY;
    }

    public final float[] getBeamPointF() {
        return this.beamPointF;
    }

    public final float[] getBeamRgb() {
        return this.beamRgb;
    }

    public final float getBeamScaleX() {
        return this.beamScaleX;
    }

    public final float getBeamShift() {
        return this.beamShift;
    }

    public int hashCode() {
        return (((((((((((((((Arrays.hashCode(this.beamPointF) * 31) + Float.hashCode(this.beamLineY)) * 31) + Float.hashCode(this.beamHighBand)) * 31) + Float.hashCode(this.beamE)) * 31) + Arrays.hashCode(this.beamRgb)) * 31) + Float.hashCode(this.beamScaleX)) * 31) + Float.hashCode(this.beamAlpha)) * 31) + Float.hashCode(this.beamShift)) * 31) + Float.hashCode(this.beamDispersion);
    }

    public final void setBeamAlpha(float f2) {
        this.beamAlpha = f2;
    }

    public final void setBeamDispersion(float f2) {
        this.beamDispersion = f2;
    }

    public final void setBeamE(float f2) {
        this.beamE = f2;
    }

    public final void setBeamHighBand(float f2) {
        this.beamHighBand = f2;
    }

    public final void setBeamLineY(float f2) {
        this.beamLineY = f2;
    }

    public final void setBeamScaleX(float f2) {
        this.beamScaleX = f2;
    }

    public final void setBeamShift(float f2) {
        this.beamShift = f2;
    }

    public String toString() {
        return "Beam(beamPointF=" + Arrays.toString(this.beamPointF) + ", beamLineY=" + this.beamLineY + ", beamHighBand=" + this.beamHighBand + ", beamE=" + this.beamE + ", beamRgb=" + Arrays.toString(this.beamRgb) + ", beamScaleX=" + this.beamScaleX + ", beamAlpha=" + this.beamAlpha + ", beamShift=" + this.beamShift + ", beamDispersion=" + this.beamDispersion + ")";
    }
}
