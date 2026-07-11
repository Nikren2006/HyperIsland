package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class RegisteredMediaRouteProvider extends MediaRouteProvider implements ServiceConnection {
    static final boolean DEBUG = false;
    static final String TAG = "MediaRouteProviderProxy";
    private Connection mActiveConnection;
    private boolean mBound;
    private final ComponentName mComponentName;
    private boolean mConnectionReady;
    private ControllerCallback mControllerCallback;
    private final ArrayList<ControllerConnection> mControllerConnections;
    final PrivateHandler mPrivateHandler;
    private boolean mStarted;

    public final class Connection implements IBinder.DeathRecipient {
        private int mPendingRegisterRequestId;
        private final ReceiveHandler mReceiveHandler;
        private final Messenger mReceiveMessenger;
        private final Messenger mServiceMessenger;
        private int mServiceVersion;
        private int mNextRequestId = 1;
        private int mNextControllerId = 1;
        private final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks = new SparseArray<>();

        public Connection(Messenger messenger) {
            this.mServiceMessenger = messenger;
            ReceiveHandler receiveHandler = new ReceiveHandler(this);
            this.mReceiveHandler = receiveHandler;
            this.mReceiveMessenger = new Messenger(receiveHandler);
        }

        private boolean sendRequest(int i2, int i3, int i4, Object obj, Bundle bundle) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            messageObtain.arg2 = i4;
            messageObtain.obj = obj;
            messageObtain.setData(bundle);
            messageObtain.replyTo = this.mReceiveMessenger;
            try {
                this.mServiceMessenger.send(messageObtain);
                return true;
            } catch (DeadObjectException unused) {
                return false;
            } catch (RemoteException e2) {
                if (i2 == 2) {
                    return false;
                }
                Log.e(RegisteredMediaRouteProvider.TAG, "Could not send message to service.", e2);
                return false;
            }
        }

        public void addMemberRoute(int i2, String str) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID, str);
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            sendRequest(12, i3, i2, null, bundle);
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            RegisteredMediaRouteProvider.this.mPrivateHandler.post(new Runnable() { // from class: androidx.mediarouter.media.RegisteredMediaRouteProvider.Connection.2
                @Override // java.lang.Runnable
                public void run() {
                    Connection connection = Connection.this;
                    RegisteredMediaRouteProvider.this.onConnectionDied(connection);
                }
            });
        }

        public int createDynamicGroupRouteController(String str, MediaRouter.ControlRequestCallback controlRequestCallback) {
            int i2 = this.mNextControllerId;
            this.mNextControllerId = i2 + 1;
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            Bundle bundle = new Bundle();
            bundle.putString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID, str);
            sendRequest(11, i3, i2, null, bundle);
            this.mPendingCallbacks.put(i3, controlRequestCallback);
            return i2;
        }

        public int createRouteController(String str, String str2) {
            int i2 = this.mNextControllerId;
            this.mNextControllerId = i2 + 1;
            Bundle bundle = new Bundle();
            bundle.putString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID, str);
            bundle.putString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP, str2);
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            sendRequest(3, i3, i2, null, bundle);
            return i2;
        }

        public void dispose() {
            sendRequest(2, 0, 0, null, null);
            this.mReceiveHandler.dispose();
            this.mServiceMessenger.getBinder().unlinkToDeath(this, 0);
            RegisteredMediaRouteProvider.this.mPrivateHandler.post(new Runnable() { // from class: androidx.mediarouter.media.RegisteredMediaRouteProvider.Connection.1
                @Override // java.lang.Runnable
                public void run() {
                    Connection.this.failPendingCallbacks();
                }
            });
        }

        public void failPendingCallbacks() {
            int size = this.mPendingCallbacks.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mPendingCallbacks.valueAt(i2).onError(null, null);
            }
            this.mPendingCallbacks.clear();
        }

        public boolean onControlRequestFailed(int i2, String str, Bundle bundle) {
            MediaRouter.ControlRequestCallback controlRequestCallback = this.mPendingCallbacks.get(i2);
            if (controlRequestCallback == null) {
                return false;
            }
            this.mPendingCallbacks.remove(i2);
            controlRequestCallback.onError(str, bundle);
            return true;
        }

        public boolean onControlRequestSucceeded(int i2, Bundle bundle) {
            MediaRouter.ControlRequestCallback controlRequestCallback = this.mPendingCallbacks.get(i2);
            if (controlRequestCallback == null) {
                return false;
            }
            this.mPendingCallbacks.remove(i2);
            controlRequestCallback.onResult(bundle);
            return true;
        }

        public void onControllerReleasedByProvider(int i2) {
            RegisteredMediaRouteProvider.this.onConnectionControllerReleasedByProvider(this, i2);
        }

        public boolean onDescriptorChanged(Bundle bundle) {
            if (this.mServiceVersion == 0) {
                return false;
            }
            RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
            return true;
        }

        public void onDynamicGroupRouteControllerCreated(int i2, Bundle bundle) {
            MediaRouter.ControlRequestCallback controlRequestCallback = this.mPendingCallbacks.get(i2);
            if (bundle == null || !bundle.containsKey(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID)) {
                controlRequestCallback.onError("DynamicGroupRouteController is created without valid route id.", bundle);
            } else {
                this.mPendingCallbacks.remove(i2);
                controlRequestCallback.onResult(bundle);
            }
        }

        public boolean onDynamicRouteDescriptorsChanged(int i2, Bundle bundle) {
            if (this.mServiceVersion == 0) {
                return false;
            }
            Bundle bundle2 = (Bundle) bundle.getParcelable(MediaRouteProviderProtocol.DATA_KEY_GROUP_ROUTE_DESCRIPTOR);
            MediaRouteDescriptor mediaRouteDescriptorFromBundle = bundle2 != null ? MediaRouteDescriptor.fromBundle(bundle2) : null;
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(MediaRouteProviderProtocol.DATA_KEY_DYNAMIC_ROUTE_DESCRIPTORS);
            ArrayList arrayList = new ArrayList();
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                arrayList.add(MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor.fromBundle((Bundle) it.next()));
            }
            RegisteredMediaRouteProvider.this.onDynamicRouteDescriptorChanged(this, i2, mediaRouteDescriptorFromBundle, arrayList);
            return true;
        }

        public void onGenericFailure(int i2) {
            if (i2 == this.mPendingRegisterRequestId) {
                this.mPendingRegisterRequestId = 0;
                RegisteredMediaRouteProvider.this.onConnectionError(this, "Registration failed");
            }
            MediaRouter.ControlRequestCallback controlRequestCallback = this.mPendingCallbacks.get(i2);
            if (controlRequestCallback != null) {
                this.mPendingCallbacks.remove(i2);
                controlRequestCallback.onError(null, null);
            }
        }

        public boolean onRegistered(int i2, int i3, Bundle bundle) {
            if (this.mServiceVersion != 0 || i2 != this.mPendingRegisterRequestId || i3 < 1) {
                return false;
            }
            this.mPendingRegisterRequestId = 0;
            this.mServiceVersion = i3;
            RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
            RegisteredMediaRouteProvider.this.onConnectionReady(this);
            return true;
        }

        public boolean register() {
            int i2 = this.mNextRequestId;
            this.mNextRequestId = i2 + 1;
            this.mPendingRegisterRequestId = i2;
            if (!sendRequest(1, i2, 4, null, null)) {
                return false;
            }
            try {
                this.mServiceMessenger.getBinder().linkToDeath(this, 0);
                return true;
            } catch (RemoteException unused) {
                binderDied();
                return false;
            }
        }

        public void releaseRouteController(int i2) {
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            sendRequest(4, i3, i2, null, null);
        }

        public void removeMemberRoute(int i2, String str) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_ID, str);
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            sendRequest(13, i3, i2, null, bundle);
        }

        public void selectRoute(int i2) {
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            sendRequest(5, i3, i2, null, null);
        }

        public boolean sendControlRequest(int i2, Intent intent, MediaRouter.ControlRequestCallback controlRequestCallback) {
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            if (!sendRequest(9, i3, i2, intent, null)) {
                return false;
            }
            if (controlRequestCallback == null) {
                return true;
            }
            this.mPendingCallbacks.put(i3, controlRequestCallback);
            return true;
        }

        public void setDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            int i2 = this.mNextRequestId;
            this.mNextRequestId = i2 + 1;
            sendRequest(10, i2, 0, mediaRouteDiscoveryRequest != null ? mediaRouteDiscoveryRequest.asBundle() : null, null);
        }

        public void setVolume(int i2, int i3) {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i3);
            int i4 = this.mNextRequestId;
            this.mNextRequestId = i4 + 1;
            sendRequest(7, i4, i2, null, bundle);
        }

        public void unselectRoute(int i2, int i3) {
            Bundle bundle = new Bundle();
            bundle.putInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, i3);
            int i4 = this.mNextRequestId;
            this.mNextRequestId = i4 + 1;
            sendRequest(6, i4, i2, null, bundle);
        }

        public void updateMemberRoutes(int i2, List<String> list) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(MediaRouteProviderProtocol.CLIENT_DATA_MEMBER_ROUTE_IDS, new ArrayList<>(list));
            int i3 = this.mNextRequestId;
            this.mNextRequestId = i3 + 1;
            sendRequest(14, i3, i2, null, bundle);
        }

        public void updateVolume(int i2, int i3) {
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i3);
            int i4 = this.mNextRequestId;
            this.mNextRequestId = i4 + 1;
            sendRequest(8, i4, i2, null, bundle);
        }
    }

    public interface ControllerCallback {
        void onControllerReleasedByProvider(MediaRouteProvider.RouteController routeController);
    }

    public interface ControllerConnection {
        void attachConnection(Connection connection);

        void detachConnection();

        int getControllerId();
    }

    public static final class PrivateHandler extends Handler {
    }

    public static final class ReceiveHandler extends Handler {
        private final WeakReference<Connection> mConnectionRef;

        public ReceiveHandler(Connection connection) {
            this.mConnectionRef = new WeakReference<>(connection);
        }

        private boolean processMessage(Connection connection, int i2, int i3, int i4, Object obj, Bundle bundle) {
            switch (i2) {
                case 0:
                    connection.onGenericFailure(i3);
                    return true;
                case 1:
                    return true;
                case 2:
                    if (obj == null || (obj instanceof Bundle)) {
                        return connection.onRegistered(i3, i4, (Bundle) obj);
                    }
                    return false;
                case 3:
                    if (obj == null || (obj instanceof Bundle)) {
                        return connection.onControlRequestSucceeded(i3, (Bundle) obj);
                    }
                    return false;
                case 4:
                    if (obj == null || (obj instanceof Bundle)) {
                        return connection.onControlRequestFailed(i3, bundle == null ? null : bundle.getString(MediaRouteProviderProtocol.SERVICE_DATA_ERROR), (Bundle) obj);
                    }
                    return false;
                case 5:
                    if (obj == null || (obj instanceof Bundle)) {
                        return connection.onDescriptorChanged((Bundle) obj);
                    }
                    return false;
                case 6:
                    if (obj instanceof Bundle) {
                        connection.onDynamicGroupRouteControllerCreated(i3, (Bundle) obj);
                        return false;
                    }
                    Log.w(RegisteredMediaRouteProvider.TAG, "No further information on the dynamic group controller");
                    return false;
                case 7:
                    if (obj == null || (obj instanceof Bundle)) {
                        return connection.onDynamicRouteDescriptorsChanged(i4, (Bundle) obj);
                    }
                    return false;
                case 8:
                    connection.onControllerReleasedByProvider(i4);
                    return false;
                default:
                    return false;
            }
        }

        public void dispose() {
            this.mConnectionRef.clear();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Connection connection = this.mConnectionRef.get();
            if (connection == null || processMessage(connection, message.what, message.arg1, message.arg2, message.obj, message.peekData()) || !RegisteredMediaRouteProvider.DEBUG) {
                return;
            }
            Log.d(RegisteredMediaRouteProvider.TAG, "Unhandled message from server: " + message);
        }
    }

    public final class RegisteredDynamicController extends MediaRouteProvider.DynamicGroupRouteController implements ControllerConnection {
        private Connection mConnection;
        String mGroupableSectionTitle;
        private final String mInitialMemberRouteId;
        private int mPendingUpdateVolumeDelta;
        private boolean mSelected;
        String mTransferableSectionTitle;
        private int mPendingSetVolume = -1;
        private int mControllerId = -1;

        public RegisteredDynamicController(String str) {
            this.mInitialMemberRouteId = str;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void attachConnection(Connection connection) {
            MediaRouter.ControlRequestCallback controlRequestCallback = new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.RegisteredMediaRouteProvider.RegisteredDynamicController.1
                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onError(String str, Bundle bundle) {
                    Log.d(RegisteredMediaRouteProvider.TAG, "Error: " + str + ", data: " + bundle);
                }

                @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
                public void onResult(Bundle bundle) {
                    RegisteredDynamicController.this.mGroupableSectionTitle = bundle.getString(MediaRouteProviderProtocol.DATA_KEY_GROUPABLE_SECION_TITLE);
                    RegisteredDynamicController.this.mTransferableSectionTitle = bundle.getString(MediaRouteProviderProtocol.DATA_KEY_TRANSFERABLE_SECTION_TITLE);
                }
            };
            this.mConnection = connection;
            int iCreateDynamicGroupRouteController = connection.createDynamicGroupRouteController(this.mInitialMemberRouteId, controlRequestCallback);
            this.mControllerId = iCreateDynamicGroupRouteController;
            if (this.mSelected) {
                connection.selectRoute(iCreateDynamicGroupRouteController);
                int i2 = this.mPendingSetVolume;
                if (i2 >= 0) {
                    connection.setVolume(this.mControllerId, i2);
                    this.mPendingSetVolume = -1;
                }
                int i3 = this.mPendingUpdateVolumeDelta;
                if (i3 != 0) {
                    connection.updateVolume(this.mControllerId, i3);
                    this.mPendingUpdateVolumeDelta = 0;
                }
            }
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void detachConnection() {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.releaseRouteController(this.mControllerId);
                this.mConnection = null;
                this.mControllerId = 0;
            }
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public int getControllerId() {
            return this.mControllerId;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public String getGroupableSelectionTitle() {
            return this.mGroupableSectionTitle;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public String getTransferableSectionTitle() {
            return this.mTransferableSectionTitle;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(@NonNull String str) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.addMemberRoute(this.mControllerId, str);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(@NonNull Intent intent, MediaRouter.ControlRequestCallback controlRequestCallback) {
            Connection connection = this.mConnection;
            if (connection != null) {
                return connection.sendControlRequest(this.mControllerId, intent, controlRequestCallback);
            }
            return false;
        }

        public void onDynamicRoutesChanged(MediaRouteDescriptor mediaRouteDescriptor, List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> list) {
            notifyDynamicRoutesChanged(mediaRouteDescriptor, list);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            RegisteredMediaRouteProvider.this.onControllerReleased(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(@NonNull String str) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.removeMemberRoute(this.mControllerId, str);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mSelected = true;
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.selectRoute(this.mControllerId);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int i2) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.setVolume(this.mControllerId, i2);
            } else {
                this.mPendingSetVolume = i2;
                this.mPendingUpdateVolumeDelta = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect() {
            onUnselect(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(@Nullable List<String> list) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.updateMemberRoutes(this.mControllerId, list);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int i2) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.updateVolume(this.mControllerId, i2);
            } else {
                this.mPendingUpdateVolumeDelta += i2;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int i2) {
            this.mSelected = false;
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.unselectRoute(this.mControllerId, i2);
            }
        }
    }

    public final class RegisteredRouteController extends MediaRouteProvider.RouteController implements ControllerConnection {
        private Connection mConnection;
        private int mControllerId;
        private int mPendingSetVolume = -1;
        private int mPendingUpdateVolumeDelta;
        private final String mRouteGroupId;
        private final String mRouteId;
        private boolean mSelected;

        public RegisteredRouteController(String str, String str2) {
            this.mRouteId = str;
            this.mRouteGroupId = str2;
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void attachConnection(Connection connection) {
            this.mConnection = connection;
            int iCreateRouteController = connection.createRouteController(this.mRouteId, this.mRouteGroupId);
            this.mControllerId = iCreateRouteController;
            if (this.mSelected) {
                connection.selectRoute(iCreateRouteController);
                int i2 = this.mPendingSetVolume;
                if (i2 >= 0) {
                    connection.setVolume(this.mControllerId, i2);
                    this.mPendingSetVolume = -1;
                }
                int i3 = this.mPendingUpdateVolumeDelta;
                if (i3 != 0) {
                    connection.updateVolume(this.mControllerId, i3);
                    this.mPendingUpdateVolumeDelta = 0;
                }
            }
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public void detachConnection() {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.releaseRouteController(this.mControllerId);
                this.mConnection = null;
                this.mControllerId = 0;
            }
        }

        @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerConnection
        public int getControllerId() {
            return this.mControllerId;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public boolean onControlRequest(@NonNull Intent intent, MediaRouter.ControlRequestCallback controlRequestCallback) {
            Connection connection = this.mConnection;
            if (connection != null) {
                return connection.sendControlRequest(this.mControllerId, intent, controlRequestCallback);
            }
            return false;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            RegisteredMediaRouteProvider.this.onControllerReleased(this);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSelect() {
            this.mSelected = true;
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.selectRoute(this.mControllerId);
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int i2) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.setVolume(this.mControllerId, i2);
            } else {
                this.mPendingSetVolume = i2;
                this.mPendingUpdateVolumeDelta = 0;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect() {
            onUnselect(0);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int i2) {
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.updateVolume(this.mControllerId, i2);
            } else {
                this.mPendingUpdateVolumeDelta += i2;
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUnselect(int i2) {
            this.mSelected = false;
            Connection connection = this.mConnection;
            if (connection != null) {
                connection.unselectRoute(this.mControllerId, i2);
            }
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public RegisteredMediaRouteProvider(Context context, ComponentName componentName) {
        super(context, new MediaRouteProvider.ProviderMetadata(componentName));
        this.mControllerConnections = new ArrayList<>();
        this.mComponentName = componentName;
        this.mPrivateHandler = new PrivateHandler();
    }

    private void attachControllersToConnection() {
        int size = this.mControllerConnections.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mControllerConnections.get(i2).attachConnection(this.mActiveConnection);
        }
    }

    private void bind() {
        if (this.mBound) {
            return;
        }
        Intent intent = new Intent("android.media.MediaRouteProviderService");
        intent.setComponent(this.mComponentName);
        try {
            this.mBound = getContext().bindService(intent, this, 4097);
        } catch (SecurityException unused) {
        }
    }

    private MediaRouteProvider.DynamicGroupRouteController createDynamicGroupRouteController(String str) {
        MediaRouteProviderDescriptor descriptor = getDescriptor();
        if (descriptor == null) {
            return null;
        }
        List<MediaRouteDescriptor> routes = descriptor.getRoutes();
        int size = routes.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (routes.get(i2).getId().equals(str)) {
                RegisteredDynamicController registeredDynamicController = new RegisteredDynamicController(str);
                this.mControllerConnections.add(registeredDynamicController);
                if (this.mConnectionReady) {
                    registeredDynamicController.attachConnection(this.mActiveConnection);
                }
                updateBinding();
                return registeredDynamicController;
            }
        }
        return null;
    }

    private MediaRouteProvider.RouteController createRouteController(String str, String str2) {
        MediaRouteProviderDescriptor descriptor = getDescriptor();
        if (descriptor == null) {
            return null;
        }
        List<MediaRouteDescriptor> routes = descriptor.getRoutes();
        int size = routes.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (routes.get(i2).getId().equals(str)) {
                RegisteredRouteController registeredRouteController = new RegisteredRouteController(str, str2);
                this.mControllerConnections.add(registeredRouteController);
                if (this.mConnectionReady) {
                    registeredRouteController.attachConnection(this.mActiveConnection);
                }
                updateBinding();
                return registeredRouteController;
            }
        }
        return null;
    }

    private void detachControllersFromConnection() {
        int size = this.mControllerConnections.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mControllerConnections.get(i2).detachConnection();
        }
    }

    private void disconnect() {
        if (this.mActiveConnection != null) {
            setDescriptor(null);
            this.mConnectionReady = false;
            detachControllersFromConnection();
            this.mActiveConnection.dispose();
            this.mActiveConnection = null;
        }
    }

    private ControllerConnection findControllerById(int i2) {
        for (ControllerConnection controllerConnection : this.mControllerConnections) {
            if (controllerConnection.getControllerId() == i2) {
                return controllerConnection;
            }
        }
        return null;
    }

    private boolean shouldBind() {
        if (this.mStarted) {
            return (getDiscoveryRequest() == null && this.mControllerConnections.isEmpty()) ? false : true;
        }
        return false;
    }

    private void unbind() {
        if (this.mBound) {
            this.mBound = false;
            disconnect();
            try {
                getContext().unbindService(this);
            } catch (IllegalArgumentException e2) {
                Log.e(TAG, this + ": unbindService failed", e2);
            }
        }
    }

    private void updateBinding() {
        if (shouldBind()) {
            bind();
        } else {
            unbind();
        }
    }

    public boolean hasComponentName(String str, String str2) {
        return this.mComponentName.getPackageName().equals(str) && this.mComponentName.getClassName().equals(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConnectionControllerReleasedByProvider(Connection connection, int i2) {
        if (this.mActiveConnection == connection) {
            ControllerConnection controllerConnectionFindControllerById = findControllerById(i2);
            ControllerCallback controllerCallback = this.mControllerCallback;
            if (controllerCallback != null && (controllerConnectionFindControllerById instanceof MediaRouteProvider.RouteController)) {
                controllerCallback.onControllerReleasedByProvider((MediaRouteProvider.RouteController) controllerConnectionFindControllerById);
            }
            onControllerReleased(controllerConnectionFindControllerById);
        }
    }

    public void onConnectionDescriptorChanged(Connection connection, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        if (this.mActiveConnection == connection) {
            setDescriptor(mediaRouteProviderDescriptor);
        }
    }

    public void onConnectionDied(Connection connection) {
        if (this.mActiveConnection == connection) {
            disconnect();
        }
    }

    public void onConnectionError(Connection connection, String str) {
        if (this.mActiveConnection == connection) {
            unbind();
        }
    }

    public void onConnectionReady(Connection connection) {
        if (this.mActiveConnection == connection) {
            this.mConnectionReady = true;
            attachControllersToConnection();
            MediaRouteDiscoveryRequest discoveryRequest = getDiscoveryRequest();
            if (discoveryRequest != null) {
                this.mActiveConnection.setDiscoveryRequest(discoveryRequest);
            }
        }
    }

    public void onControllerReleased(ControllerConnection controllerConnection) {
        this.mControllerConnections.remove(controllerConnection);
        controllerConnection.detachConnection();
        updateBinding();
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.DynamicGroupRouteController onCreateDynamicGroupRouteController(@NonNull String str) {
        if (str != null) {
            return createDynamicGroupRouteController(str);
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String str) {
        if (str != null) {
            return createRouteController(str, null);
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        if (this.mConnectionReady) {
            this.mActiveConnection.setDiscoveryRequest(mediaRouteDiscoveryRequest);
        }
        updateBinding();
    }

    public void onDynamicRouteDescriptorChanged(Connection connection, int i2, MediaRouteDescriptor mediaRouteDescriptor, List<MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor> list) {
        if (this.mActiveConnection == connection) {
            ControllerConnection controllerConnectionFindControllerById = findControllerById(i2);
            if (controllerConnectionFindControllerById instanceof RegisteredDynamicController) {
                ((RegisteredDynamicController) controllerConnectionFindControllerById).onDynamicRoutesChanged(mediaRouteDescriptor, list);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.mBound) {
            disconnect();
            Messenger messenger = iBinder != null ? new Messenger(iBinder) : null;
            if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
                Connection connection = new Connection(messenger);
                if (connection.register()) {
                    this.mActiveConnection = connection;
                    return;
                }
                return;
            }
            Log.e(TAG, this + ": Service returned invalid messenger binder");
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        disconnect();
    }

    public void rebindIfDisconnected() {
        if (this.mActiveConnection == null && shouldBind()) {
            unbind();
            bind();
        }
    }

    public void setControllerCallback(@Nullable ControllerCallback controllerCallback) {
        this.mControllerCallback = controllerCallback;
    }

    public void start() {
        if (this.mStarted) {
            return;
        }
        this.mStarted = true;
        updateBinding();
    }

    public void stop() {
        if (this.mStarted) {
            this.mStarted = false;
            updateBinding();
        }
    }

    @NonNull
    public String toString() {
        return "Service connection " + this.mComponentName.flattenToShortString();
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return createRouteController(str, str2);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }
}
