package com.android.systemui.miui.volume;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeColumn$onTouchListener$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ VolumeColumn this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeColumn$onTouchListener$2(VolumeColumn volumeColumn) {
        super(0);
        this.this$0 = volumeColumn;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.miui.volume.VolumeColumn$onTouchListener$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final VolumeColumn volumeColumn = this.this$0;
        return new View.OnTouchListener() { // from class: com.android.systemui.miui.volume.VolumeColumn$onTouchListener$2.1
            private boolean mDragging;
            private final Rect mSliderHitRect = new Rect();

            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View v2, MotionEvent event) {
                kotlin.jvm.internal.n.g(v2, "v");
                kotlin.jvm.internal.n.g(event, "event");
                volumeColumn.getSlider().getHitRect(this.mSliderHitRect);
                if (!this.mDragging && event.getActionMasked() == 0 && event.getY() < this.mSliderHitRect.top) {
                    this.mDragging = true;
                }
                if (!this.mDragging) {
                    return false;
                }
                Rect rect = this.mSliderHitRect;
                event.offsetLocation(-rect.left, -rect.top);
                volumeColumn.getSlider().dispatchTouchEvent(event);
                if (event.getActionMasked() == 1 || event.getActionMasked() == 3) {
                    this.mDragging = false;
                }
                return true;
            }
        };
    }
}
