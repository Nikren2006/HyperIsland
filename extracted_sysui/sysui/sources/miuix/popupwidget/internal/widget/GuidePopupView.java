package miuix.popupwidget.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import miuix.popupwidget.R;
import miuix.popupwidget.widget.GuidePopupWindow;

/* JADX INFO: loaded from: classes5.dex */
public class GuidePopupView extends FrameLayout implements View.OnTouchListener {
    public static final int ARROW_BOTTOM_LEFT_MODE = 7;
    public static final int ARROW_BOTTOM_MODE = 0;
    public static final int ARROW_BOTTOM_RIGHT_MODE = 5;
    public static final int ARROW_LEFT_MODE = 3;
    public static final int ARROW_NONE_MODE = -1;
    public static final int ARROW_RIGHT_MODE = 2;
    public static final int ARROW_TOP_LEFT_MODE = 4;
    public static final int ARROW_TOP_MODE = 1;
    public static final int ARROW_TOP_RIGHT_MODE = 6;
    private View mAnchor;
    private int mAnchorHeight;
    private int mAnchorLocationX;
    private int mAnchorLocationY;
    private int mAnchorWidth;
    private ObjectAnimator mAnimator;
    private int mArrowMode;
    private int mColorBackground;
    private Context mContext;
    private int mDefaultOffset;
    private GuidePopupWindow mGuidePopupWindow;
    private Animator.AnimatorListener mHideAnimatorListener;
    private boolean mIsDismissing;
    private boolean mIsMirrored;
    private float mLineLength;
    private int mMinBorder;
    private LinearLayout mMirroredTextGroup;
    private int mOffsetX;
    private int mOffsetY;
    private final Paint mPaint;
    private Animator.AnimatorListener mShowAnimatorListener;
    private float mStartPointRadius;
    private float mTextCircleRadius;
    private ColorStateList mTextColor;
    private LinearLayout mTextGroup;
    private int mTextSize;
    private View.OnTouchListener mTouchInterceptor;
    private boolean mUseDefaultOffset;

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
            GuidePopupView.this.mGuidePopupWindow.dismiss(true);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }
    }

    public GuidePopupView(Context context) {
        this(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void adjustArrowMode() {
        /*
            r12 = this;
            int r0 = r12.getWidth()
            int r1 = r12.getHeight()
            int r2 = r12.mAnchorLocationY
            int r3 = r1 - r2
            int r4 = r12.mAnchorHeight
            int r3 = r3 - r4
            int r5 = r12.mAnchorLocationX
            int r6 = r0 - r5
            int r7 = r12.mAnchorWidth
            int r6 = r6 - r7
            int[] r3 = new int[]{r2, r3, r5, r6}
            r6 = 2
            int r7 = r7 / r6
            int r5 = r5 + r7
            int r4 = r4 / r6
            int r2 = r2 + r4
            r4 = 0
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = r7
            r7 = r4
        L24:
            r9 = 4
            if (r4 >= r9) goto L35
            r10 = r3[r4]
            int r11 = r12.mMinBorder
            if (r10 < r11) goto L2e
            goto L36
        L2e:
            if (r10 <= r8) goto L32
            r7 = r4
            r8 = r10
        L32:
            int r4 = r4 + 1
            goto L24
        L35:
            r4 = r7
        L36:
            r3 = 5
            r7 = 7
            if (r4 == 0) goto L74
            r8 = 6
            r10 = 1
            if (r4 == r10) goto L65
            if (r4 == r6) goto L54
            r0 = 3
            if (r4 == r0) goto L44
            goto L83
        L44:
            float r0 = (float) r2
            float r3 = r12.mTextCircleRadius
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L4c
            goto L84
        L4c:
            int r1 = r1 - r2
            float r0 = (float) r1
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L83
        L52:
            r9 = r7
            goto L84
        L54:
            float r0 = (float) r2
            float r5 = r12.mTextCircleRadius
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L5d
        L5b:
            r9 = r8
            goto L84
        L5d:
            int r1 = r1 - r2
            float r0 = (float) r1
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L83
        L63:
            r9 = r3
            goto L84
        L65:
            float r1 = (float) r5
            float r2 = r12.mTextCircleRadius
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 >= 0) goto L6d
            goto L84
        L6d:
            int r0 = r0 - r5
            float r0 = (float) r0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L83
            goto L5b
        L74:
            float r1 = (float) r5
            float r2 = r12.mTextCircleRadius
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 >= 0) goto L7c
            goto L52
        L7c:
            int r0 = r0 - r5
            float r0 = (float) r0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L83
            goto L63
        L83:
            r9 = r4
        L84:
            r12.setArrowMode(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.popupwidget.internal.widget.GuidePopupView.adjustArrowMode():void");
    }

    private void arrowLayout() {
        caculateDefaultOffset();
        drawText(this.mArrowMode, this.mTextGroup, this.mOffsetX, this.mOffsetY);
        if (this.mIsMirrored) {
            drawText(getMirroredMode(), this.mMirroredTextGroup, -this.mOffsetX, -this.mOffsetY);
        }
    }

    private void caculateDefaultOffset() {
        if (!this.mUseDefaultOffset) {
            this.mDefaultOffset = 0;
            return;
        }
        int i2 = this.mAnchorWidth / 2;
        int i3 = this.mAnchorHeight / 2;
        int iSqrt = (int) Math.sqrt(Math.pow(i2, 2.0d) + Math.pow(i3, 2.0d));
        int i4 = this.mArrowMode;
        if (i4 == 0 || i4 == 1) {
            this.mDefaultOffset = i3;
        } else if (i4 == 2 || i4 == 3) {
            this.mDefaultOffset = i2;
        } else {
            this.mDefaultOffset = iSqrt;
        }
    }

    private void drawPopup(Canvas canvas, int i2, int i3, int i4) {
        float f2;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        float f3 = this.mAnchorLocationX + (this.mAnchorWidth / 2) + i3;
        float f4 = this.mAnchorLocationY + (this.mAnchorHeight / 2) + i4;
        switch (i2) {
            case 0:
                f2 = 180.0f;
                break;
            case 1:
            default:
                f2 = 0.0f;
                break;
            case 2:
                f2 = 90.0f;
                break;
            case 3:
                f2 = -90.0f;
                break;
            case 4:
                f2 = -45.0f;
                break;
            case 5:
                f2 = 135.0f;
                break;
            case 6:
                f2 = 45.0f;
                break;
            case 7:
                f2 = -135.0f;
                break;
        }
        canvas.save();
        canvas.rotate(f2, f3, f4);
        canvas.translate(0.0f, this.mDefaultOffset);
        int iSave = canvas.save();
        canvas.clipRect(f3 - 2.0f, f4, f3 + 2.0f, f4 + this.mStartPointRadius, Region.Op.DIFFERENCE);
        canvas.drawCircle(f3, f4, this.mStartPointRadius, this.mPaint);
        canvas.restoreToCount(iSave);
        Paint paint = this.mPaint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        this.mPaint.setStrokeWidth(4.0f);
        canvas.drawLine(f3, f4, f3, f4 + this.mLineLength, this.mPaint);
        float f5 = f4 + this.mLineLength + this.mTextCircleRadius;
        this.mPaint.setStyle(style);
        this.mPaint.setStrokeWidth(4.0f);
        canvas.drawCircle(f3, f5, this.mTextCircleRadius, this.mPaint);
        canvas.restore();
    }

    private void drawText(int i2, LinearLayout linearLayout, int i3, int i4) {
        int measuredWidth;
        float f2;
        int measuredHeight;
        int i5;
        int measuredHeight2;
        float f3 = this.mDefaultOffset + this.mLineLength + this.mTextCircleRadius;
        int i6 = this.mAnchorLocationX + (this.mAnchorWidth / 2);
        int i7 = this.mAnchorLocationY + (this.mAnchorHeight / 2);
        switch (i2) {
            case 0:
            case 5:
            case 7:
                measuredWidth = i6 - (linearLayout.getMeasuredWidth() / 2);
                f2 = i7 - f3;
                measuredHeight = linearLayout.getMeasuredHeight() / 2;
                i5 = (int) (f2 - measuredHeight);
                break;
            case 1:
            case 4:
            case 6:
                measuredWidth = i6 - (linearLayout.getMeasuredWidth() / 2);
                f2 = i7 + f3;
                measuredHeight = linearLayout.getMeasuredHeight() / 2;
                i5 = (int) (f2 - measuredHeight);
                break;
            case 2:
                measuredWidth = (int) ((i6 - f3) - (linearLayout.getMeasuredWidth() / 2));
                measuredHeight2 = linearLayout.getMeasuredHeight() / 2;
                i5 = i7 - measuredHeight2;
                break;
            case 3:
                measuredWidth = (int) ((i6 + f3) - (linearLayout.getMeasuredWidth() / 2));
                measuredHeight2 = linearLayout.getMeasuredHeight() / 2;
                i5 = i7 - measuredHeight2;
                break;
            default:
                measuredWidth = 0;
                i5 = 0;
                break;
        }
        int iSin = (int) (((double) f3) * Math.sin(0.7853981633974483d));
        int i8 = (int) (f3 - iSin);
        if (i2 != 4) {
            if (i2 == 5) {
                measuredWidth -= iSin;
            } else {
                if (i2 != 6) {
                    if (i2 == 7) {
                        measuredWidth += iSin;
                    }
                    int i9 = measuredWidth + i3;
                    int i10 = i5 + i4;
                    linearLayout.layout(i9, i10, linearLayout.getMeasuredWidth() + i9, linearLayout.getMeasuredHeight() + i10);
                }
                measuredWidth -= iSin;
            }
            i5 += i8;
            int i92 = measuredWidth + i3;
            int i102 = i5 + i4;
            linearLayout.layout(i92, i102, linearLayout.getMeasuredWidth() + i92, linearLayout.getMeasuredHeight() + i102);
        }
        measuredWidth += iSin;
        i5 -= i8;
        int i922 = measuredWidth + i3;
        int i1022 = i5 + i4;
        linearLayout.layout(i922, i1022, linearLayout.getMeasuredWidth() + i922, linearLayout.getMeasuredHeight() + i1022);
    }

    private int getMirroredMode() {
        int i2 = this.mArrowMode;
        if (i2 == -1) {
            return -1;
        }
        return i2 % 2 == 0 ? i2 + 1 : i2 - 1;
    }

    public void addGuideTextView(LinearLayout linearLayout, String str) {
        String[] strArrSplit;
        if (TextUtils.isEmpty(str) || (strArrSplit = str.split("\n")) == null || strArrSplit.length == 0) {
            return;
        }
        for (String str2 : strArrSplit) {
            AppCompatTextView appCompatTextView = (AppCompatTextView) FrameLayout.inflate(this.mContext, R.layout.miuix_appcompat_guide_popup_text_view, null);
            appCompatTextView.setText(str2);
            appCompatTextView.setTextSize(0, this.mTextSize);
            ColorStateList colorStateList = this.mTextColor;
            if (colorStateList != null) {
                appCompatTextView.setTextColor(colorStateList);
            }
            linearLayout.addView(appCompatTextView);
        }
    }

    public void animateToDismiss() {
        if (this.mIsDismissing) {
            return;
        }
        ObjectAnimator objectAnimator = this.mAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<GuidePopupView, Float>) View.ALPHA, 0.0f);
        this.mAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        this.mAnimator.addListener(this.mHideAnimatorListener);
        this.mAnimator.start();
    }

    public void animateToShow() {
        setAlpha(0.0f);
        invalidate();
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: miuix.popupwidget.internal.widget.GuidePopupView.3
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                GuidePopupView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                if (GuidePopupView.this.mAnimator != null) {
                    GuidePopupView.this.mAnimator.cancel();
                }
                GuidePopupView guidePopupView = GuidePopupView.this;
                guidePopupView.mAnimator = ObjectAnimator.ofFloat(guidePopupView, (Property<GuidePopupView, Float>) View.ALPHA, 1.0f);
                GuidePopupView.this.mAnimator.setDuration(300L);
                GuidePopupView.this.mAnimator.addListener(GuidePopupView.this.mShowAnimatorListener);
                GuidePopupView.this.mAnimator.start();
                return true;
            }
        });
    }

    public void clearOffset() {
        setOffset(0, 0);
        this.mUseDefaultOffset = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(this.mAnchorLocationX, this.mAnchorLocationY);
        this.mAnchor.setDrawingCacheEnabled(true);
        this.mAnchor.buildDrawingCache();
        canvas.drawBitmap(this.mAnchor.getDrawingCache(), 0.0f, 0.0f, (Paint) null);
        this.mAnchor.setDrawingCacheEnabled(false);
        canvas.restore();
        drawPopup(canvas, this.mArrowMode, this.mOffsetX, this.mOffsetY);
        if (this.mIsMirrored) {
            drawPopup(canvas, getMirroredMode(), -this.mOffsetX, -this.mOffsetY);
        }
    }

    public int getArrowMode() {
        return this.mArrowMode;
    }

    public int getColorBackground() {
        return this.mColorBackground;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mTextGroup = (LinearLayout) findViewById(R.id.text_group);
        this.mMirroredTextGroup = (LinearLayout) findViewById(R.id.mirrored_text_group);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.mAnchorWidth == 0 || this.mAnchorHeight == 0) {
            setAnchor(this.mAnchor);
        }
        this.mTextCircleRadius = (float) Math.max(Math.sqrt(Math.pow(this.mTextGroup.getMeasuredWidth(), 2.0d) + Math.pow(this.mTextGroup.getMeasuredHeight(), 2.0d)) / 2.0d, this.mTextCircleRadius);
        if (this.mIsMirrored) {
            this.mTextCircleRadius = (float) Math.max(Math.sqrt(Math.pow(this.mMirroredTextGroup.getMeasuredWidth(), 2.0d) + Math.pow(this.mMirroredTextGroup.getMeasuredHeight(), 2.0d)) / 2.0d, this.mTextCircleRadius);
        }
        if (this.mArrowMode == -1) {
            adjustArrowMode();
        } else {
            arrowLayout();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        int i2 = this.mAnchorLocationX;
        Rect rect = new Rect(i2, this.mAnchorLocationY, this.mAnchor.getWidth() + i2, this.mAnchorLocationY + this.mAnchor.getHeight());
        if (motionEvent.getAction() == 0 && rect.contains(x2, y2)) {
            this.mAnchor.callOnClick();
            return true;
        }
        this.mGuidePopupWindow.dismiss(true);
        return true;
    }

    public void setAnchor(View view) {
        this.mAnchor = view;
        this.mAnchorWidth = view.getWidth();
        this.mAnchorHeight = this.mAnchor.getHeight();
        int[] iArr = new int[2];
        this.mAnchor.getLocationInWindow(iArr);
        this.mAnchorLocationX = iArr[0];
        this.mAnchorLocationY = iArr[1];
    }

    public void setArrowMode(int i2) {
        this.mArrowMode = i2;
    }

    public void setGuidePopupWindow(GuidePopupWindow guidePopupWindow) {
        this.mGuidePopupWindow = guidePopupWindow;
    }

    public void setOffset(int i2, int i3) {
        this.mOffsetX = i2;
        this.mOffsetY = i3;
        this.mUseDefaultOffset = false;
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.mTouchInterceptor = onTouchListener;
    }

    public GuidePopupView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.guidePopupViewStyle);
    }

    public void setArrowMode(int i2, boolean z2) {
        setArrowMode(i2);
        this.mIsMirrored = z2;
        if (z2) {
            this.mMirroredTextGroup.setVisibility(0);
        } else {
            this.mMirroredTextGroup.setVisibility(8);
        }
    }

    public GuidePopupView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mUseDefaultOffset = true;
        this.mTextColor = null;
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mShowAnimatorListener = new AnimatorListenerAdapter() { // from class: miuix.popupwidget.internal.widget.GuidePopupView.1
            private boolean mCancel;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                this.mCancel = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (this.mCancel) {
                    return;
                }
                GuidePopupView.this.mAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                this.mCancel = false;
            }
        };
        this.mHideAnimatorListener = new AnimatorListenerAdapter() { // from class: miuix.popupwidget.internal.widget.GuidePopupView.2
            private boolean mCancel;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                this.mCancel = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (this.mCancel) {
                    return;
                }
                GuidePopupView.this.mIsDismissing = false;
                GuidePopupView.this.mAnimator = null;
                GuidePopupView.this.mGuidePopupWindow.dismiss();
                GuidePopupView.this.setArrowMode(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                this.mCancel = false;
                GuidePopupView.this.mIsDismissing = true;
            }
        };
        this.mArrowMode = -1;
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GuidePopupView, i2, R.style.Widget_GuidePopupView_DayNight);
        this.mStartPointRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.GuidePopupView_startPointRadius, 0.0f);
        this.mLineLength = typedArrayObtainStyledAttributes.getDimension(R.styleable.GuidePopupView_lineLength, 0.0f);
        this.mTextCircleRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.GuidePopupView_textCircleRadius, 0.0f);
        this.mColorBackground = typedArrayObtainStyledAttributes.getColor(R.styleable.GuidePopupView_android_colorBackground, 0);
        paint.setColor(typedArrayObtainStyledAttributes.getColor(R.styleable.GuidePopupView_paintColor, -1));
        this.mTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.GuidePopupView_android_textSize, 15);
        this.mTextColor = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.GuidePopupView_android_textColor);
        typedArrayObtainStyledAttributes.recycle();
        this.mMinBorder = (int) (this.mLineLength + (this.mTextCircleRadius * 2.5f));
    }
}
