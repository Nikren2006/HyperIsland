package com.android.systemui;

import android.util.Log;
import com.miui.miplay.audio.data.MediaMetaData;
import m0.C0465C;

/* JADX INFO: loaded from: classes.dex */
public final class CpState {
    private final String TAG = "CpState";
    private int appState = -1;
    private boolean isPlayingAdvertisement;

    private final boolean isAppBackground() {
        return this.appState == 0;
    }

    public final boolean allowControlRemoteTV() {
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
        if (value == null) {
            return false;
        }
        int mediaType = value.getMediaType();
        Log.d(this.TAG, "allowControlRemoteTV mediaType:" + mediaType + ",isAppBackground:" + isAppBackground() + ",isPlayingAdvertisement:" + this.isPlayingAdvertisement + ",isLocalPausing:" + miPlayDetailViewModel.isLocalPausing());
        if (mediaType == 1) {
            if (this.isPlayingAdvertisement) {
                return false;
            }
            C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
            Integer numValueOf = miplay_audio_manager != null ? Integer.valueOf(miplay_audio_manager.d(mediaType)) : null;
            if (isAppBackground() && numValueOf != null && numValueOf.intValue() == 0) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 0 && miPlayDetailViewModel.isLocalPausing()) {
                return false;
            }
        }
        return true;
    }

    public final int getAppState() {
        return this.appState;
    }

    public final boolean isPlayingAdvertisement() {
        return this.isPlayingAdvertisement;
    }

    public final void setAppState(int i2) {
        this.appState = i2;
    }

    public final void setPlayingAdvertisement(boolean z2) {
        this.isPlayingAdvertisement = z2;
    }
}
