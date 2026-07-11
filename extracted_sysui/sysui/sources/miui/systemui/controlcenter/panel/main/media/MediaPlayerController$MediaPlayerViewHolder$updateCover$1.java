package miui.systemui.controlcenter.panel.main.media;

import H0.k;
import H0.s;
import N0.l;
import android.graphics.Bitmap;
import com.android.systemui.MiPlayDetailViewModel;
import g1.E;
import g1.M;
import kotlin.jvm.functions.Function2;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;
import miuix.transition.FlipAnimation;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$updateCover$1", f = "MediaPlayerController.kt", l = {764}, m = "invokeSuspend")
public final class MediaPlayerController$MediaPlayerViewHolder$updateCover$1 extends l implements Function2 {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ boolean $isSameMedia;
    final /* synthetic */ String $metaDataId;
    int label;
    final /* synthetic */ MediaPlayerController.MediaPlayerViewHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerController$MediaPlayerViewHolder$updateCover$1(MediaPlayerController.MediaPlayerViewHolder mediaPlayerViewHolder, boolean z2, Bitmap bitmap, String str, L0.d dVar) {
        super(2, dVar);
        this.this$0 = mediaPlayerViewHolder;
        this.$isSameMedia = z2;
        this.$bitmap = bitmap;
        this.$metaDataId = str;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new MediaPlayerController$MediaPlayerViewHolder$updateCover$1(this.this$0, this.$isSameMedia, this.$bitmap, this.$metaDataId, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, L0.d dVar) {
        return ((MediaPlayerController$MediaPlayerViewHolder$updateCover$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            this.label = 1;
            if (M.b(500L, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        if (!FlipAnimation.isInFlipAnimation(this.this$0.binding.cover)) {
            if (this.$isSameMedia) {
                MediaPlayerController.MediaPlayerViewHolder.setCover$default(this.this$0, this.$bitmap, false, 2, null);
            } else {
                this.this$0.performFlip(this.$bitmap);
            }
            MiPlayDetailViewModel.INSTANCE.setMMediaIDForCardCover(this.$metaDataId);
        }
        return s.f314a;
    }
}
