package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
class AppLocalesStorageHelper {
    static final String APPLICATION_LOCALES_RECORD_FILE = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file";
    static final String APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME = "androidx.appcompat.app.AppLocalesMetadataHolderService";
    static final String LOCALE_RECORD_ATTRIBUTE_TAG = "application_locales";
    static final String LOCALE_RECORD_FILE_TAG = "locales";
    static final String TAG = "AppLocalesStorageHelper";

    public static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock = new Object();
        final Queue<Runnable> mTasks = new ArrayDeque();

        public SerialExecutor(Executor executor) {
            this.mExecutor = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$execute$0(Runnable runnable) {
            try {
                runnable.run();
            } finally {
                scheduleNext();
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable runnable) {
            synchronized (this.mLock) {
                try {
                    this.mTasks.add(new Runnable() { // from class: androidx.appcompat.app.e
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f1135a.lambda$execute$0(runnable);
                        }
                    });
                    if (this.mActive == null) {
                        scheduleNext();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void scheduleNext() {
            synchronized (this.mLock) {
                try {
                    Runnable runnablePoll = this.mTasks.poll();
                    this.mActive = runnablePoll;
                    if (runnablePoll != null) {
                        this.mExecutor.execute(runnablePoll);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static class ThreadPerTaskExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    private AppLocalesStorageHelper() {
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void persistLocales(@androidx.annotation.NonNull android.content.Context r6, @androidx.annotation.NonNull java.lang.String r7) {
        /*
            java.lang.String r0 = "locales"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            boolean r2 = r7.equals(r2)
            java.lang.String r3 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            if (r2 == 0) goto L12
            r6.deleteFile(r3)
            return
        L12:
            r2 = 0
            java.io.FileOutputStream r6 = r6.openFileOutput(r3, r2)     // Catch: java.io.FileNotFoundException -> L74
            org.xmlpull.v1.XmlSerializer r2 = android.util.Xml.newSerializer()
            r3 = 0
            r2.setOutput(r6, r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r4 = "UTF-8"
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2.startDocument(r4, r5)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2.startTag(r3, r0)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r4 = "application_locales"
            r2.attribute(r3, r4, r7)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2.endTag(r3, r0)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2.endDocument()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r0.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r2 = "Storing App Locales : app-locales: "
            r0.append(r2)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r0.append(r7)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r2 = " persisted successfully."
            r0.append(r2)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            android.util.Log.d(r1, r0)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            if (r6 == 0) goto L6d
        L4f:
            r6.close()     // Catch: java.io.IOException -> L6d
            goto L6d
        L53:
            r7 = move-exception
            goto L6e
        L55:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53
            r2.<init>()     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = "Storing App Locales : Failed to persist app-locales: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L53
            r2.append(r7)     // Catch: java.lang.Throwable -> L53
            java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L53
            android.util.Log.w(r1, r7, r0)     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L6d
            goto L4f
        L6d:
            return
        L6e:
            if (r6 == 0) goto L73
            r6.close()     // Catch: java.io.IOException -> L73
        L73:
            throw r7
        L74:
            java.lang.String r6 = "Storing App Locales : FileNotFoundException: Cannot open file %s for writing "
            java.lang.Object[] r7 = new java.lang.Object[]{r3}
            java.lang.String r6 = java.lang.String.format(r6, r7)
            android.util.Log.w(r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppLocalesStorageHelper.persistLocales(android.content.Context, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r2 = r4.getAttributeValue(null, androidx.appcompat.app.AppLocalesStorageHelper.LOCALE_RECORD_ATTRIBUTE_TAG);
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0045 A[EXC_TOP_SPLITTER, PHI: r2
      0x0045: PHI (r2v2 java.lang.String) = (r2v0 java.lang.String), (r2v4 java.lang.String) binds: [B:25:0x004e, B:21:0x0043] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String readLocales(@androidx.annotation.NonNull android.content.Context r9) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            java.io.FileInputStream r3 = r9.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L76
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r5 = "UTF-8"
            r4.setInput(r3, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            int r5 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L17:
            int r6 = r4.next()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            r7 = 1
            if (r6 == r7) goto L43
            r7 = 3
            if (r6 != r7) goto L2a
            int r8 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r8 <= r5) goto L43
            goto L2a
        L28:
            r9 = move-exception
            goto L70
        L2a:
            if (r6 == r7) goto L17
            r7 = 4
            if (r6 != r7) goto L30
            goto L17
        L30:
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r7 = "locales"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r6 == 0) goto L17
            java.lang.String r5 = "application_locales"
            r6 = 0
            java.lang.String r2 = r4.getAttributeValue(r6, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L43:
            if (r3 == 0) goto L51
        L45:
            r3.close()     // Catch: java.io.IOException -> L51
            goto L51
        L49:
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r4)     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L51
            goto L45
        L51:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L6c
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Reading app Locales : Locales read from file: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file , appLocales: "
            r9.append(r0)
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            android.util.Log.d(r1, r9)
            goto L6f
        L6c:
            r9.deleteFile(r0)
        L6f:
            return r2
        L70:
            if (r3 == 0) goto L75
            r3.close()     // Catch: java.io.IOException -> L75
        L75:
            throw r9
        L76:
            java.lang.String r9 = "Reading app Locales : Locales record file not found: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppLocalesStorageHelper.readLocales(android.content.Context):java.lang.String");
    }

    public static void syncLocalesToFramework(Context context) {
        ComponentName componentName = new ComponentName(context, APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME);
        if (context.getPackageManager().getComponentEnabledSetting(componentName) != 1) {
            if (AppCompatDelegate.getApplicationLocales().isEmpty()) {
                String locales = readLocales(context);
                Object systemService = context.getSystemService("locale");
                if (systemService != null) {
                    AppCompatDelegate.Api33Impl.localeManagerSetApplicationLocales(systemService, AppCompatDelegate.Api24Impl.localeListForLanguageTags(locales));
                }
            }
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
        }
    }
}
