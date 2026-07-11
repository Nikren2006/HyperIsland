package miuix.view.animation;

/* JADX INFO: loaded from: classes5.dex */
public class AnimationUtils extends android.view.animation.AnimationUtils {
    private static ThreadLocal<AnimationNanoState> sAnimationNanoState = new ThreadLocal<AnimationNanoState>() { // from class: miuix.view.animation.AnimationUtils.1
        @Override // java.lang.ThreadLocal
        public AnimationNanoState initialValue() {
            return new AnimationNanoState();
        }
    };

    public static class AnimationNanoState {
        long lastReportedTimeNanos;

        private AnimationNanoState() {
        }
    }

    public static long currentAnimationTimeNanos() {
        AnimationNanoState animationNanoState = sAnimationNanoState.get();
        long jNanoTime = System.nanoTime();
        animationNanoState.lastReportedTimeNanos = jNanoTime;
        return jNanoTime;
    }
}
