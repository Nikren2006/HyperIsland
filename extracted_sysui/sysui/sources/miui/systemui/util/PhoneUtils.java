package miui.systemui.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.UserHandle;
import android.telecom.TelecomManager;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class PhoneUtils {
    private static final int ENTRY_TYPE_POWER_MENU = 2;
    private static final String EXTRA_ENTRY_TYPE = "com.android.phone.EmergencyDialer.extra.ENTRY_TYPE";
    private static final String INDIA_EMERGENCY_CALL_NUMBER = "112";
    private static final String TAG = "PhoneUtils";

    private static TelecomManager getTelecomManager(Context context) {
        return (TelecomManager) context.getSystemService("telecom");
    }

    public static void takeEmergencyCallAction(Context context) {
        if (getTelecomManager(context) == null) {
            Log.wtf(TAG, "TelecomManager was null, cannot launch emergency dialer");
            return;
        }
        Intent intent = new Intent("android.intent.action.CALL_EMERGENCY");
        intent.setData(Uri.fromParts("tel", INDIA_EMERGENCY_CALL_NUMBER, null));
        intent.setFlags(343932928);
        intent.putExtra(EXTRA_ENTRY_TYPE, 2);
        context.startActivityAsUser(intent, UserHandle.CURRENT);
    }
}
