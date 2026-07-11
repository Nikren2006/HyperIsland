package miuix.core.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import miuix.core.util.DirectIndexedFile;

/* JADX INFO: loaded from: classes3.dex */
public class DirectIndexedFileExtractor {
    private static final String IDF_DIR_NAME = "idf";
    public static String IDF_FILES_PATH = null;
    private static final String IDF_FILE_EXT = ".idf";
    private static final String TAG = "DirectIndexedFileExt";
    private static final int TARGET_DIR_MODE = 505;
    private static final int TARGET_FILE_MODE = 436;
    private static final String TMP_FILE_NAME_POSTFIX = "-tmp";

    private static void ensureIdfPath(Context context) {
        if (IDF_FILES_PATH == null) {
            try {
                Context contextCreatePackageContext = context.createPackageContext(context.getPackageName(), 2);
                try {
                    contextCreatePackageContext = (Context) contextCreatePackageContext.getClass().getMethod("createDeviceProtectedStorageContext", null).invoke(contextCreatePackageContext, null);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                }
                if (contextCreatePackageContext.getFilesDir() != null) {
                    IDF_FILES_PATH = contextCreatePackageContext.getFilesDir().getAbsolutePath() + File.separator + IDF_DIR_NAME;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        if (IDF_FILES_PATH == null) {
            Log.w(TAG, "Error: Cannot locate IDF_FILES_PATH");
            return;
        }
        File file = new File(IDF_FILES_PATH);
        if (file.exists() || !file.mkdirs()) {
            return;
        }
        try {
            Os.mkdir(IDF_FILES_PATH, 505);
        } catch (ErrnoException e3) {
            e3.printStackTrace();
        }
    }

    public static String getDirectIndexedFilePath(Context context, String str) {
        ensureIdfPath(context);
        if (IDF_FILES_PATH == null) {
            return null;
        }
        return IDF_FILES_PATH + File.separator + str;
    }

    private static boolean shouldOverwrite(Context context, String str, String str2) {
        try {
            DirectIndexedFile.Reader readerOpen = DirectIndexedFile.open(context.getAssets().open(str, 1));
            try {
                DirectIndexedFile.Reader readerOpen2 = DirectIndexedFile.open(str2);
                boolean z2 = readerOpen.getDataVersion() > readerOpen2.getDataVersion();
                readerOpen.close();
                readerOpen2.close();
                return z2;
            } catch (IOException e2) {
                e2.printStackTrace();
                readerOpen.close();
                return true;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }
}
