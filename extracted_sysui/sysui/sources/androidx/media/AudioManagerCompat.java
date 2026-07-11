package androidx.media;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class AudioManagerCompat {
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    private static final String TAG = "AudioManCompat";

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        @DoNotInline
        public static int abandonAudioFocusRequest(AudioManager audioManager, AudioFocusRequest audioFocusRequest) {
            return audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }

        @DoNotInline
        public static int requestAudioFocus(AudioManager audioManager, AudioFocusRequest audioFocusRequest) {
            return audioManager.requestAudioFocus(audioFocusRequest);
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        @DoNotInline
        public static int getStreamMinVolume(AudioManager audioManager, int i2) {
            return audioManager.getStreamMinVolume(i2);
        }
    }

    private AudioManagerCompat() {
    }

    public static int abandonAudioFocusRequest(@NonNull AudioManager audioManager, @NonNull AudioFocusRequestCompat audioFocusRequestCompat) {
        if (audioManager == null) {
            throw new IllegalArgumentException("AudioManager must not be null");
        }
        if (audioFocusRequestCompat != null) {
            return Api26Impl.abandonAudioFocusRequest(audioManager, audioFocusRequestCompat.getAudioFocusRequest());
        }
        throw new IllegalArgumentException("AudioFocusRequestCompat must not be null");
    }

    @IntRange(from = 0)
    public static int getStreamMaxVolume(@NonNull AudioManager audioManager, int i2) {
        return audioManager.getStreamMaxVolume(i2);
    }

    @IntRange(from = 0)
    public static int getStreamMinVolume(@NonNull AudioManager audioManager, int i2) {
        return Api28Impl.getStreamMinVolume(audioManager, i2);
    }

    public static int requestAudioFocus(@NonNull AudioManager audioManager, @NonNull AudioFocusRequestCompat audioFocusRequestCompat) {
        if (audioManager == null) {
            throw new IllegalArgumentException("AudioManager must not be null");
        }
        if (audioFocusRequestCompat != null) {
            return Api26Impl.requestAudioFocus(audioManager, audioFocusRequestCompat.getAudioFocusRequest());
        }
        throw new IllegalArgumentException("AudioFocusRequestCompat must not be null");
    }
}
