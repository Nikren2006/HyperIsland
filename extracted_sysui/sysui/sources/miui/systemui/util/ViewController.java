package miui.systemui.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ViewController<T extends View> {
    private boolean inited;
    private final View.OnAttachStateChangeListener onAttachStateListener;
    private boolean startedOnce;
    private final T view;

    public ViewController(T view) {
        kotlin.jvm.internal.n.g(view, "view");
        this.view = view;
        this.onAttachStateListener = new View.OnAttachStateChangeListener(this) { // from class: miui.systemui.util.ViewController$onAttachStateListener$1
            final /* synthetic */ ViewController<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
                kotlin.jvm.internal.n.g(v2, "v");
                this.this$0.onViewAttached();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                kotlin.jvm.internal.n.g(v2, "v");
                this.this$0.onViewDetached();
            }
        };
    }

    public final void addOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    public final Context getContext() {
        Context context = this.view.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        return context;
    }

    public final boolean getInited() {
        return this.inited;
    }

    public final View.OnAttachStateChangeListener getOnAttachStateListener() {
        return this.onAttachStateListener;
    }

    public final Resources getResources() {
        Resources resources = this.view.getResources();
        kotlin.jvm.internal.n.f(resources, "getResources(...)");
        return resources;
    }

    public final boolean getStartedOnce() {
        return this.startedOnce;
    }

    public final T getView() {
        return this.view;
    }

    public void init() {
        if (this.inited) {
            return;
        }
        onCreate();
        this.inited = true;
        if (isAttachedToWindow()) {
            this.onAttachStateListener.onViewAttachedToWindow(this.view);
        }
        addOnAttachStateChangeListener(this.onAttachStateListener);
    }

    public final boolean isAttachedToWindow() {
        return this.view.isAttachedToWindow();
    }

    public void onCreate() {
    }

    public void onViewAttached() {
    }

    public void onViewDetached() {
    }

    public final void removeOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
    }

    public final void setInited(boolean z2) {
        this.inited = z2;
    }

    public final void setStartedOnce(boolean z2) {
        this.startedOnce = z2;
    }
}
