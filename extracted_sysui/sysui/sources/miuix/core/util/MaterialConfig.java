package miuix.core.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialConfig {
    public static final int SUPPORT_VERSION = 30;

    @Nullable
    private BloomStrokeConfig mBloomStrokeConfig;

    @Nullable
    private BlurConfig mBlurConfig;

    @Nullable
    private ColorBlendConfig mColorBlendConfig;

    @Nullable
    private ColorBlendConfig mFallbackColorBlendConfig;

    @Nullable
    private ShadowConfig mShadowConfig;
    public final String token;
    public final String tokenVariant;
    public final int version;

    public static class ShadowConfig {
        public int shadowColor;
        public float shadowDispersion;
        public float shadowOffsetX;
        public float shadowOffsetY;
        public float shadowRadius;

        public ShadowConfig(int i2, float f2, float f3, float f4, float f5) {
            this.shadowColor = i2;
            this.shadowOffsetX = f2;
            this.shadowOffsetY = f3;
            this.shadowRadius = f4;
            this.shadowDispersion = f5;
        }
    }

    public MaterialConfig(Parcel parcel) {
        int i2 = parcel.readInt();
        this.version = i2;
        String string = parcel.readString();
        this.token = string;
        String string2 = parcel.readString();
        this.tokenVariant = string2;
        if (i2 <= 30) {
            parseCurrentVersion(parcel);
        } else {
            Log.e("MaterialConfig", String.format("Error! Can't read this token: the support version is %d, your token version is %d, tokenInfo:%s_%s", 30, Integer.valueOf(i2), string, string2));
        }
    }

    @Nullable
    public static MaterialConfig create(@Nullable Parcelable parcelable, @NonNull float[] fArr) {
        MaterialConfig materialConfigCreate = create(parcelable);
        if (materialConfigCreate == null) {
            return null;
        }
        materialConfigCreate.mBloomStrokeConfig = new BloomStrokeConfig(fArr);
        return materialConfigCreate;
    }

    public static BlurConfig createContainerBlurConfig(int i2) {
        return new BlurConfig(1, 0, 0, i2);
    }

    private void parseCurrentVersion(Parcel parcel) {
        parseVersionBelow30(parcel);
    }

    private void parseVersionBelow30(Parcel parcel) {
        float[] fArr;
        int[] iArr;
        int[] iArr2;
        float[] fArr2;
        int[] iArr3;
        int[] iArr4;
        float[] fArr3;
        if (Math.max(0, parcel.readInt()) > 0) {
            int iMax = Math.max(0, parcel.readInt());
            if (iMax > 0) {
                iArr = new int[iMax];
                parcel.readIntArray(iArr);
            } else {
                iArr = null;
            }
            int iMax2 = Math.max(0, parcel.readInt());
            if (iMax2 > 0) {
                iArr2 = new int[iMax2];
                parcel.readIntArray(iArr2);
            } else {
                iArr2 = null;
            }
            int iMax3 = Math.max(0, parcel.readInt());
            if (iMax3 > 0) {
                fArr2 = new float[iMax3];
                parcel.readFloatArray(fArr2);
            } else {
                fArr2 = null;
            }
            this.mColorBlendConfig = new ColorBlendConfig(iArr, iArr2, fArr2);
            if (Math.max(0, parcel.readInt()) > 0) {
                int iMax4 = Math.max(0, parcel.readInt());
                if (iMax4 > 0) {
                    iArr3 = new int[iMax4];
                    parcel.readIntArray(iArr3);
                } else {
                    iArr3 = null;
                }
                int iMax5 = Math.max(0, parcel.readInt());
                if (iMax5 > 0) {
                    iArr4 = new int[iMax5];
                    parcel.readIntArray(iArr4);
                } else {
                    iArr4 = null;
                }
                int iMax6 = Math.max(0, parcel.readInt());
                if (iMax6 > 0) {
                    fArr3 = new float[iMax6];
                    parcel.readFloatArray(fArr3);
                } else {
                    fArr3 = null;
                }
                this.mFallbackColorBlendConfig = new ColorBlendConfig(iArr3, iArr4, fArr3);
            }
        }
        if (Math.max(0, parcel.readInt()) > 0) {
            int i2 = parcel.readInt();
            int i3 = parcel.readInt();
            int i4 = parcel.readInt();
            int i5 = parcel.readInt();
            int i6 = parcel.readInt();
            int iMax7 = Math.max(0, parcel.readInt());
            if (iMax7 > 0) {
                float[] fArr4 = new float[iMax7];
                parcel.readFloatArray(fArr4);
                fArr = fArr4;
            } else {
                fArr = null;
            }
            this.mBlurConfig = new BlurConfig(i2, i3, i4, i5, i6, fArr, this.version <= RomUtils.getHyperOsVersion() * 10 ? this.mColorBlendConfig : this.mFallbackColorBlendConfig);
        } else {
            this.mBlurConfig = null;
        }
        if (Math.max(0, parcel.readInt()) > 0) {
            this.mShadowConfig = new ShadowConfig(parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
        } else {
            this.mShadowConfig = null;
        }
        if (Math.max(0, parcel.readInt()) > 0) {
            this.mBloomStrokeConfig = new BloomStrokeConfig(parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
        } else {
            this.mBloomStrokeConfig = null;
        }
    }

    @Nullable
    public BloomStrokeConfig getBloomStrokeConfig() {
        return this.mBloomStrokeConfig;
    }

    @Nullable
    public BlurConfig getBlurConfig() {
        return this.mBlurConfig;
    }

    @Nullable
    public ColorBlendConfig getColorBlendConfig() {
        return this.mColorBlendConfig;
    }

    @Nullable
    public ColorBlendConfig getFallbackColorBlendConfig() {
        return this.mFallbackColorBlendConfig;
    }

    @Nullable
    public ShadowConfig getShadowConfig() {
        return this.mShadowConfig;
    }

    public static BlurConfig createContainerBlurConfig(int i2, int[] iArr, int[] iArr2) {
        return new BlurConfig(1, 0, 0, i2, iArr, iArr2);
    }

    @Nullable
    public static MaterialConfig create(@Nullable Parcelable parcelable, @Nullable BloomStrokeConfig bloomStrokeConfig) {
        MaterialConfig materialConfigCreate = create(parcelable);
        if (materialConfigCreate == null) {
            return null;
        }
        if (bloomStrokeConfig != null) {
            materialConfigCreate.mBloomStrokeConfig = bloomStrokeConfig;
        }
        return materialConfigCreate;
    }

    public static class ColorBlendConfig {
        public int[] blendColors;
        public float[] blendExtraParams;
        public int[] blendModes;

        public ColorBlendConfig(int[] iArr, int[] iArr2) {
            this.blendColors = iArr;
            this.blendModes = iArr2;
            this.blendExtraParams = null;
        }

        public static class Element {

            @NonNull
            public final ColorBlendConfig colorBlendConfig;
            public final int[] elementColors;
            public final int[] elementModes;
            private float ratio = 1.0f;

            public Element(@NonNull ColorBlendConfig colorBlendConfig) {
                this.colorBlendConfig = colorBlendConfig;
                int[] iArr = colorBlendConfig.blendColors;
                this.elementColors = Arrays.copyOfRange(iArr, 0, iArr.length);
                this.elementModes = colorBlendConfig.blendModes;
            }

            public void updateRatio(float f2) {
                if (this.ratio == f2) {
                    return;
                }
                this.ratio = f2;
                int i2 = 0;
                while (true) {
                    int[] iArr = this.elementColors;
                    if (i2 >= iArr.length) {
                        return;
                    }
                    iArr[i2] = (this.colorBlendConfig.blendColors[i2] & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((r1 >>> 24) * this.ratio)) << 24);
                    i2++;
                }
            }

            public Element(@NonNull ColorBlendConfig colorBlendConfig, float f2) {
                this.colorBlendConfig = colorBlendConfig;
                int[] iArr = colorBlendConfig.blendColors;
                this.elementColors = Arrays.copyOfRange(iArr, 0, iArr.length);
                this.elementModes = colorBlendConfig.blendModes;
                updateRatio(f2);
            }
        }

        public ColorBlendConfig(int[] iArr, int[] iArr2, float[] fArr) {
            this.blendColors = iArr;
            this.blendModes = iArr2;
            this.blendExtraParams = fArr;
        }
    }

    @Nullable
    public static MaterialConfig create(@Nullable Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.setDataPosition(0);
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            MaterialConfig materialConfig = new MaterialConfig(parcelObtain);
            parcelObtain.recycle();
            return materialConfig;
        } catch (Exception unused) {
            parcelObtain.recycle();
            return null;
        }
    }

    public static class BlurConfig {
        public int blurBgMode;
        public int blurContentMode;
        public float[] blurExtraParams;
        public int blurRadius;
        public int blurSubType;
        public int blurType;

        @Nullable
        public ColorBlendConfig colorBlendConfig;

        public BlurConfig(@Nullable ColorBlendConfig colorBlendConfig) {
            this.blurBgMode = 0;
            this.blurContentMode = 0;
            this.blurType = 0;
            this.blurRadius = 0;
            this.blurExtraParams = null;
            this.colorBlendConfig = colorBlendConfig;
        }

        public BlurConfig(int i2, int i3, int i4, int i5) {
            this.blurBgMode = i2;
            this.blurContentMode = i3;
            this.blurType = i4;
            this.blurRadius = i5;
            this.blurSubType = 0;
            this.blurExtraParams = null;
            this.colorBlendConfig = null;
        }

        public BlurConfig(int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2) {
            this.blurBgMode = i2;
            this.blurContentMode = i3;
            this.blurType = i4;
            this.blurRadius = i5;
            this.blurSubType = 0;
            this.blurExtraParams = null;
            this.colorBlendConfig = new ColorBlendConfig(iArr, iArr2);
        }

        public BlurConfig(int i2, int i3, int i4, int i5, int i6, float[] fArr, @Nullable ColorBlendConfig colorBlendConfig) {
            this.blurBgMode = i2;
            this.blurContentMode = i3;
            this.blurType = i4;
            this.blurRadius = i5;
            this.blurSubType = i6;
            this.blurExtraParams = fArr;
            this.colorBlendConfig = colorBlendConfig;
        }
    }

    public static class BloomStrokeConfig {
        public float bloomStrokeColorA;
        public float bloomStrokeColorB;
        public float bloomStrokeColorG;
        public float bloomStrokeColorR;
        public float bloomStrokeGradientDegree;
        public float bloomStrokeWidth;
        public float normalWidth;
        public float source1A;
        public float source1B;
        public float source1G;
        public float source1R;
        public float source1X;
        public float source1Y;
        public float source1Z;
        public float source2A;
        public float source2B;
        public float source2G;
        public float source2R;
        public float source2X;
        public float source2Y;
        public float source2Z;

        public BloomStrokeConfig(float[] fArr) {
            this.normalWidth = 0.0f;
            this.source1X = 0.0f;
            this.source1Y = 0.0f;
            this.source1Z = 0.0f;
            this.source1R = 0.0f;
            this.source1G = 0.0f;
            this.source1B = 0.0f;
            this.source1A = 0.0f;
            this.source2X = 0.0f;
            this.source2Y = 0.0f;
            this.source2Z = 0.0f;
            this.source2R = 0.0f;
            this.source2G = 0.0f;
            this.source2B = 0.0f;
            this.source2A = 0.0f;
            if (fArr.length < 21) {
                Log.w("MiuixWarning", "BloomStrokeConfig config.length error, must greater than 21!:" + Log.getStackTraceString(new Throwable()));
                return;
            }
            this.bloomStrokeWidth = fArr[0];
            this.bloomStrokeGradientDegree = fArr[1];
            this.bloomStrokeColorR = fArr[2];
            this.bloomStrokeColorG = fArr[3];
            this.bloomStrokeColorB = fArr[4];
            this.bloomStrokeColorA = fArr[5];
            this.normalWidth = fArr[6];
            this.source1X = fArr[7];
            this.source1Y = fArr[8];
            this.source1Z = fArr[9];
            this.source1R = fArr[10];
            this.source1G = fArr[11];
            this.source1B = fArr[12];
            this.source1A = fArr[13];
            this.source2X = fArr[14];
            this.source2Y = fArr[15];
            this.source2Z = fArr[16];
            this.source2R = fArr[17];
            this.source2G = fArr[18];
            this.source2B = fArr[19];
            this.source2A = fArr[20];
        }

        public BloomStrokeConfig(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22) {
            this.bloomStrokeWidth = f2;
            this.bloomStrokeGradientDegree = f3;
            this.bloomStrokeColorR = f4;
            this.bloomStrokeColorG = f5;
            this.bloomStrokeColorB = f6;
            this.bloomStrokeColorA = f7;
            this.normalWidth = f8;
            this.source1X = f9;
            this.source1Y = f10;
            this.source1Z = f11;
            this.source1R = f12;
            this.source1G = f13;
            this.source1B = f14;
            this.source1A = f15;
            this.source2X = f16;
            this.source2Y = f17;
            this.source2Z = f18;
            this.source2R = f19;
            this.source2G = f20;
            this.source2B = f21;
            this.source2A = f22;
        }
    }
}
