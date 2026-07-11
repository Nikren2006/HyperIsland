package com.android.systemui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.widget.RelativeSeekBarInjector;
import miuix.androidbasewidget.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayVolumeBar extends SeekBar {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_PROGRESS = 1000;
    private final H0.d mInjector$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MiPlayVolumeBar(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    private final RelativeSeekBarInjector getMInjector() {
        return (RelativeSeekBarInjector) this.mInjector$delegate.getValue();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // miuix.androidbasewidget.widget.SeekBar, android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        getMInjector().transformTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MiPlayVolumeBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ MiPlayVolumeBar(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlayVolumeBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.mInjector$delegate = H0.e.b(new MiPlayVolumeBar$mInjector$2(this));
        setMax(1000);
    }
}
