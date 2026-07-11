package miui.systemui.flashlight.effect;

import java.util.Arrays;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class BeamConfig {
    private final Beam beam1;
    private final Beam beam2;
    private final Beam beam3;
    private final Beam beam4;
    private final Beam beam5;
    private final Beam beam6;
    private final float[] particleColor;
    private float particleCount;
    private float particleSize;
    private float progress;

    public BeamConfig(float f2, float f3, float f4, float[] particleColor, Beam beam1, Beam beam2, Beam beam3, Beam beam4, Beam beam5, Beam beam6) {
        n.g(particleColor, "particleColor");
        n.g(beam1, "beam1");
        n.g(beam2, "beam2");
        n.g(beam3, "beam3");
        n.g(beam4, "beam4");
        n.g(beam5, "beam5");
        n.g(beam6, "beam6");
        this.progress = f2;
        this.particleCount = f3;
        this.particleSize = f4;
        this.particleColor = particleColor;
        this.beam1 = beam1;
        this.beam2 = beam2;
        this.beam3 = beam3;
        this.beam4 = beam4;
        this.beam5 = beam5;
        this.beam6 = beam6;
    }

    public final float component1() {
        return this.progress;
    }

    public final Beam component10() {
        return this.beam6;
    }

    public final float component2() {
        return this.particleCount;
    }

    public final float component3() {
        return this.particleSize;
    }

    public final float[] component4() {
        return this.particleColor;
    }

    public final Beam component5() {
        return this.beam1;
    }

    public final Beam component6() {
        return this.beam2;
    }

    public final Beam component7() {
        return this.beam3;
    }

    public final Beam component8() {
        return this.beam4;
    }

    public final Beam component9() {
        return this.beam5;
    }

    public final BeamConfig copy(float f2, float f3, float f4, float[] particleColor, Beam beam1, Beam beam2, Beam beam3, Beam beam4, Beam beam5, Beam beam6) {
        n.g(particleColor, "particleColor");
        n.g(beam1, "beam1");
        n.g(beam2, "beam2");
        n.g(beam3, "beam3");
        n.g(beam4, "beam4");
        n.g(beam5, "beam5");
        n.g(beam6, "beam6");
        return new BeamConfig(f2, f3, f4, particleColor, beam1, beam2, beam3, beam4, beam5, beam6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BeamConfig)) {
            return false;
        }
        BeamConfig beamConfig = (BeamConfig) obj;
        return Float.compare(this.progress, beamConfig.progress) == 0 && Float.compare(this.particleCount, beamConfig.particleCount) == 0 && Float.compare(this.particleSize, beamConfig.particleSize) == 0 && n.c(this.particleColor, beamConfig.particleColor) && n.c(this.beam1, beamConfig.beam1) && n.c(this.beam2, beamConfig.beam2) && n.c(this.beam3, beamConfig.beam3) && n.c(this.beam4, beamConfig.beam4) && n.c(this.beam5, beamConfig.beam5) && n.c(this.beam6, beamConfig.beam6);
    }

    public final Beam getBeam1() {
        return this.beam1;
    }

    public final Beam getBeam2() {
        return this.beam2;
    }

    public final Beam getBeam3() {
        return this.beam3;
    }

    public final Beam getBeam4() {
        return this.beam4;
    }

    public final Beam getBeam5() {
        return this.beam5;
    }

    public final Beam getBeam6() {
        return this.beam6;
    }

    public final float[] getParticleColor() {
        return this.particleColor;
    }

    public final float getParticleCount() {
        return this.particleCount;
    }

    public final float getParticleSize() {
        return this.particleSize;
    }

    public final float getProgress() {
        return this.progress;
    }

    public int hashCode() {
        return (((((((((((((((((Float.hashCode(this.progress) * 31) + Float.hashCode(this.particleCount)) * 31) + Float.hashCode(this.particleSize)) * 31) + Arrays.hashCode(this.particleColor)) * 31) + this.beam1.hashCode()) * 31) + this.beam2.hashCode()) * 31) + this.beam3.hashCode()) * 31) + this.beam4.hashCode()) * 31) + this.beam5.hashCode()) * 31) + this.beam6.hashCode();
    }

    public final void setParticleCount(float f2) {
        this.particleCount = f2;
    }

    public final void setParticleSize(float f2) {
        this.particleSize = f2;
    }

    public final void setProgress(float f2) {
        this.progress = f2;
    }

    public String toString() {
        return "BeamConfig(progress=" + this.progress + ", particleCount=" + this.particleCount + ", particleSize=" + this.particleSize + ", particleColor=" + Arrays.toString(this.particleColor) + ", beam1=" + this.beam1 + ", beam2=" + this.beam2 + ", beam3=" + this.beam3 + ", beam4=" + this.beam4 + ", beam5=" + this.beam5 + ", beam6=" + this.beam6 + ")";
    }
}
