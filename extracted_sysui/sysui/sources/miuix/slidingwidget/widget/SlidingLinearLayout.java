package miuix.slidingwidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.RequiresApi;
import java.util.HashMap;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;

/* JADX INFO: loaded from: classes5.dex */
public class SlidingLinearLayout extends LinearLayout {
    private final HashMap<View, Pair<Float, Float>> preAddViewPairs;
    private final int[] preLayout;
    private final HashMap<View, Pair<Float, Float>> preRemoveViewPairs;

    public SlidingLinearLayout(Context context) {
        this(context, null);
    }

    private void preAddView() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            this.preAddViewPairs.put(childAt, new Pair<>(Float.valueOf(childAt.getX()), Float.valueOf(childAt.getY())));
        }
    }

    private void preRemoveView(View view) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (view != childAt && childAt.getVisibility() != 8) {
                this.preRemoveViewPairs.put(childAt, new Pair<>(Float.valueOf(childAt.getX()), Float.valueOf(childAt.getY())));
            }
        }
    }

    private void show(View view) {
        Folme.useAt(view).visible().setFlags(1L).setFlags(1L).setShowDelay(100L).setHide().show(new AnimConfig[0]);
    }

    public void addViewSliding(View view) {
        preAddView();
        addView(view);
        show(view);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    @RequiresApi(api = 24)
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        float fFloatValue;
        float fFloatValue2;
        float fFloatValue3;
        float fFloatValue4;
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2) {
            int childCount = getChildCount();
            char c2 = 1;
            char c3 = 0;
            boolean z3 = getOrientation() != 1 ? Math.abs(this.preLayout[0] - i2) > Math.abs(this.preLayout[2] - i4) : Math.abs(this.preLayout[1] - i3) > Math.abs(this.preLayout[3] - i5);
            int i6 = 0;
            while (i6 < childCount) {
                View childAt = getChildAt(i6);
                HashMap<View, Pair<Float, Float>> map = this.preAddViewPairs;
                if (map != null && map.size() > 0) {
                    Pair<Float, Float> pair = this.preAddViewPairs.get(childAt);
                    if (pair != null && childAt.getVisibility() != 8) {
                        if ((childAt.getX() != ((Float) pair.first).floatValue() || childAt.getY() != ((Float) pair.second).floatValue()) && !z3) {
                            fFloatValue3 = ((Float) pair.first).floatValue() - childAt.getX();
                            fFloatValue4 = ((Float) pair.second).floatValue() - childAt.getY();
                        } else if (childAt.getX() == ((Float) pair.first).floatValue() && childAt.getY() == ((Float) pair.second).floatValue() && z3) {
                            int[] iArr = this.preLayout;
                            float f2 = iArr[c3] - i2;
                            fFloatValue4 = iArr[c2] - i3;
                            fFloatValue3 = f2;
                        } else {
                            fFloatValue3 = 0.0f;
                            fFloatValue4 = 0.0f;
                        }
                        AnimState animState = new AnimState("start");
                        ViewProperty viewProperty = ViewProperty.TRANSLATION_X;
                        AnimState animStateAdd = animState.add(viewProperty, fFloatValue3);
                        ViewProperty viewProperty2 = ViewProperty.TRANSLATION_Y;
                        AnimState animStateAdd2 = animStateAdd.add(viewProperty2, fFloatValue4);
                        Folme.useAt(childAt).state().setTo(animStateAdd2).fromTo(animStateAdd2, new AnimState("end").add(viewProperty, 0.0d).add(viewProperty2, 0.0d), new AnimConfig[0]);
                    }
                    this.preAddViewPairs.remove(childAt);
                }
                HashMap<View, Pair<Float, Float>> map2 = this.preRemoveViewPairs;
                if (map2 != null && map2.size() > 0) {
                    Pair<Float, Float> pair2 = this.preRemoveViewPairs.get(childAt);
                    if (pair2 != null && childAt.getVisibility() != 8) {
                        if ((childAt.getX() != ((Float) pair2.first).floatValue() || childAt.getY() != ((Float) pair2.second).floatValue()) && !z3) {
                            fFloatValue = ((Float) pair2.first).floatValue() - childAt.getX();
                            fFloatValue2 = ((Float) pair2.second).floatValue() - childAt.getY();
                        } else if (childAt.getX() == ((Float) pair2.first).floatValue() && childAt.getY() == ((Float) pair2.second).floatValue() && z3) {
                            int[] iArr2 = this.preLayout;
                            fFloatValue = iArr2[0] - i2;
                            fFloatValue2 = iArr2[1] - i3;
                        } else {
                            fFloatValue2 = 0.0f;
                            fFloatValue = 0.0f;
                        }
                        AnimState animState2 = new AnimState("start");
                        ViewProperty viewProperty3 = ViewProperty.TRANSLATION_X;
                        AnimState animStateAdd3 = animState2.add(viewProperty3, fFloatValue);
                        ViewProperty viewProperty4 = ViewProperty.TRANSLATION_Y;
                        AnimState animStateAdd4 = animStateAdd3.add(viewProperty4, fFloatValue2);
                        Folme.useAt(childAt).state().setTo(animStateAdd4).fromTo(animStateAdd4, new AnimState("end").add(viewProperty3, 0.0d).add(viewProperty4, 0.0d), new AnimConfig[0]);
                    }
                    this.preRemoveViewPairs.remove(childAt);
                }
                i6++;
                c2 = 1;
                c3 = 0;
            }
            this.preAddViewPairs.clear();
            this.preRemoveViewPairs.clear();
            int[] iArr3 = this.preLayout;
            iArr3[0] = i2;
            iArr3[1] = i3;
            iArr3[2] = i4;
            iArr3[3] = i5;
        }
    }

    public void removeViewSliding(final View view) {
        preRemoveView(view);
        Folme.useAt(view).visible().setFlags(1L).setShow().hide(new AnimConfig().addListeners(new TransitionListener() { // from class: miuix.slidingwidget.widget.SlidingLinearLayout.1
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                SlidingLinearLayout.this.removeView(view);
            }
        }));
    }

    public void removeViewSlidingAt(final int i2) {
        View childAt = getChildAt(i2);
        preRemoveView(childAt);
        Folme.useAt(childAt).visible().setFlags(1L).setShow().hide(new AnimConfig().addListeners(new TransitionListener() { // from class: miuix.slidingwidget.widget.SlidingLinearLayout.2
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (i2 < SlidingLinearLayout.this.getChildCount()) {
                    try {
                        SlidingLinearLayout.this.removeViewAt(i2);
                    } catch (Exception e2) {
                        Log.e("SlidingLinear", "error in removeViewSlidingAt, index " + i2 + " " + e2);
                    }
                }
            }
        }));
    }

    public void removeViewsSliding(final int i2, final int i3) {
        preRemoveView(i2, i3);
        final int i4 = i2 + i3;
        for (int i5 = i2; i5 < i4; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                final int i6 = i5;
                Folme.useAt(childAt).visible().setFlags(1L).setShow().hide(new AnimConfig().addListeners(new TransitionListener() { // from class: miuix.slidingwidget.widget.SlidingLinearLayout.3
                    @Override // miuix.animation.listener.TransitionListener
                    public void onComplete(Object obj) {
                        if (i6 != i4 - 1 || i2 + i3 > SlidingLinearLayout.this.getChildCount()) {
                            return;
                        }
                        try {
                            SlidingLinearLayout.this.removeViews(i2, i3);
                        } catch (Exception e2) {
                            Log.e("SlidingLinear", "error in removeViewsSliding,start " + i2 + " count " + i3 + " " + e2);
                        }
                    }
                }));
            }
        }
    }

    public SlidingLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingLinearLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.preAddViewPairs = new HashMap<>();
        this.preRemoveViewPairs = new HashMap<>();
        this.preLayout = new int[4];
        setLayoutTransition(null);
    }

    public void addViewSliding(View view, int i2) {
        preAddView();
        addView(view, i2);
        show(view);
    }

    private void preRemoveView(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if ((i4 < i2 || i4 >= i2 + i3) && childAt.getVisibility() != 8) {
                this.preRemoveViewPairs.put(childAt, new Pair<>(Float.valueOf(childAt.getX()), Float.valueOf(childAt.getY())));
            }
        }
    }

    public void addViewSliding(View view, int i2, int i3) {
        preAddView();
        addView(view, i2, i3);
        show(view);
    }

    public void addViewSliding(View view, LinearLayout.LayoutParams layoutParams) {
        preAddView();
        addView(view, layoutParams);
        show(view);
    }

    public void addViewSliding(View view, int i2, LinearLayout.LayoutParams layoutParams) {
        preAddView();
        addView(view, i2, layoutParams);
        show(view);
    }
}
