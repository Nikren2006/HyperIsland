package com.miui.miplay.audio.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackState implements Parcelable {
    public static final Parcelable.Creator<PlaybackState> CREATOR = new Parcelable.Creator<PlaybackState>() { // from class: com.miui.miplay.audio.data.PlaybackState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlaybackState createFromParcel(Parcel parcel) {
            return new PlaybackState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlaybackState[] newArray(int i2) {
            return new PlaybackState[i2];
        }
    };
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_STOPPED = 1;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;

    public PlaybackState() {
        this.mPosition = 0L;
        this.mState = 0;
        this.mSpeed = 0.0f;
    }

    public static String stateToName(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 6 ? i2 != 7 ? i2 != 8 ? "illegalState" : "connecting" : MediaRouteProviderProtocol.SERVICE_DATA_ERROR : "buffering" : MiPlayEventsKt.POSITION_PLAY : MiPlayEventsKt.POSITION_PAUSE : "stop" : "none";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getPosition() {
        return this.mPosition;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public int getState() {
        return this.mState;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.mPosition);
        parcel.writeInt(this.mState);
        parcel.writeFloat(this.mSpeed);
    }

    public PlaybackState(@Nullable android.media.session.PlaybackState playbackState) {
        if (playbackState != null) {
            this.mPosition = playbackState.getPosition();
            this.mState = playbackState.getState();
            this.mSpeed = playbackState.getPlaybackSpeed();
        } else {
            this.mPosition = 0L;
            this.mState = 0;
            this.mSpeed = 0.0f;
        }
    }

    public PlaybackState(long j2, int i2, float f2) {
        this.mPosition = j2;
        this.mState = i2;
        this.mSpeed = f2;
    }

    public PlaybackState(Parcel parcel) {
        this.mPosition = parcel.readLong();
        this.mState = parcel.readInt();
        this.mSpeed = parcel.readFloat();
    }
}
