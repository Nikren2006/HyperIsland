package androidx.mediarouter.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaRouteProviderService extends Service {
    static final boolean DEBUG = false;
    static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    static final String TAG = "MediaRouteProviderSrv";
    final MediaRouteProviderServiceImpl mImpl;
    final PrivateHandler mPrivateHandler;
    MediaRouteProvider mProvider;
    private final MediaRouteProvider.Callback mProviderCallback;
    private final ReceiveHandler mReceiveHandler;
    final Messenger mReceiveMessenger;

    public interface MediaRouteProviderServiceImpl {
        void attachBaseContext(Context context);

        MediaRouteProvider.Callback getProviderCallback();

        boolean onAddMemberRoute(Messenger messenger, int i2, int i3, String str);

        IBinder onBind(Intent intent);

        void onBinderDied(Messenger messenger);

        boolean onCreateDynamicGroupRouteController(Messenger messenger, int i2, int i3, String str);

        boolean onCreateRouteController(Messenger messenger, int i2, int i3, String str, String str2);

        boolean onRegisterClient(Messenger messenger, int i2, int i3, String str);

        boolean onReleaseRouteController(Messenger messenger, int i2, int i3);

        boolean onRemoveMemberRoute(Messenger messenger, int i2, int i3, String str);

        boolean onRouteControlRequest(Messenger messenger, int i2, int i3, Intent intent);

        boolean onSelectRoute(Messenger messenger, int i2, int i3);

        boolean onSetDiscoveryRequest(Messenger messenger, int i2, MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest);

        boolean onSetRouteVolume(Messenger messenger, int i2, int i3, int i4);

        boolean onUnregisterClient(Messenger messenger, int i2);

        boolean onUnselectRoute(Messenger messenger, int i2, int i3, int i4);

        boolean onUpdateMemberRoutes(Messenger messenger, int i2, int i3, List<String> list);

        boolean onUpdateRouteVolume(Messenger messenger, int i2, int i3, int i4);
    }

    @RequiresApi(api = 30)
    public static class MediaRouteProviderServiceImplApi30 extends MediaRouteProviderServiceImplBase {
        final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener;
        MediaRoute2ProviderServiceAdapter mMR2ProviderServiceAdapter;

        public class ClientRecord extends MediaRouteProviderServiceImplBase.ClientRecord {
            private static final long DISABLE_ROUTE_FOR_RELEASED_CONTROLLER_TIMEOUT_MS = 5000;
            private final Handler mClientHandler;
            private final Map<String, Integer> mReleasedControllerIds;
            private final Map<String, MediaRouteProvider.RouteController> mRouteIdToControllerMap;

            public ClientRecord(Messenger messenger, int i2, String str) {
                super(messenger, i2, str);
                this.mRouteIdToControllerMap = new ArrayMap();
                this.mClientHandler = new Handler(Looper.getMainLooper());
                if (i2 < 4) {
                    this.mReleasedControllerIds = new ArrayMap();
                } else {
                    this.mReleasedControllerIds = Collections.emptyMap();
                }
            }

            private void disableRouteForReleasedRouteController(final String str, int i2) {
                this.mReleasedControllerIds.put(str, Integer.valueOf(i2));
                this.mClientHandler.postDelayed(new Runnable() { // from class: androidx.mediarouter.media.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1265a.lambda$disableRouteForReleasedRouteController$0(str);
                    }
                }, DISABLE_ROUTE_FOR_RELEASED_CONTROLLER_TIMEOUT_MS);
                sendDescriptor();
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: enableRouteForReleasedRouteController, reason: merged with bridge method [inline-methods] */
            public void lambda$disableRouteForReleasedRouteController$0(String str) {
                if (this.mReleasedControllerIds.remove(str) == null) {
                    return;
                }
                sendDescriptor();
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public Bundle createDescriptorBundle(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                if (this.mReleasedControllerIds.isEmpty()) {
                    return super.createDescriptorBundle(mediaRouteProviderDescriptor);
                }
                ArrayList arrayList = new ArrayList();
                for (MediaRouteDescriptor mediaRouteDescriptor : mediaRouteProviderDescriptor.getRoutes()) {
                    if (this.mReleasedControllerIds.containsKey(mediaRouteDescriptor.getId())) {
                        arrayList.add(new MediaRouteDescriptor.Builder(mediaRouteDescriptor).setEnabled(false).build());
                    } else {
                        arrayList.add(mediaRouteDescriptor);
                    }
                }
                return super.createDescriptorBundle(new MediaRouteProviderDescriptor.Builder(mediaRouteProviderDescriptor).setRoutes(arrayList).build());
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public Bundle createDynamicGroupRouteController(String str, int i2) {
                Bundle bundleCreateDynamicGroupRouteController = super.createDynamicGroupRouteController(str, i2);
                if (bundleCreateDynamicGroupRouteController != null && this.mPackageName != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.notifyRouteControllerAdded(this, this.mControllers.get(i2), i2, this.mPackageName, str);
                }
                return bundleCreateDynamicGroupRouteController;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean createRouteController(String str, String str2, int i2) {
                MediaRouteProvider.RouteController routeController = this.mRouteIdToControllerMap.get(str);
                if (routeController != null) {
                    this.mControllers.put(i2, routeController);
                    return true;
                }
                boolean zCreateRouteController = super.createRouteController(str, str2, i2);
                if (str2 == null && zCreateRouteController && this.mPackageName != null) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.notifyRouteControllerAdded(this, this.mControllers.get(i2), i2, this.mPackageName, str);
                }
                if (zCreateRouteController) {
                    this.mRouteIdToControllerMap.put(str, this.mControllers.get(i2));
                }
                return zCreateRouteController;
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public void dispose() {
                int size = this.mControllers.size();
                for (int i2 = 0; i2 < size; i2++) {
                    MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.notifyRouteControllerRemoved(this.mControllers.keyAt(i2));
                }
                this.mRouteIdToControllerMap.clear();
                super.dispose();
            }

            public MediaRouteProvider.RouteController findControllerByRouteId(String str) {
                return this.mRouteIdToControllerMap.get(str);
            }

            public int findControllerIdByController(MediaRouteProvider.RouteController routeController) {
                int iIndexOfValue = this.mControllers.indexOfValue(routeController);
                if (iIndexOfValue < 0) {
                    return -1;
                }
                return this.mControllers.keyAt(iIndexOfValue);
            }

            public void releaseControllerByProvider(MediaRouteProvider.RouteController routeController, String str) {
                int iFindControllerIdByController = findControllerIdByController(routeController);
                releaseRouteController(iFindControllerIdByController);
                if (this.mVersion < 4) {
                    disableRouteForReleasedRouteController(str, iFindControllerIdByController);
                    return;
                }
                if (iFindControllerIdByController >= 0) {
                    MediaRouteProviderService.sendMessage(this.mMessenger, 8, 0, iFindControllerIdByController, null, null);
                    return;
                }
                Log.w(MediaRouteProviderService.TAG, "releaseControllerByProvider: Can't find the controller. route ID=" + str);
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public boolean releaseRouteController(int i2) {
                MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter.notifyRouteControllerRemoved(i2);
                MediaRouteProvider.RouteController routeController = this.mControllers.get(i2);
                if (routeController != null) {
                    Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> it = this.mRouteIdToControllerMap.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<String, MediaRouteProvider.RouteController> next = it.next();
                        if (next.getValue() == routeController) {
                            this.mRouteIdToControllerMap.remove(next.getKey());
                            break;
                        }
                    }
                }
                Iterator<Map.Entry<String, Integer>> it2 = this.mReleasedControllerIds.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Map.Entry<String, Integer> next2 = it2.next();
                    if (next2.getValue().intValue() == i2) {
                        lambda$disableRouteForReleasedRouteController$0(next2.getKey());
                        break;
                    }
                }
                return super.releaseRouteController(i2);
            }

            public void sendDescriptor() {
                MediaRouteProviderDescriptor descriptor = MediaRouteProviderServiceImplApi30.this.getService().getMediaRouteProvider().getDescriptor();
                if (descriptor != null) {
                    MediaRouteProviderService.sendMessage(this.mMessenger, 5, 0, 0, createDescriptorBundle(descriptor), null);
                }
            }

            @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord
            public void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                super.sendDynamicRouteDescriptors(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter = MediaRouteProviderServiceImplApi30.this.mMR2ProviderServiceAdapter;
                if (mediaRoute2ProviderServiceAdapter != null) {
                    mediaRoute2ProviderServiceAdapter.setDynamicRouteDescriptor(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            }
        }

        public MediaRouteProviderServiceImplApi30(MediaRouteProviderService mediaRouteProviderService) {
            super(mediaRouteProviderService);
            this.mDynamicRoutesChangedListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.b
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public final void onRoutesChanged(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection collection) {
                    this.f1264a.lambda$new$0(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection collection) {
            this.mMR2ProviderServiceAdapter.setDynamicRouteDescriptor(dynamicGroupRouteController, mediaRouteDescriptor, collection);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context context) {
            MediaRoute2ProviderServiceAdapter mediaRoute2ProviderServiceAdapter = this.mMR2ProviderServiceAdapter;
            if (mediaRoute2ProviderServiceAdapter != null) {
                mediaRoute2ProviderServiceAdapter.attachBaseContext(context);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        public MediaRouteProviderServiceImplBase.ClientRecord createClientRecord(Messenger messenger, int i2, String str) {
            return new ClientRecord(messenger, i2, str);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase, androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent intent) {
            this.mService.ensureProvider();
            if (this.mMR2ProviderServiceAdapter == null) {
                this.mMR2ProviderServiceAdapter = new MediaRoute2ProviderServiceAdapter(this);
                if (this.mService.getBaseContext() != null) {
                    this.mMR2ProviderServiceAdapter.attachBaseContext(this.mService);
                }
            }
            IBinder iBinderOnBind = super.onBind(intent);
            return iBinderOnBind != null ? iBinderOnBind : this.mMR2ProviderServiceAdapter.onBind(intent);
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase
        public void sendDescriptorChanged(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            super.sendDescriptorChanged(mediaRouteProviderDescriptor);
            this.mMR2ProviderServiceAdapter.setProviderDescriptor(mediaRouteProviderDescriptor);
        }

        public void setDynamicRoutesChangedListener(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController) {
            dynamicGroupRouteController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
        }
    }

    public static class MediaRouteProviderServiceImplBase implements MediaRouteProviderServiceImpl {
        MediaRouteDiscoveryRequest mBaseDiscoveryRequest;
        long mBaseDiscoveryRequestTimestamp;
        MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
        final MediaRouteProviderService mService;
        final ArrayList<ClientRecord> mClients = new ArrayList<>();
        private final MediaRouterActiveScanThrottlingHelper mActiveScanThrottlingHelper = new MediaRouterActiveScanThrottlingHelper(new Runnable() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.1
            @Override // java.lang.Runnable
            public void run() {
                MediaRouteProviderServiceImplBase.this.updateCompositeDiscoveryRequest();
            }
        });

        public class ClientRecord implements IBinder.DeathRecipient {
            public MediaRouteDiscoveryRequest mDiscoveryRequest;
            public long mDiscoveryRequestTimestamp;
            public final Messenger mMessenger;
            public final String mPackageName;
            public final int mVersion;
            final SparseArray<MediaRouteProvider.RouteController> mControllers = new SparseArray<>();
            final MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener mDynamicRoutesChangedListener = new MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.ClientRecord.1
                @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener
                public void onRoutesChanged(@NonNull MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, @NonNull MediaRouteDescriptor mediaRouteDescriptor, @NonNull Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                    ClientRecord.this.sendDynamicRouteDescriptors(dynamicGroupRouteController, mediaRouteDescriptor, collection);
                }
            };

            public ClientRecord(Messenger messenger, int i2, String str) {
                this.mMessenger = messenger;
                this.mVersion = i2;
                this.mPackageName = str;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                MediaRouteProviderServiceImplBase.this.mService.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
            }

            public Bundle createDescriptorBundle(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                return MediaRouteProviderService.createDescriptorBundleForClientVersion(mediaRouteProviderDescriptor, this.mVersion);
            }

            public Bundle createDynamicGroupRouteController(String str, int i2) {
                MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerOnCreateDynamicGroupRouteController;
                if (this.mControllers.indexOfKey(i2) >= 0 || (dynamicGroupRouteControllerOnCreateDynamicGroupRouteController = MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateDynamicGroupRouteController(str)) == null) {
                    return null;
                }
                dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.setOnDynamicRoutesChangedListener(ContextCompat.getMainExecutor(MediaRouteProviderServiceImplBase.this.mService.getApplicationContext()), this.mDynamicRoutesChangedListener);
                this.mControllers.put(i2, dynamicGroupRouteControllerOnCreateDynamicGroupRouteController);
                Bundle bundle = new Bundle();
                bundle.putString(MediaRouteProviderProtocol.DATA_KEY_GROUPABLE_SECION_TITLE, dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.getGroupableSelectionTitle());
                bundle.putString(MediaRouteProviderProtocol.DATA_KEY_TRANSFERABLE_SECTION_TITLE, dynamicGroupRouteControllerOnCreateDynamicGroupRouteController.getTransferableSectionTitle());
                return bundle;
            }

            public boolean createRouteController(String str, String str2, int i2) {
                if (this.mControllers.indexOfKey(i2) >= 0) {
                    return false;
                }
                MediaRouteProvider.RouteController routeControllerOnCreateRouteController = str2 == null ? MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateRouteController(str) : MediaRouteProviderServiceImplBase.this.mService.getMediaRouteProvider().onCreateRouteController(str, str2);
                if (routeControllerOnCreateRouteController == null) {
                    return false;
                }
                this.mControllers.put(i2, routeControllerOnCreateRouteController);
                return true;
            }

            public void dispose() {
                int size = this.mControllers.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.mControllers.valueAt(i2).onRelease();
                }
                this.mControllers.clear();
                this.mMessenger.getBinder().unlinkToDeath(this, 0);
                setDiscoveryRequest(null);
            }

            public MediaRouteProvider.RouteController getRouteController(int i2) {
                return this.mControllers.get(i2);
            }

            public boolean hasMessenger(Messenger messenger) {
                return this.mMessenger.getBinder() == messenger.getBinder();
            }

            public boolean register() {
                try {
                    this.mMessenger.getBinder().linkToDeath(this, 0);
                    return true;
                } catch (RemoteException unused) {
                    binderDied();
                    return false;
                }
            }

            public boolean releaseRouteController(int i2) {
                MediaRouteProvider.RouteController routeController = this.mControllers.get(i2);
                if (routeController == null) {
                    return false;
                }
                this.mControllers.remove(i2);
                routeController.onRelease();
                return true;
            }

            public void sendDynamicRouteDescriptors(MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteController, MediaRouteDescriptor mediaRouteDescriptor, Collection<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> collection) {
                int iIndexOfValue = this.mControllers.indexOfValue(dynamicGroupRouteController);
                if (iIndexOfValue < 0) {
                    Log.w(MediaRouteProviderService.TAG, "Ignoring unknown dynamic group route controller: " + dynamicGroupRouteController);
                    return;
                }
                int iKeyAt = this.mControllers.keyAt(iIndexOfValue);
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                Iterator<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toBundle());
                }
                Bundle bundle = new Bundle();
                if (mediaRouteDescriptor != null) {
                    bundle.putParcelable(MediaRouteProviderProtocol.DATA_KEY_GROUP_ROUTE_DESCRIPTOR, mediaRouteDescriptor.asBundle());
                }
                bundle.putParcelableArrayList(MediaRouteProviderProtocol.DATA_KEY_DYNAMIC_ROUTE_DESCRIPTORS, arrayList);
                MediaRouteProviderService.sendMessage(this.mMessenger, 7, 0, iKeyAt, bundle, null);
            }

            public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (ObjectsCompat.equals(this.mDiscoveryRequest, mediaRouteDiscoveryRequest)) {
                    return false;
                }
                this.mDiscoveryRequest = mediaRouteDiscoveryRequest;
                this.mDiscoveryRequestTimestamp = jElapsedRealtime;
                return MediaRouteProviderServiceImplBase.this.updateCompositeDiscoveryRequest();
            }

            @NonNull
            public String toString() {
                return MediaRouteProviderService.getClientId(this.mMessenger);
            }
        }

        public class ProviderCallbackBase extends MediaRouteProvider.Callback {
            public ProviderCallbackBase() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.Callback
            public void onDescriptorChanged(@NonNull MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                MediaRouteProviderServiceImplBase.this.sendDescriptorChanged(mediaRouteProviderDescriptor);
            }
        }

        public MediaRouteProviderServiceImplBase(MediaRouteProviderService mediaRouteProviderService) {
            this.mService = mediaRouteProviderService;
        }

        private ClientRecord getClient(Messenger messenger) {
            int iFindClient = findClient(messenger);
            if (iFindClient >= 0) {
                return this.mClients.get(iFindClient);
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void attachBaseContext(Context context) {
        }

        public ClientRecord createClientRecord(Messenger messenger, int i2, String str) {
            return new ClientRecord(messenger, i2, str);
        }

        public int findClient(Messenger messenger) {
            int size = this.mClients.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mClients.get(i2).hasMessenger(messenger)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public MediaRouteProvider.Callback getProviderCallback() {
            return new ProviderCallbackBase();
        }

        public MediaRouteProviderService getService() {
            return this.mService;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onAddMemberRoute(Messenger messenger, int i2, int i3, String str) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            MediaRouteProvider.RouteController routeController = client.getRouteController(i3);
            if (!(routeController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) routeController).onAddMemberRoute(str);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Added a member route, controllerId=" + i3 + ", memberId=" + str);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public IBinder onBind(Intent intent) {
            if (!intent.getAction().equals("android.media.MediaRouteProviderService")) {
                return null;
            }
            this.mService.ensureProvider();
            if (this.mService.getMediaRouteProvider() != null) {
                return this.mService.mReceiveMessenger.getBinder();
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public void onBinderDied(Messenger messenger) {
            int iFindClient = findClient(messenger);
            if (iFindClient >= 0) {
                ClientRecord clientRecordRemove = this.mClients.remove(iFindClient);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, clientRecordRemove + ": Binder died");
                }
                clientRecordRemove.dispose();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateDynamicGroupRouteController(Messenger messenger, int i2, int i3, String str) {
            Bundle bundleCreateDynamicGroupRouteController;
            ClientRecord client = getClient(messenger);
            if (client == null || (bundleCreateDynamicGroupRouteController = client.createDynamicGroupRouteController(str, i3)) == null) {
                return false;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route controller created, controllerId=" + i3 + ", initialMemberRouteId=" + str);
            }
            MediaRouteProviderService.sendMessage(messenger, 6, i2, 3, bundleCreateDynamicGroupRouteController, null);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onCreateRouteController(Messenger messenger, int i2, int i3, String str, String str2) {
            ClientRecord client = getClient(messenger);
            if (client == null || !client.createRouteController(str, str2, i3)) {
                return false;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route controller created, controllerId=" + i3 + ", routeId=" + str + ", routeGroupId=" + str2);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRegisterClient(Messenger messenger, int i2, int i3, String str) {
            if (i3 < 1 || findClient(messenger) >= 0) {
                return false;
            }
            ClientRecord clientRecordCreateClientRecord = createClientRecord(messenger, i3, str);
            if (!clientRecordCreateClientRecord.register()) {
                return false;
            }
            this.mClients.add(clientRecordCreateClientRecord);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, clientRecordCreateClientRecord + ": Registered, version=" + i3);
            }
            if (i2 != 0) {
                MediaRouteProviderService.sendMessage(messenger, 2, i2, 3, MediaRouteProviderService.createDescriptorBundleForClientVersion(this.mService.getMediaRouteProvider().getDescriptor(), clientRecordCreateClientRecord.mVersion), null);
            }
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onReleaseRouteController(Messenger messenger, int i2, int i3) {
            ClientRecord client = getClient(messenger);
            if (client == null || !client.releaseRouteController(i3)) {
                return false;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route controller released, controllerId=" + i3);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRemoveMemberRoute(Messenger messenger, int i2, int i3, String str) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            MediaRouteProvider.RouteController routeController = client.getRouteController(i3);
            if (!(routeController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) routeController).onRemoveMemberRoute(str);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Removed a member route, controllerId=" + i3 + ", memberId=" + str);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onRouteControlRequest(final Messenger messenger, final int i2, final int i3, final Intent intent) {
            MediaRouteProvider.RouteController routeController;
            final ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i3)) == null) {
                return false;
            }
            if (!routeController.onControlRequest(intent, i2 != 0 ? new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImplBase.2
                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onError(String str, Bundle bundle) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, client + ": Route control request failed, controllerId=" + i3 + ", intent=" + intent + ", error=" + str + ", data=" + bundle);
                    }
                    if (MediaRouteProviderServiceImplBase.this.findClient(messenger) >= 0) {
                        if (str == null) {
                            MediaRouteProviderService.sendMessage(messenger, 4, i2, 0, bundle, null);
                            return;
                        }
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, str);
                        MediaRouteProviderService.sendMessage(messenger, 4, i2, 0, bundle, bundle2);
                    }
                }

                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onResult(Bundle bundle) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, client + ": Route control request succeeded, controllerId=" + i3 + ", intent=" + intent + ", data=" + bundle);
                    }
                    if (MediaRouteProviderServiceImplBase.this.findClient(messenger) >= 0) {
                        MediaRouteProviderService.sendMessage(messenger, 3, i2, 0, bundle, null);
                    }
                }
            } : null)) {
                return false;
            }
            if (!MediaRouteProviderService.DEBUG) {
                return true;
            }
            Log.d(MediaRouteProviderService.TAG, client + ": Route control request delivered, controllerId=" + i3 + ", intent=" + intent);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSelectRoute(Messenger messenger, int i2, int i3) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i3)) == null) {
                return false;
            }
            routeController.onSelect();
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route selected, controllerId=" + i3);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetDiscoveryRequest(Messenger messenger, int i2, MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            boolean discoveryRequest = client.setDiscoveryRequest(mediaRouteDiscoveryRequest);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Set discovery request, request=" + mediaRouteDiscoveryRequest + ", actuallyChanged=" + discoveryRequest + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onSetRouteVolume(Messenger messenger, int i2, int i3, int i4) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i3)) == null) {
                return false;
            }
            routeController.onSetVolume(i4);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route volume changed, controllerId=" + i3 + ", volume=" + i4);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnregisterClient(Messenger messenger, int i2) {
            int iFindClient = findClient(messenger);
            if (iFindClient < 0) {
                return false;
            }
            ClientRecord clientRecordRemove = this.mClients.remove(iFindClient);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, clientRecordRemove + ": Unregistered");
            }
            clientRecordRemove.dispose();
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUnselectRoute(Messenger messenger, int i2, int i3, int i4) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i3)) == null) {
                return false;
            }
            routeController.onUnselect(i4);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route unselected, controllerId=" + i3);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateMemberRoutes(Messenger messenger, int i2, int i3, List<String> list) {
            ClientRecord client = getClient(messenger);
            if (client == null) {
                return false;
            }
            MediaRouteProvider.RouteController routeController = client.getRouteController(i3);
            if (!(routeController instanceof MediaRouteProvider.DynamicGroupRouteController)) {
                return false;
            }
            ((MediaRouteProvider.DynamicGroupRouteController) routeController).onUpdateMemberRoutes(list);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Updated list of member routes, controllerId=" + i3 + ", memberIds=" + list);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        @Override // androidx.mediarouter.media.MediaRouteProviderService.MediaRouteProviderServiceImpl
        public boolean onUpdateRouteVolume(Messenger messenger, int i2, int i3, int i4) {
            MediaRouteProvider.RouteController routeController;
            ClientRecord client = getClient(messenger);
            if (client == null || (routeController = client.getRouteController(i3)) == null) {
                return false;
            }
            routeController.onUpdateVolume(i4);
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, client + ": Route volume updated, controllerId=" + i3 + ", delta=" + i4);
            }
            MediaRouteProviderService.sendGenericSuccess(messenger, i2);
            return true;
        }

        public void sendDescriptorChanged(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            int size = this.mClients.size();
            for (int i2 = 0; i2 < size; i2++) {
                ClientRecord clientRecord = this.mClients.get(i2);
                MediaRouteProviderService.sendMessage(clientRecord.mMessenger, 5, 0, 0, clientRecord.createDescriptorBundle(mediaRouteProviderDescriptor), null);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, clientRecord + ": Sent descriptor change event, descriptor=" + mediaRouteProviderDescriptor);
                }
            }
        }

        public boolean setBaseDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (ObjectsCompat.equals(this.mBaseDiscoveryRequest, mediaRouteDiscoveryRequest) && !mediaRouteDiscoveryRequest.isActiveScan()) {
                return false;
            }
            this.mBaseDiscoveryRequest = mediaRouteDiscoveryRequest;
            this.mBaseDiscoveryRequestTimestamp = jElapsedRealtime;
            return updateCompositeDiscoveryRequest();
        }

        public boolean updateCompositeDiscoveryRequest() {
            MediaRouteSelector.Builder builder;
            this.mActiveScanThrottlingHelper.reset();
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mBaseDiscoveryRequest;
            if (mediaRouteDiscoveryRequest != null) {
                this.mActiveScanThrottlingHelper.requestActiveScan(mediaRouteDiscoveryRequest.isActiveScan(), this.mBaseDiscoveryRequestTimestamp);
                builder = new MediaRouteSelector.Builder(this.mBaseDiscoveryRequest.getSelector());
            } else {
                builder = null;
            }
            int size = this.mClients.size();
            for (int i2 = 0; i2 < size; i2++) {
                ClientRecord clientRecord = this.mClients.get(i2);
                MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest2 = clientRecord.mDiscoveryRequest;
                if (mediaRouteDiscoveryRequest2 != null && (!mediaRouteDiscoveryRequest2.getSelector().isEmpty() || mediaRouteDiscoveryRequest2.isActiveScan())) {
                    this.mActiveScanThrottlingHelper.requestActiveScan(mediaRouteDiscoveryRequest2.isActiveScan(), clientRecord.mDiscoveryRequestTimestamp);
                    if (builder == null) {
                        builder = new MediaRouteSelector.Builder(mediaRouteDiscoveryRequest2.getSelector());
                    } else {
                        builder.addSelector(mediaRouteDiscoveryRequest2.getSelector());
                    }
                }
            }
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest3 = builder != null ? new MediaRouteDiscoveryRequest(builder.build(), this.mActiveScanThrottlingHelper.finalizeActiveScanAndScheduleSuppressActiveScanRunnable()) : null;
            if (ObjectsCompat.equals(this.mCompositeDiscoveryRequest, mediaRouteDiscoveryRequest3)) {
                return false;
            }
            this.mCompositeDiscoveryRequest = mediaRouteDiscoveryRequest3;
            MediaRouteProvider mediaRouteProvider = this.mService.getMediaRouteProvider();
            if (mediaRouteProvider == null) {
                return true;
            }
            mediaRouteProvider.setDiscoveryRequest(mediaRouteDiscoveryRequest3);
            return true;
        }
    }

    public final class PrivateHandler extends Handler {
        public PrivateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            MediaRouteProviderService.this.mImpl.onBinderDied((Messenger) message.obj);
        }
    }

    public static final class ReceiveHandler extends Handler {
        private final WeakReference<MediaRouteProviderService> mServiceRef;

        public ReceiveHandler(MediaRouteProviderService mediaRouteProviderService) {
            this.mServiceRef = new WeakReference<>(mediaRouteProviderService);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private boolean processMessage(int i2, Messenger messenger, int i3, int i4, Object obj, Bundle bundle, String str) {
            MediaRouteProviderService mediaRouteProviderService = this.mServiceRef.get();
            if (mediaRouteProviderService != null) {
                switch (i2) {
                    case 1:
                        return mediaRouteProviderService.mImpl.onRegisterClient(messenger, i3, i4, str);
                    case 2:
                        return mediaRouteProviderService.mImpl.onUnregisterClient(messenger, i3);
                    case 3:
                        String string = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
                        String string2 = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP);
                        if (string != null) {
                            return mediaRouteProviderService.mImpl.onCreateRouteController(messenger, i3, i4, string, string2);
                        }
                        break;
                    case 4:
                        return mediaRouteProviderService.mImpl.onReleaseRouteController(messenger, i3, i4);
                    case 5:
                        return mediaRouteProviderService.mImpl.onSelectRoute(messenger, i3, i4);
                    case 6:
                        return mediaRouteProviderService.mImpl.onUnselectRoute(messenger, i3, i4, bundle != null ? bundle.getInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, 0) : 0);
                    case 7:
                        int i5 = bundle.getInt("volume", -1);
                        if (i5 >= 0) {
                            return mediaRouteProviderService.mImpl.onSetRouteVolume(messenger, i3, i4, i5);
                        }
                        break;
                    case 8:
                        int i6 = bundle.getInt("volume", 0);
                        if (i6 != 0) {
                            return mediaRouteProviderService.mImpl.onUpdateRouteVolume(messenger, i3, i4, i6);
                        }
                        break;
                    case 9:
                        if (obj instanceof Intent) {
                            return mediaRouteProviderService.mImpl.onRouteControlRequest(messenger, i3, i4, (Intent) obj);
                        }
                        break;
                    case 10:
                        if (obj == null || (obj instanceof Bundle)) {
                            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequestFromBundle = MediaRouteDiscoveryRequest.fromBundle((Bundle) obj);
                            MediaRouteProviderServiceImpl mediaRouteProviderServiceImpl = mediaRouteProviderService.mImpl;
                            if (mediaRouteDiscoveryRequestFromBundle == null || !mediaRouteDiscoveryRequestFromBundle.isValid()) {
                                mediaRouteDiscoveryRequestFromBundle = null;
                            }
                            return mediaRouteProviderServiceImpl.onSetDiscoveryRequest(messenger, i3, mediaRouteDiscoveryRequestFromBundle);
                        }
                        break;
                    case 11:
                        String string3 = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
                        if (string3 != null) {
                            return mediaRouteProviderService.mImpl.onCreateDynamicGroupRouteController(messenger, i3, i4, string3);
                        }
                        break;
                    case 12:
                        String string4 = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
                        if (string4 != null) {
                            return mediaRouteProviderService.mImpl.onAddMemberRoute(messenger, i3, i4, string4);
                        }
                        break;
                    case 13:
                        String string5 = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID);
                        if (string5 != null) {
                            return mediaRouteProviderService.mImpl.onRemoveMemberRoute(messenger, i3, i4, string5);
                        }
                        break;
                    case 14:
                        ArrayList<String> stringArrayList = bundle.getStringArrayList(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_IDS);
                        if (stringArrayList != null) {
                            return mediaRouteProviderService.mImpl.onUpdateMemberRoutes(messenger, i3, i4, stringArrayList);
                        }
                        break;
                }
            }
            return false;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String[] packagesForUid;
            Messenger messenger = message.replyTo;
            if (!MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
                if (MediaRouteProviderService.DEBUG) {
                    Log.d(MediaRouteProviderService.TAG, "Ignoring message without valid reply messenger.");
                    return;
                }
                return;
            }
            int i2 = message.what;
            int i3 = message.arg1;
            int i4 = message.arg2;
            Object obj = message.obj;
            Bundle bundlePeekData = message.peekData();
            if (processMessage(i2, messenger, i3, i4, obj, bundlePeekData, (i2 != 1 || (packagesForUid = this.mServiceRef.get().getPackageManager().getPackagesForUid(message.sendingUid)) == null || packagesForUid.length <= 0) ? null : packagesForUid[0])) {
                return;
            }
            if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, MediaRouteProviderService.getClientId(messenger) + ": Message failed, what=" + i2 + ", requestId=" + i3 + ", arg=" + i4 + ", obj=" + obj + ", data=" + bundlePeekData);
            }
            MediaRouteProviderService.sendGenericFailure(messenger, i3);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRouteProviderService() {
        ReceiveHandler receiveHandler = new ReceiveHandler(this);
        this.mReceiveHandler = receiveHandler;
        this.mReceiveMessenger = new Messenger(receiveHandler);
        this.mPrivateHandler = new PrivateHandler();
        MediaRouteProviderServiceImplApi30 mediaRouteProviderServiceImplApi30 = new MediaRouteProviderServiceImplApi30(this);
        this.mImpl = mediaRouteProviderServiceImplApi30;
        this.mProviderCallback = mediaRouteProviderServiceImplApi30.getProviderCallback();
    }

    @VisibleForTesting
    public static Bundle createDescriptorBundleForClientVersion(MediaRouteProviderDescriptor mediaRouteProviderDescriptor, int i2) {
        if (mediaRouteProviderDescriptor == null) {
            return null;
        }
        MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder(mediaRouteProviderDescriptor);
        builder.setRoutes(null);
        if (i2 < 4) {
            builder.setSupportsDynamicGroupRoute(false);
        }
        for (MediaRouteDescriptor mediaRouteDescriptor : mediaRouteProviderDescriptor.getRoutes()) {
            if (i2 >= mediaRouteDescriptor.getMinClientVersion() && i2 <= mediaRouteDescriptor.getMaxClientVersion()) {
                builder.addRoute(mediaRouteDescriptor);
            }
        }
        return builder.build().asBundle();
    }

    public static String getClientId(Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }

    public static void sendGenericFailure(Messenger messenger, int i2) {
        if (i2 != 0) {
            sendMessage(messenger, 0, i2, 0, null, null);
        }
    }

    public static void sendGenericSuccess(Messenger messenger, int i2) {
        if (i2 != 0) {
            sendMessage(messenger, 1, i2, 0, null, null);
        }
    }

    public static void sendMessage(Messenger messenger, int i2, int i3, int i4, Object obj, Bundle bundle) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i2;
        messageObtain.arg1 = i3;
        messageObtain.arg2 = i4;
        messageObtain.obj = obj;
        messageObtain.setData(bundle);
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e2) {
            Log.e(TAG, "Could not send message to " + getClientId(messenger), e2);
        }
    }

    @Override // android.app.Service, android.content.ContextWrapper
    public void attachBaseContext(@NonNull Context context) {
        super.attachBaseContext(context);
        this.mImpl.attachBaseContext(context);
    }

    public void ensureProvider() {
        MediaRouteProvider mediaRouteProviderOnCreateMediaRouteProvider;
        if (this.mProvider != null || (mediaRouteProviderOnCreateMediaRouteProvider = onCreateMediaRouteProvider()) == null) {
            return;
        }
        String packageName = mediaRouteProviderOnCreateMediaRouteProvider.getMetadata().getPackageName();
        if (packageName.equals(getPackageName())) {
            this.mProvider = mediaRouteProviderOnCreateMediaRouteProvider;
            mediaRouteProviderOnCreateMediaRouteProvider.setCallback(this.mProviderCallback);
            return;
        }
        throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + packageName + ".  Service package name: " + getPackageName() + ".");
    }

    @Nullable
    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NonNull Intent intent) {
        return this.mImpl.onBind(intent);
    }

    @Nullable
    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    @Override // android.app.Service
    public void onDestroy() {
        MediaRouteProvider mediaRouteProvider = this.mProvider;
        if (mediaRouteProvider != null) {
            mediaRouteProvider.setCallback(null);
        }
        super.onDestroy();
    }
}
