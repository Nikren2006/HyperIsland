package com.android.systemui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.android.systemui.miplay.R;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContentSupportLand extends QSControlMiPlayDetailContent implements QSControlMiPlayDetailHeader.TVControllerVisibilityCallback {
    private View spiltScreenDividerView;
    private Boolean tvControllerVisibility;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContentSupportLand(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    private final void resetByOrientation(int i2) {
        setOrientation(i2 == 1 ? 1 : 0);
        setMaxHeight(resetMaxHeight());
        resetUILayout();
        ArrayList<QSControlMiPlayDetailContent.ListItem> mListItems = getMListItems();
        if (mListItems != null) {
            updateHeight(mListItems, false, true);
        }
        getMHeader().onOrientationChanged(i2);
        requestLayout();
    }

    private final void resetUILayout() {
        View view = this.spiltScreenDividerView;
        if (view == null) {
            kotlin.jvm.internal.n.w("spiltScreenDividerView");
            view = null;
        }
        view.setVisibility(getOrientation() == 1 ? 8 : 0);
        QSControlMiPlayDetailHeader mHeader = getMHeader();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        Resources resources = mHeader.getContext().getResources();
        int i2 = R.dimen.miplay_detail_header_width;
        int dimensionPixelSize = resources.getDimensionPixelSize(i2);
        Resources resources2 = mHeader.getContext().getResources();
        int i3 = R.dimen.miplay_detail_header_height;
        CommonUtils.setLayoutSize$default(commonUtils, mHeader, dimensionPixelSize, resources2.getDimensionPixelSize(i3), false, 4, null);
        RecyclerView mRecycler = getMRecycler();
        int dimensionPixelSize2 = mRecycler.getContext().getResources().getDimensionPixelSize(i2);
        Context context = mRecycler.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        CommonUtils.setLayoutSize$default(commonUtils, mRecycler, dimensionPixelSize2, commonUtils.getInVerticalMode(context) ? mRecycler.getMeasuredHeight() : mRecycler.getContext().getResources().getDimensionPixelSize(i3), false, 4, null);
        commonUtils.setMarginTop(mRecycler, mRecycler.getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_recycler_margin_top), true);
        Resources resources3 = mRecycler.getContext().getResources();
        int i4 = R.dimen.miplay_detail_recycler_margin_start_end;
        mRecycler.setPaddingRelative(resources3.getDimensionPixelSize(i4), mRecycler.getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_recycler_padding_top), mRecycler.getContext().getResources().getDimensionPixelSize(i4), mRecycler.getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_recycler_padding_bottom));
        mRecycler.scrollToPosition(0);
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent
    public int getMaxItemCount() {
        return getContext().getResources().getConfiguration().orientation == 1 ? 4 : 6;
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent
    public int getRVMaxHeight() {
        if (getContext().getResources().getConfiguration().orientation == 1) {
            return getMaxHeight() - getResources().getDimensionPixelSize(kotlin.jvm.internal.n.c(this.tvControllerVisibility, Boolean.TRUE) ? R.dimen.miplay_detail_header_height_has_tvController : R.dimen.miplay_detail_header_height_no_tvController);
        }
        return getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_height);
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        prepareShowPanel();
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        View viewFindViewById = findViewById(R.id.spilt_screen_divider);
        kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
        this.spiltScreenDividerView = viewFindViewById;
        getMHeader().setTvControllerVisibilityCallback(this);
        prepareShowPanel();
    }

    @Override // com.android.systemui.QSControlMiPlayDetailHeader.TVControllerVisibilityCallback
    public void onTvControllerVisibilityChanged(boolean z2) {
        if (kotlin.jvm.internal.n.c(this.tvControllerVisibility, Boolean.valueOf(z2))) {
            return;
        }
        this.tvControllerVisibility = Boolean.valueOf(z2);
        if (getOrientation() == 1) {
            return;
        }
        getMHeader().onOrientationChanged(getResources().getConfiguration().orientation);
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent
    public void prepareShowPanel() {
        super.prepareShowPanel();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        resetByOrientation(commonUtils.getInVerticalMode(context) ? 1 : 0);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContentSupportLand(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ QSControlMiPlayDetailContentSupportLand(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContentSupportLand(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
    }
}
