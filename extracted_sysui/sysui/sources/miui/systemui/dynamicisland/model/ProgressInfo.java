package miui.systemui.dynamicisland.model;

/* JADX INFO: loaded from: classes3.dex */
public final class ProgressInfo {
    private String colorReach;
    private String colorUnReach;
    private Boolean isCCW = Boolean.FALSE;
    private Integer progress;

    public final String getColorReach() {
        return this.colorReach;
    }

    public final String getColorUnReach() {
        return this.colorUnReach;
    }

    public final Integer getProgress() {
        return this.progress;
    }

    public final Boolean isCCW() {
        return this.isCCW;
    }

    public final void setCCW(Boolean bool) {
        this.isCCW = bool;
    }

    public final void setColorReach(String str) {
        this.colorReach = str;
    }

    public final void setColorUnReach(String str) {
        this.colorUnReach = str;
    }

    public final void setProgress(Integer num) {
        this.progress = num;
    }
}
