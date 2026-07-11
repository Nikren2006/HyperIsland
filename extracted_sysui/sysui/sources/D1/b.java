package D1;

import java.io.Closeable;
import java.io.Flushable;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes5.dex */
public interface b extends Closeable, Flushable, WritableByteChannel {
    b i(String str);

    b k(String str, int i2, int i3);

    b o(int i2);
}
