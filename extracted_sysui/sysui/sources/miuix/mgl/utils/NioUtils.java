package miuix.mgl.utils;

import androidx.annotation.NonNull;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: classes3.dex */
final class NioUtils {

    public enum BufferType {
        BYTE,
        CHAR,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE
    }

    private NioUtils() {
    }

    public static Object getBaseArray(@NonNull Buffer buffer) {
        if (buffer.hasArray()) {
            return buffer.array();
        }
        return null;
    }

    public static int getBaseArrayOffset(@NonNull Buffer buffer, int i2) {
        if (buffer.hasArray()) {
            return (buffer.arrayOffset() + buffer.position()) << i2;
        }
        return 0;
    }

    public static long getBasePointer(@NonNull Buffer buffer, long j2, int i2) {
        if (j2 != 0) {
            return ((long) (buffer.position() << i2)) + j2;
        }
        return 0L;
    }

    public static int getBufferType(@NonNull Buffer buffer) {
        return buffer instanceof ByteBuffer ? BufferType.BYTE.ordinal() : buffer instanceof CharBuffer ? BufferType.CHAR.ordinal() : buffer instanceof ShortBuffer ? BufferType.SHORT.ordinal() : buffer instanceof IntBuffer ? BufferType.INT.ordinal() : buffer instanceof LongBuffer ? BufferType.LONG.ordinal() : buffer instanceof FloatBuffer ? BufferType.FLOAT.ordinal() : BufferType.DOUBLE.ordinal();
    }
}
