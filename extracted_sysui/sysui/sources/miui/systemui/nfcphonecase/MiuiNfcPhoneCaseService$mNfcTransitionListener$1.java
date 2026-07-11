package miui.systemui.nfcphonecase;

import android.util.Log;
import java.util.Collection;
import java.util.function.Consumer;
import kotlin.jvm.internal.n;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$mNfcTransitionListener$1 extends TransitionListener {
    final /* synthetic */ MiuiNfcPhoneCaseService this$0;

    public MiuiNfcPhoneCaseService$mNfcTransitionListener$1(MiuiNfcPhoneCaseService miuiNfcPhoneCaseService) {
        this.this$0 = miuiNfcPhoneCaseService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onUpdate$lambda$0(MiuiNfcPhoneCaseService this$0, UpdateInfo updateInfo) {
        n.g(this$0, "this$0");
        n.g(updateInfo, "updateInfo");
        String name = updateInfo.property.getName();
        float floatValue = updateInfo.getFloatValue();
        Integer num = (Integer) this$0.mPositionMap.get(name);
        if (num != null) {
            this$0.getMCurState()[num.intValue()] = floatValue;
        }
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onBegin(Object obj) {
        super.onBegin(obj);
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onCancel(Object obj) {
        super.onCancel(obj);
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onComplete(Object obj) {
        super.onComplete(obj);
        if ((n.c("ANIM_STATE_HIDE", obj) ? this : null) != null) {
            MiuiNfcPhoneCaseService miuiNfcPhoneCaseService = this.this$0;
            Log.i(MiuiNfcPhoneCaseService.TAG, "Hide anim complete!");
            miuiNfcPhoneCaseService.stopSelf();
        }
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onUpdate(Object toTag, Collection<? extends UpdateInfo> updateList) {
        n.g(toTag, "toTag");
        n.g(updateList, "updateList");
        super.onUpdate(toTag, updateList);
        final MiuiNfcPhoneCaseService miuiNfcPhoneCaseService = this.this$0;
        updateList.forEach(new Consumer() { // from class: miui.systemui.nfcphonecase.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MiuiNfcPhoneCaseService$mNfcTransitionListener$1.onUpdate$lambda$0(miuiNfcPhoneCaseService, (UpdateInfo) obj);
            }
        });
        this.this$0.updateSurface();
    }
}
