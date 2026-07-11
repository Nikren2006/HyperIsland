package miuix.colorful.texteffect;

/* JADX INFO: loaded from: classes3.dex */
public interface TextSwitcher extends Runnable {
    public static final int EFFECT_LEVEL_COMMON = 2;
    public static final int EFFECT_LEVEL_HIGHEST = 3;
    public static final int EFFECT_LEVEL_LOW = 1;
    public static final int EFFECT_LEVEL_NONE = 0;

    void cancelAnim();

    void enableEffect(boolean z2);

    int getEffectLevel();

    boolean isEnableEffect();

    boolean isEnableEffectWithInit();

    void requestAnimation();

    void setEffectLevel(int i2);

    void setEnableEffectWithInit(boolean z2);
}
