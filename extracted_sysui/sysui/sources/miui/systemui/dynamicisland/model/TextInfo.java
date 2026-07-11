package miui.systemui.dynamicisland.model;

/* JADX INFO: loaded from: classes3.dex */
public final class TextInfo {
    private String content;
    private String frontTitle;
    private Boolean isTitleDigit;
    private Boolean narrowFont;
    private Boolean showHighlightColor;
    private String title;
    private Boolean turnAnim;

    public TextInfo() {
        Boolean bool = Boolean.FALSE;
        this.showHighlightColor = bool;
        this.narrowFont = bool;
        this.turnAnim = bool;
        this.isTitleDigit = bool;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getFrontTitle() {
        return this.frontTitle;
    }

    public final Boolean getNarrowFont() {
        return this.narrowFont;
    }

    public final Boolean getShowHighlightColor() {
        return this.showHighlightColor;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Boolean getTurnAnim() {
        return this.turnAnim;
    }

    public final Boolean isTitleDigit() {
        return this.isTitleDigit;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final void setFrontTitle(String str) {
        this.frontTitle = str;
    }

    public final void setNarrowFont(Boolean bool) {
        this.narrowFont = bool;
    }

    public final void setShowHighlightColor(Boolean bool) {
        this.showHighlightColor = bool;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final void setTitleDigit(Boolean bool) {
        this.isTitleDigit = bool;
    }

    public final void setTurnAnim(Boolean bool) {
        this.turnAnim = bool;
    }
}
