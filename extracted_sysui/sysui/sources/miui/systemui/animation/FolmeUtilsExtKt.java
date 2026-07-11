package miui.systemui.animation;

import kotlin.jvm.internal.n;
import miuix.animation.FolmeEase;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes2.dex */
public final class FolmeUtilsExtKt {
    private static final EaseManager.EaseStyle EASE_COLLAPSE_COLOR;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_FROM_CONTENT_COLOR;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_POSITION;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_SIZE;
    private static final EaseManager.EaseStyle EASE_COLLAPSE_TO_CONTENT_COLOR;
    private static final EaseManager.EaseStyle EASE_EXPAND_COLOR;
    private static final EaseManager.EaseStyle EASE_EXPAND_FROM_CONTENT_COLOR;
    private static final EaseManager.EaseStyle EASE_EXPAND_POSITION;
    private static final EaseManager.EaseStyle EASE_EXPAND_SIZE;
    private static final EaseManager.EaseStyle EASE_EXPAND_TO_CONTENT_COLOR;
    public static final float SLIDER_TOUCH_SCALE = 0.94f;

    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(0.82f, 0.4f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_EXPAND_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_EXPAND_POSITION = easeStyleSpring2;
        EaseManager.EaseStyle easeStyleSpring3 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring3, "spring(...)");
        EASE_EXPAND_COLOR = easeStyleSpring3;
        EaseManager.EaseStyle easeStyleSpring4 = FolmeEase.spring(0.95f, 0.15f);
        n.f(easeStyleSpring4, "spring(...)");
        EASE_EXPAND_FROM_CONTENT_COLOR = easeStyleSpring4;
        EaseManager.EaseStyle easeStyleSpring5 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring5, "spring(...)");
        EASE_EXPAND_TO_CONTENT_COLOR = easeStyleSpring5;
        EaseManager.EaseStyle easeStyleSpring6 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring6, "spring(...)");
        EASE_COLLAPSE_SIZE = easeStyleSpring6;
        EaseManager.EaseStyle easeStyleSpring7 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring7, "spring(...)");
        EASE_COLLAPSE_POSITION = easeStyleSpring7;
        EaseManager.EaseStyle easeStyleSpring8 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring8, "spring(...)");
        EASE_COLLAPSE_COLOR = easeStyleSpring8;
        EaseManager.EaseStyle easeStyleSpring9 = FolmeEase.spring(0.9f, 0.3f);
        n.f(easeStyleSpring9, "spring(...)");
        EASE_COLLAPSE_FROM_CONTENT_COLOR = easeStyleSpring9;
        EaseManager.EaseStyle easeStyleSpring10 = FolmeEase.spring(0.95f, 0.15f);
        n.f(easeStyleSpring10, "spring(...)");
        EASE_COLLAPSE_TO_CONTENT_COLOR = easeStyleSpring10;
    }

    public static final EaseManager.EaseStyle getEASE_COLLAPSE_COLOR() {
        return EASE_COLLAPSE_COLOR;
    }

    public static final EaseManager.EaseStyle getEASE_COLLAPSE_FROM_CONTENT_COLOR() {
        return EASE_COLLAPSE_FROM_CONTENT_COLOR;
    }

    public static final EaseManager.EaseStyle getEASE_COLLAPSE_POSITION() {
        return EASE_COLLAPSE_POSITION;
    }

    public static final EaseManager.EaseStyle getEASE_COLLAPSE_SIZE() {
        return EASE_COLLAPSE_SIZE;
    }

    public static final EaseManager.EaseStyle getEASE_COLLAPSE_TO_CONTENT_COLOR() {
        return EASE_COLLAPSE_TO_CONTENT_COLOR;
    }

    public static final EaseManager.EaseStyle getEASE_EXPAND_COLOR() {
        return EASE_EXPAND_COLOR;
    }

    public static final EaseManager.EaseStyle getEASE_EXPAND_FROM_CONTENT_COLOR() {
        return EASE_EXPAND_FROM_CONTENT_COLOR;
    }

    public static final EaseManager.EaseStyle getEASE_EXPAND_POSITION() {
        return EASE_EXPAND_POSITION;
    }

    public static final EaseManager.EaseStyle getEASE_EXPAND_SIZE() {
        return EASE_EXPAND_SIZE;
    }

    public static final EaseManager.EaseStyle getEASE_EXPAND_TO_CONTENT_COLOR() {
        return EASE_EXPAND_TO_CONTENT_COLOR;
    }
}
