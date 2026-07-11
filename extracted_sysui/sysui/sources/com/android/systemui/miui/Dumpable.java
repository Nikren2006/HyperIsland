package com.android.systemui.miui;

import androidx.annotation.NonNull;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes2.dex */
public interface Dumpable {
    void dump(@NonNull FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @NonNull String[] strArr);
}
