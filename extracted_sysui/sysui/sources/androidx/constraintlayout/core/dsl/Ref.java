package androidx.constraintlayout.core.dsl;

import com.xiaomi.onetrack.util.aa;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Ref {
    private String mId;
    private float mPostMargin;
    private float mPreMargin;
    private float mWeight;

    public Ref(String str) {
        this.mWeight = Float.NaN;
        this.mPreMargin = Float.NaN;
        this.mPostMargin = Float.NaN;
        this.mId = str;
    }

    public static void addStringToReferences(String str, ArrayList<Ref> arrayList) {
        Object obj;
        if (str == null || str.length() == 0) {
            return;
        }
        Object[] objArr = new Object[4];
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < str.length(); i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt != ' ' && cCharAt != '\'') {
                if (cCharAt == ',') {
                    if (i2 < 3) {
                        objArr[i2] = sb.toString();
                        sb.setLength(0);
                        i2++;
                    }
                    if (i3 == 1 && (obj = objArr[0]) != null) {
                        arrayList.add(new Ref(obj.toString()));
                        objArr[0] = null;
                        i2 = 0;
                    }
                } else if (cCharAt == '[') {
                    i3++;
                } else if (cCharAt != ']') {
                    sb.append(cCharAt);
                } else if (i3 > 0) {
                    i3--;
                    objArr[i2] = sb.toString();
                    sb.setLength(0);
                    Object obj2 = objArr[0];
                    if (obj2 != null) {
                        arrayList.add(new Ref(obj2.toString(), parseFloat(objArr[1]), parseFloat(objArr[2]), parseFloat(objArr[3])));
                        Arrays.fill(objArr, (Object) null);
                        i2 = 0;
                    }
                }
            }
        }
    }

    public static float parseFloat(Object obj) {
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception unused) {
            return Float.NaN;
        }
    }

    public static Ref parseStringToRef(String str) {
        String[] strArrSplit = str.replaceAll("[\\[\\]\\']", "").split(aa.f3429b);
        if (strArrSplit.length == 0) {
            return null;
        }
        Object[] objArr = new Object[4];
        for (int i2 = 0; i2 < strArrSplit.length && i2 < 4; i2++) {
            objArr[i2] = strArrSplit[i2];
        }
        return new Ref(objArr[0].toString().replace("'", ""), parseFloat(objArr[1]), parseFloat(objArr[2]), parseFloat(objArr[3]));
    }

    public String getId() {
        return this.mId;
    }

    public float getPostMargin() {
        return this.mPostMargin;
    }

    public float getPreMargin() {
        return this.mPreMargin;
    }

    public float getWeight() {
        return this.mWeight;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setPostMargin(float f2) {
        this.mPostMargin = f2;
    }

    public void setPreMargin(float f2) {
        this.mPreMargin = f2;
    }

    public void setWeight(float f2) {
        this.mWeight = f2;
    }

    public String toString() {
        String str = this.mId;
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = (Float.isNaN(this.mWeight) && Float.isNaN(this.mPreMargin) && Float.isNaN(this.mPostMargin)) ? false : true;
        if (z2) {
            sb.append("[");
        }
        sb.append("'");
        sb.append(this.mId);
        sb.append("'");
        if (!Float.isNaN(this.mPostMargin)) {
            sb.append(aa.f3429b);
            sb.append(!Float.isNaN(this.mWeight) ? this.mWeight : 0.0f);
            sb.append(aa.f3429b);
            sb.append(Float.isNaN(this.mPreMargin) ? 0.0f : this.mPreMargin);
            sb.append(aa.f3429b);
            sb.append(this.mPostMargin);
        } else if (!Float.isNaN(this.mPreMargin)) {
            sb.append(aa.f3429b);
            sb.append(Float.isNaN(this.mWeight) ? 0.0f : this.mWeight);
            sb.append(aa.f3429b);
            sb.append(this.mPreMargin);
        } else if (!Float.isNaN(this.mWeight)) {
            sb.append(aa.f3429b);
            sb.append(this.mWeight);
        }
        if (z2) {
            sb.append("]");
        }
        sb.append(aa.f3429b);
        return sb.toString();
    }

    public Ref(String str, float f2) {
        this.mPreMargin = Float.NaN;
        this.mPostMargin = Float.NaN;
        this.mId = str;
        this.mWeight = f2;
    }

    public Ref(String str, float f2, float f3) {
        this.mPostMargin = Float.NaN;
        this.mId = str;
        this.mWeight = f2;
        this.mPreMargin = f3;
    }

    public Ref(String str, float f2, float f3, float f4) {
        this.mId = str;
        this.mWeight = f2;
        this.mPreMargin = f3;
        this.mPostMargin = f4;
    }
}
