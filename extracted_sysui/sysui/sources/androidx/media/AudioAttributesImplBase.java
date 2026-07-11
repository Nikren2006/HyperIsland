package androidx.media;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImpl;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AudioAttributesImplBase implements AudioAttributesImpl {

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mContentType;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mFlags;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mLegacyStream;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int mUsage;

    public static class Builder implements AudioAttributesImpl.Builder {
        private int mContentType;
        private int mFlags;
        private int mLegacyStream;
        private int mUsage;

        public Builder() {
            this.mUsage = 0;
            this.mContentType = 0;
            this.mFlags = 0;
            this.mLegacyStream = -1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private Builder setInternalLegacyStreamType(int i2) {
            switch (i2) {
                case 0:
                    this.mContentType = 1;
                    break;
                case 1:
                    this.mContentType = 4;
                    break;
                case 2:
                    this.mContentType = 4;
                    break;
                case 3:
                    this.mContentType = 2;
                    break;
                case 4:
                    this.mContentType = 4;
                    break;
                case 5:
                    this.mContentType = 4;
                    break;
                case 6:
                    this.mContentType = 1;
                    this.mFlags |= 4;
                    break;
                case 7:
                    this.mFlags = 1 | this.mFlags;
                    this.mContentType = 4;
                    break;
                case 8:
                    this.mContentType = 4;
                    break;
                case 9:
                    this.mContentType = 4;
                    break;
                case 10:
                    this.mContentType = 1;
                    break;
                default:
                    Log.e("AudioAttributesCompat", "Invalid stream type " + i2 + " for AudioAttributesCompat");
                    break;
            }
            this.mUsage = AudioAttributesImplBase.usageForStreamType(i2);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public AudioAttributesImpl build() {
            return new AudioAttributesImplBase(this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream);
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setContentType(int i2) {
            if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
                this.mContentType = i2;
            } else {
                this.mContentType = 0;
            }
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setFlags(int i2) {
            this.mFlags = (i2 & 1023) | this.mFlags;
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setLegacyStreamType(int i2) {
            if (i2 == 10) {
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            }
            this.mLegacyStream = i2;
            return setInternalLegacyStreamType(i2);
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        @NonNull
        public Builder setUsage(int i2) {
            switch (i2) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    this.mUsage = i2;
                    return this;
                case 16:
                    this.mUsage = 12;
                    return this;
                default:
                    this.mUsage = 0;
                    return this;
            }
        }

        public Builder(AudioAttributesCompat audioAttributesCompat) {
            this.mUsage = 0;
            this.mContentType = 0;
            this.mFlags = 0;
            this.mLegacyStream = -1;
            this.mUsage = audioAttributesCompat.getUsage();
            this.mContentType = audioAttributesCompat.getContentType();
            this.mFlags = audioAttributesCompat.getFlags();
            this.mLegacyStream = audioAttributesCompat.getRawLegacyStreamType();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public AudioAttributesImplBase() {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
        this.mLegacyStream = -1;
    }

    public static int usageForStreamType(int i2) {
        switch (i2) {
        }
        return 2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.mContentType == audioAttributesImplBase.getContentType() && this.mFlags == audioAttributesImplBase.getFlags() && this.mUsage == audioAttributesImplBase.getUsage() && this.mLegacyStream == audioAttributesImplBase.mLegacyStream;
    }

    @Override // androidx.media.AudioAttributesImpl
    @Nullable
    public Object getAudioAttributes() {
        return null;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.mContentType;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        int i2 = this.mFlags;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i2 |= 4;
        } else if (legacyStreamType == 7) {
            i2 |= 1;
        }
        return i2 & 273;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i2 = this.mLegacyStream;
        return i2 != -1 ? i2 : AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.mLegacyStream;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.mUsage;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream != -1) {
            sb.append(" stream=");
            sb.append(this.mLegacyStream);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.usageToString(this.mUsage));
        sb.append(" content=");
        sb.append(this.mContentType);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb.toString();
    }

    public AudioAttributesImplBase(int i2, int i3, int i4, int i5) {
        this.mContentType = i2;
        this.mFlags = i3;
        this.mUsage = i4;
        this.mLegacyStream = i5;
    }
}
