package miuix.mgl.utils;

import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes3.dex */
public class IOUtils {
    private IOUtils() {
        throw new IllegalStateException("Utility class");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:(2:32|7)|(3:34|8|(1:10))|30|14) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.nio.ByteBuffer loadBufferFromFile(java.lang.String r4) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r4 = r0.exists()
            r1 = 0
            if (r4 == 0) goto L3d
            boolean r4 = r0.isFile()
            if (r4 != 0) goto L13
            goto L3d
        L13:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
            int r0 = r4.available()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L3a
            byte[] r2 = new byte[r0]     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L3a
            int r3 = r4.read(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L3a
            if (r3 != r0) goto L2c
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L3a
            goto L2c
        L29:
            r0 = move-exception
            r1 = r4
            goto L34
        L2c:
            r4.close()     // Catch: java.io.IOException -> L3d
            goto L3d
        L30:
            r0 = move-exception
            goto L34
        L32:
            r4 = r1
            goto L3a
        L34:
            if (r1 == 0) goto L39
            r1.close()     // Catch: java.io.IOException -> L39
        L39:
            throw r0
        L3a:
            if (r4 == 0) goto L3d
            goto L2c
        L3d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.mgl.utils.IOUtils.loadBufferFromFile(java.lang.String):java.nio.ByteBuffer");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(2:28|3)|(3:30|4|(1:6))|24|10|20|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.nio.ByteBuffer loadBufferFromRes(int r3, android.content.res.Resources r4) throws java.lang.Throwable {
        /*
            r0 = 0
            android.util.TypedValue r1 = new android.util.TypedValue     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            r1.<init>()     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            java.io.InputStream r3 = r4.openRawResource(r3, r1)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            int r4 = r3.available()     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L2c
            byte[] r1 = new byte[r4]     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L2c
            int r2 = r3.read(r1)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L2c
            if (r2 != r4) goto L1e
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r1)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L2c
            goto L1e
        L1b:
            r4 = move-exception
            r0 = r3
            goto L26
        L1e:
            r3.close()     // Catch: java.io.IOException -> L2f
            goto L2f
        L22:
            r4 = move-exception
            goto L26
        L24:
            r3 = r0
            goto L2c
        L26:
            if (r0 == 0) goto L2b
            r0.close()     // Catch: java.io.IOException -> L2b
        L2b:
            throw r4
        L2c:
            if (r3 == 0) goto L2f
            goto L1e
        L2f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.mgl.utils.IOUtils.loadBufferFromRes(int, android.content.res.Resources):java.nio.ByteBuffer");
    }

    public static String readTextFileFromResource(int i2, Resources resources) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resources.openRawResource(i2)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(line);
                sb.append('\n');
            }
        } catch (Resources.NotFoundException e2) {
            throw new RuntimeException("Resource not found: " + i2, e2);
        } catch (IOException e3) {
            throw new RuntimeException("Could not open resource: " + i2, e3);
        }
    }
}
