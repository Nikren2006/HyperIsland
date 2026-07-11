package androidx.constraintlayout.core.motion.utils;

/* JADX INFO: loaded from: classes.dex */
public interface StopEngine {
    String debug(String str, float f2);

    float getInterpolation(float f2);

    float getVelocity();

    float getVelocity(float f2);

    boolean isStopped();
}
