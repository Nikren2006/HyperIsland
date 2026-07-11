package miuix.appcompat.internal.app.widget;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import miuix.appcompat.app.ActionBar;

/* JADX INFO: loaded from: classes3.dex */
public interface SecondaryTabBar extends ActionBar.FragmentViewPagerChangeListener {
    void addTab(@NonNull ActionBar.Tab tab, int i2, boolean z2);

    void addTab(@NonNull ActionBar.Tab tab, boolean z2);

    void animateToTab(int i2);

    @NonNull
    ViewGroup asViewGroup();

    void removeAllTabs();

    void removeTabAt(int i2);

    void setBadgeVisibility(int i2, boolean z2);

    void setParentBlurEnabled(boolean z2);

    void setTabBadgeDisappearOnClick(int i2, boolean z2);

    void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4);

    void setTabSelected(int i2);

    @Deprecated
    void setTextAppearance(int i2, int i3);

    void setTextAppearance(int i2, int i3, int i4);

    void updateTab(int i2);
}
