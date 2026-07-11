package miuix.appcompat.internal.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.actionbar.CollapseTitle;
import miuix.appcompat.internal.app.widget.actionbar.ExpandTitle;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarViewFactory {
    public static CollapseTitle generateCollapseTitle(Context context, int i2, int i3) {
        CollapseTitle collapseTitle = new CollapseTitle(context, i2, i3);
        collapseTitle.init();
        return collapseTitle;
    }

    public static ExpandTitle generateExpandTitle(Context context) {
        ExpandTitle expandTitle = new ExpandTitle(context);
        expandTitle.init();
        return expandTitle;
    }

    public static View generateTitleUpView(final Context context, ViewGroup viewGroup) {
        final int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_title_view_padding_horizontal);
        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.post(new Runnable() { // from class: miuix.appcompat.internal.util.a
            @Override // java.lang.Runnable
            public final void run() {
                ActionBarViewFactory.lambda$generateTitleUpView$0(frameLayout, dimensionPixelOffset);
            }
        });
        frameLayout.setId(R.id.up);
        frameLayout.setVisibility(8);
        frameLayout.setContentDescription(context.getResources().getString(R.string.actionbar_button_up_description));
        ViewCompat.setAccessibilityDelegate(frameLayout, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.internal.util.ActionBarViewFactory.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setClassName(Button.class.getName());
            }
        });
        frameLayout.setClipChildren(false);
        final AppCompatImageView appCompatImageView = new AppCompatImageView(context);
        appCompatImageView.setDuplicateParentStateEnabled(true);
        appCompatImageView.post(new Runnable() { // from class: miuix.appcompat.internal.util.b
            @Override // java.lang.Runnable
            public final void run() {
                ActionBarViewFactory.lambda$generateTitleUpView$1(appCompatImageView, context);
            }
        });
        frameLayout.addView(appCompatImageView, new FrameLayout.LayoutParams(-2, -2));
        if (viewGroup != null) {
            viewGroup.addView(frameLayout);
        }
        return frameLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$generateTitleUpView$0(FrameLayout frameLayout, int i2) {
        if (ViewUtils.isLayoutRtl(frameLayout)) {
            frameLayout.setPadding(i2, 0, 0, 0);
        } else {
            frameLayout.setPadding(0, 0, i2, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$generateTitleUpView$1(AppCompatImageView appCompatImageView, Context context) {
        appCompatImageView.setImageDrawable(AttributeResolver.resolveDrawable(context, android.R.attr.homeAsUpIndicator));
        Folme.useAt(appCompatImageView).hover().setFeedbackRadius(60.0f);
        Folme.useAt(appCompatImageView).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf((View) appCompatImageView.getParent(), new AnimConfig[0]);
    }
}
