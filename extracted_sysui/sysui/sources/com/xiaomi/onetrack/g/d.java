package com.xiaomi.onetrack.g;

import android.text.TextUtils;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.xiaomi.onetrack.api.ah;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3376a = 200;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3377b = 401;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3378c = 404;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3379d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public JSONArray f3380e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3381f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f3382g;

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (this.f3379d == 200) {
                this.f3380e = jSONObject.optJSONArray("messageIds");
            } else {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(MediaRouteProviderProtocol.SERVICE_DATA_ERROR);
                this.f3381f = jSONObjectOptJSONObject.optString(ah.f2833m);
                this.f3382g = jSONObjectOptJSONObject.optString("status");
            }
        } catch (Exception unused) {
        }
    }

    public String toString() {
        return "PublishResponse{code=" + this.f3379d + ", messageIds=" + this.f3380e + ", message='" + this.f3381f + "', status='" + this.f3382g + "'}";
    }
}
