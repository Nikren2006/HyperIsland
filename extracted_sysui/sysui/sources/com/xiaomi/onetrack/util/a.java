package com.xiaomi.onetrack.util;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3416a = "onetrack_active";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3417b = "onetrack_bug_report";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3418c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3419d = "region";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3420e = "area";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f3421f = "EU";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f3422g = "pub_gzipencrypt";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f3423h = "pub_sid";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f3424i = "true";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Set<String> f3425j = new HashSet(Arrays.asList("AT", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "DE", "EL", "GR", "HU", "IS", "IE", "IT", "LV", "LI", "LT", "LU", "MT", "NL", "NO", "PL", "PT", "RO", "SK", "SI", "ES", "SE", "UK", "GB"));

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Set<String> f3426k = new HashSet(Arrays.asList("CN", "RU", "CU", "IR", "KP", "SD", "SY", "TR"));

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f3427l = "Constants";

    public static String a() {
        try {
            String strL = r.l();
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(strL)) {
                jSONObject.put("region", strL);
                if (f3425j.contains(strL.toUpperCase())) {
                    jSONObject.put(f3420e, f3421f);
                }
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            q.a(f3427l, "getDefaultAttributes exception:" + e2.getMessage());
            return "";
        }
    }

    public static Map<String, String> b() {
        HashMap map = new HashMap();
        try {
            String strL = r.l();
            if (!TextUtils.isEmpty(strL)) {
                map.put("region", strL);
                if (f3425j.contains(strL.toUpperCase())) {
                    map.put(f3420e, f3421f);
                }
            }
        } catch (Exception e2) {
            q.a(f3427l, "getDefaultAttributes exception:" + e2.getMessage());
        }
        return map;
    }
}
