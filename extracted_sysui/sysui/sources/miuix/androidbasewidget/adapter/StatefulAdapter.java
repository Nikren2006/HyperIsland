package miuix.androidbasewidget.adapter;

import android.os.Parcelable;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes4.dex */
public interface StatefulAdapter {
    void restoreState(@NonNull Parcelable parcelable);

    @NonNull
    Parcelable saveState();
}
