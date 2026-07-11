package miui.systemui.controlcenter.utils;

import H0.d;
import H0.e;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.os.Build;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class WordlessModeCompat {
    public static final WordlessModeCompat INSTANCE = new WordlessModeCompat();
    private static final d isShowWordlessModeLabel$delegate = e.b(AnonymousClass2.INSTANCE);

    /* JADX INFO: renamed from: miui.systemui.controlcenter.utils.WordlessModeCompat$isShowWordlessModeLabel$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(Build.IS_INTERNATIONAL_BUILD);
        }
    }

    private WordlessModeCompat() {
    }

    public final boolean isShowWordlessModeLabel() {
        return ((Boolean) isShowWordlessModeLabel$delegate.getValue()).booleanValue();
    }

    public final void updateResourcesCompat(View view) {
        n.g(view, "view");
        int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen.qs_edit_wordless_mode_header_margin_bottom);
        int dimensionPixelSize2 = view.getContext().getResources().getDimensionPixelSize(R.dimen.customize_header_inside_padding);
        view.setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize);
    }
}
