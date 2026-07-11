package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.dsl.Helper;

/* JADX INFO: loaded from: classes.dex */
public abstract class Guideline extends Helper {
    private int mEnd;
    private float mPercent;
    private int mStart;

    public Guideline(String str) {
        super(str, new Helper.HelperType(""));
        this.mStart = Integer.MIN_VALUE;
        this.mEnd = Integer.MIN_VALUE;
        this.mPercent = Float.NaN;
    }

    public int getEnd() {
        return this.mEnd;
    }

    public float getPercent() {
        return this.mPercent;
    }

    public int getStart() {
        return this.mStart;
    }

    public void setEnd(int i2) {
        this.mEnd = i2;
        this.configMap.put("end", String.valueOf(i2));
    }

    public void setPercent(float f2) {
        this.mPercent = f2;
        this.configMap.put("percent", String.valueOf(f2));
    }

    public void setStart(int i2) {
        this.mStart = i2;
        this.configMap.put("start", String.valueOf(i2));
    }
}
