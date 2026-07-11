package androidx.activity;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes.dex */
public final class FullyDrawnReporterKt {

    /* JADX INFO: renamed from: androidx.activity.FullyDrawnReporterKt$reportWhenComplete$1, reason: invalid class name */
    @N0.f(c = "androidx.activity.FullyDrawnReporterKt", f = "FullyDrawnReporter.kt", l = {185}, m = "reportWhenComplete")
    public static final class AnonymousClass1 extends N0.d {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FullyDrawnReporterKt.reportWhenComplete(null, null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r4v4, types: [H0.s, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object reportWhenComplete(androidx.activity.FullyDrawnReporter r4, kotlin.jvm.functions.Function1 r5, L0.d r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof androidx.activity.FullyDrawnReporterKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.activity.FullyDrawnReporterKt$reportWhenComplete$1 r0 = (androidx.activity.FullyDrawnReporterKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.activity.FullyDrawnReporterKt$reportWhenComplete$1 r0 = new androidx.activity.FullyDrawnReporterKt$reportWhenComplete$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r4 = r0.L$0
            androidx.activity.FullyDrawnReporter r4 = (androidx.activity.FullyDrawnReporter) r4
            H0.k.b(r6)     // Catch: java.lang.Throwable -> L2d
            goto L51
        L2d:
            r5 = move-exception
            goto L5d
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            H0.k.b(r6)
            r4.addReporter()
            boolean r6 = r4.isFullyDrawnReported()
            if (r6 == 0) goto L46
            H0.s r4 = H0.s.f314a
            return r4
        L46:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L2d
            r0.label = r3     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r5 = r5.invoke(r0)     // Catch: java.lang.Throwable -> L2d
            if (r5 != r1) goto L51
            return r1
        L51:
            kotlin.jvm.internal.m.b(r3)
            r4.removeReporter()
            kotlin.jvm.internal.m.a(r3)
            H0.s r4 = H0.s.f314a
            return r4
        L5d:
            kotlin.jvm.internal.m.b(r3)
            r4.removeReporter()
            kotlin.jvm.internal.m.a(r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.FullyDrawnReporterKt.reportWhenComplete(androidx.activity.FullyDrawnReporter, kotlin.jvm.functions.Function1, L0.d):java.lang.Object");
    }

    private static final Object reportWhenComplete$$forInline(FullyDrawnReporter fullyDrawnReporter, Function1 function1, L0.d dVar) {
        fullyDrawnReporter.addReporter();
        if (fullyDrawnReporter.isFullyDrawnReported()) {
            return s.f314a;
        }
        try {
            function1.invoke(dVar);
            m.b(1);
            fullyDrawnReporter.removeReporter();
            m.a(1);
            return s.f314a;
        } catch (Throwable th) {
            m.b(1);
            fullyDrawnReporter.removeReporter();
            m.a(1);
            throw th;
        }
    }
}
