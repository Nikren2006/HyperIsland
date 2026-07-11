package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MediaRouteProviderDescriptor {
    private static final String KEY_ROUTES = "routes";
    private static final String KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE = "supportsDynamicGroupRoute";
    Bundle mBundle;
    final List<MediaRouteDescriptor> mRoutes;
    final boolean mSupportsDynamicGroupRoute;

    public MediaRouteProviderDescriptor(@NonNull List<MediaRouteDescriptor> list, boolean z2) {
        if (list.isEmpty()) {
            this.mRoutes = Collections.emptyList();
        } else {
            this.mRoutes = Collections.unmodifiableList(new ArrayList(list));
        }
        this.mSupportsDynamicGroupRoute = z2;
    }

    @Nullable
    public static MediaRouteProviderDescriptor fromBundle(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ROUTES);
        if (parcelableArrayList != null) {
            for (int i2 = 0; i2 < parcelableArrayList.size(); i2++) {
                arrayList.add(MediaRouteDescriptor.fromBundle((Bundle) parcelableArrayList.get(i2)));
            }
        }
        return new MediaRouteProviderDescriptor(arrayList, bundle.getBoolean(KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE, false));
    }

    @NonNull
    public Bundle asBundle() {
        Bundle bundle = this.mBundle;
        if (bundle != null) {
            return bundle;
        }
        this.mBundle = new Bundle();
        if (!this.mRoutes.isEmpty()) {
            int size = this.mRoutes.size();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(size);
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(this.mRoutes.get(i2).asBundle());
            }
            this.mBundle.putParcelableArrayList(KEY_ROUTES, arrayList);
        }
        this.mBundle.putBoolean(KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE, this.mSupportsDynamicGroupRoute);
        return this.mBundle;
    }

    @NonNull
    public List<MediaRouteDescriptor> getRoutes() {
        return this.mRoutes;
    }

    public boolean isValid() {
        int size = getRoutes().size();
        for (int i2 = 0; i2 < size; i2++) {
            MediaRouteDescriptor mediaRouteDescriptor = this.mRoutes.get(i2);
            if (mediaRouteDescriptor == null || !mediaRouteDescriptor.isValid()) {
                return false;
            }
        }
        return true;
    }

    public boolean supportsDynamicGroupRoute() {
        return this.mSupportsDynamicGroupRoute;
    }

    @NonNull
    public String toString() {
        return "MediaRouteProviderDescriptor{ routes=" + Arrays.toString(getRoutes().toArray()) + ", isValid=" + isValid() + " }";
    }

    public static final class Builder {
        private final List<MediaRouteDescriptor> mRoutes;
        private boolean mSupportsDynamicGroupRoute;

        public Builder() {
            this.mRoutes = new ArrayList();
            this.mSupportsDynamicGroupRoute = false;
        }

        @NonNull
        public Builder addRoute(@NonNull MediaRouteDescriptor mediaRouteDescriptor) {
            if (mediaRouteDescriptor == null) {
                throw new IllegalArgumentException("route must not be null");
            }
            if (this.mRoutes.contains(mediaRouteDescriptor)) {
                throw new IllegalArgumentException("route descriptor already added");
            }
            this.mRoutes.add(mediaRouteDescriptor);
            return this;
        }

        @NonNull
        public Builder addRoutes(@NonNull Collection<MediaRouteDescriptor> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("routes must not be null");
            }
            if (!collection.isEmpty()) {
                Iterator<MediaRouteDescriptor> it = collection.iterator();
                while (it.hasNext()) {
                    addRoute(it.next());
                }
            }
            return this;
        }

        @NonNull
        public MediaRouteProviderDescriptor build() {
            return new MediaRouteProviderDescriptor(this.mRoutes, this.mSupportsDynamicGroupRoute);
        }

        @NonNull
        public Builder setRoutes(@Nullable Collection<MediaRouteDescriptor> collection) {
            this.mRoutes.clear();
            if (collection != null) {
                this.mRoutes.addAll(collection);
            }
            return this;
        }

        @NonNull
        public Builder setSupportsDynamicGroupRoute(boolean z2) {
            this.mSupportsDynamicGroupRoute = z2;
            return this;
        }

        public Builder(@NonNull MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            ArrayList arrayList = new ArrayList();
            this.mRoutes = arrayList;
            this.mSupportsDynamicGroupRoute = false;
            if (mediaRouteProviderDescriptor != null) {
                arrayList.addAll(mediaRouteProviderDescriptor.getRoutes());
                this.mSupportsDynamicGroupRoute = mediaRouteProviderDescriptor.mSupportsDynamicGroupRoute;
                return;
            }
            throw new IllegalArgumentException("descriptor must not be null");
        }
    }
}
