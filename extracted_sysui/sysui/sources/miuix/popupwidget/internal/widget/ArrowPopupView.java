package miuix.popupwidget.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import miuix.internal.util.DeviceHelper;
import miuix.internal.util.ViewUtils;
import miuix.popupwidget.R;
import miuix.popupwidget.widget.ArrowPopupWindow;
import miuix.view.CompatViewMethod;

/* JADX INFO: loaded from: classes5.dex */
public class ArrowPopupView extends FrameLayout implements View.OnTouchListener {
    private static final int ANIMATION_REPEAT_COUNT = 8;
    public static final byte ARROW_BOTTOM_LEFT_MODE = 18;
    public static final byte ARROW_BOTTOM_MODE = 16;
    public static final byte ARROW_BOTTOM_RIGHT_MODE = 17;
    public static final byte ARROW_LEFT_MODE = 32;
    private static final int ARROW_MIDDLE_OFFSET = 28;
    public static final byte ARROW_NONE_MODE = 0;
    private static final int ARROW_PADDING = 1;
    public static final byte ARROW_RIGHT_MODE = 64;
    public static final byte ARROW_TOP_LEFT_MODE = 9;
    public static final byte ARROW_TOP_MODE = 8;
    public static final byte ARROW_TOP_RIGHT_MODE = 10;
    public static final int LAYOUT_MODE_LTR = 0;
    public static final int LAYOUT_MODE_RTL = 1;
    public static final int LAYOUT_MODE_UNSPECIFIED = 2;
    private static final String TAG = "ArrowPopupView";
    private static final int TRANSLATION_VALUE = 4;
    private boolean mAlphaAnimationEnabled;
    private View mAnchor;
    private View.OnLayoutChangeListener mAnchorTrackListener;
    private AnimationSet mAnimationSet;
    private AnimatorSet mAnimator;
    private AppCompatImageView mArrow;
    private int mArrowBackgroundPaintColor;
    private Drawable mArrowBottom;
    private Drawable mArrowBottomLeft;
    private Drawable mArrowBottomRight;
    private final Runnable mArrowLayoutTask;
    private Drawable mArrowLeft;
    private int mArrowMode;
    private ArrowPopupWindow mArrowPopupWindow;
    private Drawable mArrowRight;
    private int mArrowSpaceLeft;
    private int mArrowSpaceTop;
    private Drawable mArrowTop;
    private Drawable mArrowTopLeft;
    private Drawable mArrowTopRight;
    private Drawable mArrowTopWithTitle;
    private boolean mAutoDismiss;
    private Drawable mBackground;
    private Drawable mBackgroundLeft;
    private Drawable mBackgroundRight;
    private FrameLayout mContentFrame;
    private ArrowPopupContentWrapper mContentFrameWrapper;
    private int mElevation;
    private boolean mEnableTrackAnchor;
    private boolean mHasFirstLayout;
    private Animation.AnimationListener mHideAnimatorListener;
    private boolean mIsDismissing;
    private int mMinBorder;
    private AppCompatButton mNegativeButton;
    private WrapperOnClickListener mNegativeClickListener;
    private int mOffsetX;
    private int mOffsetY;
    private AppCompatButton mPositiveButton;
    private WrapperOnClickListener mPositiveClickListener;
    private int mRtlMode;
    private Animation.AnimationListener mShowAnimationListener;
    private boolean mShowingAnimation;
    private int mSpaceLeft;
    private int mSpaceTop;
    private Drawable mTitleBackground;
    private LinearLayout mTitleLayout;
    private AppCompatTextView mTitleText;
    private Rect mTmpRect;
    private RectF mTmpRectF;
    private View.OnTouchListener mTouchInterceptor;
    private int mTranslationValue;

    public class WrapperOnClickListener implements View.OnClickListener {
        public View.OnClickListener mOnClickListener;

        public WrapperOnClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.mOnClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            ArrowPopupView.this.mArrowPopupWindow.dismiss(true);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }
    }

    public ArrowPopupView(Context context) {
        this(context, null);
    }

    private void adjustArrowMode() {
        int[] iArr = new int[2];
        this.mAnchor.getLocationInWindow(iArr);
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = this.mContentFrameWrapper.getMeasuredWidth();
        int measuredHeight = this.mContentFrameWrapper.getMeasuredHeight();
        int height2 = this.mAnchor.getHeight();
        int width2 = this.mAnchor.getWidth();
        SparseIntArray sparseIntArray = new SparseIntArray(4);
        int i2 = 16;
        sparseIntArray.put(16, iArr[1] - measuredHeight);
        sparseIntArray.put(8, ((height - iArr[1]) - height2) - measuredHeight);
        int i3 = 0;
        sparseIntArray.put(64, iArr[0] - measuredWidth);
        sparseIntArray.put(32, ((width - iArr[0]) - width2) - measuredWidth);
        int i4 = Integer.MIN_VALUE;
        while (true) {
            if (i3 >= sparseIntArray.size()) {
                break;
            }
            int iKeyAt = sparseIntArray.keyAt(i3);
            if (sparseIntArray.get(iKeyAt) >= this.mMinBorder) {
                i2 = iKeyAt;
                break;
            }
            if (sparseIntArray.get(iKeyAt) > i4) {
                i4 = sparseIntArray.get(iKeyAt);
                i2 = iKeyAt;
            }
            i3++;
        }
        setArrowMode(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateShowing() {
        if (DeviceHelper.isFeatureWholeAnim()) {
            AnimationSet animationSet = this.mAnimationSet;
            if (animationSet != null) {
                animationSet.cancel();
            }
            AnimatorSet animatorSet = this.mAnimator;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mAnimator = animatorSet2;
            animatorSet2.addListener(new AnimatorListenerAdapter() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.8
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ArrowPopupView.this.mArrowPopupWindow.dismiss();
                }
            });
            float f2 = getContext().getResources().getDisplayMetrics().density * 4.0f;
            Property property = View.TRANSLATION_Y;
            int i2 = this.mRtlMode;
            boolean z2 = i2 == 1 || (i2 == 2 && ViewUtils.isLayoutRtl(this));
            int i3 = this.mArrowMode;
            if (i3 == 16) {
                f2 = -f2;
            } else if (i3 == 32) {
                if (z2) {
                    f2 = -f2;
                }
                property = View.TRANSLATION_X;
            } else if (i3 == 64) {
                if (!z2) {
                    f2 = -f2;
                }
                property = View.TRANSLATION_X;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mContentFrameWrapper, (Property<ArrowPopupContentWrapper, Float>) property, 0.0f, f2, 0.0f);
            objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimatorOfFloat.setDuration(1200L);
            if (this.mAutoDismiss) {
                objectAnimatorOfFloat.setRepeatCount(8);
            } else {
                objectAnimatorOfFloat.setRepeatCount(-1);
            }
            objectAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.9
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ArrowPopupView.this.mTranslationValue = ((Float) valueAnimator.getAnimatedValue()).intValue();
                    int iAbs = Math.abs(ArrowPopupView.this.mTranslationValue);
                    ArrowPopupView arrowPopupView = ArrowPopupView.this;
                    arrowPopupView.invalidate(arrowPopupView.mContentFrameWrapper.getLeft() - iAbs, ArrowPopupView.this.mContentFrameWrapper.getTop() - iAbs, ArrowPopupView.this.mContentFrameWrapper.getRight() + iAbs, ArrowPopupView.this.mContentFrameWrapper.getBottom() + iAbs);
                }
            });
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mArrow, (Property<AppCompatImageView, Float>) property, 0.0f, f2, 0.0f);
            objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimatorOfFloat2.setDuration(1200L);
            if (this.mAutoDismiss) {
                objectAnimatorOfFloat2.setRepeatCount(8);
            } else {
                objectAnimatorOfFloat2.setRepeatCount(-1);
            }
            this.mAnimator.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
            this.mAnimator.start();
        }
    }

    private void arrowHorizontalLayout(int i2) {
        int[] iArr = new int[2];
        this.mAnchor.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        getLocationOnScreen(iArr);
        int width = this.mAnchor.getWidth();
        int height = this.mAnchor.getHeight();
        int width2 = getWidth();
        int height2 = getHeight();
        int iMax = Math.max(this.mContentFrameWrapper.getMeasuredWidth(), this.mContentFrameWrapper.getMinimumWidth());
        int iMax2 = Math.max(this.mContentFrameWrapper.getMeasuredHeight(), this.mContentFrameWrapper.getMinimumHeight());
        int arrowWidth = getArrowWidth(this.mArrowMode);
        int arrowHeight = getArrowHeight(this.mArrowMode);
        int i5 = iArr[1];
        int i6 = ((height / 2) + i4) - i5;
        this.mSpaceTop = i6;
        int i7 = height2 - i6;
        this.mArrowSpaceTop = ((i4 + ((height - arrowHeight) / 2)) - i5) + ((this.mContentFrameWrapper.getPaddingTop() - this.mContentFrameWrapper.getPaddingBottom()) / 2);
        int i8 = iMax2 / 2;
        int i9 = iMax2 - i8;
        this.mSpaceLeft = getLeft() + i2;
        if (isRightMode()) {
            int i10 = this.mRtlMode;
            if (i10 == 1 || (i10 == 2 && ViewUtils.isLayoutRtl(this))) {
                this.mSpaceLeft += (((i3 + width) - this.mContentFrameWrapper.getPaddingLeft()) + arrowWidth) - iArr[0];
                this.mContentFrameWrapper.getPaddingLeft();
            } else {
                this.mSpaceLeft += (((i3 - iMax) + this.mContentFrameWrapper.getPaddingRight()) - arrowWidth) - iArr[0];
            }
        } else if (isLeftMode()) {
            int i11 = this.mRtlMode;
            if (i11 == 1 || (i11 == 2 && ViewUtils.isLayoutRtl(this))) {
                this.mSpaceLeft += ((((i3 - iMax) + this.mContentFrameWrapper.getPaddingRight()) - arrowWidth) - iArr[0]) + 1;
            } else {
                this.mSpaceLeft += (((i3 + width) - this.mContentFrameWrapper.getPaddingLeft()) + arrowWidth) - iArr[0];
                this.mContentFrameWrapper.getPaddingLeft();
            }
        }
        int i12 = this.mSpaceTop;
        if (i12 >= i8 && i7 >= i9) {
            this.mSpaceTop = (i12 - i8) + this.mOffsetY;
        } else if (i7 < i9) {
            this.mSpaceTop = (height2 - iMax2) + this.mOffsetY;
        } else if (i12 < i8) {
            this.mSpaceTop = this.mOffsetY;
        }
        int i13 = this.mArrowSpaceTop + this.mOffsetY;
        this.mArrowSpaceTop = i13;
        if (i13 < 0) {
            this.mArrowSpaceTop = 0;
        } else if (i13 + arrowHeight > height2) {
            this.mArrowSpaceTop = i13 - ((arrowHeight + i13) - height2);
        }
        this.mContentFrameWrapper.setArrowMode(this.mArrowMode);
        this.mContentFrameWrapper.setRtlMode(this.mRtlMode);
        this.mContentFrameWrapper.layout(Math.max(this.mSpaceLeft, 0), Math.max(this.mSpaceTop, 0), Math.min(this.mSpaceLeft + iMax, width2), Math.min(this.mSpaceTop + iMax2, height2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void arrowLayout() {
        int i2 = this.mRtlMode;
        int i3 = (i2 == 1 || (i2 == 2 && ViewUtils.isLayoutRtl(this))) ? -this.mOffsetX : this.mOffsetX;
        if (isVerticalMode()) {
            arrowVerticalLayout(i3);
        } else {
            arrowHorizontalLayout(i3);
        }
        View contentView = getContentView();
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            if (contentView.getMeasuredHeight() > this.mContentFrameWrapper.getHeight() - this.mTitleLayout.getHeight()) {
                layoutParams.height = this.mContentFrameWrapper.getHeight() - this.mTitleLayout.getHeight();
                contentView.setLayoutParams(layoutParams);
            } else if (contentView.getMeasuredWidth() > this.mContentFrameWrapper.getWidth()) {
                layoutParams.width = this.mContentFrameWrapper.getWidth();
                contentView.setLayoutParams(layoutParams);
            }
            if (layoutParams.height <= 0 || layoutParams.width <= 0) {
                Log.w(TAG, "Invalid LayoutPrams of content view, please check the anchor view");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void arrowVerticalLayout(int r19) {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.popupwidget.internal.widget.ArrowPopupView.arrowVerticalLayout(int):void");
    }

    private void executeLayoutArrow(int i2, int i3, int i4) {
        int i5;
        int right;
        int bottom;
        int measuredHeight;
        int i6 = this.mRtlMode;
        boolean z2 = i6 == 1 || (i6 == 2 && ViewUtils.isLayoutRtl(this));
        int i7 = this.mArrowMode;
        if (i7 == 9 || i7 == 10) {
            int right2 = ((z2 || i7 != 9) && !(z2 && i7 == 10)) ? ((this.mContentFrameWrapper.getRight() - this.mContentFrameWrapper.getPaddingStart()) - i2) + 1 : (this.mContentFrameWrapper.getLeft() + this.mContentFrameWrapper.getPaddingStart()) - 1;
            i4 = (i4 + this.mContentFrameWrapper.getPaddingTop()) - i3;
            AppCompatImageView appCompatImageView = this.mArrow;
            appCompatImageView.layout(right2, i4, right2 + i2, appCompatImageView.getMeasuredHeight() + i4);
            i5 = right2;
        } else if (i7 == 17 || i7 == 18) {
            if ((z2 || i7 != 18) && !(z2 && i7 == 17)) {
                right = (this.mContentFrameWrapper.getRight() - this.mContentFrameWrapper.getPaddingEnd()) - i2;
                bottom = this.mContentFrameWrapper.getBottom() - this.mContentFrameWrapper.getPaddingBottom();
                measuredHeight = this.mArrow.getMeasuredHeight();
            } else {
                right = this.mContentFrameWrapper.getLeft() + this.mContentFrameWrapper.getPaddingStart();
                bottom = this.mContentFrameWrapper.getBottom() - this.mContentFrameWrapper.getPaddingBottom();
                measuredHeight = this.mArrow.getMeasuredHeight();
            }
            int i8 = bottom - (measuredHeight - i3);
            i5 = right;
            if (this.mArrowMode == 18) {
                AppCompatImageView appCompatImageView2 = this.mArrow;
                appCompatImageView2.layout(i5, i8, i5 + i2, appCompatImageView2.getMeasuredHeight() + i8);
            }
            i4 = i8 - 5;
        } else {
            i5 = this.mArrowSpaceLeft;
        }
        AppCompatImageView appCompatImageView3 = this.mArrow;
        appCompatImageView3.layout(i5, i4, i2 + i5, appCompatImageView3.getDrawable().getIntrinsicHeight() + i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAnimationPivot(float[] fArr) {
        float right;
        float bottom;
        int height;
        float f2;
        float f3;
        float f4;
        int top;
        int top2 = this.mArrow.getTop();
        int bottom2 = this.mArrow.getBottom();
        int left = this.mArrow.getLeft();
        int right2 = this.mArrow.getRight();
        int i2 = this.mRtlMode;
        boolean z2 = i2 == 1 || (i2 == 2 && ViewUtils.isLayoutRtl(this));
        int i3 = this.mArrowMode;
        if (i3 == 32) {
            ArrowPopupContentWrapper arrowPopupContentWrapper = this.mContentFrameWrapper;
            right = z2 ? arrowPopupContentWrapper.getRight() : arrowPopupContentWrapper.getLeft();
            bottom = this.mContentFrameWrapper.getBottom();
            height = this.mContentFrameWrapper.getHeight();
        } else {
            if (i3 != 64) {
                switch (i3) {
                    case 8:
                        right = this.mContentFrameWrapper.getRight() - (this.mContentFrameWrapper.getWidth() / 2.0f);
                        top = this.mContentFrameWrapper.getTop();
                        f2 = top;
                        float f5 = right;
                        f3 = f2;
                        f4 = f5;
                        break;
                    case 9:
                        ArrowPopupContentWrapper arrowPopupContentWrapper2 = this.mContentFrameWrapper;
                        right = z2 ? arrowPopupContentWrapper2.getRight() : arrowPopupContentWrapper2.getLeft();
                        top = this.mContentFrameWrapper.getTop();
                        f2 = top;
                        float f52 = right;
                        f3 = f2;
                        f4 = f52;
                        break;
                    case 10:
                        ArrowPopupContentWrapper arrowPopupContentWrapper3 = this.mContentFrameWrapper;
                        right = z2 ? arrowPopupContentWrapper3.getLeft() : arrowPopupContentWrapper3.getRight();
                        top = this.mContentFrameWrapper.getTop();
                        f2 = top;
                        float f522 = right;
                        f3 = f2;
                        f4 = f522;
                        break;
                    default:
                        switch (i3) {
                            case 16:
                                right = this.mContentFrameWrapper.getRight() - (this.mContentFrameWrapper.getWidth() / 2.0f);
                                top = this.mContentFrameWrapper.getBottom();
                                f2 = top;
                                float f5222 = right;
                                f3 = f2;
                                f4 = f5222;
                                break;
                            case 17:
                                ArrowPopupContentWrapper arrowPopupContentWrapper4 = this.mContentFrameWrapper;
                                right = z2 ? arrowPopupContentWrapper4.getLeft() : arrowPopupContentWrapper4.getRight();
                                top = this.mContentFrameWrapper.getBottom();
                                f2 = top;
                                float f52222 = right;
                                f3 = f2;
                                f4 = f52222;
                                break;
                            case 18:
                                ArrowPopupContentWrapper arrowPopupContentWrapper5 = this.mContentFrameWrapper;
                                right = z2 ? arrowPopupContentWrapper5.getRight() : arrowPopupContentWrapper5.getLeft();
                                top = this.mContentFrameWrapper.getBottom();
                                f2 = top;
                                float f522222 = right;
                                f3 = f2;
                                f4 = f522222;
                                break;
                            default:
                                f4 = (right2 + left) / 2;
                                f3 = (bottom2 + top2) / 2;
                                break;
                        }
                        break;
                }
                fArr[0] = f4;
                fArr[1] = f3;
            }
            ArrowPopupContentWrapper arrowPopupContentWrapper6 = this.mContentFrameWrapper;
            right = z2 ? arrowPopupContentWrapper6.getLeft() : arrowPopupContentWrapper6.getRight();
            bottom = this.mContentFrameWrapper.getBottom();
            height = this.mContentFrameWrapper.getHeight();
        }
        f2 = bottom - (height / 2.0f);
        float f5222222 = right;
        f3 = f2;
        f4 = f5222222;
        fArr[0] = f4;
        fArr[1] = f3;
    }

    private boolean isBottomMode() {
        return isCertainMode(16);
    }

    private boolean isCertainMode(int i2) {
        return (this.mArrowMode & i2) == i2;
    }

    private boolean isLeftMode() {
        return isCertainMode(32);
    }

    private boolean isRightMode() {
        return isCertainMode(64);
    }

    private boolean isTopMode() {
        return isCertainMode(8);
    }

    private boolean isVerticalMode() {
        return isTopMode() || isBottomMode();
    }

    private void updateArrowDrawable(int i2) {
        int i3 = this.mRtlMode;
        boolean z2 = true;
        if (i3 != 1 && (i3 != 2 || !ViewUtils.isLayoutRtl(this))) {
            z2 = false;
        }
        if (i2 == 32) {
            this.mArrow.setImageDrawable(z2 ? this.mArrowRight : this.mArrowLeft);
        }
        if (i2 == 64) {
            this.mArrow.setImageDrawable(z2 ? this.mArrowLeft : this.mArrowRight);
            return;
        }
        switch (i2) {
            case 8:
                this.mArrow.setImageDrawable(this.mTitleLayout.getVisibility() == 0 ? this.mArrowTopWithTitle : this.mArrowTop);
                break;
            case 9:
                this.mArrow.setImageDrawable(z2 ? this.mArrowTopRight : this.mArrowTopLeft);
                break;
            case 10:
                this.mArrow.setImageDrawable(z2 ? this.mArrowTopLeft : this.mArrowTopRight);
                break;
            default:
                switch (i2) {
                    case 16:
                        this.mArrow.setImageDrawable(this.mArrowBottom);
                        break;
                    case 17:
                        this.mArrow.setImageDrawable(z2 ? this.mArrowBottomLeft : this.mArrowBottomRight);
                        break;
                    case 18:
                        this.mArrow.setImageDrawable(z2 ? this.mArrowBottomRight : this.mArrowBottomLeft);
                        break;
                }
                break;
        }
    }

    public void addShadow() {
        addShadow(this.mArrow, new ViewOutlineProvider() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.4
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (view.getWidth() == 0 || view.getHeight() == 0) {
                    return;
                }
                int width = view.getWidth();
                int height = view.getHeight();
                boolean z2 = false;
                Rect rect = new Rect(0, 0, width, height);
                if (width > height) {
                    int i2 = (width - height) / 2;
                    rect.left += i2;
                    rect.right -= i2;
                } else {
                    int i3 = (height - width) / 2;
                    rect.top += i3;
                    rect.bottom -= i3;
                }
                Path path = new Path();
                int i4 = ArrowPopupView.this.mArrowMode;
                if (i4 != 32 && i4 != 64) {
                    switch (i4) {
                        case 8:
                            int i5 = rect.right;
                            path.moveTo(rect.left, rect.bottom);
                            path.quadTo((i5 + r0) / 2.0f, -rect.height(), rect.right, rect.bottom);
                            path.close();
                            break;
                        case 9:
                        case 10:
                            if ((ArrowPopupView.this.mRtlMode != 1 && ArrowPopupView.this.mArrowMode == 9) || (ArrowPopupView.this.mRtlMode == 1 && ArrowPopupView.this.mArrowMode == 10)) {
                                z2 = true;
                            }
                            path.moveTo(0.0f, ArrowPopupView.this.mArrowTop.getIntrinsicHeight());
                            if (z2) {
                                path.quadTo(0.0f, (-ArrowPopupView.this.mArrowTop.getIntrinsicHeight()) * 0.7f, rect.right * 0.52f, ArrowPopupView.this.mArrowTop.getIntrinsicHeight());
                            } else {
                                path.quadTo(rect.right, (-ArrowPopupView.this.mArrowTop.getIntrinsicHeight()) * 0.7f, rect.right * 0.5f, ArrowPopupView.this.mArrowTop.getIntrinsicHeight());
                            }
                            path.close();
                            break;
                    }
                } else {
                    if ((ArrowPopupView.this.mRtlMode != 1 && ArrowPopupView.this.mArrowMode == 32) || (ArrowPopupView.this.mRtlMode == 1 && ArrowPopupView.this.mArrowMode == 64)) {
                        z2 = true;
                    }
                    int i6 = rect.bottom;
                    int i7 = rect.top;
                    float f2 = (i6 + i7) / 2.0f;
                    if (z2) {
                        path.moveTo(rect.right, i7);
                        path.quadTo(-rect.width(), f2, rect.right, rect.bottom);
                    } else {
                        path.moveTo(rect.left, i7);
                        path.quadTo(rect.right + rect.width(), f2, rect.left, rect.bottom);
                    }
                    path.close();
                }
                if (path.isConvex()) {
                    outline.setConvexPath(path);
                } else {
                    Log.d(ArrowPopupView.TAG, "outline path is not convex");
                    outline.setOval(rect);
                }
            }
        });
        addShadow(this.mContentFrameWrapper, new ViewOutlineProvider() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.5
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (view.getWidth() == 0 || view.getHeight() == 0) {
                    return;
                }
                Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
                rect.bottom -= view.getPaddingBottom();
                rect.top += view.getPaddingTop();
                rect.right -= view.getPaddingRight();
                rect.left += view.getPaddingLeft();
                outline.setRoundRect(rect, ArrowPopupView.this.getContext().getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_view_round_corners));
            }
        });
    }

    public void animateToDismiss() {
        if (this.mIsDismissing) {
            return;
        }
        AnimatorSet animatorSet = this.mAnimator;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimationSet animationSet = this.mAnimationSet;
        if (animationSet != null) {
            animationSet.cancel();
        }
        this.mAnimationSet = new AnimationSet(true);
        float[] fArr = new float[2];
        getAnimationPivot(fArr);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, 0, fArr[0], 0, fArr[1]);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        if (DeviceHelper.isFeatureWholeAnim()) {
            scaleAnimation.setDuration(150L);
            alphaAnimation.setDuration(150L);
        } else {
            this.mAnimationSet.setDuration(0L);
        }
        this.mAnimationSet.addAnimation(scaleAnimation);
        if (this.mAlphaAnimationEnabled) {
            this.mAnimationSet.addAnimation(alphaAnimation);
        }
        this.mAnimationSet.setAnimationListener(this.mHideAnimatorListener);
        this.mAnimationSet.setInterpolator(new AccelerateInterpolator(2.0f));
        startAnimation(this.mAnimationSet);
    }

    public void animateToShow() {
        invalidate();
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.7
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ArrowPopupView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ArrowPopupView.this.mAnimator != null) {
                    ArrowPopupView.this.mAnimator.cancel();
                }
                if (ArrowPopupView.this.mAnimationSet != null) {
                    ArrowPopupView.this.mAnimationSet.cancel();
                }
                ArrowPopupView.this.mAnimationSet = new AnimationSet(true);
                float[] fArr = new float[2];
                ArrowPopupView.this.getAnimationPivot(fArr);
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, 0, fArr[0], 0, fArr[1]);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                if (DeviceHelper.isFeatureWholeAnim()) {
                    alphaAnimation.setDuration(100L);
                    scaleAnimation.setDuration(280L);
                } else {
                    ArrowPopupView.this.mAnimationSet.setDuration(0L);
                }
                ArrowPopupView.this.mAnimationSet.addAnimation(scaleAnimation);
                if (ArrowPopupView.this.mAlphaAnimationEnabled) {
                    ArrowPopupView.this.mAnimationSet.addAnimation(alphaAnimation);
                }
                ArrowPopupView.this.mAnimationSet.setAnimationListener(ArrowPopupView.this.mShowAnimationListener);
                ArrowPopupView.this.mAnimationSet.setInterpolator(new DecelerateInterpolator(1.5f));
                ArrowPopupView arrowPopupView = ArrowPopupView.this;
                arrowPopupView.startAnimation(arrowPopupView.mAnimationSet);
                return true;
            }
        });
    }

    public void enableShowingAnimation(boolean z2) {
        this.mShowingAnimation = z2;
    }

    public int getArrowHeight(int i2) {
        int i3 = this.mArrowMode;
        if (i3 == 32 || i3 == 64) {
            return getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_arrow_height_horizontal);
        }
        switch (i3) {
            case 8:
            case 9:
            case 10:
                break;
            default:
                switch (i3) {
                    case 16:
                    case 17:
                    case 18:
                        break;
                    default:
                        return 0;
                }
                break;
        }
        return getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_arrow_height_vertical);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int[] getArrowImageHeightAndWidth(int i2) {
        int[] iArr = new int[2];
        if (i2 == 32) {
            iArr[0] = this.mArrowLeft.getIntrinsicHeight();
            iArr[1] = this.mArrowLeft.getIntrinsicWidth();
        } else if (i2 != 64) {
            switch (i2) {
                case 8:
                    iArr[0] = this.mArrowTop.getIntrinsicHeight();
                    iArr[1] = this.mArrowTop.getIntrinsicWidth();
                    break;
                case 9:
                    iArr[0] = this.mArrowTopLeft.getIntrinsicHeight();
                    iArr[1] = this.mArrowTopLeft.getIntrinsicWidth();
                    break;
                case 10:
                    iArr[0] = this.mArrowTopRight.getIntrinsicHeight();
                    iArr[1] = this.mArrowTopRight.getIntrinsicWidth();
                    break;
                default:
                    switch (i2) {
                        case 16:
                            iArr[0] = this.mArrowBottom.getIntrinsicHeight();
                            iArr[1] = this.mArrowBottom.getIntrinsicWidth();
                            break;
                        case 17:
                            iArr[0] = this.mArrowBottomRight.getIntrinsicHeight();
                            iArr[1] = this.mArrowBottomRight.getIntrinsicWidth();
                            break;
                        case 18:
                            iArr[0] = this.mArrowBottomLeft.getIntrinsicHeight();
                            iArr[1] = this.mArrowBottomLeft.getIntrinsicWidth();
                            break;
                    }
                    break;
            }
        } else {
            iArr[0] = this.mArrowRight.getIntrinsicHeight();
            iArr[1] = this.mArrowRight.getIntrinsicWidth();
        }
        return iArr;
    }

    public int getArrowMode() {
        return this.mArrowMode;
    }

    public int getArrowWidth(int i2) {
        if (i2 == 32 || i2 == 64) {
            return getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_arrow_width_horizontal);
        }
        switch (i2) {
            case 8:
            case 9:
            case 10:
                break;
            default:
                switch (i2) {
                    case 16:
                    case 17:
                    case 18:
                        break;
                    default:
                        return 0;
                }
                break;
        }
        return getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_arrow_width_vertical);
    }

    public int getContentFrameWrapperBottomPadding() {
        return this.mContentFrameWrapper.getPaddingBottom();
    }

    public int getContentFrameWrapperTopPadding() {
        return this.mContentFrameWrapper.getPaddingTop();
    }

    public View getContentView() {
        if (this.mContentFrame.getChildCount() > 0) {
            return this.mContentFrame.getChildAt(0);
        }
        return null;
    }

    public AppCompatButton getNegativeButton() {
        return this.mNegativeButton;
    }

    public int getPopupElevation() {
        return this.mElevation;
    }

    public AppCompatButton getPositiveButton() {
        return this.mPositiveButton;
    }

    @Deprecated
    public float getRollingPercent() {
        return 1.0f;
    }

    public int getTitleHeight() {
        if (this.mTitleLayout.getVisibility() != 8) {
            return this.mTitleLayout.getMeasuredHeight();
        }
        return 0;
    }

    public boolean isTitleEmpty() {
        return TextUtils.isEmpty(this.mTitleText.getText());
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final View contentView = getContentView();
        if (contentView != null) {
            contentView.post(new Runnable() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.6
                @Override // java.lang.Runnable
                public void run() {
                    contentView.requestLayout();
                    contentView.invalidate();
                }
            });
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View view = this.mAnchor;
        if (view != null) {
            view.removeOnLayoutChangeListener(this.mAnchorTrackListener);
            this.mAnchor.removeCallbacks(this.mArrowLayoutTask);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int right;
        int right2;
        float f2;
        if (this.mBackground != null) {
            return;
        }
        int width = this.mSpaceLeft + (this.mContentFrameWrapper.getWidth() / 2);
        int height = this.mSpaceTop + (this.mContentFrameWrapper.getHeight() / 2);
        int i2 = this.mArrowMode;
        if (i2 == 8) {
            int measuredWidth = this.mArrowSpaceLeft + (this.mArrow.getMeasuredWidth() / 2);
            right = measuredWidth - this.mSpaceLeft;
            right2 = this.mContentFrameWrapper.getRight() - measuredWidth;
            f2 = 0.0f;
        } else if (i2 == 16) {
            int measuredWidth2 = this.mArrowSpaceLeft + (this.mArrow.getMeasuredWidth() / 2);
            right = this.mContentFrameWrapper.getRight() - measuredWidth2;
            right2 = measuredWidth2 - this.mSpaceLeft;
            f2 = 180.0f;
        } else if (i2 == 32) {
            int measuredHeight = this.mArrowSpaceTop + (this.mArrow.getMeasuredHeight() / 2);
            right = this.mContentFrameWrapper.getBottom() - measuredHeight;
            right2 = measuredHeight - this.mSpaceTop;
            f2 = -90.0f;
        } else if (i2 != 64) {
            f2 = 0.0f;
            right = 0;
            right2 = 0;
        } else {
            int measuredHeight2 = this.mArrowSpaceTop + (this.mArrow.getMeasuredHeight() / 2);
            right = measuredHeight2 - this.mSpaceTop;
            right2 = this.mContentFrameWrapper.getBottom() - measuredHeight2;
            f2 = 90.0f;
        }
        int iSave = canvas.save();
        canvas.rotate(f2, width, height);
        int i3 = this.mArrowMode;
        if (i3 == 8 || i3 == 16) {
            canvas.translate(this.mSpaceLeft, this.mSpaceTop);
            this.mBackgroundLeft.setBounds(0, 0, right, this.mContentFrameWrapper.getHeight());
            canvas.translate(0.0f, isTopMode() ? this.mTranslationValue : -this.mTranslationValue);
            this.mBackgroundLeft.draw(canvas);
            canvas.translate(right, 0.0f);
            this.mBackgroundRight.setBounds(0, 0, right2, this.mContentFrameWrapper.getHeight());
            this.mBackgroundRight.draw(canvas);
        } else if (i3 == 32 || i3 == 64) {
            canvas.translate(width - (this.mContentFrameWrapper.getHeight() / 2), height - (this.mContentFrameWrapper.getWidth() / 2));
            this.mBackgroundLeft.setBounds(0, 0, right, this.mContentFrameWrapper.getWidth());
            canvas.translate(0.0f, isLeftMode() ? this.mTranslationValue : -this.mTranslationValue);
            this.mBackgroundLeft.draw(canvas);
            canvas.translate(right, 0.0f);
            this.mBackgroundRight.setBounds(0, 0, right2, this.mContentFrameWrapper.getWidth());
            this.mBackgroundRight.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mArrow = (AppCompatImageView) findViewById(R.id.popup_arrow);
        this.mContentFrame = (FrameLayout) findViewById(android.R.id.content);
        ArrowPopupContentWrapper arrowPopupContentWrapper = (ArrowPopupContentWrapper) findViewById(R.id.content_wrapper);
        this.mContentFrameWrapper = arrowPopupContentWrapper;
        arrowPopupContentWrapper.setArrowBackgroundPaintColor(this.mArrowBackgroundPaintColor);
        this.mContentFrameWrapper.setMinimumHeight(getContext().getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_view_min_height));
        if (this.mBackgroundLeft != null && this.mBackgroundRight != null) {
            Rect rect = new Rect();
            this.mBackgroundLeft.getPadding(rect);
            ArrowPopupContentWrapper arrowPopupContentWrapper2 = this.mContentFrameWrapper;
            int i2 = rect.top;
            arrowPopupContentWrapper2.setPadding(i2, i2, i2, i2);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.title_layout);
        this.mTitleLayout = linearLayout;
        linearLayout.setBackground(this.mTitleBackground);
        this.mTitleText = (AppCompatTextView) findViewById(android.R.id.title);
        this.mPositiveButton = (AppCompatButton) findViewById(16908314);
        this.mNegativeButton = (AppCompatButton) findViewById(16908313);
        this.mPositiveClickListener = new WrapperOnClickListener();
        this.mNegativeClickListener = new WrapperOnClickListener();
        this.mPositiveButton.setOnClickListener(this.mPositiveClickListener);
        this.mNegativeButton.setOnClickListener(this.mNegativeClickListener);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (!this.mAnchor.isAttachedToWindow()) {
            if (this.mArrowPopupWindow.isShowing()) {
                this.mArrowPopupWindow.dismiss();
            }
        } else {
            if (this.mArrowMode == 0) {
                adjustArrowMode();
            }
            updateArrowDrawable(this.mArrowMode);
            arrowLayout();
            this.mHasFirstLayout = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        Rect rect = this.mTmpRect;
        this.mContentFrameWrapper.getHitRect(rect);
        if (motionEvent.getAction() != 0 || rect.contains(x2, y2)) {
            View.OnTouchListener onTouchListener = this.mTouchInterceptor;
            return onTouchListener != null && onTouchListener.onTouch(view, motionEvent);
        }
        this.mArrowPopupWindow.dismiss(true);
        return true;
    }

    public void setAlphaAnimation(boolean z2) {
        this.mAlphaAnimationEnabled = z2;
    }

    public void setAnchor(View view) {
        View view2 = this.mAnchor;
        if (view2 != null) {
            view2.removeOnLayoutChangeListener(this.mAnchorTrackListener);
        }
        this.mAnchor = view;
        this.mHasFirstLayout = false;
        if (!this.mEnableTrackAnchor || view == null) {
            return;
        }
        view.addOnLayoutChangeListener(this.mAnchorTrackListener);
    }

    public void setArrowMode(int i2) {
        this.mArrowMode = i2;
        updateArrowDrawable(i2);
    }

    public void setArrowPopupWindow(ArrowPopupWindow arrowPopupWindow) {
        this.mArrowPopupWindow = arrowPopupWindow;
    }

    public void setAutoDismiss(boolean z2) {
        this.mAutoDismiss = z2;
    }

    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(-2, -2));
    }

    public void setEnableTrackAnchor(boolean z2) {
        this.mEnableTrackAnchor = z2;
    }

    public void setLayoutRtlMode(int i2) {
        if (i2 > 2 || i2 < 0) {
            this.mRtlMode = 2;
        } else {
            this.mRtlMode = i2;
        }
    }

    public void setNegativeButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.mNegativeButton.setText(charSequence);
        this.mNegativeButton.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mNegativeClickListener.setOnClickListener(onClickListener);
    }

    public void setOffset(int i2, int i3) {
        this.mOffsetX = i2;
        this.mOffsetY = i3;
    }

    public void setPositiveButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.mPositiveButton.setText(charSequence);
        this.mPositiveButton.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mPositiveClickListener.setOnClickListener(onClickListener);
    }

    @Deprecated
    public void setRollingPercent(float f2) {
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitleLayout.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.mTitleText.setText(charSequence);
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.mTouchInterceptor = onTouchListener;
    }

    public ArrowPopupView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.arrowPopupViewStyle);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mContentFrame.removeAllViews();
        if (view != null) {
            this.mContentFrame.addView(view, layoutParams);
        }
    }

    public ArrowPopupView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTmpRect = new Rect();
        this.mTmpRectF = new RectF();
        this.mAutoDismiss = true;
        this.mRtlMode = 2;
        this.mShowingAnimation = false;
        this.mShowAnimationListener = new Animation.AnimationListener() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ArrowPopupView.this.mAnimationSet = null;
                if (ArrowPopupView.this.mShowingAnimation) {
                    ArrowPopupView.this.animateShowing();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.mHideAnimatorListener = new Animation.AnimationListener() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                ArrowPopupView.this.mIsDismissing = false;
                ArrowPopupView.this.mAnimationSet = null;
                ArrowPopupView.this.mArrowPopupWindow.dismiss();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.mEnableTrackAnchor = true;
        this.mHasFirstLayout = false;
        this.mArrowLayoutTask = new Runnable() { // from class: miuix.popupwidget.internal.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6140a.arrowLayout();
            }
        };
        this.mAnchorTrackListener = new View.OnLayoutChangeListener() { // from class: miuix.popupwidget.internal.widget.ArrowPopupView.3
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                if (!ArrowPopupView.this.mHasFirstLayout || view == null) {
                    return;
                }
                view.post(ArrowPopupView.this.mArrowLayoutTask);
            }
        };
        this.mArrowMode = 0;
        this.mAlphaAnimationEnabled = true;
        CompatViewMethod.setForceDarkAllowed(this, false);
        this.mAutoDismiss = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ArrowPopupView, i2, R.style.Widget_ArrowPopupView_DayNight);
        this.mArrowBackgroundPaintColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ArrowPopupView_arrowBackgroundColor, getResources().getColor(R.color.miuix_appcompat_arrow_popup_background_color));
        this.mBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_contentBackground);
        this.mBackgroundLeft = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_backgroundLeft);
        this.mBackgroundRight = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_backgroundRight);
        this.mTitleBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_titleBackground);
        this.mArrowTop = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_topArrow);
        this.mArrowTopWithTitle = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_topArrowWithTitle);
        this.mArrowBottom = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_bottomArrow);
        this.mArrowRight = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_rightArrow);
        this.mArrowLeft = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_leftArrow);
        this.mArrowTopLeft = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_topLeftArrow);
        this.mArrowTopRight = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_topRightArrow);
        this.mArrowBottomRight = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_bottomRightArrow);
        this.mArrowBottomLeft = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ArrowPopupView_bottomLeftArrow);
        this.mElevation = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ArrowPopupView_android_elevation, getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_arrow_popup_window_elevation));
        typedArrayObtainStyledAttributes.recycle();
        this.mMinBorder = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_window_min_border);
    }

    private void addShadow(View view, ViewOutlineProvider viewOutlineProvider) {
        view.setOutlineProvider(viewOutlineProvider);
        view.setElevation(this.mElevation);
    }

    public void setContentView(int i2) {
        setContentView(LayoutInflater.from(getContext()).inflate(i2, (ViewGroup) null));
    }
}
