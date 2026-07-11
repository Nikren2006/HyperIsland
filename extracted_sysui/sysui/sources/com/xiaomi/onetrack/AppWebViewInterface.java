package com.xiaomi.onetrack;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.xiaomi.onetrack.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class AppWebViewInterface {
    public static final String JAVASCRIPT_INTERFACE_NAME = "OneTrack_APP_H5_Bridge";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2573a = "AppWebViewInterface";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OneTrack f2574b;

    public AppWebViewInterface(OneTrack oneTrack) {
        this.f2574b = oneTrack;
    }

    @JavascriptInterface
    public boolean track(String str) {
        q.a(f2573a, "received h5 data. data: " + str);
        if (this.f2574b == null) {
            q.a(f2573a, "mOneTrack is null, return false");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            q.a(f2573a, "h5 data is empty, return false");
            return false;
        }
        this.f2574b.trackEventFromH5(str);
        return true;
    }
}
