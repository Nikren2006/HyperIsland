package miui.systemui.controlcenter.windowview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterContent;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.util.InjectionInflationController;
import miui.systemui.util.ThemeUtils;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterWindowViewCreator implements LifecycleOwner {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ControlCenterWindowViewCreator";
    private final u _windowView;
    private final E0.a context;
    private final E0.a injectionInflationController;
    private final LifecycleRegistry lifecycleRegistry;
    private final I windowView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public ControlCenterWindowViewCreator(@Plugin E0.a context, E0.a injectionInflationController) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(injectionInflationController, "injectionInflationController");
        this.context = context;
        this.injectionInflationController = injectionInflationController;
        u uVarA = K.a(null);
        this._windowView = uVarA;
        this.windowView = AbstractC0420h.b(uVarA);
        this.lifecycleRegistry = new LifecycleRegistry(this);
    }

    private final void destroyView() {
        ControlCenterWindowViewImpl controlCenterWindowViewImpl = (ControlCenterWindowViewImpl) this._windowView.getValue();
        if (controlCenterWindowViewImpl != null) {
            Log.w(TAG, "Destroy window view " + controlCenterWindowViewImpl + ".");
            controlCenterWindowViewImpl.destroy();
        }
        this._windowView.setValue(null);
    }

    public final ControlCenterContent createView() throws IllegalAccessException {
        ControlCenterWindowViewImpl root;
        destroyView();
        u uVar = this._windowView;
        if (((Context) this.context.get()).getResources().getBoolean(R.bool.use_plugin_control_center)) {
            ThemeUtils themeUtils = ThemeUtils.INSTANCE;
            Object obj = this.context.get();
            kotlin.jvm.internal.n.f(obj, "get(...)");
            themeUtils.fixResourcesPackage((Context) obj);
            InjectionInflationController injectionInflationController = (InjectionInflationController) this.injectionInflationController.get();
            LayoutInflater layoutInflaterFrom = LayoutInflater.from((Context) this.context.get());
            kotlin.jvm.internal.n.f(layoutInflaterFrom, "from(...)");
            root = ControlCenterBinding.inflate(injectionInflationController.injectable(layoutInflaterFrom)).getRoot();
        } else {
            Log.w(TAG, "Use plugin control center is disable.");
            root = null;
        }
        uVar.setValue(root);
        ControlCenterWindowViewImpl controlCenterWindowViewImpl = (ControlCenterWindowViewImpl) this._windowView.getValue();
        if (controlCenterWindowViewImpl != null) {
            return controlCenterWindowViewImpl.getWindowViewController();
        }
        return null;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final I getWindowView() {
        return this.windowView;
    }

    public final void onPluginDestroy() {
        destroyView();
    }
}
