package miuix.appcompat.internal.app.widget.actionbar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.widget.TextViewCompat;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.ColorTransitionTextView;
import miuix.core.util.RomUtils;
import miuix.internal.util.AttributeResolver;
import miuix.theme.Typography;

/* JADX INFO: loaded from: classes3.dex */
public class ExpandTitle {
    private Context mContext;
    private ColorTransitionTextView mExpandSubtitleView;
    private LinearLayout mExpandTitleLayout;
    private ColorTransitionTextView mExpandTitleView;
    private boolean mVisible = true;
    private boolean mTextColorTransitEnable = false;
    View.OnTouchListener subtitleTouchListener = new View.OnTouchListener() { // from class: miuix.appcompat.internal.app.widget.actionbar.ExpandTitle.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return !view.isClickable();
        }
    };
    private int mTitleStyle = R.style.Miuix_AppCompat_TextAppearance_WindowTitle_Expand;
    private int mSubtitleStyle = R.style.Miuix_AppCompat_TextAppearance_WindowTitle_Subtitle_Expand;

    public ExpandTitle(Context context) {
        this.mContext = context;
    }

    private LinearLayout.LayoutParams getChildLayoutParams() {
        return new LinearLayout.LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0() {
        this.mExpandTitleLayout.setBackground(AttributeResolver.resolveDrawable(this.mContext, android.R.attr.actionBarItemBackground));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnClickListener$1() {
        this.mExpandTitleLayout.setTouchDelegate(new TouchDelegate(new Rect(0, 0, this.mExpandTitleLayout.getWidth(), this.mExpandTitleLayout.getHeight()), this.mExpandTitleView));
    }

    public View getLayout() {
        return this.mExpandTitleLayout;
    }

    public int getVisibility() {
        return this.mExpandTitleLayout.getVisibility();
    }

    public void init() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        this.mExpandTitleLayout = linearLayout;
        linearLayout.setImportantForAccessibility(2);
        this.mExpandTitleLayout.setOrientation(1);
        this.mExpandTitleLayout.post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.actionbar.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f6063a.lambda$init$0();
            }
        });
        ColorTransitionTextView colorTransitionTextView = new ColorTransitionTextView(this.mContext, null, R.attr.expandTitleTheme);
        this.mExpandTitleView = colorTransitionTextView;
        colorTransitionTextView.setId(R.id.action_bar_title_expand);
        this.mExpandTitleView.setVerticalScrollBarEnabled(false);
        this.mExpandTitleView.setHorizontalScrollBarEnabled(false);
        this.mExpandTitleView.setFocusableInTouchMode(false);
        if (RomUtils.getHyperOsVersion() <= 1) {
            Typography.applyMiSansLight(this.mExpandTitleView);
        }
        this.mExpandTitleLayout.addView(this.mExpandTitleView, getChildLayoutParams());
        ColorTransitionTextView colorTransitionTextView2 = new ColorTransitionTextView(this.mContext, null, R.attr.expandSubtitleTheme);
        this.mExpandSubtitleView = colorTransitionTextView2;
        colorTransitionTextView2.setId(R.id.action_bar_subtitle_expand);
        this.mExpandSubtitleView.setVisibility(8);
        this.mExpandSubtitleView.setVerticalScrollBarEnabled(false);
        this.mExpandSubtitleView.setHorizontalScrollBarEnabled(false);
        this.mExpandTitleLayout.addView(this.mExpandSubtitleView, getChildLayoutParams());
        Resources resources = this.mContext.getResources();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mExpandSubtitleView.getLayoutParams();
        layoutParams.topMargin = resources.getDimensionPixelOffset(R.dimen.action_bar_subtitle_top_margin);
        layoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen.action_bar_subtitle_bottom_margin);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mExpandTitleView.setTextAppearance(this.mTitleStyle);
        this.mExpandSubtitleView.setTextAppearance(this.mSubtitleStyle);
        if (RomUtils.getHyperOsVersion() <= 1) {
            Typography.applyMiSansLight(this.mExpandTitleView);
        }
    }

    public void setAllTitlesClickable(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mExpandTitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setClickable(z2);
        }
        ColorTransitionTextView colorTransitionTextView2 = this.mExpandSubtitleView;
        if (colorTransitionTextView2 != null) {
            colorTransitionTextView2.setClickable(z2);
        }
    }

    public void setEnabled(boolean z2) {
        this.mExpandTitleLayout.setEnabled(z2);
    }

    public void setOnClickListener(View.OnClickListener onClickListener, boolean z2) {
        this.mExpandTitleView.setOnClickListener(onClickListener);
        this.mExpandTitleView.post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.actionbar.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f6062a.lambda$setOnClickListener$1();
            }
        });
        this.mExpandTitleView.setClickable(z2);
    }

    public void setSubTitle(CharSequence charSequence) {
        this.mExpandSubtitleView.setText(charSequence);
        setSubTitleVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public void setSubTitleClickable(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mExpandSubtitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setClickable(z2);
        }
    }

    public void setSubTitleOnClickListener(View.OnClickListener onClickListener, boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mExpandSubtitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setOnClickListener(onClickListener);
            this.mExpandSubtitleView.setClickable(z2);
            this.mExpandSubtitleView.setOnTouchListener(this.subtitleTouchListener);
        }
    }

    public void setSubTitleStyle(int i2) {
        this.mSubtitleStyle = i2;
        TextViewCompat.setTextAppearance(this.mExpandSubtitleView, i2);
        this.mExpandSubtitleView.invalidate();
    }

    public void setSubTitleVisibility(int i2) {
        this.mExpandSubtitleView.setVisibility(i2);
    }

    public void setTextColorTransitEnable(boolean z2, int i2) {
        if (this.mTextColorTransitEnable != z2) {
            if (!z2) {
                this.mExpandTitleView.startColorTransition(false, false);
            }
            this.mTextColorTransitEnable = z2;
            if (z2 && i2 == 1) {
                this.mExpandTitleView.startColorTransition(true, false);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.mExpandTitleView.setText(charSequence);
        setEnabled(!TextUtils.isEmpty(charSequence));
    }

    public void setTitleClickable(boolean z2) {
        ColorTransitionTextView colorTransitionTextView = this.mExpandTitleView;
        if (colorTransitionTextView != null) {
            colorTransitionTextView.setClickable(z2);
        }
    }

    public void setTitleStyle(int i2) {
        this.mTitleStyle = i2;
        TextViewCompat.setTextAppearance(this.mExpandTitleView, i2);
        this.mExpandTitleView.invalidate();
    }

    public void setTitleVisibility(int i2) {
        this.mExpandTitleView.setVisibility(i2);
    }

    public void setVisibility(int i2) {
        if (this.mVisible || i2 != 0) {
            this.mExpandTitleLayout.setVisibility(i2);
        } else {
            this.mExpandTitleLayout.setVisibility(4);
        }
    }

    public void setVisible(boolean z2) {
        if (this.mVisible != z2) {
            this.mVisible = z2;
            this.mExpandTitleLayout.setVisibility(z2 ? 0 : 4);
        }
    }

    public void startColorTransition(boolean z2, boolean z3) {
        if (this.mTextColorTransitEnable) {
            this.mExpandTitleView.startColorTransition(z2, z3);
        }
    }
}
