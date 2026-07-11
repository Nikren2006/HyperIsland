package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class OverlayListView extends ListView {
    private final List<OverlayObject> mOverlayObjects;

    public static class OverlayObject {
        private BitmapDrawable mBitmap;
        private Rect mCurrentBounds;
        private int mDeltaY;
        private long mDuration;
        private Interpolator mInterpolator;
        private boolean mIsAnimationEnded;
        private boolean mIsAnimationStarted;
        private OnAnimationEndListener mListener;
        private Rect mStartRect;
        private long mStartTime;
        private float mCurrentAlpha = 1.0f;
        private float mStartAlpha = 1.0f;
        private float mEndAlpha = 1.0f;

        public interface OnAnimationEndListener {
            void onAnimationEnd();
        }

        public OverlayObject(@Nullable BitmapDrawable bitmapDrawable, @Nullable Rect rect) {
            this.mBitmap = bitmapDrawable;
            this.mStartRect = rect;
            this.mCurrentBounds = new Rect(rect);
            BitmapDrawable bitmapDrawable2 = this.mBitmap;
            if (bitmapDrawable2 != null) {
                bitmapDrawable2.setAlpha((int) (this.mCurrentAlpha * 255.0f));
                this.mBitmap.setBounds(this.mCurrentBounds);
            }
        }

        @Nullable
        public BitmapDrawable getBitmapDrawable() {
            return this.mBitmap;
        }

        public boolean isAnimationStarted() {
            return this.mIsAnimationStarted;
        }

        @NonNull
        public OverlayObject setAlphaAnimation(float f2, float f3) {
            this.mStartAlpha = f2;
            this.mEndAlpha = f3;
            return this;
        }

        @NonNull
        public OverlayObject setAnimationEndListener(@Nullable OnAnimationEndListener onAnimationEndListener) {
            this.mListener = onAnimationEndListener;
            return this;
        }

        @NonNull
        public OverlayObject setDuration(long j2) {
            this.mDuration = j2;
            return this;
        }

        @NonNull
        public OverlayObject setInterpolator(@Nullable Interpolator interpolator) {
            this.mInterpolator = interpolator;
            return this;
        }

        @NonNull
        public OverlayObject setTranslateYAnimation(int i2) {
            this.mDeltaY = i2;
            return this;
        }

        public void startAnimation(long j2) {
            this.mStartTime = j2;
            this.mIsAnimationStarted = true;
        }

        public void stopAnimation() {
            this.mIsAnimationStarted = true;
            this.mIsAnimationEnded = true;
            OnAnimationEndListener onAnimationEndListener = this.mListener;
            if (onAnimationEndListener != null) {
                onAnimationEndListener.onAnimationEnd();
            }
        }

        public boolean update(long j2) {
            if (this.mIsAnimationEnded) {
                return false;
            }
            float fMax = this.mIsAnimationStarted ? Math.max(0.0f, Math.min(1.0f, (j2 - this.mStartTime) / this.mDuration)) : 0.0f;
            Interpolator interpolator = this.mInterpolator;
            float interpolation = interpolator == null ? fMax : interpolator.getInterpolation(fMax);
            int i2 = (int) (this.mDeltaY * interpolation);
            Rect rect = this.mCurrentBounds;
            Rect rect2 = this.mStartRect;
            rect.top = rect2.top + i2;
            rect.bottom = rect2.bottom + i2;
            float f2 = this.mStartAlpha;
            float f3 = f2 + ((this.mEndAlpha - f2) * interpolation);
            this.mCurrentAlpha = f3;
            BitmapDrawable bitmapDrawable = this.mBitmap;
            if (bitmapDrawable != null && rect != null) {
                bitmapDrawable.setAlpha((int) (f3 * 255.0f));
                this.mBitmap.setBounds(this.mCurrentBounds);
            }
            if (this.mIsAnimationStarted && fMax >= 1.0f) {
                this.mIsAnimationEnded = true;
                OnAnimationEndListener onAnimationEndListener = this.mListener;
                if (onAnimationEndListener != null) {
                    onAnimationEndListener.onAnimationEnd();
                }
            }
            return !this.mIsAnimationEnded;
        }
    }

    public OverlayListView(Context context) {
        super(context);
        this.mOverlayObjects = new ArrayList();
    }

    public void addOverlayObject(OverlayObject overlayObject) {
        this.mOverlayObjects.add(overlayObject);
    }

    @Override // android.view.View
    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (this.mOverlayObjects.size() > 0) {
            Iterator<OverlayObject> it = this.mOverlayObjects.iterator();
            while (it.hasNext()) {
                OverlayObject next = it.next();
                BitmapDrawable bitmapDrawable = next.getBitmapDrawable();
                if (bitmapDrawable != null) {
                    bitmapDrawable.draw(canvas);
                }
                if (!next.update(getDrawingTime())) {
                    it.remove();
                }
            }
        }
    }

    public void startAnimationAll() {
        for (OverlayObject overlayObject : this.mOverlayObjects) {
            if (!overlayObject.isAnimationStarted()) {
                overlayObject.startAnimation(getDrawingTime());
            }
        }
    }

    public void stopAnimationAll() {
        Iterator<OverlayObject> it = this.mOverlayObjects.iterator();
        while (it.hasNext()) {
            it.next().stopAnimation();
        }
    }

    public OverlayListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOverlayObjects = new ArrayList();
    }

    public OverlayListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOverlayObjects = new ArrayList();
    }
}
