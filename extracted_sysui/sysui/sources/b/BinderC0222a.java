package b;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import b.c;
import com.android.systemui.settings.UserContextProvider;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.window.content.DynamicIslandShareData;
import miui.systemui.dynamicisland.window.content.ShareProviderData;

/* JADX INFO: renamed from: b.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC0222a extends c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UserContextProvider f1300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1301b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ShareProviderData f1302c;

    public BinderC0222a(UserContextProvider userContextProvider) {
        n.g(userContextProvider, "userContextProvider");
        this.f1300a = userContextProvider;
        this.f1301b = "DynamicIslandDropInfoNotifierService";
        this.f1302c = DynamicIslandShareData.INSTANCE.getShareProviderData();
    }

    public final void Z0() {
        this.f1302c.setPackageName(null);
        this.f1302c.setTitle(null);
        this.f1302c.setShareMessage(null);
        this.f1302c.setShareUrl(null);
        this.f1302c.setShareIconByteArray(null);
        this.f1302c.setValue(-1);
    }

    public final Bundle a1() {
        Bundle bundle = new Bundle();
        bundle.putString(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_APP_PACKAGE_NAME, this.f1302c.getPackageName());
        bundle.putString(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_APP_TITLE, this.f1302c.getTitle());
        bundle.putString(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_APP_MESSAGE, this.f1302c.getShareMessage());
        bundle.putString(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_APP_URL, this.f1302c.getShareUrl());
        bundle.putByteArray(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_APP_MESSAGE_ICON, this.f1302c.getShareIconByteArray());
        bundle.putInt(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_APP_SCENE, this.f1302c.getValue());
        return bundle;
    }

    @Override // b.c
    public void n(String str, Bundle bundle) {
        Bundle bundleA1 = a1();
        Uri uri = Uri.parse(DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_URI);
        try {
            try {
                ContentResolver contentResolver = this.f1300a.getUserContext().getContentResolver();
                if (contentResolver != null) {
                    contentResolver.call(uri, DynamicIslandConstants.SHARE_TO_PERSONALASSISTANT_METHOD, (String) null, bundleA1);
                }
                Log.i(this.f1301b, "now page is WX and send provider to personalAssistant");
            } catch (SecurityException e2) {
                Log.e(this.f1301b, "no permission: " + e2.getMessage());
            } catch (Exception e3) {
                Log.e(this.f1301b, "share to WX failure: " + e3.getMessage());
            }
            Z0();
        } catch (Throwable th) {
            Z0();
            throw th;
        }
    }
}
