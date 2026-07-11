package miui.systemui.flashlight.effect;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.MathUtils;
import javax.microedition.khronos.egl.EGLConfig;
import miui.systemui.flashlight.R;
import miui.systemui.flashlight.effect.particle.DustParticleEffect;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miuix.mgl.MaterialEnums;
import miuix.mgl.Primitive;
import miuix.mgl.RenderMaterial;
import miuix.mgl.RenderTexture;
import miuix.mgl.Texture;
import miuix.mgl.Texture2D;
import miuix.mgl.android.AssetRepo;
import miuix.mgl.android.MglRenderer;
import miuix.mgl.math.Math;

/* JADX INFO: loaded from: classes3.dex */
public class FlashlightRender extends MglRenderer {
    private static final String TAG = "MiFlash_FlashlightRender";
    private boolean isFirstFrame;
    private final BeamConfig mBeamConfig0;
    private final BeamConfig mBeamConfig100;
    private final BeamConfig mBeamConfig25;
    private final BeamConfig mBeamConfig50;
    private final BeamConfig mBeamConfig75;
    private Texture2D mBgTex;
    private final BeamConfig mCurBeanConfig;
    private RenderTexture mFBO;
    private OnFirstFrameDrawListener mFirstFrameDrawListener;
    private RenderMaterial mFlashlightMaterial;
    private Primitive mFlashlightPrimitive;
    private int mHeight;
    private Texture2D mNoiseBwTex;
    private DustParticleEffect mParticle;
    private float mProgress;
    private float[] mResolution;
    private float mTime;
    private Texture2D mUiTex;
    private int mWidth;

    public interface OnFirstFrameDrawListener {
        void onFirstFrameDraw();
    }

    public FlashlightRender(Context context) {
        super(context, false, 0);
        this.mProgress = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mCurBeanConfig = new BeamConfig(0.0f, 0.0f, 0.0f, new float[]{0.482f, 0.482f, 0.482f, 1.0f}, new Beam(new float[]{196.0f, 171.0f}, 190.0f, 0.11f, 1.85f, new float[]{1.0f, 1.0f, 1.0f}, 4.48f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 97.0f}, 102.0f, 0.0f, 6.98f, new float[]{1.0f, 1.0f, 1.0f}, 1.79f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 0.0f, new float[]{0.331f, 0.54f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 0.0f, new float[]{1.0f, 1.0f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 348.0f}, 409.0f, 0.51f, 40.0f, new float[]{1.0f, 1.0f, 1.0f}, 5.66f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 14.0f}, 214.0f, 0.4f, 1.6f, new float[]{1.0f, 1.0f, 1.0f}, 3.18f, 0.0f, 0.03f, 0.0f));
        this.mBeamConfig0 = new BeamConfig(0.0f, 0.0f, 0.0f, new float[]{0.482f, 0.482f, 0.482f, 1.0f}, new Beam(new float[]{196.0f, 171.0f}, 190.0f, 0.11f, 1.85f, new float[]{1.0f, 1.0f, 1.0f}, 4.48f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 97.0f}, 102.0f, 0.0f, 6.98f, new float[]{1.0f, 1.0f, 1.0f}, 1.79f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 0.0f, new float[]{0.331f, 0.54f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 0.0f, new float[]{1.0f, 1.0f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 348.0f}, 409.0f, 0.51f, 40.0f, new float[]{1.0f, 1.0f, 1.0f}, 5.66f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 14.0f}, 214.0f, 0.4f, 1.6f, new float[]{1.0f, 1.0f, 1.0f}, 3.18f, 0.0f, 0.03f, 0.0f));
        this.mBeamConfig25 = new BeamConfig(0.25f, 0.0f, 0.0f, new float[]{0.482f, 0.482f, 0.482f, 1.0f}, new Beam(new float[]{196.0f, 242.0f}, 209.0f, 0.19f, 1.85f, new float[]{1.0f, 1.0f, 1.0f}, 4.01f, 1.62f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 5.5f, new float[]{1.0f, 1.0f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 0.0f}, 346.0f, 0.85f, 13.83f, new float[]{0.331f, 0.54f, 1.0f}, 3.54f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 0.0f, new float[]{1.0f, 1.0f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 572.0f}, 405.0f, 0.7f, 40.0f, new float[]{1.0f, 1.0f, 1.0f}, 5.66f, 2.15f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 14.0f}, 198.0f, 0.39f, 1.6f, new float[]{1.0f, 1.0f, 1.0f}, 3.46f, 2.3f, 0.03f, 0.0f));
        this.mBeamConfig50 = new BeamConfig(0.5265655f, 0.0f, 0.0f, new float[]{0.482f, 0.482f, 0.482f, 1.0f}, new Beam(new float[]{196.0f, 166.0f}, 122.0f, 0.11f, 1.85f, new float[]{1.0f, 1.0f, 1.0f}, 4.503681f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 125.56357f}, 62.3093f, 0.5f, 8.27f, new float[]{1.0f, 1.0f, 1.0f}, 1.3073814f, 1.2f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 27.309298f}, 329.5313f, 0.8334061f, 10.391613f, new float[]{0.331f, 0.54f, 1.0f}, 3.7702467f, 0.981556f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 0.0f}, 0.0f, 0.0f, 1.0f, new float[]{1.0f, 1.0f, 1.0f}, 0.0f, 0.0f, 0.0f, 0.0f), new Beam(new float[]{196.0f, 149.05124f}, 413.57495f, 0.5253131f, 40.0f, new float[]{1.0f, 1.0f, 1.0f}, 5.09f, 2.2435105f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 26.0f}, 276.0f, 0.4f, 1.25f, new float[]{1.0f, 1.0f, 1.0f}, 1.55f, 2.46f, 0.0f, 0.015939279f));
        this.mBeamConfig75 = new BeamConfig(0.75f, 3.46f, 1.87f, new float[]{0.482f, 0.482f, 0.482f, 1.0f}, new Beam(new float[]{196.0f, 229.0f}, 190.0f, 0.11f, 1.85f, new float[]{1.0f, 1.0f, 1.0f}, 4.03f, 0.06f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 206.0f}, 292.0f, 0.5f, 8.27f, new float[]{1.0f, 1.0f, 1.0f}, 1.79f, 1.2f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 257.0f}, 334.0f, 0.82f, 9.48f, new float[]{0.331f, 0.54f, 1.0f}, 3.52f, 0.49f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 45.0f}, 140.0f, 0.1f, 1.0f, new float[]{1.0f, 1.0f, 1.0f}, 1.54f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 242.0f}, 410.0f, 0.57f, 40.0f, new float[]{1.0f, 1.0f, 1.0f}, 5.66f, 3.03f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 31.0f}, 241.0f, 0.41f, 1.59f, new float[]{1.0f, 1.0f, 1.0f}, 2.67f, 2.98f, 0.0f, 0.15f));
        this.mBeamConfig100 = new BeamConfig(1.0f, 9.52f, 1.41f, new float[]{0.482f, 0.482f, 0.482f, 1.0f}, new Beam(new float[]{196.0f, 406.0f}, 143.0f, 0.18f, 1.85f, new float[]{1.0f, 1.0f, 1.0f}, 4.75f, 2.08f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 121.0f}, 108.0f, 0.24f, 14.26f, new float[]{1.0f, 1.0f, 1.0f}, 2.56f, 1.62f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 414.0f}, 305.0f, 0.82f, 10.5f, new float[]{0.331f, 0.54f, 1.0f}, 5.43f, 1.03f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 45.0f}, 100.0f, 0.1f, 1.0f, new float[]{1.0f, 1.0f, 1.0f}, 1.54f, 0.0f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 335.0f}, 410.0f, 0.53f, 30.16f, new float[]{1.0f, 1.0f, 1.0f}, 4.53f, 3.27f, 0.0f, 1.0f), new Beam(new float[]{196.0f, 33.0f}, 252.0f, 0.44f, 1.64f, new float[]{1.0f, 1.0f, 0.92156863f}, 2.75f, 3.59f, 0.45f, 0.66f));
        this.isFirstFrame = true;
    }

    private boolean isFoldInner(Context context) {
        if (context == null || !CommonUtils.INSTANCE.getIS_FOLD()) {
            return false;
        }
        return CommonUtils.isScreenLayoutLarge(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDrawFrame$0() {
        this.mFirstFrameDrawListener.onFirstFrameDraw();
    }

    private void updateBeam(Beam beam, Beam beam2, Beam beam3, float f2) {
        float[] beamPointF = beam.getBeamPointF();
        Math.Companion companion = Math.Companion;
        beamPointF[0] = companion.lerp(beam2.getBeamPointF()[0], beam3.getBeamPointF()[0], f2);
        beam.getBeamPointF()[1] = companion.lerp(beam2.getBeamPointF()[1], beam3.getBeamPointF()[1], f2);
        beam.setBeamLineY(companion.lerp(beam2.getBeamLineY(), beam3.getBeamLineY(), f2));
        beam.setBeamE(companion.lerp(beam2.getBeamE(), beam3.getBeamE(), f2));
        beam.setBeamHighBand(companion.lerp(beam2.getBeamHighBand(), beam3.getBeamHighBand(), f2));
        beam.setBeamAlpha(companion.lerp(beam2.getBeamAlpha(), beam3.getBeamAlpha(), f2));
        beam.setBeamScaleX(companion.lerp(beam2.getBeamScaleX(), beam3.getBeamScaleX(), f2));
        beam.setBeamShift(companion.lerp(beam2.getBeamShift(), beam3.getBeamShift(), f2));
        beam.setBeamDispersion(companion.lerp(beam2.getBeamDispersion(), beam3.getBeamDispersion(), f2));
    }

    private void updateBeamConfig(BeamConfig beamConfig, BeamConfig beamConfig2, float f2) {
        BeamConfig beamConfig3 = this.mCurBeanConfig;
        Math.Companion companion = Math.Companion;
        beamConfig3.setParticleCount(companion.lerp(beamConfig.getParticleCount(), beamConfig2.getParticleCount(), f2));
        this.mCurBeanConfig.setParticleSize(companion.lerp(beamConfig.getParticleSize(), beamConfig2.getParticleSize(), f2));
        updateBeam(this.mCurBeanConfig.getBeam1(), beamConfig.getBeam1(), beamConfig2.getBeam1(), f2);
        updateBeam(this.mCurBeanConfig.getBeam2(), beamConfig.getBeam2(), beamConfig2.getBeam2(), f2);
        updateBeam(this.mCurBeanConfig.getBeam3(), beamConfig.getBeam3(), beamConfig2.getBeam3(), f2);
        updateBeam(this.mCurBeanConfig.getBeam4(), beamConfig.getBeam4(), beamConfig2.getBeam4(), f2);
        updateBeam(this.mCurBeanConfig.getBeam5(), beamConfig.getBeam5(), beamConfig2.getBeam5(), f2);
        updateBeam(this.mCurBeanConfig.getBeam6(), beamConfig.getBeam6(), beamConfig2.getBeam6(), f2);
    }

    private void updateCanvasScale() {
        if (isFoldInner(getContext())) {
            this.mFlashlightMaterial.setFloat("uCanvasScale", 1.1f);
        } else {
            this.mFlashlightMaterial.setFloat("uCanvasScale", 1.0f);
        }
    }

    private void updateMaterial() {
        this.mFlashlightMaterial.setFloat("uTime", getTime());
        this.mFlashlightMaterial.setFloat("uParticleCount", this.mCurBeanConfig.getParticleCount());
        this.mFlashlightMaterial.setFloat("uParticleSize", this.mCurBeanConfig.getParticleSize());
        RenderMaterial renderMaterial = this.mFlashlightMaterial;
        MaterialEnums.UniformFloatType uniformFloatType = MaterialEnums.UniformFloatType.FLOAT2;
        renderMaterial.setFloatArray("uBeam1PointF", uniformFloatType, this.mCurBeanConfig.getBeam1().getBeamPointF());
        this.mFlashlightMaterial.setFloat("uBeam1LineY", this.mCurBeanConfig.getBeam1().getBeamLineY());
        this.mFlashlightMaterial.setFloat("uBeam1E", this.mCurBeanConfig.getBeam1().getBeamE());
        this.mFlashlightMaterial.setFloat("uBeam1HighBand", this.mCurBeanConfig.getBeam1().getBeamHighBand());
        this.mFlashlightMaterial.setFloat("uBeam1Alpha", this.mCurBeanConfig.getBeam1().getBeamAlpha());
        this.mFlashlightMaterial.setFloat("uBeam1ScaleX", this.mCurBeanConfig.getBeam1().getBeamScaleX());
        this.mFlashlightMaterial.setFloat("uBeam1Shift", this.mCurBeanConfig.getBeam1().getBeamShift());
        this.mFlashlightMaterial.setFloat("uBeam1Dispersion", this.mCurBeanConfig.getBeam1().getBeamDispersion());
        this.mFlashlightMaterial.setFloatArray("uBeam2PointF", uniformFloatType, this.mCurBeanConfig.getBeam2().getBeamPointF());
        this.mFlashlightMaterial.setFloat("uBeam2LineY", this.mCurBeanConfig.getBeam2().getBeamLineY());
        this.mFlashlightMaterial.setFloat("uBeam2E", this.mCurBeanConfig.getBeam2().getBeamE());
        this.mFlashlightMaterial.setFloat("uBeam2HighBand", this.mCurBeanConfig.getBeam2().getBeamHighBand());
        this.mFlashlightMaterial.setFloat("uBeam2Alpha", this.mCurBeanConfig.getBeam2().getBeamAlpha());
        this.mFlashlightMaterial.setFloat("uBeam2ScaleX", this.mCurBeanConfig.getBeam2().getBeamScaleX());
        this.mFlashlightMaterial.setFloat("uBeam2Shift", this.mCurBeanConfig.getBeam2().getBeamShift());
        this.mFlashlightMaterial.setFloat("uBeam2Dispersion", this.mCurBeanConfig.getBeam2().getBeamDispersion());
        this.mFlashlightMaterial.setFloatArray("uBeam3PointF", uniformFloatType, this.mCurBeanConfig.getBeam3().getBeamPointF());
        this.mFlashlightMaterial.setFloat("uBeam3LineY", this.mCurBeanConfig.getBeam3().getBeamLineY());
        this.mFlashlightMaterial.setFloat("uBeam3E", this.mCurBeanConfig.getBeam3().getBeamE());
        this.mFlashlightMaterial.setFloat("uBeam3HighBand", this.mCurBeanConfig.getBeam3().getBeamHighBand());
        this.mFlashlightMaterial.setFloat("uBeam3Alpha", this.mCurBeanConfig.getBeam3().getBeamAlpha());
        this.mFlashlightMaterial.setFloat("uBeam3ScaleX", this.mCurBeanConfig.getBeam3().getBeamScaleX());
        this.mFlashlightMaterial.setFloat("uBeam3Shift", this.mCurBeanConfig.getBeam3().getBeamShift());
        this.mFlashlightMaterial.setFloat("uBeam3Dispersion", this.mCurBeanConfig.getBeam3().getBeamDispersion());
        this.mFlashlightMaterial.setFloatArray("uBeam4PointF", uniformFloatType, this.mCurBeanConfig.getBeam4().getBeamPointF());
        this.mFlashlightMaterial.setFloat("uBeam4LineY", this.mCurBeanConfig.getBeam4().getBeamLineY());
        this.mFlashlightMaterial.setFloat("uBeam4E", this.mCurBeanConfig.getBeam4().getBeamE());
        this.mFlashlightMaterial.setFloat("uBeam4HighBand", this.mCurBeanConfig.getBeam4().getBeamHighBand());
        this.mFlashlightMaterial.setFloat("uBeam4Alpha", this.mCurBeanConfig.getBeam4().getBeamAlpha());
        this.mFlashlightMaterial.setFloat("uBeam4ScaleX", this.mCurBeanConfig.getBeam4().getBeamScaleX());
        this.mFlashlightMaterial.setFloat("uBeam4Shift", this.mCurBeanConfig.getBeam4().getBeamShift());
        this.mFlashlightMaterial.setFloat("uBeam4Dispersion", this.mCurBeanConfig.getBeam4().getBeamDispersion());
        this.mFlashlightMaterial.setFloatArray("uBeam5PointF", uniformFloatType, this.mCurBeanConfig.getBeam5().getBeamPointF());
        this.mFlashlightMaterial.setFloat("uBeam5LineY", this.mCurBeanConfig.getBeam5().getBeamLineY());
        this.mFlashlightMaterial.setFloat("uBeam5E", this.mCurBeanConfig.getBeam5().getBeamE());
        this.mFlashlightMaterial.setFloat("uBeam5HighBand", this.mCurBeanConfig.getBeam5().getBeamHighBand());
        this.mFlashlightMaterial.setFloat("uBeam5Alpha", this.mCurBeanConfig.getBeam5().getBeamAlpha());
        this.mFlashlightMaterial.setFloat("uBeam5ScaleX", this.mCurBeanConfig.getBeam5().getBeamScaleX());
        this.mFlashlightMaterial.setFloat("uBeam5Shift", this.mCurBeanConfig.getBeam5().getBeamShift());
        this.mFlashlightMaterial.setFloat("uBeam5Dispersion", this.mCurBeanConfig.getBeam5().getBeamDispersion());
        this.mFlashlightMaterial.setFloatArray("uBeam6PointF", uniformFloatType, this.mCurBeanConfig.getBeam6().getBeamPointF());
        this.mFlashlightMaterial.setFloat("uBeam6LineY", this.mCurBeanConfig.getBeam6().getBeamLineY());
        this.mFlashlightMaterial.setFloat("uBeam6E", this.mCurBeanConfig.getBeam6().getBeamE());
        this.mFlashlightMaterial.setFloat("uBeam6HighBand", this.mCurBeanConfig.getBeam6().getBeamHighBand());
        this.mFlashlightMaterial.setFloat("uBeam6Alpha", this.mCurBeanConfig.getBeam6().getBeamAlpha());
        this.mFlashlightMaterial.setFloat("uBeam6ScaleX", this.mCurBeanConfig.getBeam6().getBeamScaleX());
        this.mFlashlightMaterial.setFloat("uBeam6Shift", this.mCurBeanConfig.getBeam6().getBeamShift());
        this.mFlashlightMaterial.setFloat("uBeam6Dispersion", this.mCurBeanConfig.getBeam6().getBeamDispersion());
    }

    private void updateParticle(int i2, int i3) {
        this.mParticle.setAspect(getRenderContext().getAspect());
        this.mParticle.reset();
    }

    private void updateProgressValue() {
        float f2 = this.mProgress;
        if (f2 >= 0.0f && f2 < 0.25f) {
            updateBeamConfig(this.mBeamConfig0, this.mBeamConfig25, (f2 - 0.0f) / 0.25f);
            return;
        }
        if (f2 >= 0.25f && f2 < 0.5f) {
            updateBeamConfig(this.mBeamConfig25, this.mBeamConfig50, (f2 - 0.25f) / 0.25f);
        } else if (f2 < 0.5f || f2 >= 0.75f) {
            updateBeamConfig(this.mBeamConfig75, this.mBeamConfig100, (f2 - 0.75f) / 0.25f);
        } else {
            updateBeamConfig(this.mBeamConfig50, this.mBeamConfig75, (f2 - 0.5f) / 0.25f);
        }
    }

    public float getProgress() {
        return this.mProgress;
    }

    @Override // miuix.mgl.android.MglRenderer
    public void onDrawFrame() {
        if (this.mFirstFrameDrawListener != null && this.isFirstFrame) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: miui.systemui.flashlight.effect.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5763a.lambda$onDrawFrame$0();
                }
            });
            this.isFirstFrame = false;
        }
        updateDustAlpha();
        updateProgressValue();
        updateMaterial();
        this.mFBO.active(1);
        this.mFlashlightMaterial.active();
        Primitive primitive = this.mFlashlightPrimitive;
        if (primitive != null) {
            primitive.draw(1);
        }
        this.mParticle.draw();
        this.mFBO.deActive();
        this.mFBO.blit(null, getRenderContext().getWidth(), getRenderContext().getHeight());
    }

    @Override // miuix.mgl.android.MglRenderer
    public void onSurfaceChanged(int i2, int i3) {
        int i4 = (!DeviceUtils.isLowEndDevice() || MathUtils.min(i2, i3) <= 720.0f) ? 2 : 4;
        Log.i(TAG, "onSurfaceChanged scale:" + i4 + " width:" + i2 + " height:" + i3);
        int i5 = i2 / i4;
        this.mWidth = i5;
        int i6 = i3 / i4;
        this.mHeight = i6;
        this.mFlashlightMaterial.setFloatArray("uResolution", MaterialEnums.UniformFloatType.FLOAT2, new float[]{(float) i5, (float) i6});
        updateParticle(i2, i3);
        RenderTexture renderTexture = this.mFBO;
        if (renderTexture != null) {
            renderTexture.destroy(true);
        }
        int i7 = this.mHeight;
        if ((i7 & 1) == 1) {
            i7++;
        }
        RenderTexture.Builder builderHeight = RenderTexture.Builder.create().width(this.mWidth).height(i7);
        RenderTexture.AttachmentPoint attachmentPoint = RenderTexture.AttachmentPoint.COLOR;
        this.mFBO = builderHeight.enable(attachmentPoint).colorFormat(RenderTexture.ColorFormat.RGBA8).samplable(attachmentPoint, false).build(getMglContext());
        updateCanvasScale();
    }

    @Override // miuix.mgl.android.MglRenderer
    public void onSurfaceCreated(EGLConfig eGLConfig) {
        this.mFlashlightPrimitive = getAssetRepo().getDefaultPrimitive();
        RenderMaterial renderMaterial = getAssetRepo().getRenderMaterial(new AssetRepo.MKey(R.raw.flashlight_vert, R.raw.flashlight_frag));
        this.mFlashlightMaterial = renderMaterial;
        renderMaterial.setFloat("uMaskOn", 1.0f);
        if (DeviceUtils.isLowEndDevice()) {
            this.mFlashlightMaterial.setFloat("uLite", 1.0f);
        } else {
            this.mFlashlightMaterial.setFloat("uLite", 0.0f);
        }
        updateCanvasScale();
        this.mFlashlightMaterial.setFloatArray("uParticleColor", MaterialEnums.UniformFloatType.FLOAT4, this.mCurBeanConfig.getParticleColor());
        RenderMaterial renderMaterial2 = this.mFlashlightMaterial;
        MaterialEnums.UniformFloatType uniformFloatType = MaterialEnums.UniformFloatType.FLOAT3;
        renderMaterial2.setFloatArray("uBeam1RGB", uniformFloatType, this.mCurBeanConfig.getBeam1().getBeamRgb());
        this.mFlashlightMaterial.setFloatArray("uBeam2RGB", uniformFloatType, this.mCurBeanConfig.getBeam2().getBeamRgb());
        this.mFlashlightMaterial.setFloatArray("uBeam3RGB", uniformFloatType, this.mCurBeanConfig.getBeam3().getBeamRgb());
        this.mFlashlightMaterial.setFloatArray("uBeam4RGB", uniformFloatType, this.mCurBeanConfig.getBeam4().getBeamRgb());
        this.mFlashlightMaterial.setFloatArray("uBeam5RGB", uniformFloatType, this.mCurBeanConfig.getBeam5().getBeamRgb());
        this.mFlashlightMaterial.setFloatArray("uBeam6RGB", uniformFloatType, this.mCurBeanConfig.getBeam6().getBeamRgb());
        Texture2D texture2DCreateFromPngRes = Texture2D.createFromPngRes(R.drawable.noise_bw, getContext().getResources(), getMglContext());
        this.mNoiseBwTex = texture2DCreateFromPngRes;
        texture2DCreateFromPngRes.setWrapMod(Texture.TextureWrapMod.MIRRORED_REPEAT);
        this.mFlashlightMaterial.setTexture("uNoiseBwTex", this.mNoiseBwTex);
        DustParticleEffect dustParticleEffect = this.mParticle;
        if (dustParticleEffect != null) {
            dustParticleEffect.destroy(false);
        }
        this.mParticle = new DustParticleEffect(getRenderContext(), getContext(), getMglContext());
    }

    public void setOnFirstFrameDrawListener(OnFirstFrameDrawListener onFirstFrameDrawListener) {
        this.mFirstFrameDrawListener = onFirstFrameDrawListener;
    }

    public void setProgress(float f2) {
        this.mProgress = Math.Companion.clamp(f2, 0.0f, 1.0f);
    }

    public void updateDustAlpha() {
        float f2 = (this.mProgress - 0.5f) / 0.5f;
        Math.Companion companion = Math.Companion;
        this.mParticle.setAlpha(companion.lerp(0.0f, 0.5f, companion.clamp(f2, 0.0f, 1.0f)));
    }
}
