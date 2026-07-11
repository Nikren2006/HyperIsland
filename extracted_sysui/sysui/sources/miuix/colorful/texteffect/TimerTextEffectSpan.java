package miuix.colorful.texteffect;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.text.Bidi;

/* JADX INFO: loaded from: classes3.dex */
public class TimerTextEffectSpan extends ReplacementSpan {
    private int mBackgroundColor;

    @Nullable
    private Matrix mBitmapMatrix;
    private RectF mClipRect;
    private int mContentGravity;

    @Nullable
    private String mDrawNewText;

    @Nullable
    private String mDrawOldText;
    private final Paint mEffectPaint;
    private boolean mEnableAnim;
    private boolean mEnableAnimWithInit;

    @NonNull
    private final TextSwitcherAnimator mEnterController;

    @Nullable
    private RenderNode mEnterNode;

    @NonNull
    private final TextSwitcherAnimator mExitController;

    @Nullable
    private RenderNode mExitNode;
    private int mForegroundColor;
    private final WeakReference<TimerTextEffectView> mHost;
    private final TextUtils.TruncateAt mHostEllipsize;
    private boolean mIsHostSpecifiedWidth;
    private boolean mIsNewRtl;
    private boolean mIsNewTextEllipsize;
    private boolean mIsOldRtl;
    private boolean mIsOldTextEllipsize;
    private boolean mIsRtl;
    private boolean mIsRunningAnim;
    private int mLayoutDirection;

    @Nullable
    private String mNewText;

    @Nullable
    private Integer mNewTextColor;

    @Nullable
    private Paint mNewTextEffectPaint;
    private float mNumberSize;

    @Nullable
    private String mOldText;

    @Nullable
    private Integer mOldTextColor;
    private float[] mPaddings;
    private RectF mRoundRect;
    private int mSize;
    private int mSortIndex;

    @Nullable
    private Bitmap mTextEnterBitmap;

    @Nullable
    private Bitmap mTextExitBitmap;

    public TimerTextEffectSpan(@NonNull TimerTextEffectView timerTextEffectView, int i2, int i3, float[] fArr, int i4) {
        Paint paint = new Paint();
        this.mEffectPaint = paint;
        this.mEnableAnimWithInit = false;
        this.mIsRunningAnim = false;
        this.mRoundRect = null;
        this.mClipRect = null;
        this.mHost = new WeakReference<>(timerTextEffectView);
        this.mHostEllipsize = timerTextEffectView.getEllipsize();
        this.mContentGravity = timerTextEffectView.getGravity();
        this.mLayoutDirection = timerTextEffectView.getLayoutDirection();
        this.mEnterController = new TextSwitcherAnimator(timerTextEffectView, 0);
        this.mExitController = new TextSwitcherAnimator(timerTextEffectView, 1);
        this.mBackgroundColor = i2;
        this.mForegroundColor = i3;
        this.mSortIndex = i4;
        this.mPaddings = fArr;
        this.mEnableAnim = timerTextEffectView.isEnableEffect();
        this.mNumberSize = 0.0f;
        paint.setColor(this.mForegroundColor);
        paint.setTextSize(timerTextEffectView.getTextSize());
        if (i2 != 0) {
            this.mRoundRect = new RectF();
            this.mClipRect = new RectF();
        }
        this.mBitmapMatrix = new Matrix();
        this.mEnterNode = new RenderNode("n_node_in_" + i4);
        this.mExitNode = new RenderNode("n_node_out_" + i4);
    }

    private boolean isNumeric() {
        return this.mSortIndex >= 0;
    }

    private int measureForSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            if (charSequence != null) {
                int i4 = i3 - i2;
                paint.getFontMetricsInt(charSequence, i2, i4, i2, i4, false, fontMetricsInt);
            } else {
                paint.getFontMetricsInt(fontMetricsInt);
            }
        }
        return Math.round(measureText(paint, charSequence, i2, i3));
    }

    private void measureNumberSize(Paint paint) {
        for (int i2 = 0; i2 < 10; i2++) {
            float fMeasureText = paint.measureText(String.valueOf(i2));
            if (fMeasureText > this.mNumberSize) {
                this.mNumberSize = fMeasureText;
            }
        }
    }

    private float measureText(Paint paint, CharSequence charSequence, int i2, int i3) {
        if (!isNumeric()) {
            float fMeasureText = paint.measureText(charSequence, i2, i3);
            float[] fArr = this.mPaddings;
            return fMeasureText + fArr[0] + fArr[2] + fArr[4];
        }
        if (this.mNumberSize == 0.0f) {
            measureNumberSize(paint);
        }
        float f2 = this.mNumberSize;
        float[] fArr2 = this.mPaddings;
        return (f2 + fArr2[0] + fArr2[2] + fArr2[4]) * (i3 - i2);
    }

    private void startTextChangeAnimation(float f2, int i2, int i3, int i4, Canvas canvas, Paint paint) {
        boolean zIsNumeric = isNumeric();
        String str = this.mOldText;
        if (str != null) {
            if (zIsNumeric || this.mHostEllipsize == null) {
                this.mDrawOldText = str;
            } else if (this.mDrawOldText == null) {
                this.mDrawOldText = (String) TextUtils.ellipsize(str, (TextPaint) paint, canvas.getWidth(), this.mHostEllipsize);
            }
            if (this.mDrawOldText != null) {
                this.mIsOldRtl = new Bidi(this.mDrawOldText, -2).isRightToLeft();
                this.mIsOldTextEllipsize = this.mDrawOldText.length() < this.mOldText.length();
            } else {
                this.mIsOldTextEllipsize = false;
            }
            this.mTextExitBitmap = TextEffectUtils.createTextBitmap(paint, this.mDrawOldText, zIsNumeric, this.mNumberSize);
        }
        if (this.mNewTextEffectPaint == null) {
            this.mNewTextEffectPaint = new Paint(paint);
        }
        String str2 = this.mNewText;
        if (str2 != null) {
            if (zIsNumeric || this.mHostEllipsize == null) {
                this.mDrawNewText = str2;
            } else if (this.mDrawNewText == null) {
                this.mDrawNewText = (String) TextUtils.ellipsize(str2, (TextPaint) paint, canvas.getWidth(), this.mHostEllipsize);
            }
            if (this.mDrawNewText != null) {
                this.mIsNewRtl = new Bidi(this.mDrawNewText, -2).isRightToLeft();
                this.mIsNewTextEllipsize = this.mDrawNewText.length() < this.mNewText.length();
            } else {
                this.mIsNewTextEllipsize = false;
            }
            Paint paint2 = this.mNewTextEffectPaint;
            Integer num = this.mNewTextColor;
            paint2.setColor(num != null ? num.intValue() : this.mForegroundColor);
            Paint paint3 = this.mNewTextEffectPaint;
            if (paint3 != null) {
                paint = paint3;
            }
            this.mTextEnterBitmap = TextEffectUtils.createTextBitmap(paint, this.mDrawNewText, zIsNumeric, this.mNumberSize);
        }
        long jMax = Math.max(0L, ((long) this.mSortIndex) * 15);
        if (this.mOldText != null) {
            if (this.mExitController.isRunningAnim()) {
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "  mExitController.cancel()");
                }
                this.mExitController.cancel();
            }
            this.mExitController.setAlphaInt(255);
            this.mExitController.setBlurRadius(0);
            this.mExitController.setRotationX(!this.mIsRtl ? -0.22222222f : 0.22222222f);
            this.mExitController.setScale(0.8f);
            this.mExitController.setTranslationY(i3);
            this.mExitController.setAnimDelay(jMax);
            this.mExitController.exit();
        }
        if (this.mEnterController.isRunningAnim()) {
            if (TimerTextEffectView.DEBUGGABLE) {
                Log.d(TimerTextEffectView.TAG, "  mEnterController.cancel()");
            }
            this.mEnterController.cancel();
        }
        this.mEnterController.setAlphaInt(0);
        this.mEnterController.setBlurRadius(30);
        this.mEnterController.setRotationX(this.mIsRtl ? -0.22222222f : 0.22222222f);
        this.mEnterController.setScale(0.8f);
        this.mEnterController.setTranslationY(-i3);
        this.mEnterController.setAnimDelay(jMax);
        this.mEnterController.enter();
        if (TimerTextEffectView.DEBUGGABLE) {
            Log.d(TimerTextEffectView.TAG, "-> span startTextChangeAnimation mDrawNewText:" + this.mDrawNewText + " mDrawOldText:" + this.mDrawOldText);
        }
    }

    public void clear() {
        this.mIsRunningAnim = false;
        this.mEnterController.cancel();
        this.mExitController.cancel();
        this.mEffectPaint.reset();
        RenderNode renderNode = this.mEnterNode;
        if (renderNode != null) {
            renderNode.discardDisplayList();
        }
        RenderNode renderNode2 = this.mExitNode;
        if (renderNode2 != null) {
            renderNode2.discardDisplayList();
        }
        Integer num = this.mNewTextColor;
        if (num != null) {
            this.mOldTextColor = num;
        }
        this.mOldText = null;
        this.mDrawOldText = null;
        this.mDrawNewText = null;
        this.mNewTextEffectPaint = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00c1  */
    @Override // android.text.style.ReplacementSpan
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(@androidx.annotation.NonNull android.graphics.Canvas r27, java.lang.CharSequence r28, int r29, int r30, float r31, int r32, int r33, int r34, @androidx.annotation.NonNull android.graphics.Paint r35) {
        /*
            Method dump skipped, instruction units count: 1235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.colorful.texteffect.TimerTextEffectSpan.draw(android.graphics.Canvas, java.lang.CharSequence, int, int, float, int, int, int, android.graphics.Paint):void");
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        String str;
        TimerTextEffectView timerTextEffectView = this.mHost.get();
        ViewGroup.LayoutParams layoutParams = timerTextEffectView.getLayoutParams();
        boolean zIsInPreMeasureMode = timerTextEffectView.isInPreMeasureMode();
        this.mContentGravity = timerTextEffectView.getGravity();
        int layoutDirection = timerTextEffectView.getLayoutDirection();
        this.mLayoutDirection = layoutDirection;
        this.mIsRtl = layoutDirection == 1;
        if (layoutParams == null || layoutParams.width == -2) {
            this.mIsHostSpecifiedWidth = false;
        } else {
            this.mIsHostSpecifiedWidth = true;
        }
        String str2 = this.mOldText;
        if (str2 == null && (str = this.mNewText) != null && !TextUtils.equals(charSequence, str)) {
            str2 = this.mNewText;
        }
        String str3 = str2;
        try {
        } catch (Exception e2) {
            if (TimerTextEffectView.DEBUGGABLE) {
                Log.d(TimerTextEffectView.TAG, "getSize oldSizeError: " + e2);
            }
        }
        int iMeasureForSize = !TextUtils.isEmpty(str3) ? measureForSize(paint, str3, 0, str3.length(), fontMetricsInt) : 0;
        int iMeasureForSize2 = charSequence != null ? measureForSize(paint, charSequence, i2, i3, fontMetricsInt) : 0;
        if (TimerTextEffectView.DEBUGGABLE) {
            Log.d(TimerTextEffectView.TAG, "getSize view=" + this.mHost.get());
            Log.d(TimerTextEffectView.TAG, "getSize isInPreMeasureMode=" + zIsInPreMeasureMode);
            Log.d(TimerTextEffectView.TAG, "getSize oldText=" + ((Object) str3) + " text=" + ((Object) charSequence) + " mNewText=" + this.mNewText + " mOldText=" + this.mOldText);
            StringBuilder sb = new StringBuilder();
            sb.append("getSize oldSize=");
            sb.append(iMeasureForSize);
            sb.append(" newSize=");
            sb.append(iMeasureForSize2);
            Log.d(TimerTextEffectView.TAG, sb.toString());
        }
        int iMax = Math.max(iMeasureForSize, iMeasureForSize2);
        this.mSize = iMax;
        if (this.mIsHostSpecifiedWidth) {
            return 0;
        }
        return zIsInPreMeasureMode ? iMeasureForSize2 : iMax;
    }

    public boolean isRunningAnim() {
        return this.mIsRunningAnim;
    }

    public void setEnableAnim(boolean z2) {
        this.mEnableAnim = z2;
    }

    public void setEnableAnimWithInit(boolean z2) {
        this.mEnableAnimWithInit = z2;
    }

    public void setNewTextAppearance(@ColorInt Integer num) {
        if (TimerTextEffectView.DEBUGGABLE) {
            Log.d(TimerTextEffectView.TAG, "  setNewTextAppearance newTextColor=" + num);
        }
        if (num != null) {
            this.mNewTextColor = num;
        } else {
            this.mNewTextColor = null;
            this.mNewTextEffectPaint = null;
        }
    }

    public void setOldTextAppearance(CharSequence charSequence, @ColorInt Integer num) {
        if (TimerTextEffectView.DEBUGGABLE) {
            Log.d(TimerTextEffectView.TAG, " setOldTextAppearance oldText=" + ((Object) charSequence) + " newTextColor=" + num);
        }
        String string = charSequence.toString();
        this.mOldText = string;
        this.mDrawOldText = null;
        this.mNewText = string;
        if (num != null) {
            this.mOldTextColor = num;
        }
        this.mNewTextColor = null;
        this.mNewTextEffectPaint = null;
    }
}
