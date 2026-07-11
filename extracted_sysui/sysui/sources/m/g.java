package m;

import android.util.Pair;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import p.AbstractC0724d;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f5263a;

    public g(e eVar) {
        this.f5263a = eVar;
    }

    public static String b(String str, c cVar, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append("lottie_cache_");
        sb.append(str.replaceAll("\\W+", ""));
        sb.append(z2 ? cVar.a() : cVar.f5262a);
        return sb.toString();
    }

    public Pair a(String str) {
        try {
            File fileC = c(str);
            if (fileC == null) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(fileC);
            c cVar = fileC.getAbsolutePath().endsWith(".zip") ? c.ZIP : c.JSON;
            AbstractC0724d.a("Cache hit for " + str + " at " + fileC.getAbsolutePath());
            return new Pair(cVar, fileInputStream);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public final File c(String str) {
        File file = new File(d(), b(str, c.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(d(), b(str, c.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        return null;
    }

    public final File d() {
        File fileA = this.f5263a.a();
        if (fileA.isFile()) {
            fileA.delete();
        }
        if (!fileA.exists()) {
            fileA.mkdirs();
        }
        return fileA;
    }

    public void e(String str, c cVar) {
        File file = new File(d(), b(str, cVar, true));
        File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean zRenameTo = file.renameTo(file2);
        AbstractC0724d.a("Copying temp file to real file (" + file2 + ")");
        if (zRenameTo) {
            return;
        }
        AbstractC0724d.c("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + ".");
    }

    public File f(String str, InputStream inputStream, c cVar) throws IOException {
        File file = new File(d(), b(str, cVar, true));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i2 = inputStream.read(bArr);
                    if (i2 == -1) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return file;
                    }
                    fileOutputStream.write(bArr, 0, i2);
                }
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } finally {
            inputStream.close();
        }
    }
}
