package miuix.popupwidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatButton;
import miuix.popupwidget.R;
import miuix.popupwidget.internal.widget.ArrowPopupView;

/* JADX INFO: loaded from: classes5.dex */
public class ArrowPopupWindow extends android.widget.PopupWindow {
    public static final int ARROW_BOTTOM_LEFT_MODE = 18;
    public static final int ARROW_BOTTOM_MODE = 16;
    public static final int ARROW_BOTTOM_RIGHT_MODE = 17;
    public static final int ARROW_LEFT_MODE = 32;
    public static final int ARROW_RIGHT_MODE = 64;
    public static final int ARROW_TOP_LEFT_MODE = 9;
    public static final int ARROW_TOP_MODE = 8;
    public static final int ARROW_TOP_RIGHT_MODE = 10;
    public static final int LAYOUT_MODE_LTR = 0;
    public static final int LAYOUT_MODE_RTL = 1;
    public static final int LAYOUT_MODE_UNSPECIFIED = 2;
    protected ArrowPopupView mArrowPopupView;
    private boolean mAutoDismiss;
    private Context mContext;
    private int mListViewMaxHeight;
    private int mMaxAvailableHeight;
    protected int mRtlMode;

    public ArrowPopupWindow(Context context) {
        this(context, null);
    }

    private void setupPopupWindow() {
        this.mListViewMaxHeight = this.mContext.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_arrow_popup_window_list_max_height);
        ArrowPopupView arrowPopupView = (ArrowPopupView) getLayoutInflater().inflate(R.layout.miuix_appcompat_arrow_popup_view, (ViewGroup) null, false);
        this.mArrowPopupView = arrowPopupView;
        super.setContentView(arrowPopupView);
        super.setWidth(-1);
        super.setHeight(-1);
        setSoftInputMode(3);
        this.mArrowPopupView.setArrowPopupWindow(this);
        super.setTouchInterceptor(getDefaultOnTouchListener());
        this.mArrowPopupView.addShadow();
        onPrepareWindow();
        update();
    }

    public void dismiss(boolean z2) {
        if (z2) {
            this.mArrowPopupView.animateToDismiss();
        } else {
            dismiss();
        }
    }

    public int getArrowMode() {
        return this.mArrowPopupView.getArrowMode();
    }

    public boolean getAutoDismiss() {
        return this.mAutoDismiss;
    }

    public int getContentHeight() {
        View contentView = getContentView();
        if (contentView != null) {
            return contentView.getHeight();
        }
        return 0;
    }

    @Override // android.widget.PopupWindow
    public View getContentView() {
        return this.mArrowPopupView.getContentView();
    }

    public int getContentWidth() {
        View contentView = getContentView();
        if (contentView != null) {
            return contentView.getWidth();
        }
        return 0;
    }

    public Context getContext() {
        return this.mContext;
    }

    public View.OnTouchListener getDefaultOnTouchListener() {
        return this.mArrowPopupView;
    }

    @Override // android.widget.PopupWindow
    public int getHeight() {
        return getContentHeight();
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public int getMaxAvailableHeight(int i2, int i3) {
        int arrowMode = getArrowMode();
        switch (arrowMode) {
            case 8:
            case 9:
            case 10:
                break;
            default:
                switch (arrowMode) {
                    case 16:
                    case 17:
                    case 18:
                        i2 = i3;
                        break;
                    default:
                        i2 = Math.max(i2, i3);
                        break;
                }
                break;
        }
        this.mMaxAvailableHeight = i2;
        return i2;
    }

    public AppCompatButton getNegativeButton() {
        return this.mArrowPopupView.getNegativeButton();
    }

    public AppCompatButton getPositiveButton() {
        return this.mArrowPopupView.getPositiveButton();
    }

    @Override // android.widget.PopupWindow
    public int getWidth() {
        return getContentWidth();
    }

    public void onPrepareWindow() {
    }

    public void setAlphaAnimationEnabled(boolean z2) {
        this.mArrowPopupView.setAlphaAnimation(z2);
    }

    public void setArrowMode(int i2) {
        this.mArrowPopupView.setArrowMode(i2);
    }

    public void setAutoDismiss(boolean z2) {
        this.mAutoDismiss = z2;
    }

    public void setContentHeight(int i2) {
        int i3;
        if (i2 == this.mMaxAvailableHeight) {
            i2 -= this.mArrowPopupView.getContentFrameWrapperBottomPadding() + this.mArrowPopupView.getContentFrameWrapperTopPadding();
        }
        if (!this.mArrowPopupView.isTitleEmpty()) {
            i2 -= this.mArrowPopupView.getTitleHeight();
        }
        View contentView = getContentView();
        if ((contentView instanceof ListView) && i2 > (i3 = this.mListViewMaxHeight)) {
            i2 = i3;
        }
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.height = i2;
            contentView.setLayoutParams(layoutParams);
        }
    }

    @Override // android.widget.PopupWindow
    public final void setContentView(View view) {
        this.mArrowPopupView.setContentView(view);
    }

    public void setContentWidth(int i2) {
        View contentView = getContentView();
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = i2;
            contentView.setLayoutParams(layoutParams);
        }
    }

    public final void setEnableTrackAnchor(boolean z2) {
        this.mArrowPopupView.setEnableTrackAnchor(z2);
    }

    @Override // android.widget.PopupWindow
    public void setHeight(int i2) {
        setContentHeight(i2);
    }

    public void setLayoutRtlMode(int i2) {
        if (i2 > 2 || i2 < 0) {
            this.mRtlMode = 2;
        } else {
            this.mRtlMode = i2;
        }
        this.mArrowPopupView.setLayoutRtlMode(i2);
    }

    public void setNegativeButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.mArrowPopupView.setNegativeButton(charSequence, onClickListener);
    }

    public void setPositiveButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.mArrowPopupView.setPositiveButton(charSequence, onClickListener);
    }

    public void setSuperHeight(int i2) {
        super.setHeight(i2);
    }

    public void setSuperWidth(int i2) {
        super.setWidth(i2);
    }

    public void setTitle(CharSequence charSequence) {
        this.mArrowPopupView.setTitle(charSequence);
    }

    @Override // android.widget.PopupWindow
    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.mArrowPopupView.setTouchInterceptor(onTouchListener);
    }

    @Override // android.widget.PopupWindow
    public void setWidth(int i2) {
        setContentWidth(i2);
    }

    public void show(View view, int i2, int i3) {
        this.mArrowPopupView.setAnchor(view);
        this.mArrowPopupView.setOffset(i2, i3);
        showAtLocation(view, 8388659, 0, 0);
        this.mArrowPopupView.setAutoDismiss(this.mAutoDismiss);
        this.mArrowPopupView.animateToShow();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3) {
        show(view, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(int i2, int i3, int i4, int i5, boolean z2) {
        super.update(0, 0, -2, -2, z2);
        setContentHeight(i5);
    }

    public ArrowPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mArrowPopupView.setContentView(view, layoutParams);
    }

    public void setNegativeButton(int i2, View.OnClickListener onClickListener) {
        setNegativeButton(this.mContext.getString(i2), onClickListener);
    }

    public void setPositiveButton(int i2, View.OnClickListener onClickListener) {
        setPositiveButton(this.mContext.getString(i2), onClickListener);
    }

    public void setTitle(int i2) {
        setTitle(this.mContext.getString(i2));
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        show(view, i2, i3);
    }

    public ArrowPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public final void setContentView(int i2) {
        this.mArrowPopupView.setContentView(i2);
    }

    public ArrowPopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mRtlMode = 2;
        this.mContext = context;
        this.mAutoDismiss = true;
        setupPopupWindow();
        this.mArrowPopupView.setLayoutRtlMode(this.mRtlMode);
    }
}
