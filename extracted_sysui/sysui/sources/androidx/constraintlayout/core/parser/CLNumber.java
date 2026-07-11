package androidx.constraintlayout.core.parser;

/* JADX INFO: loaded from: classes.dex */
public class CLNumber extends CLElement {
    float mValue;

    public CLNumber(char[] cArr) {
        super(cArr);
        this.mValue = Float.NaN;
    }

    public static CLElement allocate(char[] cArr) {
        return new CLNumber(cArr);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CLNumber)) {
            return false;
        }
        float f2 = getFloat();
        float f3 = ((CLNumber) obj).getFloat();
        return (Float.isNaN(f2) && Float.isNaN(f3)) || f2 == f3;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public float getFloat() {
        if (Float.isNaN(this.mValue) && hasContent()) {
            this.mValue = Float.parseFloat(content());
        }
        return this.mValue;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public int getInt() {
        if (Float.isNaN(this.mValue) && hasContent()) {
            this.mValue = Integer.parseInt(content());
        }
        return (int) this.mValue;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        float f2 = this.mValue;
        return iHashCode + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public boolean isInt() {
        float f2 = getFloat();
        return ((float) ((int) f2)) == f2;
    }

    public void putValue(float f2) {
        this.mValue = f2;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        addIndent(sb, i2);
        float f2 = getFloat();
        int i4 = (int) f2;
        if (i4 == f2) {
            sb.append(i4);
        } else {
            sb.append(f2);
        }
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        float f2 = getFloat();
        int i2 = (int) f2;
        if (i2 == f2) {
            return "" + i2;
        }
        return "" + f2;
    }

    public CLNumber(float f2) {
        super(null);
        this.mValue = f2;
    }
}
