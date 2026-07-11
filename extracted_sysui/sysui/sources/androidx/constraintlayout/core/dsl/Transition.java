package androidx.constraintlayout.core.dsl;

import miuix.appcompat.internal.app.widget.SearchActionModeView;

/* JADX INFO: loaded from: classes.dex */
public class Transition {
    private String mConstraintSetEnd;
    private String mConstraintSetStart;
    private String mId;
    private OnSwipe mOnSwipe = null;
    final int UNSET = -1;
    private final int DEFAULT_DURATION = SearchActionModeView.ANIMATION_DURATION;
    private final float DEFAULT_STAGGER = 0.0f;
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private int mDefaultInterpolatorID = -1;
    private int mDuration = SearchActionModeView.ANIMATION_DURATION;
    private float mStagger = 0.0f;
    private KeyFrames mKeyFrames = new KeyFrames();

    public Transition(String str, String str2) {
        this.mId = null;
        this.mConstraintSetEnd = null;
        this.mConstraintSetStart = null;
        this.mId = "default";
        this.mConstraintSetStart = str;
        this.mConstraintSetEnd = str2;
    }

    public String getId() {
        return this.mId;
    }

    public void setDuration(int i2) {
        this.mDuration = i2;
    }

    public void setFrom(String str) {
        this.mConstraintSetStart = str;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setKeyFrames(Keys keys) {
        this.mKeyFrames.add(keys);
    }

    public void setOnSwipe(OnSwipe onSwipe) {
        this.mOnSwipe = onSwipe;
    }

    public void setStagger(float f2) {
        this.mStagger = f2;
    }

    public void setTo(String str) {
        this.mConstraintSetEnd = str;
    }

    public String toJson() {
        return toString();
    }

    public String toString() {
        String str = this.mId + ":{\nfrom:'" + this.mConstraintSetStart + "',\nto:'" + this.mConstraintSetEnd + "',\n";
        if (this.mDuration != 400) {
            str = str + "duration:" + this.mDuration + ",\n";
        }
        if (this.mStagger != 0.0f) {
            str = str + "stagger:" + this.mStagger + ",\n";
        }
        if (this.mOnSwipe != null) {
            str = str + this.mOnSwipe.toString();
        }
        return (str + this.mKeyFrames.toString()) + "},\n";
    }

    public Transition(String str, String str2, String str3) {
        this.mId = null;
        this.mConstraintSetEnd = null;
        this.mConstraintSetStart = null;
        this.mId = str;
        this.mConstraintSetStart = str2;
        this.mConstraintSetEnd = str3;
    }
}
