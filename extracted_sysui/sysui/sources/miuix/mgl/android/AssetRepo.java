package miuix.mgl.android;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import miuix.mgl.MglContext;
import miuix.mgl.PngParser;
import miuix.mgl.Primitive;
import miuix.mgl.RenderMaterial;
import miuix.mgl.Shader;
import miuix.mgl.Texture;
import miuix.mgl.Texture2D;
import miuix.mgl.WebpParser;
import miuix.mgl.ZstcParser;
import miuix.mgl.utils.IOUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AssetRepo {
    private Context context;
    private MglContext mglContext;
    private Primitive primitive;
    private Primitive primitiveReverseUV;
    private Map<MKey, Shader> shaderMap = new HashMap();
    private Map<Integer, Texture2D> texture2DMap = new HashMap();
    private static final float[] VERTEX_POS = {-1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f};
    private static final float[] VERTEX_UV = {0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    private static final float[] VERTEX_UV_RE = {0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f};
    private static final byte[] INDEX = {0, 1, 2, 3, 0, 2};

    /* JADX INFO: renamed from: miuix.mgl.android.AssetRepo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$miuix$mgl$Shader$ShaderType;

        static {
            int[] iArr = new int[Shader.ShaderType.values().length];
            $SwitchMap$miuix$mgl$Shader$ShaderType = iArr;
            try {
                iArr[Shader.ShaderType.VERTEX_FRAGMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$miuix$mgl$Shader$ShaderType[Shader.ShaderType.COMPUTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AssetRepo(Context context) {
        this.context = context;
    }

    public void clearNativeResource() {
        Primitive primitive = this.primitive;
        if (primitive != null) {
            primitive.destroy(false);
        }
        Primitive primitive2 = this.primitiveReverseUV;
        if (primitive2 != null) {
            primitive2.destroy(false);
        }
        this.primitive = null;
        this.primitiveReverseUV = null;
        this.texture2DMap.forEach(new BiConsumer() { // from class: miuix.mgl.android.a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Texture2D) obj2).destroy(false);
            }
        });
        this.texture2DMap.clear();
        this.shaderMap.forEach(new BiConsumer() { // from class: miuix.mgl.android.b
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Shader) obj2).destroy(false);
            }
        });
        this.shaderMap.clear();
    }

    public void destroyTexture2D(int i2, boolean z2) {
        Texture2D texture2D = this.texture2DMap.get(Integer.valueOf(i2));
        if (texture2D != null) {
            texture2D.destroy(z2);
            this.texture2DMap.remove(Integer.valueOf(i2));
        }
    }

    public Primitive getDefaultPrimitive() {
        if (this.primitive == null) {
            Primitive.Builder builderCreate = Primitive.Builder.create(4);
            float[] fArr = VERTEX_POS;
            Primitive.VertexElementType vertexElementType = Primitive.VertexElementType.FLOAT;
            Primitive.ComponentSize componentSize = Primitive.ComponentSize.TWO;
            this.primitive = builderCreate.vertexAttribute(0, fArr, vertexElementType, componentSize, false).vertexAttribute(1, VERTEX_UV, vertexElementType, componentSize, false).indices(INDEX).primitiveType(Primitive.PrimitiveType.TRIANGLES).build(this.mglContext, Primitive.Builder.Mod.ONE);
        }
        return this.primitive;
    }

    public Primitive getDefaultPrimitiveReverseUV() {
        if (this.primitiveReverseUV == null) {
            Primitive.Builder builderCreate = Primitive.Builder.create(4);
            float[] fArr = VERTEX_POS;
            Primitive.VertexElementType vertexElementType = Primitive.VertexElementType.FLOAT;
            Primitive.ComponentSize componentSize = Primitive.ComponentSize.TWO;
            this.primitiveReverseUV = builderCreate.vertexAttribute(0, fArr, vertexElementType, componentSize, false).vertexAttribute(1, VERTEX_UV_RE, vertexElementType, componentSize, false).indices(INDEX).primitiveType(Primitive.PrimitiveType.TRIANGLES).build(this.mglContext, Primitive.Builder.Mod.ONE);
        }
        return this.primitiveReverseUV;
    }

    public RenderMaterial getRenderMaterial(MKey mKey) {
        return RenderMaterial.create(getShader(mKey));
    }

    public Shader getShader(MKey mKey) {
        Shader shaderBuild = this.shaderMap.get(mKey);
        if (shaderBuild != null) {
            return shaderBuild;
        }
        int i2 = AnonymousClass1.$SwitchMap$miuix$mgl$Shader$ShaderType[mKey.type.ordinal()];
        if (i2 == 1) {
            shaderBuild = Shader.Builder.create().type(mKey.type).vertexSource(IOUtils.readTextFileFromResource(mKey.vertId, this.context.getResources())).fragmentSource(IOUtils.readTextFileFromResource(mKey.fragId, this.context.getResources())).build(this.mglContext);
        } else if (i2 == 2) {
            shaderBuild = Shader.Builder.create().type(mKey.type).computeSource(IOUtils.readTextFileFromResource(mKey.compId, this.context.getResources())).build(this.mglContext);
        }
        this.shaderMap.put(mKey, shaderBuild);
        return shaderBuild;
    }

    public Texture2D getTexture2DPng(int i2) {
        return getTexture2DPng(i2, 0);
    }

    public Texture2D getTexture2DWebp(int i2) {
        Texture2D texture2D = this.texture2DMap.get(Integer.valueOf(i2));
        if (texture2D != null) {
            return texture2D;
        }
        WebpParser webpParserCreate = WebpParser.create();
        webpParserCreate.parseFromRes(i2, this.context.getResources());
        Texture2D texture2DBuild = Texture2D.Builder.create().width(webpParserCreate.getWidth()).height(webpParserCreate.getHeight()).format(webpParserCreate.getTextureFormat()).build(this.mglContext);
        texture2DBuild.setDataFromParser(0, webpParserCreate);
        webpParserCreate.destroy();
        texture2DBuild.setWrapMod(Texture.TextureWrapMod.CLAMP_TO_EDGE);
        this.texture2DMap.put(Integer.valueOf(i2), texture2DBuild);
        return texture2DBuild;
    }

    public Texture2D getTexture2DZstc(int i2) {
        Texture2D texture2D = this.texture2DMap.get(Integer.valueOf(i2));
        if (texture2D != null) {
            return texture2D;
        }
        ZstcParser zstcParserCreate = ZstcParser.create();
        zstcParserCreate.parseFromRes(i2, this.context.getResources());
        Texture2D texture2DBuild = Texture2D.Builder.create().width(zstcParserCreate.getWidth()).height(zstcParserCreate.getHeight()).format(zstcParserCreate.getTextureFormat()).build(this.mglContext);
        texture2DBuild.setDataFromParser(0, zstcParserCreate);
        zstcParserCreate.destroy();
        texture2DBuild.setWrapMod(Texture.TextureWrapMod.CLAMP_TO_EDGE);
        this.texture2DMap.put(Integer.valueOf(i2), texture2DBuild);
        return texture2DBuild;
    }

    public void initContext(MglContext mglContext) {
        this.mglContext = mglContext;
    }

    public Texture2D getTexture2DPng(int i2, int i3) {
        Texture2D texture2D = this.texture2DMap.get(Integer.valueOf(i2));
        if (texture2D != null) {
            return texture2D;
        }
        PngParser pngParserCreate = PngParser.create(i3);
        pngParserCreate.parseFromRes(i2, this.context.getResources());
        Texture2D texture2DBuild = Texture2D.Builder.create().width(pngParserCreate.getWidth()).height(pngParserCreate.getHeight()).format(pngParserCreate.getTextureFormat()).build(this.mglContext);
        texture2DBuild.setDataFromParser(0, pngParserCreate);
        pngParserCreate.destroy();
        texture2DBuild.setWrapMod(Texture.TextureWrapMod.CLAMP_TO_EDGE);
        this.texture2DMap.put(Integer.valueOf(i2), texture2DBuild);
        return texture2DBuild;
    }

    public static class MKey {
        public int compId;
        public int fragId;
        public Shader.ShaderType type;
        public int vertId;

        public MKey(int i2, int i3) {
            this.vertId = i2;
            this.fragId = i3;
            this.type = Shader.ShaderType.VERTEX_FRAGMENT;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MKey mKey = (MKey) obj;
            return this.vertId == mKey.vertId && this.fragId == mKey.fragId && this.compId == mKey.compId && Objects.equals(this.type, mKey.type);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.vertId), Integer.valueOf(this.fragId), Integer.valueOf(this.compId), this.type);
        }

        public MKey(int i2) {
            this.compId = i2;
            this.type = Shader.ShaderType.COMPUTE;
        }
    }

    public void destroyTexture2D(int i2) {
        destroyTexture2D(i2, true);
    }
}
