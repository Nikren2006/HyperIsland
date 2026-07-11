package androidx.activity;

import H0.s;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.RequiresApi;
import j1.AbstractC0420h;
import j1.InterfaceC0419g;

/* JADX INFO: loaded from: classes.dex */
public final class PipHintTrackerKt {
    @RequiresApi(26)
    public static final Object trackPipAnimationHintView(final Activity activity, View view, L0.d dVar) {
        Object objCollect = AbstractC0420h.e(new PipHintTrackerKt$trackPipAnimationHintView$flow$1(view, null)).collect(new InterfaceC0419g() { // from class: androidx.activity.PipHintTrackerKt.trackPipAnimationHintView.2
            @Override // j1.InterfaceC0419g
            public final Object emit(Rect rect, L0.d dVar2) {
                Api26Impl.INSTANCE.setPipParamsSourceRectHint(activity, rect);
                return s.f314a;
            }
        }, dVar);
        return objCollect == M0.c.c() ? objCollect : s.f314a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect trackPipAnimationHintView$positionInWindow(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }
}
