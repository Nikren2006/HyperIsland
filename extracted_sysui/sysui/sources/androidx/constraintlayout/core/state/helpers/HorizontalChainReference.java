package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalChainReference extends ChainReference {

    /* JADX INFO: renamed from: androidx.constraintlayout.core.state.helpers.HorizontalChainReference$1, reason: invalid class name */
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

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mHelperState.constraints(it.next()).clearHorizontal();
        }
        ConstraintReference constraintReference = null;
        ConstraintReference constraintReference2 = null;
        for (Object obj : this.mReferences) {
            ConstraintReference constraintReferenceConstraints = this.mHelperState.constraints(obj);
            if (constraintReference2 == null) {
                Object obj2 = this.mStartToStart;
                if (obj2 != null) {
                    constraintReferenceConstraints.startToStart(obj2).margin(this.mMarginStart).marginGone(this.mMarginStartGone);
                } else {
                    Object obj3 = this.mStartToEnd;
                    if (obj3 != null) {
                        constraintReferenceConstraints.startToEnd(obj3).margin(this.mMarginStart).marginGone(this.mMarginStartGone);
                    } else {
                        Object obj4 = this.mLeftToLeft;
                        if (obj4 != null) {
                            constraintReferenceConstraints.startToStart(obj4).margin(this.mMarginLeft).marginGone(this.mMarginLeftGone);
                        } else {
                            Object obj5 = this.mLeftToRight;
                            if (obj5 != null) {
                                constraintReferenceConstraints.startToEnd(obj5).margin(this.mMarginLeft).marginGone(this.mMarginLeftGone);
                            } else {
                                String string = constraintReferenceConstraints.getKey().toString();
                                constraintReferenceConstraints.startToStart(State.PARENT).margin(Float.valueOf(getPreMargin(string))).marginGone(Float.valueOf(getPreGoneMargin(string)));
                            }
                        }
                    }
                }
                constraintReference2 = constraintReferenceConstraints;
            }
            if (constraintReference != null) {
                String string2 = constraintReference.getKey().toString();
                String string3 = constraintReferenceConstraints.getKey().toString();
                constraintReference.endToStart(constraintReferenceConstraints.getKey()).margin(Float.valueOf(getPostMargin(string2))).marginGone(Float.valueOf(getPostGoneMargin(string2)));
                constraintReferenceConstraints.startToEnd(constraintReference.getKey()).margin(Float.valueOf(getPreMargin(string3))).marginGone(Float.valueOf(getPreGoneMargin(string3)));
            }
            float weight = getWeight(obj.toString());
            if (weight != -1.0f) {
                constraintReferenceConstraints.setHorizontalChainWeight(weight);
            }
            constraintReference = constraintReferenceConstraints;
        }
        if (constraintReference != null) {
            Object obj6 = this.mEndToStart;
            if (obj6 != null) {
                constraintReference.endToStart(obj6).margin(this.mMarginEnd).marginGone(this.mMarginEndGone);
            } else {
                Object obj7 = this.mEndToEnd;
                if (obj7 != null) {
                    constraintReference.endToEnd(obj7).margin(this.mMarginEnd).marginGone(this.mMarginEndGone);
                } else {
                    Object obj8 = this.mRightToLeft;
                    if (obj8 != null) {
                        constraintReference.endToStart(obj8).margin(this.mMarginRight).marginGone(this.mMarginRightGone);
                    } else {
                        Object obj9 = this.mRightToRight;
                        if (obj9 != null) {
                            constraintReference.endToEnd(obj9).margin(this.mMarginRight).marginGone(this.mMarginRightGone);
                        } else {
                            String string4 = constraintReference.getKey().toString();
                            constraintReference.endToEnd(State.PARENT).margin(Float.valueOf(getPostMargin(string4))).marginGone(Float.valueOf(getPostGoneMargin(string4)));
                        }
                    }
                }
            }
        }
        if (constraintReference2 == null) {
            return;
        }
        float f2 = this.mBias;
        if (f2 != 0.5f) {
            constraintReference2.horizontalBias(f2);
        }
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$state$State$Chain[this.mStyle.ordinal()];
        if (i2 == 1) {
            constraintReference2.setHorizontalChainStyle(0);
        } else if (i2 == 2) {
            constraintReference2.setHorizontalChainStyle(1);
        } else {
            if (i2 != 3) {
                return;
            }
            constraintReference2.setHorizontalChainStyle(2);
        }
    }
}
