package com.miui.miplay.audio.data;

/* JADX INFO: loaded from: classes2.dex */
public class AdvertisementParam {
    private int mediaType;
    private String pkg;
    private int state;

    public AdvertisementParam(int i2, String str, int i3) {
        this.state = i2;
        this.pkg = str;
        this.mediaType = i3;
    }

    public int getMediaType() {
        return this.mediaType;
    }

    public String getPkg() {
        return this.pkg;
    }

    public int getState() {
        return this.state;
    }
}
