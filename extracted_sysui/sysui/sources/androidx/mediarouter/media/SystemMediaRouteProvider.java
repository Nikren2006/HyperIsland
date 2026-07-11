package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.view.Display;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterJellybean;
import androidx.mediarouter.media.MediaRouterJellybeanMr1;
import com.miui.miplay.audio.data.DeviceInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
abstract class SystemMediaRouteProvider extends MediaRouteProvider {
    public static final String DEFAULT_ROUTE_ID = "DEFAULT_ROUTE";
    public static final String PACKAGE_NAME = "android";

    @RequiresApi(24)
    public static class Api24Impl extends JellybeanMr2Impl {
        public Api24Impl(Context context, SyncCallback syncCallback) {
            super(context, syncCallback);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanMr2Impl, androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanMr1Impl, androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        @DoNotInline
        public void onBuildSystemRouteDescriptor(JellybeanImpl.SystemRouteRecord systemRouteRecord, MediaRouteDescriptor.Builder builder) {
            super.onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            builder.setDeviceType(((MediaRouter.RouteInfo) systemRouteRecord.mRouteObj).getDeviceType());
        }
    }

    @RequiresApi(16)
    public static class JellybeanImpl extends SystemMediaRouteProvider implements MediaRouterJellybean.Callback, MediaRouterJellybean.VolumeCallback {
        private static final ArrayList<IntentFilter> LIVE_AUDIO_CONTROL_FILTERS;
        private static final ArrayList<IntentFilter> LIVE_VIDEO_CONTROL_FILTERS;
        protected boolean mActiveScan;
        protected final Object mCallbackObj;
        protected boolean mCallbackRegistered;
        private MediaRouterJellybean.GetDefaultRouteWorkaround mGetDefaultRouteWorkaround;
        protected int mRouteTypes;
        protected final Object mRouterObj;
        private MediaRouterJellybean.SelectRouteWorkaround mSelectRouteWorkaround;
        private final SyncCallback mSyncCallback;
        protected final ArrayList<SystemRouteRecord> mSystemRouteRecords;
        protected final Object mUserRouteCategoryObj;
        protected final ArrayList<UserRouteRecord> mUserRouteRecords;
        protected final Object mVolumeCallbackObj;

        public static final class SystemRouteController extends MediaRouteProvider.RouteController {
            private final Object mRouteObj;

            public SystemRouteController(Object obj) {
                this.mRouteObj = obj;
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
            public void onSetVolume(int i2) {
                MediaRouterJellybean.RouteInfo.requestSetVolume(this.mRouteObj, i2);
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
            public void onUpdateVolume(int i2) {
                MediaRouterJellybean.RouteInfo.requestUpdateVolume(this.mRouteObj, i2);
            }
        }

        public static final class SystemRouteRecord {
            public MediaRouteDescriptor mRouteDescriptor;
            public final String mRouteDescriptorId;
            public final Object mRouteObj;

            public SystemRouteRecord(Object obj, String str) {
                this.mRouteObj = obj;
                this.mRouteDescriptorId = str;
            }
        }

        public static final class UserRouteRecord {
            public final MediaRouter.RouteInfo mRoute;
            public final Object mRouteObj;

            public UserRouteRecord(MediaRouter.RouteInfo routeInfo, Object obj) {
                this.mRoute = routeInfo;
                this.mRouteObj = obj;
            }
        }

        static {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO);
            ArrayList<IntentFilter> arrayList = new ArrayList<>();
            LIVE_AUDIO_CONTROL_FILTERS = arrayList;
            arrayList.add(intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
            ArrayList<IntentFilter> arrayList2 = new ArrayList<>();
            LIVE_VIDEO_CONTROL_FILTERS = arrayList2;
            arrayList2.add(intentFilter2);
        }

        public JellybeanImpl(Context context, SyncCallback syncCallback) {
            super(context);
            this.mSystemRouteRecords = new ArrayList<>();
            this.mUserRouteRecords = new ArrayList<>();
            this.mSyncCallback = syncCallback;
            Object mediaRouter = MediaRouterJellybean.getMediaRouter(context);
            this.mRouterObj = mediaRouter;
            this.mCallbackObj = createCallbackObj();
            this.mVolumeCallbackObj = createVolumeCallbackObj();
            this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(mediaRouter, context.getResources().getString(R.string.mr_user_route_category_name), false);
            updateSystemRoutes();
        }

        private boolean addSystemRouteNoPublish(Object obj) {
            if (getUserRouteRecord(obj) != null || findSystemRouteRecord(obj) >= 0) {
                return false;
            }
            SystemRouteRecord systemRouteRecord = new SystemRouteRecord(obj, assignRouteId(obj));
            updateSystemRouteDescriptor(systemRouteRecord);
            this.mSystemRouteRecords.add(systemRouteRecord);
            return true;
        }

        private String assignRouteId(Object obj) {
            String str = getDefaultRoute() == obj ? SystemMediaRouteProvider.DEFAULT_ROUTE_ID : String.format(Locale.US, "ROUTE_%08x", Integer.valueOf(getRouteName(obj).hashCode()));
            if (findSystemRouteRecordByDescriptorId(str) < 0) {
                return str;
            }
            int i2 = 2;
            while (true) {
                String str2 = String.format(Locale.US, "%s_%d", str, Integer.valueOf(i2));
                if (findSystemRouteRecordByDescriptorId(str2) < 0) {
                    return str2;
                }
                i2++;
            }
        }

        private void updateSystemRoutes() {
            updateCallback();
            Iterator it = MediaRouterJellybean.getRoutes(this.mRouterObj).iterator();
            boolean zAddSystemRouteNoPublish = false;
            while (it.hasNext()) {
                zAddSystemRouteNoPublish |= addSystemRouteNoPublish(it.next());
            }
            if (zAddSystemRouteNoPublish) {
                publishRoutes();
            }
        }

        public Object createCallbackObj() {
            return MediaRouterJellybean.createCallback(this);
        }

        public Object createVolumeCallbackObj() {
            return MediaRouterJellybean.createVolumeCallback(this);
        }

        public int findSystemRouteRecord(Object obj) {
            int size = this.mSystemRouteRecords.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mSystemRouteRecords.get(i2).mRouteObj == obj) {
                    return i2;
                }
            }
            return -1;
        }

        public int findSystemRouteRecordByDescriptorId(String str) {
            int size = this.mSystemRouteRecords.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mSystemRouteRecords.get(i2).mRouteDescriptorId.equals(str)) {
                    return i2;
                }
            }
            return -1;
        }

        public int findUserRouteRecord(MediaRouter.RouteInfo routeInfo) {
            int size = this.mUserRouteRecords.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mUserRouteRecords.get(i2).mRoute == routeInfo) {
                    return i2;
                }
            }
            return -1;
        }

        public Object getDefaultRoute() {
            if (this.mGetDefaultRouteWorkaround == null) {
                this.mGetDefaultRouteWorkaround = new MediaRouterJellybean.GetDefaultRouteWorkaround();
            }
            return this.mGetDefaultRouteWorkaround.getDefaultRoute(this.mRouterObj);
        }

        public String getRouteName(Object obj) {
            CharSequence name = MediaRouterJellybean.RouteInfo.getName(obj, getContext());
            return name != null ? name.toString() : "";
        }

        public UserRouteRecord getUserRouteRecord(Object obj) {
            Object tag = MediaRouterJellybean.RouteInfo.getTag(obj);
            if (tag instanceof UserRouteRecord) {
                return (UserRouteRecord) tag;
            }
            return null;
        }

        public void onBuildSystemRouteDescriptor(SystemRouteRecord systemRouteRecord, MediaRouteDescriptor.Builder builder) {
            int supportedTypes = MediaRouterJellybean.RouteInfo.getSupportedTypes(systemRouteRecord.mRouteObj);
            if ((supportedTypes & 1) != 0) {
                builder.addControlFilters(LIVE_AUDIO_CONTROL_FILTERS);
            }
            if ((supportedTypes & 2) != 0) {
                builder.addControlFilters(LIVE_VIDEO_CONTROL_FILTERS);
            }
            builder.setPlaybackType(MediaRouterJellybean.RouteInfo.getPlaybackType(systemRouteRecord.mRouteObj));
            builder.setPlaybackStream(MediaRouterJellybean.RouteInfo.getPlaybackStream(systemRouteRecord.mRouteObj));
            builder.setVolume(MediaRouterJellybean.RouteInfo.getVolume(systemRouteRecord.mRouteObj));
            builder.setVolumeMax(MediaRouterJellybean.RouteInfo.getVolumeMax(systemRouteRecord.mRouteObj));
            builder.setVolumeHandling(MediaRouterJellybean.RouteInfo.getVolumeHandling(systemRouteRecord.mRouteObj));
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String str) {
            int iFindSystemRouteRecordByDescriptorId = findSystemRouteRecordByDescriptorId(str);
            if (iFindSystemRouteRecordByDescriptorId >= 0) {
                return new SystemRouteController(this.mSystemRouteRecords.get(iFindSystemRouteRecordByDescriptorId).mRouteObj);
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            boolean zIsActiveScan;
            int i2 = 0;
            if (mediaRouteDiscoveryRequest != null) {
                List<String> controlCategories = mediaRouteDiscoveryRequest.getSelector().getControlCategories();
                int size = controlCategories.size();
                int i3 = 0;
                while (i2 < size) {
                    String str = controlCategories.get(i2);
                    i3 = str.equals(MediaControlIntent.CATEGORY_LIVE_AUDIO) ? i3 | 1 : str.equals(MediaControlIntent.CATEGORY_LIVE_VIDEO) ? i3 | 2 : i3 | 8388608;
                    i2++;
                }
                zIsActiveScan = mediaRouteDiscoveryRequest.isActiveScan();
                i2 = i3;
            } else {
                zIsActiveScan = false;
            }
            if (this.mRouteTypes == i2 && this.mActiveScan == zIsActiveScan) {
                return;
            }
            this.mRouteTypes = i2;
            this.mActiveScan = zIsActiveScan;
            updateSystemRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteAdded(@NonNull Object obj) {
            if (addSystemRouteNoPublish(obj)) {
                publishRoutes();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteChanged(@NonNull Object obj) {
            int iFindSystemRouteRecord;
            if (getUserRouteRecord(obj) != null || (iFindSystemRouteRecord = findSystemRouteRecord(obj)) < 0) {
                return;
            }
            updateSystemRouteDescriptor(this.mSystemRouteRecords.get(iFindSystemRouteRecord));
            publishRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteGrouped(@NonNull Object obj, @NonNull Object obj2, int i2) {
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteRemoved(@NonNull Object obj) {
            int iFindSystemRouteRecord;
            if (getUserRouteRecord(obj) != null || (iFindSystemRouteRecord = findSystemRouteRecord(obj)) < 0) {
                return;
            }
            this.mSystemRouteRecords.remove(iFindSystemRouteRecord);
            publishRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteSelected(int i2, @NonNull Object obj) {
            if (obj != MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611)) {
                return;
            }
            UserRouteRecord userRouteRecord = getUserRouteRecord(obj);
            if (userRouteRecord != null) {
                userRouteRecord.mRoute.select();
                return;
            }
            int iFindSystemRouteRecord = findSystemRouteRecord(obj);
            if (iFindSystemRouteRecord >= 0) {
                this.mSyncCallback.onSystemRouteSelectedByDescriptorId(this.mSystemRouteRecords.get(iFindSystemRouteRecord).mRouteDescriptorId);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteUngrouped(@NonNull Object obj, @NonNull Object obj2) {
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteUnselected(int i2, @NonNull Object obj) {
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.Callback
        public void onRouteVolumeChanged(@NonNull Object obj) {
            int iFindSystemRouteRecord;
            if (getUserRouteRecord(obj) != null || (iFindSystemRouteRecord = findSystemRouteRecord(obj)) < 0) {
                return;
            }
            SystemRouteRecord systemRouteRecord = this.mSystemRouteRecords.get(iFindSystemRouteRecord);
            int volume = MediaRouterJellybean.RouteInfo.getVolume(obj);
            if (volume != systemRouteRecord.mRouteDescriptor.getVolume()) {
                systemRouteRecord.mRouteDescriptor = new MediaRouteDescriptor.Builder(systemRouteRecord.mRouteDescriptor).setVolume(volume).build();
                publishRoutes();
            }
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider
        public void onSyncRouteAdded(MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.getProviderInstance() == this) {
                int iFindSystemRouteRecord = findSystemRouteRecord(MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611));
                if (iFindSystemRouteRecord < 0 || !this.mSystemRouteRecords.get(iFindSystemRouteRecord).mRouteDescriptorId.equals(routeInfo.getDescriptorId())) {
                    return;
                }
                routeInfo.select();
                return;
            }
            Object objCreateUserRoute = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
            UserRouteRecord userRouteRecord = new UserRouteRecord(routeInfo, objCreateUserRoute);
            MediaRouterJellybean.RouteInfo.setTag(objCreateUserRoute, userRouteRecord);
            MediaRouterJellybean.UserRouteInfo.setVolumeCallback(objCreateUserRoute, this.mVolumeCallbackObj);
            updateUserRouteProperties(userRouteRecord);
            this.mUserRouteRecords.add(userRouteRecord);
            MediaRouterJellybean.addUserRoute(this.mRouterObj, objCreateUserRoute);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider
        public void onSyncRouteChanged(MediaRouter.RouteInfo routeInfo) {
            int iFindUserRouteRecord;
            if (routeInfo.getProviderInstance() == this || (iFindUserRouteRecord = findUserRouteRecord(routeInfo)) < 0) {
                return;
            }
            updateUserRouteProperties(this.mUserRouteRecords.get(iFindUserRouteRecord));
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider
        public void onSyncRouteRemoved(MediaRouter.RouteInfo routeInfo) {
            int iFindUserRouteRecord;
            if (routeInfo.getProviderInstance() == this || (iFindUserRouteRecord = findUserRouteRecord(routeInfo)) < 0) {
                return;
            }
            UserRouteRecord userRouteRecordRemove = this.mUserRouteRecords.remove(iFindUserRouteRecord);
            MediaRouterJellybean.RouteInfo.setTag(userRouteRecordRemove.mRouteObj, null);
            MediaRouterJellybean.UserRouteInfo.setVolumeCallback(userRouteRecordRemove.mRouteObj, null);
            MediaRouterJellybean.removeUserRoute(this.mRouterObj, userRouteRecordRemove.mRouteObj);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider
        public void onSyncRouteSelected(MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.isSelected()) {
                if (routeInfo.getProviderInstance() != this) {
                    int iFindUserRouteRecord = findUserRouteRecord(routeInfo);
                    if (iFindUserRouteRecord >= 0) {
                        selectRoute(this.mUserRouteRecords.get(iFindUserRouteRecord).mRouteObj);
                        return;
                    }
                    return;
                }
                int iFindSystemRouteRecordByDescriptorId = findSystemRouteRecordByDescriptorId(routeInfo.getDescriptorId());
                if (iFindSystemRouteRecordByDescriptorId >= 0) {
                    selectRoute(this.mSystemRouteRecords.get(iFindSystemRouteRecordByDescriptorId).mRouteObj);
                }
            }
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.VolumeCallback
        public void onVolumeSetRequest(@NonNull Object obj, int i2) {
            UserRouteRecord userRouteRecord = getUserRouteRecord(obj);
            if (userRouteRecord != null) {
                userRouteRecord.mRoute.requestSetVolume(i2);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybean.VolumeCallback
        public void onVolumeUpdateRequest(@NonNull Object obj, int i2) {
            UserRouteRecord userRouteRecord = getUserRouteRecord(obj);
            if (userRouteRecord != null) {
                userRouteRecord.mRoute.requestUpdateVolume(i2);
            }
        }

        public void publishRoutes() {
            MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder();
            int size = this.mSystemRouteRecords.size();
            for (int i2 = 0; i2 < size; i2++) {
                builder.addRoute(this.mSystemRouteRecords.get(i2).mRouteDescriptor);
            }
            setDescriptor(builder.build());
        }

        public void selectRoute(Object obj) {
            if (this.mSelectRouteWorkaround == null) {
                this.mSelectRouteWorkaround = new MediaRouterJellybean.SelectRouteWorkaround();
            }
            this.mSelectRouteWorkaround.selectRoute(this.mRouterObj, 8388611, obj);
        }

        public void updateCallback() {
            if (this.mCallbackRegistered) {
                this.mCallbackRegistered = false;
                MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
            }
            int i2 = this.mRouteTypes;
            if (i2 != 0) {
                this.mCallbackRegistered = true;
                MediaRouterJellybean.addCallback(this.mRouterObj, i2, this.mCallbackObj);
            }
        }

        public void updateSystemRouteDescriptor(SystemRouteRecord systemRouteRecord) {
            MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(systemRouteRecord.mRouteDescriptorId, getRouteName(systemRouteRecord.mRouteObj));
            onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            systemRouteRecord.mRouteDescriptor = builder.build();
        }

        public void updateUserRouteProperties(UserRouteRecord userRouteRecord) {
            MediaRouterJellybean.UserRouteInfo.setName(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getName());
            MediaRouterJellybean.UserRouteInfo.setPlaybackType(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getPlaybackType());
            MediaRouterJellybean.UserRouteInfo.setPlaybackStream(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getPlaybackStream());
            MediaRouterJellybean.UserRouteInfo.setVolume(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getVolume());
            MediaRouterJellybean.UserRouteInfo.setVolumeMax(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getVolumeMax());
            MediaRouterJellybean.UserRouteInfo.setVolumeHandling(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getVolumeHandling());
        }
    }

    @RequiresApi(17)
    public static class JellybeanMr1Impl extends JellybeanImpl implements MediaRouterJellybeanMr1.Callback {
        private MediaRouterJellybeanMr1.ActiveScanWorkaround mActiveScanWorkaround;
        private MediaRouterJellybeanMr1.IsConnectingWorkaround mIsConnectingWorkaround;

        public JellybeanMr1Impl(Context context, SyncCallback syncCallback) {
            super(context, syncCallback);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        public Object createCallbackObj() {
            return MediaRouterJellybeanMr1.createCallback(this);
        }

        public boolean isConnecting(JellybeanImpl.SystemRouteRecord systemRouteRecord) {
            if (this.mIsConnectingWorkaround == null) {
                this.mIsConnectingWorkaround = new MediaRouterJellybeanMr1.IsConnectingWorkaround();
            }
            return this.mIsConnectingWorkaround.isConnecting(systemRouteRecord.mRouteObj);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        public void onBuildSystemRouteDescriptor(JellybeanImpl.SystemRouteRecord systemRouteRecord, MediaRouteDescriptor.Builder builder) {
            super.onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            if (!MediaRouterJellybeanMr1.RouteInfo.isEnabled(systemRouteRecord.mRouteObj)) {
                builder.setEnabled(false);
            }
            if (isConnecting(systemRouteRecord)) {
                builder.setConnectionState(1);
            }
            Display presentationDisplay = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(systemRouteRecord.mRouteObj);
            if (presentationDisplay != null) {
                builder.setPresentationDisplayId(presentationDisplay.getDisplayId());
            }
        }

        @Override // androidx.mediarouter.media.MediaRouterJellybeanMr1.Callback
        public void onRoutePresentationDisplayChanged(@NonNull Object obj) {
            int iFindSystemRouteRecord = findSystemRouteRecord(obj);
            if (iFindSystemRouteRecord >= 0) {
                JellybeanImpl.SystemRouteRecord systemRouteRecord = this.mSystemRouteRecords.get(iFindSystemRouteRecord);
                Display presentationDisplay = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(obj);
                int displayId = presentationDisplay != null ? presentationDisplay.getDisplayId() : -1;
                if (displayId != systemRouteRecord.mRouteDescriptor.getPresentationDisplayId()) {
                    systemRouteRecord.mRouteDescriptor = new MediaRouteDescriptor.Builder(systemRouteRecord.mRouteDescriptor).setPresentationDisplayId(displayId).build();
                    publishRoutes();
                }
            }
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        public void updateCallback() {
            super.updateCallback();
            if (this.mActiveScanWorkaround == null) {
                this.mActiveScanWorkaround = new MediaRouterJellybeanMr1.ActiveScanWorkaround(getContext(), getHandler());
            }
            this.mActiveScanWorkaround.setActiveScanRouteTypes(this.mActiveScan ? this.mRouteTypes : 0);
        }
    }

    @RequiresApi(18)
    public static class JellybeanMr2Impl extends JellybeanMr1Impl {
        public JellybeanMr2Impl(Context context, SyncCallback syncCallback) {
            super(context, syncCallback);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        @DoNotInline
        public Object getDefaultRoute() {
            return ((android.media.MediaRouter) this.mRouterObj).getDefaultRoute();
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanMr1Impl
        @DoNotInline
        public boolean isConnecting(JellybeanImpl.SystemRouteRecord systemRouteRecord) {
            return ((MediaRouter.RouteInfo) systemRouteRecord.mRouteObj).isConnecting();
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanMr1Impl, androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        @DoNotInline
        public void onBuildSystemRouteDescriptor(JellybeanImpl.SystemRouteRecord systemRouteRecord, MediaRouteDescriptor.Builder builder) {
            super.onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            CharSequence description = ((MediaRouter.RouteInfo) systemRouteRecord.mRouteObj).getDescription();
            if (description != null) {
                builder.setDescription(description.toString());
            }
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        @DoNotInline
        public void selectRoute(Object obj) {
            MediaRouterJellybean.selectRoute(this.mRouterObj, 8388611, obj);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanMr1Impl, androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        @DoNotInline
        public void updateCallback() {
            if (this.mCallbackRegistered) {
                MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
            }
            this.mCallbackRegistered = true;
            ((android.media.MediaRouter) this.mRouterObj).addCallback(this.mRouteTypes, (MediaRouter.Callback) this.mCallbackObj, (this.mActiveScan ? 1 : 0) | 2);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.JellybeanImpl
        @DoNotInline
        public void updateUserRouteProperties(JellybeanImpl.UserRouteRecord userRouteRecord) {
            super.updateUserRouteProperties(userRouteRecord);
            ((MediaRouter.UserRouteInfo) userRouteRecord.mRouteObj).setDescription(userRouteRecord.mRoute.getDescription());
        }
    }

    public static class LegacyImpl extends SystemMediaRouteProvider {
        private static final ArrayList<IntentFilter> CONTROL_FILTERS;
        static final int PLAYBACK_STREAM = 3;
        final AudioManager mAudioManager;
        int mLastReportedVolume;
        private final VolumeChangeReceiver mVolumeChangeReceiver;

        public final class DefaultRouteController extends MediaRouteProvider.RouteController {
            public DefaultRouteController() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
            public void onSetVolume(int i2) {
                LegacyImpl.this.mAudioManager.setStreamVolume(3, i2, 0);
                LegacyImpl.this.publishRoutes();
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
            public void onUpdateVolume(int i2) {
                int streamVolume = LegacyImpl.this.mAudioManager.getStreamVolume(3);
                if (Math.min(LegacyImpl.this.mAudioManager.getStreamMaxVolume(3), Math.max(0, i2 + streamVolume)) != streamVolume) {
                    LegacyImpl.this.mAudioManager.setStreamVolume(3, streamVolume, 0);
                }
                LegacyImpl.this.publishRoutes();
            }
        }

        public final class VolumeChangeReceiver extends BroadcastReceiver {
            public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
            public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
            public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";

            public VolumeChangeReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra;
                if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3 && (intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1)) >= 0) {
                    LegacyImpl legacyImpl = LegacyImpl.this;
                    if (intExtra != legacyImpl.mLastReportedVolume) {
                        legacyImpl.publishRoutes();
                    }
                }
            }
        }

        static {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO);
            intentFilter.addCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
            ArrayList<IntentFilter> arrayList = new ArrayList<>();
            CONTROL_FILTERS = arrayList;
            arrayList.add(intentFilter);
        }

        public LegacyImpl(Context context) {
            super(context);
            this.mLastReportedVolume = -1;
            this.mAudioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
            VolumeChangeReceiver volumeChangeReceiver = new VolumeChangeReceiver();
            this.mVolumeChangeReceiver = volumeChangeReceiver;
            context.registerReceiver(volumeChangeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            publishRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String str) {
            if (str.equals(SystemMediaRouteProvider.DEFAULT_ROUTE_ID)) {
                return new DefaultRouteController();
            }
            return null;
        }

        public void publishRoutes() {
            Resources resources = getContext().getResources();
            int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
            this.mLastReportedVolume = this.mAudioManager.getStreamVolume(3);
            setDescriptor(new MediaRouteProviderDescriptor.Builder().addRoute(new MediaRouteDescriptor.Builder(SystemMediaRouteProvider.DEFAULT_ROUTE_ID, resources.getString(R.string.mr_system_route_name)).addControlFilters(CONTROL_FILTERS).setPlaybackStream(3).setPlaybackType(0).setVolumeHandling(1).setVolumeMax(streamMaxVolume).setVolume(this.mLastReportedVolume).build()).build());
        }
    }

    public interface SyncCallback {
        void onSystemRouteSelectedByDescriptorId(@NonNull String str);
    }

    public SystemMediaRouteProvider(Context context) {
        super(context, new MediaRouteProvider.ProviderMetadata(new ComponentName(PACKAGE_NAME, SystemMediaRouteProvider.class.getName())));
    }

    public static SystemMediaRouteProvider obtain(Context context, SyncCallback syncCallback) {
        return new Api24Impl(context, syncCallback);
    }

    public void onSyncRouteAdded(MediaRouter.RouteInfo routeInfo) {
    }

    public void onSyncRouteChanged(MediaRouter.RouteInfo routeInfo) {
    }

    public void onSyncRouteRemoved(MediaRouter.RouteInfo routeInfo) {
    }

    public void onSyncRouteSelected(MediaRouter.RouteInfo routeInfo) {
    }
}
