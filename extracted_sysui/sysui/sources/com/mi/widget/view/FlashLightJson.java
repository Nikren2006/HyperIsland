package com.mi.widget.view;

import androidx.annotation.Keep;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import u1.u;

/* JADX INFO: loaded from: classes2.dex */
@Keep
final class FlashLightJson {
    public static final Companion Companion = new Companion(null);
    private float beam1Alpha;
    private float beam1Dispersion;
    private float beam1E;
    private float beam1Highband;
    private float beam1LineY;
    private float[] beam1PointF;
    private float[] beam1Rgb;
    private float beam1ScaleX;
    private float beam1Shift;
    private float beam2Alpha;
    private float beam2Dispersion;
    private float beam2E;
    private float beam2Highband;
    private float beam2LineY;
    private float[] beam2PointF;
    private float[] beam2Rgb;
    private float beam2ScaleX;
    private float beam2Shift;
    private float beam3Alpha;
    private float beam3Dispersion;
    private float beam3E;
    private float beam3Highband;
    private float beam3LineY;
    private float[] beam3PointF;
    private float[] beam3Rgb;
    private float beam3ScaleX;
    private float beam3Shift;
    private float beam4Alpha;
    private float beam4Dispersion;
    private float beam4E;
    private float beam4Highband;
    private float beam4LineY;
    private float[] beam4PointF;
    private float[] beam4Rgb;
    private float beam4ScaleX;
    private float beam4Shift;
    private float beam5Alpha;
    private float beam5Dispersion;
    private float beam5E;
    private float beam5Highband;
    private float beam5LineY;
    private float[] beam5PointF;
    private float[] beam5Rgb;
    private float beam5ScaleX;
    private float beam5Shift;
    private float beam6Alpha;
    private float beam6Dispersion;
    private float beam6E;
    private float beam6Highband;
    private float beam6LineY;
    private float[] beam6PointF;
    private float[] beam6Rgb;
    private float beam6ScaleX;
    private float beam6Shift;
    private float[] particleColor;
    private float particleCount;
    private float particleSize;
    private float progress;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final q1.b serializer() {
            return FlashLightJson$$serializer.INSTANCE;
        }

        public Companion() {
        }
    }

    public FlashLightJson() {
        this(0.0f, 0.0f, 0.0f, (float[]) null, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, (float[]) null, 0.0f, 0.0f, 0.0f, 0.0f, -1, 67108863, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ void getBeam1Alpha$annotations() {
    }

    public static /* synthetic */ void getBeam1Dispersion$annotations() {
    }

    public static /* synthetic */ void getBeam1E$annotations() {
    }

    public static /* synthetic */ void getBeam1Highband$annotations() {
    }

    public static /* synthetic */ void getBeam1LineY$annotations() {
    }

    public static /* synthetic */ void getBeam1PointF$annotations() {
    }

    public static /* synthetic */ void getBeam1Rgb$annotations() {
    }

    public static /* synthetic */ void getBeam1ScaleX$annotations() {
    }

    public static /* synthetic */ void getBeam1Shift$annotations() {
    }

    public static /* synthetic */ void getBeam2Alpha$annotations() {
    }

    public static /* synthetic */ void getBeam2Dispersion$annotations() {
    }

    public static /* synthetic */ void getBeam2E$annotations() {
    }

    public static /* synthetic */ void getBeam2Highband$annotations() {
    }

    public static /* synthetic */ void getBeam2LineY$annotations() {
    }

    public static /* synthetic */ void getBeam2PointF$annotations() {
    }

    public static /* synthetic */ void getBeam2Rgb$annotations() {
    }

    public static /* synthetic */ void getBeam2ScaleX$annotations() {
    }

    public static /* synthetic */ void getBeam2Shift$annotations() {
    }

    public static /* synthetic */ void getBeam3Alpha$annotations() {
    }

    public static /* synthetic */ void getBeam3Dispersion$annotations() {
    }

    public static /* synthetic */ void getBeam3E$annotations() {
    }

    public static /* synthetic */ void getBeam3Highband$annotations() {
    }

    public static /* synthetic */ void getBeam3LineY$annotations() {
    }

    public static /* synthetic */ void getBeam3PointF$annotations() {
    }

    public static /* synthetic */ void getBeam3Rgb$annotations() {
    }

    public static /* synthetic */ void getBeam3ScaleX$annotations() {
    }

    public static /* synthetic */ void getBeam3Shift$annotations() {
    }

    public static /* synthetic */ void getBeam4Alpha$annotations() {
    }

    public static /* synthetic */ void getBeam4Dispersion$annotations() {
    }

    public static /* synthetic */ void getBeam4E$annotations() {
    }

    public static /* synthetic */ void getBeam4Highband$annotations() {
    }

    public static /* synthetic */ void getBeam4LineY$annotations() {
    }

    public static /* synthetic */ void getBeam4PointF$annotations() {
    }

    public static /* synthetic */ void getBeam4Rgb$annotations() {
    }

    public static /* synthetic */ void getBeam4ScaleX$annotations() {
    }

    public static /* synthetic */ void getBeam4Shift$annotations() {
    }

    public static /* synthetic */ void getBeam5Alpha$annotations() {
    }

    public static /* synthetic */ void getBeam5Dispersion$annotations() {
    }

    public static /* synthetic */ void getBeam5E$annotations() {
    }

    public static /* synthetic */ void getBeam5Highband$annotations() {
    }

    public static /* synthetic */ void getBeam5LineY$annotations() {
    }

    public static /* synthetic */ void getBeam5PointF$annotations() {
    }

    public static /* synthetic */ void getBeam5Rgb$annotations() {
    }

    public static /* synthetic */ void getBeam5ScaleX$annotations() {
    }

    public static /* synthetic */ void getBeam5Shift$annotations() {
    }

    public static /* synthetic */ void getBeam6Alpha$annotations() {
    }

    public static /* synthetic */ void getBeam6Dispersion$annotations() {
    }

    public static /* synthetic */ void getBeam6E$annotations() {
    }

    public static /* synthetic */ void getBeam6Highband$annotations() {
    }

    public static /* synthetic */ void getBeam6LineY$annotations() {
    }

    public static /* synthetic */ void getBeam6PointF$annotations() {
    }

    public static /* synthetic */ void getBeam6Rgb$annotations() {
    }

    public static /* synthetic */ void getBeam6ScaleX$annotations() {
    }

    public static /* synthetic */ void getBeam6Shift$annotations() {
    }

    public static /* synthetic */ void getParticleColor$annotations() {
    }

    public static /* synthetic */ void getParticleCount$annotations() {
    }

    public static /* synthetic */ void getParticleSize$annotations() {
    }

    public static /* synthetic */ void getProgress$annotations() {
    }

    public static final /* synthetic */ void write$Self$hyper_widget_1_0_7_pluginRelease(FlashLightJson flashLightJson, t1.c cVar, s1.c cVar2) {
        if (cVar.c(cVar2, 0) || Float.compare(flashLightJson.progress, 0.0f) != 0) {
            cVar.b(cVar2, 0, flashLightJson.progress);
        }
        if (cVar.c(cVar2, 1) || Float.compare(flashLightJson.particleCount, 0.0f) != 0) {
            cVar.b(cVar2, 1, flashLightJson.particleCount);
        }
        if (cVar.c(cVar2, 2) || Float.compare(flashLightJson.particleSize, 0.0f) != 0) {
            cVar.b(cVar2, 2, flashLightJson.particleSize);
        }
        if (cVar.c(cVar2, 3) || !n.c(flashLightJson.particleColor, new float[3])) {
            cVar.a(cVar2, 3, u1.e.f6865c, flashLightJson.particleColor);
        }
        if (cVar.c(cVar2, 4) || !n.c(flashLightJson.beam1PointF, new float[2])) {
            cVar.a(cVar2, 4, u1.e.f6865c, flashLightJson.beam1PointF);
        }
        if (cVar.c(cVar2, 5) || Float.compare(flashLightJson.beam1LineY, 0.0f) != 0) {
            cVar.b(cVar2, 5, flashLightJson.beam1LineY);
        }
        if (cVar.c(cVar2, 6) || Float.compare(flashLightJson.beam1Highband, 0.0f) != 0) {
            cVar.b(cVar2, 6, flashLightJson.beam1Highband);
        }
        if (cVar.c(cVar2, 7) || Float.compare(flashLightJson.beam1E, 0.0f) != 0) {
            cVar.b(cVar2, 7, flashLightJson.beam1E);
        }
        if (cVar.c(cVar2, 8) || !n.c(flashLightJson.beam1Rgb, new float[3])) {
            cVar.a(cVar2, 8, u1.e.f6865c, flashLightJson.beam1Rgb);
        }
        if (cVar.c(cVar2, 9) || Float.compare(flashLightJson.beam1ScaleX, 0.0f) != 0) {
            cVar.b(cVar2, 9, flashLightJson.beam1ScaleX);
        }
        if (cVar.c(cVar2, 10) || Float.compare(flashLightJson.beam1Alpha, 0.0f) != 0) {
            cVar.b(cVar2, 10, flashLightJson.beam1Alpha);
        }
        if (cVar.c(cVar2, 11) || Float.compare(flashLightJson.beam1Shift, 0.0f) != 0) {
            cVar.b(cVar2, 11, flashLightJson.beam1Shift);
        }
        if (cVar.c(cVar2, 12) || Float.compare(flashLightJson.beam1Dispersion, 0.0f) != 0) {
            cVar.b(cVar2, 12, flashLightJson.beam1Dispersion);
        }
        if (cVar.c(cVar2, 13) || !n.c(flashLightJson.beam2PointF, new float[2])) {
            cVar.a(cVar2, 13, u1.e.f6865c, flashLightJson.beam2PointF);
        }
        if (cVar.c(cVar2, 14) || Float.compare(flashLightJson.beam2LineY, 0.0f) != 0) {
            cVar.b(cVar2, 14, flashLightJson.beam2LineY);
        }
        if (cVar.c(cVar2, 15) || Float.compare(flashLightJson.beam2Highband, 0.0f) != 0) {
            cVar.b(cVar2, 15, flashLightJson.beam2Highband);
        }
        if (cVar.c(cVar2, 16) || Float.compare(flashLightJson.beam2E, 0.0f) != 0) {
            cVar.b(cVar2, 16, flashLightJson.beam2E);
        }
        if (cVar.c(cVar2, 17) || !n.c(flashLightJson.beam2Rgb, new float[3])) {
            cVar.a(cVar2, 17, u1.e.f6865c, flashLightJson.beam2Rgb);
        }
        if (cVar.c(cVar2, 18) || Float.compare(flashLightJson.beam2ScaleX, 0.0f) != 0) {
            cVar.b(cVar2, 18, flashLightJson.beam2ScaleX);
        }
        if (cVar.c(cVar2, 19) || Float.compare(flashLightJson.beam2Alpha, 0.0f) != 0) {
            cVar.b(cVar2, 19, flashLightJson.beam2Alpha);
        }
        if (cVar.c(cVar2, 20) || Float.compare(flashLightJson.beam2Shift, 0.0f) != 0) {
            cVar.b(cVar2, 20, flashLightJson.beam2Shift);
        }
        if (cVar.c(cVar2, 21) || Float.compare(flashLightJson.beam2Dispersion, 0.0f) != 0) {
            cVar.b(cVar2, 21, flashLightJson.beam2Dispersion);
        }
        if (cVar.c(cVar2, 22) || !n.c(flashLightJson.beam3PointF, new float[2])) {
            cVar.a(cVar2, 22, u1.e.f6865c, flashLightJson.beam3PointF);
        }
        if (cVar.c(cVar2, 23) || Float.compare(flashLightJson.beam3LineY, 0.0f) != 0) {
            cVar.b(cVar2, 23, flashLightJson.beam3LineY);
        }
        if (cVar.c(cVar2, 24) || Float.compare(flashLightJson.beam3Highband, 0.0f) != 0) {
            cVar.b(cVar2, 24, flashLightJson.beam3Highband);
        }
        if (cVar.c(cVar2, 25) || Float.compare(flashLightJson.beam3E, 0.0f) != 0) {
            cVar.b(cVar2, 25, flashLightJson.beam3E);
        }
        if (cVar.c(cVar2, 26) || !n.c(flashLightJson.beam3Rgb, new float[3])) {
            cVar.a(cVar2, 26, u1.e.f6865c, flashLightJson.beam3Rgb);
        }
        if (cVar.c(cVar2, 27) || Float.compare(flashLightJson.beam3ScaleX, 0.0f) != 0) {
            cVar.b(cVar2, 27, flashLightJson.beam3ScaleX);
        }
        if (cVar.c(cVar2, 28) || Float.compare(flashLightJson.beam3Alpha, 0.0f) != 0) {
            cVar.b(cVar2, 28, flashLightJson.beam3Alpha);
        }
        if (cVar.c(cVar2, 29) || Float.compare(flashLightJson.beam3Shift, 0.0f) != 0) {
            cVar.b(cVar2, 29, flashLightJson.beam3Shift);
        }
        if (cVar.c(cVar2, 30) || Float.compare(flashLightJson.beam3Dispersion, 0.0f) != 0) {
            cVar.b(cVar2, 30, flashLightJson.beam3Dispersion);
        }
        if (cVar.c(cVar2, 31) || !n.c(flashLightJson.beam4PointF, new float[2])) {
            cVar.a(cVar2, 31, u1.e.f6865c, flashLightJson.beam4PointF);
        }
        if (cVar.c(cVar2, 32) || Float.compare(flashLightJson.beam4LineY, 0.0f) != 0) {
            cVar.b(cVar2, 32, flashLightJson.beam4LineY);
        }
        if (cVar.c(cVar2, 33) || Float.compare(flashLightJson.beam4Highband, 0.0f) != 0) {
            cVar.b(cVar2, 33, flashLightJson.beam4Highband);
        }
        if (cVar.c(cVar2, 34) || Float.compare(flashLightJson.beam4E, 0.0f) != 0) {
            cVar.b(cVar2, 34, flashLightJson.beam4E);
        }
        if (cVar.c(cVar2, 35) || !n.c(flashLightJson.beam4Rgb, new float[3])) {
            cVar.a(cVar2, 35, u1.e.f6865c, flashLightJson.beam4Rgb);
        }
        if (cVar.c(cVar2, 36) || Float.compare(flashLightJson.beam4ScaleX, 0.0f) != 0) {
            cVar.b(cVar2, 36, flashLightJson.beam4ScaleX);
        }
        if (cVar.c(cVar2, 37) || Float.compare(flashLightJson.beam4Alpha, 0.0f) != 0) {
            cVar.b(cVar2, 37, flashLightJson.beam4Alpha);
        }
        if (cVar.c(cVar2, 38) || Float.compare(flashLightJson.beam4Shift, 0.0f) != 0) {
            cVar.b(cVar2, 38, flashLightJson.beam4Shift);
        }
        if (cVar.c(cVar2, 39) || Float.compare(flashLightJson.beam4Dispersion, 0.0f) != 0) {
            cVar.b(cVar2, 39, flashLightJson.beam4Dispersion);
        }
        if (cVar.c(cVar2, 40) || !n.c(flashLightJson.beam5PointF, new float[2])) {
            cVar.a(cVar2, 40, u1.e.f6865c, flashLightJson.beam5PointF);
        }
        if (cVar.c(cVar2, 41) || Float.compare(flashLightJson.beam5LineY, 0.0f) != 0) {
            cVar.b(cVar2, 41, flashLightJson.beam5LineY);
        }
        if (cVar.c(cVar2, 42) || Float.compare(flashLightJson.beam5Highband, 0.0f) != 0) {
            cVar.b(cVar2, 42, flashLightJson.beam5Highband);
        }
        if (cVar.c(cVar2, 43) || Float.compare(flashLightJson.beam5E, 0.0f) != 0) {
            cVar.b(cVar2, 43, flashLightJson.beam5E);
        }
        if (cVar.c(cVar2, 44) || !n.c(flashLightJson.beam5Rgb, new float[3])) {
            cVar.a(cVar2, 44, u1.e.f6865c, flashLightJson.beam5Rgb);
        }
        if (cVar.c(cVar2, 45) || Float.compare(flashLightJson.beam5ScaleX, 0.0f) != 0) {
            cVar.b(cVar2, 45, flashLightJson.beam5ScaleX);
        }
        if (cVar.c(cVar2, 46) || Float.compare(flashLightJson.beam5Alpha, 0.0f) != 0) {
            cVar.b(cVar2, 46, flashLightJson.beam5Alpha);
        }
        if (cVar.c(cVar2, 47) || Float.compare(flashLightJson.beam5Shift, 0.0f) != 0) {
            cVar.b(cVar2, 47, flashLightJson.beam5Shift);
        }
        if (cVar.c(cVar2, 48) || Float.compare(flashLightJson.beam5Dispersion, 0.0f) != 0) {
            cVar.b(cVar2, 48, flashLightJson.beam5Dispersion);
        }
        if (cVar.c(cVar2, 49) || !n.c(flashLightJson.beam6PointF, new float[2])) {
            cVar.a(cVar2, 49, u1.e.f6865c, flashLightJson.beam6PointF);
        }
        if (cVar.c(cVar2, 50) || Float.compare(flashLightJson.beam6LineY, 0.0f) != 0) {
            cVar.b(cVar2, 50, flashLightJson.beam6LineY);
        }
        if (cVar.c(cVar2, 51) || Float.compare(flashLightJson.beam6Highband, 0.0f) != 0) {
            cVar.b(cVar2, 51, flashLightJson.beam6Highband);
        }
        if (cVar.c(cVar2, 52) || Float.compare(flashLightJson.beam6E, 0.0f) != 0) {
            cVar.b(cVar2, 52, flashLightJson.beam6E);
        }
        if (cVar.c(cVar2, 53) || !n.c(flashLightJson.beam6Rgb, new float[3])) {
            cVar.a(cVar2, 53, u1.e.f6865c, flashLightJson.beam6Rgb);
        }
        if (cVar.c(cVar2, 54) || Float.compare(flashLightJson.beam6ScaleX, 0.0f) != 0) {
            cVar.b(cVar2, 54, flashLightJson.beam6ScaleX);
        }
        if (cVar.c(cVar2, 55) || Float.compare(flashLightJson.beam6Alpha, 0.0f) != 0) {
            cVar.b(cVar2, 55, flashLightJson.beam6Alpha);
        }
        if (cVar.c(cVar2, 56) || Float.compare(flashLightJson.beam6Shift, 0.0f) != 0) {
            cVar.b(cVar2, 56, flashLightJson.beam6Shift);
        }
        if (!cVar.c(cVar2, 57) && Float.compare(flashLightJson.beam6Dispersion, 0.0f) == 0) {
            return;
        }
        cVar.b(cVar2, 57, flashLightJson.beam6Dispersion);
    }

    public final float component1() {
        return this.progress;
    }

    public final float component10() {
        return this.beam1ScaleX;
    }

    public final float component11() {
        return this.beam1Alpha;
    }

    public final float component12() {
        return this.beam1Shift;
    }

    public final float component13() {
        return this.beam1Dispersion;
    }

    public final float[] component14() {
        return this.beam2PointF;
    }

    public final float component15() {
        return this.beam2LineY;
    }

    public final float component16() {
        return this.beam2Highband;
    }

    public final float component17() {
        return this.beam2E;
    }

    public final float[] component18() {
        return this.beam2Rgb;
    }

    public final float component19() {
        return this.beam2ScaleX;
    }

    public final float component2() {
        return this.particleCount;
    }

    public final float component20() {
        return this.beam2Alpha;
    }

    public final float component21() {
        return this.beam2Shift;
    }

    public final float component22() {
        return this.beam2Dispersion;
    }

    public final float[] component23() {
        return this.beam3PointF;
    }

    public final float component24() {
        return this.beam3LineY;
    }

    public final float component25() {
        return this.beam3Highband;
    }

    public final float component26() {
        return this.beam3E;
    }

    public final float[] component27() {
        return this.beam3Rgb;
    }

    public final float component28() {
        return this.beam3ScaleX;
    }

    public final float component29() {
        return this.beam3Alpha;
    }

    public final float component3() {
        return this.particleSize;
    }

    public final float component30() {
        return this.beam3Shift;
    }

    public final float component31() {
        return this.beam3Dispersion;
    }

    public final float[] component32() {
        return this.beam4PointF;
    }

    public final float component33() {
        return this.beam4LineY;
    }

    public final float component34() {
        return this.beam4Highband;
    }

    public final float component35() {
        return this.beam4E;
    }

    public final float[] component36() {
        return this.beam4Rgb;
    }

    public final float component37() {
        return this.beam4ScaleX;
    }

    public final float component38() {
        return this.beam4Alpha;
    }

    public final float component39() {
        return this.beam4Shift;
    }

    public final float[] component4() {
        return this.particleColor;
    }

    public final float component40() {
        return this.beam4Dispersion;
    }

    public final float[] component41() {
        return this.beam5PointF;
    }

    public final float component42() {
        return this.beam5LineY;
    }

    public final float component43() {
        return this.beam5Highband;
    }

    public final float component44() {
        return this.beam5E;
    }

    public final float[] component45() {
        return this.beam5Rgb;
    }

    public final float component46() {
        return this.beam5ScaleX;
    }

    public final float component47() {
        return this.beam5Alpha;
    }

    public final float component48() {
        return this.beam5Shift;
    }

    public final float component49() {
        return this.beam5Dispersion;
    }

    public final float[] component5() {
        return this.beam1PointF;
    }

    public final float[] component50() {
        return this.beam6PointF;
    }

    public final float component51() {
        return this.beam6LineY;
    }

    public final float component52() {
        return this.beam6Highband;
    }

    public final float component53() {
        return this.beam6E;
    }

    public final float[] component54() {
        return this.beam6Rgb;
    }

    public final float component55() {
        return this.beam6ScaleX;
    }

    public final float component56() {
        return this.beam6Alpha;
    }

    public final float component57() {
        return this.beam6Shift;
    }

    public final float component58() {
        return this.beam6Dispersion;
    }

    public final float component6() {
        return this.beam1LineY;
    }

    public final float component7() {
        return this.beam1Highband;
    }

    public final float component8() {
        return this.beam1E;
    }

    public final float[] component9() {
        return this.beam1Rgb;
    }

    public final FlashLightJson copy(float f2, float f3, float f4, float[] particleColor, float[] beam1PointF, float f5, float f6, float f7, float[] beam1Rgb, float f8, float f9, float f10, float f11, float[] beam2PointF, float f12, float f13, float f14, float[] beam2Rgb, float f15, float f16, float f17, float f18, float[] beam3PointF, float f19, float f20, float f21, float[] beam3Rgb, float f22, float f23, float f24, float f25, float[] beam4PointF, float f26, float f27, float f28, float[] beam4Rgb, float f29, float f30, float f31, float f32, float[] beam5PointF, float f33, float f34, float f35, float[] beam5Rgb, float f36, float f37, float f38, float f39, float[] beam6PointF, float f40, float f41, float f42, float[] beam6Rgb, float f43, float f44, float f45, float f46) {
        n.g(particleColor, "particleColor");
        n.g(beam1PointF, "beam1PointF");
        n.g(beam1Rgb, "beam1Rgb");
        n.g(beam2PointF, "beam2PointF");
        n.g(beam2Rgb, "beam2Rgb");
        n.g(beam3PointF, "beam3PointF");
        n.g(beam3Rgb, "beam3Rgb");
        n.g(beam4PointF, "beam4PointF");
        n.g(beam4Rgb, "beam4Rgb");
        n.g(beam5PointF, "beam5PointF");
        n.g(beam5Rgb, "beam5Rgb");
        n.g(beam6PointF, "beam6PointF");
        n.g(beam6Rgb, "beam6Rgb");
        return new FlashLightJson(f2, f3, f4, particleColor, beam1PointF, f5, f6, f7, beam1Rgb, f8, f9, f10, f11, beam2PointF, f12, f13, f14, beam2Rgb, f15, f16, f17, f18, beam3PointF, f19, f20, f21, beam3Rgb, f22, f23, f24, f25, beam4PointF, f26, f27, f28, beam4Rgb, f29, f30, f31, f32, beam5PointF, f33, f34, f35, beam5Rgb, f36, f37, f38, f39, beam6PointF, f40, f41, f42, beam6Rgb, f43, f44, f45, f46);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashLightJson)) {
            return false;
        }
        FlashLightJson flashLightJson = (FlashLightJson) obj;
        return this.progress == flashLightJson.progress && this.particleCount == flashLightJson.particleCount && this.particleSize == flashLightJson.particleSize && this.beam1LineY == flashLightJson.beam1LineY && this.beam1Highband == flashLightJson.beam1Highband && this.beam1E == flashLightJson.beam1E && this.beam1ScaleX == flashLightJson.beam1ScaleX && this.beam1Alpha == flashLightJson.beam1Alpha && this.beam1Shift == flashLightJson.beam1Shift && this.beam1Dispersion == flashLightJson.beam1Dispersion && this.beam2LineY == flashLightJson.beam2LineY && this.beam2Highband == flashLightJson.beam2Highband && this.beam2E == flashLightJson.beam2E && this.beam2ScaleX == flashLightJson.beam2ScaleX && this.beam2Alpha == flashLightJson.beam2Alpha && this.beam2Shift == flashLightJson.beam2Shift && this.beam2Dispersion == flashLightJson.beam2Dispersion && this.beam3LineY == flashLightJson.beam3LineY && this.beam3Highband == flashLightJson.beam3Highband && this.beam3E == flashLightJson.beam3E && this.beam3ScaleX == flashLightJson.beam3ScaleX && this.beam3Alpha == flashLightJson.beam3Alpha && this.beam3Shift == flashLightJson.beam3Shift && this.beam3Dispersion == flashLightJson.beam3Dispersion && this.beam4LineY == flashLightJson.beam4LineY && this.beam4Highband == flashLightJson.beam4Highband && this.beam4E == flashLightJson.beam4E && this.beam4ScaleX == flashLightJson.beam4ScaleX && this.beam4Alpha == flashLightJson.beam4Alpha && this.beam4Shift == flashLightJson.beam4Shift && this.beam4Dispersion == flashLightJson.beam4Dispersion && this.beam5LineY == flashLightJson.beam5LineY && this.beam5Highband == flashLightJson.beam5Highband && this.beam5E == flashLightJson.beam5E && this.beam5ScaleX == flashLightJson.beam5ScaleX && this.beam5Alpha == flashLightJson.beam5Alpha && this.beam5Shift == flashLightJson.beam5Shift && this.beam5Dispersion == flashLightJson.beam5Dispersion && this.beam6LineY == flashLightJson.beam6LineY && this.beam6Highband == flashLightJson.beam6Highband && this.beam6E == flashLightJson.beam6E && this.beam6ScaleX == flashLightJson.beam6ScaleX && this.beam6Alpha == flashLightJson.beam6Alpha && this.beam6Shift == flashLightJson.beam6Shift && this.beam6Dispersion == flashLightJson.beam6Dispersion && Arrays.equals(this.particleColor, flashLightJson.particleColor) && Arrays.equals(this.beam1PointF, flashLightJson.beam1PointF) && Arrays.equals(this.beam1Rgb, flashLightJson.beam1Rgb) && Arrays.equals(this.beam2PointF, flashLightJson.beam2PointF) && Arrays.equals(this.beam2Rgb, flashLightJson.beam2Rgb) && Arrays.equals(this.beam3PointF, flashLightJson.beam3PointF) && Arrays.equals(this.beam3Rgb, flashLightJson.beam3Rgb) && Arrays.equals(this.beam4PointF, flashLightJson.beam4PointF) && Arrays.equals(this.beam4Rgb, flashLightJson.beam4Rgb) && Arrays.equals(this.beam5PointF, flashLightJson.beam5PointF) && Arrays.equals(this.beam5Rgb, flashLightJson.beam5Rgb) && Arrays.equals(this.beam6PointF, flashLightJson.beam6PointF) && Arrays.equals(this.beam6Rgb, flashLightJson.beam6Rgb);
    }

    public final float getBeam1Alpha() {
        return this.beam1Alpha;
    }

    public final float getBeam1Dispersion() {
        return this.beam1Dispersion;
    }

    public final float getBeam1E() {
        return this.beam1E;
    }

    public final float getBeam1Highband() {
        return this.beam1Highband;
    }

    public final float getBeam1LineY() {
        return this.beam1LineY;
    }

    public final float[] getBeam1PointF() {
        return this.beam1PointF;
    }

    public final float[] getBeam1Rgb() {
        return this.beam1Rgb;
    }

    public final float getBeam1ScaleX() {
        return this.beam1ScaleX;
    }

    public final float getBeam1Shift() {
        return this.beam1Shift;
    }

    public final float getBeam2Alpha() {
        return this.beam2Alpha;
    }

    public final float getBeam2Dispersion() {
        return this.beam2Dispersion;
    }

    public final float getBeam2E() {
        return this.beam2E;
    }

    public final float getBeam2Highband() {
        return this.beam2Highband;
    }

    public final float getBeam2LineY() {
        return this.beam2LineY;
    }

    public final float[] getBeam2PointF() {
        return this.beam2PointF;
    }

    public final float[] getBeam2Rgb() {
        return this.beam2Rgb;
    }

    public final float getBeam2ScaleX() {
        return this.beam2ScaleX;
    }

    public final float getBeam2Shift() {
        return this.beam2Shift;
    }

    public final float getBeam3Alpha() {
        return this.beam3Alpha;
    }

    public final float getBeam3Dispersion() {
        return this.beam3Dispersion;
    }

    public final float getBeam3E() {
        return this.beam3E;
    }

    public final float getBeam3Highband() {
        return this.beam3Highband;
    }

    public final float getBeam3LineY() {
        return this.beam3LineY;
    }

    public final float[] getBeam3PointF() {
        return this.beam3PointF;
    }

    public final float[] getBeam3Rgb() {
        return this.beam3Rgb;
    }

    public final float getBeam3ScaleX() {
        return this.beam3ScaleX;
    }

    public final float getBeam3Shift() {
        return this.beam3Shift;
    }

    public final float getBeam4Alpha() {
        return this.beam4Alpha;
    }

    public final float getBeam4Dispersion() {
        return this.beam4Dispersion;
    }

    public final float getBeam4E() {
        return this.beam4E;
    }

    public final float getBeam4Highband() {
        return this.beam4Highband;
    }

    public final float getBeam4LineY() {
        return this.beam4LineY;
    }

    public final float[] getBeam4PointF() {
        return this.beam4PointF;
    }

    public final float[] getBeam4Rgb() {
        return this.beam4Rgb;
    }

    public final float getBeam4ScaleX() {
        return this.beam4ScaleX;
    }

    public final float getBeam4Shift() {
        return this.beam4Shift;
    }

    public final float getBeam5Alpha() {
        return this.beam5Alpha;
    }

    public final float getBeam5Dispersion() {
        return this.beam5Dispersion;
    }

    public final float getBeam5E() {
        return this.beam5E;
    }

    public final float getBeam5Highband() {
        return this.beam5Highband;
    }

    public final float getBeam5LineY() {
        return this.beam5LineY;
    }

    public final float[] getBeam5PointF() {
        return this.beam5PointF;
    }

    public final float[] getBeam5Rgb() {
        return this.beam5Rgb;
    }

    public final float getBeam5ScaleX() {
        return this.beam5ScaleX;
    }

    public final float getBeam5Shift() {
        return this.beam5Shift;
    }

    public final float getBeam6Alpha() {
        return this.beam6Alpha;
    }

    public final float getBeam6Dispersion() {
        return this.beam6Dispersion;
    }

    public final float getBeam6E() {
        return this.beam6E;
    }

    public final float getBeam6Highband() {
        return this.beam6Highband;
    }

    public final float getBeam6LineY() {
        return this.beam6LineY;
    }

    public final float[] getBeam6PointF() {
        return this.beam6PointF;
    }

    public final float[] getBeam6Rgb() {
        return this.beam6Rgb;
    }

    public final float getBeam6ScaleX() {
        return this.beam6ScaleX;
    }

    public final float getBeam6Shift() {
        return this.beam6Shift;
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
        return Objects.hash(Float.valueOf(this.particleCount), Float.valueOf(this.particleSize), Float.valueOf(this.beam1LineY), Float.valueOf(this.beam1Highband), Float.valueOf(this.beam1E), Float.valueOf(this.beam1ScaleX), Float.valueOf(this.beam1Alpha), Float.valueOf(this.beam1Shift), Float.valueOf(this.beam1Dispersion), Float.valueOf(this.beam2LineY), Float.valueOf(this.beam2Highband), Float.valueOf(this.beam2E), Float.valueOf(this.beam2ScaleX), Float.valueOf(this.beam2Alpha), Float.valueOf(this.beam2Shift), Float.valueOf(this.beam2Dispersion), Float.valueOf(this.beam3LineY), Float.valueOf(this.beam3Highband), Float.valueOf(this.beam3E), Float.valueOf(this.beam3ScaleX), Float.valueOf(this.beam3Alpha), Float.valueOf(this.beam3Shift), Float.valueOf(this.beam3Dispersion), Float.valueOf(this.beam4LineY), Float.valueOf(this.beam4Highband), Float.valueOf(this.beam4E), Float.valueOf(this.beam4ScaleX), Float.valueOf(this.beam4Alpha), Float.valueOf(this.beam4Shift), Float.valueOf(this.beam4Dispersion), Float.valueOf(this.beam5LineY), Float.valueOf(this.beam5Highband), Float.valueOf(this.beam5E), Float.valueOf(this.beam5ScaleX), Float.valueOf(this.beam5Alpha), Float.valueOf(this.beam5Shift), Float.valueOf(this.beam5Dispersion), Float.valueOf(this.beam6LineY), Float.valueOf(this.beam6Highband), Float.valueOf(this.beam6E), Float.valueOf(this.beam6ScaleX), Float.valueOf(this.beam6Alpha), Float.valueOf(this.beam6Shift), Float.valueOf(this.beam6Dispersion), this.particleColor, this.beam1PointF, this.beam1Rgb, this.beam2PointF, this.beam2Rgb, this.beam3PointF, this.beam3Rgb, this.beam4PointF, this.beam4Rgb, this.beam5PointF, this.beam5Rgb, this.beam6PointF, this.beam6Rgb);
    }

    public final void setBeam1Alpha(float f2) {
        this.beam1Alpha = f2;
    }

    public final void setBeam1Dispersion(float f2) {
        this.beam1Dispersion = f2;
    }

    public final void setBeam1E(float f2) {
        this.beam1E = f2;
    }

    public final void setBeam1Highband(float f2) {
        this.beam1Highband = f2;
    }

    public final void setBeam1LineY(float f2) {
        this.beam1LineY = f2;
    }

    public final void setBeam1PointF(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam1PointF = fArr;
    }

    public final void setBeam1Rgb(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam1Rgb = fArr;
    }

    public final void setBeam1ScaleX(float f2) {
        this.beam1ScaleX = f2;
    }

    public final void setBeam1Shift(float f2) {
        this.beam1Shift = f2;
    }

    public final void setBeam2Alpha(float f2) {
        this.beam2Alpha = f2;
    }

    public final void setBeam2Dispersion(float f2) {
        this.beam2Dispersion = f2;
    }

    public final void setBeam2E(float f2) {
        this.beam2E = f2;
    }

    public final void setBeam2Highband(float f2) {
        this.beam2Highband = f2;
    }

    public final void setBeam2LineY(float f2) {
        this.beam2LineY = f2;
    }

    public final void setBeam2PointF(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam2PointF = fArr;
    }

    public final void setBeam2Rgb(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam2Rgb = fArr;
    }

    public final void setBeam2ScaleX(float f2) {
        this.beam2ScaleX = f2;
    }

    public final void setBeam2Shift(float f2) {
        this.beam2Shift = f2;
    }

    public final void setBeam3Alpha(float f2) {
        this.beam3Alpha = f2;
    }

    public final void setBeam3Dispersion(float f2) {
        this.beam3Dispersion = f2;
    }

    public final void setBeam3E(float f2) {
        this.beam3E = f2;
    }

    public final void setBeam3Highband(float f2) {
        this.beam3Highband = f2;
    }

    public final void setBeam3LineY(float f2) {
        this.beam3LineY = f2;
    }

    public final void setBeam3PointF(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam3PointF = fArr;
    }

    public final void setBeam3Rgb(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam3Rgb = fArr;
    }

    public final void setBeam3ScaleX(float f2) {
        this.beam3ScaleX = f2;
    }

    public final void setBeam3Shift(float f2) {
        this.beam3Shift = f2;
    }

    public final void setBeam4Alpha(float f2) {
        this.beam4Alpha = f2;
    }

    public final void setBeam4Dispersion(float f2) {
        this.beam4Dispersion = f2;
    }

    public final void setBeam4E(float f2) {
        this.beam4E = f2;
    }

    public final void setBeam4Highband(float f2) {
        this.beam4Highband = f2;
    }

    public final void setBeam4LineY(float f2) {
        this.beam4LineY = f2;
    }

    public final void setBeam4PointF(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam4PointF = fArr;
    }

    public final void setBeam4Rgb(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam4Rgb = fArr;
    }

    public final void setBeam4ScaleX(float f2) {
        this.beam4ScaleX = f2;
    }

    public final void setBeam4Shift(float f2) {
        this.beam4Shift = f2;
    }

    public final void setBeam5Alpha(float f2) {
        this.beam5Alpha = f2;
    }

    public final void setBeam5Dispersion(float f2) {
        this.beam5Dispersion = f2;
    }

    public final void setBeam5E(float f2) {
        this.beam5E = f2;
    }

    public final void setBeam5Highband(float f2) {
        this.beam5Highband = f2;
    }

    public final void setBeam5LineY(float f2) {
        this.beam5LineY = f2;
    }

    public final void setBeam5PointF(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam5PointF = fArr;
    }

    public final void setBeam5Rgb(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam5Rgb = fArr;
    }

    public final void setBeam5ScaleX(float f2) {
        this.beam5ScaleX = f2;
    }

    public final void setBeam5Shift(float f2) {
        this.beam5Shift = f2;
    }

    public final void setBeam6Alpha(float f2) {
        this.beam6Alpha = f2;
    }

    public final void setBeam6Dispersion(float f2) {
        this.beam6Dispersion = f2;
    }

    public final void setBeam6E(float f2) {
        this.beam6E = f2;
    }

    public final void setBeam6Highband(float f2) {
        this.beam6Highband = f2;
    }

    public final void setBeam6LineY(float f2) {
        this.beam6LineY = f2;
    }

    public final void setBeam6PointF(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam6PointF = fArr;
    }

    public final void setBeam6Rgb(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.beam6Rgb = fArr;
    }

    public final void setBeam6ScaleX(float f2) {
        this.beam6ScaleX = f2;
    }

    public final void setBeam6Shift(float f2) {
        this.beam6Shift = f2;
    }

    public final void setParticleColor(float[] fArr) {
        n.g(fArr, "<set-?>");
        this.particleColor = fArr;
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
        return "FlashLightJson(progress=" + this.progress + ", particleCount=" + this.particleCount + ", particleSize=" + this.particleSize + ", particleColor=" + Arrays.toString(this.particleColor) + ", beam1PointF=" + Arrays.toString(this.beam1PointF) + ", beam1LineY=" + this.beam1LineY + ", beam1Highband=" + this.beam1Highband + ", beam1E=" + this.beam1E + ", beam1Rgb=" + Arrays.toString(this.beam1Rgb) + ", beam1ScaleX=" + this.beam1ScaleX + ", beam1Alpha=" + this.beam1Alpha + ", beam1Shift=" + this.beam1Shift + ", beam1Dispersion=" + this.beam1Dispersion + ", beam2PointF=" + Arrays.toString(this.beam2PointF) + ", beam2LineY=" + this.beam2LineY + ", beam2Highband=" + this.beam2Highband + ", beam2E=" + this.beam2E + ", beam2Rgb=" + Arrays.toString(this.beam2Rgb) + ", beam2ScaleX=" + this.beam2ScaleX + ", beam2Alpha=" + this.beam2Alpha + ", beam2Shift=" + this.beam2Shift + ", beam2Dispersion=" + this.beam2Dispersion + ", beam3PointF=" + Arrays.toString(this.beam3PointF) + ", beam3LineY=" + this.beam3LineY + ", beam3Highband=" + this.beam3Highband + ", beam3E=" + this.beam3E + ", beam3Rgb=" + Arrays.toString(this.beam3Rgb) + ", beam3ScaleX=" + this.beam3ScaleX + ", beam3Alpha=" + this.beam3Alpha + ", beam3Shift=" + this.beam3Shift + ", beam3Dispersion=" + this.beam3Dispersion + ", beam4PointF=" + Arrays.toString(this.beam4PointF) + ", beam4LineY=" + this.beam4LineY + ", beam4Highband=" + this.beam4Highband + ", beam4E=" + this.beam4E + ", beam4Rgb=" + Arrays.toString(this.beam4Rgb) + ", beam4ScaleX=" + this.beam4ScaleX + ", beam4Alpha=" + this.beam4Alpha + ", beam4Shift=" + this.beam4Shift + ", beam4Dispersion=" + this.beam4Dispersion + ", beam5PointF=" + Arrays.toString(this.beam5PointF) + ", beam5LineY=" + this.beam5LineY + ", beam5Highband=" + this.beam5Highband + ", beam5E=" + this.beam5E + ", beam5Rgb=" + Arrays.toString(this.beam5Rgb) + ", beam5ScaleX=" + this.beam5ScaleX + ", beam5Alpha=" + this.beam5Alpha + ", beam5Shift=" + this.beam5Shift + ", beam5Dispersion=" + this.beam5Dispersion + ", beam6PointF=" + Arrays.toString(this.beam6PointF) + ", beam6LineY=" + this.beam6LineY + ", beam6Highband=" + this.beam6Highband + ", beam6E=" + this.beam6E + ", beam6Rgb=" + Arrays.toString(this.beam6Rgb) + ", beam6ScaleX=" + this.beam6ScaleX + ", beam6Alpha=" + this.beam6Alpha + ", beam6Shift=" + this.beam6Shift + ", beam6Dispersion=" + this.beam6Dispersion + ")";
    }

    public FlashLightJson(float f2, float f3, float f4, float[] particleColor, float[] beam1PointF, float f5, float f6, float f7, float[] beam1Rgb, float f8, float f9, float f10, float f11, float[] beam2PointF, float f12, float f13, float f14, float[] beam2Rgb, float f15, float f16, float f17, float f18, float[] beam3PointF, float f19, float f20, float f21, float[] beam3Rgb, float f22, float f23, float f24, float f25, float[] beam4PointF, float f26, float f27, float f28, float[] beam4Rgb, float f29, float f30, float f31, float f32, float[] beam5PointF, float f33, float f34, float f35, float[] beam5Rgb, float f36, float f37, float f38, float f39, float[] beam6PointF, float f40, float f41, float f42, float[] beam6Rgb, float f43, float f44, float f45, float f46) {
        n.g(particleColor, "particleColor");
        n.g(beam1PointF, "beam1PointF");
        n.g(beam1Rgb, "beam1Rgb");
        n.g(beam2PointF, "beam2PointF");
        n.g(beam2Rgb, "beam2Rgb");
        n.g(beam3PointF, "beam3PointF");
        n.g(beam3Rgb, "beam3Rgb");
        n.g(beam4PointF, "beam4PointF");
        n.g(beam4Rgb, "beam4Rgb");
        n.g(beam5PointF, "beam5PointF");
        n.g(beam5Rgb, "beam5Rgb");
        n.g(beam6PointF, "beam6PointF");
        n.g(beam6Rgb, "beam6Rgb");
        this.progress = f2;
        this.particleCount = f3;
        this.particleSize = f4;
        this.particleColor = particleColor;
        this.beam1PointF = beam1PointF;
        this.beam1LineY = f5;
        this.beam1Highband = f6;
        this.beam1E = f7;
        this.beam1Rgb = beam1Rgb;
        this.beam1ScaleX = f8;
        this.beam1Alpha = f9;
        this.beam1Shift = f10;
        this.beam1Dispersion = f11;
        this.beam2PointF = beam2PointF;
        this.beam2LineY = f12;
        this.beam2Highband = f13;
        this.beam2E = f14;
        this.beam2Rgb = beam2Rgb;
        this.beam2ScaleX = f15;
        this.beam2Alpha = f16;
        this.beam2Shift = f17;
        this.beam2Dispersion = f18;
        this.beam3PointF = beam3PointF;
        this.beam3LineY = f19;
        this.beam3Highband = f20;
        this.beam3E = f21;
        this.beam3Rgb = beam3Rgb;
        this.beam3ScaleX = f22;
        this.beam3Alpha = f23;
        this.beam3Shift = f24;
        this.beam3Dispersion = f25;
        this.beam4PointF = beam4PointF;
        this.beam4LineY = f26;
        this.beam4Highband = f27;
        this.beam4E = f28;
        this.beam4Rgb = beam4Rgb;
        this.beam4ScaleX = f29;
        this.beam4Alpha = f30;
        this.beam4Shift = f31;
        this.beam4Dispersion = f32;
        this.beam5PointF = beam5PointF;
        this.beam5LineY = f33;
        this.beam5Highband = f34;
        this.beam5E = f35;
        this.beam5Rgb = beam5Rgb;
        this.beam5ScaleX = f36;
        this.beam5Alpha = f37;
        this.beam5Shift = f38;
        this.beam5Dispersion = f39;
        this.beam6PointF = beam6PointF;
        this.beam6LineY = f40;
        this.beam6Highband = f41;
        this.beam6E = f42;
        this.beam6Rgb = beam6Rgb;
        this.beam6ScaleX = f43;
        this.beam6Alpha = f44;
        this.beam6Shift = f45;
        this.beam6Dispersion = f46;
    }

    public /* synthetic */ FlashLightJson(int i2, int i3, float f2, float f3, float f4, float[] fArr, float[] fArr2, float f5, float f6, float f7, float[] fArr3, float f8, float f9, float f10, float f11, float[] fArr4, float f12, float f13, float f14, float[] fArr5, float f15, float f16, float f17, float f18, float[] fArr6, float f19, float f20, float f21, float[] fArr7, float f22, float f23, float f24, float f25, float[] fArr8, float f26, float f27, float f28, float[] fArr9, float f29, float f30, float f31, float f32, float[] fArr10, float f33, float f34, float f35, float[] fArr11, float f36, float f37, float f38, float f39, float[] fArr12, float f40, float f41, float f42, float[] fArr13, float f43, float f44, float f45, float f46, u uVar) {
        if ((i2 & 1) == 0) {
            this.progress = 0.0f;
        } else {
            this.progress = f2;
        }
        if ((i2 & 2) == 0) {
            this.particleCount = 0.0f;
        } else {
            this.particleCount = f3;
        }
        if ((i2 & 4) == 0) {
            this.particleSize = 0.0f;
        } else {
            this.particleSize = f4;
        }
        if ((i2 & 8) == 0) {
            this.particleColor = new float[3];
        } else {
            this.particleColor = fArr;
        }
        if ((i2 & 16) == 0) {
            this.beam1PointF = new float[2];
        } else {
            this.beam1PointF = fArr2;
        }
        if ((i2 & 32) == 0) {
            this.beam1LineY = 0.0f;
        } else {
            this.beam1LineY = f5;
        }
        if ((i2 & 64) == 0) {
            this.beam1Highband = 0.0f;
        } else {
            this.beam1Highband = f6;
        }
        if ((i2 & 128) == 0) {
            this.beam1E = 0.0f;
        } else {
            this.beam1E = f7;
        }
        if ((i2 & 256) == 0) {
            this.beam1Rgb = new float[3];
        } else {
            this.beam1Rgb = fArr3;
        }
        if ((i2 & 512) == 0) {
            this.beam1ScaleX = 0.0f;
        } else {
            this.beam1ScaleX = f8;
        }
        if ((i2 & 1024) == 0) {
            this.beam1Alpha = 0.0f;
        } else {
            this.beam1Alpha = f9;
        }
        if ((i2 & 2048) == 0) {
            this.beam1Shift = 0.0f;
        } else {
            this.beam1Shift = f10;
        }
        if ((i2 & 4096) == 0) {
            this.beam1Dispersion = 0.0f;
        } else {
            this.beam1Dispersion = f11;
        }
        if ((i2 & 8192) == 0) {
            this.beam2PointF = new float[2];
        } else {
            this.beam2PointF = fArr4;
        }
        if ((i2 & 16384) == 0) {
            this.beam2LineY = 0.0f;
        } else {
            this.beam2LineY = f12;
        }
        if ((i2 & 32768) == 0) {
            this.beam2Highband = 0.0f;
        } else {
            this.beam2Highband = f13;
        }
        if ((i2 & 65536) == 0) {
            this.beam2E = 0.0f;
        } else {
            this.beam2E = f14;
        }
        if ((i2 & 131072) == 0) {
            this.beam2Rgb = new float[3];
        } else {
            this.beam2Rgb = fArr5;
        }
        if ((i2 & 262144) == 0) {
            this.beam2ScaleX = 0.0f;
        } else {
            this.beam2ScaleX = f15;
        }
        if ((i2 & 524288) == 0) {
            this.beam2Alpha = 0.0f;
        } else {
            this.beam2Alpha = f16;
        }
        if ((1048576 & i2) == 0) {
            this.beam2Shift = 0.0f;
        } else {
            this.beam2Shift = f17;
        }
        if ((2097152 & i2) == 0) {
            this.beam2Dispersion = 0.0f;
        } else {
            this.beam2Dispersion = f18;
        }
        if ((4194304 & i2) == 0) {
            this.beam3PointF = new float[2];
        } else {
            this.beam3PointF = fArr6;
        }
        if ((8388608 & i2) == 0) {
            this.beam3LineY = 0.0f;
        } else {
            this.beam3LineY = f19;
        }
        if ((16777216 & i2) == 0) {
            this.beam3Highband = 0.0f;
        } else {
            this.beam3Highband = f20;
        }
        if ((33554432 & i2) == 0) {
            this.beam3E = 0.0f;
        } else {
            this.beam3E = f21;
        }
        if ((67108864 & i2) == 0) {
            this.beam3Rgb = new float[3];
        } else {
            this.beam3Rgb = fArr7;
        }
        if ((134217728 & i2) == 0) {
            this.beam3ScaleX = 0.0f;
        } else {
            this.beam3ScaleX = f22;
        }
        if ((268435456 & i2) == 0) {
            this.beam3Alpha = 0.0f;
        } else {
            this.beam3Alpha = f23;
        }
        if ((536870912 & i2) == 0) {
            this.beam3Shift = 0.0f;
        } else {
            this.beam3Shift = f24;
        }
        if ((1073741824 & i2) == 0) {
            this.beam3Dispersion = 0.0f;
        } else {
            this.beam3Dispersion = f25;
        }
        if ((i2 & Integer.MIN_VALUE) == 0) {
            this.beam4PointF = new float[2];
        } else {
            this.beam4PointF = fArr8;
        }
        if ((i3 & 1) == 0) {
            this.beam4LineY = 0.0f;
        } else {
            this.beam4LineY = f26;
        }
        if ((i3 & 2) == 0) {
            this.beam4Highband = 0.0f;
        } else {
            this.beam4Highband = f27;
        }
        if ((i3 & 4) == 0) {
            this.beam4E = 0.0f;
        } else {
            this.beam4E = f28;
        }
        if ((i3 & 8) == 0) {
            this.beam4Rgb = new float[3];
        } else {
            this.beam4Rgb = fArr9;
        }
        if ((i3 & 16) == 0) {
            this.beam4ScaleX = 0.0f;
        } else {
            this.beam4ScaleX = f29;
        }
        if ((i3 & 32) == 0) {
            this.beam4Alpha = 0.0f;
        } else {
            this.beam4Alpha = f30;
        }
        if ((i3 & 64) == 0) {
            this.beam4Shift = 0.0f;
        } else {
            this.beam4Shift = f31;
        }
        if ((i3 & 128) == 0) {
            this.beam4Dispersion = 0.0f;
        } else {
            this.beam4Dispersion = f32;
        }
        if ((i3 & 256) == 0) {
            this.beam5PointF = new float[2];
        } else {
            this.beam5PointF = fArr10;
        }
        if ((i3 & 512) == 0) {
            this.beam5LineY = 0.0f;
        } else {
            this.beam5LineY = f33;
        }
        if ((i3 & 1024) == 0) {
            this.beam5Highband = 0.0f;
        } else {
            this.beam5Highband = f34;
        }
        if ((i3 & 2048) == 0) {
            this.beam5E = 0.0f;
        } else {
            this.beam5E = f35;
        }
        if ((i3 & 4096) == 0) {
            this.beam5Rgb = new float[3];
        } else {
            this.beam5Rgb = fArr11;
        }
        if ((i3 & 8192) == 0) {
            this.beam5ScaleX = 0.0f;
        } else {
            this.beam5ScaleX = f36;
        }
        if ((i3 & 16384) == 0) {
            this.beam5Alpha = 0.0f;
        } else {
            this.beam5Alpha = f37;
        }
        if ((i3 & 32768) == 0) {
            this.beam5Shift = 0.0f;
        } else {
            this.beam5Shift = f38;
        }
        if ((i3 & 65536) == 0) {
            this.beam5Dispersion = 0.0f;
        } else {
            this.beam5Dispersion = f39;
        }
        if ((i3 & 131072) == 0) {
            this.beam6PointF = new float[2];
        } else {
            this.beam6PointF = fArr12;
        }
        if ((i3 & 262144) == 0) {
            this.beam6LineY = 0.0f;
        } else {
            this.beam6LineY = f40;
        }
        if ((i3 & 524288) == 0) {
            this.beam6Highband = 0.0f;
        } else {
            this.beam6Highband = f41;
        }
        if ((1048576 & i3) == 0) {
            this.beam6E = 0.0f;
        } else {
            this.beam6E = f42;
        }
        if ((2097152 & i3) == 0) {
            this.beam6Rgb = new float[3];
        } else {
            this.beam6Rgb = fArr13;
        }
        if ((4194304 & i3) == 0) {
            this.beam6ScaleX = 0.0f;
        } else {
            this.beam6ScaleX = f43;
        }
        if ((8388608 & i3) == 0) {
            this.beam6Alpha = 0.0f;
        } else {
            this.beam6Alpha = f44;
        }
        if ((16777216 & i3) == 0) {
            this.beam6Shift = 0.0f;
        } else {
            this.beam6Shift = f45;
        }
        if ((33554432 & i3) == 0) {
            this.beam6Dispersion = 0.0f;
        } else {
            this.beam6Dispersion = f46;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FlashLightJson(float f2, float f3, float f4, float[] fArr, float[] fArr2, float f5, float f6, float f7, float[] fArr3, float f8, float f9, float f10, float f11, float[] fArr4, float f12, float f13, float f14, float[] fArr5, float f15, float f16, float f17, float f18, float[] fArr6, float f19, float f20, float f21, float[] fArr7, float f22, float f23, float f24, float f25, float[] fArr8, float f26, float f27, float f28, float[] fArr9, float f29, float f30, float f31, float f32, float[] fArr10, float f33, float f34, float f35, float[] fArr11, float f36, float f37, float f38, float f39, float[] fArr12, float f40, float f41, float f42, float[] fArr13, float f43, float f44, float f45, float f46, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        float f47 = (i2 & 1) != 0 ? 0.0f : f2;
        float f48 = (i2 & 2) != 0 ? 0.0f : f3;
        float f49 = (i2 & 4) != 0 ? 0.0f : f4;
        float[] fArr14 = (i2 & 8) != 0 ? new float[3] : fArr;
        float[] fArr15 = (i2 & 16) != 0 ? new float[2] : fArr2;
        float f50 = (i2 & 32) != 0 ? 0.0f : f5;
        float f51 = (i2 & 64) != 0 ? 0.0f : f6;
        float f52 = (i2 & 128) != 0 ? 0.0f : f7;
        float[] fArr16 = (i2 & 256) != 0 ? new float[3] : fArr3;
        float f53 = (i2 & 512) != 0 ? 0.0f : f8;
        float f54 = (i2 & 1024) != 0 ? 0.0f : f9;
        float f55 = (i2 & 2048) != 0 ? 0.0f : f10;
        float f56 = (i2 & 4096) != 0 ? 0.0f : f11;
        float[] fArr17 = (i2 & 8192) != 0 ? new float[2] : fArr4;
        float f57 = (i2 & 16384) != 0 ? 0.0f : f12;
        float f58 = (i2 & 32768) != 0 ? 0.0f : f13;
        float f59 = (i2 & 65536) != 0 ? 0.0f : f14;
        float[] fArr18 = fArr17;
        float f60 = f57;
        float[] fArr19 = (i2 & 131072) != 0 ? new float[3] : fArr5;
        float f61 = (i2 & 262144) != 0 ? 0.0f : f15;
        float f62 = (i2 & 524288) != 0 ? 0.0f : f16;
        float f63 = (i2 & 1048576) != 0 ? 0.0f : f17;
        float f64 = (i2 & 2097152) != 0 ? 0.0f : f18;
        float[] fArr20 = fArr19;
        float[] fArr21 = (i2 & 4194304) != 0 ? new float[2] : fArr6;
        float f65 = (8388608 & i2) != 0 ? 0.0f : f19;
        float f66 = (i2 & 16777216) != 0 ? 0.0f : f20;
        float f67 = (i2 & 33554432) != 0 ? 0.0f : f21;
        float[] fArr22 = fArr21;
        float f68 = f65;
        float[] fArr23 = (i2 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? new float[3] : fArr7;
        float f69 = (134217728 & i2) != 0 ? 0.0f : f22;
        float f70 = (i2 & 268435456) != 0 ? 0.0f : f23;
        float f71 = (i2 & 536870912) != 0 ? 0.0f : f24;
        float f72 = (i2 & BasicMeasure.EXACTLY) != 0 ? 0.0f : f25;
        float f73 = f69;
        float[] fArr24 = (i2 & Integer.MIN_VALUE) != 0 ? new float[2] : fArr8;
        float f74 = (i3 & 1) != 0 ? 0.0f : f26;
        float f75 = (i3 & 2) != 0 ? 0.0f : f27;
        float f76 = (i3 & 4) != 0 ? 0.0f : f28;
        float f77 = f74;
        float[] fArr25 = fArr24;
        float[] fArr26 = (i3 & 8) != 0 ? new float[3] : fArr9;
        float f78 = (i3 & 16) != 0 ? 0.0f : f29;
        float f79 = (i3 & 32) != 0 ? 0.0f : f30;
        float f80 = (i3 & 64) != 0 ? 0.0f : f31;
        float f81 = f78;
        float f82 = (i3 & 128) != 0 ? 0.0f : f32;
        float[] fArr27 = fArr26;
        float[] fArr28 = (i3 & 256) != 0 ? new float[2] : fArr10;
        float f83 = (i3 & 512) != 0 ? 0.0f : f33;
        float f84 = (i3 & 1024) != 0 ? 0.0f : f34;
        float f85 = (i3 & 2048) != 0 ? 0.0f : f35;
        float[] fArr29 = fArr28;
        float[] fArr30 = (i3 & 4096) != 0 ? new float[3] : fArr11;
        float f86 = (i3 & 8192) != 0 ? 0.0f : f36;
        float f87 = (i3 & 16384) != 0 ? 0.0f : f37;
        this(f47, f48, f49, fArr14, fArr15, f50, f51, f52, fArr16, f53, f54, f55, f56, fArr18, f60, f58, f59, fArr20, f61, f62, f63, f64, fArr22, f68, f66, f67, fArr23, f73, f70, f71, f72, fArr25, f77, f75, f76, fArr27, f81, f79, f80, f82, fArr29, f83, f84, f85, fArr30, f86, f87, (i3 & 32768) != 0 ? 0.0f : f38, (i3 & 65536) != 0 ? 0.0f : f39, (i3 & 131072) != 0 ? new float[2] : fArr12, (i3 & 262144) != 0 ? 0.0f : f40, (i3 & 524288) != 0 ? 0.0f : f41, (i3 & 1048576) != 0 ? 0.0f : f42, (i3 & 2097152) != 0 ? new float[3] : fArr13, (i3 & 4194304) != 0 ? 0.0f : f43, (i3 & 8388608) != 0 ? 0.0f : f44, (i3 & 16777216) != 0 ? 0.0f : f45, (i3 & 33554432) != 0 ? 0.0f : f46);
    }
}
