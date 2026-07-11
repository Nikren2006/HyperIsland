package androidx.window.embedding;

import I0.u;
import androidx.window.core.ExperimentalWindowApi;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
public final class ActivityRule extends EmbeddingRule {
    private final boolean alwaysExpand;
    private final Set<ActivityFilter> filters;

    public /* synthetic */ ActivityRule(Set set, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i2 & 2) != 0 ? false : z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityRule)) {
            return false;
        }
        ActivityRule activityRule = (ActivityRule) obj;
        return n.c(this.filters, activityRule.filters) && this.alwaysExpand == activityRule.alwaysExpand;
    }

    public final boolean getAlwaysExpand() {
        return this.alwaysExpand;
    }

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public int hashCode() {
        return (this.filters.hashCode() * 31) + Boolean.hashCode(this.alwaysExpand);
    }

    public final ActivityRule plus$window_release(ActivityFilter filter) {
        n.g(filter, "filter");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(filter);
        return new ActivityRule(u.o0(linkedHashSet), this.alwaysExpand);
    }

    public ActivityRule(Set<ActivityFilter> filters, boolean z2) {
        n.g(filters, "filters");
        this.alwaysExpand = z2;
        this.filters = u.o0(filters);
    }
}
