package miui.systemui.notification.auth;

import android.content.Context;
import miui.systemui.notification.focus.InflateAndAuthCallBack;

/* JADX INFO: loaded from: classes4.dex */
public interface IAuthManager {
    void auth(Context context, String str, String str2, InflateAndAuthCallBack inflateAndAuthCallBack);

    void connect(Context context);

    void disConnect(Context context);

    boolean isConnected();

    boolean syncAuth(Context context, String str);

    boolean syncAuthTimeout(Context context, String str, long j2);
}
