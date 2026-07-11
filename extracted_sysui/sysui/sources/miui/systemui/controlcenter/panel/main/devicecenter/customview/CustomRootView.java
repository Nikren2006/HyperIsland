package miui.systemui.controlcenter.panel.main.devicecenter.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class CustomRootView extends FrameLayout {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CustomRootView";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomRootView(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    private final String printMeasureMode(int i2) {
        return i2 != Integer.MIN_VALUE ? i2 != 0 ? i2 != 1073741824 ? "unknown" : "exactly" : "unspecified" : "at_most";
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        Log.i(TAG, "onMeasureWidthMode " + printMeasureMode(mode) + "  onMeasureHeightMode " + printMeasureMode(mode2));
        Log.i(TAG, "onMeasuredWidth " + View.MeasureSpec.getSize(i2) + "  onMeasuredHeight " + View.MeasureSpec.getSize(i3));
        if (mode == Integer.MIN_VALUE) {
            setMeasuredDimension(View.MeasureSpec.getSize(i2) / 4, size2);
        } else {
            setMeasuredDimension(size, size2);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomRootView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ CustomRootView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomRootView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }
}
