package miuix.appcompat.internal.app.widget.actionbar;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.widget.TextViewCompat;
import miuix.appcompat.app.TextViewDrawableConfig;
import miuix.appcompat.internal.view.CollapseTitleColorTransitionTextView;
import miuix.appcompat.internal.view.ColorTransitionTextView;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes3.dex */
public class CollapseTitle {
    private ColorTransitionTextView mCollapseSubtitleView;
    private LinearLayout mCollapseTitleLayout;
    private ColorTransitionTextView mCollapseTitleView;
    private Context mContext;
    private int mSubtitleStyle;
    private int mTitleStyle;
    private boolean mVisible = true;
    private float mDefaultSubtitleSize = 0.0f;
    private boolean mIsTitleDirty = false;
    private float mCollapseTitlePaintTextSize = -1.0f;
    private float mTitleLength = 0.0f;
    private boolean mSubtitleSizeable = true;
    private boolean mTextColorTransitEnable = false;
    private boolean mLargeFontAdaptEnable = false;
    private int mLargeFontTitleMaxLine = 2;
    View.OnTouchListener subtitleTouchListener = new View.OnTouchListener() { // from class: miuix.appcompat.internal.app.widget.actionbar.CollapseTitle.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return !view.isClickable();
        }
    };

    public CollapseTitle(Context context, int i2, int i3) {
        this.mContext = context;
        this.mTitleStyle = i2;
        this.mSubtitleStyle = i3;
    }

    private LinearLayout.LayoutParams getChildLayoutParams() {
        return new LinearLayout.LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0() {
        this.mCollapseTitleLayout.setBackground(AttributeResolver.resolveDrawable(this.mContext, R.attr.actionBarItemBackground));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnClickListener$1() {
        this.mCollapseTitleLayout.setTouchDelegate(new TouchDelegate(new Rect(0, 0, this.mCollapseTitleLayout.getWidth(), this.mCollapseTitleLayout.getHeight()), this.mCollapseTitleView));
    }

    private void resetTitleMaxLine(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mCollapseTitleView;
        if (colorTransitionTextView == null || !this.mLargeFontAdaptEnable) {
            return;
        }
        if (z2 && colorTransitionTextView.getMaxLines() > 1) {
            this.mCollapseTitleView.setSingleLine(true);
            this.mCollapseTitleView.setMaxLines(1);
        } else {
            if (z2 || this.mCollapseTitleView.getMaxLines() != 1) {
                return;
            }
            this.mCollapseTitleView.setSingleLine(false);
            this.mCollapseTitleView.setMaxLines(this.mLargeFontTitleMaxLine);
        }
    }

    public boolean canTitleBeShown(String str) {
        TextPaint paint = this.mCollapseTitleView.getPaint();
        float f2 = this.mCollapseTitlePaintTextSize;
        if (f2 == -1.0f || f2 != paint.getTextSize()) {
            this.mCollapseTitlePaintTextSize = paint.getTextSize();
            this.mIsTitleDirty = true;
        }
        if (this.mIsTitleDirty) {
            this.mTitleLength = this.mCollapseTitleView.getPaint().measureText(str);
            this.mIsTitleDirty = false;
        }
        return this.mCollapseTitleView.getMeasuredWidth() == 0 || this.mTitleLength <= ((float) this.mCollapseTitleView.getMeasuredWidth());
    }

    public Rect getHitRect() {
        Rect rect = new Rect();
        this.mCollapseTitleLayout.getHitRect(rect);
        return rect;
    }

    public View getLayout() {
        return this.mCollapseTitleLayout;
    }

    public float getSubtitleAdjustSize() {
        float f2 = this.mDefaultSubtitleSize;
        Resources resources = this.mContext.getResources();
        int measuredHeight = ((this.mCollapseTitleLayout.getMeasuredHeight() - this.mCollapseTitleView.getMeasuredHeight()) - this.mCollapseSubtitleView.getPaddingTop()) - this.mCollapseSubtitleView.getPaddingBottom();
        if (measuredHeight <= 0) {
            return f2;
        }
        TextPaint textPaint = new TextPaint(this.mCollapseSubtitleView.getPaint());
        textPaint.setTextSize(f2);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        int iCeil = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        float f3 = f2 / 2.0f;
        float f4 = resources.getDisplayMetrics().scaledDensity;
        while (iCeil > measuredHeight && f2 >= f3) {
            f2 -= f4;
            textPaint.setTextSize(f2);
            Paint.FontMetrics fontMetrics2 = textPaint.getFontMetrics();
            iCeil = (int) Math.ceil(fontMetrics2.descent - fontMetrics2.ascent);
        }
        return f2;
    }

    public ViewGroup getTitleParent() {
        return (ViewGroup) this.mCollapseTitleView.getParent();
    }

    public int getTitleVisibility() {
        return this.mCollapseTitleView.getVisibility();
    }

    public int getVisibility() {
        return this.mCollapseTitleLayout.getVisibility();
    }

    public void init() {
        Resources resources = this.mContext.getResources();
        EnvStateManager.getWindowInfo(this.mContext);
        this.mDefaultSubtitleSize = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_subtitle_text_size);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        this.mCollapseTitleLayout = linearLayout;
        linearLayout.setImportantForAccessibility(2);
        CollapseTitleColorTransitionTextView collapseTitleColorTransitionTextView = new CollapseTitleColorTransitionTextView(this.mContext, null, miuix.appcompat.R.attr.collapseTitleTheme);
        this.mCollapseTitleView = collapseTitleColorTransitionTextView;
        collapseTitleColorTransitionTextView.setVerticalScrollBarEnabled(false);
        this.mCollapseTitleView.setHorizontalScrollBarEnabled(false);
        this.mCollapseTitleView.setFocusableInTouchMode(false);
        boolean z2 = AttributeResolver.resolveBoolean(this.mContext, miuix.appcompat.R.attr.actionBarTitleAdaptLargeFont, true) && (MiuixUIUtils.getFontLevel(this.mContext) == 2);
        this.mLargeFontAdaptEnable = z2;
        if (z2) {
            this.mLargeFontTitleMaxLine = AttributeResolver.resolveInt(this.mContext, miuix.appcompat.R.attr.collapseTitleLargeFontMaxLine, 2);
            this.mCollapseTitleView.setSingleLine(false);
            this.mCollapseTitleView.setMaxLines(this.mLargeFontTitleMaxLine);
        }
        ColorTransitionTextView colorTransitionTextView = new ColorTransitionTextView(this.mContext, null, miuix.appcompat.R.attr.collapseSubtitleTheme);
        this.mCollapseSubtitleView = colorTransitionTextView;
        colorTransitionTextView.setVerticalScrollBarEnabled(false);
        this.mCollapseSubtitleView.setHorizontalScrollBarEnabled(false);
        this.mCollapseTitleLayout.setOrientation(1);
        this.mCollapseTitleLayout.post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.actionbar.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f6061a.lambda$init$0();
            }
        });
        this.mCollapseTitleView.setId(miuix.appcompat.R.id.action_bar_title);
        this.mCollapseTitleLayout.addView(this.mCollapseTitleView, getChildLayoutParams());
        this.mCollapseSubtitleView.setId(miuix.appcompat.R.id.action_bar_subtitle);
        this.mCollapseSubtitleView.setVisibility(8);
        this.mCollapseTitleLayout.addView(this.mCollapseSubtitleView, getChildLayoutParams());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mCollapseSubtitleView.getLayoutParams();
        layoutParams.topMargin = resources.getDimensionPixelOffset(miuix.appcompat.R.dimen.action_bar_subtitle_top_margin);
        layoutParams.bottomMargin = resources.getDimensionPixelOffset(miuix.appcompat.R.dimen.action_bar_subtitle_bottom_margin);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void setAllTitlesClickable(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mCollapseTitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setClickable(z2);
        }
        ColorTransitionTextView colorTransitionTextView2 = this.mCollapseSubtitleView;
        if (colorTransitionTextView2 != null) {
            colorTransitionTextView2.setClickable(z2);
        }
    }

    public void setEnabled(boolean z2) {
        this.mCollapseTitleLayout.setEnabled(z2);
    }

    public void setOnClickListener(View.OnClickListener onClickListener, boolean z2) {
        this.mCollapseTitleView.setOnClickListener(onClickListener);
        this.mCollapseTitleView.post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.actionbar.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6060a.lambda$setOnClickListener$1();
            }
        });
        this.mCollapseTitleView.setClickable(z2);
    }

    public void setSubTitle(CharSequence charSequence) {
        this.mCollapseSubtitleView.setText(charSequence);
        int i2 = TextUtils.isEmpty(charSequence) ? 8 : 0;
        setSubTitleVisibility(i2);
        resetTitleMaxLine(i2 == 0);
    }

    public void setSubTitleClickable(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mCollapseSubtitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setClickable(z2);
        }
    }

    public void setSubTitleDrawable(TextViewDrawableConfig textViewDrawableConfig) {
        textViewDrawableConfig.setTextViewDrawable(this.mCollapseSubtitleView);
    }

    public void setSubTitleOnClickListener(View.OnClickListener onClickListener, boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mCollapseSubtitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setOnClickListener(onClickListener);
            this.mCollapseSubtitleView.setClickable(z2);
            this.mCollapseSubtitleView.setOnTouchListener(this.subtitleTouchListener);
        }
    }

    public void setSubTitleStyle(int i2) {
        this.mSubtitleStyle = i2;
        TextViewCompat.setTextAppearance(this.mCollapseSubtitleView, i2);
        this.mCollapseTitleView.invalidate();
    }

    public void setSubTitleTextSize(float f2) {
        if (this.mSubtitleSizeable) {
            this.mCollapseSubtitleView.setTextSize(0, f2);
        }
    }

    public void setSubTitleVisibility(int i2) {
        this.mCollapseSubtitleView.setVisibility(i2);
    }

    public void setTextColorTransitEnable(boolean z2, int i2) {
        if (this.mTextColorTransitEnable != z2) {
            if (!z2) {
                this.mCollapseTitleView.startColorTransition(false, false);
            }
            this.mTextColorTransitEnable = z2;
            if (z2 && i2 == 0) {
                this.mCollapseTitleView.startColorTransition(true, false);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.mCollapseTitleView.getText())) {
            return;
        }
        this.mCollapseTitleView.setText(charSequence);
        setEnabled(!TextUtils.isEmpty(charSequence));
        this.mIsTitleDirty = true;
    }

    public void setTitleClickable(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mCollapseTitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setClickable(z2);
        }
    }

    public void setTitleStyle(int i2) {
        this.mTitleStyle = i2;
        TextViewCompat.setTextAppearance(this.mCollapseTitleView, i2);
        this.mCollapseTitleView.invalidate();
    }

    public void setTitleVisibility(int i2) {
        this.mCollapseTitleView.setVisibility(i2);
    }

    public void setVisibility(int i2) {
        if (this.mVisible || i2 != 0) {
            this.mCollapseTitleLayout.setVisibility(i2);
        } else {
            this.mCollapseTitleLayout.setVisibility(4);
        }
    }

    public void setVisible(boolean z2) {
        if (this.mVisible != z2) {
            this.mVisible = z2;
            this.mCollapseTitleLayout.setVisibility(z2 ? 0 : 4);
        }
    }

    public void startColorTransition(boolean z2, boolean z3) {
        if (this.mTextColorTransitEnable) {
            this.mCollapseTitleView.startColorTransition(z2, z3);
        }
    }

    public void updateTitleCenter(boolean z2) {
        ViewGroup titleParent = getTitleParent();
        if (titleParent instanceof LinearLayout) {
            ((LinearLayout) titleParent).setGravity((z2 ? 1 : 8388611) | 16);
        }
        this.mCollapseTitleView.setGravity((z2 ? 1 : 8388611) | 16);
        ColorTransitionTextView colorTransitionTextView = this.mCollapseTitleView;
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        colorTransitionTextView.setEllipsize(truncateAt);
        this.mCollapseSubtitleView.setGravity((z2 ? 1 : 8388611) | 16);
        this.mCollapseSubtitleView.setEllipsize(truncateAt);
    }
}
