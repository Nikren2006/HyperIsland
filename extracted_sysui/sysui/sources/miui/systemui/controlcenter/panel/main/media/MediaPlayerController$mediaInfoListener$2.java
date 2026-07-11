package miui.systemui.controlcenter.panel.main.media;

import com.android.systemui.plugins.qs.DetailAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;
import miui.systemui.controlcenter.media.MediaPlayerIconsInfo;
import miui.systemui.controlcenter.media.MediaPlayerMetaData;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;
import miui.systemui.controlcenter.panel.secondary.MediaFromView;
import miui.systemui.controlcenter.panel.secondary.MediaPanelParams;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerController$mediaInfoListener$2 extends o implements Function0 {
    final /* synthetic */ MediaPlayerController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerController$mediaInfoListener$2(MediaPlayerController mediaPlayerController) {
        super(0);
        this.this$0 = mediaPlayerController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.controlcenter.panel.main.media.MediaPlayerController$mediaInfoListener$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MediaPlayerController mediaPlayerController = this.this$0;
        return new MediaPlayerAdapter.MediaInfoListener() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$mediaInfoListener$2.1
            @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter.MediaInfoListener
            public void disableMediaController() {
                MediaPlayerController.MediaPlayerViewHolder viewHolder = mediaPlayerController.getViewHolder();
                if (viewHolder != null) {
                    viewHolder.disableMediaController();
                }
            }

            @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter.MediaInfoListener
            public void enableMediaController() {
                MediaPlayerController.MediaPlayerViewHolder viewHolder = mediaPlayerController.getViewHolder();
                if (viewHolder != null) {
                    viewHolder.enableMediaController();
                }
            }

            @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter.MediaInfoListener
            public void showDetail(DetailAdapter adapter) {
                n.g(adapter, "adapter");
                SecondaryPanelRouter secondaryPanelRouter = (SecondaryPanelRouter) mediaPlayerController.secondaryPanelRouter.get();
                Object holder = mediaPlayerController.getHolder();
                secondaryPanelRouter.routeToMediaPanel(new MediaPanelParams(holder instanceof MediaFromView ? (MediaFromView) holder : null));
            }

            @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter.MediaInfoListener
            public void updateIconsInfo(MediaPlayerIconsInfo iconsInfo) {
                n.g(iconsInfo, "iconsInfo");
                MediaPlayerController.MediaPlayerViewHolder viewHolder = mediaPlayerController.getViewHolder();
                if (viewHolder != null) {
                    MediaPlayerController.MediaPlayerViewHolder.updateIconsInfo$default(viewHolder, iconsInfo, false, 2, null);
                }
            }

            @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter.MediaInfoListener
            public void updateMetaData(MediaPlayerMetaData mediaPlayerMetaData) {
                MediaPlayerController.MediaPlayerViewHolder viewHolder = mediaPlayerController.getViewHolder();
                if (viewHolder != null) {
                    viewHolder.updateMetaData(mediaPlayerMetaData);
                }
            }
        };
    }
}
