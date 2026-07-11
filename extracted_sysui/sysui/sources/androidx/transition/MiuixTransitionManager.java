package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import miuix.transition.MiuixTransition;

/* JADX INFO: loaded from: classes.dex */
public class MiuixTransitionManager {
    private static final boolean DBG = false;
    private static final String LOG_TAG = "TransitionManager";
    private static MiuixTransition sDefaultTransition = new miuix.transition.AutoTransition();
    static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<MiuixTransition>>>> sRunningTransitions = new ThreadLocal<>();
    static ArrayList<ViewGroup> sPendingTransitions = new ArrayList<>();
    private ArrayMap<Scene, MiuixTransition> mSceneTransitions = new ArrayMap<>();
    private ArrayMap<Scene, ArrayMap<Scene, MiuixTransition>> mScenePairTransitions = new ArrayMap<>();

    public static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        ViewGroup mSceneRoot;
        MiuixTransition mTransition;

        public MultiListener(MiuixTransition miuixTransition, ViewGroup viewGroup) {
            this.mTransition = miuixTransition;
            this.mSceneRoot = viewGroup;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        @SuppressLint({"RestrictedApi"})
        public boolean onPreDraw() {
            removeListeners();
            if (!MiuixTransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                return true;
            }
            final ArrayMap<ViewGroup, ArrayList<MiuixTransition>> runningTransitions = MiuixTransitionManager.getRunningTransitions();
            ArrayList<MiuixTransition> arrayList = runningTransitions.get(this.mSceneRoot);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                runningTransitions.put(this.mSceneRoot, arrayList);
            }
            arrayList.add(this.mTransition);
            this.mTransition.addListener(new miuix.transition.TransitionListenerAdapter() { // from class: androidx.transition.MiuixTransitionManager.MultiListener.1
                @Override // miuix.transition.TransitionListenerAdapter, miuix.transition.MiuixTransition.MiuixTransitionListener
                public void onTransitionEnd(MiuixTransition miuixTransition) {
                    ((ArrayList) runningTransitions.get(MultiListener.this.mSceneRoot)).remove(miuixTransition);
                    miuixTransition.removeListener(this);
                }
            });
            this.mTransition.captureValues(this.mSceneRoot, false);
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(@NonNull View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        @SuppressLint({"RestrictedApi"})
        public void onViewDetachedFromWindow(View view) {
            removeListeners();
            MiuixTransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<MiuixTransition> arrayList = MiuixTransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<MiuixTransition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().forceToEnd();
                }
                arrayList.clear();
            }
            this.mTransition.clearValues(true);
            this.mTransition.clearValues(false);
            this.mTransition = null;
        }
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup) {
        beginDelayedTransition(viewGroup, null);
    }

    private static void changeScene(Scene scene, MiuixTransition miuixTransition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sPendingTransitions.contains(sceneRoot)) {
            return;
        }
        Scene currentScene = Scene.getCurrentScene(sceneRoot);
        if (miuixTransition == null) {
            if (currentScene != null) {
                currentScene.exit();
            }
            scene.enter();
            return;
        }
        sPendingTransitions.add(sceneRoot);
        MiuixTransition miuixTransitionClone = miuixTransition.clone();
        miuixTransitionClone.setSceneRoot(sceneRoot);
        if (currentScene != null && currentScene.isCreatedFromLayoutResource()) {
            miuixTransitionClone.setCanRemoveViews(true);
        }
        sceneChangeSetup(sceneRoot, miuixTransitionClone);
        scene.enter();
        sceneChangeRunTransition(sceneRoot, miuixTransitionClone);
    }

    public static MiuixTransition getDefaultTransition() {
        return sDefaultTransition;
    }

    public static ArrayMap<ViewGroup, ArrayList<MiuixTransition>> getRunningTransitions() {
        ArrayMap<ViewGroup, ArrayList<MiuixTransition>> arrayMap;
        WeakReference<ArrayMap<ViewGroup, ArrayList<MiuixTransition>>> weakReference = sRunningTransitions.get();
        if (weakReference != null && (arrayMap = weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap<ViewGroup, ArrayList<MiuixTransition>> arrayMap2 = new ArrayMap<>();
        sRunningTransitions.set(new WeakReference<>(arrayMap2));
        return arrayMap2;
    }

    public static void go(@NonNull Scene scene) {
        changeScene(scene, sDefaultTransition);
    }

    public static void removeRunningTransitions() {
        sRunningTransitions.remove();
    }

    private static void sceneChangeRunTransition(ViewGroup viewGroup, MiuixTransition miuixTransition) {
        if (miuixTransition == null || viewGroup == null) {
            return;
        }
        MultiListener multiListener = new MultiListener(miuixTransition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(multiListener);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
    }

    @SuppressLint({"RestrictedApi"})
    private static void sceneChangeSetup(ViewGroup viewGroup, MiuixTransition miuixTransition) {
        ArrayList<MiuixTransition> arrayList = getRunningTransitions().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            for (MiuixTransition miuixTransition2 : arrayList) {
                miuixTransition2.forceToEnd();
                miuixTransition2.clear();
            }
            arrayList.clear();
        }
        if (miuixTransition != null) {
            miuixTransition.captureValues(viewGroup, true);
        }
        Scene currentScene = Scene.getCurrentScene(viewGroup);
        if (currentScene != null) {
            currentScene.exit();
        }
    }

    public static void setDefaultTransition(MiuixTransition miuixTransition) {
        sDefaultTransition = miuixTransition;
    }

    public MiuixTransition getTransition(Scene scene) {
        Scene currentScene;
        ArrayMap<Scene, MiuixTransition> arrayMap;
        MiuixTransition miuixTransition;
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sceneRoot != null && (currentScene = Scene.getCurrentScene(sceneRoot)) != null && (arrayMap = this.mScenePairTransitions.get(scene)) != null && (miuixTransition = arrayMap.get(currentScene)) != null) {
            return miuixTransition;
        }
        MiuixTransition miuixTransition2 = this.mSceneTransitions.get(scene);
        return miuixTransition2 != null ? miuixTransition2 : sDefaultTransition;
    }

    public void setTransition(@NonNull Scene scene, @Nullable MiuixTransition miuixTransition) {
        this.mSceneTransitions.put(scene, miuixTransition);
    }

    public void transitionTo(@NonNull Scene scene) {
        changeScene(scene, getTransition(scene));
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable MiuixTransition miuixTransition) {
        if (sPendingTransitions.contains(viewGroup) || !ViewCompat.isLaidOut(viewGroup)) {
            return;
        }
        sPendingTransitions.add(viewGroup);
        if (miuixTransition == null) {
            miuixTransition = sDefaultTransition;
        }
        MiuixTransition miuixTransitionClone = miuixTransition.clone();
        sceneChangeSetup(viewGroup, miuixTransitionClone);
        Scene.setCurrentScene(viewGroup, null);
        sceneChangeRunTransition(viewGroup, miuixTransitionClone);
    }

    public static void go(@NonNull Scene scene, @Nullable MiuixTransition miuixTransition) {
        changeScene(scene, miuixTransition);
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable MiuixTransition miuixTransition) {
        ArrayMap<Scene, MiuixTransition> arrayMap = this.mScenePairTransitions.get(scene2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
            this.mScenePairTransitions.put(scene2, arrayMap);
        }
        arrayMap.put(scene, miuixTransition);
    }
}
