package miuix.popupwidget.widget;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.FolmeObject;
import miuix.animation.IFolme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ValueProperty;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import miuix.popupwidget.R;
import miuix.smooth.SmoothContainerDrawable2;
import miuix.smooth.SmoothFrameLayout2;

/* JADX INFO: loaded from: classes5.dex */
public class PopupAnimHelper implements FolmeObject {
    private static final float DAMPING = 0.82f;
    private static final float RESPONSE = 0.33f;
    private final AnimConfig mAnimConfig;
    private float mBlur;
    private final View mContentView;
    private float mDimValue;
    private final AnimConfig mEnterAlphaConfig;
    private Folme.ObjectFolmeImpl mFolmeAnimator;
    private float mFraction;
    private boolean mInAnimation = false;
    private final View mRootView;
    private ScaleListener mScaleListener;
    private final View mSpringBackLayout;

    @Nullable
    WindowManager.LayoutParams mWindowLayoutParams;
    private static final ValueProperty<PopupAnimHelper> POPUP_FRACTION = new ValueProperty<PopupAnimHelper>("fraction") { // from class: miuix.popupwidget.widget.PopupAnimHelper.1
        @Override // miuix.animation.property.ValueProperty, miuix.animation.property.FloatProperty
        public float getValue(PopupAnimHelper popupAnimHelper) {
            return popupAnimHelper.mFraction;
        }

        @Override // miuix.animation.property.ValueProperty, miuix.animation.property.FloatProperty
        public void setValue(PopupAnimHelper popupAnimHelper, float f2) {
            popupAnimHelper.mFraction = f2;
        }
    };
    private static final ValueProperty<PopupAnimHelper> POPUP_BLUR = new ValueProperty<PopupAnimHelper>("popupBlur", 0.1f) { // from class: miuix.popupwidget.widget.PopupAnimHelper.2
        @Override // miuix.animation.property.ValueProperty, miuix.animation.property.FloatProperty
        public float getValue(PopupAnimHelper popupAnimHelper) {
            return popupAnimHelper.mBlur;
        }

        @Override // miuix.animation.property.ValueProperty, miuix.animation.property.FloatProperty
        public void setValue(PopupAnimHelper popupAnimHelper, float f2) {
            popupAnimHelper.mBlur = f2;
        }
    };

    public class ScaleListener extends TransitionListener {
        private static final int END_RADIUS = 16;
        private static final float SIZE_W = 0.15f;
        private static final int START_RADIUS = 4;
        private ArrayList<Point> mColorModes = new ArrayList<>();
        private float mCurrentDim;
        private int mEndBottom;
        private int mEndHeight;
        private float mEndKGB;
        private int mEndLeft;
        private float mEndRadius;
        private int mEndRight;
        private int mEndTop;
        private int mEndWidth;
        private View mRootView;
        private int mStartBottom;
        private int mStartHeight;
        private final float mStartKGB;
        private int mStartLeft;
        private float mStartRadius;
        private int mStartRight;
        private int mStartTop;
        private int mStartWidth;
        private float mTargetDim;
        private float mVGrav;

        public ScaleListener(Rect rect, int i2, int i3) {
            this.mEndLeft = rect.left;
            this.mEndTop = rect.top;
            this.mEndRight = rect.right;
            this.mEndBottom = rect.bottom;
            Rect startBounds = getStartBounds(rect, i2, i3);
            this.mStartLeft = startBounds.left;
            this.mStartTop = startBounds.top;
            this.mStartRight = startBounds.right;
            this.mStartBottom = startBounds.bottom;
            this.mEndWidth = PopupAnimHelper.this.mContentView.getWidth();
            int height = PopupAnimHelper.this.mContentView.getHeight();
            this.mEndHeight = height;
            this.mStartKGB = 0.2f;
            int i4 = this.mEndWidth;
            this.mEndKGB = i4 == 0 ? 0.0f : height / i4;
            int i5 = (int) (i4 * SIZE_W);
            this.mStartWidth = i5;
            this.mStartHeight = (int) (i5 * 0.2f);
            View rootView = PopupAnimHelper.this.mContentView.getRootView();
            this.mRootView = rootView;
            if (rootView.getLayoutParams() instanceof WindowManager.LayoutParams) {
                PopupAnimHelper.this.mWindowLayoutParams = (WindowManager.LayoutParams) this.mRootView.getLayoutParams();
            } else {
                PopupAnimHelper.this.mWindowLayoutParams = null;
            }
            float f2 = this.mRootView.getContext().getResources().getDisplayMetrics().density;
            this.mStartRadius = 4.0f * f2;
            this.mEndRadius = f2 * 16.0f;
        }

        private void changeWindowDimAmount(View view, float f2) {
            WindowManager.LayoutParams layoutParams;
            if (view == null || !view.isAttachedToWindow() || (layoutParams = PopupAnimHelper.this.mWindowLayoutParams) == null) {
                return;
            }
            layoutParams.flags |= 2;
            layoutParams.dimAmount = f2;
            ((WindowManager) view.getContext().getSystemService("window")).updateViewLayout(view, PopupAnimHelper.this.mWindowLayoutParams);
        }

        private Rect getStartBounds(Rect rect, int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            int iWidth = rect.width();
            int iHeight = rect.height();
            int i8 = (int) (iWidth * SIZE_W);
            int i9 = i8 / 5;
            int absoluteGravity = Gravity.getAbsoluteGravity(i2, i3) & 7;
            this.mEndWidth = iWidth;
            this.mEndHeight = iHeight;
            this.mEndKGB = iWidth == 0 ? 0.0f : iHeight / iWidth;
            if (absoluteGravity == 3) {
                i4 = rect.left;
                i5 = rect.right - (iWidth - i8);
            } else {
                i4 = rect.left + (iWidth - i8);
                i5 = rect.right;
            }
            int absoluteGravity2 = Gravity.getAbsoluteGravity(i2, i3) & 112;
            this.mVGrav = absoluteGravity2;
            if (absoluteGravity2 == 48) {
                i6 = rect.top;
                i7 = rect.bottom - (iHeight - i9);
            } else {
                i6 = rect.top + (iHeight - i9);
                i7 = rect.bottom;
            }
            return new Rect(i4, i6, i5, i7);
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            if ("end".equals(obj)) {
                ((View) PopupAnimHelper.this.mSpringBackLayout.getParent()).setLeftTopRightBottom(this.mEndLeft, this.mEndTop, this.mEndRight, this.mEndBottom);
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
            int i2;
            int i3;
            UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "fraction");
            if (updateInfoFindByName != null) {
                float floatValue = updateInfoFindByName.getFloatValue();
                if (floatValue < 0.0f) {
                    floatValue = 0.0f;
                }
                int i4 = (int) (this.mStartLeft + ((this.mEndLeft - r0) * floatValue));
                int i5 = (int) (this.mStartRight + ((this.mEndRight - r1) * floatValue));
                float f2 = this.mStartKGB;
                float f3 = i5 - i4;
                int i6 = (int) (((f2 + ((this.mEndKGB - f2) * floatValue)) * f3) + 0.5f);
                if (this.mVGrav == 48.0f) {
                    i2 = this.mStartTop;
                    i3 = i6 + i2;
                } else {
                    int i7 = this.mStartBottom;
                    i2 = i7 - i6;
                    i3 = i7;
                }
                View view = (View) PopupAnimHelper.this.mSpringBackLayout.getParent();
                if (view == null) {
                    return;
                }
                view.setLeftTopRightBottom(i4, i2, i5, i3);
                float f4 = f3 / this.mEndWidth;
                PopupAnimHelper.this.mSpringBackLayout.setPivotX(0.0f);
                PopupAnimHelper.this.mSpringBackLayout.setPivotY(0.0f);
                PopupAnimHelper.this.mSpringBackLayout.setScaleX(f4);
                PopupAnimHelper.this.mSpringBackLayout.setScaleY(f4);
                if (PopupAnimHelper.this.mContentView instanceof SmoothFrameLayout2) {
                    float f5 = this.mEndRadius;
                    if (f5 != 0.0f) {
                        float f6 = this.mStartRadius;
                        float f7 = (int) (f6 + ((f5 - f6) * floatValue));
                        ((SmoothFrameLayout2) PopupAnimHelper.this.mContentView).setCornerRadius(f7);
                        Drawable background = PopupAnimHelper.this.mContentView.getBackground();
                        if (background instanceof SmoothContainerDrawable2) {
                            ((SmoothContainerDrawable2) background).setCornerRadius(f7);
                        }
                    }
                }
                float f8 = floatValue * this.mTargetDim;
                if (Math.abs(f8 - this.mCurrentDim) >= 0.02f) {
                    this.mCurrentDim = f8;
                    changeWindowDimAmount(this.mRootView, f8);
                }
            }
        }

        public void setDimValue(float f2) {
            this.mTargetDim = f2;
        }

        public void updateScaleBounds(Rect rect, int i2, int i3) {
            Rect startBounds = getStartBounds(rect, i2, i3);
            this.mEndLeft = rect.left;
            this.mEndTop = rect.top;
            this.mEndRight = rect.right;
            this.mEndBottom = rect.bottom;
            this.mStartLeft = startBounds.left;
            this.mStartTop = startBounds.top;
            this.mStartRight = startBounds.right;
            this.mStartBottom = startBounds.bottom;
        }
    }

    @RequiresApi(api = 29)
    public PopupAnimHelper(@NonNull final View view) {
        View rootView = view.getRootView();
        this.mRootView = rootView;
        this.mSpringBackLayout = rootView.findViewById(R.id.spring_back);
        this.mEnterAlphaConfig = new AnimConfig().setEase(EaseManager.getStyle(1, 200.0f));
        this.mAnimConfig = new AnimConfig().setEase(EaseManager.getStyle(-2, DAMPING, RESPONSE)).addListeners(new TransitionListener() { // from class: miuix.popupwidget.widget.PopupAnimHelper.3
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (PopupAnimHelper.this.mSpringBackLayout instanceof ViewGroup) {
                    ((ViewGroup) PopupAnimHelper.this.mSpringBackLayout).suppressLayout(true);
                }
                view.setLayerType(2, null);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                view.setLayerType(0, null);
                PopupAnimHelper.this.mInAnimation = false;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (PopupAnimHelper.this.mSpringBackLayout instanceof ViewGroup) {
                    ((ViewGroup) PopupAnimHelper.this.mSpringBackLayout).suppressLayout(false);
                }
                view.setLayerType(0, null);
                PopupAnimHelper.this.mInAnimation = false;
            }
        });
        this.mContentView = view;
    }

    public void dismissWithAnim(final Runnable runnable) {
        View view = this.mContentView;
        if (view == null || !view.isAttachedToWindow() || this.mContentView.getParent() == null) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        this.mInAnimation = true;
        if (this.mContentView instanceof ViewGroup) {
            Folme.use(this.mContentView).to(ViewProperty.ALPHA, Float.valueOf(0.0f), new AnimConfig().setEase(EaseManager.getStyle(1, 150.0f)).addListeners(new TransitionListener() { // from class: miuix.popupwidget.widget.PopupAnimHelper.5
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    super.onComplete(obj);
                    PopupAnimHelper.this.folme().end();
                }
            }));
            AnimState animStateAdd = new AnimState().add(POPUP_FRACTION, 0.0d);
            this.mAnimConfig.addListeners(new TransitionListener() { // from class: miuix.popupwidget.widget.PopupAnimHelper.6
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    PopupAnimHelper.this.mAnimConfig.removeListeners(this);
                }
            });
            Folme.useValue(this).to(animStateAdd, this.mAnimConfig);
        }
    }

    @Override // miuix.animation.FolmeObject
    public Folme.ObjectFolmeImpl folme() {
        return this.mFolmeAnimator;
    }

    public void setDimValue(float f2) {
        this.mDimValue = f2;
    }

    public void setEdgeExtension(boolean z2) {
        if (z2) {
            POPUP_FRACTION.setMinVisibleChange(-1.0f);
        } else {
            POPUP_FRACTION.setMinVisibleChange(0.04f);
        }
    }

    @Override // miuix.animation.FolmeObject
    public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
        this.mFolmeAnimator = objectFolmeImpl;
    }

    public void showWithAnim(final int i2) {
        View view = this.mContentView;
        if (view == null || view.getParent() == null) {
            return;
        }
        this.mInAnimation = true;
        final int layoutDirection = this.mContentView.getLayoutDirection();
        this.mContentView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: miuix.popupwidget.widget.PopupAnimHelper.4
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                PopupAnimHelper.this.mContentView.getViewTreeObserver().removeOnPreDrawListener(this);
                int left = PopupAnimHelper.this.mContentView.getLeft();
                int top = PopupAnimHelper.this.mContentView.getTop();
                int right = PopupAnimHelper.this.mContentView.getRight();
                int bottom = PopupAnimHelper.this.mContentView.getBottom();
                if (PopupAnimHelper.this.mScaleListener != null) {
                    PopupAnimHelper.this.mAnimConfig.removeListeners(PopupAnimHelper.this.mScaleListener);
                }
                PopupAnimHelper popupAnimHelper = PopupAnimHelper.this;
                popupAnimHelper.mScaleListener = popupAnimHelper.new ScaleListener(new Rect(left, top, right, bottom), i2, layoutDirection);
                PopupAnimHelper.this.mScaleListener.setDimValue(PopupAnimHelper.this.mDimValue);
                PopupAnimHelper.this.mAnimConfig.addListeners(PopupAnimHelper.this.mScaleListener);
                IFolme iFolmeUse = Folme.use(PopupAnimHelper.this.mContentView);
                ViewProperty viewProperty = ViewProperty.ALPHA;
                iFolmeUse.resetTo(viewProperty, Float.valueOf(0.0f)).to(viewProperty, Float.valueOf(1.0f), PopupAnimHelper.this.mEnterAlphaConfig);
                AnimState animStateAdd = new AnimState().add(PopupAnimHelper.POPUP_FRACTION, 0.0d);
                AnimState animStateAdd2 = new AnimState("end").add(PopupAnimHelper.POPUP_FRACTION, 1.0d);
                Folme.use((FolmeObject) PopupAnimHelper.this);
                PopupAnimHelper.this.folme().resetTo(animStateAdd).to(animStateAdd2, PopupAnimHelper.this.mAnimConfig);
                return false;
            }
        });
    }

    public void update(int i2) {
        if (this.mInAnimation) {
            return;
        }
        Rect rect = new Rect(this.mContentView.getLeft(), this.mContentView.getTop(), this.mContentView.getRight(), this.mContentView.getBottom());
        ScaleListener scaleListener = this.mScaleListener;
        if (scaleListener != null) {
            scaleListener.updateScaleBounds(rect, i2, this.mContentView.getLayoutDirection());
        }
    }
}
