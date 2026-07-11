package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_ABOVE = 5;
    public static final int ARC_BELOW = 4;
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int DOWN_ARC = 4;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    private static final int UP_ARC = 5;
    Arc[] mArcs;
    private boolean mExtrapolate = true;
    private final double[] mTime;

    public static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] sOurPercent = new double[91];
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        boolean mLinear;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        public Arc(int i2, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.mLinear = false;
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (i2 == 1) {
                this.mVertical = true;
            } else if (i2 == 4) {
                this.mVertical = d9 > 0.0d;
            } else if (i2 != 5) {
                this.mVertical = false;
            } else {
                this.mVertical = d9 < 0.0d;
            }
            this.mTime1 = d2;
            this.mTime2 = d3;
            this.mOneOverDeltaTime = 1.0d / (d3 - d2);
            if (3 == i2) {
                this.mLinear = true;
            }
            if (!this.mLinear && Math.abs(d8) >= EPSILON && Math.abs(d9) >= EPSILON) {
                this.mLut = new double[101];
                boolean z2 = this.mVertical;
                this.mEllipseA = d8 * ((double) (z2 ? -1 : 1));
                this.mEllipseB = d9 * ((double) (z2 ? 1 : -1));
                this.mEllipseCenterX = z2 ? d6 : d4;
                this.mEllipseCenterY = z2 ? d5 : d7;
                buildTable(d4, d5, d6, d7);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.mLinear = true;
            this.mX1 = d4;
            this.mX2 = d6;
            this.mY1 = d5;
            this.mY2 = d7;
            double dHypot = Math.hypot(d9, d8);
            this.mArcDistance = dHypot;
            this.mArcVelocity = dHypot * this.mOneOverDeltaTime;
            double d10 = this.mTime2;
            double d11 = this.mTime1;
            this.mEllipseCenterX = d8 / (d10 - d11);
            this.mEllipseCenterY = d9 / (d10 - d11);
        }

        private void buildTable(double d2, double d3, double d4, double d5) {
            double dHypot;
            double d6 = d4 - d2;
            double d7 = d3 - d5;
            int i2 = 0;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (true) {
                if (i2 >= sOurPercent.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((((double) i2) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d6;
                double dCos = Math.cos(radians) * d7;
                if (i2 > 0) {
                    dHypot = Math.hypot(dSin - d9, dCos - d10) + d11;
                    sOurPercent[i2] = dHypot;
                } else {
                    dHypot = d11;
                }
                i2++;
                d10 = dCos;
                d8 = dHypot;
                d9 = dSin;
            }
            double d12 = d8;
            this.mArcDistance = d12;
            int i3 = 0;
            while (true) {
                double[] dArr = sOurPercent;
                if (i3 >= dArr.length) {
                    break;
                }
                dArr[i3] = dArr[i3] / d12;
                i3++;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.mLut.length) {
                    return;
                }
                double length = ((double) i4) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(sOurPercent, length);
                if (iBinarySearch >= 0) {
                    this.mLut[i4] = ((double) iBinarySearch) / ((double) (sOurPercent.length - 1));
                } else if (iBinarySearch == -1) {
                    this.mLut[i4] = 0.0d;
                } else {
                    int i5 = -iBinarySearch;
                    int i6 = i5 - 2;
                    double[] dArr2 = sOurPercent;
                    double d13 = dArr2[i6];
                    this.mLut[i4] = (((double) i6) + ((length - d13) / (dArr2[i5 - 1] - d13))) / ((double) (dArr2.length - 1));
                }
                i4++;
            }
        }

        public double getDX() {
            double d2 = this.mEllipseA * this.mTmpCosAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d2, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d2 = -d2;
            }
            return d2 * dHypot;
        }

        public double getDY() {
            double d2 = this.mEllipseA * this.mTmpCosAngle;
            double d3 = (-this.mEllipseB) * this.mTmpSinAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d2, d3);
            return this.mVertical ? (-d3) * dHypot : d3 * dHypot;
        }

        public double getLinearDX(double d2) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d2) {
            return this.mEllipseCenterY;
        }

        public double getLinearX(double d2) {
            double d3 = (d2 - this.mTime1) * this.mOneOverDeltaTime;
            double d4 = this.mX1;
            return d4 + (d3 * (this.mX2 - d4));
        }

        public double getLinearY(double d2) {
            double d3 = (d2 - this.mTime1) * this.mOneOverDeltaTime;
            double d4 = this.mY1;
            return d4 + (d3 * (this.mY2 - d4));
        }

        public double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        public double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        public double lookup(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d2 * ((double) (dArr.length - 1));
            int i2 = (int) length;
            double d3 = length - ((double) i2);
            double d4 = dArr[i2];
            return d4 + (d3 * (dArr[i2 + 1] - d4));
        }

        public void setPoint(double d2) {
            double dLookup = lookup((this.mVertical ? this.mTime2 - d2 : d2 - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(dLookup);
            this.mTmpCosAngle = Math.cos(dLookup);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002e A[PHI: r9
      0x002e: PHI (r9v1 int) = (r9v0 int), (r9v3 int), (r9v4 int) binds: [B:6:0x001e, B:12:0x0028, B:14:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcCurveFit(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r2 = 1
            r0.mExtrapolate = r2
            r0.mTime = r1
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r3 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit.Arc[r3]
            r0.mArcs = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r7 = r0.mArcs
            int r8 = r7.length
            if (r4 >= r8) goto L58
            r8 = r25[r4]
            r9 = 3
            if (r8 == 0) goto L2e
            if (r8 == r2) goto L37
            r10 = 2
            if (r8 == r10) goto L35
            if (r8 == r9) goto L30
            r9 = 4
            if (r8 == r9) goto L2e
            r9 = 5
            if (r8 == r9) goto L2e
            goto L39
        L2e:
            r6 = r9
            goto L39
        L30:
            if (r5 != r2) goto L37
            goto L35
        L33:
            r6 = r5
            goto L39
        L35:
            r5 = r10
            goto L33
        L37:
            r5 = r2
            goto L33
        L39:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc r22 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r3]
            r16 = r8[r2]
            r8 = r27[r23]
            r18 = r8[r3]
            r20 = r8[r2]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L16
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d2, double[] dArr) {
        if (this.mExtrapolate) {
            Arc[] arcArr = this.mArcs;
            Arc arc = arcArr[0];
            double d3 = arc.mTime1;
            if (d2 < d3) {
                double d4 = d2 - d3;
                if (arc.mLinear) {
                    dArr[0] = arc.getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4);
                    dArr[1] = this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3));
                    return;
                } else {
                    arc.setPoint(d3);
                    dArr[0] = this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4);
                    dArr[1] = this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY());
                    return;
                }
            }
            if (d2 > arcArr[arcArr.length - 1].mTime2) {
                double d5 = arcArr[arcArr.length - 1].mTime2;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                Arc arc2 = arcArr[length];
                if (arc2.mLinear) {
                    dArr[0] = arc2.getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6);
                    dArr[1] = this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5));
                    return;
                } else {
                    arc2.setPoint(d2);
                    dArr[0] = this.mArcs[length].getX() + (this.mArcs[length].getDX() * d6);
                    dArr[1] = this.mArcs[length].getY() + (d6 * this.mArcs[length].getDY());
                    return;
                }
            }
        } else {
            Arc[] arcArr2 = this.mArcs;
            double d7 = arcArr2[0].mTime1;
            if (d2 < d7) {
                d2 = d7;
            }
            if (d2 > arcArr2[arcArr2.length - 1].mTime2) {
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i2 >= arcArr3.length) {
                return;
            }
            Arc arc3 = arcArr3[i2];
            if (d2 <= arc3.mTime2) {
                if (arc3.mLinear) {
                    dArr[0] = arc3.getLinearX(d2);
                    dArr[1] = this.mArcs[i2].getLinearY(d2);
                    return;
                } else {
                    arc3.setPoint(d2);
                    dArr[0] = this.mArcs[i2].getX();
                    dArr[1] = this.mArcs[i2].getY();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        Arc[] arcArr = this.mArcs;
        double d3 = arcArr[0].mTime1;
        if (d2 < d3) {
            d2 = d3;
        } else if (d2 > arcArr[arcArr.length - 1].mTime2) {
            d2 = arcArr[arcArr.length - 1].mTime2;
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i2 >= arcArr2.length) {
                return;
            }
            Arc arc = arcArr2[i2];
            if (d2 <= arc.mTime2) {
                if (arc.mLinear) {
                    dArr[0] = arc.getLinearDX(d2);
                    dArr[1] = this.mArcs[i2].getLinearDY(d2);
                    return;
                } else {
                    arc.setPoint(d2);
                    dArr[0] = this.mArcs[i2].getDX();
                    dArr[1] = this.mArcs[i2].getDY();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d2, int i2) {
        Arc[] arcArr = this.mArcs;
        int i3 = 0;
        double d3 = arcArr[0].mTime1;
        if (d2 < d3) {
            d2 = d3;
        }
        if (d2 > arcArr[arcArr.length - 1].mTime2) {
            d2 = arcArr[arcArr.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i3 >= arcArr2.length) {
                return Double.NaN;
            }
            Arc arc = arcArr2[i3];
            if (d2 <= arc.mTime2) {
                if (arc.mLinear) {
                    if (i2 == 0) {
                        return arc.getLinearDX(d2);
                    }
                    return arc.getLinearDY(d2);
                }
                arc.setPoint(d2);
                if (i2 == 0) {
                    return this.mArcs[i3].getDX();
                }
                return this.mArcs[i3].getDY();
            }
            i3++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d2, float[] fArr) {
        if (this.mExtrapolate) {
            Arc[] arcArr = this.mArcs;
            Arc arc = arcArr[0];
            double d3 = arc.mTime1;
            if (d2 < d3) {
                double d4 = d2 - d3;
                if (arc.mLinear) {
                    fArr[0] = (float) (arc.getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4));
                    fArr[1] = (float) (this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3)));
                    return;
                } else {
                    arc.setPoint(d3);
                    fArr[0] = (float) (this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4));
                    fArr[1] = (float) (this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY()));
                    return;
                }
            }
            if (d2 > arcArr[arcArr.length - 1].mTime2) {
                double d5 = arcArr[arcArr.length - 1].mTime2;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                Arc arc2 = arcArr[length];
                if (arc2.mLinear) {
                    fArr[0] = (float) (arc2.getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6));
                    fArr[1] = (float) (this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5)));
                    return;
                } else {
                    arc2.setPoint(d2);
                    fArr[0] = (float) this.mArcs[length].getX();
                    fArr[1] = (float) this.mArcs[length].getY();
                    return;
                }
            }
        } else {
            Arc[] arcArr2 = this.mArcs;
            double d7 = arcArr2[0].mTime1;
            if (d2 < d7) {
                d2 = d7;
            } else if (d2 > arcArr2[arcArr2.length - 1].mTime2) {
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i2 = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i2 >= arcArr3.length) {
                return;
            }
            Arc arc3 = arcArr3[i2];
            if (d2 <= arc3.mTime2) {
                if (arc3.mLinear) {
                    fArr[0] = (float) arc3.getLinearX(d2);
                    fArr[1] = (float) this.mArcs[i2].getLinearY(d2);
                    return;
                } else {
                    arc3.setPoint(d2);
                    fArr[0] = (float) this.mArcs[i2].getX();
                    fArr[1] = (float) this.mArcs[i2].getY();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d2, int i2) {
        double linearY;
        double linearDY;
        double y2;
        double dy;
        double linearY2;
        double linearDY2;
        int i3 = 0;
        if (this.mExtrapolate) {
            Arc[] arcArr = this.mArcs;
            Arc arc = arcArr[0];
            double d3 = arc.mTime1;
            if (d2 < d3) {
                double d4 = d2 - d3;
                if (arc.mLinear) {
                    if (i2 == 0) {
                        linearY2 = arc.getLinearX(d3);
                        linearDY2 = this.mArcs[0].getLinearDX(d3);
                    } else {
                        linearY2 = arc.getLinearY(d3);
                        linearDY2 = this.mArcs[0].getLinearDY(d3);
                    }
                    return linearY2 + (d4 * linearDY2);
                }
                arc.setPoint(d3);
                if (i2 == 0) {
                    y2 = this.mArcs[0].getX();
                    dy = this.mArcs[0].getDX();
                } else {
                    y2 = this.mArcs[0].getY();
                    dy = this.mArcs[0].getDY();
                }
                return y2 + (d4 * dy);
            }
            if (d2 > arcArr[arcArr.length - 1].mTime2) {
                double d5 = arcArr[arcArr.length - 1].mTime2;
                double d6 = d2 - d5;
                int length = arcArr.length - 1;
                if (i2 == 0) {
                    linearY = arcArr[length].getLinearX(d5);
                    linearDY = this.mArcs[length].getLinearDX(d5);
                } else {
                    linearY = arcArr[length].getLinearY(d5);
                    linearDY = this.mArcs[length].getLinearDY(d5);
                }
                return linearY + (d6 * linearDY);
            }
        } else {
            Arc[] arcArr2 = this.mArcs;
            double d7 = arcArr2[0].mTime1;
            if (d2 < d7) {
                d2 = d7;
            } else if (d2 > arcArr2[arcArr2.length - 1].mTime2) {
                d2 = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i3 >= arcArr3.length) {
                return Double.NaN;
            }
            Arc arc2 = arcArr3[i3];
            if (d2 <= arc2.mTime2) {
                if (arc2.mLinear) {
                    if (i2 == 0) {
                        return arc2.getLinearX(d2);
                    }
                    return arc2.getLinearY(d2);
                }
                arc2.setPoint(d2);
                if (i2 == 0) {
                    return this.mArcs[i3].getX();
                }
                return this.mArcs[i3].getY();
            }
            i3++;
        }
    }
}
