package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.CallSuper;
import androidx.annotation.StyleRes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public class ComponentDialog extends Dialog implements LifecycleOwner, OnBackPressedDispatcherOwner, SavedStateRegistryOwner {
    private LifecycleRegistry _lifecycleRegistry;
    private final OnBackPressedDispatcher onBackPressedDispatcher;
    private final SavedStateRegistryController savedStateRegistryController;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context) {
        this(context, 0, 2, null);
        n.g(context, "context");
    }

    private final LifecycleRegistry getLifecycleRegistry() {
        LifecycleRegistry lifecycleRegistry = this._lifecycleRegistry;
        if (lifecycleRegistry != null) {
            return lifecycleRegistry;
        }
        LifecycleRegistry lifecycleRegistry2 = new LifecycleRegistry(this);
        this._lifecycleRegistry = lifecycleRegistry2;
        return lifecycleRegistry2;
    }

    public static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBackPressedDispatcher$lambda$1(ComponentDialog this$0) {
        n.g(this$0, "this$0");
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        n.g(view, "view");
        initializeViewTreeOwners();
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return getLifecycleRegistry();
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.onBackPressedDispatcher;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistryController.getSavedStateRegistry();
    }

    @CallSuper
    public void initializeViewTreeOwners() {
        Window window = getWindow();
        n.d(window);
        View decorView = window.getDecorView();
        n.f(decorView, "window!!.decorView");
        ViewTreeLifecycleOwner.set(decorView, this);
        Window window2 = getWindow();
        n.d(window2);
        View decorView2 = window2.getDecorView();
        n.f(decorView2, "window!!.decorView");
        ViewTreeOnBackPressedDispatcherOwner.set(decorView2, this);
        Window window3 = getWindow();
        n.d(window3);
        View decorView3 = window3.getDecorView();
        n.f(decorView3, "window!!.decorView");
        ViewTreeSavedStateRegistryOwner.set(decorView3, this);
    }

    @Override // android.app.Dialog
    @CallSuper
    public void onBackPressed() {
        this.onBackPressedDispatcher.onBackPressed();
    }

    @Override // android.app.Dialog
    @CallSuper
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        OnBackPressedDispatcher onBackPressedDispatcher = this.onBackPressedDispatcher;
        OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
        n.f(onBackInvokedDispatcher, "onBackInvokedDispatcher");
        onBackPressedDispatcher.setOnBackInvokedDispatcher(onBackInvokedDispatcher);
        this.savedStateRegistryController.performRestore(bundle);
        getLifecycleRegistry().handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        n.f(bundleOnSaveInstanceState, "super.onSaveInstanceState()");
        this.savedStateRegistryController.performSave(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    @CallSuper
    public void onStart() {
        super.onStart();
        getLifecycleRegistry().handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Dialog
    @CallSuper
    public void onStop() {
        getLifecycleRegistry().handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        this._lifecycleRegistry = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public void setContentView(int i2) {
        initializeViewTreeOwners();
        super.setContentView(i2);
    }

    public /* synthetic */ ComponentDialog(Context context, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentDialog(Context context, @StyleRes int i2) {
        super(context, i2);
        n.g(context, "context");
        this.savedStateRegistryController = SavedStateRegistryController.Companion.create(this);
        this.onBackPressedDispatcher = new OnBackPressedDispatcher(new Runnable() { // from class: androidx.activity.f
            @Override // java.lang.Runnable
            public final void run() {
                ComponentDialog.onBackPressedDispatcher$lambda$1(this.f1118a);
            }
        });
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        n.g(view, "view");
        initializeViewTreeOwners();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        n.g(view, "view");
        initializeViewTreeOwners();
        super.setContentView(view, layoutParams);
    }
}
