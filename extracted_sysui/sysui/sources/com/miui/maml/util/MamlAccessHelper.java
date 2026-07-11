package com.miui.maml.util;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.miui.maml.CommandTriggers;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.elements.AnimatedScreenElement;
import com.miui.maml.elements.ButtonScreenElement;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MamlAccessHelper extends ExploreByTouchHelper {
    private static final String TAG = "MamlAccessHelper";
    private WeakReference<View> mHostViewRef;
    private WeakReference<ScreenElementRoot> mRootRef;

    public MamlAccessHelper(ScreenElementRoot screenElementRoot, View view) {
        super(view);
        this.mRootRef = new WeakReference<>(screenElementRoot);
        this.mHostViewRef = new WeakReference<>(view);
        ScreenElementRoot screenElementRoot2 = this.mRootRef.get();
        if (screenElementRoot2 != null) {
            screenElementRoot2.setMamlAccessHelper(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$performAccessibilityAction$0(View view, int i2, int i3) {
        getAccessibilityNodeProvider(view).performAction(i2, i3, null);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public int getVirtualViewAt(float f2, float f3) {
        ScreenElementRoot screenElementRoot = this.mRootRef.get();
        if (screenElementRoot == null) {
            return Integer.MIN_VALUE;
        }
        List<AnimatedScreenElement> accessibleElements = screenElementRoot.getAccessibleElements();
        for (int size = accessibleElements.size() - 1; size >= 0; size--) {
            AnimatedScreenElement animatedScreenElement = accessibleElements.get(size);
            if (animatedScreenElement.isVisible() && animatedScreenElement.touched(f2, f3)) {
                return size;
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void getVisibleVirtualViews(List<Integer> list) {
        ScreenElementRoot screenElementRoot = this.mRootRef.get();
        if (screenElementRoot != null) {
            List<AnimatedScreenElement> accessibleElements = screenElementRoot.getAccessibleElements();
            for (int i2 = 0; i2 < accessibleElements.size(); i2++) {
                if (accessibleElements.get(i2).isVisible()) {
                    list.add(Integer.valueOf(i2));
                }
            }
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public boolean onPerformActionForVirtualView(int i2, int i3, Bundle bundle) {
        ScreenElementRoot screenElementRoot = this.mRootRef.get();
        if (screenElementRoot == null || i3 != 16) {
            return false;
        }
        List<AnimatedScreenElement> accessibleElements = screenElementRoot.getAccessibleElements();
        if (i2 >= 0 && i2 < accessibleElements.size()) {
            AnimatedScreenElement animatedScreenElement = accessibleElements.get(i2);
            animatedScreenElement.performAction("up");
            if (!(animatedScreenElement instanceof ButtonScreenElement)) {
                return true;
            }
            ((ButtonScreenElement) animatedScreenElement).onActionUp();
            return true;
        }
        return false;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateEventForVirtualView(int i2, AccessibilityEvent accessibilityEvent) {
        ScreenElementRoot screenElementRoot = this.mRootRef.get();
        if (screenElementRoot != null) {
            List<AnimatedScreenElement> accessibleElements = screenElementRoot.getAccessibleElements();
            if (i2 < 0 || i2 >= accessibleElements.size()) {
                return;
            }
            accessibilityEvent.setContentDescription(accessibleElements.get(i2).getContentDescription());
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateNodeForVirtualView(int i2, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ScreenElementRoot screenElementRoot = this.mRootRef.get();
        String str = "";
        if (screenElementRoot == null) {
            MamlLog.e(TAG, "mRoot == null, add an empty content description");
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 0, 0));
            return;
        }
        List<AnimatedScreenElement> accessibleElements = screenElementRoot.getAccessibleElements();
        if (i2 < 0 || i2 >= accessibleElements.size()) {
            MamlLog.e(TAG, "virtualViewId not found " + i2);
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 0, 0));
            return;
        }
        AnimatedScreenElement animatedScreenElement = accessibleElements.get(i2);
        String contentDescription = animatedScreenElement.getContentDescription();
        if (contentDescription == null) {
            MamlLog.e(TAG, "element.getContentDescription() == null " + animatedScreenElement.getName());
        } else {
            str = contentDescription;
        }
        accessibilityNodeInfoCompat.setContentDescription(str);
        CommandTriggers triggers = animatedScreenElement.getTriggers();
        if ((animatedScreenElement instanceof ButtonScreenElement) || (triggers != null && (triggers.find("up") != null || triggers.find("down") != null))) {
            accessibilityNodeInfoCompat.addAction(16);
        }
        accessibilityNodeInfoCompat.setBoundsInParent(new Rect((int) animatedScreenElement.getAbsoluteLeft(), (int) animatedScreenElement.getAbsoluteTop(), (int) (animatedScreenElement.getAbsoluteLeft() + animatedScreenElement.getWidth()), (int) (animatedScreenElement.getAbsoluteTop() + animatedScreenElement.getHeight())));
    }

    public void performAccessibilityAction(final int i2, final int i3) {
        final View view = this.mHostViewRef.get();
        if (view != null) {
            view.post(new Runnable() { // from class: com.miui.maml.util.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2564a.lambda$performAccessibilityAction$0(view, i2, i3);
                }
            });
        }
    }

    public void removeRoot() {
        ScreenElementRoot screenElementRoot = this.mRootRef.get();
        if (screenElementRoot != null) {
            screenElementRoot.clearMamlAccessHelper();
        }
    }
}
