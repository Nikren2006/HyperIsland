package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SpringRecyclerView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miuix.spring.view.SpringHelper;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelRecyclerView extends ClickableRecyclerView implements GestureDispatcher.GestureAcceptor {
    public static final Companion Companion = new Companion(0 == true ? 1 : 0);
    private static final String TAG = "MainPanelRecyclerView";
    private static final Field mDistanceField;
    private static final Field mSpringHelperField;
    private static final Field mVerticalField;
    private boolean acceptAllGesture;
    private GestureDispatcher.GestureHelper gestureHelper;
    private RecyclerView.OnScrollListener scrollListener;
    private final ArrayList<RecyclerView.OnScrollListener> scrollListeners;
    private boolean slided;
    private boolean smoothScrollToTopRequested;
    private int topDrawingIndex;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static abstract class ScrollHelper extends RecyclerView.OnScrollListener {
        public abstract void onScrollToTop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Field declaredField;
        Field declaredField2;
        Field field = null;
        try {
            declaredField = SpringRecyclerView.class.getDeclaredField("mSpringHelper");
            declaredField.setAccessible(true);
        } catch (Throwable unused) {
            declaredField = null;
        }
        mSpringHelperField = declaredField;
        try {
            declaredField2 = SpringHelper.class.getDeclaredField("mVertical");
            declaredField2.setAccessible(true);
        } catch (Throwable unused2) {
            declaredField2 = null;
        }
        mVerticalField = declaredField2;
        try {
            Field declaredField3 = Class.forName("miuix.spring.view.SpringHelper$AxisHandler").getDeclaredField("mDistance");
            declaredField3.setAccessible(true);
            field = declaredField3;
        } catch (Throwable unused3) {
        }
        mDistanceField = field;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MainPanelRecyclerView(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    private final void resetVerticalSpring() {
        Field field;
        try {
            Field field2 = mSpringHelperField;
            Object obj = field2 != null ? field2.get(this) : null;
            if (obj == null) {
                return;
            }
            Field field3 = mVerticalField;
            Object obj2 = field3 != null ? field3.get(obj) : null;
            if (obj2 == null || (field = mDistanceField) == null) {
                return;
            }
            field.set(obj2, Float.valueOf(0.0f));
        } catch (Throwable unused) {
            Log.d(TAG, "reset vertical spring failed.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        n.g(listener, "listener");
        super.addOnScrollListener(listener);
        this.scrollListeners.add(listener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void clearOnScrollListeners() {
        super.clearOnScrollListeners();
        this.scrollListeners.clear();
    }

    @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureAcceptor
    public GestureDispatcher.GestureHelper createGestureHelper(GestureDispatcher gestureDispatcher) {
        n.g(gestureDispatcher, "gestureDispatcher");
        GestureDispatcher.GestureHelper gestureHelper = new GestureDispatcher.GestureHelper(gestureDispatcher) { // from class: miui.systemui.controlcenter.widget.MainPanelRecyclerView.createGestureHelper.1
            {
                Boolean bool = Boolean.TRUE;
            }

            @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper
            public boolean check(boolean z2, boolean z3) {
                MainPanelRecyclerView.this.slided = true;
                if (MainPanelRecyclerView.this.getAcceptAllGesture()) {
                    return true;
                }
                if (!z2) {
                    return false;
                }
                if (!z3 || MainPanelRecyclerView.this.canScrollVertically(-1)) {
                    return z3 || MainPanelRecyclerView.this.canScrollVertically(1);
                }
                return false;
            }

            @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper
            public void onTouchEvent(MotionEvent event) {
                n.g(event, "event");
            }
        };
        this.gestureHelper = gestureHelper;
        return gestureHelper;
    }

    public final boolean getAcceptAllGesture() {
        return this.acceptAllGesture;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.topDrawingIndex;
        return i4 >= i2 ? i3 : i3 == i2 + (-1) ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // androidx.recyclerview.widget.SpringRecyclerView, androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        GestureDispatcher.GestureHelper gestureHelper = this.gestureHelper;
        if (gestureHelper == null) {
            n.w("gestureHelper");
            gestureHelper = null;
        }
        gestureHelper.onInterceptTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // miuix.recyclerview.widget.RecyclerView, androidx.recyclerview.widget.SpringRecyclerView, androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i2) {
        super.onScrollStateChanged(i2);
        if (isAttachedToWindow()) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
            if (linearLayoutManager != null && this.smoothScrollToTopRequested) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        return;
                    }
                    this.smoothScrollToTopRequested = false;
                } else if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0) {
                    this.smoothScrollToTopRequested = false;
                } else {
                    this.smoothScrollToTopRequested = true;
                    smoothScrollToPosition(0);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void removeOnScrollListener(RecyclerView.OnScrollListener listener) {
        n.g(listener, "listener");
        super.removeOnScrollListener(listener);
        this.scrollListeners.remove(listener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void scrollToPosition(int i2) {
        super.scrollToPosition(i2);
        if (i2 == 0) {
            RecyclerView.OnScrollListener onScrollListener = this.scrollListener;
            ScrollHelper scrollHelper = onScrollListener instanceof ScrollHelper ? (ScrollHelper) onScrollListener : null;
            if (scrollHelper != null) {
                scrollHelper.onScrollToTop();
            }
            for (RecyclerView.OnScrollListener onScrollListener2 : this.scrollListeners) {
                ScrollHelper scrollHelper2 = onScrollListener2 instanceof ScrollHelper ? (ScrollHelper) onScrollListener2 : null;
                if (scrollHelper2 != null) {
                    scrollHelper2.onScrollToTop();
                }
            }
        }
    }

    public final void scrollToTop() {
        stopScroll();
        scrollToPosition(0);
        this.smoothScrollToTopRequested = false;
        resetVerticalSpring();
    }

    public final void setAcceptAllGesture(boolean z2) {
        this.acceptAllGesture = z2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        super.setOnScrollListener(onScrollListener);
        this.scrollListener = onScrollListener;
    }

    public final void setTopDrawingChild(View child) {
        n.g(child, "child");
        int iIndexOfChild = indexOfChild(child);
        if (iIndexOfChild < 0) {
            return;
        }
        this.topDrawingIndex = iIndexOfChild;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void smoothScrollToPosition(int i2) {
        super.smoothScrollToPosition(i2);
        if (i2 == 0) {
            RecyclerView.OnScrollListener onScrollListener = this.scrollListener;
            ScrollHelper scrollHelper = onScrollListener instanceof ScrollHelper ? (ScrollHelper) onScrollListener : null;
            if (scrollHelper != null) {
                scrollHelper.onScrollToTop();
            }
            for (RecyclerView.OnScrollListener onScrollListener2 : this.scrollListeners) {
                ScrollHelper scrollHelper2 = onScrollListener2 instanceof ScrollHelper ? (ScrollHelper) onScrollListener2 : null;
                if (scrollHelper2 != null) {
                    scrollHelper2.onScrollToTop();
                }
            }
        }
    }

    public final boolean smoothScrollToTop() {
        if (!isAttachedToWindow()) {
            return true;
        }
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return true;
        }
        int scrollState = getScrollState();
        if (scrollState != 0) {
            if (scrollState != 2) {
                Log.w(TAG, "smoothScrollToTop requested when not idle or settling " + getScrollState());
                this.smoothScrollToTopRequested = false;
                return true;
            }
            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0) {
                this.smoothScrollToTopRequested = true;
                return true;
            }
            if (!this.smoothScrollToTopRequested) {
                smoothScrollToPosition(0);
                this.smoothScrollToTopRequested = true;
            }
        } else {
            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0) {
                this.smoothScrollToTopRequested = false;
                return true;
            }
            if (!this.smoothScrollToTopRequested) {
                smoothScrollToPosition(0);
                this.smoothScrollToTopRequested = true;
            }
        }
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MainPanelRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ MainPanelRecyclerView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainPanelRecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        setNestedScrollingEnabled(false);
        setOverScrollMode(1);
        setChildrenDrawingOrderEnabled(true);
        this.scrollListeners = new ArrayList<>();
    }
}
