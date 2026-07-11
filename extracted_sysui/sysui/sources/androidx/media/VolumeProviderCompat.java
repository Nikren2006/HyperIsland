package androidx.media;

import android.media.VolumeProvider;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final String mControlId;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private VolumeProvider mVolumeProviderFwk;

    /* JADX INFO: renamed from: androidx.media.VolumeProviderCompat$2, reason: invalid class name */
    public class AnonymousClass2 extends VolumeProvider {
        public AnonymousClass2(int i2, int i3, int i4) {
            super(i2, i3, i4);
        }

        @Override // android.media.VolumeProvider
        public void onAdjustVolume(int i2) {
            VolumeProviderCompat.this.onAdjustVolume(i2);
        }

        @Override // android.media.VolumeProvider
        public void onSetVolumeTo(int i2) {
            VolumeProviderCompat.this.onSetVolumeTo(i2);
        }
    }

    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        @DoNotInline
        public static void setCurrentVolume(VolumeProvider volumeProvider, int i2) {
            volumeProvider.setCurrentVolume(i2);
        }
    }

    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ControlType {
    }

    public VolumeProviderCompat(int i2, int i3, int i4) {
        this(i2, i3, i4, null);
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public final String getVolumeControlId() {
        return this.mControlId;
    }

    public Object getVolumeProvider() {
        if (this.mVolumeProviderFwk == null) {
            this.mVolumeProviderFwk = new VolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, this.mControlId) { // from class: androidx.media.VolumeProviderCompat.1
                @Override // android.media.VolumeProvider
                public void onAdjustVolume(int i2) {
                    VolumeProviderCompat.this.onAdjustVolume(i2);
                }

                @Override // android.media.VolumeProvider
                public void onSetVolumeTo(int i2) {
                    VolumeProviderCompat.this.onSetVolumeTo(i2);
                }
            };
        }
        return this.mVolumeProviderFwk;
    }

    public void onAdjustVolume(int i2) {
    }

    public void onSetVolumeTo(int i2) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public final void setCurrentVolume(int i2) {
        this.mCurrentVolume = i2;
        Api21Impl.setCurrentVolume((VolumeProvider) getVolumeProvider(), i2);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onVolumeChanged(this);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public VolumeProviderCompat(int i2, int i3, int i4, @Nullable String str) {
        this.mControlType = i2;
        this.mMaxVolume = i3;
        this.mCurrentVolume = i4;
        this.mControlId = str;
    }
}
