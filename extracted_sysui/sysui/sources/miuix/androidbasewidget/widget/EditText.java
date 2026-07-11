package miuix.androidbasewidget.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import androidx.appcompat.widget.AppCompatEditText;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes4.dex */
public class EditText extends AppCompatEditText {
    private static final int LEVEL_ERROR = 404;
    private static final int LEVEL_NORMAL = 0;
    private static final String TAG = "EditText";
    private boolean isAddListener;
    private boolean mCanVerticalScroll;
    private int mCurrentHandleAndCursorColor;
    private TextWatcher mErrorWatcher;
    private int mOffsetHeight;
    private boolean mReachEdgeFlag;
    private int mTextHandleAndCursorColor;

    public class ErrorWatcher implements TextWatcher {
        private ErrorWatcher() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            EditText.this.setMiuiStyleError(null);
            if (EditText.this.isAddListener) {
                EditText.this.isAddListener = false;
                EditText editText = EditText.this;
                editText.removeTextChangedListener(editText.mErrorWatcher);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    public EditText(Context context) {
        this(context, null);
    }

    private boolean canVerticalScroll() {
        int scrollY = getScrollY();
        int height = getLayout().getHeight() - ((getMeasuredHeight() - getCompoundPaddingTop()) - getCompoundPaddingBottom());
        this.mOffsetHeight = height;
        if (height == 0) {
            return false;
        }
        return scrollY > 0 || scrollY < height - 1;
    }

    private int obtainHighlightColor() {
        return Color.argb(51, Color.red(this.mTextHandleAndCursorColor), Color.green(this.mTextHandleAndCursorColor), Color.blue(this.mTextHandleAndCursorColor));
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mReachEdgeFlag = false;
        }
        if (this.mReachEdgeFlag) {
            motionEvent.setAction(3);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.mCanVerticalScroll = canVerticalScroll();
    }

    @Override // android.widget.TextView, android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        boolean zOnPreDraw = super.onPreDraw();
        if (getHighlightColor() != obtainHighlightColor()) {
            setHighlightColor(obtainHighlightColor());
        }
        int i2 = this.mCurrentHandleAndCursorColor;
        if (i2 == -1 || i2 != this.mTextHandleAndCursorColor) {
            Drawable textSelectHandleLeft = getTextSelectHandleLeft();
            Drawable textSelectHandleRight = getTextSelectHandleRight();
            Drawable textSelectHandle = getTextSelectHandle();
            Drawable textCursorDrawable = getTextCursorDrawable();
            Drawable[] drawableArr = {textSelectHandleLeft, textSelectHandleRight, textSelectHandle, textCursorDrawable};
            for (int i3 = 0; i3 < 4; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable != null) {
                    drawable.setColorFilter(this.mTextHandleAndCursorColor, PorterDuff.Mode.SRC_IN);
                    this.mCurrentHandleAndCursorColor = this.mTextHandleAndCursorColor;
                }
            }
            setTextSelectHandleLeft(textSelectHandleLeft);
            setTextSelectHandleRight(textSelectHandleRight);
            setTextSelectHandle(textSelectHandle);
            setTextCursorDrawable(textCursorDrawable);
        }
        return zOnPreDraw;
    }

    @Override // android.widget.TextView, android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        this.mCanVerticalScroll = canVerticalScroll();
        if (i3 == this.mOffsetHeight || i3 == 0) {
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
            this.mReachEdgeFlag = true;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        ViewParent parent = getParent();
        if (this.mCanVerticalScroll) {
            if (!this.mReachEdgeFlag && parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        } else if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        return zOnTouchEvent;
    }

    public void setMiuiStyleError(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            getBackground().setLevel(0);
            return;
        }
        getBackground().setLevel(404);
        if (this.isAddListener) {
            return;
        }
        this.isAddListener = true;
        addTextChangedListener(this.mErrorWatcher);
    }

    public EditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public EditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mReachEdgeFlag = false;
        this.mCurrentHandleAndCursorColor = -1;
        this.mErrorWatcher = new ErrorWatcher();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, miuix.androidbasewidget.R.styleable.EditText, i2, miuix.androidbasewidget.R.style.Widget_EditText_DayNight);
        this.mTextHandleAndCursorColor = typedArrayObtainStyledAttributes.getColor(miuix.androidbasewidget.R.styleable.EditText_textHandleAndCursorColor, getResources().getColor(miuix.androidbasewidget.R.color.miuix_appcompat_handle_and_cursor_color_light));
        typedArrayObtainStyledAttributes.recycle();
        Drawable background = getBackground();
        if (background == null || background.getOpacity() == -2) {
            return;
        }
        Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.NORMAL).handleHoverOf(this, new AnimConfig[0]);
    }
}
