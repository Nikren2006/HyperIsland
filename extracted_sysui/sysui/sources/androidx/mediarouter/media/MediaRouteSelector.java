package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MediaRouteSelector {
    public static final MediaRouteSelector EMPTY = new MediaRouteSelector(new Bundle(), null);
    static final String KEY_CONTROL_CATEGORIES = "controlCategories";
    private final Bundle mBundle;
    List<String> mControlCategories;

    public static final class Builder {
        private ArrayList<String> mControlCategories;

        public Builder() {
        }

        @NonNull
        public Builder addControlCategories(@NonNull Collection<String> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("categories must not be null");
            }
            if (!collection.isEmpty()) {
                Iterator<String> it = collection.iterator();
                while (it.hasNext()) {
                    addControlCategory(it.next());
                }
            }
            return this;
        }

        @NonNull
        public Builder addControlCategory(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (this.mControlCategories == null) {
                this.mControlCategories = new ArrayList<>();
            }
            if (!this.mControlCategories.contains(str)) {
                this.mControlCategories.add(str);
            }
            return this;
        }

        @NonNull
        public Builder addSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            addControlCategories(mediaRouteSelector.getControlCategories());
            return this;
        }

        @NonNull
        public MediaRouteSelector build() {
            if (this.mControlCategories == null) {
                return MediaRouteSelector.EMPTY;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(MediaRouteSelector.KEY_CONTROL_CATEGORIES, this.mControlCategories);
            return new MediaRouteSelector(bundle, this.mControlCategories);
        }

        public Builder(@NonNull MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            mediaRouteSelector.ensureControlCategories();
            if (mediaRouteSelector.mControlCategories.isEmpty()) {
                return;
            }
            this.mControlCategories = new ArrayList<>(mediaRouteSelector.mControlCategories);
        }
    }

    public MediaRouteSelector(Bundle bundle, List<String> list) {
        this.mBundle = bundle;
        this.mControlCategories = list;
    }

    @Nullable
    public static MediaRouteSelector fromBundle(@Nullable Bundle bundle) {
        if (bundle != null) {
            return new MediaRouteSelector(bundle, null);
        }
        return null;
    }

    @NonNull
    public Bundle asBundle() {
        return this.mBundle;
    }

    public boolean contains(@NonNull MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            return false;
        }
        ensureControlCategories();
        mediaRouteSelector.ensureControlCategories();
        return this.mControlCategories.containsAll(mediaRouteSelector.mControlCategories);
    }

    public void ensureControlCategories() {
        if (this.mControlCategories == null) {
            ArrayList<String> stringArrayList = this.mBundle.getStringArrayList(KEY_CONTROL_CATEGORIES);
            this.mControlCategories = stringArrayList;
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                this.mControlCategories = Collections.emptyList();
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaRouteSelector)) {
            return false;
        }
        MediaRouteSelector mediaRouteSelector = (MediaRouteSelector) obj;
        ensureControlCategories();
        mediaRouteSelector.ensureControlCategories();
        return this.mControlCategories.equals(mediaRouteSelector.mControlCategories);
    }

    @NonNull
    public List<String> getControlCategories() {
        ensureControlCategories();
        return new ArrayList(this.mControlCategories);
    }

    public boolean hasControlCategory(@Nullable String str) {
        if (str == null) {
            return false;
        }
        ensureControlCategories();
        int size = this.mControlCategories.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mControlCategories.get(i2).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ensureControlCategories();
        return this.mControlCategories.hashCode();
    }

    public boolean isEmpty() {
        ensureControlCategories();
        return this.mControlCategories.isEmpty();
    }

    public boolean isValid() {
        ensureControlCategories();
        return !this.mControlCategories.contains(null);
    }

    public boolean matchesControlFilters(@Nullable List<IntentFilter> list) {
        if (list == null) {
            return false;
        }
        ensureControlCategories();
        if (this.mControlCategories.isEmpty()) {
            return false;
        }
        for (IntentFilter intentFilter : list) {
            if (intentFilter != null) {
                Iterator<String> it = this.mControlCategories.iterator();
                while (it.hasNext()) {
                    if (intentFilter.hasCategory(it.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @NonNull
    public String toString() {
        return "MediaRouteSelector{ controlCategories=" + Arrays.toString(getControlCategories().toArray()) + " }";
    }
}
