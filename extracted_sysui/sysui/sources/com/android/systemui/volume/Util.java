package com.android.systemui.volume;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.onetrack.api.au;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
class Util {
    private static final SimpleDateFormat HMMAA = new SimpleDateFormat("h:mm aa", Locale.US);
    public static boolean DEBUG = Log.isLoggable("volume", 3);
    private static int[] AUDIO_MANAGER_FLAGS = {1, 16, 4, 2, 8, 2048, 128, 4096, 1024};
    private static String[] AUDIO_MANAGER_FLAG_NAMES = {"SHOW_UI", "VIBRATE", "PLAY_SOUND", "ALLOW_RINGER_MODES", "REMOVE_SOUND_AND_VIBRATE", "SHOW_VIBRATE_HINT", "SHOW_SILENT_HINT", "FROM_KEY", "SHOW_UI_WARNINGS"};

    public static String audioManagerFlagsToString(int i2) {
        return bitFieldToString(i2, AUDIO_MANAGER_FLAGS, AUDIO_MANAGER_FLAG_NAMES);
    }

    private static String bitFieldToString(int i2, int[] iArr, String[] strArr) {
        if (i2 == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if ((iArr[i3] & i2) != 0) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(strArr[i3]);
            }
            i2 &= ~iArr[i3];
        }
        if (i2 != 0) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append("UNKNOWN_");
            sb.append(i2);
        }
        return sb.toString();
    }

    private static CharSequence emptyToNull(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return null;
        }
        return charSequence;
    }

    public static String getShortTime(long j2) {
        return HMMAA.format(new Date(j2));
    }

    public static boolean isVoiceCapable(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(au.f2924d);
        return telephonyManager != null && telephonyManager.isVoiceCapable();
    }

    public static String logTag(String str) {
        String str2 = "vol." + str;
        return str2.length() < 23 ? str2 : str2.substring(0, 23);
    }

    public static String mediaMetadataToString(MediaMetadata mediaMetadata) {
        return mediaMetadata.getDescription().toString();
    }

    public static String playbackInfoToString(MediaController.PlaybackInfo playbackInfo) {
        if (playbackInfo == null) {
            return null;
        }
        return String.format("PlaybackInfo[vol=%s,max=%s,type=%s,vc=%s],atts=%s", Integer.valueOf(playbackInfo.getCurrentVolume()), Integer.valueOf(playbackInfo.getMaxVolume()), playbackInfoTypeToString(playbackInfo.getPlaybackType()), volumeProviderControlToString(playbackInfo.getVolumeControl()), playbackInfo.getAudioAttributes());
    }

    public static String playbackInfoTypeToString(int i2) {
        if (i2 == 1) {
            return "LOCAL";
        }
        if (i2 == 2) {
            return "REMOTE";
        }
        return "UNKNOWN_" + i2;
    }

    public static String playbackStateStateToString(int i2) {
        if (i2 == 0) {
            return "STATE_NONE";
        }
        if (i2 == 1) {
            return "STATE_STOPPED";
        }
        if (i2 == 2) {
            return "STATE_PAUSED";
        }
        if (i2 == 3) {
            return "STATE_PLAYING";
        }
        return "UNKNOWN_" + i2;
    }

    public static String playbackStateToString(PlaybackState playbackState) {
        if (playbackState == null) {
            return null;
        }
        return playbackStateStateToString(playbackState.getState()) + " " + playbackState;
    }

    public static String ringerModeToString(int i2) {
        if (i2 == 0) {
            return "RINGER_MODE_SILENT";
        }
        if (i2 == 1) {
            return "RINGER_MODE_VIBRATE";
        }
        if (i2 == 2) {
            return "RINGER_MODE_NORMAL";
        }
        return "RINGER_MODE_UNKNOWN_" + i2;
    }

    public static boolean setText(TextView textView, CharSequence charSequence) {
        if (Objects.equals(emptyToNull(textView.getText()), emptyToNull(charSequence))) {
            return false;
        }
        textView.setText(charSequence);
        return true;
    }

    public static final void setVisOrGone(View view, boolean z2) {
        if (view != null) {
            if ((view.getVisibility() == 0) == z2) {
                return;
            }
            view.setVisibility(z2 ? 0 : 8);
        }
    }

    public static final void setVisOrInvis(View view, boolean z2) {
        if (view != null) {
            if ((view.getVisibility() == 0) == z2) {
                return;
            }
            view.setVisibility(z2 ? 0 : 4);
        }
    }

    public static String volumeProviderControlToString(int i2) {
        if (i2 == 0) {
            return "VOLUME_CONTROL_FIXED";
        }
        if (i2 == 1) {
            return "VOLUME_CONTROL_RELATIVE";
        }
        if (i2 == 2) {
            return "VOLUME_CONTROL_ABSOLUTE";
        }
        return "VOLUME_CONTROL_UNKNOWN_" + i2;
    }
}
