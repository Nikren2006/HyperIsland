package miui.systemui.util;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class StatusBarGuideParams {
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_ICON_FORMAT = "iconFormat";
    private static final String KEY_ICON_PARAMS = "iconParams";
    private static final String KEY_ICON_RES_NAME = "iconResName";
    private static final String KEY_ICON_TYPE = "iconType";
    private static final String KEY_LEFT = "left";
    private static final String KEY_RIGHT = "right";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TEXT_COLOR = "textColor";
    private static final String KEY_TEXT_PARAMS = "textParams";
    private static final String KEY_TURN_ANIM = "turnAnim";
    ViewArea left;
    ViewArea right;

    public static class Builder {
        ViewArea left;
        ViewArea right;

        public StatusBarGuideParams create() {
            return new StatusBarGuideParams(this.left, this.right);
        }

        public Builder setLeftIcon(IconParams iconParams) {
            if (this.left == null) {
                this.left = new ViewArea();
            }
            this.left.iconParams = iconParams;
            return this;
        }

        public Builder setLeftText(TextParams textParams) {
            if (this.left == null) {
                this.left = new ViewArea();
            }
            this.left.textParams = textParams;
            return this;
        }

        public Builder setRightIcon(IconParams iconParams) {
            if (this.right == null) {
                this.right = new ViewArea();
            }
            this.right.iconParams = iconParams;
            return this;
        }

        public Builder setRightText(TextParams textParams) {
            if (this.right == null) {
                this.right = new ViewArea();
            }
            this.right.textParams = textParams;
            return this;
        }
    }

    public static class IconParams {
        String category;
        String iconFormat;
        String iconResName;
        Integer iconType;

        public IconParams(String str, Integer num, String str2, String str3) {
            this.iconResName = str;
            this.iconType = num;
            this.iconFormat = str2;
            this.category = str3;
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StatusBarGuideParams.KEY_ICON_RES_NAME, this.iconResName);
                jSONObject.put(StatusBarGuideParams.KEY_ICON_TYPE, this.iconType);
                jSONObject.put(StatusBarGuideParams.KEY_ICON_FORMAT, this.iconFormat);
                jSONObject.put("category", this.category);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public StatusBarGuideParams(ViewArea viewArea, ViewArea viewArea2) {
        this.left = viewArea;
        this.right = viewArea2;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            ViewArea viewArea = this.left;
            if (viewArea != null) {
                jSONObject.put(KEY_LEFT, viewArea.toJSONObject());
            }
            ViewArea viewArea2 = this.right;
            if (viewArea2 != null) {
                jSONObject.put(KEY_RIGHT, viewArea2.toJSONObject());
            }
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static class TextParams {
        String text;
        Integer textColor;
        boolean turnAnim;

        public TextParams(String str, Integer num) {
            this.text = str;
            this.textColor = num;
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StatusBarGuideParams.KEY_TEXT, this.text);
                jSONObject.put(StatusBarGuideParams.KEY_TEXT_COLOR, this.textColor);
                jSONObject.put(StatusBarGuideParams.KEY_TURN_ANIM, this.turnAnim);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public TextParams(String str, Integer num, boolean z2) {
            this.text = str;
            this.textColor = num;
            this.turnAnim = z2;
        }
    }

    public static class ViewArea {
        IconParams iconParams;
        TextParams textParams;

        public ViewArea(TextParams textParams, IconParams iconParams) {
            this.textParams = textParams;
            this.iconParams = iconParams;
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                TextParams textParams = this.textParams;
                if (textParams != null) {
                    jSONObject.put(StatusBarGuideParams.KEY_TEXT_PARAMS, textParams.toJSONObject());
                }
                IconParams iconParams = this.iconParams;
                if (iconParams != null) {
                    jSONObject.put(StatusBarGuideParams.KEY_ICON_PARAMS, iconParams.toJSONObject());
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public ViewArea() {
        }
    }
}
