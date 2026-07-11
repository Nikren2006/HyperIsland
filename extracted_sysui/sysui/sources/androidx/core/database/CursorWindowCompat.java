package androidx.core.database;

import android.database.CursorWindow;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class CursorWindowCompat {

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static CursorWindow createCursorWindow(String str, long j2) {
            return new CursorWindow(str, j2);
        }
    }

    private CursorWindowCompat() {
    }

    public static CursorWindow create(String str, long j2) {
        return Api28Impl.createCursorWindow(str, j2);
    }
}
