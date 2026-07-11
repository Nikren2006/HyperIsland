package y1;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public static Intent a(Context context, Intent intent, String str, boolean z2) {
        Intent intent2 = new Intent("android.settings.SETTINGS");
        if (z2) {
            intent2.addFlags(268435456);
        }
        intent2.putExtra("split_page_intent", new a(intent, str));
        return intent2;
    }
}
