package u;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import java.util.List;

/* JADX INFO: renamed from: u.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0744b {
    public static void a(AnimatorSet animatorSet, List list) {
        int size = list.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Animator animator = (Animator) list.get(i2);
            jMax = Math.max(jMax, animator.getStartDelay() + animator.getDuration());
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
        valueAnimatorOfInt.setDuration(jMax);
        list.add(0, valueAnimatorOfInt);
        animatorSet.playTogether(list);
    }
}
