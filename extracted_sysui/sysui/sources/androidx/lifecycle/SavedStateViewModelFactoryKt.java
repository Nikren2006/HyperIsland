package androidx.lifecycle;

import I0.AbstractC0181i;
import I0.AbstractC0184l;
import I0.m;
import android.app.Application;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class SavedStateViewModelFactoryKt {
    private static final List<Class<?>> ANDROID_VIEWMODEL_SIGNATURE = m.j(Application.class, SavedStateHandle.class);
    private static final List<Class<?>> VIEWMODEL_SIGNATURE = AbstractC0184l.d(SavedStateHandle.class);

    public static final <T> Constructor<T> findMatchingConstructor(Class<T> modelClass, List<? extends Class<?>> signature) {
        n.g(modelClass, "modelClass");
        n.g(signature, "signature");
        Object[] constructors = modelClass.getConstructors();
        n.f(constructors, "modelClass.constructors");
        for (Object obj : constructors) {
            Constructor<T> constructor = (Constructor<T>) obj;
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            n.f(parameterTypes, "constructor.parameterTypes");
            List listS = AbstractC0181i.S(parameterTypes);
            if (n.c(signature, listS)) {
                n.e(constructor, "null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
                return constructor;
            }
            if (signature.size() == listS.size() && listS.containsAll(signature)) {
                throw new UnsupportedOperationException("Class " + modelClass.getSimpleName() + " must have parameters in the proper order: " + signature);
            }
        }
        return null;
    }

    public static final <T extends ViewModel> T newInstance(Class<T> modelClass, Constructor<T> constructor, Object... params) {
        n.g(modelClass, "modelClass");
        n.g(constructor, "constructor");
        n.g(params, "params");
        try {
            return constructor.newInstance(Arrays.copyOf(params, params.length));
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to access " + modelClass, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("A " + modelClass + " cannot be instantiated.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("An exception happened in constructor of " + modelClass, e4.getCause());
        }
    }
}
