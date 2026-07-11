package miui.systemui.flashlight.view;

import H0.d;
import H0.e;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class MiBottomDrawUpView extends View {
    private float actionStartPositionY;
    private long actionStartTime;
    private final d finishBarHeight$delegate;
    private Function1 onBottomUpListener;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MiBottomDrawUpView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        n.g(context, "context");
    }

    private final double getFinishBarHeight() {
        return ((Number) this.finishBarHeight$delegate.getValue()).doubleValue();
    }

    public final Function1 getOnBottomUpListener() {
        return this.onBottomUpListener;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(View.MeasureSpec.getSize(i2), (int) getFinishBarHeight());
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Function1 function1;
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            if (motionEvent.getY() > ((double) getHeight()) - getFinishBarHeight()) {
                this.actionStartPositionY = motionEvent.getY();
                this.actionStartTime = System.currentTimeMillis();
                return true;
            }
        } else if (numValueOf != null && numValueOf.intValue() == 2) {
            if (this.actionStartPositionY != -1.0f) {
                return true;
            }
        } else if (numValueOf != null && numValueOf.intValue() == 1) {
            float f2 = this.actionStartPositionY;
            if (f2 != -1.0f) {
                float y2 = f2 - motionEvent.getY();
                long jCurrentTimeMillis = System.currentTimeMillis() - this.actionStartTime;
                this.actionStartPositionY = -1.0f;
                this.actionStartTime = 0L;
                double d2 = y2;
                if ((d2 > ((double) 3) * getFinishBarHeight() || (d2 > ((double) 2) * getFinishBarHeight() && jCurrentTimeMillis < 500)) && (function1 = this.onBottomUpListener) != null) {
                    function1.invoke(this);
                }
                return true;
            }
        } else {
            this.actionStartPositionY = -1.0f;
            this.actionStartTime = 0L;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnBottomUpListener(Function1 function1) {
        this.onBottomUpListener = function1;
    }

    public /* synthetic */ MiBottomDrawUpView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiBottomDrawUpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.finishBarHeight$delegate = e.b(new MiBottomDrawUpView$finishBarHeight$2(context));
        this.actionStartPositionY = -1.0f;
    }
}
