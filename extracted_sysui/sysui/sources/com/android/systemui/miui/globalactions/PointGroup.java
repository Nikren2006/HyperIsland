package com.android.systemui.miui.globalactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miuix.view.animation.SineEaseInOutInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public class PointGroup extends ViewGroup {
    private static final int ANIMATION_DECREASE_TIME = 640;
    private static final int ANIMATION_DELAY_TIME = 250;
    private static final int ANIMATION_INCREASE_TIME = 360;
    private static final int ANIMATION_STOP_TIME = 500;
    public static final int DOWN = 1;
    private static final String TAG = "PointGroup";
    public static final int UP = 0;
    private final int POINT_NUMBER;
    private final Interpolator SINE_EASE_IN_OUT;
    private Context mContext;
    private Handler mH;
    private int mInterval;
    private int mOrder;
    private int mPointSize;
    private List<PointView> mPoints;
    private boolean mStop;

    /* JADX INFO: renamed from: com.android.systemui.miui.globalactions.PointGroup$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ int val$id;

        public AnonymousClass2(int i2) {
            this.val$id = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onAnimationEnd$0() {
            PointGroup.this.sendMessages();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if ((PointGroup.this.mOrder == 0 && this.val$id == PointGroup.this.mPoints.size() - 1) || (PointGroup.this.mOrder == 1 && this.val$id == 0)) {
                PointGroup.this.mH.postDelayed(new Runnable() { // from class: com.android.systemui.miui.globalactions.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1461a.lambda$onAnimationEnd$0();
                    }
                }, 500L);
            }
        }
    }

    public static class H extends Handler {
        private final WeakReference<PointGroup> mOuter;

        public H(PointGroup pointGroup) {
            this.mOuter = new WeakReference<>(pointGroup);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.mOuter.get() == null || this.mOuter.get().mStop || this.mOuter.get() == null) {
                return;
            }
            this.mOuter.get().startLoopAnimation(message.what);
        }
    }

    public class PointView extends ImageView {
        public PointView(PointGroup pointGroup, Context context) {
            super(context);
        }

        @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
        public void sendAccessibilityEvent(int i2) {
        }

        @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
        public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        }
    }

    public PointGroup(Context context, int i2) {
        this(context, (AttributeSet) null);
        this.mOrder = i2;
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mPointSize = (int) context.getResources().getDimension(R.dimen.point_size);
        this.mInterval = (int) this.mContext.getResources().getDimension(R.dimen.point_interval);
        for (int i2 = 0; i2 < 3; i2++) {
            PointView pointView = new PointView(this, context);
            this.mPoints.add(pointView);
            pointView.setVisibility(0);
            pointView.setAlpha(0.0f);
            addView(pointView, i2);
            pointView.setBackgroundResource(R.drawable.ic_point);
            pointView.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessages() {
        int i2 = 0;
        if (this.mOrder == 0) {
            int size = this.mPoints.size() - 1;
            while (size >= 0) {
                this.mH.sendEmptyMessageDelayed(size, ((long) i2) * 250);
                size--;
                i2++;
            }
            return;
        }
        int i3 = 0;
        while (i2 < this.mPoints.size()) {
            this.mH.sendEmptyMessageDelayed(i2, ((long) i3) * 250);
            i2++;
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoopAnimation(int i2) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mPoints.get(i2), "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat.setInterpolator(this.SINE_EASE_IN_OUT);
        objectAnimatorOfFloat.setDuration(360L);
        final ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mPoints.get(i2), "alpha", 1.0f, 0.0f);
        objectAnimatorOfFloat2.setInterpolator(this.SINE_EASE_IN_OUT);
        objectAnimatorOfFloat2.setDuration(640L);
        objectAnimatorOfFloat.start();
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter(this) { // from class: com.android.systemui.miui.globalactions.PointGroup.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                objectAnimatorOfFloat2.start();
            }
        });
        objectAnimatorOfFloat2.addListener(new AnonymousClass2(i2));
    }

    public void clear() {
        this.mStop = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        for (int i7 = 0; i7 < this.mPoints.size(); i7++) {
            PointView pointView = this.mPoints.get(i7);
            int i8 = this.mPointSize;
            pointView.layout(0, i6, i8, i6 + i8);
            i6 += this.mPointSize + this.mInterval;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int i4 = this.mPointSize;
        int i5 = (i4 * 3) + (this.mInterval * 2);
        Iterator<PointView> it = this.mPoints.iterator();
        while (it.hasNext()) {
            it.next().measure(View.MeasureSpec.makeMeasureSpec(this.mPointSize, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.mPointSize, BasicMeasure.EXACTLY));
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(i4, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i5, BasicMeasure.EXACTLY));
    }

    public void startLoop() {
        Log.d(TAG, "startLoop: " + this.mOrder + " " + this.mStop);
        if (this.mStop) {
            this.mStop = false;
            sendMessages();
        }
    }

    public PointGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PointGroup(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mOrder = 1;
        this.POINT_NUMBER = 3;
        this.mPoints = new ArrayList(3);
        this.mStop = true;
        this.SINE_EASE_IN_OUT = new SineEaseInOutInterpolator();
        this.mH = new H(this);
        initView(context);
    }
}
