package miui.systemui.controlcenter.media;

import androidx.annotation.DrawableRes;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerIconsInfo {
    private int deviceRes;
    private int nextRes;
    private int playRes;
    private int prevRes;

    public MediaPlayerIconsInfo(@DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4, @DrawableRes int i5) {
        this.playRes = i2;
        this.prevRes = i3;
        this.nextRes = i4;
        this.deviceRes = i5;
    }

    public static /* synthetic */ MediaPlayerIconsInfo copy$default(MediaPlayerIconsInfo mediaPlayerIconsInfo, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = mediaPlayerIconsInfo.playRes;
        }
        if ((i6 & 2) != 0) {
            i3 = mediaPlayerIconsInfo.prevRes;
        }
        if ((i6 & 4) != 0) {
            i4 = mediaPlayerIconsInfo.nextRes;
        }
        if ((i6 & 8) != 0) {
            i5 = mediaPlayerIconsInfo.deviceRes;
        }
        return mediaPlayerIconsInfo.copy(i2, i3, i4, i5);
    }

    public final int component1() {
        return this.playRes;
    }

    public final int component2() {
        return this.prevRes;
    }

    public final int component3() {
        return this.nextRes;
    }

    public final int component4() {
        return this.deviceRes;
    }

    public final MediaPlayerIconsInfo copy(@DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4, @DrawableRes int i5) {
        return new MediaPlayerIconsInfo(i2, i3, i4, i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPlayerIconsInfo)) {
            return false;
        }
        MediaPlayerIconsInfo mediaPlayerIconsInfo = (MediaPlayerIconsInfo) obj;
        return this.playRes == mediaPlayerIconsInfo.playRes && this.prevRes == mediaPlayerIconsInfo.prevRes && this.nextRes == mediaPlayerIconsInfo.nextRes && this.deviceRes == mediaPlayerIconsInfo.deviceRes;
    }

    public final int getDeviceRes() {
        return this.deviceRes;
    }

    public final int getNextRes() {
        return this.nextRes;
    }

    public final int getPlayRes() {
        return this.playRes;
    }

    public final int getPrevRes() {
        return this.prevRes;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.playRes) * 31) + Integer.hashCode(this.prevRes)) * 31) + Integer.hashCode(this.nextRes)) * 31) + Integer.hashCode(this.deviceRes);
    }

    public final void setDeviceRes(int i2) {
        this.deviceRes = i2;
    }

    public final void setNextRes(int i2) {
        this.nextRes = i2;
    }

    public final void setPlayRes(int i2) {
        this.playRes = i2;
    }

    public final void setPrevRes(int i2) {
        this.prevRes = i2;
    }

    public String toString() {
        return "MediaPlayerIconsInfo(playRes=" + this.playRes + ", prevRes=" + this.prevRes + ", nextRes=" + this.nextRes + ", deviceRes=" + this.deviceRes + ")";
    }
}
