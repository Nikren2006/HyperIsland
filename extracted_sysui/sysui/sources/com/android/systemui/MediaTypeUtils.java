package com.android.systemui;

/* JADX INFO: loaded from: classes.dex */
public final class MediaTypeUtils {
    public static final MediaTypeUtils INSTANCE = new MediaTypeUtils();
    public static final int MEDIA_TYPE_AUDIO = 0;
    public static final int MEDIA_TYPE_VIDEO = 1;

    private MediaTypeUtils() {
    }

    public final boolean isAudioType(Integer num) {
        return num != null && num.intValue() == 0;
    }

    public final boolean isVideoType(Integer num) {
        return num != null && num.intValue() == 1;
    }
}
