package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MotionKeyTrigger extends MotionKey {
    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    private static final String TAG = "KeyTrigger";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final int TYPE_CROSS = 312;
    public static final int TYPE_NEGATIVE_CROSS = 310;
    public static final int TYPE_POSITIVE_CROSS = 309;
    public static final int TYPE_POST_LAYOUT = 304;
    public static final int TYPE_TRIGGER_COLLISION_ID = 307;
    public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
    public static final int TYPE_TRIGGER_ID = 308;
    public static final int TYPE_TRIGGER_RECEIVER = 311;
    public static final int TYPE_TRIGGER_SLACK = 305;
    public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
    public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
    public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    FloatRect mCollisionRect;
    private boolean mFireCrossReset;
    private float mFireLastPos;
    private boolean mFireNegativeReset;
    private boolean mFirePositiveReset;
    private float mFireThreshold;
    private String mNegativeCross;
    private String mPositiveCross;
    private boolean mPostLayout;
    FloatRect mTargetRect;
    private int mTriggerCollisionId;
    private int mTriggerID;
    private int mTriggerReceiver;
    float mTriggerSlack;
    int mViewTransitionOnCross;
    int mViewTransitionOnNegativeCross;
    int mViewTransitionOnPositiveCross;
    private int mCurveFit = -1;
    private String mCross = null;

    public MotionKeyTrigger() {
        int i2 = MotionKey.UNSET;
        this.mTriggerReceiver = i2;
        this.mNegativeCross = null;
        this.mPositiveCross = null;
        this.mTriggerID = i2;
        this.mTriggerCollisionId = i2;
        this.mTriggerSlack = 0.1f;
        this.mFireCrossReset = true;
        this.mFireNegativeReset = true;
        this.mFirePositiveReset = true;
        this.mFireThreshold = Float.NaN;
        this.mPostLayout = false;
        this.mViewTransitionOnNegativeCross = i2;
        this.mViewTransitionOnPositiveCross = i2;
        this.mViewTransitionOnCross = i2;
        this.mCollisionRect = new FloatRect();
        this.mTargetRect = new FloatRect();
        this.mType = 5;
        this.mCustom = new HashMap<>();
    }

    private void fireCustom(String str, MotionWidget motionWidget) {
        boolean z2 = str.length() == 1;
        if (!z2) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.mCustom.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z2 || lowerCase.matches(str)) {
                CustomVariable customVariable = this.mCustom.get(str2);
                if (customVariable != null) {
                    customVariable.applyToWidget(motionWidget);
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap<String, SplineSet> map) {
    }

    public void conditionallyFire(float f2, MotionWidget motionWidget) {
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        str.hashCode();
        switch (str) {
            case "positiveCross":
                return 309;
            case "viewTransitionOnPositiveCross":
                return 302;
            case "triggerCollisionId":
                return 307;
            case "triggerID":
                return 308;
            case "negativeCross":
                return 310;
            case "triggerCollisionView":
                return 306;
            case "viewTransitionOnNegativeCross":
                return 303;
            case "triggerSlack":
                return 305;
            case "viewTransitionOnCross":
                return 301;
            case "postLayout":
                return 304;
            case "triggerReceiver":
                return 311;
            default:
                return -1;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, int i3) {
        if (i2 == 307) {
            this.mTriggerCollisionId = i3;
            return true;
        }
        if (i2 == 308) {
            this.mTriggerID = toInt(Integer.valueOf(i3));
            return true;
        }
        if (i2 == 311) {
            this.mTriggerReceiver = i3;
            return true;
        }
        switch (i2) {
            case 301:
                this.mViewTransitionOnCross = i3;
                return true;
            case 302:
                this.mViewTransitionOnPositiveCross = i3;
                return true;
            case 303:
                this.mViewTransitionOnNegativeCross = i3;
                return true;
            default:
                return super.setValue(i2, i3);
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* JADX INFO: renamed from: clone */
    public MotionKey mo33clone() {
        return new MotionKeyTrigger().copy((MotionKey) this);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKeyTrigger copy(MotionKey motionKey) {
        super.copy(motionKey);
        MotionKeyTrigger motionKeyTrigger = (MotionKeyTrigger) motionKey;
        this.mCurveFit = motionKeyTrigger.mCurveFit;
        this.mCross = motionKeyTrigger.mCross;
        this.mTriggerReceiver = motionKeyTrigger.mTriggerReceiver;
        this.mNegativeCross = motionKeyTrigger.mNegativeCross;
        this.mPositiveCross = motionKeyTrigger.mPositiveCross;
        this.mTriggerID = motionKeyTrigger.mTriggerID;
        this.mTriggerCollisionId = motionKeyTrigger.mTriggerCollisionId;
        this.mTriggerSlack = motionKeyTrigger.mTriggerSlack;
        this.mFireCrossReset = motionKeyTrigger.mFireCrossReset;
        this.mFireNegativeReset = motionKeyTrigger.mFireNegativeReset;
        this.mFirePositiveReset = motionKeyTrigger.mFirePositiveReset;
        this.mFireThreshold = motionKeyTrigger.mFireThreshold;
        this.mFireLastPos = motionKeyTrigger.mFireLastPos;
        this.mPostLayout = motionKeyTrigger.mPostLayout;
        this.mCollisionRect = motionKeyTrigger.mCollisionRect;
        this.mTargetRect = motionKeyTrigger.mTargetRect;
        return this;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, float f2) {
        if (i2 != 305) {
            return super.setValue(i2, f2);
        }
        this.mTriggerSlack = f2;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, String str) {
        if (i2 == 309) {
            this.mPositiveCross = str;
            return true;
        }
        if (i2 == 310) {
            this.mNegativeCross = str;
            return true;
        }
        if (i2 != 312) {
            return super.setValue(i2, str);
        }
        this.mCross = str;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, boolean z2) {
        if (i2 != 304) {
            return super.setValue(i2, z2);
        }
        this.mPostLayout = z2;
        return true;
    }
}
