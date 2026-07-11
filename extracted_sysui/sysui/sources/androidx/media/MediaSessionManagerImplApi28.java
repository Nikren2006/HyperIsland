package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.annotation.RequiresApi;
import androidx.media.MediaSessionManager;
import androidx.media.MediaSessionManagerImplBase;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(28)
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    android.media.session.MediaSessionManager mObject;

    public MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // androidx.media.MediaSessionManagerImplApi21, androidx.media.MediaSessionManagerImplBase, androidx.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return super.isTrustedForMediaControl(remoteUserInfoImpl);
    }

    @RequiresApi(28)
    public static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
        final MediaSessionManager.RemoteUserInfo mObject;

        public RemoteUserInfoImplApi28(String str, int i2, int i3) {
            super(str, i2, i3);
            this.mObject = new MediaSessionManager.RemoteUserInfo(str, i2, i3);
        }

        public static String getPackageName(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            return remoteUserInfo.getPackageName();
        }

        public RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            super(remoteUserInfo.getPackageName(), remoteUserInfo.getPid(), remoteUserInfo.getUid());
            this.mObject = remoteUserInfo;
        }
    }
}
