package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class VerticalChainReference extends ChainReference {

    /* JADX INFO: renamed from: androidx.constraintlayout.core.state.helpers.VerticalChainReference$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$state$State$Chain;

        static {
            int[] iArr = new int[State.Chain.values().length];
            $SwitchMap$androidx$constraintlayout$core$state$State$Chain = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Chain[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Chain[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public VerticalChainReference(State state) {
        super(state, State.Helper.VERTICAL_CHAIN);
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mHelperState.constraints(it.next()).clearVertical();
        }
        ConstraintReference constraintReference = null;
        ConstraintReference constraintReference2 = null;
        for (Object obj : this.mReferences) {
            ConstraintReference constraintReferenceConstraints = this.mHelperState.constraints(obj);
            if (constraintReference2 == null) {
                Object obj2 = this.mTopToTop;
                if (obj2 != null) {
                    constraintReferenceConstraints.topToTop(obj2).margin(this.mMarginTop).marginGone(this.mMarginTopGone);
                } else {
                    Object obj3 = this.mTopToBottom;
                    if (obj3 != null) {
                        constraintReferenceConstraints.topToBottom(obj3).margin(this.mMarginTop).marginGone(this.mMarginTopGone);
                    } else {
                        String string = constraintReferenceConstraints.getKey().toString();
                        constraintReferenceConstraints.topToTop(State.PARENT).margin(Float.valueOf(getPreMargin(string))).marginGone(Float.valueOf(getPreGoneMargin(string)));
                    }
                }
                constraintReference2 = constraintReferenceConstraints;
            }
            if (constraintReference != null) {
                String string2 = constraintReference.getKey().toString();
                String string3 = constraintReferenceConstraints.getKey().toString();
                constraintReference.bottomToTop(constraintReferenceConstraints.getKey()).margin(Float.valueOf(getPostMargin(string2))).marginGone(Float.valueOf(getPostGoneMargin(string2)));
                constraintReferenceConstraints.topToBottom(constraintReference.getKey()).margin(Float.valueOf(getPreMargin(string3))).marginGone(Float.valueOf(getPreGoneMargin(string3)));
            }
            float weight = getWeight(obj.toString());
            if (weight != -1.0f) {
                constraintReferenceConstraints.setVerticalChainWeight(weight);
            }
            constraintReference = constraintReferenceConstraints;
        }
        if (constraintReference != null) {
            Object obj4 = this.mBottomToTop;
            if (obj4 != null) {
                constraintReference.bottomToTop(obj4).margin(this.mMarginBottom).marginGone(this.mMarginBottomGone);
            } else {
                Object obj5 = this.mBottomToBottom;
                if (obj5 != null) {
                    constraintReference.bottomToBottom(obj5).margin(this.mMarginBottom).marginGone(this.mMarginBottomGone);
                } else {
                    String string4 = constraintReference.getKey().toString();
                    constraintReference.bottomToBottom(State.PARENT).margin(Float.valueOf(getPostMargin(string4))).marginGone(Float.valueOf(getPostGoneMargin(string4)));
                }
            }
        }
        if (constraintReference2 == null) {
            return;
        }
        float f2 = this.mBias;
        if (f2 != 0.5f) {
            constraintReference2.verticalBias(f2);
        }
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$state$State$Chain[this.mStyle.ordinal()];
        if (i2 == 1) {
            constraintReference2.setVerticalChainStyle(0);
        } else if (i2 == 2) {
            constraintReference2.setVerticalChainStyle(1);
        } else {
            if (i2 != 3) {
                return;
            }
            constraintReference2.setVerticalChainStyle(2);
        }
    }
}
