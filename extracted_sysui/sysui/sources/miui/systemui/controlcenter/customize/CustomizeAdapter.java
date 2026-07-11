package miui.systemui.controlcenter.customize;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public interface CustomizeAdapter {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int HIDE_REASON_BACK = 0;
    public static final int HIDE_REASON_HOME = 1;
    public static final int HIDE_REASON_SAVE = 2;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int HIDE_REASON_BACK = 0;
        public static final int HIDE_REASON_HOME = 1;
        public static final int HIDE_REASON_SAVE = 2;

        private Companion() {
        }
    }

    RecyclerView.Adapter<?> getAddedAdapter();

    String getAddedSubtitle();

    String getAddedTitle();

    RecyclerView.Adapter<?> getNotAddedAdapter();

    String getNotAddedTitle();

    String getSave();

    void hide();

    default void hide(int i2) {
        hide();
    }

    void onHideFinish();

    void onHideStart();

    void onShowFinish();

    void onShowStart();

    default void onShowStart(View titleContainer) {
        n.g(titleContainer, "titleContainer");
        onShowStart();
    }

    void show();
}
