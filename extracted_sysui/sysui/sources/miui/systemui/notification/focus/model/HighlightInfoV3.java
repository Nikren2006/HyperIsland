package miui.systemui.notification.focus.model;

/* JADX INFO: loaded from: classes4.dex */
public final class HighlightInfoV3 extends TextAndColorInfo {
    private ActionInfo actionInfo;
    private String highLightText;
    private String highLightTextColor;
    private String highLightTextColorDark;
    private String highLightbgColor;
    private String highLightbgColorDark;
    private String primaryColor;
    private String primaryColorDark;
    private String primaryText;
    private String secondaryColor;
    private String secondaryColorDark;
    private String secondaryText;
    private Boolean showSecondaryLine = Boolean.FALSE;

    public final ActionInfo getActionInfo() {
        return this.actionInfo;
    }

    public final String getHighLightText() {
        return this.highLightText;
    }

    public final String getHighLightTextColor() {
        return this.highLightTextColor;
    }

    public final String getHighLightTextColorDark() {
        return this.highLightTextColorDark;
    }

    public final String getHighLightbgColor() {
        return this.highLightbgColor;
    }

    public final String getHighLightbgColorDark() {
        return this.highLightbgColorDark;
    }

    public final String getPrimaryColor() {
        return this.primaryColor;
    }

    public final String getPrimaryColorDark() {
        return this.primaryColorDark;
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getSecondaryColor() {
        return this.secondaryColor;
    }

    public final String getSecondaryColorDark() {
        return this.secondaryColorDark;
    }

    public final String getSecondaryText() {
        return this.secondaryText;
    }

    public final Boolean getShowSecondaryLine() {
        return this.showSecondaryLine;
    }

    public final void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public final void setHighLightText(String str) {
        this.highLightText = str;
    }

    public final void setHighLightTextColor(String str) {
        this.highLightTextColor = str;
    }

    public final void setHighLightTextColorDark(String str) {
        this.highLightTextColorDark = str;
    }

    public final void setHighLightbgColor(String str) {
        this.highLightbgColor = str;
    }

    public final void setHighLightbgColorDark(String str) {
        this.highLightbgColorDark = str;
    }

    public final void setPrimaryColor(String str) {
        this.primaryColor = str;
    }

    public final void setPrimaryColorDark(String str) {
        this.primaryColorDark = str;
    }

    public final void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public final void setSecondaryColor(String str) {
        this.secondaryColor = str;
    }

    public final void setSecondaryColorDark(String str) {
        this.secondaryColorDark = str;
    }

    public final void setSecondaryText(String str) {
        this.secondaryText = str;
    }

    public final void setShowSecondaryLine(Boolean bool) {
        this.showSecondaryLine = bool;
    }
}
