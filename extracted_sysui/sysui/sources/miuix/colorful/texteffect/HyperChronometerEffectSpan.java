package miuix.colorful.texteffect;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.text.style.ReplacementSpan;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class HyperChronometerEffectSpan extends ReplacementSpan {
    private int mBackgroundColor;

    @Nullable
    private final Matrix mBitmapMatrix;
    private RectF mClipRect;
    private boolean mEnableAnim;
    private final TextSwitcherAnimator mEnterController;

    @Nullable
    private RenderNode mEnterNode;
    private final TextSwitcherAnimator mExitController;

    @Nullable
    private RenderNode mExitNode;
    private int mForegroundColor;

    @Nullable
    String mNewText;

    @Nullable
    private Integer mNewTextColor;

    @Nullable
    private Paint mNewTextEffectPaint;

    @Nullable
    String mOldText;

    @Nullable
    private Integer mOldTextColor;

    @NonNull
    private final float[] mPaddings;
    private RectF mRoundRect;
    int mSortIndex;

    @Nullable
    private Bitmap mTextEnterBitmap;

    @Nullable
    private Bitmap mTextExitBitmap;
    private final Paint mEffectPaint = new Paint();
    private boolean mEnableAnimWithInit = false;
    private boolean mIsRunningAnim = false;
    private float mNumberSize = 0.0f;

    public HyperChronometerEffectSpan(TextSwitcher textSwitcher, int i2, int i3, float[] fArr, int i4) {
        this.mRoundRect = null;
        this.mClipRect = null;
        this.mEnterController = new TextSwitcherAnimator(textSwitcher, 0);
        this.mExitController = new TextSwitcherAnimator(textSwitcher, 1);
        this.mBackgroundColor = i2;
        this.mForegroundColor = i3;
        this.mSortIndex = i4;
        this.mPaddings = fArr;
        this.mEnableAnim = textSwitcher.isEnableEffect();
        if (i2 != 0) {
            this.mRoundRect = new RectF();
            this.mClipRect = new RectF();
        }
        this.mBitmapMatrix = new Matrix();
        this.mEnterNode = new RenderNode("n_node_in_" + i4);
        this.mExitNode = new RenderNode("n_node_out_" + i4);
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
        this.mTextExitBitmap = TextEffectUtils.createTextBitmap(paint, this.mOldText, zIsNumeric, this.mNumberSize);
        if (this.mNewTextEffectPaint == null) {
            this.mNewTextEffectPaint = new Paint(paint);
        }
        Paint paint2 = this.mNewTextEffectPaint;
        Integer num = this.mNewTextColor;
        paint2.setColor(num != null ? num.intValue() : this.mForegroundColor);
        Paint paint3 = this.mNewTextEffectPaint;
        if (paint3 == null) {
            paint3 = paint;
        }
        this.mTextEnterBitmap = TextEffectUtils.createTextBitmap(paint3, this.mNewText, zIsNumeric, this.mNumberSize);
        long jMax = Math.max(0L, ((long) this.mSortIndex) * 15);
        if (this.mTextExitBitmap != null) {
            if (this.mExitController.isRunningAnim()) {
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "  mExitController.cancel()");
                }
                this.mExitController.cancel();
            }
            this.mExitController.setAlphaInt(255);
            this.mExitController.setBlurRadius(0);
            this.mExitController.setRotationX(paint.getTextAlign() != Paint.Align.RIGHT ? -0.22222222f : 0.22222222f);
            this.mExitController.setScale(0.8f);
            this.mExitController.setTranslationY(i3);
            this.mExitController.setAnimDelay(jMax);
            this.mExitController.exit();
        }
        if (this.mTextEnterBitmap != null) {
            if (this.mEnterController.isRunningAnim()) {
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "  mEnterController.cancel()");
                }
                this.mEnterController.cancel();
            }
            this.mEnterController.setAlphaInt(0);
            this.mEnterController.setBlurRadius(30);
            this.mEnterController.setRotationX(paint.getTextAlign() == Paint.Align.RIGHT ? -0.22222222f : 0.22222222f);
            this.mEnterController.setScale(0.8f);
            this.mEnterController.setTranslationY(-i3);
            this.mEnterController.setAnimDelay(jMax);
            this.mEnterController.enter();
        }
    }

    public void clear() {
        this.mIsRunningAnim = false;
        TextSwitcherAnimator textSwitcherAnimator = this.mExitController;
        if (textSwitcherAnimator != null) {
            textSwitcherAnimator.cancel();
        }
        TextSwitcherAnimator textSwitcherAnimator2 = this.mEnterController;
        if (textSwitcherAnimator2 != null) {
            textSwitcherAnimator2.cancel();
        }
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
        this.mNewTextEffectPaint = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    @Override // android.text.style.ReplacementSpan
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(@androidx.annotation.NonNull android.graphics.Canvas r21, java.lang.CharSequence r22, int r23, int r24, float r25, int r26, int r27, int r28, @androidx.annotation.NonNull android.graphics.Paint r29) {
        /*
            Method dump skipped, instruction units count: 478
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.colorful.texteffect.HyperChronometerEffectSpan.draw(android.graphics.Canvas, java.lang.CharSequence, int, int, float, int, int, int, android.graphics.Paint):void");
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
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

    public boolean isNumeric() {
        return this.mSortIndex >= 0;
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
        if (num != null) {
            this.mNewTextColor = num;
        } else {
            this.mNewTextColor = null;
            this.mNewTextEffectPaint = null;
        }
    }

    public void setOldTextAppearance(CharSequence charSequence, @ColorInt Integer num) {
        String string = charSequence.toString();
        this.mOldText = string;
        this.mNewText = string;
        if (num != null) {
            this.mOldTextColor = num;
        }
        this.mNewTextColor = null;
        this.mNewTextEffectPaint = null;
    }

    public void updateSortIndex(int i2) {
        this.mSortIndex = i2;
    }
}
