package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.media.RemoteControlClient;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(16)
final class MediaRouterJellybean {
    public static final int ALL_ROUTE_TYPES = 8388611;
    public static final int DEVICE_OUT_BLUETOOTH = 896;
    public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
    public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
    public static final int ROUTE_TYPE_USER = 8388608;
    private static final String TAG = "MediaRouterJellybean";

    public interface Callback {
        void onRouteAdded(@NonNull Object obj);

        void onRouteChanged(@NonNull Object obj);

        void onRouteGrouped(@NonNull Object obj, @NonNull Object obj2, int i2);

        void onRouteRemoved(@NonNull Object obj);

        void onRouteSelected(int i2, @NonNull Object obj);

        void onRouteUngrouped(@NonNull Object obj, @NonNull Object obj2);

        void onRouteUnselected(int i2, @NonNull Object obj);

        void onRouteVolumeChanged(@NonNull Object obj);
    }

    public static class CallbackProxy<T extends Callback> extends MediaRouter.Callback {
        protected final T mCallback;

        public CallbackProxy(T t2) {
            this.mCallback = t2;
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteAdded(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            this.mCallback.onRouteAdded(routeInfo);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteChanged(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            this.mCallback.onRouteChanged(routeInfo);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteGrouped(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup, int i2) {
            this.mCallback.onRouteGrouped(routeInfo, routeGroup, i2);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteRemoved(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            this.mCallback.onRouteRemoved(routeInfo);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteSelected(android.media.MediaRouter mediaRouter, int i2, MediaRouter.RouteInfo routeInfo) {
            this.mCallback.onRouteSelected(i2, routeInfo);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteUngrouped(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup) {
            this.mCallback.onRouteUngrouped(routeInfo, routeGroup);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteUnselected(android.media.MediaRouter mediaRouter, int i2, MediaRouter.RouteInfo routeInfo) {
            this.mCallback.onRouteUnselected(i2, routeInfo);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRouteVolumeChanged(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            this.mCallback.onRouteVolumeChanged(routeInfo);
        }
    }

    public static final class GetDefaultRouteWorkaround {
        private Method mGetSystemAudioRouteMethod;

        public GetDefaultRouteWorkaround() {
            throw new UnsupportedOperationException();
        }

        @NonNull
        @SuppressLint({"BanUncheckedReflection"})
        public Object getDefaultRoute(@NonNull Object obj) {
            android.media.MediaRouter mediaRouter = (android.media.MediaRouter) obj;
            Method method = this.mGetSystemAudioRouteMethod;
            if (method != null) {
                try {
                    return method.invoke(mediaRouter, null);
                } catch (IllegalAccessException | InvocationTargetException unused) {
                }
            }
            return mediaRouter.getRouteAt(0);
        }
    }

    public static final class RouteCategory {
        private RouteCategory() {
        }

        @Nullable
        public static CharSequence getName(@NonNull Object obj, @NonNull Context context) {
            return ((MediaRouter.RouteCategory) obj).getName(context);
        }

        @NonNull
        public static List getRoutes(@NonNull Object obj) {
            ArrayList arrayList = new ArrayList();
            ((MediaRouter.RouteCategory) obj).getRoutes(arrayList);
            return arrayList;
        }

        public static int getSupportedTypes(@NonNull Object obj) {
            return ((MediaRouter.RouteCategory) obj).getSupportedTypes();
        }

        public static boolean isGroupable(@NonNull Object obj) {
            return ((MediaRouter.RouteCategory) obj).isGroupable();
        }
    }

    public static final class RouteGroup {
        private RouteGroup() {
        }

        @NonNull
        public static List getGroupedRoutes(@NonNull Object obj) {
            MediaRouter.RouteGroup routeGroup = (MediaRouter.RouteGroup) obj;
            int routeCount = routeGroup.getRouteCount();
            ArrayList arrayList = new ArrayList(routeCount);
            for (int i2 = 0; i2 < routeCount; i2++) {
                arrayList.add(routeGroup.getRouteAt(i2));
            }
            return arrayList;
        }
    }

    public static final class RouteInfo {
        private RouteInfo() {
        }

        @Nullable
        public static Object getCategory(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getCategory();
        }

        @Nullable
        public static Object getGroup(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getGroup();
        }

        @Nullable
        public static Drawable getIconDrawable(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getIconDrawable();
        }

        @NonNull
        public static CharSequence getName(@NonNull Object obj, @NonNull Context context) {
            return ((MediaRouter.RouteInfo) obj).getName(context);
        }

        public static int getPlaybackStream(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getPlaybackStream();
        }

        public static int getPlaybackType(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getPlaybackType();
        }

        @NonNull
        public static CharSequence getStatus(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getStatus();
        }

        public static int getSupportedTypes(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getSupportedTypes();
        }

        @Nullable
        public static Object getTag(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getTag();
        }

        public static int getVolume(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getVolume();
        }

        public static int getVolumeHandling(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getVolumeHandling();
        }

        public static int getVolumeMax(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).getVolumeMax();
        }

        public static boolean isGroup(@NonNull Object obj) {
            return obj instanceof MediaRouter.RouteGroup;
        }

        public static void requestSetVolume(@NonNull Object obj, int i2) {
            ((MediaRouter.RouteInfo) obj).requestSetVolume(i2);
        }

        public static void requestUpdateVolume(@NonNull Object obj, int i2) {
            ((MediaRouter.RouteInfo) obj).requestUpdateVolume(i2);
        }

        public static void setTag(@NonNull Object obj, @Nullable Object obj2) {
            ((MediaRouter.RouteInfo) obj).setTag(obj2);
        }
    }

    public static final class SelectRouteWorkaround {
        private Method mSelectRouteIntMethod;

        public SelectRouteWorkaround() {
            throw new UnsupportedOperationException();
        }

        @SuppressLint({"BanUncheckedReflection"})
        public void selectRoute(@NonNull Object obj, int i2, @NonNull Object obj2) {
            android.media.MediaRouter mediaRouter = (android.media.MediaRouter) obj;
            MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) obj2;
            if ((routeInfo.getSupportedTypes() & 8388608) == 0) {
                Method method = this.mSelectRouteIntMethod;
                if (method != null) {
                    try {
                        method.invoke(mediaRouter, Integer.valueOf(i2), routeInfo);
                        return;
                    } catch (IllegalAccessException e2) {
                        Log.w(MediaRouterJellybean.TAG, "Cannot programmatically select non-user route.  Media routing may not work.", e2);
                    } catch (InvocationTargetException e3) {
                        Log.w(MediaRouterJellybean.TAG, "Cannot programmatically select non-user route.  Media routing may not work.", e3);
                    }
                } else {
                    Log.w(MediaRouterJellybean.TAG, "Cannot programmatically select non-user route because the platform is missing the selectRouteInt() method.  Media routing may not work.");
                }
            }
            mediaRouter.selectRoute(i2, routeInfo);
        }
    }

    public static final class UserRouteInfo {
        private UserRouteInfo() {
        }

        public static void setIconDrawable(@NonNull Object obj, @Nullable Drawable drawable) {
            ((MediaRouter.UserRouteInfo) obj).setIconDrawable(drawable);
        }

        public static void setName(@NonNull Object obj, @NonNull CharSequence charSequence) {
            ((MediaRouter.UserRouteInfo) obj).setName(charSequence);
        }

        public static void setPlaybackStream(@NonNull Object obj, int i2) {
            ((MediaRouter.UserRouteInfo) obj).setPlaybackStream(i2);
        }

        public static void setPlaybackType(@NonNull Object obj, int i2) {
            ((MediaRouter.UserRouteInfo) obj).setPlaybackType(i2);
        }

        public static void setRemoteControlClient(@NonNull Object obj, @Nullable Object obj2) {
            ((MediaRouter.UserRouteInfo) obj).setRemoteControlClient((RemoteControlClient) obj2);
        }

        public static void setStatus(@NonNull Object obj, @NonNull CharSequence charSequence) {
            ((MediaRouter.UserRouteInfo) obj).setStatus(charSequence);
        }

        public static void setVolume(@NonNull Object obj, int i2) {
            ((MediaRouter.UserRouteInfo) obj).setVolume(i2);
        }

        public static void setVolumeCallback(@NonNull Object obj, @NonNull Object obj2) {
            ((MediaRouter.UserRouteInfo) obj).setVolumeCallback((MediaRouter.VolumeCallback) obj2);
        }

        public static void setVolumeHandling(@NonNull Object obj, int i2) {
            ((MediaRouter.UserRouteInfo) obj).setVolumeHandling(i2);
        }

        public static void setVolumeMax(@NonNull Object obj, int i2) {
            ((MediaRouter.UserRouteInfo) obj).setVolumeMax(i2);
        }
    }

    public interface VolumeCallback {
        void onVolumeSetRequest(@NonNull Object obj, int i2);

        void onVolumeUpdateRequest(@NonNull Object obj, int i2);
    }

    public static class VolumeCallbackProxy<T extends VolumeCallback> extends MediaRouter.VolumeCallback {
        protected final T mCallback;

        public VolumeCallbackProxy(T t2) {
            this.mCallback = t2;
        }

        @Override // android.media.MediaRouter.VolumeCallback
        public void onVolumeSetRequest(MediaRouter.RouteInfo routeInfo, int i2) {
            this.mCallback.onVolumeSetRequest(routeInfo, i2);
        }

        @Override // android.media.MediaRouter.VolumeCallback
        public void onVolumeUpdateRequest(MediaRouter.RouteInfo routeInfo, int i2) {
            this.mCallback.onVolumeUpdateRequest(routeInfo, i2);
        }
    }

    private MediaRouterJellybean() {
    }

    public static void addCallback(Object obj, int i2, Object obj2) {
        ((android.media.MediaRouter) obj).addCallback(i2, (MediaRouter.Callback) obj2);
    }

    public static void addUserRoute(Object obj, Object obj2) {
        ((android.media.MediaRouter) obj).addUserRoute((MediaRouter.UserRouteInfo) obj2);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    public static Object createRouteCategory(Object obj, String str, boolean z2) {
        return ((android.media.MediaRouter) obj).createRouteCategory(str, z2);
    }

    public static Object createUserRoute(Object obj, Object obj2) {
        return ((android.media.MediaRouter) obj).createUserRoute((MediaRouter.RouteCategory) obj2);
    }

    public static Object createVolumeCallback(VolumeCallback volumeCallback) {
        return new VolumeCallbackProxy(volumeCallback);
    }

    public static List getCategories(Object obj) {
        android.media.MediaRouter mediaRouter = (android.media.MediaRouter) obj;
        int categoryCount = mediaRouter.getCategoryCount();
        ArrayList arrayList = new ArrayList(categoryCount);
        for (int i2 = 0; i2 < categoryCount; i2++) {
            arrayList.add(mediaRouter.getCategoryAt(i2));
        }
        return arrayList;
    }

    public static Object getMediaRouter(Context context) {
        return context.getSystemService("media_router");
    }

    public static List getRoutes(Object obj) {
        android.media.MediaRouter mediaRouter = (android.media.MediaRouter) obj;
        int routeCount = mediaRouter.getRouteCount();
        ArrayList arrayList = new ArrayList(routeCount);
        for (int i2 = 0; i2 < routeCount; i2++) {
            arrayList.add(mediaRouter.getRouteAt(i2));
        }
        return arrayList;
    }

    public static Object getSelectedRoute(Object obj, int i2) {
        return ((android.media.MediaRouter) obj).getSelectedRoute(i2);
    }

    public static void removeCallback(Object obj, Object obj2) {
        ((android.media.MediaRouter) obj).removeCallback((MediaRouter.Callback) obj2);
    }

    public static void removeUserRoute(Object obj, Object obj2) {
        try {
            ((android.media.MediaRouter) obj).removeUserRoute((MediaRouter.UserRouteInfo) obj2);
        } catch (IllegalArgumentException e2) {
            Log.w(TAG, "Failed to remove user route", e2);
        }
    }

    public static void selectRoute(Object obj, int i2, Object obj2) {
        ((android.media.MediaRouter) obj).selectRoute(i2, (MediaRouter.RouteInfo) obj2);
    }
}
