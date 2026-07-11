package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDetailViewModelKt {
    public static final int progressToVolume(int i2, int i3, int i4) {
        return i3 + ((int) ((i4 - i3) * (i2 / 1000)));
    }

    public static final int volumeToProgress(int i2, int i3, int i4) {
        return (int) (((i2 - i3) / (i4 - i3)) * 1000);
    }
}
