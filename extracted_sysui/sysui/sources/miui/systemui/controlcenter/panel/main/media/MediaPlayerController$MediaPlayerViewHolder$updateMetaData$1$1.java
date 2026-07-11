package miui.systemui.controlcenter.panel.main.media;

import H0.k;
import H0.s;
import N0.l;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.MiPlayMediaPlayerAdapter;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.E;
import g1.M;
import kotlin.jvm.functions.Function2;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1", f = "MediaPlayerController.kt", l = {TypedValues.TransitionType.TYPE_FROM}, m = "invokeSuspend")
public final class MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1 extends l implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaPlayerController.MediaPlayerViewHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1(MediaPlayerController.MediaPlayerViewHolder mediaPlayerViewHolder, L0.d dVar) {
        super(2, dVar);
        this.this$0 = mediaPlayerViewHolder;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1 mediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1 = new MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1(this.this$0, dVar);
        mediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1.L$0 = obj;
        return mediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, L0.d dVar) {
        return ((MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            this.L$0 = (E) this.L$0;
            this.label = 1;
            if (M.b(1000L, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
        s sVar = null;
        if (value != null) {
            this.this$0.updateCover(MiPlayMediaPlayerAdapter.Companion.convert$default(MiPlayMediaPlayerAdapter.Companion, value, null, 1, null));
            sVar = s.f314a;
        }
        if (sVar == null) {
            MediaPlayerController.MediaPlayerViewHolder mediaPlayerViewHolder = this.this$0;
            mediaPlayerViewHolder.binding.cover.setImageResource(0);
            mediaPlayerViewHolder.hasCover = false;
        }
        return s.f314a;
    }
}
