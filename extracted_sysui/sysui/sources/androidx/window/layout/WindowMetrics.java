package androidx.window.layout;

import android.graphics.Rect;
import androidx.annotation.RestrictTo;
import androidx.window.core.Bounds;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class WindowMetrics {
    private final Bounds _bounds;

    public WindowMetrics(Bounds _bounds) {
        n.g(_bounds, "_bounds");
        this._bounds = _bounds;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !n.c(WindowMetrics.class, obj.getClass())) {
            return false;
        }
        return n.c(this._bounds, ((WindowMetrics) obj)._bounds);
    }

    public final Rect getBounds() {
        return this._bounds.toRect();
    }

    public int hashCode() {
        return this._bounds.hashCode();
    }

    public String toString() {
        return "WindowMetrics { bounds: " + getBounds() + " }";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @RestrictTo({RestrictTo.Scope.TESTS})
    public WindowMetrics(Rect bounds) {
        this(new Bounds(bounds));
        n.g(bounds, "bounds");
    }
}
