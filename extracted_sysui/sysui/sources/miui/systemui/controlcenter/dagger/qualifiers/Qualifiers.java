package miui.systemui.controlcenter.dagger.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class Qualifiers {
    public static final Qualifiers INSTANCE = new Qualifiers();

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ControlCenter {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LeftMainPanel {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MainPanelContainer {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Mirror {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RightMainPanel {
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WindowView {
    }

    private Qualifiers() {
    }
}
