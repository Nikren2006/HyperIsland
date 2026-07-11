package miui.systemui.util;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Choreographer;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class AnimatedVectorDrawableUtils {
    public static List<Drawable> sRunningDrawables = new ArrayList();
    private static Choreographer.FrameCallback sFrameCallback = new AnonymousClass1();

    /* JADX INFO: renamed from: miui.systemui.util.AnimatedVectorDrawableUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements Choreographer.FrameCallback {
        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j2) {
            if (AnimatedVectorDrawableUtils.sRunningDrawables.size() != 0) {
                Choreographer.getInstance().postFrameCallback(AnimatedVectorDrawableUtils.sFrameCallback);
                AnimatedVectorDrawableUtils.sRunningDrawables.forEach(new Consumer() { // from class: miui.systemui.util.h
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((Drawable) obj).invalidateSelf();
                    }
                });
            }
        }
    }

    public static void setDrawable(ImageView imageView, Drawable drawable) {
        Drawable drawable2 = imageView.getDrawable();
        if (drawable2 instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable2;
            if (animatedVectorDrawable.isRunning()) {
                animatedVectorDrawable.stop();
                animatedVectorDrawable.reset();
            }
        }
        if (drawable instanceof AnimatedVectorDrawable) {
            final AnimatedVectorDrawable animatedVectorDrawable2 = (AnimatedVectorDrawable) drawable;
            animatedVectorDrawable2.mutate();
            animatedVectorDrawable2.registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: miui.systemui.util.AnimatedVectorDrawableUtils.2
                @Override // android.graphics.drawable.Animatable2.AnimationCallback
                public void onAnimationEnd(Drawable drawable3) {
                    AnimatedVectorDrawableUtils.sRunningDrawables.remove(animatedVectorDrawable2);
                }

                @Override // android.graphics.drawable.Animatable2.AnimationCallback
                public void onAnimationStart(Drawable drawable3) {
                    AnimatedVectorDrawableUtils.sRunningDrawables.add(animatedVectorDrawable2);
                    Choreographer.getInstance().postFrameCallback(AnimatedVectorDrawableUtils.sFrameCallback);
                }
            });
        }
        imageView.setImageDrawable(drawable);
    }

    public static void setDrawableToEnd(ImageView imageView, Drawable drawable) {
        setDrawable(imageView, drawable);
        Drawable drawable2 = imageView.getDrawable();
        if (drawable2 instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable2;
            animatedVectorDrawable.start();
            animatedVectorDrawable.reset();
            animatedVectorDrawable.stop();
        }
    }
}
