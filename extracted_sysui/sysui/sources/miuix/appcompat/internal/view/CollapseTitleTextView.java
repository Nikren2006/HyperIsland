package miuix.appcompat.internal.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes3.dex */
public class CollapseTitleTextView extends AppCompatTextView {
    private float mOriginalTextSize;
    private final float mSmallTextSize;
    private final boolean mSmallTextSizeEnabled;

    public CollapseTitleTextView(@NonNull Context context) {
        this(context, null);
    }

    private boolean isTextEllipsis() {
        Layout layout = getLayout();
        int lineCount = layout.getLineCount();
        if (getMaxLines() > 0 && lineCount > getMaxLines()) {
            return true;
        }
        for (int i2 = 0; i2 < lineCount; i2++) {
            if (layout.getEllipsisCount(i2) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.mSmallTextSizeEnabled) {
            float f2 = this.mOriginalTextSize;
            if (f2 > this.mSmallTextSize) {
                setTextSize(0, f2);
                super.onMeasure(i2, i3);
                if (isTextEllipsis()) {
                    setTextSize(0, this.mSmallTextSize);
                    super.onMeasure(i2, i3);
                    return;
                }
                return;
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        this.mOriginalTextSize = getTextSize();
    }

    @Override // android.widget.TextView
    public void setTextSize(float f2) {
        super.setTextSize(f2);
        this.mOriginalTextSize = getTextSize();
    }

    public CollapseTitleTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public CollapseTitleTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOriginalTextSize = getTextSize();
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, miuix.appcompat.R.styleable.CollapseTitleView, i2, 0);
        this.mSmallTextSizeEnabled = typedArrayObtainStyledAttributes.getBoolean(miuix.appcompat.R.styleable.CollapseTitleView_smallTextSizeEnabled, true);
        this.mSmallTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(miuix.appcompat.R.styleable.CollapseTitleView_smallTextSize, context.getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_font_size_headline1));
        typedArrayObtainStyledAttributes.recycle();
    }
}
