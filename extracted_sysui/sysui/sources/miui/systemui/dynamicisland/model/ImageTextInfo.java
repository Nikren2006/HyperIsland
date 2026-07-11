package miui.systemui.dynamicisland.model;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageTextInfo {
    private PicInfo picInfo;
    private ProgressInfo progressInfo;
    private TextInfo textInfo;
    private Integer type;

    public final PicInfo getPicInfo() {
        return this.picInfo;
    }

    public final ProgressInfo getProgressInfo() {
        return this.progressInfo;
    }

    public final TextInfo getTextInfo() {
        return this.textInfo;
    }

    public final Integer getType() {
        return this.type;
    }

    public final void setPicInfo(PicInfo picInfo) {
        this.picInfo = picInfo;
    }

    public final void setProgressInfo(ProgressInfo progressInfo) {
        this.progressInfo = progressInfo;
    }

    public final void setTextInfo(TextInfo textInfo) {
        this.textInfo = textInfo;
    }

    public final void setType(Integer num) {
        this.type = num;
    }
}
