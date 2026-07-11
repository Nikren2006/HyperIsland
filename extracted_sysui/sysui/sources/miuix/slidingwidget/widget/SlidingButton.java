package miuix.slidingwidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import miuix.slidingwidget.R;

/* JADX INFO: loaded from: classes5.dex */
public class SlidingButton extends AppCompatCheckBox {
    private SlidingButtonHelper mHelper;

    public SlidingButton(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper != null) {
            slidingButtonHelper.setSliderDrawState();
        }
    }

    @Override // android.view.View
    public float getAlpha() {
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        return slidingButtonHelper != null ? slidingButtonHelper.getAlpha() : super.getAlpha();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper != null) {
            slidingButtonHelper.jumpDrawablesToCurrentState();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper == null) {
            super.onDraw(canvas);
        } else {
            slidingButtonHelper.onDraw(canvas);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper != null) {
            slidingButtonHelper.onHoverEvent(motionEvent);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(true);
        accessibilityNodeInfo.setClassName(Switch.class.getName());
        accessibilityNodeInfo.setStateDescription(isChecked() ? getContext().getResources().getString(R.string.accessibility_sliding_button_state_description_on) : getContext().getResources().getString(R.string.accessibility_sliding_button_state_description_off));
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(this.mHelper.getMeasuredWidth(), this.mHelper.getMeasuredHeight());
        this.mHelper.setParentClipChildren();
    }

    @Override // android.view.View
    public boolean onSetAlpha(int i2) {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper == null) {
            return true;
        }
        slidingButtonHelper.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        super.performClick();
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper == null) {
            return true;
        }
        slidingButtonHelper.notifyCheckedChangeListener();
        return true;
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper != null) {
            slidingButtonHelper.setAlpha(f2);
        }
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        if (isChecked() != z2) {
            super.setChecked(z2);
            boolean zIsChecked = isChecked();
            SlidingButtonHelper slidingButtonHelper = this.mHelper;
            if (slidingButtonHelper != null) {
                slidingButtonHelper.setChecked(zIsChecked);
            }
        }
    }

    @Override // android.view.View
    public void setLayerType(int i2, @Nullable Paint paint) {
        super.setLayerType(i2, paint);
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper != null) {
            slidingButtonHelper.setLayerType(i2);
        }
    }

    public void setOnPerformCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        SlidingButtonHelper slidingButtonHelper = this.mHelper;
        if (slidingButtonHelper != null) {
            slidingButtonHelper.setOnPerformCheckedChangeListener(onCheckedChangeListener);
        }
    }

    @Override // android.view.View
    public void setPressed(boolean z2) {
        super.setPressed(z2);
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        SlidingButtonHelper slidingButtonHelper;
        return super.verifyDrawable(drawable) || ((slidingButtonHelper = this.mHelper) != null && slidingButtonHelper.verifyDrawable(drawable));
    }

    public SlidingButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.slidingButtonStyle);
    }

    public SlidingButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        SlidingButtonHelper slidingButtonHelper = new SlidingButtonHelper(this);
        this.mHelper = slidingButtonHelper;
        slidingButtonHelper.initAnim();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingButton, i2, R.style.Widget_SlidingButton_DayNight);
        this.mHelper.initResource(context, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }
}
