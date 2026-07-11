package miui.systemui.clouddata;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.n;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class CloudDataFormat {
    public static final CloudDataFormat INSTANCE = new CloudDataFormat();

    private CloudDataFormat() {
    }

    public final JSONArray createJSONArray(String json) {
        n.g(json, "json");
        if (!TextUtils.isEmpty(json)) {
            try {
                return new JSONArray(json);
            } catch (JSONException e2) {
                Log.e("CloudDataFormat", "createJSONArray exception json=" + json, e2);
            }
        }
        return null;
    }

    public final List<String> jsonArray2List(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            try {
                String string = jSONArray.getString(i2);
                n.f(string, "getString(...)");
                arrayList.add(string);
            } catch (JSONException e2) {
                Log.e("CloudDataFormat", "jsonArray2List exception i=" + i2, e2);
            }
        }
        return arrayList;
    }
}
