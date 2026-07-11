package miuix.mgl.particle;

import miuix.mgl.MaterialEnums;
import miuix.mgl.MglContext;
import miuix.mgl.Primitive;
import miuix.mgl.RenderMaterial;
import miuix.mgl.Shader;
import miuix.mgl.ShaderStorageBuffer;
import miuix.mgl.UniformBuffer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Painter {
    MglContext mContext;
    protected RenderMaterial mMaterial;
    int mMatrixIndex;
    boolean mOwnPrimitive;
    Primitive mPrimitive;
    boolean mSupportVertexSSBO;
    private static final float[] VERTEX_POS_UV = {-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f};
    private static final byte[] INDEX = {0, 1, 2, 3, 0, 2};

    public Painter(Shader shader, Primitive primitive) {
        this.mOwnPrimitive = false;
        this.mSupportVertexSSBO = true;
        this.mMatrixIndex = -1;
        RenderMaterial renderMaterialCreate = RenderMaterial.create(shader);
        this.mMaterial = renderMaterialCreate;
        this.mPrimitive = primitive;
        this.mOwnPrimitive = false;
        this.mMatrixIndex = renderMaterialCreate.getPropertyIndex("uMatrix");
    }

    private Primitive createDivisorPrimitive(MglContext mglContext, int i2) {
        Primitive.LegacyBuilder legacyBuilder = new Primitive.LegacyBuilder(6);
        Primitive.VertexElementType vertexElementType = Primitive.VertexElementType.FLOAT;
        Primitive.ComponentSize componentSize = Primitive.ComponentSize.TWO;
        Primitive.LegacyBuilder legacyBuilderVertexBuffer = legacyBuilder.vertexAttribute(0, vertexElementType, componentSize, 0, 0, 16).vertexAttribute(1, vertexElementType, componentSize, 0, 8, 16).vertexBuffer(0, VERTEX_POS_UV);
        Primitive.ComponentSize componentSize2 = Primitive.ComponentSize.FOUR;
        return legacyBuilderVertexBuffer.vertexAttribute(2, vertexElementType, componentSize2, 1, 0, 128).vertexDivisor(2, 1).vertexAttribute(3, vertexElementType, componentSize2, 1, 16, 128).vertexDivisor(3, 1).vertexAttribute(4, vertexElementType, componentSize2, 1, 32, 128).vertexDivisor(4, 1).vertexAttribute(5, vertexElementType, componentSize2, 1, 48, 128).vertexDivisor(5, 1).vertexAttribute(6, vertexElementType, componentSize2, 1, 64, 128).vertexDivisor(6, 1).vertexAttribute(7, vertexElementType, componentSize2, 1, 80, 128).vertexDivisor(7, 1).vertexAttribute(8, vertexElementType, componentSize2, 1, 96, 128).vertexDivisor(8, 1).vertexAttribute(9, vertexElementType, componentSize2, 1, 112, 128).vertexDivisor(9, 1).initialInstanceCount(i2).indices(INDEX).primitiveType(Primitive.PrimitiveType.TRIANGLES).build(mglContext);
    }

    private Primitive createNormalPrimitive(MglContext mglContext) {
        return Primitive.createQuad(mglContext);
    }

    private Primitive createPrimitive(MglContext mglContext, int i2) {
        return this.mSupportVertexSSBO ? createNormalPrimitive(mglContext) : createDivisorPrimitive(mglContext, i2);
    }

    private void initPrimitive(int i2) {
        if (this.mPrimitive != null) {
            return;
        }
        this.mOwnPrimitive = true;
        this.mPrimitive = createPrimitive(this.mContext, i2);
    }

    public void destroy(boolean z2) {
        Primitive primitive;
        onDestroy(z2);
        RenderMaterial renderMaterial = this.mMaterial;
        if (renderMaterial != null) {
            renderMaterial.destroy(z2);
        }
        if (!this.mOwnPrimitive || (primitive = this.mPrimitive) == null) {
            return;
        }
        primitive.destroy(z2);
    }

    public void draw(float[] fArr, int i2, ShaderStorageBuffer shaderStorageBuffer) {
        int i3 = this.mMatrixIndex;
        if (i3 >= 0) {
            this.mMaterial.setFloatArray(i3, MaterialEnums.UniformFloatType.MAT4, fArr);
        }
        initPrimitive(i2);
        if (this.mSupportVertexSSBO) {
            this.mMaterial.setShaderStorageBuffer("Particles", shaderStorageBuffer);
        } else {
            this.mPrimitive.updateDivisorBufferSize(i2);
            this.mPrimitive.setVertexDataFromSSBO(1, shaderStorageBuffer);
        }
        this.mMaterial.active();
        this.mPrimitive.draw(i2);
    }

    public RenderMaterial getMaterial() {
        return this.mMaterial;
    }

    public abstract void onDestroy(boolean z2);

    public void setBaseEmitterParam(UniformBuffer uniformBuffer) {
        this.mMaterial.setUniformBuffer("EmitterParam", uniformBuffer);
    }

    public void setTimeParam(UniformBuffer uniformBuffer) {
        this.mMaterial.setUniformBuffer("TimeParam", uniformBuffer);
    }

    public Painter(Shader shader) {
        this.mOwnPrimitive = false;
        this.mSupportVertexSSBO = true;
        this.mMatrixIndex = -1;
        this.mMaterial = RenderMaterial.create(shader);
        MglContext context = shader.getContext();
        this.mContext = context;
        this.mSupportVertexSSBO = (context == null ? MglContext.getInstance() : context).getMaxVertexSSBOCount() > 0;
        this.mMatrixIndex = this.mMaterial.getPropertyIndex("uMatrix");
    }
}
