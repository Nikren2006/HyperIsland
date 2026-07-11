package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EdgeEffect;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RemixRecyclerView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import miuix.animation.utils.EaseManager;
import miuix.os.Build;
import miuix.spring.view.SpringHelper;
import miuix.spring.view.SpringStateListener;
import miuix.util.HapticFeedbackCompat;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
public abstract class SpringRecyclerView extends RemixRecyclerView {
    private static final Field NESTED_SCROLL_HELPER;
    private static final RecyclerView.EdgeEffectFactory NON_EFFECT_FACTORY;
    private static final String TAG = "SpringRecyclerView";
    private static final Field VIEW_FLINGER;
    private boolean mHorizontalOverScrolling;
    private boolean mInGlobalRomMode;
    private int mManagedScrollState;

    @Nullable
    private SpringFlinger mSpringFlinger;
    private SpringHelper mSpringHelper;
    private SpringNestedScrollingHelper mSpringNestedScrollingHelper;
    private List<SpringStateListener> mSpringStateListeners;
    private float mSpringX;
    private float mSpringY;
    private boolean mVerticalOverScrolling;

    public static class NonEdgeEffect extends EdgeEffect {
        public NonEdgeEffect(Context context) {
            super(context);
        }

        @Override // android.widget.EdgeEffect
        public boolean draw(Canvas canvas) {
            return false;
        }

        @Override // android.widget.EdgeEffect
        public void finish() {
        }

        @Override // android.widget.EdgeEffect
        @Nullable
        public BlendMode getBlendMode() {
            return null;
        }

        @Override // android.widget.EdgeEffect
        public int getColor() {
            return 0;
        }

        @Override // android.widget.EdgeEffect
        public int getMaxHeight() {
            return 0;
        }

        @Override // android.widget.EdgeEffect
        public boolean isFinished() {
            return true;
        }

        @Override // android.widget.EdgeEffect
        public void onAbsorb(int i2) {
        }

        @Override // android.widget.EdgeEffect
        public void onPull(float f2) {
        }

        @Override // android.widget.EdgeEffect
        public void onRelease() {
        }

        @Override // android.widget.EdgeEffect
        public void setBlendMode(@Nullable BlendMode blendMode) {
        }

        @Override // android.widget.EdgeEffect
        public void setColor(int i2) {
        }

        @Override // android.widget.EdgeEffect
        public void setSize(int i2, int i3) {
        }

        @Override // android.widget.EdgeEffect
        public void onPull(float f2, float f3) {
        }
    }

    public static class NonEdgeEffectFactory extends RecyclerView.EdgeEffectFactory {
        private NonEdgeEffectFactory() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory
        @NonNull
        public EdgeEffect createEdgeEffect(@NonNull RecyclerView recyclerView, int i2) {
            return new NonEdgeEffect(recyclerView.getContext());
        }
    }

    public class SpringFlinger extends RemixRecyclerView.ViewFlinger {
        private SpringFlinger() {
            super();
        }

        @Override // androidx.recyclerview.widget.RemixRecyclerView.ViewFlinger
        public void checkDoneScrolling() {
            super.checkDoneScrolling();
            int horizontalDistance = SpringRecyclerView.this.mSpringHelper.getHorizontalDistance();
            int verticalDistance = SpringRecyclerView.this.mSpringHelper.getVerticalDistance();
            if (horizontalDistance == 0 && verticalDistance == 0) {
                return;
            }
            springBack(horizontalDistance, verticalDistance);
        }

        @Override // androidx.recyclerview.widget.RemixRecyclerView.ViewFlinger, androidx.recyclerview.widget.RecyclerView.ViewFlinger
        public void fling(int i2, int i3) {
            int horizontalDistance = SpringRecyclerView.this.mSpringHelper.getHorizontalDistance();
            int verticalDistance = SpringRecyclerView.this.mSpringHelper.getVerticalDistance();
            if (SpringRecyclerView.this.springAvailable() && (horizontalDistance != 0 || verticalDistance != 0)) {
                overFling(i2, i3, horizontalDistance, verticalDistance);
                return;
            }
            if (SpringRecyclerView.this.getOverScrollMode() == 1) {
                SpringRecyclerView.this.mHorizontalOverScrolling = false;
                SpringRecyclerView.this.mVerticalOverScrolling = false;
            }
            super.fling(i2, i3);
        }

        public void notifyHorizontalEdgeReached(int i2) {
            SpringRecyclerView.this.mHorizontalOverScrolling = true;
            SpringRecyclerView.this.setScrollState(2);
            resetFlingPosition();
            ((RemixRecyclerView.ViewFlinger) this).mOverScroller.notifyHorizontalEdgeReached(0, -i2, SpringRecyclerView.this.getWidth());
        }

        public void notifyVerticalEdgeReached(int i2) {
            SpringRecyclerView.this.mVerticalOverScrolling = true;
            SpringRecyclerView.this.setScrollState(2);
            resetFlingPosition();
            ((RemixRecyclerView.ViewFlinger) this).mOverScroller.notifyVerticalEdgeReached(0, -i2, SpringRecyclerView.this.getHeight());
        }

        public void overFling(int i2, int i3, int i4, int i5) {
            int i6;
            int i7;
            int i8;
            int i9;
            SpringRecyclerView.this.mHorizontalOverScrolling = i4 != 0;
            SpringRecyclerView.this.mVerticalOverScrolling = i5 != 0;
            SpringRecyclerView.this.setScrollState(2);
            resetFlingPosition();
            int i10 = Integer.MAX_VALUE;
            int i11 = Integer.MIN_VALUE;
            if (Integer.signum(i2) * i4 > 0) {
                i6 = -i4;
                i7 = i6;
            } else if (i2 < 0) {
                i7 = -i4;
                i6 = Integer.MIN_VALUE;
            } else {
                i6 = -i4;
                i7 = Integer.MAX_VALUE;
            }
            if (Integer.signum(i3) * i5 > 0) {
                i9 = -i5;
                i8 = i9;
            } else {
                if (i3 < 0) {
                    i10 = -i5;
                } else {
                    i11 = -i5;
                }
                i8 = i10;
                i9 = i11;
            }
            ((RemixRecyclerView.ViewFlinger) this).mOverScroller.fling(0, 0, i2, i3, i6, i7, i9, i8, SpringRecyclerView.this.getWidth(), SpringRecyclerView.this.getHeight());
            postOnAnimation();
        }

        public void springBack(int i2, int i3) {
            if (i2 != 0) {
                SpringRecyclerView.this.mHorizontalOverScrolling = true;
            }
            if (i3 != 0) {
                SpringRecyclerView.this.mVerticalOverScrolling = true;
            }
            SpringRecyclerView.this.setScrollState(2);
            resetFlingPosition();
            int i4 = -i2;
            int i5 = -i3;
            ((RemixRecyclerView.ViewFlinger) this).mOverScroller.springBack(0, 0, i4, i4, i5, i5);
            postOnAnimation();
        }
    }

    public class SpringNestedScrollingHelper extends NestedScrollingChildHelper {
        public SpringNestedScrollingHelper(@NonNull View view) {
            super(view);
        }

        @Override // androidx.core.view.NestedScrollingChildHelper
        public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
            return SpringRecyclerView.this.mSpringHelper.handleNestedPreScroll(i2, i3, iArr, iArr2, i4);
        }

        @Override // androidx.core.view.NestedScrollingChildHelper
        public void dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
            SpringRecyclerView.this.mSpringHelper.handleNestedScroll(i2, i3, i4, i5, iArr, i6, iArr2);
        }

        public boolean super_dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
            if (SpringRecyclerView.this.mHorizontalOverScrolling || SpringRecyclerView.this.mVerticalOverScrolling) {
                return false;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            return super.dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
        }

        public void super_dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
            if (SpringRecyclerView.this.mHorizontalOverScrolling || SpringRecyclerView.this.mVerticalOverScrolling) {
                return;
            }
            super.dispatchNestedScroll(i2, i3, i4, i5, iArr, i6, iArr2);
        }
    }

    static {
        try {
            Field declaredField = RecyclerView.class.getDeclaredField("mViewFlinger");
            VIEW_FLINGER = declaredField;
            declaredField.setAccessible(true);
            try {
                Field declaredField2 = RecyclerView.class.getDeclaredField("mScrollingChildHelper");
                NESTED_SCROLL_HELPER = declaredField2;
                declaredField2.setAccessible(true);
                NON_EFFECT_FACTORY = new NonEdgeEffectFactory();
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchFieldException e3) {
            throw new RuntimeException(e3);
        }
    }

    public SpringRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    private void replaceNestedScrollingHelper(NestedScrollingChildHelper nestedScrollingChildHelper) {
        try {
            NESTED_SCROLL_HELPER.set(this, nestedScrollingChildHelper);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void replaceViewFlinger(RemixRecyclerView.ViewFlinger viewFlinger) {
        try {
            VIEW_FLINGER.set(this, viewFlinger);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean springAvailable() {
        return getOverScrollMode() != 2 && getSpringEnabled();
    }

    public void addSpringStateListener(SpringStateListener springStateListener) {
        this.mSpringStateListeners.add(springStateListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        int horizontalDistance = this.mSpringHelper.getHorizontalDistance();
        int verticalDistance = this.mSpringHelper.getVerticalDistance();
        if (horizontalDistance == 0 && verticalDistance == 0) {
            super.draw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.translate(-horizontalDistance, -verticalDistance);
        super.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    public int getDragFlingVelocityX() {
        SpringFlinger springFlinger = this.mSpringFlinger;
        if (springFlinger != null) {
            return springFlinger.mDragFlingVelocityX;
        }
        return 0;
    }

    public int getDragFlingVelocityY() {
        SpringFlinger springFlinger = this.mSpringFlinger;
        if (springFlinger != null) {
            return springFlinger.mDragFlingVelocityY;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView
    public /* bridge */ /* synthetic */ boolean getSpringEnabled() {
        return super.getSpringEnabled();
    }

    public float getSpringX() {
        return this.mSpringX;
    }

    public float getSpringY() {
        return this.mSpringY;
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView
    public boolean isOverScrolling() {
        return this.mHorizontalOverScrolling || this.mVerticalOverScrolling;
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public /* bridge */ /* synthetic */ boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public /* bridge */ /* synthetic */ boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i2) {
        super.onScrollStateChanged(i2);
        this.mManagedScrollState = i2;
        if (springAvailable()) {
            boolean zIsOverScrolling = isOverScrolling();
            if (i2 != 2 && zIsOverScrolling) {
                stopSpringFling();
                this.mHorizontalOverScrolling = false;
                this.mVerticalOverScrolling = false;
            }
            if (i2 == 0 && zIsOverScrolling) {
                this.mSpringHelper.resetDistance();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void removeSpringStateListener(SpringStateListener springStateListener) {
        this.mSpringStateListeners.remove(springStateListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z2) {
        super.setNestedScrollingEnabled(z2);
        SpringNestedScrollingHelper springNestedScrollingHelper = this.mSpringNestedScrollingHelper;
        if (springNestedScrollingHelper != null) {
            springNestedScrollingHelper.setNestedScrollingEnabled(z2);
        }
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView, android.view.View
    public /* bridge */ /* synthetic */ void setOverScrollMode(int i2) {
        super.setOverScrollMode(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setScrollState(int i2) {
        if (this.mManagedScrollState == 1 && i2 == 0) {
            int horizontalDistance = this.mSpringHelper.getHorizontalDistance();
            int verticalDistance = this.mSpringHelper.getVerticalDistance();
            SpringFlinger springFlinger = this.mSpringFlinger;
            if (springFlinger != null && (horizontalDistance != 0 || verticalDistance != 0)) {
                springFlinger.springBack(horizontalDistance, verticalDistance);
                return;
            }
        }
        super.setScrollState(i2);
    }

    @Override // androidx.recyclerview.widget.RemixRecyclerView
    public void setSpringEnabled(boolean z2) {
        if (this.mInGlobalRomMode && z2) {
            return;
        }
        super.setSpringEnabled(z2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void stopScroll() {
        stopSpringFling();
        super.stopScroll();
    }

    public void stopSpringFling() {
        SpringFlinger springFlinger = this.mSpringFlinger;
        if (springFlinger != null) {
            springFlinger.stop();
        }
    }

    public SpringRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    public SpringRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSpringStateListeners = new ArrayList();
        this.mSpringX = 0.0f;
        this.mSpringY = 0.0f;
        this.mManagedScrollState = 0;
        this.mSpringHelper = new SpringHelper() { // from class: androidx.recyclerview.widget.SpringRecyclerView.1
            HapticFeedbackCompat mHapticFeedbackCompat;

            private HapticFeedbackCompat getHapticFeedbackCompat() {
                if (this.mHapticFeedbackCompat == null) {
                    this.mHapticFeedbackCompat = new HapticFeedbackCompat(SpringRecyclerView.this.getContext());
                }
                return this.mHapticFeedbackCompat;
            }

            @Override // miuix.spring.view.SpringHelper
            public boolean canScrollHorizontally() {
                RecyclerView.LayoutManager layoutManager = SpringRecyclerView.this.mLayout;
                return layoutManager != null && layoutManager.canScrollHorizontally();
            }

            @Override // miuix.spring.view.SpringHelper
            public boolean canScrollVertically() {
                RecyclerView.LayoutManager layoutManager = SpringRecyclerView.this.mLayout;
                return layoutManager != null && layoutManager.canScrollVertically();
            }

            @Override // miuix.spring.view.SpringHelper
            public boolean dispatchNestedPreScroll(int i3, int i4, @Nullable int[] iArr, @Nullable int[] iArr2, int i5) {
                if (SpringRecyclerView.this.mHorizontalOverScrolling && getHorizontalDistance() == 0) {
                    SpringRecyclerView.this.mHorizontalOverScrolling = false;
                }
                if (SpringRecyclerView.this.mVerticalOverScrolling && getVerticalDistance() == 0) {
                    SpringRecyclerView.this.mVerticalOverScrolling = false;
                }
                return SpringRecyclerView.this.mSpringNestedScrollingHelper.super_dispatchNestedPreScroll(i3, i4, iArr, iArr2, i5);
            }

            @Override // miuix.spring.view.SpringHelper
            public void dispatchNestedScroll(int i3, int i4, int i5, int i6, @Nullable int[] iArr, int i7, @Nullable int[] iArr2) {
                SpringRecyclerView.this.mSpringNestedScrollingHelper.super_dispatchNestedScroll(i3, i4, i5, i6, iArr, i7, iArr2);
                if (springAvailable() && SpringRecyclerView.this.mManagedScrollState == 2) {
                    if (!SpringRecyclerView.this.mHorizontalOverScrolling && canScrollHorizontally() && i5 != 0) {
                        SpringRecyclerView.this.mSpringFlinger.notifyHorizontalEdgeReached(i5);
                    }
                    if (SpringRecyclerView.this.mVerticalOverScrolling || !canScrollVertically() || i6 == 0) {
                        return;
                    }
                    SpringRecyclerView.this.mSpringFlinger.notifyVerticalEdgeReached(i6);
                }
            }

            @Override // miuix.spring.view.SpringHelper
            public int getHeight() {
                return SpringRecyclerView.this.getHeight();
            }

            @Override // miuix.spring.view.SpringHelper
            public int getWidth() {
                return SpringRecyclerView.this.getWidth();
            }

            @Override // miuix.spring.view.SpringStateListener
            public void onSpringDistanceChanged(float f2, float f3) {
                SpringRecyclerView.this.mSpringX = f2;
                SpringRecyclerView.this.mSpringY = f3;
                for (int i3 = 0; i3 < SpringRecyclerView.this.mSpringStateListeners.size(); i3++) {
                    ((SpringStateListener) SpringRecyclerView.this.mSpringStateListeners.get(0)).onSpringDistanceChanged(f2, f3);
                }
            }

            @Override // miuix.spring.view.SpringHelper
            public boolean springAvailable() {
                return SpringRecyclerView.this.springAvailable();
            }

            @Override // miuix.spring.view.SpringHelper
            @Keep
            public void vibrate() {
                if (!HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                    HapticCompat.performHapticFeedbackAsync(SpringRecyclerView.this, HapticFeedbackConstants.MIUI_SCROLL_EDGE);
                } else if (SpringRecyclerView.this.isHapticFeedbackEnabled()) {
                    getHapticFeedbackCompat().performExtHapticFeedbackAsync(EaseManager.EaseStyleDef.PERLIN);
                }
            }
        };
        this.mInGlobalRomMode = Build.IS_INTERNATIONAL_BUILD;
        this.mSpringFlinger = new SpringFlinger();
        SpringNestedScrollingHelper springNestedScrollingHelper = new SpringNestedScrollingHelper(this);
        this.mSpringNestedScrollingHelper = springNestedScrollingHelper;
        springNestedScrollingHelper.setNestedScrollingEnabled(isNestedScrollingEnabled());
        replaceViewFlinger(this.mSpringFlinger);
        replaceNestedScrollingHelper(this.mSpringNestedScrollingHelper);
        if (this.mInGlobalRomMode) {
            setSpringEnabled(false);
        } else {
            super.setEdgeEffectFactory(NON_EFFECT_FACTORY);
        }
    }
}
