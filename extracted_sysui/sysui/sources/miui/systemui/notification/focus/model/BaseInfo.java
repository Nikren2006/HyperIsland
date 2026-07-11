package miui.systemui.notification.focus.model;

/* JADX INFO: loaded from: classes4.dex */
public final class BaseInfo extends TextAndColorInfo {
    private String picFunction;
    private Boolean setMarginBottom;
    private Boolean setMarginTop;
    private Boolean showContentDivider;
    private Boolean showDivider;
    private Integer type;

    public BaseInfo() {
        Boolean bool = Boolean.FALSE;
        this.showDivider = bool;
        this.showContentDivider = bool;
        this.setMarginTop = bool;
        this.setMarginBottom = bool;
    }

    public final String getPicFunction() {
        return this.picFunction;
    }

    public final Boolean getSetMarginBottom() {
        return this.setMarginBottom;
    }

    public final Boolean getSetMarginTop() {
        return this.setMarginTop;
    }

    public final Boolean getShowContentDivider() {
        return this.showContentDivider;
    }

    public final Boolean getShowDivider() {
        return this.showDivider;
    }

    public final Integer getType() {
        return this.type;
    }

    public final void setPicFunction(String str) {
        this.picFunction = str;
    }

    public final void setSetMarginBottom(Boolean bool) {
        this.setMarginBottom = bool;
    }

    public final void setSetMarginTop(Boolean bool) {
        this.setMarginTop = bool;
    }

    public final void setShowContentDivider(Boolean bool) {
        this.showContentDivider = bool;
    }

    public final void setShowDivider(Boolean bool) {
        this.showDivider = bool;
    }

    public final void setType(Integer num) {
        this.type = num;
    }
}
