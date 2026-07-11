package miuix.mgl;

import androidx.exifinterface.media.ExifInterface;
import androidx.mediarouter.media.MediaRouter;
import com.xiaomi.onetrack.api.ai;

/* JADX INFO: loaded from: classes3.dex */
public class RenderMaterial extends Material {

    public enum BlendEquation {
        ADD(32774),
        SUB(32778),
        REVERSE_SUB(32779),
        MIN(32775),
        MAX(32776);

        int equation;

        BlendEquation(int i2) {
            this.equation = i2;
        }
    }

    public enum BlendFuncFactor {
        ZERO(0),
        ONE(1),
        SRC_COLOR(768),
        ONE_MINUS_SRC_COLOR(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTER_PARAMS_CHANGED),
        DST_COLOR(774),
        ONE_MINUS_DST_COLOR(775),
        SRC_ALPHA(770),
        ONE_MINUS_SRC_ALPHA(771),
        DST_ALPHA(772),
        ONE_MINUS_DST_ALPHA(773),
        CONSTANT_COLOR(32769),
        ONE_MINUS_CONSTANT_COLOR(32770),
        CONSTANT_ALPHA(32771),
        ONE_MINUS_CONSTANT_ALPHA(32772),
        SRC_ALPHA_SATURATE(776);

        int factor;

        BlendFuncFactor(int i2) {
            this.factor = i2;
        }
    }

    public enum ColorWriteLayer {
        R(1),
        G(2),
        B(4),
        A(8);

        int layer;

        ColorWriteLayer(int i2) {
            this.layer = i2;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'R' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class ColorWriteMask {
        private static final /* synthetic */ ColorWriteMask[] $VALUES;

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public static final ColorWriteMask f6119A;
        public static final ColorWriteMask ALL;

        /* JADX INFO: renamed from: B, reason: collision with root package name */
        public static final ColorWriteMask f6120B;

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public static final ColorWriteMask f6121G;
        public static final ColorWriteMask NONE = new ColorWriteMask("NONE", 0, 0);

        /* JADX INFO: renamed from: R, reason: collision with root package name */
        public static final ColorWriteMask f6122R;
        public static final ColorWriteMask RG;
        public static final ColorWriteMask RGB;
        public static final ColorWriteMask RGBA;
        int mask;

        private static /* synthetic */ ColorWriteMask[] $values() {
            return new ColorWriteMask[]{NONE, f6122R, f6121G, f6120B, f6119A, RG, RGB, RGBA, ALL};
        }

        static {
            ColorWriteLayer colorWriteLayer = ColorWriteLayer.R;
            f6122R = new ColorWriteMask("R", 1, colorWriteLayer.layer);
            ColorWriteLayer colorWriteLayer2 = ColorWriteLayer.G;
            f6121G = new ColorWriteMask("G", 2, colorWriteLayer2.layer);
            ColorWriteLayer colorWriteLayer3 = ColorWriteLayer.B;
            f6120B = new ColorWriteMask(ai.f2847a, 3, colorWriteLayer3.layer);
            ColorWriteLayer colorWriteLayer4 = ColorWriteLayer.A;
            f6119A = new ColorWriteMask(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, 4, colorWriteLayer4.layer);
            ColorWriteMask colorWriteMask = new ColorWriteMask("RG", 5, colorWriteLayer.layer | colorWriteLayer2.layer);
            RG = colorWriteMask;
            ColorWriteMask colorWriteMask2 = new ColorWriteMask("RGB", 6, colorWriteMask.mask | colorWriteLayer3.layer);
            RGB = colorWriteMask2;
            ColorWriteMask colorWriteMask3 = new ColorWriteMask("RGBA", 7, colorWriteMask2.mask | colorWriteLayer4.layer);
            RGBA = colorWriteMask3;
            ALL = new ColorWriteMask("ALL", 8, colorWriteMask3.mask);
            $VALUES = $values();
        }

        private ColorWriteMask(String str, int i2, int i3) {
            this.mask = i3;
        }

        public static ColorWriteMask valueOf(String str) {
            return (ColorWriteMask) Enum.valueOf(ColorWriteMask.class, str);
        }

        public static ColorWriteMask[] values() {
            return (ColorWriteMask[]) $VALUES.clone();
        }
    }

    public enum Cull {
        NONE(0),
        FRONT(1028),
        BACK(1029),
        FRONT_AND_BACK(1032);

        int cull;

        Cull(int i2) {
            this.cull = i2;
        }
    }

    public enum DepthFunc {
        NEVER(512),
        LESS(513),
        EQUAL(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED),
        LEQUAL(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_CHANGED),
        GREATER(516),
        NOTEQUAL(517),
        GEQUAL(518),
        ALWAYS(519);

        int func;

        DepthFunc(int i2) {
            this.func = i2;
        }
    }

    public enum StencilFace {
        FRONT(1028),
        BACK(1029);

        int face;

        StencilFace(int i2) {
            this.face = i2;
        }
    }

    public enum StencilFunc {
        NEVER(512),
        LESS(513),
        LEQUAL(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_CHANGED),
        GREATER(516),
        GEQUAL(518),
        EQUAL(MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED),
        NOTEQUAL(517),
        ALWAYS(519);

        int func;

        StencilFunc(int i2) {
            this.func = i2;
        }
    }

    public enum StencilOp {
        KEEP(7680),
        ZERO(0),
        REPLACE(7681),
        INCR(7682),
        INCR_WRAP(34055),
        DECR(7683),
        DECR_WRAP(34056),
        INVERT(5386);

        int op;

        StencilOp(int i2) {
            this.op = i2;
        }
    }

    public RenderMaterial(long j2) {
        super(j2);
    }

    public static RenderMaterial create(Shader shader) {
        return new RenderMaterial(nCreate(shader.getNativeObject()));
    }

    public static RenderMaterial createTemp(long j2) {
        RenderMaterial renderMaterial = new RenderMaterial(0L);
        renderMaterial.initTempNativeObject(j2);
        return renderMaterial;
    }

    private static native long nCreate(long j2);

    private static native void nEnableBlend(long j2, boolean z2);

    private static native void nEnableDepthTest(long j2, boolean z2);

    private static native void nEnableDepthWrite(long j2, boolean z2);

    private static native void nEnableStencilTest(long j2, boolean z2);

    private static native void nSetBlendEquationSeparate(long j2, int i2, int i3);

    private static native void nSetBlendFunc(long j2, int i2, int i3);

    private static native void nSetBlendFuncSeparate(long j2, int i2, int i3, int i4, int i5);

    private static native void nSetColorWriteMask(long j2, int i2);

    private static native void nSetCull(long j2, int i2);

    private static native void nSetDepthFunc(long j2, int i2);

    private static native void nSetStencilFunc(long j2, int i2, int i3, int i4, int i5);

    private static native void nSetStencilFuncV2(long j2, int i2, int i3, int i4);

    private static native void nSetStencilOp(long j2, int i2, int i3, int i4, int i5);

    private static native void nSetStencilOpV2(long j2, int i2, int i3, int i4);

    private static native void nSetStencilWriteMask(long j2, int i2, int i3);

    private static native void nSetStencilWriteMaskV2(long j2, int i2);

    public void enableBlend(boolean z2) {
        nEnableBlend(getNativeObject(), z2);
    }

    public void enableDepthTest(boolean z2) {
        nEnableDepthTest(getNativeObject(), z2);
    }

    public void enableDepthWrite(boolean z2) {
        nEnableDepthWrite(getNativeObject(), z2);
    }

    public void enableStencilTest(boolean z2) {
        nEnableStencilTest(getNativeObject(), z2);
    }

    public void setBlendEquation(BlendEquation blendEquation) {
        long nativeObject = getNativeObject();
        int i2 = blendEquation.equation;
        nSetBlendEquationSeparate(nativeObject, i2, i2);
    }

    public void setBlendFunc(BlendFuncFactor blendFuncFactor, BlendFuncFactor blendFuncFactor2) {
        nSetBlendFunc(getNativeObject(), blendFuncFactor.factor, blendFuncFactor2.factor);
    }

    public void setColorWriteMask(ColorWriteMask colorWriteMask) {
        nSetColorWriteMask(getNativeObject(), colorWriteMask.mask);
    }

    public void setCull(Cull cull) {
        nSetCull(getNativeObject(), cull.cull);
    }

    public void setDepthFunc(DepthFunc depthFunc) {
        nSetDepthFunc(getNativeObject(), depthFunc.func);
    }

    public void setStencilFunc(StencilFace stencilFace, StencilFunc stencilFunc, int i2, int i3) {
        nSetStencilFunc(getNativeObject(), stencilFace.face, stencilFunc.func, i2, i3);
    }

    public void setStencilOp(StencilFace stencilFace, StencilOp stencilOp, StencilOp stencilOp2, StencilOp stencilOp3) {
        nSetStencilOp(getNativeObject(), stencilFace.face, stencilOp.op, stencilOp2.op, stencilOp3.op);
    }

    public void setStencilWriteMask(StencilFace stencilFace, int i2) {
        nSetStencilWriteMask(getNativeObject(), stencilFace.face, i2);
    }

    public void setBlendEquation(BlendEquation blendEquation, BlendEquation blendEquation2) {
        nSetBlendEquationSeparate(getNativeObject(), blendEquation.equation, blendEquation2.equation);
    }

    public void setBlendFunc(BlendFuncFactor blendFuncFactor, BlendFuncFactor blendFuncFactor2, BlendFuncFactor blendFuncFactor3, BlendFuncFactor blendFuncFactor4) {
        nSetBlendFuncSeparate(getNativeObject(), blendFuncFactor.factor, blendFuncFactor2.factor, blendFuncFactor3.factor, blendFuncFactor4.factor);
    }

    public void setStencilFunc(StencilFunc stencilFunc, int i2, int i3) {
        nSetStencilFuncV2(getNativeObject(), stencilFunc.func, i2, i3);
    }

    public void setStencilOp(StencilOp stencilOp, StencilOp stencilOp2, StencilOp stencilOp3) {
        nSetStencilOpV2(getNativeObject(), stencilOp.op, stencilOp2.op, stencilOp3.op);
    }

    public void setStencilWriteMask(int i2) {
        nSetStencilWriteMaskV2(getNativeObject(), i2);
    }
}
