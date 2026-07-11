package miui.systemui.notification.unimportant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes4.dex */
public class IOUtils {
    private final int DEFAULT_BUFFER_SIZE = 4096;
    private final ThreadLocal<SoftReference<byte[]>> THREAD_LOCAL_BYTE_BUFFER = new ThreadLocal<>();

    private byte[] getByteArrayBuffer() {
        SoftReference<byte[]> softReference = this.THREAD_LOCAL_BYTE_BUFFER.get();
        byte[] bArr = softReference != null ? softReference.get() : null;
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[4096];
        this.THREAD_LOCAL_BYTE_BUFFER.set(new SoftReference<>(bArr2));
        return bArr2;
    }

    public long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] byteArrayBuffer = getByteArrayBuffer();
        long j2 = 0;
        while (true) {
            int i2 = inputStream.read(byteArrayBuffer);
            if (i2 == -1) {
                outputStream.flush();
                return j2;
            }
            outputStream.write(byteArrayBuffer, 0, i2);
            j2 += (long) i2;
        }
    }
}
