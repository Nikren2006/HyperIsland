package miuix.springback.trigger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import miuix.core.util.MiuixUIUtils;
import miuix.springback.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseTrigger {
    private static int mDefaultIndeterminateDistance;
    private static int mDefaultSimpleEnter;
    private static int mDefaultSimpleTrigger;
    private static int mDefaultUpIndeterminateDistance;
    private List<Action> mActions = new ArrayList();
    private Action mUpAction;

    public static abstract class Action {
        static final Comparator<Action> DISTANCE_COMPARATOR = new Comparator<Action>() { // from class: miuix.springback.trigger.BaseTrigger.Action.1
            @Override // java.util.Comparator
            public int compare(Action action, Action action2) {
                return Integer.compare(action.mEnterPoint, action2.mEnterPoint);
            }
        };
        public int mEnterPoint;
        public int mTriggerPoint;

        public Action(int i2, int i3) {
            if (i2 < 0 || i3 < 0 || i3 < i2) {
                throw new IllegalArgumentException("not allow enterPoint < 0 or triggerPoint < 0 or triggerPoint < enterPoint!");
            }
            this.mEnterPoint = i2;
            this.mTriggerPoint = i3;
        }

        public void notifyActivated() {
            onActivated();
        }

        public void notifyEntered() {
            onEntered();
        }

        public void notifyExit() {
            onExit();
        }

        public void notifyFinished() {
            onFinished();
        }

        public void notifyTriggered() {
            onTriggered();
        }

        public abstract void onActivated();

        public View onCreateIndicator(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            return null;
        }

        public abstract void onEntered();

        public abstract void onExit();

        public abstract void onFinished();

        public abstract void onTriggered();
    }

    public static abstract class SimpleAction extends Action {

        public interface OnSimpleActionViewListener {
            float getViewRestartOffsetPoint();

            void onViewActivated(int i2);

            void onViewActivating(int i2);

            void onViewEntered(int i2);

            void onViewEntering(int i2);

            void onViewExit(int i2);

            void onViewFinished(int i2);

            void onViewStart(int i2);

            void onViewStarting(int i2);

            void onViewTriggered(int i2);
        }

        public SimpleAction() {
            super(BaseTrigger.mDefaultSimpleEnter, BaseTrigger.mDefaultSimpleTrigger);
        }

        public SimpleAction(int i2, int i3) {
            super(i2, i3);
        }
    }

    public BaseTrigger(Context context) {
        boolean z2 = MiuixUIUtils.getFontLevel(context) == 2;
        mDefaultIndeterminateDistance = context.getResources().getDimensionPixelSize(z2 ? R.dimen.miuix_sbl_action_indeterminate_distance_large_font : R.dimen.miuix_sbl_action_indeterminate_distance);
        boolean zIsTallFontLang = MiuixUIUtils.isTallFontLang(context);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miuix_sbl_action_upindeterminate_distance);
        mDefaultUpIndeterminateDistance = dimensionPixelSize;
        if (zIsTallFontLang && z2) {
            dimensionPixelSize = (int) (dimensionPixelSize * 1.8f);
        }
        mDefaultUpIndeterminateDistance = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.miuix_sbl_action_simple_enter);
        mDefaultSimpleEnter = dimensionPixelSize2;
        mDefaultSimpleTrigger = dimensionPixelSize2;
    }

    public void addAction(Action action) {
        if (action instanceof IndeterminateUpAction) {
            this.mUpAction = action;
            return;
        }
        if (Collections.binarySearch(this.mActions, action, Action.DISTANCE_COMPARATOR) >= 0) {
            throw new IllegalArgumentException("action conflict.");
        }
        this.mActions.add((-r0) - 1, action);
    }

    public boolean containAction(Action action) {
        return action instanceof IndeterminateUpAction ? this.mUpAction != null : action != null && this.mActions.contains(action);
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public IndeterminateAction getIndeterminateAction() {
        for (int i2 = 0; i2 < this.mActions.size(); i2++) {
            Action action = this.mActions.get(i2);
            if (action != null && (action instanceof IndeterminateAction)) {
                return (IndeterminateAction) action;
            }
        }
        return null;
    }

    public IndeterminateUpAction getIndeterminateUpAction() {
        return (IndeterminateUpAction) this.mUpAction;
    }

    public SimpleAction getSimpleAction() {
        for (int i2 = 0; i2 < this.mActions.size(); i2++) {
            Action action = this.mActions.get(i2);
            if (action != null && (action instanceof SimpleAction)) {
                return (SimpleAction) action;
            }
        }
        return null;
    }

    public abstract boolean isActionRunning();

    public abstract boolean isActionRunning(Action action);

    public boolean removeAction(Action action) {
        if (isActionRunning()) {
            return false;
        }
        if (action instanceof IndeterminateUpAction) {
            ((IndeterminateUpAction) action).mUpDataListener = null;
            this.mUpAction = null;
            return true;
        }
        if (action != null && this.mActions.contains(action)) {
            if (action instanceof IndeterminateAction) {
                ((IndeterminateAction) action).mCompleteListener = null;
            }
            this.mActions.remove(action);
        }
        return true;
    }

    public static abstract class IndeterminateAction extends Action {
        public static final int DEFAULT_OFFSET_POINT = 0;
        static final int[] DEFAULT_TRIGGER_TEXTIDS = {R.string.miuix_sbl_tracking_progress_labe_pull_to_refresh, R.string.miuix_sbl_tracking_progress_labe_release_to_refresh, R.string.miuix_sbl_tracking_progress_labe_refreshing, R.string.miuix_sbl_tracking_progress_labe_refreshed};
        protected OnActionCompleteListener mCompleteListener;
        private int mCountNoData;
        public int[] mTriggerTextIDs;
        public String[] mTriggerTexts;

        public interface OnActionCompleteListener {
            void onActionComplete(IndeterminateAction indeterminateAction);

            void onActionLoadCancel(IndeterminateAction indeterminateAction);

            void onActionLoadFail(IndeterminateAction indeterminateAction);

            void onActionNoData(IndeterminateAction indeterminateAction, int i2);

            void onActionStart(IndeterminateAction indeterminateAction);

            void onUpdateTriggerTextIndex(IndeterminateAction indeterminateAction, int i2, String str);
        }

        public interface OnIndeterminateActionViewListener {
            float getViewRestartOffsetPoint();

            void onViewActivated(int i2);

            void onViewActivating(int i2);

            void onViewEntered(int i2);

            void onViewEntering(int i2);

            void onViewExit(int i2);

            void onViewFinished(int i2);

            void onViewStart(int i2);

            void onViewStarting(int i2);

            void onViewTriggered(int i2);
        }

        public IndeterminateAction(int i2) {
            super(i2, BaseTrigger.mDefaultIndeterminateDistance + i2);
            int[] iArr = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr.length];
            this.mCountNoData = 0;
            if (i2 < 0) {
                throw new IllegalArgumentException("invalid offsetPoint");
            }
            this.mTriggerTextIDs = iArr;
        }

        public int getCountNoData() {
            return this.mCountNoData;
        }

        public boolean isNoData() {
            return this.mCountNoData > 0;
        }

        public void notifyActionNoData() {
            OnActionCompleteListener onActionCompleteListener = this.mCompleteListener;
            if (onActionCompleteListener != null) {
                int i2 = this.mCountNoData + 1;
                this.mCountNoData = i2;
                onActionCompleteListener.onActionNoData(this, i2);
            }
        }

        public void notifyLoadCancel() {
            OnActionCompleteListener onActionCompleteListener = this.mCompleteListener;
            if (onActionCompleteListener != null) {
                onActionCompleteListener.onActionLoadCancel(this);
            }
        }

        public void notifyLoadComplete() {
            OnActionCompleteListener onActionCompleteListener = this.mCompleteListener;
            if (onActionCompleteListener != null) {
                onActionCompleteListener.onActionComplete(this);
            }
        }

        public void notifyLoadFail() {
            OnActionCompleteListener onActionCompleteListener = this.mCompleteListener;
            if (onActionCompleteListener != null) {
                onActionCompleteListener.onActionLoadFail(this);
            }
        }

        public void notifyTriggerTextIndex(int i2, String str) {
            if (i2 >= DEFAULT_TRIGGER_TEXTIDS.length) {
                throw new IllegalArgumentException("invalid index");
            }
            OnActionCompleteListener onActionCompleteListener = this.mCompleteListener;
            if (onActionCompleteListener != null) {
                onActionCompleteListener.onUpdateTriggerTextIndex(this, i2, str);
            }
        }

        public void startIndeterminateAction() {
            OnActionCompleteListener onActionCompleteListener = this.mCompleteListener;
            if (onActionCompleteListener != null) {
                onActionCompleteListener.onActionStart(this);
            }
        }

        public IndeterminateAction(int i2, int[] iArr) {
            super(i2, BaseTrigger.mDefaultIndeterminateDistance + i2);
            int[] iArr2 = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr2.length];
            this.mCountNoData = 0;
            if (i2 >= 0) {
                if (iArr != null && iArr.length == iArr2.length) {
                    this.mTriggerTextIDs = iArr;
                    return;
                }
                throw new IllegalArgumentException("invalid text Ids");
            }
            throw new IllegalArgumentException("invalid offsetPoint");
        }

        public IndeterminateAction(int i2, int i3) {
            super(i2, i3);
            int[] iArr = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr.length];
            this.mCountNoData = 0;
            this.mTriggerTextIDs = iArr;
        }

        public IndeterminateAction(int i2, int i3, int[] iArr) {
            super(i2, i3);
            int[] iArr2 = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr2.length];
            this.mCountNoData = 0;
            if (iArr != null && iArr.length == iArr2.length) {
                this.mTriggerTextIDs = iArr;
                return;
            }
            throw new IllegalArgumentException("invalid text Ids");
        }
    }

    public static abstract class IndeterminateUpAction extends Action {
        public static final int DEFAULT_OFFSET_POINT = 0;
        static final int[] DEFAULT_TRIGGER_TEXTIDS = {R.string.miuix_sbl_tracking_progress_labe_up_refresh, R.string.miuix_sbl_tracking_progress_labe_up_refresh_fail, R.string.miuix_sbl_tracking_progress_labe_up_nodata, R.string.miuix_sbl_tracking_progress_labe_up_none};
        private int mCountNoData;
        public int[] mTriggerTextIDs;
        public String[] mTriggerTexts;
        protected OnUpActionDataListener mUpDataListener;

        public interface OnIndeterminateUpActionViewListener {
            float getViewRestartOffsetPoint();

            void onViewActivated(int i2);

            void onViewActivating(int i2);

            void onViewEntered(int i2);

            void onViewEntering(int i2);

            void onViewExit(int i2);

            void onViewFinished(int i2);

            void onViewStart(int i2);

            void onViewStarting(int i2);

            void onViewTriggered(int i2);
        }

        public interface OnUpActionDataListener {
            void onActionComplete(IndeterminateUpAction indeterminateUpAction);

            void onActionLoadCancel(IndeterminateUpAction indeterminateUpAction);

            void onActionLoadFail(IndeterminateUpAction indeterminateUpAction);

            void onActionNoData(IndeterminateUpAction indeterminateUpAction, int i2);

            void onActionStart(IndeterminateUpAction indeterminateUpAction);

            void onUpdateTriggerTextIndex(IndeterminateUpAction indeterminateUpAction, int i2, String str);
        }

        public IndeterminateUpAction(int i2) {
            super(i2, BaseTrigger.mDefaultUpIndeterminateDistance + i2);
            int[] iArr = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr.length];
            this.mCountNoData = 0;
            if (i2 < 0) {
                throw new IllegalArgumentException("invalid offsetPoint");
            }
            this.mTriggerTextIDs = iArr;
        }

        public int getCountNoData() {
            return this.mCountNoData;
        }

        public boolean isNoData() {
            return this.mCountNoData > 0;
        }

        public void notifyActionNoData() {
            OnUpActionDataListener onUpActionDataListener = this.mUpDataListener;
            if (onUpActionDataListener != null) {
                int i2 = this.mCountNoData + 1;
                this.mCountNoData = i2;
                onUpActionDataListener.onActionNoData(this, i2);
            }
        }

        public void notifyLoadCancel() {
            OnUpActionDataListener onUpActionDataListener = this.mUpDataListener;
            if (onUpActionDataListener != null) {
                onUpActionDataListener.onActionLoadCancel(this);
            }
        }

        public void notifyLoadComplete() {
            OnUpActionDataListener onUpActionDataListener = this.mUpDataListener;
            if (onUpActionDataListener != null) {
                this.mCountNoData = 0;
                onUpActionDataListener.onActionComplete(this);
            }
        }

        public void notifyLoadFail() {
            OnUpActionDataListener onUpActionDataListener = this.mUpDataListener;
            if (onUpActionDataListener != null) {
                onUpActionDataListener.onActionLoadFail(this);
            }
        }

        public void notifyTriggerTextIndex(int i2, String str) {
            if (i2 >= DEFAULT_TRIGGER_TEXTIDS.length) {
                throw new IllegalArgumentException("invalid index");
            }
            OnUpActionDataListener onUpActionDataListener = this.mUpDataListener;
            if (onUpActionDataListener != null) {
                onUpActionDataListener.onUpdateTriggerTextIndex(this, i2, str);
            }
        }

        public void startIndeterminateUpAction() {
            OnUpActionDataListener onUpActionDataListener = this.mUpDataListener;
            if (onUpActionDataListener != null) {
                this.mCountNoData = 0;
                onUpActionDataListener.onActionStart(this);
            }
        }

        public IndeterminateUpAction(int i2, int[] iArr) {
            super(i2, BaseTrigger.mDefaultUpIndeterminateDistance + i2);
            int[] iArr2 = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr2.length];
            this.mCountNoData = 0;
            if (i2 >= 0) {
                if (iArr != null && iArr.length == iArr2.length) {
                    this.mTriggerTextIDs = iArr;
                    return;
                }
                throw new IllegalArgumentException("invalid text Ids");
            }
            throw new IllegalArgumentException("invalid offsetPoint");
        }

        public IndeterminateUpAction(int i2, int i3) {
            super(i2, i3);
            int[] iArr = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr.length];
            this.mCountNoData = 0;
            this.mTriggerTextIDs = iArr;
        }

        public IndeterminateUpAction(int i2, int i3, int[] iArr) {
            super(i2, i3);
            int[] iArr2 = DEFAULT_TRIGGER_TEXTIDS;
            this.mTriggerTexts = new String[iArr2.length];
            this.mCountNoData = 0;
            if (iArr != null && iArr.length == iArr2.length) {
                this.mTriggerTextIDs = iArr;
                return;
            }
            throw new IllegalArgumentException("invalid text Ids");
        }
    }
}
