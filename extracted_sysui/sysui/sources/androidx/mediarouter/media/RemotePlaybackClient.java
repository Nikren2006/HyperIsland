package androidx.mediarouter.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.mediarouter.media.MediaRouter;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class RemotePlaybackClient {
    static final boolean DEBUG = false;
    static final String TAG = "RemotePlaybackClient";
    private final ActionReceiver mActionReceiver;
    private final Context mContext;
    private final PendingIntent mItemStatusPendingIntent;
    private final PendingIntent mMessagePendingIntent;
    OnMessageReceivedListener mOnMessageReceivedListener;
    private final MediaRouter.RouteInfo mRoute;
    private boolean mRouteSupportsMessaging;
    private boolean mRouteSupportsQueuing;
    private boolean mRouteSupportsRemotePlayback;
    private boolean mRouteSupportsSessionManagement;
    String mSessionId;
    private final PendingIntent mSessionStatusPendingIntent;
    StatusCallback mStatusCallback;

    public static abstract class ActionCallback {
        public void onError(@Nullable String str, int i2, @Nullable Bundle bundle) {
        }
    }

    public final class ActionReceiver extends BroadcastReceiver {
        public static final String ACTION_ITEM_STATUS_CHANGED = "androidx.mediarouter.media.actions.ACTION_ITEM_STATUS_CHANGED";
        public static final String ACTION_MESSAGE_RECEIVED = "androidx.mediarouter.media.actions.ACTION_MESSAGE_RECEIVED";
        public static final String ACTION_SESSION_STATUS_CHANGED = "androidx.mediarouter.media.actions.ACTION_SESSION_STATUS_CHANGED";

        public ActionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(MediaControlIntent.EXTRA_SESSION_ID);
            if (stringExtra == null || !stringExtra.equals(RemotePlaybackClient.this.mSessionId)) {
                Log.w(RemotePlaybackClient.TAG, "Discarding spurious status callback with missing or invalid session id: sessionId=" + stringExtra);
                return;
            }
            MediaSessionStatus mediaSessionStatusFromBundle = MediaSessionStatus.fromBundle(intent.getBundleExtra(MediaControlIntent.EXTRA_SESSION_STATUS));
            String action = intent.getAction();
            if (action.equals(ACTION_ITEM_STATUS_CHANGED)) {
                String stringExtra2 = intent.getStringExtra(MediaControlIntent.EXTRA_ITEM_ID);
                if (stringExtra2 == null) {
                    Log.w(RemotePlaybackClient.TAG, "Discarding spurious status callback with missing item id.");
                    return;
                }
                MediaItemStatus mediaItemStatusFromBundle = MediaItemStatus.fromBundle(intent.getBundleExtra(MediaControlIntent.EXTRA_ITEM_STATUS));
                if (mediaItemStatusFromBundle == null) {
                    Log.w(RemotePlaybackClient.TAG, "Discarding spurious status callback with missing item status.");
                    return;
                }
                if (RemotePlaybackClient.DEBUG) {
                    Log.d(RemotePlaybackClient.TAG, "Received item status callback: sessionId=" + stringExtra + ", sessionStatus=" + mediaSessionStatusFromBundle + ", itemId=" + stringExtra2 + ", itemStatus=" + mediaItemStatusFromBundle);
                }
                StatusCallback statusCallback = RemotePlaybackClient.this.mStatusCallback;
                if (statusCallback != null) {
                    statusCallback.onItemStatusChanged(intent.getExtras(), stringExtra, mediaSessionStatusFromBundle, stringExtra2, mediaItemStatusFromBundle);
                    return;
                }
                return;
            }
            if (!action.equals(ACTION_SESSION_STATUS_CHANGED)) {
                if (action.equals(ACTION_MESSAGE_RECEIVED)) {
                    if (RemotePlaybackClient.DEBUG) {
                        Log.d(RemotePlaybackClient.TAG, "Received message callback: sessionId=" + stringExtra);
                    }
                    OnMessageReceivedListener onMessageReceivedListener = RemotePlaybackClient.this.mOnMessageReceivedListener;
                    if (onMessageReceivedListener != null) {
                        onMessageReceivedListener.onMessageReceived(stringExtra, intent.getBundleExtra(MediaControlIntent.EXTRA_MESSAGE));
                        return;
                    }
                    return;
                }
                return;
            }
            if (mediaSessionStatusFromBundle == null) {
                Log.w(RemotePlaybackClient.TAG, "Discarding spurious media status callback with missing session status.");
                return;
            }
            if (RemotePlaybackClient.DEBUG) {
                Log.d(RemotePlaybackClient.TAG, "Received session status callback: sessionId=" + stringExtra + ", sessionStatus=" + mediaSessionStatusFromBundle);
            }
            StatusCallback statusCallback2 = RemotePlaybackClient.this.mStatusCallback;
            if (statusCallback2 != null) {
                statusCallback2.onSessionStatusChanged(intent.getExtras(), stringExtra, mediaSessionStatusFromBundle);
            }
        }
    }

    @RequiresApi(33)
    public static class Api33 {
        private Api33() {
        }

        @DoNotInline
        public static void registerReceiver(@NonNull Context context, @NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter, int i2) {
            context.registerReceiver(broadcastReceiver, intentFilter, i2);
        }
    }

    public static abstract class ItemActionCallback extends ActionCallback {
        public void onResult(@NonNull Bundle bundle, @NonNull String str, @Nullable MediaSessionStatus mediaSessionStatus, @NonNull String str2, @NonNull MediaItemStatus mediaItemStatus) {
        }
    }

    public interface OnMessageReceivedListener {
        void onMessageReceived(@NonNull String str, @Nullable Bundle bundle);
    }

    public static abstract class SessionActionCallback extends ActionCallback {
        public void onResult(@NonNull Bundle bundle, @NonNull String str, @Nullable MediaSessionStatus mediaSessionStatus) {
        }
    }

    public static abstract class StatusCallback {
        public void onItemStatusChanged(@Nullable Bundle bundle, @NonNull String str, @Nullable MediaSessionStatus mediaSessionStatus, @NonNull String str2, @NonNull MediaItemStatus mediaItemStatus) {
        }

        public void onSessionChanged(@Nullable String str) {
        }

        public void onSessionStatusChanged(@Nullable Bundle bundle, @NonNull String str, @Nullable MediaSessionStatus mediaSessionStatus) {
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public RemotePlaybackClient(@NonNull Context context, @NonNull MediaRouter.RouteInfo routeInfo) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (routeInfo == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        this.mContext = context;
        this.mRoute = routeInfo;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionReceiver.ACTION_ITEM_STATUS_CHANGED);
        intentFilter.addAction(ActionReceiver.ACTION_SESSION_STATUS_CHANGED);
        intentFilter.addAction(ActionReceiver.ACTION_MESSAGE_RECEIVED);
        ActionReceiver actionReceiver = new ActionReceiver();
        this.mActionReceiver = actionReceiver;
        Api33.registerReceiver(context, actionReceiver, intentFilter, 4);
        Intent intent = new Intent(ActionReceiver.ACTION_ITEM_STATUS_CHANGED);
        intent.setPackage(context.getPackageName());
        this.mItemStatusPendingIntent = PendingIntent.getBroadcast(context, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Intent intent2 = new Intent(ActionReceiver.ACTION_SESSION_STATUS_CHANGED);
        intent2.setPackage(context.getPackageName());
        this.mSessionStatusPendingIntent = PendingIntent.getBroadcast(context, 0, intent2, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Intent intent3 = new Intent(ActionReceiver.ACTION_MESSAGE_RECEIVED);
        intent3.setPackage(context.getPackageName());
        this.mMessagePendingIntent = PendingIntent.getBroadcast(context, 0, intent3, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        detectFeatures();
    }

    public static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        bundle.size();
        return bundle.toString();
    }

    private void detectFeatures() {
        boolean z2 = false;
        boolean z3 = routeSupportsAction(MediaControlIntent.ACTION_PLAY) && routeSupportsAction(MediaControlIntent.ACTION_SEEK) && routeSupportsAction(MediaControlIntent.ACTION_GET_STATUS) && routeSupportsAction(MediaControlIntent.ACTION_PAUSE) && routeSupportsAction(MediaControlIntent.ACTION_RESUME) && routeSupportsAction(MediaControlIntent.ACTION_STOP);
        this.mRouteSupportsRemotePlayback = z3;
        this.mRouteSupportsQueuing = z3 && routeSupportsAction(MediaControlIntent.ACTION_ENQUEUE) && routeSupportsAction(MediaControlIntent.ACTION_REMOVE);
        if (this.mRouteSupportsRemotePlayback && routeSupportsAction(MediaControlIntent.ACTION_START_SESSION) && routeSupportsAction(MediaControlIntent.ACTION_GET_SESSION_STATUS) && routeSupportsAction(MediaControlIntent.ACTION_END_SESSION)) {
            z2 = true;
        }
        this.mRouteSupportsSessionManagement = z2;
        this.mRouteSupportsMessaging = doesRouteSupportMessaging();
    }

    private boolean doesRouteSupportMessaging() {
        Iterator<IntentFilter> it = this.mRoute.getControlFilters().iterator();
        while (it.hasNext()) {
            if (it.next().hasAction(MediaControlIntent.ACTION_SEND_MESSAGE)) {
                return true;
            }
        }
        return false;
    }

    public static String inferMissingResult(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        if (str == null || str.equals(str2)) {
            return str2;
        }
        return null;
    }

    private static void logRequest(Intent intent) {
    }

    private void performItemAction(final Intent intent, final String str, final String str2, Bundle bundle, final ItemActionCallback itemActionCallback) {
        intent.addCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK);
        if (str != null) {
            intent.putExtra(MediaControlIntent.EXTRA_SESSION_ID, str);
        }
        if (str2 != null) {
            intent.putExtra(MediaControlIntent.EXTRA_ITEM_ID, str2);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.RemotePlaybackClient.1
            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onError(String str3, Bundle bundle2) {
                RemotePlaybackClient.this.handleError(intent, itemActionCallback, str3, bundle2);
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onResult(Bundle bundle2) {
                if (bundle2 != null) {
                    String strInferMissingResult = RemotePlaybackClient.inferMissingResult(str, bundle2.getString(MediaControlIntent.EXTRA_SESSION_ID));
                    MediaSessionStatus mediaSessionStatusFromBundle = MediaSessionStatus.fromBundle(bundle2.getBundle(MediaControlIntent.EXTRA_SESSION_STATUS));
                    String strInferMissingResult2 = RemotePlaybackClient.inferMissingResult(str2, bundle2.getString(MediaControlIntent.EXTRA_ITEM_ID));
                    MediaItemStatus mediaItemStatusFromBundle = MediaItemStatus.fromBundle(bundle2.getBundle(MediaControlIntent.EXTRA_ITEM_STATUS));
                    RemotePlaybackClient.this.adoptSession(strInferMissingResult);
                    if (strInferMissingResult != null && strInferMissingResult2 != null && mediaItemStatusFromBundle != null) {
                        if (RemotePlaybackClient.DEBUG) {
                            Log.d(RemotePlaybackClient.TAG, "Received result from " + intent.getAction() + ": data=" + RemotePlaybackClient.bundleToString(bundle2) + ", sessionId=" + strInferMissingResult + ", sessionStatus=" + mediaSessionStatusFromBundle + ", itemId=" + strInferMissingResult2 + ", itemStatus=" + mediaItemStatusFromBundle);
                        }
                        itemActionCallback.onResult(bundle2, strInferMissingResult, mediaSessionStatusFromBundle, strInferMissingResult2, mediaItemStatusFromBundle);
                        return;
                    }
                }
                RemotePlaybackClient.this.handleInvalidResult(intent, itemActionCallback, bundle2);
            }
        });
    }

    private void performSessionAction(final Intent intent, final String str, Bundle bundle, final SessionActionCallback sessionActionCallback) {
        intent.addCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK);
        if (str != null) {
            intent.putExtra(MediaControlIntent.EXTRA_SESSION_ID, str);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        logRequest(intent);
        this.mRoute.sendControlRequest(intent, new MediaRouter.ControlRequestCallback() { // from class: androidx.mediarouter.media.RemotePlaybackClient.2
            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onError(String str2, Bundle bundle2) {
                RemotePlaybackClient.this.handleError(intent, sessionActionCallback, str2, bundle2);
            }

            @Override // androidx.mediarouter.media.MediaRouter.ControlRequestCallback
            public void onResult(Bundle bundle2) {
                boolean zEquals;
                boolean zEquals2;
                if (bundle2 != null) {
                    String strInferMissingResult = RemotePlaybackClient.inferMissingResult(str, bundle2.getString(MediaControlIntent.EXTRA_SESSION_ID));
                    MediaSessionStatus mediaSessionStatusFromBundle = MediaSessionStatus.fromBundle(bundle2.getBundle(MediaControlIntent.EXTRA_SESSION_STATUS));
                    RemotePlaybackClient.this.adoptSession(strInferMissingResult);
                    if (strInferMissingResult != null) {
                        if (RemotePlaybackClient.DEBUG) {
                            Log.d(RemotePlaybackClient.TAG, "Received result from " + intent.getAction() + ": data=" + RemotePlaybackClient.bundleToString(bundle2) + ", sessionId=" + strInferMissingResult + ", sessionStatus=" + mediaSessionStatusFromBundle);
                        }
                        try {
                            sessionActionCallback.onResult(bundle2, strInferMissingResult, mediaSessionStatusFromBundle);
                            if (zEquals) {
                                if (zEquals2) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                            return;
                        } finally {
                            if (intent.getAction().equals(MediaControlIntent.ACTION_END_SESSION) && strInferMissingResult.equals(RemotePlaybackClient.this.mSessionId)) {
                                RemotePlaybackClient.this.setSessionId(null);
                            }
                        }
                    }
                }
                RemotePlaybackClient.this.handleInvalidResult(intent, sessionActionCallback, bundle2);
            }
        });
    }

    private void playOrEnqueue(Uri uri, String str, Bundle bundle, long j2, Bundle bundle2, ItemActionCallback itemActionCallback, String str2) {
        if (uri == null) {
            throw new IllegalArgumentException("contentUri must not be null");
        }
        throwIfRemotePlaybackNotSupported();
        if (str2.equals(MediaControlIntent.ACTION_ENQUEUE)) {
            throwIfQueuingNotSupported();
        }
        Intent intent = new Intent(str2);
        intent.setDataAndType(uri, str);
        intent.putExtra(MediaControlIntent.EXTRA_ITEM_STATUS_UPDATE_RECEIVER, this.mItemStatusPendingIntent);
        if (bundle != null) {
            intent.putExtra(MediaControlIntent.EXTRA_ITEM_METADATA, bundle);
        }
        if (j2 != 0) {
            intent.putExtra(MediaControlIntent.EXTRA_ITEM_CONTENT_POSITION, j2);
        }
        performItemAction(intent, this.mSessionId, null, bundle2, itemActionCallback);
    }

    private boolean routeSupportsAction(String str) {
        return this.mRoute.supportsControlAction(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK, str);
    }

    private void throwIfMessageNotSupported() {
        if (!this.mRouteSupportsMessaging) {
            throw new UnsupportedOperationException("The route does not support message.");
        }
    }

    private void throwIfNoCurrentSession() {
        if (this.mSessionId == null) {
            throw new IllegalStateException("There is no current session.");
        }
    }

    private void throwIfQueuingNotSupported() {
        if (!this.mRouteSupportsQueuing) {
            throw new UnsupportedOperationException("The route does not support queuing.");
        }
    }

    private void throwIfRemotePlaybackNotSupported() {
        if (!this.mRouteSupportsRemotePlayback) {
            throw new UnsupportedOperationException("The route does not support remote playback.");
        }
    }

    private void throwIfSessionManagementNotSupported() {
        if (!this.mRouteSupportsSessionManagement) {
            throw new UnsupportedOperationException("The route does not support session management.");
        }
    }

    public void adoptSession(String str) {
        if (str != null) {
            setSessionId(str);
        }
    }

    public void endSession(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_END_SESSION), this.mSessionId, bundle, sessionActionCallback);
    }

    public void enqueue(@NonNull Uri uri, @Nullable String str, @Nullable Bundle bundle, long j2, @Nullable Bundle bundle2, @Nullable ItemActionCallback itemActionCallback) {
        playOrEnqueue(uri, str, bundle, j2, bundle2, itemActionCallback, MediaControlIntent.ACTION_ENQUEUE);
    }

    @Nullable
    public String getSessionId() {
        return this.mSessionId;
    }

    public void getSessionStatus(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfSessionManagementNotSupported();
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_GET_SESSION_STATUS), this.mSessionId, bundle, sessionActionCallback);
    }

    public void getStatus(@NonNull String str, @Nullable Bundle bundle, @Nullable ItemActionCallback itemActionCallback) {
        if (str == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        throwIfNoCurrentSession();
        performItemAction(new Intent(MediaControlIntent.ACTION_GET_STATUS), this.mSessionId, str, bundle, itemActionCallback);
    }

    public void handleError(Intent intent, ActionCallback actionCallback, String str, Bundle bundle) {
        actionCallback.onError(str, bundle != null ? bundle.getInt(MediaControlIntent.EXTRA_ERROR_CODE, 0) : 0, bundle);
    }

    public void handleInvalidResult(Intent intent, ActionCallback actionCallback, Bundle bundle) {
        Log.w(TAG, "Received invalid result data from " + intent.getAction() + ": data=" + bundleToString(bundle));
        actionCallback.onError(null, 0, bundle);
    }

    public boolean hasSession() {
        return this.mSessionId != null;
    }

    public boolean isMessagingSupported() {
        return this.mRouteSupportsMessaging;
    }

    public boolean isQueuingSupported() {
        return this.mRouteSupportsQueuing;
    }

    public boolean isRemotePlaybackSupported() {
        return this.mRouteSupportsRemotePlayback;
    }

    public boolean isSessionManagementSupported() {
        return this.mRouteSupportsSessionManagement;
    }

    public void pause(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_PAUSE), this.mSessionId, bundle, sessionActionCallback);
    }

    public void play(@NonNull Uri uri, @Nullable String str, @Nullable Bundle bundle, long j2, @Nullable Bundle bundle2, @Nullable ItemActionCallback itemActionCallback) {
        playOrEnqueue(uri, str, bundle, j2, bundle2, itemActionCallback, MediaControlIntent.ACTION_PLAY);
    }

    public void release() {
        this.mContext.unregisterReceiver(this.mActionReceiver);
    }

    public void remove(@NonNull String str, @Nullable Bundle bundle, @Nullable ItemActionCallback itemActionCallback) {
        if (str == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        throwIfQueuingNotSupported();
        throwIfNoCurrentSession();
        performItemAction(new Intent(MediaControlIntent.ACTION_REMOVE), this.mSessionId, str, bundle, itemActionCallback);
    }

    public void resume(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_RESUME), this.mSessionId, bundle, sessionActionCallback);
    }

    public void seek(@NonNull String str, long j2, @Nullable Bundle bundle, @Nullable ItemActionCallback itemActionCallback) {
        if (str == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        throwIfNoCurrentSession();
        Intent intent = new Intent(MediaControlIntent.ACTION_SEEK);
        intent.putExtra(MediaControlIntent.EXTRA_ITEM_CONTENT_POSITION, j2);
        performItemAction(intent, this.mSessionId, str, bundle, itemActionCallback);
    }

    public void sendMessage(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfNoCurrentSession();
        throwIfMessageNotSupported();
        performSessionAction(new Intent(MediaControlIntent.ACTION_SEND_MESSAGE), this.mSessionId, bundle, sessionActionCallback);
    }

    public void setOnMessageReceivedListener(@Nullable OnMessageReceivedListener onMessageReceivedListener) {
        this.mOnMessageReceivedListener = onMessageReceivedListener;
    }

    public void setSessionId(@Nullable String str) {
        if (ObjectsCompat.equals(this.mSessionId, str)) {
            return;
        }
        this.mSessionId = str;
        StatusCallback statusCallback = this.mStatusCallback;
        if (statusCallback != null) {
            statusCallback.onSessionChanged(str);
        }
    }

    public void setStatusCallback(@Nullable StatusCallback statusCallback) {
        this.mStatusCallback = statusCallback;
    }

    public void startSession(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfSessionManagementNotSupported();
        Intent intent = new Intent(MediaControlIntent.ACTION_START_SESSION);
        intent.putExtra(MediaControlIntent.EXTRA_SESSION_STATUS_UPDATE_RECEIVER, this.mSessionStatusPendingIntent);
        if (this.mRouteSupportsMessaging) {
            intent.putExtra(MediaControlIntent.EXTRA_MESSAGE_RECEIVER, this.mMessagePendingIntent);
        }
        performSessionAction(intent, null, bundle, sessionActionCallback);
    }

    public void stop(@Nullable Bundle bundle, @Nullable SessionActionCallback sessionActionCallback) {
        throwIfNoCurrentSession();
        performSessionAction(new Intent(MediaControlIntent.ACTION_STOP), this.mSessionId, bundle, sessionActionCallback);
    }
}
