package miui.systemui.util;

import android.view.LayoutInflater;

/* JADX INFO: loaded from: classes4.dex */
public interface InjectionInflationController {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String VIEW_CONTEXT = "view_context";

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String VIEW_CONTEXT = "view_context";

        private Companion() {
        }
    }

    LayoutInflater injectable(LayoutInflater layoutInflater);
}
