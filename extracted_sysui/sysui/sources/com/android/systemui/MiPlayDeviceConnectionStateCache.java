package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDeviceConnectionStateCache extends MiPlayDeviceInfoCache<H0.i, m0.y> {
    public static final MiPlayDeviceConnectionStateCache INSTANCE = new MiPlayDeviceConnectionStateCache();
    private static final String TAG = "MiPlayDeviceConnectionStateCache";

    /* JADX INFO: renamed from: com.android.systemui.MiPlayDeviceConnectionStateCache$fetchValue$1, reason: invalid class name */
    @N0.f(c = "com.android.systemui.MiPlayDeviceConnectionStateCache", f = "MiPlayDeviceConnectionStateCache.kt", l = {20, 20}, m = "fetchValue")
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
            return MiPlayDeviceConnectionStateCache.this.fetchValue(null, this);
        }
    }

    private MiPlayDeviceConnectionStateCache() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.android.systemui.MiPlayDeviceInfoCache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object fetchValue(m0.i r6, L0.d r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.android.systemui.MiPlayDeviceConnectionStateCache.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.MiPlayDeviceConnectionStateCache$fetchValue$1 r0 = (com.android.systemui.MiPlayDeviceConnectionStateCache.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.MiPlayDeviceConnectionStateCache$fetchValue$1 r0 = new com.android.systemui.MiPlayDeviceConnectionStateCache$fetchValue$1
            r0.<init>(r7)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r7 = M0.c.c()
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r6 = r0.L$0
            H0.k.b(r5)
            goto L5b
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            java.lang.Object r6 = r0.L$0
            m0.i r6 = (m0.i) r6
            H0.k.b(r5)
            goto L4d
        L3e:
            H0.k.b(r5)
            r0.L$0 = r6
            r0.label = r3
            r5 = 0
            java.lang.Object r5 = com.android.systemui.MiPlayExtentionsKt.fetchConnectionState(r6, r5, r0)
            if (r5 != r7) goto L4d
            return r7
        L4d:
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = com.android.systemui.MiPlayExtentionsKt.fetchConnectionState(r6, r3, r0)
            if (r6 != r7) goto L58
            return r7
        L58:
            r4 = r6
            r6 = r5
            r5 = r4
        L5b:
            H0.i r7 = new H0.i
            r7.<init>(r6, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.MiPlayDeviceConnectionStateCache.fetchValue(m0.i, L0.d):java.lang.Object");
    }

    public final String getTAG() {
        return TAG;
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public m0.y createListener(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        return new MiPlayConnectionStateChangeListener(device);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void putValue(m0.i device, H0.i iVar) {
        kotlin.jvm.internal.n.g(device, "device");
        super.putValue(device, iVar);
        MiPlayDetailViewModel.INSTANCE.updateDeviceListNotCache2Connection();
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void registerListener(m0.i device, m0.y listener) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(listener, "listener");
        device.u(listener, null);
    }

    @Override // com.android.systemui.MiPlayDeviceInfoCache
    public void unregisterListener(m0.i device, m0.y listener) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(listener, "listener");
        device.y(listener);
    }
}
