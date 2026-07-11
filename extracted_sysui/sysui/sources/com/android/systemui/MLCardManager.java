package com.android.systemui;

import android.content.Context;
import android.util.Log;
import com.miui.circulate.device.api.DeviceInfo;
import h0.C0401a;
import i0.InterfaceC0405c;
import i0.InterfaceC0406d;

/* JADX INFO: loaded from: classes.dex */
public final class MLCardManager {
    private static final String TAG = "MLCardManager";
    private static boolean isInit;
    public static final MLCardManager INSTANCE = new MLCardManager();
    private static final H0.d cardStateListener$delegate = H0.e.b(MLCardManager$cardStateListener$2.INSTANCE);
    private static final H0.d cardServerStateListener$delegate = H0.e.b(MLCardManager$cardServerStateListener$2.INSTANCE);

    public static final class CardServerStateListener implements InterfaceC0405c {
        @Override // i0.InterfaceC0405c
        public /* bridge */ /* synthetic */ H0.s onServiceDisconnectedCallback() {
            m56onServiceDisconnectedCallback();
            return H0.s.f314a;
        }

        @Override // i0.InterfaceC0405c
        public /* bridge */ /* synthetic */ H0.s onStartServiceFailCallback() {
            m57onStartServiceFailCallback();
            return H0.s.f314a;
        }

        @Override // i0.InterfaceC0405c
        public /* bridge */ /* synthetic */ H0.s onStartServiceSuccessCallback() {
            m58onStartServiceSuccessCallback();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: onServiceDisconnectedCallback, reason: collision with other method in class */
        public void m56onServiceDisconnectedCallback() {
            Log.e(MLCardManager.TAG, "onServiceDisconnectedCallback");
        }

        /* JADX INFO: renamed from: onStartServiceFailCallback, reason: collision with other method in class */
        public void m57onStartServiceFailCallback() {
            Log.e(MLCardManager.TAG, "onStartServiceFailCallback");
        }

        /* JADX INFO: renamed from: onStartServiceSuccessCallback, reason: collision with other method in class */
        public void m58onStartServiceSuccessCallback() {
            Log.d(MLCardManager.TAG, "onStartServiceSuccessCallback");
        }
    }

    public static final class CardStateListener implements InterfaceC0406d {
        @Override // i0.InterfaceC0406d
        public /* bridge */ /* synthetic */ H0.s onCardContentErrCallback(int i2) {
            m59onCardContentErrCallback(i2);
            return H0.s.f314a;
        }

        @Override // i0.InterfaceC0406d
        public /* bridge */ /* synthetic */ H0.s onCardCreatedCallback(int i2) {
            m60onCardCreatedCallback(i2);
            return H0.s.f314a;
        }

        @Override // i0.InterfaceC0406d
        public /* bridge */ /* synthetic */ H0.s onCardHiddenCallback(int i2) {
            m61onCardHiddenCallback(i2);
            return H0.s.f314a;
        }

        @Override // i0.InterfaceC0406d
        public H0.s onCardShowAndChangedCallback(int i2) {
            Log.d(MLCardManager.TAG, "onCardShowAndChangedCallback cardId = " + i2);
            return null;
        }

        /* JADX INFO: renamed from: onCardContentErrCallback, reason: collision with other method in class */
        public void m59onCardContentErrCallback(int i2) {
            Log.e(MLCardManager.TAG, "onCardContentErrCallback errType = " + i2);
        }

        /* JADX INFO: renamed from: onCardCreatedCallback, reason: collision with other method in class */
        public void m60onCardCreatedCallback(int i2) {
            Log.d(MLCardManager.TAG, "onCardCreatedCallback cardId = " + i2);
        }

        /* JADX INFO: renamed from: onCardHiddenCallback, reason: collision with other method in class */
        public void m61onCardHiddenCallback(int i2) {
            Log.d(MLCardManager.TAG, "onCardHiddenCallback cardId = " + i2);
        }
    }

    private MLCardManager() {
    }

    private final void closeTvCard() {
        C0401a.E(C0401a.f4478i, 0, 1, null);
    }

    private final CardServerStateListener getCardServerStateListener() {
        return (CardServerStateListener) cardServerStateListener$delegate.getValue();
    }

    private final CardStateListener getCardStateListener() {
        return (CardStateListener) cardStateListener$delegate.getValue();
    }

    public final void destroy() {
        closeTvCard();
        C0401a c0401a = C0401a.f4478i;
        MLCardManager mLCardManager = INSTANCE;
        c0401a.w(mLCardManager.getCardStateListener());
        c0401a.v(mLCardManager.getCardServerStateListener());
        isInit = false;
    }

    public final void init() {
        if (isInit) {
            return;
        }
        C0401a c0401a = C0401a.f4478i;
        MLCardManager mLCardManager = INSTANCE;
        c0401a.d(mLCardManager.getCardStateListener());
        c0401a.c(mLCardManager.getCardServerStateListener());
        isInit = true;
    }

    public final void openTvCard(Context context, String deviceId, String category, String deviceType, String toSpecifyPanel, String deviceName, String str) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(deviceId, "deviceId");
        kotlin.jvm.internal.n.g(category, "category");
        kotlin.jvm.internal.n.g(deviceType, "deviceType");
        kotlin.jvm.internal.n.g(toSpecifyPanel, "toSpecifyPanel");
        kotlin.jvm.internal.n.g(deviceName, "deviceName");
        init();
        C0401a.f4478i.G(context, new DeviceInfo.Builder().setDeviceType(deviceType).setCategory(category).setMac(str).setId(deviceId).setTitle(deviceName).build(), toSpecifyPanel);
    }
}
