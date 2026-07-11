package androidx.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class AtomicFileKt {
    public static final byte[] readBytes(android.util.AtomicFile atomicFile) {
        return atomicFile.readFully();
    }

    public static final String readText(android.util.AtomicFile atomicFile, Charset charset) {
        return new String(atomicFile.readFully(), charset);
    }

    public static /* synthetic */ String readText$default(android.util.AtomicFile atomicFile, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = f1.c.f4238b;
        }
        return readText(atomicFile, charset);
    }

    public static final void tryWrite(android.util.AtomicFile atomicFile, Function1 function1) throws IOException {
        FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
        try {
            function1.invoke(fileOutputStreamStartWrite);
            m.b(1);
            atomicFile.finishWrite(fileOutputStreamStartWrite);
            m.a(1);
        } catch (Throwable th) {
            m.b(1);
            atomicFile.failWrite(fileOutputStreamStartWrite);
            m.a(1);
            throw th;
        }
    }

    public static final void writeBytes(android.util.AtomicFile atomicFile, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
        try {
            fileOutputStreamStartWrite.write(bArr);
            atomicFile.finishWrite(fileOutputStreamStartWrite);
        } catch (Throwable th) {
            atomicFile.failWrite(fileOutputStreamStartWrite);
            throw th;
        }
    }

    public static final void writeText(android.util.AtomicFile atomicFile, String str, Charset charset) throws IOException {
        byte[] bytes = str.getBytes(charset);
        n.f(bytes, "this as java.lang.String).getBytes(charset)");
        writeBytes(atomicFile, bytes);
    }

    public static /* synthetic */ void writeText$default(android.util.AtomicFile atomicFile, String str, Charset charset, int i2, Object obj) throws IOException {
        if ((i2 & 2) != 0) {
            charset = f1.c.f4238b;
        }
        writeText(atomicFile, str, charset);
    }
}
