package miuix.miuixbasewidget.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;
import miuix.miuixbasewidget.R;

/* JADX INFO: loaded from: classes.dex */
public class MessageView extends LinearLayout {

    @Deprecated
    private Drawable mCloseBackground;
    private ImageView mEndIcon;
    private String mEndIconContentDescription;
    private Drawable mEndIconDrawable;
    private int mIconMarginHorizontal;
    private int mIconSize;
    private OnMessageViewCloseListener mOnMessageViewCloseListener;
    private OnMessageViewEndIconClickListener mOnMessageViewEndIconClickListener;
    private ImageView mStartIcon;
    private String mStartIconContentDescription;
    private Drawable mStartIconDrawable;
    private TextView mTextView;

    public interface OnMessageViewCloseListener {
        void onClosed();
    }

    public interface OnMessageViewEndIconClickListener {
        void onEndIconClicked();
    }

    public MessageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    private void addCloseIcon() {
        this.mEndIcon = new ImageView(getContext());
        int i2 = this.mIconSize;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_message_view_text_margin_right));
        this.mEndIcon.setId(R.id.end);
        this.mEndIcon.setBackground(this.mEndIconDrawable);
        this.mEndIcon.setContentDescription(this.mEndIconContentDescription);
        this.mEndIcon.setOnClickListener(new View.OnClickListener() { // from class: miuix.miuixbasewidget.widget.MessageView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MessageView.this.setVisibility(8);
                if (MessageView.this.mOnMessageViewCloseListener != null) {
                    MessageView.this.mOnMessageViewCloseListener.onClosed();
                }
            }
        });
        addView(this.mEndIcon, layoutParams);
        Folme.useAt(this.mEndIcon).touch().handleTouchOf(this.mEndIcon, new AnimConfig[0]);
    }

    private void addEndIcon() {
        this.mEndIcon = new ImageView(getContext());
        int i2 = this.mIconSize;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        layoutParams.setMarginStart(this.mIconMarginHorizontal);
        this.mEndIcon.setId(R.id.close);
        this.mEndIcon.setBackground(this.mEndIconDrawable);
        this.mEndIcon.setContentDescription(this.mEndIconContentDescription);
        this.mEndIcon.setOnClickListener(new View.OnClickListener() { // from class: miuix.miuixbasewidget.widget.MessageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MessageView.this.mOnMessageViewEndIconClickListener != null) {
                    MessageView.this.mOnMessageViewEndIconClickListener.onEndIconClicked();
                    return;
                }
                MessageView.this.setVisibility(8);
                if (MessageView.this.mOnMessageViewCloseListener != null) {
                    MessageView.this.mOnMessageViewCloseListener.onClosed();
                }
            }
        });
        addView(this.mEndIcon, layoutParams);
        Folme.useAt(this.mEndIcon).touch().handleTouchOf(this.mEndIcon, new AnimConfig[0]);
    }

    private void addStartIcon() {
        this.mStartIcon = new ImageView(getContext());
        int i2 = this.mIconSize;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        layoutParams.setMarginEnd(this.mIconMarginHorizontal);
        this.mStartIcon.setId(R.id.start);
        this.mStartIcon.setBackground(this.mStartIconDrawable);
        this.mStartIcon.setContentDescription(this.mStartIconContentDescription);
        addView(this.mStartIcon, layoutParams);
    }

    private void initView(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MessageView, i2, R.style.Widget_MessageView);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.MessageView_android_text);
        ColorStateList colorStateList = AppCompatResources.getColorStateList(context, typedArrayObtainStyledAttributes.getResourceId(R.styleable.MessageView_android_textColor, R.color.miuix_appcompat_message_view_text_color_light));
        this.mIconMarginHorizontal = getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_message_view_icon_margin_horizontal);
        this.mIconSize = getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_message_view_icon_width);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MessageView_closable, true);
        this.mCloseBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.MessageView_closeBackground);
        this.mStartIconDrawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.MessageView_startIcon);
        Drawable drawable = this.mCloseBackground;
        if (drawable == null) {
            drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.MessageView_endIcon);
        }
        this.mEndIconDrawable = drawable;
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.MessageView_closeIconContentDescription);
        this.mEndIconContentDescription = string2;
        if (string2 == null) {
            this.mEndIconContentDescription = context.getResources().getString(R.string.close);
        }
        boolean z3 = this.mStartIconDrawable != null;
        typedArrayObtainStyledAttributes.recycle();
        setStartIconVisible(z3);
        this.mTextView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        this.mTextView.setId(android.R.id.text1);
        this.mTextView.setPaddingRelative(getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_message_view_text_padding_start), 0, 0, 0);
        this.mTextView.setText(string);
        this.mTextView.setTextColor(colorStateList);
        this.mTextView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_message_view_text_size));
        this.mTextView.setTextDirection(5);
        addView(this.mTextView, layoutParams);
        setEndIconVisible(z2);
        setGravity(16);
        Folme.useAt(this).touch().setTintMode(0).setScale(1.0f, new ITouchStyle.TouchType[0]).handleTouchOf(this, new AnimConfig[0]);
        Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.FLOATED).handleHoverOf(this, new AnimConfig[0]);
    }

    private void setEndIconVisible(boolean z2) {
        View viewFindViewById = findViewById(R.id.close);
        if (z2) {
            if (viewFindViewById == null) {
                addEndIcon();
            }
        } else if (viewFindViewById != null) {
            removeView(viewFindViewById);
        }
    }

    private void setStartIconVisible(boolean z2) {
        View viewFindViewById = findViewById(R.id.start);
        if (z2) {
            if (viewFindViewById == null) {
                addStartIcon();
            }
        } else if (viewFindViewById != null) {
            removeView(viewFindViewById);
        }
    }

    public void setClosable(boolean z2) {
        View viewFindViewById = findViewById(R.id.close);
        if (z2) {
            if (viewFindViewById == null) {
                addEndIcon();
            }
        } else if (viewFindViewById != null) {
            removeView(viewFindViewById);
        }
    }

    @Deprecated
    public void setCloseIcon(@DrawableRes int i2, String str) {
        if (this.mEndIcon != null) {
            setEndIcon(i2, str);
        }
    }

    public void setEndIcon(@DrawableRes int i2, String str) {
        ImageView imageView = this.mEndIcon;
        if (imageView != null) {
            imageView.setBackgroundResource(i2);
            this.mEndIcon.setContentDescription(str);
        }
    }

    public void setMessage(CharSequence charSequence) {
        this.mTextView.setText(charSequence);
    }

    public void setOnMessageViewCloseListener(OnMessageViewCloseListener onMessageViewCloseListener) {
        this.mOnMessageViewCloseListener = onMessageViewCloseListener;
    }

    public void setOnMessageViewEndIconClickListener(OnMessageViewEndIconClickListener onMessageViewEndIconClickListener) {
        this.mOnMessageViewEndIconClickListener = onMessageViewEndIconClickListener;
    }

    public void setStartIcon(@DrawableRes int i2, String str) {
        ImageView imageView = this.mStartIcon;
        if (imageView != null) {
            imageView.setBackgroundResource(i2);
            this.mStartIcon.setContentDescription(str);
        }
    }

    public void setTextColor(int i2) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    @SuppressLint({"ResourceAsColor"})
    public void setTextColorResource(@ColorRes int i2) {
        if (this.mTextView == null || getContext() == null) {
            return;
        }
        this.mTextView.setTextColor(ContextCompat.getColor(getContext(), i2));
    }

    public void show() {
        setVisibility(0);
        View viewFindViewById = findViewById(R.id.close);
        if (viewFindViewById != null) {
            Folme.useAt(viewFindViewById).visible().cancel();
            Folme.useAt(viewFindViewById).visible().setShow();
        }
    }

    public MessageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context, attributeSet, i2);
    }

    @Deprecated
    public void setCloseIcon(Drawable drawable, String str) {
        if (this.mEndIcon != null) {
            setEndIcon(drawable, str);
        }
    }

    public void setEndIcon(Drawable drawable, String str) {
        ImageView imageView = this.mEndIcon;
        if (imageView != null) {
            imageView.setBackground(drawable);
            this.mEndIcon.setContentDescription(str);
        }
    }

    public void setStartIcon(Drawable drawable, String str) {
        ImageView imageView = this.mStartIcon;
        if (imageView != null) {
            imageView.setBackground(drawable);
            this.mStartIcon.setContentDescription(str);
        }
    }
}
