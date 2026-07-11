package miui.systemui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes2.dex */
public interface Dumpable {
    void dump(@Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @NonNull String[] strArr);
}
