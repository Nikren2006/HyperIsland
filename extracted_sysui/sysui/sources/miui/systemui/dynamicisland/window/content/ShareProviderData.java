package miui.systemui.dynamicisland.window.content;

/* JADX INFO: loaded from: classes3.dex */
public final class ShareProviderData {
    private String packageName;
    private byte[] shareIconByteArray;
    private String shareMessage;
    private String shareUrl;
    private String title;
    private int value = -1;

    public final String getPackageName() {
        return this.packageName;
    }

    public final byte[] getShareIconByteArray() {
        return this.shareIconByteArray;
    }

    public final String getShareMessage() {
        return this.shareMessage;
    }

    public final String getShareUrl() {
        return this.shareUrl;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getValue() {
        return this.value;
    }

    public final void setPackageName(String str) {
        this.packageName = str;
    }

    public final void setShareIconByteArray(byte[] bArr) {
        this.shareIconByteArray = bArr;
    }

    public final void setShareMessage(String str) {
        this.shareMessage = str;
    }

    public final void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final void setValue(int i2) {
        this.value = i2;
    }
}
