package com.android.systemui.dagger;

import android.content.Context;
import com.android.systemui.MiPlayMediaPlayerAdapter;
import m0.C0465C;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;
import miui.systemui.dagger.qualifiers.SystemUI;

/* JADX INFO: loaded from: classes.dex */
public class MiPlayModule {
    public C0465C provideMiPlayAudioManager(@SystemUI Context context) {
        return new C0465C(context);
    }

    public MediaPlayerAdapter provideMiPlayMediaPlayerAdapter(Context context) {
        return new MiPlayMediaPlayerAdapter(context);
    }
}
