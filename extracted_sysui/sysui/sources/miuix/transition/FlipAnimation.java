package miuix.transition;

import android.graphics.Point;
import android.view.View;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import miuix.core.util.MiuiBlurUtils;

/* JADX INFO: loaded from: classes5.dex */
public class FlipAnimation {
    private static final int HALF_ROTATION = 90;
    private FlipListener mFlipListener;
    private final ArrayList<Point> mColorModes = new ArrayList<>();
    private boolean mBlurEnabled = true;

    public interface FlipListener {
        void onFlip(boolean z2, float f2);
    }

    public static boolean isInFlipAnimation(View view) {
        return view.getTag(R.id.miuix_animation_tag_is_flip) != null;
    }

    public void flip(@NonNull final View view, final boolean z2) {
        AnimConfig animConfigAddListeners = new AnimConfig().setEase(EaseManager.getStyle(-2, 0.72f, 0.5f)).addListeners(new TransitionListener() { // from class: miuix.transition.FlipAnimation.1
            private int lastRotationY = -1;
            private boolean mHasTriggeredNegative;
            private boolean mHasTriggeredPositive;

            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                this.mHasTriggeredPositive = false;
                this.mHasTriggeredNegative = false;
                this.lastRotationY = -1;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                view.setTag(R.id.miuix_animation_tag_is_flip, null);
                FlipAnimation flipAnimation = FlipAnimation.this;
                flipAnimation.setMiSelfBlur(view, 0, flipAnimation.mColorModes);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                view.setTag(R.id.miuix_animation_tag_is_flip, null);
                FlipAnimation flipAnimation = FlipAnimation.this;
                flipAnimation.setMiSelfBlur(view, 0, flipAnimation.mColorModes);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                int rotationY = ((int) view.getRotationY()) % 360;
                int i2 = this.lastRotationY;
                int i3 = rotationY < 0 ? rotationY + 360 : rotationY;
                int i4 = i2 < 0 ? i2 + 360 : i2;
                if (i2 != -1) {
                    boolean z3 = z2;
                    if (z3 && !this.mHasTriggeredPositive && (i3 / 90) % 2 == 1 && (rotationY / 90) - (i2 / 90) == 1) {
                        this.mHasTriggeredPositive = true;
                        if (FlipAnimation.this.mFlipListener != null) {
                            FlipAnimation.this.mFlipListener.onFlip(true, view.getRotationY());
                        }
                    } else if (!z3 && !this.mHasTriggeredNegative && (i4 / 90) % 2 == 1 && (rotationY / 90) - (i2 / 90) == -1) {
                        this.mHasTriggeredNegative = true;
                        if (FlipAnimation.this.mFlipListener != null) {
                            FlipAnimation.this.mFlipListener.onFlip(false, view.getRotationY());
                        }
                    }
                }
                this.lastRotationY = rotationY;
                if (i3 <= 90 || i3 >= 270) {
                    view.setScaleX(1.0f);
                } else {
                    view.setScaleX(-1.0f);
                }
                int iAbs = Math.abs((int) (Math.sin((((double) i3) * 3.141592653589793d) / 180.0d) * 40.0d));
                FlipAnimation flipAnimation = FlipAnimation.this;
                flipAnimation.setMiSelfBlur(view, iAbs, flipAnimation.mColorModes);
            }
        });
        view.setTag(R.id.miuix_animation_tag_is_flip, Boolean.TRUE);
        int i2 = R.id.miuix_animation_tag_flip_rotation_y;
        Object tag = view.getTag(i2);
        if (z2) {
            int iIntValue = (((tag != null ? ((Integer) tag).intValue() : (int) view.getRotationY()) + 180) / 180) * 180;
            view.setTag(i2, Integer.valueOf(iIntValue));
            Folme.use(view).state().to(ViewProperty.ROTATION_Y, Integer.valueOf(iIntValue), animConfigAddListeners);
        } else {
            int iIntValue2 = (((tag != null ? ((Integer) tag).intValue() : (int) view.getRotationY()) - 180) / 180) * 180;
            view.setTag(i2, Integer.valueOf(iIntValue2));
            Folme.use(view).state().to(ViewProperty.ROTATION_Y, Integer.valueOf(iIntValue2), animConfigAddListeners);
        }
    }

    public void reset(View view) {
        if (view == null) {
            return;
        }
        Folme.use(view).state().setTo(ViewProperty.ROTATION_Y, 0);
        view.setTag(R.id.miuix_animation_tag_flip_rotation_y, null);
        setMiSelfBlur(view, 0, null);
        view.setScaleX(1.0f);
    }

    public void setBlurEnabled(boolean z2) {
        this.mBlurEnabled = z2;
    }

    public void setFlipListener(FlipListener flipListener) {
        this.mFlipListener = flipListener;
    }

    public void setMiSelfBlur(View view, int i2, ArrayList<Point> arrayList) {
        if (!this.mBlurEnabled || view == null) {
            return;
        }
        MiuiBlurUtils.setSelfBlur(view, i2, arrayList);
    }
}
