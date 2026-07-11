package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.graphics.drawable.Icon;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlInterface {
    static /* synthetic */ void getDeviceType$annotations() {
    }

    ComponentName getComponent();

    String getControlId();

    Icon getCustomIcon();

    int getDeviceType();

    boolean getFavorite();

    default boolean getRemoved() {
        return false;
    }

    CharSequence getSubtitle();

    CharSequence getTitle();

    void setFavorite(boolean z2);
}
