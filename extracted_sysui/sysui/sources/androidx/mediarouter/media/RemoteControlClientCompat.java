package androidx.mediarouter.media;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.MediaRouterJellybean;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
abstract class RemoteControlClientCompat {
    protected final Context mContext;
    protected final Object mRcc;
    protected VolumeCallback mVolumeCallback;

    @RequiresApi(16)
    public static class JellybeanImpl extends RemoteControlClientCompat {
        private boolean mRegistered;
        private final Object mRouterObj;
        private final Object mUserRouteCategoryObj;
        private final Object mUserRouteObj;

        public static final class VolumeCallbackWrapper implements MediaRouterJellybean.VolumeCallback {
            private final WeakReference<JellybeanImpl> mImplWeak;

            public VolumeCallbackWrapper(JellybeanImpl jellybeanImpl) {
                this.mImplWeak = new WeakReference<>(jellybeanImpl);
            }

            @Override // androidx.mediarouter.media.MediaRouterJellybean.VolumeCallback
            public void onVolumeSetRequest(@NonNull Object obj, int i2) {
                VolumeCallback volumeCallback;
                JellybeanImpl jellybeanImpl = this.mImplWeak.get();
                if (jellybeanImpl == null || (volumeCallback = jellybeanImpl.mVolumeCallback) == null) {
                    return;
                }
                volumeCallback.onVolumeSetRequest(i2);
            }

            @Override // androidx.mediarouter.media.MediaRouterJellybean.VolumeCallback
            public void onVolumeUpdateRequest(@NonNull Object obj, int i2) {
                VolumeCallback volumeCallback;
                JellybeanImpl jellybeanImpl = this.mImplWeak.get();
                if (jellybeanImpl == null || (volumeCallback = jellybeanImpl.mVolumeCallback) == null) {
                    return;
                }
                volumeCallback.onVolumeUpdateRequest(i2);
            }
        }

        public JellybeanImpl(Context context, Object obj) {
            super(context, obj);
            Object mediaRouter = MediaRouterJellybean.getMediaRouter(context);
            this.mRouterObj = mediaRouter;
            Object objCreateRouteCategory = MediaRouterJellybean.createRouteCategory(mediaRouter, "", false);
            this.mUserRouteCategoryObj = objCreateRouteCategory;
            this.mUserRouteObj = MediaRouterJellybean.createUserRoute(mediaRouter, objCreateRouteCategory);
        }

        @Override // androidx.mediarouter.media.RemoteControlClientCompat
        public void setPlaybackInfo(PlaybackInfo playbackInfo) {
            MediaRouterJellybean.UserRouteInfo.setVolume(this.mUserRouteObj, playbackInfo.volume);
            MediaRouterJellybean.UserRouteInfo.setVolumeMax(this.mUserRouteObj, playbackInfo.volumeMax);
            MediaRouterJellybean.UserRouteInfo.setVolumeHandling(this.mUserRouteObj, playbackInfo.volumeHandling);
            MediaRouterJellybean.UserRouteInfo.setPlaybackStream(this.mUserRouteObj, playbackInfo.playbackStream);
            MediaRouterJellybean.UserRouteInfo.setPlaybackType(this.mUserRouteObj, playbackInfo.playbackType);
            if (this.mRegistered) {
                return;
            }
            this.mRegistered = true;
            MediaRouterJellybean.UserRouteInfo.setVolumeCallback(this.mUserRouteObj, MediaRouterJellybean.createVolumeCallback(new VolumeCallbackWrapper(this)));
            MediaRouterJellybean.UserRouteInfo.setRemoteControlClient(this.mUserRouteObj, this.mRcc);
        }
    }

    public static class LegacyImpl extends RemoteControlClientCompat {
        public LegacyImpl(Context context, Object obj) {
            super(context, obj);
        }
    }

    public static final class PlaybackInfo {
        public int volume;

        @Nullable
        public String volumeControlId;
        public int volumeMax;
        public int volumeHandling = 0;
        public int playbackStream = 3;
        public int playbackType = 1;
    }

    public interface VolumeCallback {
        void onVolumeSetRequest(int i2);

        void onVolumeUpdateRequest(int i2);
    }

    public RemoteControlClientCompat(Context context, Object obj) {
        this.mContext = context;
        this.mRcc = obj;
    }

    public static RemoteControlClientCompat obtain(Context context, Object obj) {
        return new JellybeanImpl(context, obj);
    }

    public Object getRemoteControlClient() {
        return this.mRcc;
    }

    public void setPlaybackInfo(PlaybackInfo playbackInfo) {
    }

    public void setVolumeCallback(VolumeCallback volumeCallback) {
        this.mVolumeCallback = volumeCallback;
    }
}
