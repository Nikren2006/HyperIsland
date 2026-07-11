package androidx.window.embedding;

import android.app.Activity;
import androidx.window.core.ExperimentalWindowApi;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
public final class SplitInfo {
    private final ActivityStack primaryActivityStack;
    private final ActivityStack secondaryActivityStack;
    private final float splitRatio;

    public SplitInfo(ActivityStack primaryActivityStack, ActivityStack secondaryActivityStack, float f2) {
        n.g(primaryActivityStack, "primaryActivityStack");
        n.g(secondaryActivityStack, "secondaryActivityStack");
        this.primaryActivityStack = primaryActivityStack;
        this.secondaryActivityStack = secondaryActivityStack;
        this.splitRatio = f2;
    }

    public final boolean contains(Activity activity) {
        n.g(activity, "activity");
        return this.primaryActivityStack.contains(activity) || this.secondaryActivityStack.contains(activity);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitInfo)) {
            return false;
        }
        SplitInfo splitInfo = (SplitInfo) obj;
        return n.c(this.primaryActivityStack, splitInfo.primaryActivityStack) && n.c(this.secondaryActivityStack, splitInfo.secondaryActivityStack) && this.splitRatio == splitInfo.splitRatio;
    }

    public final ActivityStack getPrimaryActivityStack() {
        return this.primaryActivityStack;
    }

    public final ActivityStack getSecondaryActivityStack() {
        return this.secondaryActivityStack;
    }

    public final float getSplitRatio() {
        return this.splitRatio;
    }

    public int hashCode() {
        return (((this.primaryActivityStack.hashCode() * 31) + this.secondaryActivityStack.hashCode()) * 31) + Float.hashCode(this.splitRatio);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SplitInfo:{");
        sb.append("primaryActivityStack=" + getPrimaryActivityStack() + ',');
        sb.append("secondaryActivityStack=" + getSecondaryActivityStack() + ',');
        sb.append("splitRatio=" + getSplitRatio() + '}');
        String string = sb.toString();
        n.f(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
