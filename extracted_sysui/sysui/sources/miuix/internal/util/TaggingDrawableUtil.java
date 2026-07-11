package miuix.internal.util;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import miuix.internal.graphics.drawable.TaggingDrawable;

/* JADX INFO: loaded from: classes3.dex */
public class TaggingDrawableUtil {
    private static final int UNINITIAL = -1;
    private static int mPaddingLarge = -1;
    private static int mPaddingSingle = -1;
    private static int mPaddingSmall = -1;
    private static final int[] STATES_TAGS = {R.attr.state_single, R.attr.state_first, R.attr.state_middle, R.attr.state_last};
    private static final int[] STATE_SET_SINGLE = {R.attr.state_single};
    private static final int[] STATE_SET_FIRST = {R.attr.state_first};
    private static final int[] STATE_SET_MIDDLE = {R.attr.state_middle};
    private static final int[] STATE_SET_LAST = {R.attr.state_last};

    private static int getDimen(Context context, int i2) {
        return context.getResources().getDimensionPixelSize(i2);
    }

    public static void updateBackgroundState(View view, int i2, int i3) {
        if (view == null || i3 == 0) {
            return;
        }
        Drawable background = view.getBackground();
        if ((background instanceof StateListDrawable) && TaggingDrawable.containsTagState((StateListDrawable) background, STATES_TAGS)) {
            TaggingDrawable taggingDrawable = new TaggingDrawable(background);
            view.setBackground(taggingDrawable);
            background = taggingDrawable;
        }
        if (background instanceof TaggingDrawable) {
            ((TaggingDrawable) background).setTaggingState(i3 == 1 ? STATE_SET_SINGLE : i2 == 0 ? STATE_SET_FIRST : i2 == i3 - 1 ? STATE_SET_LAST : STATE_SET_MIDDLE);
        }
    }

    public static void updateItemBackground(View view, int i2, int i3) {
        updateBackgroundState(view, i2, i3);
        updateItemPadding(view, i2, i3);
    }

    public static void updateItemPadding(View view, int i2, int i3) {
        int i4;
        int i5;
        if (view == null || i3 == 0) {
            return;
        }
        Context context = view.getContext();
        int paddingStart = view.getPaddingStart();
        view.getPaddingTop();
        int paddingEnd = view.getPaddingEnd();
        view.getPaddingBottom();
        int dimen = getDimen(context, miuix.appcompat.R.dimen.miuix_appcompat_drop_down_item_min_height);
        if (i3 != 1) {
            if (mPaddingSmall == -1) {
                mPaddingSmall = getDimen(context, miuix.appcompat.R.dimen.miuix_appcompat_drop_down_menu_padding_small);
            }
            if (mPaddingLarge == -1) {
                mPaddingLarge = getDimen(context, miuix.appcompat.R.dimen.miuix_appcompat_drop_down_menu_padding_large);
            }
            if (i2 == 0) {
                i4 = mPaddingLarge;
                i5 = mPaddingSmall;
                dimen = getDimen(context, miuix.appcompat.R.dimen.miuix_appcompat_drop_down_first_item_min_height);
            } else if (i2 == i3 - 1) {
                i4 = mPaddingSmall;
                i5 = mPaddingLarge;
                dimen = getDimen(context, miuix.appcompat.R.dimen.miuix_appcompat_drop_down_last_item_min_height);
            } else {
                i4 = mPaddingSmall;
            }
            view.setMinimumHeight(dimen);
            view.setPaddingRelative(paddingStart, i4, paddingEnd, i5);
        }
        if (mPaddingSingle == -1) {
            mPaddingSingle = getDimen(context, miuix.appcompat.R.dimen.miuix_appcompat_drop_down_menu_padding_single_item);
        }
        i4 = mPaddingSingle;
        i5 = i4;
        view.setMinimumHeight(dimen);
        view.setPaddingRelative(paddingStart, i4, paddingEnd, i5);
    }
}
