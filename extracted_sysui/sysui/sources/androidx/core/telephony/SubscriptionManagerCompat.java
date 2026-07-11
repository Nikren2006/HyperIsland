package androidx.core.telephony;

import android.telephony.SubscriptionManager;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
public class SubscriptionManagerCompat {
    private static Method sGetSlotIndexMethod;

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static int getSlotIndex(int i2) {
            return SubscriptionManager.getSlotIndex(i2);
        }
    }

    private SubscriptionManagerCompat() {
    }

    public static int getSlotIndex(int i2) {
        if (i2 == -1) {
            return -1;
        }
        return Api29Impl.getSlotIndex(i2);
    }
}
