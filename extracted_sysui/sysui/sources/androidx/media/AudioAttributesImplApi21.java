package androidx.media;

import android.media.AudioAttributes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImpl;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AudioAttributesImplApi21 implements AudioAttributesImpl {

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public AudioAttributes mAudioAttributes;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mLegacyStreamType;

    @RequiresApi(21)
    public static class Builder implements AudioAttributesImpl.Builder {
        final AudioAttributes.Builder mFwkBuilder;

        public Builder() {
            this.mFwkBuilder = new AudioAttributes.Builder();
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public AudioAttributesImpl build() {
            return new AudioAttributesImplApi21(this.mFwkBuilder.build());
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setContentType(int i2) {
            this.mFwkBuilder.setContentType(i2);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setFlags(int i2) {
            this.mFwkBuilder.setFlags(i2);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setLegacyStreamType(int i2) {
            this.mFwkBuilder.setLegacyStreamType(i2);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setUsage(int i2) {
            if (i2 == 16) {
                i2 = 12;
            }
            this.mFwkBuilder.setUsage(i2);
            return this;
        }

        public Builder(Object obj) {
            this.mFwkBuilder = new AudioAttributes.Builder((AudioAttributes) obj);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
        }
        return false;
    }

    @Override // androidx.media.AudioAttributesImpl
    @Nullable
    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i2 = this.mLegacyStreamType;
        return i2 != -1 ? i2 : AudioAttributesCompat.toVolumeStreamType(false, getFlags(), getUsage());
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    @NonNull
    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.mAudioAttributes;
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i2) {
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = i2;
    }
}
