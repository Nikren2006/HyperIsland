package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miuix.animation.internal.FolmeCore;

/* JADX INFO: loaded from: classes.dex */
final class ExpandButton extends Preference {
    private long mId;

    public ExpandButton(@NonNull Context context, List<Preference> list, long j2) {
        super(context);
        initLayout();
        setSummary(list);
        this.mId = j2 + FolmeCore.NANOS_TO_MS;
    }

    private void initLayout() {
        setLayoutResource(R.layout.expand_button);
        setIcon(R.drawable.ic_arrow_down_24dp);
        setTitle(R.string.expand_button_title);
        setOrder(DynamicIslandConstants.USER_XSPACE);
    }

    private void setSummary(List<Preference> list) {
        ArrayList arrayList = new ArrayList();
        CharSequence string = null;
        for (Preference preference : list) {
            CharSequence title = preference.getTitle();
            boolean z2 = preference instanceof PreferenceGroup;
            if (z2 && !TextUtils.isEmpty(title)) {
                arrayList.add((PreferenceGroup) preference);
            }
            if (arrayList.contains(preference.getParent())) {
                if (z2) {
                    arrayList.add((PreferenceGroup) preference);
                }
            } else if (!TextUtils.isEmpty(title)) {
                string = string == null ? title : getContext().getString(R.string.summary_collapsed_preference_list, string, title);
            }
        }
        setSummary(string);
    }

    @Override // androidx.preference.Preference
    public long getId() {
        return this.mId;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(@NonNull PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.setDividerAllowedAbove(false);
    }
}
