package miui.systemui.controlcenter.windowview;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.CallSuper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class GestureDispatcher extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "GestureDispatcher";
    private boolean accepted;
    private boolean attached;
    private float initX;
    private float initY;
    private final ArrayList<GestureHelper> queue;
    private boolean started;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface GestureAcceptor {
        GestureHelper createGestureHelper(GestureDispatcher gestureDispatcher);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GestureDispatcher(ControlCenterWindowViewImpl windowViewImpl, E0.a windowViewController) {
        super(windowViewImpl);
        kotlin.jvm.internal.n.g(windowViewImpl, "windowViewImpl");
        kotlin.jvm.internal.n.g(windowViewController, "windowViewController");
        this.windowViewController = windowViewController;
        this.queue = new ArrayList<>();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c0, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ff, code lost:
    
        r8 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatch(miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper r10, android.view.MotionEvent r11) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.windowview.GestureDispatcher.dispatch(miui.systemui.controlcenter.windowview.GestureDispatcher$GestureHelper, android.view.MotionEvent):void");
    }

    public final void finish() {
        if (this.started) {
            this.started = false;
            this.accepted = false;
            Iterator<T> it = this.queue.iterator();
            while (it.hasNext()) {
                ((GestureHelper) it.next()).reset();
            }
            this.queue.clear();
        }
    }

    public final boolean getAccepted() {
        return this.accepted;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        finish();
    }

    @Override // miui.systemui.util.ViewController
    public void onViewAttached() {
        this.attached = true;
    }

    @Override // miui.systemui.util.ViewController
    public void onViewDetached() {
        this.attached = false;
    }

    public final void start(GestureHelper gestureHelper, MotionEvent event) {
        View view;
        kotlin.jvm.internal.n.g(event, "event");
        String simpleName = (gestureHelper == null || (view = gestureHelper.getView()) == null) ? null : view.getClass().getSimpleName();
        Log.v(TAG, "gesture start with " + simpleName + " " + ControlCenterUtils.INSTANCE.getEventActionDesc(Integer.valueOf(event.getActionMasked())));
        if (this.attached && event.getActionMasked() == 0) {
            this.accepted = false;
            if (!this.started) {
                this.queue.clear();
                this.initX = event.getRawX();
                this.initY = event.getRawY();
                this.started = true;
            }
            if (gestureHelper != null) {
                this.queue.add(gestureHelper);
            }
        }
    }

    public static class GestureHelper {
        private boolean gestureAccept;
        private final GestureDispatcher gestureDispatcher;
        private final WeakReference<View> target;
        private final Boolean vertical;
        private final View view;

        public GestureHelper(View view, GestureDispatcher gestureDispatcher, Boolean bool) {
            kotlin.jvm.internal.n.g(view, "view");
            kotlin.jvm.internal.n.g(gestureDispatcher, "gestureDispatcher");
            this.view = view;
            this.gestureDispatcher = gestureDispatcher;
            this.vertical = bool;
            this.target = new WeakReference<>(view);
        }

        public final void accept() {
            View view = this.target.get();
            Log.d(GestureDispatcher.TAG, "gesture accepted by " + (view != null ? view.getClass().getSimpleName() : null));
            this.gestureAccept = true;
            onAccept();
        }

        public boolean check(boolean z2, boolean z3) {
            return kotlin.jvm.internal.n.c(Boolean.valueOf(z2), this.vertical);
        }

        public final boolean getGestureAccept() {
            return this.gestureAccept;
        }

        public final View getView() {
            return this.view;
        }

        public boolean intercept(boolean z2, boolean z3) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public void onAccept() {
            View view = this.target.get();
            if (view != 0) {
                ControlCenterUtils.INSTANCE.requestParentDisallowInterceptTouchEvent(view, true);
                if (view instanceof ViewParent) {
                    ((ViewParent) view).requestDisallowInterceptTouchEvent(false);
                }
            }
        }

        @CallSuper
        public boolean onInterceptTouchEvent(MotionEvent event) {
            kotlin.jvm.internal.n.g(event, "event");
            if (event.getActionMasked() != 2) {
                View view = this.target.get();
                String simpleName = view != null ? view.getClass().getSimpleName() : null;
                Log.v(GestureDispatcher.TAG, "gesture intercept " + simpleName + " " + ControlCenterUtils.INSTANCE.getEventActionDesc(Integer.valueOf(event.getActionMasked())));
            }
            int actionMasked = event.getActionMasked();
            if (actionMasked == 0) {
                this.gestureDispatcher.start(this, event);
                reset();
                return false;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (this.gestureDispatcher.getAccepted()) {
                        return this.gestureAccept;
                    }
                    this.gestureDispatcher.dispatch(this, event);
                    return this.gestureAccept;
                }
                if (actionMasked != 3) {
                    return this.gestureAccept;
                }
            }
            this.gestureDispatcher.finish();
            if (this.gestureAccept) {
                reset();
                return true;
            }
            reset();
            return false;
        }

        public void onReset() {
            View view = this.target.get();
            if (view != null) {
                ControlCenterUtils.INSTANCE.requestParentDisallowInterceptTouchEvent(view, false);
            }
        }

        public void onTouchEvent(MotionEvent event) {
            kotlin.jvm.internal.n.g(event, "event");
            this.view.onTouchEvent(event);
        }

        public final void reset() {
            this.gestureAccept = false;
            onReset();
        }

        public final void setGestureAccept(boolean z2) {
            this.gestureAccept = z2;
        }

        public final boolean touch() {
            return this.gestureDispatcher.getAccepted() && this.gestureAccept;
        }

        public /* synthetic */ GestureHelper(View view, GestureDispatcher gestureDispatcher, Boolean bool, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(view, gestureDispatcher, (i2 & 4) != 0 ? Boolean.FALSE : bool);
        }
    }
}
