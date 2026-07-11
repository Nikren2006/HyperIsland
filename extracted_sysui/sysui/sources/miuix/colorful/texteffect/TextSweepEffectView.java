package miuix.colorful.texteffect;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class TextSweepEffectView extends AppCompatTextView {
    private int[] mGradientColors;
    private float[] mGradientPositions;
    private float mHighlightPos;
    private int mRenderMode;

    @Nullable
    private ValueAnimator mSweepAnimator;

    @Nullable
    LinearGradient mSweepShader;
    Matrix mTransitionMatrix;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RenderMode {
        public static final int SRC = 1;
        public static final int SRC_OVER = 0;
    }

    public TextSweepEffectView(@NonNull Context context) {
        super(context);
        this.mGradientColors = new int[]{ViewCompat.MEASURED_SIZE_MASK, -1, ViewCompat.MEASURED_SIZE_MASK};
        this.mGradientPositions = new float[]{0.0f, 0.5f, 1.0f};
        this.mHighlightPos = 0.0f;
        this.mTransitionMatrix = new Matrix();
        this.mSweepShader = null;
        this.mSweepAnimator = null;
        this.mRenderMode = 0;
    }

    private void cancelAnim() {
        ValueAnimator valueAnimator = this.mSweepAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mSweepAnimator.cancel();
        }
        this.mSweepAnimator = null;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeAnim() {
        if (this.mSweepAnimator == null) {
            ValueAnimator duration = ValueAnimator.ofFloat(-1.0f, 1.0f).setDuration(2000L);
            this.mSweepAnimator = duration;
            duration.setInterpolator(new LinearInterpolator());
            this.mSweepAnimator.setRepeatCount(-1);
            this.mSweepAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miuix.colorful.texteffect.TextSweepEffectView.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                    TextSweepEffectView.this.mHighlightPos = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    TextSweepEffectView textSweepEffectView = TextSweepEffectView.this;
                    textSweepEffectView.mTransitionMatrix.setTranslate(textSweepEffectView.mHighlightPos * TextSweepEffectView.this.getWidth(), 0.0f);
                    TextSweepEffectView textSweepEffectView2 = TextSweepEffectView.this;
                    textSweepEffectView2.mSweepShader.setLocalMatrix(textSweepEffectView2.mTransitionMatrix);
                    TextSweepEffectView.this.invalidate();
                }
            });
        }
        this.mSweepAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShader(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mSweepShader = new LinearGradient(0.0f, 0.0f, i2, 0.0f, this.mGradientColors, this.mGradientPositions, Shader.TileMode.CLAMP);
        this.mTransitionMatrix.setTranslate(-i2, 0.0f);
        this.mSweepShader.setLocalMatrix(this.mTransitionMatrix);
        if (this.mRenderMode == 1) {
            getPaint().setShader(this.mSweepShader);
        }
    }

    public void enableEffect(boolean z2) {
        if (!z2) {
            cancelAnim();
        } else if (getWidth() == 0 || getHeight() == 0) {
            post(new Runnable() { // from class: miuix.colorful.texteffect.TextSweepEffectView.1
                @Override // java.lang.Runnable
                public void run() {
                    TextSweepEffectView textSweepEffectView = TextSweepEffectView.this;
                    if (textSweepEffectView.mSweepShader == null) {
                        textSweepEffectView.updateShader(textSweepEffectView.getWidth());
                    }
                    TextSweepEffectView.this.executeAnim();
                }
            });
        } else {
            executeAnim();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        ValueAnimator valueAnimator;
        super.onDraw(canvas);
        if (this.mRenderMode == 0 && (valueAnimator = this.mSweepAnimator) != null && valueAnimator.isRunning()) {
            getPaint().setShader(this.mSweepShader);
            super.onDraw(canvas);
            getPaint().setShader(null);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        updateShader(i4 - i2);
    }

    public void setRenderMode(int i2) {
        if (this.mRenderMode != i2) {
            this.mRenderMode = i2;
            if (i2 == 0) {
                getPaint().setShader(null);
            } else if (this.mSweepShader != null) {
                getPaint().setShader(this.mSweepShader);
            }
        }
    }

    public void setSweepColor(int i2) {
        int i3 = 16777215 & i2;
        int i4 = 0;
        while (true) {
            int[] iArr = this.mGradientColors;
            if (i4 >= iArr.length) {
                return;
            }
            if (i4 == 0 || i4 == iArr.length - 1) {
                iArr[i4] = i3;
            } else {
                iArr[i4] = i2;
            }
            i4++;
        }
    }

    public void setSweepColors(int[] iArr, float[] fArr) {
        if (iArr.length != fArr.length) {
            return;
        }
        this.mGradientColors = iArr;
        this.mGradientPositions = fArr;
        invalidate();
    }

    public TextSweepEffectView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mGradientColors = new int[]{ViewCompat.MEASURED_SIZE_MASK, -1, ViewCompat.MEASURED_SIZE_MASK};
        this.mGradientPositions = new float[]{0.0f, 0.5f, 1.0f};
        this.mHighlightPos = 0.0f;
        this.mTransitionMatrix = new Matrix();
        this.mSweepShader = null;
        this.mSweepAnimator = null;
        this.mRenderMode = 0;
    }

    public TextSweepEffectView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mGradientColors = new int[]{ViewCompat.MEASURED_SIZE_MASK, -1, ViewCompat.MEASURED_SIZE_MASK};
        this.mGradientPositions = new float[]{0.0f, 0.5f, 1.0f};
        this.mHighlightPos = 0.0f;
        this.mTransitionMatrix = new Matrix();
        this.mSweepShader = null;
        this.mSweepAnimator = null;
        this.mRenderMode = 0;
    }
}
