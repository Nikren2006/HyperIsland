package miui.systemui.dagger;

import android.content.Context;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import miui.systemui.util.InjectionInflationController;

/* JADX INFO: loaded from: classes.dex */
public class InjectionInflationControllerImpl implements InjectionInflationController {
    private static final String TAG = "InjectionInflater";
    private final ViewCreator mViewCreator;
    private final ArrayMap<String, Method> mInjectionMap = new ArrayMap<>();
    private final LayoutInflater.Factory2 mFactory = new InjectionFactory();

    public class InjectionFactory implements LayoutInflater.Factory2 {
        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            Method method = (Method) InjectionInflationControllerImpl.this.mInjectionMap.get(str);
            if (method == null) {
                return null;
            }
            ViewAttributeProvider viewAttributeProvider = new ViewAttributeProvider(context, attributeSet);
            try {
                for (Method method2 : ViewCreator.class.getDeclaredMethods()) {
                    if (method2.getReturnType() == method.getDeclaringClass()) {
                        return (View) method.invoke(method2.invoke(InjectionInflationControllerImpl.this.mViewCreator, viewAttributeProvider), null);
                    }
                }
                throw new InflateException("Could not inflate " + str + ", creator method not found");
            } catch (IllegalAccessException e2) {
                throw new InflateException("Could not inflate " + str, e2);
            } catch (InvocationTargetException e3) {
                throw new InflateException("Could not inflate " + str, e3);
            }
        }

        private InjectionFactory() {
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return onCreateView(str, context, attributeSet);
        }
    }

    public InjectionInflationControllerImpl(PluginComponent pluginComponent) {
        this.mViewCreator = pluginComponent.createViewCreator();
        initInjectionMap();
    }

    private void initInjectionMap() {
        for (Method method : ViewCreator.class.getDeclaredMethods()) {
            try {
                for (Method method2 : method.getReturnType().getDeclaredMethods()) {
                    if (View.class.isAssignableFrom(method2.getReturnType()) && (method2.getModifiers() & 1) != 0) {
                        this.mInjectionMap.put(method2.getReturnType().getName(), method2);
                    }
                }
            } catch (NoClassDefFoundError e2) {
                Log.e(TAG, "init view map for " + method.getReturnType().getSimpleName() + " failed", e2);
            }
        }
    }

    public ViewCreator getFragmentCreator() {
        return this.mViewCreator;
    }

    public ArrayMap<String, Method> getInjectionMap() {
        return this.mInjectionMap;
    }

    @Override // miui.systemui.util.InjectionInflationController
    public LayoutInflater injectable(LayoutInflater layoutInflater) {
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(layoutInflater.getContext());
        layoutInflaterCloneInContext.setPrivateFactory(this.mFactory);
        return layoutInflaterCloneInContext;
    }
}
