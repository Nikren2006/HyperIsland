package u;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: u.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0745c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SimpleArrayMap f6839a = new SimpleArrayMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SimpleArrayMap f6840b = new SimpleArrayMap();

    public static void a(C0745c c0745c, Animator animator) {
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            c0745c.e(objectAnimator.getPropertyName(), objectAnimator.getValues());
            c0745c.f(objectAnimator.getPropertyName(), C0746d.a(objectAnimator));
        } else {
            throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
        }
    }

    public static C0745c b(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        if (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return c(context, resourceId);
    }

    public static C0745c c(Context context, int i2) {
        try {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, i2);
            if (animatorLoadAnimator instanceof AnimatorSet) {
                return d(((AnimatorSet) animatorLoadAnimator).getChildAnimations());
            }
            if (animatorLoadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(animatorLoadAnimator);
            return d(arrayList);
        } catch (Exception e2) {
            Log.w("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i2), e2);
            return null;
        }
    }

    public static C0745c d(List list) {
        C0745c c0745c = new C0745c();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(c0745c, (Animator) list.get(i2));
        }
        return c0745c;
    }

    public void e(String str, PropertyValuesHolder[] propertyValuesHolderArr) {
        this.f6840b.put(str, propertyValuesHolderArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0745c) {
            return this.f6839a.equals(((C0745c) obj).f6839a);
        }
        return false;
    }

    public void f(String str, C0746d c0746d) {
        this.f6839a.put(str, c0746d);
    }

    public int hashCode() {
        return this.f6839a.hashCode();
    }

    public String toString() {
        return '\n' + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f6839a + "}\n";
    }
}
