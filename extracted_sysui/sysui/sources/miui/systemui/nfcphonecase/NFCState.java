package miui.systemui.nfcphonecase;

/* JADX INFO: loaded from: classes4.dex */
public final class NFCState {
    private final float blurRadius;
    private float blurScale;
    private float boxGlowColorA;
    private float boxGlowColorB;
    private float boxGlowColorG;
    private float boxGlowColorR;
    private float boxGlowIntensity;
    private float boxGlowStrength;
    private float dimRatio;
    private float ringGlowColorA;
    private float ringGlowColorB;
    private float ringGlowColorG;
    private float ringGlowColorR;
    private float ringGlowIntensity;
    private float ringGlowStrength;
    private float ringSize;
    private float ringThickness;

    public NFCState(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18) {
        this.blurRadius = f2;
        this.blurScale = f3;
        this.dimRatio = f4;
        this.boxGlowStrength = f5;
        this.boxGlowIntensity = f6;
        this.boxGlowColorR = f7;
        this.boxGlowColorG = f8;
        this.boxGlowColorB = f9;
        this.boxGlowColorA = f10;
        this.ringSize = f11;
        this.ringThickness = f12;
        this.ringGlowStrength = f13;
        this.ringGlowIntensity = f14;
        this.ringGlowColorR = f15;
        this.ringGlowColorG = f16;
        this.ringGlowColorB = f17;
        this.ringGlowColorA = f18;
    }

    public final float component1() {
        return this.blurRadius;
    }

    public final float component10() {
        return this.ringSize;
    }

    public final float component11() {
        return this.ringThickness;
    }

    public final float component12() {
        return this.ringGlowStrength;
    }

    public final float component13() {
        return this.ringGlowIntensity;
    }

    public final float component14() {
        return this.ringGlowColorR;
    }

    public final float component15() {
        return this.ringGlowColorG;
    }

    public final float component16() {
        return this.ringGlowColorB;
    }

    public final float component17() {
        return this.ringGlowColorA;
    }

    public final float component2() {
        return this.blurScale;
    }

    public final float component3() {
        return this.dimRatio;
    }

    public final float component4() {
        return this.boxGlowStrength;
    }

    public final float component5() {
        return this.boxGlowIntensity;
    }

    public final float component6() {
        return this.boxGlowColorR;
    }

    public final float component7() {
        return this.boxGlowColorG;
    }

    public final float component8() {
        return this.boxGlowColorB;
    }

    public final float component9() {
        return this.boxGlowColorA;
    }

    public final NFCState copy(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18) {
        return new NFCState(f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NFCState)) {
            return false;
        }
        NFCState nFCState = (NFCState) obj;
        return Float.compare(this.blurRadius, nFCState.blurRadius) == 0 && Float.compare(this.blurScale, nFCState.blurScale) == 0 && Float.compare(this.dimRatio, nFCState.dimRatio) == 0 && Float.compare(this.boxGlowStrength, nFCState.boxGlowStrength) == 0 && Float.compare(this.boxGlowIntensity, nFCState.boxGlowIntensity) == 0 && Float.compare(this.boxGlowColorR, nFCState.boxGlowColorR) == 0 && Float.compare(this.boxGlowColorG, nFCState.boxGlowColorG) == 0 && Float.compare(this.boxGlowColorB, nFCState.boxGlowColorB) == 0 && Float.compare(this.boxGlowColorA, nFCState.boxGlowColorA) == 0 && Float.compare(this.ringSize, nFCState.ringSize) == 0 && Float.compare(this.ringThickness, nFCState.ringThickness) == 0 && Float.compare(this.ringGlowStrength, nFCState.ringGlowStrength) == 0 && Float.compare(this.ringGlowIntensity, nFCState.ringGlowIntensity) == 0 && Float.compare(this.ringGlowColorR, nFCState.ringGlowColorR) == 0 && Float.compare(this.ringGlowColorG, nFCState.ringGlowColorG) == 0 && Float.compare(this.ringGlowColorB, nFCState.ringGlowColorB) == 0 && Float.compare(this.ringGlowColorA, nFCState.ringGlowColorA) == 0;
    }

    public final float getBlurRadius() {
        return this.blurRadius;
    }

    public final float getBlurScale() {
        return this.blurScale;
    }

    public final float getBoxGlowColorA() {
        return this.boxGlowColorA;
    }

    public final float getBoxGlowColorB() {
        return this.boxGlowColorB;
    }

    public final float getBoxGlowColorG() {
        return this.boxGlowColorG;
    }

    public final float getBoxGlowColorR() {
        return this.boxGlowColorR;
    }

    public final float getBoxGlowIntensity() {
        return this.boxGlowIntensity;
    }

    public final float getBoxGlowStrength() {
        return this.boxGlowStrength;
    }

    public final float getDimRatio() {
        return this.dimRatio;
    }

    public final float getRingGlowColorA() {
        return this.ringGlowColorA;
    }

    public final float getRingGlowColorB() {
        return this.ringGlowColorB;
    }

    public final float getRingGlowColorG() {
        return this.ringGlowColorG;
    }

    public final float getRingGlowColorR() {
        return this.ringGlowColorR;
    }

    public final float getRingGlowIntensity() {
        return this.ringGlowIntensity;
    }

    public final float getRingGlowStrength() {
        return this.ringGlowStrength;
    }

    public final float getRingSize() {
        return this.ringSize;
    }

    public final float getRingThickness() {
        return this.ringThickness;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((Float.hashCode(this.blurRadius) * 31) + Float.hashCode(this.blurScale)) * 31) + Float.hashCode(this.dimRatio)) * 31) + Float.hashCode(this.boxGlowStrength)) * 31) + Float.hashCode(this.boxGlowIntensity)) * 31) + Float.hashCode(this.boxGlowColorR)) * 31) + Float.hashCode(this.boxGlowColorG)) * 31) + Float.hashCode(this.boxGlowColorB)) * 31) + Float.hashCode(this.boxGlowColorA)) * 31) + Float.hashCode(this.ringSize)) * 31) + Float.hashCode(this.ringThickness)) * 31) + Float.hashCode(this.ringGlowStrength)) * 31) + Float.hashCode(this.ringGlowIntensity)) * 31) + Float.hashCode(this.ringGlowColorR)) * 31) + Float.hashCode(this.ringGlowColorG)) * 31) + Float.hashCode(this.ringGlowColorB)) * 31) + Float.hashCode(this.ringGlowColorA);
    }

    public final void setBlurScale(float f2) {
        this.blurScale = f2;
    }

    public final void setBoxGlowColorA(float f2) {
        this.boxGlowColorA = f2;
    }

    public final void setBoxGlowColorB(float f2) {
        this.boxGlowColorB = f2;
    }

    public final void setBoxGlowColorG(float f2) {
        this.boxGlowColorG = f2;
    }

    public final void setBoxGlowColorR(float f2) {
        this.boxGlowColorR = f2;
    }

    public final void setBoxGlowIntensity(float f2) {
        this.boxGlowIntensity = f2;
    }

    public final void setBoxGlowStrength(float f2) {
        this.boxGlowStrength = f2;
    }

    public final void setDimRatio(float f2) {
        this.dimRatio = f2;
    }

    public final void setRingGlowColorA(float f2) {
        this.ringGlowColorA = f2;
    }

    public final void setRingGlowColorB(float f2) {
        this.ringGlowColorB = f2;
    }

    public final void setRingGlowColorG(float f2) {
        this.ringGlowColorG = f2;
    }

    public final void setRingGlowColorR(float f2) {
        this.ringGlowColorR = f2;
    }

    public final void setRingGlowIntensity(float f2) {
        this.ringGlowIntensity = f2;
    }

    public final void setRingGlowStrength(float f2) {
        this.ringGlowStrength = f2;
    }

    public final void setRingSize(float f2) {
        this.ringSize = f2;
    }

    public final void setRingThickness(float f2) {
        this.ringThickness = f2;
    }

    public String toString() {
        return "NFCState(blurRadius=" + this.blurRadius + ", blurScale=" + this.blurScale + ", dimRatio=" + this.dimRatio + ", boxGlowStrength=" + this.boxGlowStrength + ", boxGlowIntensity=" + this.boxGlowIntensity + ", boxGlowColorR=" + this.boxGlowColorR + ", boxGlowColorG=" + this.boxGlowColorG + ", boxGlowColorB=" + this.boxGlowColorB + ", boxGlowColorA=" + this.boxGlowColorA + ", ringSize=" + this.ringSize + ", ringThickness=" + this.ringThickness + ", ringGlowStrength=" + this.ringGlowStrength + ", ringGlowIntensity=" + this.ringGlowIntensity + ", ringGlowColorR=" + this.ringGlowColorR + ", ringGlowColorG=" + this.ringGlowColorG + ", ringGlowColorB=" + this.ringGlowColorB + ", ringGlowColorA=" + this.ringGlowColorA + ")";
    }
}
