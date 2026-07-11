package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
class GhostViewUtils {
    private GhostViewUtils() {
    }

    @Nullable
    public static GhostView addGhost(@NonNull View view, @NonNull ViewGroup viewGroup, @Nullable Matrix matrix) {
        return GhostViewPort.addGhost(view, viewGroup, matrix);
    }

    public static void removeGhost(View view) {
        GhostViewPort.removeGhost(view);
    }
}
