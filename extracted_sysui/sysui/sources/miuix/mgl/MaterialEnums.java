package miuix.mgl;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialEnums {

    public enum UniformBoolType {
        BOOL(35670),
        BOOL2(35671),
        BOOL3(35672),
        BOOL4(35673);

        int type;

        UniformBoolType(int i2) {
            this.type = i2;
        }
    }

    public enum UniformFloatType {
        FLOAT(5126),
        FLOAT2(35664),
        FLOAT3(35665),
        FLOAT4(35666),
        MAT2(35674),
        MAT3(35675),
        MAT4(35676);

        int type;

        UniformFloatType(int i2) {
            this.type = i2;
        }
    }

    public enum UniformIntType {
        INT(5124),
        INT2(35667),
        INT3(35668),
        INT4(35669);

        int type;

        UniformIntType(int i2) {
            this.type = i2;
        }
    }

    public enum UniformUIntType {
        UINT(5125),
        UINT2(36294),
        UINT3(36295),
        UINT4(36296);

        int type;

        UniformUIntType(int i2) {
            this.type = i2;
        }
    }

    public enum UniformValueType {
        FLOAT(5126),
        FLOAT2(35664),
        FLOAT3(35665),
        FLOAT4(35666),
        INT(5124),
        INT2(35667),
        INT3(35668),
        INT4(35669),
        UINT(5125),
        UINT2(36294),
        UINT3(36295),
        UINT4(36296),
        BOOL(35670),
        BOOL2(35671),
        BOOL3(35672),
        BOOL4(35673),
        MAT2(35674),
        MAT3(35675),
        MAT4(35676);

        int type;

        UniformValueType(int i2) {
            this.type = i2;
        }
    }

    private MaterialEnums() {
        throw new IllegalStateException("Utility class");
    }
}
