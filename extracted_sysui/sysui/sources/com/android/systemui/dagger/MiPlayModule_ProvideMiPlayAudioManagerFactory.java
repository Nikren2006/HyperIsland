package com.android.systemui.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.content.Context;
import m0.C0465C;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayModule_ProvideMiPlayAudioManagerFactory implements e {
    private final a contextProvider;
    private final MiPlayModule module;

    public MiPlayModule_ProvideMiPlayAudioManagerFactory(MiPlayModule miPlayModule, a aVar) {
        this.module = miPlayModule;
        this.contextProvider = aVar;
    }

    public static MiPlayModule_ProvideMiPlayAudioManagerFactory create(MiPlayModule miPlayModule, a aVar) {
        return new MiPlayModule_ProvideMiPlayAudioManagerFactory(miPlayModule, aVar);
    }

    public static C0465C provideMiPlayAudioManager(MiPlayModule miPlayModule, Context context) {
        return (C0465C) i.d(miPlayModule.provideMiPlayAudioManager(context));
    }

    @Override // G0.a
    public C0465C get() {
        return provideMiPlayAudioManager(this.module, (Context) this.contextProvider.get());
    }
}
