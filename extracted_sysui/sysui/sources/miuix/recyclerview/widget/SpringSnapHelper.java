package miuix.recyclerview.widget;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import miuix.animation.Folme;
import miuix.animation.controller.FolmeState;
import miuix.animation.property.FloatProperty;
import miuix.reflect.Reflects;
import miuix.spring.view.SpringHelper;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SpringSnapHelper extends RecyclerView.OnFlingListener {
    public static final int SNAP_TO_CENTER = 1;
    public static final int SNAP_TO_END = 2;
    public static final int SNAP_TO_START = 0;
    protected int mCurrentPosition;
    protected FolmeState mFolmeState;
    protected int mLastPosition;
    protected int mMax;
    protected int mMin;
    protected boolean mOutBounds;
    protected FloatProperty mProperty;
    protected RecyclerView mRecyclerView;
    private RecyclerView.OnScrollListener mScrollListener;
    protected SpringHelper mSpringHelper;
    protected final float mMinVisibleChange = 0.2f;
    protected float mFriction = 0.61904764f;
    protected float mDamping = 1.0f;
    protected float mResponse = 0.4f;
    protected float mVelocityThreshold = 1000.0f;
    protected int mItemWidth = Integer.MAX_VALUE;
    protected int mItemHeight = Integer.MAX_VALUE;
    protected Rect mBounds = new Rect();
    protected int mSnapPreference = 0;

    @SuppressLint({"ClickableViewAccessibility"})
    public SpringSnapHelper() {
        init();
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(null);
        this.mRecyclerView.setOnTouchListener(null);
        this.mSpringHelper = null;
    }

    public static float getFrictionTo(float f2, float f3, FloatProperty floatProperty, float f4, float f5) {
        float minVisibleChange = floatProperty.getMinVisibleChange();
        if (f2 * minVisibleChange < 0.0f) {
            minVisibleChange = -minVisibleChange;
        }
        float f6 = f4 - f3;
        if (Math.abs(f2) < f5 || f2 * f6 <= 0.0f) {
            return -1.0f;
        }
        return (float) (((double) ((-(f2 - minVisibleChange)) / f6)) / (-4.2d));
    }

    public static float getPredict(float f2, float f3) {
        return (-f2) / (f3 * (-4.2f));
    }

    private SpringHelper getSpringHelper() {
        if (this.mSpringHelper != null || this.mRecyclerView == null) {
            return null;
        }
        try {
            return (SpringHelper) Reflects.getDeclaredField("androidx.recyclerview.widget.SpringRecyclerView", "mSpringHelper").get(this.mRecyclerView);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupCallbacks$0(View view, MotionEvent motionEvent) {
        this.mFolmeState.cancel();
        return false;
    }

    public static float predictDistance(float f2, FloatProperty floatProperty, float f3, float f4) {
        float minVisibleChange = floatProperty.getMinVisibleChange();
        if (f2 * minVisibleChange < 0.0f) {
            minVisibleChange = -minVisibleChange;
        }
        float predict = getPredict(f2, f3) - getPredict(minVisibleChange, f3);
        if (Math.abs(f2) < f4) {
            return 0.0f;
        }
        return predict;
    }

    private void setupCallbacks() {
        if (this.mRecyclerView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(this);
        this.mRecyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: miuix.recyclerview.widget.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f6154a.lambda$setupCallbacks$0(view, motionEvent);
            }
        });
        this.mSpringHelper = getSpringHelper();
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            destroyCallbacks();
        }
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            setupCallbacks();
            snapFromFling(this.mRecyclerView.getLayoutManager(), 0);
        }
    }

    public abstract int computeFinalDistance(int i2, int i3, int i4);

    @CallSuper
    public void init() {
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: miuix.recyclerview.widget.SpringSnapHelper.1
            boolean mScrolled = false;

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, int i2) {
                if (this.mScrolled) {
                    SpringSnapHelper.this.updateConstructData();
                    SpringSnapHelper.this.snapFromFling(recyclerView.getLayoutManager(), 0);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, int i2, int i3) {
                if (i2 == 0 && i3 == 0) {
                    return;
                }
                this.mScrolled = true;
            }
        };
        this.mFolmeState = (FolmeState) Folme.useValue(this).setFlags(1L);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int i2, int i3) {
        if (this.mRecyclerView.getLayoutManager() == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        updateConstructData();
        if (this instanceof HorizontalSnapHelper) {
            snapFromFling(this.mRecyclerView.getLayoutManager(), i2);
            return true;
        }
        if (this instanceof VerticalSnapHelper) {
            snapFromFling(this.mRecyclerView.getLayoutManager(), i3);
        }
        return true;
    }

    public void onInterceptTouchEvent() {
        this.mFolmeState.cancel();
    }

    public void setDamping(float f2) {
        this.mDamping = f2;
    }

    public void setFriction(float f2) {
        this.mFriction = f2;
    }

    public void setResponse(float f2) {
        this.mResponse = f2;
    }

    public void setSnapPreference(int i2) {
        this.mSnapPreference = i2;
    }

    public void setSpringHorizontalDistance(SpringHelper springHelper, int i2) {
        if (springHelper == null) {
            return;
        }
        springHelper.setHorizontalDistance(i2);
    }

    public void setSpringVerticalDistance(SpringHelper springHelper, int i2) {
        if (springHelper == null) {
            return;
        }
        springHelper.setVerticalDistance(i2);
    }

    public abstract void snapFromFling(RecyclerView.LayoutManager layoutManager, int i2);

    public abstract void updateConstructData();
}
