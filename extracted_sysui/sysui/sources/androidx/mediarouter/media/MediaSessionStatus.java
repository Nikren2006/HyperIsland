package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.TimeUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MediaSessionStatus {
    static final String KEY_EXTRAS = "extras";
    static final String KEY_QUEUE_PAUSED = "queuePaused";
    static final String KEY_SESSION_STATE = "sessionState";
    static final String KEY_TIMESTAMP = "timestamp";
    public static final int SESSION_STATE_ACTIVE = 0;
    public static final int SESSION_STATE_ENDED = 1;
    public static final int SESSION_STATE_INVALIDATED = 2;
    final Bundle mBundle;

    public MediaSessionStatus(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Nullable
    public static MediaSessionStatus fromBundle(@Nullable Bundle bundle) {
        if (bundle != null) {
            return new MediaSessionStatus(bundle);
        }
        return null;
    }

    private static String sessionStateToString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? Integer.toString(i2) : "invalidated" : "ended" : "active";
    }

    @NonNull
    public Bundle asBundle() {
        return this.mBundle;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mBundle.getBundle(KEY_EXTRAS);
    }

    public int getSessionState() {
        return this.mBundle.getInt(KEY_SESSION_STATE, 2);
    }

    public long getTimestamp() {
        return this.mBundle.getLong("timestamp");
    }

    public boolean isQueuePaused() {
        return this.mBundle.getBoolean(KEY_QUEUE_PAUSED);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaSessionStatus{ ");
        sb.append("timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - getTimestamp(), sb);
        sb.append(" ms ago");
        sb.append(", sessionState=");
        sb.append(sessionStateToString(getSessionState()));
        sb.append(", queuePaused=");
        sb.append(isQueuePaused());
        sb.append(", extras=");
        sb.append(getExtras());
        sb.append(" }");
        return sb.toString();
    }

    public static final class Builder {
        private final Bundle mBundle;

        public Builder(int i2) {
            this.mBundle = new Bundle();
            setTimestamp(SystemClock.elapsedRealtime());
            setSessionState(i2);
        }

        @NonNull
        public MediaSessionStatus build() {
            return new MediaSessionStatus(this.mBundle);
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle bundle) {
            if (bundle == null) {
                this.mBundle.putBundle(MediaSessionStatus.KEY_EXTRAS, null);
            } else {
                this.mBundle.putBundle(MediaSessionStatus.KEY_EXTRAS, new Bundle(bundle));
            }
            return this;
        }

        @NonNull
        public Builder setQueuePaused(boolean z2) {
            this.mBundle.putBoolean(MediaSessionStatus.KEY_QUEUE_PAUSED, z2);
            return this;
        }

        @NonNull
        public Builder setSessionState(int i2) {
            this.mBundle.putInt(MediaSessionStatus.KEY_SESSION_STATE, i2);
            return this;
        }

        @NonNull
        public Builder setTimestamp(long j2) {
            this.mBundle.putLong("timestamp", j2);
            return this;
        }

        public Builder(@NonNull MediaSessionStatus mediaSessionStatus) {
            if (mediaSessionStatus != null) {
                this.mBundle = new Bundle(mediaSessionStatus.mBundle);
                return;
            }
            throw new IllegalArgumentException("status must not be null");
        }
    }
}
