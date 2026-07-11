package androidx.window.embedding;

import I0.u;
import android.content.Intent;
import androidx.window.core.ExperimentalWindowApi;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@ExperimentalWindowApi
public final class SplitPlaceholderRule extends SplitRule {
    private final Set<ActivityFilter> filters;
    private final Intent placeholderIntent;

    public /* synthetic */ SplitPlaceholderRule(Set set, Intent intent, int i2, int i3, float f2, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, intent, (i5 & 4) != 0 ? 0 : i2, (i5 & 8) != 0 ? 0 : i3, (i5 & 16) != 0 ? 0.5f : f2, (i5 & 32) != 0 ? 3 : i4);
    }

    @Override // androidx.window.embedding.SplitRule
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPlaceholderRule) || !super.equals(obj) || !super.equals(obj)) {
            return false;
        }
        SplitPlaceholderRule splitPlaceholderRule = (SplitPlaceholderRule) obj;
        return n.c(this.filters, splitPlaceholderRule.filters) && n.c(this.placeholderIntent, splitPlaceholderRule.placeholderIntent);
    }

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public final Intent getPlaceholderIntent() {
        return this.placeholderIntent;
    }

    @Override // androidx.window.embedding.SplitRule
    public int hashCode() {
        return (((super.hashCode() * 31) + this.filters.hashCode()) * 31) + this.placeholderIntent.hashCode();
    }

    public final SplitPlaceholderRule plus$window_release(ActivityFilter filter) {
        n.g(filter, "filter");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(filter);
        return new SplitPlaceholderRule(u.o0(linkedHashSet), this.placeholderIntent, getMinWidth(), getMinSmallestWidth(), getSplitRatio(), getLayoutDirection());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPlaceholderRule(Set<ActivityFilter> filters, Intent placeholderIntent, int i2, int i3, float f2, int i4) {
        super(i2, i3, f2, i4);
        n.g(filters, "filters");
        n.g(placeholderIntent, "placeholderIntent");
        this.placeholderIntent = placeholderIntent;
        this.filters = u.o0(filters);
    }
}
