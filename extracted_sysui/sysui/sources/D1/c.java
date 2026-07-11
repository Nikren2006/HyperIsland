package D1;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: loaded from: classes5.dex */
public interface c extends l, ReadableByteChannel {
    a b();

    int p(f fVar);

    c peek();

    long q(d dVar);

    byte readByte();

    boolean request(long j2);

    long v(d dVar);

    InputStream y();
}
