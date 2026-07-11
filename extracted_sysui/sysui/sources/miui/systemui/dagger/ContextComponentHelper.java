package miui.systemui.dagger;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;

/* JADX INFO: loaded from: classes.dex */
public interface ContextComponentHelper {
    Activity resolveActivity(String str);

    BroadcastReceiver resolveBroadcastReceiver(String str);

    Service resolveService(String str);
}
