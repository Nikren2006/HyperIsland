package miuix.appcompat.app.floatingactivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.HashMap;
import miuix.appcompat.app.floatingactivity.multiapp.IFloatingService;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MemoryFileUtil {
    public static final String KEY_FD = "parcelFile";
    public static final String KEY_HEIGHT = "key_height";
    public static final String KEY_LENGTH = "parcelFileLength";
    public static final String KEY_WIDTH = "key_width";
    private static final String TAG = "MemoryFileUtil";

    public static Bitmap readBitmap(Bundle bundle) throws Throwable {
        HashMap map = (HashMap) bundle.getSerializable(KEY_FD);
        int i2 = bundle.getInt(KEY_LENGTH);
        int i3 = bundle.getInt(KEY_WIDTH);
        int i4 = bundle.getInt(KEY_HEIGHT);
        byte[] fromMemory = readFromMemory(map, i2);
        Bitmap bitmapCreateBitmap = null;
        if (fromMemory == null) {
            Log.d(TAG, "getSnapShot with data is null");
            return null;
        }
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(fromMemory));
            return bitmapCreateBitmap;
        } catch (IllegalArgumentException e2) {
            Log.w(TAG, "catch illegal argument exception", e2);
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError e3) {
            Log.w(TAG, "catch oom exception", e3);
            return bitmapCreateBitmap;
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0031: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:17:0x0031 */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] readFromMemory(java.util.HashMap<java.lang.String, android.os.ParcelFileDescriptor> r6, int r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "catch close fd error"
            java.lang.String r1 = "catch close FileInputStream error"
            java.lang.String r2 = "MemoryFileUtil"
            java.lang.String r3 = "parcelFile"
            java.lang.Object r6 = r6.get(r3)
            android.os.ParcelFileDescriptor r6 = (android.os.ParcelFileDescriptor) r6
            r3 = 0
            if (r6 == 0) goto L64
            byte[] r7 = new byte[r7]
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            java.io.FileDescriptor r5 = r6.getFileDescriptor()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r4.read(r7)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L33
            r4.close()     // Catch: java.io.IOException -> L23
            goto L27
        L23:
            r3 = move-exception
            android.util.Log.w(r2, r1, r3)
        L27:
            r6.close()     // Catch: java.io.IOException -> L2b
            goto L2f
        L2b:
            r6 = move-exception
            android.util.Log.w(r2, r0, r6)
        L2f:
            return r7
        L30:
            r7 = move-exception
            r3 = r4
            goto L51
        L33:
            r7 = move-exception
            goto L39
        L35:
            r7 = move-exception
            goto L51
        L37:
            r7 = move-exception
            r4 = r3
        L39:
            java.lang.String r5 = "catch read from memory error"
            android.util.Log.w(r2, r5, r7)     // Catch: java.lang.Throwable -> L30
            if (r4 == 0) goto L64
            r4.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r7 = move-exception
            android.util.Log.w(r2, r1, r7)
        L48:
            r6.close()     // Catch: java.io.IOException -> L4c
            goto L64
        L4c:
            r6 = move-exception
            android.util.Log.w(r2, r0, r6)
            goto L64
        L51:
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r3 = move-exception
            android.util.Log.w(r2, r1, r3)
        L5b:
            r6.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r6 = move-exception
            android.util.Log.w(r2, r0, r6)
        L63:
            throw r7
        L64:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.app.floatingactivity.MemoryFileUtil.readFromMemory(java.util.HashMap, int):byte[]");
    }

    public static void sendToFdServer(IFloatingService iFloatingService, byte[] bArr, int i2, int i3, int i4, String str, int i5) throws Throwable {
        ParcelFileDescriptor parcelFileDescriptorWriteToMemory = writeToMemory(bArr, i2);
        HashMap map = new HashMap(1);
        map.put(KEY_FD, parcelFileDescriptorWriteToMemory);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FD, map);
        bundle.putInt(KEY_LENGTH, i2);
        bundle.putInt(KEY_WIDTH, i3);
        bundle.putInt(KEY_HEIGHT, i4);
        bundle.putInt(MethodCodeHelper.KEY_TASK_ID, i5);
        bundle.putString(MethodCodeHelper.KEY_REQUEST_IDENTITY, str);
        if (iFloatingService != null) {
            try {
                iFloatingService.callServiceMethod(7, bundle);
            } catch (RemoteException e2) {
                Log.w(TAG, "catch stash snapshot to service error", e2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.os.ParcelFileDescriptor writeToMemory(byte[] r3, int r4) throws java.lang.Throwable {
        /*
            r0 = 0
            android.os.MemoryFile r1 = new android.os.MemoryFile     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2d
            java.lang.String r2 = ""
            r1.<init>(r2, r4)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2d
            r2 = 0
            r1.writeBytes(r3, r2, r2, r4)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            java.lang.Class<android.os.MemoryFile> r3 = android.os.MemoryFile.class
            java.lang.String r4 = "getFileDescriptor"
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            r4 = 1
            r3.setAccessible(r4)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            java.lang.Object r3 = r3.invoke(r1, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            java.io.FileDescriptor r3 = (java.io.FileDescriptor) r3     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            android.os.ParcelFileDescriptor r0 = android.os.ParcelFileDescriptor.dup(r3)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
        L22:
            r1.close()
            goto L39
        L26:
            r3 = move-exception
            r0 = r1
            goto L3a
        L29:
            r3 = move-exception
            goto L2f
        L2b:
            r3 = move-exception
            goto L3a
        L2d:
            r3 = move-exception
            r1 = r0
        L2f:
            java.lang.String r4 = "MemoryFileUtil"
            java.lang.String r2 = "catch write to memory error"
            android.util.Log.w(r4, r2, r3)     // Catch: java.lang.Throwable -> L26
            if (r1 == 0) goto L39
            goto L22
        L39:
            return r0
        L3a:
            if (r0 == 0) goto L3f
            r0.close()
        L3f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.app.floatingactivity.MemoryFileUtil.writeToMemory(byte[], int):android.os.ParcelFileDescriptor");
    }
}
