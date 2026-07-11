package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;
import miuix.appcompat.R;
import miuix.core.util.MiuixUIUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ActionMenuItemViewChildren {
    private static final int DEFAULT_IMAGE_VIEW_SIZE_DP = 28;
    private static final int ITEM_TEXT_SIZE_DP = 11;
    private static final int ITEM_TEXT_SIZE_DP_IN_LARGE_FONT = 16;
    private int mDensityDpi;
    private ImageView mImageView;
    private boolean mLargerFontEnabled = false;
    private LinearLayout mParent;
    private TextView mTextView;

    public ActionMenuItemViewChildren(final LinearLayout linearLayout) {
        this.mParent = linearLayout;
        Context context = linearLayout.getContext();
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        LinearLayout.inflate(context, R.layout.miuix_appcompat_action_menu_item_child_layout, linearLayout);
        this.mImageView = (ImageView) linearLayout.findViewById(R.id.action_menu_item_child_icon);
        this.mTextView = (TextView) linearLayout.findViewById(R.id.action_menu_item_child_text);
        this.mImageView.setForceDarkAllowed(false);
        this.mDensityDpi = context.getResources().getDisplayMetrics().densityDpi;
        linearLayout.post(new Runnable() { // from class: miuix.appcompat.internal.view.menu.action.ActionMenuItemViewChildren.1
            @Override // java.lang.Runnable
            public void run() {
                Folme.useAt(linearLayout).touch().setScale(1.0f, new ITouchStyle.TouchType[0]).setAlpha(0.6f, ITouchStyle.TouchType.DOWN).setAlpha(1.0f, ITouchStyle.TouchType.UP).handleTouchOf(linearLayout, new AnimConfig[0]);
                Folme.useAt(linearLayout).hover().setAlpha(1.0f, new IHoverStyle.HoverType[0]).setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(linearLayout, new AnimConfig[0]);
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        int i2 = configuration.densityDpi;
        if (i2 != this.mDensityDpi) {
            this.mDensityDpi = i2;
            int iDp2px = MiuixUIUtils.dp2px(this.mImageView.getContext(), 28.0f);
            this.mImageView.setLayoutParams(new LinearLayout.LayoutParams(iDp2px, iDp2px));
            setLargeFontEnabled(this.mLargerFontEnabled);
        }
    }

    public void setContentDescription(CharSequence charSequence) {
        if (charSequence == null || TextUtils.isEmpty(charSequence)) {
            this.mParent.setContentDescription(this.mTextView.getText());
        } else {
            this.mParent.setContentDescription(charSequence);
        }
    }

    public void setEnabled(boolean z2) {
        this.mImageView.setEnabled(z2);
        this.mTextView.setEnabled(z2);
    }

    public void setIcon(Drawable drawable) {
        if (this.mImageView.getDrawable() != drawable) {
            this.mImageView.setImageDrawable(drawable);
        }
    }

    public void setLargeFontEnabled(boolean z2) {
        this.mLargerFontEnabled = z2;
        if (z2) {
            this.mTextView.setTextSize(1, 16.0f);
        } else {
            this.mTextView.setTextSize(1, 11.0f);
        }
    }

    public void setSelected(boolean z2) {
        this.mImageView.setSelected(z2);
        this.mTextView.setSelected(z2);
    }

    public void setText(CharSequence charSequence) {
        this.mTextView.setText(charSequence);
    }
}
