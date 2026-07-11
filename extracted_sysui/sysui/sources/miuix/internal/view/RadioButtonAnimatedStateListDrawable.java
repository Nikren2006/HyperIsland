package miuix.internal.view;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.RequiresApi;
import miuix.appcompat.R;
import miuix.internal.view.CheckWidgetAnimatedStateListDrawable;

/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(api = 21)
public class RadioButtonAnimatedStateListDrawable extends CheckBoxAnimatedStateListDrawable {
    private int mDrawPadding;

    public static class RadioButtonConstantState extends CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState {
        @Override // miuix.internal.view.CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState
        public Drawable newAnimatedStateListDrawable(Resources resources, Resources.Theme theme, CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState checkWidgetConstantState) {
            return new RadioButtonAnimatedStateListDrawable(resources, theme, checkWidgetConstantState);
        }
    }

    public RadioButtonAnimatedStateListDrawable() {
        this.mDrawPadding = 19;
    }

    @Override // miuix.internal.view.CheckBoxAnimatedStateListDrawable
    public int getCheckWidgetDrawableStyle() {
        return R.style.CheckWidgetDrawable_RadioButton;
    }

    @Override // miuix.internal.view.CheckBoxAnimatedStateListDrawable
    public boolean isSingleSelectionWidget() {
        return true;
    }

    @Override // miuix.internal.view.CheckBoxAnimatedStateListDrawable, miuix.internal.view.CheckWidgetAnimatedStateListDrawable
    public CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState newCheckWidgetConstantState() {
        return new RadioButtonConstantState();
    }

    @Override // miuix.internal.view.CheckBoxAnimatedStateListDrawable
    public void setCheckWidgetDrawableBounds(int i2, int i3, int i4, int i5) {
        int i6 = this.mDrawPadding;
        super.setCheckWidgetDrawableBounds(i2 + i6, i3 + i6, i4 - i6, i5 - i6);
    }

    @Override // miuix.internal.view.CheckBoxAnimatedStateListDrawable
    public void setCheckWidgetDrawableBounds(Rect rect) {
        int i2 = this.mDrawPadding;
        rect.inset(i2, i2);
        super.setCheckWidgetDrawableBounds(rect);
    }

    public RadioButtonAnimatedStateListDrawable(Resources resources, Resources.Theme theme, CheckWidgetAnimatedStateListDrawable.CheckWidgetConstantState checkWidgetConstantState) {
        super(resources, theme, checkWidgetConstantState);
        this.mDrawPadding = 19;
        if (resources != null) {
            this.mDrawPadding = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_radio_button_drawable_padding);
        }
    }
}
