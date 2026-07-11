package miuix.responsive.interfaces;

import android.content.res.Configuration;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ScreenSpec;

/* JADX INFO: loaded from: classes5.dex */
public interface IResponsive<T> {
    default void dispatchResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        onResponsiveLayout(configuration, screenSpec, z2);
    }

    ResponsiveState getResponsiveState();

    T getResponsiveSubject();

    void onResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2);
}
