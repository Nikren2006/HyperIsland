package d0;

import android.util.Log;

/* JADX INFO: renamed from: d0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0321b {
    public static void a(Exception exc, StringBuilder sb, String str) {
        sb.append(exc.toString());
        Log.e(str, sb.toString());
    }
}
