package miui.systemui.controlcenter.media;

import com.android.systemui.plugins.qs.DetailAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface MediaPlayerAdapter {

    public interface MediaInfoListener {
        void disableMediaController();

        void enableMediaController();

        void showDetail(DetailAdapter detailAdapter);

        void updateIconsInfo(MediaPlayerIconsInfo mediaPlayerIconsInfo);

        void updateMetaData(MediaPlayerMetaData mediaPlayerMetaData);
    }

    void onDeviceClicked();

    void onNextClicked();

    void onPlayClicked();

    void onPlayerClicked();

    void onPrevClicked();

    void setListening(boolean z2);

    void setMediaInfoListener(MediaInfoListener mediaInfoListener);
}
