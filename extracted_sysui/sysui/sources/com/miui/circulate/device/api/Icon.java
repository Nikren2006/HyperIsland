package com.miui.circulate.device.api;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class Icon implements Flat {
    public static final Companion Companion = new Companion(null);
    public static final int TYPE_COLOR_VALUE = 2;
    public static final int TYPE_RES = 1;
    public static final int TYPE_URI = 3;
    private final Object data;
    private final int type;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Icon fromColor(@ColorInt int i2) {
            return new Icon(2, Integer.valueOf(i2));
        }

        public final Icon fromPublicDrawable(String pkg, @DrawableRes int i2) {
            n.g(pkg, "pkg");
            return new Icon(1, new Res(pkg, i2));
        }

        public final Icon fromUri(String uri) {
            n.g(uri, "uri");
            return new Icon(3, uri);
        }

        public final Icon parse(String str) {
            Icon icon;
            if (str == null || str.length() == 0) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i2 = jSONObject.getInt("t");
                if (i2 == 1) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("d");
                    String string = jSONObject2.getString("pkg");
                    n.f(string, "resJson.getString(\"pkg\")");
                    return new Icon(i2, new Res(string, jSONObject2.getInt(Column.ID)));
                }
                if (i2 == 2) {
                    String string2 = jSONObject.getString("d");
                    n.f(string2, "jo.getString(\"d\")");
                    icon = new Icon(i2, Integer.valueOf(Integer.parseInt(string2)));
                } else {
                    if (i2 != 3) {
                        return null;
                    }
                    String string3 = jSONObject.getString("d");
                    n.f(string3, "jo.getString(\"d\")");
                    icon = new Icon(i2, string3);
                }
                return icon;
            } catch (Exception e2) {
                Log.e(Constant.TAG, "parse icon", e2);
                return null;
            }
        }

        private Companion() {
        }
    }

    public static final class Res {
        private final int id;
        private final String pkg;

        public Res(String pkg, int i2) {
            n.g(pkg, "pkg");
            this.pkg = pkg;
            this.id = i2;
        }

        public static /* synthetic */ Res copy$default(Res res, String str, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = res.pkg;
            }
            if ((i3 & 2) != 0) {
                i2 = res.id;
            }
            return res.copy(str, i2);
        }

        public final String component1() {
            return this.pkg;
        }

        public final int component2() {
            return this.id;
        }

        public final Res copy(String pkg, int i2) {
            n.g(pkg, "pkg");
            return new Res(pkg, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Res)) {
                return false;
            }
            Res res = (Res) obj;
            return n.c(this.pkg, res.pkg) && this.id == res.id;
        }

        public final int getId() {
            return this.id;
        }

        public final String getPkg() {
            return this.pkg;
        }

        public int hashCode() {
            return (this.pkg.hashCode() * 31) + Integer.hashCode(this.id);
        }

        public String toString() {
            return "Res(pkg=" + this.pkg + ", id=" + this.id + ')';
        }
    }

    public Icon(int i2, Object data) {
        n.g(data, "data");
        this.type = i2;
        this.data = data;
    }

    public static /* synthetic */ Icon copy$default(Icon icon, int i2, Object obj, int i3, Object obj2) {
        if ((i3 & 1) != 0) {
            i2 = icon.type;
        }
        if ((i3 & 2) != 0) {
            obj = icon.data;
        }
        return icon.copy(i2, obj);
    }

    public static final Icon fromColor(@ColorInt int i2) {
        return Companion.fromColor(i2);
    }

    public static final Icon fromPublicDrawable(String str, @DrawableRes int i2) {
        return Companion.fromPublicDrawable(str, i2);
    }

    public static final Icon fromUri(String str) {
        return Companion.fromUri(str);
    }

    public final int component1() {
        return this.type;
    }

    public final Object component2() {
        return this.data;
    }

    public final Icon copy(int i2, Object data) {
        n.g(data, "data");
        return new Icon(i2, data);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Icon)) {
            return false;
        }
        Icon icon = (Icon) obj;
        return this.type == icon.type && n.c(this.data, icon.data);
    }

    @Override // com.miui.circulate.device.api.Flat
    public String flat() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("t", this.type);
        if (this.type == 1) {
            JSONObject jSONObject2 = new JSONObject();
            Object obj = this.data;
            n.e(obj, "null cannot be cast to non-null type com.miui.circulate.device.api.Icon.Res");
            Res res = (Res) obj;
            jSONObject2.put("pkg", res.getPkg());
            jSONObject2.put(Column.ID, res.getId());
            jSONObject.put("d", jSONObject2);
        } else {
            jSONObject.put("d", this.data.toString());
        }
        String string = jSONObject.toString();
        n.f(string, "json.toString()");
        return string;
    }

    public final int getColorInt() {
        if (this.type == 2) {
            Object obj = this.data;
            n.e(obj, "null cannot be cast to non-null type kotlin.Int");
            return ((Integer) obj).intValue();
        }
        throw new IllegalArgumentException("illegal color value icon, " + this);
    }

    public final Object getData() {
        return this.data;
    }

    public final Res getDrawableRes() {
        if (this.type == 1) {
            Object obj = this.data;
            n.e(obj, "null cannot be cast to non-null type com.miui.circulate.device.api.Icon.Res");
            return (Res) obj;
        }
        throw new IllegalArgumentException("illegal drawable res icon, " + this);
    }

    public final int getType() {
        return this.type;
    }

    public final Uri getUri() {
        if (this.type != 3) {
            throw new IllegalArgumentException("illegal uri icon, " + this);
        }
        Object obj = this.data;
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        Uri uri = Uri.parse((String) obj);
        n.f(uri, "parse(data as String)");
        return uri;
    }

    public int hashCode() {
        return (Integer.hashCode(this.type) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "Icon(type=" + this.type + ", data=" + this.data + ')';
    }
}
