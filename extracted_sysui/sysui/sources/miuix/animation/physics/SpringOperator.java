package miuix.animation.physics;

/* JADX INFO: loaded from: classes4.dex */
public class SpringOperator implements PhysicsOperator {
    double[] params;

    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void updateValues(miuix.animation.internal.AnimData r19, double r20, double r22, double r24, double r26, boolean r28) {
        /*
            r0 = r19
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r28 == 0) goto L8
            r3 = r1
            goto La
        L8:
            double r3 = r0.targetValue
        La:
            if (r28 == 0) goto Lf
            double r5 = r0.progress
            goto L11
        Lf:
            double r5 = r0.value
        L11:
            double r7 = r0.velocity
            double r5 = r5 - r3
            r9 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            double r11 = r22 * r9
            double r11 = r11 * r26
            double r11 = java.lang.Math.exp(r11)
            r13 = 0
            int r15 = (r20 > r13 ? 1 : (r20 == r13 ? 0 : -1))
            r16 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r15 < 0) goto L4f
            int r15 = (r20 > r1 ? 1 : (r20 == r1 ? 0 : -1))
            if (r15 >= 0) goto L4f
            double r1 = r22 * r16
            double r1 = r1 * r5
            double r7 = r7 + r1
            double r7 = r7 / r24
            double r1 = r24 * r26
            double r13 = java.lang.Math.cos(r1)
            double r1 = java.lang.Math.sin(r1)
            double r15 = r5 * r13
            double r17 = r7 * r1
            double r15 = r15 + r17
            double r15 = r15 * r11
            double r9 = r9 * r15
            double r9 = r9 * r22
            double r7 = r7 * r13
            double r5 = r5 * r1
            double r7 = r7 - r5
            double r7 = r7 * r24
            double r7 = r7 * r11
            double r9 = r9 + r7
            r0.velocity = r9
        L4d:
            r13 = r15
            goto L8a
        L4f:
            int r1 = (r20 > r1 ? 1 : (r20 == r1 ? 0 : -1))
            if (r1 != 0) goto L64
            double r1 = r22 * r16
            double r1 = r1 * r5
            double r7 = r7 + r1
            double r1 = r7 * r26
            double r5 = r5 + r1
            double r13 = r5 * r11
            double r9 = r9 * r13
            double r9 = r9 * r22
            double r7 = r7 * r11
            double r9 = r9 + r7
            r0.velocity = r9
            goto L8a
        L64:
            if (r1 <= 0) goto L8a
            double r1 = r22 * r16
            double r1 = r1 * r5
            double r7 = r7 + r1
            double r7 = r7 / r24
            double r1 = r24 * r26
            double r13 = java.lang.Math.cosh(r1)
            double r1 = java.lang.Math.sinh(r1)
            double r15 = r5 * r13
            double r17 = r7 * r1
            double r15 = r15 + r17
            double r15 = r15 * r11
            double r9 = r9 * r15
            double r9 = r9 * r22
            double r7 = r7 * r13
            double r5 = r5 * r1
            double r7 = r7 + r5
            double r7 = r7 * r24
            double r7 = r7 * r11
            double r9 = r9 + r7
            r0.velocity = r9
            goto L4d
        L8a:
            if (r28 == 0) goto L90
            double r13 = r13 + r3
            r0.progress = r13
            goto L93
        L90:
            double r13 = r13 + r3
            r0.value = r13
        L93:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.animation.physics.SpringOperator.updateValues(miuix.animation.internal.AnimData, double, double, double, double, boolean):void");
    }

    @Override // miuix.animation.physics.PhysicsOperator
    public void getParameters(double[] dArr, double[] dArr2) {
        double d2 = dArr[0];
        double d3 = 6.283185307179586d / dArr[1];
        double d4 = dArr.length >= 3 ? dArr[2] : 1.0d;
        dArr2[0] = Math.pow(d3, 2.0d) * d4;
        dArr2[1] = 2.0d * d3 * d2 * d4;
        if (dArr2.length >= 3) {
            if (d2 > 1.0d) {
                dArr2[2] = d3 * Math.sqrt((d2 * d2) - 1.0d);
            } else {
                if (d2 < 0.0d || d2 >= 1.0d) {
                    return;
                }
                dArr2[2] = d3 * Math.sqrt(1.0d - (d2 * d2));
            }
        }
    }

    @Deprecated
    public double updateVelocity(double d2, float f2, float... fArr) {
        if (this.params == null) {
            return d2;
        }
        double[] dArr = new double[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            dArr[i2] = fArr[i2];
        }
        double[] dArr2 = this.params;
        return updateVelocity(d2, dArr2[0], dArr2[1], f2, dArr);
    }

    @Override // miuix.animation.physics.PhysicsOperator
    @Deprecated
    public double updateVelocity(double d2, double d3, double d4, double d5, double... dArr) {
        return (d2 * (1.0d - (Math.max(d4, 60.0d) * d5))) + ((double) ((float) (d3 * (dArr[0] - dArr[1]) * d5)));
    }
}
