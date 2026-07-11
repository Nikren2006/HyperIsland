package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.R;

/* JADX INFO: loaded from: classes.dex */
class MediaRouteExpandCollapseButton extends AppCompatImageButton {
    final AnimationDrawable mCollapseAnimationDrawable;
    final String mCollapseGroupDescription;
    final AnimationDrawable mExpandAnimationDrawable;
    final String mExpandGroupDescription;
    boolean mIsGroupExpanded;
    View.OnClickListener mListener;

    public MediaRouteExpandCollapseButton(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.mr_group_expand);
        this.mExpandAnimationDrawable = animationDrawable;
        AnimationDrawable animationDrawable2 = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.mr_group_collapse);
        this.mCollapseAnimationDrawable = animationDrawable2;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(MediaRouterThemeHelper.getControllerColor(context, i2), PorterDuff.Mode.SRC_IN);
        animationDrawable.setColorFilter(porterDuffColorFilter);
        animationDrawable2.setColorFilter(porterDuffColorFilter);
        String string = context.getString(R.string.mr_controller_expand_group);
        this.mExpandGroupDescription = string;
        this.mCollapseGroupDescription = context.getString(R.string.mr_controller_collapse_group);
        setImageDrawable(animationDrawable.getFrame(0));
        setContentDescription(string);
        super.setOnClickListener(new View.OnClickListener() { // from class: androidx.mediarouter.app.MediaRouteExpandCollapseButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
                boolean z2 = mediaRouteExpandCollapseButton.mIsGroupExpanded;
                mediaRouteExpandCollapseButton.mIsGroupExpanded = !z2;
                if (z2) {
                    mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.mCollapseAnimationDrawable);
                    MediaRouteExpandCollapseButton.this.mCollapseAnimationDrawable.start();
                    MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton2 = MediaRouteExpandCollapseButton.this;
                    mediaRouteExpandCollapseButton2.setContentDescription(mediaRouteExpandCollapseButton2.mExpandGroupDescription);
                } else {
                    mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.mExpandAnimationDrawable);
                    MediaRouteExpandCollapseButton.this.mExpandAnimationDrawable.start();
                    MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton3 = MediaRouteExpandCollapseButton.this;
                    mediaRouteExpandCollapseButton3.setContentDescription(mediaRouteExpandCollapseButton3.mCollapseGroupDescription);
                }
                View.OnClickListener onClickListener = MediaRouteExpandCollapseButton.this.mListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }
}
