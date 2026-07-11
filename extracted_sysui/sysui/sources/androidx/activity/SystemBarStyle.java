package androidx.activity;

import androidx.annotation.ColorInt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class SystemBarStyle {
    public static final Companion Companion = new Companion(null);
    private final int darkScrim;
    private final Function1 detectDarkMode;
    private final int lightScrim;
    private final int nightMode;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ SystemBarStyle auto$default(Companion companion, int i2, int i3, Function1 function1, int i4, Object obj) {
            if ((i4 & 4) != 0) {
                function1 = SystemBarStyle$Companion$auto$1.INSTANCE;
            }
            return companion.auto(i2, i3, function1);
        }

        public final SystemBarStyle auto(@ColorInt int i2, @ColorInt int i3) {
            return auto$default(this, i2, i3, null, 4, null);
        }

        public final SystemBarStyle dark(@ColorInt int i2) {
            return new SystemBarStyle(i2, i2, 2, SystemBarStyle$Companion$dark$1.INSTANCE, null);
        }

        public final SystemBarStyle light(@ColorInt int i2, @ColorInt int i3) {
            return new SystemBarStyle(i2, i3, 1, SystemBarStyle$Companion$light$1.INSTANCE, null);
        }

        private Companion() {
        }

        public final SystemBarStyle auto(@ColorInt int i2, @ColorInt int i3, Function1 detectDarkMode) {
            n.g(detectDarkMode, "detectDarkMode");
            return new SystemBarStyle(i2, i3, 0, detectDarkMode, null);
        }
    }

    public /* synthetic */ SystemBarStyle(int i2, int i3, int i4, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3, i4, function1);
    }

    public static final SystemBarStyle auto(@ColorInt int i2, @ColorInt int i3) {
        return Companion.auto(i2, i3);
    }

    public static final SystemBarStyle dark(@ColorInt int i2) {
        return Companion.dark(i2);
    }

    public static final SystemBarStyle light(@ColorInt int i2, @ColorInt int i3) {
        return Companion.light(i2, i3);
    }

    public final int getDarkScrim$activity_release() {
        return this.darkScrim;
    }

    public final Function1 getDetectDarkMode$activity_release() {
        return this.detectDarkMode;
    }

    public final int getNightMode$activity_release() {
        return this.nightMode;
    }

    public final int getScrim$activity_release(boolean z2) {
        return z2 ? this.darkScrim : this.lightScrim;
    }

    public final int getScrimWithEnforcedContrast$activity_release(boolean z2) {
        if (this.nightMode == 0) {
            return 0;
        }
        return z2 ? this.darkScrim : this.lightScrim;
    }

    private SystemBarStyle(int i2, int i3, int i4, Function1 function1) {
        this.lightScrim = i2;
        this.darkScrim = i3;
        this.nightMode = i4;
        this.detectDarkMode = function1;
    }

    public static final SystemBarStyle auto(@ColorInt int i2, @ColorInt int i3, Function1 function1) {
        return Companion.auto(i2, i3, function1);
    }
}
