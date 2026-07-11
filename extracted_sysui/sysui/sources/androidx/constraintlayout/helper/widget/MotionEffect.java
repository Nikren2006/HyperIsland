package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.widget.R;

/* JADX INFO: loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    private static final int UNSET = -1;
    public static final int WEST = 3;
    private int mFadeMove;
    private float mMotionEffectAlpha;
    private int mMotionEffectEnd;
    private int mMotionEffectStart;
    private boolean mMotionEffectStrictMove;
    private int mMotionEffectTranslationX;
    private int mMotionEffectTranslationY;
    private int mViewTransitionId;

    public MotionEffect(Context context) {
        super(context);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MotionEffect);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionEffect_motionEffect_start) {
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, this.mMotionEffectStart);
                    this.mMotionEffectStart = i3;
                    this.mMotionEffectStart = Math.max(Math.min(i3, 99), 0);
                } else if (index == R.styleable.MotionEffect_motionEffect_end) {
                    int i4 = typedArrayObtainStyledAttributes.getInt(index, this.mMotionEffectEnd);
                    this.mMotionEffectEnd = i4;
                    this.mMotionEffectEnd = Math.max(Math.min(i4, 99), 0);
                } else if (index == R.styleable.MotionEffect_motionEffect_translationX) {
                    this.mMotionEffectTranslationX = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMotionEffectTranslationX);
                } else if (index == R.styleable.MotionEffect_motionEffect_translationY) {
                    this.mMotionEffectTranslationY = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMotionEffectTranslationY);
                } else if (index == R.styleable.MotionEffect_motionEffect_alpha) {
                    this.mMotionEffectAlpha = typedArrayObtainStyledAttributes.getFloat(index, this.mMotionEffectAlpha);
                } else if (index == R.styleable.MotionEffect_motionEffect_move) {
                    this.mFadeMove = typedArrayObtainStyledAttributes.getInt(index, this.mFadeMove);
                } else if (index == R.styleable.MotionEffect_motionEffect_strict) {
                    this.mMotionEffectStrictMove = typedArrayObtainStyledAttributes.getBoolean(index, this.mMotionEffectStrictMove);
                } else if (index == R.styleable.MotionEffect_motionEffect_viewTransition) {
                    this.mViewTransitionId = typedArrayObtainStyledAttributes.getResourceId(index, this.mViewTransitionId);
                }
            }
            int i5 = this.mMotionEffectStart;
            int i6 = this.mMotionEffectEnd;
            if (i5 == i6) {
                if (i5 > 0) {
                    this.mMotionEffectStart = i5 - 1;
                } else {
                    this.mMotionEffectEnd = i6 + 1;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isDecorator() {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0185, code lost:
    
        if (r14 == 0.0f) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0199, code lost:
    
        if (r14 == 0.0f) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01a9, code lost:
    
        if (r15 == 0.0f) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01e4  */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPreSetup(androidx.constraintlayout.motion.widget.MotionLayout r23, java.util.HashMap<android.view.View, androidx.constraintlayout.motion.widget.MotionController> r24) {
        /*
            Method dump skipped, instruction units count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.MotionEffect.onPreSetup(androidx.constraintlayout.motion.widget.MotionLayout, java.util.HashMap):void");
    }

    public MotionEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
        init(context, attributeSet);
    }

    public MotionEffect(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
        init(context, attributeSet);
    }
}
