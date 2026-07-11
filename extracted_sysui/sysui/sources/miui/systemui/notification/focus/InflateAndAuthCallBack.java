package miui.systemui.notification.focus;

/* JADX INFO: loaded from: classes4.dex */
public interface InflateAndAuthCallBack {
    void onAuthFailed(String str, String str2);

    void onAuthFinish(String str, String str2);

    void onAuthSuccess(String str, String str2);

    void onInflateFailed(String str);

    void onInflateFinish(String str);

    void onInflateSuccess(String str);
}
