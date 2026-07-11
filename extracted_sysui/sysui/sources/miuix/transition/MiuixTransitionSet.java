package miuix.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes5.dex */
public class MiuixTransitionSet extends MiuixTransition {
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList<MiuixTransition> mTransitions;

    public static class TransitionSetListener extends TransitionListenerAdapter {
        MiuixTransitionSet mTransitionSet;

        public TransitionSetListener(MiuixTransitionSet miuixTransitionSet) {
            this.mTransitionSet = miuixTransitionSet;
        }

        @Override // miuix.transition.TransitionListenerAdapter, miuix.transition.MiuixTransition.MiuixTransitionListener
        public void onTransitionEnd(MiuixTransition miuixTransition) {
            MiuixTransitionSet miuixTransitionSet = this.mTransitionSet;
            int i2 = miuixTransitionSet.mCurrentListeners - 1;
            miuixTransitionSet.mCurrentListeners = i2;
            if (i2 == 0) {
                miuixTransitionSet.mStarted = false;
                miuixTransitionSet.onTransitionEnd();
            }
            miuixTransition.removeListener(this);
        }

        @Override // miuix.transition.TransitionListenerAdapter, miuix.transition.MiuixTransition.MiuixTransitionListener
        public void onTransitionStart(MiuixTransition miuixTransition) {
            MiuixTransitionSet miuixTransitionSet = this.mTransitionSet;
            if (miuixTransitionSet.mStarted) {
                return;
            }
            miuixTransitionSet.onTransitionStart();
            this.mTransitionSet.mStarted = true;
        }
    }

    public MiuixTransitionSet() {
        this.mTransitions = new ArrayList<>();
        this.mPlayTogether = true;
        this.mStarted = false;
    }

    private void addTransitionInternal(MiuixTransition miuixTransition) {
        this.mTransitions.add(miuixTransition);
        miuixTransition.mParent = this;
    }

    private void setupStartEndListeners() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator<MiuixTransition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().addListener(transitionSetListener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    public MiuixTransitionSet addTransition(MiuixTransition miuixTransition) {
        if (miuixTransition != null) {
            addTransitionInternal(miuixTransition);
        }
        return this;
    }

    @Override // miuix.transition.MiuixTransition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            for (MiuixTransition miuixTransition : this.mTransitions) {
                if (miuixTransition.isValidTarget(transitionValues.view)) {
                    miuixTransition.captureEndValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(miuixTransition);
                }
            }
        }
    }

    @Override // miuix.transition.MiuixTransition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            for (MiuixTransition miuixTransition : this.mTransitions) {
                if (miuixTransition.isValidTarget(transitionValues.view)) {
                    miuixTransition.captureStartValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(miuixTransition);
                }
            }
        }
    }

    @Override // miuix.transition.MiuixTransition
    public void clear() {
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).clear();
        }
    }

    @Override // miuix.transition.MiuixTransition
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).createAnimators(viewGroup, transitionValuesMaps, transitionValuesMaps2, arrayList, arrayList2);
        }
    }

    @Override // miuix.transition.MiuixTransition
    public void forceToEnd() {
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).forceToEnd();
        }
    }

    public int getOrdering() {
        return !this.mPlayTogether ? 1 : 0;
    }

    public MiuixTransition getTransitionAt(int i2) {
        if (i2 < 0 || i2 >= this.mTransitions.size()) {
            return null;
        }
        return this.mTransitions.get(i2);
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    @Override // miuix.transition.MiuixTransition
    public void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            onTransitionStart();
            onTransitionEnd();
            return;
        }
        setupStartEndListeners();
        int size = this.mTransitions.size();
        if (this.mPlayTogether) {
            for (int i2 = 0; i2 < size; i2++) {
                this.mTransitions.get(i2).runAnimators();
            }
            return;
        }
        for (int i3 = 1; i3 < size; i3++) {
            final MiuixTransition miuixTransition = this.mTransitions.get(i3 - 1);
            final MiuixTransition miuixTransition2 = this.mTransitions.get(i3);
            miuixTransition.addListener(new TransitionListenerAdapter() { // from class: miuix.transition.MiuixTransitionSet.1
                @Override // miuix.transition.TransitionListenerAdapter, miuix.transition.MiuixTransition.MiuixTransitionListener
                public void onTransitionEnd(MiuixTransition miuixTransition3) {
                    miuixTransition.removeListener(this);
                    miuixTransition2.runAnimators();
                }
            });
        }
        MiuixTransition miuixTransition3 = this.mTransitions.get(0);
        if (miuixTransition3 != null) {
            miuixTransition3.runAnimators();
        }
    }

    @Override // miuix.transition.MiuixTransition
    public MiuixTransition setAnimConfig(AnimConfig animConfig) {
        super.setAnimConfig(animConfig);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).setAnimConfig(animConfig);
        }
        return this;
    }

    @Override // miuix.transition.MiuixTransition
    public void setCanRemoveViews(boolean z2) {
        super.setCanRemoveViews(z2);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).setCanRemoveViews(z2);
        }
    }

    public MiuixTransitionSet setOrdering(int i2) {
        if (i2 == 0) {
            this.mPlayTogether = true;
        } else {
            if (i2 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i2);
            }
            this.mPlayTogether = false;
        }
        return this;
    }

    @Override // miuix.transition.MiuixTransition
    public MiuixTransition setTransitionMode(int i2) {
        super.setTransitionMode(i2);
        int size = this.mTransitions.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mTransitions.get(i3).setTransitionMode(i2);
        }
        return this;
    }

    @Override // miuix.transition.MiuixTransition
    public String toString(String str) {
        String string = super.toString(str);
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("\n");
            sb.append(this.mTransitions.get(i2).toString(str + "  "));
            string = sb.toString();
        }
        return string;
    }

    @Override // miuix.transition.MiuixTransition
    public MiuixTransitionSet setSceneRoot(ViewGroup viewGroup) {
        super.setSceneRoot(viewGroup);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).setSceneRoot(viewGroup);
        }
        return this;
    }

    @Override // miuix.transition.MiuixTransition
    public MiuixTransitionSet clone() {
        MiuixTransitionSet miuixTransitionSet = (MiuixTransitionSet) super.clone();
        miuixTransitionSet.mTransitions = new ArrayList<>();
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            miuixTransitionSet.addTransitionInternal(this.mTransitions.get(i2).clone());
        }
        return miuixTransitionSet;
    }

    public MiuixTransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTransitions = new ArrayList<>();
        this.mPlayTogether = true;
        this.mStarted = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TransitionSet);
        setOrdering(typedArrayObtainStyledAttributes.getInt(R.styleable.TransitionSet_transitionOrdering, 0));
        typedArrayObtainStyledAttributes.recycle();
    }
}
