package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class ClickableRecyclerView extends RecyclerView {
    private final GestureDetector gestureDetector;
    private final ClickableRecyclerView$gestureListener$1 gestureListener;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ClickableRecyclerView(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    @Override // androidx.recyclerview.widget.SpringRecyclerView, androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ClickableRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ ClickableRecyclerView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.GestureDetector$OnGestureListener, miui.systemui.controlcenter.widget.ClickableRecyclerView$gestureListener$1] */
    public ClickableRecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        ?? r3 = new GestureDetector.OnGestureListener() { // from class: miui.systemui.controlcenter.widget.ClickableRecyclerView$gestureListener$1
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                if (this.this$0.getScrollState() == 0) {
                    this.this$0.performLongClick();
                }
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                if (this.this$0.getScrollState() != 0) {
                    return false;
                }
                this.this$0.performClick();
                return false;
            }
        };
        this.gestureListener = r3;
        this.gestureDetector = new GestureDetector(context, (GestureDetector.OnGestureListener) r3);
    }
}
