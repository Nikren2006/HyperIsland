package miuix.theme.token;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class MaterialToken implements Parcelable {
    public static final Parcelable.Creator<MaterialToken> CREATOR = new Parcelable.Creator<MaterialToken>() { // from class: miuix.theme.token.MaterialToken.1
        @Override // android.os.Parcelable.Creator
        public MaterialToken createFromParcel(Parcel parcel) {
            return new MaterialToken(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MaterialToken[] newArray(int i2) {
            return new MaterialToken[i2];
        }
    };
    public int[] blendColors;
    public float[] blendExtraParams;
    public int[] blendModes;
    public float bloomStrokeColorA;
    public float bloomStrokeColorB;
    public float bloomStrokeColorG;
    public float bloomStrokeColorR;
    public float bloomStrokeGradientDegree;
    public float bloomStrokeWidth;
    public int blurContainerMode;
    public int blurElementMode;
    public float[] blurExtraParams;
    public int blurRadius;
    public int blurSubType;
    public int blurType;
    public int enableBloomStroke;
    public int enableBlur;
    public int enableColorBlend;
    public int enableShadow;
    public int[] fallbackBlendColors;
    public float[] fallbackBlendExtraParams;
    public int[] fallbackBlendModes;
    public int hasFallbackColorBlend;
    public float normalWidth;
    public int shadowColor;
    public float shadowDispersion;
    public float shadowOffsetX;
    public float shadowOffsetY;
    public float shadowRadius;
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
    public String token;
    public String tokenVariant;
    public final int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        if (TextUtils.isEmpty(this.token)) {
            return "{no token name}@" + Integer.toHexString(hashCode());
        }
        if (TextUtils.isEmpty(this.tokenVariant)) {
            return this.token;
        }
        return this.token + "_" + this.tokenVariant;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int[] iArr;
        int[] iArr2;
        parcel.writeInt(this.version);
        parcel.writeString(TextUtils.isEmpty(this.token) ? "" : this.token);
        parcel.writeString(TextUtils.isEmpty(this.tokenVariant) ? "" : this.tokenVariant);
        parcel.writeInt(this.enableColorBlend);
        if (this.enableColorBlend > 0) {
            int[] iArr3 = this.blendColors;
            if (iArr3 == null || iArr3.length <= 0 || (iArr2 = this.blendModes) == null || iArr2.length <= 0) {
                parcel.writeInt(0);
                parcel.writeInt(0);
                parcel.writeInt(0);
            } else {
                parcel.writeInt(iArr3.length);
                parcel.writeIntArray(this.blendColors);
                parcel.writeInt(this.blendModes.length);
                parcel.writeIntArray(this.blendModes);
                float[] fArr = this.blendExtraParams;
                if (fArr != null) {
                    parcel.writeInt(fArr.length);
                    float[] fArr2 = this.blendExtraParams;
                    if (fArr2.length > 0) {
                        parcel.writeFloatArray(fArr2);
                    }
                } else {
                    parcel.writeInt(0);
                }
            }
            parcel.writeInt(this.hasFallbackColorBlend);
            if (this.hasFallbackColorBlend > 0) {
                int[] iArr4 = this.fallbackBlendColors;
                if (iArr4 == null || iArr4.length <= 0 || (iArr = this.fallbackBlendModes) == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                    parcel.writeInt(0);
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr4.length);
                    parcel.writeIntArray(this.fallbackBlendColors);
                    parcel.writeInt(this.fallbackBlendModes.length);
                    parcel.writeIntArray(this.fallbackBlendModes);
                    float[] fArr3 = this.fallbackBlendExtraParams;
                    if (fArr3 != null) {
                        parcel.writeInt(fArr3.length);
                        float[] fArr4 = this.fallbackBlendExtraParams;
                        if (fArr4.length > 0) {
                            parcel.writeFloatArray(fArr4);
                        }
                    } else {
                        parcel.writeInt(0);
                    }
                }
            }
        }
        parcel.writeInt(this.enableBlur);
        if (this.enableBlur > 0) {
            parcel.writeInt(this.blurContainerMode);
            parcel.writeInt(this.blurElementMode);
            parcel.writeInt(this.blurType);
            parcel.writeInt(this.blurRadius);
            parcel.writeInt(this.blurSubType);
            float[] fArr5 = this.blurExtraParams;
            if (fArr5 != null) {
                parcel.writeInt(fArr5.length);
                float[] fArr6 = this.blurExtraParams;
                if (fArr6.length > 0) {
                    parcel.writeFloatArray(fArr6);
                }
            } else {
                parcel.writeInt(0);
            }
        }
        parcel.writeInt(this.enableShadow);
        if (this.enableShadow > 0) {
            parcel.writeInt(this.shadowColor);
            parcel.writeFloat(this.shadowOffsetX);
            parcel.writeFloat(this.shadowOffsetY);
            parcel.writeFloat(this.shadowRadius);
            parcel.writeFloat(this.shadowDispersion);
        }
        parcel.writeInt(this.enableBloomStroke);
        if (this.enableBloomStroke > 0) {
            parcel.writeFloat(this.bloomStrokeWidth);
            parcel.writeFloat(this.bloomStrokeGradientDegree);
            parcel.writeFloat(this.bloomStrokeColorR);
            parcel.writeFloat(this.bloomStrokeColorG);
            parcel.writeFloat(this.bloomStrokeColorB);
            parcel.writeFloat(this.bloomStrokeColorA);
            parcel.writeFloat(this.normalWidth);
            parcel.writeFloat(this.source1X);
            parcel.writeFloat(this.source1Y);
            parcel.writeFloat(this.source1Z);
            parcel.writeFloat(this.source1R);
            parcel.writeFloat(this.source1G);
            parcel.writeFloat(this.source1B);
            parcel.writeFloat(this.source1A);
            parcel.writeFloat(this.source2X);
            parcel.writeFloat(this.source2Y);
            parcel.writeFloat(this.source2Z);
            parcel.writeFloat(this.source2R);
            parcel.writeFloat(this.source2G);
            parcel.writeFloat(this.source2B);
            parcel.writeFloat(this.source2A);
        }
    }

    public static class Builder {
        private MaterialToken mToken;

        public Builder(int i2) {
            this.mToken = new MaterialToken(i2);
        }

        public MaterialToken build() {
            return this.mToken;
        }

        public Builder setBloomStroke(float[] fArr) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBloomStroke = 1;
            materialToken.bloomStrokeWidth = fArr[0];
            materialToken.bloomStrokeGradientDegree = fArr[1];
            materialToken.bloomStrokeColorR = fArr[2];
            materialToken.bloomStrokeColorG = fArr[3];
            materialToken.bloomStrokeColorB = fArr[4];
            materialToken.bloomStrokeColorA = fArr[5];
            materialToken.normalWidth = fArr[6];
            materialToken.source1X = fArr[7];
            materialToken.source1Y = fArr[8];
            materialToken.source1Z = fArr[9];
            materialToken.source1R = fArr[10];
            materialToken.source1G = fArr[11];
            materialToken.source1B = fArr[12];
            materialToken.source1A = fArr[13];
            materialToken.source2X = fArr[14];
            materialToken.source2Y = fArr[15];
            materialToken.source2Z = fArr[16];
            materialToken.source2R = fArr[17];
            materialToken.source2G = fArr[18];
            materialToken.source2B = fArr[19];
            materialToken.source2A = fArr[20];
            return this;
        }

        public Builder setBlur(int i2, int i3, int i4, int i5) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBlur = 1;
            materialToken.blurContainerMode = i2;
            materialToken.blurElementMode = i3;
            materialToken.blurType = i4;
            materialToken.blurRadius = i5;
            return this;
        }

        public Builder setColorBlend(int[] iArr) {
            if (iArr != null && iArr.length >= 2) {
                MaterialToken materialToken = this.mToken;
                materialToken.enableColorBlend = 1;
                materialToken.blendColors = new int[iArr.length / 2];
                materialToken.blendModes = new int[iArr.length / 2];
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (i4 % 2 == 0) {
                        this.mToken.blendColors[i3] = iArr[i4];
                        i3++;
                    } else {
                        this.mToken.blendModes[i2] = iArr[i4];
                        i2++;
                    }
                }
            }
            return this;
        }

        public Builder setContainerBlur(int i2, int i3) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBlur = 1;
            materialToken.blurContainerMode = i3;
            materialToken.blurElementMode = 0;
            materialToken.blurType = 0;
            materialToken.blurRadius = i2;
            return this;
        }

        public Builder setElementBlur() {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBlur = 1;
            materialToken.blurContainerMode = 0;
            materialToken.blurElementMode = 1;
            materialToken.blurType = 0;
            materialToken.blurRadius = -1;
            return this;
        }

        public Builder setMaskBlur(int i2) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBlur = 1;
            materialToken.blurContainerMode = 1;
            materialToken.blurElementMode = 1;
            materialToken.blurType = 0;
            materialToken.blurRadius = i2;
            return this;
        }

        public Builder setShadow(ShadowToken shadowToken) {
            if (shadowToken == null) {
                return this;
            }
            MaterialToken materialToken = this.mToken;
            materialToken.enableShadow = 1;
            materialToken.shadowColor = shadowToken.color;
            materialToken.shadowOffsetX = shadowToken.offsetX;
            materialToken.shadowOffsetY = shadowToken.offsetY;
            materialToken.shadowRadius = shadowToken.radius;
            materialToken.shadowDispersion = shadowToken.dispersion;
            return this;
        }

        public Builder setTokenInfo(String str, String str2) {
            MaterialToken materialToken = this.mToken;
            if (str == null) {
                str = "";
            }
            materialToken.token = str;
            if (str2 == null) {
                str2 = "";
            }
            materialToken.tokenVariant = str2;
            return this;
        }

        public Builder(int i2, String str, String str2) {
            this.mToken = new MaterialToken(i2);
            setTokenInfo(str, str2);
        }

        public Builder setElementBlur(int i2) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBlur = 1;
            materialToken.blurContainerMode = 0;
            materialToken.blurElementMode = 1;
            materialToken.blurType = 0;
            materialToken.blurRadius = i2;
            return this;
        }

        public Builder setShadow(int i2, float f2, float f3, float f4, float f5) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableShadow = 1;
            materialToken.shadowColor = i2;
            materialToken.shadowOffsetX = f2;
            materialToken.shadowOffsetY = f3;
            materialToken.shadowRadius = f4;
            materialToken.shadowDispersion = f5;
            return this;
        }

        public Builder setColorBlend(int[] iArr, int[] iArr2) {
            if (iArr != null && iArr.length >= 2) {
                MaterialToken materialToken = this.mToken;
                materialToken.enableColorBlend = 1;
                materialToken.blendColors = new int[iArr.length / 2];
                materialToken.blendModes = new int[iArr.length / 2];
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (i4 % 2 == 0) {
                        this.mToken.blendColors[i3] = iArr[i4];
                        i3++;
                    } else {
                        this.mToken.blendModes[i2] = iArr[i4];
                        i2++;
                    }
                }
                if (iArr2 != null && iArr2.length >= 2) {
                    MaterialToken materialToken2 = this.mToken;
                    materialToken2.hasFallbackColorBlend = 1;
                    materialToken2.fallbackBlendColors = new int[iArr2.length / 2];
                    materialToken2.fallbackBlendModes = new int[iArr2.length / 2];
                    int i5 = 0;
                    int i6 = 0;
                    for (int i7 = 0; i7 < iArr2.length; i7++) {
                        if (i7 % 2 == 0) {
                            this.mToken.fallbackBlendColors[i6] = iArr2[i7];
                            i6++;
                        } else {
                            this.mToken.fallbackBlendModes[i5] = iArr2[i7];
                            i5++;
                        }
                    }
                }
            }
            return this;
        }

        public Builder setBloomStroke(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22) {
            MaterialToken materialToken = this.mToken;
            materialToken.enableBloomStroke = 1;
            materialToken.bloomStrokeWidth = f2;
            materialToken.bloomStrokeColorR = f4;
            materialToken.bloomStrokeColorG = f5;
            materialToken.bloomStrokeColorB = f6;
            materialToken.bloomStrokeColorA = f7;
            materialToken.bloomStrokeGradientDegree = f3;
            materialToken.normalWidth = f8;
            materialToken.source1X = f9;
            materialToken.source1Y = f10;
            materialToken.source1Z = f11;
            materialToken.source1R = f12;
            materialToken.source1G = f13;
            materialToken.source1B = f14;
            materialToken.source1A = f15;
            materialToken.source2X = f16;
            materialToken.source2Y = f17;
            materialToken.source2Z = f18;
            materialToken.source2R = f19;
            materialToken.source2G = f20;
            materialToken.source2B = f21;
            materialToken.source2A = f22;
            return this;
        }

        public Builder setColorBlend(ArrayList<Point> arrayList) {
            return setColorBlend(arrayList, (ArrayList<Point>) null);
        }

        public Builder setColorBlend(ArrayList<Point> arrayList, ArrayList<Point> arrayList2) {
            if (arrayList == null) {
                return this;
            }
            MaterialToken materialToken = this.mToken;
            materialToken.enableColorBlend = 1;
            materialToken.blendColors = new int[arrayList.size()];
            this.mToken.blendModes = new int[arrayList.size()];
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                Point point = arrayList.get(i4);
                MaterialToken materialToken2 = this.mToken;
                materialToken2.blendColors[i2] = point.x;
                materialToken2.blendModes[i3] = point.y;
                i2++;
                i3++;
            }
            if (arrayList2 != null) {
                MaterialToken materialToken3 = this.mToken;
                materialToken3.hasFallbackColorBlend = 1;
                materialToken3.fallbackBlendColors = new int[arrayList2.size()];
                this.mToken.fallbackBlendModes = new int[arrayList2.size()];
                int i5 = 0;
                int i6 = 0;
                for (int i7 = 0; i7 < arrayList2.size(); i7++) {
                    Point point2 = arrayList2.get(i7);
                    MaterialToken materialToken4 = this.mToken;
                    materialToken4.fallbackBlendColors[i5] = point2.x;
                    materialToken4.fallbackBlendModes[i6] = point2.y;
                    i5++;
                    i6++;
                }
            }
            return this;
        }

        public Builder setColorBlend(ColorBlendToken colorBlendToken) {
            if (colorBlendToken == null) {
                return this;
            }
            MaterialToken materialToken = this.mToken;
            materialToken.enableColorBlend = 1;
            materialToken.blendColors = colorBlendToken.colors;
            materialToken.blendModes = colorBlendToken.blendModes;
            materialToken.blendExtraParams = colorBlendToken.extraBlendParams;
            int[] iArr = colorBlendToken.fallbackColors;
            if (iArr != null) {
                materialToken.hasFallbackColorBlend = 1;
                materialToken.fallbackBlendColors = iArr;
                materialToken.fallbackBlendModes = colorBlendToken.fallbackBlendModes;
                materialToken.fallbackBlendExtraParams = colorBlendToken.fallbackExtraBlendParams;
            } else {
                materialToken.hasFallbackColorBlend = 0;
            }
            return this;
        }
    }

    public MaterialToken(Parcel parcel) {
        this.enableColorBlend = 0;
        this.hasFallbackColorBlend = 0;
        this.enableBlur = 0;
        this.enableShadow = 0;
        this.shadowOffsetX = 0.0f;
        this.shadowOffsetY = 0.0f;
        this.shadowRadius = 0.0f;
        this.shadowDispersion = 1.0f;
        this.enableBloomStroke = 0;
        this.bloomStrokeWidth = 0.0f;
        this.bloomStrokeGradientDegree = 0.0f;
        this.bloomStrokeColorR = 0.0f;
        this.bloomStrokeColorG = 0.0f;
        this.bloomStrokeColorB = 0.0f;
        this.bloomStrokeColorA = 0.0f;
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
        this.version = parcel.readInt();
        this.token = parcel.readString();
        this.tokenVariant = parcel.readString();
        int i2 = parcel.readInt();
        this.enableColorBlend = i2;
        if (i2 > 0) {
            int i3 = parcel.readInt();
            if (i3 > 0) {
                int[] iArr = new int[i3];
                this.blendColors = iArr;
                parcel.readIntArray(iArr);
            }
            int i4 = parcel.readInt();
            if (i4 > 0) {
                int[] iArr2 = new int[i4];
                this.blendModes = iArr2;
                parcel.readIntArray(iArr2);
            }
            int i5 = parcel.readInt();
            if (i5 > 0) {
                float[] fArr = new float[i5];
                this.blendExtraParams = fArr;
                parcel.readFloatArray(fArr);
            }
            int i6 = parcel.readInt();
            this.hasFallbackColorBlend = i6;
            if (i6 > 0) {
                int i7 = parcel.readInt();
                if (i7 > 0) {
                    int[] iArr3 = new int[i7];
                    this.fallbackBlendColors = iArr3;
                    parcel.readIntArray(iArr3);
                }
                int i8 = parcel.readInt();
                if (i8 > 0) {
                    int[] iArr4 = new int[i8];
                    this.fallbackBlendModes = iArr4;
                    parcel.readIntArray(iArr4);
                }
                int i9 = parcel.readInt();
                if (i9 > 0) {
                    float[] fArr2 = new float[i9];
                    this.fallbackBlendExtraParams = fArr2;
                    parcel.readFloatArray(fArr2);
                }
            }
        }
        int i10 = parcel.readInt();
        this.enableBlur = i10;
        if (i10 > 0) {
            this.blurContainerMode = parcel.readInt();
            this.blurElementMode = parcel.readInt();
            this.blurType = parcel.readInt();
            this.blurRadius = parcel.readInt();
            this.blurSubType = parcel.readInt();
            int i11 = parcel.readInt();
            if (i11 > 0) {
                float[] fArr3 = new float[i11];
                this.blurExtraParams = fArr3;
                parcel.readFloatArray(fArr3);
            } else {
                this.blurExtraParams = null;
            }
        }
        int i12 = parcel.readInt();
        this.enableShadow = i12;
        if (i12 > 0) {
            this.shadowColor = parcel.readInt();
            this.shadowOffsetX = parcel.readFloat();
            this.shadowOffsetY = parcel.readFloat();
            this.shadowRadius = parcel.readFloat();
            this.shadowDispersion = parcel.readFloat();
        }
        int i13 = parcel.readInt();
        this.enableBloomStroke = i13;
        if (i13 > 0) {
            this.bloomStrokeWidth = parcel.readFloat();
            this.bloomStrokeGradientDegree = parcel.readFloat();
            this.bloomStrokeColorR = parcel.readFloat();
            this.bloomStrokeColorG = parcel.readFloat();
            this.bloomStrokeColorB = parcel.readFloat();
            this.bloomStrokeColorA = parcel.readFloat();
            this.normalWidth = parcel.readFloat();
            this.source1X = parcel.readFloat();
            this.source1Y = parcel.readFloat();
            this.source1Z = parcel.readFloat();
            this.source1R = parcel.readFloat();
            this.source1G = parcel.readFloat();
            this.source1B = parcel.readFloat();
            this.source1A = parcel.readFloat();
            this.source2X = parcel.readFloat();
            this.source2Y = parcel.readFloat();
            this.source2Z = parcel.readFloat();
            this.source2R = parcel.readFloat();
            this.source2G = parcel.readFloat();
            this.source2B = parcel.readFloat();
            this.source2A = parcel.readFloat();
        }
    }

    private MaterialToken(int i2) {
        this.enableColorBlend = 0;
        this.hasFallbackColorBlend = 0;
        this.enableBlur = 0;
        this.enableShadow = 0;
        this.shadowOffsetX = 0.0f;
        this.shadowOffsetY = 0.0f;
        this.shadowRadius = 0.0f;
        this.shadowDispersion = 1.0f;
        this.enableBloomStroke = 0;
        this.bloomStrokeWidth = 0.0f;
        this.bloomStrokeGradientDegree = 0.0f;
        this.bloomStrokeColorR = 0.0f;
        this.bloomStrokeColorG = 0.0f;
        this.bloomStrokeColorB = 0.0f;
        this.bloomStrokeColorA = 0.0f;
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
        this.version = i2;
    }
}
