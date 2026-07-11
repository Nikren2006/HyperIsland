package androidx.window.layout;

import I0.u;
import androidx.annotation.RestrictTo;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class WindowLayoutInfo {
    private final List<DisplayFeature> displayFeatures;

    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({RestrictTo.Scope.TESTS})
    public WindowLayoutInfo(List<? extends DisplayFeature> displayFeatures) {
        n.g(displayFeatures, "displayFeatures");
        this.displayFeatures = displayFeatures;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !n.c(WindowLayoutInfo.class, obj.getClass())) {
            return false;
        }
        return n.c(this.displayFeatures, ((WindowLayoutInfo) obj).displayFeatures);
    }

    public final List<DisplayFeature> getDisplayFeatures() {
        return this.displayFeatures;
    }

    public int hashCode() {
        return this.displayFeatures.hashCode();
    }

    public String toString() {
        return u.T(this.displayFeatures, ", ", "WindowLayoutInfo{ DisplayFeatures[", "] }", 0, null, null, 56, null);
    }
}
