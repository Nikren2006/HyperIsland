package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRoute2Info;
import android.media.MediaRoute2ProviderService;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.ArrayMap;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.mediarouter.media.MediaRouter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 30)
class MediaRoute2ProviderServiceAdapter extends MediaRoute2ProviderService {
    static final boolean DEBUG = false;

    @SuppressLint({"InlinedApi"})
    public static final String SERVICE_INTERFACE = "android.media.MediaRoute2ProviderService";
    private static final String TAG = "MR2ProviderService";
    private volatile MediaRouteProviderDescriptor mProviderDescriptor;
    final MediaRouteProviderService.MediaRouteProviderServiceImplApi30 mServiceImpl;
    private final Object mLock = new Object();

    @GuardedBy("mLock")
    final Map<String, SessionRecord> mSessionRecords = new ArrayMap();
    final SparseArray<String> mSessionIdMap = new SparseArray<>();

    public static class DynamicGroupRouteControllerProxy extends MediaRouteProvider.DynamicGroupRouteController {
        final MediaRouteProvider.RouteController mRouteController;
        private final String mRouteId;

        public DynamicGroupRouteControllerProxy(String str, MediaRouteProvider.RouteController routeController) {
            this.mRouteId = str;
            this.mRouteController = routeController;
        }

        public String getRouteId() {
            return this.mRouteId;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(@NonNull String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(@NonNull Intent intent, MediaRouter.ControlRequestCallback controlRequestCallback) {
            return this.mRouteController.onControlRequest(intent, controlRequestCallback);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRouteController.onRelease();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(@NonNull String str) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mRouteController.onSelect();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int i2) {
            this.mRouteController.onSetVolume(i2);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int i2) {
            this.mRouteController.onUnselect(i2);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(@Nullable List<String> list) {
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int i2) {
            this.mRouteController.onUpdateVolume(i2);
        }
    }

    public static class IncomingHandler extends Handler {
        private final MediaRoute2ProviderServiceAdapter mServiceAdapter;
        private final String mSessionId;

        public IncomingHandler(MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter, String str) {
            super(Looper.myLooper());
            this.mServiceAdapter = mediaRoute2ProviderServiceAdapter;
            this.mSessionId = str;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Messenger messenger = message.replyTo;
            int i2 = message.what;
            int i3 = message.arg1;
            Object obj = message.obj;
            Bundle data = message.getData();
            if (i2 == 7) {
                int i4 = data.getInt("volume", -1);
                String string = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
                if (i4 < 0 || string == null) {
                    return;
                }
                this.mServiceAdapter.setRouteVolume(string, i4);
                return;
            }
            if (i2 != 8) {
                if (i2 == 9 && (obj instanceof Intent)) {
                    this.mServiceAdapter.onControlRequest(messenger, i3, this.mSessionId, (Intent) obj);
                    return;
                }
                return;
            }
            int i5 = data.getInt("volume", 0);
            String string2 = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
            if (i5 == 0 || string2 == null) {
                return;
            }
            this.mServiceAdapter.updateRouteVolume(string2, i5);
        }
    }

    @RequiresApi(api = 30)
    public final class SessionRecord {
        static final int SESSION_FLAG_DYNAMIC = 4;
        static final int SESSION_FLAG_GROUP = 2;
        static final int SESSION_FLAG_MR2 = 1;
        private final WeakReference<MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord> mClientRecord;
        private final MediaRouteProvider.DynamicGroupRouteController mController;
        private final int mFlags;
        private boolean mIsCreated;
        private boolean mIsReleased;
        private final long mRequestId;
        String mRouteId;
        private final Map<String, MediaRouteProvider.RouteController> mRouteIdToControllerMap;
        String mSessionId;
        private RoutingSessionInfo mSessionInfo;

        public SessionRecord(MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter, MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, long j2, int i2) {
            this(dynamicGroupRouteController, j2, i2, null);
        }

        private MediaRouteProvider.RouteController getOrCreateRouteController(String str, String str2) {
            MediaRouteProvider.RouteController routeController = this.mRouteIdToControllerMap.get(str);
            if (routeController != null) {
                return routeController;
            }
            MediaRouteProvider.RouteController routeControllerOnCreateRouteController = str2 == null ? MediaRoute2ProviderServiceAdapter.this.getMediaRouteProvider().onCreateRouteController(str) : MediaRoute2ProviderServiceAdapter.this.getMediaRouteProvider().onCreateRouteController(str, str2);
            if (routeControllerOnCreateRouteController != null) {
                this.mRouteIdToControllerMap.put(str, routeControllerOnCreateRouteController);
            }
            return routeControllerOnCreateRouteController;
        }

        private void notifySessionCreated() {
            if (this.mIsCreated) {
                Log.w(MediaRoute2ProviderServiceAdapter.TAG, "notifySessionCreated: Routing session is already created.");
            } else {
                this.mIsCreated = true;
                MediaRoute2ProviderServiceAdapter.this.notifySessionCreated(this.mRequestId, this.mSessionInfo);
            }
        }

        private boolean releaseRouteControllerByRouteId(String str) {
            MediaRouteProvider.RouteController routeControllerRemove = this.mRouteIdToControllerMap.remove(str);
            if (routeControllerRemove == null) {
                return false;
            }
            routeControllerRemove.onUnselect(0);
            routeControllerRemove.onRelease();
            return true;
        }

        public MediaRouteProvider.RouteController findControllerByRouteId(String str) {
            MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord clientRecord = this.mClientRecord.get();
            return clientRecord != null ? clientRecord.findControllerByRouteId(str) : this.mRouteIdToControllerMap.get(str);
        }

        public int getFlags() {
            return this.mFlags;
        }

        public MediaRouteProvider.DynamicGroupRouteController getGroupController() {
            return this.mController;
        }

        public void release(boolean z2) {
            MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord clientRecord;
            if (this.mIsReleased) {
                return;
            }
            if ((this.mFlags & 3) == 3) {
                updateMemberRouteControllers(null, this.mSessionInfo, null);
            }
            if (z2) {
                this.mController.onUnselect(2);
                this.mController.onRelease();
                if ((this.mFlags & 1) == 0 && (clientRecord = this.mClientRecord.get()) != null) {
                    MediaRouteProvider.RouteController routeController = this.mController;
                    if (routeController instanceof DynamicGroupRouteControllerProxy) {
                        routeController = ((DynamicGroupRouteControllerProxy) routeController).mRouteController;
                    }
                    clientRecord.releaseControllerByProvider(routeController, this.mRouteId);
                }
            }
            this.mIsReleased = true;
            MediaRoute2ProviderServiceAdapter.this.notifySessionReleased(this.mSessionId);
        }

        public void setSessionInfo(@NonNull RoutingSessionInfo routingSessionInfo) {
            if (this.mSessionInfo != null) {
                Log.w(MediaRoute2ProviderServiceAdapter.TAG, "setSessionInfo: This shouldn't be called after sessionInfo is set");
                return;
            }
            Messenger messenger = new Messenger(new IncomingHandler(MediaRoute2ProviderServiceAdapter.this, this.mSessionId));
            RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(routingSessionInfo);
            Bundle bundle = new Bundle();
            bundle.putParcelable("androidx.mediarouter.media.KEY_MESSENGER", messenger);
            bundle.putString("androidx.mediarouter.media.KEY_SESSION_NAME", routingSessionInfo.getName() != null ? routingSessionInfo.getName().toString() : null);
            this.mSessionInfo = builder.setControlHints(bundle).build();
        }

        public void updateMemberRouteControllers(String str, RoutingSessionInfo routingSessionInfo, RoutingSessionInfo routingSessionInfo2) {
            List<String> listEmptyList = routingSessionInfo == null ? Collections.emptyList() : routingSessionInfo.getSelectedRoutes();
            List<String> listEmptyList2 = routingSessionInfo2 == null ? Collections.emptyList() : routingSessionInfo2.getSelectedRoutes();
            for (String str2 : listEmptyList2) {
                if (findControllerByRouteId(str2) == null) {
                    getOrCreateRouteController(str2, str).onSelect();
                }
            }
            for (String str3 : listEmptyList) {
                if (!listEmptyList2.contains(str3)) {
                    releaseRouteControllerByRouteId(str3);
                }
            }
        }

        public void updateSessionInfo(@Nullable MediaRouteDescriptor mediaRouteDescriptor, @Nullable Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
            RoutingSessionInfo routingSessionInfo = this.mSessionInfo;
            if (routingSessionInfo == null) {
                Log.w(MediaRoute2ProviderServiceAdapter.TAG, "updateSessionInfo: mSessionInfo is null. This shouldn't happen.");
                return;
            }
            if (mediaRouteDescriptor != null && !mediaRouteDescriptor.isEnabled()) {
                MediaRoute2ProviderServiceAdapter.this.onReleaseSession(0L, this.mSessionId);
                return;
            }
            RoutingSessionInfo.Builder builder = new RoutingSessionInfo.Builder(routingSessionInfo);
            if (mediaRouteDescriptor != null) {
                this.mRouteId = mediaRouteDescriptor.getId();
                builder.setName(mediaRouteDescriptor.getName()).setVolume(mediaRouteDescriptor.getVolume()).setVolumeMax(mediaRouteDescriptor.getVolumeMax()).setVolumeHandling(mediaRouteDescriptor.getVolumeHandling());
                builder.clearSelectedRoutes();
                if (mediaRouteDescriptor.getGroupMemberIds().isEmpty()) {
                    builder.addSelectedRoute(this.mRouteId);
                } else {
                    Iterator<String> it = mediaRouteDescriptor.getGroupMemberIds().iterator();
                    while (it.hasNext()) {
                        builder.addSelectedRoute(it.next());
                    }
                }
                Bundle controlHints = routingSessionInfo.getControlHints();
                if (controlHints == null) {
                    Log.w(MediaRoute2ProviderServiceAdapter.TAG, "updateSessionInfo: controlHints is null. This shouldn't happen.");
                    controlHints = new Bundle();
                }
                controlHints.putString("androidx.mediarouter.media.KEY_SESSION_NAME", mediaRouteDescriptor.getName());
                controlHints.putBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE", mediaRouteDescriptor.asBundle());
                builder.setControlHints(controlHints);
            }
            this.mSessionInfo = builder.build();
            if (collection != null && !collection.isEmpty()) {
                builder.clearSelectedRoutes();
                builder.clearSelectableRoutes();
                builder.clearDeselectableRoutes();
                builder.clearTransferableRoutes();
                boolean z2 = false;
                for (MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor dynamicRouteDescriptor : collection) {
                    String id = dynamicRouteDescriptor.getRouteDescriptor().getId();
                    int i2 = dynamicRouteDescriptor.mSelectionState;
                    if (i2 == 2 || i2 == 3) {
                        builder.addSelectedRoute(id);
                        z2 = true;
                    }
                    if (dynamicRouteDescriptor.isGroupable()) {
                        builder.addSelectableRoute(id);
                    }
                    if (dynamicRouteDescriptor.isUnselectable()) {
                        builder.addDeselectableRoute(id);
                    }
                    if (dynamicRouteDescriptor.isTransferable()) {
                        builder.addTransferableRoute(id);
                    }
                }
                if (z2) {
                    this.mSessionInfo = builder.build();
                }
            }
            if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                Log.d(MediaRoute2ProviderServiceAdapter.TAG, "updateSessionInfo: groupRoute=" + mediaRouteDescriptor + ", sessionInfo=" + this.mSessionInfo);
            }
            if ((this.mFlags & 5) == 5 && mediaRouteDescriptor != null) {
                updateMemberRouteControllers(mediaRouteDescriptor.getId(), routingSessionInfo, this.mSessionInfo);
            }
            if (this.mIsCreated) {
                MediaRoute2ProviderServiceAdapter.this.notifySessionUpdated(this.mSessionInfo);
            } else {
                notifySessionCreated();
            }
        }

        public SessionRecord(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, long j2, int i2, MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord clientRecord) {
            this.mRouteIdToControllerMap = new ArrayMap();
            this.mIsCreated = false;
            this.mController = dynamicGroupRouteController;
            this.mRequestId = j2;
            this.mFlags = i2;
            this.mClientRecord = new WeakReference<>(clientRecord);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRoute2ProviderServiceAdapter(MediaRouteProviderService.MediaRouteProviderServiceImplApi30 mediaRouteProviderServiceImplApi30) {
        this.mServiceImpl = mediaRouteProviderServiceImplApi30;
    }

    private String assignSessionId(SessionRecord sessionRecord) {
        String string;
        synchronized (this.mLock) {
            do {
                string = UUID.randomUUID().toString();
            } while (this.mSessionRecords.containsKey(string));
            sessionRecord.mSessionId = string;
            this.mSessionRecords.put(string, sessionRecord);
        }
        return string;
    }

    private MediaRouteProvider.RouteController findControllerByRouteId(String str) {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mSessionRecords.values());
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MediaRouteProvider.RouteController routeControllerFindControllerByRouteId = ((SessionRecord) it.next()).findControllerByRouteId(str);
            if (routeControllerFindControllerByRouteId != null) {
                return routeControllerFindControllerByRouteId;
            }
        }
        return null;
    }

    private MediaRouteProvider.DynamicGroupRouteController findControllerBySessionId(String str) {
        MediaRouteProvider.DynamicGroupRouteController groupController;
        synchronized (this.mLock) {
            SessionRecord sessionRecord = this.mSessionRecords.get(str);
            groupController = sessionRecord == null ? null : sessionRecord.getGroupController();
        }
        return groupController;
    }

    private SessionRecord findSessionRecordByController(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController) {
        synchronized (this.mLock) {
            try {
                Iterator<Map.Entry<String, SessionRecord>> it = this.mSessionRecords.entrySet().iterator();
                while (it.hasNext()) {
                    SessionRecord value = it.next().getValue();
                    if (value.getGroupController() == dynamicGroupRouteController) {
                        return value;
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private MediaRouteDescriptor getRouteDescriptor(String str, String str2) {
        if (getMediaRouteProvider() == null || this.mProviderDescriptor == null) {
            Log.w(TAG, str2 + ": no provider info");
            return null;
        }
        for (MediaRouteDescriptor mediaRouteDescriptor : this.mProviderDescriptor.getRoutes()) {
            if (TextUtils.equals(mediaRouteDescriptor.getId(), str)) {
                return mediaRouteDescriptor;
            }
        }
        Log.w(TAG, str2 + ": Couldn't find a route : " + str);
        return null;
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public MediaRouteProvider getMediaRouteProvider() {
        MediaRouteProviderService service = this.mServiceImpl.getService();
        if (service == null) {
            return null;
        }
        return service.getMediaRouteProvider();
    }

    public void notifyRouteControllerAdded(MediaRouteProviderService.MediaRouteProviderServiceImplApi30.ClientRecord clientRecord, MediaRouteProvider.RouteController routeController, int i2, String str, String str2) {
        int i3;
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerProxy;
        MediaRouteDescriptor routeDescriptor = getRouteDescriptor(str2, "notifyRouteControllerAdded");
        if (routeDescriptor == null) {
            return;
        }
        if (routeController instanceof MediaRouteProvider.DynamicGroupRouteController) {
            dynamicGroupRouteControllerProxy = (MediaRouteProvider.DynamicGroupRouteController) routeController;
            i3 = 6;
        } else {
            i3 = !routeDescriptor.getGroupMemberIds().isEmpty() ? 2 : 0;
            dynamicGroupRouteControllerProxy = new DynamicGroupRouteControllerProxy(str2, routeController);
        }
        SessionRecord sessionRecord = new SessionRecord(dynamicGroupRouteControllerProxy, 0L, i3, clientRecord);
        sessionRecord.mRouteId = str2;
        String strAssignSessionId = assignSessionId(sessionRecord);
        this.mSessionIdMap.put(i2, strAssignSessionId);
        RoutingSessionInfo.Builder volumeMax = new RoutingSessionInfo.Builder(strAssignSessionId, str).setName(routeDescriptor.getName()).setVolumeHandling(routeDescriptor.getVolumeHandling()).setVolume(routeDescriptor.getVolume()).setVolumeMax(routeDescriptor.getVolumeMax());
        if (routeDescriptor.getGroupMemberIds().isEmpty()) {
            volumeMax.addSelectedRoute(str2);
        } else {
            Iterator<String> it = routeDescriptor.getGroupMemberIds().iterator();
            while (it.hasNext()) {
                volumeMax.addSelectedRoute(it.next());
            }
        }
        sessionRecord.setSessionInfo(volumeMax.build());
    }

    public void notifyRouteControllerRemoved(int i2) {
        SessionRecord sessionRecordRemove;
        String str = this.mSessionIdMap.get(i2);
        if (str == null) {
            return;
        }
        this.mSessionIdMap.remove(i2);
        synchronized (this.mLock) {
            sessionRecordRemove = this.mSessionRecords.remove(str);
        }
        if (sessionRecordRemove != null) {
            sessionRecordRemove.release(false);
        }
    }

    public void onControlRequest(final Messenger messenger, final int i2, final String str, final Intent intent) {
        if (getSessionInfo(str) == null) {
            Log.w(TAG, "onCustomCommand: Couldn't find a session");
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerFindControllerBySessionId = findControllerBySessionId(str);
        if (dynamicGroupRouteControllerFindControllerBySessionId != null) {
            dynamicGroupRouteControllerFindControllerBySessionId.onControlRequest(intent, new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.MediaRoute2ProviderServiceAdapter.1
                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onError(String str2, Bundle bundle) {
                    if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                        Log.d(MediaRoute2ProviderServiceAdapter.TAG, "Route control request failed, sessionId=" + str + ", intent=" + intent + ", error=" + str2 + ", data=" + bundle);
                    }
                    if (str2 == null) {
                        sendReply(messenger, 4, i2, 0, bundle, null);
                        return;
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, str2);
                    sendReply(messenger, 4, i2, 0, bundle, bundle2);
                }

                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onResult(Bundle bundle) {
                    if (MediaRoute2ProviderServiceAdapter.DEBUG) {
                        Log.d(MediaRoute2ProviderServiceAdapter.TAG, "Route control request succeeded, sessionId=" + str + ", intent=" + intent + ", data=" + bundle);
                    }
                    sendReply(messenger, 3, i2, 0, bundle, null);
                }

                public void sendReply(Messenger messenger2, int i3, int i4, int i5, Object obj, Bundle bundle) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = i3;
                    messageObtain.arg1 = i4;
                    messageObtain.arg2 = i5;
                    messageObtain.obj = obj;
                    messageObtain.setData(bundle);
                    try {
                        messenger2.send(messageObtain);
                    } catch (DeadObjectException unused) {
                    } catch (RemoteException e2) {
                        Log.e(MediaRoute2ProviderServiceAdapter.TAG, "Could not send message to the client.", e2);
                    }
                }
            });
        } else {
            Log.w(TAG, "onControlRequest: Couldn't find a controller");
            notifyRequestFailed(i2, 3);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onCreateSession(long j2, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        int i2;
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerProxy;
        MediaRouteProvider mediaRouteProvider = getMediaRouteProvider();
        MediaRouteDescriptor routeDescriptor = getRouteDescriptor(str2, "onCreateSession");
        if (routeDescriptor == null) {
            notifyRequestFailed(j2, 3);
            return;
        }
        if (this.mProviderDescriptor.supportsDynamicGroupRoute()) {
            dynamicGroupRouteControllerProxy = mediaRouteProvider.onCreateDynamicGroupRouteController(str2);
            if (dynamicGroupRouteControllerProxy == null) {
                Log.w(TAG, "onCreateSession: Couldn't create a dynamic controller");
                notifyRequestFailed(j2, 1);
                return;
            }
            i2 = 7;
        } else {
            MediaRouteProvider.RouteController routeControllerOnCreateRouteController = mediaRouteProvider.onCreateRouteController(str2);
            if (routeControllerOnCreateRouteController == null) {
                Log.w(TAG, "onCreateSession: Couldn't create a controller");
                notifyRequestFailed(j2, 1);
                return;
            } else {
                i2 = routeDescriptor.getGroupMemberIds().isEmpty() ? 1 : 3;
                dynamicGroupRouteControllerProxy = new DynamicGroupRouteControllerProxy(str2, routeControllerOnCreateRouteController);
            }
        }
        dynamicGroupRouteControllerProxy.onSelect();
        SessionRecord sessionRecord = new SessionRecord(this, dynamicGroupRouteControllerProxy, j2, i2);
        RoutingSessionInfo.Builder volumeMax = new RoutingSessionInfo.Builder(assignSessionId(sessionRecord), str).setName(routeDescriptor.getName()).setVolumeHandling(routeDescriptor.getVolumeHandling()).setVolume(routeDescriptor.getVolume()).setVolumeMax(routeDescriptor.getVolumeMax());
        if (routeDescriptor.getGroupMemberIds().isEmpty()) {
            volumeMax.addSelectedRoute(str2);
        } else {
            Iterator<String> it = routeDescriptor.getGroupMemberIds().iterator();
            while (it.hasNext()) {
                volumeMax.addSelectedRoute(it.next());
            }
        }
        RoutingSessionInfo routingSessionInfoBuild = volumeMax.build();
        sessionRecord.setSessionInfo(routingSessionInfoBuild);
        if ((i2 & 6) == 2) {
            sessionRecord.updateMemberRouteControllers(str2, null, routingSessionInfoBuild);
        }
        this.mServiceImpl.setDynamicRoutesChangedListener(dynamicGroupRouteControllerProxy);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDeselectRoute(long j2, @NonNull String str, @NonNull String str2) {
        if (getSessionInfo(str) == null) {
            Log.w(TAG, "onDeselectRoute: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        } else {
            if (getRouteDescriptor(str2, "onDeselectRoute") == null) {
                notifyRequestFailed(j2, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerFindControllerBySessionId = findControllerBySessionId(str);
            if (dynamicGroupRouteControllerFindControllerBySessionId != null) {
                dynamicGroupRouteControllerFindControllerBySessionId.onRemoveMemberRoute(str2);
            } else {
                Log.w(TAG, "onDeselectRoute: Couldn't find a controller");
                notifyRequestFailed(j2, 3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onDiscoveryPreferenceChanged(@NonNull RouteDiscoveryPreference routeDiscoveryPreference) {
        this.mServiceImpl.setBaseDiscoveryRequest(MediaRouter2Utils.toMediaRouteDiscoveryRequest(routeDiscoveryPreference));
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onReleaseSession(long j2, @NonNull String str) {
        SessionRecord sessionRecordRemove;
        if (getSessionInfo(str) == null) {
            return;
        }
        synchronized (this.mLock) {
            sessionRecordRemove = this.mSessionRecords.remove(str);
        }
        if (sessionRecordRemove != null) {
            sessionRecordRemove.release(true);
        } else {
            Log.w(TAG, "onReleaseSession: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSelectRoute(long j2, @NonNull String str, @NonNull String str2) {
        if (getSessionInfo(str) == null) {
            Log.w(TAG, "onSelectRoute: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        } else {
            if (getRouteDescriptor(str2, "onSelectRoute") == null) {
                notifyRequestFailed(j2, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerFindControllerBySessionId = findControllerBySessionId(str);
            if (dynamicGroupRouteControllerFindControllerBySessionId != null) {
                dynamicGroupRouteControllerFindControllerBySessionId.onAddMemberRoute(str2);
            } else {
                Log.w(TAG, "onSelectRoute: Couldn't find a controller");
                notifyRequestFailed(j2, 3);
            }
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetRouteVolume(long j2, @NonNull String str, int i2) {
        MediaRouteProvider.RouteController routeControllerFindControllerByRouteId = findControllerByRouteId(str);
        if (routeControllerFindControllerByRouteId != null) {
            routeControllerFindControllerByRouteId.onSetVolume(i2);
            return;
        }
        Log.w(TAG, "onSetRouteVolume: Couldn't find a controller for routeId=" + str);
        notifyRequestFailed(j2, 3);
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onSetSessionVolume(long j2, @NonNull String str, int i2) {
        if (getSessionInfo(str) == null) {
            Log.w(TAG, "onSetSessionVolume: Couldn't find a session");
            notifyRequestFailed(j2, 4);
            return;
        }
        MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerFindControllerBySessionId = findControllerBySessionId(str);
        if (dynamicGroupRouteControllerFindControllerBySessionId != null) {
            dynamicGroupRouteControllerFindControllerBySessionId.onSetVolume(i2);
        } else {
            Log.w(TAG, "onSetSessionVolume: Couldn't find a controller");
            notifyRequestFailed(j2, 3);
        }
    }

    @Override // android.media.MediaRoute2ProviderService
    public void onTransferToRoute(long j2, @NonNull String str, @NonNull String str2) {
        if (getSessionInfo(str) == null) {
            Log.w(TAG, "onTransferToRoute: Couldn't find a session");
            notifyRequestFailed(j2, 4);
        } else {
            if (getRouteDescriptor(str2, "onTransferToRoute") == null) {
                notifyRequestFailed(j2, 3);
                return;
            }
            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerFindControllerBySessionId = findControllerBySessionId(str);
            if (dynamicGroupRouteControllerFindControllerBySessionId != null) {
                dynamicGroupRouteControllerFindControllerBySessionId.onUpdateMemberRoutes(Collections.singletonList(str2));
            } else {
                Log.w(TAG, "onTransferToRoute: Couldn't find a controller");
                notifyRequestFailed(j2, 3);
            }
        }
    }

    public void setDynamicRouteDescriptor(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
        SessionRecord sessionRecordFindSessionRecordByController = findSessionRecordByController(dynamicGroupRouteController);
        if (sessionRecordFindSessionRecordByController == null) {
            Log.w(TAG, "setDynamicRouteDescriptor: Ignoring unknown controller");
        } else {
            sessionRecordFindSessionRecordByController.updateSessionInfo(mediaRouteDescriptor, collection);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setProviderDescriptor(@Nullable MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        this.mProviderDescriptor = mediaRouteProviderDescriptor;
        List<MediaRouteDescriptor> listEmptyList = mediaRouteProviderDescriptor == null ? Collections.emptyList() : mediaRouteProviderDescriptor.getRoutes();
        Map<String, MediaRouteDescriptor> arrayMap = new ArrayMap<>();
        for (MediaRouteDescriptor mediaRouteDescriptor : listEmptyList) {
            if (mediaRouteDescriptor != null) {
                arrayMap.put(mediaRouteDescriptor.getId(), mediaRouteDescriptor);
            }
        }
        updateStaticSessions(arrayMap);
        ArrayList arrayList = new ArrayList();
        Iterator it = arrayMap.values().iterator();
        while (it.hasNext()) {
            MediaRoute2Info fwkMediaRoute2Info = MediaRouter2Utils.toFwkMediaRoute2Info((MediaRouteDescriptor) it.next());
            if (fwkMediaRoute2Info != null) {
                arrayList.add(fwkMediaRoute2Info);
            }
        }
        notifyRoutes(arrayList);
    }

    public void setRouteVolume(@NonNull String str, int i2) {
        MediaRouteProvider.RouteController routeControllerFindControllerByRouteId = findControllerByRouteId(str);
        if (routeControllerFindControllerByRouteId != null) {
            routeControllerFindControllerByRouteId.onSetVolume(i2);
            return;
        }
        Log.w(TAG, "setRouteVolume: Couldn't find a controller for routeId=" + str);
    }

    public void updateRouteVolume(@NonNull String str, int i2) {
        MediaRouteProvider.RouteController routeControllerFindControllerByRouteId = findControllerByRouteId(str);
        if (routeControllerFindControllerByRouteId != null) {
            routeControllerFindControllerByRouteId.onUpdateVolume(i2);
            return;
        }
        Log.w(TAG, "updateRouteVolume: Couldn't find a controller for routeId=" + str);
    }

    public void updateStaticSessions(Map<String, MediaRouteDescriptor> map) {
        ArrayList<SessionRecord> arrayList = new ArrayList();
        synchronized (this.mLock) {
            try {
                for (SessionRecord sessionRecord : this.mSessionRecords.values()) {
                    if ((sessionRecord.getFlags() & 4) == 0) {
                        arrayList.add(sessionRecord);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (SessionRecord sessionRecord2 : arrayList) {
            DynamicGroupRouteControllerProxy dynamicGroupRouteControllerProxy = (DynamicGroupRouteControllerProxy) sessionRecord2.getGroupController();
            if (map.containsKey(dynamicGroupRouteControllerProxy.getRouteId())) {
                sessionRecord2.updateSessionInfo(map.get(dynamicGroupRouteControllerProxy.getRouteId()), null);
            }
        }
    }
}
