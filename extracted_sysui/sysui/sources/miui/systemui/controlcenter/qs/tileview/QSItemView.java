package miui.systemui.controlcenter.qs.tileview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.widget.ExpandableView;

/* JADX INFO: loaded from: classes.dex */
public interface QSItemView extends ExpandableView {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final Drawable getInvisibleDrawableCompat(QSTile.Icon icon, Context context) {
            n.g(icon, "<this>");
            n.g(context, "context");
            try {
                return icon.getInvisibleDrawable(context);
            } catch (Throwable unused) {
                return icon.getDrawable(context);
            }
        }

        public final boolean isRestrictedCompat(QSTile.State state) {
            n.g(state, "<this>");
            try {
                QSTile.RestrictState restrictState = state instanceof QSTile.RestrictState ? (QSTile.RestrictState) state : null;
                if (restrictState != null) {
                    return restrictState.isRestricted;
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        }

        public final boolean isTrafficWarning(QSTile.State state) {
            n.g(state, "<this>");
            try {
                QSTile.TrafficState trafficState = state instanceof QSTile.TrafficState ? (QSTile.TrafficState) state : null;
                if (trafficState != null) {
                    return trafficState.warning;
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        }

        public final void setRestrictedCompat(QSTile.State state, boolean z2) {
            n.g(state, "<this>");
            try {
                QSTile.RestrictState restrictState = state instanceof QSTile.RestrictState ? (QSTile.RestrictState) state : null;
                if (restrictState == null) {
                    return;
                }
                restrictState.isRestricted = z2;
            } catch (Throwable unused) {
            }
        }
    }

    View asView();

    default void attachListeners(Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
    }

    void detachListeners();

    View getBlendTarget();

    boolean getCompatTile();

    MainPanelItemViewHolder.TouchAnimator getTouchAnimator();

    void onConfigurationChanged(int i2);

    default void onModeChanged(MainPanelController.Mode mode, boolean z2) {
        n.g(mode, "mode");
    }

    void onStateChanged(QSTile.State state);

    default void onStyleChanged(MainPanelController.Style style) {
        n.g(style, "style");
    }

    default void onTextModeChanged(QSListController.TextMode mode, boolean z2) {
        n.g(mode, "mode");
    }

    default void recycle() {
    }

    void setCompatTile(boolean z2);

    void setTouchAnimator(MainPanelItemViewHolder.TouchAnimator touchAnimator);

    default void startMarquee() {
    }

    default void stopMarquee() {
    }

    default void updateAdded(boolean z2, boolean z3) {
    }

    default void updateCustomizeState(QSTile.State state, boolean z2) {
    }

    default void updateRemovable(boolean z2, boolean z3) {
    }

    void updateState(QSTile.State state, boolean z2, boolean z3);
}
