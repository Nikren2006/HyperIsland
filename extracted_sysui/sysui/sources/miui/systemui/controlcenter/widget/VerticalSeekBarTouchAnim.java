package miui.systemui.controlcenter.widget;

import H0.d;
import H0.e;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.util.CommonUtils;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalSeekBarTouchAnim {
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_TOUCH_DOWN_SIZE;
    private static final EaseManager.EaseStyle EASE_TOUCH_UP_SIZE;
    private static final String PROP_SCALE = "scaleXY";
    private static final String TAG = "VerticalSeekBarTouchAnim";
    private VerticalSeekBar _seekBar;
    private final GestureDetector gestureDetector;
    private final VerticalSeekBarTouchAnim$gestureListener$1 gestureListener;
    private boolean inScale;
    private final TransitionListener listener;
    private Function0 longClickAction;
    private final d scaleAnim$delegate;
    private final AnimConfig scaleAnimConfig;
    private float scaleXY;
    private TouchCallback touchCallback;
    private View touchView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EaseManager.EaseStyle getEASE_TOUCH_DOWN_SIZE() {
            return VerticalSeekBarTouchAnim.EASE_TOUCH_DOWN_SIZE;
        }

        public final EaseManager.EaseStyle getEASE_TOUCH_UP_SIZE() {
            return VerticalSeekBarTouchAnim.EASE_TOUCH_UP_SIZE;
        }

        private Companion() {
        }
    }

    public interface TouchCallback {
        default void onBeginScale() {
        }

        default void onEndScale() {
        }
    }

    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(1.0f, 1.0f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_TOUCH_DOWN_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_TOUCH_UP_SIZE = easeStyleSpring2;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [android.view.GestureDetector$OnGestureListener, miui.systemui.controlcenter.widget.VerticalSeekBarTouchAnim$gestureListener$1] */
    public VerticalSeekBarTouchAnim(Context context) {
        n.g(context, "context");
        this.scaleAnim$delegate = e.b(new VerticalSeekBarTouchAnim$scaleAnim$2(this));
        AnimConfig animConfig = new AnimConfig();
        this.scaleAnimConfig = animConfig;
        this.scaleXY = 1.0f;
        TransitionListener transitionListener = new TransitionListener() { // from class: miui.systemui.controlcenter.widget.VerticalSeekBarTouchAnim$listener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object toTag, Collection<? extends UpdateInfo> updateList) {
                n.g(toTag, "toTag");
                n.g(updateList, "updateList");
                super.onUpdate(toTag, updateList);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(updateList, "scaleXY");
                if (updateInfoFindByName != null) {
                    this.this$0.setScaleXY(updateInfoFindByName.getFloatValue());
                }
            }
        };
        this.listener = transitionListener;
        ?? r2 = new GestureDetector.OnGestureListener() { // from class: miui.systemui.controlcenter.widget.VerticalSeekBarTouchAnim$gestureListener$1
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e2) {
                n.g(e2, "e");
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent e12, MotionEvent e2, float f2, float f3) {
                n.g(e12, "e1");
                n.g(e2, "e2");
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e2) {
                n.g(e2, "e");
                Log.i("VerticalSeekBarTouchAnim", "onLongPress");
                Function0 longClickAction = this.this$0.getLongClickAction();
                if (longClickAction != null) {
                    longClickAction.invoke();
                }
                VerticalSeekBar verticalSeekBar = this.this$0._seekBar;
                if (verticalSeekBar == null || !verticalSeekBar.hasOnLongClickListeners()) {
                    return;
                }
                VerticalSeekBar verticalSeekBar2 = this.this$0._seekBar;
                if (verticalSeekBar2 != null) {
                    verticalSeekBar2.setScaleX(0.94f);
                }
                VerticalSeekBar verticalSeekBar3 = this.this$0._seekBar;
                if (verticalSeekBar3 != null) {
                    verticalSeekBar3.setScaleY(0.94f);
                }
                VerticalSeekBarTouchAnim.endScale$default(this.this$0, false, 1, null);
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e12, MotionEvent e2, float f2, float f3) {
                n.g(e12, "e1");
                n.g(e2, "e2");
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent e2) {
                n.g(e2, "e");
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent e2) {
                n.g(e2, "e");
                return false;
            }
        };
        this.gestureListener = r2;
        this.gestureDetector = new GestureDetector(context, (GestureDetector.OnGestureListener) r2);
        animConfig.addListeners(transitionListener);
    }

    private final void beginScale() {
        if (this.inScale) {
            return;
        }
        this.inScale = true;
        TouchCallback touchCallback = this.touchCallback;
        if (touchCallback != null) {
            touchCallback.onBeginScale();
        }
        getScaleAnim().to(PROP_SCALE, Float.valueOf(0.94f), this.scaleAnimConfig.setEase(EASE_TOUCH_DOWN_SIZE));
    }

    public static /* synthetic */ void endScale$default(VerticalSeekBarTouchAnim verticalSeekBarTouchAnim, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        verticalSeekBarTouchAnim.endScale(z2);
    }

    private final IStateStyle getScaleAnim() {
        return (IStateStyle) this.scaleAnim$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScaleXY(float f2) {
        float f3 = this.scaleXY;
        if (f3 == f2) {
            return;
        }
        this.scaleXY = f2;
        View view = this.touchView;
        ViewParent parent = view != null ? view.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            float scaleX = viewGroup.getScaleX() + (this.scaleXY - f3);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            commonUtils.setScaleYEx(viewGroup, scaleX);
            commonUtils.setScaleXEx(viewGroup, scaleX);
        }
    }

    public final void cleanAnim() {
        getScaleAnim().setTo(PROP_SCALE, Float.valueOf(1.0f), this.scaleAnimConfig.setDelay(0L));
        getScaleAnim().clean();
    }

    public final void endScale(boolean z2) {
        boolean z3 = this.inScale;
        Float fValueOf = Float.valueOf(1.0f);
        if (z3) {
            this.inScale = false;
            TouchCallback touchCallback = this.touchCallback;
            if (touchCallback != null) {
                touchCallback.onEndScale();
            }
            if (z2) {
                getScaleAnim().setTo(PROP_SCALE, fValueOf, this.scaleAnimConfig.setDelay(0L));
            } else {
                getScaleAnim().to(PROP_SCALE, fValueOf, this.scaleAnimConfig.setEase(EASE_TOUCH_UP_SIZE));
            }
        }
        VerticalSeekBar verticalSeekBar = this._seekBar;
        if (verticalSeekBar != null) {
            verticalSeekBar.setScaleX(1.0f);
            verticalSeekBar.setScaleY(1.0f);
        }
    }

    public final Function0 getLongClickAction() {
        return this.longClickAction;
    }

    public final TouchCallback getTouchCallback() {
        return this.touchCallback;
    }

    public final View getTouchView() {
        return this.touchView;
    }

    public final void onTouchEvent(VerticalSeekBar seekBar, MotionEvent motionEvent) {
        n.g(seekBar, "seekBar");
        if (motionEvent == null) {
            return;
        }
        this._seekBar = seekBar;
        this.gestureDetector.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            beginScale();
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            endScale$default(this, false, 1, null);
        }
    }

    public final void setLongClickAction(Function0 function0) {
        this.longClickAction = function0;
    }

    public final void setTouchCallback(TouchCallback touchCallback) {
        this.touchCallback = touchCallback;
    }

    public final void setTouchView(View view) {
        this.touchView = view;
    }
}
