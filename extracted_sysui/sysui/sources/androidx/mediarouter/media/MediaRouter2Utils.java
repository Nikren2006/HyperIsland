package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArraySet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 30)
class MediaRouter2Utils {
    static final String FEATURE_EMPTY = "android.media.route.feature.EMPTY";
    static final String FEATURE_REMOTE_GROUP_PLAYBACK = "android.media.route.feature.REMOTE_GROUP_PLAYBACK";
    static final String KEY_CONTROL_FILTERS = "androidx.mediarouter.media.KEY_CONTROL_FILTERS";
    static final String KEY_DEVICE_TYPE = "androidx.mediarouter.media.KEY_DEVICE_TYPE";
    static final String KEY_EXTRAS = "androidx.mediarouter.media.KEY_EXTRAS";
    static final String KEY_GROUP_ROUTE = "androidx.mediarouter.media.KEY_GROUP_ROUTE";
    static final String KEY_MESSENGER = "androidx.mediarouter.media.KEY_MESSENGER";
    static final String KEY_ORIGINAL_ROUTE_ID = "androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID";
    static final String KEY_PLAYBACK_TYPE = "androidx.mediarouter.media.KEY_PLAYBACK_TYPE";
    static final String KEY_SESSION_NAME = "androidx.mediarouter.media.KEY_SESSION_NAME";

    private MediaRouter2Utils() {
    }

    @NonNull
    public static List<String> getRouteIds(@Nullable List<MediaRoute2Info> list) {
        if (list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (MediaRoute2Info mediaRoute2Info : list) {
            if (mediaRoute2Info != null) {
                arrayList.add(mediaRoute2Info.getId());
            }
        }
        return arrayList;
    }

    public static String toControlCategory(String str) {
        str.hashCode();
        switch (str) {
            case "android.media.route.feature.REMOTE_PLAYBACK":
                return MediaControlIntent.CATEGORY_REMOTE_PLAYBACK;
            case "android.media.route.feature.LIVE_AUDIO":
                return MediaControlIntent.CATEGORY_LIVE_AUDIO;
            case "android.media.route.feature.LIVE_VIDEO":
                return MediaControlIntent.CATEGORY_LIVE_VIDEO;
            default:
                return str;
        }
    }

    @NonNull
    public static List<IntentFilter> toControlFilters(@Nullable Collection<String> collection) {
        if (collection == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        for (String str : collection) {
            if (!arraySet.contains(str)) {
                arraySet.add(str);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addCategory(toControlCategory(str));
                arrayList.add(intentFilter);
            }
        }
        return arrayList;
    }

    @NonNull
    public static RouteDiscoveryPreference toDiscoveryPreference(@Nullable MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        if (mediaRouteDiscoveryRequest == null || !mediaRouteDiscoveryRequest.isValid()) {
            return new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        }
        boolean zIsActiveScan = mediaRouteDiscoveryRequest.isActiveScan();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = mediaRouteDiscoveryRequest.getSelector().getControlCategories().iterator();
        while (it.hasNext()) {
            arrayList.add(toRouteFeature(it.next()));
        }
        return new RouteDiscoveryPreference.Builder(arrayList, zIsActiveScan).build();
    }

    public static Collection<String> toFeatures(List<IntentFilter> list) {
        HashSet hashSet = new HashSet();
        for (IntentFilter intentFilter : list) {
            int iCountCategories = intentFilter.countCategories();
            for (int i2 = 0; i2 < iCountCategories; i2++) {
                hashSet.add(toRouteFeature(intentFilter.getCategory(i2)));
            }
        }
        return hashSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00cb  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.media.MediaRoute2Info toFwkMediaRoute2Info(@androidx.annotation.Nullable androidx.mediarouter.media.MediaRouteDescriptor r4) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter2Utils.toFwkMediaRoute2Info(androidx.mediarouter.media.MediaRouteDescriptor):android.media.MediaRoute2Info");
    }

    @Nullable
    public static MediaRouteDescriptor toMediaRouteDescriptor(@Nullable MediaRoute2Info mediaRoute2Info) {
        if (mediaRoute2Info == null) {
            return null;
        }
        MediaRouteDescriptor.Builder canDisconnect = new MediaRouteDescriptor.Builder(mediaRoute2Info.getId(), mediaRoute2Info.getName().toString()).setConnectionState(mediaRoute2Info.getConnectionState()).setVolumeHandling(mediaRoute2Info.getVolumeHandling()).setVolumeMax(mediaRoute2Info.getVolumeMax()).setVolume(mediaRoute2Info.getVolume()).setExtras(mediaRoute2Info.getExtras()).setEnabled(true).setCanDisconnect(false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            canDisconnect.setDescription(description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            canDisconnect.setIconUri(iconUri);
        }
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey(KEY_EXTRAS) || !extras.containsKey(KEY_DEVICE_TYPE) || !extras.containsKey(KEY_CONTROL_FILTERS)) {
            return null;
        }
        canDisconnect.setExtras(extras.getBundle(KEY_EXTRAS));
        canDisconnect.setDeviceType(extras.getInt(KEY_DEVICE_TYPE, 0));
        canDisconnect.setPlaybackType(extras.getInt(KEY_PLAYBACK_TYPE, 1));
        ArrayList parcelableArrayList = extras.getParcelableArrayList(KEY_CONTROL_FILTERS);
        if (parcelableArrayList != null) {
            canDisconnect.addControlFilters(parcelableArrayList);
        }
        return canDisconnect.build();
    }

    @NonNull
    public static MediaRouteDiscoveryRequest toMediaRouteDiscoveryRequest(@NonNull RouteDiscoveryPreference routeDiscoveryPreference) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = routeDiscoveryPreference.getPreferredFeatures().iterator();
        while (it.hasNext()) {
            arrayList.add(toControlCategory(it.next()));
        }
        return new MediaRouteDiscoveryRequest(new MediaRouteSelector.Builder().addControlCategories(arrayList).build(), routeDiscoveryPreference.shouldPerformActiveScan());
    }

    public static String toRouteFeature(String str) {
        str.hashCode();
        switch (str) {
            case "android.media.intent.category.REMOTE_PLAYBACK":
                return "android.media.route.feature.REMOTE_PLAYBACK";
            case "android.media.intent.category.LIVE_AUDIO":
                return "android.media.route.feature.LIVE_AUDIO";
            case "android.media.intent.category.LIVE_VIDEO":
                return "android.media.route.feature.LIVE_VIDEO";
            default:
                return str;
        }
    }
}
