package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miuix.slidingwidget.widget.SlidingButtonHelper;

/* JADX INFO: loaded from: classes.dex */
public final class SlidingButton extends AppCompatCheckBox {
    private final AttributeSet attrs;
    private final int defStyle;
    private final SlidingButtonHelper helper;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SlidingButton(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$1(SlidingButton this$0, View view, MotionEvent motionEvent) {
        n.g(this$0, "this$0");
        this$0.helper.onHoverEvent(motionEvent);
        return true;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.helper.setSliderDrawState();
    }

    @Override // android.view.View
    public float getAlpha() {
        return this.helper.getAlpha();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.helper.jumpDrawablesToCurrentState();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        n.g(canvas, "canvas");
        this.helper.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(this.helper.getMeasuredWidth(), this.helper.getMeasuredHeight());
        this.helper.setParentClipChildren();
    }

    @Override // android.view.View
    public boolean onSetAlpha(int i2) {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        n.g(event, "event");
        if (!isEnabled()) {
            return false;
        }
        this.helper.onTouchEvent(event);
        return true;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        super.performClick();
        this.helper.notifyCheckedChangeListener();
        return true;
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        this.helper.setAlpha(f2);
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        if (isChecked() != z2) {
            super.setChecked(z2);
            this.helper.setChecked(isChecked());
        }
    }

    @Override // android.view.View
    public void setLayerType(int i2, Paint paint) {
        super.setLayerType(i2, paint);
        this.helper.setLayerType(i2);
    }

    public final void setOnPerformCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.helper.setOnPerformCheckedChangeListener(onCheckedChangeListener);
    }

    @Override // android.view.View
    public void setPressed(boolean z2) {
        super.setPressed(z2);
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        super.toggle();
        this.helper.notifyCheckedChangeListener();
    }

    public final void updateResources() {
        SlidingButtonHelper slidingButtonHelper = this.helper;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(this.attrs, R.styleable.SlidingButton, this.defStyle, R.style.Widget_SlidingButton_Dark);
        slidingButtonHelper.initResource(getContext(), typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable who) {
        n.g(who, "who");
        return super.verifyDrawable(who) || this.helper.verifyDrawable(who);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SlidingButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ SlidingButton(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? R.attr.slidingButtonStyle : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SlidingButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        this.attrs = attributeSet;
        this.defStyle = i2;
        SlidingButtonHelper slidingButtonHelper = new SlidingButtonHelper(this);
        slidingButtonHelper.initAnim();
        this.helper = slidingButtonHelper;
        updateResources();
        setOnHoverListener(new View.OnHoverListener() { // from class: miui.systemui.controlcenter.widget.b
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SlidingButton._init_$lambda$1(this.f5493a, view, motionEvent);
            }
        });
    }
}
