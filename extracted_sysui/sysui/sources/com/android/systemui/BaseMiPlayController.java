package com.android.systemui;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import m0.C0465C;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.settings.GlobalSettings;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMiPlayController {
    private C0465C MIPLAY_AUDIO_MANAGER;
    private final String TAG = "MiPlayController";
    public E0.a _MIPLAY_AUDIO_MANAGER;
    public BroadcastDispatcher broadcastDispatcher;
    public Context context;
    public GlobalSettings globalSettings;
    private int mState;
    public Handler mainHandler;
    public Context systemUIContext;

    @Main
    public static /* synthetic */ void getMainHandler$annotations() {
    }

    @SystemUI
    public static /* synthetic */ void getSystemUIContext$annotations() {
    }

    public final void ensureService() {
        if (!isInitialized()) {
            Log.d(this.TAG, "ensureService not Initialized ");
            return;
        }
        int i2 = this.mState;
        if (i2 == 0 || i2 == 7 || i2 == 6) {
            Log.d(this.TAG, "miplay service state invalid = " + i2);
            ((C0465C) get_MIPLAY_AUDIO_MANAGER().get()).k();
        }
    }

    public final BroadcastDispatcher getBroadcastDispatcher() {
        BroadcastDispatcher broadcastDispatcher = this.broadcastDispatcher;
        if (broadcastDispatcher != null) {
            return broadcastDispatcher;
        }
        kotlin.jvm.internal.n.w("broadcastDispatcher");
        return null;
    }

    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        kotlin.jvm.internal.n.w("context");
        return null;
    }

    public final GlobalSettings getGlobalSettings() {
        GlobalSettings globalSettings = this.globalSettings;
        if (globalSettings != null) {
            return globalSettings;
        }
        kotlin.jvm.internal.n.w("globalSettings");
        return null;
    }

    public final C0465C getMIPLAY_AUDIO_MANAGER() {
        if (isInitialized()) {
            ensureService();
            return (C0465C) get_MIPLAY_AUDIO_MANAGER().get();
        }
        Log.d(this.TAG, "MIPLAY_AUDIO_MANAGER get instance not Initialized ");
        return null;
    }

    public final int getMState() {
        return this.mState;
    }

    public final Handler getMainHandler() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            return handler;
        }
        kotlin.jvm.internal.n.w("mainHandler");
        return null;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final E0.a get_MIPLAY_AUDIO_MANAGER() {
        E0.a aVar = this._MIPLAY_AUDIO_MANAGER;
        if (aVar != null) {
            return aVar;
        }
        kotlin.jvm.internal.n.w("_MIPLAY_AUDIO_MANAGER");
        return null;
    }

    public final boolean isContextValid() {
        return this.context != null;
    }

    public final boolean isInitialized() {
        return this._MIPLAY_AUDIO_MANAGER != null;
    }

    public final void setBroadcastDispatcher(BroadcastDispatcher broadcastDispatcher) {
        kotlin.jvm.internal.n.g(broadcastDispatcher, "<set-?>");
        this.broadcastDispatcher = broadcastDispatcher;
    }

    public final void setContext(Context context) {
        kotlin.jvm.internal.n.g(context, "<set-?>");
        this.context = context;
    }

    public final void setGlobalSettings(GlobalSettings globalSettings) {
        kotlin.jvm.internal.n.g(globalSettings, "<set-?>");
        this.globalSettings = globalSettings;
    }

    public final void setMIPLAY_AUDIO_MANAGER(C0465C c0465c) {
        this.MIPLAY_AUDIO_MANAGER = c0465c;
    }

    public final void setMState(int i2) {
        this.mState = i2;
    }

    public final void setMainHandler(Handler handler) {
        kotlin.jvm.internal.n.g(handler, "<set-?>");
        this.mainHandler = handler;
    }

    public final void set_MIPLAY_AUDIO_MANAGER(E0.a aVar) {
        kotlin.jvm.internal.n.g(aVar, "<set-?>");
        this._MIPLAY_AUDIO_MANAGER = aVar;
    }
}
