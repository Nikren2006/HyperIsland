package miuix.androidbasewidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import miuix.androidbasewidget.R;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes4.dex */
public class StateEditText extends EditText {
    private static final Class<?>[] WIDGET_MANAGER_CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private ExploreByTouchHelper mExploreByTouchHelper;
    private Drawable[] mExtraDrawables;
    private String mLabel;
    private StaticLayout mLabelLayout;
    private int mLabelLength;
    private int mLabelMaxWidth;
    private boolean mPressed;
    private WidgetManager mWidgetManager;
    private int mWidgetPadding;

    public static abstract class WidgetManager {
        public WidgetManager(Context context, AttributeSet attributeSet) {
        }

        public abstract Drawable[] getWidgetDrawables();

        public void onAttached(StateEditText stateEditText) {
        }

        public void onDetached() {
        }

        public abstract void onPopulateNodeForVirtualView(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

        public abstract void onWidgetClick(int i2);
    }

    public StateEditText(Context context) {
        this(context, null);
    }

    private void createLabelLayout() {
        String str = this.mLabel;
        this.mLabelLayout = StaticLayout.Builder.obtain(str, 0, str.length(), getPaint(), this.mLabelLength).build();
    }

    private WidgetManager createWidgetManager(Context context, String str, AttributeSet attributeSet) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Constructor constructor = context.getClassLoader().loadClass(str).asSubclass(WidgetManager.class).getConstructor(WIDGET_MANAGER_CONSTRUCTOR_SIGNATURE);
            constructor.setAccessible(true);
            return (WidgetManager) constructor.newInstance(context, attributeSet);
        } catch (ClassNotFoundException e2) {
            throw new IllegalStateException("Can't find WidgetManager: " + str, e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException("Can't access non-public constructor " + str, e3);
        } catch (InstantiationException e4) {
            throw new IllegalStateException("Could not instantiate the WidgetManager: " + str, e4);
        } catch (NoSuchMethodException e5) {
            throw new IllegalStateException("Error creating WidgetManager " + str, e5);
        } catch (InvocationTargetException e6) {
            throw new IllegalStateException("Could not instantiate the WidgetManager: " + str, e6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dispatchEndDrawableTouchEvent(MotionEvent motionEvent) {
        if (this.mWidgetManager != null) {
            return isWidgetResumedEvent(motionEvent);
        }
        return false;
    }

    private void drawExtraWidget(Canvas canvas) {
        if (this.mExtraDrawables == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        int scrollX = getScrollX();
        int paddingEnd = getPaddingEnd();
        Drawable drawable = getCompoundDrawablesRelative()[2];
        int i2 = 0;
        int intrinsicWidth = drawable == null ? 0 : drawable.getIntrinsicWidth() + this.mWidgetPadding;
        int i3 = height / 2;
        int i4 = 0;
        while (true) {
            Drawable[] drawableArr = this.mExtraDrawables;
            if (i2 >= drawableArr.length) {
                return;
            }
            int intrinsicWidth2 = drawableArr[i2].getIntrinsicWidth();
            int intrinsicHeight = this.mExtraDrawables[i2].getIntrinsicHeight();
            if (ViewUtils.isLayoutRtl(this)) {
                int i5 = scrollX + paddingEnd + intrinsicWidth;
                int i6 = intrinsicHeight / 2;
                this.mExtraDrawables[i2].setBounds(i5 + i4, i3 - i6, i5 + intrinsicWidth2 + i4, i6 + i3);
            } else {
                int i7 = ((scrollX + width) - paddingEnd) - intrinsicWidth;
                int i8 = intrinsicHeight / 2;
                this.mExtraDrawables[i2].setBounds((i7 - intrinsicWidth2) - i4, i3 - i8, i7 - i4, i8 + i3);
            }
            i4 = this.mWidgetPadding + intrinsicWidth2;
            this.mExtraDrawables[i2].draw(canvas);
            i2++;
        }
    }

    private void drawLabel(Canvas canvas) {
        if (TextUtils.isEmpty(this.mLabel) || this.mLabelLayout == null) {
            return;
        }
        int color = getPaint().getColor();
        getPaint().setColor(getCurrentTextColor());
        int paddingStart = getPaddingStart();
        int intrinsicWidth = 0;
        Drawable drawable = getCompoundDrawablesRelative()[0];
        if (drawable != null) {
            intrinsicWidth = this.mWidgetPadding + drawable.getIntrinsicWidth();
        }
        float fMax = Math.max(0.0f, (getMeasuredHeight() - this.mLabelLayout.getHeight()) / 2.0f);
        canvas.save();
        if (ViewUtils.isLayoutRtl(this)) {
            canvas.translate((((getScrollX() + getWidth()) - intrinsicWidth) - this.mLabelLength) - paddingStart, fMax);
        } else {
            canvas.translate(paddingStart + getScrollX() + intrinsicWidth, fMax);
        }
        this.mLabelLayout.draw(canvas);
        canvas.restore();
        getPaint().setColor(color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean emptyContentDescription() {
        return TextUtils.isEmpty(getContentDescription()) && TextUtils.isEmpty(getStateDescription()) && TextUtils.isEmpty(getHint()) && TextUtils.isEmpty(getText());
    }

    private int getLabelLength() {
        int i2 = this.mLabelLength;
        return i2 + (i2 == 0 ? 0 : this.mWidgetPadding);
    }

    private int getWidgetLength() {
        Drawable[] drawableArr = this.mExtraDrawables;
        if (drawableArr == null) {
            return 0;
        }
        int intrinsicWidth = 0;
        for (Drawable drawable : drawableArr) {
            intrinsicWidth = intrinsicWidth + drawable.getIntrinsicWidth() + this.mWidgetPadding;
        }
        return intrinsicWidth;
    }

    private void initView(Context context, AttributeSet attributeSet, int i2) {
        String string;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.miuixAppcompatStateEditText, i2, R.style.Widget_StateEditText_DayNight);
            string = typedArrayObtainStyledAttributes.getString(R.styleable.miuixAppcompatStateEditText_miuixAppcompatWidgetManager);
            this.mLabel = typedArrayObtainStyledAttributes.getString(R.styleable.miuixAppcompatStateEditText_miuixAppcompatLabel);
            this.mLabelMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.miuixAppcompatStateEditText_miuixAppcompatLabelMaxWidth, 0);
            this.mWidgetPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.miuixAppcompatStateEditText_miuixAppcompatWidgetPadding, 0);
            typedArrayObtainStyledAttributes.recycle();
        } else {
            string = null;
        }
        setWidgetManager(createWidgetManager(context, string, attributeSet));
        this.mExtraDrawables = null;
        WidgetManager widgetManager = this.mWidgetManager;
        if (widgetManager != null) {
            this.mExtraDrawables = widgetManager.getWidgetDrawables();
        }
        setLabel(this.mLabel);
        if (!TextUtils.isEmpty(this.mLabel)) {
            setTextAlignment(6);
        }
        ViewCompat.setAccessibilityDelegate(this, this.mExploreByTouchHelper);
    }

    private boolean isWidgetResumedEvent(MotionEvent motionEvent) {
        if (this.mExtraDrawables != null) {
            int scrollX = getScrollX();
            int i2 = 0;
            while (true) {
                Drawable[] drawableArr = this.mExtraDrawables;
                if (i2 >= drawableArr.length) {
                    break;
                }
                Rect bounds = drawableArr[i2].getBounds();
                if (motionEvent.getX() < bounds.right - scrollX && motionEvent.getX() > bounds.left - scrollX) {
                    return onWidgetTouchEvent(motionEvent, i2);
                }
                i2++;
            }
        }
        this.mPressed = false;
        return false;
    }

    private boolean onWidgetTouchEvent(MotionEvent motionEvent, int i2) {
        WidgetManager widgetManager;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mPressed = true;
        } else if (action != 1) {
            if (action == 3 && this.mPressed) {
                this.mPressed = false;
            }
        } else if (this.mPressed && (widgetManager = this.mWidgetManager) != null) {
            widgetManager.onWidgetClick(i2);
            this.mPressed = false;
            return true;
        }
        return this.mPressed;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.mExploreByTouchHelper.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    @Override // miuix.androidbasewidget.widget.EditText, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return dispatchEndDrawableTouchEvent(motionEvent) || super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView
    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft() + (ViewUtils.isLayoutRtl(this) ? getWidgetLength() : getLabelLength());
    }

    @Override // android.widget.TextView
    public int getCompoundPaddingRight() {
        return super.getCompoundPaddingRight() + (ViewUtils.isLayoutRtl(this) ? getLabelLength() : getWidgetLength());
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawExtraWidget(canvas);
        drawLabel(canvas);
    }

    @Override // miuix.androidbasewidget.widget.EditText, android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (TextUtils.isEmpty(this.mLabel) || this.mLabelLayout == null) {
            return;
        }
        if (this.mLabelMaxWidth == 0 && this.mLabelLength > getMeasuredWidth() / 2) {
            this.mLabelLength = getMeasuredWidth() / 2;
            createLabelLayout();
        }
        int height = this.mLabelLayout.getHeight() + getPaddingTop() + getPaddingBottom();
        if (height > getMeasuredHeight()) {
            setMeasuredDimension(getMeasuredWidth(), height);
        }
    }

    @Override // android.widget.TextView
    public void setInputType(int i2) {
        Typeface typeface = getTypeface();
        super.setInputType(i2);
        setTypeface(typeface);
    }

    public void setLabel(String str) {
        this.mLabel = str;
        setStateDescription(str);
        if (this.mLabelMaxWidth > 0) {
            this.mLabelLength = TextUtils.isEmpty(this.mLabel) ? 0 : Math.min((int) getPaint().measureText(this.mLabel), this.mLabelMaxWidth);
        } else {
            this.mLabelLength = TextUtils.isEmpty(this.mLabel) ? 0 : (int) getPaint().measureText(this.mLabel);
        }
        if (!TextUtils.isEmpty(this.mLabel)) {
            createLabelLayout();
        }
        invalidate();
    }

    public void setWidgetManager(WidgetManager widgetManager) {
        WidgetManager widgetManager2 = this.mWidgetManager;
        if (widgetManager2 != null) {
            widgetManager2.onDetached();
            this.mExtraDrawables = null;
        }
        this.mWidgetManager = widgetManager;
        if (widgetManager != null) {
            this.mExtraDrawables = widgetManager.getWidgetDrawables();
            this.mWidgetManager.onAttached(this);
        }
    }

    public StateEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.miuixAppcompatStateEditTextStyle);
    }

    public StateEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mLabelLayout = null;
        this.mExploreByTouchHelper = new ExploreByTouchHelper(this) { // from class: miuix.androidbasewidget.widget.StateEditText.1
            @Override // androidx.customview.widget.ExploreByTouchHelper
            public int getVirtualViewAt(float f2, float f3) {
                if (StateEditText.this.mExtraDrawables == null) {
                    return Integer.MIN_VALUE;
                }
                for (int i3 = 0; i3 < StateEditText.this.mExtraDrawables.length; i3++) {
                    int scrollX = StateEditText.this.getScrollX();
                    Rect bounds = StateEditText.this.mExtraDrawables[i3].getBounds();
                    if (new Rect(bounds.left - scrollX, bounds.top, bounds.right - scrollX, bounds.bottom).contains((int) f2, (int) f3) && StateEditText.this.mExtraDrawables[i3].isVisible()) {
                        return i3;
                    }
                }
                return Integer.MIN_VALUE;
            }

            @Override // androidx.customview.widget.ExploreByTouchHelper
            public void getVisibleVirtualViews(List<Integer> list) {
                if (StateEditText.this.mExtraDrawables == null || StateEditText.this.emptyContentDescription()) {
                    return;
                }
                for (int i3 = 0; i3 < StateEditText.this.mExtraDrawables.length; i3++) {
                    if (StateEditText.this.mExtraDrawables[i3].isVisible()) {
                        list.add(Integer.valueOf(i3));
                    }
                }
            }

            @Override // androidx.customview.widget.ExploreByTouchHelper
            public boolean onPerformActionForVirtualView(int i3, int i4, @Nullable Bundle bundle) {
                if (StateEditText.this.mExtraDrawables != null && i4 == 16) {
                    for (int i5 = 0; i5 < StateEditText.this.mExtraDrawables.length; i5++) {
                        if (i3 == i5) {
                            invalidateVirtualView(i3);
                            float fCenterX = StateEditText.this.mExtraDrawables[i5].getBounds().centerX() - StateEditText.this.getScrollX();
                            float fCenterY = StateEditText.this.mExtraDrawables[i5].getBounds().centerY();
                            StateEditText.this.dispatchEndDrawableTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, fCenterX, fCenterY, 0));
                            StateEditText.this.dispatchEndDrawableTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, fCenterX, fCenterY, 0));
                            if (StateEditText.this.mExtraDrawables[i5].isVisible()) {
                                sendEventForVirtualView(i5, 128);
                                return true;
                            }
                            sendEventForVirtualView(i5, 65536);
                            StateEditText.this.sendAccessibilityEvent(32768);
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override // androidx.customview.widget.ExploreByTouchHelper
            public void onPopulateNodeForVirtualView(int i3, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                if (StateEditText.this.mExtraDrawables == null) {
                    return;
                }
                for (int i4 = 0; i4 < StateEditText.this.mExtraDrawables.length; i4++) {
                    if (i3 == i4) {
                        accessibilityNodeInfoCompat.setVisibleToUser(true);
                        accessibilityNodeInfoCompat.setAccessibilityFocused(true);
                        accessibilityNodeInfoCompat.setFocusable(true);
                        accessibilityNodeInfoCompat.setClickable(true);
                        Rect bounds = StateEditText.this.mExtraDrawables[i4].getBounds();
                        accessibilityNodeInfoCompat.setText("");
                        accessibilityNodeInfoCompat.setBoundsInParent(bounds);
                        accessibilityNodeInfoCompat.setClassName(Button.class.getName());
                        accessibilityNodeInfoCompat.addAction(16);
                        StateEditText.this.mWidgetManager.onPopulateNodeForVirtualView(i4, accessibilityNodeInfoCompat);
                    }
                }
            }
        };
        initView(context, attributeSet, i2);
    }
}
