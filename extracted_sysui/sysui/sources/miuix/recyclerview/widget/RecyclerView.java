package miuix.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.SpringRecyclerView;
import miuix.recyclerview.tool.GetSpeedForDynamicRefreshRate;

/* JADX INFO: loaded from: classes5.dex */
public class RecyclerView extends SpringRecyclerView {
    private static final int MIN_FLING_VELOCITY = 300;
    private final GetSpeedForDynamicRefreshRate mGetSpeedForDynamicRefreshRate;

    public RecyclerView(@NonNull Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        GetSpeedForDynamicRefreshRate getSpeedForDynamicRefreshRate = this.mGetSpeedForDynamicRefreshRate;
        if (getSpeedForDynamicRefreshRate != null) {
            getSpeedForDynamicRefreshRate.touchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        if (Math.abs(i2) < 300) {
            i2 = 0;
        }
        if (Math.abs(i3) < 300) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        return super.fling(i2, i3);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z2, int i2, @Nullable Rect rect) {
        super.onFocusChanged(z2, i2, rect);
        GetSpeedForDynamicRefreshRate getSpeedForDynamicRefreshRate = this.mGetSpeedForDynamicRefreshRate;
        if (getSpeedForDynamicRefreshRate != null) {
            getSpeedForDynamicRefreshRate.onFocusChange(z2);
        }
    }

    @Override // androidx.recyclerview.widget.SpringRecyclerView, androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i2) {
        super.onScrollStateChanged(i2);
        GetSpeedForDynamicRefreshRate getSpeedForDynamicRefreshRate = this.mGetSpeedForDynamicRefreshRate;
        if (getSpeedForDynamicRefreshRate != null) {
            getSpeedForDynamicRefreshRate.scrollState(this, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int i2, int i3) {
        GetSpeedForDynamicRefreshRate getSpeedForDynamicRefreshRate = this.mGetSpeedForDynamicRefreshRate;
        if (getSpeedForDynamicRefreshRate != null) {
            getSpeedForDynamicRefreshRate.calculateSpeed(i2, i3, getDragFlingVelocityX(), getDragFlingVelocityY());
        }
        super.onScrolled(i2, i3);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setItemAnimator(new MiuiDefaultItemAnimator());
        this.mGetSpeedForDynamicRefreshRate = new GetSpeedForDynamicRefreshRate(this);
    }
}
