package miuix.appcompat.app.floatingactivity;

import android.view.View;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import miuix.appcompat.view.menu.HyperMenuContract;

/* JADX INFO: loaded from: classes2.dex */
public class FloatingSwitcherAnimHelper {
    private static final int DISTANCE_X = 200;
    public static final int EASE_STYLE_TRANS_X = 0;
    public static final int EASE_STYLE_TRANS_Y_ENTER = 1;
    public static final int EASE_STYLE_TRANS_Y_EXIT = 2;

    public static void executeCloseEnterAnimation(View view) {
        executeCloseEnterAnimation(view, null);
    }

    public static void executeCloseExitAnimation(View view) {
        executeCloseExitAnimation(view, null);
    }

    public static void executeOpenEnterAnimation(View view) {
        executeOpenEnterAnimation(view, null);
    }

    public static void executeOpenExitAnimation(View view) {
        executeOpenExitAnimation(view, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void executeSlideIn(View view, AnimConfig animConfig) {
        AnimState animState = new AnimState();
        ViewProperty viewProperty = ViewProperty.TRANSLATION_X;
        AnimState animStateAdd = animState.add(viewProperty, 0.0d);
        IStateStyle to = Folme.useAt(view).state().setTo(viewProperty, Integer.valueOf(view.getWidth()));
        if (animConfig == null) {
            animConfig = getAnimConfig();
        }
        to.to(animStateAdd, animConfig);
    }

    public static AnimConfig getAnimConfig() {
        return getAnimConfig(0, null);
    }

    public static void executeCloseEnterAnimation(View view, AnimConfig animConfig) {
        AnimState animState = new AnimState();
        ViewProperty viewProperty = ViewProperty.TRANSLATION_X;
        AnimState animStateAdd = animState.add(viewProperty, 0);
        IStateStyle to = Folme.useAt(view).state().setTo(viewProperty, Integer.valueOf(HyperMenuContract.MENU_ITEM_OTHER_ITEM_ID));
        if (animConfig == null) {
            animConfig = getAnimConfig(0, null);
        }
        to.to(animStateAdd, animConfig);
    }

    public static void executeCloseExitAnimation(View view, AnimConfig animConfig) {
        int width = view.getWidth();
        AnimState animState = new AnimState();
        ViewProperty viewProperty = ViewProperty.TRANSLATION_X;
        AnimState animStateAdd = animState.add(viewProperty, width);
        IStateStyle to = Folme.useAt(view).state().setTo(viewProperty, 0);
        if (animConfig == null) {
            animConfig = getAnimConfig();
        }
        to.to(animStateAdd, animConfig);
    }

    public static void executeOpenEnterAnimation(final View view, final AnimConfig animConfig) {
        if (view.isAttachedToWindow()) {
            executeSlideIn(view, animConfig);
        } else {
            view.post(new Runnable() { // from class: miuix.appcompat.app.floatingactivity.FloatingSwitcherAnimHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    FloatingSwitcherAnimHelper.executeSlideIn(view, animConfig);
                }
            });
        }
    }

    public static void executeOpenExitAnimation(View view, AnimConfig animConfig) {
        AnimState animStateAdd = new AnimState().add(ViewProperty.TRANSLATION_X, -200.0d);
        IStateStyle iStateStyleState = Folme.useAt(view).state();
        if (animConfig == null) {
            animConfig = getAnimConfig(0, null);
        }
        iStateStyleState.to(animStateAdd, animConfig);
    }

    public static AnimConfig getAnimConfig(int i2, final Runnable runnable) {
        final AnimConfig animConfig = getAnimConfig(i2);
        if (runnable != null) {
            animConfig.addListeners(new TransitionListener() { // from class: miuix.appcompat.app.floatingactivity.FloatingSwitcherAnimHelper.1
                @Override // miuix.animation.listener.TransitionListener
                public void onCancel(Object obj) {
                    super.onCancel(obj);
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    animConfig.removeListeners(this);
                }

                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    super.onComplete(obj);
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    animConfig.removeListeners(this);
                }
            });
        }
        return animConfig;
    }

    private static AnimConfig getAnimConfig(int i2) {
        AnimConfig animConfig = new AnimConfig();
        if (i2 == 1) {
            animConfig.setEase(EaseManager.getStyle(-2, 0.85f, 0.3f));
        } else if (i2 != 2) {
            animConfig.setEase(EaseManager.getStyle(-2, 1.0f, 0.46f));
        } else {
            animConfig.setEase(EaseManager.getStyle(-2, 0.95f, 0.3f));
        }
        return animConfig;
    }
}
