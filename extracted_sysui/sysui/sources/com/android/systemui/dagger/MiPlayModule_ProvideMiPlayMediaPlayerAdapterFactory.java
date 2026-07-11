package com.android.systemui.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.content.Context;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayModule_ProvideMiPlayMediaPlayerAdapterFactory implements e {
    private final a contextProvider;
    private final MiPlayModule module;

    public MiPlayModule_ProvideMiPlayMediaPlayerAdapterFactory(MiPlayModule miPlayModule, a aVar) {
        this.module = miPlayModule;
        this.contextProvider = aVar;
    }

    public static MiPlayModule_ProvideMiPlayMediaPlayerAdapterFactory create(MiPlayModule miPlayModule, a aVar) {
        return new MiPlayModule_ProvideMiPlayMediaPlayerAdapterFactory(miPlayModule, aVar);
    }

    public static MediaPlayerAdapter provideMiPlayMediaPlayerAdapter(MiPlayModule miPlayModule, Context context) {
        return (MediaPlayerAdapter) i.d(miPlayModule.provideMiPlayMediaPlayerAdapter(context));
    }

    @Override // G0.a
    public MediaPlayerAdapter get() {
        return provideMiPlayMediaPlayerAdapter(this.module, (Context) this.contextProvider.get());
    }
}
