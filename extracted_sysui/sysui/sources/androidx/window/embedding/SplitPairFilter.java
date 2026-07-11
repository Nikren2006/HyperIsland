package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import androidx.window.core.ExperimentalWindowApi;
import f1.o;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
public final class SplitPairFilter {
    private final ComponentName primaryActivityName;
    private final String secondaryActivityIntentAction;
    private final ComponentName secondaryActivityName;

    public SplitPairFilter(ComponentName primaryActivityName, ComponentName secondaryActivityName, String str) {
        String str2;
        Object obj;
        int i2;
        boolean z2;
        CharSequence charSequence;
        n.g(primaryActivityName, "primaryActivityName");
        n.g(secondaryActivityName, "secondaryActivityName");
        this.primaryActivityName = primaryActivityName;
        this.secondaryActivityName = secondaryActivityName;
        this.secondaryActivityIntentAction = str;
        String packageName = primaryActivityName.getPackageName();
        n.f(packageName, "primaryActivityName.packageName");
        String className = primaryActivityName.getClassName();
        n.f(className, "primaryActivityName.className");
        String packageName2 = secondaryActivityName.getPackageName();
        n.f(packageName2, "secondaryActivityName.packageName");
        String className2 = secondaryActivityName.getClassName();
        n.f(className2, "secondaryActivityName.className");
        if (packageName.length() == 0 || packageName2.length() == 0) {
            throw new IllegalArgumentException("Package name must not be empty");
        }
        if (className.length() == 0 || className2.length() == 0) {
            throw new IllegalArgumentException("Activity class name must not be empty.");
        }
        if (o.v(packageName, "*", false, 2, null) && o.E(packageName, "*", 0, false, 6, null) != packageName.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        }
        if (o.v(className, "*", false, 2, null)) {
            str2 = "Wildcard in package name is only allowed at the end.";
            obj = null;
            i2 = 2;
            z2 = false;
            charSequence = "*";
            if (o.E(className, "*", 0, false, 6, null) != className.length() - 1) {
                throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
            }
        } else {
            str2 = "Wildcard in package name is only allowed at the end.";
            obj = null;
            i2 = 2;
            z2 = false;
            charSequence = "*";
        }
        if (o.v(packageName2, charSequence, z2, i2, obj) && o.E(packageName2, "*", 0, false, 6, null) != packageName2.length() - 1) {
            throw new IllegalArgumentException(str2);
        }
        if (o.v(className2, charSequence, z2, i2, obj) && o.E(className2, "*", 0, false, 6, null) != className2.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPairFilter)) {
            return false;
        }
        SplitPairFilter splitPairFilter = (SplitPairFilter) obj;
        return n.c(this.primaryActivityName, splitPairFilter.primaryActivityName) && n.c(this.secondaryActivityName, splitPairFilter.secondaryActivityName) && n.c(this.secondaryActivityIntentAction, splitPairFilter.secondaryActivityIntentAction);
    }

    public final ComponentName getPrimaryActivityName() {
        return this.primaryActivityName;
    }

    public final String getSecondaryActivityIntentAction() {
        return this.secondaryActivityIntentAction;
    }

    public final ComponentName getSecondaryActivityName() {
        return this.secondaryActivityName;
    }

    public int hashCode() {
        int iHashCode = ((this.primaryActivityName.hashCode() * 31) + this.secondaryActivityName.hashCode()) * 31;
        String str = this.secondaryActivityIntentAction;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean matchesActivityIntentPair(Activity primaryActivity, Intent secondaryActivityIntent) {
        n.g(primaryActivity, "primaryActivity");
        n.g(secondaryActivityIntent, "secondaryActivityIntent");
        ComponentName componentName = primaryActivity.getComponentName();
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        if (!matcherUtils.areComponentsMatching$window_release(componentName, this.primaryActivityName) || !matcherUtils.areComponentsMatching$window_release(secondaryActivityIntent.getComponent(), this.secondaryActivityName)) {
            return false;
        }
        String str = this.secondaryActivityIntentAction;
        return str == null || n.c(str, secondaryActivityIntent.getAction());
    }

    public final boolean matchesActivityPair(Activity primaryActivity, Activity secondaryActivity) {
        n.g(primaryActivity, "primaryActivity");
        n.g(secondaryActivity, "secondaryActivity");
        MatcherUtils matcherUtils = MatcherUtils.INSTANCE;
        boolean z2 = false;
        boolean z3 = matcherUtils.areComponentsMatching$window_release(primaryActivity.getComponentName(), this.primaryActivityName) && matcherUtils.areComponentsMatching$window_release(secondaryActivity.getComponentName(), this.secondaryActivityName);
        if (secondaryActivity.getIntent() == null) {
            return z3;
        }
        if (z3) {
            Intent intent = secondaryActivity.getIntent();
            n.f(intent, "secondaryActivity.intent");
            if (matchesActivityIntentPair(primaryActivity, intent)) {
                z2 = true;
            }
        }
        return z2;
    }

    public String toString() {
        return "SplitPairFilter{primaryActivityName=" + this.primaryActivityName + ", secondaryActivityName=" + this.secondaryActivityName + ", secondaryActivityAction=" + ((Object) this.secondaryActivityIntentAction) + '}';
    }
}
