package androidx.core.net;

import android.net.Uri;
import java.io.File;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class UriKt {
    public static final File toFile(Uri uri) {
        if (!n.c(uri.getScheme(), "file")) {
            throw new IllegalArgumentException(("Uri lacks 'file' scheme: " + uri).toString());
        }
        String path = uri.getPath();
        if (path != null) {
            return new File(path);
        }
        throw new IllegalArgumentException(("Uri path is null: " + uri).toString());
    }

    public static final Uri toUri(String str) {
        return Uri.parse(str);
    }

    public static final Uri toUri(File file) {
        return Uri.fromFile(file);
    }
}
