package miuix.appcompat.widget.dialoganim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.WindowInsetsCompat;
import com.xiaomi.onetrack.util.a;
import java.lang.ref.WeakReference;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IFolme;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.widget.DialogAnimHelper;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.SystemProperties;
import miuix.core.util.WindowUtils;
import miuix.internal.util.AnimHelper;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneDialogAnim implements IDialogAnim {
    private static final float DAMPING = 0.82f;
    private static final int DISMISS_DURATION = 200;
    private static final int DURATION = 350;
    private static final int MARGIN = 15;
    private static final float RESPONSE = 0.3f;
    private static final String TAG = "PhoneDialogAnim";
    private static final String TAG_HIDE = "hide";
    private static final String TAG_SHOW = "show";
    private static WeakReference<ValueAnimator> sValueAnimatorWeakRef;
    private IFolme mAnimator;
    private WindowInsetsAnimationController mWindowInsetsAnimationController;
    private boolean mIsDebugMode = false;
    private boolean mDiscardImeAnimEnabled = false;
    private int mImeHeight = 0;

    public class AnimLayoutChangeListener implements View.OnLayoutChangeListener {
        final WeakReference<View> wkDecorView;
        final WeakReference<View> wkDimBgView;
        final Rect windowVisibleFrame = new Rect();
        final Point screenSize = new Point();

        public AnimLayoutChangeListener(View view, View view2) {
            this.wkDecorView = new WeakReference<>(view.getRootView());
            this.wkDimBgView = new WeakReference<>(view2);
        }

        public boolean isInMultiScreenBottom(Context context) {
            WindowUtils.getDisplay(context).getRealSize(this.screenSize);
            Rect rect = this.windowVisibleFrame;
            if (rect.left != 0) {
                return false;
            }
            int i2 = rect.right;
            Point point = this.screenSize;
            if (i2 == point.x) {
                return rect.top >= ((int) (((float) point.y) * 0.2f));
            }
            return false;
        }

        public boolean isInMultiScreenMode(Context context) {
            return MiuixUIUtils.isInMultiWindowMode(context) && !MiuixUIUtils.isFreeformMode(context);
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            View view2 = this.wkDecorView.get();
            if (view2 != null) {
                view2.getWindowVisibleDisplayFrame(this.windowVisibleFrame);
            }
        }

        public void updateDimBgMargin(int i2) {
            View view = this.wkDimBgView.get();
            if (view != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                if (marginLayoutParams.bottomMargin != i2) {
                    marginLayoutParams.bottomMargin = i2;
                    view.setLayoutParams(marginLayoutParams);
                }
            }
        }
    }

    public class WeakRefDismissListener extends TransitionListener {
        WeakReference<DialogAnimHelper.OnDismiss> mOnDismiss;
        WeakReference<View> mView;

        public WeakRefDismissListener(View view, DialogAnimHelper.OnDismiss onDismiss) {
            this.mOnDismiss = new WeakReference<>(onDismiss);
            this.mView = new WeakReference<>(view);
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onBegin(Object obj) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag("hide");
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onCancel(Object obj) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(null);
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(null);
            }
            DialogAnimHelper.OnDismiss onDismiss = this.mOnDismiss.get();
            if (onDismiss != null) {
                onDismiss.end();
            } else {
                Log.d(PhoneDialogAnim.TAG, "onComplete mOnDismiss get null");
            }
        }
    }

    public class WeakRefDismissOnAndroidUIListener implements Animator.AnimatorListener {
        WeakReference<DialogAnimHelper.OnDismiss> mOnDismiss;
        WeakReference<View> mView;

        public WeakRefDismissOnAndroidUIListener(View view, DialogAnimHelper.OnDismiss onDismiss) {
            this.mOnDismiss = new WeakReference<>(onDismiss);
            this.mView = new WeakReference<>(view);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(null);
            }
            DialogAnimHelper.OnDismiss onDismiss = this.mOnDismiss.get();
            if (onDismiss != null) {
                onDismiss.end();
            } else {
                Log.d(PhoneDialogAnim.TAG, "onCancel mOnDismiss get null");
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(null);
            }
            DialogAnimHelper.OnDismiss onDismiss = this.mOnDismiss.get();
            if (onDismiss != null) {
                onDismiss.end();
            } else {
                Log.d(PhoneDialogAnim.TAG, "onComplete mOnDismiss get null");
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag("hide");
            }
        }
    }

    public class WeakRefShowListener extends TransitionListener {
        int mEndTranslateY;
        View.OnLayoutChangeListener mOnLayoutChange;
        WeakReference<AlertDialog.OnDialogShowAnimListener> mOnShow;
        WeakReference<View> mView;

        public WeakRefShowListener(AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener, View.OnLayoutChangeListener onLayoutChangeListener, View view, int i2) {
            this.mOnShow = new WeakReference<>(onDialogShowAnimListener);
            this.mOnLayoutChange = onLayoutChangeListener;
            this.mView = new WeakReference<>(view);
            this.mEndTranslateY = i2;
        }

        private void done() {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(null);
                View.OnLayoutChangeListener onLayoutChangeListener = this.mOnLayoutChange;
                if (onLayoutChangeListener != null) {
                    view.removeOnLayoutChangeListener(onLayoutChangeListener);
                    this.mOnLayoutChange = null;
                }
            }
            AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener = this.mOnShow.get();
            if (onDialogShowAnimListener != null) {
                onDialogShowAnimListener.onShowAnimComplete();
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onBegin(Object obj) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(PhoneDialogAnim.TAG_SHOW);
                View.OnLayoutChangeListener onLayoutChangeListener = this.mOnLayoutChange;
                if (onLayoutChangeListener != null) {
                    view.addOnLayoutChangeListener(onLayoutChangeListener);
                }
            }
            AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener = this.mOnShow.get();
            if (onDialogShowAnimListener != null) {
                onDialogShowAnimListener.onShowAnimStart();
            }
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onCancel(Object obj) {
            done();
            this.mOnShow.clear();
            this.mView.clear();
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            WindowInsets rootWindowInsets;
            done();
            View view = this.mView.get();
            if (view != null && (rootWindowInsets = view.getRootWindowInsets()) != null) {
                boolean zIsVisible = rootWindowInsets.isVisible(WindowInsets.Type.ime());
                Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.ime());
                Insets insets2 = rootWindowInsets.getInsets(WindowInsets.Type.navigationBars());
                if (zIsVisible) {
                    PhoneDialogAnim.this.mImeHeight = insets.bottom - insets2.bottom;
                } else {
                    PhoneDialogAnim.this.mImeHeight = 0;
                }
                if (PhoneDialogAnim.this.mIsDebugMode) {
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: isImeVisible = " + zIsVisible + ", mImeHeight = " + PhoneDialogAnim.this.mImeHeight);
                    StringBuilder sb = new StringBuilder();
                    sb.append("onAnimationEnd: imeInsets = ");
                    sb.append(insets);
                    Log.d(PhoneDialogAnim.TAG, sb.toString());
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: navigationBarInsets = " + insets2);
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: newValue = " + this.mEndTranslateY);
                }
            }
            this.mOnShow.clear();
            this.mView.clear();
        }
    }

    public class WeakRefShowOnAndroidUIListener extends AnimatorListenerAdapter {
        int mEndTranslateY;
        View.OnLayoutChangeListener mOnLayoutChange;
        WeakReference<AlertDialog.OnDialogShowAnimListener> mOnShow;
        WeakReference<View> mView;

        public WeakRefShowOnAndroidUIListener(AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener, View.OnLayoutChangeListener onLayoutChangeListener, View view, int i2) {
            this.mOnShow = new WeakReference<>(onDialogShowAnimListener);
            this.mOnLayoutChange = onLayoutChangeListener;
            this.mView = new WeakReference<>(view);
            this.mEndTranslateY = i2;
        }

        private void done() {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(null);
                View.OnLayoutChangeListener onLayoutChangeListener = this.mOnLayoutChange;
                if (onLayoutChangeListener != null) {
                    view.removeOnLayoutChangeListener(onLayoutChangeListener);
                    this.mOnLayoutChange = null;
                }
            }
            AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener = this.mOnShow.get();
            if (onDialogShowAnimListener != null) {
                onDialogShowAnimListener.onShowAnimComplete();
            }
            if (PhoneDialogAnim.sValueAnimatorWeakRef != null) {
                PhoneDialogAnim.sValueAnimatorWeakRef.clear();
                WeakReference unused = PhoneDialogAnim.sValueAnimatorWeakRef = null;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            done();
            View view = this.mView.get();
            if (view != null) {
                PhoneDialogAnim.relayoutView(view, this.mEndTranslateY, true);
            }
            this.mOnShow.clear();
            this.mView.clear();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            WindowInsets rootWindowInsets;
            super.onAnimationEnd(animator);
            done();
            View view = this.mView.get();
            if (view != null && (rootWindowInsets = view.getRootWindowInsets()) != null) {
                boolean zIsVisible = rootWindowInsets.isVisible(WindowInsets.Type.ime());
                Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.ime());
                Insets insets2 = rootWindowInsets.getInsets(WindowInsets.Type.navigationBars());
                if (!zIsVisible || PhoneDialogAnim.this.mDiscardImeAnimEnabled) {
                    PhoneDialogAnim.this.mImeHeight = 0;
                } else {
                    PhoneDialogAnim.this.mImeHeight = insets.bottom - insets2.bottom;
                }
                if (PhoneDialogAnim.this.mIsDebugMode) {
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: isImeVisible = " + zIsVisible + ", mImeHeight = " + PhoneDialogAnim.this.mImeHeight);
                    StringBuilder sb = new StringBuilder();
                    sb.append("onAnimationEnd: imeInsets = ");
                    sb.append(insets);
                    Log.d(PhoneDialogAnim.TAG, sb.toString());
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: navigationBarInsets = " + insets2);
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: newValue = " + (this.mEndTranslateY - PhoneDialogAnim.this.mImeHeight));
                    Log.d(PhoneDialogAnim.TAG, "onAnimationEnd: mDiscardImeAnimEnabled = " + PhoneDialogAnim.this.mDiscardImeAnimEnabled);
                }
                PhoneDialogAnim.relayoutView(view, this.mEndTranslateY - PhoneDialogAnim.this.mImeHeight, true);
            }
            this.mOnShow.clear();
            this.mView.clear();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator, boolean z2) {
            View view = this.mView.get();
            if (view != null) {
                view.setTag(PhoneDialogAnim.TAG_SHOW);
                View.OnLayoutChangeListener onLayoutChangeListener = this.mOnLayoutChange;
                if (onLayoutChangeListener != null) {
                    view.addOnLayoutChangeListener(onLayoutChangeListener);
                }
            }
            AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener = this.mOnShow.get();
            if (onDialogShowAnimListener != null) {
                onDialogShowAnimListener.onShowAnimStart();
            }
        }
    }

    public class WeakRefUpdateOnAndroidUIListener implements ValueAnimator.AnimatorUpdateListener {
        boolean mIsLandscape;
        WeakReference<View> mView;

        public WeakRefUpdateOnAndroidUIListener(View view, boolean z2) {
            this.mView = new WeakReference<>(view);
            this.mIsLandscape = z2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            View view = this.mView.get();
            if (view == null) {
                return;
            }
            if ("hide".equals(view.getTag())) {
                valueAnimator.cancel();
                return;
            }
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets != null) {
                boolean zIsVisible = rootWindowInsets.isVisible(WindowInsets.Type.ime());
                Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.ime());
                Insets insets2 = rootWindowInsets.getInsets(WindowInsets.Type.navigationBars());
                if (!zIsVisible || PhoneDialogAnim.this.mDiscardImeAnimEnabled) {
                    PhoneDialogAnim.this.mImeHeight = 0;
                } else {
                    PhoneDialogAnim.this.mImeHeight = insets.bottom - insets2.bottom;
                }
                if (PhoneDialogAnim.this.mIsDebugMode) {
                    Log.d(PhoneDialogAnim.TAG, "onAnimationUpdate: isImeVisible = " + zIsVisible + ", mImeHeight = " + PhoneDialogAnim.this.mImeHeight);
                    StringBuilder sb = new StringBuilder();
                    sb.append("onAnimationUpdate: imeInsets = ");
                    sb.append(insets);
                    Log.d(PhoneDialogAnim.TAG, sb.toString());
                    Log.d(PhoneDialogAnim.TAG, "onAnimationUpdate: navigationBarInsets = " + insets2);
                    Log.d(PhoneDialogAnim.TAG, "onAnimationUpdate: mDiscardImeAnimEnabled = " + PhoneDialogAnim.this.mDiscardImeAnimEnabled);
                }
            }
            int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (PhoneDialogAnim.this.mIsDebugMode) {
                Log.d(PhoneDialogAnim.TAG, "onAnimationUpdate: newValue = " + (iIntValue - PhoneDialogAnim.this.mImeHeight));
            }
            PhoneDialogAnim.relayoutView(view, iIntValue - PhoneDialogAnim.this.mImeHeight, false);
        }
    }

    public PhoneDialogAnim() {
        isDebugEnabled();
    }

    private void dismissPanel(View view, DialogAnimHelper.OnDismiss onDismiss) {
        if (view == null) {
            return;
        }
        int height = view.getHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        if (AnimHelper.isDialogDebugInAndroidUIThreadEnabled()) {
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(ViewProperty.TRANSLATION_Y, view.getTranslationY(), height));
            objectAnimatorOfPropertyValuesHolder.setInterpolator(new DecelerateInterpolator(1.5f));
            objectAnimatorOfPropertyValuesHolder.addListener(new WeakRefDismissOnAndroidUIListener(view, onDismiss));
            objectAnimatorOfPropertyValuesHolder.setDuration(200L);
            objectAnimatorOfPropertyValuesHolder.start();
            return;
        }
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(FolmeEase.decelerate(1.5f, 200L));
        animConfig.addListeners(new WeakRefDismissListener(view, onDismiss));
        if (this.mAnimator == null) {
            this.mAnimator = Folme.use(view);
        }
        if (view.getTranslationY() >= 0.0f) {
            this.mAnimator.to(ViewProperty.TRANSLATION_Y, Integer.valueOf(height), animConfig);
            return;
        }
        WindowInsetsController windowInsetsController = view.getWindowInsetsController();
        if (windowInsetsController != null) {
            setupImeAnimation(windowInsetsController, view, animConfig, height);
        } else {
            this.mAnimator.to(ViewProperty.TRANSLATION_Y, Integer.valueOf(height), animConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doExecuteShowAnim(View view, int i2, int i3, boolean z2, AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener, View.OnLayoutChangeListener onLayoutChangeListener) {
        if (AnimHelper.isDialogDebugInAndroidUIThreadEnabled()) {
            executeShowAnimAndroidUIThread(i2, i3, new WeakRefShowOnAndroidUIListener(onDialogShowAnimListener, onLayoutChangeListener, view, 0), new WeakRefUpdateOnAndroidUIListener(view, z2));
            return;
        }
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(EaseManager.getStyle(-2, DAMPING, 0.3f));
        animConfig.addListeners(new WeakRefShowListener(onDialogShowAnimListener, onLayoutChangeListener, view, 0));
        if (this.mAnimator == null) {
            this.mAnimator = Folme.use(view);
        }
        IFolme iFolme = this.mAnimator;
        ViewProperty viewProperty = ViewProperty.TRANSLATION_Y;
        iFolme.setTo(viewProperty, Integer.valueOf(i2)).to(viewProperty, Integer.valueOf(i3), animConfig);
    }

    private void executeShowAnimAndroidUIThread(int i2, int i3, WeakRefShowOnAndroidUIListener weakRefShowOnAndroidUIListener, WeakRefUpdateOnAndroidUIListener weakRefUpdateOnAndroidUIListener) {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(i2, i3);
        valueAnimatorOfInt.setDuration(350L);
        valueAnimatorOfInt.setInterpolator(EaseManager.getInterpolator(0, DAMPING, 0.3f));
        valueAnimatorOfInt.addUpdateListener(weakRefUpdateOnAndroidUIListener);
        valueAnimatorOfInt.addListener(weakRefShowOnAndroidUIListener);
        valueAnimatorOfInt.start();
        sValueAnimatorWeakRef = new WeakReference<>(valueAnimatorOfInt);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public Rect getContentViewMargins(View view) {
        Rect rect = new Rect();
        if (view == null) {
            rect.left = 0;
            rect.top = 0;
            rect.right = 0;
            rect.bottom = 0;
            return rect;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            rect.left = marginLayoutParams.leftMargin;
            rect.top = marginLayoutParams.topMargin;
            rect.right = marginLayoutParams.rightMargin;
            rect.bottom = marginLayoutParams.bottomMargin;
        }
        return rect;
    }

    private boolean isDebugEnabled() {
        String str = "";
        try {
            String str2 = SystemProperties.get("log.tag.alertdialog.ime.debug.enable");
            if (str2 != null) {
                str = str2;
            }
        } catch (Exception e2) {
            Log.i(TAG, "can not access property log.tag.alertdialog.ime.enable, debug mode disabled", e2);
        }
        boolean zEquals = TextUtils.equals(a.f3424i, str);
        this.mIsDebugMode = zEquals;
        return zEquals;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void relayoutView(View view, int i2, boolean z2) {
        if (z2) {
            view.animate().cancel();
            view.animate().setDuration(100L).translationY(i2).start();
        } else {
            view.animate().cancel();
            view.setTranslationY(i2);
        }
    }

    @RequiresApi(api = 30)
    private void setupImeAnimation(@NonNull WindowInsetsController windowInsetsController, @NonNull final View view, final AnimConfig animConfig, final int i2) {
        windowInsetsController.controlWindowInsetsAnimation(WindowInsetsCompat.Type.ime(), -1L, null, null, new WindowInsetsAnimationControlListener() { // from class: miuix.appcompat.widget.dialoganim.PhoneDialogAnim.4
            @Override // android.view.WindowInsetsAnimationControlListener
            public void onCancelled(@Nullable WindowInsetsAnimationController windowInsetsAnimationController) {
                if (PhoneDialogAnim.this.mWindowInsetsAnimationController == null) {
                    PhoneDialogAnim.this.mAnimator.to(ViewProperty.TRANSLATION_Y, Integer.valueOf(i2), animConfig);
                }
                PhoneDialogAnim.this.mWindowInsetsAnimationController = null;
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onFinished(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
                PhoneDialogAnim.this.mWindowInsetsAnimationController = null;
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onReady(@NonNull WindowInsetsAnimationController windowInsetsAnimationController, int i3) {
                PhoneDialogAnim.this.mWindowInsetsAnimationController = windowInsetsAnimationController;
                final Insets shownStateInsets = PhoneDialogAnim.this.mWindowInsetsAnimationController.getShownStateInsets();
                final Insets hiddenStateInsets = PhoneDialogAnim.this.mWindowInsetsAnimationController.getHiddenStateInsets();
                final float translationY = view.getTranslationY();
                float f2 = i2;
                animConfig.addListeners(new TransitionListener() { // from class: miuix.appcompat.widget.dialoganim.PhoneDialogAnim.4.1
                    @Override // miuix.animation.listener.TransitionListener
                    public void onCancel(Object obj) {
                        animConfig.removeListeners(this);
                    }

                    @Override // miuix.animation.listener.TransitionListener
                    public void onComplete(Object obj) {
                        animConfig.removeListeners(this);
                    }

                    @Override // miuix.animation.listener.TransitionListener
                    public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                        UpdateInfo updateInfoFindBy;
                        if (PhoneDialogAnim.this.mWindowInsetsAnimationController == null || (updateInfoFindBy = UpdateInfo.findBy(collection, ViewProperty.TRANSLATION_Y)) == null) {
                            return;
                        }
                        float floatValue = updateInfoFindBy.getFloatValue();
                        float f3 = floatValue - translationY;
                        int i4 = shownStateInsets.bottom;
                        if (f3 > i4 || i4 == 0 || floatValue >= 0.0f) {
                            if (PhoneDialogAnim.this.mWindowInsetsAnimationController.isFinished()) {
                                return;
                            }
                            PhoneDialogAnim.this.mWindowInsetsAnimationController.finish(false);
                        } else {
                            float fMax = Math.max(1.0f - (f3 / i4), 0.0f);
                            int i5 = shownStateInsets.left;
                            Insets insets = hiddenStateInsets;
                            PhoneDialogAnim.this.mWindowInsetsAnimationController.setInsetsAndAlpha(Insets.of((int) (((i5 - insets.left) * fMax) + 0.5f), (int) (((r0.top - insets.top) * fMax) + 0.5f), (int) (((r0.right - insets.right) * fMax) + 0.5f), (int) (((r0.bottom - insets.bottom) * fMax) + 0.5f)), 1.0f, fMax);
                        }
                    }
                });
                PhoneDialogAnim.this.mAnimator.to(ViewProperty.TRANSLATION_Y, Float.valueOf(f2), animConfig);
            }
        });
    }

    @Override // miuix.appcompat.widget.dialoganim.IDialogAnim
    public void cancelAnimator() {
        ValueAnimator valueAnimator;
        WeakReference<ValueAnimator> weakReference = sValueAnimatorWeakRef;
        if (weakReference != null && (valueAnimator = weakReference.get()) != null) {
            valueAnimator.cancel();
        }
        IFolme iFolme = this.mAnimator;
        if (iFolme != null) {
            iFolme.cancel();
        }
    }

    @Override // miuix.appcompat.widget.dialoganim.IDialogAnim
    public void executeDismissAnim(View view, View view2, DialogAnimHelper.OnDismiss onDismiss) {
        if ("hide".equals(view.getTag())) {
            return;
        }
        dismissPanel(view, onDismiss);
        DimAnimator.dismiss(view2);
    }

    @Override // miuix.appcompat.widget.dialoganim.IDialogAnim
    public void executeShowAnim(final View view, View view2, final boolean z2, final AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener) {
        if (TAG_SHOW.equals(view.getTag())) {
            return;
        }
        this.mImeHeight = 0;
        final int i2 = ((ViewGroup.MarginLayoutParams) view2.getLayoutParams()).bottomMargin;
        if (view.getScaleX() != 1.0f) {
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
        final AnimLayoutChangeListener animLayoutChangeListener = new AnimLayoutChangeListener(view, view2) { // from class: miuix.appcompat.widget.dialoganim.PhoneDialogAnim.1
            @Override // miuix.appcompat.widget.dialoganim.PhoneDialogAnim.AnimLayoutChangeListener, android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view3, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                Insets insets;
                super.onLayoutChange(view3, i3, i4, i5, i6, i7, i8, i9, i10);
                WindowInsets rootWindowInsets = view3.getRootWindowInsets();
                if (rootWindowInsets != null) {
                    boolean zIsVisible = rootWindowInsets.isVisible(WindowInsets.Type.ime());
                    insets = rootWindowInsets.getInsets(WindowInsets.Type.ime());
                    Insets insets2 = rootWindowInsets.getInsets(WindowInsets.Type.navigationBars());
                    if (zIsVisible) {
                        PhoneDialogAnim.this.mImeHeight = insets.bottom - insets2.bottom;
                    }
                } else {
                    insets = null;
                }
                Context context = view3.getContext();
                if (isInMultiScreenMode(context) && isInMultiScreenBottom(context)) {
                    updateDimBgMargin(i2 + (insets != null ? insets.bottom : 0));
                }
            }
        };
        if (view.getHeight() > 0) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miuix.appcompat.widget.dialoganim.PhoneDialogAnim.2
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view3, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                    view3.removeOnLayoutChangeListener(this);
                    Rect contentViewMargins = PhoneDialogAnim.this.getContentViewMargins(view);
                    if (PhoneDialogAnim.this.mIsDebugMode) {
                        Log.i(PhoneDialogAnim.TAG, "onLayoutChange: contentView.height > 0, contentViewMargins: " + contentViewMargins);
                    }
                    int height = contentViewMargins.bottom + view.getHeight();
                    PhoneDialogAnim.relayoutView(view3, height, false);
                    PhoneDialogAnim.this.doExecuteShowAnim(view3, height, 0, z2, onDialogShowAnimListener, animLayoutChangeListener);
                    view3.setVisibility(0);
                }
            });
            view.setVisibility(4);
            view.setAlpha(1.0f);
        } else {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miuix.appcompat.widget.dialoganim.PhoneDialogAnim.3
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view3, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                    view3.removeOnLayoutChangeListener(this);
                    Rect contentViewMargins = PhoneDialogAnim.this.getContentViewMargins(view3);
                    if (PhoneDialogAnim.this.mIsDebugMode) {
                        Log.i(PhoneDialogAnim.TAG, "onLayoutChange: contentView.height <= 0, contentViewMargins: " + contentViewMargins);
                    }
                    int i11 = contentViewMargins.bottom + (i6 - i4);
                    PhoneDialogAnim.relayoutView(view3, i11, false);
                    PhoneDialogAnim.this.doExecuteShowAnim(view3, i11, 0, z2, onDialogShowAnimListener, animLayoutChangeListener);
                }
            });
        }
        DimAnimator.show(view2);
    }

    public void setDiscardImeAnimEnabled(boolean z2) {
        this.mDiscardImeAnimEnabled = z2;
    }
}
