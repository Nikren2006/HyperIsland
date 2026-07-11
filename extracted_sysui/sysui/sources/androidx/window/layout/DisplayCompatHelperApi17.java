package androidx.window.layout;

import android.graphics.Point;
import android.view.Display;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(17)
public final class DisplayCompatHelperApi17 {
    public static final DisplayCompatHelperApi17 INSTANCE = new DisplayCompatHelperApi17();

    private DisplayCompatHelperApi17() {
    }

    public final void getRealSize(Display display, Point point) {
        n.g(display, "display");
        n.g(point, "point");
        display.getRealSize(point);
    }
}
