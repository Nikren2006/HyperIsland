package miui.systemui.dynamicisland.window.content;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandShareData {
    public static final DynamicIslandShareData INSTANCE = new DynamicIslandShareData();
    private static ShareProviderData shareProviderData = new ShareProviderData();

    private DynamicIslandShareData() {
    }

    public final ShareProviderData getShareProviderData() {
        return shareProviderData;
    }

    public final void setShareProviderData(ShareProviderData shareProviderData2) {
        n.g(shareProviderData2, "<set-?>");
        shareProviderData = shareProviderData2;
    }
}
