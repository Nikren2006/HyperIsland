package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.TypedArrayUtils;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // androidx.preference.Preference
    public boolean isEnabled() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(@NonNull PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setAccessibilityHeading(true);
    }

    @Override // androidx.preference.Preference
    public boolean shouldDisableDependents() {
        return !super.isEnabled();
    }

    public PreferenceCategory(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PreferenceCategory(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceCategoryStyle, android.R.attr.preferenceCategoryStyle));
    }

    public PreferenceCategory(@NonNull Context context) {
        this(context, null);
    }
}
