package miuix.springback.trigger;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import miuix.animation.utils.VelocityMonitor;
import miuix.core.view.ViewCompatOnScrollChangeListener;
import miuix.springback.R;
import miuix.springback.trigger.BaseTrigger;
import miuix.springback.view.SpringBackLayout;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CustomTrigger extends BaseTrigger {
    private static final int ACTION_COMPLETE_HAPTIC_THRESHOLD = 5000;
    private static final float OFFSET_RESET_STATE = 0.25f;
    private static final float OFFSET_SIMPLE_VELOCITY_Y = 1000.0f;
    private static final String TAG = "CustomTrigger";
    protected final ActionComplete mActionComplete;
    private int mActionIndex;
    protected final ActionStart mActionStart;
    protected final ActionTriggered mActionTriggered;
    private BaseTrigger.IndeterminateAction.OnActionCompleteListener mCompleteListener;
    private RelativeLayout mContainer;
    protected Context mContext;
    private BaseTrigger.Action mCurrentAction;
    private TriggerState mCurrentState;
    private long mEnterTime;
    protected final Idle mIdle;
    private FrameLayout mIndicatorContainer;
    private boolean mIsExitISimpleAction;
    private boolean mIsExitIndeterminateAction;
    private boolean mIsExitIndeterminateUpAction;
    private boolean mIsShowContainer;
    private boolean mIsStartIndeterminate;
    private boolean mIsStartIndeterminateUp;
    protected int mLastScrollDistance;
    public SpringBackLayout mLayout;
    private View.OnLayoutChangeListener mLayoutChangeListener;
    protected LayoutInflater mLayoutInflater;
    private View mLoadingContainer;
    private OnIndeterminateActionDataListener mOnActionDataListener;
    private BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener mOnIndeterminateActionViewListener;
    private BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener mOnIndeterminateUpActionViewListener;
    private ViewCompatOnScrollChangeListener mOnScrollListener;
    private BaseTrigger.SimpleAction.OnSimpleActionViewListener mOnSimpleActionViewListener;
    private SpringBackLayout.OnSpringListener mOnSpringListener;
    private OnIndeterminateUpActionDataListener mOnUpActionDataListener;
    protected int mScrollDistance;
    private int mScrollState;
    private boolean mScrollerFinished;
    private View mSimpleActionView;
    protected final Tracking mTracking;
    private boolean mUpActionBegin;
    private BaseTrigger.IndeterminateUpAction.OnUpActionDataListener mUpActionDataListener;
    private View mUpContainer;
    private VelocityMonitor mVelocityMonitor;
    private float mVelocityY;
    protected final WaitForIndeterminate mWaitForIndeterminate;

    public class ActionComplete extends TriggerState {
        private ActionComplete() {
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrollStateChange(int i2, int i3) {
            super.handleScrollStateChange(i2, i3);
            if (i3 == 0) {
                CustomTrigger customTrigger = CustomTrigger.this;
                customTrigger.transition(customTrigger.mIdle);
            }
        }
    }

    public class ActionStart extends TriggerState {
        private ActionStart() {
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrollStateChange(int i2, int i3) {
            super.handleScrollStateChange(i2, i3);
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrolled(int i2, int i3) {
            super.handleScrolled(i2, i3);
        }

        @Override // miuix.springback.trigger.TriggerState
        public boolean handleSpringBack() {
            if (CustomTrigger.this.mCurrentAction != null && (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateAction)) {
                CustomTrigger customTrigger = CustomTrigger.this;
                if (customTrigger.mScrollDistance > customTrigger.mCurrentAction.mTriggerPoint) {
                    CustomTrigger customTrigger2 = CustomTrigger.this;
                    customTrigger2.mLayout.smoothScrollTo(0, -customTrigger2.mCurrentAction.mTriggerPoint);
                    return true;
                }
            }
            return super.handleSpringBack();
        }
    }

    public class ActionTriggered extends TriggerState {
        private ActionTriggered() {
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrollStateChange(int i2, int i3) {
            if (i3 == 0) {
                CustomTrigger customTrigger = CustomTrigger.this;
                customTrigger.transition(customTrigger.mIdle);
            }
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrolled(int i2, int i3) {
            if (CustomTrigger.this.mCurrentAction == null || !(CustomTrigger.this.mCurrentAction instanceof BaseTrigger.SimpleAction)) {
                return;
            }
            CustomTrigger customTrigger = CustomTrigger.this;
            if (customTrigger.mScrollDistance >= customTrigger.mCurrentAction.mEnterPoint || CustomTrigger.this.mScrollState != 1) {
                return;
            }
            CustomTrigger.this.mActionIndex = -1;
            CustomTrigger customTrigger2 = CustomTrigger.this;
            customTrigger2.transition(customTrigger2.mTracking);
        }
    }

    public class Idle extends TriggerState {
        private Idle() {
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrollStateChange(int i2, int i3) {
            if (i2 == 0) {
                if (i3 == 1 || i3 == 2) {
                    CustomTrigger customTrigger = CustomTrigger.this;
                    customTrigger.transition(customTrigger.mTracking);
                }
            }
        }
    }

    public interface OnIndeterminateActionDataListener {
        void onActionComplete(BaseTrigger.IndeterminateAction indeterminateAction);

        void onActionLoadCancel(BaseTrigger.IndeterminateAction indeterminateAction);

        void onActionLoadFail(BaseTrigger.IndeterminateAction indeterminateAction);

        void onActionNoData(BaseTrigger.IndeterminateAction indeterminateAction, int i2);

        void onActionStart(BaseTrigger.IndeterminateAction indeterminateAction);
    }

    public interface OnIndeterminateUpActionDataListener {
        void onActionComplete(BaseTrigger.IndeterminateUpAction indeterminateUpAction);

        void onActionLoadCancel(BaseTrigger.IndeterminateUpAction indeterminateUpAction);

        void onActionLoadFail(BaseTrigger.IndeterminateUpAction indeterminateUpAction);

        void onActionNoData(BaseTrigger.IndeterminateUpAction indeterminateUpAction, int i2);

        void onActionStart(BaseTrigger.IndeterminateUpAction indeterminateUpAction);
    }

    public class Tracking extends TriggerState {
        private boolean mLockActivated;
        private boolean mTriggerable;
        private boolean mUpTriggerable;

        private Tracking() {
            this.mTriggerable = false;
            this.mUpTriggerable = false;
            this.mLockActivated = false;
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrollStateChange(int i2, int i3) {
            if (i3 == 0) {
                CustomTrigger customTrigger = CustomTrigger.this;
                customTrigger.transition(customTrigger.mIdle);
                CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                this.mUpTriggerable = false;
                this.mLockActivated = false;
            }
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrolled(int i2, int i3) {
            if (CustomTrigger.this.mScrollState == 1 || CustomTrigger.this.mScrollState == 2) {
                BaseTrigger.Action action = CustomTrigger.this.mCurrentAction;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (customTrigger.mScrollDistance < 0) {
                    if (!customTrigger.mUpActionBegin) {
                        this.mUpTriggerable = false;
                    }
                    boolean z2 = this.mUpTriggerable;
                    BaseTrigger.IndeterminateUpAction indeterminateUpAction = CustomTrigger.this.getIndeterminateUpAction();
                    if (indeterminateUpAction != null) {
                        CustomTrigger.this.mIsStartIndeterminateUp = true;
                        View indeterminateUpView = CustomTrigger.this.getIndeterminateUpView();
                        if (indeterminateUpView != null && indeterminateUpView.getVisibility() != 0) {
                            indeterminateUpView.setVisibility(0);
                        }
                        CustomTrigger.this.mCurrentAction = indeterminateUpAction;
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.notifyViewsStart(customTrigger2.mCurrentAction, action, CustomTrigger.this.mLastScrollDistance);
                        if (Math.abs(CustomTrigger.this.mScrollDistance) > CustomTrigger.this.getIndeterminateUpAction().mEnterPoint && !CustomTrigger.this.mUpActionBegin) {
                            CustomTrigger.this.mUpActionBegin = true;
                            this.mUpTriggerable = true;
                            CustomTrigger.this.mEnterTime = SystemClock.elapsedRealtime();
                            indeterminateUpAction.notifyEntered();
                            CustomTrigger customTrigger3 = CustomTrigger.this;
                            customTrigger3.notifyViewsEntered(customTrigger3.mCurrentAction, CustomTrigger.this.mScrollDistance);
                        }
                        boolean z3 = this.mUpTriggerable;
                        if (z2 != z3 && z3) {
                            indeterminateUpAction.notifyActivated();
                            CustomTrigger customTrigger4 = CustomTrigger.this;
                            customTrigger4.notifyViewsActivated(customTrigger4.mCurrentAction, CustomTrigger.this.mScrollDistance);
                            if (CustomTrigger.this.mScrollState == 2) {
                                CustomTrigger.this.mLayout.smoothScrollTo(0, indeterminateUpAction.mTriggerPoint);
                                CustomTrigger customTrigger5 = CustomTrigger.this;
                                customTrigger5.transition(customTrigger5.mWaitForIndeterminate);
                            }
                        }
                    }
                    CustomTrigger customTrigger6 = CustomTrigger.this;
                    customTrigger6.notifyViewsAnimator(customTrigger6.mCurrentAction, action, CustomTrigger.this.mScrollDistance);
                    return;
                }
                this.mUpTriggerable = false;
                int i4 = customTrigger.mActionIndex;
                boolean z4 = this.mTriggerable;
                BaseTrigger.Action action2 = CustomTrigger.this.mCurrentAction;
                for (int i5 = 0; i5 < CustomTrigger.this.getActions().size(); i5++) {
                    CustomTrigger customTrigger7 = CustomTrigger.this;
                    if (customTrigger7.mScrollDistance <= customTrigger7.getActions().get(i5).mEnterPoint) {
                        break;
                    }
                    CustomTrigger.this.mActionIndex = i5;
                }
                if (CustomTrigger.this.mActionIndex >= 0) {
                    BaseTrigger.Action action3 = CustomTrigger.this.getActions().get(CustomTrigger.this.mActionIndex);
                    boolean z5 = action3 != null && (action3 instanceof BaseTrigger.SimpleAction);
                    if (!(z5 && CustomTrigger.this.mVelocityY < CustomTrigger.OFFSET_SIMPLE_VELOCITY_Y && CustomTrigger.this.mScrollState == 1) && z5) {
                        CustomTrigger.this.mActionIndex = i4;
                    } else {
                        CustomTrigger.this.mCurrentAction = action3;
                        CustomTrigger customTrigger8 = CustomTrigger.this;
                        customTrigger8.notifyViewsStart(customTrigger8.mCurrentAction, action, CustomTrigger.this.mLastScrollDistance);
                        CustomTrigger customTrigger9 = CustomTrigger.this;
                        this.mTriggerable = customTrigger9.mScrollDistance >= customTrigger9.mCurrentAction.mTriggerPoint;
                    }
                } else {
                    CustomTrigger.this.mCurrentAction = null;
                    this.mTriggerable = false;
                }
                if (i4 != CustomTrigger.this.mActionIndex) {
                    if (action2 != null) {
                        action2.onExit();
                        if (CustomTrigger.this.getSimpleActionView() != null) {
                            CustomTrigger.this.getSimpleActionView().setVisibility(8);
                        }
                    }
                    if (CustomTrigger.this.mCurrentAction != null) {
                        if (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateAction) {
                            if (CustomTrigger.this.getSimpleActionView() != null) {
                                CustomTrigger.this.getSimpleActionView().setVisibility(8);
                            }
                        } else if ((CustomTrigger.this.mCurrentAction instanceof BaseTrigger.SimpleAction) && CustomTrigger.this.getSimpleActionView() != null) {
                            CustomTrigger.this.getSimpleActionView().setVisibility(0);
                        }
                        CustomTrigger.this.mEnterTime = SystemClock.elapsedRealtime();
                        CustomTrigger.this.mCurrentAction.notifyEntered();
                        CustomTrigger customTrigger10 = CustomTrigger.this;
                        customTrigger10.notifyViewsEntered(customTrigger10.mCurrentAction, CustomTrigger.this.mScrollDistance);
                        this.mLockActivated = false;
                        if (this.mTriggerable) {
                            if (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.SimpleAction) {
                                this.mLockActivated = true;
                                HapticCompat.performHapticFeedback(CustomTrigger.this.mLayout, HapticFeedbackConstants.MIUI_BOUNDARY_SPATIAL, HapticFeedbackConstants.MIUI_SWITCH);
                            }
                            CustomTrigger.this.mCurrentAction.notifyActivated();
                            CustomTrigger customTrigger11 = CustomTrigger.this;
                            customTrigger11.notifyViewsActivated(customTrigger11.mCurrentAction, CustomTrigger.this.mScrollDistance);
                        }
                    } else if (CustomTrigger.this.getSimpleActionView() != null) {
                        CustomTrigger.this.getSimpleActionView().setVisibility(8);
                    }
                } else if (action2 != null && z4 != this.mTriggerable) {
                    if (z4) {
                        CustomTrigger.this.mEnterTime = SystemClock.elapsedRealtime();
                        action2.notifyEntered();
                        CustomTrigger customTrigger12 = CustomTrigger.this;
                        customTrigger12.notifyViewsEntered(customTrigger12.mCurrentAction, CustomTrigger.this.mScrollDistance);
                        this.mLockActivated = false;
                    } else {
                        if (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.SimpleAction) {
                            this.mLockActivated = true;
                        }
                        HapticCompat.performHapticFeedback(CustomTrigger.this.mLayout, HapticFeedbackConstants.MIUI_BOUNDARY_SPATIAL, HapticFeedbackConstants.MIUI_MESH_NORMAL);
                        action2.notifyActivated();
                        CustomTrigger customTrigger13 = CustomTrigger.this;
                        customTrigger13.notifyViewsActivated(customTrigger13.mCurrentAction, CustomTrigger.this.mScrollDistance);
                    }
                }
                CustomTrigger customTrigger14 = CustomTrigger.this;
                customTrigger14.notifyViewsAnimator(customTrigger14.mCurrentAction, action, CustomTrigger.this.mScrollDistance);
            }
        }

        @Override // miuix.springback.trigger.TriggerState
        public boolean handleSpringBack() {
            if ((!this.mTriggerable || CustomTrigger.this.mCurrentAction == null) && CustomTrigger.this.mCurrentAction != null && (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.SimpleAction) && CustomTrigger.this.getSimpleActionView() != null) {
                CustomTrigger.this.getSimpleActionView().setVisibility(8);
            }
            if (CustomTrigger.this.mCurrentAction == null) {
                return false;
            }
            if (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateAction) {
                CustomTrigger customTrigger = CustomTrigger.this;
                if (customTrigger.mScrollDistance > customTrigger.mCurrentAction.mEnterPoint) {
                    if (this.mTriggerable) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.mLayout.smoothScrollTo(0, -customTrigger2.mCurrentAction.mTriggerPoint);
                        CustomTrigger customTrigger3 = CustomTrigger.this;
                        customTrigger3.transition(customTrigger3.mWaitForIndeterminate);
                    } else {
                        if (Math.abs(CustomTrigger.this.mLayout.getScaleY()) < Math.abs(CustomTrigger.this.mCurrentAction.mTriggerPoint)) {
                            CustomTrigger.this.mCurrentAction.notifyExit();
                            CustomTrigger customTrigger4 = CustomTrigger.this;
                            customTrigger4.notifyViewsExit(customTrigger4.mCurrentAction, CustomTrigger.this.mScrollDistance);
                        }
                        CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                    }
                    return true;
                }
            }
            if (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateUpAction) {
                CustomTrigger customTrigger5 = CustomTrigger.this;
                customTrigger5.mLayout.smoothScrollTo(0, customTrigger5.mCurrentAction.mTriggerPoint);
                CustomTrigger customTrigger6 = CustomTrigger.this;
                customTrigger6.transition(customTrigger6.mWaitForIndeterminate);
                return true;
            }
            CustomTrigger customTrigger7 = CustomTrigger.this;
            customTrigger7.transition(customTrigger7.mActionTriggered);
            if (this.mLockActivated) {
                CustomTrigger.this.mCurrentAction.notifyTriggered();
                CustomTrigger customTrigger8 = CustomTrigger.this;
                customTrigger8.notifyViewsTriggered(customTrigger8.mCurrentAction, CustomTrigger.this.mScrollDistance);
            } else {
                CustomTrigger.this.mCurrentAction.notifyExit();
                CustomTrigger customTrigger9 = CustomTrigger.this;
                customTrigger9.notifyViewsExit(customTrigger9.mCurrentAction, CustomTrigger.this.mScrollDistance);
            }
            if (CustomTrigger.this.getSimpleActionView() != null) {
                CustomTrigger.this.getSimpleActionView().setVisibility(8);
            }
            return false;
        }
    }

    public class WaitForIndeterminate extends TriggerState {
        private WaitForIndeterminate() {
        }

        @Override // miuix.springback.trigger.TriggerState
        public void handleScrollStateChange(int i2, int i3) {
            if (i3 == 0) {
                CustomTrigger customTrigger = CustomTrigger.this;
                customTrigger.transition(customTrigger.mActionStart);
                if (CustomTrigger.this.mCurrentAction != null && (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateAction)) {
                    CustomTrigger.this.mCurrentAction.notifyTriggered();
                    CustomTrigger customTrigger2 = CustomTrigger.this;
                    customTrigger2.notifyViewsTriggered(customTrigger2.mCurrentAction, CustomTrigger.this.mScrollDistance);
                } else {
                    if (CustomTrigger.this.getIndeterminateUpAction() == null || !(CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateUpAction)) {
                        return;
                    }
                    CustomTrigger.this.getIndeterminateUpAction().notifyTriggered();
                    CustomTrigger customTrigger3 = CustomTrigger.this;
                    customTrigger3.notifyViewsTriggered(customTrigger3.mCurrentAction, CustomTrigger.this.mScrollDistance);
                }
            }
        }
    }

    public CustomTrigger(Context context) {
        super(context);
        this.mVelocityY = 0.0f;
        this.mScrollerFinished = true;
        this.mUpActionBegin = false;
        this.mEnterTime = -1L;
        this.mActionIndex = -1;
        this.mIsShowContainer = true;
        this.mIsExitIndeterminateAction = false;
        this.mIsExitIndeterminateUpAction = false;
        this.mIsExitISimpleAction = false;
        this.mIsStartIndeterminate = false;
        this.mIsStartIndeterminateUp = false;
        this.mLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: miuix.springback.trigger.CustomTrigger.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                int paddingTop;
                int paddingBottom;
                SpringBackLayout springBackLayout = (SpringBackLayout) view;
                int springScrollY = springBackLayout.getSpringScrollY();
                int i10 = -springScrollY;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), BasicMeasure.EXACTLY);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i10, 0);
                CustomTrigger.this.mContainer.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                View indeterminateView = CustomTrigger.this.getIndeterminateView();
                View indeterminateUpView = CustomTrigger.this.getIndeterminateUpView();
                if (indeterminateView != null) {
                    indeterminateView.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                }
                if (springBackLayout.springBackEnable()) {
                    CustomTrigger.this.mContainer.layout(0, -i10, view.getWidth(), 0);
                } else {
                    CustomTrigger.this.mContainer.layout(0, 0, view.getWidth(), i10);
                }
                if (CustomTrigger.this.mLayout.getTarget() != null) {
                    paddingTop = CustomTrigger.this.mLayout.getTarget().getPaddingTop();
                    paddingBottom = CustomTrigger.this.mLayout.getTarget().getPaddingBottom();
                } else {
                    paddingTop = 0;
                    paddingBottom = 0;
                }
                if (indeterminateUpView != null) {
                    indeterminateUpView.measure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(springScrollY, 0));
                    indeterminateUpView.layout(0, CustomTrigger.this.mLayout.getHeight() - paddingBottom, view.getWidth(), (CustomTrigger.this.mLayout.getHeight() - paddingBottom) + springScrollY);
                }
                if (indeterminateView != null) {
                    if (springBackLayout.springBackEnable()) {
                        indeterminateView.layout(0, (-i10) + paddingTop, view.getWidth(), paddingTop);
                    } else {
                        indeterminateView.layout(0, paddingTop, view.getWidth(), i10 + paddingTop);
                    }
                }
                CustomTrigger.this.onSpringBackLayoutChange(view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        };
        this.mOnSpringListener = new SpringBackLayout.OnSpringListener() { // from class: miuix.springback.trigger.CustomTrigger.2
            @Override // miuix.springback.view.SpringBackLayout.OnSpringListener
            public boolean onSpringBack() {
                return CustomTrigger.this.mCurrentState.handleSpringBack();
            }
        };
        this.mOnScrollListener = new ViewCompatOnScrollChangeListener() { // from class: miuix.springback.trigger.CustomTrigger.3
            @Override // miuix.core.view.ViewCompatOnScrollChangeListener
            public void onScrollChange(View view, int i2, int i3, int i4, int i5) {
                SpringBackLayout springBackLayout = (SpringBackLayout) view;
                int i6 = i3 - i5;
                int i7 = i2 - i4;
                int springScrollY = springBackLayout.getSpringScrollY();
                CustomTrigger customTrigger = CustomTrigger.this;
                customTrigger.mLastScrollDistance = customTrigger.mScrollDistance;
                customTrigger.mScrollDistance = -springScrollY;
                customTrigger.mVelocityMonitor.update(CustomTrigger.this.mScrollDistance);
                CustomTrigger customTrigger2 = CustomTrigger.this;
                customTrigger2.mVelocityY = customTrigger2.mVelocityMonitor.getVelocity(0);
                if (springBackLayout.springBackEnable()) {
                    CustomTrigger.this.mContainer.setTop(springScrollY);
                } else {
                    CustomTrigger.this.mContainer.setTop(0);
                }
                int paddingBottom = CustomTrigger.this.mLayout.getTarget() != null ? CustomTrigger.this.mLayout.getTarget().getPaddingBottom() : 0;
                if (CustomTrigger.this.mUpContainer != null && springScrollY >= 0) {
                    CustomTrigger.this.mUpContainer.layout(0, CustomTrigger.this.mLayout.getHeight() - paddingBottom, view.getWidth(), (CustomTrigger.this.mLayout.getHeight() - paddingBottom) + springScrollY);
                }
                CustomTrigger customTrigger3 = CustomTrigger.this;
                if (customTrigger3.mScrollDistance < 0 && customTrigger3.mCurrentAction == CustomTrigger.this.getIndeterminateUpAction() && CustomTrigger.this.getIndeterminateUpAction() != null) {
                    CustomTrigger customTrigger4 = CustomTrigger.this;
                    float fActionRestartOffsetPoint = customTrigger4.actionRestartOffsetPoint(customTrigger4.mCurrentAction);
                    if (CustomTrigger.this.mScrollState == 1 && (Math.abs(CustomTrigger.this.mLastScrollDistance) < fActionRestartOffsetPoint || Math.abs(CustomTrigger.this.mScrollDistance) < fActionRestartOffsetPoint)) {
                        TriggerState triggerState = CustomTrigger.this.mCurrentState;
                        CustomTrigger customTrigger5 = CustomTrigger.this;
                        if (triggerState == customTrigger5.mActionComplete) {
                            customTrigger5.transition(customTrigger5.mTracking);
                        }
                    }
                }
                if (CustomTrigger.this.mCurrentAction != null && (CustomTrigger.this.mCurrentAction instanceof BaseTrigger.IndeterminateAction)) {
                    CustomTrigger customTrigger6 = CustomTrigger.this;
                    float fActionRestartOffsetPoint2 = customTrigger6.actionRestartOffsetPoint(customTrigger6.mCurrentAction);
                    if (CustomTrigger.this.mScrollState == 1 && (Math.abs(CustomTrigger.this.mLastScrollDistance) < fActionRestartOffsetPoint2 || Math.abs(CustomTrigger.this.mScrollDistance) < fActionRestartOffsetPoint2)) {
                        TriggerState triggerState2 = CustomTrigger.this.mCurrentState;
                        CustomTrigger customTrigger7 = CustomTrigger.this;
                        if (triggerState2 == customTrigger7.mActionComplete) {
                            customTrigger7.transition(customTrigger7.mTracking);
                        }
                    }
                    if (CustomTrigger.this.mScrollState == 1) {
                        TriggerState triggerState3 = CustomTrigger.this.mCurrentState;
                        CustomTrigger customTrigger8 = CustomTrigger.this;
                        if (triggerState3 == customTrigger8.mWaitForIndeterminate && Math.abs(customTrigger8.mLastScrollDistance) > CustomTrigger.this.mCurrentAction.mEnterPoint) {
                            CustomTrigger customTrigger9 = CustomTrigger.this;
                            customTrigger9.transition(customTrigger9.mTracking);
                        }
                    }
                }
                CustomTrigger.this.mCurrentState.handleScrolled(i6, springScrollY);
                CustomTrigger customTrigger10 = CustomTrigger.this;
                customTrigger10.onSpringBackScrolled(springBackLayout, i7, i6, customTrigger10.mScrollDistance);
            }

            @Override // miuix.core.view.ViewCompatOnScrollChangeListener
            public void onStateChanged(int i2, int i3, boolean z2) {
                CustomTrigger.this.mScrollState = i3;
                CustomTrigger.this.mScrollerFinished = z2;
                CustomTrigger.this.mCurrentState.handleScrollStateChange(i2, i3);
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mIdle) {
                    View indeterminateUpView = customTrigger.getIndeterminateUpView();
                    if (CustomTrigger.this.mIsStartIndeterminateUp || indeterminateUpView == null || indeterminateUpView.getVisibility() != 0) {
                        return;
                    }
                    indeterminateUpView.setVisibility(8);
                    return;
                }
                if (customTrigger.isShowContainer()) {
                    CustomTrigger.this.mContainer.setVisibility(0);
                } else {
                    CustomTrigger.this.mContainer.setVisibility(4);
                }
                View indeterminateUpView2 = CustomTrigger.this.getIndeterminateUpView();
                if (!CustomTrigger.this.mIsStartIndeterminateUp || indeterminateUpView2 == null || indeterminateUpView2.getVisibility() == 0) {
                    return;
                }
                indeterminateUpView2.setVisibility(0);
            }
        };
        this.mUpActionDataListener = new BaseTrigger.IndeterminateUpAction.OnUpActionDataListener() { // from class: miuix.springback.trigger.CustomTrigger.4
            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateUpAction.OnUpActionDataListener
            public void onActionComplete(BaseTrigger.IndeterminateUpAction indeterminateUpAction) {
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateUpAction) {
                    if (CustomTrigger.this.mLayout.getSpringScrollY() != 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mActionComplete);
                        if (CustomTrigger.this.mOnUpActionDataListener != null) {
                            CustomTrigger.this.mOnUpActionDataListener.onActionComplete(indeterminateUpAction);
                        }
                        if (CustomTrigger.this.mScrollState == 0) {
                            CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                        }
                    } else {
                        CustomTrigger customTrigger3 = CustomTrigger.this;
                        customTrigger3.transition(customTrigger3.mIdle);
                    }
                    View indeterminateUpView = CustomTrigger.this.getIndeterminateUpView();
                    if (CustomTrigger.this.mScrollState == 0 && indeterminateUpView != null && indeterminateUpView.getVisibility() == 0) {
                        indeterminateUpView.setVisibility(8);
                    }
                }
                CustomTrigger.this.mIsStartIndeterminateUp = false;
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateUpAction.OnUpActionDataListener
            public void onActionLoadCancel(BaseTrigger.IndeterminateUpAction indeterminateUpAction) {
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateUpAction) {
                    View indeterminateUpView = CustomTrigger.this.getIndeterminateUpView();
                    if (CustomTrigger.this.mScrollState == 0 && indeterminateUpView != null && indeterminateUpView.getVisibility() == 0) {
                        indeterminateUpView.setVisibility(8);
                    }
                }
                CustomTrigger.this.mIsStartIndeterminateUp = false;
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateUpAction.OnUpActionDataListener
            public void onActionLoadFail(BaseTrigger.IndeterminateUpAction indeterminateUpAction) {
                CustomTrigger.this.mIsStartIndeterminateUp = false;
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateUpAction) {
                    if (CustomTrigger.this.mOnUpActionDataListener != null) {
                        CustomTrigger.this.mOnUpActionDataListener.onActionLoadFail(indeterminateUpAction);
                    }
                    if (CustomTrigger.this.mLayout.getSpringScrollY() == 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mIdle);
                        return;
                    }
                    CustomTrigger customTrigger3 = CustomTrigger.this;
                    customTrigger3.transition(customTrigger3.mActionComplete);
                    if (CustomTrigger.this.mScrollState == 0) {
                        CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                    }
                }
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateUpAction.OnUpActionDataListener
            public void onActionNoData(BaseTrigger.IndeterminateUpAction indeterminateUpAction, int i2) {
                CustomTrigger.this.mIsStartIndeterminateUp = false;
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateUpAction) {
                    if (CustomTrigger.this.mOnUpActionDataListener != null) {
                        CustomTrigger.this.mOnUpActionDataListener.onActionNoData(indeterminateUpAction, i2);
                    }
                    if (CustomTrigger.this.mLayout.getSpringScrollY() == 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mIdle);
                        return;
                    }
                    CustomTrigger customTrigger3 = CustomTrigger.this;
                    customTrigger3.transition(customTrigger3.mActionComplete);
                    if (CustomTrigger.this.mScrollState == 0) {
                        CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                    }
                }
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateUpAction.OnUpActionDataListener
            public void onActionStart(BaseTrigger.IndeterminateUpAction indeterminateUpAction) {
                CustomTrigger.this.mIsStartIndeterminateUp = true;
                if (CustomTrigger.this.getIndeterminateUpAction() == null || CustomTrigger.this.getIndeterminateUpAction() != indeterminateUpAction) {
                    return;
                }
                CustomTrigger customTrigger = CustomTrigger.this;
                customTrigger.transition(customTrigger.mTracking);
                CustomTrigger customTrigger2 = CustomTrigger.this;
                customTrigger2.mCurrentAction = customTrigger2.getIndeterminateUpAction();
                View indeterminateUpView = CustomTrigger.this.getIndeterminateUpView();
                if (indeterminateUpView != null) {
                    indeterminateUpView.setVisibility(0);
                }
                if (CustomTrigger.this.mOnUpActionDataListener != null) {
                    CustomTrigger.this.mOnUpActionDataListener.onActionStart(indeterminateUpAction);
                }
                CustomTrigger customTrigger3 = CustomTrigger.this;
                customTrigger3.mLayout.smoothScrollTo(0, customTrigger3.mCurrentAction.mTriggerPoint);
                if (indeterminateUpView != null) {
                    if (CustomTrigger.this.mLayout.springBackEnable()) {
                        indeterminateUpView.layout(0, CustomTrigger.this.mLayout.getHeight(), CustomTrigger.this.mLayout.getWidth(), CustomTrigger.this.mLayout.getHeight() + indeterminateUpView.getMeasuredHeight());
                    } else {
                        indeterminateUpView.layout(0, CustomTrigger.this.mLayout.getHeight() - indeterminateUpView.getMeasuredHeight(), CustomTrigger.this.mLayout.getWidth(), CustomTrigger.this.mLayout.getHeight());
                    }
                }
                CustomTrigger customTrigger4 = CustomTrigger.this;
                customTrigger4.transition(customTrigger4.mWaitForIndeterminate);
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateUpAction.OnUpActionDataListener
            public void onUpdateTriggerTextIndex(BaseTrigger.IndeterminateUpAction indeterminateUpAction, int i2, String str) {
                indeterminateUpAction.mTriggerTexts[i2] = str;
            }
        };
        this.mCompleteListener = new BaseTrigger.IndeterminateAction.OnActionCompleteListener() { // from class: miuix.springback.trigger.CustomTrigger.5
            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateAction.OnActionCompleteListener
            public void onActionComplete(BaseTrigger.IndeterminateAction indeterminateAction) {
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateAction) {
                    if (CustomTrigger.this.mLayout.getSpringScrollY() != 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mActionComplete);
                        if (CustomTrigger.this.mScrollState == 0 || CustomTrigger.this.mScrollState == 2) {
                            CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                        }
                    } else {
                        CustomTrigger customTrigger3 = CustomTrigger.this;
                        customTrigger3.transition(customTrigger3.mIdle);
                    }
                    if (CustomTrigger.this.mOnActionDataListener != null) {
                        CustomTrigger.this.mOnActionDataListener.onActionComplete(indeterminateAction);
                    }
                }
                if (!CustomTrigger.this.mIsStartIndeterminate && CustomTrigger.this.getActionIntervalTime() > 5000) {
                    HapticCompat.performHapticFeedback(CustomTrigger.this.mLayout, HapticFeedbackConstants.MIUI_BOUNDARY_SPATIAL, HapticFeedbackConstants.MIUI_MESH_NORMAL);
                    CustomTrigger.this.resetEnterTime();
                }
                CustomTrigger.this.mIsStartIndeterminate = false;
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateAction.OnActionCompleteListener
            public void onActionLoadCancel(BaseTrigger.IndeterminateAction indeterminateAction) {
                CustomTrigger.this.mIsStartIndeterminate = false;
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateAction) {
                    if (CustomTrigger.this.mLayout.getSpringScrollY() != 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mActionComplete);
                        if (CustomTrigger.this.mScrollState == 0) {
                            CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                        }
                    } else {
                        CustomTrigger customTrigger3 = CustomTrigger.this;
                        customTrigger3.transition(customTrigger3.mIdle);
                    }
                    if (CustomTrigger.this.mOnActionDataListener != null) {
                        CustomTrigger.this.mOnActionDataListener.onActionComplete(indeterminateAction);
                    }
                }
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateAction.OnActionCompleteListener
            public void onActionLoadFail(BaseTrigger.IndeterminateAction indeterminateAction) {
                CustomTrigger.this.mIsStartIndeterminate = false;
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateAction) {
                    if (CustomTrigger.this.mOnActionDataListener != null) {
                        CustomTrigger.this.mOnActionDataListener.onActionLoadFail(indeterminateAction);
                    }
                    if (CustomTrigger.this.mLayout.getSpringScrollY() == 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mIdle);
                        return;
                    }
                    CustomTrigger customTrigger3 = CustomTrigger.this;
                    customTrigger3.transition(customTrigger3.mActionComplete);
                    if (CustomTrigger.this.mScrollState == 0) {
                        CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                    }
                }
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateAction.OnActionCompleteListener
            public void onActionNoData(BaseTrigger.IndeterminateAction indeterminateAction, int i2) {
                CustomTrigger.this.mIsStartIndeterminate = false;
                TriggerState triggerState = CustomTrigger.this.mCurrentState;
                CustomTrigger customTrigger = CustomTrigger.this;
                if (triggerState == customTrigger.mActionStart && customTrigger.mCurrentAction == indeterminateAction) {
                    if (CustomTrigger.this.mOnActionDataListener != null) {
                        CustomTrigger.this.mOnActionDataListener.onActionNoData(indeterminateAction, i2);
                    }
                    if (CustomTrigger.this.mLayout.getSpringScrollY() == 0) {
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.transition(customTrigger2.mIdle);
                        return;
                    }
                    CustomTrigger customTrigger3 = CustomTrigger.this;
                    customTrigger3.transition(customTrigger3.mActionComplete);
                    if (CustomTrigger.this.mScrollState == 0) {
                        CustomTrigger.this.mLayout.smoothScrollTo(0, 0);
                    }
                }
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateAction.OnActionCompleteListener
            public void onActionStart(BaseTrigger.IndeterminateAction indeterminateAction) {
                BaseTrigger.Action action;
                CustomTrigger.this.mIsStartIndeterminate = true;
                if (CustomTrigger.this.getActions().size() > 0 && (action = CustomTrigger.this.getActions().get(0)) == indeterminateAction && CustomTrigger.this.mCurrentAction == null) {
                    TriggerState triggerState = CustomTrigger.this.mCurrentState;
                    CustomTrigger customTrigger = CustomTrigger.this;
                    if (triggerState == customTrigger.mIdle) {
                        customTrigger.transition(customTrigger.mTracking);
                        BaseTrigger.Action action2 = CustomTrigger.this.mCurrentAction;
                        CustomTrigger.this.mCurrentAction = action;
                        CustomTrigger customTrigger2 = CustomTrigger.this;
                        customTrigger2.notifyViewsStart(customTrigger2.mCurrentAction, action2, CustomTrigger.this.mLastScrollDistance);
                        if (CustomTrigger.this.mOnActionDataListener != null) {
                            CustomTrigger.this.mOnActionDataListener.onActionStart(indeterminateAction);
                        }
                        CustomTrigger customTrigger3 = CustomTrigger.this;
                        customTrigger3.mLayout.smoothScrollTo(0, -customTrigger3.mCurrentAction.mTriggerPoint);
                        if (CustomTrigger.this.mLayout.springBackEnable()) {
                            CustomTrigger.this.mContainer.layout(0, -CustomTrigger.this.mCurrentAction.mTriggerPoint, CustomTrigger.this.mContainer.getWidth(), 0);
                        } else {
                            CustomTrigger.this.mContainer.layout(0, 0, CustomTrigger.this.mContainer.getWidth(), CustomTrigger.this.mCurrentAction.mTriggerPoint);
                        }
                        CustomTrigger customTrigger4 = CustomTrigger.this;
                        customTrigger4.transition(customTrigger4.mWaitForIndeterminate);
                    }
                }
            }

            @Override // miuix.springback.trigger.BaseTrigger.IndeterminateAction.OnActionCompleteListener
            public void onUpdateTriggerTextIndex(BaseTrigger.IndeterminateAction indeterminateAction, int i2, String str) {
                indeterminateAction.mTriggerTexts[i2] = str;
            }
        };
        Idle idle = new Idle();
        this.mIdle = idle;
        this.mTracking = new Tracking();
        this.mActionStart = new ActionStart();
        this.mActionComplete = new ActionComplete();
        this.mWaitForIndeterminate = new WaitForIndeterminate();
        this.mActionTriggered = new ActionTriggered();
        this.mCurrentState = idle;
        init(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float actionRestartOffsetPoint(BaseTrigger.Action action) {
        int i2;
        float f2;
        if (((action == null || !(action instanceof BaseTrigger.IndeterminateAction)) ? (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction)) ? (action == null || !(action instanceof BaseTrigger.SimpleAction)) ? -1.0f : getSimpleViewRestartOffsetPoint() : getIndeterminateUpViewRestartOffsetPoint() : getIndeterminateViewRestartOffsetPoint()) < 0.0f) {
            if (this.mScrollDistance >= 0 || action != getIndeterminateUpAction() || getIndeterminateUpAction() == null) {
                BaseTrigger.Action action2 = this.mCurrentAction;
                if (action2 != null && (action instanceof BaseTrigger.IndeterminateAction)) {
                    int i3 = action2.mTriggerPoint;
                    i2 = action2.mEnterPoint;
                    f2 = (i3 - i2) * OFFSET_RESET_STATE;
                }
            } else {
                f2 = (getIndeterminateUpAction().mTriggerPoint - getIndeterminateUpAction().mEnterPoint) * OFFSET_RESET_STATE;
                i2 = getIndeterminateUpAction().mEnterPoint;
            }
            return f2 + i2;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getActionIntervalTime() {
        if (this.mEnterTime == -1) {
            return 0L;
        }
        return SystemClock.elapsedRealtime() - this.mEnterTime;
    }

    private float getIndeterminateUpViewRestartOffsetPoint() {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            return onIndeterminateUpActionViewListener.getViewRestartOffsetPoint();
        }
        return 0.0f;
    }

    private float getIndeterminateViewRestartOffsetPoint() {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            return onIndeterminateActionViewListener.getViewRestartOffsetPoint();
        }
        return 0.0f;
    }

    private float getSimpleViewRestartOffsetPoint() {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            return onSimpleActionViewListener.getViewRestartOffsetPoint();
        }
        return 0.0f;
    }

    private void init(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mVelocityMonitor = new VelocityMonitor();
        RelativeLayout relativeLayout = (RelativeLayout) this.mLayoutInflater.inflate(R.layout.miuix_sbl_trigger_layout, (ViewGroup) null);
        this.mContainer = relativeLayout;
        this.mIndicatorContainer = (FrameLayout) relativeLayout.findViewById(R.id.indicator_container);
    }

    private void notifyIndeterminateUpViewActivated(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewActivated(i2);
        }
    }

    private void notifyIndeterminateUpViewActivating(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewActivating(i2);
        }
    }

    private void notifyIndeterminateUpViewEntered(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewEntered(i2);
        }
    }

    private void notifyIndeterminateUpViewEntering(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewEntering(i2);
        }
    }

    private void notifyIndeterminateUpViewExit(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewExit(i2);
        }
    }

    private void notifyIndeterminateUpViewFinished(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewFinished(i2);
        }
    }

    private void notifyIndeterminateUpViewStart(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewStart(i2);
        }
    }

    private void notifyIndeterminateUpViewStarting(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewStarting(i2);
        }
    }

    private void notifyIndeterminateUpViewTriggered(int i2) {
        BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener = this.mOnIndeterminateUpActionViewListener;
        if (onIndeterminateUpActionViewListener != null) {
            onIndeterminateUpActionViewListener.onViewTriggered(i2);
        }
    }

    private void notifyIndeterminateViewActivated(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewActivated(i2);
        }
    }

    private void notifyIndeterminateViewActivating(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewActivating(i2);
        }
    }

    private void notifyIndeterminateViewEntered(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewEntered(i2);
        }
    }

    private void notifyIndeterminateViewEntering(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewEntering(i2);
        }
    }

    private void notifyIndeterminateViewExit(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewExit(i2);
        }
    }

    private void notifyIndeterminateViewFinished(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewFinished(i2);
        }
    }

    private void notifyIndeterminateViewStart(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewStart(i2);
        }
    }

    private void notifyIndeterminateViewStarting(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewStarting(i2);
        }
    }

    private void notifyIndeterminateViewTriggered(int i2) {
        BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener = this.mOnIndeterminateActionViewListener;
        if (onIndeterminateActionViewListener != null) {
            onIndeterminateActionViewListener.onViewTriggered(i2);
        }
    }

    private void notifySimpleViewActivated(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewActivated(i2);
        }
    }

    private void notifySimpleViewActivating(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewActivating(i2);
        }
    }

    private void notifySimpleViewEntered(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewEntered(i2);
        }
    }

    private void notifySimpleViewEntering(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewEntering(i2);
        }
    }

    private void notifySimpleViewExit(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewExit(i2);
        }
    }

    private void notifySimpleViewFinished(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewFinished(i2);
        }
    }

    private void notifySimpleViewStart(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewStart(i2);
        }
    }

    private void notifySimpleViewStarting(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewStarting(i2);
        }
    }

    private void notifySimpleViewTriggered(int i2) {
        BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener = this.mOnSimpleActionViewListener;
        if (onSimpleActionViewListener != null) {
            onSimpleActionViewListener.onViewTriggered(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewsActivated(BaseTrigger.Action action, int i2) {
        if (action != null && (action instanceof BaseTrigger.IndeterminateAction)) {
            notifyIndeterminateViewActivated(i2);
            return;
        }
        if (action != null && (action instanceof BaseTrigger.SimpleAction)) {
            notifySimpleViewActivated(i2);
        } else {
            if (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction)) {
                return;
            }
            notifyIndeterminateUpViewActivated(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewsAnimator(BaseTrigger.Action action, BaseTrigger.Action action2, int i2) {
        if (action != null && (action instanceof BaseTrigger.IndeterminateAction)) {
            if (Math.abs(i2) < action.mEnterPoint) {
                notifyIndeterminateViewStarting(i2);
            }
            if (Math.abs(i2) >= action.mEnterPoint && Math.abs(i2) < action.mTriggerPoint) {
                notifyIndeterminateViewEntering(i2);
            }
            if (Math.abs(i2) >= action.mTriggerPoint) {
                notifyIndeterminateViewActivating(i2);
                return;
            }
            return;
        }
        if (action != null && (action instanceof BaseTrigger.SimpleAction)) {
            if (Math.abs(i2) < action.mEnterPoint) {
                notifySimpleViewStarting(i2);
            }
            if (Math.abs(i2) >= action.mEnterPoint && Math.abs(i2) < action.mTriggerPoint) {
                notifySimpleViewEntering(i2);
            }
            if (Math.abs(i2) >= action.mTriggerPoint) {
                notifySimpleViewActivating(i2);
                return;
            }
            return;
        }
        if (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction)) {
            return;
        }
        if (Math.abs(i2) < action.mEnterPoint) {
            notifyIndeterminateUpViewStarting(i2);
        }
        if (Math.abs(i2) >= action.mEnterPoint && Math.abs(i2) < action.mTriggerPoint) {
            notifyIndeterminateUpViewEntering(i2);
        }
        if (Math.abs(i2) >= action.mTriggerPoint) {
            notifyIndeterminateUpViewActivating(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewsEntered(BaseTrigger.Action action, int i2) {
        if (action != null && (action instanceof BaseTrigger.IndeterminateAction)) {
            notifyIndeterminateViewEntered(i2);
            return;
        }
        if (action != null && (action instanceof BaseTrigger.SimpleAction)) {
            notifySimpleViewEntered(i2);
        } else {
            if (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction)) {
                return;
            }
            notifyIndeterminateUpViewEntered(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewsExit(BaseTrigger.Action action, int i2) {
        if (action != null && (action instanceof BaseTrigger.IndeterminateAction)) {
            notifyIndeterminateViewExit(i2);
            return;
        }
        if (action != null && (action instanceof BaseTrigger.SimpleAction)) {
            notifySimpleViewExit(i2);
        } else {
            if (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction)) {
                return;
            }
            notifyIndeterminateUpViewExit(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewsStart(BaseTrigger.Action action, BaseTrigger.Action action2, int i2) {
        if (action != null && (action instanceof BaseTrigger.IndeterminateAction) && action2 != action) {
            notifyIndeterminateViewStart(i2);
            return;
        }
        if (action != null && (action instanceof BaseTrigger.SimpleAction) && action2 != action) {
            notifySimpleViewStart(i2);
        } else {
            if (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction) || action2 == action) {
                return;
            }
            notifyIndeterminateUpViewStart(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewsTriggered(BaseTrigger.Action action, int i2) {
        if (action != null && (action instanceof BaseTrigger.IndeterminateAction)) {
            notifyIndeterminateViewTriggered(i2);
            return;
        }
        if (action != null && (action instanceof BaseTrigger.SimpleAction)) {
            notifySimpleViewTriggered(i2);
        } else {
            if (action == null || !(action instanceof BaseTrigger.IndeterminateUpAction)) {
                return;
            }
            notifyIndeterminateUpViewTriggered(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetEnterTime() {
        this.mEnterTime = -1L;
    }

    @Override // miuix.springback.trigger.BaseTrigger
    public void addAction(BaseTrigger.Action action) {
        View view;
        View view2;
        View view3;
        super.addAction(action);
        if (action instanceof BaseTrigger.IndeterminateUpAction) {
            this.mIsExitIndeterminateUpAction = true;
            BaseTrigger.IndeterminateUpAction indeterminateUpAction = (BaseTrigger.IndeterminateUpAction) action;
            indeterminateUpAction.mUpDataListener = this.mUpActionDataListener;
            if (this.mUpContainer == null) {
                View viewOnCreateIndicator = indeterminateUpAction.onCreateIndicator(this.mLayoutInflater, this.mLayout);
                this.mUpContainer = viewOnCreateIndicator;
                if (viewOnCreateIndicator == null) {
                    this.mUpContainer = this.mLayoutInflater.inflate(R.layout.miuix_sbl_trigger_up_layout, (ViewGroup) null);
                }
                SpringBackLayout springBackLayout = this.mLayout;
                if (springBackLayout == null || (view3 = this.mUpContainer) == null) {
                    return;
                }
                springBackLayout.addView(view3);
                return;
            }
            return;
        }
        if (!(action instanceof BaseTrigger.IndeterminateAction)) {
            if (action instanceof BaseTrigger.SimpleAction) {
                this.mIsExitISimpleAction = true;
                BaseTrigger.SimpleAction simpleAction = (BaseTrigger.SimpleAction) action;
                if (this.mSimpleActionView == null) {
                    View viewOnCreateIndicator2 = simpleAction.onCreateIndicator(this.mLayoutInflater, this.mIndicatorContainer);
                    this.mSimpleActionView = viewOnCreateIndicator2;
                    if (viewOnCreateIndicator2 == null) {
                        this.mSimpleActionView = this.mLayoutInflater.inflate(R.layout.miuix_sbl_simple_indicator, (ViewGroup) this.mIndicatorContainer, false);
                    }
                    FrameLayout frameLayout = this.mIndicatorContainer;
                    if (frameLayout == null || (view = this.mSimpleActionView) == null) {
                        return;
                    }
                    frameLayout.addView(view);
                    return;
                }
                return;
            }
            return;
        }
        this.mIsExitIndeterminateAction = true;
        BaseTrigger.IndeterminateAction indeterminateAction = (BaseTrigger.IndeterminateAction) action;
        indeterminateAction.mCompleteListener = this.mCompleteListener;
        if (this.mLoadingContainer == null) {
            View viewOnCreateIndicator3 = indeterminateAction.onCreateIndicator(this.mLayoutInflater, this.mContainer);
            this.mLoadingContainer = viewOnCreateIndicator3;
            if (viewOnCreateIndicator3 == null) {
                View viewInflate = this.mLayoutInflater.inflate(R.layout.miuix_sbl_trigger_loading_progress, (ViewGroup) null);
                View viewInflate2 = this.mLayoutInflater.inflate(R.layout.miuix_sbl_trigger_tracking_progress, (ViewGroup) null);
                View viewInflate3 = this.mLayoutInflater.inflate(R.layout.miuix_sbl_trigger_tracking_progress_label, (ViewGroup) null);
                this.mContainer.addView(viewInflate);
                this.mContainer.addView(viewInflate2);
                this.mContainer.addView(viewInflate3);
            }
            RelativeLayout relativeLayout = this.mContainer;
            if (relativeLayout == null || (view2 = this.mLoadingContainer) == null) {
                return;
            }
            relativeLayout.addView(view2);
        }
    }

    public void attach(SpringBackLayout springBackLayout) {
        if (!springBackLayout.springBackEnable()) {
            springBackLayout.setSpringBackEnableOnTriggerAttached(true);
        }
        this.mLayout = springBackLayout;
        springBackLayout.addView(this.mContainer);
        if (this.mUpContainer != null) {
            boolean z2 = false;
            for (int i2 = 0; i2 < this.mLayout.getChildCount(); i2++) {
                if (this.mLayout.getChildAt(i2) == this.mUpContainer) {
                    z2 = true;
                }
            }
            if (!z2) {
                this.mLayout.addView(this.mUpContainer);
            }
        }
        if (this.mSimpleActionView != null) {
            boolean z3 = false;
            for (int i3 = 0; i3 < this.mIndicatorContainer.getChildCount(); i3++) {
                if (this.mIndicatorContainer.getChildAt(i3) == this.mSimpleActionView) {
                    z3 = true;
                }
            }
            if (!z3) {
                this.mIndicatorContainer.addView(this.mSimpleActionView);
            }
        }
        springBackLayout.addOnLayoutChangeListener(this.mLayoutChangeListener);
        springBackLayout.setOnSpringListener(this.mOnSpringListener);
        springBackLayout.addOnScrollChangeListener(this.mOnScrollListener);
    }

    public BaseTrigger.Action getCurrentAction() {
        return this.mCurrentAction;
    }

    public TriggerState getCurrentState() {
        return this.mCurrentState;
    }

    public View getIndeterminateUpView() {
        return this.mUpContainer;
    }

    public View getIndeterminateView() {
        return this.mLoadingContainer;
    }

    public ViewGroup getIndicatorContainer() {
        return this.mIndicatorContainer;
    }

    public ViewGroup getRootContainer() {
        return this.mContainer;
    }

    public View getSimpleActionView() {
        return this.mSimpleActionView;
    }

    @Override // miuix.springback.trigger.BaseTrigger
    public boolean isActionRunning() {
        TriggerState triggerState = this.mCurrentState;
        return (triggerState == null || triggerState == this.mIdle) ? false : true;
    }

    public boolean isExitIndeterminateAction() {
        return this.mIsExitIndeterminateAction;
    }

    public boolean isExitIndeterminateUpAction() {
        return this.mIsExitIndeterminateUpAction;
    }

    public boolean isExitSimpleAction() {
        return this.mIsExitISimpleAction;
    }

    public boolean isShowContainer() {
        return this.mIsShowContainer;
    }

    public abstract void onSpringBackLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    public abstract void onSpringBackScrolled(SpringBackLayout springBackLayout, int i2, int i3, int i4);

    @Override // miuix.springback.trigger.BaseTrigger
    public boolean removeAction(BaseTrigger.Action action) {
        boolean zRemoveAction = super.removeAction(action);
        if (zRemoveAction && (action instanceof BaseTrigger.IndeterminateUpAction)) {
            this.mIsExitIndeterminateUpAction = false;
            View view = this.mUpContainer;
            if (view != null) {
                this.mLayout.removeView(view);
                this.mUpContainer = null;
            }
        } else if (zRemoveAction && (action instanceof BaseTrigger.IndeterminateAction)) {
            this.mIsExitIndeterminateAction = false;
            View view2 = this.mLoadingContainer;
            if (view2 != null) {
                this.mContainer.removeView(view2);
                this.mLoadingContainer = null;
            }
        } else if (zRemoveAction && (action instanceof BaseTrigger.SimpleAction)) {
            this.mIsExitISimpleAction = false;
            View view3 = this.mSimpleActionView;
            if (view3 != null) {
                this.mIndicatorContainer.removeView(view3);
                this.mSimpleActionView = null;
            }
        }
        return zRemoveAction;
    }

    public void setOnActionDataListener(OnIndeterminateActionDataListener onIndeterminateActionDataListener) {
        this.mOnActionDataListener = onIndeterminateActionDataListener;
    }

    public void setOnIndeterminateActionViewListener(BaseTrigger.IndeterminateAction.OnIndeterminateActionViewListener onIndeterminateActionViewListener) {
        this.mOnIndeterminateActionViewListener = onIndeterminateActionViewListener;
    }

    public void setOnIndeterminateUpActionViewListener(BaseTrigger.IndeterminateUpAction.OnIndeterminateUpActionViewListener onIndeterminateUpActionViewListener) {
        this.mOnIndeterminateUpActionViewListener = onIndeterminateUpActionViewListener;
    }

    public void setOnSimpleActionViewListener(BaseTrigger.SimpleAction.OnSimpleActionViewListener onSimpleActionViewListener) {
        this.mOnSimpleActionViewListener = onSimpleActionViewListener;
    }

    public void setOnUpActionDataListener(OnIndeterminateUpActionDataListener onIndeterminateUpActionDataListener) {
        this.mOnUpActionDataListener = onIndeterminateUpActionDataListener;
    }

    public void setShowContainer(boolean z2) {
        this.mIsShowContainer = z2;
    }

    public void transition(TriggerState triggerState) {
        BaseTrigger.Action action;
        this.mCurrentState = triggerState;
        if (triggerState == this.mIdle) {
            if (this.mScrollerFinished && (action = this.mCurrentAction) != null) {
                action.notifyFinished();
                BaseTrigger.Action action2 = this.mCurrentAction;
                if (action2 instanceof BaseTrigger.IndeterminateAction) {
                    notifyIndeterminateViewFinished(this.mScrollDistance);
                } else if (action2 instanceof BaseTrigger.IndeterminateUpAction) {
                    notifyIndeterminateUpViewFinished(this.mScrollDistance);
                } else if (action2 instanceof BaseTrigger.SimpleAction) {
                    notifySimpleViewFinished(this.mScrollDistance);
                }
            }
            this.mCurrentAction = null;
            this.mActionIndex = -1;
            this.mVelocityMonitor.clear();
        }
    }

    @Override // miuix.springback.trigger.BaseTrigger
    public boolean isActionRunning(BaseTrigger.Action action) {
        TriggerState triggerState = this.mCurrentState;
        return (triggerState == null || triggerState == this.mIdle || this.mCurrentAction != action) ? false : true;
    }
}
