package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteCursorCompat {

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor, boolean z2) {
            sQLiteCursor.setFillWindowForwardOnly(z2);
        }
    }

    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(SQLiteCursor sQLiteCursor, boolean z2) {
        Api28Impl.setFillWindowForwardOnly(sQLiteCursor, z2);
    }
}
