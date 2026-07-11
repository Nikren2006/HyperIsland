package miui.systemui.flashlight.effect.particle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import miui.systemui.flashlight.R;
import miuix.mgl.MglContext;
import miuix.mgl.Texture;
import miuix.mgl.Texture2D;
import miuix.mgl.android.AssetRepo;
import miuix.mgl.android.MglRenderer;
import miuix.mgl.particle.ParticleRenderer;

/* JADX INFO: loaded from: classes3.dex */
public class DustParticleEffect {
    private final MglRenderer.RenderContext mContext;
    private final DustEffector mEffector;
    private final DustEmitter mEmitter;
    private final DustPainter mPainter;
    private final ParticleRenderer mParticleRenderer;
    private Texture2D mPerlinTex;

    public DustParticleEffect(MglRenderer.RenderContext renderContext, Context context, MglContext mglContext) {
        this.mContext = renderContext;
        DustEmitter dustEmitter = new DustEmitter(renderContext.getmAssetRepo().getShader(new AssetRepo.MKey(R.raw.particle_dust_emitter)), null);
        this.mEmitter = dustEmitter;
        dustEmitter.setLife(56.0f, 56.0f);
        dustEmitter.setScale(renderContext.getHwRatio(), 1.0f, 1.0f);
        dustEmitter.setStartSize(0.002f, 0.0035f);
        dustEmitter.setFrequency(1.9f);
        dustEmitter.enablePreWarm(true);
        dustEmitter.setLineY(0.5f);
        Texture2D texture2DCreateFromBitmap = Texture2D.createFromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.perlin).copy(Bitmap.Config.ARGB_8888, true), mglContext);
        this.mPerlinTex = texture2DCreateFromBitmap;
        texture2DCreateFromBitmap.setWrapMod(Texture.TextureWrapMod.MIRRORED_REPEAT);
        DustEffector dustEffector = new DustEffector(renderContext.getmAssetRepo().getShader(new AssetRepo.MKey(R.raw.particle_dust)), this.mPerlinTex);
        this.mEffector = dustEffector;
        dustEffector.setSpeed(1.0f);
        DustPainter dustPainter = new DustPainter(renderContext.getmAssetRepo().getShader(new AssetRepo.MKey(renderContext.getMglContext().getMaxVertexSSBOCount() > 0 ? R.raw.particle_vertex_shader : R.raw.particle_no_ssbo_vertex_shader, R.raw.particle_dust_frag_shader)), renderContext.getmAssetRepo().getTexture2DZstc(R.raw.particle));
        this.mPainter = dustPainter;
        dustPainter.setAlpha(0.3f);
        this.mParticleRenderer = new ParticleRenderer(dustEmitter, dustEffector, dustPainter);
    }

    public void destroy(boolean z2) {
        this.mParticleRenderer.destroy(z2);
    }

    public void draw() {
        this.mParticleRenderer.draw(this.mContext.getTime(), null);
    }

    public void reset() {
        this.mEmitter.reset();
    }

    public void setAlpha(float f2) {
        this.mPainter.setAlpha(f2);
    }

    public void setAspect(float f2) {
        this.mEmitter.setScale(this.mContext.getHwRatio() * 0.75f, 0.75f, 1.0f);
    }

    public void setEmitterY(float f2) {
        this.mEmitter.setLineY(f2);
    }

    public void setSpeed(float f2) {
        this.mEffector.setSpeed(f2);
    }
}
