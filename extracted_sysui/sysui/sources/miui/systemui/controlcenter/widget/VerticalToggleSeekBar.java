package miui.systemui.controlcenter.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.GestureDispatcher;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public final class VerticalToggleSeekBar extends SeekBar implements GestureDispatcher.GestureAcceptor {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VerticalToggleSeekBar";
    private String accessibilityLabel;
    private GestureDispatcher.GestureHelper gestureHelper;
    private boolean intercepted;
    private final VerticalToggleSeekBar$seekBarChangeDelegate$1 seekBarChangeDelegate;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener;
    private boolean trackingStarted;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.controlcenter.widget.VerticalToggleSeekBar$seekBarChangeDelegate$1] */
    public VerticalToggleSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.seekBarChangeDelegate = new SeekBar.OnSeekBarChangeListener() { // from class: miui.systemui.controlcenter.widget.VerticalToggleSeekBar$seekBarChangeDelegate$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
                Log.d("VerticalToggleSeekBar", "onProgressChanged " + i2 + " " + z2);
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.this$0.seekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onProgressChanged(seekBar, i2, z2);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (!this.this$0.getIntercepted() || this.this$0.getTrackingStarted()) {
                    return;
                }
                Log.w("VerticalToggleSeekBar", "onStartTrackingTouch");
                this.this$0.setTrackingStarted(true);
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.this$0.seekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("VerticalToggleSeekBar", "onStopTrackingTouch");
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.this$0.seekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
                this.this$0.setIntercepted(false);
                this.this$0.setTrackingStarted(false);
            }
        };
    }

    @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureAcceptor
    public GestureDispatcher.GestureHelper createGestureHelper(GestureDispatcher gestureDispatcher) {
        n.g(gestureDispatcher, "gestureDispatcher");
        GestureDispatcher.GestureHelper gestureHelper = new GestureDispatcher.GestureHelper(this, gestureDispatcher, Boolean.TRUE);
        this.gestureHelper = gestureHelper;
        n.d(gestureHelper);
        return gestureHelper;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0017  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L17
            miui.systemui.controlcenter.windowview.GestureDispatcher$GestureHelper r0 = r3.gestureHelper
            if (r0 == 0) goto Lf
            boolean r0 = r0.onInterceptTouchEvent(r4)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L10
        Lf:
            r0 = 0
        L10:
            if (r0 == 0) goto L17
            boolean r0 = r0.booleanValue()
            goto L18
        L17:
            r0 = 0
        L18:
            r3.intercepted = r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "dispatchTouchEvent "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "VerticalToggleSeekBar"
            android.util.Log.d(r1, r0)
            boolean r0 = r3.intercepted
            if (r0 == 0) goto L47
            boolean r0 = r3.trackingStarted
            if (r0 != 0) goto L47
            java.lang.String r0 = "onStartTrackingTouch"
            android.util.Log.w(r1, r0)
            r0 = 1
            r3.trackingStarted = r0
            android.widget.SeekBar$OnSeekBarChangeListener r0 = r3.seekBarChangeListener
            if (r0 == 0) goto L47
            r0.onStartTrackingTouch(r3)
        L47:
            boolean r3 = super.dispatchTouchEvent(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.widget.VerticalToggleSeekBar.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public final String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    public final boolean getIntercepted() {
        return this.intercepted;
    }

    public final boolean getTrackingStarted() {
        return this.trackingStarted;
    }

    public void internalSetPadding(int i2, int i3, int i4, int i5) {
    }

    public boolean isInScrollingContainer() {
        return false;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        super.setOnSeekBarChangeListener(this.seekBarChangeDelegate);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = this.accessibilityLabel;
        if (str == null || accessibilityNodeInfo == null) {
            return;
        }
        accessibilityNodeInfo.setText(str);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i3, i2);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(TAG, "onTouch " + (motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null) + " " + (this.gestureHelper != null));
        if (!isEnabled()) {
            setEnabled(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setAccessibilityLabel(String str) {
        this.accessibilityLabel = str;
    }

    public final void setIntercepted(boolean z2) {
        this.intercepted = z2;
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.seekBarChangeListener = onSeekBarChangeListener;
    }

    public final void setTrackingStarted(boolean z2) {
        this.trackingStarted = z2;
    }
}
