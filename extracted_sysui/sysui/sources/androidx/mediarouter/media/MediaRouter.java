package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.display.DisplayManagerCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.media.VolumeProviderCompat;
import androidx.mediarouter.media.MediaRoute2Provider;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher;
import androidx.mediarouter.media.RemoteControlClientCompat;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes.dex */
public final class MediaRouter {
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    static final boolean DEBUG = false;
    static final String TAG = "MediaRouter";
    public static final int UNSELECT_REASON_DISCONNECTED = 1;
    public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
    public static final int UNSELECT_REASON_STOPPED = 2;
    public static final int UNSELECT_REASON_UNKNOWN = 0;
    static GlobalMediaRouter sGlobal;
    final ArrayList<CallbackRecord> mCallbackRecords = new ArrayList<>();
    final Context mContext;

    public static abstract class Callback {
        public void onProviderAdded(@NonNull MediaRouter mediaRouter, @NonNull ProviderInfo providerInfo) {
        }

        public void onProviderChanged(@NonNull MediaRouter mediaRouter, @NonNull ProviderInfo providerInfo) {
        }

        public void onProviderRemoved(@NonNull MediaRouter mediaRouter, @NonNull ProviderInfo providerInfo) {
        }

        public void onRouteAdded(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        public void onRouteChanged(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        public void onRoutePresentationDisplayChanged(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        public void onRouteRemoved(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        @Deprecated
        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        @Deprecated
        public void onRouteUnselected(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        public void onRouteVolumeChanged(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public void onRouterParamsChanged(@NonNull MediaRouter mediaRouter, @Nullable MediaRouterParams mediaRouterParams) {
        }

        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo, int i2) {
            onRouteSelected(mediaRouter, routeInfo);
        }

        public void onRouteUnselected(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo, int i2) {
            onRouteUnselected(mediaRouter, routeInfo);
        }

        public void onRouteSelected(@NonNull MediaRouter mediaRouter, @NonNull RouteInfo routeInfo, int i2, @NonNull RouteInfo routeInfo2) {
            onRouteSelected(mediaRouter, routeInfo, i2);
        }
    }

    public static final class CallbackRecord {
        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;
        public long mTimestamp;

        public CallbackRecord(MediaRouter mediaRouter, Callback callback) {
            this.mRouter = mediaRouter;
            this.mCallback = callback;
        }

        public boolean filterRouteEvent(RouteInfo routeInfo, int i2, RouteInfo routeInfo2, int i3) {
            if ((this.mFlags & 2) != 0 || routeInfo.matchesSelector(this.mSelector)) {
                return true;
            }
            if (MediaRouter.isTransferToLocalEnabled() && routeInfo.isDefaultOrBluetooth() && i2 == 262 && i3 == 3 && routeInfo2 != null) {
                return !routeInfo2.isDefaultOrBluetooth();
            }
            return false;
        }
    }

    public static abstract class ControlRequestCallback {
        public void onError(@Nullable String str, @Nullable Bundle bundle) {
        }

        public void onResult(@Nullable Bundle bundle) {
        }
    }

    public static final class GlobalMediaRouter implements SystemMediaRouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback {
        private MediaRouterActiveScanThrottlingHelper mActiveScanThrottlingHelper;
        final Context mApplicationContext;
        private RouteInfo mBluetoothRoute;
        private int mCallbackCount;
        private MediaSessionCompat mCompatSession;
        RouteInfo mDefaultRoute;
        private MediaRouteDiscoveryRequest mDiscoveryRequest;
        private MediaRouteDiscoveryRequest mDiscoveryRequestForMr2Provider;
        private DisplayManagerCompat mDisplayManager;
        boolean mIsInitialized;
        private final boolean mLowRam;
        private MediaSessionRecord mMediaSession;
        MediaRoute2Provider mMr2Provider;
        OnPrepareTransferListener mOnPrepareTransferListener;
        MediaSessionCompat mRccMediaSession;

        @VisibleForTesting
        RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
        RouteInfo mRequestedRoute;
        MediaRouteProvider.RouteController mRequestedRouteController;
        private MediaRouterParams mRouterParams;
        RouteInfo mSelectedRoute;
        MediaRouteProvider.RouteController mSelectedRouteController;
        SystemMediaRouteProvider mSystemProvider;
        PrepareTransferNotifier mTransferNotifier;
        boolean mTransferReceiverDeclared;
        RouteInfo mTransferredRoute;
        MediaRouteProvider.RouteController mTransferredRouteController;
        final ArrayList<WeakReference<MediaRouter>> mRouters = new ArrayList<>();
        private final ArrayList<RouteInfo> mRoutes = new ArrayList<>();
        private final Map<Pair<String, String>, String> mUniqueIdMap = new HashMap();
        private final ArrayList<ProviderInfo> mProviders = new ArrayList<>();
        private final ArrayList<RemoteControlClientRecord> mRemoteControlClients = new ArrayList<>();
        final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
        private final ProviderCallback mProviderCallback = new ProviderCallback();
        final CallbackHandler mCallbackHandler = new CallbackHandler();
        final Map<String, MediaRouteProvider.RouteController> mRouteControllerMap = new HashMap();
        private final MediaSessionCompat.f mSessionActiveListener = new MediaSessionCompat.f() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.1
            public void onActiveChanged() {
                MediaSessionCompat mediaSessionCompat = GlobalMediaRouter.this.mRccMediaSession;
                if (mediaSessionCompat != null) {
                    if (mediaSessionCompat.g()) {
                        GlobalMediaRouter globalMediaRouter = GlobalMediaRouter.this;
                        globalMediaRouter.addRemoteControlClient(globalMediaRouter.mRccMediaSession.d());
                    } else {
                        GlobalMediaRouter globalMediaRouter2 = GlobalMediaRouter.this;
                        globalMediaRouter2.removeRemoteControlClient(globalMediaRouter2.mRccMediaSession.d());
                    }
                }
            }
        };
        MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.3
            @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
            public void onRoutesChanged(@NonNull MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, @Nullable MediaRouteDescriptor mediaRouteDescriptor, @NonNull Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                GlobalMediaRouter globalMediaRouter = GlobalMediaRouter.this;
                if (dynamicGroupRouteController != globalMediaRouter.mRequestedRouteController || mediaRouteDescriptor == null) {
                    if (dynamicGroupRouteController == globalMediaRouter.mSelectedRouteController) {
                        if (mediaRouteDescriptor != null) {
                            globalMediaRouter.updateRouteDescriptorAndNotify(globalMediaRouter.mSelectedRoute, mediaRouteDescriptor);
                        }
                        GlobalMediaRouter.this.mSelectedRoute.updateDynamicDescriptors(collection);
                        return;
                    }
                    return;
                }
                ProviderInfo provider = globalMediaRouter.mRequestedRoute.getProvider();
                String id = mediaRouteDescriptor.getId();
                RouteInfo routeInfo = new RouteInfo(provider, id, GlobalMediaRouter.this.assignRouteUniqueId(provider, id));
                routeInfo.maybeUpdateDescriptor(mediaRouteDescriptor);
                GlobalMediaRouter globalMediaRouter2 = GlobalMediaRouter.this;
                if (globalMediaRouter2.mSelectedRoute == routeInfo) {
                    return;
                }
                globalMediaRouter2.notifyTransfer(globalMediaRouter2, routeInfo, globalMediaRouter2.mRequestedRouteController, 3, globalMediaRouter2.mRequestedRoute, collection);
                GlobalMediaRouter globalMediaRouter3 = GlobalMediaRouter.this;
                globalMediaRouter3.mRequestedRoute = null;
                globalMediaRouter3.mRequestedRouteController = null;
            }
        };

        public final class CallbackHandler extends Handler {
            public static final int MSG_PROVIDER_ADDED = 513;
            public static final int MSG_PROVIDER_CHANGED = 515;
            public static final int MSG_PROVIDER_REMOVED = 514;
            public static final int MSG_ROUTER_PARAMS_CHANGED = 769;
            public static final int MSG_ROUTE_ADDED = 257;
            public static final int MSG_ROUTE_ANOTHER_SELECTED = 264;
            public static final int MSG_ROUTE_CHANGED = 259;
            public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
            public static final int MSG_ROUTE_REMOVED = 258;
            public static final int MSG_ROUTE_SELECTED = 262;
            public static final int MSG_ROUTE_UNSELECTED = 263;
            public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
            private static final int MSG_TYPE_MASK = 65280;
            private static final int MSG_TYPE_PROVIDER = 512;
            private static final int MSG_TYPE_ROUTE = 256;
            private static final int MSG_TYPE_ROUTER = 768;
            private final ArrayList<CallbackRecord> mTempCallbackRecords = new ArrayList<>();
            private final List<RouteInfo> mDynamicGroupRoutes = new ArrayList();

            public CallbackHandler() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            private void invokeCallback(CallbackRecord callbackRecord, int i2, Object obj, int i3) {
                MediaRouter mediaRouter = callbackRecord.mRouter;
                Callback callback = callbackRecord.mCallback;
                int i4 = 65280 & i2;
                if (i4 != 256) {
                    if (i4 != 512) {
                        if (i4 == MSG_TYPE_ROUTER && i2 == 769) {
                            callback.onRouterParamsChanged(mediaRouter, (MediaRouterParams) obj);
                            return;
                        }
                        return;
                    }
                    ProviderInfo providerInfo = (ProviderInfo) obj;
                    switch (i2) {
                        case 513:
                            callback.onProviderAdded(mediaRouter, providerInfo);
                            break;
                        case MSG_PROVIDER_REMOVED /* 514 */:
                            callback.onProviderRemoved(mediaRouter, providerInfo);
                            break;
                        case MSG_PROVIDER_CHANGED /* 515 */:
                            callback.onProviderChanged(mediaRouter, providerInfo);
                            break;
                    }
                }
                RouteInfo routeInfo = (i2 == 264 || i2 == 262) ? (RouteInfo) ((Pair) obj).second : (RouteInfo) obj;
                RouteInfo routeInfo2 = (i2 == 264 || i2 == 262) ? (RouteInfo) ((Pair) obj).first : null;
                if (routeInfo == null || !callbackRecord.filterRouteEvent(routeInfo, i2, routeInfo2, i3)) {
                    return;
                }
                switch (i2) {
                    case 257:
                        callback.onRouteAdded(mediaRouter, routeInfo);
                        break;
                    case MSG_ROUTE_REMOVED /* 258 */:
                        callback.onRouteRemoved(mediaRouter, routeInfo);
                        break;
                    case MSG_ROUTE_CHANGED /* 259 */:
                        callback.onRouteChanged(mediaRouter, routeInfo);
                        break;
                    case MSG_ROUTE_VOLUME_CHANGED /* 260 */:
                        callback.onRouteVolumeChanged(mediaRouter, routeInfo);
                        break;
                    case MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED /* 261 */:
                        callback.onRoutePresentationDisplayChanged(mediaRouter, routeInfo);
                        break;
                    case MSG_ROUTE_SELECTED /* 262 */:
                        callback.onRouteSelected(mediaRouter, routeInfo, i3, routeInfo);
                        break;
                    case MSG_ROUTE_UNSELECTED /* 263 */:
                        callback.onRouteUnselected(mediaRouter, routeInfo, i3);
                        break;
                    case MSG_ROUTE_ANOTHER_SELECTED /* 264 */:
                        callback.onRouteSelected(mediaRouter, routeInfo, i3, routeInfo2);
                        break;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            private void syncWithSystemProvider(int i2, Object obj) {
                if (i2 == 262) {
                    RouteInfo routeInfo = (RouteInfo) ((Pair) obj).second;
                    GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected(routeInfo);
                    if (GlobalMediaRouter.this.mDefaultRoute == null || !routeInfo.isDefaultOrBluetooth()) {
                        return;
                    }
                    Iterator<RouteInfo> it = this.mDynamicGroupRoutes.iterator();
                    while (it.hasNext()) {
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved(it.next());
                    }
                    this.mDynamicGroupRoutes.clear();
                }
                if (i2 == 264) {
                    RouteInfo routeInfo2 = (RouteInfo) ((Pair) obj).second;
                    this.mDynamicGroupRoutes.add(routeInfo2);
                    GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded(routeInfo2);
                    GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected(routeInfo2);
                    return;
                }
                switch (i2) {
                    case 257:
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded((RouteInfo) obj);
                        break;
                    case MSG_ROUTE_REMOVED /* 258 */:
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved((RouteInfo) obj);
                        break;
                    case MSG_ROUTE_CHANGED /* 259 */:
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteChanged((RouteInfo) obj);
                        break;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                Object obj = message.obj;
                int i3 = message.arg1;
                if (i2 == 259 && GlobalMediaRouter.this.getSelectedRoute().getId().equals(((RouteInfo) obj).getId())) {
                    GlobalMediaRouter.this.updateSelectedRouteIfNeeded(true);
                }
                syncWithSystemProvider(i2, obj);
                try {
                    int size = GlobalMediaRouter.this.mRouters.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        MediaRouter mediaRouter = GlobalMediaRouter.this.mRouters.get(size).get();
                        if (mediaRouter == null) {
                            GlobalMediaRouter.this.mRouters.remove(size);
                        } else {
                            this.mTempCallbackRecords.addAll(mediaRouter.mCallbackRecords);
                        }
                    }
                    int size2 = this.mTempCallbackRecords.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        invokeCallback(this.mTempCallbackRecords.get(i4), i2, obj, i3);
                    }
                    this.mTempCallbackRecords.clear();
                } catch (Throwable th) {
                    this.mTempCallbackRecords.clear();
                    throw th;
                }
            }

            public void post(int i2, Object obj) {
                obtainMessage(i2, obj).sendToTarget();
            }

            public void post(int i2, Object obj, int i3) {
                Message messageObtainMessage = obtainMessage(i2, obj);
                messageObtainMessage.arg1 = i3;
                messageObtainMessage.sendToTarget();
            }
        }

        public final class MediaSessionRecord {
            private int mControlType;
            private int mMaxVolume;
            private final MediaSessionCompat mMsCompat;
            private VolumeProviderCompat mVpCompat;

            public MediaSessionRecord(GlobalMediaRouter globalMediaRouter, Object obj) {
                this(MediaSessionCompat.b(globalMediaRouter.mApplicationContext, obj));
            }

            public void clearVolumeHandling() {
                MediaSessionCompat mediaSessionCompat = this.mMsCompat;
                if (mediaSessionCompat != null) {
                    mediaSessionCompat.h(GlobalMediaRouter.this.mPlaybackInfo.playbackStream);
                    this.mVpCompat = null;
                }
            }

            public void configureVolume(int i2, int i3, int i4, @Nullable String str) {
                if (this.mMsCompat != null) {
                    VolumeProviderCompat volumeProviderCompat = this.mVpCompat;
                    if (volumeProviderCompat != null && i2 == this.mControlType && i3 == this.mMaxVolume) {
                        volumeProviderCompat.setCurrentVolume(i4);
                        return;
                    }
                    VolumeProviderCompat volumeProviderCompat2 = new VolumeProviderCompat(i2, i3, i4, str) { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.MediaSessionRecord.1
                        @Override // androidx.media.VolumeProviderCompat
                        public void onAdjustVolume(final int i5) {
                            GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.MediaSessionRecord.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    RouteInfo routeInfo = GlobalMediaRouter.this.mSelectedRoute;
                                    if (routeInfo != null) {
                                        routeInfo.requestUpdateVolume(i5);
                                    }
                                }
                            });
                        }

                        @Override // androidx.media.VolumeProviderCompat
                        public void onSetVolumeTo(final int i5) {
                            GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.MediaSessionRecord.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    RouteInfo routeInfo = GlobalMediaRouter.this.mSelectedRoute;
                                    if (routeInfo != null) {
                                        routeInfo.requestSetVolume(i5);
                                    }
                                }
                            });
                        }
                    };
                    this.mVpCompat = volumeProviderCompat2;
                    this.mMsCompat.i(volumeProviderCompat2);
                }
            }

            public MediaSessionCompat.Token getToken() {
                MediaSessionCompat mediaSessionCompat = this.mMsCompat;
                if (mediaSessionCompat != null) {
                    return mediaSessionCompat.e();
                }
                return null;
            }

            public MediaSessionRecord(MediaSessionCompat mediaSessionCompat) {
                this.mMsCompat = mediaSessionCompat;
            }
        }

        public final class Mr2ProviderCallback extends MediaRoute2Provider.Callback {
            public Mr2ProviderCallback() {
            }

            @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
            public void onReleaseController(@NonNull MediaRouteProvider.RouteController routeController) {
                if (routeController == GlobalMediaRouter.this.mSelectedRouteController) {
                    selectRouteToFallbackRoute(2);
                } else if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "A RouteController unrelated to the selected route is released. controller=" + routeController);
                }
            }

            @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
            public void onSelectFallbackRoute(int i2) {
                selectRouteToFallbackRoute(i2);
            }

            @Override // androidx.mediarouter.media.MediaRoute2Provider.Callback
            public void onSelectRoute(@NonNull String str, int i2) {
                RouteInfo next;
                Iterator<RouteInfo> it = GlobalMediaRouter.this.getRoutes().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (next.getProviderInstance() == GlobalMediaRouter.this.mMr2Provider && TextUtils.equals(str, next.getDescriptorId())) {
                        break;
                    }
                }
                if (next != null) {
                    GlobalMediaRouter.this.selectRouteInternal(next, i2);
                    return;
                }
                Log.w(MediaRouter.TAG, "onSelectRoute: The target RouteInfo is not found for descriptorId=" + str);
            }

            public void selectRouteToFallbackRoute(int i2) {
                RouteInfo routeInfoChooseFallbackRoute = GlobalMediaRouter.this.chooseFallbackRoute();
                if (GlobalMediaRouter.this.getSelectedRoute() != routeInfoChooseFallbackRoute) {
                    GlobalMediaRouter.this.selectRouteInternal(routeInfoChooseFallbackRoute, i2);
                }
            }
        }

        public final class ProviderCallback extends MediaRouteProvider.Callback {
            public ProviderCallback() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
            public void onDescriptorChanged(@NonNull MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                GlobalMediaRouter.this.updateProviderDescriptor(mediaRouteProvider, mediaRouteProviderDescriptor);
            }
        }

        public final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
            private boolean mDisconnected;
            private final RemoteControlClientCompat mRccCompat;

            public RemoteControlClientRecord(Object obj) {
                RemoteControlClientCompat remoteControlClientCompatObtain = RemoteControlClientCompat.obtain(GlobalMediaRouter.this.mApplicationContext, obj);
                this.mRccCompat = remoteControlClientCompatObtain;
                remoteControlClientCompatObtain.setVolumeCallback(this);
                updatePlaybackInfo();
            }

            public void disconnect() {
                this.mDisconnected = true;
                this.mRccCompat.setVolumeCallback(null);
            }

            public Object getRemoteControlClient() {
                return this.mRccCompat.getRemoteControlClient();
            }

            @Override // androidx.mediarouter.media.RemoteControlClientCompat.VolumeCallback
            public void onVolumeSetRequest(int i2) {
                RouteInfo routeInfo;
                if (this.mDisconnected || (routeInfo = GlobalMediaRouter.this.mSelectedRoute) == null) {
                    return;
                }
                routeInfo.requestSetVolume(i2);
            }

            @Override // androidx.mediarouter.media.RemoteControlClientCompat.VolumeCallback
            public void onVolumeUpdateRequest(int i2) {
                RouteInfo routeInfo;
                if (this.mDisconnected || (routeInfo = GlobalMediaRouter.this.mSelectedRoute) == null) {
                    return;
                }
                routeInfo.requestUpdateVolume(i2);
            }

            public void updatePlaybackInfo() {
                this.mRccCompat.setPlaybackInfo(GlobalMediaRouter.this.mPlaybackInfo);
            }
        }

        public GlobalMediaRouter(Context context) {
            this.mApplicationContext = context;
            this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager) context.getSystemService("activity"));
        }

        private ProviderInfo findProviderInfo(MediaRouteProvider mediaRouteProvider) {
            int size = this.mProviders.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mProviders.get(i2).mProviderInstance == mediaRouteProvider) {
                    return this.mProviders.get(i2);
                }
            }
            return null;
        }

        private int findRemoteControlClientRecord(Object obj) {
            int size = this.mRemoteControlClients.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mRemoteControlClients.get(i2).getRemoteControlClient() == obj) {
                    return i2;
                }
            }
            return -1;
        }

        private int findRouteByUniqueId(String str) {
            int size = this.mRoutes.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mRoutes.get(i2).mUniqueId.equals(str)) {
                    return i2;
                }
            }
            return -1;
        }

        private boolean isSystemDefaultRoute(RouteInfo routeInfo) {
            return routeInfo.getProviderInstance() == this.mSystemProvider && routeInfo.mDescriptorId.equals(SystemMediaRouteProvider.DEFAULT_ROUTE_ID);
        }

        private boolean isSystemLiveAudioOnlyRoute(RouteInfo routeInfo) {
            return routeInfo.getProviderInstance() == this.mSystemProvider && routeInfo.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !routeInfo.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
        }

        private void setMediaSessionRecord(MediaSessionRecord mediaSessionRecord) {
            MediaSessionRecord mediaSessionRecord2 = this.mMediaSession;
            if (mediaSessionRecord2 != null) {
                mediaSessionRecord2.clearVolumeHandling();
            }
            this.mMediaSession = mediaSessionRecord;
            if (mediaSessionRecord != null) {
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        private void start() {
            this.mActiveScanThrottlingHelper = new MediaRouterActiveScanThrottlingHelper(new Runnable() { // from class: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.2
                @Override // java.lang.Runnable
                public void run() {
                    GlobalMediaRouter.this.updateDiscoveryRequest();
                }
            });
            addProvider(this.mSystemProvider);
            MediaRoute2Provider mediaRoute2Provider = this.mMr2Provider;
            if (mediaRoute2Provider != null) {
                addProvider(mediaRoute2Provider);
            }
            RegisteredMediaRouteProviderWatcher registeredMediaRouteProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this);
            this.mRegisteredProviderWatcher = registeredMediaRouteProviderWatcher;
            registeredMediaRouteProviderWatcher.start();
        }

        private void updateMr2ProviderDiscoveryRequest(@NonNull MediaRouteSelector mediaRouteSelector, boolean z2) {
            if (isMediaTransferEnabled()) {
                MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mDiscoveryRequestForMr2Provider;
                if (mediaRouteDiscoveryRequest != null && mediaRouteDiscoveryRequest.getSelector().equals(mediaRouteSelector) && this.mDiscoveryRequestForMr2Provider.isActiveScan() == z2) {
                    return;
                }
                if (!mediaRouteSelector.isEmpty() || z2) {
                    this.mDiscoveryRequestForMr2Provider = new MediaRouteDiscoveryRequest(mediaRouteSelector, z2);
                } else if (this.mDiscoveryRequestForMr2Provider == null) {
                    return;
                } else {
                    this.mDiscoveryRequestForMr2Provider = null;
                }
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Updated MediaRoute2Provider's discovery request: " + this.mDiscoveryRequestForMr2Provider);
                }
                this.mMr2Provider.setDiscoveryRequest(this.mDiscoveryRequestForMr2Provider);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void updateProviderContents(ProviderInfo providerInfo, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            boolean z2;
            if (providerInfo.updateDescriptor(mediaRouteProviderDescriptor)) {
                int i2 = 0;
                if (mediaRouteProviderDescriptor == null || !(mediaRouteProviderDescriptor.isValid() || mediaRouteProviderDescriptor == this.mSystemProvider.getDescriptor())) {
                    Log.w(MediaRouter.TAG, "Ignoring invalid provider descriptor: " + mediaRouteProviderDescriptor);
                    z2 = false;
                } else {
                    List<MediaRouteDescriptor> routes = mediaRouteProviderDescriptor.getRoutes();
                    ArrayList<Pair> arrayList = new ArrayList();
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    z2 = false;
                    for (MediaRouteDescriptor mediaRouteDescriptor : routes) {
                        if (mediaRouteDescriptor == null || !mediaRouteDescriptor.isValid()) {
                            Log.w(MediaRouter.TAG, "Ignoring invalid system route descriptor: " + mediaRouteDescriptor);
                        } else {
                            String id = mediaRouteDescriptor.getId();
                            int iFindRouteIndexByDescriptorId = providerInfo.findRouteIndexByDescriptorId(id);
                            if (iFindRouteIndexByDescriptorId < 0) {
                                RouteInfo routeInfo = new RouteInfo(providerInfo, id, assignRouteUniqueId(providerInfo, id));
                                int i3 = i2 + 1;
                                providerInfo.mRoutes.add(i2, routeInfo);
                                this.mRoutes.add(routeInfo);
                                if (mediaRouteDescriptor.getGroupMemberIds().size() > 0) {
                                    arrayList.add(new Pair(routeInfo, mediaRouteDescriptor));
                                } else {
                                    routeInfo.maybeUpdateDescriptor(mediaRouteDescriptor);
                                    if (MediaRouter.DEBUG) {
                                        Log.d(MediaRouter.TAG, "Route added: " + routeInfo);
                                    }
                                    this.mCallbackHandler.post(257, routeInfo);
                                }
                                i2 = i3;
                            } else if (iFindRouteIndexByDescriptorId < i2) {
                                Log.w(MediaRouter.TAG, "Ignoring route descriptor with duplicate id: " + mediaRouteDescriptor);
                            } else {
                                RouteInfo routeInfo2 = providerInfo.mRoutes.get(iFindRouteIndexByDescriptorId);
                                int i4 = i2 + 1;
                                Collections.swap(providerInfo.mRoutes, iFindRouteIndexByDescriptorId, i2);
                                if (mediaRouteDescriptor.getGroupMemberIds().size() > 0) {
                                    arrayList2.add(new Pair(routeInfo2, mediaRouteDescriptor));
                                } else if (updateRouteDescriptorAndNotify(routeInfo2, mediaRouteDescriptor) != 0 && routeInfo2 == this.mSelectedRoute) {
                                    z2 = true;
                                }
                                i2 = i4;
                            }
                        }
                    }
                    for (Pair pair : arrayList) {
                        RouteInfo routeInfo3 = (RouteInfo) pair.first;
                        routeInfo3.maybeUpdateDescriptor((MediaRouteDescriptor) pair.second);
                        if (MediaRouter.DEBUG) {
                            Log.d(MediaRouter.TAG, "Route added: " + routeInfo3);
                        }
                        this.mCallbackHandler.post(257, routeInfo3);
                    }
                    for (Pair pair2 : arrayList2) {
                        RouteInfo routeInfo4 = (RouteInfo) pair2.first;
                        if (updateRouteDescriptorAndNotify(routeInfo4, (MediaRouteDescriptor) pair2.second) != 0 && routeInfo4 == this.mSelectedRoute) {
                            z2 = true;
                        }
                    }
                }
                for (int size = providerInfo.mRoutes.size() - 1; size >= i2; size--) {
                    RouteInfo routeInfo5 = providerInfo.mRoutes.get(size);
                    routeInfo5.maybeUpdateDescriptor(null);
                    this.mRoutes.remove(routeInfo5);
                }
                updateSelectedRouteIfNeeded(z2);
                for (int size2 = providerInfo.mRoutes.size() - 1; size2 >= i2; size2--) {
                    RouteInfo routeInfoRemove = providerInfo.mRoutes.remove(size2);
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route removed: " + routeInfoRemove);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_REMOVED, routeInfoRemove);
                }
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider changed: " + providerInfo);
                }
                this.mCallbackHandler.post(CallbackHandler.MSG_PROVIDER_CHANGED, providerInfo);
            }
        }

        public void addMemberToDynamicGroup(@NonNull RouteInfo routeInfo) {
            if (!(this.mSelectedRouteController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            RouteInfo.DynamicGroupState dynamicGroupState = getDynamicGroupState(routeInfo);
            if (!this.mSelectedRoute.getMemberRoutes().contains(routeInfo) && dynamicGroupState != null && dynamicGroupState.isGroupable()) {
                ((MediaRouteProvider.DynamicGroupRouteController) this.mSelectedRouteController).onAddMemberRoute(routeInfo.getDescriptorId());
                return;
            }
            Log.w(MediaRouter.TAG, "Ignoring attempt to add a non-groupable route to dynamic group : " + routeInfo);
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
        public void addProvider(@NonNull MediaRouteProvider mediaRouteProvider) {
            if (findProviderInfo(mediaRouteProvider) == null) {
                ProviderInfo providerInfo = new ProviderInfo(mediaRouteProvider);
                this.mProviders.add(providerInfo);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider added: " + providerInfo);
                }
                this.mCallbackHandler.post(513, providerInfo);
                updateProviderContents(providerInfo, mediaRouteProvider.getDescriptor());
                mediaRouteProvider.setCallback(this.mProviderCallback);
                mediaRouteProvider.setDiscoveryRequest(this.mDiscoveryRequest);
            }
        }

        public void addRemoteControlClient(Object obj) {
            if (findRemoteControlClientRecord(obj) < 0) {
                this.mRemoteControlClients.add(new RemoteControlClientRecord(obj));
            }
        }

        public String assignRouteUniqueId(ProviderInfo providerInfo, String str) {
            String strFlattenToShortString = providerInfo.getComponentName().flattenToShortString();
            String str2 = strFlattenToShortString + MethodCodeHelper.IDENTITY_INFO_SEPARATOR + str;
            if (findRouteByUniqueId(str2) < 0) {
                this.mUniqueIdMap.put(new Pair<>(strFlattenToShortString, str), str2);
                return str2;
            }
            Log.w(MediaRouter.TAG, "Either " + str + " isn't unique in " + strFlattenToShortString + " or we're trying to assign a unique ID for an already added route");
            int i2 = 2;
            while (true) {
                String str3 = String.format(Locale.US, "%s_%d", str2, Integer.valueOf(i2));
                if (findRouteByUniqueId(str3) < 0) {
                    this.mUniqueIdMap.put(new Pair<>(strFlattenToShortString, str), str3);
                    return str3;
                }
                i2++;
            }
        }

        public RouteInfo chooseFallbackRoute() {
            for (RouteInfo routeInfo : this.mRoutes) {
                if (routeInfo != this.mDefaultRoute && isSystemLiveAudioOnlyRoute(routeInfo) && routeInfo.isSelectable()) {
                    return routeInfo;
                }
            }
            return this.mDefaultRoute;
        }

        @SuppressLint({"NewApi", "SyntheticAccessor"})
        public void ensureInitialized() {
            if (this.mIsInitialized) {
                return;
            }
            this.mIsInitialized = true;
            boolean zIsDeclared = MediaTransferReceiver.isDeclared(this.mApplicationContext);
            this.mTransferReceiverDeclared = zIsDeclared;
            if (zIsDeclared) {
                this.mMr2Provider = new MediaRoute2Provider(this.mApplicationContext, new Mr2ProviderCallback());
            } else {
                this.mMr2Provider = null;
            }
            this.mSystemProvider = SystemMediaRouteProvider.obtain(this.mApplicationContext, this);
            start();
        }

        public RouteInfo getBluetoothRoute() {
            return this.mBluetoothRoute;
        }

        public int getCallbackCount() {
            return this.mCallbackCount;
        }

        public ContentResolver getContentResolver() {
            return this.mApplicationContext.getContentResolver();
        }

        @NonNull
        public RouteInfo getDefaultRoute() {
            RouteInfo routeInfo = this.mDefaultRoute;
            if (routeInfo != null) {
                return routeInfo;
            }
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
        }

        public Display getDisplay(int i2) {
            if (this.mDisplayManager == null) {
                this.mDisplayManager = DisplayManagerCompat.getInstance(this.mApplicationContext);
            }
            return this.mDisplayManager.getDisplay(i2);
        }

        @Nullable
        public RouteInfo.DynamicGroupState getDynamicGroupState(RouteInfo routeInfo) {
            return this.mSelectedRoute.getDynamicGroupState(routeInfo);
        }

        public MediaSessionCompat.Token getMediaSessionToken() {
            MediaSessionRecord mediaSessionRecord = this.mMediaSession;
            if (mediaSessionRecord != null) {
                return mediaSessionRecord.getToken();
            }
            MediaSessionCompat mediaSessionCompat = this.mCompatSession;
            if (mediaSessionCompat != null) {
                return mediaSessionCompat.e();
            }
            return null;
        }

        public Context getProviderContext(String str) {
            if (str.equals(SystemMediaRouteProvider.PACKAGE_NAME)) {
                return this.mApplicationContext;
            }
            try {
                return this.mApplicationContext.createPackageContext(str, 4);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        @Nullable
        public List<ProviderInfo> getProviders() {
            return this.mProviders;
        }

        public RouteInfo getRoute(String str) {
            for (RouteInfo routeInfo : this.mRoutes) {
                if (routeInfo.mUniqueId.equals(str)) {
                    return routeInfo;
                }
            }
            return null;
        }

        public MediaRouter getRouter(Context context) {
            int size = this.mRouters.size();
            while (true) {
                size--;
                if (size < 0) {
                    MediaRouter mediaRouter = new MediaRouter(context);
                    this.mRouters.add(new WeakReference<>(mediaRouter));
                    return mediaRouter;
                }
                MediaRouter mediaRouter2 = this.mRouters.get(size).get();
                if (mediaRouter2 == null) {
                    this.mRouters.remove(size);
                } else if (mediaRouter2.mContext == context) {
                    return mediaRouter2;
                }
            }
        }

        @Nullable
        public MediaRouterParams getRouterParams() {
            return this.mRouterParams;
        }

        public List<RouteInfo> getRoutes() {
            return this.mRoutes;
        }

        @NonNull
        public RouteInfo getSelectedRoute() {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo != null) {
                return routeInfo;
            }
            throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
        }

        public String getUniqueId(ProviderInfo providerInfo, String str) {
            return this.mUniqueIdMap.get(new Pair(providerInfo.getComponentName().flattenToShortString(), str));
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public boolean isGroupVolumeUxEnabled() {
            Bundle bundle;
            MediaRouterParams mediaRouterParams = this.mRouterParams;
            return mediaRouterParams == null || (bundle = mediaRouterParams.mExtras) == null || bundle.getBoolean(MediaRouterParams.ENABLE_GROUP_VOLUME_UX, true);
        }

        public boolean isMediaTransferEnabled() {
            MediaRouterParams mediaRouterParams;
            return this.mTransferReceiverDeclared && ((mediaRouterParams = this.mRouterParams) == null || mediaRouterParams.isMediaTransferReceiverEnabled());
        }

        public boolean isRouteAvailable(MediaRouteSelector mediaRouteSelector, int i2) {
            if (mediaRouteSelector.isEmpty()) {
                return false;
            }
            if ((i2 & 2) == 0 && this.mLowRam) {
                return true;
            }
            MediaRouterParams mediaRouterParams = this.mRouterParams;
            boolean z2 = mediaRouterParams != null && mediaRouterParams.isOutputSwitcherEnabled() && isMediaTransferEnabled();
            int size = this.mRoutes.size();
            for (int i3 = 0; i3 < size; i3++) {
                RouteInfo routeInfo = this.mRoutes.get(i3);
                if (((i2 & 1) == 0 || !routeInfo.isDefaultOrBluetooth()) && ((!z2 || routeInfo.isDefaultOrBluetooth() || routeInfo.getProviderInstance() == this.mMr2Provider) && routeInfo.matchesSelector(mediaRouteSelector))) {
                    return true;
                }
            }
            return false;
        }

        public boolean isTransferToLocalEnabled() {
            MediaRouterParams mediaRouterParams = this.mRouterParams;
            if (mediaRouterParams == null) {
                return false;
            }
            return mediaRouterParams.isTransferToLocalEnabled();
        }

        public void maybeUpdateMemberRouteControllers() {
            if (this.mSelectedRoute.isGroup()) {
                List<RouteInfo> memberRoutes = this.mSelectedRoute.getMemberRoutes();
                HashSet hashSet = new HashSet();
                Iterator<RouteInfo> it = memberRoutes.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().mUniqueId);
                }
                Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> it2 = this.mRouteControllerMap.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<String, MediaRouteProvider.RouteController> next = it2.next();
                    if (!hashSet.contains(next.getKey())) {
                        MediaRouteProvider.RouteController value = next.getValue();
                        value.onUnselect(0);
                        value.onRelease();
                        it2.remove();
                    }
                }
                for (RouteInfo routeInfo : memberRoutes) {
                    if (!this.mRouteControllerMap.containsKey(routeInfo.mUniqueId)) {
                        MediaRouteProvider.RouteController routeControllerOnCreateRouteController = routeInfo.getProviderInstance().onCreateRouteController(routeInfo.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                        routeControllerOnCreateRouteController.onSelect();
                        this.mRouteControllerMap.put(routeInfo.mUniqueId, routeControllerOnCreateRouteController);
                    }
                }
            }
        }

        public void notifyTransfer(GlobalMediaRouter globalMediaRouter, RouteInfo routeInfo, @Nullable MediaRouteProvider.RouteController routeController, int i2, @Nullable RouteInfo routeInfo2, @Nullable Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            OnPrepareTransferListener onPrepareTransferListener;
            PrepareTransferNotifier prepareTransferNotifier = this.mTransferNotifier;
            if (prepareTransferNotifier != null) {
                prepareTransferNotifier.cancel();
                this.mTransferNotifier = null;
            }
            PrepareTransferNotifier prepareTransferNotifier2 = new PrepareTransferNotifier(globalMediaRouter, routeInfo, routeController, i2, routeInfo2, collection);
            this.mTransferNotifier = prepareTransferNotifier2;
            if (prepareTransferNotifier2.mReason != 3 || (onPrepareTransferListener = this.mOnPrepareTransferListener) == null) {
                prepareTransferNotifier2.finishTransfer();
                return;
            }
            T.a aVarOnPrepareTransfer = onPrepareTransferListener.onPrepareTransfer(this.mSelectedRoute, prepareTransferNotifier2.mToRoute);
            if (aVarOnPrepareTransfer == null) {
                this.mTransferNotifier.finishTransfer();
            } else {
                this.mTransferNotifier.setFuture(aVarOnPrepareTransfer);
            }
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.SyncCallback
        public void onSystemRouteSelectedByDescriptorId(@NonNull String str) {
            RouteInfo routeInfoFindRouteByDescriptorId;
            this.mCallbackHandler.removeMessages(CallbackHandler.MSG_ROUTE_SELECTED);
            ProviderInfo providerInfoFindProviderInfo = findProviderInfo(this.mSystemProvider);
            if (providerInfoFindProviderInfo == null || (routeInfoFindRouteByDescriptorId = providerInfoFindProviderInfo.findRouteByDescriptorId(str)) == null) {
                return;
            }
            routeInfoFindRouteByDescriptorId.select();
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
        public void releaseProviderController(@NonNull RegisteredMediaRouteProvider registeredMediaRouteProvider, @NonNull MediaRouteProvider.RouteController routeController) {
            if (this.mSelectedRouteController == routeController) {
                selectRoute(chooseFallbackRoute(), 2);
            }
        }

        public void removeMemberFromDynamicGroup(@NonNull RouteInfo routeInfo) {
            if (!(this.mSelectedRouteController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            RouteInfo.DynamicGroupState dynamicGroupState = getDynamicGroupState(routeInfo);
            if (this.mSelectedRoute.getMemberRoutes().contains(routeInfo) && dynamicGroupState != null && dynamicGroupState.isUnselectable()) {
                if (this.mSelectedRoute.getMemberRoutes().size() <= 1) {
                    Log.w(MediaRouter.TAG, "Ignoring attempt to remove the last member route.");
                    return;
                } else {
                    ((MediaRouteProvider.DynamicGroupRouteController) this.mSelectedRouteController).onRemoveMemberRoute(routeInfo.getDescriptorId());
                    return;
                }
            }
            Log.w(MediaRouter.TAG, "Ignoring attempt to remove a non-unselectable member route : " + routeInfo);
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.Callback
        public void removeProvider(@NonNull MediaRouteProvider mediaRouteProvider) {
            ProviderInfo providerInfoFindProviderInfo = findProviderInfo(mediaRouteProvider);
            if (providerInfoFindProviderInfo != null) {
                mediaRouteProvider.setCallback(null);
                mediaRouteProvider.setDiscoveryRequest(null);
                updateProviderContents(providerInfoFindProviderInfo, null);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider removed: " + providerInfoFindProviderInfo);
                }
                this.mCallbackHandler.post(CallbackHandler.MSG_PROVIDER_REMOVED, providerInfoFindProviderInfo);
                this.mProviders.remove(providerInfoFindProviderInfo);
            }
        }

        public void removeRemoteControlClient(Object obj) {
            int iFindRemoteControlClientRecord = findRemoteControlClientRecord(obj);
            if (iFindRemoteControlClientRecord >= 0) {
                this.mRemoteControlClients.remove(iFindRemoteControlClientRecord).disconnect();
            }
        }

        public void requestSetVolume(RouteInfo routeInfo, int i2) {
            MediaRouteProvider.RouteController routeController;
            MediaRouteProvider.RouteController routeController2;
            if (routeInfo == this.mSelectedRoute && (routeController2 = this.mSelectedRouteController) != null) {
                routeController2.onSetVolume(i2);
            } else {
                if (this.mRouteControllerMap.isEmpty() || (routeController = this.mRouteControllerMap.get(routeInfo.mUniqueId)) == null) {
                    return;
                }
                routeController.onSetVolume(i2);
            }
        }

        public void requestUpdateVolume(RouteInfo routeInfo, int i2) {
            MediaRouteProvider.RouteController routeController;
            MediaRouteProvider.RouteController routeController2;
            if (routeInfo == this.mSelectedRoute && (routeController2 = this.mSelectedRouteController) != null) {
                routeController2.onUpdateVolume(i2);
            } else {
                if (this.mRouteControllerMap.isEmpty() || (routeController = this.mRouteControllerMap.get(routeInfo.mUniqueId)) == null) {
                    return;
                }
                routeController.onUpdateVolume(i2);
            }
        }

        public void reset() {
            if (this.mIsInitialized) {
                this.mRegisteredProviderWatcher.stop();
                this.mActiveScanThrottlingHelper.reset();
                setMediaSessionCompat(null);
                Iterator<RemoteControlClientRecord> it = this.mRemoteControlClients.iterator();
                while (it.hasNext()) {
                    it.next().disconnect();
                }
                Iterator it2 = new ArrayList(this.mProviders).iterator();
                while (it2.hasNext()) {
                    removeProvider(((ProviderInfo) it2.next()).mProviderInstance);
                }
                this.mCallbackHandler.removeCallbacksAndMessages(null);
            }
        }

        public void selectRoute(@NonNull RouteInfo routeInfo, int i2) {
            if (!this.mRoutes.contains(routeInfo)) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to select removed route: " + routeInfo);
                return;
            }
            if (!routeInfo.mEnabled) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to select disabled route: " + routeInfo);
                return;
            }
            MediaRouteProvider providerInstance = routeInfo.getProviderInstance();
            MediaRoute2Provider mediaRoute2Provider = this.mMr2Provider;
            if (providerInstance != mediaRoute2Provider || this.mSelectedRoute == routeInfo) {
                selectRouteInternal(routeInfo, i2);
            } else {
                mediaRoute2Provider.transferTo(routeInfo.getDescriptorId());
            }
        }

        public void selectRouteInternal(@NonNull RouteInfo routeInfo, int i2) {
            if (MediaRouter.sGlobal == null || (this.mBluetoothRoute != null && routeInfo.isDefault())) {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                for (int i3 = 3; i3 < stackTrace.length; i3++) {
                    StackTraceElement stackTraceElement = stackTrace[i3];
                    sb.append(stackTraceElement.getClassName());
                    sb.append(".");
                    sb.append(stackTraceElement.getMethodName());
                    sb.append(MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
                    sb.append(stackTraceElement.getLineNumber());
                    sb.append("  ");
                }
                if (MediaRouter.sGlobal == null) {
                    Log.w(MediaRouter.TAG, "setSelectedRouteInternal is called while sGlobal is null: pkgName=" + this.mApplicationContext.getPackageName() + ", callers=" + ((Object) sb));
                } else {
                    Log.w(MediaRouter.TAG, "Default route is selected while a BT route is available: pkgName=" + this.mApplicationContext.getPackageName() + ", callers=" + ((Object) sb));
                }
            }
            if (this.mSelectedRoute == routeInfo) {
                return;
            }
            if (this.mRequestedRoute != null) {
                this.mRequestedRoute = null;
                MediaRouteProvider.RouteController routeController = this.mRequestedRouteController;
                if (routeController != null) {
                    routeController.onUnselect(3);
                    this.mRequestedRouteController.onRelease();
                    this.mRequestedRouteController = null;
                }
            }
            if (isMediaTransferEnabled() && routeInfo.getProvider().supportsDynamicGroup()) {
                MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerOnCreateDynamicGroupRouteController = routeInfo.getProviderInstance().onCreateDynamicGroupRouteController(routeInfo.mDescriptorId);
                if (dynamicGroupRouteControllerOnCreateDynamicGroupRouteController != null) {
                    dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mApplicationContext), this.mDynamicRoutesListener);
                    this.mRequestedRoute = routeInfo;
                    this.mRequestedRouteController = dynamicGroupRouteControllerOnCreateDynamicGroupRouteController;
                    dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.onSelect();
                    return;
                }
                Log.w(MediaRouter.TAG, "setSelectedRouteInternal: Failed to create dynamic group route controller. route=" + routeInfo);
            }
            MediaRouteProvider.RouteController routeControllerOnCreateRouteController = routeInfo.getProviderInstance().onCreateRouteController(routeInfo.mDescriptorId);
            if (routeControllerOnCreateRouteController != null) {
                routeControllerOnCreateRouteController.onSelect();
            }
            if (MediaRouter.DEBUG) {
                Log.d(MediaRouter.TAG, "Route selected: " + routeInfo);
            }
            if (this.mSelectedRoute != null) {
                notifyTransfer(this, routeInfo, routeControllerOnCreateRouteController, i2, null, null);
                return;
            }
            this.mSelectedRoute = routeInfo;
            this.mSelectedRouteController = routeControllerOnCreateRouteController;
            this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_SELECTED, new Pair(null, routeInfo), i2);
        }

        public void sendControlRequest(RouteInfo routeInfo, Intent intent, ControlRequestCallback controlRequestCallback) {
            MediaRouteProvider.RouteController routeController;
            MediaRouteProvider.RouteController routeController2;
            if (routeInfo == this.mSelectedRoute && (routeController2 = this.mSelectedRouteController) != null && routeController2.onControlRequest(intent, controlRequestCallback)) {
                return;
            }
            PrepareTransferNotifier prepareTransferNotifier = this.mTransferNotifier;
            if ((prepareTransferNotifier == null || routeInfo != prepareTransferNotifier.mToRoute || (routeController = prepareTransferNotifier.mToRouteController) == null || !routeController.onControlRequest(intent, controlRequestCallback)) && controlRequestCallback != null) {
                controlRequestCallback.onError(null, null);
            }
        }

        public void setMediaSession(Object obj) {
            setMediaSessionRecord(obj != null ? new MediaSessionRecord(this, obj) : null);
        }

        public void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
            this.mCompatSession = mediaSessionCompat;
            setMediaSessionRecord(mediaSessionCompat != null ? new MediaSessionRecord(mediaSessionCompat) : null);
        }

        @SuppressLint({"NewApi"})
        public void setRouterParams(@Nullable MediaRouterParams mediaRouterParams) {
            MediaRouterParams mediaRouterParams2 = this.mRouterParams;
            this.mRouterParams = mediaRouterParams;
            if (isMediaTransferEnabled()) {
                if (this.mMr2Provider == null) {
                    MediaRoute2Provider mediaRoute2Provider = new MediaRoute2Provider(this.mApplicationContext, new Mr2ProviderCallback());
                    this.mMr2Provider = mediaRoute2Provider;
                    addProvider(mediaRoute2Provider);
                    updateDiscoveryRequest();
                    this.mRegisteredProviderWatcher.rescan();
                }
                boolean z2 = false;
                boolean z3 = mediaRouterParams2 != null && mediaRouterParams2.isTransferToLocalEnabled();
                if (mediaRouterParams != null && mediaRouterParams.isTransferToLocalEnabled()) {
                    z2 = true;
                }
                if (z3 != z2) {
                    this.mMr2Provider.setDiscoveryRequestInternal(this.mDiscoveryRequestForMr2Provider);
                }
            } else {
                MediaRouteProvider mediaRouteProvider = this.mMr2Provider;
                if (mediaRouteProvider != null) {
                    removeProvider(mediaRouteProvider);
                    this.mMr2Provider = null;
                    this.mRegisteredProviderWatcher.rescan();
                }
            }
            this.mCallbackHandler.post(CallbackHandler.MSG_ROUTER_PARAMS_CHANGED, mediaRouterParams);
        }

        public void transferToRoute(@NonNull RouteInfo routeInfo) {
            if (!(this.mSelectedRouteController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                throw new IllegalStateException("There is no currently selected dynamic group route.");
            }
            RouteInfo.DynamicGroupState dynamicGroupState = getDynamicGroupState(routeInfo);
            if (dynamicGroupState == null || !dynamicGroupState.isTransferable()) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to transfer to a non-transferable route.");
            } else {
                ((MediaRouteProvider.DynamicGroupRouteController) this.mSelectedRouteController).onUpdateMemberRoutes(Collections.singletonList(routeInfo.getDescriptorId()));
            }
        }

        public void updateDiscoveryRequest() {
            MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
            this.mActiveScanThrottlingHelper.reset();
            int size = this.mRouters.size();
            int i2 = 0;
            boolean z2 = false;
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                MediaRouter mediaRouter = this.mRouters.get(size).get();
                if (mediaRouter == null) {
                    this.mRouters.remove(size);
                } else {
                    int size2 = mediaRouter.mCallbackRecords.size();
                    i2 += size2;
                    for (int i3 = 0; i3 < size2; i3++) {
                        CallbackRecord callbackRecord = mediaRouter.mCallbackRecords.get(i3);
                        builder.addSelector(callbackRecord.mSelector);
                        boolean z3 = (callbackRecord.mFlags & 1) != 0;
                        this.mActiveScanThrottlingHelper.requestActiveScan(z3, callbackRecord.mTimestamp);
                        if (z3) {
                            z2 = true;
                        }
                        int i4 = callbackRecord.mFlags;
                        if ((i4 & 4) != 0 && !this.mLowRam) {
                            z2 = true;
                        }
                        if ((i4 & 8) != 0) {
                            z2 = true;
                        }
                    }
                }
            }
            boolean zFinalizeActiveScanAndScheduleSuppressActiveScanRunnable = this.mActiveScanThrottlingHelper.finalizeActiveScanAndScheduleSuppressActiveScanRunnable();
            this.mCallbackCount = i2;
            MediaRouteSelector mediaRouteSelectorBuild = z2 ? builder.build() : MediaRouteSelector.EMPTY;
            updateMr2ProviderDiscoveryRequest(builder.build(), zFinalizeActiveScanAndScheduleSuppressActiveScanRunnable);
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mDiscoveryRequest;
            if (mediaRouteDiscoveryRequest != null && mediaRouteDiscoveryRequest.getSelector().equals(mediaRouteSelectorBuild) && this.mDiscoveryRequest.isActiveScan() == zFinalizeActiveScanAndScheduleSuppressActiveScanRunnable) {
                return;
            }
            if (!mediaRouteSelectorBuild.isEmpty() || zFinalizeActiveScanAndScheduleSuppressActiveScanRunnable) {
                this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(mediaRouteSelectorBuild, zFinalizeActiveScanAndScheduleSuppressActiveScanRunnable);
            } else if (this.mDiscoveryRequest == null) {
                return;
            } else {
                this.mDiscoveryRequest = null;
            }
            if (MediaRouter.DEBUG) {
                Log.d(MediaRouter.TAG, "Updated discovery request: " + this.mDiscoveryRequest);
            }
            if (z2 && !zFinalizeActiveScanAndScheduleSuppressActiveScanRunnable && this.mLowRam) {
                Log.i(MediaRouter.TAG, "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
            }
            int size3 = this.mProviders.size();
            for (int i5 = 0; i5 < size3; i5++) {
                MediaRouteProvider mediaRouteProvider = this.mProviders.get(i5).mProviderInstance;
                if (mediaRouteProvider != this.mMr2Provider) {
                    mediaRouteProvider.setDiscoveryRequest(this.mDiscoveryRequest);
                }
            }
        }

        @SuppressLint({"NewApi"})
        public void updatePlaybackInfoFromSelectedRoute() {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo == null) {
                MediaSessionRecord mediaSessionRecord = this.mMediaSession;
                if (mediaSessionRecord != null) {
                    mediaSessionRecord.clearVolumeHandling();
                    return;
                }
                return;
            }
            this.mPlaybackInfo.volume = routeInfo.getVolume();
            this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
            this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
            this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
            this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
            if (isMediaTransferEnabled() && this.mSelectedRoute.getProviderInstance() == this.mMr2Provider) {
                this.mPlaybackInfo.volumeControlId = MediaRoute2Provider.getSessionIdForRouteController(this.mSelectedRouteController);
            } else {
                this.mPlaybackInfo.volumeControlId = null;
            }
            int size = this.mRemoteControlClients.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mRemoteControlClients.get(i2).updatePlaybackInfo();
            }
            if (this.mMediaSession != null) {
                if (this.mSelectedRoute == getDefaultRoute() || this.mSelectedRoute == getBluetoothRoute()) {
                    this.mMediaSession.clearVolumeHandling();
                } else {
                    RemoteControlClientCompat.PlaybackInfo playbackInfo = this.mPlaybackInfo;
                    this.mMediaSession.configureVolume(playbackInfo.volumeHandling == 1 ? 2 : 0, playbackInfo.volumeMax, playbackInfo.volume, playbackInfo.volumeControlId);
                }
            }
        }

        public void updateProviderDescriptor(MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            ProviderInfo providerInfoFindProviderInfo = findProviderInfo(mediaRouteProvider);
            if (providerInfoFindProviderInfo != null) {
                updateProviderContents(providerInfoFindProviderInfo, mediaRouteProviderDescriptor);
            }
        }

        public int updateRouteDescriptorAndNotify(RouteInfo routeInfo, MediaRouteDescriptor mediaRouteDescriptor) {
            int iMaybeUpdateDescriptor = routeInfo.maybeUpdateDescriptor(mediaRouteDescriptor);
            if (iMaybeUpdateDescriptor != 0) {
                if ((iMaybeUpdateDescriptor & 1) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route changed: " + routeInfo);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_CHANGED, routeInfo);
                }
                if ((iMaybeUpdateDescriptor & 2) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route volume changed: " + routeInfo);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, routeInfo);
                }
                if ((iMaybeUpdateDescriptor & 4) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route presentation display changed: " + routeInfo);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED, routeInfo);
                }
            }
            return iMaybeUpdateDescriptor;
        }

        public void updateSelectedRouteIfNeeded(boolean z2) {
            RouteInfo routeInfo = this.mDefaultRoute;
            if (routeInfo != null && !routeInfo.isSelectable()) {
                Log.i(MediaRouter.TAG, "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
                this.mDefaultRoute = null;
            }
            if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
                Iterator<RouteInfo> it = this.mRoutes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    RouteInfo next = it.next();
                    if (isSystemDefaultRoute(next) && next.isSelectable()) {
                        this.mDefaultRoute = next;
                        Log.i(MediaRouter.TAG, "Found default route: " + this.mDefaultRoute);
                        break;
                    }
                }
            }
            RouteInfo routeInfo2 = this.mBluetoothRoute;
            if (routeInfo2 != null && !routeInfo2.isSelectable()) {
                Log.i(MediaRouter.TAG, "Clearing the bluetooth route because it is no longer selectable: " + this.mBluetoothRoute);
                this.mBluetoothRoute = null;
            }
            if (this.mBluetoothRoute == null && !this.mRoutes.isEmpty()) {
                Iterator<RouteInfo> it2 = this.mRoutes.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    RouteInfo next2 = it2.next();
                    if (isSystemLiveAudioOnlyRoute(next2) && next2.isSelectable()) {
                        this.mBluetoothRoute = next2;
                        Log.i(MediaRouter.TAG, "Found bluetooth route: " + this.mBluetoothRoute);
                        break;
                    }
                }
            }
            RouteInfo routeInfo3 = this.mSelectedRoute;
            if (routeInfo3 != null && routeInfo3.isEnabled()) {
                if (z2) {
                    maybeUpdateMemberRouteControllers();
                    updatePlaybackInfoFromSelectedRoute();
                    return;
                }
                return;
            }
            Log.i(MediaRouter.TAG, "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
            selectRouteInternal(chooseFallbackRoute(), 0);
        }
    }

    public interface OnPrepareTransferListener {
        @Nullable
        @MainThread
        T.a onPrepareTransfer(@NonNull RouteInfo routeInfo, @NonNull RouteInfo routeInfo2);
    }

    public static final class PrepareTransferNotifier {
        private static final long TRANSFER_TIMEOUT_MS = 15000;
        private final RouteInfo mFromRoute;

        @Nullable
        final List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> mMemberRoutes;
        final int mReason;
        private final RouteInfo mRequestedRoute;
        private final WeakReference<GlobalMediaRouter> mRouter;
        final RouteInfo mToRoute;
        final MediaRouteProvider.RouteController mToRouteController;
        private T.a mFuture = null;
        private boolean mFinished = false;
        private boolean mCanceled = false;

        public PrepareTransferNotifier(GlobalMediaRouter globalMediaRouter, RouteInfo routeInfo, @Nullable MediaRouteProvider.RouteController routeController, int i2, @Nullable RouteInfo routeInfo2, @Nullable Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            this.mRouter = new WeakReference<>(globalMediaRouter);
            this.mToRoute = routeInfo;
            this.mToRouteController = routeController;
            this.mReason = i2;
            this.mFromRoute = globalMediaRouter.mSelectedRoute;
            this.mRequestedRoute = routeInfo2;
            this.mMemberRoutes = collection != null ? new ArrayList(collection) : null;
            globalMediaRouter.mCallbackHandler.postDelayed(new d(this), 15000L);
        }

        private void selectToRouteAndNotify() {
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null) {
                return;
            }
            RouteInfo routeInfo = this.mToRoute;
            globalMediaRouter.mSelectedRoute = routeInfo;
            globalMediaRouter.mSelectedRouteController = this.mToRouteController;
            RouteInfo routeInfo2 = this.mRequestedRoute;
            if (routeInfo2 == null) {
                globalMediaRouter.mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, new Pair(this.mFromRoute, routeInfo), this.mReason);
            } else {
                globalMediaRouter.mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED, new Pair(routeInfo2, routeInfo), this.mReason);
            }
            globalMediaRouter.mRouteControllerMap.clear();
            globalMediaRouter.maybeUpdateMemberRouteControllers();
            globalMediaRouter.updatePlaybackInfoFromSelectedRoute();
            List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> list = this.mMemberRoutes;
            if (list != null) {
                globalMediaRouter.mSelectedRoute.updateDynamicDescriptors(list);
            }
        }

        private void unselectFromRouteAndNotify() {
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter != null) {
                RouteInfo routeInfo = globalMediaRouter.mSelectedRoute;
                RouteInfo routeInfo2 = this.mFromRoute;
                if (routeInfo != routeInfo2) {
                    return;
                }
                globalMediaRouter.mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED, routeInfo2, this.mReason);
                MediaRouteProvider.RouteController routeController = globalMediaRouter.mSelectedRouteController;
                if (routeController != null) {
                    routeController.onUnselect(this.mReason);
                    globalMediaRouter.mSelectedRouteController.onRelease();
                }
                if (!globalMediaRouter.mRouteControllerMap.isEmpty()) {
                    for (MediaRouteProvider.RouteController routeController2 : globalMediaRouter.mRouteControllerMap.values()) {
                        routeController2.onUnselect(this.mReason);
                        routeController2.onRelease();
                    }
                    globalMediaRouter.mRouteControllerMap.clear();
                }
                globalMediaRouter.mSelectedRouteController = null;
            }
        }

        public void cancel() {
            if (this.mFinished || this.mCanceled) {
                return;
            }
            this.mCanceled = true;
            MediaRouteProvider.RouteController routeController = this.mToRouteController;
            if (routeController != null) {
                routeController.onUnselect(0);
                this.mToRouteController.onRelease();
            }
        }

        @MainThread
        public void finishTransfer() {
            T.a aVar;
            MediaRouter.checkCallingThread();
            if (this.mFinished || this.mCanceled) {
                return;
            }
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null || globalMediaRouter.mTransferNotifier != this || ((aVar = this.mFuture) != null && aVar.isCancelled())) {
                cancel();
                return;
            }
            this.mFinished = true;
            globalMediaRouter.mTransferNotifier = null;
            unselectFromRouteAndNotify();
            selectToRouteAndNotify();
        }

        public void setFuture(T.a aVar) {
            GlobalMediaRouter globalMediaRouter = this.mRouter.get();
            if (globalMediaRouter == null || globalMediaRouter.mTransferNotifier != this) {
                Log.w(MediaRouter.TAG, "Router is released. Cancel transfer");
                cancel();
            } else {
                if (this.mFuture != null) {
                    throw new IllegalStateException("future is already set");
                }
                this.mFuture = aVar;
                d dVar = new d(this);
                final GlobalMediaRouter.CallbackHandler callbackHandler = globalMediaRouter.mCallbackHandler;
                Objects.requireNonNull(callbackHandler);
                aVar.addListener(dVar, new Executor() { // from class: androidx.mediarouter.media.e
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        callbackHandler.post(runnable);
                    }
                });
            }
        }
    }

    public static final class ProviderInfo {
        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        final MediaRouteProvider mProviderInstance;
        final List<RouteInfo> mRoutes = new ArrayList();

        public ProviderInfo(MediaRouteProvider mediaRouteProvider) {
            this.mProviderInstance = mediaRouteProvider;
            this.mMetadata = mediaRouteProvider.getMetadata();
        }

        public RouteInfo findRouteByDescriptorId(String str) {
            int size = this.mRoutes.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mRoutes.get(i2).mDescriptorId.equals(str)) {
                    return this.mRoutes.get(i2);
                }
            }
            return null;
        }

        public int findRouteIndexByDescriptorId(String str) {
            int size = this.mRoutes.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mRoutes.get(i2).mDescriptorId.equals(str)) {
                    return i2;
                }
            }
            return -1;
        }

        @NonNull
        public ComponentName getComponentName() {
            return this.mMetadata.getComponentName();
        }

        @NonNull
        public String getPackageName() {
            return this.mMetadata.getPackageName();
        }

        @NonNull
        @MainThread
        public MediaRouteProvider getProviderInstance() {
            MediaRouter.checkCallingThread();
            return this.mProviderInstance;
        }

        @NonNull
        @MainThread
        public List<RouteInfo> getRoutes() {
            MediaRouter.checkCallingThread();
            return Collections.unmodifiableList(this.mRoutes);
        }

        public boolean supportsDynamicGroup() {
            MediaRouteProviderDescriptor mediaRouteProviderDescriptor = this.mDescriptor;
            return mediaRouteProviderDescriptor != null && mediaRouteProviderDescriptor.supportsDynamicGroupRoute();
        }

        @NonNull
        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + getPackageName() + " }";
        }

        public boolean updateDescriptor(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            if (this.mDescriptor == mediaRouteProviderDescriptor) {
                return false;
            }
            this.mDescriptor = mediaRouteProviderDescriptor;
            return true;
        }
    }

    public static class RouteInfo {
        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int CONNECTION_STATE_CONNECTED = 2;
        public static final int CONNECTION_STATE_CONNECTING = 1;
        public static final int CONNECTION_STATE_DISCONNECTED = 0;

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public static final int DEVICE_TYPE_BLUETOOTH = 3;
        public static final int DEVICE_TYPE_SPEAKER = 2;
        public static final int DEVICE_TYPE_TV = 1;

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public static final int DEVICE_TYPE_UNKNOWN = 0;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
        static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";
        private boolean mCanDisconnect;
        private int mConnectionState;
        private String mDescription;
        MediaRouteDescriptor mDescriptor;
        final String mDescriptorId;
        private int mDeviceType;
        private Map<String, MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> mDynamicGroupDescriptors;
        boolean mEnabled;
        private Bundle mExtras;
        private Uri mIconUri;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private final ProviderInfo mProvider;
        private IntentSender mSettingsIntent;
        final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;
        private final ArrayList<IntentFilter> mControlFilters = new ArrayList<>();
        private int mPresentationDisplayId = -1;
        private List<RouteInfo> mMemberRoutes = new ArrayList();

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public static final class DynamicGroupState {
            final MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor mDynamicDescriptor;

            public DynamicGroupState(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor) {
                this.mDynamicDescriptor = dynamicRouteDescriptor;
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public int getSelectionState() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mDynamicDescriptor;
                if (dynamicRouteDescriptor != null) {
                    return dynamicRouteDescriptor.getSelectionState();
                }
                return 1;
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public boolean isGroupable() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mDynamicDescriptor;
                return dynamicRouteDescriptor != null && dynamicRouteDescriptor.isGroupable();
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public boolean isTransferable() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mDynamicDescriptor;
                return dynamicRouteDescriptor != null && dynamicRouteDescriptor.isTransferable();
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public boolean isUnselectable() {
                MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor = this.mDynamicDescriptor;
                return dynamicRouteDescriptor == null || dynamicRouteDescriptor.isUnselectable();
            }
        }

        public RouteInfo(ProviderInfo providerInfo, String str, String str2) {
            this.mProvider = providerInfo;
            this.mDescriptorId = str;
            this.mUniqueId = str2;
        }

        private boolean isSameControlFilter(IntentFilter intentFilter, IntentFilter intentFilter2) {
            int iCountActions;
            if (intentFilter == intentFilter2) {
                return true;
            }
            if (intentFilter == null || intentFilter2 == null || (iCountActions = intentFilter.countActions()) != intentFilter2.countActions()) {
                return false;
            }
            for (int i2 = 0; i2 < iCountActions; i2++) {
                if (!intentFilter.getAction(i2).equals(intentFilter2.getAction(i2))) {
                    return false;
                }
            }
            int iCountCategories = intentFilter.countCategories();
            if (iCountCategories != intentFilter2.countCategories()) {
                return false;
            }
            for (int i3 = 0; i3 < iCountCategories; i3++) {
                if (!intentFilter.getCategory(i3).equals(intentFilter2.getCategory(i3))) {
                    return false;
                }
            }
            return true;
        }

        private boolean isSameControlFilters(List<IntentFilter> list, List<IntentFilter> list2) {
            if (list == list2) {
                return true;
            }
            if (list == null || list2 == null) {
                return false;
            }
            ListIterator<IntentFilter> listIterator = list.listIterator();
            ListIterator<IntentFilter> listIterator2 = list2.listIterator();
            while (listIterator.hasNext() && listIterator2.hasNext()) {
                if (!isSameControlFilter(listIterator.next(), listIterator2.next())) {
                    return false;
                }
            }
            return (listIterator.hasNext() || listIterator2.hasNext()) ? false : true;
        }

        private static boolean isSystemMediaRouteProvider(RouteInfo routeInfo) {
            return TextUtils.equals(routeInfo.getProviderInstance().getMetadata().getPackageName(), "android");
        }

        public boolean canDisconnect() {
            return this.mCanDisconnect;
        }

        public RouteInfo findRouteByDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor) {
            return getProvider().findRouteByDescriptorId(dynamicRouteDescriptor.getRouteDescriptor().getId());
        }

        public int getConnectionState() {
            return this.mConnectionState;
        }

        @NonNull
        public List<IntentFilter> getControlFilters() {
            return this.mControlFilters;
        }

        @Nullable
        public String getDescription() {
            return this.mDescription;
        }

        public String getDescriptorId() {
            return this.mDescriptorId;
        }

        public int getDeviceType() {
            return this.mDeviceType;
        }

        @Nullable
        @MainThread
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public MediaRouteProvider.DynamicGroupRouteController getDynamicGroupController() {
            MediaRouter.checkCallingThread();
            MediaRouteProvider.RouteController routeController = MediaRouter.getGlobalRouter().mSelectedRouteController;
            if (routeController instanceof MediaRouteProvider.DynamicGroupRouteController) {
                return (MediaRouteProvider.DynamicGroupRouteController) routeController;
            }
            return null;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public DynamicGroupState getDynamicGroupState(@NonNull RouteInfo routeInfo) {
            if (routeInfo == null) {
                throw new NullPointerException("route must not be null");
            }
            Map<String, MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> map = this.mDynamicGroupDescriptors;
            if (map == null || !map.containsKey(routeInfo.mUniqueId)) {
                return null;
            }
            return new DynamicGroupState(this.mDynamicGroupDescriptors.get(routeInfo.mUniqueId));
        }

        @Nullable
        public Bundle getExtras() {
            return this.mExtras;
        }

        @Nullable
        public Uri getIconUri() {
            return this.mIconUri;
        }

        @NonNull
        public String getId() {
            return this.mUniqueId;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public List<RouteInfo> getMemberRoutes() {
            return Collections.unmodifiableList(this.mMemberRoutes);
        }

        @NonNull
        public String getName() {
            return this.mName;
        }

        public int getPlaybackStream() {
            return this.mPlaybackStream;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        @Nullable
        @MainThread
        public Display getPresentationDisplay() {
            MediaRouter.checkCallingThread();
            if (this.mPresentationDisplayId >= 0 && this.mPresentationDisplay == null) {
                this.mPresentationDisplay = MediaRouter.getGlobalRouter().getDisplay(this.mPresentationDisplayId);
            }
            return this.mPresentationDisplay;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public int getPresentationDisplayId() {
            return this.mPresentationDisplayId;
        }

        @NonNull
        public ProviderInfo getProvider() {
            return this.mProvider;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public MediaRouteProvider getProviderInstance() {
            return this.mProvider.getProviderInstance();
        }

        @Nullable
        public IntentSender getSettingsIntent() {
            return this.mSettingsIntent;
        }

        public int getVolume() {
            return this.mVolume;
        }

        public int getVolumeHandling() {
            if (!isGroup() || MediaRouter.isGroupVolumeUxEnabled()) {
                return this.mVolumeHandling;
            }
            return 0;
        }

        public int getVolumeMax() {
            return this.mVolumeMax;
        }

        @MainThread
        public boolean isBluetooth() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getBluetoothRoute() == this;
        }

        @Deprecated
        public boolean isConnecting() {
            return this.mConnectionState == 1;
        }

        @MainThread
        public boolean isDefault() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getDefaultRoute() == this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public boolean isDefaultOrBluetooth() {
            if (isDefault() || this.mDeviceType == 3) {
                return true;
            }
            return isSystemMediaRouteProvider(this) && supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
        }

        public boolean isDeviceSpeaker() {
            return isDefault() && TextUtils.equals(Resources.getSystem().getText(Resources.getSystem().getIdentifier("default_audio_route_name", TypedValues.Custom.S_STRING, "android")), this.mName);
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public boolean isGroup() {
            return getMemberRoutes().size() >= 1;
        }

        public boolean isSelectable() {
            return this.mDescriptor != null && this.mEnabled;
        }

        @MainThread
        public boolean isSelected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.getGlobalRouter().getSelectedRoute() == this;
        }

        @MainThread
        public boolean matchesSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            MediaRouter.checkCallingThread();
            return mediaRouteSelector.matchesControlFilters(this.mControlFilters);
        }

        public int maybeUpdateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            if (this.mDescriptor != mediaRouteDescriptor) {
                return updateDescriptor(mediaRouteDescriptor);
            }
            return 0;
        }

        @MainThread
        public void requestSetVolume(int i2) {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, i2)));
        }

        @MainThread
        public void requestUpdateVolume(int i2) {
            MediaRouter.checkCallingThread();
            if (i2 != 0) {
                MediaRouter.getGlobalRouter().requestUpdateVolume(this, i2);
            }
        }

        @MainThread
        public void select() {
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().selectRoute(this, 3);
        }

        @MainThread
        public void sendControlRequest(@NonNull Intent intent, @Nullable ControlRequestCallback controlRequestCallback) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            MediaRouter.getGlobalRouter().sendControlRequest(this, intent, controlRequestCallback);
        }

        @MainThread
        public boolean supportsControlAction(@NonNull String str, @NonNull String str2) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (str2 == null) {
                throw new IllegalArgumentException("action must not be null");
            }
            MediaRouter.checkCallingThread();
            int size = this.mControlFilters.size();
            for (int i2 = 0; i2 < size; i2++) {
                IntentFilter intentFilter = this.mControlFilters.get(i2);
                if (intentFilter.hasCategory(str) && intentFilter.hasAction(str2)) {
                    return true;
                }
            }
            return false;
        }

        @MainThread
        public boolean supportsControlCategory(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            MediaRouter.checkCallingThread();
            int size = this.mControlFilters.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mControlFilters.get(i2).hasCategory(str)) {
                    return true;
                }
            }
            return false;
        }

        @MainThread
        public boolean supportsControlRequest(@NonNull Intent intent) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            ContentResolver contentResolver = MediaRouter.getGlobalRouter().getContentResolver();
            int size = this.mControlFilters.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mControlFilters.get(i2).match(contentResolver, intent, true, MediaRouter.TAG) >= 0) {
                    return true;
                }
            }
            return false;
        }

        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MediaRouter.RouteInfo{ uniqueId=");
            sb.append(this.mUniqueId);
            sb.append(", name=");
            sb.append(this.mName);
            sb.append(", description=");
            sb.append(this.mDescription);
            sb.append(", iconUri=");
            sb.append(this.mIconUri);
            sb.append(", enabled=");
            sb.append(this.mEnabled);
            sb.append(", connectionState=");
            sb.append(this.mConnectionState);
            sb.append(", canDisconnect=");
            sb.append(this.mCanDisconnect);
            sb.append(", playbackType=");
            sb.append(this.mPlaybackType);
            sb.append(", playbackStream=");
            sb.append(this.mPlaybackStream);
            sb.append(", deviceType=");
            sb.append(this.mDeviceType);
            sb.append(", volumeHandling=");
            sb.append(this.mVolumeHandling);
            sb.append(", volume=");
            sb.append(this.mVolume);
            sb.append(", volumeMax=");
            sb.append(this.mVolumeMax);
            sb.append(", presentationDisplayId=");
            sb.append(this.mPresentationDisplayId);
            sb.append(", extras=");
            sb.append(this.mExtras);
            sb.append(", settingsIntent=");
            sb.append(this.mSettingsIntent);
            sb.append(", providerPackageName=");
            sb.append(this.mProvider.getPackageName());
            if (isGroup()) {
                sb.append(", members=[");
                int size = this.mMemberRoutes.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (i2 > 0) {
                        sb.append(", ");
                    }
                    if (this.mMemberRoutes.get(i2) != this) {
                        sb.append(this.mMemberRoutes.get(i2).getId());
                    }
                }
                sb.append(']');
            }
            sb.append(" }");
            return sb.toString();
        }

        public int updateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            int i2;
            this.mDescriptor = mediaRouteDescriptor;
            if (mediaRouteDescriptor == null) {
                return 0;
            }
            if (ObjectsCompat.equals(this.mName, mediaRouteDescriptor.getName())) {
                i2 = 0;
            } else {
                this.mName = mediaRouteDescriptor.getName();
                i2 = 1;
            }
            if (!ObjectsCompat.equals(this.mDescription, mediaRouteDescriptor.getDescription())) {
                this.mDescription = mediaRouteDescriptor.getDescription();
                i2 = 1;
            }
            if (!ObjectsCompat.equals(this.mIconUri, mediaRouteDescriptor.getIconUri())) {
                this.mIconUri = mediaRouteDescriptor.getIconUri();
                i2 = 1;
            }
            if (this.mEnabled != mediaRouteDescriptor.isEnabled()) {
                this.mEnabled = mediaRouteDescriptor.isEnabled();
                i2 = 1;
            }
            if (this.mConnectionState != mediaRouteDescriptor.getConnectionState()) {
                this.mConnectionState = mediaRouteDescriptor.getConnectionState();
                i2 = 1;
            }
            if (!isSameControlFilters(this.mControlFilters, mediaRouteDescriptor.getControlFilters())) {
                this.mControlFilters.clear();
                this.mControlFilters.addAll(mediaRouteDescriptor.getControlFilters());
                i2 = 1;
            }
            if (this.mPlaybackType != mediaRouteDescriptor.getPlaybackType()) {
                this.mPlaybackType = mediaRouteDescriptor.getPlaybackType();
                i2 = 1;
            }
            if (this.mPlaybackStream != mediaRouteDescriptor.getPlaybackStream()) {
                this.mPlaybackStream = mediaRouteDescriptor.getPlaybackStream();
                i2 = 1;
            }
            if (this.mDeviceType != mediaRouteDescriptor.getDeviceType()) {
                this.mDeviceType = mediaRouteDescriptor.getDeviceType();
                i2 = 1;
            }
            int i3 = 3;
            if (this.mVolumeHandling != mediaRouteDescriptor.getVolumeHandling()) {
                this.mVolumeHandling = mediaRouteDescriptor.getVolumeHandling();
                i2 = 3;
            }
            if (this.mVolume != mediaRouteDescriptor.getVolume()) {
                this.mVolume = mediaRouteDescriptor.getVolume();
                i2 = 3;
            }
            if (this.mVolumeMax != mediaRouteDescriptor.getVolumeMax()) {
                this.mVolumeMax = mediaRouteDescriptor.getVolumeMax();
            } else {
                i3 = i2;
            }
            if (this.mPresentationDisplayId != mediaRouteDescriptor.getPresentationDisplayId()) {
                this.mPresentationDisplayId = mediaRouteDescriptor.getPresentationDisplayId();
                this.mPresentationDisplay = null;
                i3 |= 5;
            }
            if (!ObjectsCompat.equals(this.mExtras, mediaRouteDescriptor.getExtras())) {
                this.mExtras = mediaRouteDescriptor.getExtras();
                i3 |= 1;
            }
            if (!ObjectsCompat.equals(this.mSettingsIntent, mediaRouteDescriptor.getSettingsActivity())) {
                this.mSettingsIntent = mediaRouteDescriptor.getSettingsActivity();
                i3 |= 1;
            }
            if (this.mCanDisconnect != mediaRouteDescriptor.canDisconnectAndKeepPlaying()) {
                this.mCanDisconnect = mediaRouteDescriptor.canDisconnectAndKeepPlaying();
                i3 |= 5;
            }
            List<String> groupMemberIds = mediaRouteDescriptor.getGroupMemberIds();
            ArrayList arrayList = new ArrayList();
            boolean z2 = groupMemberIds.size() != this.mMemberRoutes.size();
            if (!groupMemberIds.isEmpty()) {
                GlobalMediaRouter globalRouter = MediaRouter.getGlobalRouter();
                Iterator<String> it = groupMemberIds.iterator();
                while (it.hasNext()) {
                    RouteInfo route = globalRouter.getRoute(globalRouter.getUniqueId(getProvider(), it.next()));
                    if (route != null) {
                        arrayList.add(route);
                        if (!z2 && !this.mMemberRoutes.contains(route)) {
                            z2 = true;
                        }
                    }
                }
            }
            if (!z2) {
                return i3;
            }
            this.mMemberRoutes = arrayList;
            return i3 | 1;
        }

        public void updateDynamicDescriptors(Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            this.mMemberRoutes.clear();
            if (this.mDynamicGroupDescriptors == null) {
                this.mDynamicGroupDescriptors = new ArrayMap();
            }
            this.mDynamicGroupDescriptors.clear();
            for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor : collection) {
                RouteInfo routeInfoFindRouteByDynamicRouteDescriptor = findRouteByDynamicRouteDescriptor(dynamicRouteDescriptor);
                if (routeInfoFindRouteByDynamicRouteDescriptor != null) {
                    this.mDynamicGroupDescriptors.put(routeInfoFindRouteByDynamicRouteDescriptor.mUniqueId, dynamicRouteDescriptor);
                    if (dynamicRouteDescriptor.getSelectionState() == 2 || dynamicRouteDescriptor.getSelectionState() == 3) {
                        this.mMemberRoutes.add(routeInfoFindRouteByDynamicRouteDescriptor);
                    }
                }
            }
            MediaRouter.getGlobalRouter().mCallbackHandler.post(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, this);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRouter(Context context) {
        this.mContext = context;
    }

    public static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    private int findCallbackRecord(Callback callback) {
        int size = this.mCallbackRecords.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mCallbackRecords.get(i2).mCallback == callback) {
                return i2;
            }
        }
        return -1;
    }

    public static int getGlobalCallbackCount() {
        if (sGlobal == null) {
            return 0;
        }
        return getGlobalRouter().getCallbackCount();
    }

    public static GlobalMediaRouter getGlobalRouter() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return null;
        }
        globalMediaRouter.ensureInitialized();
        return sGlobal;
    }

    @NonNull
    @MainThread
    public static MediaRouter getInstance(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        checkCallingThread();
        if (sGlobal == null) {
            sGlobal = new GlobalMediaRouter(context.getApplicationContext());
        }
        return sGlobal.getRouter(context);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static boolean isGroupVolumeUxEnabled() {
        if (sGlobal == null) {
            return false;
        }
        return getGlobalRouter().isGroupVolumeUxEnabled();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static boolean isMediaTransferEnabled() {
        if (sGlobal == null) {
            return false;
        }
        return getGlobalRouter().isMediaTransferEnabled();
    }

    public static boolean isTransferToLocalEnabled() {
        GlobalMediaRouter globalRouter = getGlobalRouter();
        return globalRouter != null && globalRouter.isTransferToLocalEnabled();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void resetGlobalRouter() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return;
        }
        globalMediaRouter.reset();
        sGlobal = null;
    }

    @MainThread
    public void addCallback(@NonNull MediaRouteSelector mediaRouteSelector, @NonNull Callback callback) {
        addCallback(mediaRouteSelector, callback, 0);
    }

    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void addMemberToDynamicGroup(@NonNull RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new NullPointerException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().addMemberToDynamicGroup(routeInfo);
    }

    @MainThread
    public void addProvider(@NonNull MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        getGlobalRouter().addProvider(mediaRouteProvider);
    }

    @MainThread
    public void addRemoteControlClient(@NonNull Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        getGlobalRouter().addRemoteControlClient(obj);
    }

    @Nullable
    @MainThread
    public RouteInfo getBluetoothRoute() {
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        if (globalRouter == null) {
            return null;
        }
        return globalRouter.getBluetoothRoute();
    }

    @NonNull
    @MainThread
    public RouteInfo getDefaultRoute() {
        checkCallingThread();
        return getGlobalRouter().getDefaultRoute();
    }

    @Nullable
    public MediaSessionCompat.Token getMediaSessionToken() {
        GlobalMediaRouter globalMediaRouter = sGlobal;
        if (globalMediaRouter == null) {
            return null;
        }
        return globalMediaRouter.getMediaSessionToken();
    }

    @NonNull
    @MainThread
    public List<ProviderInfo> getProviders() {
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        return globalRouter == null ? Collections.emptyList() : globalRouter.getProviders();
    }

    @Nullable
    @MainThread
    public RouteInfo getRoute(String str) {
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        if (globalRouter == null) {
            return null;
        }
        return globalRouter.getRoute(str);
    }

    @Nullable
    @MainThread
    public MediaRouterParams getRouterParams() {
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        if (globalRouter == null) {
            return null;
        }
        return globalRouter.getRouterParams();
    }

    @NonNull
    @MainThread
    public List<RouteInfo> getRoutes() {
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        return globalRouter == null ? Collections.emptyList() : globalRouter.getRoutes();
    }

    @NonNull
    @MainThread
    public RouteInfo getSelectedRoute() {
        checkCallingThread();
        return getGlobalRouter().getSelectedRoute();
    }

    @MainThread
    public boolean isRouteAvailable(@NonNull MediaRouteSelector mediaRouteSelector, int i2) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        return getGlobalRouter().isRouteAvailable(mediaRouteSelector, i2);
    }

    @MainThread
    public void removeCallback(@NonNull Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        int iFindCallbackRecord = findCallbackRecord(callback);
        if (iFindCallbackRecord >= 0) {
            this.mCallbackRecords.remove(iFindCallbackRecord);
            getGlobalRouter().updateDiscoveryRequest();
        }
    }

    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void removeMemberFromDynamicGroup(@NonNull RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new NullPointerException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().removeMemberFromDynamicGroup(routeInfo);
    }

    @MainThread
    public void removeProvider(@NonNull MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        getGlobalRouter().removeProvider(mediaRouteProvider);
    }

    @MainThread
    public void removeRemoteControlClient(@NonNull Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        getGlobalRouter().removeRemoteControlClient(obj);
    }

    @MainThread
    public void selectRoute(@NonNull RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().selectRoute(routeInfo, 3);
    }

    @MainThread
    public void setMediaSession(@Nullable Object obj) {
        checkCallingThread();
        getGlobalRouter().setMediaSession(obj);
    }

    @MainThread
    public void setMediaSessionCompat(@Nullable MediaSessionCompat mediaSessionCompat) {
        checkCallingThread();
        getGlobalRouter().setMediaSessionCompat(mediaSessionCompat);
    }

    @MainThread
    public void setOnPrepareTransferListener(@Nullable OnPrepareTransferListener onPrepareTransferListener) {
        checkCallingThread();
        getGlobalRouter().mOnPrepareTransferListener = onPrepareTransferListener;
    }

    @MainThread
    public void setRouterParams(@Nullable MediaRouterParams mediaRouterParams) {
        checkCallingThread();
        getGlobalRouter().setRouterParams(mediaRouterParams);
    }

    @MainThread
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void transferToRoute(@NonNull RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new NullPointerException("route must not be null");
        }
        checkCallingThread();
        getGlobalRouter().transferToRoute(routeInfo);
    }

    @MainThread
    public void unselect(int i2) {
        if (i2 < 0 || i2 > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        RouteInfo routeInfoChooseFallbackRoute = globalRouter.chooseFallbackRoute();
        if (globalRouter.getSelectedRoute() != routeInfoChooseFallbackRoute) {
            globalRouter.selectRoute(routeInfoChooseFallbackRoute, i2);
        }
    }

    @NonNull
    @MainThread
    public RouteInfo updateSelectedRoute(@NonNull MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        GlobalMediaRouter globalRouter = getGlobalRouter();
        RouteInfo selectedRoute = globalRouter.getSelectedRoute();
        if (selectedRoute.isDefaultOrBluetooth() || selectedRoute.matchesSelector(mediaRouteSelector)) {
            return selectedRoute;
        }
        RouteInfo routeInfoChooseFallbackRoute = globalRouter.chooseFallbackRoute();
        globalRouter.selectRoute(routeInfoChooseFallbackRoute, 3);
        return routeInfoChooseFallbackRoute;
    }

    @MainThread
    public void addCallback(@NonNull MediaRouteSelector mediaRouteSelector, @NonNull Callback callback, int i2) {
        CallbackRecord callbackRecord;
        boolean z2;
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        int iFindCallbackRecord = findCallbackRecord(callback);
        if (iFindCallbackRecord < 0) {
            callbackRecord = new CallbackRecord(this, callback);
            this.mCallbackRecords.add(callbackRecord);
        } else {
            callbackRecord = this.mCallbackRecords.get(iFindCallbackRecord);
        }
        boolean z3 = true;
        if (i2 != callbackRecord.mFlags) {
            callbackRecord.mFlags = i2;
            z2 = true;
        } else {
            z2 = false;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        callbackRecord.mTimestamp = jElapsedRealtime;
        if (callbackRecord.mSelector.contains(mediaRouteSelector)) {
            z3 = z2;
        } else {
            callbackRecord.mSelector = new MediaRouteSelector.Builder(callbackRecord.mSelector).addSelector(mediaRouteSelector).build();
        }
        if (z3) {
            getGlobalRouter().updateDiscoveryRequest();
        }
    }
}
