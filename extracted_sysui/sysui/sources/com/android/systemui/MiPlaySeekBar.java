package com.android.systemui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.widget.RelativeSeekBarInjector;
import miuix.miuixbasewidget.widget.HyperProgressSeekBar;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlaySeekBar extends HyperProgressSeekBar {
    private final H0.d mInjector$delegate;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MiPlaySeekBar(Context context) {
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

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isEnabled()) {
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.setScrollable(true);
            }
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.addAction(4096);
            }
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.addAction(8192);
            }
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
            }
            if (accessibilityNodeInfo == null) {
                return;
            }
            accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, 0.0f, getMax(), getProgress()));
        }
    }

    @Override // miuix.miuixbasewidget.widget.HyperProgressSeekBar, android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        getMInjector().transformTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (i2 != 4096 && i2 != 8192) {
            return super.performAccessibilityAction(i2, bundle);
        }
        if (!isEnabled()) {
            return false;
        }
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.seekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
            setProgress(i2 == 4096 ? getProgress() + 5 : getProgress() - 5);
            if (getProgress() > getMax()) {
                setProgress(getMax());
            } else if (getProgress() < 0) {
                setProgress(0);
            }
            onSeekBarChangeListener.onProgressChanged(this, getProgress(), true);
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
        return true;
    }

    @Override // miuix.miuixbasewidget.widget.HyperProgressSeekBar, android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        super.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.seekBarChangeListener = onSeekBarChangeListener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MiPlaySeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ MiPlaySeekBar(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlaySeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.mInjector$delegate = H0.e.b(new MiPlaySeekBar$mInjector$2(this));
    }
}
