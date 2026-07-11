package D1;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f85a = Logger.getLogger(e.class.getName());

    public class a implements l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ m f86a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ InputStream f87b;

        public a(m mVar, InputStream inputStream) {
            this.f86a = mVar;
            this.f87b = inputStream;
        }

        @Override // D1.l, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            this.f87b.close();
        }

        @Override // D1.l
        public long g(D1.a aVar, long j2) throws IOException {
            if (j2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j2);
            }
            if (j2 == 0) {
                return 0L;
            }
            try {
                this.f86a.a();
                i iVarI = aVar.I(1);
                int i2 = this.f87b.read(iVarI.f100a, iVarI.f102c, (int) Math.min(j2, 8192 - iVarI.f102c));
                if (i2 == -1) {
                    return -1L;
                }
                iVarI.f102c += i2;
                long j3 = i2;
                aVar.f78b += j3;
                return j3;
            } catch (AssertionError e2) {
                if (e.b(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }

        public String toString() {
            return "source(" + this.f87b + ")";
        }
    }

    public static c a(l lVar) {
        return new h(lVar);
    }

    public static boolean b(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static l c(InputStream inputStream) {
        return d(inputStream, new m());
    }

    public static l d(InputStream inputStream, m mVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (mVar != null) {
            return new a(mVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }
}
