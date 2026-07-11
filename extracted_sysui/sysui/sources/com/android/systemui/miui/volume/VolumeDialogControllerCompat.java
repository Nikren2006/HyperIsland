package com.android.systemui.miui.volume;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.plugins.VolumeDialogController;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeDialogControllerCompat {
    public static final int IS_CHANGE = 8;
    public static final int IS_LONG_PRESS = 1;
    public static final int IS_MAX_LEVEL = 2;
    public static final int IS_MIN_LEVEL = 4;
    public static final int IS_SHOWUI = 16;
    private static final String TAG = "VolumeDialogControllerCompat";
    private Callback mCallback;
    private final VolumeDialogController.Callbacks mControllerCallbackH;
    private VolumeDialogController mVolumeController;

    public interface Callback {
        void onAccessibilityModeChanged(Boolean bool);

        void onConfigurationChanged();

        void onDismissRequested(int i2);

        void onLayoutDirectionChanged(int i2);

        void onPerformHapticFeedback(int i2);

        void onScreenOff();

        void onShowRequested(int i2);

        void onShowSafetyWarning(int i2);

        void onShowSilentHint();

        void onShowVibrateHint();

        void onStateChanged(VolumeDialogController.State state);
    }

    public VolumeDialogControllerCompat(VolumeDialogController volumeDialogController, Handler handler) {
        VolumeDialogController.Callbacks callbacks = new VolumeDialogController.Callbacks() { // from class: com.android.systemui.miui.volume.VolumeDialogControllerCompat.1
            public void onAccessibilityModeChanged(Boolean bool) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onAccessibilityModeChanged(bool);
                }
            }

            public void onCaptionComponentStateChanged(Boolean bool, Boolean bool2) {
            }

            public void onCaptionEnabledStateChanged(Boolean bool, Boolean bool2) {
            }

            public void onConfigurationChanged() {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onConfigurationChanged();
                }
            }

            public void onDismissRequested(int i2) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onDismissRequested(i2);
                }
            }

            public void onLayoutDirectionChanged(int i2) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onLayoutDirectionChanged(i2);
                }
            }

            public void onPerformHapticFeedback(int i2) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onPerformHapticFeedback(i2);
                }
            }

            public void onScreenOff() {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onScreenOff();
                }
            }

            public void onShowCsdWarning(int i2, int i3) {
            }

            public void onShowRequested(int i2) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onShowRequested(i2);
                }
            }

            public void onShowSafetyWarning(int i2) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onShowSafetyWarning(i2);
                }
            }

            public void onShowSilentHint() {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onShowSilentHint();
                }
            }

            public void onShowVibrateHint() {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onShowVibrateHint();
                }
            }

            public void onStateChanged(VolumeDialogController.State state) {
                if (VolumeDialogControllerCompat.this.mCallback != null) {
                    VolumeDialogControllerCompat.this.mCallback.onStateChanged(state);
                }
            }

            public void onVolumeChangedFromKey() {
                super.onVolumeChangedFromKey();
            }

            public void onShowRequested(int i2, boolean z2, int i3) {
                super.onShowRequested(i2, z2, i3);
            }
        };
        this.mControllerCallbackH = callbacks;
        this.mVolumeController = volumeDialogController;
        volumeDialogController.addCallback(callbacks, handler);
    }

    public void addCallback(Callback callback) {
        this.mCallback = callback;
    }

    public String getStreamLabelH(Context context, VolumeDialogController.StreamState streamState) {
        if (streamState == null) {
            return "";
        }
        String str = streamState.remoteLabel;
        if (str != null) {
            return str;
        }
        try {
            return context.getString(streamState.name);
        } catch (Resources.NotFoundException unused) {
            Log.e(TAG, "Can't find translation for stream " + streamState);
            return "";
        }
    }

    public void removeCallback(Callback callback) {
        this.mCallback = null;
        this.mVolumeController.removeCallback(this.mControllerCallbackH);
    }
}
