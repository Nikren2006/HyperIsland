package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.m;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteDatabaseKt {
    public static final <T> T transaction(SQLiteDatabase sQLiteDatabase, boolean z2, Function1 function1) {
        if (z2) {
            sQLiteDatabase.beginTransaction();
        } else {
            sQLiteDatabase.beginTransactionNonExclusive();
        }
        try {
            T t2 = (T) function1.invoke(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            return t2;
        } finally {
            m.b(1);
            sQLiteDatabase.endTransaction();
            m.a(1);
        }
    }

    public static /* synthetic */ Object transaction$default(SQLiteDatabase sQLiteDatabase, boolean z2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        if (z2) {
            sQLiteDatabase.beginTransaction();
        } else {
            sQLiteDatabase.beginTransactionNonExclusive();
        }
        try {
            Object objInvoke = function1.invoke(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            return objInvoke;
        } finally {
            m.b(1);
            sQLiteDatabase.endTransaction();
            m.a(1);
        }
    }
}
